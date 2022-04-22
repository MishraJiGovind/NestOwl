package com.nestowl.model;

import java.util.ArrayList;

public class PostPricingModal {
    String user_id, property_id, expectedprice, price_per_sq_ft, price_negotiable, other_charge, maintenance_charge,
            charge_type, booking_amount, expected_rental, rental_membership, annual_dues_payble, idea_for_businesses, ideal_for_use,
            office_previous_used_for, parking_capacity, water_availability, water_source, facing_road_ft,
            best_suitable_for_crop, covered_parking_capacity_two_wheeler, covered_parking_capacity_four_wheeler, open_parking_capacity_two_wheeler,
            open_parking_capacity_four_wheeler, privete_parking_in_basement_two_wheeler, privete_parking_in_basement_four_wheeler,
            privete_parking_in_outside_two_wheeler, privete_parking_in_outside_four_wheeler, public_parking_two_wheeler, public_parking_four_wheeler,
            residentiallift, commerciallift, passengerlift, servicelift, occupancy_certificate, office_noc_certified,
            shop_noc_certified, showroom_noc_certified, warehouse_noc_certified, leed_certification, electricity_availability,
            power_backup, floor_details, land_zone, special_zone, approval_date, assured_returns, currently_leased_out,
            property_leased, leased_on, current_business_sector, business_since, centeral_air_conditioning, oxygen_duct,
            usp, fire_safety, ideal_businesses, number_of_staircase, direction_facing, facing_road;
    ArrayList<String> additional_price, additional_rooms, overlooking, parking_facility;

    public PostPricingModal() {
    }

    public PostPricingModal(String user_id, String property_id, String expectedprice, String price_per_sq_ft, String price_negotiable, String other_charge, String maintenance_charge, String charge_type, String booking_amount, String expected_rental, String rental_membership, String annual_dues_payble, String idea_for_businesses, String ideal_for_use, String office_previous_used_for, String parking_capacity, String water_availability, String water_source, String facing_road_ft, String best_suitable_for_crop, String covered_parking_capacity_two_wheeler, String covered_parking_capacity_four_wheeler, String open_parking_capacity_two_wheeler, String open_parking_capacity_four_wheeler, String privete_parking_in_basement_two_wheeler, String privete_parking_in_basement_four_wheeler, String privete_parking_in_outside_two_wheeler, String privete_parking_in_outside_four_wheeler, String public_parking_two_wheeler, String public_parking_four_wheeler, String residentiallift, String commerciallift, String passengerlift, String servicelift, String occupancy_certificate, String office_noc_certified, String shop_noc_certified, String showroom_noc_certified, String warehouse_noc_certified, String leed_certification, String electricity_availability, String power_backup, String floor_details, String land_zone, String special_zone, String approval_date, String assured_returns, String currently_leased_out, String property_leased, String leased_on, String current_business_sector, String business_since, String centeral_air_conditioning, String oxygen_duct, String usp, String fire_safety, String ideal_businesses, String number_of_staircase, String direction_facing, String facing_road, ArrayList<String> additional_price, ArrayList<String> additional_rooms, ArrayList<String> overlooking, ArrayList<String> parking_facility) {
        this.user_id = user_id;
        this.property_id = property_id;
        this.expectedprice = expectedprice;
        this.price_per_sq_ft = price_per_sq_ft;
        this.price_negotiable = price_negotiable;
        this.other_charge = other_charge;
        this.maintenance_charge = maintenance_charge;
        this.charge_type = charge_type;
        this.booking_amount = booking_amount;
        this.expected_rental = expected_rental;
        this.rental_membership = rental_membership;
        this.annual_dues_payble = annual_dues_payble;
        this.idea_for_businesses = idea_for_businesses;
        this.ideal_for_use = ideal_for_use;
        this.office_previous_used_for = office_previous_used_for;
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
        this.land_zone = land_zone;
        this.special_zone = special_zone;
        this.approval_date = approval_date;
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
        this.number_of_staircase = number_of_staircase;
        this.direction_facing = direction_facing;
        this.facing_road = facing_road;
        this.additional_price = additional_price;
        this.additional_rooms = additional_rooms;
        this.overlooking = overlooking;
        this.parking_facility = parking_facility;
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

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
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

    public String getNumber_of_staircase() {
        return number_of_staircase;
    }

    public void setNumber_of_staircase(String number_of_staircase) {
        this.number_of_staircase = number_of_staircase;
    }

    public String getDirection_facing() {
        return direction_facing;
    }

    public void setDirection_facing(String direction_facing) {
        this.direction_facing = direction_facing;
    }

    public String getFacing_road() {
        return facing_road;
    }

    public void setFacing_road(String facing_road) {
        this.facing_road = facing_road;
    }

    public ArrayList<String> getAdditional_price() {
        return additional_price;
    }

    public void setAdditional_price(ArrayList<String> additional_price) {
        this.additional_price = additional_price;
    }

    public ArrayList<String> getAdditional_rooms() {
        return additional_rooms;
    }

    public void setAdditional_rooms(ArrayList<String> additional_rooms) {
        this.additional_rooms = additional_rooms;
    }

    public ArrayList<String> getOverlooking() {
        return overlooking;
    }

    public void setOverlooking(ArrayList<String> overlooking) {
        this.overlooking = overlooking;
    }

    public ArrayList<String> getParking_facility() {
        return parking_facility;
    }

    public void setParking_facility(ArrayList<String> parking_facility) {
        this.parking_facility = parking_facility;
    }
}