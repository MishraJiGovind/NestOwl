package com.nestowl.model;

public class ProposalPropertyModal {
    String user_id,property_id,number_of_buyers,stage,deal_to_happen,verified_buyer,negotiate,price,discuss_with_owner,booking_payment,final_payment,transaction,meeting_days,meeting_time,loan_facilty,client_verification,faciltyproper_document;

    public ProposalPropertyModal() {
    }

    public ProposalPropertyModal(String user_id, String property_id, String number_of_buyers, String stage, String deal_to_happen, String verified_buyer, String negotiate, String price, String discuss_with_owner, String booking_payment, String final_payment, String transaction, String meeting_days, String meeting_time, String loan_facilty, String client_verification, String faciltyproper_document) {
        this.user_id = user_id;
        this.property_id = property_id;
        this.number_of_buyers = number_of_buyers;
        this.stage = stage;
        this.deal_to_happen = deal_to_happen;
        this.verified_buyer = verified_buyer;
        this.negotiate = negotiate;
        this.price = price;
        this.discuss_with_owner = discuss_with_owner;
        this.booking_payment = booking_payment;
        this.final_payment = final_payment;
        this.transaction = transaction;
        this.meeting_days = meeting_days;
        this.meeting_time = meeting_time;
        this.loan_facilty = loan_facilty;
        this.client_verification = client_verification;
        this.faciltyproper_document = faciltyproper_document;
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

    public String getNumber_of_buyers() {
        return number_of_buyers;
    }

    public void setNumber_of_buyers(String number_of_buyers) {
        this.number_of_buyers = number_of_buyers;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDeal_to_happen() {
        return deal_to_happen;
    }

    public void setDeal_to_happen(String deal_to_happen) {
        this.deal_to_happen = deal_to_happen;
    }

    public String getVerified_buyer() {
        return verified_buyer;
    }

    public void setVerified_buyer(String verified_buyer) {
        this.verified_buyer = verified_buyer;
    }

    public String getNegotiate() {
        return negotiate;
    }

    public void setNegotiate(String negotiate) {
        this.negotiate = negotiate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscuss_with_owner() {
        return discuss_with_owner;
    }

    public void setDiscuss_with_owner(String discuss_with_owner) {
        this.discuss_with_owner = discuss_with_owner;
    }

    public String getBooking_payment() {
        return booking_payment;
    }

    public void setBooking_payment(String booking_payment) {
        this.booking_payment = booking_payment;
    }

    public String getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(String final_payment) {
        this.final_payment = final_payment;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getMeeting_days() {
        return meeting_days;
    }

    public void setMeeting_days(String meeting_days) {
        this.meeting_days = meeting_days;
    }

    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(String meeting_time) {
        this.meeting_time = meeting_time;
    }

    public String getLoan_facilty() {
        return loan_facilty;
    }

    public void setLoan_facilty(String loan_facilty) {
        this.loan_facilty = loan_facilty;
    }

    public String getClient_verification() {
        return client_verification;
    }

    public void setClient_verification(String client_verification) {
        this.client_verification = client_verification;
    }

    public String getFaciltyproper_document() {
        return faciltyproper_document;
    }

    public void setFaciltyproper_document(String faciltyproper_document) {
        this.faciltyproper_document = faciltyproper_document;
    }
}
