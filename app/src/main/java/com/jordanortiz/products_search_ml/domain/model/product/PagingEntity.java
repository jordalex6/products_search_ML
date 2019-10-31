package com.jordanortiz.products_search_ml.domain.model.product;

public class PagingEntity {
    private Integer total;
    private Integer limit;
    private Integer primaryResults;

    public PagingEntity(Integer total, Integer limit, Integer primaryResults) {
        this.total = total;
        this.limit = limit;
        this.primaryResults = primaryResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(Integer primaryResults) {
        this.primaryResults = primaryResults;
    }
}
