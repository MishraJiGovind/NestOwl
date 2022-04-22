package com.nestowl.model;

public class LeadsAcceptRequrementsModal {
    String id,user_id,requirement_id,name,mobile,email,property_type,property,project_name,city,locality,sublocality,address,want_to,status,created_at,
            updated_at;

    public LeadsAcceptRequrementsModal() {
    }

    public LeadsAcceptRequrementsModal(String id, String user_id, String requirement_id, String name, String mobile, String email, String property_type, String property, String project_name, String city, String locality, String sublocality, String address, String want_to, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.requirement_id = requirement_id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.property_type = property_type;
        this.property = property;
        this.project_name = project_name;
        this.city = city;
        this.locality = locality;
        this.sublocality = sublocality;
        this.address = address;
        this.want_to = want_to;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getRequirement_id() {
        return requirement_id;
    }

    public void setRequirement_id(String requirement_id) {
        this.requirement_id = requirement_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getSublocality() {
        return sublocality;
    }

    public void setSublocality(String sublocality) {
        this.sublocality = sublocality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWant_to() {
        return want_to;
    }

    public void setWant_to(String want_to) {
        this.want_to = want_to;
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

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
