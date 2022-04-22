package com.nestowl.api_service;

public interface UrlContainer {
    String TAG = "MyShopLog";
    String SEND_UPDATED = "UpdateReciever";
    String TRANSFER_DATA = "TransferData";
    String TRANSFER_ANOTHER_DATA = "TransferAnotherData";
    String LOCAL_DATA = "TransferLocalData";
// String BASE_URL = "http://invispaitsolution.com/SuperCabc/";
    String BASE_URL = "https://munafa.digitalfueled.in/";
    String FORGOT_PASSWORD="api/forgot-password";
    String RESET_PASSWORD="api/reset-password";
    String RESEND_OTP="register/resend-otp";
    String SEND_OTP="api/otp";
    String VARIFY_OTP="/api/otpcheck";

    String LOGIN_OTP="api/login-withotp";
    String VERIFY_LOGIN_OTP="api/login-withotp/verify";//?mobile=8630538177&otp=944682";
    String LOGIN="api/login";
    String REGISTER="api/register";
    String LOGOUT="api/logout";//?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9pbnZpc3BhaXRzb2x1dGlvbi5jb21cL0Jvb2tTaG9wVFwvYXBpXC9hZG1pblwvbG9naW4iLCJpYXQiOjE2MTMzNzM5NjgsImV4cCI6MTYxMzM3NzU2OCwibmJmIjoxNjEzMzczOTY4LCJqdGkiOiJMOWVoUzRKelM1c0lSdkw5Iiwic3ViIjoxLCJwcnYiOiI1ODUwNzhmZDNkYjJiZTc2OWUxMDgxNDY0YWJlYjJlYjAzZWVhMzU4In0.cX_9HEZ6VUEpsP5r1_so_MeLilgqVZLToSSFqcRkVgY";
    String HOME_PAGE="app-front";
    String GET_PROFILE="api/profile";
    String UPDATE_PROFILE="api/profile-update";//?name=Ujjwal&email=ujjwaltyagi143@gmail.com&mobile=8630538177";
    String BROKER_PERSONAL_DETAILS="api/broker/personaldetails";
    String GET_STATES="api/project/allstate";
    String GET_CITY_BY_STATES="api/project/city";
    String GET_ALL_CITY="api/project/allcity";
    String GET_LOCALITY="api/projectaddress";
    String SET_VERIFICATION = "api/broker/verification";
    String SET_SEVICE_CHARGE = "api/broker/servicecharges";
    String SET_EARN_MORE ="api/broker/earnmore";
    String BROKER_OFFICE_DETAILS="api/broker/officedetails";
    String BROKER_WORK_DETAILS="api/broker/workdescription";
    String BROKER_PHOTO_UPLOAD="api/broker/photo";
    String PROPERTY_PHOTO_UPLOAD="api/postproperty/postphotos";
    String BROKER_PHOTO_UPDATE="api/broker/updatephoto";
//    String BROKER_PHOTO_UPLOAD="api/broker/photo";
    String POST_PROPOSAL_REQ="api/broker-post-requirement";
    String POST_PROPOSAL_REQ_IMG="api/broker-post-requirement-image";

    String BASIC_DETAILS="api/post-property/postbasicdetails";
    String GENERATE_PROPETY_ID="api/post-property/generateid";
    String AMENTIES_FEATURES="api/post-property/postamenities";
    String POST_PHOTOS="api/post-property/postphotos";
    String POST_VERIFICATION="api/post-property/postverification";
    String UPDATE_USER_PERSONAL="api/update-personal-details";
    String GET_ALL_ACCEPTED="api/all-accept-reject";

    String UPDATE_PERSONAL_DETAILS="api/broker/updatepersonaldetails";
    String UPDATE_SERVICE_CHARGE_DETAILS="api/broker/updateservicecharges";
    String UPDATE_VERIFICATION="api/broker/updateverification";

    String UPDATE_BROKER_OFFICE_DETAILS="api/broker/updateofficedetails";
    String UPDATE_BROKER_WORK_DETAILS="api/broker/updateworkdescription";
    String POST_BROKER_WORK_DETAILS="api/broker/workdescription";
    String UPDATE_EARN_MORE_DETAILS="api/broker/updateearnmore";
    String POST_SCHEDULE="api/post-property/postphotos";
    String PRICING_FEATURES="api/post-property/postphotos";
    String SUMBIT_REVIEW="api/review";



}
