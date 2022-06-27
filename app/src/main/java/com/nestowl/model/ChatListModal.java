package com.nestowl.model;

public class ChatListModal {
    String name, massage, date, dpurl,  imageUrl, uid,type,time,seen,filename,fileSize,unreadCount,replayMassage,replayType;
    Boolean isOnline, isImage, isUnread,isReply;

    public ChatListModal() {
    }

    public ChatListModal(String name, String massage, String date, String dpurl, String imageUrl, String uid, String type, String time, String seen, String filename, String fileSize, String unreadCount, String replayMassage, String replayType, Boolean isOnline, Boolean isImage, Boolean isUnread, Boolean isReply) {
        this.name = name;
        this.massage = massage;
        this.date = date;
        this.dpurl = dpurl;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.type = type;
        this.time = time;
        this.seen = seen;
        this.filename = filename;
        this.fileSize = fileSize;
        this.unreadCount = unreadCount;
        this.replayMassage = replayMassage;
        this.replayType = replayType;
        this.isOnline = isOnline;
        this.isImage = isImage;
        this.isUnread = isUnread;
        this.isReply = isReply;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(String unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getReplayMassage() {
        return replayMassage;
    }

    public void setReplayMassage(String replayMassage) {
        this.replayMassage = replayMassage;
    }

    public String getReplayType() {
        return replayType;
    }

    public void setReplayType(String replayType) {
        this.replayType = replayType;
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

    public Boolean getReply() {
        return isReply;
    }

    public void setReply(Boolean reply) {
        isReply = reply;
    }
}
