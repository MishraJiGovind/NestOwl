package com.nestowl.model.chatmodals;

public class ChatConversationModal {
    String id,from_id,to_id,to_type,message,status,message_type,file_name,created_at,updated_at,reply_to,url_details,time_from_now_in_min,is_group;
    ChatUserModal sender,receiver;
    ChatConversationModal reply_message;

    public ChatConversationModal() {
    }

    public ChatConversationModal(String id, String from_id, String to_id, String to_type, String message, String status, String message_type, String file_name, String created_at, String updated_at, String reply_to, String url_details, String time_from_now_in_min, String is_group, ChatUserModal sender, ChatUserModal receiver, ChatConversationModal reply_message) {
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
        this.sender = sender;
        this.receiver = receiver;
        this.reply_message = reply_message;
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

    public ChatUserModal getSender() {
        return sender;
    }

    public void setSender(ChatUserModal sender) {
        this.sender = sender;
    }

    public ChatUserModal getReceiver() {
        return receiver;
    }

    public void setReceiver(ChatUserModal receiver) {
        this.receiver = receiver;
    }

    public ChatConversationModal getReply_message() {
        return reply_message;
    }

    public void setReply_message(ChatConversationModal reply_message) {
        this.reply_message = reply_message;
    }
}
