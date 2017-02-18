package com.troutslaps.goodwallchallenge.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.format.DateUtils;
import android.util.Log;

import com.mikepenz.goodwall_typeface_library.GoodWall;
import com.mikepenz.iconics.IconicsDrawable;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.model.Location;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by duchess on 12/02/2017.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();
    private static HashMap<String, String> PhotoUrls = new HashMap<>();
    private static HashMap<String, String> ProfilePhotoUrls = new HashMap<>();
    public static int[] Placeholders = {R.drawable.img_placeholder_green, R.drawable
            .img_placeholder_red, R.drawable.img_placeholder_violet,
            R.drawable.img_placeholder_yellow};

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
                    DateUtils.WEEK_IN_MILLIS, 0).toString();
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

    public static String getRandomPhoto(String name) {
        if (name != null && !name.isEmpty()) {
            if (PhotoUrls.containsKey(name)) {
                return PhotoUrls.get(name);
            } else {
                String newUrl = generateRandomPhotoUrl();
                PhotoUrls.put(name, newUrl);
                return newUrl;
            }
        }
        return "";
    }

    public static String getRandomProfilePhoto(String name) {
        if (name != null && !name.isEmpty()) {
            if (ProfilePhotoUrls.containsKey(name)) {
                return ProfilePhotoUrls.get(name);
            } else {
                String newUrl = generateRandomProfilePhotoUrl();
                ProfilePhotoUrls.put(name, newUrl);
                return newUrl;
            }
        }
        return "";
    }

    private static String generateRandomPhotoUrl() {
        String base = "https://unsplash.it/768/768?image=";
        String id = Integer.toString(new Random().nextInt(1000));
        return base + id;
    }

    private static String generateRandomProfilePhotoUrl() {
        String base = "https://randomuser.me/api/portraits/";
        String gender = new Random().nextInt(2) == 0 ? "men/" : "women/";
        String id = Integer.toString(new Random().nextInt(99)) + ".jpg";
        return base + gender + id;
    }

    public static Drawable getCongratsDrawable(Context context) {
        return new IconicsDrawable(context)
                .icon(GoodWall.Icon.gdw_Congrats_08_18)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null))
                .sizeDp(16);
    }

    public static Drawable getRandomPlaceholder(Context context) {
        int index = new Random().nextInt(Placeholders.length);
        return ResourcesCompat.getDrawable(context.getResources(), Placeholders[index], null);
    }
}
