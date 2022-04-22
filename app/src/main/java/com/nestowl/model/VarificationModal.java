package com.nestowl.model;

public class VarificationModal {
    String id,user_id,property_id,verification_name,verification_photo,verification_name_or,verification_photo_or_front,verification_photo_or_back,approval_date;

    public VarificationModal() {
    }

    public VarificationModal(String id, String user_id, String property_id, String verification_name, String verification_photo, String verification_name_or, String verification_photo_or_front, String verification_photo_or_back, String approval_date) {
        this.id = id;
        this.user_id = user_id;
        this.property_id = property_id;
        this.verification_name = verification_name;
        this.verification_photo = verification_photo;
        this.verification_name_or = verification_name_or;
        this.verification_photo_or_front = verification_photo_or_front;
        this.verification_photo_or_back = verification_photo_or_back;
        this.approval_date = approval_date;
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

    public String getVerification_name() {
        return verification_name;
    }

    public void setVerification_name(String verification_name) {
        this.verification_name = verification_name;
    }

    public String getVerification_photo() {
        return verification_photo;
    }

    public void setVerification_photo(String verification_photo) {
        this.verification_photo = verification_photo;
    }

    public String getVerification_name_or() {
        return verification_name_or;
    }

    public void setVerification_name_or(String verification_name_or) {
        this.verification_name_or = verification_name_or;
    }

    public String getVerification_photo_or_front() {
        return verification_photo_or_front;
    }

    public void setVerification_photo_or_front(String verification_photo_or_front) {
        this.verification_photo_or_front = verification_photo_or_front;
    }

    public String getVerification_photo_or_back() {
        return verification_photo_or_back;
    }

    public void setVerification_photo_or_back(String verification_photo_or_back) {
        this.verification_photo_or_back = verification_photo_or_back;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
    }
}
