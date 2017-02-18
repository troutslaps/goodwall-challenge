package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.Location;
import com.troutslaps.goodwallchallenge.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Sort;

/**
 * Created by duchess on 12/02/2017.
 */

public class AchievementViewModel extends BaseObservable implements PostViewModelInterface {
    private final static int NumberOfComments = 2;
    private Context context;
    private Achievement achievement;
    private CommentViewModel firstComment;
    private CommentViewModel secondComment;
    private Listener listener;

    @Bindable
    public String newComment;

    public AchievementViewModel(Context context, Achievement achievement, Listener listener) {
        this.context = context;
        this.achievement = achievement;
        this.listener = listener;
    }

    @Bindable
    public CommentViewModel getSecondComment() {
        return secondComment;
    }

    public void setSecondComment(CommentViewModel secondComment) {
        this.secondComment = secondComment;
    }

    @Bindable
    public CommentViewModel getFirstComment() {
        return firstComment;
    }

    public void setFirstComment(CommentViewModel firstComment) {
        this.firstComment = firstComment;
    }

    @Bindable
    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
        List<Comment> lastComments = achievement.getComments().sort(Constants.Fields.Created,
                Sort.DESCENDING);

        if(getSecondComment() == null) {
            setSecondComment(new CommentViewModel(null, context, listener));
        }
        if(lastComments.size() > 0 && lastComments.get(0) != null) {
            getSecondComment().setComment(lastComments.get(0));
        } else {
            getSecondComment().setComment(null);
        }

        if(getFirstComment() == null) {
            setFirstComment(new CommentViewModel(null, context, listener));
        }
        if(lastComments.size() > 1 && lastComments.get(1) != null) {
            getFirstComment().setComment(lastComments.get(1));
        } else {
            getFirstComment().setComment(null);
        }
        notifyChange();
    }





    @Bindable
    public String getPostTimeAgo() {
        return Utils.DateTime.getTimeAgo(context, achievement.getTimelineDate());
    }

    @Bindable
    public String getAchievementTitle() {
        return achievement.getTitle();
    }

    @Bindable
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

    @Bindable
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

    @Bindable
    public String getAuthorName() {
        User user = achievement.getAuthor();
        return user.getDisplayName();
    }

    @Bindable
    @Override
    public String getCommentBody() {
        return achievement.getBody();
    }

    @Bindable
    @Override
    public String getCommentTime() {
        return Utils.DateTime.getDisplayTime(achievement.getCreated());
    }


    @Override
    public View.OnClickListener onAuthorNameClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onAuthorNameClicked(achievement.getAuthor());
            }
        };
    }

    public String getLocation() {
        return Utils.Locality.getLocalityDisplay(context, achievement.getAuthor().getLocation());
    }

    @Bindable
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
                listener.onAddCommentButtonClicked(achievement, newComment, achievement.getAuthor
                        ());
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
