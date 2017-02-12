package com.troutslaps.goodwallchallenge.model;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by duchess on 12/02/2017.
 */
public class Photo extends RealmObject {

    @PrimaryKey
    int id;
    String name;
    int retinaLevel;
    String use;

    public Photo() {
        // do nothing
    }

    public Photo(int id, String name, int retinaLevel, String use) {
        this.id = id;
        this.name = name;
        this.retinaLevel = retinaLevel;
        this.use = use;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRetinaLevel() {
        return retinaLevel;
    }

    public void setRetinaLevel(int retinaLevel) {
        this.retinaLevel = retinaLevel;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    enum Usage {
        Profile("profile"),
        Content("content");

        String usage;

        Usage(String usage) {
            this.usage = usage;
        }

        String usage() {
            return usage;
        }
    }
}
