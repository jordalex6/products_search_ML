package com.jordanortiz.products_search_ml.domain.model.product;

public class ProductInstallmentsEntity {

    private Integer quantity;
    private Float amount;
    private Float rate;
    private String currencyId;

    public ProductInstallmentsEntity() {
    }

    public ProductInstallmentsEntity(Integer quantity, Float amount, Float rate, String currencyId) {
        this.quantity = quantity;
        this.amount = amount;
        this.rate = rate;
        this.currencyId = currencyId;
    }

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

    @Override
    public String toString() {
        return "ProductInstallmentsEntity{" +
                "quantity=" + quantity +
                ", amount=" + amount +
                ", rate=" + rate +
                ", currencyId='" + currencyId + '\'' +
                '}';
    }
}
