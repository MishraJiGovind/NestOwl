package com.nestowl.model.chatmodals;

public class ChatUserRolePivotModal {
    String model_id,role_id,model_type;

    public ChatUserRolePivotModal() {
    }

    public ChatUserRolePivotModal(String model_id, String role_id, String model_type) {
        this.model_id = model_id;
        this.role_id = role_id;
        this.model_type = model_type;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }
}
