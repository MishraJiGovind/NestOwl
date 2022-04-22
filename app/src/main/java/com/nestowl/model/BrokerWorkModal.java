package com.nestowl.model;

import java.util.ArrayList;

public class BrokerWorkModal {
    String id,user_id,pincode,agent_dealers,trade_license_number,date_of_Issue,breif_description;
    ArrayList<LocalitiesModal> working_localities;

    public BrokerWorkModal() {
    }

    public BrokerWorkModal(String id, String user_id, String pincode, String agent_dealers, String trade_license_number, String date_of_Issue, String breif_description, ArrayList<LocalitiesModal> working_localities) {
        this.id = id;
        this.user_id = user_id;
        this.pincode = pincode;
        this.agent_dealers = agent_dealers;
        this.trade_license_number = trade_license_number;
        this.date_of_Issue = date_of_Issue;
        this.breif_description = breif_description;
        this.working_localities = working_localities;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPincode() {
        return pincode;
    }

    public String getAgent_dealers() {
        return agent_dealers;
    }

    public String getTrade_license_number() {
        return trade_license_number;
    }

    public String getDate_of_Issue() {
        return date_of_Issue;
    }

    public String getBreif_description() {
        return breif_description;
    }

    public ArrayList<LocalitiesModal> getWorking_localities() {
        return working_localities;
    }
}
