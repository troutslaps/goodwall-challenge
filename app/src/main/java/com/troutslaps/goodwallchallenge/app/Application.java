package com.troutslaps.goodwallchallenge.app;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.scopely.fontain.Fontain;

import io.realm.Realm;

/**
 * Created by duchess on 12/02/2017.
 */

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        Fontain.init(this, "Karla");
        BigImageViewer.initialize(GlideImageLoader.with(this.getApplicationContext()));
    }
}
