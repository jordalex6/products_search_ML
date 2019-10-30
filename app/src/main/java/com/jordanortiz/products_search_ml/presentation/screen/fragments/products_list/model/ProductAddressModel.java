package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

public class ProductAddressModel {

    private String stateName;
    private String cityName;

    public ProductAddressModel(String stateName, String cityName) {
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
