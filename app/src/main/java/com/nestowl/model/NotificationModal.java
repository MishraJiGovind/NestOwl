package com.nestowl.model;

public class NotificationModal {
    String id,title,body,time,uri,propertyId,userId,where;
    boolean isUnread;

    public NotificationModal() {
    }

    public NotificationModal(String id, String title, String body, String time, String uri, String propertyId, String userId, String where, boolean isUnread) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
        this.uri = uri;
        this.propertyId = propertyId;
        this.userId = userId;
        this.where = where;
        this.isUnread = isUnread;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public boolean isUnread() {
        return isUnread;
    }

    public void setUnread(boolean unread) {
        isUnread = unread;
    }
}
