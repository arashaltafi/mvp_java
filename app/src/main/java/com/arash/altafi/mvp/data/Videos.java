package com.arash.altafi.mvp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Videos implements Parcelable {

    String id , title , videolink , image;

    public Videos(String id, String title, String videolink, String image) {
        this.id = id;
        this.title = title;
        this.videolink = videolink;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
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
        dest.writeString(this.title);
        dest.writeString(this.videolink);
        dest.writeString(this.image);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readString();
        this.title = source.readString();
        this.videolink = source.readString();
        this.image = source.readString();
    }

    protected Videos(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.videolink = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Videos> CREATOR = new Parcelable.Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            return new Videos(source);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
}