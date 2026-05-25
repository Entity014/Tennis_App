package com.warkiz.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public class IndicatorSeekBar extends View {
    private static final String FORMAT_PROGRESS = "${PROGRESS}";
    private static final String FORMAT_TICK_TEXT = "${TICK_TEXT}";
    private static final int THUMB_MAX_WIDTH = 30;
    private float lastProgress;
    private boolean mAdjustAuto;
    private RectF mBackgroundTrack;
    private int mBackgroundTrackColor;
    private int mBackgroundTrackSize;
    private boolean mClearPadding;
    private Context mContext;
    private float mCustomDrawableMaxHeight;
    private boolean mCustomTrackSectionColorResult;
    private float mFaultTolerance;
    private boolean mHideThumb;
    private int mHoveredTextColor;
    private Indicator mIndicator;
    private int mIndicatorColor;
    private View mIndicatorContentView;
    private boolean mIndicatorStayAlways;
    private int mIndicatorTextColor;
    private String mIndicatorTextFormat;
    private int mIndicatorTextSize;
    private View mIndicatorTopContentView;
    private boolean mIsFloatProgress;
    private boolean mIsTouching;
    private float mMax;
    private int mMeasuredWidth;
    private float mMin;
    private boolean mOnlyThumbDraggable;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Bitmap mPressedThumbBitmap;
    private int mPressedThumbColor;
    private float mProgress;
    private float[] mProgressArr;
    private RectF mProgressTrack;
    private int mProgressTrackColor;
    private int mProgressTrackSize;
    private boolean mR2L;
    private Rect mRect;
    private int mScale;
    private float mScreenWidth;
    private int[] mSectionTrackColorArray;
    private float mSeekBlockLength;
    private OnSeekChangeListener mSeekChangeListener;
    private float mSeekLength;
    private SeekParams mSeekParams;
    private boolean mSeekSmoothly;
    private Bitmap mSelectTickMarksBitmap;
    private int mSelectedTextsColor;
    private int mSelectedTickMarksColor;
    private boolean mShowBothTickTextsOnly;
    private int mShowIndicatorType;
    private boolean mShowThumbText;
    private int mShowTickMarksType;
    private boolean mShowTickText;
    private Paint mStockPaint;
    private float[] mTextCenterX;
    private TextPaint mTextPaint;
    private Typeface mTextsTypeface;
    private Bitmap mThumbBitmap;
    private int mThumbColor;
    private Drawable mThumbDrawable;
    private float mThumbRadius;
    private int mThumbSize;
    private int mThumbTextColor;
    private float mThumbTextY;
    private float mThumbTouchRadius;
    private Drawable mTickMarksDrawable;
    private boolean mTickMarksEndsHide;
    private int mTickMarksSize;
    private boolean mTickMarksSweptHide;
    private float[] mTickMarksX;
    private float mTickRadius;
    private float mTickTextY;
    private String[] mTickTextsArr;
    private CharSequence[] mTickTextsCustomArray;
    private int mTickTextsHeight;
    private int mTickTextsSize;
    private float[] mTickTextsWidth;
    private int mTicksCount;
    private boolean mTrackRoundedCorners;
    private int mUnSelectedTickMarksColor;
    private Bitmap mUnselectTickMarksBitmap;
    private int mUnselectedTextsColor;
    private boolean mUserSeekable;

    public IndicatorSeekBar(Context context) {
        this(context, null);
    }

    public IndicatorSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        this.mContext = context;
        initAttrs(context, attrs);
        initParams();
    }

    public IndicatorSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        this.mContext = context;
        initAttrs(context, attrs);
        initParams();
    }

    IndicatorSeekBar(Builder builder) {
        super(builder.context);
        this.mFaultTolerance = -1.0f;
        this.mScreenWidth = -1.0f;
        this.mScale = 1;
        Context context = builder.context;
        this.mContext = context;
        int dp2px = SizeUtils.dp2px(context, 16.0f);
        setPadding(dp2px, getPaddingTop(), dp2px, getPaddingBottom());
        apply(builder);
        initParams();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        Builder builder = new Builder(context);
        if (attrs == null) {
            apply(builder);
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.IndicatorSeekBar);
        this.mMax = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_max, builder.max);
        this.mMin = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_min, builder.min);
        this.mProgress = obtainStyledAttributes.getFloat(R.styleable.IndicatorSeekBar_isb_progress, builder.progress);
        this.mIsFloatProgress = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_progress_value_float, builder.progressValueFloat);
        this.mUserSeekable = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_user_seekable, builder.userSeekable);
        this.mClearPadding = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_clear_default_padding, builder.clearPadding);
        this.mOnlyThumbDraggable = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_only_thumb_draggable, builder.onlyThumbDraggable);
        this.mSeekSmoothly = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_seek_smoothly, builder.seekSmoothly);
        this.mR2L = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_r2l, builder.r2l);
        this.mBackgroundTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_background_size, builder.trackBackgroundSize);
        this.mProgressTrackSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_progress_size, builder.trackProgressSize);
        this.mBackgroundTrackColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_track_background_color, builder.trackBackgroundColor);
        this.mProgressTrackColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_track_progress_color, builder.trackProgressColor);
        this.mTrackRoundedCorners = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_track_rounded_corners, builder.trackRoundedCorners);
        this.mThumbSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_thumb_size, builder.thumbSize);
        this.mThumbDrawable = obtainStyledAttributes.getDrawable(R.styleable.IndicatorSeekBar_isb_thumb_drawable);
        this.mAdjustAuto = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_thumb_adjust_auto, true);
        initThumbColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_thumb_color), builder.thumbColor);
        this.mShowThumbText = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_show_thumb_text, builder.showThumbText);
        this.mThumbTextColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_thumb_text_color, builder.thumbTextColor);
        this.mTicksCount = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_ticks_count, builder.tickCount);
        this.mShowTickMarksType = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_show_tick_marks_type, builder.showTickMarksType);
        this.mTickMarksSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_tick_marks_size, builder.tickMarksSize);
        initTickMarksColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_tick_marks_color), builder.tickMarksColor);
        this.mTickMarksDrawable = obtainStyledAttributes.getDrawable(R.styleable.IndicatorSeekBar_isb_tick_marks_drawable);
        this.mTickMarksSweptHide = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_marks_swept_hide, builder.tickMarksSweptHide);
        this.mTickMarksEndsHide = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_marks_ends_hide, builder.tickMarksEndsHide);
        this.mShowTickText = obtainStyledAttributes.getBoolean(R.styleable.IndicatorSeekBar_isb_show_tick_texts, builder.showTickText);
        this.mTickTextsSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_tick_texts_size, builder.tickTextsSize);
        initTickTextsColor(obtainStyledAttributes.getColorStateList(R.styleable.IndicatorSeekBar_isb_tick_texts_color), builder.tickTextsColor);
        this.mTickTextsCustomArray = obtainStyledAttributes.getTextArray(R.styleable.IndicatorSeekBar_isb_tick_texts_array);
        initTextsTypeface(obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_tick_texts_typeface, -1), builder.tickTextsTypeFace);
        this.mShowIndicatorType = obtainStyledAttributes.getInt(R.styleable.IndicatorSeekBar_isb_show_indicator, builder.showIndicatorType);
        this.mIndicatorColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_indicator_color, builder.indicatorColor);
        this.mIndicatorTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_indicator_text_size, builder.indicatorTextSize);
        this.mIndicatorTextColor = obtainStyledAttributes.getColor(R.styleable.IndicatorSeekBar_isb_indicator_text_color, builder.indicatorTextColor);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_content_layout, 0);
        if (resourceId > 0) {
            this.mIndicatorContentView = View.inflate(this.mContext, resourceId, null);
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_top_content_layout, 0);
        if (resourceId2 > 0) {
            this.mIndicatorTopContentView = View.inflate(this.mContext, resourceId2, null);
        }
        obtainStyledAttributes.recycle();
    }

    private void initParams() {
        initProgressRangeValue();
        int i = this.mBackgroundTrackSize;
        int i2 = this.mProgressTrackSize;
        if (i > i2) {
            this.mBackgroundTrackSize = i2;
        }
        if (this.mThumbDrawable == null) {
            float f = this.mThumbSize / 2.0f;
            this.mThumbRadius = f;
            this.mThumbTouchRadius = f * 1.2f;
        } else {
            float min = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize) / 2.0f;
            this.mThumbRadius = min;
            this.mThumbTouchRadius = min;
        }
        if (this.mTickMarksDrawable == null) {
            this.mTickRadius = this.mTickMarksSize / 2.0f;
        } else {
            this.mTickRadius = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize) / 2.0f;
        }
        this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, this.mTickRadius) * 2.0f;
        initStrokePaint();
        measureTickTextsBonds();
        this.lastProgress = this.mProgress;
        collectTicksInfo();
        this.mProgressTrack = new RectF();
        this.mBackgroundTrack = new RectF();
        initDefaultPadding();
        initIndicatorContentView();
    }

    private void collectTicksInfo() {
        int i = this.mTicksCount;
        if (i < 0 || i > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        }
        if (i == 0) {
            return;
        }
        this.mTickMarksX = new float[i];
        if (this.mShowTickText) {
            this.mTextCenterX = new float[i];
            this.mTickTextsWidth = new float[i];
        }
        this.mProgressArr = new float[i];
        int i2 = 0;
        while (true) {
            float[] fArr = this.mProgressArr;
            if (i2 >= fArr.length) {
                return;
            }
            float f = this.mMin;
            fArr[i2] = f + ((i2 * (this.mMax - f)) / (this.mTicksCount + (-1) > 0 ? r4 - 1 : 1));
            i2++;
        }
    }

    private void initDefaultPadding() {
        if (this.mClearPadding) {
            return;
        }
        int dp2px = SizeUtils.dp2px(this.mContext, 16.0f);
        if (getPaddingLeft() == 0) {
            setPadding(dp2px, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        if (getPaddingRight() == 0) {
            setPadding(getPaddingLeft(), getPaddingTop(), dp2px, getPaddingBottom());
        }
    }

    private void initProgressRangeValue() {
        float f = this.mMax;
        float f2 = this.mMin;
        if (f < f2) {
            throw new IllegalArgumentException("the Argument: MAX's value must be larger than MIN's.");
        }
        if (this.mProgress < f2) {
            this.mProgress = f2;
        }
        if (this.mProgress > f) {
            this.mProgress = f;
        }
    }

    private void initStrokePaint() {
        if (this.mStockPaint == null) {
            this.mStockPaint = new Paint();
        }
        if (this.mTrackRoundedCorners) {
            this.mStockPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        this.mStockPaint.setAntiAlias(true);
        int i = this.mBackgroundTrackSize;
        if (i > this.mProgressTrackSize) {
            this.mProgressTrackSize = i;
        }
    }

    private void measureTickTextsBonds() {
        if (needDrawText()) {
            initTextPaint();
            this.mTextPaint.setTypeface(this.mTextsTypeface);
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            this.mTickTextsHeight = this.mRect.height() + SizeUtils.dp2px(this.mContext, 3.0f);
        }
    }

    private boolean needDrawText() {
        return this.mShowThumbText || (this.mTicksCount != 0 && this.mShowTickText);
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            TextPaint textPaint = new TextPaint();
            this.mTextPaint = textPaint;
            textPaint.setAntiAlias(true);
            this.mTextPaint.setTextAlign(Paint.Align.CENTER);
            this.mTextPaint.setTextSize(this.mTickTextsSize);
        }
        if (this.mRect == null) {
            this.mRect = new Rect();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(SizeUtils.dp2px(this.mContext, 170.0f), widthMeasureSpec), Math.round(this.mCustomDrawableMaxHeight + getPaddingTop() + getPaddingBottom()) + this.mTickTextsHeight);
        initSeekBarInfo();
        refreshSeekBarLocation();
    }

    private void initSeekBarInfo() {
        this.mMeasuredWidth = getMeasuredWidth();
        this.mPaddingLeft = getPaddingStart();
        this.mPaddingRight = getPaddingEnd();
        this.mPaddingTop = getPaddingTop();
        float f = (this.mMeasuredWidth - this.mPaddingLeft) - this.mPaddingRight;
        this.mSeekLength = f;
        this.mSeekBlockLength = f / (this.mTicksCount + (-1) > 0 ? r1 - 1 : 1);
    }

    private void refreshSeekBarLocation() {
        initTrackLocation();
        if (needDrawText()) {
            this.mTextPaint.getTextBounds("j", 0, 1, this.mRect);
            float round = this.mPaddingTop + this.mCustomDrawableMaxHeight + Math.round(this.mRect.height() - this.mTextPaint.descent()) + SizeUtils.dp2px(this.mContext, 3.0f);
            this.mTickTextY = round;
            this.mThumbTextY = round;
        }
        if (this.mTickMarksX == null) {
            return;
        }
        initTextsArray();
        if (this.mTicksCount > 2) {
            float f = this.mProgressArr[getClosestIndex()];
            this.mProgress = f;
            this.lastProgress = f;
        }
        refreshThumbCenterXByProgress(this.mProgress);
    }

    private void initTextsArray() {
        int i = this.mTicksCount;
        if (i == 0) {
            return;
        }
        if (this.mShowTickText) {
            this.mTickTextsArr = new String[i];
        }
        for (int i2 = 0; i2 < this.mTickMarksX.length; i2++) {
            if (this.mShowTickText) {
                this.mTickTextsArr[i2] = getTickTextByPosition(i2);
                TextPaint textPaint = this.mTextPaint;
                String str = this.mTickTextsArr[i2];
                textPaint.getTextBounds(str, 0, str.length(), this.mRect);
                this.mTickTextsWidth[i2] = this.mRect.width();
                this.mTextCenterX[i2] = this.mPaddingLeft + (this.mSeekBlockLength * i2);
            }
            this.mTickMarksX[i2] = this.mPaddingLeft + (this.mSeekBlockLength * i2);
        }
    }

    private void initTrackLocation() {
        if (this.mR2L) {
            this.mBackgroundTrack.left = this.mPaddingLeft;
            this.mBackgroundTrack.top = this.mPaddingTop + this.mThumbTouchRadius;
            this.mBackgroundTrack.right = this.mPaddingLeft + (this.mSeekLength * (1.0f - ((this.mProgress - this.mMin) / getAmplitude())));
            RectF rectF = this.mBackgroundTrack;
            rectF.bottom = rectF.top;
            this.mProgressTrack.left = this.mBackgroundTrack.right;
            this.mProgressTrack.top = this.mBackgroundTrack.top;
            this.mProgressTrack.right = this.mMeasuredWidth - this.mPaddingRight;
            this.mProgressTrack.bottom = this.mBackgroundTrack.bottom;
            return;
        }
        this.mProgressTrack.left = this.mPaddingLeft;
        this.mProgressTrack.top = this.mPaddingTop + this.mThumbTouchRadius;
        this.mProgressTrack.right = (((this.mProgress - this.mMin) * this.mSeekLength) / getAmplitude()) + this.mPaddingLeft;
        RectF rectF2 = this.mProgressTrack;
        rectF2.bottom = rectF2.top;
        this.mBackgroundTrack.left = this.mProgressTrack.right;
        this.mBackgroundTrack.top = this.mProgressTrack.bottom;
        this.mBackgroundTrack.right = this.mMeasuredWidth - this.mPaddingRight;
        this.mBackgroundTrack.bottom = this.mProgressTrack.bottom;
    }

    private String getTickTextByPosition(int index) {
        CharSequence[] charSequenceArr = this.mTickTextsCustomArray;
        if (charSequenceArr == null) {
            return getProgressString(this.mProgressArr[index]);
        }
        if (index < charSequenceArr.length) {
            return String.valueOf(charSequenceArr[index]);
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshThumbCenterXByProgress(float progress) {
        if (this.mR2L) {
            this.mBackgroundTrack.right = this.mPaddingLeft + (this.mSeekLength * (1.0f - ((progress - this.mMin) / getAmplitude())));
            this.mProgressTrack.left = this.mBackgroundTrack.right;
            return;
        }
        this.mProgressTrack.right = (((progress - this.mMin) * this.mSeekLength) / getAmplitude()) + this.mPaddingLeft;
        this.mBackgroundTrack.left = this.mProgressTrack.right;
    }

    @Override // android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        drawTrack(canvas);
        drawTickMarks(canvas);
        drawTickTexts(canvas);
        drawThumb(canvas);
        drawThumbText(canvas);
    }

    private void drawTrack(Canvas canvas) {
        if (this.mCustomTrackSectionColorResult) {
            int i = this.mTicksCount;
            int i2 = i + (-1) > 0 ? i - 1 : 1;
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.mR2L) {
                    this.mStockPaint.setColor(this.mSectionTrackColorArray[(i2 - i3) - 1]);
                } else {
                    this.mStockPaint.setColor(this.mSectionTrackColorArray[i3]);
                }
                float thumbPosOnTickFloat = getThumbPosOnTickFloat();
                float f = i3;
                if (f < thumbPosOnTickFloat) {
                    int i4 = i3 + 1;
                    if (thumbPosOnTickFloat < i4) {
                        float thumbCenterX = getThumbCenterX();
                        this.mStockPaint.setStrokeWidth(getLeftSideTrackSize());
                        canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, thumbCenterX, this.mProgressTrack.bottom, this.mStockPaint);
                        this.mStockPaint.setStrokeWidth(getRightSideTrackSize());
                        canvas.drawLine(thumbCenterX, this.mProgressTrack.top, this.mTickMarksX[i4], this.mProgressTrack.bottom, this.mStockPaint);
                    }
                }
                if (f < thumbPosOnTickFloat) {
                    this.mStockPaint.setStrokeWidth(getLeftSideTrackSize());
                } else {
                    this.mStockPaint.setStrokeWidth(getRightSideTrackSize());
                }
                canvas.drawLine(this.mTickMarksX[i3], this.mProgressTrack.top, this.mTickMarksX[i3 + 1], this.mProgressTrack.bottom, this.mStockPaint);
            }
            return;
        }
        this.mStockPaint.setColor(this.mProgressTrackColor);
        this.mStockPaint.setStrokeWidth(this.mProgressTrackSize);
        canvas.drawLine(this.mProgressTrack.left, this.mProgressTrack.top, this.mProgressTrack.right, this.mProgressTrack.bottom, this.mStockPaint);
        this.mStockPaint.setColor(this.mBackgroundTrackColor);
        this.mStockPaint.setStrokeWidth(this.mBackgroundTrackSize);
        canvas.drawLine(this.mBackgroundTrack.left, this.mBackgroundTrack.top, this.mBackgroundTrack.right, this.mBackgroundTrack.bottom, this.mStockPaint);
    }

    private void drawTickMarks(Canvas canvas) {
        int rightSideTrackSize;
        Bitmap bitmap;
        if (this.mTicksCount != 0) {
            if (this.mShowTickMarksType == 0 && this.mTickMarksDrawable == null) {
                return;
            }
            float thumbCenterX = getThumbCenterX();
            for (int i = 0; i < this.mTickMarksX.length; i++) {
                float thumbPosOnTickFloat = getThumbPosOnTickFloat();
                if ((!this.mTickMarksSweptHide || thumbCenterX < this.mTickMarksX[i]) && ((!this.mTickMarksEndsHide || (i != 0 && i != this.mTickMarksX.length - 1)) && (i != getThumbPosOnTick() || this.mTicksCount <= 2 || this.mSeekSmoothly))) {
                    float f = i;
                    if (f <= thumbPosOnTickFloat) {
                        this.mStockPaint.setColor(getLeftSideTickColor());
                    } else {
                        this.mStockPaint.setColor(getRightSideTickColor());
                    }
                    if (this.mTickMarksDrawable != null) {
                        if (this.mSelectTickMarksBitmap == null || this.mUnselectTickMarksBitmap == null) {
                            initTickMarksBitmap();
                        }
                        Bitmap bitmap2 = this.mSelectTickMarksBitmap;
                        if (bitmap2 == null || (bitmap = this.mUnselectTickMarksBitmap) == null) {
                            throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
                        }
                        if (f <= thumbPosOnTickFloat) {
                            canvas.drawBitmap(bitmap2, this.mTickMarksX[i] - (bitmap.getWidth() / 2.0f), this.mProgressTrack.top - (this.mUnselectTickMarksBitmap.getHeight() / 2.0f), this.mStockPaint);
                        } else {
                            canvas.drawBitmap(bitmap, this.mTickMarksX[i] - (bitmap.getWidth() / 2.0f), this.mProgressTrack.top - (this.mUnselectTickMarksBitmap.getHeight() / 2.0f), this.mStockPaint);
                        }
                    } else {
                        int i2 = this.mShowTickMarksType;
                        if (i2 == 1) {
                            canvas.drawCircle(this.mTickMarksX[i], this.mProgressTrack.top, this.mTickRadius, this.mStockPaint);
                        } else if (i2 == 3) {
                            int dp2px = SizeUtils.dp2px(this.mContext, 1.0f);
                            if (thumbCenterX >= this.mTickMarksX[i]) {
                                rightSideTrackSize = getLeftSideTrackSize();
                            } else {
                                rightSideTrackSize = getRightSideTrackSize();
                            }
                            float f2 = dp2px;
                            float f3 = rightSideTrackSize / 2.0f;
                            canvas.drawRect(this.mTickMarksX[i] - f2, this.mProgressTrack.top - f3, this.mTickMarksX[i] + f2, this.mProgressTrack.top + f3, this.mStockPaint);
                        } else if (i2 == 2) {
                            float f4 = this.mTickMarksX[i] - (this.mTickMarksSize / 2.0f);
                            float f5 = this.mProgressTrack.top;
                            int i3 = this.mTickMarksSize;
                            canvas.drawRect(f4, f5 - (i3 / 2.0f), this.mTickMarksX[i] + (i3 / 2.0f), this.mProgressTrack.top + (this.mTickMarksSize / 2.0f), this.mStockPaint);
                        }
                    }
                }
            }
        }
    }

    private void drawTickTexts(Canvas canvas) {
        if (this.mTickTextsArr == null) {
            return;
        }
        float thumbPosOnTickFloat = getThumbPosOnTickFloat();
        int i = 0;
        while (true) {
            if (i >= this.mTickTextsArr.length) {
                return;
            }
            if (!this.mShowBothTickTextsOnly || i == 0 || i == r2.length - 1) {
                if (i == getThumbPosOnTick() && i == thumbPosOnTickFloat) {
                    this.mTextPaint.setColor(this.mHoveredTextColor);
                } else if (i < thumbPosOnTickFloat) {
                    this.mTextPaint.setColor(getLeftSideTickTextsColor());
                } else {
                    this.mTextPaint.setColor(getRightSideTickTextsColor());
                }
                int length = this.mR2L ? (this.mTickTextsArr.length - i) - 1 : i;
                if (i == 0) {
                    canvas.drawText(this.mTickTextsArr[length], this.mTextCenterX[i] + (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                } else {
                    String[] strArr = this.mTickTextsArr;
                    if (i == strArr.length - 1) {
                        canvas.drawText(strArr[length], this.mTextCenterX[i] - (this.mTickTextsWidth[length] / 2.0f), this.mTickTextY, this.mTextPaint);
                    } else {
                        canvas.drawText(strArr[length], this.mTextCenterX[i], this.mTickTextY, this.mTextPaint);
                    }
                }
            }
            i++;
        }
    }

    private void drawThumb(Canvas canvas) {
        if (this.mHideThumb) {
            return;
        }
        float thumbCenterX = getThumbCenterX();
        if (this.mThumbDrawable != null) {
            if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
                initThumbBitmap();
            }
            if (this.mThumbBitmap == null || this.mPressedThumbBitmap == null) {
                throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
            }
            this.mStockPaint.setAlpha(255);
            if (this.mIsTouching) {
                canvas.drawBitmap(this.mPressedThumbBitmap, thumbCenterX - (r1.getWidth() / 2.0f), this.mProgressTrack.top - (this.mPressedThumbBitmap.getHeight() / 2.0f), this.mStockPaint);
                return;
            } else {
                canvas.drawBitmap(this.mThumbBitmap, thumbCenterX - (r1.getWidth() / 2.0f), this.mProgressTrack.top - (this.mThumbBitmap.getHeight() / 2.0f), this.mStockPaint);
                return;
            }
        }
        if (this.mIsTouching) {
            this.mStockPaint.setColor(this.mPressedThumbColor);
        } else {
            this.mStockPaint.setColor(this.mThumbColor);
        }
        canvas.drawCircle(thumbCenterX, this.mProgressTrack.top, this.mIsTouching ? this.mThumbTouchRadius : this.mThumbRadius, this.mStockPaint);
    }

    private void drawThumbText(Canvas canvas) {
        if (this.mShowThumbText) {
            if (!this.mShowTickText || this.mTicksCount <= 2) {
                this.mTextPaint.setColor(this.mThumbTextColor);
                canvas.drawText(getProgressString(this.mProgress), getThumbCenterX(), this.mThumbTextY, this.mTextPaint);
            }
        }
    }

    private float getThumbCenterX() {
        if (this.mR2L) {
            return this.mBackgroundTrack.right;
        }
        return this.mProgressTrack.right;
    }

    private int getLeftSideTickColor() {
        if (this.mR2L) {
            return this.mUnSelectedTickMarksColor;
        }
        return this.mSelectedTickMarksColor;
    }

    private int getRightSideTickColor() {
        if (this.mR2L) {
            return this.mSelectedTickMarksColor;
        }
        return this.mUnSelectedTickMarksColor;
    }

    private int getLeftSideTickTextsColor() {
        if (this.mR2L) {
            return this.mUnselectedTextsColor;
        }
        return this.mSelectedTextsColor;
    }

    private int getRightSideTickTextsColor() {
        if (this.mR2L) {
            return this.mSelectedTextsColor;
        }
        return this.mUnselectedTextsColor;
    }

    private int getLeftSideTrackSize() {
        if (this.mR2L) {
            return this.mBackgroundTrackSize;
        }
        return this.mProgressTrackSize;
    }

    private int getRightSideTrackSize() {
        if (this.mR2L) {
            return this.mProgressTrackSize;
        }
        return this.mBackgroundTrackSize;
    }

    private int getThumbPosOnTick() {
        if (this.mTicksCount != 0) {
            return Math.round((getThumbCenterX() - this.mPaddingLeft) / this.mSeekBlockLength);
        }
        return 0;
    }

    private float getThumbPosOnTickFloat() {
        if (this.mTicksCount != 0) {
            return (getThumbCenterX() - this.mPaddingLeft) / this.mSeekBlockLength;
        }
        return 0.0f;
    }

    private int getHeightByRatio(Drawable drawable, int width) {
        return Math.round(((width * 1.0f) * drawable.getIntrinsicHeight()) / drawable.getIntrinsicWidth());
    }

    private Bitmap getDrawBitmap(Drawable drawable, boolean isThumb) {
        int intrinsicHeight;
        int i;
        if (drawable == null) {
            return null;
        }
        int dp2px = SizeUtils.dp2px(this.mContext, 30.0f);
        if (drawable.getIntrinsicWidth() > dp2px) {
            if (isThumb) {
                i = this.mThumbSize;
            } else {
                i = this.mTickMarksSize;
            }
            intrinsicHeight = getHeightByRatio(drawable, i);
            if (i > dp2px) {
                intrinsicHeight = getHeightByRatio(drawable, dp2px);
            } else {
                dp2px = i;
            }
        } else {
            dp2px = drawable.getIntrinsicWidth();
            intrinsicHeight = drawable.getIntrinsicHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(dp2px, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private void initThumbColor(ColorStateList colorStateList, int defaultColor) {
        if (colorStateList == null) {
            this.mThumbColor = defaultColor;
            this.mPressedThumbColor = defaultColor;
            return;
        }
        int[][] iArr = null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                int i = iArr2[0];
                this.mThumbColor = i;
                this.mPressedThumbColor = i;
            } else {
                if (iArr.length == 2) {
                    for (int i2 = 0; i2 < iArr.length; i2++) {
                        int[] iArr3 = iArr[i2];
                        if (iArr3.length == 0) {
                            this.mPressedThumbColor = iArr2[i2];
                        } else if (iArr3[0] == 16842919) {
                            this.mThumbColor = iArr2[i2];
                        } else {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("the selector color file you set for the argument: isb_thumb_color is in wrong format.");
            }
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTickMarksColor(ColorStateList colorStateList, int defaultColor) {
        if (colorStateList == null) {
            this.mSelectedTickMarksColor = defaultColor;
            this.mUnSelectedTickMarksColor = defaultColor;
            return;
        }
        int[][] iArr = null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                int i = iArr2[0];
                this.mSelectedTickMarksColor = i;
                this.mUnSelectedTickMarksColor = i;
            } else {
                if (iArr.length == 2) {
                    for (int i2 = 0; i2 < iArr.length; i2++) {
                        int[] iArr3 = iArr[i2];
                        if (iArr3.length == 0) {
                            this.mUnSelectedTickMarksColor = iArr2[i2];
                        } else if (iArr3[0] == 16842913) {
                            this.mSelectedTickMarksColor = iArr2[i2];
                        } else {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_marks_color is in wrong format.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Something wrong happened when parsing thumb selector color." + e.getMessage());
        }
    }

    private void initTickTextsColor(ColorStateList colorStateList, int defaultColor) {
        if (colorStateList == null) {
            this.mUnselectedTextsColor = defaultColor;
            this.mSelectedTextsColor = defaultColor;
            this.mHoveredTextColor = defaultColor;
            return;
        }
        int[][] iArr = null;
        try {
            int[] iArr2 = null;
            for (Field field : colorStateList.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if ("mStateSpecs".equals(field.getName())) {
                    iArr = (int[][]) field.get(colorStateList);
                }
                if ("mColors".equals(field.getName())) {
                    iArr2 = (int[]) field.get(colorStateList);
                }
            }
            if (iArr == null || iArr2 == null) {
                return;
            }
            if (iArr.length == 1) {
                int i = iArr2[0];
                this.mUnselectedTextsColor = i;
                this.mSelectedTextsColor = i;
                this.mHoveredTextColor = i;
                return;
            }
            if (iArr.length == 3) {
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    int[] iArr3 = iArr[i2];
                    if (iArr3.length == 0) {
                        this.mUnselectedTextsColor = iArr2[i2];
                    } else {
                        int i3 = iArr3[0];
                        if (i3 == 16842913) {
                            this.mSelectedTextsColor = iArr2[i2];
                        } else if (i3 == 16843623) {
                            this.mHoveredTextColor = iArr2[i2];
                        } else {
                            throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
                        }
                    }
                }
                return;
            }
            throw new IllegalArgumentException("the selector color file you set for the argument: isb_tick_texts_color is in wrong format.");
        } catch (Exception unused) {
            throw new RuntimeException("Something wrong happened when parseing thumb selector color.");
        }
    }

    private void initTextsTypeface(int typeface, Typeface defaultTypeface) {
        if (typeface == 0) {
            this.mTextsTypeface = Typeface.DEFAULT;
            return;
        }
        if (typeface == 1) {
            this.mTextsTypeface = Typeface.MONOSPACE;
            return;
        }
        if (typeface == 2) {
            this.mTextsTypeface = Typeface.SANS_SERIF;
            return;
        }
        if (typeface == 3) {
            this.mTextsTypeface = Typeface.SERIF;
        } else if (defaultTypeface == null) {
            this.mTextsTypeface = Typeface.DEFAULT;
        } else {
            this.mTextsTypeface = defaultTypeface;
        }
    }

    private void initThumbBitmap() {
        Drawable drawable = this.mThumbDrawable;
        if (drawable == null) {
            return;
        }
        if (drawable instanceof StateListDrawable) {
            try {
                StateListDrawable stateListDrawable = (StateListDrawable) drawable;
                Class<?> cls = stateListDrawable.getClass();
                int intValue = ((Integer) cls.getMethod("getStateCount", null).invoke(stateListDrawable, null)).intValue();
                if (intValue == 2) {
                    Method method = cls.getMethod("getStateSet", Integer.TYPE);
                    Method method2 = cls.getMethod("getStateDrawable", Integer.TYPE);
                    for (int i = 0; i < intValue; i++) {
                        int[] iArr = (int[]) method.invoke(stateListDrawable, Integer.valueOf(i));
                        if (iArr.length > 0) {
                            if (iArr[0] == 16842919) {
                                this.mPressedThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), true);
                            } else {
                                throw new IllegalArgumentException("the state of the selector thumb drawable is wrong!");
                            }
                        } else {
                            this.mThumbBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), true);
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("the format of the selector thumb drawable is wrong!");
            } catch (Exception unused) {
                Bitmap drawBitmap = getDrawBitmap(this.mThumbDrawable, true);
                this.mThumbBitmap = drawBitmap;
                this.mPressedThumbBitmap = drawBitmap;
                return;
            }
        }
        Bitmap drawBitmap2 = getDrawBitmap(drawable, true);
        this.mThumbBitmap = drawBitmap2;
        this.mPressedThumbBitmap = drawBitmap2;
    }

    private void initTickMarksBitmap() {
        Drawable drawable = this.mTickMarksDrawable;
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            try {
                Class<?> cls = stateListDrawable.getClass();
                int intValue = ((Integer) cls.getMethod("getStateCount", null).invoke(stateListDrawable, null)).intValue();
                if (intValue == 2) {
                    Method method = cls.getMethod("getStateSet", Integer.TYPE);
                    Method method2 = cls.getMethod("getStateDrawable", Integer.TYPE);
                    for (int i = 0; i < intValue; i++) {
                        int[] iArr = (int[]) method.invoke(stateListDrawable, Integer.valueOf(i));
                        if (iArr.length > 0) {
                            if (iArr[0] == 16842913) {
                                this.mSelectTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), false);
                            } else {
                                throw new IllegalArgumentException("the state of the selector TickMarks drawable is wrong!");
                            }
                        } else {
                            this.mUnselectTickMarksBitmap = getDrawBitmap((Drawable) method2.invoke(stateListDrawable, Integer.valueOf(i)), false);
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("the format of the selector TickMarks drawable is wrong!");
            } catch (Exception unused) {
                Bitmap drawBitmap = getDrawBitmap(this.mTickMarksDrawable, false);
                this.mUnselectTickMarksBitmap = drawBitmap;
                this.mSelectTickMarksBitmap = drawBitmap;
                return;
            }
        }
        Bitmap drawBitmap2 = getDrawBitmap(drawable, false);
        this.mUnselectTickMarksBitmap = drawBitmap2;
        this.mSelectTickMarksBitmap = drawBitmap2;
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        if (enabled == isEnabled()) {
            return;
        }
        super.setEnabled(enabled);
        if (isEnabled()) {
            setAlpha(1.0f);
            if (this.mIndicatorStayAlways) {
                this.mIndicatorContentView.setAlpha(1.0f);
                return;
            }
            return;
        }
        setAlpha(0.3f);
        if (this.mIndicatorStayAlways) {
            this.mIndicatorContentView.setAlpha(0.3f);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        post(new Runnable() { // from class: com.warkiz.widget.IndicatorSeekBar.1
            @Override // java.lang.Runnable
            public void run() {
                IndicatorSeekBar.this.requestLayout();
            }
        });
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();
        if (parent == null) {
            return super.dispatchTouchEvent(event);
        }
        int action = event.getAction();
        if (action == 0) {
            parent.requestDisallowInterceptTouchEvent(true);
        } else if (action == 1 || action == 3) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(event);
    }

    @Override // android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("isb_instance_state", super.onSaveInstanceState());
        bundle.putFloat("isb_progress", this.mProgress);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            setProgress(bundle.getFloat("isb_progress"));
            super.onRestoreInstanceState(bundle.getParcelable("isb_instance_state"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0019, code lost:
    
        if (r0 != 3) goto L37;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            boolean r0 = r4.mUserSeekable
            r1 = 0
            if (r0 == 0) goto L68
            boolean r0 = r4.isEnabled()
            if (r0 != 0) goto Lc
            goto L68
        Lc:
            int r0 = r5.getAction()
            r2 = 1
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L20
            r2 = 2
            if (r0 == r2) goto L1c
            r2 = 3
            if (r0 == r2) goto L20
            goto L63
        L1c:
            r4.refreshSeekBar(r5)
            goto L63
        L20:
            r4.mIsTouching = r1
            com.warkiz.widget.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L29
            r0.onStopTrackingTouch(r4)
        L29:
            boolean r0 = r4.autoAdjustThumb()
            if (r0 != 0) goto L32
            r4.invalidate()
        L32:
            com.warkiz.widget.Indicator r0 = r4.mIndicator
            if (r0 == 0) goto L63
            r0.hide()
            goto L63
        L3a:
            r4.performClick()
            float r0 = r5.getX()
            float r3 = r5.getY()
            boolean r3 = r4.isTouchSeekBar(r0, r3)
            if (r3 == 0) goto L63
            boolean r3 = r4.mOnlyThumbDraggable
            if (r3 == 0) goto L56
            boolean r0 = r4.isTouchThumb(r0)
            if (r0 != 0) goto L56
            return r1
        L56:
            r4.mIsTouching = r2
            com.warkiz.widget.OnSeekChangeListener r0 = r4.mSeekChangeListener
            if (r0 == 0) goto L5f
            r0.onStartTrackingTouch(r4)
        L5f:
            r4.refreshSeekBar(r5)
            return r2
        L63:
            boolean r5 = super.onTouchEvent(r5)
            return r5
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.warkiz.widget.IndicatorSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void refreshSeekBar(MotionEvent event) {
        refreshThumbCenterXByProgress(calculateProgress(calculateTouchX(adjustTouchX(event))));
        setSeekListener(true);
        invalidate();
        updateIndicator();
    }

    private boolean progressChange() {
        return this.mIsFloatProgress ? this.lastProgress != this.mProgress : Math.round(this.lastProgress) != Math.round(this.mProgress);
    }

    private float adjustTouchX(MotionEvent event) {
        float x = event.getX();
        int i = this.mPaddingLeft;
        if (x >= i) {
            float x2 = event.getX();
            int i2 = this.mMeasuredWidth;
            int i3 = this.mPaddingRight;
            if (x2 <= i2 - i3) {
                return event.getX();
            }
            i = i2 - i3;
        }
        return i;
    }

    private float calculateProgress(float touchX) {
        this.lastProgress = this.mProgress;
        float amplitude = this.mMin + ((getAmplitude() * (touchX - this.mPaddingLeft)) / this.mSeekLength);
        this.mProgress = amplitude;
        return amplitude;
    }

    private float calculateTouchX(float touchX) {
        if (this.mTicksCount > 2 && !this.mSeekSmoothly) {
            touchX = this.mPaddingLeft + (this.mSeekBlockLength * Math.round((touchX - this.mPaddingLeft) / this.mSeekBlockLength));
        }
        return this.mR2L ? (this.mSeekLength - touchX) + (this.mPaddingLeft * 2) : touchX;
    }

    private boolean isTouchSeekBar(float mX, float mY) {
        if (this.mFaultTolerance == -1.0f) {
            this.mFaultTolerance = SizeUtils.dp2px(this.mContext, 5.0f);
        }
        float f = this.mPaddingLeft;
        float f2 = this.mFaultTolerance;
        return ((mX > (f - (f2 * 2.0f)) ? 1 : (mX == (f - (f2 * 2.0f)) ? 0 : -1)) >= 0 && (mX > (((float) (this.mMeasuredWidth - this.mPaddingRight)) + (f2 * 2.0f)) ? 1 : (mX == (((float) (this.mMeasuredWidth - this.mPaddingRight)) + (f2 * 2.0f)) ? 0 : -1)) <= 0) && ((mY > ((this.mProgressTrack.top - this.mThumbTouchRadius) - this.mFaultTolerance) ? 1 : (mY == ((this.mProgressTrack.top - this.mThumbTouchRadius) - this.mFaultTolerance) ? 0 : -1)) >= 0 && (mY > ((this.mProgressTrack.top + this.mThumbTouchRadius) + this.mFaultTolerance) ? 1 : (mY == ((this.mProgressTrack.top + this.mThumbTouchRadius) + this.mFaultTolerance) ? 0 : -1)) <= 0);
    }

    private boolean isTouchThumb(float mX) {
        float f;
        refreshThumbCenterXByProgress(this.mProgress);
        if (this.mR2L) {
            f = this.mBackgroundTrack.right;
        } else {
            f = this.mProgressTrack.right;
        }
        int i = this.mThumbSize;
        return f - (((float) i) / 2.0f) <= mX && mX <= f + (((float) i) / 2.0f);
    }

    private void updateIndicator() {
        if (this.mIndicatorStayAlways) {
            updateStayIndicator();
            return;
        }
        Indicator indicator = this.mIndicator;
        if (indicator == null) {
            return;
        }
        indicator.iniPop();
        if (this.mIndicator.isShowing()) {
            this.mIndicator.update(getThumbCenterX());
        } else {
            this.mIndicator.show(getThumbCenterX());
        }
    }

    private void initIndicatorContentView() {
        int i = this.mShowIndicatorType;
        if (i != 0 && this.mIndicator == null) {
            Indicator indicator = new Indicator(this.mContext, this, this.mIndicatorColor, i, this.mIndicatorTextSize, this.mIndicatorTextColor, this.mIndicatorContentView, this.mIndicatorTopContentView);
            this.mIndicator = indicator;
            this.mIndicatorContentView = indicator.getInsideContentView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStayIndicator() {
        Indicator indicator;
        int i;
        if (!this.mIndicatorStayAlways || (indicator = this.mIndicator) == null) {
            return;
        }
        indicator.setProgressTextView(getIndicatorTextString());
        int i2 = 0;
        this.mIndicatorContentView.measure(0, 0);
        int measuredWidth = this.mIndicatorContentView.getMeasuredWidth();
        float thumbCenterX = getThumbCenterX();
        if (this.mScreenWidth == -1.0f) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                this.mScreenWidth = displayMetrics.widthPixels;
            }
        }
        float f = measuredWidth / 2;
        float f2 = f + thumbCenterX;
        int i3 = this.mMeasuredWidth;
        if (f2 > i3) {
            i2 = i3 - measuredWidth;
            i = (int) ((thumbCenterX - i2) - f);
        } else if (thumbCenterX - f < 0.0f) {
            i = -((int) (f - thumbCenterX));
        } else {
            i2 = (int) (getThumbCenterX() - f);
            i = 0;
        }
        this.mIndicator.updateIndicatorLocation(i2);
        this.mIndicator.updateArrowViewLocation(i);
    }

    private boolean autoAdjustThumb() {
        if (this.mTicksCount < 3 || !this.mSeekSmoothly || !this.mAdjustAuto) {
            return false;
        }
        final int closestIndex = getClosestIndex();
        final float f = this.mProgress;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, Math.abs(f - this.mProgressArr[closestIndex]));
        ofFloat.start();
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.warkiz.widget.IndicatorSeekBar.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                IndicatorSeekBar indicatorSeekBar = IndicatorSeekBar.this;
                indicatorSeekBar.lastProgress = indicatorSeekBar.mProgress;
                if (f - IndicatorSeekBar.this.mProgressArr[closestIndex] > 0.0f) {
                    IndicatorSeekBar.this.mProgress = f - ((Float) animation.getAnimatedValue()).floatValue();
                } else {
                    IndicatorSeekBar.this.mProgress = f + ((Float) animation.getAnimatedValue()).floatValue();
                }
                IndicatorSeekBar indicatorSeekBar2 = IndicatorSeekBar.this;
                indicatorSeekBar2.refreshThumbCenterXByProgress(indicatorSeekBar2.mProgress);
                IndicatorSeekBar.this.setSeekListener(false);
                if (IndicatorSeekBar.this.mIndicator != null && IndicatorSeekBar.this.mIndicatorStayAlways) {
                    IndicatorSeekBar.this.mIndicator.refreshProgressText();
                    IndicatorSeekBar.this.updateStayIndicator();
                }
                IndicatorSeekBar.this.invalidate();
            }
        });
        return true;
    }

    private String getProgressString(float progress) {
        if (this.mIsFloatProgress) {
            return FormatUtils.fastFormat(progress, this.mScale);
        }
        return String.valueOf(Math.round(progress));
    }

    private int getClosestIndex() {
        float abs = Math.abs(this.mMax - this.mMin);
        int i = 0;
        int i2 = 0;
        while (true) {
            float[] fArr = this.mProgressArr;
            if (i >= fArr.length) {
                return i2;
            }
            float abs2 = Math.abs(fArr[i] - this.mProgress);
            if (abs2 <= abs) {
                i2 = i;
                abs = abs2;
            }
            i++;
        }
    }

    private float getAmplitude() {
        float f = this.mMax;
        float f2 = this.mMin;
        if (f - f2 > 0.0f) {
            return f - f2;
        }
        return 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeekListener(boolean formUser) {
        if (this.mSeekChangeListener != null && progressChange()) {
            this.mSeekChangeListener.onSeeking(collectParams(formUser));
        }
    }

    private SeekParams collectParams(boolean formUser) {
        String[] strArr;
        if (this.mSeekParams == null) {
            this.mSeekParams = new SeekParams(this);
        }
        this.mSeekParams.progress = getProgress();
        this.mSeekParams.progressFloat = getProgressFloat();
        this.mSeekParams.fromUser = formUser;
        if (this.mTicksCount > 2) {
            int thumbPosOnTick = getThumbPosOnTick();
            if (this.mShowTickText && (strArr = this.mTickTextsArr) != null) {
                this.mSeekParams.tickText = strArr[thumbPosOnTick];
            }
            if (this.mR2L) {
                this.mSeekParams.thumbPosition = (this.mTicksCount - thumbPosOnTick) - 1;
            } else {
                this.mSeekParams.thumbPosition = thumbPosOnTick;
            }
        }
        return this.mSeekParams;
    }

    private void apply(Builder builder) {
        this.mMax = builder.max;
        this.mMin = builder.min;
        this.mProgress = builder.progress;
        this.mIsFloatProgress = builder.progressValueFloat;
        this.mTicksCount = builder.tickCount;
        this.mSeekSmoothly = builder.seekSmoothly;
        this.mR2L = builder.r2l;
        this.mUserSeekable = builder.userSeekable;
        this.mClearPadding = builder.clearPadding;
        this.mOnlyThumbDraggable = builder.onlyThumbDraggable;
        this.mShowIndicatorType = builder.showIndicatorType;
        this.mIndicatorColor = builder.indicatorColor;
        this.mIndicatorTextColor = builder.indicatorTextColor;
        this.mIndicatorTextSize = builder.indicatorTextSize;
        this.mIndicatorContentView = builder.indicatorContentView;
        this.mIndicatorTopContentView = builder.indicatorTopContentView;
        this.mBackgroundTrackSize = builder.trackBackgroundSize;
        this.mBackgroundTrackColor = builder.trackBackgroundColor;
        this.mProgressTrackSize = builder.trackProgressSize;
        this.mProgressTrackColor = builder.trackProgressColor;
        this.mTrackRoundedCorners = builder.trackRoundedCorners;
        this.mThumbSize = builder.thumbSize;
        this.mThumbDrawable = builder.thumbDrawable;
        this.mThumbTextColor = builder.thumbTextColor;
        initThumbColor(builder.thumbColorStateList, builder.thumbColor);
        this.mShowThumbText = builder.showThumbText;
        this.mShowTickMarksType = builder.showTickMarksType;
        this.mTickMarksSize = builder.tickMarksSize;
        this.mTickMarksDrawable = builder.tickMarksDrawable;
        this.mTickMarksEndsHide = builder.tickMarksEndsHide;
        this.mTickMarksSweptHide = builder.tickMarksSweptHide;
        initTickMarksColor(builder.tickMarksColorStateList, builder.tickMarksColor);
        this.mShowTickText = builder.showTickText;
        this.mTickTextsSize = builder.tickTextsSize;
        this.mTickTextsCustomArray = builder.tickTextsCustomArray;
        this.mTextsTypeface = builder.tickTextsTypeFace;
        initTickTextsColor(builder.tickTextsColorStateList, builder.tickTextsColor);
    }

    void showStayIndicator() {
        this.mIndicatorContentView.setVisibility(4);
        postDelayed(new Runnable() { // from class: com.warkiz.widget.IndicatorSeekBar.3
            @Override // java.lang.Runnable
            public void run() {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                alphaAnimation.setDuration(180L);
                IndicatorSeekBar.this.mIndicatorContentView.setAnimation(alphaAnimation);
                IndicatorSeekBar.this.updateStayIndicator();
                IndicatorSeekBar.this.mIndicatorContentView.setVisibility(0);
            }
        }, 300L);
    }

    void setIndicatorStayAlways(boolean indicatorStayAlways) {
        this.mIndicatorStayAlways = indicatorStayAlways;
    }

    View getIndicatorContentView() {
        return this.mIndicatorContentView;
    }

    String getIndicatorTextString() {
        String[] strArr;
        String str = this.mIndicatorTextFormat;
        if (str != null && str.contains(FORMAT_TICK_TEXT)) {
            if (this.mTicksCount > 2 && (strArr = this.mTickTextsArr) != null) {
                return this.mIndicatorTextFormat.replace(FORMAT_TICK_TEXT, strArr[getThumbPosOnTick()]);
            }
        } else {
            String str2 = this.mIndicatorTextFormat;
            if (str2 != null && str2.contains(FORMAT_PROGRESS)) {
                return this.mIndicatorTextFormat.replace(FORMAT_PROGRESS, getProgressString(this.mProgress));
            }
        }
        return getProgressString(this.mProgress);
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public Indicator getIndicator() {
        return this.mIndicator;
    }

    public int getTickCount() {
        return this.mTicksCount;
    }

    public synchronized float getProgressFloat() {
        return BigDecimal.valueOf(this.mProgress).setScale(this.mScale, 4).floatValue();
    }

    public int getProgress() {
        return Math.round(this.mProgress);
    }

    public float getMax() {
        return this.mMax;
    }

    public float getMin() {
        return this.mMin;
    }

    public OnSeekChangeListener getOnSeekChangeListener() {
        return this.mSeekChangeListener;
    }

    public synchronized void setProgress(float progress) {
        this.lastProgress = this.mProgress;
        float f = this.mMin;
        if (progress >= f) {
            f = this.mMax;
            if (progress > f) {
            }
            this.mProgress = progress;
            if (!this.mSeekSmoothly && this.mTicksCount > 2) {
                this.mProgress = this.mProgressArr[getClosestIndex()];
            }
            setSeekListener(false);
            refreshThumbCenterXByProgress(this.mProgress);
            postInvalidate();
            updateStayIndicator();
        }
        progress = f;
        this.mProgress = progress;
        if (!this.mSeekSmoothly) {
            this.mProgress = this.mProgressArr[getClosestIndex()];
        }
        setSeekListener(false);
        refreshThumbCenterXByProgress(this.mProgress);
        postInvalidate();
        updateStayIndicator();
    }

    public synchronized void setMax(float max) {
        this.mMax = Math.max(this.mMin, max);
        initProgressRangeValue();
        collectTicksInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public synchronized void setMin(float min) {
        this.mMin = Math.min(this.mMax, min);
        initProgressRangeValue();
        collectTicksInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public void setR2L(boolean isR2L) {
        this.mR2L = isR2L;
        requestLayout();
        invalidate();
        updateStayIndicator();
    }

    public void setThumbDrawable(Drawable drawable) {
        if (drawable == null) {
            this.mThumbDrawable = null;
            this.mThumbBitmap = null;
            this.mPressedThumbBitmap = null;
        } else {
            this.mThumbDrawable = drawable;
            float min = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mThumbSize) / 2.0f;
            this.mThumbRadius = min;
            this.mThumbTouchRadius = min;
            this.mCustomDrawableMaxHeight = Math.max(min, this.mTickRadius) * 2.0f;
            initThumbBitmap();
        }
        requestLayout();
        invalidate();
    }

    public void hideThumb(boolean hide) {
        this.mHideThumb = hide;
        invalidate();
    }

    public void hideThumbText(boolean hide) {
        this.mShowThumbText = !hide;
        invalidate();
    }

    public void thumbColor(int thumbColor) {
        this.mThumbColor = thumbColor;
        this.mPressedThumbColor = thumbColor;
        invalidate();
    }

    public void thumbColorStateList(ColorStateList thumbColorStateList) {
        initThumbColor(thumbColorStateList, this.mThumbColor);
        invalidate();
    }

    public void setTickMarksDrawable(Drawable drawable) {
        if (drawable == null) {
            this.mTickMarksDrawable = null;
            this.mUnselectTickMarksBitmap = null;
            this.mSelectTickMarksBitmap = null;
        } else {
            this.mTickMarksDrawable = drawable;
            float min = Math.min(SizeUtils.dp2px(this.mContext, 30.0f), this.mTickMarksSize) / 2.0f;
            this.mTickRadius = min;
            this.mCustomDrawableMaxHeight = Math.max(this.mThumbTouchRadius, min) * 2.0f;
            initTickMarksBitmap();
        }
        invalidate();
    }

    public void tickMarksColor(int tickMarksColor) {
        this.mSelectedTickMarksColor = tickMarksColor;
        this.mUnSelectedTickMarksColor = tickMarksColor;
        invalidate();
    }

    public void tickMarksColor(ColorStateList tickMarksColorStateList) {
        initTickMarksColor(tickMarksColorStateList, this.mSelectedTickMarksColor);
        invalidate();
    }

    public void tickTextsColor(int tickTextsColor) {
        this.mUnselectedTextsColor = tickTextsColor;
        this.mSelectedTextsColor = tickTextsColor;
        this.mHoveredTextColor = tickTextsColor;
        invalidate();
    }

    public void tickTextsColorStateList(ColorStateList tickTextsColorStateList) {
        initTickTextsColor(tickTextsColorStateList, this.mSelectedTextsColor);
        invalidate();
    }

    public void setDecimalScale(int scale) {
        this.mScale = scale;
    }

    public void setIndicatorTextFormat(String format) {
        this.mIndicatorTextFormat = format;
        initTextsArray();
        updateStayIndicator();
    }

    public void customSectionTrackColor(ColorCollector collector) {
        int i = this.mTicksCount;
        int i2 = i + (-1) > 0 ? i - 1 : 1;
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = this.mBackgroundTrackColor;
        }
        this.mCustomTrackSectionColorResult = collector.collectSectionTrackColor(iArr);
        this.mSectionTrackColorArray = iArr;
        invalidate();
    }

    public void customTickTexts(String[] tickTextsArr) {
        String str;
        this.mTickTextsCustomArray = tickTextsArr;
        if (this.mTickTextsArr != null) {
            for (int i = 0; i < this.mTickTextsArr.length; i++) {
                if (i < tickTextsArr.length) {
                    str = String.valueOf(tickTextsArr[i]);
                } else {
                    str = "";
                }
                int i2 = this.mR2L ? (this.mTicksCount - 1) - i : i;
                this.mTickTextsArr[i2] = str;
                TextPaint textPaint = this.mTextPaint;
                if (textPaint != null && this.mRect != null) {
                    textPaint.getTextBounds(str, 0, str.length(), this.mRect);
                    this.mTickTextsWidth[i2] = this.mRect.width();
                }
            }
            invalidate();
        }
    }

    public void customTickTextsTypeface(Typeface typeface) {
        this.mTextsTypeface = typeface;
        measureTickTextsBonds();
        requestLayout();
        invalidate();
    }

    public void setOnSeekChangeListener(OnSeekChangeListener listener) {
        this.mSeekChangeListener = listener;
    }

    public void showBothEndsTickTextsOnly(boolean onlyShow) {
        this.mShowBothTickTextsOnly = onlyShow;
    }

    public void setUserSeekAble(boolean seekAble) {
        this.mUserSeekable = seekAble;
    }

    public synchronized void setTickCount(int tickCount) {
        int i = this.mTicksCount;
        if (i < 0 || i > 50) {
            throw new IllegalArgumentException("the Argument: TICK COUNT must be limited between (0-50), Now is " + this.mTicksCount);
        }
        this.mTicksCount = tickCount;
        collectTicksInfo();
        initTextsArray();
        initSeekBarInfo();
        refreshSeekBarLocation();
        invalidate();
        updateStayIndicator();
    }

    public void setThumbAdjustAuto(boolean adjustAuto) {
        this.mAdjustAuto = adjustAuto;
    }
}
