package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {

    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("paging")
    @Expose
    private PagingRaw paging;
    @SerializedName("results")
    @Expose
    private List<ProductsRaw> results = null;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public PagingRaw getProductsPaging() {
        return paging;
    }

    public void setProductsPaging(PagingRaw paging) {
        this.paging = paging;
    }

    public List<ProductsRaw> getProductsRaws() {
        return results;
    }

    public void setProductsRaws(List<ProductsRaw> results) {
        this.results = results;
    }
}
