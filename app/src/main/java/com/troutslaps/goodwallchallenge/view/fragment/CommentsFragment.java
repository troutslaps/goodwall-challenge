package com.troutslaps.goodwallchallenge.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scopely.fontain.Fontain;
import com.scopely.fontain.interfaces.FontFamily;
import com.troutslaps.goodwallchallenge.BR;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.databinding.FragmentCommentsBinding;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.view.adapter.CommentAdapter;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

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
    AchievementViewModel achievementViewModel;
    String newComment;

    public CommentsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false);
        setupRecyclerView(binding.getRoot());
        FontFamily family = Fontain.getFontFamily("Karla");
        Fontain.applyFontFamilyToViewHierarchy(binding.getRoot(), family);
        return binding.getRoot();
    }

    private void setupRecyclerView(View v) {
        commentsRv = (RecyclerView) v.findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setStackFromEnd(true);
        commentsRv.setLayoutManager(llm);

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
                                .getComments().sort(Constants.Fields.Created, Sort.ASCENDING));
                        commentsRv.setAdapter(commentsAdapter);
                    } else {
                        commentsAdapter.notifyDataSetChanged();
                    }

                    listener.onAchievementUpdated(achievement);
                    commentsRv.scrollToPosition(commentsAdapter.getItemCount() - 1);
                    if (achievementViewModel == null) {
                        achievementViewModel = new AchievementViewModel
                                (getContext(), achievement, (AchievementViewModel.Listener)
                                        getActivity());
                        achievementViewModel.setNewComment(newComment);
                        binding.setVariable(BR.viewModel, achievementViewModel);
                    } else {
                        achievementViewModel.setNewComment(newComment);
                        binding.notifyChange();
                    }
                    newComment = null;

                }
            };
            achievement.addChangeListener(achievementChangeListener);

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        newComment = achievementViewModel.getNewComment();
        outState.putString(Constants.Fields.PendingComment, newComment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            newComment = savedInstanceState.getString(Constants.Fields.PendingComment);
            if (achievementViewModel != null) {
                achievementViewModel.setNewComment(newComment);
                achievementViewModel.notifyChange();
            }
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

        void onAchievementUpdated(Achievement achievement);
    }
}
