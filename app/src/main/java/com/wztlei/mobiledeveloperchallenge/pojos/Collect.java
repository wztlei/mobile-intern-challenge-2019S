package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

public class Collect {
    @SerializedName("product_id")
    private long product_id;

    public Collect(long product_id) {
        this.product_id = product_id;
    }

    public long getProductId() {
        return product_id;
    }

    public void setProductId(long product_id) {
        this.product_id = product_id;
    }
}
