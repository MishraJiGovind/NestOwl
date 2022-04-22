package com.nestowl.model;

public class LeadLiveContactModal {
    String id, user_id, lead_user_id, property_id, name, mobile, email, comment, tnc, status, updated_at;

    public LeadLiveContactModal() {
    }

    public LeadLiveContactModal(String id, String user_id, String lead_user_id, String property_id, String name, String mobile, String email, String comment, String tnc, String status, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.lead_user_id = lead_user_id;
        this.property_id = property_id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.comment = comment;
        this.tnc = tnc;
        this.status = status;
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

    public String getLead_user_id() {
        return lead_user_id;
    }

    public void setLead_user_id(String lead_user_id) {
        this.lead_user_id = lead_user_id;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
