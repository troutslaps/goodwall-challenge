package com.troutslaps.goodwallchallenge.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.User;
import com.troutslaps.goodwallchallenge.view.fragment.CommentsFragment;
import com.troutslaps.goodwallchallenge.view.fragment.UserBottomSheetDialogFragment;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;
import com.troutslaps.goodwallchallenge.viewmodel.UserViewModel;

import java.util.Date;

import io.realm.Realm;

public class CommentsActivity extends AppCompatActivity implements CommentsFragment
        .CommentsFragmentListener, AchievementViewModel.Listener, UserViewModel.Listener {

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
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
    public void onAddCommentButtonClicked(final Achievement achievement, final String comment,
                                          final User user) {
        Realm realm = Realm.getDefaultInstance();

        final int achievementId = achievement.getId();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Achievement achievementLocal = realm.where(Achievement.class).equalTo(Constants
                        .Fields
                        .Id, achievementId).findFirst();
                User randomUser = User.getRandomUser(realm);
                Number currentCommentId = realm.where(Comment.class).max(Constants.Fields.Id);
                int nextId;
                if (currentCommentId == null) {
                    nextId = 1;
                } else {
                    nextId = currentCommentId.intValue() + 1;
                }
                Comment commentNew = new Comment();
                commentNew.setBody(comment);
                commentNew.setAchievementId(achievementLocal.getId());
                commentNew.setCreated(new Date());
                commentNew.setAuthor(randomUser);
                commentNew.setModified(null);
                commentNew.setId(nextId);
                realm.copyToRealmOrUpdate(commentNew);
                achievementLocal.addComment(commentNew);
                realm.copyToRealmOrUpdate(achievementLocal);
            }
        });

        realm.close();
    }

    @Override
    public void onAuthorClicked(User user) {
        UserBottomSheetDialogFragment bottomSheetDialogFragment = new
                UserBottomSheetDialogFragment();
        bottomSheetDialogFragment.setUserViewModel(new UserViewModel(user, this, this));
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment
                .getTag());
    }

    @Override
    public void onSendMessageClicked(User recipient) {

    }

    @Override
    public void onViewProfileClicked(User profile) {

    }

    @Override
    public void onReportOrBlockClicked(User profile) {

    }
}
