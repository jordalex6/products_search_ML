package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

import java.util.List;

public class ProductListModel {

    private ListPagingModel paging;
    private List<ProductModel> productList;

    public ListPagingModel getPaging() {
        return paging;
    }

    public void setPaging(ListPagingModel paging) {
        this.paging = paging;
    }

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }
}
