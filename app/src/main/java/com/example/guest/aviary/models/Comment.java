package com.example.guest.aviary.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/19/16.
 */
@Parcel
public class Comment {
    String body;
    String name;
    int likes;
    int dislikes;
    String birdId;
    String id;

    public Comment() {}

    public Comment(String body, String name) {
        this.body = body;
        this.name = name;
        this.likes = 0;
        this.dislikes = 0;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getBirdId() {
        return birdId;
    }

    public void setBirdId(String birdId) {
        this.birdId = birdId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
