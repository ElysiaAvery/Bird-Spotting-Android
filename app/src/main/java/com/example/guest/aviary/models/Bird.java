package com.example.guest.aviary.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/19/16.
 */
@Parcel
public class Bird {
    String name;
    String family;
    String gender;
    String city;
    String state;
    String zip;
    String imageUrl;
    String audioFile;
    private String pushId;
    String index;

    public Bird() {}

    public Bird(String name, String family, String gender, String city, String state, String zip) {
        this.name = name;
        this.family = family;
        this.gender = gender;
        this.city = city;
        this.state =state;
        this.zip = zip;
        this.imageUrl = "not_specified";
        this.audioFile = "not_specified;";
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
