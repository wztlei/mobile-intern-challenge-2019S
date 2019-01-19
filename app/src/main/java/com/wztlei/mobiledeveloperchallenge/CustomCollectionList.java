package com.wztlei.mobiledeveloperchallenge;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomCollectionList {
    @SerializedName("custom_collections")
    private List<CustomCollection> custom_collections;

    CustomCollectionList(List<CustomCollection> custom_collections) {
        this.custom_collections = custom_collections;
    }


    public List<CustomCollection> getCustomCollections() {
        return custom_collections;
    }

    public void setCustom_collections(List<CustomCollection> customCollections) {
        this.custom_collections = custom_collections;
    }
}
