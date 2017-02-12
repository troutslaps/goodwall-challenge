package com.troutslaps.goodwallchallenge.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.User;
import com.troutslaps.goodwallchallenge.view.fragment.CommentsFragment;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;

import java.util.Date;

import io.realm.Realm;

public class CommentsActivity extends AppCompatActivity implements CommentsFragment
        .CommentsFragmentListener, AchievementViewModel.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public int getAchievementId() {
        return getIntent().getIntExtra(Constants.Fields.Id, -1);
    }

    @Override
    public void onCongratsClicked(Achievement achievement) {
        // do nothing
    }

    @Override
    public void onCommentsClicked(Achievement achievement) {
        // do nothing
    }

    @Override
    public void onAddCommentButtonClicked(Achievement achievement, final String comment,
                                          final User user) {
        Realm realm = Realm.getDefaultInstance();

        final int achievementId = achievement.getId();
        final int userId = user.getId();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Achievement achievementLocal = realm.where(Achievement.class).equalTo(Constants
                                .Fields
                                .Id, achievementId).findFirst();
                User userLocal = realm.where(User.class).equalTo(Constants.Fields.Id, userId)
                        .findFirst();
                Number currentCommentId = realm.where(Comment.class).max(Constants.Fields.Id);
                int nextId;
                if(currentCommentId == null) {
                    nextId = 1;
                } else {
                    nextId = currentCommentId.intValue() + 1;
                }
                Comment commentNew = new Comment();
                commentNew.setBody(comment);
                commentNew.setAchievementId(achievementLocal.getId());
                commentNew.setCreated(new Date());
                commentNew.setAuthor(userLocal);
                commentNew.setModified(null);
                commentNew.setId(nextId);
                achievementLocal.addComment(commentNew);
            }
        });

        realm.close();
    }

    @Override
    public void onAuthorNameClicked(Comment comment) {
        // show bottom sheet
    }
}