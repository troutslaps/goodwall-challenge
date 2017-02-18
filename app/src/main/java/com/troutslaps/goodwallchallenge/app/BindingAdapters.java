package com.troutslaps.goodwallchallenge.app;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.troutslaps.goodwallchallenge.view.utils.CircleTransform;

/**
 * Created by duchess on 18/02/2017.
 */

public class BindingAdapters {
    private static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter({"bind:imageUrl", "bind:placeholder", "bind:error"})
    public static void loadImage(ImageView view, String url, Drawable placeholder, Drawable error) {
        Picasso.with(view.getContext()).load(url).placeholder(placeholder).error(error).into(view);
    }

    @BindingAdapter({"bind:profilePhotoUrl", "bind:placeholder", "bind:error"})
    public static void loadProfilePhoto(ImageView view, String url, Drawable placeholder,
                                        Drawable error) {
        Picasso.with(view.getContext()).load(url).placeholder(placeholder).error(error).transform
                (new CircleTransform()).into(view);
    }
}
