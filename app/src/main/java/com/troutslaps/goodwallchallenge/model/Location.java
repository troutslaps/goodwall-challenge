package com.troutslaps.goodwallchallenge.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by duchess on 12/02/2017.
 */

public class Location extends RealmObject {

    @PrimaryKey
    int id;
    String locality;
    String country;
    String adminArea1;

    public Location() {
        // do nothing
    }

    public Location(int id, String locality, String country, String adminArea1) {
        this.id = id;
        this.locality = locality;
        this.country = country;
        this.adminArea1 = adminArea1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdminArea1() {
        return adminArea1;
    }

    public void setAdminArea1(String adminArea1) {
        this.adminArea1 = adminArea1;
    }


}
