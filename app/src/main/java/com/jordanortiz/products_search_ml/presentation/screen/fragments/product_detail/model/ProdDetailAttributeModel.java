package com.jordanortiz.products_search_ml.presentation.screen.fragments.product_detail.model;

public class ProdDetailAttributeModel {

    private Long source;
    private String name;
    private String valueName;

    public ProdDetailAttributeModel(Long source, String name, String valueName) {
        this.source = source;
        this.name = name;
        this.valueName = valueName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }
}
