package com.yunusseker.heroin.util;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageDownloader {

    public static void loadCircularImage(int placeholder, String url, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .apply(new RequestOptions().placeholder(placeholder))
                .into(imageView);
    }

    public static void load(int placeholder, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(new RequestOptions().placeholder(placeholder))
                    .into(imageView);
        }

    }

    public static void loadDrawable(Drawable drawable, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load("")
                .apply(new RequestOptions().placeholder(drawable))
                .into(imageView);


    }

}