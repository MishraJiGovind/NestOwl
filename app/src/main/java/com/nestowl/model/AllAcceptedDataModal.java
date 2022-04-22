package com.nestowl.model;

public class AllAcceptedDataModal {
    String id,user_id,pro_id,accepts,reject,type,submittedproposal,acceptedproposal,client_acccept,acceptedproposal_date,client_acccept_date;

    public AllAcceptedDataModal() {
    }

    public AllAcceptedDataModal(String id, String user_id, String pro_id, String accepts, String reject, String type, String submittedproposal, String acceptedproposal, String client_acccept, String acceptedproposal_date, String client_acccept_date) {
        this.id = id;
        this.user_id = user_id;
        this.pro_id = pro_id;
        this.accepts = accepts;
        this.reject = reject;
        this.type = type;
        this.submittedproposal = submittedproposal;
        this.acceptedproposal = acceptedproposal;
        this.client_acccept = client_acccept;
        this.acceptedproposal_date = acceptedproposal_date;
        this.client_acccept_date = client_acccept_date;
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

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubmittedproposal() {
        return submittedproposal;
    }

    public void setSubmittedproposal(String submittedproposal) {
        this.submittedproposal = submittedproposal;
    }

    public String getAcceptedproposal() {
        return acceptedproposal;
    }

    public void setAcceptedproposal(String acceptedproposal) {
        this.acceptedproposal = acceptedproposal;
    }

    public String getClient_acccept() {
        return client_acccept;
    }

    public void setClient_acccept(String client_acccept) {
        this.client_acccept = client_acccept;
    }

    public String getAcceptedproposal_date() {
        return acceptedproposal_date;
    }

    public void setAcceptedproposal_date(String acceptedproposal_date) {
        this.acceptedproposal_date = acceptedproposal_date;
    }

    public String getClient_acccept_date() {
        return client_acccept_date;
    }

    public void setClient_acccept_date(String client_acccept_date) {
        this.client_acccept_date = client_acccept_date;
    }
}
