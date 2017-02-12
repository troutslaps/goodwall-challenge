package com.troutslaps.goodwallchallenge.app;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.model.Location;

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

    private static ThreadLocal<SimpleDateFormat> sdfForView = new
            ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(Constants.View.DateFormat);
                }

            };

    private static ThreadLocal<SimpleDateFormat> sdfForTime = new
            ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(Constants.View.TimeFormat);
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

        public static String getFormattedDate(Date date) {
            if (date == null) {
                return "";
            }
            return sdfForView.get().format(date);
        }

        public static String getTimeAgo(Context context, Date date) {
            return DateUtils.getRelativeTimeSpanString(date.getTime(), new Date().getTime(),
                    DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        }

        public static String getDisplayTime(Date date) {
            if (date == null) {
                return "";
            }
            return sdfForTime.get().format(date);
        }
    }


    public static class Locality {
        public static String getLocalityDisplay(Context context, Location location) {
            String locality = "";
            if (location != null) {
                if (location.getCountry() != null && location.getLocality() != null) {
                    locality = String.format(context.getString(R.string.complete_locality),
                            location.getLocality(), location.getCountry());
                } else if (location.getCountry() != null) {
                    locality = String.format(context.getString(R.string.locality), location
                            .getCountry());
                } else {
                    locality = String.format(context.getString(R.string.locality), location
                            .getLocality());
                }
            }
            return locality;
        }
    }
}
