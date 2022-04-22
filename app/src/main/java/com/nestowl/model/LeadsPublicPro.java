package com.nestowl.model;

import java.util.ArrayList;

public class LeadsPublicPro {
    String  id,user_id,property_id,looking,propertytype,property,city,project_name,locality,sublocality,address,land_zone,special_zone,p_type,approval_date,status,pid,
            ownership_status,approved_by,amenities,property_features,sb_usp,office_on_the_floor,other_features,metro_railway_name,metro_railway_dist,bus_stops_name,bus_stops_dist,airports_name,airports_dist,
            shopping_malls_name,shopping_malls_dist,office_complex_name,office_complex_dist,college_univercity_name,college_univercity_dist,market_name,market_dist,playground_park_name,playground_park_dist,auto_taxi_stand_name,
            auto_taxi_stand_dist,gym_name,gym_dist,police_station_name,police_station_dist,key_selling_points,room_detail,roomlength,roombreadth,roomhall,masterroom,total_no_of_floor,floors_belongs,furnished_status,furnishings,
            washrooms,cafeteria,no_of_cafeteria,no_of_share_cafeteria,area_type,captur_area_min,captur_area_min_mezor,captur_area_max,captur_area_max_mezor,buildup_area_min,buildup_area_min_mezor,buildup_area_max,buildup_area_max_mezor,
            super_area_min,super_area_min_mezor,super_area_max,super_area_max_mezor,area,area_mezor,plot_area,plot_area_mezor,plotlength_ft,plotbreadth_ft,possession,possession_status,age_property,transaction_type,floor_allowed_for_construction,
            all_floors_belongs_to_me,no_of_open_sides,bedrooms,balconies,propertyonfloor,bathrooms,sharedbathrooms,personalbathrooms,sharedwashrooms,any_construction,boundary_wall_made,gated_colony,width_of_entrance,width_of_entrance_mezor,
            construction_status_of_walls,conference_room,totle_no_conference_room,reception_area,min_no_of_seats,max_no_of_seats,no_of_cabins,no_of_meeting_rooms,doors_constructed,venue_type,virtual_space,building_class,rate_of_return,
            shared_pantry,site_view,common_area,washroom_view,exterior_view,living_room,p_bedrooms,p_bathrooms,kitchen,floor_plan,master_plan,location_map,others,post_property,hide_contact_details,schedule_details_type,prefference_day,
            prefference_time,whats_app,jio_meet,skype,zoom,google_duo,facetime,brochure,bank,properypost,expectedprice,price_per_sq_ft,price_negotiable,additional_price,other_charge,maintenance_charge,charge_type,
            booking_amount,expected_rental,rental_membership,annual_dues_payble,idea_for_businesses,assured_returns,currently_leased_out,property_leased,leased_on,current_business_sector,business_since,centeral_air_conditioning,
            oxygen_duct,usp,fire_safety,ideal_businesses,ideal_for_use,office_previous_used_for,number_of_staircase,additional_rooms,direction_facing,overlooking,facing_road,parking_facility,parking_capacity,water_availability,water_source,
            facing_road_ft,best_suitable_for_crop,covered_parking_capacity_two_wheeler,covered_parking_capacity_four_wheeler,open_parking_capacity_two_wheeler,open_parking_capacity_four_wheeler,privete_parking_in_basement_two_wheeler,
            privete_parking_in_basement_four_wheeler,privete_parking_in_outside_two_wheeler,privete_parking_in_outside_four_wheeler,public_parking_two_wheeler,public_parking_four_wheeler,residentiallift,commerciallift,passengerlift,
            servicelift,occupancy_certificate,office_noc_certified,shop_noc_certified,showroom_noc_certified,warehouse_noc_certified,leed_certification,electricity_availability,power_backup,floor_details,verification_name,verification_photo,
            verification_name_or,verification_photo_or_front,verification_photo_or_back,created_at,updated_at,isUnsumbited,name,isAccepted;
    ArrayList<String> images;

    public LeadsPublicPro() {
    }

