package com.nestowl.model;

public class ChatListModal {
    String name, massage, date, dpurl,  imageUrl, uid;
    Boolean isOnline, isImage, isUnread;

    public ChatListModal() {
    }

    public ChatListModal(String name, String massage, String date, String dpurl, String imageUrl, String uid, Boolean isOnline, Boolean isImage, Boolean isUnread) {
        this.name = name;
        this.massage = massage;
        this.date = date;
        this.dpurl = dpurl;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.isOnline = isOnline;
        this.isImage = isImage;
        this.isUnread = isUnread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDpurl() {
        return dpurl;
    }

    public void setDpurl(String dpurl) {
        this.dpurl = dpurl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getImage() {
        return isImage;
    }

    public void setImage(Boolean image) {
        isImage = image;
    }

    public Boolean getUnread() {
        return isUnread;
    }

    public void setUnread(Boolean unread) {
        isUnread = unread;
    }
}
