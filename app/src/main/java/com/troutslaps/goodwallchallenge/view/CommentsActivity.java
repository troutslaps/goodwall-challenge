package com.troutslaps.goodwallchallenge.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.app.Constants;

public class CommentsActivity extends AppCompatActivity implements CommentsFragment
        .CommentsFragmentListener {

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
}
