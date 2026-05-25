package cn.qqtheme.framework.popup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import cn.qqtheme.framework.R;
import cn.qqtheme.framework.util.LogUtils;

/* loaded from: classes.dex */
public class Popup {
    private FrameLayout contentLayout;
    private Dialog dialog;

    public Popup(Context context) {
        init(context);
    }

    private void init(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        this.contentLayout = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.contentLayout.setFocusable(true);
        this.contentLayout.setFocusableInTouchMode(true);
        Dialog dialog = new Dialog(context);
        this.dialog = dialog;
        dialog.setCanceledOnTouchOutside(true);
        this.dialog.setCancelable(true);
        Window window = this.dialog.getWindow();
        window.setGravity(80);
        window.setWindowAnimations(R.style.Animation_Popup);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.requestFeature(1);
        window.setContentView(this.contentLayout);
    }

    public Context getContext() {
        return this.contentLayout.getContext();
    }

    public void setAnimationStyle(int i) {
        this.dialog.getWindow().setWindowAnimations(i);
    }

    public boolean isShowing() {
        return this.dialog.isShowing();
    }

    public void show() {
        this.dialog.show();
    }

    public void dismiss() {
        this.dialog.dismiss();
    }

    public void setContentView(View view) {
        this.contentLayout.removeAllViews();
        this.contentLayout.addView(view);
    }

    public View getContentView() {
        return this.contentLayout.getChildAt(0);
    }

    public void setSize(int i, int i2) {
        LogUtils.debug(String.format("will set popup width/height to: %s/%s", Integer.valueOf(i), Integer.valueOf(i2)));
        ViewGroup.LayoutParams layoutParams = this.contentLayout.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i, i2);
        } else {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        this.contentLayout.setLayoutParams(layoutParams);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.dialog.setOnDismissListener(onDismissListener);
    }

    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.dialog.setOnKeyListener(onKeyListener);
    }

    public Window getWindow() {
        return this.dialog.getWindow();
    }

    public ViewGroup getRootView() {
        return this.contentLayout;
    }
}
