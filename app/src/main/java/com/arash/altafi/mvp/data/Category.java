package com.arash.altafi.mvp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    String id,name,number,image;

    public Category(String id, String name, String number, String image) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.image = image;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.image);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.name = source.readString();
        this.number = source.readString();
        this.image = source.readString();
    }

    protected Category(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.number = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}