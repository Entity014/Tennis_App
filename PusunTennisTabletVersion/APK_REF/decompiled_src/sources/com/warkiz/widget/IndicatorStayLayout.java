package com.warkiz.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* loaded from: classes3.dex */
public class IndicatorStayLayout extends LinearLayout {
    public IndicatorStayLayout(Context context) {
        this(context, null);
    }

    public IndicatorStayLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorStayLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(1);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            layoutIndicator(getChildAt(childCount), childCount);
        }
        super.onFinishInflate();
    }

    public void attachTo(IndicatorSeekBar seekBar) {
        attachTo(seekBar, -2);
    }

    public void attachTo(IndicatorSeekBar seekBar, int index) {
        if (seekBar == null) {
            throw new NullPointerException("the seek bar wanna attach to IndicatorStayLayout can not be null value.");
        }
        layoutIndicator(seekBar, index);
        addView(seekBar, index + 1);
    }

    private void layoutIndicator(View child, int index) {
        if (child instanceof IndicatorSeekBar) {
            IndicatorSeekBar indicatorSeekBar = (IndicatorSeekBar) child;
            indicatorSeekBar.setIndicatorStayAlways(true);
            View indicatorContentView = indicatorSeekBar.getIndicatorContentView();
            if (indicatorContentView == null) {
                throw new IllegalStateException("Can not find any indicator in the IndicatorSeekBar, please make sure you have called the attr: SHOW_INDICATOR_TYPE for IndicatorSeekBar and the value is not IndicatorType.NONE.");
            }
            if (indicatorContentView instanceof IndicatorSeekBar) {
                throw new IllegalStateException("IndicatorSeekBar can not be a contentView for Indicator in case this inflating loop.");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, SizeUtils.dp2px(indicatorSeekBar.getContext(), 2.0f) - indicatorSeekBar.getPaddingTop());
            addView(indicatorContentView, index, marginLayoutParams);
            indicatorSeekBar.showStayIndicator();
        }
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int orientation) {
        if (orientation != 1) {
            throw new IllegalArgumentException("IndicatorStayLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(orientation);
    }
}
