package com.nestowl.model;

import com.google.gson.annotations.SerializedName;

public class ArticleModal {
    String user_id,id,title,slug,postdate,image,image1,banner_img,body,view_count,metatitle,metadescription,metakeyword,created_by;
    @SerializedName("short")
    String shorts;

    public ArticleModal() {
    }

    public ArticleModal(String user_id, String id, String title, String slug, String postdate, String image, String image1, String banner_img, String body, String view_count, String metatitle, String metadescription, String metakeyword, String created_by, String shorts) {
        this.user_id = user_id;
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.postdate = postdate;
        this.image = image;
        this.image1 = image1;
        this.banner_img = banner_img;
        this.body = body;
        this.view_count = view_count;
        this.metatitle = metatitle;
        this.metadescription = metadescription;
        this.metakeyword = metakeyword;
        this.created_by = created_by;
        this.shorts = shorts;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getMetatitle() {
        return metatitle;
    }

    public void setMetatitle(String metatitle) {
        this.metatitle = metatitle;
    }

    public String getMetadescription() {
        return metadescription;
    }

    public void setMetadescription(String metadescription) {
        this.metadescription = metadescription;
    }

    public String getMetakeyword() {
        return metakeyword;
    }

    public void setMetakeyword(String metakeyword) {
        this.metakeyword = metakeyword;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getShorts() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }
}
