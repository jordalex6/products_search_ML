package com.jordanortiz.products_search_ml.domain.model.product;

public class ProductAttributeEntity {

    private String id;
    private String name;
    private String valueName;
    private String attributeGroupName;
    private String attributeGroupId;

    public ProductAttributeEntity() {
    }

    public ProductAttributeEntity(String id, String name, String valueName,
                                  String attributeGroupName, String attributeGroupId)
    {
        this.id = id;
        this.name = name;
        this.valueName = valueName;
        this.attributeGroupName = attributeGroupName;
        this.attributeGroupId = attributeGroupId;
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

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }

    @Override
    public String toString() {
        return "ProductAttributeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", valueName='" + valueName + '\'' +
                ", attributeGroupName='" + attributeGroupName + '\'' +
                ", attributeGroupId='" + attributeGroupId + '\'' +
                '}';
    }
}
