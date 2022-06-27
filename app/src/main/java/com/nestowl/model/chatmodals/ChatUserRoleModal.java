package com.nestowl.model.chatmodals;

public class ChatUserRoleModal {
    String id,name,guard_name,is_default,created_at,updated_at;
    ChatUserRolePivotModal pivot;

    public ChatUserRoleModal() {
    }

    public ChatUserRoleModal(String id, String name, String guard_name, String is_default, String created_at, String updated_at, ChatUserRolePivotModal pivot) {
        this.id = id;
        this.name = name;
        this.guard_name = guard_name;
        this.is_default = is_default;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.pivot = pivot;
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

    public String getGuard_name() {
        return guard_name;
    }

    public void setGuard_name(String guard_name) {
        this.guard_name = guard_name;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
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

    public ChatUserRolePivotModal getPivot() {
        return pivot;
    }

    public void setPivot(ChatUserRolePivotModal pivot) {
        this.pivot = pivot;
    }
}
