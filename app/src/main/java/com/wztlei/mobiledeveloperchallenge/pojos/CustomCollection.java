package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

public class CustomCollection {

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("body_html")
    private String body_html;

    @SerializedName("image")
    private Image image;

    public CustomCollection(long id, String title, String body_html, Image image) {
        this.id = id;
        this.title = title;
        this.body_html = body_html;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyHtml() {
        return body_html;
    }

    public void setBodyHtml(String body_html) {
        this.body_html = body_html;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
