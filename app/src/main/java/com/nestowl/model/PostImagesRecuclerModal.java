package com.nestowl.model;

import android.net.Uri;

public class PostImagesRecuclerModal {
    String imageFrom,setAscover,isFromServer,image,id,propertyId;
    Uri imageLink;

    public PostImagesRecuclerModal() {
    }

    public PostImagesRecuclerModal(String imageFrom, String setAscover, String isFromServer, String image, String id, String propertyId, Uri imageLink) {
        this.imageFrom = imageFrom;
        this.setAscover = setAscover;
        this.isFromServer = isFromServer;
        this.image = image;
        this.id = id;
        this.propertyId = propertyId;
        this.imageLink = imageLink;
    }

    public String getImageFrom() {
        return imageFrom;
    }

    public void setImageFrom(String imageFrom) {
        this.imageFrom = imageFrom;
    }

    public String getSetAscover() {
        return setAscover;
    }

    public void setSetAscover(String setAscover) {
        this.setAscover = setAscover;
    }

    public String getIsFromServer() {
        return isFromServer;
    }

    public void setIsFromServer(String isFromServer) {
        this.isFromServer = isFromServer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Uri getImageLink() {
        return imageLink;
    }

    public void setImageLink(Uri imageLink) {
        this.imageLink = imageLink;
    }
}
