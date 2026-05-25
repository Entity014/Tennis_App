package cn.qqtheme.framework.util;

import android.os.Debug;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class LogUtils {
    private static final int MAX_STACK_TRACE_SIZE = 131071;
    private static String debugTag = "liyujiang";
    private static boolean isDebug = false;

    public static void debug(String str) {
        debug(debugTag, str);
    }

    public static void debug(Object obj, String str) {
        debug(obj.getClass().getSimpleName(), str);
    }

    public static void debug(String str, String str2) {
        if (isDebug) {
            try {
                Log.d(debugTag + "-" + str, str2);
            } catch (Exception unused) {
                System.out.println(str + ">>>" + str2);
            }
        }
    }

    public static void warn(Throwable th) {
        warn(toStackTraceString(th));
    }

    public static void warn(String str) {
        warn(debugTag, str);
    }

    public static void warn(Object obj, String str) {
        warn(obj.getClass().getSimpleName(), str);
    }

    public static void warn(Object obj, Throwable th) {
        warn(obj.getClass().getSimpleName(), toStackTraceString(th));
    }

    public static void warn(String str, String str2) {
        if (isDebug) {
            try {
                Log.w(debugTag + str, str2);
            } catch (Exception unused) {
                System.out.println(debugTag + ">>>" + str2);
            }
        }
    }

    public static void error(Throwable th) {
        error(toStackTraceString(th));
    }

    public static void error(String str) {
        error(debugTag, str);
    }

    public static void error(Object obj, String str) {
        error(obj.getClass().getSimpleName(), str);
    }

    public static void error(Object obj, Throwable th) {
        error(obj.getClass().getSimpleName(), toStackTraceString(th));
    }

    public static void error(String str, String str2) {
        if (isDebug) {
            try {
                Log.e(debugTag + str, str2);
            } catch (Exception unused) {
                System.out.println(debugTag + ">>>" + str2);
            }
        }
    }

    public static void startMethodTracing() {
        if (isDebug) {
            Debug.startMethodTracing(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + debugTag + ".trace");
        }
    }

    public static void stopMethodTracing() {
        if (isDebug) {
            Debug.stopMethodTracing();
        }
    }

    public static String toStackTraceString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (stringWriter2.length() <= MAX_STACK_TRACE_SIZE) {
            return stringWriter2;
        }
        return stringWriter2.substring(0, 131047) + " [stack trace too large]";
    }
}
