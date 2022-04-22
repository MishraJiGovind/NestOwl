package com.nestowl.model;

public class LocalitiesViewModal {
    String name,pincode;
    boolean isAdded;

    public LocalitiesViewModal() {
    }

    public LocalitiesViewModal(String name, String pincode, boolean isAdded) {
        this.name = name;
        this.pincode = pincode;
        this.isAdded = isAdded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }
}
