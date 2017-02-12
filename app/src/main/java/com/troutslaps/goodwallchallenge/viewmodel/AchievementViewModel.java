package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.Location;
import com.troutslaps.goodwallchallenge.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duchess on 12/02/2017.
 */

public class AchievementViewModel extends BaseObservable {
    private final static int NumberOfComments = 2;
    private Context context;
    private Achievement achievement;
    private List<CommentViewModel> commentViewModels;
    private Listener listener;

    @Bindable
    public String newComment;

    public AchievementViewModel(Context context, Achievement achievement, Listener listener) {
        this.context = context;
        this.achievement = achievement;
        this.listener = listener;
        List<Comment> lastComments = achievement.getLastComments(NumberOfComments);
        this.commentViewModels = new ArrayList<>();
        CommentViewModel cvm;
        for (Comment comment : lastComments) {
            cvm = new CommentViewModel(comment, context, null);
            commentViewModels.add(cvm);
        }
    }

    public int getCommentVisibility(int position) {
        return commentViewModels.size() > position ? View.VISIBLE : View.GONE;
    }

    public CommentViewModel getCommentViewModel(int position) {
        if (commentViewModels.size() > position) {
            return commentViewModels.get(position);
        } else {
            return null;
        }
    }

    public String getPostTimeAgo() {
        return Utils.DateTime.getTimeAgo(context, achievement.getTimelineDate());
    }

    public String getAchievementTitle() {
        return achievement.getTitle();
    }

    public String getAchievementDate() {
        String start = Utils.DateTime.getFormattedDate(achievement.getStartDate());
        String end = Utils.DateTime.getFormattedDate(achievement.getCreated());
        if (!start.isEmpty() && !end.isEmpty()) {
            return String.format(context.getString(R.string.achievement_date_range), start, end);
        }
        if (end.isEmpty()) {
            return String.format(context.getString(R.string.achievement_date_start), start);
        }
        return String.format(context.getString(R.string.achievement_date_start), end);
    }

    public String getAchievementBody() {
        Location location = achievement.getLocation();
        String locality = Utils.Locality
                .getLocalityDisplay(context, location).toUpperCase();
        if (locality.isEmpty()) {
            return String.format(context.getString(R.string.achievement_body), achievement
                    .getBody());
        } else {
            return String.format(context.getString(R.string.achievement_body_with_loc), locality,
                    achievement
                            .getBody());
        }
    }

    public String getAuthorName() {
        User user = achievement.getAuthor();
        return user.getDisplayName();
    }

    public String getLocation() {
        return Utils.Locality.getLocalityDisplay(context, achievement.getAuthor().getLocation());
    }

    public String getCommentsDisplay() {
        if (achievement.getCommentsCount() == 0) {
            return context.getString(R.string.add_a_comment);
        } else if (achievement.getCommentsCount() == 1) {
            return context.getString(R.string.view_one_comment);
        } else {
            return String.format(context.getString(R.string.view_all_comments), achievement
                    .getCommentsCount());
        }
    }

    public CommentViewModel getAchievementAsComment(){
        Comment comment = new Comment();
        comment.setAchievementId(achievement.getId());
        comment.setAuthor(achievement.getAuthor());
        comment.setCreated(achievement.getCreated());
        comment.setBody(achievement.getBody());
        return new CommentViewModel(comment, context, listener);
    }
    public View.OnClickListener onCongratsClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onCongratsClicked(achievement);
            }
        };
    }

    public View.OnClickListener onAddCommentClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onAddCommentButtonClicked(achievement, newComment, achievement.getAuthor());
            }
        };
    }
    public View.OnClickListener onCommentsClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onCommentsClicked(achievement);
            }
        };
    }

    public interface Listener extends CommentViewModel.Listener {
        void onCongratsClicked(Achievement achievement);
        void onCommentsClicked(Achievement achievement);
        void onAddCommentButtonClicked(Achievement achievement, String comment, User user);
    }

}
