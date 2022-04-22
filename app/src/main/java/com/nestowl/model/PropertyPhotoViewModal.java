package com.nestowl.model;

public class PropertyPhotoViewModal {
    String id,name,type,filename,category_id;

    public PropertyPhotoViewModal() {
    }

    public PropertyPhotoViewModal(String id, String name, String type, String filename, String category_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filename = filename;
        this.category_id = category_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }
}
