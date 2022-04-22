package com.nestowl.model;

public class NotificationServerModal {
    String id,user_id,title,notification,type,pid,readmsg,todate,status,created_at,updated_at;

    public NotificationServerModal() {
    }

    public NotificationServerModal(String id, String user_id, String title, String notification, String type, String pid, String readmsg, String todate, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.notification = notification;
        this.type = type;
        this.pid = pid;
        this.readmsg = readmsg;
        this.todate = todate;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getReadmsg() {
        return readmsg;
    }

    public void setReadmsg(String readmsg) {
        this.readmsg = readmsg;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
