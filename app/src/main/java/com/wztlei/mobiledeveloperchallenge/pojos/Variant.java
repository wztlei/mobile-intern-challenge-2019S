package com.wztlei.mobiledeveloperchallenge.pojos;

import com.google.gson.annotations.SerializedName;

public class Variant {
    @SerializedName("inventory_quantity")
    private int inventory_quantity;

    public Variant(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public int getInventoryQuantity() {
        return inventory_quantity;
    }

    public void setInventoryQuantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }
}
