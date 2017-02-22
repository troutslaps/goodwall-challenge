package com.troutslaps.goodwallchallenge.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scopely.fontain.Fontain;
import com.scopely.fontain.interfaces.FontFamily;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.databinding.FragmentFeedBinding;
import com.troutslaps.goodwallchallenge.http.client.AchievementRestClient;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.view.adapter.AchievementAdapter;
import com.troutslaps.goodwallchallenge.viewmodel.FeedViewModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedFragment extends Fragment {

    private static final String TAG = FeedFragment.class.getSimpleName();
    RealmChangeListener changeListener;
    Realm realm;
    RealmResults<Achievement> achievements;
    FeedViewModel feedViewModel;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentFeedBinding binding = DataBindingUtil.inflate(inflater, R.layout
                .fragment_feed, container, false);
        FontFamily family = Fontain.getFontFamily(Constants
                .View.DefaultFont);
        Fontain.applyFontFamilyToViewHierarchy(binding.getRoot(), family);
        binding.setFeedViewModel(feedViewModel);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        realm = Realm.getDefaultInstance();
        // leave the change listener here, since it has to be removed at hte end of the lifecycle
        changeListener = new RealmChangeListener<RealmResults<Achievement>>() {
            @Override
            public void onChange(RealmResults<Achievement> element) {
                feedViewModel.notifyChange();
            }
        };
        achievements = Achievement.getAllAchievements(realm);
        achievements.addChangeListener(changeListener);
        feedViewModel = new FeedViewModel(achievements, getContext(), (FeedViewModel.Listener)
                getActivity(), realm);
        AchievementRestClient.getInstance(feedViewModel).getAchievements();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        achievements.removeChangeListener(changeListener);
        realm.close();
    }
}
