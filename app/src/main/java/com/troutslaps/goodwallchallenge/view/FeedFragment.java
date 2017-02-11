package com.troutslaps.goodwallchallenge.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.model.Achievement;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedFragment extends Fragment {

    private static final String TAG = FeedFragment.class.getSimpleName();
    RealmResults<Achievement> achievements;
    RealmChangeListener changeListener;
    Realm realm;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Realm realm = Realm.getDefaultInstance();
        changeListener = new RealmChangeListener<RealmResults<Achievement>>() {
            @Override
            public void onChange(RealmResults<Achievement> element) {
                Log.d(TAG, "achievements = " + achievements);
            }
        };
        achievements = realm.where(Achievement.class).findAllSortedAsync(Constants.Fields.Created);
        achievements.addChangeListener(changeListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        achievements.removeChangeListener(changeListener);
        realm.close();
    }
}
