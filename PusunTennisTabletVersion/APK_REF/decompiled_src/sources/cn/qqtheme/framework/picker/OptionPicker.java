package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.widget.WheelView;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class OptionPicker extends WheelPicker {
    private String label;
    private OnOptionPickListener onOptionPickListener;
    protected ArrayList<String> options;
    private String selectedOption;

    public interface OnOptionPickListener {
        void onOptionPicked(String str);
    }

    public OptionPicker(Activity activity, String[] strArr) {
        super(activity);
        ArrayList<String> arrayList = new ArrayList<>();
        this.options = arrayList;
        this.selectedOption = "";
        this.label = "";
        arrayList.addAll(Arrays.asList(strArr));
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setSelectedIndex(int i) {
        for (int i2 = 0; i2 < this.options.size(); i2++) {
            if (i == i2) {
                this.selectedOption = this.options.get(i);
                return;
            }
        }
    }

    public void setSelectedItem(String str) {
        this.selectedOption = str;
    }

    public void setOnOptionPickListener(OnOptionPickListener onOptionPickListener) {
        this.onOptionPickListener = onOptionPickListener;
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    protected View makeCenterView() {
        if (this.options.size() == 0) {
            throw new IllegalArgumentException("please initial options at first, can't be empty");
        }
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity);
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        wheelView.setOffset(this.offset);
        linearLayout.addView(wheelView);
        TextView textView = new TextView(this.activity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextColor(this.textColorFocus);
        textView.setTextSize(this.textSize);
        linearLayout.addView(textView);
        if (!TextUtils.isEmpty(this.label)) {
            textView.setText(this.label);
        }
        if (TextUtils.isEmpty(this.selectedOption)) {
            wheelView.setItems(this.options);
        } else {
            wheelView.setItems(this.options, this.selectedOption);
        }
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.OptionPicker.1
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i, String str) {
                OptionPicker.this.selectedOption = str;
            }
        });
        return linearLayout;
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    public void onSubmit() {
        OnOptionPickListener onOptionPickListener = this.onOptionPickListener;
        if (onOptionPickListener != null) {
            onOptionPickListener.onOptionPicked(this.selectedOption);
        }
    }

    public String getSelectedOption() {
        return this.selectedOption;
    }
}
