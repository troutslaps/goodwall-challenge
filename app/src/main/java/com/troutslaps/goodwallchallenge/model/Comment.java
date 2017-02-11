package com.troutslaps.goodwallchallenge.model;

import java.util.Date;

/**
 * Created by duchess on 11/02/2017.
 */

public class Comment {
    int id;
    String body;
    Date created;
    Date modified;
    User author;
    int achievementId;

    public Comment() {
        // do nothing
    }

    public Comment(int id, String body, Date created, Date modified, User author, int
            achievementId) {
        this.id = id;
        this.body = body;
        this.created = created;
        this.modified = modified;
        this.author = author;
        this.achievementId = achievementId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }
}
