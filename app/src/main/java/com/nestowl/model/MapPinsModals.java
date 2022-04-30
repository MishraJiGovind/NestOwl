package com.nestowl.model;

import java.util.ArrayList;

public class MapPinsModals {
    String name;
    ArrayList<MapPinModal> pins;

    public MapPinsModals() {
    }

    public MapPinsModals(String name, ArrayList<MapPinModal> pins) {
        this.name = name;
        this.pins = pins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MapPinModal> getPins() {
        return pins;
    }

    public void setPins(ArrayList<MapPinModal> pins) {
        this.pins = pins;
    }
}
