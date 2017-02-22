package com.troutslaps.goodwallchallenge.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

import com.mikepenz.goodwall_typeface_library.GoodWall;
import com.mikepenz.iconics.IconicsDrawable;
import com.troutslaps.goodwallchallenge.R;
import com.troutslaps.goodwallchallenge.model.User;

/**
 * Created by duchess on 19/02/2017.
 */

public class UserViewModel extends BaseObservable {
    User user;
    Context context;
    Listener listener;

    public UserViewModel(User user, Context context, Listener listener) {
        this.user = user;
        this.context = context;
        this.listener = listener;
    }

    @Bindable
    public Drawable getViewProfileDrawable() {
        return new IconicsDrawable(context).icon(GoodWall.Icon.gdw_Profile18)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.colorTxtInfo,
                        null))
                .sizeDp(Math.round(context.getResources().getDimension(R.dimen.like_icon)));
    }

    @Bindable
    public Drawable getSendMessageDrawable() {
        return new IconicsDrawable(context).icon(GoodWall.Icon.gdw_Message20)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.colorTxtInfo,
                        null))
                .sizeDp(Math.round(context.getResources().getDimension(R.dimen.like_icon)));
    }

    @Bindable
    public Drawable getReportBlockDrawable() {
        return new IconicsDrawable(context).icon(GoodWall.Icon.gdw_Report18)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.colorTxtInfo,
                        null))
                .sizeDp(Math.round(context.getResources().getDimension(R.dimen.like_icon)));
    }


    public View.OnClickListener onViewProfileClicked() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onViewProfileClicked(user);
            }
        };
    }

    public View.OnClickListener onSendMessageClicked() {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onSendMessageClicked(user);
            }
        };
    }

    public View.OnClickListener onReportOrBlockClicked() {
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onReportOrBlockClicked(user);
            }
        };
    }

    public interface Listener {
        void onSendMessageClicked(User recipient);
        void onViewProfileClicked(User profile);
        void onReportOrBlockClicked(User profile);
    }

}
