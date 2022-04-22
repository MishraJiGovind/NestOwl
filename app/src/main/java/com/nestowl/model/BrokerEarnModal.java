package com.nestowl.model;

public class BrokerEarnModal {
    String id,user_id,join_now,benifit_to_you;

    public BrokerEarnModal() {
    }

    public BrokerEarnModal(String id, String user_id, String join_now, String benifit_to_you) {
        this.id = id;
        this.user_id = user_id;
        this.join_now = join_now;
        this.benifit_to_you = benifit_to_you;
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

    public String getJoin_now() {
        return join_now;
    }

    public void setJoin_now(String join_now) {
        this.join_now = join_now;
    }

    public String getBenifit_to_you() {
        return benifit_to_you;
    }

    public void setBenifit_to_you(String benifit_to_you) {
        this.benifit_to_you = benifit_to_you;
    }
}
