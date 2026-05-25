package com.warkiz.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

/* loaded from: classes3.dex */
public class Builder {
    final Context context;
    int indicatorTextSize;
    boolean showTickText;
    int thumbSize;
    int tickMarksSize;
    int tickTextsSize;
    int trackBackgroundSize;
    int trackProgressSize;
    float max = 100.0f;
    float min = 0.0f;
    float progress = 0.0f;
    boolean progressValueFloat = false;
    boolean seekSmoothly = false;
    boolean r2l = false;
    boolean userSeekable = true;
    boolean onlyThumbDraggable = false;
    boolean clearPadding = false;
    int showIndicatorType = 2;
    int indicatorColor = Color.parseColor("#FF4081");
    int indicatorTextColor = Color.parseColor("#FFFFFF");
    View indicatorContentView = null;
    View indicatorTopContentView = null;
    int trackBackgroundColor = Color.parseColor("#D7D7D7");
    int trackProgressColor = Color.parseColor("#FF4081");
    boolean trackRoundedCorners = false;
    int thumbTextColor = Color.parseColor("#FF4081");
    boolean showThumbText = false;
    int thumbColor = Color.parseColor("#FF4081");
    ColorStateList thumbColorStateList = null;
    Drawable thumbDrawable = null;
    int tickTextsColor = Color.parseColor("#FF4081");
    String[] tickTextsCustomArray = null;
    Typeface tickTextsTypeFace = Typeface.DEFAULT;
    ColorStateList tickTextsColorStateList = null;
    int tickCount = 0;
    int showTickMarksType = 0;
    int tickMarksColor = Color.parseColor("#FF4081");
    Drawable tickMarksDrawable = null;
    boolean tickMarksEndsHide = false;
    boolean tickMarksSweptHide = false;
    ColorStateList tickMarksColorStateList = null;

    Builder(Context context) {
        this.indicatorTextSize = 0;
        this.trackBackgroundSize = 0;
        this.trackProgressSize = 0;
        this.thumbSize = 0;
        this.tickTextsSize = 0;
        this.tickMarksSize = 0;
        this.context = context;
        this.indicatorTextSize = SizeUtils.sp2px(context, 14.0f);
        this.trackBackgroundSize = SizeUtils.dp2px(context, 2.0f);
        this.trackProgressSize = SizeUtils.dp2px(context, 2.0f);
        this.tickMarksSize = SizeUtils.dp2px(context, 10.0f);
        this.tickTextsSize = SizeUtils.sp2px(context, 13.0f);
        this.thumbSize = SizeUtils.dp2px(context, 14.0f);
    }

    public IndicatorSeekBar build() {
        return new IndicatorSeekBar(this);
    }

    public Builder max(float max) {
        this.max = max;
        return this;
    }

    public Builder min(float min) {
        this.min = min;
        return this;
    }

    public Builder progress(float progress) {
        this.progress = progress;
        return this;
    }

    public Builder progressValueFloat(boolean isFloatProgress) {
        this.progressValueFloat = isFloatProgress;
        return this;
    }

    public Builder seekSmoothly(boolean seekSmoothly) {
        this.seekSmoothly = seekSmoothly;
        return this;
    }

    public Builder r2l(boolean r2l) {
        this.r2l = r2l;
        return this;
    }

    public Builder clearPadding(boolean clearPadding) {
        this.clearPadding = clearPadding;
        return this;
    }

    public Builder userSeekable(boolean userSeekable) {
        this.userSeekable = userSeekable;
        return this;
    }

    public Builder onlyThumbDraggable(boolean onlyThumbDraggable) {
        this.onlyThumbDraggable = onlyThumbDraggable;
        return this;
    }

    public Builder showIndicatorType(int showIndicatorType) {
        this.showIndicatorType = showIndicatorType;
        return this;
    }

