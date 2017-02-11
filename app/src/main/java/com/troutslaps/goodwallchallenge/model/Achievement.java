package com.troutslaps.goodwallchallenge.model;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by duchess on 11/02/2017.
 */

public class Achievement extends RealmObject {

    @PrimaryKey
    int id;
    String slug;
    String title;
    String body;
    int color;
    Date created;
    Date modified;
    Date timelineDate;
    Date startDate;
    int likeCount;
    int commentsCount;
    boolean hasLiked;
    Location location;
    User author;
    RealmList<Photo> pictures;
    RealmList<Comment> comments;

    public Achievement() {
    }

    public Achievement(int id, String slug, String title, String body, int color, Date created,
                       Date modified, Date timelineDate, Date startDate, int likeCount, int
                               commentsCount, boolean hasLiked, Location location, User author,
                       RealmList<Photo> pictures, RealmList<Comment> comments) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.color = color;
        this.created = created;
        this.modified = modified;
        this.timelineDate = timelineDate;
        this.startDate = startDate;
        this.likeCount = likeCount;
        this.commentsCount = commentsCount;
        this.hasLiked = hasLiked;
        this.location = location;
        this.author = author;
        this.pictures = pictures;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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

    public Date getTimelineDate() {
        return timelineDate;
    }

    public void setTimelineDate(Date timelineDate) {
        this.timelineDate = timelineDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean getHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public RealmList<Photo> getPictures() {
        return pictures;
    }

    public void setPictures(RealmList<Photo> pictures) {
        this.pictures = pictures;
    }

    public RealmList<Comment> getComments() {
        return comments;
    }

    public void setComments(RealmList<Comment> comments) {
        this.comments = comments;
    }
}
