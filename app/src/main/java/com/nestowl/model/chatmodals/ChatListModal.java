package com.nestowl.model.chatmodals;

public class ChatListModal {
    String latest_id,user_id,is_online,is_active,group_id,unread_count,id,from_id,to_id,to_type,message,status,message_type,file_name,created_at,updated_at,reply_to,url_details,time_from_now_in_min,is_group,reply_message;
    ChatUserModal user;

    public ChatListModal() {
    }

    public ChatListModal(String latest_id, String user_id, String is_online, String is_active, String group_id, String unread_count, String id, String from_id, String to_id, String to_type, String message, String status, String message_type, String file_name, String created_at, String updated_at, String reply_to, String url_details, String time_from_now_in_min, String is_group, String reply_message, ChatUserModal user) {
        this.latest_id = latest_id;
        this.user_id = user_id;
        this.is_online = is_online;
        this.is_active = is_active;
        this.group_id = group_id;
        this.unread_count = unread_count;
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.to_type = to_type;
        this.message = message;
        this.status = status;
        this.message_type = message_type;
        this.file_name = file_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.reply_to = reply_to;
        this.url_details = url_details;
        this.time_from_now_in_min = time_from_now_in_min;
        this.is_group = is_group;
        this.reply_message = reply_message;
        this.user = user;
    }

    public String getLatest_id() {
        return latest_id;
    }

    public void setLatest_id(String latest_id) {
        this.latest_id = latest_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(String unread_count) {
        this.unread_count = unread_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getTo_type() {
        return to_type;
    }

    public void setTo_type(String to_type) {
        this.to_type = to_type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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

    public String getReply_to() {
        return reply_to;
    }

    public void setReply_to(String reply_to) {
        this.reply_to = reply_to;
    }

    public String getUrl_details() {
        return url_details;
    }

    public void setUrl_details(String url_details) {
        this.url_details = url_details;
    }

    public String getTime_from_now_in_min() {
        return time_from_now_in_min;
    }

    public void setTime_from_now_in_min(String time_from_now_in_min) {
        this.time_from_now_in_min = time_from_now_in_min;
    }

    public String getIs_group() {
        return is_group;
    }

    public void setIs_group(String is_group) {
        this.is_group = is_group;
    }

    public String getReply_message() {
        return reply_message;
    }

    public void setReply_message(String reply_message) {
        this.reply_message = reply_message;
    }

    public ChatUserModal getUser() {
        return user;
    }

    public void setUser(ChatUserModal user) {
        this.user = user;
    }
}
