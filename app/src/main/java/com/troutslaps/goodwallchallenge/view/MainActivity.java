package com.troutslaps.goodwallchallenge.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.http.client.AchievementRestClient;
import com.troutslaps.goodwallchallenge.http.client.Result;
import com.troutslaps.goodwallchallenge.http.response.ApiListWithMetadata;
import com.troutslaps.goodwallchallenge.model.Achievement;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements AchievementRestClient.GetAchievementsCallback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AchievementRestClient.getInstance(this).getAchievements();
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
                        realm.copyToRealmOrUpdate(achievements);
                    }
                });
                Snackbar.make(fab, "Done!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFailure(Result result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(fab, "Failed!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
