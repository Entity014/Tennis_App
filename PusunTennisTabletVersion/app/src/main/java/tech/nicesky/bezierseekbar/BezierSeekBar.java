package tech.nicesky.bezierseekbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.core.view.ViewCompat;
import java.text.DecimalFormat;

/* loaded from: classes3.dex */
public class BezierSeekBar extends View {
    private boolean animInFinshed;
    private ValueAnimator animatorFingerIn;
    private ValueAnimator animatorFingerOut;
    private Paint ballPaint;
    private float bezierHeight;
    private Paint bezierPaint;
    private Path bezierPath;
    private RectF bgRect;
    private float circleRadius;
    private float circleRadiusMax;
    private float circleRadiusMin;
    private int colorBall;
    private int colorBgSelected;
    private int colorLine;
    private int colorValue;
    private int colorValueSelected;
    private Context context;
    private DecimalFormat decimalFormat;
    private int diameterDefault;
    private Point fingerPoint;
    private float fingerX;
    private float fingerXDefault;
    private float fingerXMax;
    private float fingerXmin;
    private float fingerYDefault;
    private int height;
    private int mPaddingBottom;
    private int mPaddingEnd;
    private int mPaddingStart;
    private int mPaddingTop;
    private boolean robTouchEvent;
    private OnSelectedListener selectedListener;
    private float spaceToLine;
    private Paint textDownPaint;
    private Paint textPaint;
    private float textSelectedSize;
    private float textSize;
    private Paint txtSelectedBgPaint;
    private String unit;
    private int valueMax;
    private int valueMin;
    private int valueSelected;
    private int width;

    public BezierSeekBar(Context context) {
        super(context);
        this.diameterDefault = 300;
        this.bezierHeight = 50.0f;
        this.circleRadiusMin = 15.0f;
        this.circleRadiusMax = 1.5f * 15.0f;
        this.circleRadius = 15.0f;
        this.spaceToLine = 2.0f * 15.0f;
        this.fingerXmin = 15.0f;
        this.textSelectedSize = 20.0f;
        this.textSize = 12.0f;
        this.unit = "kg";
        this.animInFinshed = false;
        this.robTouchEvent = false;
        init(context, null);
    }

