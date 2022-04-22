package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.BrokerDealClosedAdapter;
import com.nestowl.AdapterClass.OfficePhotoViewProfile;
import com.nestowl.AdapterClass.ReviewsAdapter;
import com.nestowl.model.BrokerOfficeModal;
import com.nestowl.model.BrokerPersonalModal;
import com.nestowl.model.BrokerPhotoModal;
import com.nestowl.model.BrokerWorkModal;
import com.nestowl.model.DealCLosedModal;
import com.nestowl.model.ReviewCalculatorModal;
import com.nestowl.model.ReviewsModal;
import com.nestowl.model.User;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BrokerSubmitProfileRequirement extends AppCompatActivity {
    String userId,officePhotoS,id;
    RatingBar ratingbarTop,ratingBarBottom;
    ImageView cover,dp,dubleDealImg,badges1,badges2;
    TextView name,reviewsTop,rera,address,deals,oprating,website,aboutHead,aboutPara,aboutReadmore,duubleDealsHead,dubleDealsPara,
    reviewsbottom,dealClosedViewAll,officePhotoViewAll;
    FrameLayout chat,viewContact,sumbit,reviewsMe;
    LinearLayout images;
    RecyclerView projectOnboard,officePhoto,recyclerView;
    ArrayList<String> photos;
    ArrayList<DealCLosedModal> cLosedModals;
    ArrayList<ReviewsModal> reviewsModals;
    ReviewsAdapter adapter;
    ReviewCalculatorModal reviewCalculatorModal;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker_submit_profile_requirement);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        Intent intent =  getIntent();
        userId=intent.getStringExtra("key");
        ratingbarTop=findViewById(R.id.BROKER_PROFILE_RATING_BAR);
        ratingBarBottom=findViewById(R.id.BROKER_RATING_BAR);
        cover=findViewById(R.id.BROKER_COVER_IMG);
        dp=findViewById(R.id.BROKER_PROFILE_DP);
        dubleDealImg=findViewById(R.id.BROKER_PROFILE_DUUBLE_DEALS_IMG);
        name=findViewById(R.id.BROKER_PROFILE_NAME);
        reviewsTop=findViewById(R.id.BROKER_PROFILE_REVIEWS);
        rera=findViewById(R.id.BROKER_PROFILE_RERA);
        address=findViewById(R.id.BROKER_PROFILE_ADDRESS);
        deals=findViewById(R.id.BROKER_PROFILE_DEALS_IN);
        oprating=findViewById(R.id.BROKER_PROFILE_OPRATING);
        website=findViewById(R.id.BROKER_PROFILE_WEBSITE);
        aboutHead=findViewById(R.id.BROKER_PROFILE_ABOUT_HEAD);
        aboutPara=findViewById(R.id.BROKER_PROFILE_ABOUT_PARA);
        aboutReadmore=findViewById(R.id.BROKER_PROFILE_READMORE_ABOUT);
        duubleDealsHead=findViewById(R.id.BROKER_PROFILE_DUBBLE_DEALS_HEAD);
        dubleDealsPara=findViewById(R.id.BROKER_PROFILE_DUBBLE_DEALS_PARA);
        reviewsbottom=findViewById(R.id.BROKER_REVIEWS);
        chat=findViewById(R.id.BROKER_CHAT);
        viewContact=findViewById(R.id.BROKER_CONTACT);
        sumbit=findViewById(R.id.BROKER_SUMBIT);
        reviewsMe=findViewById(R.id.BROKER_REVIEW_ME);
        badges1=findViewById(R.id.BROKER_PROFILE_BADGE_1);
        badges2=findViewById(R.id.BROKER_PROFILE_BADGE_2);
        images=findViewById(R.id.BROKER_PROFILE_OFFICE_POHOTOS);
        projectOnboard = findViewById(R.id.BROKER_PROFILE_DEALS_CLOSE_RECYCLER);
        officePhoto = findViewById(R.id.BROKER_PROFILE_OFICE_PHOTO_RECYCLER);
        recyclerView = findViewById(R.id.BROKER_REVIEWS_RECYCLER);
        officePhotoViewAll = findViewById(R.id.BROKER_PROFILE_OFFICE_VIEW_ALL);
        dealClosedViewAll = findViewById(R.id.BROKER_PROFILE_CLOSED_DEALS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        photos=new ArrayList<>();
        cLosedModals=new ArrayList<>();
        reviewsModals=new ArrayList<>();
        adapter=new ReviewsAdapter(this,reviewsModals);
        fetchReviews();

        projectOnboard.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        officePhoto.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        dealClosedViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BrokerSubmitProfileRequirement.this,DealClosed.class);
                intent1.putExtra("user_id",userId);
                startActivity(intent1);
            }
        });
        officePhotoViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BrokerSubmitProfileRequirement.this, OfficePhotos.class);
                intent1.putExtra("user_id",userId);
                startActivity(intent1);
            }
        });


        fetchUserInfo(userId);
    }
    private void fetchUserInfo(String userId) {
        getPersonalBroker(userId);
        getOfficeDetails(userId);
        getWorkDescriptio(userId);
        getPhoto(userId);
        fetchClosed(userId);
    }
    private void fetchClosed(String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_PROJECT_CLOSED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("ProjectDealClosedLead_data");
                        for (int i=0;i<jsonArray.length();i++){
                            DealCLosedModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),DealCLosedModal.class);
                            cLosedModals.add(data);
                        }
                        BrokerDealClosedAdapter adapter = new BrokerDealClosedAdapter(BrokerSubmitProfileRequirement.this,cLosedModals);
                        projectOnboard.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getPhoto(String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status  = jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        BrokerPhotoModal photoModal = new Gson().fromJson(jsonObject.getJSONObject("data").toString(),BrokerPhotoModal.class);
                        Glide.with(BrokerSubmitProfileRequirement.this).load(UrlClass.BASE_URL+photoModal.getProfile_photo()).placeholder(R.drawable.profile_img_sahil).into(dp);
                        Glide.with(BrokerSubmitProfileRequirement.this).load(UrlClass.BASE_URL+photoModal.getCover()).placeholder(R.drawable.house_png_same).into(cover);
                        JSONArray officePhotos = jsonObject.getJSONArray("Office_Photo");
                        for (int i=0;i<officePhotos.length();i++){
                            JSONObject jsonObject1 = officePhotos.getJSONObject(i);
                            photos.add(UrlClass.BASE_URL2+jsonObject1.getString("filename"));
                            Log.e("errors", "onResponse: "+jsonObject1+" "+jsonObject1.getString("filename") );
                        }
                        OfficePhotoViewProfile adapter =  new OfficePhotoViewProfile(BrokerSubmitProfileRequirement.this,photos);
                        officePhoto.setAdapter(adapter);

                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getWorkDescriptio(String userId) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_WORK_DESCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        BrokerWorkModal workModal =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),BrokerWorkModal.class);
                        aboutPara.setText(workModal.getBreif_description());
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getOfficeDetails(String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_OFFICE_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        BrokerOfficeModal officeModal =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),BrokerOfficeModal.class);
                        address.setText(officeModal.getLocality());
                        website.setText(officeModal.getCompany_website());
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getPersonalBroker(String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PERSONAL_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        BrokerPersonalModal data = new Gson().fromJson(jsonObject.getJSONObject("data").toString(),BrokerPersonalModal.class);
                        name.setText(data.getOwner_name());
                        oprating.setText(data.getOperating_since());
                        deals.setText(data.getDealing());
                        aboutHead.setText("About "+data.getOwner_name());
                    }
                }catch (Exception e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return  hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void fetchReviews() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.REVIEW_LIST_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String staus =  jsonObject.getString("status");
                    if (staus.equals("1")){
                        JSONArray jsonArray  = jsonObject.getJSONArray("Review");
                        reviewCalculatorModal=new ReviewCalculatorModal();
                        reviewCalculatorModal.setOne(0);
                        reviewCalculatorModal.setTwo(0);
                        reviewCalculatorModal.setThree(0);
                        reviewCalculatorModal.setFour(0);
                        reviewCalculatorModal.setFive(0);
                        for (int u=0;u<jsonArray.length();u++){
                            ReviewsModal reviewsModal  = new Gson().fromJson(jsonArray.getJSONObject(u).toString(),ReviewsModal.class);
                            reviewsModals.add(reviewsModal);
                            if (reviewsModal.getStar_review().contains("1")){
                                reviewCalculatorModal.setOne(reviewCalculatorModal.getOne()+1);
                            }
                            if (reviewsModal.getStar_review().contains("2")){
                                reviewCalculatorModal.setTwo(reviewCalculatorModal.getTwo()+1);
                            }
                            if (reviewsModal.getStar_review().contains("3")){
                                reviewCalculatorModal.setThree(reviewCalculatorModal.getThree()+1);
                            }
                            if (reviewsModal.getStar_review().contains("4")){
                                reviewCalculatorModal.setFour(reviewCalculatorModal.getFour()+1);
                            }
                            if (reviewsModal.getStar_review().contains("5")){
                                reviewCalculatorModal.setFive(reviewCalculatorModal.getFive()+1);
                            }
                        }
                        Integer totalReviews  = 1*reviewCalculatorModal.getOne()+2*reviewCalculatorModal.getTwo()+3*reviewCalculatorModal.getThree()+4*reviewCalculatorModal.getFour()+5*reviewCalculatorModal.getFive();
                        Integer divedRview = reviewCalculatorModal.getOne()+reviewCalculatorModal.getTwo()+reviewCalculatorModal.getThree()+reviewCalculatorModal.getFour()+reviewCalculatorModal.getFive();
                        Float review = Float.valueOf(totalReviews/divedRview);
                        ratingbarTop.setRating(review);
                        ratingBarBottom.setRating(review);
                        reviewsTop.setText(String.valueOf(jsonArray.length())+" reviews");
                        reviewsbottom.setText(String.valueOf(jsonArray.length())+" reviews");
                        fechProfile(reviewsModals);
                    }
                }catch (Exception e){
                    Log.e("REVIEWS ERROR", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void fechProfile(ArrayList<ReviewsModal> reviews) {
        reviewsModals = new ArrayList<>();

        for (int i=0;i<reviews.size();i++){
            ReviewsModal temps =  reviews.get(i);
            StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject =  new JSONObject(response);
                        String status =  jsonObject.getString("status");
                        if (status.equals("1")){
                            User name =  new Gson().fromJson(jsonObject.getString("data"),User.class);
                            temps.setName(name.getFirst_name()+" "+name.getLast_name());
                            JSONObject jsonObject1  = jsonObject.getJSONObject("Photo");
                            temps.setDp(UrlClass.BASE_URL+jsonObject1.getString("profile_photo"));
                            temps.setTypes("NEST PROS");
                            reviewsModals.add(temps);
                            adapter.notifyDataSetChanged();
                        }
                    }catch (Exception e){
                        Log.e("geting names in reviews", "onResponse: "+e );
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("user_id",temps.getUser_id());
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(request);

        }
        adapter=new ReviewsAdapter(this,reviewsModals);
        recyclerView.setAdapter(adapter);
    }

}
