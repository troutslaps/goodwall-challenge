package com.troutslaps.goodwallchallenge.model;

import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by duchess on 11/02/2017.
 */

public class User extends RealmObject {

    @PrimaryKey
    int id;
    String displayName;
    String type;
    String slug;
    int level;
    Photo picture;
    Location location;
    String connection;

    public User() {
        // do nothing
    }

    public User(int id, String displayName, String type, String slug, int level, Photo picture, Location location, String connection) {
        this.id = id;
        this.displayName = displayName;
        this.type = type;
        this.slug = slug;
        this.level = level;
        this.picture = picture;
        this.location = location;
        this.connection = connection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Photo getPicture() {
        return picture;
    }

    public void setPicture(Photo picture) {
        this.picture = picture;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public static User getRandomUser(Realm realm) {
        List<User> users = realm.where(User.class).findAll();
        return users.get(new Random().nextInt(users.size()));

    }

}

