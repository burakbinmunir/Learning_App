package com.android.learningapp;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }
}
