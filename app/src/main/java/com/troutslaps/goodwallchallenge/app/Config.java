package com.troutslaps.goodwallchallenge.app;

/**
 * Created by duchess on 12/02/2017.
 */

public class Config {

    private final static String BaseUrl = "http://private-b8ff34-api492.apiary-mock.com";

    public static String getBaseUrl() {
        return BaseUrl;
    }

    public class Endpoints {
        public final static String Achievements = "feed";
    }
}
