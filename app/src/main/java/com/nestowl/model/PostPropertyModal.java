package com.nestowl.model;

import java.util.ArrayList;

public class PostPropertyModal {
    String user_id,property_id,room_detail,masterroom,total_no_of_floor,floors_belongs,furnished_status,washrooms,cafeteria,no_of_cafeteria,no_of_share_cafeteria,
            captur_area_min,captur_area_min_mezor,captur_area_max,captur_area_max_mezor,buildup_area_min,buildup_area_min_mezor,buildup_area_max,buildup_area_max_mezor,
            super_area_min,super_area_min_mezor,super_area_max,super_area_max_mezor,area,area_mezor,plot_area,plot_area_mezor,plotlength_ft,plotbreadth_ft,possession,
            possession_status,age_property,transaction_type,floor_allowed_for_construction,all_floors_belongs_to_me,no_of_open_sides,bedrooms,balconies,propertyonfloor,
            bathrooms,sharedbathrooms,personalbathrooms,sharedwashrooms,any_construction,boundary_wall_made,gated_colony,width_of_entrance,width_of_entrance_mezor,
            construction_status_of_walls,conference_room,totle_no_conference_room,reception_area,min_no_of_seats,max_no_of_seats,no_of_cabins,no_of_meeting_rooms,
            doors_constructed,venue_type,virtual_space,building_class,rate_of_return,shared_pantry;
    String roomlength[],roombreadth[],roomhall[],furnishings[],area_type[];

    public PostPropertyModal() {
    }

