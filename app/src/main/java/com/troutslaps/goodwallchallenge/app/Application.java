package com.troutslaps.goodwallchallenge.app;

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
    }
}
