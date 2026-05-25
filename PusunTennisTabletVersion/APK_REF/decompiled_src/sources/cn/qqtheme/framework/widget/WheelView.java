package cn.qqtheme.framework.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.qqtheme.framework.util.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class WheelView extends ScrollView {
    private static final int DELAY = 50;
    public static final int LINE_COLOR = -8139290;
    public static final int OFF_SET = 1;
    public static final int TEXT_COLOR_FOCUS = -16611122;
    public static final int TEXT_COLOR_NORMAL = -4473925;
    public static final int TEXT_SIZE = 20;
    private Context context;
    private int displayItemCount;
    private int initialY;
    private boolean isUserScroll;
    private int itemHeight;
    private List<String> items;
    private int lineColor;
    private boolean lineVisible;
    private int offset;
    private OnWheelViewListener onWheelViewListener;
    private Paint paint;
    private float previousY;
    private Runnable scrollerTask;
    private int[] selectedAreaBorder;
    private int selectedIndex;
    private int textColorFocus;
    private int textColorNormal;
    private int textSize;
    private int viewWidth;
    private LinearLayout views;

    public interface OnWheelViewListener {
        void onSelected(boolean z, int i, String str);
    }

    public WheelView(Context context) {
        super(context);
        this.items = new ArrayList();
        this.offset = 1;
        this.selectedIndex = 1;
        this.scrollerTask = new ScrollerTask();
        this.itemHeight = 0;
        this.textSize = 20;
        this.textColorNormal = TEXT_COLOR_NORMAL;
        this.textColorFocus = TEXT_COLOR_FOCUS;
        this.lineColor = LINE_COLOR;
        this.lineVisible = true;
        this.isUserScroll = false;
        this.previousY = 0.0f;
        init(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.items = new ArrayList();
        this.offset = 1;
        this.selectedIndex = 1;
        this.scrollerTask = new ScrollerTask();
        this.itemHeight = 0;
        this.textSize = 20;
        this.textColorNormal = TEXT_COLOR_NORMAL;
        this.textColorFocus = TEXT_COLOR_FOCUS;
        this.lineColor = LINE_COLOR;
        this.lineVisible = true;
        this.isUserScroll = false;
        this.previousY = 0.0f;
        init(context);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.items = new ArrayList();
        this.offset = 1;
        this.selectedIndex = 1;
        this.scrollerTask = new ScrollerTask();
        this.itemHeight = 0;
        this.textSize = 20;
        this.textColorNormal = TEXT_COLOR_NORMAL;
        this.textColorFocus = TEXT_COLOR_FOCUS;
        this.lineColor = LINE_COLOR;
        this.lineVisible = true;
        this.isUserScroll = false;
        this.previousY = 0.0f;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setFadingEdgeLength(0);
        setOverScrollMode(2);
        setVerticalScrollBarEnabled(false);
        LinearLayout linearLayout = new LinearLayout(context);
        this.views = linearLayout;
        linearLayout.setOrientation(1);
        addView(this.views);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startScrollerTask() {
        this.initialY = getScrollY();
        postDelayed(this.scrollerTask, 50L);
    }

    private void initData() {
        this.displayItemCount = (this.offset * 2) + 1;
        this.views.removeAllViews();
        Iterator<String> it = this.items.iterator();
        while (it.hasNext()) {
            this.views.addView(createView(it.next()));
        }
        refreshItemView(this.itemHeight * (this.selectedIndex - this.offset));
    }

    private TextView createView(String str) {
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(str);
        textView.setTextSize(this.textSize);
        textView.setGravity(17);
        int dip2px = dip2px(15.0f);
        textView.setPadding(dip2px, dip2px, dip2px, dip2px);
        if (this.itemHeight == 0) {
            this.itemHeight = getViewMeasuredHeight(textView);
            LogUtils.debug(this, "itemHeight: " + this.itemHeight);
            this.views.setLayoutParams(new FrameLayout.LayoutParams(-1, this.itemHeight * this.displayItemCount));
            setLayoutParams(new LinearLayout.LayoutParams(((LinearLayout.LayoutParams) getLayoutParams()).width, this.itemHeight * this.displayItemCount));
        }
        return textView;
    }

    private void refreshItemView(int i) {
        int i2 = this.itemHeight;
        int i3 = this.offset;
        int i4 = (i / i2) + i3;
        int i5 = i % i2;
        int i6 = i / i2;
        if (i5 == 0) {
            i4 = i6 + i3;
        } else if (i5 > i2 / 2) {
            i4 = i6 + i3 + 1;
        }
        int childCount = this.views.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            TextView textView = (TextView) this.views.getChildAt(i7);
            if (textView == null) {
                return;
            }
            if (i4 == i7) {
                textView.setTextColor(this.textColorFocus);
            } else {
                textView.setTextColor(this.textColorNormal);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] obtainSelectedAreaBorder() {
        if (this.selectedAreaBorder == null) {
            this.selectedAreaBorder = new int[]{r1 * r2, r1 * (r2 + 1)};
            int i = this.itemHeight;
            int i2 = this.offset;
        }
        return this.selectedAreaBorder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSelectedCallBack() {
        OnWheelViewListener onWheelViewListener = this.onWheelViewListener;
        if (onWheelViewListener != null) {
            boolean z = this.isUserScroll;
            int i = this.selectedIndex;
            onWheelViewListener.onSelected(z, i - this.offset, this.items.get(i));
        }
    }

    private int dip2px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int getViewMeasuredHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        return view.getMeasuredHeight();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.viewWidth == 0) {
            this.viewWidth = ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth();
            LogUtils.debug(this, "viewWidth: " + this.viewWidth);
        }
        if (this.lineVisible) {
            if (this.paint == null) {
                Paint paint = new Paint();
                this.paint = paint;
                paint.setColor(this.lineColor);
                this.paint.setStrokeWidth(dip2px(1.0f));
            }
            super.setBackgroundDrawable(new Drawable() { // from class: cn.qqtheme.framework.widget.WheelView.1
                @Override // android.graphics.drawable.Drawable
                public int getOpacity() {
                    return 0;
                }

                @Override // android.graphics.drawable.Drawable
                public void setAlpha(int i) {
                }

                @Override // android.graphics.drawable.Drawable
                public void setColorFilter(ColorFilter colorFilter) {
                }

                @Override // android.graphics.drawable.Drawable
                public void draw(Canvas canvas) {
                    int[] obtainSelectedAreaBorder = WheelView.this.obtainSelectedAreaBorder();
                    canvas.drawLine(WheelView.this.viewWidth / 6, obtainSelectedAreaBorder[0], (WheelView.this.viewWidth * 5) / 6, obtainSelectedAreaBorder[0], WheelView.this.paint);
                    canvas.drawLine(WheelView.this.viewWidth / 6, obtainSelectedAreaBorder[1], (WheelView.this.viewWidth * 5) / 6, obtainSelectedAreaBorder[1], WheelView.this.paint);
                }
            });
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        refreshItemView(i2);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        LogUtils.debug(this, "w: " + i + ", h: " + i2 + ", oldw: " + i3 + ", oldh: " + i4);
        this.viewWidth = i;
        setBackgroundDrawable(null);
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        super.fling(i / 3);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.previousY = motionEvent.getY();
        } else if (action == 1) {
            LogUtils.debug(this, String.format("items=%s, offset=%s", Integer.valueOf(this.items.size()), Integer.valueOf(this.offset)));
            LogUtils.debug(this, "selectedIndex=" + this.selectedIndex);
            float y = motionEvent.getY() - this.previousY;
            LogUtils.debug(this, "delta=" + y);
            int i = this.selectedIndex;
            if (i == this.offset && y > 0.0f) {
                setSelectedIndex((this.items.size() - (this.offset * 2)) - 1);
            } else if (i == (this.items.size() - this.offset) - 1 && y < 0.0f) {
                setSelectedIndex(0);
            } else {
                this.isUserScroll = true;
                startScrollerTask();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void _setItems(List<String> list) {
        this.items.clear();
        this.items.addAll(list);
        for (int i = 0; i < this.offset; i++) {
            this.items.add(0, "");
            this.items.add("");
        }
        initData();
    }

    public void setItems(List<String> list) {
        _setItems(list);
        setSelectedIndex(0);
    }

    public void setItems(List<String> list, int i) {
        _setItems(list);
        setSelectedIndex(i);
    }

    public void setItems(List<String> list, String str) {
        _setItems(list);
        setSelectedItem(str);
    }

    public int getTextSize() {
        return this.textSize;
    }

    public void setTextSize(int i) {
        this.textSize = i;
    }

    public int getTextColor() {
        return this.textColorFocus;
    }

    public void setTextColor(int i, int i2) {
        this.textColorNormal = i;
        this.textColorFocus = i2;
    }

    public void setTextColor(int i) {
        this.textColorFocus = i;
    }

    public boolean isLineVisible() {
        return this.lineVisible;
    }

    public void setLineVisible(boolean z) {
        this.lineVisible = z;
    }

    public int getLineColor() {
        return this.lineColor;
    }

    public void setLineColor(int i) {
        this.lineColor = i;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException("Offset must between 1 and 4");
        }
        this.offset = i;
    }

    private void setSelectedIndex(final int i) {
        this.isUserScroll = false;
        post(new Runnable() { // from class: cn.qqtheme.framework.widget.WheelView.2
            @Override // java.lang.Runnable
            public void run() {
                WheelView wheelView = WheelView.this;
                wheelView.smoothScrollTo(0, i * wheelView.itemHeight);
                WheelView wheelView2 = WheelView.this;
                wheelView2.selectedIndex = i + wheelView2.offset;
                WheelView.this.onSelectedCallBack();
            }
        });
    }

    public void setSelectedItem(String str) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).equals(str)) {
                setSelectedIndex(i - this.offset);
                return;
            }
        }
    }

    @Deprecated
    public String getSeletedItem() {
        return getSelectedItem();
    }

    public String getSelectedItem() {
        return this.items.get(this.selectedIndex);
    }

    @Deprecated
    public int getSeletedIndex() {
        return getSelectedIndex();
    }

    public int getSelectedIndex() {
        return this.selectedIndex - this.offset;
    }

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
    }

    private class ScrollerTask implements Runnable {
        private ScrollerTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (WheelView.this.itemHeight == 0) {
                LogUtils.debug(this, "itemHeight is zero");
                return;
            }
            if (WheelView.this.initialY - WheelView.this.getScrollY() == 0) {
                final int i = WheelView.this.initialY % WheelView.this.itemHeight;
                final int i2 = WheelView.this.initialY / WheelView.this.itemHeight;
                LogUtils.debug(this, "initialY: " + WheelView.this.initialY + ", remainder: " + i + ", divided: " + i2);
                if (i != 0) {
                    if (i > WheelView.this.itemHeight / 2) {
                        WheelView.this.post(new Runnable() { // from class: cn.qqtheme.framework.widget.WheelView.ScrollerTask.1
                            @Override // java.lang.Runnable
                            public void run() {
                                WheelView.this.smoothScrollTo(0, (WheelView.this.initialY - i) + WheelView.this.itemHeight);
                                WheelView.this.selectedIndex = i2 + WheelView.this.offset + 1;
                                WheelView.this.onSelectedCallBack();
                            }
                        });
                        return;
                    } else {
                        WheelView.this.post(new Runnable() { // from class: cn.qqtheme.framework.widget.WheelView.ScrollerTask.2
                            @Override // java.lang.Runnable
                            public void run() {
                                WheelView.this.smoothScrollTo(0, WheelView.this.initialY - i);
                                WheelView.this.selectedIndex = i2 + WheelView.this.offset;
                                WheelView.this.onSelectedCallBack();
                            }
                        });
                        return;
                    }
                }
                WheelView wheelView = WheelView.this;
                wheelView.selectedIndex = i2 + wheelView.offset;
                WheelView.this.onSelectedCallBack();
                return;
            }
            WheelView.this.startScrollerTask();
        }
    }
}
