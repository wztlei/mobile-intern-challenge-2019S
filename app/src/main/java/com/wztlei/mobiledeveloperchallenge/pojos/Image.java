package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("src")
    private String src;

    public Image(String src) {
        this.src = src;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
