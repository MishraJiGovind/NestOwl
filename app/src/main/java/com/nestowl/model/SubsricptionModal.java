package com.nestowl.model;

public class SubsricptionModal {
    String id,time,offer,price,line,line2,purchases,extraLine,type;

    public SubsricptionModal() {
    }

    public SubsricptionModal(String id, String time, String offer, String price, String line, String line2, String purchases, String extraLine, String type) {
        this.id = id;
        this.time = time;
        this.offer = offer;
        this.price = price;
        this.line = line;
        this.line2 = line2;
        this.purchases = purchases;
        this.extraLine = extraLine;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPurchases() {
        return purchases;
    }

    public void setPurchases(String purchases) {
        this.purchases = purchases;
    }

    public String getExtraLine() {
        return extraLine;
    }

    public void setExtraLine(String extraLine) {
        this.extraLine = extraLine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
