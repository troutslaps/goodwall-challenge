package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Photo;

/**
 * Created by duchess on 22/02/2017.
 */

public class PhotoViewModel extends BaseObservable {
    private Context context;
    private Photo photo;

    public PhotoViewModel(Context context, Photo photo) {
        this.context = context;
        this.photo = photo;
    }

    @Bindable
    public String getPhotoUrl() {
        return Utils.getRandomPhoto(photo.getName());
    }
}
