package com.troutslaps.goodwallchallenge.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by duchess on 12/02/2017.
 */

public class Utils {
    private static ThreadLocal<SimpleDateFormat> sdfForApiResponse = new
            ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(Constants.Api.DateFormat);
                }

            };

    public static class DateTime {
        public static Date parseFromApi(String string) {
            try {
                return sdfForApiResponse.get().parse(string);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new Date();
        }
    }


}
