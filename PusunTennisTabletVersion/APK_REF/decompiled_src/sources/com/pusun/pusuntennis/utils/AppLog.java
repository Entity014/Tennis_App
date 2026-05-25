package com.pusun.pusuntennis.utils;

import android.util.Log;

/* loaded from: classes3.dex */
public class AppLog {
    private static final boolean DEBUG = true;
    private static final int LOG_LEVEL = 2;
    private static final String TAG = "AppLog";

    public static void e(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 4) {
            Log.e(TAG, "Stack to shallow");
            return;
        }
        String className = stackTrace[3].getClassName();
        String substring = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTrace[3].getMethodName();
        Log.e(TAG, "[" + substring + ":" + stackTrace[3].getLineNumber() + " " + methodName + "()] - " + str);
    }

    public static void d(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            Log.e(TAG, "Stack to shallow");
            return;
        }
        String className = stackTrace[3].getClassName();
        String substring = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTrace[3].getMethodName();
        Log.d(TAG, "[" + substring + ":" + stackTrace[3].getLineNumber() + " " + methodName + "()] - " + str);
    }

    public static void i(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            Log.e(TAG, "Stack to shallow");
            return;
        }
        String className = stackTrace[3].getClassName();
        String substring = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTrace[3].getMethodName();
        Log.i(TAG, "[" + substring + ":" + stackTrace[3].getLineNumber() + " " + methodName + "()] - " + str);
    }

    public static void w(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            Log.e(TAG, "Stack to shallow");
            return;
        }
        String className = stackTrace[3].getClassName();
        String substring = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTrace[3].getMethodName();
        Log.w(TAG, "[" + substring + ":" + stackTrace[3].getLineNumber() + " " + methodName + "()] - " + str);
    }

    public static void v(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            Log.e(TAG, "Stack to shallow");
            return;
        }
        String className = stackTrace[3].getClassName();
        String substring = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTrace[3].getMethodName();
        Log.v(TAG, "[" + substring + ":" + stackTrace[3].getLineNumber() + " " + methodName + "()] - " + str);
    }
}
