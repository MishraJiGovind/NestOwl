package com.nestowl.model;

public class PropertyListModal {
    String id,user_id,property_id,looking,propertytype,property,city,project_name,locality,sublocality,address,land_zone,special_zone,p_type,approval_date,status;

    public PropertyListModal() {
    }

    public PropertyListModal(String id, String user_id, String property_id, String looking, String propertytype, String property, String city, String project_name, String locality, String sublocality, String address, String land_zone, String special_zone, String p_type, String approval_date, String status) {
        this.id = id;
        this.user_id = user_id;
        this.property_id = property_id;
        this.looking = looking;
        this.propertytype = propertytype;
        this.property = property;
        this.city = city;
        this.project_name = project_name;
        this.locality = locality;
        this.sublocality = sublocality;
        this.address = address;
        this.land_zone = land_zone;
        this.special_zone = special_zone;
        this.p_type = p_type;
        this.approval_date = approval_date;
        this.status = status;
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

    public String getLooking() {
        return looking;
    }

    public void setLooking(String looking) {
        this.looking = looking;
    }

    public String getPropertytype() {
        return propertytype;
    }

    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getLand_zone() {
        return land_zone;
    }

    public void setLand_zone(String land_zone) {
        this.land_zone = land_zone;
    }

    public String getSpecial_zone() {
        return special_zone;
    }

    public void setSpecial_zone(String special_zone) {
        this.special_zone = special_zone;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
