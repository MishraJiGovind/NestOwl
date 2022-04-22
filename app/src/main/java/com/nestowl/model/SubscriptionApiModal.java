package com.nestowl.model;

import com.google.gson.annotations.SerializedName;

public class SubscriptionApiModal {
    String id,user_role,plan_type,plan,price,number_of_listings,validity,proposal_from_nest_professionals,property_self_verification,visiblity,public_response_rate,position_on_search,image_upload_quantity,
    property_description,chat_feature,fezze_and_resume_listing_feature,contact_privacy_feature,mail_promotion,tag_on_listing,listing_rank_boost,number_of_free_listings,number_of_free_boost,response_rate_on_live_listing,seller_proposal_leads,
    buyer_proposal_leads,join_earn_more_group,join_indias_heros_more_group,local_nest_professinals_features,tie_up_with_builders,tie_up_with_nest_professinals,response_rate,photo_suite,number_of_boost,profile_page,microsite,listing_view_detail,facebook_marketing,
    google_marketing,offer_proposal_by_buyer_on_listing,freeze_and_resume_project_feature,status,created_at,updated_at;
    @SerializedName("package")
    String packages;

    public SubscriptionApiModal() {
    }

    public SubscriptionApiModal(String id, String user_role, String plan_type, String plan, String price, String number_of_listings, String validity, String proposal_from_nest_professionals, String property_self_verification, String visiblity, String public_response_rate, String position_on_search, String image_upload_quantity, String property_description, String chat_feature, String fezze_and_resume_listing_feature, String contact_privacy_feature, String mail_promotion, String tag_on_listing, String listing_rank_boost, String number_of_free_listings, String number_of_free_boost, String response_rate_on_live_listing, String seller_proposal_leads, String buyer_proposal_leads, String join_earn_more_group, String join_indias_heros_more_group, String local_nest_professinals_features, String tie_up_with_builders, String tie_up_with_nest_professinals, String response_rate, String photo_suite, String number_of_boost, String profile_page, String microsite, String listing_view_detail, String facebook_marketing, String google_marketing, String offer_proposal_by_buyer_on_listing, String freeze_and_resume_project_feature, String status, String created_at, String updated_at, String packages) {
        this.id = id;
        this.user_role = user_role;
        this.plan_type = plan_type;
        this.plan = plan;
        this.price = price;
        this.number_of_listings = number_of_listings;
        this.validity = validity;
        this.proposal_from_nest_professionals = proposal_from_nest_professionals;
        this.property_self_verification = property_self_verification;
        this.visiblity = visiblity;
        this.public_response_rate = public_response_rate;
        this.position_on_search = position_on_search;
        this.image_upload_quantity = image_upload_quantity;
        this.property_description = property_description;
        this.chat_feature = chat_feature;
        this.fezze_and_resume_listing_feature = fezze_and_resume_listing_feature;
        this.contact_privacy_feature = contact_privacy_feature;
        this.mail_promotion = mail_promotion;
        this.tag_on_listing = tag_on_listing;
        this.listing_rank_boost = listing_rank_boost;
        this.number_of_free_listings = number_of_free_listings;
        this.number_of_free_boost = number_of_free_boost;
        this.response_rate_on_live_listing = response_rate_on_live_listing;
        this.seller_proposal_leads = seller_proposal_leads;
        this.buyer_proposal_leads = buyer_proposal_leads;
        this.join_earn_more_group = join_earn_more_group;
        this.join_indias_heros_more_group = join_indias_heros_more_group;
        this.local_nest_professinals_features = local_nest_professinals_features;
        this.tie_up_with_builders = tie_up_with_builders;
        this.tie_up_with_nest_professinals = tie_up_with_nest_professinals;
        this.response_rate = response_rate;
        this.photo_suite = photo_suite;
        this.number_of_boost = number_of_boost;
        this.profile_page = profile_page;
        this.microsite = microsite;
        this.listing_view_detail = listing_view_detail;
        this.facebook_marketing = facebook_marketing;
        this.google_marketing = google_marketing;
        this.offer_proposal_by_buyer_on_listing = offer_proposal_by_buyer_on_listing;
        this.freeze_and_resume_project_feature = freeze_and_resume_project_feature;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.packages = packages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber_of_listings() {
        return number_of_listings;
    }

    public void setNumber_of_listings(String number_of_listings) {
        this.number_of_listings = number_of_listings;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getProposal_from_nest_professionals() {
        return proposal_from_nest_professionals;
    }

    public void setProposal_from_nest_professionals(String proposal_from_nest_professionals) {
        this.proposal_from_nest_professionals = proposal_from_nest_professionals;
    }

    public String getProperty_self_verification() {
        return property_self_verification;
    }

    public void setProperty_self_verification(String property_self_verification) {
        this.property_self_verification = property_self_verification;
    }

    public String getVisiblity() {
        return visiblity;
    }

    public void setVisiblity(String visiblity) {
        this.visiblity = visiblity;
    }

    public String getPublic_response_rate() {
        return public_response_rate;
    }

    public void setPublic_response_rate(String public_response_rate) {
        this.public_response_rate = public_response_rate;
    }

    public String getPosition_on_search() {
        return position_on_search;
    }

    public void setPosition_on_search(String position_on_search) {
        this.position_on_search = position_on_search;
    }

    public String getImage_upload_quantity() {
        return image_upload_quantity;
    }

    public void setImage_upload_quantity(String image_upload_quantity) {
        this.image_upload_quantity = image_upload_quantity;
    }

    public String getProperty_description() {
        return property_description;
    }

    public void setProperty_description(String property_description) {
        this.property_description = property_description;
    }

    public String getChat_feature() {
        return chat_feature;
    }

    public void setChat_feature(String chat_feature) {
        this.chat_feature = chat_feature;
    }

    public String getFezze_and_resume_listing_feature() {
        return fezze_and_resume_listing_feature;
    }

    public void setFezze_and_resume_listing_feature(String fezze_and_resume_listing_feature) {
        this.fezze_and_resume_listing_feature = fezze_and_resume_listing_feature;
    }

    public String getContact_privacy_feature() {
        return contact_privacy_feature;
    }

    public void setContact_privacy_feature(String contact_privacy_feature) {
        this.contact_privacy_feature = contact_privacy_feature;
    }

    public String getMail_promotion() {
        return mail_promotion;
    }

    public void setMail_promotion(String mail_promotion) {
        this.mail_promotion = mail_promotion;
    }

    public String getTag_on_listing() {
        return tag_on_listing;
    }

    public void setTag_on_listing(String tag_on_listing) {
        this.tag_on_listing = tag_on_listing;
    }

    public String getListing_rank_boost() {
        return listing_rank_boost;
    }

    public void setListing_rank_boost(String listing_rank_boost) {
        this.listing_rank_boost = listing_rank_boost;
    }

    public String getNumber_of_free_listings() {
        return number_of_free_listings;
    }

    public void setNumber_of_free_listings(String number_of_free_listings) {
        this.number_of_free_listings = number_of_free_listings;
    }

    public String getNumber_of_free_boost() {
        return number_of_free_boost;
    }

    public void setNumber_of_free_boost(String number_of_free_boost) {
        this.number_of_free_boost = number_of_free_boost;
    }

    public String getResponse_rate_on_live_listing() {
        return response_rate_on_live_listing;
    }

    public void setResponse_rate_on_live_listing(String response_rate_on_live_listing) {
        this.response_rate_on_live_listing = response_rate_on_live_listing;
    }

    public String getSeller_proposal_leads() {
        return seller_proposal_leads;
    }

    public void setSeller_proposal_leads(String seller_proposal_leads) {
        this.seller_proposal_leads = seller_proposal_leads;
    }

    public String getBuyer_proposal_leads() {
        return buyer_proposal_leads;
    }

    public void setBuyer_proposal_leads(String buyer_proposal_leads) {
        this.buyer_proposal_leads = buyer_proposal_leads;
    }

    public String getJoin_earn_more_group() {
        return join_earn_more_group;
    }

    public void setJoin_earn_more_group(String join_earn_more_group) {
        this.join_earn_more_group = join_earn_more_group;
    }

    public String getJoin_indias_heros_more_group() {
        return join_indias_heros_more_group;
    }

    public void setJoin_indias_heros_more_group(String join_indias_heros_more_group) {
        this.join_indias_heros_more_group = join_indias_heros_more_group;
    }

    public String getLocal_nest_professinals_features() {
        return local_nest_professinals_features;
    }

    public void setLocal_nest_professinals_features(String local_nest_professinals_features) {
        this.local_nest_professinals_features = local_nest_professinals_features;
    }

    public String getTie_up_with_builders() {
        return tie_up_with_builders;
    }

    public void setTie_up_with_builders(String tie_up_with_builders) {
        this.tie_up_with_builders = tie_up_with_builders;
    }

    public String getTie_up_with_nest_professinals() {
        return tie_up_with_nest_professinals;
    }

    public void setTie_up_with_nest_professinals(String tie_up_with_nest_professinals) {
        this.tie_up_with_nest_professinals = tie_up_with_nest_professinals;
    }

    public String getResponse_rate() {
        return response_rate;
    }

    public void setResponse_rate(String response_rate) {
        this.response_rate = response_rate;
    }

    public String getPhoto_suite() {
        return photo_suite;
    }

    public void setPhoto_suite(String photo_suite) {
        this.photo_suite = photo_suite;
    }

    public String getNumber_of_boost() {
        return number_of_boost;
    }

    public void setNumber_of_boost(String number_of_boost) {
        this.number_of_boost = number_of_boost;
    }

    public String getProfile_page() {
        return profile_page;
    }

    public void setProfile_page(String profile_page) {
        this.profile_page = profile_page;
    }

    public String getMicrosite() {
        return microsite;
    }

    public void setMicrosite(String microsite) {
        this.microsite = microsite;
    }

    public String getListing_view_detail() {
        return listing_view_detail;
    }

    public void setListing_view_detail(String listing_view_detail) {
        this.listing_view_detail = listing_view_detail;
    }

    public String getFacebook_marketing() {
        return facebook_marketing;
    }

    public void setFacebook_marketing(String facebook_marketing) {
        this.facebook_marketing = facebook_marketing;
    }

    public String getGoogle_marketing() {
        return google_marketing;
    }

    public void setGoogle_marketing(String google_marketing) {
        this.google_marketing = google_marketing;
    }

    public String getOffer_proposal_by_buyer_on_listing() {
        return offer_proposal_by_buyer_on_listing;
    }

    public void setOffer_proposal_by_buyer_on_listing(String offer_proposal_by_buyer_on_listing) {
        this.offer_proposal_by_buyer_on_listing = offer_proposal_by_buyer_on_listing;
    }

    public String getFreeze_and_resume_project_feature() {
        return freeze_and_resume_project_feature;
    }

    public void setFreeze_and_resume_project_feature(String freeze_and_resume_project_feature) {
        this.freeze_and_resume_project_feature = freeze_and_resume_project_feature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }
}
