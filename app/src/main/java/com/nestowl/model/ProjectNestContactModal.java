package com.nestowl.model;

public class ProjectNestContactModal {
    String id,user_id,nest_id,name,mobile,email;

    public ProjectNestContactModal() {
    }

    public ProjectNestContactModal(String id, String user_id, String nest_id, String name, String mobile, String email) {
        this.id = id;
        this.user_id = user_id;
        this.nest_id = nest_id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
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

    public String getNest_id() {
        return nest_id;
    }

    public void setNest_id(String nest_id) {
        this.nest_id = nest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
