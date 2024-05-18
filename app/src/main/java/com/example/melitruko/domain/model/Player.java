package com.example.melitruko.domain.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "players")
public class Player implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String photoPath;
    private boolean isPartOfATeam = false;

    public Player() {
    }

    public Player(String name, String photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    protected Player(Parcel in) {
        id = in.readInt();
        name = in.readString();
        photoPath = in.readString();
        isPartOfATeam = in.readByte() != 0;
    }

    public Bitmap getImageBitmap(){
        if (photoPath != null){
            return BitmapFactory.decodeFile(photoPath);
        } else {
            return null;
        }
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
        parcel.writeString(photoPath);
        parcel.writeByte((byte) (isPartOfATeam ? 1 : 0));
    }
}