    public Builder indicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        return this;
    }

    public Builder indicatorTextColor(int indicatorTextColor) {
        this.indicatorTextColor = indicatorTextColor;
        return this;
    }

    public Builder indicatorTextSize(int indicatorTextSize) {
        this.indicatorTextSize = SizeUtils.sp2px(this.context, indicatorTextSize);
        return this;
    }

    public Builder indicatorContentView(View indicatorContentView) {
        this.indicatorContentView = indicatorContentView;
        return this;
    }

    public Builder indicatorContentViewLayoutId(int layoutId) {
        this.indicatorContentView = View.inflate(this.context, layoutId, null);
        return this;
    }

    public Builder indicatorTopContentView(View topContentView) {
        this.indicatorTopContentView = topContentView;
        return this;
    }

    public Builder indicatorTopContentViewLayoutId(int layoutId) {
        this.indicatorTopContentView = View.inflate(this.context, layoutId, null);
        return this;
    }

    public Builder trackBackgroundSize(int trackBackgroundSize) {
        this.trackBackgroundSize = SizeUtils.dp2px(this.context, trackBackgroundSize);
        return this;
    }

    public Builder trackBackgroundColor(int trackBackgroundColor) {
        this.trackBackgroundColor = trackBackgroundColor;
        return this;
    }

    public Builder trackProgressSize(int trackProgressSize) {
        this.trackProgressSize = SizeUtils.dp2px(this.context, trackProgressSize);
        return this;
    }

    public Builder trackProgressColor(int trackProgressColor) {
        this.trackProgressColor = trackProgressColor;
        return this;
    }

    public Builder trackRoundedCorners(boolean trackRoundedCorners) {
        this.trackRoundedCorners = trackRoundedCorners;
        return this;
    }

    public Builder thumbTextColor(int thumbTextColor) {
        this.thumbTextColor = thumbTextColor;
        return this;
    }

    public Builder showThumbText(boolean showThumbText) {
        this.showThumbText = showThumbText;
        return this;
    }

    public Builder thumbColor(int thumbColor) {
        this.thumbColor = thumbColor;
        return this;
    }

    public Builder thumbColorStateList(ColorStateList thumbColorStateList) {
        this.thumbColorStateList = thumbColorStateList;
        return this;
    }

    public Builder thumbSize(int thumbSize) {
        this.thumbSize = SizeUtils.dp2px(this.context, thumbSize);
        return this;
    }

    public Builder thumbDrawable(Drawable thumbDrawable) {
        this.thumbDrawable = thumbDrawable;
        return this;
    }

    public Builder thumbDrawable(StateListDrawable thumbStateListDrawable) {
        this.thumbDrawable = thumbStateListDrawable;
        return this;
    }

    public Builder showTickTexts(boolean showTickText) {
        this.showTickText = showTickText;
        return this;
    }

    public Builder tickTextsColor(int tickTextsColor) {
        this.tickTextsColor = tickTextsColor;
        return this;
    }

    public Builder tickTextsColorStateList(ColorStateList tickTextsColorStateList) {
        this.tickTextsColorStateList = tickTextsColorStateList;
        return this;
    }

    public Builder tickTextsSize(int tickTextsSize) {
        this.tickTextsSize = SizeUtils.sp2px(this.context, tickTextsSize);
        return this;
    }

    public Builder tickTextsArray(String[] tickTextsArray) {
        this.tickTextsCustomArray = tickTextsArray;
        return this;
    }

    public Builder tickTextsArray(int tickTextsArray) {
        this.tickTextsCustomArray = this.context.getResources().getStringArray(tickTextsArray);
        return this;
    }

    public Builder tickTextsTypeFace(Typeface tickTextsTypeFace) {
        this.tickTextsTypeFace = tickTextsTypeFace;
        return this;
    }

    public Builder tickCount(int tickCount) {
        this.tickCount = tickCount;
        return this;
    }

    public Builder showTickMarksType(int tickMarksType) {
        this.showTickMarksType = tickMarksType;
        return this;
    }

    public Builder tickMarksColor(int tickMarksColor) {
        this.tickMarksColor = tickMarksColor;
        return this;
    }

    public Builder tickMarksColor(ColorStateList tickMarksColorStateList) {
        this.tickMarksColorStateList = tickMarksColorStateList;
        return this;
    }

    public Builder tickMarksSize(int tickMarksSize) {
        this.tickMarksSize = SizeUtils.dp2px(this.context, tickMarksSize);
        return this;
    }

    public Builder tickMarksDrawable(Drawable tickMarksDrawable) {
        this.tickMarksDrawable = tickMarksDrawable;
        return this;
    }

    public Builder tickMarksDrawable(StateListDrawable tickMarksStateListDrawable) {
        this.tickMarksDrawable = tickMarksStateListDrawable;
        return this;
    }

    public Builder tickMarksEndsHide(boolean tickMarksEndsHide) {
        this.tickMarksEndsHide = tickMarksEndsHide;
        return this;
    }

    public Builder tickMarksSweptHide(boolean tickMarksSweptHide) {
        this.tickMarksSweptHide = tickMarksSweptHide;
        return this;
    }
}
