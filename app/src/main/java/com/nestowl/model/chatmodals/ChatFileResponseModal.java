package com.nestowl.model.chatmodals;

public class ChatFileResponseModal {
    String attachment,message_type,file_name,unique_code;

    public ChatFileResponseModal() {
    }

    public ChatFileResponseModal(String attachment, String message_type, String file_name, String unique_code) {
        this.attachment = attachment;
        this.message_type = message_type;
        this.file_name = file_name;
        this.unique_code = unique_code;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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

    public String getUnique_code() {
        return unique_code;
    }

    public void setUnique_code(String unique_code) {
        this.unique_code = unique_code;
    }
}
