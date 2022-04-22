package com.nestowl.model;

public class LocalitiesDioModal {
    String name, selected;

    public LocalitiesDioModal() {

    }

    public LocalitiesDioModal(String name, String selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
