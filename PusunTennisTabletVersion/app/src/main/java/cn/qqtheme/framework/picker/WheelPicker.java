package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.view.View;
import cn.qqtheme.framework.popup.ConfirmPopup;
import cn.qqtheme.framework.widget.WheelView;

/* loaded from: classes.dex */
public abstract class WheelPicker extends ConfirmPopup<View> {
    protected int lineColor;
    protected boolean lineVisible;
    protected int offset;
    protected int textColorFocus;
    protected int textColorNormal;
    protected int textSize;

    public WheelPicker(Activity activity) {
        super(activity);
        this.textSize = 20;
        this.textColorNormal = WheelView.TEXT_COLOR_NORMAL;
        this.textColorFocus = WheelView.TEXT_COLOR_FOCUS;
        this.lineColor = WheelView.LINE_COLOR;
        this.lineVisible = true;
        this.offset = 1;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public void setTextColor(int i, int i2) {
        this.textColorFocus = i;
        this.textColorNormal = i2;
    }

    public void setTextColor(int i) {
        this.textColorFocus = i;
    }

    public void setLineVisible(boolean z) {
        this.lineVisible = z;
    }

    public void setLineColor(int i) {
        this.lineColor = i;
    }

    public void setOffset(int i) {
        this.offset = i;
    }
}
