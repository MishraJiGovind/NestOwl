package com.nestowl.model;

public class PaymentDetailsModal {
    String id,user_id,payment_method,payment_username,payment_price,payment_status,payer_name,payment_address,payment_address1,payer_city,payer_state,payer_country,payer_zip,payer_email,payer_mobile,payment_trnid,
            order_id,currency,status,created_at,updated_at;

    public PaymentDetailsModal() {
    }

    public PaymentDetailsModal(String id, String user_id, String payment_method, String payment_username, String payment_price, String payment_status, String payer_name, String payment_address, String payment_address1, String payer_city, String payer_state, String payer_country, String payer_zip, String payer_email, String payer_mobile, String payment_trnid, String order_id, String currency, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.payment_method = payment_method;
        this.payment_username = payment_username;
        this.payment_price = payment_price;
        this.payment_status = payment_status;
        this.payer_name = payer_name;
        this.payment_address = payment_address;
        this.payment_address1 = payment_address1;
        this.payer_city = payer_city;
        this.payer_state = payer_state;
        this.payer_country = payer_country;
        this.payer_zip = payer_zip;
        this.payer_email = payer_email;
        this.payer_mobile = payer_mobile;
        this.payment_trnid = payment_trnid;
        this.order_id = order_id;
        this.currency = currency;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPayment_username() {
        return payment_username;
    }

    public void setPayment_username(String payment_username) {
        this.payment_username = payment_username;
    }

    public String getPayment_price() {
        return payment_price;
    }

    public void setPayment_price(String payment_price) {
        this.payment_price = payment_price;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getPayment_address() {
        return payment_address;
    }

    public void setPayment_address(String payment_address) {
        this.payment_address = payment_address;
    }

    public String getPayment_address1() {
        return payment_address1;
    }

    public void setPayment_address1(String payment_address1) {
        this.payment_address1 = payment_address1;
    }

    public String getPayer_city() {
        return payer_city;
    }

    public void setPayer_city(String payer_city) {
        this.payer_city = payer_city;
    }

    public String getPayer_state() {
        return payer_state;
    }

    public void setPayer_state(String payer_state) {
        this.payer_state = payer_state;
    }

    public String getPayer_country() {
        return payer_country;
    }

    public void setPayer_country(String payer_country) {
        this.payer_country = payer_country;
    }

    public String getPayer_zip() {
        return payer_zip;
    }

    public void setPayer_zip(String payer_zip) {
        this.payer_zip = payer_zip;
    }

    public String getPayer_email() {
        return payer_email;
    }

    public void setPayer_email(String payer_email) {
        this.payer_email = payer_email;
    }

    public String getPayer_mobile() {
        return payer_mobile;
    }

    public void setPayer_mobile(String payer_mobile) {
        this.payer_mobile = payer_mobile;
    }

    public String getPayment_trnid() {
        return payment_trnid;
    }

    public void setPayment_trnid(String payment_trnid) {
        this.payment_trnid = payment_trnid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
