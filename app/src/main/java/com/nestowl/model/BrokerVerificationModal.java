package com.nestowl.model;

public class BrokerVerificationModal {
    String id,user_id,verification_type,verification,validities,verification_link,supporting_document,aadhar_number,aadhar_front,aadhar_back;

    public BrokerVerificationModal() {
    }

    public BrokerVerificationModal(String id, String user_id, String verification_type, String verification, String validities, String verification_link, String supporting_document, String aadhar_number, String aadhar_front, String aadhar_back) {
        this.id = id;
        this.user_id = user_id;
        this.verification_type = verification_type;
        this.verification = verification;
        this.validities = validities;
        this.verification_link = verification_link;
        this.supporting_document = supporting_document;
        this.aadhar_number = aadhar_number;
        this.aadhar_front = aadhar_front;
        this.aadhar_back = aadhar_back;
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

    public String getVerification_type() {
        return verification_type;
    }

    public void setVerification_type(String verification_type) {
        this.verification_type = verification_type;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getValidities() {
        return validities;
    }

    public void setValidities(String validities) {
        this.validities = validities;
    }

    public String getVerification_link() {
        return verification_link;
    }

    public void setVerification_link(String verification_link) {
        this.verification_link = verification_link;
    }

    public String getSupporting_document() {
        return supporting_document;
    }

    public void setSupporting_document(String supporting_document) {
        this.supporting_document = supporting_document;
    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public String getAadhar_front() {
        return aadhar_front;
    }

    public void setAadhar_front(String aadhar_front) {
        this.aadhar_front = aadhar_front;
    }

    public String getAadhar_back() {
        return aadhar_back;
    }

    public void setAadhar_back(String aadhar_back) {
        this.aadhar_back = aadhar_back;
    }
}
