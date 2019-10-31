package com.jordanortiz.products_search_ml.domain.model.product;

import java.util.List;

public class ProductsPagingEntity {

    private PagingEntity paging;
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
