package com.troutslaps.goodwallchallenge.http.client;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.troutslaps.goodwallchallenge.BuildConfig;
import com.troutslaps.goodwallchallenge.app.Config;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.http.adapter.CommentDeserializer;
import com.troutslaps.goodwallchallenge.http.interfaces.AchievementInterface;
import com.troutslaps.goodwallchallenge.http.response.ApiListWithMetadata;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.model.Comment;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duchess on 12/02/2017.
 */

public class AchievementRestClient {

    public static final String TAG = AchievementRestClient.class.getSimpleName();
    protected Retrofit retrofit;
    private static AchievementRestClient INSTANCE;
    private ScheduledExecutorService executorService;
    private AchievementInterface achievementInterface;

    private AchievementRestClient() {
        OkHttpClient client;
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor
                    (interceptor).build();
        } else {
            client = new OkHttpClient.Builder().build();
        }

        retrofit = new Retrofit.Builder().baseUrl(Config.getBaseUrl()).client(client)
                .addConverterFactory(GsonConverterFactory.create(getGson())).build();
        executorService = Executors.newSingleThreadScheduledExecutor();
        achievementInterface = retrofit.create(AchievementInterface.class);
    }

    protected Gson getGson() {
        return new GsonBuilder().setDateFormat(Constants.Api.DateFormat).registerTypeAdapter
                (Comment.class, new CommentDeserializer()).create();
    }

    public static AchievementRestClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AchievementRestClient();
        }
        return INSTANCE;
    }

    public void getAchievements(final Runnable uiCallback) {
//        executorService.execute(new Runnable() {
//
//            @Override
//            public void run() {
                Log.d(TAG,"hello??");
                Call<ApiListWithMetadata<Achievement>> call = achievementInterface
                        .getAchievements();
                Log.d(TAG, call.request().url().toString());
                // save to realm??
                call.enqueue(new Callback<ApiListWithMetadata<Achievement>>() {
                    @Override
                    public void onResponse(Call<ApiListWithMetadata<Achievement>> call,
                                           Response<ApiListWithMetadata<Achievement>> response) {

                        Log.d(TAG, "size = " + response.body().getData().size());
                        if (uiCallback != null) {
                            new Handler(Looper.getMainLooper()).post(uiCallback);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiListWithMetadata<Achievement>> call, Throwable
                            t) {
                        Log.d(TAG, "failed!!");
                        t.printStackTrace();
                        if (t instanceof ConnectException || t instanceof UnknownHostException) {


                        } else {
                        }
                    }
                });


            }
//        });
//    }


}
