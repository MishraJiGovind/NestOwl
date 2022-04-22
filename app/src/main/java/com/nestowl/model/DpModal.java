package com.nestowl.model;

public class DpModal {
    String id,user_id,logo,cover,office_photo,profile_photo;

    public DpModal() {
    }

    public DpModal(String id, String user_id, String logo, String cover, String office_photo, String profile_photo) {
        this.id = id;
        this.user_id = user_id;
        this.logo = logo;
        this.cover = cover;
        this.office_photo = office_photo;
        this.profile_photo = profile_photo;
    }

    public String getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getLogo() {
        return logo;
    }

    public String getCover() {
        return cover;
    }

    public String getOffice_photo() {
        return office_photo;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
}
