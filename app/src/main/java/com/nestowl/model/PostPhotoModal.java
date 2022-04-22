package com.nestowl.model;

import java.io.File;
import java.util.ArrayList;

public class PostPhotoModal {
    String user_id,property_id,post_property,hide_contact_details,schedule_details_type,prefference_day,status,
            prefference_time,whats_app,jio_meet,skype,zoom,google_duo,facetime,properypost;
    ArrayList<File> site_view,common_area,washroom_view,exterior_view,living_room,bedrooms,bathrooms,kitchen,
            floor_plan,master_plan,location_map,others;
    File brochure;
    ArrayList<String>bank;

    public PostPhotoModal() {
    }

    public PostPhotoModal(String user_id, String property_id, String post_property,String status, String hide_contact_details, String schedule_details_type, String prefference_day, String prefference_time, String whats_app, String jio_meet, String skype, String zoom, String google_duo, String facetime, String properypost, ArrayList<File> site_view, ArrayList<File> common_area, ArrayList<File> washroom_view, ArrayList<File> exterior_view, ArrayList<File> living_room, ArrayList<File> bedrooms, ArrayList<File> bathrooms, ArrayList<File> kitchen, ArrayList<File> floor_plan, ArrayList<File> master_plan, ArrayList<File> location_map, ArrayList<File> others, File brochure, ArrayList<String> bank) {
        this.user_id = user_id;
        this.property_id = property_id;
        this.post_property = post_property;
        this.hide_contact_details = hide_contact_details;
        this.schedule_details_type = schedule_details_type;
        this.prefference_day = prefference_day;
        this.prefference_time = prefference_time;
        this.whats_app = whats_app;
        this.jio_meet = jio_meet;
        this.skype = skype;
        this.zoom = zoom;
        this.google_duo = google_duo;
        this.facetime = facetime;
        this.properypost = properypost;
        this.site_view = site_view;
        this.common_area = common_area;
        this.washroom_view = washroom_view;
        this.exterior_view = exterior_view;
        this.living_room = living_room;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.kitchen = kitchen;
        this.floor_plan = floor_plan;
        this.master_plan = master_plan;
        this.location_map = location_map;
        this.others = others;
        this.brochure = brochure;
        this.bank = bank;
        this.status = status;
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

    public String getPost_property() {
        return post_property;
    }

    public void setPost_property(String post_property) {
        this.post_property = post_property;
    }

    public String getHide_contact_details() {
        return hide_contact_details;
    }

    public void setHide_contact_details(String hide_contact_details) {
        this.hide_contact_details = hide_contact_details;
    }

    public String getSchedule_details_type() {
        return schedule_details_type;
    }

    public void setSchedule_details_type(String schedule_details_type) {
        this.schedule_details_type = schedule_details_type;
    }

    public String getPrefference_day() {
        return prefference_day;
    }

    public void setPrefference_day(String prefference_day) {
        this.prefference_day = prefference_day;
    }

    public String getPrefference_time() {
        return prefference_time;
    }

    public void setPrefference_time(String prefference_time) {
        this.prefference_time = prefference_time;
    }

    public String getWhats_app() {
        return whats_app;
    }

    public void setWhats_app(String whats_app) {
        this.whats_app = whats_app;
    }

    public String getJio_meet() {
        return jio_meet;
    }

    public void setJio_meet(String jio_meet) {
        this.jio_meet = jio_meet;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getGoogle_duo() {
        return google_duo;
    }

    public void setGoogle_duo(String google_duo) {
        this.google_duo = google_duo;
    }

    public String getFacetime() {
        return facetime;
    }

    public void setFacetime(String facetime) {
        this.facetime = facetime;
    }

    public String getProperypost() {
        return properypost;
    }

    public void setProperypost(String properypost) {
        this.properypost = properypost;
    }

    public ArrayList<File> getSite_view() {
        return site_view;
    }

    public void setSite_view(ArrayList<File> site_view) {
        this.site_view = site_view;
    }

    public ArrayList<File> getCommon_area() {
        return common_area;
    }

    public void setCommon_area(ArrayList<File> common_area) {
        this.common_area = common_area;
    }

    public ArrayList<File> getWashroom_view() {
        return washroom_view;
    }

    public void setWashroom_view(ArrayList<File> washroom_view) {
        this.washroom_view = washroom_view;
    }

    public ArrayList<File> getExterior_view() {
        return exterior_view;
    }

    public void setExterior_view(ArrayList<File> exterior_view) {
        this.exterior_view = exterior_view;
    }

    public ArrayList<File> getLiving_room() {
        return living_room;
    }

    public void setLiving_room(ArrayList<File> living_room) {
        this.living_room = living_room;
    }

    public ArrayList<File> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(ArrayList<File> bedrooms) {
        this.bedrooms = bedrooms;
    }

    public ArrayList<File> getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(ArrayList<File> bathrooms) {
        this.bathrooms = bathrooms;
    }

    public ArrayList<File> getKitchen() {
        return kitchen;
    }

    public void setKitchen(ArrayList<File> kitchen) {
        this.kitchen = kitchen;
    }

    public ArrayList<File> getFloor_plan() {
        return floor_plan;
    }

    public void setFloor_plan(ArrayList<File> floor_plan) {
        this.floor_plan = floor_plan;
    }

    public ArrayList<File> getMaster_plan() {
        return master_plan;
    }

    public void setMaster_plan(ArrayList<File> master_plan) {
        this.master_plan = master_plan;
    }

    public ArrayList<File> getLocation_map() {
        return location_map;
    }

    public void setLocation_map(ArrayList<File> location_map) {
        this.location_map = location_map;
    }

    public ArrayList<File> getOthers() {
        return others;
    }

    public void setOthers(ArrayList<File> others) {
        this.others = others;
    }

    public File getBrochure() {
        return brochure;
    }

    public void setBrochure(File brochure) {
        this.brochure = brochure;
    }

    public ArrayList<String> getBank() {
        return bank;
    }

    public void setBank(ArrayList<String> bank) {
        this.bank = bank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
