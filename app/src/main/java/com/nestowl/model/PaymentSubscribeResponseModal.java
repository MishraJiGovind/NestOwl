package com.nestowl.model;

public class PaymentSubscribeResponseModal {
    String status,message,OrderID,Price;
    User User;

    public PaymentSubscribeResponseModal() {
    }

    public PaymentSubscribeResponseModal(String status, String message, String orderID, String price, com.nestowl.model.User user) {
        this.status = status;
        this.message = message;
        OrderID = orderID;
        Price = price;
        User = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public com.nestowl.model.User getUser() {
        return User;
    }

    public void setUser(com.nestowl.model.User user) {
        User = user;
    }
}
