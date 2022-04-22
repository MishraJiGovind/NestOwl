package com.nestowl.model;

import android.content.Context;

public class PaytmModal {
    String oderId,mid,token,ammount,callbackurl;

    public PaytmModal() {
    }

    public PaytmModal(String oderId, String mid, String token, String ammount, String callbackurl) {
        this.oderId = oderId;
        this.mid = mid;
        this.token = token;
        this.ammount = ammount;
        this.callbackurl = callbackurl;
    }

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }
}
