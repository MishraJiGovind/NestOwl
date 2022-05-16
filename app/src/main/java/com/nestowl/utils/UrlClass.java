package com.nestowl.utils;

public class UrlClass {
    public static final String BASE_URL="https://www.nestowl.in/";
    public static final String BASE_URL2="https://www.nestowl.in";
    public static final String MAP_API="AIzaSyBXs-UohXuOxHKp7bxT26tKHCW5cAcodF8";
    public static final String GET_LAT_LOG_BY_ADDRESS="https://maps.googleapis.com/maps/api/geocode/json?address=";
    public static final String GET_LAT_LOG_BY_ADDRESS_ENDING_PART="&key="+MAP_API;
    public static final String LOGIN=BASE_URL+"api/login";
    public static final String SIGN_UP=BASE_URL+"api/signup";
    public static final String OTP=BASE_URL+"api/otp";
    public static final String OTP_MOBILE=BASE_URL+"api/otps";
    public static final String OTP_Check=BASE_URL+"api/otpcheck";
    public static final String OTP_Check_MOBILE=BASE_URL+"api/otpscheck";
    public static final String LOCALITY=BASE_URL+"api/post-property/pincode";
    public static final String PROJECT_SOCIETY=BASE_URL+"api/post-property/projectsociety";
    public static final String LOCATION_SUGGESTION=BASE_URL+"api/post-property/location";
    public static final String All_CITY=BASE_URL+"api/project/allcity";
    public static final String LOCALITIES=BASE_URL+"api/projectaddress";
    public static final String PHOTOS_VERIFICATION=BASE_URL+"api/broker/photo";
    public static final String CHECKSUM_GENRATE=BASE_URL+"public/paytm-checksum/";
    public static final String BUY_NOW=BASE_URL+"api/payment";
    public static final String PAYMENT_STATUS=BASE_URL+"api/payments/status";
    public static final String MID="ycTaet64373589601419";
    public static final String PYTM_URL="https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
//    public static final String PYTM_URL="https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=";
//    post requiements
    public static final String GENRATE_PROPERTY_ID=BASE_URL+"api/post-property/generateid/";

    public static final String POST_REQUREMENT_BASIC=BASE_URL+"api/requirement/requirementbasic";
    public static final String POST_REQUREMENT_FEATURES=BASE_URL+"api/requirement/features";

