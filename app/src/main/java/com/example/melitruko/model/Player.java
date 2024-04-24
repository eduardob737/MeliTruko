package com.example.melitruko.model;

import android.net.Uri;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private Uri photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
