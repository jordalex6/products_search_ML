package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

import java.util.List;

public class ProductModel {

    private String id;
    private String title;
    private float price;
    private String thumbnailImg;
    private String conditionType;
    private int soldQuantity;
    private ProductAddressModel address;
    private ProductInstallmentsModel installments;
    private boolean shipping;
    private List<ProductAttributeModel> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getThumbnailImg() {
        return thumbnailImg;
    }

    public void setThumbnailImg(String thumbnailImg) {
        this.thumbnailImg = thumbnailImg;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public ProductAddressModel getProductAddress() {
        return address;
    }

    public void setProductAddress(ProductAddressModel address) {
        this.address = address;
    }

    public ProductInstallmentsModel getProductInstallments() {
        return installments;
    }

    public void setProductInstallments(ProductInstallmentsModel installments) {
        this.installments = installments;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public List<ProductAttributeModel> getProductAttribute() {
        return attributes;
    }

    public void setProductAttribute(List<ProductAttributeModel> attributes) {
        this.attributes = attributes;
    }

}
