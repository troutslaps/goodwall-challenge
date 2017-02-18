package com.troutslaps.goodwallchallenge.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.view.adapter.AchievementAdapter;

import java.util.List;

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
    RecyclerView achievementsRv;
    AchievementAdapter achievementsAdapter;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feed, container, false);
        setupRecyclerView(v);
        return v;
    }

    private void setupRecyclerView(View v) {
        achievementsRv = (RecyclerView) v.findViewById(R.id.list);
        achievementsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        achievementsAdapter = new AchievementAdapter(getActivity(), achievements);
        achievementsRv.setAdapter(achievementsAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        realm = Realm.getDefaultInstance();
        changeListener = new RealmChangeListener<RealmResults<Achievement>>() {
            @Override
            public void onChange(RealmResults<Achievement> element) {
                achievementsAdapter.notifyDataSetChanged();
            }
        };
        achievements = Achievement.getAllAchievements(realm);
        achievements.addChangeListener(changeListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        achievements.removeChangeListener(changeListener);
        realm.close();
    }
}
