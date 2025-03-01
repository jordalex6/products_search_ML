package com.jordanortiz.products_search_ml.domain.model.product;

public class SellerAddressElementEntity {
    private String id;
    private String name;

    public SellerAddressElementEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
