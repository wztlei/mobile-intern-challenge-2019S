package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("title")
    private String title;

    @SerializedName("vendor")
    private String vendor;

    @SerializedName("variants")
    private List<Variant> variants;

    @SerializedName("image")
    private Image image;

    public Product(String title, String vendor, List<Variant> variants, Image image) {
        this.title = title;
        this.vendor = vendor;
        this.variants = variants;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
