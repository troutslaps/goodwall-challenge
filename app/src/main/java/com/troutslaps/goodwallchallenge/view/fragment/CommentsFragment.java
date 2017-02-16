package com.troutslaps.goodwallchallenge.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.troutslaps.goodwallchallenge.BR;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.databinding.FragmentCommentsBinding;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.view.adapter.CommentAdapter;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A placeholder fragment containing a simple view.
 */
public class CommentsFragment extends Fragment {

    private static final String TAG = CommentsFragment.class.getSimpleName();
    Realm realm;
    RealmChangeListener<Achievement> achievementChangeListener;
    RealmChangeListener<RealmResults<Comment>> commentsChangeListener;
    CommentsFragmentListener listener;
    FragmentCommentsBinding binding;
    RecyclerView commentsRv;
    CommentAdapter commentsAdapter;

    Achievement achievement;

    public CommentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false);
        setupRecyclerView(binding.getRoot());
        return binding.getRoot();
    }

    private void setupRecyclerView(View v) {
        commentsRv = (RecyclerView) v.findViewById(R.id.list);
        commentsRv.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof CommentsFragmentListener && context instanceof AchievementViewModel
                .Listener) {
            listener = (CommentsFragmentListener) context;
            realm = Realm.getDefaultInstance();
            achievement = Achievement.getAchievement(realm, listener.getAchievementId());


            achievementChangeListener = new RealmChangeListener<Achievement>() {
                @Override
                public void onChange(Achievement element) {
                    if (commentsAdapter == null) {
                        commentsAdapter = new CommentAdapter(getActivity(), achievement
                                .getComments());
                        commentsRv.setAdapter(commentsAdapter);
                    } else {
                        commentsAdapter.notifyDataSetChanged();
                    }
                    binding.setVariable(BR.viewModel, new AchievementViewModel
                            (getContext(),
                                    achievement, (AchievementViewModel.Listener) getActivity()));


                }
            };
            achievement.addChangeListener(achievementChangeListener);

        } else {
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
        int getAchievementId();
    }
}
