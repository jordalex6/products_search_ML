package com.jordanortiz.products_search_ml.data.network.mercado_libre.model_raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductAttributeRaw {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value_name")
    @Expose
    private String valueName;
    @SerializedName("attribute_group_name")
    @Expose
    private String attributeGroupName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("value_id")
    @Expose
    private String valueId;
    @SerializedName("attribute_group_id")
    @Expose
    private String attributeGroupId;
    @SerializedName("source")
    @Expose
    private Long source;


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

    public String getAttributeGroupName() {
        return attributeGroupName;
    }

    public void setAttributeGroupName(String attributeGroupName) {
        this.attributeGroupName = attributeGroupName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }


    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }
}
