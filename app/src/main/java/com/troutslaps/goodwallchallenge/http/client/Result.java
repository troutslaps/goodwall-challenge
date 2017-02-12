package com.troutslaps.goodwallchallenge.http.client;

import android.os.Bundle;

/**
 * Created by duchess on 12/02/2017.
 */
public class Result {
    enum Type {
        InProgress,
        Success,
        NoInternet,
        NotFound, // 404
        GenericError // 40x, 50x
    }
    Type type;
    Bundle extras;

    public Result(Type type) {
        this.type = type;
        this.extras = null;
    }
    public Result(Type type, Bundle extras) {
        this.type = type;
        this.extras = extras;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Bundle getExtras() {
        return extras;
    }

    public void setExtras(Bundle extras) {
        this.extras = extras;
    }


}
