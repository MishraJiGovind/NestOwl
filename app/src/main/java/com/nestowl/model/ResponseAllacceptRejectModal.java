package com.nestowl.model;

import java.util.ArrayList;

public class ResponseAllacceptRejectModal {
    String status,message;
    ArrayList<AllAcceptedDataModal>Requirement_data,Property_data;

    public ResponseAllacceptRejectModal() {
    }

    public ResponseAllacceptRejectModal(String status, String message, ArrayList<AllAcceptedDataModal> requirement_data, ArrayList<AllAcceptedDataModal> property_data) {
        this.status = status;
        this.message = message;
        Requirement_data = requirement_data;
        Property_data = property_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<AllAcceptedDataModal> getRequirement_data() {
        return Requirement_data;
    }

    public void setRequirement_data(ArrayList<AllAcceptedDataModal> requirement_data) {
        Requirement_data = requirement_data;
    }

    public ArrayList<AllAcceptedDataModal> getProperty_data() {
        return Property_data;
    }

    public void setProperty_data(ArrayList<AllAcceptedDataModal> property_data) {
        Property_data = property_data;
    }
}

