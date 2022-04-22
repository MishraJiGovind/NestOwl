package com.nestowl.model;

public class MyPropertyViewModal {
    String property_id,featurd,fav,liveStatus,backimage,price,address,locality,readytoMov,resale,FT,owner,propertyPostedRank,isSumbited;

    public MyPropertyViewModal() {
    }

    public MyPropertyViewModal(String property_id, String featurd, String fav, String liveStatus, String backimage, String price, String address, String locality, String readytoMov, String resale, String FT, String owner, String propertyPostedRank, String isSumbited) {
        this.property_id = property_id;
        this.featurd = featurd;
        this.fav = fav;
        this.liveStatus = liveStatus;
        this.backimage = backimage;
        this.price = price;
        this.address = address;
        this.locality = locality;
        this.readytoMov = readytoMov;
        this.resale = resale;
        this.FT = FT;
        this.owner = owner;
        this.propertyPostedRank = propertyPostedRank;
        this.isSumbited = isSumbited;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getFeaturd() {
        return featurd;
    }

    public void setFeaturd(String featurd) {
        this.featurd = featurd;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus;
    }

    public String getBackimage() {
        return backimage;
    }

    public void setBackimage(String backimage) {
        this.backimage = backimage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getReadytoMov() {
        return readytoMov;
    }

    public void setReadytoMov(String readytoMov) {
        this.readytoMov = readytoMov;
    }

    public String getResale() {
        return resale;
    }

    public void setResale(String resale) {
        this.resale = resale;
    }

    public String getFT() {
        return FT;
    }

    public void setFT(String FT) {
        this.FT = FT;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPropertyPostedRank() {
        return propertyPostedRank;
    }

    public void setPropertyPostedRank(String propertyPostedRank) {
        this.propertyPostedRank = propertyPostedRank;
    }

    public String getIsSumbited() {
        return isSumbited;
    }

    public void setIsSumbited(String isSumbited) {
        this.isSumbited = isSumbited;
    }
}
