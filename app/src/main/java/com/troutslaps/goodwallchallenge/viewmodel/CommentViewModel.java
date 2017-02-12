package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Comment;

/**
 * Created by duchess on 12/02/2017.
 */

public class CommentViewModel extends BaseObservable {
    Comment comment;
    Context context;
    Listener listener;

    public CommentViewModel(Comment comment, Context context, Listener listener) {
        this.comment = comment;
        this.context = context;
        this.listener = listener;
    }

    public String getAuthorName() {
        return comment.getAuthor().getDisplayName();
    }

    public String getCommentBody() {
        return comment.getBody();
    }

    public String getCommentTime() {
        return Utils.DateTime.getDisplayTime(comment.getCreated());
    }

    public View.OnClickListener onAuthorNameClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAuthorNameClicked(comment);
            }
        };
    }

    public interface Listener {
        public void onAuthorNameClicked(Comment comment);
    }
}
