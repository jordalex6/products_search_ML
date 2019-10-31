package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

public class ListPagingModel {

    private Integer total;
    private Integer primaryResults;

    public ListPagingModel(Integer total, Integer primaryResults) {
        this.total = total;
        this.primaryResults = primaryResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(Integer primaryResults) {
        this.primaryResults = primaryResults;
    }
}
