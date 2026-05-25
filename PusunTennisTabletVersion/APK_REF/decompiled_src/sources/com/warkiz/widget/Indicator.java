package com.warkiz.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.exoplayer2.text.ttml.TtmlNode;

/* loaded from: classes3.dex */
public class Indicator {
    private ArrowView mArrowView;
    private Context mContext;
    private int mGap;
    private int mIndicatorColor;
    private View mIndicatorCustomTopContentView;
    private View mIndicatorCustomView;
    private PopupWindow mIndicatorPopW;
    private int mIndicatorTextColor;
    private float mIndicatorTextSize;
    private int mIndicatorType;
    private View mIndicatorView;
    private TextView mProgressTextView;
    private IndicatorSeekBar mSeekBar;
    private LinearLayout mTopContentView;
    private int[] mLocation = new int[2];
    private final int mWindowWidth = getWindowWidth();

    public Indicator(Context context, IndicatorSeekBar seekBar, int indicatorColor, int indicatorType, int indicatorTextSize, int indicatorTextColor, View indicatorCustomView, View indicatorCustomTopContentView) {
        this.mContext = context;
        this.mSeekBar = seekBar;
        this.mIndicatorColor = indicatorColor;
        this.mIndicatorType = indicatorType;
        this.mIndicatorCustomView = indicatorCustomView;
        this.mIndicatorCustomTopContentView = indicatorCustomTopContentView;
        this.mIndicatorTextSize = indicatorTextSize;
        this.mIndicatorTextColor = indicatorTextColor;
        this.mGap = SizeUtils.dp2px(this.mContext, 2.0f);
        initIndicator();
    }

    private void initIndicator() {
        View findViewById;
        int i = this.mIndicatorType;
        if (i == 4) {
            View view = this.mIndicatorCustomView;
            if (view != null) {
                this.mIndicatorView = view;
                int identifier = this.mContext.getResources().getIdentifier("isb_progress", TtmlNode.ATTR_ID, this.mContext.getApplicationContext().getPackageName());
                if (identifier <= 0 || (findViewById = this.mIndicatorView.findViewById(identifier)) == null) {
                    return;
                }
                if (findViewById instanceof TextView) {
                    TextView textView = (TextView) findViewById;
                    this.mProgressTextView = textView;
                    textView.setText(this.mSeekBar.getIndicatorTextString());
                    this.mProgressTextView.setTextSize(SizeUtils.px2sp(this.mContext, this.mIndicatorTextSize));
                    this.mProgressTextView.setTextColor(this.mIndicatorTextColor);
                    return;
                }
                throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
            }
            throw new IllegalArgumentException("the attr：indicator_custom_layout must be set while you set the indicator type to CUSTOM.");
        }
        if (i == 1) {
            CircleBubbleView circleBubbleView = new CircleBubbleView(this.mContext, this.mIndicatorTextSize, this.mIndicatorTextColor, this.mIndicatorColor, "1000");
            this.mIndicatorView = circleBubbleView;
            circleBubbleView.setProgress(this.mSeekBar.getIndicatorTextString());
            return;
        }
        View inflate = View.inflate(this.mContext, R.layout.isb_indicator, null);
        this.mIndicatorView = inflate;
        this.mTopContentView = (LinearLayout) inflate.findViewById(R.id.indicator_container);
        ArrowView arrowView = (ArrowView) this.mIndicatorView.findViewById(R.id.indicator_arrow);
        this.mArrowView = arrowView;
        arrowView.setColor(this.mIndicatorColor);
        TextView textView2 = (TextView) this.mIndicatorView.findViewById(R.id.isb_progress);
        this.mProgressTextView = textView2;
        textView2.setText(this.mSeekBar.getIndicatorTextString());
        this.mProgressTextView.setTextSize(SizeUtils.px2sp(this.mContext, this.mIndicatorTextSize));
        this.mProgressTextView.setTextColor(this.mIndicatorTextColor);
        this.mTopContentView.setBackground(getGradientDrawable());
        if (this.mIndicatorCustomTopContentView != null) {
            int identifier2 = this.mContext.getResources().getIdentifier("isb_progress", TtmlNode.ATTR_ID, this.mContext.getApplicationContext().getPackageName());
            View view2 = this.mIndicatorCustomTopContentView;
            if (identifier2 > 0) {
                View findViewById2 = view2.findViewById(identifier2);
                if (findViewById2 != null && (findViewById2 instanceof TextView)) {
                    setTopContentView(view2, (TextView) findViewById2);
                    return;
                } else {
                    setTopContentView(view2);
                    return;
                }
            }
            setTopContentView(view2);
        }
    }

    private GradientDrawable getGradientDrawable() {
        GradientDrawable gradientDrawable;
        if (this.mIndicatorType == 2) {
            gradientDrawable = (GradientDrawable) this.mContext.getResources().getDrawable(R.drawable.isb_indicator_rounded_corners);
        } else {
            gradientDrawable = (GradientDrawable) this.mContext.getResources().getDrawable(R.drawable.isb_indicator_square_corners);
        }
        gradientDrawable.setColor(this.mIndicatorColor);
        return gradientDrawable;
    }

