package com.nestowl.model;

public class AlertStatusModal {
    String id,user_id,property_id,name,job,ongoingStatus,ownerStatus,viewType,bhk,propertyType;

    public AlertStatusModal() {
    }

    public AlertStatusModal(String id, String user_id, String property_id, String name, String job, String ongoingStatus, String ownerStatus, String viewType, String bhk, String propertyType) {
        this.id = id;
        this.user_id = user_id;
        this.property_id = property_id;
        this.name = name;
        this.job = job;
        this.ongoingStatus = ongoingStatus;
        this.ownerStatus = ownerStatus;
        this.viewType = viewType;
        this.bhk = bhk;
        this.propertyType = propertyType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOngoingStatus() {
        return ongoingStatus;
    }

    public void setOngoingStatus(String ongoingStatus) {
        this.ongoingStatus = ongoingStatus;
    }

    public String getOwnerStatus() {
        return ownerStatus;
    }

    public void setOwnerStatus(String ownerStatus) {
        this.ownerStatus = ownerStatus;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
