package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.http.client.AchievementRestClient;
import com.troutslaps.goodwallchallenge.http.client.Result;
import com.troutslaps.goodwallchallenge.http.response.ApiListWithMetadata;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.view.adapter.AchievementAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by duchess on 22/02/2017.
 */

public class FeedViewModel extends BaseObservable implements AchievementRestClient
        .GetAchievementsCallback {


    private static final String TAG = FeedViewModel.class.getSimpleName();
    RealmResults<Achievement> entries;
    Context context;
    Listener listener;
    boolean isRefreshing = false;
    AchievementAdapter adapter;
    LinearLayoutManager llm;
    Realm realm;

    public FeedViewModel(RealmResults<Achievement> entries, Context context, Listener listener,
                         Realm realm) {
        this.entries = entries;
        this.context = context;
        this.listener = listener;
        this.realm = realm;
    }

    @Bindable
    public int getEmptyLoading() {
        Log.d(TAG, "getIsRefreshing() && entries.size() == 0 = " + (getIsRefreshing() && entries
                .size() == 0));
        return (getIsRefreshing() && entries.size() == 0) ? View.VISIBLE : View.GONE;
    }
    @Bindable
    public boolean getIsRefreshing() {
        return isRefreshing;
    }

    @Bindable
    public SwipeRefreshLayout.OnRefreshListener getOnFeedRefresh() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                AchievementRestClient.getInstance(FeedViewModel.this).getAchievements();
                notifyPropertyChanged(BR.isRefreshing);
            }
        };
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        if (llm == null) {
            llm = new LinearLayoutManager(context);
        }
        return llm;
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        if (adapter == null) {
            adapter = new AchievementAdapter(context, entries);
        }
        return adapter;
    }

    @Override
    public void onProgressStarted(Result result) {
        isRefreshing = true;
        notifyPropertyChanged(BR.isRefreshing);
    }

    @Override
    public void onSuccess(final Result result) {
        isRefreshing = false;
        Bundle extras = result.getExtras();
        ApiListWithMetadata<Achievement> apiResult = (ApiListWithMetadata<Achievement>)
                extras.getSerializable(Constants.Fields
                        .Achievements);
        final List<Achievement> achievements = apiResult.getData();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                Achievement.saveAchievements(realm, achievements);
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                adapter.notifyDataSetChanged();
                notifyPropertyChanged(BR.isRefreshing);
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {

            @Override
            public void onError(Throwable error) {
                notifyPropertyChanged(BR.isRefreshing);
                listener.onErrorOccured();
            }
        });
    }

    @Override
    public void onFailure(Result result) {
        new Handler(Looper.getMainLooper()).post((new Runnable() {
            @Override
            public void run() {
                isRefreshing = false;
                notifyChange();
                listener.onErrorOccured();
            }
        }));
    }

    public interface Listener {
        void onSuccess();

        void onErrorOccured();
    }
}
