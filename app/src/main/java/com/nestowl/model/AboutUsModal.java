package com.nestowl.model;

public class AboutUsModal {
    String id,title,alias,introtext,article_description,status,catid,ordering,img,img1,metatitle,metadescription,metakeyword,created_at,updated_at,mtitle,malias,banner_id;

    public AboutUsModal() {
    }

    public AboutUsModal(String id, String title, String alias, String introtext, String article_description, String status, String catid, String ordering, String img, String img1, String metatitle, String metadescription, String metakeyword, String created_at, String updated_at, String mtitle, String malias, String banner_id) {
        this.id = id;
        this.title = title;
        this.alias = alias;
        this.introtext = introtext;
        this.article_description = article_description;
        this.status = status;
        this.catid = catid;
        this.ordering = ordering;
        this.img = img;
        this.img1 = img1;
        this.metatitle = metatitle;
        this.metadescription = metadescription;
        this.metakeyword = metakeyword;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.mtitle = mtitle;
        this.malias = malias;
        this.banner_id = banner_id;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIntrotext() {
        return introtext;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    public String getArticle_description() {
        return article_description;
    }

    public void setArticle_description(String article_description) {
        this.article_description = article_description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
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

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMalias() {
        return malias;
    }

    public void setMalias(String malias) {
        this.malias = malias;
    }

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }
}
