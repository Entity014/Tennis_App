package cn.qqtheme.framework.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
public class CompatUtils {
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void setTextAppearance(TextView textView, int i) {
        if (Build.VERSION.SDK_INT < 23) {
            textView.setTextAppearance(textView.getContext(), i);
        } else {
            textView.setTextAppearance(i);
        }
    }

    public static Drawable getDrawable(Context context, int i) {
        return context.getDrawable(i);
    }

    public static String getString(Context context, int i) {
        return context.getString(i);
    }

    public static int getColor(Context context, int i) {
        int color;
        color = context.getResources().getColor(i, null);
        return color;
    }
}
