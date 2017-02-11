package com.troutslaps.goodwallchallenge.http.interfaces;

import com.troutslaps.goodwallchallenge.app.Config;
import com.troutslaps.goodwallchallenge.model.Achievement;
import com.troutslaps.goodwallchallenge.http.response.ApiListWithMetadata;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by duchess on 12/02/2017.
 */

public interface AchievementInterface {
    @GET(Config.Endpoints.Achievements)
    Call<ApiListWithMetadata<Achievement>> getAchievements();
}
