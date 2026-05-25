package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.widget.WheelView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes.dex */
public class TimePicker extends WheelPicker {
    public static final int HOUR = 1;
    public static final int HOUR_OF_DAY = 0;
    private String hourLabel;
    private String minuteLabel;
    private int mode;
    private OnTimePickListener onTimePickListener;
    private String selectedHour;
    private String selectedMinute;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTimePickListener {
        void onTimePicked(String str, String str2);
    }

    public TimePicker(Activity activity) {
        this(activity, 0);
    }

    public TimePicker(Activity activity, int i) {
        super(activity);
        this.hourLabel = "时";
        this.minuteLabel = "分";
        this.selectedHour = "";
        this.selectedMinute = "";
        this.mode = i;
        this.selectedHour = DateUtils.fillZero(Calendar.getInstance().get(11));
        this.selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(12));
    }

    public void setLabel(String str, String str2) {
        this.hourLabel = str;
        this.minuteLabel = str2;
    }

    public void setSelectedItem(int i, int i2) {
        this.selectedHour = String.valueOf(i);
        this.selectedMinute = String.valueOf(i2);
    }

    public void setOnTimePickListener(OnTimePickListener onTimePickListener) {
        this.onTimePickListener = onTimePickListener;
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    protected View makeCenterView() {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity);
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        linearLayout.addView(wheelView);
        TextView textView = new TextView(this.activity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(this.textSize);
        textView.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.hourLabel)) {
            textView.setText(this.hourLabel);
        }
        linearLayout.addView(textView);
        WheelView wheelView2 = new WheelView(this.activity);
        wheelView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView2.setTextSize(this.textSize);
        wheelView2.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView2.setLineVisible(this.lineVisible);
        wheelView2.setLineColor(this.lineColor);
        wheelView2.setOffset(this.offset);
        linearLayout.addView(wheelView2);
        TextView textView2 = new TextView(this.activity);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView2.setTextSize(this.textSize);
        textView2.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.minuteLabel)) {
            textView2.setText(this.minuteLabel);
        }
        linearLayout.addView(textView2);
        ArrayList arrayList = new ArrayList();
        if (this.mode == 1) {
            for (int i = 1; i <= 12; i++) {
                arrayList.add(DateUtils.fillZero(i));
            }
        } else {
            for (int i2 = 0; i2 < 24; i2++) {
                arrayList.add(DateUtils.fillZero(i2));
            }
        }
        wheelView.setItems(arrayList, this.selectedHour);
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < 60; i3++) {
            arrayList2.add(DateUtils.fillZero(i3));
        }
        wheelView2.setItems(arrayList2, this.selectedMinute);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.TimePicker.1
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i4, String str) {
                TimePicker.this.selectedHour = str;
            }
        });
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.TimePicker.2
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i4, String str) {
                TimePicker.this.selectedMinute = str;
            }
        });
        return linearLayout;
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    public void onSubmit() {
        OnTimePickListener onTimePickListener = this.onTimePickListener;
        if (onTimePickListener != null) {
            onTimePickListener.onTimePicked(this.selectedHour, this.selectedMinute);
        }
    }

    public String getSelectedHour() {
        return this.selectedHour;
    }

    public String getSelectedMinute() {
        return this.selectedMinute;
    }
}
