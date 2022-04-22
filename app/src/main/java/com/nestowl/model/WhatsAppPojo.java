package com.nestowl.model;

import java.io.Serializable;

public class WhatsAppPojo{
    String phone,whatsapp;

    public WhatsAppPojo() {
    }

    public WhatsAppPojo(String phone, String whatsapp) {
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
