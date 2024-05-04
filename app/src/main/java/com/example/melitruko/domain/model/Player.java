package com.example.melitruko.domain.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Player implements Parcelable {

    private int id;
    private String name;
    private Uri photo;
    private boolean isPartOfATeam = false;

    public Player() {
    }

    protected Player(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photo = in.readParcelable(Uri.class.getClassLoader());
        isPartOfATeam = in.readByte() != 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

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

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public boolean isPartOfATeam() {
        return isPartOfATeam;
    }

    public void setPartOfATeam(boolean partOfATeam) {
        isPartOfATeam = partOfATeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeParcelable(photo, i);
        parcel.writeByte((byte) (isPartOfATeam ? 1 : 0));
    }
}