package com.nestowl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OtpResponse implements Serializable {

    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("property_id")
    @Expose
    public String property_id;
    @SerializedName("data")
    @Expose
    public PersonalDetailsPOJO personalDetails;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public PersonalDetailsPOJO getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetailsPOJO personalDetails) {
        this.personalDetails = personalDetails;
    }
}
