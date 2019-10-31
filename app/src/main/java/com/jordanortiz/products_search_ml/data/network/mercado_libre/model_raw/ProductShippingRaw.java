package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductShippingRaw {
    @SerializedName("free_shipping")
    @Expose
    private Boolean freeShipping;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("logistic_type")
    @Expose
    private String logisticType;
    @SerializedName("store_pick_up")
    @Expose
    private Boolean storePickUp;

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public Boolean getStorePickUp() {
        return storePickUp;
    }

    public void setStorePickUp(Boolean storePickUp) {
        this.storePickUp = storePickUp;
    }
}
