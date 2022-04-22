package com.nestowl.model;

public class NestProBrokerModal {
    String user_id,owner_name,address,logo,profile_photo,expertise_in,transaction_type;

    public NestProBrokerModal() {
    }

    public NestProBrokerModal(String user_id, String owner_name, String address, String logo, String profile_photo, String expertise_in, String transaction_type) {
        this.user_id = user_id;
        this.owner_name = owner_name;
        this.address = address;
        this.logo = logo;
        this.profile_photo = profile_photo;
        this.expertise_in = expertise_in;
        this.transaction_type = transaction_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getExpertise_in() {
        return expertise_in;
    }

    public void setExpertise_in(String expertise_in) {
        this.expertise_in = expertise_in;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
}
