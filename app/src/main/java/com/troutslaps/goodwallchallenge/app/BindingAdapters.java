package com.troutslaps.goodwallchallenge.app;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.troutslaps.goodwallchallenge.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by duchess on 18/02/2017.
 */

public class BindingAdapters {
    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter({"bind:imageUrl", "bind:placeholder", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable placeholder, Drawable error) {
        Glide.with(view.getContext()).load(url).placeholder(placeholder).error(error).into(view);
    }

    @BindingAdapter({"bind:profilePhotoUrl", "bind:placeholder", "bind:error"})
    public static void loadProfilePhoto(ImageView view, String url, Drawable placeholder,
                                        Drawable error) {
        Glide.with(view.getContext()).load(url).placeholder(placeholder).error(error)
                .bitmapTransform(new CropCircleTransformation(view.getContext())).into
                (view);
    }
}
