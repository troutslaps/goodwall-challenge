package com.troutslaps.goodwallchallenge.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.Photo;
import com.troutslaps.goodwallchallenge.model.User;
import com.troutslaps.goodwallchallenge.view.fragment.CommentsFragment;
import com.troutslaps.goodwallchallenge.view.fragment.UserBottomSheetDialogFragment;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;
import com.troutslaps.goodwallchallenge.viewmodel.UserViewModel;

import java.util.Date;

import io.realm.Realm;

public class CommentsActivity extends AppCompatActivity implements CommentsFragment
        .CommentsFragmentListener, AchievementViewModel.Listener, UserViewModel.Listener {

    private UserBottomSheetDialogFragment bottomSheetDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getAchievementId() {
        return getIntent().getIntExtra(Constants.Fields.Id, -1);
    }

    @Override
    public void onAchievementUpdated(Achievement achievement) {
        String title = String.format(getString(R.string.lbl_comments_toolbar), achievement
                .getCommentsCount());
        getSupportActionBar().setTitle(title);
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
    public void onImageTapped(Achievement achievement, Photo photo) {
        // do nothing
    }

    @Override
    public void onAuthorClicked(User user) {
        if(bottomSheetDialogFragment == null) {
            bottomSheetDialogFragment = new
                    UserBottomSheetDialogFragment();
        }
        bottomSheetDialogFragment.setUserViewModel(new UserViewModel(user, this, this));
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment
                .getTag());


    }

    @Override
    public void onSendMessageClicked(User recipient) {
        bottomSheetDialogFragment.dismiss();
        // do nothing
    }

    @Override
    public void onViewProfileClicked(User profile) {
        bottomSheetDialogFragment.dismiss();
        // do nothing
    }

    @Override
    public void onReportOrBlockClicked(User profile) {
        bottomSheetDialogFragment.dismiss();
        // do nothing
    }
}
