package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.LeadsClicks;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class BuyerDetails extends AppCompatActivity {
    TextView tv1,head,name,typeoftext,property,loaclity,budget,propertycard,sqcard,budgetcard,localitycard;
    FrameLayout accept,reject,chat;
    ImageView imageView, dp,porpertyImage;
    LinearLayout note;
    HorizontalScrollView scrollView;
    String PROPERTY_ID,USER_ID,CURRUNT_USER_ID,PROPERTY_NUM_ID;
    boolean isWithProposal,isContact,isIntrested,isReq,isAccepted;
    LoginPojo user;
    ArrayList<AllAcceptedDataModal> propertyAcceptedData,reqAcceptedData;
    LeadsClicks leadsClicks;
    User clintInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_details);
        tv1 = findViewById(R.id.tv_akshit);
        imageView=findViewById(R.id.iv48);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyerDetails.this,AkshitActivity.class);
                startActivity(intent);
            }
        });



        user = PrefMananger.GetLoginData(this);
        CURRUNT_USER_ID=String.valueOf(user.getUserId());

        PROPERTY_ID=getIntent().getStringExtra("property_id");
        USER_ID=getIntent().getStringExtra("user_id");
        isWithProposal=getIntent().getBooleanExtra("isWith",false);
        isContact=getIntent().getBooleanExtra("isContact",false);
        isIntrested=getIntent().getBooleanExtra("isInterested",false);
        isReq=getIntent().getBooleanExtra("isReq",false);

        if (PrefMananger.getString(this,"response/key").equals("i")){
            isIntrested=true;
        }
        if (PrefMananger.getString(this,"response/key").equals("c")){
            isContact=true;
        }
        if (PrefMananger.getString(this,"response/key").equals("p")){
            isWithProposal=true;
        }

        head=findViewById(R.id.LIVE_LEADS_MORE_INFO_HEADING);
        name=findViewById(R.id.LIVE_LEADS_MORE_INFO_NAME);
        typeoftext=findViewById(R.id.LIVE_LEADS_MORE_INFO_TYPEOFLINE);
        property=findViewById(R.id.LIVE_LEADS_MORE_INFO_PROPERTY);
        loaclity=findViewById(R.id.LIVE_LEADS_MORE_INFO_LOCALITY);
        budget=findViewById(R.id.LIVE_LEADS_MORE_INFO_BUDGET);
        propertycard=findViewById(R.id.LIVE_LEADS_MORE_INFO_PRO_CARD_PROPERTY);
        sqcard=findViewById(R.id.LIVE_LEADS_MORE_INFO_PRO_CARD_AREA);
        localitycard=findViewById(R.id.LIVE_LEADS_MORE_INFO_PRO_CARD_LOACTION);
        accept=findViewById(R.id.LIVE_LEADS_MORE_INFO_ACCEPT);
        reject=findViewById(R.id.LIVE_LEADS_MORE_INFO_REJECT);
        chat=findViewById(R.id.LIVE_LEADS_MORE_INFO_CHAT);
        dp=findViewById(R.id.LIVE_LEADS_MORE_INFO_DP);
        porpertyImage=findViewById(R.id.LIVE_LEADS_MORE_INFO_PRO_CARD_IMG);
        note=findViewById(R.id.LIVE_LEADS_MORE_INFO_NOTE);
        scrollView=findViewById(R.id.LIVE_LEADS_MORE_INFO_BUTONS);

        Log.e("error", "onCreate: "+isWithProposal+isContact+isIntrested );

        if (isContact){
            reject.setVisibility(View.GONE);
            TextView textView = (TextView) accept.getChildAt(0);
            textView.setText("View Contact");
            isAccepted=true;
        }
        if (isIntrested){
            note.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
            typeoftext.setText("has started a offer for your property.");
        }

        propertyAcceptedData=new ArrayList<>();
        reqAcceptedData=new ArrayList<>();

        getUserInfo();
        getPropertyInfo();



        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAccepted){
                    new WarningDio(BuyerDetails.this, "Do you want accept proposal?", "Yes", "No", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                if (isReq){
                                    leadsClicks.acceptREQ();
                                }else {
                                    leadsClicks.acceptProperty();
                                }
                            }
                        }
                    },false);
                    return;
                }else {
                    Intent intents = new Intent(BuyerDetails.this, FrontViewQuerySecond.class);
                    intents.putExtra("user_id",CURRUNT_USER_ID);
                    intents.putExtra("id",PROPERTY_ID);
                    startActivity(intents);
                }
                if (isContact){
                    Intent intentss = new Intent(BuyerDetails.this, ViewContact.class);
                    intentss.putExtra("pid",PROPERTY_ID);
                    intentss.putExtra("user_id",USER_ID);
                    intentss.putExtra("id",PROPERTY_NUM_ID);
                    startActivity(intentss);
                }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReq){
                    leadsClicks.rejectREQ();
                }else {
                    leadsClicks.rejectProperty();
                }
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(BuyerDetails.this, Chating.class);
                intent.putExtra("id",clintInfo.getUser_id());
                intent.putExtra("name",clintInfo.getFirst_name()+" "+clintInfo.getLast_name());
                intent.putExtra("dp",clintInfo.getAvatar());
                startActivity(intent);
            }
        });
        leadsClicks=new LeadsClicks(this, PROPERTY_NUM_ID, CURRUNT_USER_ID, PROPERTY_ID, new LeadsClicks.AccepetStatus() {
            @Override
            public void status(boolean isTrue, boolean isAccept) {
                if (isAccept){
                    if (isTrue){
                        reject.setVisibility(View.GONE);
                        TextView textView = (TextView) accept.getChildAt(0);
                        textView.setText("View Proposal");
                        isAccepted=true;
                    }
                }else {

                }
            }
        });
    }

    private void getPropertyInfo() {
        if (isReq){
            StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_REQ_DETAILS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("1")){
                            LeadsRequmentsModal data = new Gson().fromJson(jsonObject.getJSONObject("requirement_data").toString(),LeadsRequmentsModal.class);
                            property.setText(data.getProperty());
                            propertycard.setText(data.getProperty());

                            loaclity.setText(data.getLocality());
                            localitycard.setText(data.getLocality());

                            budget.setText(getBudgetInLakhs(data.getBudget_max()));
                            budgetcard.setText(getBudgetInLakhs(data.getBudget_max()));

                            if (data.getArea_max()!=null&&!data.getArea_max_mezor().equals("")){
                                sqcard.setText(data.getArea_max()+" "+data.getArea_max_mezor());
                            }else {
                                sqcard.setText("N/A");
                            }

                            typeoftext.setText("is interested to buy your "+data.getProperty());
                            PROPERTY_NUM_ID=data.getId();
                            getAllAcceptedRejected(CURRUNT_USER_ID);
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
                    hashMap.put("requirement_id",PROPERTY_ID);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(request);
        }else {
            StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_DETAILS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("1")){
                            LeadsPublicPro data = new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(),LeadsPublicPro.class);
                            property.setText(data.getProperty());
                            propertycard.setText(data.getProperty());

                            loaclity.setText(data.getLocality());
                            localitycard.setText(data.getLocality());

                            budget.setText(getBudgetInLakhs(data.getExpectedprice()));
                            budgetcard.setText(getBudgetInLakhs(data.getExpectedprice()));

                            if (data.getArea()!=null&&!data.getArea().equals("")){
                            sqcard.setText(data.getArea()+" "+data.getArea_mezor());
                            }else {
                                sqcard.setText("N/A");
                            }
                            Glide.with(BuyerDetails.this).load(UrlClass.BASE_URL+data.getSite_view()).placeholder(R.drawable.default_x_y).into(porpertyImage);

                            typeoftext.setText("is interested to buy your "+data.getProperty());

                            PROPERTY_NUM_ID=data.getId();
                            getAllAcceptedRejected(CURRUNT_USER_ID);
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
                    hashMap.put("property_id",PROPERTY_ID);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(request);
        }
    }
    private void getUserInfo() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        clintInfo =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        name.setText(clintInfo.getFirst_name()+" "+clintInfo.getLast_name());

                        JSONObject jsonObject1 = jsonObject.getJSONObject("Photo");
                        Glide.with(BuyerDetails.this).load(UrlClass.BASE_URL+jsonObject1.getString("profile_photo")).placeholder(R.drawable.default_x_x).into(dp);
                        clintInfo.setAvatar(jsonObject1.getString("profile_photo"));
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
                hashMap.put("user_id",USER_ID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private String getBudgetInLakhs(String budget_max) {
        String data =  null;
        if (budget_max==null){
            return data;
        }
        int no = Integer.parseInt(budget_max);
        if (no>=1000000000){
            no=no/1000000000;
            data=no+"Arab";
            return data;
        }
        if (no>=10000000){
            no = no/10000000;
            data=no+"Crore";
            return data;
        }
        if (no>=100000){
            no = no/100000;
            data = no+"Lakh";
            return data;
        }
        if (no>=1000){
            no = no/1000;
            data = no+"K";
            return data;
        }
        if (no>999){
            data = String.valueOf(no);

        }
        return data;
    }
    private void getAllAcceptedRejected(String userID) {
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("user_id",userID);
        ApiExecutor.getApiService().getAllAccepted(hashMap).enqueue(new Callback<ResponseAllacceptRejectModal>() {
            @Override
            public void onResponse(Call<ResponseAllacceptRejectModal> call, retrofit2.Response<ResponseAllacceptRejectModal> response) {
                if (response.isSuccessful()){
                    Log.e("responseByRetroFits", "onResponse: "+response+" "+response.body().getProperty_data() );
                    propertyAcceptedData=response.body().getProperty_data();
                    reqAcceptedData=response.body().getRequirement_data();
                    if (isReq){
                        for (AllAcceptedDataModal accepted : reqAcceptedData){
                            if (accepted.getPro_id().equals(PROPERTY_NUM_ID)){
                                if (accepted.getAccepts()!=null){
                                    if (accepted.getAccepts().equals("Yes")) {
                                        reject.setVisibility(View.GONE);
                                        TextView textView = (TextView) accept.getChildAt(0);
                                        textView.setText("View Proposal");
                                        isAccepted=true;
                                    }
                                }
                            }
                        }
                    }else {
                        for (AllAcceptedDataModal accepted : propertyAcceptedData){
                            if (accepted.getPro_id().equals(PROPERTY_NUM_ID)){
                                if (accepted.getAccepts()!=null){
                                    if (accepted.getAccepts().equals("Yes")) {
                                        reject.setVisibility(View.GONE);
                                        TextView textView = (TextView) accept.getChildAt(0);
                                        textView.setText("Proposal Accepted");
                                        isAccepted=true;
                                    }
                                }
                            }
                        }
                    }

                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);

            }
        });
    }
}
