package com.yunusseker.heroin.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.yunusseker.heroin.R;

public class ImageBindingAdapter {


    @BindingAdapter("imageUrl")
    public static void setMessageImageUrl(ImageView imageView, String url) {
        setImage(imageView, url, R.drawable.rectangle_rounded_gray);
    }


    private static void setCircularImage(ImageView imageView, String url, int placeholderId) {
        try {
            ImageDownloader.loadCircularImage(placeholderId, url, imageView);
        } catch (Exception e) {
            imageView.setImageDrawable(null);
        }
    }


    @BindingAdapter("loadDrawable")
    public static void loadDrawable(ImageView imageView, Drawable drawableResource) {
        ImageDownloader.loadDrawable(drawableResource, imageView);
    }

    private static void setImage(ImageView imageView, String url, int placeholderId) {
        try {
            ImageDownloader.load(placeholderId, url, imageView);
        } catch (Exception e) {
            imageView.setImageDrawable(null);
        }
    }

}
