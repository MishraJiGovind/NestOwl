package com.nestowl.model;

import java.util.Date;

public class SubscriptionRemainModal {
    String name,price,type;
    Date date;

    public SubscriptionRemainModal() {
    }

    public SubscriptionRemainModal(String name, String price, String type, Date date) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
