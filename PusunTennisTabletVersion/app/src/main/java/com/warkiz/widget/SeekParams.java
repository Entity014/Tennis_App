package com.warkiz.widget;

/* loaded from: classes3.dex */
public class SeekParams {
    public boolean fromUser;
    public int progress;
    public float progressFloat;
    public IndicatorSeekBar seekBar;
    public int thumbPosition;
    public String tickText;

    SeekParams(IndicatorSeekBar seekBar) {
        this.seekBar = seekBar;
    }
}
