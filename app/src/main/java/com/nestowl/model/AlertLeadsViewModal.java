package com.nestowl.model;

public class AlertLeadsViewModal {
    String id,user_id,property_id,name,bhk,projectname,budget,sq,doctor,status,city,unlocking,time,address;
    boolean isAccepted, isProposalSummibted, isProposalAccepted,isType,isDubbleLead;

    public AlertLeadsViewModal() {
    }

    public AlertLeadsViewModal(String id, String user_id, String property_id, String name, String bhk, String projectname, String budget, String sq, String doctor, String status, String city, String unlocking, String time, String address, boolean isAccepted, boolean isProposalSummibted, boolean isProposalAccepted, boolean isType, boolean isDubbleLead) {
        this.id = id;
        this.user_id = user_id;
        this.property_id = property_id;
        this.name = name;
        this.bhk = bhk;
        this.projectname = projectname;
        this.budget = budget;
        this.sq = sq;
        this.doctor = doctor;
        this.status = status;
        this.city = city;
        this.unlocking = unlocking;
        this.time = time;
        this.address = address;
        this.isAccepted = isAccepted;
        this.isProposalSummibted = isProposalSummibted;
        this.isProposalAccepted = isProposalAccepted;
        this.isType = isType;
        this.isDubbleLead = isDubbleLead;
    }

    public boolean isDubbleLead() {
        return isDubbleLead;
    }

    public void setDubbleLead(boolean dubbleLead) {
        isDubbleLead = dubbleLead;
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

    public String getBhk() {
        return bhk;
    }

    public void setBhk(String bhk) {
        this.bhk = bhk;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSq() {
        return sq;
    }

    public void setSq(String sq) {
        this.sq = sq;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUnlocking() {
        return unlocking;
    }

    public void setUnlocking(String unlocking) {
        this.unlocking = unlocking;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isProposalSummibted() {
        return isProposalSummibted;
    }

    public void setProposalSummibted(boolean proposalSummibted) {
        isProposalSummibted = proposalSummibted;
    }

    public boolean isProposalAccepted() {
        return isProposalAccepted;
    }

    public void setProposalAccepted(boolean proposalAccepted) {
        isProposalAccepted = proposalAccepted;
    }

    public boolean isType() {
        return isType;
    }

    public void setType(boolean type) {
        isType = type;
    }
}
