package com.nestowl.model;

public class BrokerOfficeModal {
    String id,user_id,state,city,locality,maps,address,address_type,occupancy_type,pincode,contact_person_name,company_name,company_website,phone,whatsapp;

    public BrokerOfficeModal() {
    }

    public BrokerOfficeModal(String id, String user_id, String state, String city, String locality, String maps, String address, String address_type, String occupancy_type, String pincode, String contact_person_name, String company_name, String company_website, String phone, String whatsapp) {
        this.id = id;
        this.user_id = user_id;
        this.state = state;
        this.city = city;
        this.locality = locality;
        this.maps = maps;
        this.address = address;
        this.address_type = address_type;
        this.occupancy_type = occupancy_type;
        this.pincode = pincode;
        this.contact_person_name = contact_person_name;
        this.company_name = company_name;
        this.company_website = company_website;
        this.phone = phone;
        this.whatsapp = whatsapp;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getOccupancy_type() {
        return occupancy_type;
    }

    public void setOccupancy_type(String occupancy_type) {
        this.occupancy_type = occupancy_type;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getContact_person_name() {
        return contact_person_name;
    }

    public void setContact_person_name(String contact_person_name) {
        this.contact_person_name = contact_person_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_website() {
        return company_website;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
}
