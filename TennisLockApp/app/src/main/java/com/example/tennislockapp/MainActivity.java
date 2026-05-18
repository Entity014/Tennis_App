package com.example.tennislockapp;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.TextView;
import android.app.role.RoleManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "LockAppPrefs";

    // Track activity visibility for background security watcher service
    public static boolean isActivityVisible = false;

    // Overlay view to block status bar pull-down
    private View statusBarBlockerView;

    // View States
    private View layoutLocked;
    private View layoutActive;
    private View layoutAdmin;

    // Locked View Elements
    private View[] pinDots;
    private String enteredPin = "";
    private View tvLockStatusMsg;
    private TextView tvLockDeviceId;

    // Active View Elements
    private TextView tvRemainingTime;
    private CircularProgressIndicator progressTimer;
    private TextView tvSessionBattery;
    private long initialDurationMs = 1;

    // Admin View Elements
    private TextView tvDeviceOwnerStatus;
    private TextView tvAdbCommand;
    private View layoutAdbBox;
    private View tvAdbInstructionsLabel;
    private View tvAdbHint;
    private TextInputEditText etServerUrl;
    private TextInputEditText etAdminPin;
    private MaterialButton btnExitKiosk;
    private TextView tvAccessibilityStatus;
    private MaterialButton btnEnableAccessibility;

    // Device Admin Details
    private DevicePolicyManager dpm;
    private ComponentName adminComponent;

    // Network Utility
    private MyHttpClient httpClient;
    private String deviceId;

    // Periodic Heartbeat for Locked State
    private Handler lockedHeartbeatHandler;
    private Runnable lockedHeartbeatRunnable;

    // Broadcast Receiver to sync with Foreground Service
    private final BroadcastReceiver serviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (LockForegroundService.ACTION_TIME_TICK.equals(intent.getAction())) {
                long remainingMs = intent.getLongExtra(LockForegroundService.EXTRA_REMAINING_MS, 0);
                updateActiveUI(remainingMs);
            }
        }
    };

    // Broadcast Receiver to monitor network state changes
    private final BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkNetworkAndSetupUI();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prevent screen from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Keep active layout fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        // Initialize Utilities
        httpClient = new MyHttpClient();
        deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        adminComponent = new ComponentName(this, LockDeviceAdminReceiver.class);

        // Bind Views
        initViews();

        // Start background security watcher service
        Intent serviceIntent = new Intent(this, LockForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }

        // Setup Buttons Click Listeners
        setupKeypad();
        setupAdminDashboard();
        setupActiveView();

        // Auto-Register Device to Backend Server (Ensures listing in dashboard)
        registerDeviceWithServer();

        // Check if started with action instructions
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent == null)
            return;

        if (intent.getBooleanExtra("EXTRA_LOCK", false)) {
            Log.d(TAG, "Intent command: FORCE LOCK");
            switchToLockedState();
        } else if (intent.getBooleanExtra("EXTRA_UNLOCK", false)) {
            Log.d(TAG, "Intent command: FORCE UNLOCK");
            switchToUnlockedState();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityVisible = true;

        // Register Broadcast Receiver for Time updates
        IntentFilter filter = new IntentFilter(LockForegroundService.ACTION_TIME_TICK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(serviceReceiver, filter, 4); // 4 is Context.RECEIVER_NOT_EXPORTED
        } else {
            registerReceiver(serviceReceiver, filter);
        }

        // Register Network status monitoring
        IntentFilter netFilter = new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(networkStateReceiver, netFilter, 2); // 2 is Context.RECEIVER_EXPORTED
        } else {
            registerReceiver(networkStateReceiver, netFilter);
        }
        checkNetworkAndSetupUI();

        // Determine correct state on resume
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isActive = prefs.getBoolean("is_active", false);
        boolean isAdmin = prefs.getBoolean("is_admin_mode", false);
        boolean isPermanentlyUnlocked = prefs.getBoolean("is_permanently_unlocked", false);
        long remainingMs = prefs.getLong("remaining_ms", 0);

        if (isAdmin || isPermanentlyUnlocked) {
            switchToAdminState();
        } else if (isActive && remainingMs > 0) {
            switchToActiveState(remainingMs);
        } else {
            switchToLockedState();
        }

        // Request Device Admin if not active, otherwise check Default Launcher, then Overlay permission
        if (dpm != null && !dpm.isAdminActive(adminComponent)) {
            showActivateAdminDialog();
        } else if (!isDefaultLauncher()) {
            showDefaultLauncherDialog();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            showOverlayPermissionDialog();
        }

        // Request notifications permission for Android 13+
        requestNotificationPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActivityVisible = false;
        unregisterReceiver(serviceReceiver);
        try {
            unregisterReceiver(networkStateReceiver);
        } catch (Exception ignored) {}
        stopLockedHeartbeat();
    }

    private void initViews() {
        // Core Layout Wrappers
        layoutLocked = findViewById(R.id.layout_locked);
        layoutActive = findViewById(R.id.layout_active);
        layoutAdmin = findViewById(R.id.layout_admin);

        // Locked screen elements
        pinDots = new View[] {
                findViewById(R.id.dot1),
                findViewById(R.id.dot2),
                findViewById(R.id.dot3),
                findViewById(R.id.dot4),
                findViewById(R.id.dot5),
                findViewById(R.id.dot6)
        };
        tvLockStatusMsg = findViewById(R.id.tv_lock_status_msg);
        tvLockDeviceId = findViewById(R.id.tv_lock_device_id);
        tvLockDeviceId.setText("Device ID: " + deviceId);

        // Active screen elements
        tvRemainingTime = findViewById(R.id.tv_remaining_time);
        progressTimer = findViewById(R.id.progress_timer);
        tvSessionBattery = findViewById(R.id.tv_session_battery);

        // Admin Dashboard elements
        tvDeviceOwnerStatus = findViewById(R.id.tv_device_owner_status);
        tvAdbInstructionsLabel = findViewById(R.id.tv_adb_instructions_label);
        layoutAdbBox = findViewById(R.id.layout_adb_box);
        tvAdbCommand = findViewById(R.id.tv_adb_command);
        tvAdbHint = findViewById(R.id.tv_adb_hint);
        etServerUrl = findViewById(R.id.et_server_url);
        etAdminPin = findViewById(R.id.et_admin_pin);
        btnExitKiosk = findViewById(R.id.btn_exit_kiosk);
        tvAccessibilityStatus = findViewById(R.id.tv_accessibility_status);
        btnEnableAccessibility = findViewById(R.id.btn_enable_accessibility);

        // Load SharedPreferences to Admin Panel Inputs
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        etServerUrl.setText(prefs.getString("server_url", "http://192.168.1.100:3000"));
        etAdminPin.setText(prefs.getString("admin_pin", "999999"));
    }

    // ==================== STATE TRANSITIONS ====================

    private boolean isLockSecure() {
        boolean isOwner = dpm != null && dpm.isDeviceOwnerApp(getPackageName());
        boolean hasOverlay = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hasOverlay = Settings.canDrawOverlays(this);
        }
        boolean isLauncher = isDefaultLauncher();
        return isOwner || (isLauncher && hasOverlay);
    }

    private void switchToLockedState() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putBoolean("is_admin_mode", false)
                .putBoolean("is_permanently_unlocked", false)
                .apply();

        // Block uninstallation for renter/unauthorized user
        setUninstallBlockedState(true);

        layoutLocked.setVisibility(View.VISIBLE);
        layoutActive.setVisibility(View.GONE);
        layoutAdmin.setVisibility(View.GONE);
        enteredPin = "";
        updatePinDots();

        if (!isLockSecure()) {
            setLockStatusText("⚠️ Setup Incomplete! Click 'Admin' below to configure launcher/overlay.", 0xFFFF6200);
        } else {
            setLockStatusText("Enter PIN to activate device", 0xFFB3FFFF);
        }

        // Show overlay to block status bar pull-down
        showStatusBarBlocker();

        // Enforce Kiosk Locking (Start lock task mode)
        startLockdown();

        // Enable Immersive Mode to hide status and navigation bars and block swipe gestures
        enableImmersiveMode(true);

        // Start Heartbeat to listen for server force unlock commands
        startLockedHeartbeat();
    }

    private void switchToActiveState(long durationMs) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putBoolean("is_active", true)
                .putBoolean("is_admin_mode", false)
                .putBoolean("is_permanently_unlocked", false)
                .apply();

        // Block uninstallation during active rental session!
        setUninstallBlockedState(true);

        layoutLocked.setVisibility(View.GONE);
        layoutActive.setVisibility(View.VISIBLE);
        layoutAdmin.setVisibility(View.GONE);

        // Keep status bar blocked during active session to prevent Wi-Fi swipe down!
        showStatusBarBlocker();

        // Keep Kiosk Locking active to block swipe-out (Home/Recents/Back)!
        startLockdown();

        // Keep Immersive Mode active to hide navigation keys!
        enableImmersiveMode(true);

        initialDurationMs = durationMs;
        updateActiveUI(durationMs);

        // Stop locked heartbeat since service will do heartbeats
        stopLockedHeartbeat();

        // Start foreground service if not already running
        Intent serviceIntent = new Intent(this, LockForegroundService.class);
        serviceIntent.putExtra("duration_ms", durationMs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    private void switchToUnlockedState() {
        // Transition to standard unlocked mode (released state)
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit()
                .putBoolean("is_active", false)
                .putBoolean("is_admin_mode", false)
                .putBoolean("is_permanently_unlocked", true)
                .putLong("remaining_ms", 0L)
                .apply();

        Intent serviceIntent = new Intent(this, LockForegroundService.class);
        stopService(serviceIntent);

        // Hide overlay to allow status bar pull-down
        hideStatusBarBlocker();

        // Disable Immersive Mode
        enableImmersiveMode(false);

        // Allow app uninstallation for admin!
        setUninstallBlockedState(false);

        releaseLockdown(true); // Completely clear Kiosk restrictions

        layoutLocked.setVisibility(View.GONE);
        layoutActive.setVisibility(View.GONE);
        layoutAdmin.setVisibility(View.GONE);
        enteredPin = "";
        updatePinDots();

        Toast.makeText(this, "Device unlocked successfully. Exiting...", Toast.LENGTH_LONG).show();

        // Close the activity so the user can use the phone normally!
        finish();
    }

    private void switchToAdminState() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().putBoolean("is_admin_mode", true).apply();

        // Allow app uninstallation for admin!
        setUninstallBlockedState(false);

        layoutLocked.setVisibility(View.GONE);
        layoutActive.setVisibility(View.GONE);
        layoutAdmin.setVisibility(View.VISIBLE);

        // Hide overlay to allow status bar pull-down for settings
        hideStatusBarBlocker();

        // Disable Immersive Mode
        enableImmersiveMode(false);

        stopLockedHeartbeat();

        // Update Device Owner UI details
        boolean isOwner = dpm.isDeviceOwnerApp(getPackageName());
        if (isOwner) {
            tvDeviceOwnerStatus.setText("ACTIVE");
            tvDeviceOwnerStatus.setTextColor(0xFF03DAC5); // Teal color
            tvAdbInstructionsLabel.setVisibility(View.GONE);
            layoutAdbBox.setVisibility(View.GONE);
            tvAdbHint.setVisibility(View.GONE);
            btnExitKiosk.setEnabled(true);
        } else {
            tvDeviceOwnerStatus.setText("INACTIVE");
            tvDeviceOwnerStatus.setTextColor(0xFFFF6200); // Orange/Red color
            tvAdbInstructionsLabel.setVisibility(View.VISIBLE);
            layoutAdbBox.setVisibility(View.VISIBLE);
            tvAdbHint.setVisibility(View.VISIBLE);
            btnExitKiosk.setEnabled(true); // Always enable exit kiosk button for Admin!
        }

        // Update Accessibility Guard UI details
        boolean isAccessibilityEnabled = isAccessibilityServiceEnabled();
        if (isAccessibilityEnabled) {
            tvAccessibilityStatus.setText("ACTIVE");
            tvAccessibilityStatus.setTextColor(0xFF03DAC5); // Teal color
            btnEnableAccessibility.setText("Accessibility Guard is Active");
            btnEnableAccessibility.setEnabled(false);
        } else {
            tvAccessibilityStatus.setText("INACTIVE");
            tvAccessibilityStatus.setTextColor(0xFFFF6200); // Orange/Red color
            btnEnableAccessibility.setText("Activate Accessibility Guard");
            btnEnableAccessibility.setEnabled(true);
            btnEnableAccessibility.setOnClickListener(v -> openAccessibilitySettings());
        }
    }

    private boolean isAccessibilityServiceEnabled() {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + LockAccessibilityService.class.getName();
        try {
            accessibilityEnabled = android.provider.Settings.Secure.getInt(
                    getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (android.provider.Settings.SettingNotFoundException ignored) {
        }
        android.text.TextUtils.SimpleStringSplitter mStringColonSplitter = new android.text.TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1) {
            String settingValue = android.provider.Settings.Secure.getString(
                    getContentResolver(),
                    android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void openAccessibilitySettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    private void setUninstallBlockedState(boolean blocked) {
        try {
            boolean isOwner = dpm != null && dpm.isDeviceOwnerApp(getPackageName());
            if (isOwner) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dpm.setUninstallBlocked(adminComponent, getPackageName(), blocked);
                    Log.d(TAG, "Uninstall blocked state updated to: " + blocked);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting uninstall blocked state", e);
        }
    }

    // ==================== KEYPAD CONTROLS ====================

    private void setupKeypad() {
        int[] keyIds = {
                R.id.btn_key_0, R.id.btn_key_1, R.id.btn_key_2, R.id.btn_key_3,
                R.id.btn_key_4, R.id.btn_key_5, R.id.btn_key_6, R.id.btn_key_7,
                R.id.btn_key_8, R.id.btn_key_9
        };

        View.OnClickListener digitListener = v -> {
            MaterialButton btn = (MaterialButton) v;
            if (enteredPin.length() < 6) {
                enteredPin += btn.getText().toString();
                updatePinDots();

                // Auto-enter on 6 digits
                if (enteredPin.length() == 6) {
                    processEnteredPin();
                }
            }
        };

        for (int id : keyIds) {
            findViewById(id).setOnClickListener(digitListener);
        }

        findViewById(R.id.btn_key_clear).setOnClickListener(v -> {
            if (enteredPin.length() > 0) {
                enteredPin = enteredPin.substring(0, enteredPin.length() - 1);
                updatePinDots();
            }
        });

        findViewById(R.id.btn_key_enter).setOnClickListener(v -> processEnteredPin());
    }

    private void updatePinDots() {
        for (int i = 0; i < pinDots.length; i++) {
            if (i < enteredPin.length()) {
                pinDots[i].setAlpha(1.0f);
            } else {
                pinDots[i].setAlpha(0.3f);
            }
        }
    }

    private void processEnteredPin() {
        if (enteredPin.length() < 6) {
            Toast.makeText(this, "PIN must be 6 digits", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Check if matches Admin PIN locally
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String adminPin = prefs.getString("admin_pin", "999999");
        if (enteredPin.equals(adminPin)) {
            enteredPin = "";
            updatePinDots();
            switchToAdminState();
            return;
        }

        // 2. Otherwise, verify PIN online with backend server
        verifyPinWithServer(enteredPin);
    }

    // ==================== NETWORKS SYNC ====================

    private void verifyPinWithServer(final String pin) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String serverUrl = prefs.getString("server_url", "http://192.168.1.100:3000");
        String url = serverUrl + "/api/device/verify-pin";

        setLockStatusText("Verifying PIN...", 0xFFB3FFFF);
        disableKeypad(true);

        try {
            JSONObject payload = new JSONObject();
            payload.put("device_id", deviceId);
            payload.put("pin", pin);

            httpClient.post(url, payload.toString(), new MyHttpClient.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    disableKeypad(false);
                    try {
                        JSONObject json = new JSONObject(response);
                        long durationMinutes = json.optLong("duration_minutes", 0);
                        if (durationMinutes > 0) {
                            long durationMs = durationMinutes * 60 * 1000;

                            // Save session state locally
                            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                                    .putBoolean("is_active", true)
                                    .putLong("remaining_ms", durationMs)
                                    .putLong("last_save_time", System.currentTimeMillis())
                                    .apply();

                            setLockStatusText("PIN Verified! Unlocking...", 0xFF03DAC5);
                            switchToActiveState(durationMs);
                        } else {
                            handlePinError("Invalid duration returned from server");
                        }
                    } catch (Exception e) {
                        handlePinError("Failed to parse response");
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    disableKeypad(false);
                    String errMsg = e.getMessage();
                    String displayErr = "Invalid PIN";

                    if (errMsg != null && (errMsg.contains("Connect") || errMsg.contains("Failed to connect") || errMsg.contains("timeout"))) {
                        displayErr = "Cannot connect to server. Check IP Address & Port.";
                    }

                    handlePinError(displayErr);
                }
            });

        } catch (Exception e) {
            disableKeypad(false);
            handlePinError("Error preparing request");
        }
    }

    private void handlePinError(String errorMsg) {
        setLockStatusText(errorMsg, 0xFFE040FB); // Magenta glow error
        enteredPin = "";
        updatePinDots();

        // Shake screen/vibrate feedback if desired
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            setLockStatusText("Enter PIN to activate device", 0xFFB3FFFF);
        }, 3000);
    }

    private void registerDeviceWithServer() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String serverUrl = prefs.getString("server_url", "http://192.168.1.100:3000");
        String url = serverUrl + "/api/device/register";

        try {
            JSONObject payload = new JSONObject();
            payload.put("device_id", deviceId);

            httpClient.post(url, payload.toString(), new MyHttpClient.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.d(TAG, "Device registered successfully with server: " + response);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e(TAG, "Auto-registration failed: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to compile register payload: " + e.getMessage());
        }
    }

    // ==================== ACTIVE SESSION STATE ====================

    private void setupActiveView() {
        findViewById(R.id.btn_finish_session).setOnClickListener(v -> {
            // Early end session
            switchToLockedState();

            // Stop Foreground Countdown Service
            Intent serviceIntent = new Intent(MainActivity.this, LockForegroundService.class);
            stopService(serviceIntent);

            // Notify server of unlock (LOCKED)
            getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()
                    .putBoolean("is_active", false)
                    .putLong("remaining_ms", 0L)
                    .apply();

            Toast.makeText(MainActivity.this, "Session terminated manually", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateActiveUI(long remainingMs) {
        if (remainingMs <= 0) {
            switchToLockedState();
            return;
        }

        // Format time to HH:MM:SS
        long seconds = remainingMs / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        String timeStr = String.format("%02d:%02d:%02d", hours, minutes, secs);
        tvRemainingTime.setText(timeStr);

        // Update progress circle
        if (initialDurationMs > 0) {
            int pct = (int) ((remainingMs * 100) / initialDurationMs);
            progressTimer.setProgress(pct);
        }

        // Update battery dynamic representation
        Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = 100;
        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            batteryLevel = (int) ((level / (float) scale) * 100);
        }
        tvSessionBattery.setText("Battery: " + batteryLevel + "% | Sync Status: ONLINE");
    }

    // ==================== ADMIN SETTINGS DASHBOARD ====================

    private void setupAdminDashboard() {
        findViewById(R.id.btn_save_settings).setOnClickListener(v -> {
            String url = etServerUrl.getText().toString().trim();
            String pin = etAdminPin.getText().toString().trim();

            if (TextUtils.isEmpty(url) || TextUtils.isEmpty(pin)) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pin.length() != 6) {
                Toast.makeText(this, "Admin PIN must be exactly 6 digits", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save Settings
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            prefs.edit()
                    .putString("server_url", url)
                    .putString("admin_pin", pin)
                    .apply();

            Toast.makeText(this, "Configuration Saved!", Toast.LENGTH_SHORT).show();

            // Trigger re-registration
            registerDeviceWithServer();

            // Return to Lock Screen
            switchToLockedState();
        });

        findViewById(R.id.btn_back_to_lock).setOnClickListener(v -> switchToLockedState());

        btnExitKiosk.setOnClickListener(v -> {
            // Completely terminate session, unlock Kiosk mode and close Kiosk restriction
            // policies
            switchToUnlockedState();
        });
    }

    // ==================== DEVICE OWNER (KIOSK HARD LOCK) ====================

    private void startLockdown() {
        try {
            boolean isOwner = dpm.isDeviceOwnerApp(getPackageName());
            if (isOwner) {
                // Set lock task whitelist packages
                dpm.setLockTaskPackages(adminComponent, new String[] { getPackageName() });

                // Lock screen (Device Owner way)
                startLockTask();

                // Explicitly disable notifications and quick settings panel in LockTask mode (Android 9+)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    dpm.setLockTaskFeatures(adminComponent, DevicePolicyManager.LOCK_TASK_FEATURE_SYSTEM_INFO);
                }

                // Enforce maximum kiosk security restrictions
                dpm.setStatusBarDisabled(adminComponent, true);
                dpm.setKeyguardDisabled(adminComponent, true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_SAFE_BOOT);
                    dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET);
                }

                // Add Wi-Fi, Airplane Mode, and Date/Time restrictions!
                dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_WIFI);
                dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_AIRPLANE_MODE);
                dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_DATE_TIME);
            } else {
                Log.w(TAG, "App is not Device Owner. Skipping startLockTask fallback to prevent standard pinning dialog.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error starting lockdown Kiosk mode", e);
        }
    }

    private void releaseLockdown(boolean disableFully) {
        try {
            // Exit Kiosk Mode
            stopLockTask();

            boolean isOwner = dpm.isDeviceOwnerApp(getPackageName());
            if (isOwner) {
                if (disableFully) {
                    // Reset lock task features to default
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        dpm.setLockTaskFeatures(adminComponent, DevicePolicyManager.LOCK_TASK_FEATURE_NONE);
                    }

                    // Completely clear all restrictions for the Admin!
                    dpm.setStatusBarDisabled(adminComponent, false);
                    dpm.setKeyguardDisabled(adminComponent, false);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_SAFE_BOOT);
                        dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET);
                    }
                    dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_WIFI);
                    dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_AIRPLANE_MODE);
                    dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_DATE_TIME);
                } else {
                    // During standard ACTIVE rental, block status bar pull-down system-wide
                    // to prevent users from turning off Wi-Fi/internet!
                    dpm.setStatusBarDisabled(adminComponent, true);
                    dpm.setKeyguardDisabled(adminComponent, false);

                    // BUT keep Wi-Fi, Airplane, safe boot, and factory reset restrictions active!
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_SAFE_BOOT);
                        dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET);
                    }
                    dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_WIFI);
                    dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_AIRPLANE_MODE);
                    dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_CONFIG_DATE_TIME);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error releasing lockdown Kiosk mode", e);
        }
    }

    // ==================== POLLED HEARTBEAT FOR LOCKED STATE ====================

    private void startLockedHeartbeat() {
        stopLockedHeartbeat();
        lockedHeartbeatHandler = new Handler(Looper.getMainLooper());
        lockedHeartbeatRunnable = new Runnable() {
            @Override
            public void run() {
                sendLockedHeartbeat();
                lockedHeartbeatHandler.postDelayed(this, 15000); // Every 15 seconds
            }
        };
        lockedHeartbeatHandler.post(lockedHeartbeatRunnable);
    }

    private void stopLockedHeartbeat() {
        if (lockedHeartbeatHandler != null) {
            lockedHeartbeatHandler.removeCallbacks(lockedHeartbeatRunnable);
            lockedHeartbeatHandler = null;
        }
    }

    private void sendLockedHeartbeat() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String serverUrl = prefs.getString("server_url", "http://192.168.1.100:3000");
        String url = serverUrl + "/api/device/heartbeat";

        Intent batteryStatus = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = 100;
        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            batteryLevel = (int) ((level / (float) scale) * 100);
        }

        try {
            JSONObject payload = new JSONObject();
            payload.put("device_id", deviceId);
            payload.put("battery_level", batteryLevel);
            payload.put("status", "LOCKED");

            httpClient.post(url, payload.toString(), new MyHttpClient.HttpCallback() {
                @Override
                public void onSuccess(String response) {
                    try {
                        JSONObject json = new JSONObject(response);
                        if (json.has("pending_command")) {
                            String cmd = json.optString("pending_command", "");
                            if ("UNLOCK".equalsIgnoreCase(cmd)) {
                                Log.d(TAG, "Received FORCE UNLOCK command from server!");
                                // Start a default active session (e.g., 60 minutes) when unlocked remotely
                                long durationMs = 60 * 60 * 1000;
                                SharedPreferences p = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                                p.edit()
                                        .putBoolean("is_active", true)
                                        .putLong("remaining_ms", durationMs)
                                        .putLong("last_save_time", System.currentTimeMillis())
                                        .apply();
                                switchToActiveState(durationMs);
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
            Log.e(TAG, "Failed to send locked heartbeat: " + e.getMessage());
        }
    }

    // ==================== HELPER METHODS ====================

    private void setLockStatusText(String text, int textColorHex) {
        if (tvLockStatusMsg instanceof TextView) {
            TextView tv = (TextView) tvLockStatusMsg;
            tv.setText(text);
            tv.setTextColor(textColorHex);
        }
    }

    private void disableKeypad(boolean disable) {
        int[] keyIds = {
                R.id.btn_key_0, R.id.btn_key_1, R.id.btn_key_2, R.id.btn_key_3,
                R.id.btn_key_4, R.id.btn_key_5, R.id.btn_key_6, R.id.btn_key_7,
                R.id.btn_key_8, R.id.btn_key_9, R.id.btn_key_clear, R.id.btn_key_enter
        };
        for (int id : keyIds) {
            findViewById(id).setEnabled(!disable);
        }
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // If API 33+, we request notifications permission
            if (checkSelfPermission(
                    android.Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] { android.Manifest.permission.POST_NOTIFICATIONS }, 101);
            }
        }
    }

    @Override
    public void onBackPressed() {
        // If locked state is active, suppress the back button completely
        if (layoutLocked.getVisibility() == View.VISIBLE) {
            // Do nothing, Kiosk screen lock
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        // If locked state is active and user tries to press Home or navigate away,
        // we instantly drag them back to our locked screen.
        if (layoutLocked.getVisibility() == View.VISIBLE && isLockSecure()) {
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if (layoutLocked.getVisibility() == View.VISIBLE) {
                    Intent forceFront = new Intent(MainActivity.this, MainActivity.class);
                    forceFront.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(forceFront);
                }
            }, 50);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // If the screen is locked and we lose focus (e.g. status bar pull, system
        // dialog pop, recents),
        // we collapse all menus and force reorder this Activity to the front.
        if (!hasFocus && layoutLocked.getVisibility() == View.VISIBLE && isLockSecure()) {
            try {
                // Collapse quick settings drawer (Status Bar Notifications Tray)
                Intent closeRecents = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                sendBroadcast(closeRecents);
            } catch (Exception ignored) {
            }

            // Force push MainActivity back to the foreground instantly
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if (layoutLocked.getVisibility() == View.VISIBLE) {
                    Intent forceFront = new Intent(MainActivity.this, MainActivity.class);
                    forceFront.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(forceFront);
                }
            }, 100);
        } else if (hasFocus && layoutLocked.getVisibility() == View.VISIBLE) {
            // When gaining focus in locked state, ensure Kiosk lockdown is fully active
            startLockdown();
            enableImmersiveMode(true);
        }
    }

    private void showActivateAdminDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("เปิดใช้งานผู้ดูแลระบบอุปกรณ์")
                .setMessage(
                        "แอปจำเป็นต้องได้รับสิทธิ์ 'ผู้ดูแลระบบอุปกรณ์' (Device Administrator) เพื่อให้สามารถล็อกหน้าจอและป้องกันการกดปิดแอปพลิเคชันได้\n\nโปรดกดปุ่ม 'เปิดใช้งาน' ในหน้าถัดไปเพื่อเริ่มล็อกหน้าจอ")
                .setPositiveButton("เปิดใช้งาน", (dialog, which) -> {
                    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminComponent);
                    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                            "สิทธิ์นี้ใช้สำหรับการล็อกหน้าจอและป้องกันการปัดแอปออกระหว่างเวลาเช่าเครื่อง");
                    startActivity(intent);
                })
                .setNegativeButton("ภายหลัง", null)
                .setCancelable(true)
                .show();
    }

    private boolean isDefaultLauncher() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        PackageManager pm = getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null && resolveInfo.activityInfo != null) {
            return getPackageName().equals(resolveInfo.activityInfo.packageName);
        }
        return false;
    }

    private void showDefaultLauncherDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("ตั้งค่าแอปหน้าแรกเริ่มต้น")
                .setMessage("เพื่อระบบความปลอดภัยสูงสุด ป้องกันผู้เช่าปัดหน้าจอหนีหรือกดปิดแอปพลิเคชัน\n\nโปรดตั้งค่า TennisLockApp ให้เป็น 'แอปหน้าแรกเริ่มต้น' (Default Home App)")
                .setPositiveButton("ตั้งค่าตอนนี้", (dialog, which) -> {
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            RoleManager roleManager = (RoleManager) getSystemService(Context.ROLE_SERVICE);
                            if (roleManager != null && roleManager.isRoleAvailable(RoleManager.ROLE_HOME)) {
                                Intent intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_HOME);
                                startActivity(intent);
                            }
                        } else {
                            Intent intent = new Intent(Settings.ACTION_HOME_SETTINGS);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        // General fallback: Launch Intent Chooser for Home
                        try {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(Intent.createChooser(intent, "ตั้งค่าเป็นแอปหน้าแรกเริ่มต้น"));
                        } catch (Exception ignored) {}
                    }
                })
                .setNegativeButton("ภายหลัง", null)
                .setCancelable(true)
                .show();
    }

    private void showOverlayPermissionDialog() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("ขอสิทธิ์แสดงทับแอปอื่น")
                .setMessage("เพื่อความปลอดภัยระดับสูงสุด ป้องกันผู้ใช้เปิดหน้าต่างตั้งค่าหรือดึงแถบแจ้งเตือนลงมาเพื่อปิดแอป\n\nโปรดอนุญาตสิทธิ์ 'แสดงทับแอปอื่น' (Draw over other apps / Overlay) ในหน้าถัดไป")
                .setPositiveButton("ตั้งค่าตอนนี้", (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        try {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    android.net.Uri.parse("package:" + getPackageName()));
                            startActivity(intent);
                        } catch (Exception e) {
                            Toast.makeText(this, "ไม่สามารถเปิดการตั้งค่าได้ โปรดเปิดด้วยตัวเอง", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("ภายหลัง", null)
                .setCancelable(true)
                .show();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        if (result == 0) {
            result = (int) (30 * getResources().getDisplayMetrics().density);
        }
        return result;
    }

    // Custom View to consume all touch events and prevent status bar pull-down
    private static class BlockerOverlayView extends android.view.ViewGroup {
        public BlockerOverlayView(Context context) {
            super(context);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            // No-op
        }

        @Override
        public boolean onInterceptTouchEvent(android.view.MotionEvent ev) {
            return true; // Intercept all touch events inside this view's area
        }

        @Override
        public boolean onTouchEvent(android.view.MotionEvent event) {
            return true; // Consume all touch events so they don't reach the system / status bar
        }
    }

    // Helper to toggle system-level full screen (Immersive Mode) to hide status/navigation bars
    private void enableImmersiveMode(boolean enable) {
        if (enable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                getWindow().setDecorFitsSystemWindows(false);
                android.view.WindowInsetsController controller = getWindow().getInsetsController();
                if (controller != null) {
                    controller.hide(android.view.WindowInsets.Type.statusBars() | android.view.WindowInsets.Type.navigationBars());
                    controller.setSystemBarsBehavior(android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                }
            } else {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                getWindow().setDecorFitsSystemWindows(true);
                android.view.WindowInsetsController controller = getWindow().getInsetsController();
                if (controller != null) {
                    controller.show(android.view.WindowInsets.Type.statusBars() | android.view.WindowInsets.Type.navigationBars());
                }
            } else {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }

    private void showStatusBarBlocker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                return;
            }
        }
        
        if (statusBarBlockerView == null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            if (wm != null) {
                statusBarBlockerView = new BlockerOverlayView(this);

                int layoutType;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    layoutType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    layoutType = WindowManager.LayoutParams.TYPE_PHONE;
                }

                // Increase height to 85dp to ensure we cover the entire swipe-down trigger zone at the top
                int blockerHeight = (int) (85 * getResources().getDisplayMetrics().density);

                WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        blockerHeight, 
                        layoutType,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                        android.graphics.PixelFormat.TRANSLUCENT
                );
                params.gravity = android.view.Gravity.TOP;

                try {
                    wm.addView(statusBarBlockerView, params);
                    Log.d(TAG, "Status bar blocker overlay added successfully!");
                } catch (Exception e) {
                    Log.e(TAG, "Error adding status bar blocker overlay", e);
                    statusBarBlockerView = null;
                }
            }
        }
    }

    private void hideStatusBarBlocker() {
        if (statusBarBlockerView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            if (wm != null) {
                try {
                    wm.removeView(statusBarBlockerView);
                    Log.d(TAG, "Status bar blocker overlay removed successfully!");
                } catch (Exception e) {
                    Log.e(TAG, "Error removing status bar blocker overlay", e);
                }
            }
            statusBarBlockerView = null;
        }
    }

    private void checkNetworkAndSetupUI() {
        if (layoutLocked == null || layoutLocked.getVisibility() != View.VISIBLE) {
            return; // Only enforce on Locked screen
        }

        boolean isConnected = isNetworkConnected();
        if (!isConnected) {
            setLockStatusText("⚠️ NO INTERNET CONNECTION! Please swipe down and turn on Wi-Fi.", 0xFFFF3333);
            disableKeypad(true);
        } else {
            // Restore normal secure / insecure state text
            disableKeypad(false);
            if (!isLockSecure()) {
                setLockStatusText("⚠️ Setup Incomplete! Click 'Admin' below to configure launcher/overlay.", 0xFFFF6200);
            } else {
                setLockStatusText("Enter PIN to activate device", 0xFFB3FFFF);
            }
        }
    }

    private boolean isNetworkConnected() {
        android.net.ConnectivityManager cm = (android.net.ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        hideStatusBarBlocker();
        super.onDestroy();
    }
}
