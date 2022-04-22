package com.nestowl.model;

import java.util.ArrayList;

public class NotificationExtraModal {
    String lastupdated;
    ArrayList<NotificationModal> data;

    public NotificationExtraModal() {
    }

    public NotificationExtraModal(String lastupdated, ArrayList<NotificationModal> data) {
        this.lastupdated = lastupdated;
        this.data = data;
    }

    public String getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(String lastupdated) {
        this.lastupdated = lastupdated;
    }

    public ArrayList<NotificationModal> getData() {
        return data;
    }

    public void setData(ArrayList<NotificationModal> data) {
        this.data = data;
    }
}
