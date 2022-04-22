package com.nestowl.model;

public class ReviewsModal {
    String user_id,p_id,type,star_review,about_review,project_photo,terms;
    String name,dp,types;

    public ReviewsModal() {
    }

    public ReviewsModal(String user_id, String p_id, String type, String star_review, String about_review, String project_photo, String terms, String name, String dp, String types) {
        this.user_id = user_id;
        this.p_id = p_id;
        this.type = type;
        this.star_review = star_review;
        this.about_review = about_review;
        this.project_photo = project_photo;
        this.terms = terms;
        this.name = name;
        this.dp = dp;
        this.types = types;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStar_review() {
        return star_review;
    }

    public void setStar_review(String star_review) {
        this.star_review = star_review;
    }

    public String getAbout_review() {
        return about_review;
    }

    public void setAbout_review(String about_review) {
        this.about_review = about_review;
    }

    public String getProject_photo() {
        return project_photo;
    }

    public void setProject_photo(String project_photo) {
        this.project_photo = project_photo;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}
