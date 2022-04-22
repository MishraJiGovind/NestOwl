package com.nestowl.model;

public class EditPropertyModal {
    String PropertyType,PropertyCategory,id;

    public EditPropertyModal() {
    }

    public EditPropertyModal(String propertyType, String propertyCategory, String id) {
        PropertyType = propertyType;
        PropertyCategory = propertyCategory;
        this.id = id;
    }

    public String getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(String propertyType) {
        PropertyType = propertyType;
    }

    public String getPropertyCategory() {
        return PropertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        PropertyCategory = propertyCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
