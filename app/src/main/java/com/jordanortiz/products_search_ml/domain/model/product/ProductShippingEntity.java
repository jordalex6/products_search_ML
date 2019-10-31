package com.jordanortiz.products_search_ml.domain.model.product;

public class ProductShippingEntity {

    private Boolean freeShipping;

    public ProductShippingEntity(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
}
