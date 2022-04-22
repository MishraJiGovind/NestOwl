package com.nestowl.model;

import java.util.ArrayList;

import okhttp3.MultipartBody;

public class ProposalModal {
    String user_id,requirement_id,inventories,stage,providelater,verified_document_property,transaction_type,ownership,property_status,price,price_negotiable,booking_amount,
            booking_payment,final_payment,mode_of_payment,video_tour,property_meet,missing_requirement,meeting_date,meeting_time,chat_type,chat_number,
            possilbe_date,possilbe_time,market,bus_stop,metro,hospital,police_station,railways_station,local_facilty,client_verification,pick_and_drop,proper_document;
//    ArrayList<MultipartBody.Part>propertyimage1,propertyimage2;

    public ProposalModal() {
    }

    public ProposalModal(String user_id, String requirement_id, String inventories, String stage, String providelater, String verified_document_property, String transaction_type, String ownership, String property_status, String price, String price_negotiable, String booking_amount, String booking_payment, String final_payment, String mode_of_payment, String video_tour, String property_meet, String missing_requirement, String meeting_date, String meeting_time, String chat_type, String chat_number, String possilbe_date, String possilbe_time, String market, String bus_stop, String metro, String hospital, String police_station, String railways_station, String local_facilty, String client_verification, String pick_and_drop, String proper_document) {
        this.user_id = user_id;
        this.requirement_id = requirement_id;
        this.inventories = inventories;
        this.stage = stage;
        this.providelater = providelater;
        this.verified_document_property = verified_document_property;
        this.transaction_type = transaction_type;
        this.ownership = ownership;
        this.property_status = property_status;
        this.price = price;
        this.price_negotiable = price_negotiable;
        this.booking_amount = booking_amount;
        this.booking_payment = booking_payment;
        this.final_payment = final_payment;
        this.mode_of_payment = mode_of_payment;
        this.video_tour = video_tour;
        this.property_meet = property_meet;
        this.missing_requirement = missing_requirement;
        this.meeting_date = meeting_date;
        this.meeting_time = meeting_time;
        this.chat_type = chat_type;
        this.chat_number = chat_number;
        this.possilbe_date = possilbe_date;
        this.possilbe_time = possilbe_time;
        this.market = market;
        this.bus_stop = bus_stop;
        this.metro = metro;
        this.hospital = hospital;
        this.police_station = police_station;
        this.railways_station = railways_station;
        this.local_facilty = local_facilty;
        this.client_verification = client_verification;
        this.pick_and_drop = pick_and_drop;
        this.proper_document = proper_document;
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

    public String getInventories() {
        return inventories;
    }

    public void setInventories(String inventories) {
        this.inventories = inventories;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getProvidelater() {
        return providelater;
    }

    public void setProvidelater(String providelater) {
        this.providelater = providelater;
    }

    public String getVerified_document_property() {
        return verified_document_property;
    }

    public void setVerified_document_property(String verified_document_property) {
        this.verified_document_property = verified_document_property;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getProperty_status() {
        return property_status;
    }

    public void setProperty_status(String property_status) {
        this.property_status = property_status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice_negotiable() {
        return price_negotiable;
    }

    public void setPrice_negotiable(String price_negotiable) {
        this.price_negotiable = price_negotiable;
    }

    public String getBooking_amount() {
        return booking_amount;
    }

    public void setBooking_amount(String booking_amount) {
        this.booking_amount = booking_amount;
    }

    public String getBooking_payment() {
        return booking_payment;
    }

    public void setBooking_payment(String booking_payment) {
        this.booking_payment = booking_payment;
    }

    public String getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(String final_payment) {
        this.final_payment = final_payment;
    }

    public String getMode_of_payment() {
        return mode_of_payment;
    }

    public void setMode_of_payment(String mode_of_payment) {
        this.mode_of_payment = mode_of_payment;
    }

    public String getVideo_tour() {
        return video_tour;
    }

    public void setVideo_tour(String video_tour) {
        this.video_tour = video_tour;
    }

    public String getProperty_meet() {
        return property_meet;
    }

    public void setProperty_meet(String property_meet) {
        this.property_meet = property_meet;
    }

    public String getMissing_requirement() {
        return missing_requirement;
    }

    public void setMissing_requirement(String missing_requirement) {
        this.missing_requirement = missing_requirement;
    }

    public String getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(String meeting_date) {
        this.meeting_date = meeting_date;
    }

    public String getMeeting_time() {
        return meeting_time;
    }

    public void setMeeting_time(String meeting_time) {
        this.meeting_time = meeting_time;
    }

    public String getChat_type() {
        return chat_type;
    }

    public void setChat_type(String chat_type) {
        this.chat_type = chat_type;
    }

    public String getChat_number() {
        return chat_number;
    }

    public void setChat_number(String chat_number) {
        this.chat_number = chat_number;
    }

    public String getPossilbe_date() {
        return possilbe_date;
    }

    public void setPossilbe_date(String possilbe_date) {
        this.possilbe_date = possilbe_date;
    }

    public String getPossilbe_time() {
        return possilbe_time;
    }

    public void setPossilbe_time(String possilbe_time) {
        this.possilbe_time = possilbe_time;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getBus_stop() {
        return bus_stop;
    }

    public void setBus_stop(String bus_stop) {
        this.bus_stop = bus_stop;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPolice_station() {
        return police_station;
    }

    public void setPolice_station(String police_station) {
        this.police_station = police_station;
    }

    public String getRailways_station() {
        return railways_station;
    }

    public void setRailways_station(String railways_station) {
        this.railways_station = railways_station;
    }

    public String getLocal_facilty() {
        return local_facilty;
    }

    public void setLocal_facilty(String local_facilty) {
        this.local_facilty = local_facilty;
    }

    public String getClient_verification() {
        return client_verification;
    }

    public void setClient_verification(String client_verification) {
        this.client_verification = client_verification;
    }

    public String getPick_and_drop() {
        return pick_and_drop;
    }

    public void setPick_and_drop(String pick_and_drop) {
        this.pick_and_drop = pick_and_drop;
    }

    public String getProper_document() {
        return proper_document;
    }

    public void setProper_document(String proper_document) {
        this.proper_document = proper_document;
    }
}

