package com.nestowl.model;

public class AmentiesListModal {
    String text,image;

    public AmentiesListModal() {
    }

    public AmentiesListModal(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
