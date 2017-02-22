package com.troutslaps.goodwallchallenge.app;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
        if(url != null) {
            Glide.with(view.getContext()).load(url).placeholder(placeholder).error(error).into(view);
        } else {
            Glide.with(view.getContext()).load(error).placeholder(placeholder).error(error).into
                    (view);
        }

    }

    @BindingAdapter({"bind:profilePhotoUrl", "bind:placeholder", "bind:error"})
    public static void loadProfilePhoto(ImageView view, String url, Drawable placeholder,
                                        Drawable error) {
        Glide.with(view.getContext()).load(url).placeholder(placeholder).error(error)
                .bitmapTransform(new CropCircleTransformation(view.getContext())).into
                (view);
    }

    @BindingAdapter({"bind:onRefreshListener"})
    public static void setRefreshListener(SwipeRefreshLayout swipeRefreshLayout,
                                          SwipeRefreshLayout.OnRefreshListener listener) {
        swipeRefreshLayout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"bind:isRefreshing"})
    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout,
                                          boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    @BindingAdapter({"bind:layoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView,
                                        RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"bind:adapter"})
    public static void setLayoutManager(RecyclerView recyclerView,
                                        RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }
}
