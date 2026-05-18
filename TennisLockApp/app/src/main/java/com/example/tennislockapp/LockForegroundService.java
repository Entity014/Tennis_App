package com.example.tennislockapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import org.json.JSONObject;

public class LockForegroundService extends Service {

    private static final String TAG = "LockService";
    private static final String CHANNEL_ID = "LockServiceChannel";
    private static final int NOTIFICATION_ID = 101;

    public static final String ACTION_TIME_TICK = "com.example.tennislockapp.TIME_TICK";
    public static final String EXTRA_REMAINING_MS = "remaining_ms";
    public static final String ACTION_STOP_SERVICE = "com.example.tennislockapp.STOP_SERVICE";

    private Handler countdownHandler;
    private Runnable countdownRunnable;
    private long remainingMs = 0;

    private Handler heartbeatHandler;
    private Runnable heartbeatRunnable;

    // Relentless Kiosk Watcher to relaunch MainActivity if swiped away/killed
    private Handler lockWatcherHandler;
    private Runnable lockWatcherRunnable;

    private MyHttpClient httpClient;
    private String deviceId;

    @Override
    public void onCreate() {
        super.onCreate();
        httpClient = new MyHttpClient();
        deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACTION_STOP_SERVICE.equals(intent.getAction())) {
            stopSession(true);
            return START_NOT_STICKY;
        }

        SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
        boolean isActive = prefs.getBoolean("is_active", false);

        // If remaining time is passed in intent, update it
        if (intent != null && intent.hasExtra("duration_ms")) {
            long durationMs = intent.getLongExtra("duration_ms", 0);
            prefs.edit()
                 .putBoolean("is_active", true)
                 .putLong("remaining_ms", durationMs)
                 .putLong("last_save_time", System.currentTimeMillis())
                 .apply();
            isActive = true;
        }

        remainingMs = prefs.getLong("remaining_ms", 0);
        long lastSaveTime = prefs.getLong("last_save_time", System.currentTimeMillis());
        long elapsed = System.currentTimeMillis() - lastSaveTime;

        if (isActive && remainingMs > 0) {
            remainingMs -= elapsed;
            if (remainingMs < 0) remainingMs = 0;
        }

        // Keep foreground service alive persistently
        startForeground(NOTIFICATION_ID, buildNotification(remainingMs, isActive));

        // Start appropriate loops
        if (isActive && remainingMs > 0) {
            startCountdown();
            stopLockWatcher(); // No watcher needed when in active rental
        } else {
            stopCountdown();
            startLockWatcher(); // Watch persistently to enforce Kiosk locking
        }

        startHeartbeat();

