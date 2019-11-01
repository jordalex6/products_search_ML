package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model;

import java.util.List;

public class ProdDetailModel {

    private String id;
    private String title;
    private Double price;
    private String thumbnailImg;
    private String conditionType;
    private int soldQuantity;
    private Integer availableQuantity;
    private ProdDetailInstallmentsModel installments;
    private ProdDetailAddressModel addressModel;
    private List<ProdDetailAttributeModel> attributeModelList;
    private boolean shipping;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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


    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public ProdDetailInstallmentsModel getInstallments() {
        return installments;
    }

    public void setInstallments(ProdDetailInstallmentsModel installments) {
        this.installments = installments;
    }

    public ProdDetailAddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(ProdDetailAddressModel addressModel) {
        this.addressModel = addressModel;
    }

    public List<ProdDetailAttributeModel> getAttributeModelList() {
        return attributeModelList;
    }

    public void setAttributeModelList(List<ProdDetailAttributeModel> attributeModelList) {
        this.attributeModelList = attributeModelList;
    }
}
