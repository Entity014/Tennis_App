package com.kyleduo.switchbutton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;

/* loaded from: classes3.dex */
public class SwitchButton extends CompoundButton {
    public static final int DEFAULT_ANIMATION_DURATION = 250;
    public static final int DEFAULT_THUMB_MARGIN_DP = 2;
    public static final float DEFAULT_THUMB_RANGE_RATIO = 1.8f;
    public static final int DEFAULT_THUMB_SIZE_DP = 20;
    public static final int DEFAULT_TINT_COLOR = 3309506;
    private long mAnimationDuration;
    private ColorStateList mBackColor;
    private Drawable mBackDrawable;
    private int mBackHeight;
    private float mBackRadius;
    private RectF mBackRectF;
    private int mBackWidth;
    private boolean mCatch;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private int mClickTimeout;
    private int mCurrBackColor;
    private int mCurrThumbColor;
    private Drawable mCurrentBackDrawable;
    private boolean mDrawDebugRect;
    private boolean mFadeBack;
    private boolean mIsBackUseDrawable;
    private boolean mIsThumbUseDrawable;
    private float mLastX;
    private int mNextBackColor;
    private Drawable mNextBackDrawable;
    private Layout mOffLayout;
    private int mOffTextColor;
    private Layout mOnLayout;
    private int mOnTextColor;
    private Paint mPaint;
    private RectF mPresentThumbRectF;
    private float mProgress;
    private ValueAnimator mProgressAnimator;
    private boolean mReady;
    private Paint mRectPaint;
    private boolean mRestoring;
    private RectF mSafeRectF;
    private float mStartX;
    private float mStartY;
    private int mTextAdjust;
    private int mTextExtra;
    private float mTextHeight;
    private CharSequence mTextOff;
    private RectF mTextOffRectF;
    private CharSequence mTextOn;
    private RectF mTextOnRectF;
    private TextPaint mTextPaint;
    private int mTextThumbInset;
    private float mTextWidth;
    private ColorStateList mThumbColor;
    private Drawable mThumbDrawable;
    private int mThumbHeight;
    private RectF mThumbMargin;
    private float mThumbRadius;
    private float mThumbRangeRatio;
    private RectF mThumbRectF;
    private int mThumbWidth;
    private int mTintColor;
    private int mTouchSlop;
    private UnsetPressedState mUnsetPressedState;
    private static final int[] CHECKED_PRESSED_STATE = {android.R.attr.state_checked, android.R.attr.state_enabled, android.R.attr.state_pressed};
    private static final int[] UNCHECKED_PRESSED_STATE = {-16842912, android.R.attr.state_enabled, android.R.attr.state_pressed};

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(attributeSet);
    }

    public SwitchButton(Context context) {
        super(context);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(null);
    }

    private void init(AttributeSet attributeSet) {
        String str;
        float f;
        float f2;
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        Drawable drawable;
        float f3;
        ColorStateList colorStateList;
        Drawable drawable2;
        float f4;
        ColorStateList colorStateList2;
        boolean z;
        int i5;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        ColorStateList colorStateList3;
        TypedArray obtainStyledAttributes;
        boolean z2;
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mClickTimeout = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.mPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mRectPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mRectPaint.setStrokeWidth(getResources().getDisplayMetrics().density);
        this.mTextPaint = getPaint();
        this.mThumbRectF = new RectF();
        this.mBackRectF = new RectF();
        this.mSafeRectF = new RectF();
        this.mThumbMargin = new RectF();
        this.mTextOnRectF = new RectF();
        this.mTextOffRectF = new RectF();
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 0.0f).setDuration(250L);
        this.mProgressAnimator = duration;
        duration.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kyleduo.switchbutton.SwitchButton.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SwitchButton.this.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        this.mPresentThumbRectF = new RectF();
        float f11 = getResources().getDisplayMetrics().density * 2.0f;
        TypedArray obtainStyledAttributes2 = attributeSet == null ? null : getContext().obtainStyledAttributes(attributeSet, R.styleable.SwitchButton);
        if (obtainStyledAttributes2 != null) {
            Drawable drawable3 = obtainStyledAttributes2.getDrawable(R.styleable.SwitchButton_kswThumbDrawable);
            colorStateList = obtainStyledAttributes2.getColorStateList(R.styleable.SwitchButton_kswThumbColor);
            float dimension = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbMargin, f11);
            float dimension2 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbMarginLeft, dimension);
            float dimension3 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbMarginRight, dimension);
            float dimension4 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbMarginTop, dimension);
            float dimension5 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbMarginBottom, dimension);
            float dimension6 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbWidth, 0.0f);
            float dimension7 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbHeight, 0.0f);
            float dimension8 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswThumbRadius, -1.0f);
            float dimension9 = obtainStyledAttributes2.getDimension(R.styleable.SwitchButton_kswBackRadius, -1.0f);
            Drawable drawable4 = obtainStyledAttributes2.getDrawable(R.styleable.SwitchButton_kswBackDrawable);
            ColorStateList colorStateList4 = obtainStyledAttributes2.getColorStateList(R.styleable.SwitchButton_kswBackColor);
            float f12 = obtainStyledAttributes2.getFloat(R.styleable.SwitchButton_kswThumbRangeRatio, 1.8f);
            int integer = obtainStyledAttributes2.getInteger(R.styleable.SwitchButton_kswAnimationDuration, 250);
            boolean z3 = obtainStyledAttributes2.getBoolean(R.styleable.SwitchButton_kswFadeBack, true);
            int color = obtainStyledAttributes2.getColor(R.styleable.SwitchButton_kswTintColor, 0);
            String string = obtainStyledAttributes2.getString(R.styleable.SwitchButton_kswTextOn);
            String string2 = obtainStyledAttributes2.getString(R.styleable.SwitchButton_kswTextOff);
            int dimensionPixelSize = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SwitchButton_kswTextThumbInset, 0);
            int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SwitchButton_kswTextExtra, 0);
            int dimensionPixelSize3 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.SwitchButton_kswTextAdjust, 0);
            obtainStyledAttributes2.recycle();
            f8 = dimension3;
            f9 = dimension4;
            f = dimension5;
            str2 = string;
            colorStateList2 = colorStateList4;
            f4 = dimension2;
            i5 = integer;
            z = z3;
            i4 = color;
            f6 = dimension8;
            f7 = f12;
            drawable2 = drawable3;
            i = dimensionPixelSize;
            i3 = dimensionPixelSize3;
            f3 = dimension6;
            f2 = dimension7;
            drawable = drawable4;
            str = string2;
            f5 = dimension9;
            i2 = dimensionPixelSize2;
        } else {
            str = null;
            f = 0.0f;
            f2 = 0.0f;
            str2 = null;
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            drawable = null;
            f3 = 0.0f;
            colorStateList = null;
            drawable2 = null;
            f4 = 0.0f;
            colorStateList2 = null;
            z = true;
            i5 = 250;
            f5 = -1.0f;
            f6 = -1.0f;
            f7 = 1.8f;
            f8 = 0.0f;
            f9 = 0.0f;
        }
        float f13 = f;
        if (attributeSet == null) {
            f10 = f4;
            colorStateList3 = colorStateList2;
            obtainStyledAttributes = null;
        } else {
            f10 = f4;
            colorStateList3 = colorStateList2;
            obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{android.R.attr.focusable, android.R.attr.clickable});
        }
        if (obtainStyledAttributes != null) {
            z2 = true;
            boolean z4 = obtainStyledAttributes.getBoolean(0, true);
            boolean z5 = obtainStyledAttributes.getBoolean(1, z4);
            setFocusable(z4);
            setClickable(z5);
            obtainStyledAttributes.recycle();
        } else {
            z2 = true;
            setFocusable(true);
            setClickable(true);
        }
        this.mTextOn = str2;
        this.mTextOff = str;
        this.mTextThumbInset = i;
        this.mTextExtra = i2;
        this.mTextAdjust = i3;
        this.mThumbDrawable = drawable2;
        this.mThumbColor = colorStateList;
        this.mIsThumbUseDrawable = drawable2 != null;
        this.mTintColor = i4;
        if (i4 == 0) {
            this.mTintColor = getThemeAccentColorOrDefault(getContext(), DEFAULT_TINT_COLOR);
        }
        if (!this.mIsThumbUseDrawable && this.mThumbColor == null) {
            ColorStateList generateThumbColorWithTintColor = ColorUtils.generateThumbColorWithTintColor(this.mTintColor);
            this.mThumbColor = generateThumbColorWithTintColor;
            this.mCurrThumbColor = generateThumbColorWithTintColor.getDefaultColor();
        }
        this.mThumbWidth = ceil(f3);
        this.mThumbHeight = ceil(f2);
        this.mBackDrawable = drawable;
        ColorStateList colorStateList5 = colorStateList3;
        this.mBackColor = colorStateList5;
        if (drawable == null) {
            z2 = false;
        }
        this.mIsBackUseDrawable = z2;
        if (!z2 && colorStateList5 == null) {
            ColorStateList generateBackColorWithTintColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
            this.mBackColor = generateBackColorWithTintColor;
            int defaultColor = generateBackColorWithTintColor.getDefaultColor();
            this.mCurrBackColor = defaultColor;
            this.mNextBackColor = this.mBackColor.getColorForState(CHECKED_PRESSED_STATE, defaultColor);
        }
        this.mThumbMargin.set(f10, f9, f8, f13);
        float f14 = f7;
        this.mThumbRangeRatio = this.mThumbMargin.width() >= 0.0f ? Math.max(f14, 1.0f) : f14;
        this.mThumbRadius = f6;
        this.mBackRadius = f5;
        long j = i5;
        this.mAnimationDuration = j;
        this.mFadeBack = z;
        this.mProgressAnimator.setDuration(j);
        if (isChecked()) {
            setProgress(1.0f);
        }
    }

    private static int getThemeAccentColorOrDefault(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(android.R.attr.colorAccent, typedValue, true) ? typedValue.data : i;
    }

    private Layout makeLayout(CharSequence charSequence) {
        return new StaticLayout(charSequence, this.mTextPaint, (int) Math.ceil(Layout.getDesiredWidth(charSequence, r2)), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOnLayout == null && !TextUtils.isEmpty(this.mTextOn)) {
            this.mOnLayout = makeLayout(this.mTextOn);
        }
        if (this.mOffLayout == null && !TextUtils.isEmpty(this.mTextOff)) {
            this.mOffLayout = makeLayout(this.mTextOff);
        }
        float width = this.mOnLayout != null ? r0.getWidth() : 0.0f;
        float width2 = this.mOffLayout != null ? r2.getWidth() : 0.0f;
        if (width != 0.0f || width2 != 0.0f) {
            this.mTextWidth = Math.max(width, width2);
        } else {
            this.mTextWidth = 0.0f;
        }
        float height = this.mOnLayout != null ? r0.getHeight() : 0.0f;
        float height2 = this.mOffLayout != null ? r2.getHeight() : 0.0f;
        if (height != 0.0f || height2 != 0.0f) {
            this.mTextHeight = Math.max(height, height2);
        } else {
            this.mTextHeight = 0.0f;
        }
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (this.mThumbWidth == 0 && this.mIsThumbUseDrawable) {
            this.mThumbWidth = this.mThumbDrawable.getIntrinsicWidth();
        }
        int ceil = ceil(this.mTextWidth);
        if (this.mThumbRangeRatio == 0.0f) {
            this.mThumbRangeRatio = 1.8f;
        }
        if (mode == 1073741824) {
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            if (this.mThumbWidth != 0) {
                int ceil2 = ceil(r2 * this.mThumbRangeRatio);
                int ceil3 = (this.mTextExtra + ceil) - ((ceil2 - this.mThumbWidth) + ceil(Math.max(this.mThumbMargin.left, this.mThumbMargin.right)));
                float f = ceil2;
                int ceil4 = ceil(this.mThumbMargin.left + f + this.mThumbMargin.right + Math.max(ceil3, 0));
                this.mBackWidth = ceil4;
                if (ceil4 < 0) {
                    this.mThumbWidth = 0;
                }
                if (f + Math.max(this.mThumbMargin.left, 0.0f) + Math.max(this.mThumbMargin.right, 0.0f) + Math.max(ceil3, 0) > paddingLeft) {
                    this.mThumbWidth = 0;
                }
            }
            if (this.mThumbWidth != 0) {
                return size;
            }
            int ceil5 = ceil((((size - getPaddingLeft()) - getPaddingRight()) - Math.max(this.mThumbMargin.left, 0.0f)) - Math.max(this.mThumbMargin.right, 0.0f));
            if (ceil5 < 0) {
                this.mThumbWidth = 0;
                this.mBackWidth = 0;
                return size;
            }
            float f2 = ceil5;
            this.mThumbWidth = ceil(f2 / this.mThumbRangeRatio);
            int ceil6 = ceil(f2 + this.mThumbMargin.left + this.mThumbMargin.right);
            this.mBackWidth = ceil6;
            if (ceil6 < 0) {
                this.mThumbWidth = 0;
                this.mBackWidth = 0;
                return size;
            }
            int ceil7 = (ceil + this.mTextExtra) - ((ceil5 - this.mThumbWidth) + ceil(Math.max(this.mThumbMargin.left, this.mThumbMargin.right)));
            if (ceil7 > 0) {
                this.mThumbWidth -= ceil7;
            }
            if (this.mThumbWidth >= 0) {
                return size;
            }
            this.mThumbWidth = 0;
            this.mBackWidth = 0;
            return size;
        }
        if (this.mThumbWidth == 0) {
            this.mThumbWidth = ceil(getResources().getDisplayMetrics().density * 20.0f);
        }
        if (this.mThumbRangeRatio == 0.0f) {
            this.mThumbRangeRatio = 1.8f;
        }
        int ceil8 = ceil(this.mThumbWidth * this.mThumbRangeRatio);
        int ceil9 = ceil((ceil + this.mTextExtra) - (((ceil8 - this.mThumbWidth) + Math.max(this.mThumbMargin.left, this.mThumbMargin.right)) + this.mTextThumbInset));
        float f3 = ceil8;
        int ceil10 = ceil(this.mThumbMargin.left + f3 + this.mThumbMargin.right + Math.max(0, ceil9));
        this.mBackWidth = ceil10;
        if (ceil10 < 0) {
            this.mThumbWidth = 0;
            this.mBackWidth = 0;
            return size;
        }
        int ceil11 = ceil(f3 + Math.max(0.0f, this.mThumbMargin.left) + Math.max(0.0f, this.mThumbMargin.right) + Math.max(0, ceil9));
        return Math.max(ceil11, getPaddingLeft() + ceil11 + getPaddingRight());
    }

    private int measureHeight(int i) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (this.mThumbHeight == 0 && this.mIsThumbUseDrawable) {
            this.mThumbHeight = this.mThumbDrawable.getIntrinsicHeight();
        }
        if (mode == 1073741824) {
            if (this.mThumbHeight != 0) {
                this.mBackHeight = ceil(r6 + this.mThumbMargin.top + this.mThumbMargin.bottom);
                this.mBackHeight = ceil(Math.max(r6, this.mTextHeight));
                if ((((r6 + getPaddingTop()) + getPaddingBottom()) - Math.min(0.0f, this.mThumbMargin.top)) - Math.min(0.0f, this.mThumbMargin.bottom) > size) {
                    this.mThumbHeight = 0;
                }
            }
            if (this.mThumbHeight == 0) {
                int ceil = ceil(((size - getPaddingTop()) - getPaddingBottom()) + Math.min(0.0f, this.mThumbMargin.top) + Math.min(0.0f, this.mThumbMargin.bottom));
                this.mBackHeight = ceil;
                if (ceil < 0) {
                    this.mBackHeight = 0;
                    this.mThumbHeight = 0;
                    return size;
                }
                this.mThumbHeight = ceil((ceil - this.mThumbMargin.top) - this.mThumbMargin.bottom);
            }
            if (this.mThumbHeight >= 0) {
                return size;
            }
            this.mBackHeight = 0;
            this.mThumbHeight = 0;
            return size;
        }
        if (this.mThumbHeight == 0) {
            this.mThumbHeight = ceil(getResources().getDisplayMetrics().density * 20.0f);
        }
        int ceil2 = ceil(this.mThumbHeight + this.mThumbMargin.top + this.mThumbMargin.bottom);
        this.mBackHeight = ceil2;
        if (ceil2 < 0) {
            this.mBackHeight = 0;
            this.mThumbHeight = 0;
            return size;
        }
        int ceil3 = ceil(this.mTextHeight - ceil2);
        if (ceil3 > 0) {
            this.mBackHeight += ceil3;
            this.mThumbHeight += ceil3;
        }
        int max = Math.max(this.mThumbHeight, this.mBackHeight);
        return Math.max(Math.max(max, getPaddingTop() + max + getPaddingBottom()), getSuggestedMinimumHeight());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        setup();
    }

    private int ceil(double d) {
        return (int) Math.ceil(d);
    }

    private void setup() {
        int i;
        float paddingTop;
        float paddingLeft;
        if (this.mThumbWidth == 0 || (i = this.mThumbHeight) == 0 || this.mBackWidth == 0 || this.mBackHeight == 0) {
            return;
        }
        if (this.mThumbRadius == -1.0f) {
            this.mThumbRadius = Math.min(r0, i) / 2.0f;
        }
        if (this.mBackRadius == -1.0f) {
            this.mBackRadius = Math.min(this.mBackWidth, this.mBackHeight) / 2.0f;
        }
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int ceil = ceil((this.mBackWidth - Math.min(0.0f, this.mThumbMargin.left)) - Math.min(0.0f, this.mThumbMargin.right));
        if (measuredHeight <= ceil((this.mBackHeight - Math.min(0.0f, this.mThumbMargin.top)) - Math.min(0.0f, this.mThumbMargin.bottom))) {
            paddingTop = getPaddingTop() + Math.max(0.0f, this.mThumbMargin.top);
        } else {
            paddingTop = (((measuredHeight - r3) + 1) / 2.0f) + getPaddingTop() + Math.max(0.0f, this.mThumbMargin.top);
        }
        if (measuredWidth <= this.mBackWidth) {
            paddingLeft = getPaddingLeft() + Math.max(0.0f, this.mThumbMargin.left);
        } else {
            paddingLeft = (((measuredWidth - ceil) + 1) / 2.0f) + getPaddingLeft() + Math.max(0.0f, this.mThumbMargin.left);
        }
        this.mThumbRectF.set(paddingLeft, paddingTop, this.mThumbWidth + paddingLeft, this.mThumbHeight + paddingTop);
        float f = this.mThumbRectF.left - this.mThumbMargin.left;
        this.mBackRectF.set(f, this.mThumbRectF.top - this.mThumbMargin.top, this.mBackWidth + f, (this.mThumbRectF.top - this.mThumbMargin.top) + this.mBackHeight);
        this.mSafeRectF.set(this.mThumbRectF.left, 0.0f, (this.mBackRectF.right - this.mThumbMargin.right) - this.mThumbRectF.width(), 0.0f);
        this.mBackRadius = Math.min(Math.min(this.mBackRectF.width(), this.mBackRectF.height()) / 2.0f, this.mBackRadius);
        Drawable drawable = this.mBackDrawable;
        if (drawable != null) {
            drawable.setBounds((int) this.mBackRectF.left, (int) this.mBackRectF.top, ceil(this.mBackRectF.right), ceil(this.mBackRectF.bottom));
        }
        if (this.mOnLayout != null) {
            float width = (this.mBackRectF.left + (((((this.mBackRectF.width() + this.mTextThumbInset) - this.mThumbWidth) - this.mThumbMargin.right) - this.mOnLayout.getWidth()) / 2.0f)) - this.mTextAdjust;
            float height = this.mBackRectF.top + ((this.mBackRectF.height() - this.mOnLayout.getHeight()) / 2.0f);
            this.mTextOnRectF.set(width, height, this.mOnLayout.getWidth() + width, this.mOnLayout.getHeight() + height);
        }
        if (this.mOffLayout != null) {
            float width2 = ((this.mBackRectF.right - (((((this.mBackRectF.width() + this.mTextThumbInset) - this.mThumbWidth) - this.mThumbMargin.left) - this.mOffLayout.getWidth()) / 2.0f)) - this.mOffLayout.getWidth()) + this.mTextAdjust;
            float height2 = this.mBackRectF.top + ((this.mBackRectF.height() - this.mOffLayout.getHeight()) / 2.0f);
            this.mTextOffRectF.set(width2, height2, this.mOffLayout.getWidth() + width2, this.mOffLayout.getHeight() + height2);
        }
        this.mReady = true;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mReady) {
            setup();
        }
        if (this.mReady) {
            if (this.mIsBackUseDrawable) {
                if (this.mFadeBack && this.mCurrentBackDrawable != null && this.mNextBackDrawable != null) {
                    Drawable drawable = isChecked() ? this.mCurrentBackDrawable : this.mNextBackDrawable;
                    Drawable drawable2 = isChecked() ? this.mNextBackDrawable : this.mCurrentBackDrawable;
                    int progress = (int) (getProgress() * 255.0f);
                    drawable.setAlpha(progress);
                    drawable.draw(canvas);
                    drawable2.setAlpha(255 - progress);
                    drawable2.draw(canvas);
                } else {
                    this.mBackDrawable.setAlpha(255);
                    this.mBackDrawable.draw(canvas);
                }
            } else if (this.mFadeBack) {
                int i = isChecked() ? this.mCurrBackColor : this.mNextBackColor;
                int i2 = isChecked() ? this.mNextBackColor : this.mCurrBackColor;
                int progress2 = (int) (getProgress() * 255.0f);
                this.mPaint.setARGB((Color.alpha(i) * progress2) / 255, Color.red(i), Color.green(i), Color.blue(i));
                RectF rectF = this.mBackRectF;
                float f = this.mBackRadius;
                canvas.drawRoundRect(rectF, f, f, this.mPaint);
                this.mPaint.setARGB((Color.alpha(i2) * (255 - progress2)) / 255, Color.red(i2), Color.green(i2), Color.blue(i2));
                RectF rectF2 = this.mBackRectF;
                float f2 = this.mBackRadius;
                canvas.drawRoundRect(rectF2, f2, f2, this.mPaint);
                this.mPaint.setAlpha(255);
            } else {
                this.mPaint.setColor(this.mCurrBackColor);
                RectF rectF3 = this.mBackRectF;
                float f3 = this.mBackRadius;
                canvas.drawRoundRect(rectF3, f3, f3, this.mPaint);
            }
            Layout layout = ((double) getProgress()) > 0.5d ? this.mOnLayout : this.mOffLayout;
            RectF rectF4 = ((double) getProgress()) > 0.5d ? this.mTextOnRectF : this.mTextOffRectF;
            if (layout != null && rectF4 != null) {
                double progress3 = getProgress();
                float progress4 = getProgress();
                int progress5 = (int) ((progress3 >= 0.75d ? (progress4 * 4.0f) - 3.0f : ((double) progress4) < 0.25d ? 1.0f - (getProgress() * 4.0f) : 0.0f) * 255.0f);
                int i3 = ((double) getProgress()) > 0.5d ? this.mOnTextColor : this.mOffTextColor;
                layout.getPaint().setARGB((Color.alpha(i3) * progress5) / 255, Color.red(i3), Color.green(i3), Color.blue(i3));
                canvas.save();
                canvas.translate(rectF4.left, rectF4.top);
                layout.draw(canvas);
                canvas.restore();
            }
            this.mPresentThumbRectF.set(this.mThumbRectF);
            this.mPresentThumbRectF.offset(this.mProgress * this.mSafeRectF.width(), 0.0f);
            if (this.mIsThumbUseDrawable) {
                this.mThumbDrawable.setBounds((int) this.mPresentThumbRectF.left, (int) this.mPresentThumbRectF.top, ceil(this.mPresentThumbRectF.right), ceil(this.mPresentThumbRectF.bottom));
                this.mThumbDrawable.draw(canvas);
            } else {
                this.mPaint.setColor(this.mCurrThumbColor);
                RectF rectF5 = this.mPresentThumbRectF;
                float f4 = this.mThumbRadius;
                canvas.drawRoundRect(rectF5, f4, f4, this.mPaint);
            }
            if (this.mDrawDebugRect) {
                this.mRectPaint.setColor(Color.parseColor("#AA0000"));
                canvas.drawRect(this.mBackRectF, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor("#0000FF"));
                canvas.drawRect(this.mPresentThumbRectF, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor("#000000"));
                canvas.drawLine(this.mSafeRectF.left, this.mThumbRectF.top, this.mSafeRectF.right, this.mThumbRectF.top, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor("#00CC00"));
                canvas.drawRect(((double) getProgress()) > 0.5d ? this.mTextOnRectF : this.mTextOffRectF, this.mRectPaint);
            }
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        super.drawableStateChanged();
        if (!this.mIsThumbUseDrawable && (colorStateList2 = this.mThumbColor) != null) {
            this.mCurrThumbColor = colorStateList2.getColorForState(getDrawableState(), this.mCurrThumbColor);
        } else {
            setDrawableState(this.mThumbDrawable);
        }
        int[] iArr = isChecked() ? UNCHECKED_PRESSED_STATE : CHECKED_PRESSED_STATE;
        ColorStateList textColors = getTextColors();
        if (textColors != null) {
            int defaultColor = textColors.getDefaultColor();
            this.mOnTextColor = textColors.getColorForState(CHECKED_PRESSED_STATE, defaultColor);
            this.mOffTextColor = textColors.getColorForState(UNCHECKED_PRESSED_STATE, defaultColor);
        }
        if (!this.mIsBackUseDrawable && (colorStateList = this.mBackColor) != null) {
            int colorForState = colorStateList.getColorForState(getDrawableState(), this.mCurrBackColor);
            this.mCurrBackColor = colorForState;
            this.mNextBackColor = this.mBackColor.getColorForState(iArr, colorForState);
            return;
        }
        Drawable drawable = this.mBackDrawable;
        if ((drawable instanceof StateListDrawable) && this.mFadeBack) {
            drawable.setState(iArr);
            this.mNextBackDrawable = this.mBackDrawable.getCurrent().mutate();
        } else {
            this.mNextBackDrawable = null;
        }
        setDrawableState(this.mBackDrawable);
        Drawable drawable2 = this.mBackDrawable;
        if (drawable2 != null) {
            this.mCurrentBackDrawable = drawable2.getCurrent().mutate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r0 != 3) goto L54;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kyleduo.switchbutton.SwitchButton.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean getStatusBasedOnPos() {
        return getProgress() > 0.5f;
    }

    private float getProgress() {
        return this.mProgress;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProgress(float f) {
        if (f > 1.0f) {
            f = 1.0f;
        } else if (f < 0.0f) {
            f = 0.0f;
        }
        this.mProgress = f;
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    protected void animateToState(boolean z) {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator == null) {
            return;
        }
        if (valueAnimator.isRunning()) {
            this.mProgressAnimator.cancel();
        }
        this.mProgressAnimator.setDuration(this.mAnimationDuration);
        if (z) {
            this.mProgressAnimator.setFloatValues(this.mProgress, 1.0f);
        } else {
            this.mProgressAnimator.setFloatValues(this.mProgress, 0.0f);
        }
        this.mProgressAnimator.start();
    }

    private void catchView() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        this.mCatch = true;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (isChecked() != z) {
            animateToState(z);
        }
        if (this.mRestoring) {
            setCheckedImmediatelyNoEvent(z);
        } else {
            super.setChecked(z);
        }
    }

    public void setCheckedNoEvent(boolean z) {
        if (this.mChildOnCheckedChangeListener == null) {
            setChecked(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setChecked(z);
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void setCheckedImmediatelyNoEvent(boolean z) {
        if (this.mChildOnCheckedChangeListener == null) {
            setCheckedImmediately(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setCheckedImmediately(z);
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void toggleNoEvent() {
        if (this.mChildOnCheckedChangeListener == null) {
            toggle();
            return;
        }
        super.setOnCheckedChangeListener(null);
        toggle();
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void toggleImmediatelyNoEvent() {
        if (this.mChildOnCheckedChangeListener == null) {
            toggleImmediately();
            return;
        }
        super.setOnCheckedChangeListener(null);
        toggleImmediately();
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mChildOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setCheckedImmediately(boolean z) {
        super.setChecked(z);
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mProgressAnimator.cancel();
        }
        setProgress(z ? 1.0f : 0.0f);
        invalidate();
    }

    public void toggleImmediately() {
        setCheckedImmediately(!isChecked());
    }

    private void setDrawableState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(getDrawableState());
            invalidate();
        }
    }

    public boolean isDrawDebugRect() {
        return this.mDrawDebugRect;
    }

    public void setDrawDebugRect(boolean z) {
        this.mDrawDebugRect = z;
        invalidate();
    }

    public long getAnimationDuration() {
        return this.mAnimationDuration;
    }

    public void setAnimationDuration(long j) {
        this.mAnimationDuration = j;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public void setThumbDrawable(Drawable drawable) {
        this.mThumbDrawable = drawable;
        this.mIsThumbUseDrawable = drawable != null;
        refreshDrawableState();
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setThumbDrawableRes(int i) {
        setThumbDrawable(getDrawableCompat(getContext(), i));
    }

    public Drawable getBackDrawable() {
        return this.mBackDrawable;
    }

    public void setBackDrawable(Drawable drawable) {
        this.mBackDrawable = drawable;
        this.mIsBackUseDrawable = drawable != null;
        refreshDrawableState();
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setBackDrawableRes(int i) {
        setBackDrawable(getDrawableCompat(getContext(), i));
    }

    public ColorStateList getBackColor() {
        return this.mBackColor;
    }

    public void setBackColor(ColorStateList colorStateList) {
        this.mBackColor = colorStateList;
        if (colorStateList != null) {
            setBackDrawable(null);
        }
        invalidate();
    }

    public void setBackColorRes(int i) {
        setBackColor(getColorStateListCompat(getContext(), i));
    }

    public ColorStateList getThumbColor() {
        return this.mThumbColor;
    }

    public void setThumbColor(ColorStateList colorStateList) {
        this.mThumbColor = colorStateList;
        if (colorStateList != null) {
            setThumbDrawable(null);
        }
        invalidate();
    }

    public void setThumbColorRes(int i) {
        setThumbColor(getColorStateListCompat(getContext(), i));
    }

    public float getThumbRangeRatio() {
        return this.mThumbRangeRatio;
    }

    public void setThumbRangeRatio(float f) {
        this.mThumbRangeRatio = f;
        this.mReady = false;
        requestLayout();
    }

    public RectF getThumbMargin() {
        return this.mThumbMargin;
    }

    public void setThumbMargin(RectF rectF) {
        if (rectF == null) {
            setThumbMargin(0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            setThumbMargin(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    public void setThumbMargin(float f, float f2, float f3, float f4) {
        this.mThumbMargin.set(f, f2, f3, f4);
        this.mReady = false;
        requestLayout();
    }

    public void setThumbSize(int i, int i2) {
        this.mThumbWidth = i;
        this.mThumbHeight = i2;
        this.mReady = false;
        requestLayout();
    }

    public float getThumbWidth() {
        return this.mThumbWidth;
    }

    public float getThumbHeight() {
        return this.mThumbHeight;
    }

    public float getThumbRadius() {
        return this.mThumbRadius;
    }

    public void setThumbRadius(float f) {
        this.mThumbRadius = f;
        if (this.mIsThumbUseDrawable) {
            return;
        }
        invalidate();
    }

    public PointF getBackSizeF() {
        return new PointF(this.mBackRectF.width(), this.mBackRectF.height());
    }

    public float getBackRadius() {
        return this.mBackRadius;
    }

    public void setBackRadius(float f) {
        this.mBackRadius = f;
        if (this.mIsBackUseDrawable) {
            return;
        }
        invalidate();
    }

    public boolean isFadeBack() {
        return this.mFadeBack;
    }

    public void setFadeBack(boolean z) {
        this.mFadeBack = z;
    }

    public int getTintColor() {
        return this.mTintColor;
    }

    public void setTintColor(int i) {
        this.mTintColor = i;
        this.mThumbColor = ColorUtils.generateThumbColorWithTintColor(i);
        this.mBackColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
        this.mIsBackUseDrawable = false;
        this.mIsThumbUseDrawable = false;
        refreshDrawableState();
        invalidate();
    }

    public void setText(CharSequence charSequence, CharSequence charSequence2) {
        this.mTextOn = charSequence;
        this.mTextOff = charSequence2;
        this.mOnLayout = null;
        this.mOffLayout = null;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public void setTextThumbInset(int i) {
        this.mTextThumbInset = i;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setTextExtra(int i) {
        this.mTextExtra = i;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setTextAdjust(int i) {
        this.mTextAdjust = i;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.onText = this.mTextOn;
        savedState.offText = this.mTextOff;
        return savedState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        setText(savedState.onText, savedState.offText);
        this.mRestoring = true;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mRestoring = false;
    }

    private Drawable getDrawableCompat(Context context, int i) {
        return context.getDrawable(i);
    }

    private ColorStateList getColorStateListCompat(Context context, int i) {
        ColorStateList colorStateList;
        if (Build.VERSION.SDK_INT >= 23) {
            colorStateList = context.getColorStateList(i);
            return colorStateList;
        }
        return context.getResources().getColorStateList(i);
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.kyleduo.switchbutton.SwitchButton.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        CharSequence offText;
        CharSequence onText;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.onText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.offText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.onText, parcel, i);
            TextUtils.writeToParcel(this.offText, parcel, i);
        }
    }

    private final class UnsetPressedState implements Runnable {
        private UnsetPressedState() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SwitchButton.this.setPressed(false);
        }
    }
}
