package com.nestowl.model;

public class PropertyViewModal {
    String imageUrl,status,id,price,rentstatus,address,proId;

    public PropertyViewModal() {
    }

    public PropertyViewModal(String imageUrl, String status, String id, String price, String rentstatus, String address, String proId) {
        this.imageUrl = imageUrl;
        this.status = status;
        this.id = id;
        this.price = price;
        this.rentstatus = rentstatus;
        this.address = address;
        this.proId = proId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRentstatus() {
        return rentstatus;
    }

    public void setRentstatus(String rentstatus) {
        this.rentstatus = rentstatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
