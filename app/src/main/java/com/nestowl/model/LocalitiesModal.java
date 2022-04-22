package com.nestowl.model;

public class LocalitiesModal {
    String localities,pincode;

    public LocalitiesModal() {
    }

    public LocalitiesModal(String localities, String pincode) {
        this.localities = localities;
        this.pincode = pincode;
    }

    public String getLocalities() {
        return localities;
    }

    public void setLocalities(String localities) {
        this.localities = localities;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
