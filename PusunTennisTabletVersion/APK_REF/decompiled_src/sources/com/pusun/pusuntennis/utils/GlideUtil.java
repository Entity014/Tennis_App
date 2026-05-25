package com.pusun.pusuntennis.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.pusun.pusuntennis.R;

/* loaded from: classes3.dex */
public class GlideUtil {
    public static void loadImage(Context context, String str, ImageView imageView) {
        Glide.with(context).load(str).placeholder(R.drawable.blank_grey).error(R.drawable.blank_grey).into(imageView);
    }
}
