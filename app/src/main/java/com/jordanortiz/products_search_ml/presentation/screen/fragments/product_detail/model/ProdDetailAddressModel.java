package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model;

public class ProdDetailAddressModel {

    private String stateName;
    private String cityName;

    public ProdDetailAddressModel(String stateName, String cityName) {
        this.stateName = stateName;
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