    public static final String POST_PROPERY_FEATURES=BASE_URL+"api/post-property/postfeatures";
    public static final String POST_PROPERY_PRICING=BASE_URL+"api/post-property/postprice";
    public static final String POST_PROPERY_PHOTO=BASE_URL+"api/post-property/postphoto/";
    public static final String POST_PROPERY_AMENTHIES=BASE_URL+"api/post-property/postamenities";
    public static final String POST_PROPERY_VARIFICATION=BASE_URL+"api/post-property/postverification";
    public static final String SEARCH_NEST_PRO=BASE_URL+"api/search/property";
    public static final String GET_PROPERTY_LIST=BASE_URL+"api/propertylist";
    public static final String GET_PROPERTY_PRICE=BASE_URL+"api/post-property/getpostprice";
    public static final String GET_PROPERTY_PHOTO=BASE_URL+"api/post-property/getpostphotos";
    public static final String PHOTO_PROPERTY_PHOTO=BASE_URL+"api/postproperty/postphotos";
    public static final String GET_PROPERTY_FREEZE_STATUS=BASE_URL+"api/check-freeze";
    public static final String GET_PROPERTY_FREEZE_YES=BASE_URL+"api/freeze";
    public static final String GET_PROPERTY_FREEZE_NO=BASE_URL+"api/resume";
    public static final String GET_LEADS_COUNT_BY_PROID=BASE_URL+"api/total-lead";

//    prepertyGet
    public static final String GET_PROPERTY_FEATURES=BASE_URL+"api/post-property/getpostfeatures";
    public static final String GET_PROPERTY_BASIC=BASE_URL+"api/post-property/getpostbasicdetails";
    public static final String GET_PROPERTY_AMENTIES=BASE_URL+"api/post-property/getpostamenities";
    public static final String GET_PROPERTY_VERIFICATION=BASE_URL+"api/post-property/getpostverification";
    public static final String GET_PROPERTY_LIST_UNDER_REVIEW=BASE_URL+"api/underreview";
    public static final String GET_PROPERTY_LIST_INCOMPLETE=BASE_URL+"api/incomplete";
    public static final String GET_PROPERTY_LIST_FREEZE=BASE_URL+"api/freezelist";
    public static final String GET_PROPERTY_DETAILS=BASE_URL+"api/propertydetails";
    public static final String GET_PROPERTY_BY_ID=BASE_URL+"api/getpropertybyid";
    public static final String GET_REQ_BY_ID=BASE_URL+"api/getrequirementbyemail";
    public static final String GET_PROJECT_BY_ID=BASE_URL+"api/getprojectbyemail";
    public static final String GET_ACCEPTED_BY_USER=BASE_URL+"api/all-accept-property-by-user";
    public static final String GET_ALL_ACCEPTED_BY_USER=BASE_URL+"api/view-all-accept-reject";
    public static final String GET_CONTENT=BASE_URL+"api/getcontent";
    public static final String GET_ORDERS=BASE_URL+"api/order-payments";

//    profile
    public static final String GET_PROFILE_BY_ID=BASE_URL+"api/getuserbyid";
    public static final String GET_PROFILE_BY_Email=BASE_URL+"api/getuserbyemail";
    public static final String GET_PROFILE_PERSONAL=BASE_URL+"api/broker/getpersonaldetails";
    public static final String GET_PROFILE_WORKDESCRIPTION=BASE_URL+"api/broker/getworkdescription";
    public static final String GET_PROFILE_OFFICE=BASE_URL+"api/broker/getofficedetails";
    public static final String GET_PROFILE_SERVICE=BASE_URL+"api/broker/getservicecharges";
    public static final String GET_PROFILE_MORE=BASE_URL+"api/broker/getearnmore";
    public static final String GET_PROFILE_VARIFICATION=BASE_URL+"api/broker/getverification";
    public static final String GET_PROFILE_PHOTO=BASE_URL+"api/broker/getphoto";
    public static final String FORGET_PASS=BASE_URL+"api/forgot-password";
    public static final String UPDATE_PERSONAL=BASE_URL+"api/update-personal-details";
    public static final String UPDATE_PASSWORD=BASE_URL+"api/change-password";

//    project on board
    public static final String GET_PROJRCT_LIVE=BASE_URL+"api/project-live";
    public static final String GET_PROJRCT_RECIVED=BASE_URL+"api/project-received";
    public static final String GET_PROJRCT_PENDING=BASE_URL+"api/project-pending";
    public static final String GET_PROJRCT_INFO=BASE_URL+"api/getprojectsdetails";
    public static final String PROJECT_URL="http://munafa.digitalfueled.in/projects/";
    public static final String GET_PROPOSAL_INFO=BASE_URL+"api/project-brokar-contact";
    //serach nest pro broker
    public static final String SEARCH_BOKER=BASE_URL+"api/getbrokaruser";
//    Other
    public static final String GET_ARTICLES=BASE_URL+"api/getarticle";
//    leads
    public static final String LEADS_LIVE=BASE_URL+"api/livelead";
    public static final String LEADS_PUBLIC=BASE_URL+"api/publiclead";
    public static final String LEADS_INDIANHEROS=BASE_URL+"api/indianherolead";
    public static final String LEADS_FIND_INDIANHEROS=BASE_URL+"api/indianhero";
    public static final String LEADS_INDIANHEROS_DETAILS=BASE_URL+"api/indianheros";
    public static final String LEADS_DUBBLE=BASE_URL+"api/doublelead";
    public static final String LEADS_PROPERTY_DETAILS=BASE_URL+"api/propertydetails";
    public static final String LEADS_REQ_DETAILS=BASE_URL+"api/requirementdetails";
    public static final String LEADS_BROKER=BASE_URL+"api/brokarlead";
    public static final String LEADS_ACCEPT_REQ=BASE_URL+"api/accept-requirement";
    public static final String LEADS_REJECT_REQ=BASE_URL+"api/reject-requirement";
    public static final String LEADS_ACCEPT_PRO=BASE_URL+"api/accept-property";
    public static final String LEADS_REJECT_PRO=BASE_URL+"api/reject-property";
    public static final String LEADS_ACCEPT_VIEW=BASE_URL+"api/accept";
    public static final String LEADS_REJECT_VIEW=BASE_URL+"api/reject";
    public static final String LEADS_REJECT_UPDATE=BASE_URL+"api/leadreject";
    public static final String LEADS_CONTACT_VIEW=BASE_URL+"api/contacts-user";
    public static final String LEADS_VIEW_ACCEPT=BASE_URL+"api/view-accept-requirement";
    public static final String LEADS_VIEW_REJECT=BASE_URL+"api/view-reject-requirement";
    public static final String LEADS_VIEW_ACCEPT_PROPERTY=BASE_URL+"api/view-accept-property";
    public static final String LEADS_VIEW_REJECT_PROPERTY=BASE_URL+"api/view-reject-property";
    public static final String LEADS_CONTACT_SAVE=BASE_URL+"api/contactuser";
    public static final String LEADS_VIEW_PROPOSAL_REQ=BASE_URL+"api/view-broker-post-requirement";
    public static final String LEADS_VIEW_PROPOSAL_PRO=BASE_URL+"api/view-broker-accpept-proposal";
    public static final String LEADS_PROJECT_TODAY=BASE_URL+"api/projecttodaylead";
    public static final String LEADS_PROJECT_PREVIOUS=BASE_URL+"api/projectpreviouslead";
    public static final String LEADS_PROJECT_CLOSED=BASE_URL+"api/projectdealclosedlead";
    public static final String LEADS_PROPOSAL_SUMMITED=BASE_URL+"api/get-proposal-submitted";

