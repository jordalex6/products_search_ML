package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInstallmentsRaw {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("amount")
    @Expose
    private Float amount;
    @SerializedName("rate")
    @Expose
    private Float rate;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
