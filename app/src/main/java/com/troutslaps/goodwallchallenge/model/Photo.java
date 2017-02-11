package com.troutslaps.goodwallchallenge.model;

import java.util.List;

/**
 * Created by duchess on 12/02/2017.
 */
public class Photo {


    int id;
    String name;
    int retinaLevel;
    Usage use;

    public Photo() {
        // do nothing
    }

    public Photo(int id, String name, int retinaLevel, Usage use) {
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

    public Usage getUse() {
        return use;
    }

    public void setUse(Usage use) {
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