    public static final String GET_PERSONAL_DETAILS=BASE_URL+"api/broker/getpersonaldetails";
    public static final String GET_OFFICE_DETAILS=BASE_URL+"api/broker/getofficedetails";
    public static final String GET_PHOTO_UPLOADS=BASE_URL+"api/broker/getphoto";
    public static final String GET_WORK_DESCRIPTION=BASE_URL+"api/broker/getworkdescription";
    public static final String UPDATE_WORK_DESCRIPTION=BASE_URL+"api/broker/updateworkdescription";
    public static final String GET_EARN_MONEY=BASE_URL+"api/broker/getearnmore";
    public static final String GET_SERVICE_CHARGE=BASE_URL+"api/broker/getservicecharges";
    public static final String GET_VERIFICATION=BASE_URL+"api/broker/getverification";
    public static final String UPDATE_BROKER_PERSONAL=BASE_URL+"api/broker/updatepersonaldetails";
    public static final String UPDATE_BROKER_OFFICE=BASE_URL+"api/broker/updateofficedetails";


    public static final String BASE_LOGO="https://munafa.digitalfueled.in/frontend/images/";

//    proposal
    public static final String POST_PROPOSAL_REQ=BASE_URL+"api/broker-post-requirement";
    public static final String POST_PROPOSAL_PRO=BASE_URL+"api/broker-accpept-proposal";

    // profile
    public static final String GET_DASHBOARD_TODAY=BASE_URL+"api/dashboard-today";
    public static final String GET_DASHBOARD_OVERALL=BASE_URL+"api/dashboard-overall";

    public static final String GET_SUBSCRIPTIONS_LIST=BASE_URL+"api/subscriptionplans";
    public static final String GET_SUBSCRIPTIONS_BY_ID=BASE_URL+"api/subscriptionplansbyid";
    public static final String POST_FEED_BACK=BASE_URL+"api/feedback";
    public static final String POST_CONTACTS_US=BASE_URL+"api/contact-us";

    //create profile
    public static final String SET_PERSONALDETAILS = BASE_URL+"api/broker/personaldetails";
    public static final String SET_WORK_DESCRIPTION = BASE_URL+"api/broker/workdescription";
    public static final String SET_OFFICE_DETAILS = BASE_URL+"api/broker/officedetails";
    public static final String SET_SEVICE_CHARGE = BASE_URL+"api/broker/servicecharges";
    public static final String SET_EARN_MORE = BASE_URL+"api/broker/earnmore";
    public static final String SET_VERIFICATION = BASE_URL+"api/broker/verification";
    public static final String SET_PHOTOS = BASE_URL+"api/broker/photo";
    public static final String CLINT_DETAILS_SUMBIT = BASE_URL+"api/projectdealclose";
    public static final String SUMBIT_REVIEW = BASE_URL+"api/review";
    public static final String REVIEW_LIST_BY_ID= BASE_URL+"api/reviewbyuserid";
    public static final String REVIEW_LIST_BY_P_ID= BASE_URL+"api/reviewbypid";
    public static final String DEAL_CLOSED= BASE_URL+"api/dealclose";
    public static final String ASK_FOR_REVIEW= BASE_URL+"api/askreview";
    public static final String PROJECT_ACCEPT_REJECT= BASE_URL+"api/project-accept-reject";
    public static final String PROJECT_GET_ACCEPT_REJECT= BASE_URL+"api/view-project-accept-reject";

