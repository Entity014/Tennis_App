package cn.qqtheme.framework.picker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.qqtheme.framework.util.DateUtils;
import cn.qqtheme.framework.widget.WheelView;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class DatePicker extends WheelPicker {
    public static final int MONTH_DAY = 2;
    public static final int YEAR_MONTH = 1;
    public static final int YEAR_MONTH_DAY = 0;
    private String dayLabel;
    private ArrayList<String> days;
    private int mode;
    private String monthLabel;
    private ArrayList<String> months;
    private OnDatePickListener onDatePickListener;
    private int selectedDayIndex;
    private int selectedMonthIndex;
    private int selectedYearIndex;
    private String yearLabel;
    private ArrayList<String> years;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    protected interface OnDatePickListener {
    }

    public interface OnMonthDayPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2);
    }

    public interface OnYearMonthDayPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2, String str3);
    }

    public interface OnYearMonthPickListener extends OnDatePickListener {
        void onDatePicked(String str, String str2);
    }

    public DatePicker(Activity activity) {
        this(activity, 0);
    }

    public DatePicker(Activity activity, int i) {
        super(activity);
        this.years = new ArrayList<>();
        this.months = new ArrayList<>();
        this.days = new ArrayList<>();
        this.yearLabel = "年";
        this.monthLabel = "月";
        this.dayLabel = "日";
        this.selectedYearIndex = 0;
        this.selectedMonthIndex = 0;
        this.selectedDayIndex = 0;
        this.mode = i;
        for (int i2 = 2000; i2 <= 2050; i2++) {
            this.years.add(String.valueOf(i2));
        }
        for (int i3 = 1; i3 <= 12; i3++) {
            this.months.add(DateUtils.fillZero(i3));
        }
        for (int i4 = 1; i4 <= 31; i4++) {
            this.days.add(DateUtils.fillZero(i4));
        }
    }

    public void setLabel(String str, String str2, String str3) {
        this.yearLabel = str;
        this.monthLabel = str2;
        this.dayLabel = str3;
    }

    public void setRange(int i, int i2) {
        this.years.clear();
        while (i <= i2) {
            this.years.add(String.valueOf(i));
            i++;
        }
    }

    private int findItemIndex(ArrayList<String> arrayList, int i) {
        int binarySearch = Collections.binarySearch(arrayList, Integer.valueOf(i), new Comparator<Object>() { // from class: cn.qqtheme.framework.picker.DatePicker.1
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                String obj3 = obj.toString();
                String obj4 = obj2.toString();
                if (obj3.startsWith(SessionDescription.SUPPORTED_SDP_VERSION)) {
                    obj3 = obj3.substring(1);
                }
                if (obj4.startsWith(SessionDescription.SUPPORTED_SDP_VERSION)) {
                    obj4 = obj4.substring(1);
                }
                return Integer.parseInt(obj3) - Integer.parseInt(obj4);
            }
        });
        if (binarySearch < 0) {
            return 0;
        }
        return binarySearch;
    }

    public void setSelectedItem(int i, int i2, int i3) {
        this.selectedYearIndex = findItemIndex(this.years, i);
        this.selectedMonthIndex = findItemIndex(this.months, i2);
        this.selectedDayIndex = findItemIndex(this.days, i3);
    }

    public void setSelectedItem(int i, int i2) {
        if (this.mode == 2) {
            this.selectedMonthIndex = findItemIndex(this.months, i);
            this.selectedDayIndex = findItemIndex(this.days, i2);
        } else {
            this.selectedYearIndex = findItemIndex(this.years, i);
            this.selectedMonthIndex = findItemIndex(this.months, i2);
        }
    }

    public void setOnDatePickListener(OnDatePickListener onDatePickListener) {
        this.onDatePickListener = onDatePickListener;
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    protected View makeCenterView() {
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        WheelView wheelView = new WheelView(this.activity.getBaseContext());
        wheelView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView.setTextSize(this.textSize);
        wheelView.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView.setLineVisible(this.lineVisible);
        wheelView.setLineColor(this.lineColor);
        wheelView.setOffset(this.offset);
        linearLayout.addView(wheelView);
        TextView textView = new TextView(this.activity);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setTextSize(this.textSize);
        textView.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.yearLabel)) {
            textView.setText(this.yearLabel);
        }
        linearLayout.addView(textView);
        WheelView wheelView2 = new WheelView(this.activity.getBaseContext());
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
        if (!TextUtils.isEmpty(this.monthLabel)) {
            textView2.setText(this.monthLabel);
        }
        linearLayout.addView(textView2);
        final WheelView wheelView3 = new WheelView(this.activity.getBaseContext());
        wheelView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        wheelView3.setTextSize(this.textSize);
        wheelView3.setTextColor(this.textColorNormal, this.textColorFocus);
        wheelView3.setLineVisible(this.lineVisible);
        wheelView3.setLineColor(this.lineColor);
        wheelView3.setOffset(this.offset);
        linearLayout.addView(wheelView3);
        TextView textView3 = new TextView(this.activity);
        textView3.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView3.setTextSize(this.textSize);
        textView3.setTextColor(this.textColorFocus);
        if (!TextUtils.isEmpty(this.dayLabel)) {
            textView3.setText(this.dayLabel);
        }
        linearLayout.addView(textView3);
        int i = this.mode;
        if (i == 1) {
            wheelView3.setVisibility(8);
            textView3.setVisibility(8);
        } else if (i == 2) {
            wheelView.setVisibility(8);
            textView.setVisibility(8);
        }
        if (this.mode != 2) {
            if (!TextUtils.isEmpty(this.yearLabel)) {
                textView.setText(this.yearLabel);
            }
            int i2 = this.selectedYearIndex;
            if (i2 == 0) {
                wheelView.setItems(this.years);
            } else {
                wheelView.setItems(this.years, i2);
            }
            wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.DatePicker.2
                @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
                public void onSelected(boolean z, int i3, String str) {
                    DatePicker.this.selectedYearIndex = i3;
                    DatePicker.this.days.clear();
                    int stringToYearMonthDay = DatePicker.this.stringToYearMonthDay(str);
                    DatePicker datePicker = DatePicker.this;
                    int calculateDaysInMonth = DateUtils.calculateDaysInMonth(stringToYearMonthDay, datePicker.stringToYearMonthDay((String) datePicker.months.get(DatePicker.this.selectedMonthIndex)));
                    for (int i4 = 1; i4 <= calculateDaysInMonth; i4++) {
                        DatePicker.this.days.add(DateUtils.fillZero(i4));
                    }
                    if (DatePicker.this.selectedDayIndex >= calculateDaysInMonth) {
                        DatePicker datePicker2 = DatePicker.this;
                        datePicker2.selectedDayIndex = datePicker2.days.size() - 1;
                    }
                    wheelView3.setItems(DatePicker.this.days, DatePicker.this.selectedDayIndex);
                }
            });
        }
        if (!TextUtils.isEmpty(this.monthLabel)) {
            textView2.setText(this.monthLabel);
        }
        int i3 = this.selectedMonthIndex;
        if (i3 == 0) {
            wheelView2.setItems(this.months);
        } else {
            wheelView2.setItems(this.months, i3);
        }
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.DatePicker.3
            @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
            public void onSelected(boolean z, int i4, String str) {
                DatePicker.this.selectedMonthIndex = i4;
                if (DatePicker.this.mode != 1) {
                    DatePicker.this.days.clear();
                    DatePicker datePicker = DatePicker.this;
                    int calculateDaysInMonth = DateUtils.calculateDaysInMonth(datePicker.stringToYearMonthDay((String) datePicker.years.get(DatePicker.this.selectedYearIndex)), DatePicker.this.stringToYearMonthDay(str));
                    for (int i5 = 1; i5 <= calculateDaysInMonth; i5++) {
                        DatePicker.this.days.add(DateUtils.fillZero(i5));
                    }
                    if (DatePicker.this.selectedDayIndex >= calculateDaysInMonth) {
                        DatePicker datePicker2 = DatePicker.this;
                        datePicker2.selectedDayIndex = datePicker2.days.size() - 1;
                    }
                    wheelView3.setItems(DatePicker.this.days, DatePicker.this.selectedDayIndex);
                }
            }
        });
        if (this.mode != 1) {
            if (!TextUtils.isEmpty(this.dayLabel)) {
                textView3.setText(this.dayLabel);
            }
            int i4 = this.selectedDayIndex;
            if (i4 == 0) {
                wheelView3.setItems(this.days);
            } else {
                wheelView3.setItems(this.days, i4);
            }
            wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() { // from class: cn.qqtheme.framework.picker.DatePicker.4
                @Override // cn.qqtheme.framework.widget.WheelView.OnWheelViewListener
                public void onSelected(boolean z, int i5, String str) {
                    DatePicker.this.selectedDayIndex = i5;
                }
            });
        }
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int stringToYearMonthDay(String str) {
        if (str.startsWith(SessionDescription.SUPPORTED_SDP_VERSION)) {
            str = str.substring(1);
        }
        return Integer.parseInt(str);
    }

    @Override // cn.qqtheme.framework.popup.ConfirmPopup
    protected void onSubmit() {
        if (this.onDatePickListener != null) {
            String selectedYear = getSelectedYear();
            String selectedMonth = getSelectedMonth();
            String selectedDay = getSelectedDay();
            int i = this.mode;
            if (i == 1) {
                ((OnYearMonthPickListener) this.onDatePickListener).onDatePicked(selectedYear, selectedMonth);
            } else if (i == 2) {
                ((OnMonthDayPickListener) this.onDatePickListener).onDatePicked(selectedMonth, selectedDay);
            } else {
                ((OnYearMonthDayPickListener) this.onDatePickListener).onDatePicked(selectedYear, selectedMonth, selectedDay);
            }
        }
    }

    public String getSelectedYear() {
        return this.years.get(this.selectedYearIndex);
    }

    public String getSelectedMonth() {
        return this.months.get(this.selectedMonthIndex);
    }

    public String getSelectedDay() {
        return this.days.get(this.selectedDayIndex);
    }
}