    private int getWindowWidth() {
        WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
        if (windowManager != null) {
            return windowManager.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    private int getIndicatorScreenX() {
        this.mSeekBar.getLocationOnScreen(this.mLocation);
        return this.mLocation[0];
    }

    private void adjustArrow(float touchX) {
        int i = this.mIndicatorType;
        if (i == 4 || i == 1) {
            return;
        }
        if (getIndicatorScreenX() + touchX < this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(this.mArrowView, -((int) (((this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) - r0) - touchX)), -1, -1, -1);
        } else if ((this.mWindowWidth - r0) - touchX < this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) {
            setMargin(this.mArrowView, (int) ((this.mIndicatorPopW.getContentView().getMeasuredWidth() / 2) - ((this.mWindowWidth - r0) - touchX)), -1, -1, -1);
        } else {
            setMargin(this.mArrowView, 0, 0, 0, 0);
        }
    }

    private void setMargin(View view, int left, int top, int right, int bottom) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (left == -1) {
                left = marginLayoutParams.leftMargin;
            }
            if (top == -1) {
                top = marginLayoutParams.topMargin;
            }
            if (right == -1) {
                right = marginLayoutParams.rightMargin;
            }
            if (bottom == -1) {
                bottom = marginLayoutParams.bottomMargin;
            }
            marginLayoutParams.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    void iniPop() {
        View view;
        if (this.mIndicatorPopW != null || this.mIndicatorType == 0 || (view = this.mIndicatorView) == null) {
            return;
        }
        view.measure(0, 0);
        this.mIndicatorPopW = new PopupWindow(this.mIndicatorView, -2, -2, false);
    }

    View getInsideContentView() {
        return this.mIndicatorView;
    }

    void setProgressTextView(String text) {
        View view = this.mIndicatorView;
        if (view instanceof CircleBubbleView) {
            ((CircleBubbleView) view).setProgress(text);
            return;
        }
        TextView textView = this.mProgressTextView;
        if (textView != null) {
            textView.setText(text);
        }
    }

    void updateIndicatorLocation(int offset) {
        setMargin(this.mIndicatorView, offset, -1, -1, -1);
    }

    void updateArrowViewLocation(int offset) {
        setMargin(this.mArrowView, offset, -1, -1, -1);
    }

    void update(float touchX) {
        if (this.mSeekBar.isEnabled() && this.mSeekBar.getVisibility() == 0) {
            refreshProgressText();
            PopupWindow popupWindow = this.mIndicatorPopW;
            if (popupWindow != null) {
                popupWindow.getContentView().measure(0, 0);
                this.mIndicatorPopW.update(this.mSeekBar, (int) (touchX - (r2.getContentView().getMeasuredWidth() / 2)), -(((this.mSeekBar.getMeasuredHeight() + this.mIndicatorPopW.getContentView().getMeasuredHeight()) - this.mSeekBar.getPaddingTop()) + this.mGap), -1, -1);
                adjustArrow(touchX);
            }
        }
    }

    void show(float touchX) {
        if (this.mSeekBar.isEnabled() && this.mSeekBar.getVisibility() == 0) {
            refreshProgressText();
            PopupWindow popupWindow = this.mIndicatorPopW;
            if (popupWindow != null) {
                popupWindow.getContentView().measure(0, 0);
                this.mIndicatorPopW.showAsDropDown(this.mSeekBar, (int) (touchX - (r0.getContentView().getMeasuredWidth() / 2.0f)), -(((this.mSeekBar.getMeasuredHeight() + this.mIndicatorPopW.getContentView().getMeasuredHeight()) - this.mSeekBar.getPaddingTop()) + this.mGap));
                adjustArrow(touchX);
            }
        }
    }

    void refreshProgressText() {
        String indicatorTextString = this.mSeekBar.getIndicatorTextString();
        View view = this.mIndicatorView;
        if (view instanceof CircleBubbleView) {
            ((CircleBubbleView) view).setProgress(indicatorTextString);
            return;
        }
        TextView textView = this.mProgressTextView;
        if (textView != null) {
            textView.setText(indicatorTextString);
        }
    }

    void hide() {
        PopupWindow popupWindow = this.mIndicatorPopW;
        if (popupWindow == null) {
            return;
        }
        popupWindow.dismiss();
    }

    boolean isShowing() {
        PopupWindow popupWindow = this.mIndicatorPopW;
        return popupWindow != null && popupWindow.isShowing();
    }

    public View getContentView() {
        return this.mIndicatorView;
    }

    public void setContentView(View customIndicatorView) {
        this.mIndicatorType = 4;
        this.mIndicatorCustomView = customIndicatorView;
        initIndicator();
    }

    public void setContentView(View customIndicatorView, TextView progressTextView) {
        this.mProgressTextView = progressTextView;
        this.mIndicatorType = 4;
        this.mIndicatorCustomView = customIndicatorView;
        initIndicator();
    }

    public View getTopContentView() {
        return this.mTopContentView;
    }

    public void setTopContentView(View topContentView) {
        setTopContentView(topContentView, null);
    }

    public void setTopContentView(View topContentView, TextView progressTextView) {
        this.mProgressTextView = progressTextView;
        this.mTopContentView.removeAllViews();
        topContentView.setBackground(getGradientDrawable());
        this.mTopContentView.addView(topContentView);
    }
}
