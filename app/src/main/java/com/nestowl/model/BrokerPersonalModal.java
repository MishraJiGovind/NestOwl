package com.nestowl.model;

public class BrokerPersonalModal {
    String id,user_id,owner_name,gender,age,company_name,dealing,property_type,property,transaction_type,possession_status,operating_since,expertise_in;

    public BrokerPersonalModal() {
    }

    public BrokerPersonalModal(String id, String user_id, String owner_name, String gender, String age, String company_name, String dealing, String property_type, String property, String transaction_type, String possession_status, String operating_since, String expertise_in) {
        this.id = id;
        this.user_id = user_id;
        this.owner_name = owner_name;
        this.gender = gender;
        this.age = age;
        this.company_name = company_name;
        this.dealing = dealing;
        this.property_type = property_type;
        this.property = property;
        this.transaction_type = transaction_type;
        this.possession_status = possession_status;
        this.operating_since = operating_since;
        this.expertise_in = expertise_in;
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

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDealing() {
        return dealing;
    }

    public void setDealing(String dealing) {
        this.dealing = dealing;
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

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getPossession_status() {
        return possession_status;
    }

    public void setPossession_status(String possession_status) {
        this.possession_status = possession_status;
    }

    public String getOperating_since() {
        return operating_since;
    }

    public void setOperating_since(String operating_since) {
        this.operating_since = operating_since;
    }

    public String getExpertise_in() {
        return expertise_in;
    }

    public void setExpertise_in(String expertise_in) {
        this.expertise_in = expertise_in;
    }
}
