package com.nestowl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DropdownPOJO implements Serializable {

    @SerializedName("value")
    @Expose
    public String value;
    @SerializedName("state_code")
    @Expose
    public String state_code;
    @SerializedName("state_id")
    @Expose
    public String state_id;
    @SerializedName("id")
    @Expose
    public String id;



}