    public LeadsPublicPro(ArrayList<String> images,String id, String user_id, String property_id, String looking, String propertytype, String property, String city, String project_name, String locality, String sublocality, String address, String land_zone, String special_zone, String p_type, String approval_date, String status, String pid, String ownership_status, String approved_by, String amenities, String property_features, String sb_usp, String office_on_the_floor, String other_features, String metro_railway_name, String metro_railway_dist, String bus_stops_name, String bus_stops_dist, String airports_name, String airports_dist, String shopping_malls_name, String shopping_malls_dist, String office_complex_name, String office_complex_dist, String college_univercity_name, String college_univercity_dist, String market_name, String market_dist, String playground_park_name, String playground_park_dist, String auto_taxi_stand_name, String auto_taxi_stand_dist, String gym_name, String gym_dist, String police_station_name, String police_station_dist, String key_selling_points, String room_detail, String roomlength, String roombreadth, String roomhall, String masterroom, String total_no_of_floor, String floors_belongs, String furnished_status, String furnishings, String washrooms, String cafeteria, String no_of_cafeteria, String no_of_share_cafeteria, String area_type, String captur_area_min, String captur_area_min_mezor, String captur_area_max, String captur_area_max_mezor, String buildup_area_min, String buildup_area_min_mezor, String buildup_area_max, String buildup_area_max_mezor, String super_area_min, String super_area_min_mezor, String super_area_max, String super_area_max_mezor, String area, String area_mezor, String plot_area, String plot_area_mezor, String plotlength_ft, String plotbreadth_ft, String possession, String possession_status, String age_property, String transaction_type, String floor_allowed_for_construction, String all_floors_belongs_to_me, String no_of_open_sides, String bedrooms, String balconies, String propertyonfloor, String bathrooms, String sharedbathrooms, String personalbathrooms, String sharedwashrooms, String any_construction, String boundary_wall_made, String gated_colony, String width_of_entrance, String width_of_entrance_mezor, String construction_status_of_walls, String conference_room, String totle_no_conference_room, String reception_area, String min_no_of_seats, String max_no_of_seats, String no_of_cabins, String no_of_meeting_rooms, String doors_constructed, String venue_type, String virtual_space, String building_class, String rate_of_return, String shared_pantry, String site_view, String common_area, String washroom_view, String exterior_view, String living_room, String p_bedrooms, String p_bathrooms, String kitchen, String floor_plan, String master_plan, String location_map, String others, String post_property, String hide_contact_details, String schedule_details_type, String prefference_day, String prefference_time, String whats_app, String jio_meet, String skype, String zoom, String google_duo, String facetime, String brochure, String bank, String properypost, String expectedprice, String price_per_sq_ft, String price_negotiable, String additional_price, String other_charge, String maintenance_charge, String charge_type, String booking_amount, String expected_rental, String rental_membership, String annual_dues_payble, String idea_for_businesses, String assured_returns, String currently_leased_out, String property_leased, String leased_on, String current_business_sector, String business_since, String centeral_air_conditioning, String oxygen_duct, String usp, String fire_safety, String ideal_businesses, String ideal_for_use, String office_previous_used_for, String number_of_staircase, String additional_rooms, String direction_facing, String overlooking, String facing_road, String parking_facility, String parking_capacity, String water_availability, String water_source, String facing_road_ft, String best_suitable_for_crop, String covered_parking_capacity_two_wheeler, String covered_parking_capacity_four_wheeler, String open_parking_capacity_two_wheeler, String open_parking_capacity_four_wheeler, String privete_parking_in_basement_two_wheeler, String privete_parking_in_basement_four_wheeler, String privete_parking_in_outside_two_wheeler, String privete_parking_in_outside_four_wheeler, String public_parking_two_wheeler, String public_parking_four_wheeler, String residentiallift, String commerciallift, String passengerlift, String servicelift, String occupancy_certificate, String office_noc_certified, String shop_noc_certified, String showroom_noc_certified, String warehouse_noc_certified, String leed_certification, String electricity_availability, String power_backup, String floor_details, String verification_name, String verification_photo, String verification_name_or, String verification_photo_or_front, String verification_photo_or_back, String created_at, String updated_at, String isUnsumbited, String name, String isAccepted) {
        this.id = id;
        this.images=images;
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
        this.pid = pid;
        this.ownership_status = ownership_status;
        this.approved_by = approved_by;
        this.amenities = amenities;
        this.property_features = property_features;
        this.sb_usp = sb_usp;
        this.office_on_the_floor = office_on_the_floor;
        this.other_features = other_features;
        this.metro_railway_name = metro_railway_name;
        this.metro_railway_dist = metro_railway_dist;
        this.bus_stops_name = bus_stops_name;
        this.bus_stops_dist = bus_stops_dist;
        this.airports_name = airports_name;
        this.airports_dist = airports_dist;
        this.shopping_malls_name = shopping_malls_name;
        this.shopping_malls_dist = shopping_malls_dist;
        this.office_complex_name = office_complex_name;
        this.office_complex_dist = office_complex_dist;
        this.college_univercity_name = college_univercity_name;
        this.college_univercity_dist = college_univercity_dist;
        this.market_name = market_name;
        this.market_dist = market_dist;
        this.playground_park_name = playground_park_name;
        this.playground_park_dist = playground_park_dist;
        this.auto_taxi_stand_name = auto_taxi_stand_name;
        this.auto_taxi_stand_dist = auto_taxi_stand_dist;
        this.gym_name = gym_name;
        this.gym_dist = gym_dist;
        this.police_station_name = police_station_name;
        this.police_station_dist = police_station_dist;
        this.key_selling_points = key_selling_points;
        this.room_detail = room_detail;
        this.roomlength = roomlength;
        this.roombreadth = roombreadth;
        this.roomhall = roomhall;
        this.masterroom = masterroom;
        this.total_no_of_floor = total_no_of_floor;
        this.floors_belongs = floors_belongs;
        this.furnished_status = furnished_status;
        this.furnishings = furnishings;
        this.washrooms = washrooms;
        this.cafeteria = cafeteria;
        this.no_of_cafeteria = no_of_cafeteria;
        this.no_of_share_cafeteria = no_of_share_cafeteria;
        this.area_type = area_type;
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
        this.site_view = site_view;
        this.common_area = common_area;
        this.washroom_view = washroom_view;
        this.exterior_view = exterior_view;
        this.living_room = living_room;
        this.p_bedrooms = p_bedrooms;
        this.p_bathrooms = p_bathrooms;
        this.kitchen = kitchen;
        this.floor_plan = floor_plan;
        this.master_plan = master_plan;
        this.location_map = location_map;
        this.others = others;
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
        this.brochure = brochure;
        this.bank = bank;
        this.properypost = properypost;
        this.expectedprice = expectedprice;
        this.price_per_sq_ft = price_per_sq_ft;
        this.price_negotiable = price_negotiable;
        this.additional_price = additional_price;
        this.other_charge = other_charge;
        this.maintenance_charge = maintenance_charge;
        this.charge_type = charge_type;
        this.booking_amount = booking_amount;
        this.expected_rental = expected_rental;
        this.rental_membership = rental_membership;
        this.annual_dues_payble = annual_dues_payble;
        this.idea_for_businesses = idea_for_businesses;
        this.assured_returns = assured_returns;
        this.currently_leased_out = currently_leased_out;
        this.property_leased = property_leased;
        this.leased_on = leased_on;
        this.current_business_sector = current_business_sector;
        this.business_since = business_since;
        this.centeral_air_conditioning = centeral_air_conditioning;
        this.oxygen_duct = oxygen_duct;
        this.usp = usp;
        this.fire_safety = fire_safety;
        this.ideal_businesses = ideal_businesses;
        this.ideal_for_use = ideal_for_use;
        this.office_previous_used_for = office_previous_used_for;
        this.number_of_staircase = number_of_staircase;
        this.additional_rooms = additional_rooms;
        this.direction_facing = direction_facing;
        this.overlooking = overlooking;
        this.facing_road = facing_road;
        this.parking_facility = parking_facility;
        this.parking_capacity = parking_capacity;
        this.water_availability = water_availability;
        this.water_source = water_source;
        this.facing_road_ft = facing_road_ft;
        this.best_suitable_for_crop = best_suitable_for_crop;
        this.covered_parking_capacity_two_wheeler = covered_parking_capacity_two_wheeler;
        this.covered_parking_capacity_four_wheeler = covered_parking_capacity_four_wheeler;
        this.open_parking_capacity_two_wheeler = open_parking_capacity_two_wheeler;
        this.open_parking_capacity_four_wheeler = open_parking_capacity_four_wheeler;
        this.privete_parking_in_basement_two_wheeler = privete_parking_in_basement_two_wheeler;
        this.privete_parking_in_basement_four_wheeler = privete_parking_in_basement_four_wheeler;
        this.privete_parking_in_outside_two_wheeler = privete_parking_in_outside_two_wheeler;
        this.privete_parking_in_outside_four_wheeler = privete_parking_in_outside_four_wheeler;
        this.public_parking_two_wheeler = public_parking_two_wheeler;
        this.public_parking_four_wheeler = public_parking_four_wheeler;
        this.residentiallift = residentiallift;
        this.commerciallift = commerciallift;
        this.passengerlift = passengerlift;
        this.servicelift = servicelift;
        this.occupancy_certificate = occupancy_certificate;
        this.office_noc_certified = office_noc_certified;
        this.shop_noc_certified = shop_noc_certified;
        this.showroom_noc_certified = showroom_noc_certified;
        this.warehouse_noc_certified = warehouse_noc_certified;
        this.leed_certification = leed_certification;
        this.electricity_availability = electricity_availability;
        this.power_backup = power_backup;
        this.floor_details = floor_details;
        this.verification_name = verification_name;
        this.verification_photo = verification_photo;
        this.verification_name_or = verification_name_or;
        this.verification_photo_or_front = verification_photo_or_front;
        this.verification_photo_or_back = verification_photo_or_back;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isUnsumbited = isUnsumbited;
        this.name = name;
        this.isAccepted = isAccepted;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
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

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOwnership_status() {
        return ownership_status;
    }

    public void setOwnership_status(String ownership_status) {
        this.ownership_status = ownership_status;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getProperty_features() {
        return property_features;
    }

    public void setProperty_features(String property_features) {
        this.property_features = property_features;
    }

    public String getSb_usp() {
        return sb_usp;
    }

    public void setSb_usp(String sb_usp) {
        this.sb_usp = sb_usp;
    }

    public String getOffice_on_the_floor() {
        return office_on_the_floor;
    }

    public void setOffice_on_the_floor(String office_on_the_floor) {
        this.office_on_the_floor = office_on_the_floor;
    }

    public String getOther_features() {
        return other_features;
    }

    public void setOther_features(String other_features) {
        this.other_features = other_features;
    }

    public String getMetro_railway_name() {
        return metro_railway_name;
    }

    public void setMetro_railway_name(String metro_railway_name) {
        this.metro_railway_name = metro_railway_name;
    }

    public String getMetro_railway_dist() {
        return metro_railway_dist;
    }

    public void setMetro_railway_dist(String metro_railway_dist) {
        this.metro_railway_dist = metro_railway_dist;
    }

    public String getBus_stops_name() {
        return bus_stops_name;
    }

    public void setBus_stops_name(String bus_stops_name) {
        this.bus_stops_name = bus_stops_name;
    }

    public String getBus_stops_dist() {
        return bus_stops_dist;
    }

    public void setBus_stops_dist(String bus_stops_dist) {
        this.bus_stops_dist = bus_stops_dist;
    }

    public String getAirports_name() {
        return airports_name;
    }

    public void setAirports_name(String airports_name) {
        this.airports_name = airports_name;
    }

    public String getAirports_dist() {
        return airports_dist;
    }

    public void setAirports_dist(String airports_dist) {
        this.airports_dist = airports_dist;
    }

    public String getShopping_malls_name() {
        return shopping_malls_name;
    }

    public void setShopping_malls_name(String shopping_malls_name) {
        this.shopping_malls_name = shopping_malls_name;
    }

    public String getShopping_malls_dist() {
        return shopping_malls_dist;
    }

    public void setShopping_malls_dist(String shopping_malls_dist) {
        this.shopping_malls_dist = shopping_malls_dist;
    }

    public String getOffice_complex_name() {
        return office_complex_name;
    }

    public void setOffice_complex_name(String office_complex_name) {
        this.office_complex_name = office_complex_name;
    }

    public String getOffice_complex_dist() {
        return office_complex_dist;
    }

    public void setOffice_complex_dist(String office_complex_dist) {
        this.office_complex_dist = office_complex_dist;
    }

    public String getCollege_univercity_name() {
        return college_univercity_name;
    }

    public void setCollege_univercity_name(String college_univercity_name) {
        this.college_univercity_name = college_univercity_name;
    }

    public String getCollege_univercity_dist() {
        return college_univercity_dist;
    }

    public void setCollege_univercity_dist(String college_univercity_dist) {
        this.college_univercity_dist = college_univercity_dist;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getMarket_dist() {
        return market_dist;
    }

    public void setMarket_dist(String market_dist) {
        this.market_dist = market_dist;
    }

    public String getPlayground_park_name() {
        return playground_park_name;
    }

    public void setPlayground_park_name(String playground_park_name) {
        this.playground_park_name = playground_park_name;
    }

    public String getPlayground_park_dist() {
        return playground_park_dist;
    }

    public void setPlayground_park_dist(String playground_park_dist) {
        this.playground_park_dist = playground_park_dist;
    }

    public String getAuto_taxi_stand_name() {
        return auto_taxi_stand_name;
    }

    public void setAuto_taxi_stand_name(String auto_taxi_stand_name) {
        this.auto_taxi_stand_name = auto_taxi_stand_name;
    }

    public String getAuto_taxi_stand_dist() {
        return auto_taxi_stand_dist;
    }

    public void setAuto_taxi_stand_dist(String auto_taxi_stand_dist) {
        this.auto_taxi_stand_dist = auto_taxi_stand_dist;
    }

    public String getGym_name() {
        return gym_name;
    }

    public void setGym_name(String gym_name) {
        this.gym_name = gym_name;
    }

    public String getGym_dist() {
        return gym_dist;
    }

    public void setGym_dist(String gym_dist) {
        this.gym_dist = gym_dist;
    }

    public String getPolice_station_name() {
        return police_station_name;
    }

    public void setPolice_station_name(String police_station_name) {
        this.police_station_name = police_station_name;
    }

    public String getPolice_station_dist() {
        return police_station_dist;
    }

    public void setPolice_station_dist(String police_station_dist) {
        this.police_station_dist = police_station_dist;
    }

    public String getKey_selling_points() {
        return key_selling_points;
    }

    public void setKey_selling_points(String key_selling_points) {
        this.key_selling_points = key_selling_points;
    }

    public String getRoom_detail() {
        return room_detail;
    }

    public void setRoom_detail(String room_detail) {
        this.room_detail = room_detail;
    }

    public String getRoomlength() {
        return roomlength;
    }

    public void setRoomlength(String roomlength) {
        this.roomlength = roomlength;
    }

    public String getRoombreadth() {
        return roombreadth;
    }

    public void setRoombreadth(String roombreadth) {
        this.roombreadth = roombreadth;
    }

    public String getRoomhall() {
        return roomhall;
    }

    public void setRoomhall(String roomhall) {
        this.roomhall = roomhall;
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

    public String getFurnishings() {
        return furnishings;
    }

    public void setFurnishings(String furnishings) {
        this.furnishings = furnishings;
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

    public String getArea_type() {
        return area_type;
    }

    public void setArea_type(String area_type) {
        this.area_type = area_type;
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

    public String getSite_view() {
        return site_view;
    }

    public void setSite_view(String site_view) {
        this.site_view = site_view;
    }

    public String getCommon_area() {
        return common_area;
    }

    public void setCommon_area(String common_area) {
        this.common_area = common_area;
    }

    public String getWashroom_view() {
        return washroom_view;
    }

    public void setWashroom_view(String washroom_view) {
        this.washroom_view = washroom_view;
    }

    public String getExterior_view() {
        return exterior_view;
    }

    public void setExterior_view(String exterior_view) {
        this.exterior_view = exterior_view;
    }

    public String getLiving_room() {
        return living_room;
    }

    public void setLiving_room(String living_room) {
        this.living_room = living_room;
    }

    public String getP_bedrooms() {
        return p_bedrooms;
    }

    public void setP_bedrooms(String p_bedrooms) {
        this.p_bedrooms = p_bedrooms;
    }

    public String getP_bathrooms() {
        return p_bathrooms;
    }

    public void setP_bathrooms(String p_bathrooms) {
        this.p_bathrooms = p_bathrooms;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getFloor_plan() {
        return floor_plan;
    }

    public void setFloor_plan(String floor_plan) {
        this.floor_plan = floor_plan;
    }

    public String getMaster_plan() {
        return master_plan;
    }

    public void setMaster_plan(String master_plan) {
        this.master_plan = master_plan;
    }

    public String getLocation_map() {
        return location_map;
    }

    public void setLocation_map(String location_map) {
        this.location_map = location_map;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
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

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getProperypost() {
        return properypost;
    }

    public void setProperypost(String properypost) {
        this.properypost = properypost;
    }

    public String getExpectedprice() {
        return expectedprice;
    }

    public void setExpectedprice(String expectedprice) {
        this.expectedprice = expectedprice;
    }

    public String getPrice_per_sq_ft() {
        return price_per_sq_ft;
    }

    public void setPrice_per_sq_ft(String price_per_sq_ft) {
        this.price_per_sq_ft = price_per_sq_ft;
    }

    public String getPrice_negotiable() {
        return price_negotiable;
    }

    public void setPrice_negotiable(String price_negotiable) {
        this.price_negotiable = price_negotiable;
    }

    public String getAdditional_price() {
        return additional_price;
    }

    public void setAdditional_price(String additional_price) {
        this.additional_price = additional_price;
    }

    public String getOther_charge() {
        return other_charge;
    }

    public void setOther_charge(String other_charge) {
        this.other_charge = other_charge;
    }

    public String getMaintenance_charge() {
        return maintenance_charge;
    }

    public void setMaintenance_charge(String maintenance_charge) {
        this.maintenance_charge = maintenance_charge;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getBooking_amount() {
        return booking_amount;
    }

    public void setBooking_amount(String booking_amount) {
        this.booking_amount = booking_amount;
    }

    public String getExpected_rental() {
        return expected_rental;
    }

    public void setExpected_rental(String expected_rental) {
        this.expected_rental = expected_rental;
    }

    public String getRental_membership() {
        return rental_membership;
    }

    public void setRental_membership(String rental_membership) {
        this.rental_membership = rental_membership;
    }

    public String getAnnual_dues_payble() {
        return annual_dues_payble;
    }

    public void setAnnual_dues_payble(String annual_dues_payble) {
        this.annual_dues_payble = annual_dues_payble;
    }

    public String getIdea_for_businesses() {
        return idea_for_businesses;
    }

    public void setIdea_for_businesses(String idea_for_businesses) {
        this.idea_for_businesses = idea_for_businesses;
    }

    public String getAssured_returns() {
        return assured_returns;
    }

    public void setAssured_returns(String assured_returns) {
        this.assured_returns = assured_returns;
    }

    public String getCurrently_leased_out() {
        return currently_leased_out;
    }

    public void setCurrently_leased_out(String currently_leased_out) {
        this.currently_leased_out = currently_leased_out;
    }

    public String getProperty_leased() {
        return property_leased;
    }

    public void setProperty_leased(String property_leased) {
        this.property_leased = property_leased;
    }

    public String getLeased_on() {
        return leased_on;
    }

    public void setLeased_on(String leased_on) {
        this.leased_on = leased_on;
    }

    public String getCurrent_business_sector() {
        return current_business_sector;
    }

    public void setCurrent_business_sector(String current_business_sector) {
        this.current_business_sector = current_business_sector;
    }

    public String getBusiness_since() {
        return business_since;
    }

    public void setBusiness_since(String business_since) {
        this.business_since = business_since;
    }

    public String getCenteral_air_conditioning() {
        return centeral_air_conditioning;
    }

    public void setCenteral_air_conditioning(String centeral_air_conditioning) {
        this.centeral_air_conditioning = centeral_air_conditioning;
    }

    public String getOxygen_duct() {
        return oxygen_duct;
    }

    public void setOxygen_duct(String oxygen_duct) {
        this.oxygen_duct = oxygen_duct;
    }

    public String getUsp() {
        return usp;
    }

    public void setUsp(String usp) {
        this.usp = usp;
    }

    public String getFire_safety() {
        return fire_safety;
    }

    public void setFire_safety(String fire_safety) {
        this.fire_safety = fire_safety;
    }

    public String getIdeal_businesses() {
        return ideal_businesses;
    }

    public void setIdeal_businesses(String ideal_businesses) {
        this.ideal_businesses = ideal_businesses;
    }

    public String getIdeal_for_use() {
        return ideal_for_use;
    }

    public void setIdeal_for_use(String ideal_for_use) {
        this.ideal_for_use = ideal_for_use;
    }

    public String getOffice_previous_used_for() {
        return office_previous_used_for;
    }

    public void setOffice_previous_used_for(String office_previous_used_for) {
        this.office_previous_used_for = office_previous_used_for;
    }

    public String getNumber_of_staircase() {
        return number_of_staircase;
    }

    public void setNumber_of_staircase(String number_of_staircase) {
        this.number_of_staircase = number_of_staircase;
    }

    public String getAdditional_rooms() {
        return additional_rooms;
    }

    public void setAdditional_rooms(String additional_rooms) {
        this.additional_rooms = additional_rooms;
    }

    public String getDirection_facing() {
        return direction_facing;
    }

    public void setDirection_facing(String direction_facing) {
        this.direction_facing = direction_facing;
    }

    public String getOverlooking() {
        return overlooking;
    }

    public void setOverlooking(String overlooking) {
        this.overlooking = overlooking;
    }

    public String getFacing_road() {
        return facing_road;
    }

    public void setFacing_road(String facing_road) {
        this.facing_road = facing_road;
    }

    public String getParking_facility() {
        return parking_facility;
    }

    public void setParking_facility(String parking_facility) {
        this.parking_facility = parking_facility;
    }

    public String getParking_capacity() {
        return parking_capacity;
    }

    public void setParking_capacity(String parking_capacity) {
        this.parking_capacity = parking_capacity;
    }

    public String getWater_availability() {
        return water_availability;
    }

    public void setWater_availability(String water_availability) {
        this.water_availability = water_availability;
    }

    public String getWater_source() {
        return water_source;
    }

    public void setWater_source(String water_source) {
        this.water_source = water_source;
    }

    public String getFacing_road_ft() {
        return facing_road_ft;
    }

    public void setFacing_road_ft(String facing_road_ft) {
        this.facing_road_ft = facing_road_ft;
    }

    public String getBest_suitable_for_crop() {
        return best_suitable_for_crop;
    }

    public void setBest_suitable_for_crop(String best_suitable_for_crop) {
        this.best_suitable_for_crop = best_suitable_for_crop;
    }

    public String getCovered_parking_capacity_two_wheeler() {
        return covered_parking_capacity_two_wheeler;
    }

    public void setCovered_parking_capacity_two_wheeler(String covered_parking_capacity_two_wheeler) {
        this.covered_parking_capacity_two_wheeler = covered_parking_capacity_two_wheeler;
    }

    public String getCovered_parking_capacity_four_wheeler() {
        return covered_parking_capacity_four_wheeler;
    }

    public void setCovered_parking_capacity_four_wheeler(String covered_parking_capacity_four_wheeler) {
        this.covered_parking_capacity_four_wheeler = covered_parking_capacity_four_wheeler;
    }

    public String getOpen_parking_capacity_two_wheeler() {
        return open_parking_capacity_two_wheeler;
    }

    public void setOpen_parking_capacity_two_wheeler(String open_parking_capacity_two_wheeler) {
        this.open_parking_capacity_two_wheeler = open_parking_capacity_two_wheeler;
    }

    public String getOpen_parking_capacity_four_wheeler() {
        return open_parking_capacity_four_wheeler;
    }

    public void setOpen_parking_capacity_four_wheeler(String open_parking_capacity_four_wheeler) {
        this.open_parking_capacity_four_wheeler = open_parking_capacity_four_wheeler;
    }

    public String getPrivete_parking_in_basement_two_wheeler() {
        return privete_parking_in_basement_two_wheeler;
    }

    public void setPrivete_parking_in_basement_two_wheeler(String privete_parking_in_basement_two_wheeler) {
        this.privete_parking_in_basement_two_wheeler = privete_parking_in_basement_two_wheeler;
    }

    public String getPrivete_parking_in_basement_four_wheeler() {
        return privete_parking_in_basement_four_wheeler;
    }

    public void setPrivete_parking_in_basement_four_wheeler(String privete_parking_in_basement_four_wheeler) {
        this.privete_parking_in_basement_four_wheeler = privete_parking_in_basement_four_wheeler;
    }

    public String getPrivete_parking_in_outside_two_wheeler() {
        return privete_parking_in_outside_two_wheeler;
    }

    public void setPrivete_parking_in_outside_two_wheeler(String privete_parking_in_outside_two_wheeler) {
        this.privete_parking_in_outside_two_wheeler = privete_parking_in_outside_two_wheeler;
    }

    public String getPrivete_parking_in_outside_four_wheeler() {
        return privete_parking_in_outside_four_wheeler;
    }

    public void setPrivete_parking_in_outside_four_wheeler(String privete_parking_in_outside_four_wheeler) {
        this.privete_parking_in_outside_four_wheeler = privete_parking_in_outside_four_wheeler;
    }

    public String getPublic_parking_two_wheeler() {
        return public_parking_two_wheeler;
    }

    public void setPublic_parking_two_wheeler(String public_parking_two_wheeler) {
        this.public_parking_two_wheeler = public_parking_two_wheeler;
    }

    public String getPublic_parking_four_wheeler() {
        return public_parking_four_wheeler;
    }

    public void setPublic_parking_four_wheeler(String public_parking_four_wheeler) {
        this.public_parking_four_wheeler = public_parking_four_wheeler;
    }

    public String getResidentiallift() {
        return residentiallift;
    }

    public void setResidentiallift(String residentiallift) {
        this.residentiallift = residentiallift;
    }

    public String getCommerciallift() {
        return commerciallift;
    }

    public void setCommerciallift(String commerciallift) {
        this.commerciallift = commerciallift;
    }

    public String getPassengerlift() {
        return passengerlift;
    }

    public void setPassengerlift(String passengerlift) {
        this.passengerlift = passengerlift;
    }

    public String getServicelift() {
        return servicelift;
    }

    public void setServicelift(String servicelift) {
        this.servicelift = servicelift;
    }

    public String getOccupancy_certificate() {
        return occupancy_certificate;
    }

    public void setOccupancy_certificate(String occupancy_certificate) {
        this.occupancy_certificate = occupancy_certificate;
    }

    public String getOffice_noc_certified() {
        return office_noc_certified;
    }

    public void setOffice_noc_certified(String office_noc_certified) {
        this.office_noc_certified = office_noc_certified;
    }

    public String getShop_noc_certified() {
        return shop_noc_certified;
    }

    public void setShop_noc_certified(String shop_noc_certified) {
        this.shop_noc_certified = shop_noc_certified;
    }

    public String getShowroom_noc_certified() {
        return showroom_noc_certified;
    }

    public void setShowroom_noc_certified(String showroom_noc_certified) {
        this.showroom_noc_certified = showroom_noc_certified;
    }

    public String getWarehouse_noc_certified() {
        return warehouse_noc_certified;
    }

    public void setWarehouse_noc_certified(String warehouse_noc_certified) {
        this.warehouse_noc_certified = warehouse_noc_certified;
    }

    public String getLeed_certification() {
        return leed_certification;
    }

    public void setLeed_certification(String leed_certification) {
        this.leed_certification = leed_certification;
    }

    public String getElectricity_availability() {
        return electricity_availability;
    }

    public void setElectricity_availability(String electricity_availability) {
        this.electricity_availability = electricity_availability;
    }

    public String getPower_backup() {
        return power_backup;
    }

    public void setPower_backup(String power_backup) {
        this.power_backup = power_backup;
    }

    public String getFloor_details() {
        return floor_details;
    }

    public void setFloor_details(String floor_details) {
        this.floor_details = floor_details;
    }

    public String getVerification_name() {
        return verification_name;
    }

    public void setVerification_name(String verification_name) {
        this.verification_name = verification_name;
    }

    public String getVerification_photo() {
        return verification_photo;
    }

    public void setVerification_photo(String verification_photo) {
        this.verification_photo = verification_photo;
    }

    public String getVerification_name_or() {
        return verification_name_or;
    }

    public void setVerification_name_or(String verification_name_or) {
        this.verification_name_or = verification_name_or;
    }

    public String getVerification_photo_or_front() {
        return verification_photo_or_front;
    }

    public void setVerification_photo_or_front(String verification_photo_or_front) {
        this.verification_photo_or_front = verification_photo_or_front;
    }

    public String getVerification_photo_or_back() {
        return verification_photo_or_back;
    }

    public void setVerification_photo_or_back(String verification_photo_or_back) {
        this.verification_photo_or_back = verification_photo_or_back;
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

    public String getIsUnsumbited() {
        return isUnsumbited;
    }

    public void setIsUnsumbited(String isUnsumbited) {
        this.isUnsumbited = isUnsumbited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }
}

