package com.nestowl.model;

public class PopupMannageModal {
    String status,postedto,id,catogory;

    public PopupMannageModal() {
    }

    public PopupMannageModal(String status, String postedto, String id, String catogory) {
        this.status = status;
        this.postedto = postedto;
        this.id = id;
        this.catogory = catogory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostedto() {
        return postedto;
    }

    public void setPostedto(String postedto) {
        this.postedto = postedto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatogory() {
        return catogory;
    }

    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }
}
