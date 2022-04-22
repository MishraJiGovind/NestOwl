package com.nestowl.model;

import java.util.ArrayList;

public class StringArreyModal {
    ArrayList<String> amenities;

    public StringArreyModal() {
    }

    public StringArreyModal(ArrayList<String> amenities) {
        this.amenities = amenities;
    }

    public ArrayList<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<String> amenities) {
        this.amenities = amenities;
    }
}