    public BezierSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.diameterDefault = 300;
        this.bezierHeight = 50.0f;
        this.circleRadiusMin = 15.0f;
        this.circleRadiusMax = 1.5f * 15.0f;
        this.circleRadius = 15.0f;
        this.spaceToLine = 2.0f * 15.0f;
        this.fingerXmin = 15.0f;
        this.textSelectedSize = 20.0f;
        this.textSize = 12.0f;
        this.unit = "kg";
        this.animInFinshed = false;
        this.robTouchEvent = false;
        init(context, attributeSet);
    }

    public BezierSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.diameterDefault = 300;
        this.bezierHeight = 50.0f;
        this.circleRadiusMin = 15.0f;
        this.circleRadiusMax = 1.5f * 15.0f;
        this.circleRadius = 15.0f;
        this.spaceToLine = 2.0f * 15.0f;
        this.fingerXmin = 15.0f;
        this.textSelectedSize = 20.0f;
        this.textSize = 12.0f;
        this.unit = "kg";
        this.animInFinshed = false;
        this.robTouchEvent = false;
        init(context, attributeSet);
    }

    public BezierSeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.diameterDefault = 300;
        this.bezierHeight = 50.0f;
        this.circleRadiusMin = 15.0f;
        this.circleRadiusMax = 1.5f * 15.0f;
        this.circleRadius = 15.0f;
        this.spaceToLine = 2.0f * 15.0f;
        this.fingerXmin = 15.0f;
        this.textSelectedSize = 20.0f;
        this.textSize = 12.0f;
        this.unit = "kg";
        this.animInFinshed = false;
        this.robTouchEvent = false;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.context = context;
        this.decimalFormat = new DecimalFormat("#");
        this.textSelectedSize = dp2px(context, 20.0f);
        this.textSize = dp2px(context, 12.0f);
        this.valueMax = 200;
        this.valueMin = 30;
        this.colorValue = ViewCompat.MEASURED_STATE_MASK;
        this.colorLine = ViewCompat.MEASURED_STATE_MASK;
        this.colorBall = ViewCompat.MEASURED_STATE_MASK;
        this.colorValueSelected = -1;
        this.fingerX = 100.0f;
        initAttr(context, attributeSet);
        this.bgRect = new RectF();
        Paint paint = new Paint();
        this.bezierPaint = paint;
        paint.setAntiAlias(true);
        this.bezierPaint.setStyle(Paint.Style.STROKE);
        this.bezierPaint.setColor(this.colorLine);
        this.bezierPaint.setStrokeWidth(2.0f);
        Paint paint2 = new Paint();
        this.textPaint = paint2;
        paint2.setAntiAlias(true);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setColor(this.colorValue);
        this.textPaint.setStrokeWidth(2.0f);
        this.textPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        this.textPaint.setTextSize(this.textSelectedSize);
        Paint paint3 = new Paint();
        this.txtSelectedBgPaint = paint3;
        paint3.setAntiAlias(true);
        this.txtSelectedBgPaint.setColor(this.colorBgSelected);
        this.txtSelectedBgPaint.setStyle(Paint.Style.FILL);
        Paint paint4 = new Paint();
        this.textDownPaint = paint4;
        paint4.setAntiAlias(true);
        this.textDownPaint.setStyle(Paint.Style.FILL);
        this.textDownPaint.setColor(this.colorValue);
        this.textDownPaint.setStrokeWidth(2.0f);
        this.textDownPaint.setTextSize(this.textSize);
        Paint paint5 = new Paint();
        this.ballPaint = paint5;
        paint5.setAntiAlias(true);
        this.ballPaint.setStyle(Paint.Style.FILL);
        this.ballPaint.setColor(this.colorBall);
        Path path = new Path();
        this.bezierPath = path;
        this.fingerXDefault = 200.0f;
        this.fingerYDefault = 200.0f;
        path.moveTo(this.fingerX, 100.0f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.animatorFingerIn = ofFloat;
        ofFloat.setDuration(200L);
        this.animatorFingerIn.setInterpolator(new LinearInterpolator());
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.animatorFingerOut = ofFloat2;
        ofFloat2.setDuration(200L);
        this.animatorFingerOut.setInterpolator(new LinearInterpolator());
        this.animatorFingerIn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: tech.nicesky.bezierseekbar.BezierSeekBar.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BezierSeekBar.this.animInFinshed = floatValue >= 0.15f;
                BezierSeekBar.this.txtSelectedBgPaint.setAlpha((int) ((floatValue - 0.15f) * 255.0f));
                if (floatValue >= 0.95f) {
                    BezierSeekBar.this.textPaint.setColor(BezierSeekBar.this.colorValueSelected);
                } else {
                    BezierSeekBar.this.textPaint.setColor(BezierSeekBar.this.colorValue);
                }
                BezierSeekBar bezierSeekBar = BezierSeekBar.this;
                bezierSeekBar.bezierHeight = bezierSeekBar.circleRadiusMax * 1.5f * floatValue;
                BezierSeekBar bezierSeekBar2 = BezierSeekBar.this;
                bezierSeekBar2.circleRadius = bezierSeekBar2.circleRadiusMin + ((BezierSeekBar.this.circleRadiusMax - BezierSeekBar.this.circleRadiusMin) * floatValue);
                BezierSeekBar bezierSeekBar3 = BezierSeekBar.this;
                bezierSeekBar3.spaceToLine = bezierSeekBar3.circleRadiusMin * 2.0f * (1.0f - floatValue);
                BezierSeekBar.this.postInvalidate();
            }
        });
        this.animatorFingerOut.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: tech.nicesky.bezierseekbar.BezierSeekBar.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BezierSeekBar.this.animInFinshed = floatValue >= 0.15f;
                BezierSeekBar.this.txtSelectedBgPaint.setAlpha((int) ((floatValue - 0.15f) * 255.0f));
                if (floatValue >= 0.95f) {
                    BezierSeekBar.this.textPaint.setColor(BezierSeekBar.this.colorValueSelected);
                } else {
                    BezierSeekBar.this.textPaint.setColor(BezierSeekBar.this.colorValue);
                }
                BezierSeekBar bezierSeekBar = BezierSeekBar.this;
                bezierSeekBar.bezierHeight = bezierSeekBar.circleRadiusMax * 1.5f * floatValue;
                BezierSeekBar bezierSeekBar2 = BezierSeekBar.this;
                bezierSeekBar2.circleRadius = bezierSeekBar2.circleRadiusMin + ((BezierSeekBar.this.circleRadiusMax - BezierSeekBar.this.circleRadiusMin) * floatValue);
                BezierSeekBar bezierSeekBar3 = BezierSeekBar.this;
                bezierSeekBar3.spaceToLine = bezierSeekBar3.circleRadiusMin * 2.0f * (1.0f - floatValue);
                BezierSeekBar.this.postInvalidate();
            }
        });
    }

    private void initAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BezierSeekBar);
            this.colorBall = obtainStyledAttributes.getColor(R.styleable.BezierSeekBar_bsBar_color_ball, ViewCompat.MEASURED_STATE_MASK);
            this.colorLine = obtainStyledAttributes.getColor(R.styleable.BezierSeekBar_bsBar_color_line, ViewCompat.MEASURED_STATE_MASK);
            this.colorValue = obtainStyledAttributes.getColor(R.styleable.BezierSeekBar_bsBar_color_value, ViewCompat.MEASURED_STATE_MASK);
            this.colorValueSelected = obtainStyledAttributes.getColor(R.styleable.BezierSeekBar_bsBar_color_value_selected, -1);
            this.colorBgSelected = obtainStyledAttributes.getColor(R.styleable.BezierSeekBar_bsBar_color_bg_selected, ViewCompat.MEASURED_STATE_MASK);
            this.valueMin = obtainStyledAttributes.getInteger(R.styleable.BezierSeekBar_bsBar_value_min, 30);
            this.valueMax = obtainStyledAttributes.getInteger(R.styleable.BezierSeekBar_bsBar_value_max, 150);
            this.valueSelected = obtainStyledAttributes.getInteger(R.styleable.BezierSeekBar_bsBar_value_selected, 65);
            this.unit = obtainStyledAttributes.getString(R.styleable.BezierSeekBar_bsBar_unit) + "";
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mySize = getMySize(this.diameterDefault, i);
        this.width = mySize;
        this.fingerXMax = mySize - this.circleRadiusMin;
        if (getLayoutParams().height == -2) {
            this.height = this.diameterDefault;
        } else {
            this.height = getMySize(this.diameterDefault, i2);
        }
        setMeasuredDimension(this.width, this.height);
        float f = this.width;
        int i3 = this.valueSelected;
        int i4 = this.valueMin;
        float f2 = (f * (i3 - i4)) / (this.valueMax - i4);
        this.fingerXDefault = f2;
        this.fingerX = f2;
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mPaddingStart = getPaddingStart();
        this.mPaddingEnd = getPaddingEnd();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        this.bezierHeight = 0.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.bezierPath.reset();
        float f = 0.0f;
        this.bezierPath.moveTo(0.0f, (this.height * 2.0f) / 3.0f);
        this.bezierPath.lineTo(this.fingerX - ((this.circleRadiusMax * 2.0f) * 3.0f), (this.height * 2.0f) / 3.0f);
        this.bezierPath.moveTo(this.fingerX - ((this.circleRadiusMax * 2.0f) * 3.0f), (this.height * 2.0f) / 3.0f);
        Path path = this.bezierPath;
        float f2 = this.fingerX;
        float f3 = this.circleRadiusMax;
        int i = this.height;
        float f4 = this.bezierHeight;
        path.cubicTo(f2 - ((f3 * 2.0f) * 2.0f), (i * 2.0f) / 3.0f, f2 - ((f3 * 2.0f) * 1.0f), ((i * 2.0f) / 3.0f) - f4, f2, ((i * 2.0f) / 3.0f) - f4);
        this.bezierPath.moveTo(this.fingerX, ((this.height * 2.0f) / 3.0f) - this.bezierHeight);
        Path path2 = this.bezierPath;
        float f5 = this.fingerX;
        float f6 = this.circleRadiusMax;
        int i2 = this.height;
        path2.cubicTo(f5 + (f6 * 2.0f), ((i2 * 2.0f) / 3.0f) - this.bezierHeight, (f6 * 2.0f * 2.0f) + f5, (i2 * 2.0f) / 3.0f, f5 + (f6 * 2.0f * 3.0f), (i2 * 2.0f) / 3.0f);
        this.bezierPath.lineTo(this.width, (this.height * 2.0f) / 3.0f);
        canvas.drawPath(this.bezierPath, this.bezierPaint);
        float f7 = this.fingerX;
        float f8 = ((this.height * 2.0f) / 3.0f) + this.spaceToLine;
        float f9 = this.circleRadius;
        canvas.drawCircle(f7, f8 + f9, f9, this.ballPaint);
        canvas.drawText("" + this.valueMin, 1.0f, ((this.height * 2.0f) / 3.0f) + this.textSize, this.textDownPaint);
        canvas.drawText("" + this.valueMax, (this.width - getTextWidth(this.textDownPaint, "200")) - 1.0f, ((this.height * 2.0f) / 3.0f) + dp2px(getContext(), 12.0f), this.textDownPaint);
        String str = this.valueSelected + this.unit;
        float textWidth = (this.fingerX - (getTextWidth(this.textPaint, str) / 2.0f)) - 20.0f;
        float textWidth2 = this.fingerX + (getTextWidth(this.textPaint, str) / 2.0f) + 20.0f;
        if (textWidth <= 0.0f) {
            textWidth2 = getTextWidth(this.textPaint, str) + 40.0f;
        } else {
            f = textWidth;
        }
        int i3 = this.width;
        if (textWidth2 >= i3) {
            textWidth2 = i3;
            f = (i3 - getTextWidth(this.textPaint, str)) - 40.0f;
        }
        if (this.animInFinshed) {
            this.bgRect.set(f, ((((this.height * 2.0f) / 3.0f) - (this.bezierHeight * 2.0f)) - 30.0f) - getTextHeight(this.textPaint, str), textWidth2, (((this.height * 2.0f) / 3.0f) - (this.bezierHeight * 2.0f)) + 10.0f);
            canvas.drawRoundRect(this.bgRect, 20.0f, 20.0f, this.txtSelectedBgPaint);
        }
        canvas.drawText(str, f + 20.0f, (((this.height * 2.0f) / 3.0f) - (this.bezierHeight * 2.0f)) - 15.0f, this.textPaint);
    }

    public boolean isRobTouchEvent() {
        return this.robTouchEvent;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            float x = motionEvent.getX();
            this.fingerX = x;
            float f = this.fingerXmin;
            if (x < f) {
                this.fingerX = f;
            }
            float f2 = this.fingerX;
            float f3 = this.fingerXMax;
            if (f2 > f3) {
                this.fingerX = f3;
            }
            this.animatorFingerIn.start();
        } else if (action == 1) {
            this.robTouchEvent = false;
            this.animatorFingerOut.start();
        } else if (action == 2) {
            this.robTouchEvent = true;
            float x2 = motionEvent.getX();
            this.fingerX = x2;
            float f4 = this.fingerXmin;
            if (x2 < f4) {
                this.fingerX = f4;
            }
            float f5 = this.fingerX;
            float f6 = this.fingerXMax;
            if (f5 > f6) {
                this.fingerX = f6;
            }
            postInvalidate();
        }
        DecimalFormat decimalFormat = this.decimalFormat;
        int i = this.valueMin;
        float f7 = i;
        float f8 = this.valueMax - i;
        float f9 = this.fingerX;
        float f10 = this.fingerXmin;
        int intValue = Integer.valueOf(decimalFormat.format(f7 + ((f8 * (f9 - f10)) / (this.fingerXMax - f10)))).intValue();
        this.valueSelected = intValue;
        OnSelectedListener onSelectedListener = this.selectedListener;
        if (onSelectedListener != null) {
            onSelectedListener.onSelected(intValue);
        }
        return true;
    }

    private int getMySize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.getSize(i2) : i;
    }

    private float px2dp(Context context, float f) {
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    private float dp2px(Context context, float f) {
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    private float getTextWidth(Paint paint, String str) {
        float f = 0.0f;
        if (str != null && str.length() > 0) {
            int length = str.length();
            float[] widths = new float[length];
            paint.getTextWidths(str, widths);
            for (int i = 0; i < length; i++) {
                f += (float) Math.ceil(widths[i]);
            }
        }
        return f;
    }

    private float getTextHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public void setValueMax(int i) {
        this.valueMax = i;
    }

    public void setValueMin(int i) {
        this.valueMin = i;
    }

    public int getValueMax() {
        return this.valueMax;
    }

    public int getValueMin() {
        return this.valueMin;
    }

    public int getColorValue() {
        return this.colorValue;
    }

    public void setColorValue(int i) {
        this.colorValue = i;
        this.textDownPaint.setColor(i);
    }

    public int getColorValueSelected() {
        return this.colorValueSelected;
    }

    public void setColorValueSelected(int i) {
        this.colorValueSelected = i;
        this.textPaint.setColor(this.colorValue);
    }

    public int getColorLine() {
        return this.colorLine;
    }

    public void setColorLine(int i) {
        this.colorLine = i;
        this.bezierPaint.setColor(i);
    }

    public int getColorBall() {
        return this.colorBall;
    }

    public void setColorBgSelected(int i) {
        this.colorBgSelected = i;
        this.txtSelectedBgPaint.setColor(i);
    }

    public void setValueSelected(int i) {
        this.valueSelected = i;
        float f = this.fingerXMax;
        int i2 = this.valueMin;
        float f2 = (f * (i - i2)) / (this.valueMax - i2);
        this.fingerXDefault = f2;
        this.fingerX = f2;
        postInvalidate();
    }

    public void setColorBall(int i) {
        this.colorBall = i;
        this.ballPaint.setColor(i);
    }

    public void setSelectedListener(OnSelectedListener onSelectedListener) {
        this.selectedListener = onSelectedListener;
    }

    public int getValueSelected() {
        return this.valueSelected;
    }

    public void setUnit(String str) {
        this.unit = str;
    }
}
