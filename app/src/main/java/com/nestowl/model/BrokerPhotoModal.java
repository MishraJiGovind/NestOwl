package com.nestowl.model;

public class BrokerPhotoModal {
    String id,user_id,logo,cover,office_photo,profile_photo;

    public BrokerPhotoModal() {
    }

    public BrokerPhotoModal(String id, String user_id, String logo, String cover, String office_photo, String profile_photo) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getOffice_photo() {
        return office_photo;
    }

    public void setOffice_photo(String office_photo) {
        this.office_photo = office_photo;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }
}
