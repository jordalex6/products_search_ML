package com.jordanortiz.products_search_ml.domain.model.product;


public class ProductSellerAddressEntity {

    private SellerAddressElementEntity country;
    private SellerAddressElementEntity state;
    private SellerAddressElementEntity city;
    private String latitude;
    private String longitude;

    public ProductSellerAddressEntity() {
    }

    public SellerAddressElementEntity getCountry() {
        return country;
    }

    public void setCountry(SellerAddressElementEntity country) {
        this.country = country;
    }

    public SellerAddressElementEntity getState() {
        return state;
    }

    public void setState(SellerAddressElementEntity state) {
        this.state = state;
    }

    public SellerAddressElementEntity getCity() {
        return city;
    }

    public void setCity(SellerAddressElementEntity city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
