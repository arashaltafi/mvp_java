package com.arash.altafi.mvp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_news")
public class News implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String date;
    private String image;
    private String video;
    private String image_writer;
    private String writer;
    private String grouping;

    // جهت تغییر نام این ستون
    @ColumnInfo(name = "is_bookmarked")
    private boolean isBookMark;

    public boolean isBookMark() {
        return isBookMark;
    }

    public void setBookMark(boolean bookMark) {
        isBookMark = bookMark;
    }

    public boolean isVideoShow()
    {
        return !image.isEmpty() && !video.isEmpty();
    }

    public boolean isVideoShow1()
    {
        if (!video.isEmpty())
        {
            return true;
        }
        else
        {
            if (!image.isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public News(int id, String title, String description, String date, String image, String video, String image_writer, String writer , String grouping) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
        this.video = video;
        this.image_writer = image_writer;
        this.writer = writer;
        this.grouping = grouping;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage_writer() {
        return image_writer;
    }

    public void setImage_writer(String image_writer) {
        this.image_writer = image_writer;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.date);
        dest.writeString(this.image);
        dest.writeString(this.video);
        dest.writeString(this.image_writer);
        dest.writeString(this.writer);
        dest.writeString(this.grouping);
        dest.writeByte(this.isBookMark ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.title = source.readString();
        this.description = source.readString();
        this.date = source.readString();
        this.image = source.readString();
        this.video = source.readString();
        this.image_writer = source.readString();
        this.writer = source.readString();
        this.grouping = source.readString();
        this.isBookMark = source.readByte() != 0;
    }

    protected News(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.date = in.readString();
        this.image = in.readString();
        this.video = in.readString();
        this.image_writer = in.readString();
        this.writer = in.readString();
        this.grouping = in.readString();
        this.isBookMark = in.readByte() != 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}