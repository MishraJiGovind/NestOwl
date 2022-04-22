package com.nestowl.model;

public class ProfileDashBoardModal {
    String status,message,TotalNoofLead,Seller,Buyer,NestPro,TotalNoofLiveListing,TotalNoofListing,RemainingTotalListing,TillTodayProposalAccepted,TillTodayProposalRejected,TotalLeads,TotalListingposted;

    public ProfileDashBoardModal() {
    }

    public ProfileDashBoardModal(String status, String message, String totalNoofLead, String seller, String buyer, String nestPro, String totalNoofLiveListing, String totalNoofListing, String remainingTotalListing, String tillTodayProposalAccepted, String tillTodayProposalRejected, String totalLeads, String totalListingposted) {
        this.status = status;
        this.message = message;
        TotalNoofLead = totalNoofLead;
        Seller = seller;
        Buyer = buyer;
        NestPro = nestPro;
        TotalNoofLiveListing = totalNoofLiveListing;
        TotalNoofListing = totalNoofListing;
        RemainingTotalListing = remainingTotalListing;
        TillTodayProposalAccepted = tillTodayProposalAccepted;
        TillTodayProposalRejected = tillTodayProposalRejected;
        TotalLeads = totalLeads;
        TotalListingposted = totalListingposted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalNoofLead() {
        return TotalNoofLead;
    }

    public void setTotalNoofLead(String totalNoofLead) {
        TotalNoofLead = totalNoofLead;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getBuyer() {
        return Buyer;
    }

    public void setBuyer(String buyer) {
        Buyer = buyer;
    }

    public String getNestPro() {
        return NestPro;
    }

    public void setNestPro(String nestPro) {
        NestPro = nestPro;
    }

    public String getTotalNoofLiveListing() {
        return TotalNoofLiveListing;
    }

    public void setTotalNoofLiveListing(String totalNoofLiveListing) {
        TotalNoofLiveListing = totalNoofLiveListing;
    }

    public String getTotalNoofListing() {
        return TotalNoofListing;
    }

    public void setTotalNoofListing(String totalNoofListing) {
        TotalNoofListing = totalNoofListing;
    }

    public String getRemainingTotalListing() {
        return RemainingTotalListing;
    }

    public void setRemainingTotalListing(String remainingTotalListing) {
        RemainingTotalListing = remainingTotalListing;
    }

    public String getTillTodayProposalAccepted() {
        return TillTodayProposalAccepted;
    }

    public void setTillTodayProposalAccepted(String tillTodayProposalAccepted) {
        TillTodayProposalAccepted = tillTodayProposalAccepted;
    }

    public String getTillTodayProposalRejected() {
        return TillTodayProposalRejected;
    }

    public void setTillTodayProposalRejected(String tillTodayProposalRejected) {
        TillTodayProposalRejected = tillTodayProposalRejected;
    }

    public String getTotalLeads() {
        return TotalLeads;
    }

    public void setTotalLeads(String totalLeads) {
        TotalLeads = totalLeads;
    }

    public String getTotalListingposted() {
        return TotalListingposted;
    }

    public void setTotalListingposted(String totalListingposted) {
        TotalListingposted = totalListingposted;
    }
}
