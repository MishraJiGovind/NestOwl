package com.nestowl.model;

import java.util.ArrayList;

public class PostReqFeaturesModal {
    String user_id,requirement_id,bhk_type,area_min,area_min_mezor,area_max,area_max_mezor,budget_min,budget_max,adjest_for_builder_property,floor_prefference,number_of_seats,
            number_meeting_rooms,open_hours,lock_in_period,captur_area_min,captur_area_min_mezor,captur_area_max,captur_area_max_mezor,buildup_area_min,buildup_area_min_mezor,buildup_area_max,
            buildup_area_max_mezor,super_area_min,super_area_min_mezor,super_area_max,super_area_max_mezor,corner_property_prefference,office_min_no_seat,office_max_no_seat,reception_area_prefference,
            building_class_prefference,transaction_type,need_possession,furnisging_prefference,direction_prefference,age_of_construction,nature_of_employment,reason_for_buying,additional_requirement_details,
            boundary_wall_made,main_road_property_prefference,want_to_buy,properypost;
    ArrayList<String> area_type,furnishings;

    public PostReqFeaturesModal() {
    }

    public PostReqFeaturesModal(String user_id, String requirement_id, String bhk_type, String area_min, String area_min_mezor, String area_max, String area_max_mezor, String budget_min, String budget_max, String adjest_for_builder_property, String floor_prefference, String number_of_seats, String number_meeting_rooms, String open_hours, String lock_in_period, String captur_area_min, String captur_area_min_mezor, String captur_area_max, String captur_area_max_mezor, String buildup_area_min, String buildup_area_min_mezor, String buildup_area_max, String buildup_area_max_mezor, String super_area_min, String super_area_min_mezor, String super_area_max, String super_area_max_mezor, String corner_property_prefference, String office_min_no_seat, String office_max_no_seat, String reception_area_prefference, String building_class_prefference, String transaction_type, String need_possession, String furnisging_prefference, String direction_prefference, String age_of_construction, String nature_of_employment, String reason_for_buying, String additional_requirement_details, String boundary_wall_made, String main_road_property_prefference, String want_to_buy, String properypost, ArrayList<String> area_type, ArrayList<String> furnishings) {
        this.user_id = user_id;
        this.requirement_id = requirement_id;
        this.bhk_type = bhk_type;
        this.area_min = area_min;
        this.area_min_mezor = area_min_mezor;
        this.area_max = area_max;
        this.area_max_mezor = area_max_mezor;
        this.budget_min = budget_min;
        this.budget_max = budget_max;
        this.adjest_for_builder_property = adjest_for_builder_property;
        this.floor_prefference = floor_prefference;
        this.number_of_seats = number_of_seats;
        this.number_meeting_rooms = number_meeting_rooms;
        this.open_hours = open_hours;
        this.lock_in_period = lock_in_period;
        this.captur_area_min = captur_area_min;
        this.captur_area_min_mezor = captur_area_min_mezor;
        this.captur_area_max = captur_area_max;
        this.captur_area_max_mezor = captur_area_max_mezor;
        this.buildup_area_min = buildup_area_min;
        this.buildup_area_min_mezor = buildup_area_min_mezor;
        this.buildup_area_max = buildup_area_max;
        this.buildup_area_max_mezor = buildup_area_max_mezor;
        this.super_area_min = super_area_min;
        this.super_area_min_mezor = super_area_min_mezor;
        this.super_area_max = super_area_max;
        this.super_area_max_mezor = super_area_max_mezor;
        this.corner_property_prefference = corner_property_prefference;
        this.office_min_no_seat = office_min_no_seat;
        this.office_max_no_seat = office_max_no_seat;
        this.reception_area_prefference = reception_area_prefference;
        this.building_class_prefference = building_class_prefference;
        this.transaction_type = transaction_type;
        this.need_possession = need_possession;
        this.furnisging_prefference = furnisging_prefference;
        this.direction_prefference = direction_prefference;
        this.age_of_construction = age_of_construction;
        this.nature_of_employment = nature_of_employment;
        this.reason_for_buying = reason_for_buying;
        this.additional_requirement_details = additional_requirement_details;
        this.boundary_wall_made = boundary_wall_made;
        this.main_road_property_prefference = main_road_property_prefference;
        this.want_to_buy = want_to_buy;
        this.properypost = properypost;
        this.area_type = area_type;
        this.furnishings = furnishings;
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

    public String getBhk_type() {
        return bhk_type;
    }

    public void setBhk_type(String bhk_type) {
        this.bhk_type = bhk_type;
    }

    public String getArea_min() {
        return area_min;
    }

    public void setArea_min(String area_min) {
        this.area_min = area_min;
    }

    public String getArea_min_mezor() {
        return area_min_mezor;
    }

    public void setArea_min_mezor(String area_min_mezor) {
        this.area_min_mezor = area_min_mezor;
    }

    public String getArea_max() {
        return area_max;
    }

    public void setArea_max(String area_max) {
        this.area_max = area_max;
    }

    public String getArea_max_mezor() {
        return area_max_mezor;
    }

    public void setArea_max_mezor(String area_max_mezor) {
        this.area_max_mezor = area_max_mezor;
    }

    public String getBudget_min() {
        return budget_min;
    }

    public void setBudget_min(String budget_min) {
        this.budget_min = budget_min;
    }

    public String getBudget_max() {
        return budget_max;
    }

    public void setBudget_max(String budget_max) {
        this.budget_max = budget_max;
    }

    public String getAdjest_for_builder_property() {
        return adjest_for_builder_property;
    }

    public void setAdjest_for_builder_property(String adjest_for_builder_property) {
        this.adjest_for_builder_property = adjest_for_builder_property;
    }

    public String getFloor_prefference() {
        return floor_prefference;
    }

    public void setFloor_prefference(String floor_prefference) {
        this.floor_prefference = floor_prefference;
    }

    public String getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(String number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public String getNumber_meeting_rooms() {
        return number_meeting_rooms;
    }

    public void setNumber_meeting_rooms(String number_meeting_rooms) {
        this.number_meeting_rooms = number_meeting_rooms;
    }

    public String getOpen_hours() {
        return open_hours;
    }

    public void setOpen_hours(String open_hours) {
        this.open_hours = open_hours;
    }

    public String getLock_in_period() {
        return lock_in_period;
    }

    public void setLock_in_period(String lock_in_period) {
        this.lock_in_period = lock_in_period;
    }

    public String getCaptur_area_min() {
        return captur_area_min;
    }

    public void setCaptur_area_min(String captur_area_min) {
        this.captur_area_min = captur_area_min;
    }

    public String getCaptur_area_min_mezor() {
        return captur_area_min_mezor;
    }

    public void setCaptur_area_min_mezor(String captur_area_min_mezor) {
        this.captur_area_min_mezor = captur_area_min_mezor;
    }

    public String getCaptur_area_max() {
        return captur_area_max;
    }

    public void setCaptur_area_max(String captur_area_max) {
        this.captur_area_max = captur_area_max;
    }

    public String getCaptur_area_max_mezor() {
        return captur_area_max_mezor;
    }

    public void setCaptur_area_max_mezor(String captur_area_max_mezor) {
        this.captur_area_max_mezor = captur_area_max_mezor;
    }

    public String getBuildup_area_min() {
        return buildup_area_min;
    }

    public void setBuildup_area_min(String buildup_area_min) {
        this.buildup_area_min = buildup_area_min;
    }

    public String getBuildup_area_min_mezor() {
        return buildup_area_min_mezor;
    }

    public void setBuildup_area_min_mezor(String buildup_area_min_mezor) {
        this.buildup_area_min_mezor = buildup_area_min_mezor;
    }

    public String getBuildup_area_max() {
        return buildup_area_max;
    }

    public void setBuildup_area_max(String buildup_area_max) {
        this.buildup_area_max = buildup_area_max;
    }

    public String getBuildup_area_max_mezor() {
        return buildup_area_max_mezor;
    }

    public void setBuildup_area_max_mezor(String buildup_area_max_mezor) {
        this.buildup_area_max_mezor = buildup_area_max_mezor;
    }

    public String getSuper_area_min() {
        return super_area_min;
    }

    public void setSuper_area_min(String super_area_min) {
        this.super_area_min = super_area_min;
    }

    public String getSuper_area_min_mezor() {
        return super_area_min_mezor;
    }

    public void setSuper_area_min_mezor(String super_area_min_mezor) {
        this.super_area_min_mezor = super_area_min_mezor;
    }

    public String getSuper_area_max() {
        return super_area_max;
    }

    public void setSuper_area_max(String super_area_max) {
        this.super_area_max = super_area_max;
    }

    public String getSuper_area_max_mezor() {
        return super_area_max_mezor;
    }

    public void setSuper_area_max_mezor(String super_area_max_mezor) {
        this.super_area_max_mezor = super_area_max_mezor;
    }

    public String getCorner_property_prefference() {
        return corner_property_prefference;
    }

    public void setCorner_property_prefference(String corner_property_prefference) {
        this.corner_property_prefference = corner_property_prefference;
    }

    public String getOffice_min_no_seat() {
        return office_min_no_seat;
    }

    public void setOffice_min_no_seat(String office_min_no_seat) {
        this.office_min_no_seat = office_min_no_seat;
    }

    public String getOffice_max_no_seat() {
        return office_max_no_seat;
    }

    public void setOffice_max_no_seat(String office_max_no_seat) {
        this.office_max_no_seat = office_max_no_seat;
    }

    public String getReception_area_prefference() {
        return reception_area_prefference;
    }

    public void setReception_area_prefference(String reception_area_prefference) {
        this.reception_area_prefference = reception_area_prefference;
    }

    public String getBuilding_class_prefference() {
        return building_class_prefference;
    }

    public void setBuilding_class_prefference(String building_class_prefference) {
        this.building_class_prefference = building_class_prefference;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getNeed_possession() {
        return need_possession;
    }

    public void setNeed_possession(String need_possession) {
        this.need_possession = need_possession;
    }

    public String getFurnisging_prefference() {
        return furnisging_prefference;
    }

    public void setFurnisging_prefference(String furnisging_prefference) {
        this.furnisging_prefference = furnisging_prefference;
    }

    public String getDirection_prefference() {
        return direction_prefference;
    }

    public void setDirection_prefference(String direction_prefference) {
        this.direction_prefference = direction_prefference;
    }

    public String getAge_of_construction() {
        return age_of_construction;
    }

    public void setAge_of_construction(String age_of_construction) {
        this.age_of_construction = age_of_construction;
    }

    public String getNature_of_employment() {
        return nature_of_employment;
    }

    public void setNature_of_employment(String nature_of_employment) {
        this.nature_of_employment = nature_of_employment;
    }

    public String getReason_for_buying() {
        return reason_for_buying;
    }

    public void setReason_for_buying(String reason_for_buying) {
        this.reason_for_buying = reason_for_buying;
    }

    public String getAdditional_requirement_details() {
        return additional_requirement_details;
    }

    public void setAdditional_requirement_details(String additional_requirement_details) {
        this.additional_requirement_details = additional_requirement_details;
    }

    public String getBoundary_wall_made() {
        return boundary_wall_made;
    }

    public void setBoundary_wall_made(String boundary_wall_made) {
        this.boundary_wall_made = boundary_wall_made;
    }

    public String getMain_road_property_prefference() {
        return main_road_property_prefference;
    }

    public void setMain_road_property_prefference(String main_road_property_prefference) {
        this.main_road_property_prefference = main_road_property_prefference;
    }

    public String getWant_to_buy() {
        return want_to_buy;
    }

    public void setWant_to_buy(String want_to_buy) {
        this.want_to_buy = want_to_buy;
    }

    public String getProperypost() {
        return properypost;
    }

    public void setProperypost(String properypost) {
        this.properypost = properypost;
    }

    public ArrayList<String> getArea_type() {
        return area_type;
    }

    public void setArea_type(ArrayList<String> area_type) {
        this.area_type = area_type;
    }

    public ArrayList<String> getFurnishings() {
        return furnishings;
    }

    public void setFurnishings(ArrayList<String> furnishings) {
        this.furnishings = furnishings;
    }
}
