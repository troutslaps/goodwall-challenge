package com.troutslaps.goodwallchallenge.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.http.client.AchievementRestClient;
import com.troutslaps.goodwallchallenge.http.client.Result;
import com.troutslaps.goodwallchallenge.http.response.ApiListWithMetadata;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.User;
import com.troutslaps.goodwallchallenge.viewmodel.AchievementViewModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements AchievementRestClient
        .GetAchievementsCallback, AchievementViewModel.Listener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AchievementRestClient.getInstance(this).getAchievements();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_achievement, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressStarted(Result result) {
        // TODO tell fragment to circular progress
    }

    @Override
    public void onSuccess(final Result result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bundle extras = result.getExtras();
                ApiListWithMetadata<Achievement> apiResult = (ApiListWithMetadata<Achievement>)
                        extras
                                .getSerializable(Constants.Fields
                                        .Achievements);
                final List<Achievement> achievements = apiResult.getData();
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        // hack to query all existing comments matching the achievement id
                        // and re-associate them to parent achievement object
                        RealmResults<Comment> allComments;
                        RealmList<Comment> commentsList;
                        for (Achievement achievement : achievements) {
                            Achievement oldAchievementRecord = realm.where(Achievement.class)
                                    .equalTo(Constants.Fields.Id, achievement.getId()).findFirst();
                            if (oldAchievementRecord != null) {
                                // update existing comments with latest from API
                                realm.copyToRealmOrUpdate(achievement.getComments());
                                // then re-fetch ALL comments previously associated with
                                // this achievement
                                allComments = realm.where(Comment.class)
                                        .equalTo(Constants.Fields.AchievementId, achievement
                                                .getId()).findAll();
                                // and then re-establish association before writing
                                if(achievement.getComments() == null) {
                                    commentsList = new RealmList<Comment>();
                                } else {
                                    commentsList = achievement.getComments();
                                }
                                commentsList.addAll(allComments
                                        .subList(0,
                                                allComments.size()));
                                achievement.setComments(commentsList);
                                achievement.setCommentsCount(commentsList.size());
                            }
                            realm.copyToRealmOrUpdate(achievement);

                        }
                    }
                });

            }
        });
    }

    @Override
    public void onFailure(Result result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onCongratsClicked(Achievement achievement) {
        launchCommentsActivity(achievement);
    }

    @Override
    public void onCommentsClicked(Achievement achievement) {
        launchCommentsActivity(achievement);
    }

    @Override
    public void onAddCommentButtonClicked(Achievement achievement, String comment, User user) {
        // do nothing
    }

    @Override
    public void onAuthorNameClicked(User user) {
        // do nothing for now
    }

    private void launchCommentsActivity(Achievement achievement) {
        Intent i = new Intent(this, CommentsActivity.class);
        i.putExtra(Constants.Fields.Id, achievement.getId());
        startActivity(i);
    }
}
