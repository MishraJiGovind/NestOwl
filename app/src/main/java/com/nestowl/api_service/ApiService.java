package com.nestowl.api_service;

import androidx.annotation.NonNull;


import com.nestowl.model.DropdownHome;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.PostPhotoModal;
import com.nestowl.model.PostPricingModal;
import com.nestowl.model.PostPropertyModal;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.User;
import com.nestowl.model.chatmodals.ChatFileUploadResponseModal;
import com.nestowl.utils.UrlClass;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit.http.Body;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @NonNull
    @POST(UrlContainer.POST_PROPOSAL_REQ)
    Call<OtpResponse> postProposalReq(
//            @Part MultipartBody.Part[] images1,
//            @Part MultipartBody.Part[] images2,
            @QueryMap Map<String, String> partMap
    );
    @Multipart
    @POST(UrlContainer.POST_PROPOSAL_REQ_IMG)
    Call<OtpResponse> postProposalReqImges(
            @Part MultipartBody.Part images1,
            @Part MultipartBody.Part images2,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.SUMBIT_REVIEW)
    Call<OtpResponse> sumbitReview(
            @Part MultipartBody.Part images1,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.UPDATE_SERVICE_CHARGE_DETAILS)
    Call<OtpResponse> updateServiceCharge(
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.SET_SEVICE_CHARGE)
    Call<OtpResponse> setServiceCharge(
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.UPDATE_VERIFICATION)
    Call<OtpResponse> updateVerificationData(
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part sup,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.SET_VERIFICATION)
    Call<OtpResponse> setVerificationData(
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part sup,
            @PartMap() Map<String, RequestBody> partMap
    );

    @NonNull
    @POST(UrlContainer.UPDATE_EARN_MORE_DETAILS)
    Call<OtpResponse> updateEarnMoreDetails(
            @QueryMap HashMap<String, String> hashMap
    );
    @NonNull
    @POST(UrlContainer.SET_EARN_MORE)
    Call<OtpResponse> setEarnMoreDetails(
            @QueryMap HashMap<String, String> hashMap
    );
    @NonNull
    @POST(UrlContainer.GET_ALL_ACCEPTED)
    Call<ResponseAllacceptRejectModal> getAllAccepted(
            @QueryMap HashMap<String,String> hashMap
    );

    @NonNull
    @POST(UrlClass.POST_PROPERY_FEATURES)
    Call<PostPropertyModal> postFeature(
            @Body PostPropertyModal postPropertyModal
    );

    @NonNull
    @POST(UrlClass.POST_PROPERY_PRICING)
    Call<PostPricingModal> postPricing(
      @Body PostPricingModal postPricingModal
    );

    @NonNull
    @POST(UrlClass.POST_PROPERY_PHOTO)
    Call<PostPhotoModal> postPohoto(
            @Body PostPhotoModal postPhotoModal
    );
    @NonNull
    @POST(UrlContainer.UPDATE_USER_PERSONAL)
    Call<User> updateUserPersonal(
            @Body User user
    );

    @NonNull
    @GET(UrlContainer.GET_PROFILE)
    Call<OtpResponse> getProfile(
            @Header("Authorization") String token
    );
    @NonNull
    @POST(UrlContainer.BROKER_PERSONAL_DETAILS)
    Call<OtpResponse> updateBrokerPersonalDetails(
            @QueryMap HashMap<String, String> hashMap
    );
    @NonNull
    @POST(UrlContainer.BROKER_WORK_DETAILS)
    Call<OtpResponse> updateworkDetails(
            @QueryMap HashMap<String, String> hashMap
    );

    @NonNull
    @POST(UrlContainer.BROKER_OFFICE_DETAILS)
    Call<OtpResponse> updateBrokerOfficeDetails(
            @QueryMap HashMap<String, String> hashMap
    );


    @NonNull
    @GET(UrlContainer.GENERATE_PROPETY_ID)
    Call<OtpResponse> generate_id(
    );

    @NonNull
    @POST(UrlContainer.BASIC_DETAILS)
    Call<OtpResponse> basicDetailsSubmit(
            @QueryMap HashMap<String, String> hashMap
    );


    @NonNull
    @POST(UrlContainer.AMENTIES_FEATURES)
    Call<OtpResponse> amentiesFeaturesDetails(
            @QueryMap HashMap<String, String> hashMap
    );


    @Multipart
    @POST(UrlContainer.POST_VERIFICATION)
    Call<OtpResponse> photosVerification(

            @Part MultipartBody.Part profile_photo,
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @PartMap() Map<String, RequestBody> partMap
    );

    @Multipart
    @POST(UrlContainer.BROKER_PHOTO_UPLOAD)
    Call<OtpResponse> uploadPhotos(
            @Part MultipartBody.Part profile_photo,
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part[] office_photo,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.CHAT_FILEUPLOAD)
    Call<ChatFileUploadResponseModal>uploadChatsFile(
            @Part MultipartBody.Part[] file
    );
    @Multipart
    @POST(UrlContainer.PROPERTY_PHOTO_UPLOAD)
    Call<OtpResponse> uploadPropertyPhotos(
            @Part MultipartBody.Part[] site_view,
            @Part MultipartBody.Part[] exterior_view,
            @Part MultipartBody.Part[] living_room,
            @Part MultipartBody.Part[] p_bathrooms,
            @Part MultipartBody.Part[] kitchen,
            @Part MultipartBody.Part[] floor_plan,
            @Part MultipartBody.Part[] master_plan,
            @Part MultipartBody.Part[] location_map,
            @Part MultipartBody.Part[] others,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.BROKER_PHOTO_UPDATE)
    Call<OtpResponse> updatePhotos(
            @Part MultipartBody.Part profile_photo,
            @Part MultipartBody.Part cover,
            @Part MultipartBody.Part logo,
            @Part MultipartBody.Part[] office_photo,
            @PartMap() Map<String, RequestBody> partMap
    );
    @Multipart
    @POST(UrlContainer.BROKER_PHOTO_UPDATE)
    Call<OtpResponse> updateDp(
            @Part MultipartBody.Part profile_photo,
            @PartMap() Map<String, RequestBody> partMap
    );

    @POST()
    Call<DropdownHome> getDropdownData(
            @Url String url
    );

    @POST(UrlContainer.SEND_OTP)
    Call<OtpResponse> sendOtp(@QueryMap HashMap<String,String> hashMap);

    @POST(UrlContainer.VARIFY_OTP)
    Call<OtpResponse> varifyOtp(@QueryMap HashMap<String,String> hashMap);
    /*@NonNull
    @POST(UrlContainer.ADD_TO_WISHLIST)
    Call<OtpResponse> AddToWishList(
            @Query("user-id") String userId,
            @Query("product-id") String productId
    );
    @NonNull
    @POST(UrlContainer.DELETE_FROM_WISHLIST)
    Call<OtpResponse> RemoveFromWishList(
            @Query("user-id") String userId,
            @Query("product-id") String productId
    );



*/

   /* @NonNull
    @GET(UrlContainer.GET_OUTSTATION_PRICE)
    Call<OtpResponse> getOutStationCarList(
            @Query("booking_type") String booking_type,
            @Query("from") String from,
            @Query("to") String to,
            @Query("start_date") String start_date,
            @Query("return_date") String return_date
    );

    @NonNull
    @POST(UrlContainer.BOOK_STATION)
    Call<OtpResponse> bookCab(
            @Header("Authorization") String token,
            @Query("type") String booking_type,
            @Query("from") String from,
            @Query("to") String to,
            @Query("pick_up_date") String pick_up_date,
            @Query("return-date") String return_date,
            @Query("return-time") String return_time,
            @Query("pick_up_at") String pick_up_at,
            @Query("total-amount") String total_amount,
            @Query("payment-mode") String payment_mode,
            @Query("pay-amount") String pay_amount,
            @Query("distance") String distance,
            @Query("car") String car,
            @Query("transaction-id") String transaction_id,
            @Query("transaction-status") String transaction_status,
            @Query("pick-up") String pick_up,
            @Query("landmark") String landmark
    );

    @POST(UrlContainer.BOOK_AIRPORT_CAB)
    Call<OtpResponse> bookAirport(
            @Header("Authorization") String token,
            @Query("airpot") String airpot,
            @Query("booking_type") String booking_type,
            @Query("pick_up_location") String pick_up_location,
            @Query("pick_up_date") String pick_up_date,
            @Query("pick_up_at") String pick_up_at,
            @Query("total-amount") String total_amount,
            @Query("payment-mode") String payment_mode,
            @Query("pay-amount") String pay_amount,
            @Query("distance") String distance,
            @Query("car") String car,
            @Query("transaction-id") String transaction_id,
            @Query("transaction-status") String transaction_status,
            @Query("landmark") String landmark);

    //  http://invispaitsolution.com/SuperCabc/api/airpot-cab-book?airpot=Delhi Airpot&pick_up_date=2021-03-16&pick_up_at=10:00 am&total-amount=2000&payment-mode=Pay 10 %&pay-amount=200&distance=40&car=1&transaction-id=rt6434sad&transaction-staus=Success&pick_up_location=Railway Station Delhi&booking_type=Cab from the Airpot
    @NonNull
    @POST(UrlContainer.BOOK_STATION)
    Call<OtpResponse> bookOneWayOutStation(
            @Header("Authorization") String token,
            @Query("type") String booking_type,
            @Query("from") String from,
            @Query("to") String to,
            @Query("pick_up_date") String pick_up_date,
            @Query("pick_up_at") String pick_up_at,
            @Query("total-amount") String total_amount,
            @Query("payment-mode") String payment_mode,
            @Query("pay-amount") String pay_amount,
            @Query("distance") String distance,
            @Query("car") String car,
            @Query("transaction-id") String transaction_id,
            @Query("transaction-status") String transaction_status,
            @Query("pick-up") String pick_up,
            @Query("landmark") String landmark
    );

    @NonNull
    @POST(UrlContainer.BOOK_LOCAL_CAB)
    Call<OtpResponse> bookLocalCab(
            @Header("Authorization") String token,
            @Query("from") String from,
            @Query("pick_up_date") String pick_up_date,
            @Query("pick_up_at") String pick_up_at,
            @Query("total-amount") String total_amount,
            @Query("payment-mode") String payment_mode,
            @Query("pay-amount") String pay_amount,
            @Query("distance") String distance,
            @Query("duration") String duration,
            @Query("car") String car,
            @Query("transaction-id") String transaction_id,
            @Query("transaction-status") String transaction_status,
            @Query("pick_up_location") String pick_up,
            @Query("landmark") String landmark
    );


    @NonNull
    @GET(UrlContainer.GET_MY_BOOKING)
    Call<OtpResponse> getBookingHistory(
            @Header("Authorization") String token
    );


    @NonNull
    @GET()
    Call<OtpResponse> getOrderList(
            @Url String url
    );


    @GET(UrlContainer.HOME_PAGE)
    Call<OtpResponse> getHomePageData();

    @POST()
    Call<OtpResponse> postData(
            @Header("Authorization") String token,
            @Url String url);


    @NonNull
    @POST(UrlContainer.UPDATE_TOKEN)
    Call<OtpResponse> updateToken(
            @Header("Authorization") String token,
            @Query("device_type") String device_type,
            @Query("device_id") String device_id
    );
    @NonNull
    @POST(UrlContainer.SEND_FEEDBACK)
    Call<OtpResponse> sendFeedback(
            @Header("Authorization") String token,
            @Query("booking_id") String booking_id,
            @Query("feedback") String feedback,
            @Query("rating") String ratting
    );

    @NonNull
    @GET(UrlContainer.GET_NOTIFICATION)
    Call<OtpResponse> getNotificationList(
            @Header("Authorization") String token);
*/
}
