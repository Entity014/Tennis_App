package cn.qqtheme.framework.popup;

import android.app.Activity;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import cn.qqtheme.framework.util.LogUtils;
import cn.qqtheme.framework.util.ScreenUtils;

/* loaded from: classes.dex */
public abstract class BottomPopup<V extends View> implements DialogInterface.OnKeyListener {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    protected Activity activity;
    private Popup popup;
    protected int screenHeightPixels;
    protected int screenWidthPixels;
    private int width = 0;
    private int height = 0;
    private boolean isFillScreen = false;
    private boolean isHalfScreen = false;

    protected abstract V makeContentView();

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    protected void setContentViewAfter(V v) {
    }

    protected void setContentViewBefore() {
    }

    public BottomPopup(Activity activity) {
        this.activity = activity;
        DisplayMetrics displayMetrics = ScreenUtils.displayMetrics(activity);
        this.screenWidthPixels = displayMetrics.widthPixels;
        this.screenHeightPixels = displayMetrics.heightPixels;
        Popup popup = new Popup(activity);
        this.popup = popup;
        popup.setOnKeyListener(this);
    }

    private void onShowPrepare() {
        setContentViewBefore();
        V makeContentView = makeContentView();
        this.popup.setContentView(makeContentView);
        setContentViewAfter(makeContentView);
        LogUtils.debug("do something before popup show");
        if (this.width == 0 && this.height == 0) {
            this.width = this.screenWidthPixels;
            if (this.isFillScreen) {
                this.height = -1;
            } else if (this.isHalfScreen) {
                this.height = this.screenHeightPixels / 2;
            } else {
                this.height = -2;
            }
        }
        this.popup.setSize(this.width, this.height);
    }

    public void setFillScreen(boolean z) {
        this.isFillScreen = z;
    }

    public void setHalfScreen(boolean z) {
        this.isHalfScreen = z;
    }

    public void setAnimationStyle(int i) {
        this.popup.setAnimationStyle(i);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.popup.setOnDismissListener(onDismissListener);
        LogUtils.debug("popup setOnDismissListener");
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public boolean isShowing() {
        return this.popup.isShowing();
    }

    public void show() {
        onShowPrepare();
        this.popup.show();
        LogUtils.debug("popup show");
    }

    public void dismiss() {
        this.popup.dismiss();
        LogUtils.debug("popup dismiss");
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            return onKeyDown(i, keyEvent);
        }
        return false;
    }

    public Window getWindow() {
        return this.popup.getWindow();
    }

    public ViewGroup getRootView() {
        return this.popup.getRootView();
    }
}
