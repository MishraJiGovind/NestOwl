package com.nestowl.model;

public class PropertyPreviewImagesModal {
    String imageurl,type;

    public PropertyPreviewImagesModal() {
    }

    public PropertyPreviewImagesModal(String imageurl, String type) {
        this.imageurl = imageurl;
        this.type = type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
