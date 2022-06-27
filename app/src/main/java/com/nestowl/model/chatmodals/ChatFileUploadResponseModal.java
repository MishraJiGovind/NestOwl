package com.nestowl.model.chatmodals;

import java.util.ArrayList;

public class ChatFileUploadResponseModal {
    Boolean success;
    ArrayList<ChatFileResponseModal>data;

    public ChatFileUploadResponseModal() {
    }

    public ChatFileUploadResponseModal(Boolean success, ArrayList<ChatFileResponseModal> data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<ChatFileResponseModal> getData() {
        return data;
    }

    public void setData(ArrayList<ChatFileResponseModal> data) {
        this.data = data;
    }
}
