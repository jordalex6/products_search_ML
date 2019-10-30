package com.jordanortiz.products_search_ml.presentation.screen.fragments.products_list.model;

public class ProductAttributeModel {

    private String name;
    private String valueName;

    public ProductAttributeModel(String name, String valueName) {
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

}
