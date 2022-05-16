package com.nestowl.model;

public class MyresponeViewModal {
    String id,user_id,property_id,name,type,bhk,city,budget,sq,time,lead_user_id;

    public MyresponeViewModal() {
    }

    public MyresponeViewModal(String id, String user_id, String property_id, String name, String type, String bhk, String city, String budget, String sq, String time, String lead_user_id) {
        this.id = id;
        this.user_id = user_id;
        this.property_id = property_id;
        this.name = name;
        this.type = type;
        this.bhk = bhk;
        this.city = city;
        this.budget = budget;
        this.sq = sq;
        this.time = time;
        this.lead_user_id = lead_user_id;
    }

    public String getLead_user_id() {
        return lead_user_id;
    }

    public void setLead_user_id(String lead_user_id) {
        this.lead_user_id = lead_user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }
}
