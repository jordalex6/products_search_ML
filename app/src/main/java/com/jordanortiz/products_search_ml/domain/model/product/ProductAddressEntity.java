package com.jordanortiz.products_search_ml.domain.model.product;

public class ProductAddressEntity {
    private String stateName;
    private String cityName;

    public ProductAddressEntity(String stateName, String cityName) {
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
