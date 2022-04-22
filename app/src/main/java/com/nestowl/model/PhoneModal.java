package com.nestowl.model;

public class PhoneModal {
    String phone,whatsapp;

    public PhoneModal() {
    }

    public PhoneModal(String phone, String whatsapp) {
        this.phone = phone;
        this.whatsapp = whatsapp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
