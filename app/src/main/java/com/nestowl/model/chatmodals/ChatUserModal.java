package com.nestowl.model.chatmodals;

import java.util.ArrayList;

public class ChatUserModal {
    String id,name,email,email_verified_at,phone,last_seen,is_online,is_active,about,photo_url,activation_code,created_at,updated_at,is_system,player_id,privacygender,deleted_at,language,
            is_super_admin,role_name,user_status,reported_user;
//    common_groups
    boolean is_subscribed,is_blocked_by_auth_user,is_blocked,is_my_contact,is_req_send_receive,is_private_account;
    ArrayList<ChatUserRoleModal> roles;

    public ChatUserModal() {
    }

    public ChatUserModal(String id, String name, String email, String email_verified_at, String phone, String last_seen, String is_online, String is_active, String about, String photo_url, String activation_code, String created_at, String updated_at, String is_system, String player_id, String privacygender, String deleted_at, String language, String is_super_admin, String role_name, String user_status, String reported_user, String common_groups, boolean is_subscribed, boolean is_blocked_by_auth_user, boolean is_blocked, boolean is_my_contact, boolean is_req_send_receive, boolean is_private_account, ArrayList<ChatUserRoleModal> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.phone = phone;
        this.last_seen = last_seen;
        this.is_online = is_online;
        this.is_active = is_active;
        this.about = about;
        this.photo_url = photo_url;
        this.activation_code = activation_code;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_system = is_system;
        this.player_id = player_id;
        this.privacygender = privacygender;
        this.deleted_at = deleted_at;
        this.language = language;
        this.is_super_admin = is_super_admin;
        this.role_name = role_name;
        this.user_status = user_status;
        this.reported_user = reported_user;
//        this.common_groups = common_groups;
        this.is_subscribed = is_subscribed;
        this.is_blocked_by_auth_user = is_blocked_by_auth_user;
        this.is_blocked = is_blocked;
        this.is_my_contact = is_my_contact;
        this.is_req_send_receive = is_req_send_receive;
        this.is_private_account = is_private_account;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getActivation_code() {
        return activation_code;
    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
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

    public String getIs_system() {
        return is_system;
    }

    public void setIs_system(String is_system) {
        this.is_system = is_system;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getPrivacygender() {
        return privacygender;
    }

    public void setPrivacygender(String privacygender) {
        this.privacygender = privacygender;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIs_super_admin() {
        return is_super_admin;
    }

    public void setIs_super_admin(String is_super_admin) {
        this.is_super_admin = is_super_admin;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getReported_user() {
        return reported_user;
    }

    public void setReported_user(String reported_user) {
        this.reported_user = reported_user;
    }

//    public String getCommon_groups() {
//        return common_groups;
//    }
//
//    public void setCommon_groups(String common_groups) {
//        this.common_groups = common_groups;
//    }

    public boolean isIs_subscribed() {
        return is_subscribed;
    }

    public void setIs_subscribed(boolean is_subscribed) {
        this.is_subscribed = is_subscribed;
    }

    public boolean isIs_blocked_by_auth_user() {
        return is_blocked_by_auth_user;
    }

    public void setIs_blocked_by_auth_user(boolean is_blocked_by_auth_user) {
        this.is_blocked_by_auth_user = is_blocked_by_auth_user;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public boolean isIs_my_contact() {
        return is_my_contact;
    }

    public void setIs_my_contact(boolean is_my_contact) {
        this.is_my_contact = is_my_contact;
    }

    public boolean isIs_req_send_receive() {
        return is_req_send_receive;
    }

    public void setIs_req_send_receive(boolean is_req_send_receive) {
        this.is_req_send_receive = is_req_send_receive;
    }

    public boolean isIs_private_account() {
        return is_private_account;
    }

    public void setIs_private_account(boolean is_private_account) {
        this.is_private_account = is_private_account;
    }

    public ArrayList<ChatUserRoleModal> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<ChatUserRoleModal> roles) {
        this.roles = roles;
    }
}
