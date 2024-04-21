package com.example.melitruko.model;

import java.io.Serializable;
import java.net.URI;

public class Player implements Serializable {

    private String name;
    private URI photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getPhoto() {
        return photo;
    }

    public void setPhoto(URI photo) {
        this.photo = photo;
    }
}
