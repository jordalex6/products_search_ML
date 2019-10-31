package com.jordanortiz.products_search_ml.domain.model.product;

import java.util.List;

public class ProductEntity {

    private String id;
    private String siteId;
    private String title;
    private Double price;
    private String currencyId;
    private Integer availableQuantity;
    private Integer soldQuantity;
    private String condition;
    private String thumbnail;
    private ProductInstallmentsEntity installments;
    private ProductAddressEntity address;
    private ProductShippingEntity shipping;
    private List<ProductAttributeEntity> attributes = null;
    private String categoryId;
    private List<String> tags = null;


    public ProductEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ProductInstallmentsEntity getInstallments() {
        return installments;
    }

    public void setInstallments(ProductInstallmentsEntity installments) {
        this.installments = installments;
    }

    public ProductAddressEntity getAddress() {
        return address;
    }

    public void setAddress(ProductAddressEntity address) {
        this.address = address;
    }

    public ProductShippingEntity getShipping() {
        return shipping;
    }

    public void setShipping(ProductShippingEntity shipping) {
        this.shipping = shipping;
    }

    public List<ProductAttributeEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttributeEntity> attributes) {
        this.attributes = attributes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