    //deleting
    public static final String DELETE_PROFILE_PHOTO= BASE_URL+"api/broker/profile-photo/delete";
    public static final String DELETE_COVER_PHOTO= BASE_URL+"api/broker/cover-photo/delete";
    public static final String DELETE_OFFICE_PHOTO_FIRST= BASE_URL+"api/broker/office-photo-main/delete";
    public static final String DELETE_OFFICE_PHOTO_BY_ID= BASE_URL+"api/broker/office-photo/delete";
    public static final String DELETE_LOGO_PHOTO=BASE_URL+"/api/broker/logo-photo/delete";
    public static final String DELETE_BY_ID_SITEVIEW=BASE_URL+"api/site-view";
    public static final String DELETE_BY_ID_COMAN_AREA=BASE_URL+"api/common-area";
    public static final String DELETE_BY_ID_WASHROOM=BASE_URL+"api/washroom-view";
    public static final String DELETE_BY_ID_EXTERIR=BASE_URL+"api/exterior-view";
    public static final String DELETE_BY_ID_LIVING=BASE_URL+"api/living-room";
    public static final String DELETE_BY_ID_BEDROOMS=BASE_URL+"api/bedrooms";
    public static final String DELETE_BY_ID_BATHROOMS=BASE_URL+"api/bathrooms";
    public static final String DELETE_BY_ID_KICTHEN=BASE_URL+"api/kitchen";
    public static final String DELETE_BY_ID_FLOOR_PLANS=BASE_URL+"api/floor-plan";
    public static final String DELETE_BY_ID_MASTER_PLAN=BASE_URL+"api/master-plan";
    public static final String DELETE_BY_ID_LOCATION=BASE_URL+"api/location-map";
    public static final String DELETE_BY_ID_OTHER=BASE_URL+"api/others";
    public static final String DELETE_MAIN_SITE_VIEW=BASE_URL+"api/site-view-main";
    public static final String DELETE_MAIN_COMMON_AREA=BASE_URL+"api/common-area-main";
    public static final String DELETE_MAIN_WASHROOM=BASE_URL+"api/washroom-view-main";
    public static final String DELETE_MAIN_EXTERIR=BASE_URL+"api/exterior-view-main";
    public static final String DELETE_MAIN_LIVING=BASE_URL+"api/living-room-main";
    public static final String DELETE_MAIN_BATHROOMS=BASE_URL+"api/bathrooms-main";
    public static final String DELETE_MAIN_BEDROOMS=BASE_URL+"api/bedrooms-main";
    public static final String DELETE_MAIN_KICTHEN_MAIN=BASE_URL+"api/kitchen-main";
    public static final String DELETE_MAIN_FLOOR=BASE_URL+"api/floor-plan-main";
    public static final String DELETE_MAIN_MASTER=BASE_URL+"api/master-plan-main";
    public static final String DELETE_MAIN_LOCATION=BASE_URL+"api/location-map-main";
    public static final String DELETE_MAIN_OTHER=BASE_URL+"api/others-main";
    public static final String DELETE_VERIFICATIION=BASE_URL+"api/verification-photo";
    public static final String DELETE_VERIFICATION_PHOTO_FRONT=BASE_URL+"api/verification-photo-front";
    public static final String DELETE_VERIFICATION_PHOTO_BACK=BASE_URL+"api/verification-photo-back";


    public static final String GET_NOTIFICATION=BASE_URL+"api/notification";
    public static final String READ_NOTIFICATION=BASE_URL+"api/readnotification";
    public static final String TOTAL_NOTIFICATION=BASE_URL+"api/totalnotification";
    public static final String VIEW_NOTIFICATION=BASE_URL+"api/viewreadnotification";


}
