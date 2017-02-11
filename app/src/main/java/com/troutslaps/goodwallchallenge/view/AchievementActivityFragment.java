package com.troutslaps.goodwallchallenge.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troutslaps.goodwallchallenge.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AchievementActivityFragment extends Fragment {

    public AchievementActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

}
