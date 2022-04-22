package com.nestowl.model;

public class NestProViewModal {
    String dp,name,addres,moto,extratext,userid,viewContact;

    public NestProViewModal() {
    }

    public NestProViewModal(String dp, String name, String addres, String moto, String extratext, String userid, String viewContact) {
        this.dp = dp;
        this.name = name;
        this.addres = addres;
        this.moto = moto;
        this.extratext = extratext;
        this.userid = userid;
        this.viewContact = viewContact;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getMoto() {
        return moto;
    }

    public void setMoto(String moto) {
        this.moto = moto;
    }

    public String getExtratext() {
        return extratext;
    }

    public void setExtratext(String extratext) {
        this.extratext = extratext;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getViewContact() {
        return viewContact;
    }

    public void setViewContact(String viewContact) {
        this.viewContact = viewContact;
    }
}