        return START_STICKY;
    }

    private void startCountdown() {
        stopCountdown();
        countdownHandler = new Handler(Looper.getMainLooper());
        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (remainingMs > 0) {
                    remainingMs -= 1000;

                    SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
                    prefs.edit()
                         .putLong("remaining_ms", remainingMs)
                         .putLong("last_save_time", System.currentTimeMillis())
                         .apply();

                    Intent tickIntent = new Intent(ACTION_TIME_TICK);
                    tickIntent.putExtra(EXTRA_REMAINING_MS, remainingMs);
                    sendBroadcast(tickIntent);

                    // Update notification progress
                    if ((remainingMs / 1000) % 5 == 0) {
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        if (manager != null) {
                            manager.notify(NOTIFICATION_ID, buildNotification(remainingMs, true));
                        }
                    }

                    countdownHandler.postDelayed(this, 1000);
                } else {
                    Log.d(TAG, "Time expired! Locking device.");
                    stopSession(true);
                }
            }
        };
        countdownHandler.post(countdownRunnable);
    }

    private void stopCountdown() {
        if (countdownHandler != null) {
            countdownHandler.removeCallbacks(countdownRunnable);
            countdownHandler = null;
        }
    }

    private boolean isLockSecure() {
        android.app.admin.DevicePolicyManager dpm = (android.app.admin.DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        boolean isOwner = dpm != null && dpm.isDeviceOwnerApp(getPackageName());
        boolean hasOverlay = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasOverlay = Settings.canDrawOverlays(this);
        }
        boolean isLauncher = isDefaultLauncher();
        return isOwner || (isLauncher && hasOverlay);
    }

    private boolean isDefaultLauncher() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        android.content.pm.PackageManager pm = getPackageManager();
        android.content.pm.ResolveInfo resolveInfo = pm.resolveActivity(intent, android.content.pm.PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null && resolveInfo.activityInfo != null) {
            return getPackageName().equals(resolveInfo.activityInfo.packageName);
        }
        return false;
    }

    private void startLockWatcher() {
        stopLockWatcher();
        lockWatcherHandler = new Handler(Looper.getMainLooper());
        lockWatcherRunnable = new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
                boolean isActive = prefs.getBoolean("is_active", false);
                boolean isAdmin = prefs.getBoolean("is_admin_mode", false);
                boolean isPermanentlyUnlocked = prefs.getBoolean("is_permanently_unlocked", false);

                // If device is LOCKED, not in admin config mode, not permanently unlocked, and MainActivity is not visible, relaunch it instantly
                if (!isActive && !isAdmin && !isPermanentlyUnlocked && isLockSecure()) {
                    if (!MainActivity.isActivityVisible) {
                        Log.d(TAG, "MainActivity is hidden/killed in LOCKED state! Relaunching Kiosk Screen...");
                        Intent lockIntent = new Intent(LockForegroundService.this, MainActivity.class);
                        lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(lockIntent);
                    }
                }
                lockWatcherHandler.postDelayed(this, 200); // Check every 200 milliseconds (near-instant response)
            }
        };
        lockWatcherHandler.post(lockWatcherRunnable);
    }

    private void stopLockWatcher() {
        if (lockWatcherHandler != null) {
            lockWatcherHandler.removeCallbacks(lockWatcherRunnable);
            lockWatcherHandler = null;
        }
    }

    private void startHeartbeat() {
        if (heartbeatHandler != null) {
            heartbeatHandler.removeCallbacks(heartbeatRunnable);
        }
        heartbeatHandler = new Handler(Looper.getMainLooper());
        heartbeatRunnable = new Runnable() {
            @Override
            public void run() {
                sendHeartbeatToServer();
                heartbeatHandler.postDelayed(this, 15000); // Every 15 seconds
            }
        };
        heartbeatHandler.post(heartbeatRunnable);
    }

    private void sendHeartbeatToServer() {
        SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
        String serverUrl = prefs.getString("server_url", "http://192.168.1.100:3000");
        String url = serverUrl + "/api/device/heartbeat";

        boolean isActive = prefs.getBoolean("is_active", false);
        int batteryLevel = getBatteryLevel();

        try {
            JSONObject payload = new JSONObject();
            payload.put("device_id", deviceId);
            payload.put("battery_level", batteryLevel);
            payload.put("status", isActive ? "IN_USE" : "LOCKED");

            httpClient.post(url, payload.toString(), new MyHttpClient.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject json = new JSONObject(response);
                        if (json.has("pending_command")) {
                            String command = json.optString("pending_command", "");
                            if ("LOCK".equalsIgnoreCase(command) && isActive) {
                                Log.d(TAG, "Force Lock command received from server!");
                                stopSession(true);
                            } else if ("UNLOCK".equalsIgnoreCase(command) && !isActive) {
                                Log.d(TAG, "Force Unlock command received from server!");
                                startRemoteUnlock();
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to parse heartbeat: " + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "Heartbeat failed: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to build heartbeat payload: " + e.getMessage());
        }
    }

    private void startRemoteUnlock() {
        SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
        long durationMs = 60 * 60 * 1000; // 60 minutes default session on remote unlock
        prefs.edit()
             .putBoolean("is_active", true)
             .putLong("remaining_ms", durationMs)
             .putLong("last_save_time", System.currentTimeMillis())
             .apply();

        remainingMs = durationMs;
        
        // Update notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, buildNotification(remainingMs, true));
        }

        // Relaunch MainActivity in active state
        Intent activeIntent = new Intent(this, MainActivity.class);
        activeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(activeIntent);

        startCountdown();
        stopLockWatcher();
    }

    private void stopSession(boolean showLockScreen) {
        stopCountdown();

        SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
        prefs.edit()
             .putLong("remaining_ms", 0)
             .putBoolean("is_active", false)
             .apply();

        remainingMs = 0;

        Intent tickIntent = new Intent(ACTION_TIME_TICK);
        tickIntent.putExtra(EXTRA_REMAINING_MS, 0L);
        sendBroadcast(tickIntent);

        // Update Notification to locked security state
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, buildNotification(0, false));
        }

        if (showLockScreen) {
            Intent lockIntent = new Intent(this, MainActivity.class);
            lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            lockIntent.putExtra("EXTRA_LOCK", true);
            startActivity(lockIntent);
            startLockWatcher(); // Force watch again
        } else {
            Intent unlockIntent = new Intent(this, MainActivity.class);
            unlockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            unlockIntent.putExtra("EXTRA_UNLOCK", true);
            startActivity(unlockIntent);
            stopLockWatcher(); // Full exit Admin unlock
        }
    }

    private int getBatteryLevel() {
        Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            return (int) ((level / (float) scale) * 100);
        }
        return 100;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Lock Service Security Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            serviceChannel.setDescription("Ensures device lock security and displays countdown updates");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }

    private Notification buildNotification(long remainingMs, boolean isActive) {
        String title = isActive ? "Rental Court Session Active" : "Rental Device Security Locked";
        String contentText = isActive ? "Remaining Session Time: " + formatTime(remainingMs) : "Rental Security Guard Running...";
        int icon = isActive ? android.R.drawable.ic_media_play : android.R.drawable.ic_secure;

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(contentText)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setColor(0xFF03DAC5)
                .build();
    }

    private String formatTime(long ms) {
        long seconds = ms / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        stopCountdown();
        stopLockWatcher();
        if (heartbeatHandler != null) {
            heartbeatHandler.removeCallbacks(heartbeatRunnable);
        }
        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "onTaskRemoved: Kiosk App swiped away! Relaunching security...");
        
        // Relaunch the service using AlarmManager to ensure persistence
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        
        PendingIntent restartServicePendingIntent = PendingIntent.getService(
                getApplicationContext(), 
                1, 
                restartServiceIntent, 
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
        );
        
        android.app.AlarmManager alarmService = (android.app.AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmService != null) {
            alarmService.set(
                    android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    android.os.SystemClock.elapsedRealtime() + 500,
                    restartServicePendingIntent
            );
        }

        // Also relaunch the MainActivity instantly to lock the screen!
        Intent restartActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        restartActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        getApplicationContext().startActivity(restartActivityIntent);
    }
}
