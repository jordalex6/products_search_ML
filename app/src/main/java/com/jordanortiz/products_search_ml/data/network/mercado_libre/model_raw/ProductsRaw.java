package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsRaw {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;
    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;
    @SerializedName("sold_quantity")
    @Expose
    private Integer soldQuantity;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("installments")
    @Expose
    private ProductInstallmentsRaw installments;
    @SerializedName("address")
    @Expose
    private ProductAddressRaw address;
    @SerializedName("shipping")
    @Expose
    private ProductShippingRaw shipping;
    @SerializedName("attributes")
    @Expose
    private List<ProductAttributeRaw> attributes = null;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("catalog_listing")
    @Expose
    private Boolean catalogListing;

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

    public ProductInstallmentsRaw getProductInstallmentsRaw() {
        return installments;
    }

    public void setProductInstallmentsRaw(ProductInstallmentsRaw installments) {
        this.installments = installments;
    }

    public ProductAddressRaw getProductAddressRaw() {
        return address;
    }

    public void setProductAddressRaw(ProductAddressRaw address) {
        this.address = address;
    }

    public ProductShippingRaw getProductShipping() {
        return shipping;
    }

    public void setProductShipping(ProductShippingRaw shipping) {
        this.shipping = shipping;
    }

    public List<ProductAttributeRaw> getProductAttributesRaws() {
        return attributes;
    }

    public void setProductAttributesRaws(List<ProductAttributeRaw> attributes) {
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

    public Boolean getCatalogListing() {
        return catalogListing;
    }

    public void setCatalogListing(Boolean catalogListing) {
        this.catalogListing = catalogListing;
    }
}
