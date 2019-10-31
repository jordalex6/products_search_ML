package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductSellerAddressRaw {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("address_line")
    @Expose
    private String addressLine;
    @SerializedName("zip_code")
    @Expose
    private String zipCode;
    @SerializedName("country")
    @Expose
    private SellerAddressElementRaw country;
    @SerializedName("state")
    @Expose
    private SellerAddressElementRaw state;
    @SerializedName("city")
    @Expose
    private SellerAddressElementRaw city;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public SellerAddressElementRaw getCountry() {
        return country;
    }

    public void setCountry(SellerAddressElementRaw country) {
        this.country = country;
    }

    public SellerAddressElementRaw getState() {
        return state;
    }

    public void setState(SellerAddressElementRaw state) {
        this.state = state;
    }

    public SellerAddressElementRaw getCity() {
        return city;
    }

    public void setCity(SellerAddressElementRaw city) {
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
