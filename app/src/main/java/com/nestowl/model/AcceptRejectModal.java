package com.nestowl.model;

public class AcceptRejectModal {
    String  id,user_id,type,pro_id,interest,accepts,reject,accept_date,deal,deal_date,submittedproposal,submittedproposal_date,acceptedproposal,
            acceptedproposal_date,client_acccept,client_acccept_date,status,created_at;

    public AcceptRejectModal() {
    }

    public AcceptRejectModal(String id, String user_id, String type, String pro_id, String interest, String accepts, String reject, String accept_date, String deal, String deal_date, String submittedproposal, String submittedproposal_date, String acceptedproposal, String acceptedproposal_date, String client_acccept, String client_acccept_date, String status, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.pro_id = pro_id;
        this.interest = interest;
        this.accepts = accepts;
        this.reject = reject;
        this.accept_date = accept_date;
        this.deal = deal;
        this.deal_date = deal_date;
        this.submittedproposal = submittedproposal;
        this.submittedproposal_date = submittedproposal_date;
        this.acceptedproposal = acceptedproposal;
        this.acceptedproposal_date = acceptedproposal_date;
        this.client_acccept = client_acccept;
        this.client_acccept_date = client_acccept_date;
        this.status = status;
        this.created_at = created_at;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAccepts() {
        return accepts;
    }

    public void setAccepts(String accepts) {
        this.accepts = accepts;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(String accept_date) {
        this.accept_date = accept_date;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getDeal_date() {
        return deal_date;
    }

    public void setDeal_date(String deal_date) {
        this.deal_date = deal_date;
    }

    public String getSubmittedproposal() {
        return submittedproposal;
    }

    public void setSubmittedproposal(String submittedproposal) {
        this.submittedproposal = submittedproposal;
    }

    public String getSubmittedproposal_date() {
        return submittedproposal_date;
    }

    public void setSubmittedproposal_date(String submittedproposal_date) {
        this.submittedproposal_date = submittedproposal_date;
    }

    public String getAcceptedproposal() {
        return acceptedproposal;
    }

    public void setAcceptedproposal(String acceptedproposal) {
        this.acceptedproposal = acceptedproposal;
    }

    public String getAcceptedproposal_date() {
        return acceptedproposal_date;
    }

    public void setAcceptedproposal_date(String acceptedproposal_date) {
        this.acceptedproposal_date = acceptedproposal_date;
    }

    public String getClient_acccept() {
        return client_acccept;
    }

    public void setClient_acccept(String client_acccept) {
        this.client_acccept = client_acccept;
    }

    public String getClient_acccept_date() {
        return client_acccept_date;
    }

    public void setClient_acccept_date(String client_acccept_date) {
        this.client_acccept_date = client_acccept_date;
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
}
