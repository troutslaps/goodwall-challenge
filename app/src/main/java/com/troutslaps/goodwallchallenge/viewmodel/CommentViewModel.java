package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.Photo;
import com.troutslaps.goodwallchallenge.model.User;

/**
 * Created by duchess on 12/02/2017.
 */

public class CommentViewModel extends BaseObservable implements PostViewModelInterface {
    private static final String TAG = CommentViewModel.class.getSimpleName();
    Comment comment;
    Context context;
    Listener listener;

    public CommentViewModel(Comment comment, Context context, Listener listener) {
        this.comment = comment;
        this.context = context;
        this.listener = listener;
    }

    @Bindable
    public int getVisibility() {
        return comment != null ? View.VISIBLE : View.GONE;
    }

    @Bindable
    @Override
    public String getAuthorProfilePhoto() {
        if (comment.getAuthor() != null && comment.getAuthor().getPicture() != null) {
            Photo profilePhoto = comment.getAuthor().getPicture();
            return Utils.getRandomProfilePhoto(profilePhoto.getName());
        }
        return null;
    }

    @Bindable
    @Override
    public String getAuthorName() {
        return comment != null && comment.getAuthor() != null ? comment.getAuthor().getDisplayName
                () : "";
    }

    @Bindable
    @Override
    public String getCommentBody() {
        return comment != null ? comment.getBody() : "";
    }

    @Bindable
    @Override
    public String getCommentTime() {
        return comment != null ? Utils.DateTime.getDisplayTime(comment
                .getCreated()) : "";
    }

    @Override
    public View.OnClickListener onAuthorNameClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment != null) {
                    listener.onAuthorNameClicked(comment.getAuthor());
                }
            }
        };
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public interface Listener {
        public void onAuthorNameClicked(User user);
    }
}
