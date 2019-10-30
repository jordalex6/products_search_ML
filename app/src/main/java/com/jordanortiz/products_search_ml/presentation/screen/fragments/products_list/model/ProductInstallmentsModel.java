package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

public class ProductInstallmentsModel {

    private int quantity;
    private double amount;
    private double rate;

    public ProductInstallmentsModel(int quantity, double amount, double rate) {
        this.quantity = quantity;
        this.amount = amount;
        this.rate = rate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
