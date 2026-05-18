package com.example.tennislockapp;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class LockAccessibilityService extends AccessibilityService {

    private static final String TAG = "LockAccessibility";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence packageNameChar = event.getPackageName();
            if (packageNameChar != null) {
                String packageName = packageNameChar.toString();

                SharedPreferences prefs = getSharedPreferences("LockAppPrefs", MODE_PRIVATE);
                boolean isActive = prefs.getBoolean("is_active", false);
                boolean isAdminMode = prefs.getBoolean("is_admin_mode", false);
                boolean isPermanentlyUnlocked = prefs.getBoolean("is_permanently_unlocked", false);

                // Settings, Wi-Fi quick settings, and Package Installers are blocked unless the admin is logged in
                boolean blockSettings = !isAdminMode && !isPermanentlyUnlocked;

                // Notification drawer pull-down is blocked in BOTH LOCKED and ACTIVE states to prevent turning off Wi-Fi/internet.
                // Home and Recents keys are only blocked in the LOCKED state (allowing app navigation when ACTIVE).
                boolean blockSystemUI = false;
                if (!isAdminMode && !isPermanentlyUnlocked) {
                    if (!isActive) {
                        // When LOCKED, block all System UI events (status bar swipe-down, Home, and Recents)
                        blockSystemUI = true;
                    } else {
                        // When ACTIVE, block ONLY the status bar Notification drawer swipe-down, but ALLOW Home and Recents.
                        // Notification panel expansions under com.android.systemui match these specific UI classes.
                        CharSequence classNameChar = event.getClassName();
                        if (classNameChar != null) {
                            String className = classNameChar.toString();
                            if (className.contains("statusbar") || 
                                className.contains("Notification") || 
                                className.contains("Panel") || 
                                className.equals("android.widget.FrameLayout")) {
                                blockSystemUI = true;
                            }
                        }
                    }
                }

                if (blockSettings && (packageName.equals("com.android.settings") || 
                                      packageName.equals("com.android.packageinstaller") || 
                                      packageName.equals("com.google.android.packageinstaller"))) {
                    
                    Log.d(TAG, "Suppressed restricted package: " + packageName);
                    performGlobalAction(GLOBAL_ACTION_BACK);

                    // Relaunch our app to force front
                    Intent forceFront = new Intent(this, MainActivity.class);
                    forceFront.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(forceFront);
                } else if (blockSystemUI && packageName.equals("com.android.systemui")) {
                    Log.d(TAG, "Suppressed quick settings / system shade");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                        performGlobalAction(15); // GLOBAL_ACTION_DISMISS_NOTIFICATION_SHADE
                    }
                    performGlobalAction(GLOBAL_ACTION_BACK);
                }
            }
        }
    }

    @Override
    public void onInterrupt() {
        // No-op
    }
}
