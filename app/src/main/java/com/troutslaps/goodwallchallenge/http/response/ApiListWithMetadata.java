package com.troutslaps.goodwallchallenge.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by duchess on 12/02/2017.
 */

public class ApiListWithMetadata<T> {
    @SerializedName("data")
    private List<T> data;

    @SerializedName("meta")
    private List<Object> meta;

    public ApiListWithMetadata() {
        // do nothing
    }

    public ApiListWithMetadata(List<T> data, List<Object> meta) {
        this.data = data;
        this.meta = meta;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }
}