    public PostPropertyModal(String user_id, String property_id, String room_detail, String masterroom, String total_no_of_floor, String floors_belongs, String furnished_status, String washrooms, String cafeteria, String no_of_cafeteria, String no_of_share_cafeteria, String captur_area_min, String captur_area_min_mezor, String captur_area_max, String captur_area_max_mezor, String buildup_area_min, String buildup_area_min_mezor, String buildup_area_max, String buildup_area_max_mezor, String super_area_min, String super_area_min_mezor, String super_area_max, String super_area_max_mezor, String area, String area_mezor, String plot_area, String plot_area_mezor, String plotlength_ft, String plotbreadth_ft, String possession, String possession_status, String age_property, String transaction_type, String floor_allowed_for_construction, String all_floors_belongs_to_me, String no_of_open_sides, String bedrooms, String balconies, String propertyonfloor, String bathrooms, String sharedbathrooms, String personalbathrooms, String sharedwashrooms, String any_construction, String boundary_wall_made, String gated_colony, String width_of_entrance, String width_of_entrance_mezor, String construction_status_of_walls, String conference_room, String totle_no_conference_room, String reception_area, String min_no_of_seats, String max_no_of_seats, String no_of_cabins, String no_of_meeting_rooms, String doors_constructed, String venue_type, String virtual_space, String building_class, String rate_of_return, String shared_pantry, String[] roomlength, String[] roombreadth, String[] roomhall, String[] furnishings, String[] area_type) {
        this.user_id = user_id;
        this.property_id = property_id;
        this.room_detail = room_detail;
        this.masterroom = masterroom;
        this.total_no_of_floor = total_no_of_floor;
        this.floors_belongs = floors_belongs;
        this.furnished_status = furnished_status;
        this.washrooms = washrooms;
        this.cafeteria = cafeteria;
        this.no_of_cafeteria = no_of_cafeteria;
        this.no_of_share_cafeteria = no_of_share_cafeteria;
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
        this.area = area;
        this.area_mezor = area_mezor;
        this.plot_area = plot_area;
        this.plot_area_mezor = plot_area_mezor;
        this.plotlength_ft = plotlength_ft;
        this.plotbreadth_ft = plotbreadth_ft;
        this.possession = possession;
        this.possession_status = possession_status;
        this.age_property = age_property;
        this.transaction_type = transaction_type;
        this.floor_allowed_for_construction = floor_allowed_for_construction;
        this.all_floors_belongs_to_me = all_floors_belongs_to_me;
        this.no_of_open_sides = no_of_open_sides;
        this.bedrooms = bedrooms;
        this.balconies = balconies;
        this.propertyonfloor = propertyonfloor;
        this.bathrooms = bathrooms;
        this.sharedbathrooms = sharedbathrooms;
        this.personalbathrooms = personalbathrooms;
        this.sharedwashrooms = sharedwashrooms;
        this.any_construction = any_construction;
        this.boundary_wall_made = boundary_wall_made;
        this.gated_colony = gated_colony;
        this.width_of_entrance = width_of_entrance;
        this.width_of_entrance_mezor = width_of_entrance_mezor;
        this.construction_status_of_walls = construction_status_of_walls;
        this.conference_room = conference_room;
        this.totle_no_conference_room = totle_no_conference_room;
        this.reception_area = reception_area;
        this.min_no_of_seats = min_no_of_seats;
        this.max_no_of_seats = max_no_of_seats;
        this.no_of_cabins = no_of_cabins;
        this.no_of_meeting_rooms = no_of_meeting_rooms;
        this.doors_constructed = doors_constructed;
        this.venue_type = venue_type;
        this.virtual_space = virtual_space;
        this.building_class = building_class;
        this.rate_of_return = rate_of_return;
        this.shared_pantry = shared_pantry;
        this.roomlength = roomlength;
        this.roombreadth = roombreadth;
        this.roomhall = roomhall;
        this.furnishings = furnishings;
        this.area_type = area_type;
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

    public String getRoom_detail() {
        return room_detail;
    }

    public void setRoom_detail(String room_detail) {
        this.room_detail = room_detail;
    }

    public String getMasterroom() {
        return masterroom;
    }

    public void setMasterroom(String masterroom) {
        this.masterroom = masterroom;
    }

    public String getTotal_no_of_floor() {
        return total_no_of_floor;
    }

    public void setTotal_no_of_floor(String total_no_of_floor) {
        this.total_no_of_floor = total_no_of_floor;
    }

    public String getFloors_belongs() {
        return floors_belongs;
    }

    public void setFloors_belongs(String floors_belongs) {
        this.floors_belongs = floors_belongs;
    }

    public String getFurnished_status() {
        return furnished_status;
    }

    public void setFurnished_status(String furnished_status) {
        this.furnished_status = furnished_status;
    }

    public String getWashrooms() {
        return washrooms;
    }

    public void setWashrooms(String washrooms) {
        this.washrooms = washrooms;
    }

    public String getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(String cafeteria) {
        this.cafeteria = cafeteria;
    }

    public String getNo_of_cafeteria() {
        return no_of_cafeteria;
    }

    public void setNo_of_cafeteria(String no_of_cafeteria) {
        this.no_of_cafeteria = no_of_cafeteria;
    }

    public String getNo_of_share_cafeteria() {
        return no_of_share_cafeteria;
    }

    public void setNo_of_share_cafeteria(String no_of_share_cafeteria) {
        this.no_of_share_cafeteria = no_of_share_cafeteria;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_mezor() {
        return area_mezor;
    }

    public void setArea_mezor(String area_mezor) {
        this.area_mezor = area_mezor;
    }

    public String getPlot_area() {
        return plot_area;
    }

    public void setPlot_area(String plot_area) {
        this.plot_area = plot_area;
    }

    public String getPlot_area_mezor() {
        return plot_area_mezor;
    }

    public void setPlot_area_mezor(String plot_area_mezor) {
        this.plot_area_mezor = plot_area_mezor;
    }

    public String getPlotlength_ft() {
        return plotlength_ft;
    }

    public void setPlotlength_ft(String plotlength_ft) {
        this.plotlength_ft = plotlength_ft;
    }

    public String getPlotbreadth_ft() {
        return plotbreadth_ft;
    }

    public void setPlotbreadth_ft(String plotbreadth_ft) {
        this.plotbreadth_ft = plotbreadth_ft;
    }

    public String getPossession() {
        return possession;
    }

    public void setPossession(String possession) {
        this.possession = possession;
    }

    public String getPossession_status() {
        return possession_status;
    }

    public void setPossession_status(String possession_status) {
        this.possession_status = possession_status;
    }

    public String getAge_property() {
        return age_property;
    }

    public void setAge_property(String age_property) {
        this.age_property = age_property;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getFloor_allowed_for_construction() {
        return floor_allowed_for_construction;
    }

    public void setFloor_allowed_for_construction(String floor_allowed_for_construction) {
        this.floor_allowed_for_construction = floor_allowed_for_construction;
    }

    public String getAll_floors_belongs_to_me() {
        return all_floors_belongs_to_me;
    }

    public void setAll_floors_belongs_to_me(String all_floors_belongs_to_me) {
        this.all_floors_belongs_to_me = all_floors_belongs_to_me;
    }

    public String getNo_of_open_sides() {
        return no_of_open_sides;
    }

    public void setNo_of_open_sides(String no_of_open_sides) {
        this.no_of_open_sides = no_of_open_sides;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBalconies() {
        return balconies;
    }

    public void setBalconies(String balconies) {
        this.balconies = balconies;
    }

    public String getPropertyonfloor() {
        return propertyonfloor;
    }

    public void setPropertyonfloor(String propertyonfloor) {
        this.propertyonfloor = propertyonfloor;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getSharedbathrooms() {
        return sharedbathrooms;
    }

    public void setSharedbathrooms(String sharedbathrooms) {
        this.sharedbathrooms = sharedbathrooms;
    }

    public String getPersonalbathrooms() {
        return personalbathrooms;
    }

    public void setPersonalbathrooms(String personalbathrooms) {
        this.personalbathrooms = personalbathrooms;
    }

    public String getSharedwashrooms() {
        return sharedwashrooms;
    }

    public void setSharedwashrooms(String sharedwashrooms) {
        this.sharedwashrooms = sharedwashrooms;
    }

    public String getAny_construction() {
        return any_construction;
    }

    public void setAny_construction(String any_construction) {
        this.any_construction = any_construction;
    }

    public String getBoundary_wall_made() {
        return boundary_wall_made;
    }

    public void setBoundary_wall_made(String boundary_wall_made) {
        this.boundary_wall_made = boundary_wall_made;
    }

    public String getGated_colony() {
        return gated_colony;
    }

    public void setGated_colony(String gated_colony) {
        this.gated_colony = gated_colony;
    }

    public String getWidth_of_entrance() {
        return width_of_entrance;
    }

    public void setWidth_of_entrance(String width_of_entrance) {
        this.width_of_entrance = width_of_entrance;
    }

    public String getWidth_of_entrance_mezor() {
        return width_of_entrance_mezor;
    }

    public void setWidth_of_entrance_mezor(String width_of_entrance_mezor) {
        this.width_of_entrance_mezor = width_of_entrance_mezor;
    }

    public String getConstruction_status_of_walls() {
        return construction_status_of_walls;
    }

    public void setConstruction_status_of_walls(String construction_status_of_walls) {
        this.construction_status_of_walls = construction_status_of_walls;
    }

    public String getConference_room() {
        return conference_room;
    }

    public void setConference_room(String conference_room) {
        this.conference_room = conference_room;
    }

    public String getTotle_no_conference_room() {
        return totle_no_conference_room;
    }

    public void setTotle_no_conference_room(String totle_no_conference_room) {
        this.totle_no_conference_room = totle_no_conference_room;
    }

    public String getReception_area() {
        return reception_area;
    }

    public void setReception_area(String reception_area) {
        this.reception_area = reception_area;
    }

    public String getMin_no_of_seats() {
        return min_no_of_seats;
    }

    public void setMin_no_of_seats(String min_no_of_seats) {
        this.min_no_of_seats = min_no_of_seats;
    }

    public String getMax_no_of_seats() {
        return max_no_of_seats;
    }

    public void setMax_no_of_seats(String max_no_of_seats) {
        this.max_no_of_seats = max_no_of_seats;
    }

    public String getNo_of_cabins() {
        return no_of_cabins;
    }

    public void setNo_of_cabins(String no_of_cabins) {
        this.no_of_cabins = no_of_cabins;
    }

    public String getNo_of_meeting_rooms() {
        return no_of_meeting_rooms;
    }

    public void setNo_of_meeting_rooms(String no_of_meeting_rooms) {
        this.no_of_meeting_rooms = no_of_meeting_rooms;
    }

    public String getDoors_constructed() {
        return doors_constructed;
    }

    public void setDoors_constructed(String doors_constructed) {
        this.doors_constructed = doors_constructed;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(String venue_type) {
        this.venue_type = venue_type;
    }

    public String getVirtual_space() {
        return virtual_space;
    }

    public void setVirtual_space(String virtual_space) {
        this.virtual_space = virtual_space;
    }

    public String getBuilding_class() {
        return building_class;
    }

    public void setBuilding_class(String building_class) {
        this.building_class = building_class;
    }

    public String getRate_of_return() {
        return rate_of_return;
    }

    public void setRate_of_return(String rate_of_return) {
        this.rate_of_return = rate_of_return;
    }

    public String getShared_pantry() {
        return shared_pantry;
    }

    public void setShared_pantry(String shared_pantry) {
        this.shared_pantry = shared_pantry;
    }

    public String[] getRoomlength() {
        return roomlength;
    }

    public void setRoomlength(String[] roomlength) {
        this.roomlength = roomlength;
    }

    public String[] getRoombreadth() {
        return roombreadth;
    }

    public void setRoombreadth(String[] roombreadth) {
        this.roombreadth = roombreadth;
    }

    public String[] getRoomhall() {
        return roomhall;
    }

    public void setRoomhall(String[] roomhall) {
        this.roomhall = roomhall;
    }

    public String[] getFurnishings() {
        return furnishings;
    }

    public void setFurnishings(String[] furnishings) {
        this.furnishings = furnishings;
    }

    public String[] getArea_type() {
        return area_type;
    }

    public void setArea_type(String[] area_type) {
        this.area_type = area_type;
    }
}
