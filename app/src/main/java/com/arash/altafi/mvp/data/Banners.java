package com.arash.altafi.mvp.data;

public class Banners {

    private int id;
    private String title;
    private String description;
    private String date;
    private String image;
    private String video;
    private String image_writer;
    private String writer;
    private String grouping;

    public Banners(int id, String title, String description, String date, String image, String video, String image_writer, String writer, String grouping) {
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

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }
}
