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
import com.troutslaps.goodwallchallenge.model.Comment;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class CommentsFragment extends Fragment {

    private static final String TAG = CommentsFragment.class.getSimpleName();
    Achievement achievement;
    Realm realm;
    RealmChangeListener<Achievement> achievementChangeListener;
    CommentsFragmentListener listener;

    public CommentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CommentsFragmentListener) {
            listener = (CommentsFragmentListener) context;
            realm = Realm.getDefaultInstance();
            achievement = Achievement.getAchievement(realm, listener.getAchievementId());

            achievementChangeListener = new RealmChangeListener<Achievement>() {
                @Override
                public void onChange(Achievement element) {
                    Log.d(TAG, achievement.getComments().toString());
                }
            };
            achievement.addChangeListener(achievementChangeListener);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        achievement.removeChangeListener(achievementChangeListener);
        realm.close();
    }

    public interface CommentsFragmentListener {
        public int getAchievementId();
    }
}
