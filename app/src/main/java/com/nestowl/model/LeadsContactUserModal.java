package com.nestowl.model;

public class LeadsContactUserModal {
    String id,user_id,lead_user_id,property_id,type,name,mobile,email,comment,tnc,status,created_at,updated_at;

    public LeadsContactUserModal() {
    }

    public LeadsContactUserModal(String id, String user_id, String lead_user_id, String property_id, String type, String name, String mobile, String email, String comment, String tnc, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.lead_user_id = lead_user_id;
        this.property_id = property_id;
        this.type = type;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.comment = comment;
        this.tnc = tnc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
