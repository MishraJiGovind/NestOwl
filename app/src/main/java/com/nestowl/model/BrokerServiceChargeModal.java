package com.nestowl.model;

public class BrokerServiceChargeModal {
    String id,user_id,property_type,price_of_property,service_charge,customer_education,customer_education_doc,any_extra_achivement,any_extra_achivement_doc,site_visit,vehicle_type_you_own,loan_facility,
            registery_facility,customer_verification;

    public BrokerServiceChargeModal() {
    }

    public BrokerServiceChargeModal(String id, String user_id, String property_type, String price_of_property, String service_charge, String customer_education, String customer_education_doc, String any_extra_achivement, String any_extra_achivement_doc, String site_visit, String vehicle_type_you_own, String loan_facility, String registery_facility, String customer_verification) {
        this.id = id;
        this.user_id = user_id;
        this.property_type = property_type;
        this.price_of_property = price_of_property;
        this.service_charge = service_charge;
        this.customer_education = customer_education;
        this.customer_education_doc = customer_education_doc;
        this.any_extra_achivement = any_extra_achivement;
        this.any_extra_achivement_doc = any_extra_achivement_doc;
        this.site_visit = site_visit;
        this.vehicle_type_you_own = vehicle_type_you_own;
        this.loan_facility = loan_facility;
        this.registery_facility = registery_facility;
        this.customer_verification = customer_verification;
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

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getPrice_of_property() {
        return price_of_property;
    }

    public void setPrice_of_property(String price_of_property) {
        this.price_of_property = price_of_property;
    }

    public String getService_charge() {
        return service_charge;
    }

    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public String getCustomer_education() {
        return customer_education;
    }

    public void setCustomer_education(String customer_education) {
        this.customer_education = customer_education;
    }

    public String getCustomer_education_doc() {
        return customer_education_doc;
    }

    public void setCustomer_education_doc(String customer_education_doc) {
        this.customer_education_doc = customer_education_doc;
    }

    public String getAny_extra_achivement() {
        return any_extra_achivement;
    }

    public void setAny_extra_achivement(String any_extra_achivement) {
        this.any_extra_achivement = any_extra_achivement;
    }

    public String getAny_extra_achivement_doc() {
        return any_extra_achivement_doc;
    }

    public void setAny_extra_achivement_doc(String any_extra_achivement_doc) {
        this.any_extra_achivement_doc = any_extra_achivement_doc;
    }

    public String getSite_visit() {
        return site_visit;
    }

    public void setSite_visit(String site_visit) {
        this.site_visit = site_visit;
    }

    public String getVehicle_type_you_own() {
        return vehicle_type_you_own;
    }

    public void setVehicle_type_you_own(String vehicle_type_you_own) {
        this.vehicle_type_you_own = vehicle_type_you_own;
    }

    public String getLoan_facility() {
        return loan_facility;
    }

    public void setLoan_facility(String loan_facility) {
        this.loan_facility = loan_facility;
    }

    public String getRegistery_facility() {
        return registery_facility;
    }

    public void setRegistery_facility(String registery_facility) {
        this.registery_facility = registery_facility;
    }

    public String getCustomer_verification() {
        return customer_verification;
    }

    public void setCustomer_verification(String customer_verification) {
        this.customer_verification = customer_verification;
    }
}
