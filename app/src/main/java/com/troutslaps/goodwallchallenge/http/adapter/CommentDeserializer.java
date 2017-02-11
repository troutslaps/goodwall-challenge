package com.troutslaps.goodwallchallenge.http.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.troutslaps.goodwallchallenge.app.Constants;
import com.troutslaps.goodwallchallenge.app.Utils;
import com.troutslaps.goodwallchallenge.model.Comment;
import com.troutslaps.goodwallchallenge.model.User;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by duchess on 12/02/2017.
 */

public class CommentDeserializer implements JsonDeserializer<Comment> {
    @Override
    public Comment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext
            context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        Comment comment = new Comment();
        if (jsonObject.has(Constants.Fields.Id)) {
            int id = -1;
            String body = null;
            String rawDate = null;
            JsonObject rawObject = null;
            Date created = null;
            Date modified = null;
            User author = null;
            int achievementId = -1;

            if (!jsonObject.get(Constants.Fields.Id).isJsonNull()) {
                id = jsonObject.get(Constants.Fields.Id).getAsInt();
            }
            comment.setId(id);

            if (!jsonObject.get(Constants.Fields.Body).isJsonNull()) {
                body = jsonObject.get(Constants.Fields.Body).getAsString();
            }
            comment.setBody(body);

            if (!jsonObject.get(Constants.Fields.Created).isJsonNull()) {
                rawDate = jsonObject.get(Constants.Fields.Created).getAsString();
                created = Utils.DateTime.parseFromApi(rawDate);
            }
            comment.setCreated(created);

            if (!jsonObject.get(Constants.Fields.Modified).isJsonNull()) {
                rawDate = jsonObject.get(Constants.Fields.Modified).getAsString();
                modified = Utils.DateTime.parseFromApi(rawDate);
            }
            comment.setModified(modified);

            if (!jsonObject.get(Constants.Fields.Author).isJsonNull()) {
                author = new Gson().fromJson(jsonObject.get(Constants.Fields.Author)
                        .getAsJsonObject(), User.class);
            }
            comment.setAuthor(author);

            if (!jsonObject.get(Constants.Fields.Achievement).isJsonNull()) {
                rawObject = jsonObject.get(Constants.Fields.Achievement).getAsJsonObject();
                if (!rawObject.get(Constants.Fields.Id).isJsonNull()) {
                    achievementId = rawObject.get(Constants.Fields.Id).getAsInt();
                }
            }
            comment.setAchievementId(achievementId);

        }

        return comment;
    }
}
