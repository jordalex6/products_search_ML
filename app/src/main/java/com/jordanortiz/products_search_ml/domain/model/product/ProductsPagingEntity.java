package com.jordanortiz.products_search_ml.domain.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsPagingEntity {
    @SerializedName("paging")
    @Expose
    private PagingEntity paging;
    @SerializedName("product_list")
    @Expose
    private List<ProductEntity> productList;

    public PagingEntity getPaging() {
        return paging;
    }

    public void setPaging(PagingEntity paging) {
        this.paging = paging;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }
}
