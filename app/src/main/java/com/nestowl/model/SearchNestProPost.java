package com.nestowl.model;

import java.util.ArrayList;

public class SearchNestProPost {
    String city,locality,rera,property_type,pincode;


    public SearchNestProPost() {
    }

    public SearchNestProPost(String city, String locality, String rera, String property_type, String pincode) {
        this.city = city;
        this.locality = locality;
        this.rera = rera;
        this.property_type = property_type;
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRera() {
        return rera;
    }

    public void setRera(String rera) {
        this.rera = rera;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
