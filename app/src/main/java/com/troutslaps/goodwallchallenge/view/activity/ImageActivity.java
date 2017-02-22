package com.troutslaps.goodwallchallenge.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator;
import com.github.piasy.biv.view.BigImageView;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.databinding.ActivityBigImageBinding;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Photo;
import com.troutslaps.goodwallchallenge.model.User;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;
import com.troutslaps.goodwallchallenge.viewmodel.PhotoViewModel;

/**
 * Created by duchess on 21/02/2017.
 */import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class ImageActivity extends AppCompatActivity {

    View decorView;
    ActivityBigImageBinding binding;
    PhotoViewModel photoViewModel;
    RealmChangeListener<Photo> photoChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_big_image, null);
        setContentView(rootView);
        binding = DataBindingUtil.bind(rootView);
        setContentView(R.layout.activity_big_image);
        Intent i = getIntent();
        int photoId = i.getIntExtra(Constants.Fields.PhotoId, 0);
        Realm realm = Realm.getDefaultInstance();
        final Photo photo = realm.where(Photo.class).equalTo(Constants.Fields.Id, photoId)
                .findFirstAsync();
        photoChangeListener = new RealmChangeListener<Photo>() {
            @Override
            public void onChange(final Photo photo) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (photoViewModel != null) {
                            photoViewModel = new PhotoViewModel(ImageActivity.this, photo);
                            binding.setPhotoViewModel(photoViewModel);
                            binding.executePendingBindings();
                        } else {
                            binding.notifyChange();
                        }
                    }
                });
            }
        };
        photo.addChangeListener(photoChangeListener);
        realm.close();
        decorView = getWindow().getDecorView();
        hideSystemUI();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}