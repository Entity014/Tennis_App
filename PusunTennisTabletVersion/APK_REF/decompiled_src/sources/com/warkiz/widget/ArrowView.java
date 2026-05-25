package com.warkiz.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes3.dex */
public class ArrowView extends View {
    private final int mHeight;
    private final Paint mPaint;
    private final Path mPath;
    private final int mWidth;

    public ArrowView(Context context) {
        this(context, null);
    }

    public ArrowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArrowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int dp2px = SizeUtils.dp2px(context, 12.0f);
        this.mWidth = dp2px;
        int dp2px2 = SizeUtils.dp2px(context, 7.0f);
        this.mHeight = dp2px2;
        Path path = new Path();
        this.mPath = path;
        path.moveTo(0.0f, 0.0f);
        path.lineTo(dp2px, 0.0f);
        path.lineTo(dp2px / 2.0f, dp2px2);
        path.close();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1.0f);
    }

    void setColor(int color) {
        this.mPaint.setColor(color);
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(this.mWidth, this.mHeight);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
