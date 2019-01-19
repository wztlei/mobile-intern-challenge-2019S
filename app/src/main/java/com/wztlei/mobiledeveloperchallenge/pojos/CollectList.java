package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollectList {
    @SerializedName("collects")
    private List<Collect> collects;

    public CollectList(List<Collect> collects) {
        this.collects = collects;
    }

    public List<Collect> getCollects() {
        return collects;
    }

    public void setCollects(List<Collect> collects) {
        this.collects = collects;
    }
}
