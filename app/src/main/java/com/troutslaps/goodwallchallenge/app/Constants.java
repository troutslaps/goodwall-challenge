package com.troutslaps.goodwallchallenge.app;

import com.troutslaps.goodwallchallenge.model.User;

import java.util.Date;

/**
 * Created by duchess on 12/02/2017.
 */

public class Constants {
    public static class Api {
        public static final String DateFormat = "yyyy-MM-dd'T'HH:mm:ssZ";
    }

    public static class View {
        public static final String DateFormat = "MMM dd, yyyy";
        public static final String TimeFormat = "hh:mm a";
    }

    public static class Fields {
        public final static String Id = "id";
        public final static String Body = "body";
        public final static String Created = "created";
        public final static String Modified = "modified";
        public final static String Author = "author";
        public final static String Achievement = "achievement";
        public final static String  Achievements = "achievements";
    }

}
