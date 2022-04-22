package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.LeadsClicks;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class PostRequirementFrontView extends AppCompatActivity {
    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    TextView name,mobile,email,city,project,locality,pincode,minarea,minmjor,maxarea,maxareamjor,minbudget,maxbudget,minseats,maxseats,
    carpetarea,carpetareamzor,builtuparea,builtpareamzor,superarea,superareamzor,moredetails;
    FrameLayout wantbuy,residental,comercial,corneryes,cornerno,transactionresale,boundryyes,mainroadyes,
    drectionE,wanttobuyImidiate,jobGOV,accept,reject,chat,
    ressonResidence,floorNone,receptionYes,buildingClassA,furnished,
    age1,bhk;
    String key,idd,user_id;
    Intent intent;
    LiveCommnication liveCommnication;
    boolean acceptStatusPro,typeAccept,isAccepted,isSumited,isProposalAccepted,isBroker;
    LeadsClicks handleLeads;
    LeadsRequmentsModal data;
    int proposalSumiitedTotal;
    ArrayList<AllAcceptedDataModal> propertyAcceptedData;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_requirement_front_view);
        horizontalScrollView =findViewById(R.id.horizontal_third);
        scrollView=findViewById(R.id.scroll);
        intent =  getIntent();
        proposalSumiitedTotal=0;
        key=intent.getStringExtra("id");
        user_id=intent.getStringExtra("user_id");
        idd=intent.getStringExtra("idd");
        isAccepted=intent.getBooleanExtra("isAccepted",false);
        isSumited=intent.getBooleanExtra("isSummited",false);
        isProposalAccepted=intent.getBooleanExtra("isProposalAccepted",false);
        isBroker=intent.getBooleanExtra("isBroker",false);
        LoginPojo loginPojo = PrefMananger.GetLoginData(this);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                horizontalScrollView.setVisibility(View.GONE);
                if (scrollY>55){
                    horizontalScrollView.setVisibility(View.VISIBLE);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        data =  new LeadsRequmentsModal();
        propertyAcceptedData=new ArrayList<>();
        typeAccept = true;
        handleLeads =  new LeadsClicks(this,idd, String.valueOf(loginPojo.getUserId()), idd);
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                aichat d =  aichat;
                String key =  d.getText();
                String value = d.getValue();
                if (key.equals("AR")){
                    if (value.equals("true")){
                        acceptStatusPro = true;
                        setproposalPRO();
                    }else{
                        acceptStatusPro = false;
                        setproposalPRO();
                    }
                }
            }
        });
        name=findViewById(R.id.LEADS_REQ_SHOW_NAME);
        mobile=findViewById(R.id.LEADS_REQ_SHOW_MOBILE);
        email=findViewById(R.id.LEADS_REQ_SHOW_EMAIL);
        city=findViewById(R.id.LEADS_REQ_SHOW_CITY);
        project=findViewById(R.id.LEADS_REQ_SHOW_PROJECT);
        locality=findViewById(R.id.LEADS_REQ_SHOW_LOCALITY);
        pincode=findViewById(R.id.LEADS_REQ_SHOW_PINCODE);
        minarea=findViewById(R.id.LEADS_REQ_SHOW_MIN_AREA_IN);
        minmjor=findViewById(R.id.LEADS_REQ_SHOW_MIN_AREA_MAZOR);
        maxarea=findViewById(R.id.LEADS_REQ_SHOW_MAX_AREA);
        maxareamjor=findViewById(R.id.LEADS_REQ_SHOW_MAX_AREA_MZOR);
        minbudget=findViewById(R.id.LEADS_REQ_SHOW_MIN_BUDGET);
        maxbudget=findViewById(R.id.LEADS_REQ_SHOW_MAX_BUDGET);
        minseats=findViewById(R.id.LEADS_REQ_SHOW_MIN_NO_OFF_SEATS);
        maxseats=findViewById(R.id.LEADS_REQ_SHOW_OFFICE_MAX_SEATS);
        carpetarea=findViewById(R.id.LEADS_REQ_SHOW_AGE_CARPET_AREA);
        carpetareamzor=findViewById(R.id.LEADS_REQ_SHOW_CARPET_MAZOR);
        builtuparea=findViewById(R.id.LEADS_REQ_SHOW_BUILTOP_AREA);
        builtpareamzor=findViewById(R.id.LEADS_REQ_SHOW_BUILTUP_MAZOR);
        superarea=findViewById(R.id.LEADS_REQ_SHOW_SUPER_AREA);
        superareamzor=findViewById(R.id.LEADS_REQ_SHOW_SUPER_MAZOR);
        moredetails=findViewById(R.id.LEADS_REQ_SHOW_MORE_INFO);

        wantbuy=findViewById(R.id.LEADS_REQ_SHOW_WANT);
        residental=findViewById(R.id.LEADS_REQ_SHOW_RESIDENTAL);
        comercial=findViewById(R.id.LEADS_REQ_SHOW_COMERCIAL);
        corneryes=findViewById(R.id.LEADS_REQ_SHOW_CORNER_YES);
        cornerno=findViewById(R.id.LEADS_REQ_SHOW_CORNER_NO);
        transactionresale=findViewById(R.id.LEADS_REQ_SHOW_TRANSACTION_RESALE);
        boundryyes=findViewById(R.id.LEADS_REQ_SHOW_BOUNDRY_YES);
        mainroadyes=findViewById(R.id.LEADS_REQ_SHOW_MAIN_ROAD_YES);
        drectionE=findViewById(R.id.LEADS_REQ_SHOW_DIRECTION_E);
        wanttobuyImidiate=findViewById(R.id.LEADS_REQ_SHOW_WANT_IMIDIATE);
        jobGOV=findViewById(R.id.LEADS_REQ_SHOW_JOB_GOVER);
        ressonResidence=findViewById(R.id.LEADS_REQ_SHOW_RESSON_RESIDENCE);
        floorNone=findViewById(R.id.LEADS_REQ_SHOW_FLOR_NO);
        receptionYes=findViewById(R.id.LEADS_REQ_SHOW_RECEPTION_YES);
        buildingClassA=findViewById(R.id.LEADS_REQ_SHOW_BUILDING_CLASS);
        furnished=findViewById(R.id.LEADS_REQ_SHOW_FURNISHING);
        age1=findViewById(R.id.LEADS_REQ_SHOW_AGE_1);
        bhk=findViewById(R.id.LEADS_REQ_SHOW_BHK);
        accept=findViewById(R.id.LEADS_REQ_SHOW_ACCEPT);
        reject=findViewById(R.id.LEADS_REQ_SHOW_REJECT);
        chat=findViewById(R.id.LEADS_REQ_SHOW_CHAT);
        if (isAccepted){
            accepted();
            typeAccept=false;
            reject.setVisibility(View.GONE);
        }
        if (isSumited){
            TextView textViews = (TextView) accept.getChildAt(0);
            reject.setVisibility(View.GONE);
            textViews.setText("Preview Proposal");
        }
        if (isProposalAccepted){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
        }
        if (isBroker){
            accepted();
            typeAccept=false;
            reject.setVisibility(View.GONE);
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
        }
        getSummitedProposals(key);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSumited){
                    Intent intent =  new Intent(PostRequirementFrontView.this,FrontViewMainSecond.class);
                    intent.putExtra("id",key);
                    intent.putExtra("user_id",String.valueOf(loginPojo.getUserId()));
                    startActivity(intent);
                    return;
                }
                if (typeAccept){
                    new WarningDio(PostRequirementFrontView.this, "Do you want to accept", "Yes", "No", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                boolean ds = handleLeads.acceptREQ();
                            }
                        }
                    },false);
                }else {
                    if (isBroker){
                        Intent intent =  new Intent(PostRequirementFrontView.this,ViewContact.class);
                        intent.putExtra("pid",key);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("id",idd);
                        startActivity(intent);
                        return;
                    }
                    if (isProposalAccepted){
                        Intent intent =  new Intent(PostRequirementFrontView.this,ViewContact.class);
                        intent.putExtra("pid",key);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("id",idd);
                        startActivity(intent);
                    }else {
                        if (proposalSumiitedTotal<=0){
                            Intent intent =  new Intent(PostRequirementFrontView.this,QueryBrokerFirstScreen.class);
                            intent.putExtra("user_id",data.getUser_id());
                            intent.putExtra("id",data.getRequirement_id());
                            PrefMananger.saveString(PostRequirementFrontView.this,PrefMananger.PROPOSAL_ID,data.getRequirement_id());
                            startActivity(intent);
//                            Intent intent =  new Intent(PostRequirementFrontView.this,QueryBrokerFirstScreen.class);
//                            intent.putExtra("user_id",UserId);
//                            intent.putExtra("id",PropertyID);
//                            intent.putExtra("idd",IDD);
//                            intent.putExtra("name",NAME);
//                            intent.putExtra("bhk",BHK);
//                            intent.putExtra("budget",BUDGET);
//                            intent.putExtra("location",LOCATION);
//                            startActivity(intent);
                        }else {
                            new WarningDio(PostRequirementFrontView.this, "This requirement has reached on his proposal summiting limit", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                    }

                }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDio(PostRequirementFrontView.this, "Do you want to reject", "Yes", "No", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                            boolean d =  handleLeads.rejectREQ();
                        }
                    }
                },false);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PostRequirementFrontView.this,Chating.class);
                intent.putExtra("key",data.getUser_id());
                intent.putExtra("name",data.getName());
                intent.putExtra("dp","dfdf");
                startActivity(intent);
            }
        });
        getData();
        getAllAcceptedRejected(String.valueOf(loginPojo.getUserId()));


    }
    private void getAllAcceptedRejected(String userID) {
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("user_id",userID);
        ApiExecutor.getApiService().getAllAccepted(hashMap).enqueue(new Callback<ResponseAllacceptRejectModal>() {
            @Override
            public void onResponse(Call<ResponseAllacceptRejectModal> call, retrofit2.Response<ResponseAllacceptRejectModal> response) {
                if (response.isSuccessful()){
                    Log.e("responseByRetroFits", "onResponse: "+response+" "+response.body().getProperty_data() );
                    propertyAcceptedData=response.body().getRequirement_data();
                    for (AllAcceptedDataModal d : propertyAcceptedData){
                        if (d.getPro_id().equals(idd)){
                            if (d.getAccepts()!=null){
                                if (d.getAccepts().equals("Yes")){
                                    isAccepted=true;
                                }
                            }else {
                                isAccepted=false;
                            }
                            if (d.getAcceptedproposal()!=null){
                                if (d.getAcceptedproposal().equals("Yes")){
                                    isProposalAccepted=true;
                                }
                            }else {
                                isProposalAccepted=false;
                            }
                            if (d.getSubmittedproposal()!=null){
                                if (d.getSubmittedproposal().contains("Yes")){
                                    isSumited=true;

                                }
                            }else {
                                isSumited=false;
                            }

                        }
                    }
                    handlerAceptsRejects();
                }else {
                }
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);
            }
        });
    }

    private void handlerAceptsRejects() {
        if (isAccepted){
            typeAccept=false;
            accepted();
        }
        if (isProposalAccepted){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
        }
        if (isSumited){
            TextView textViews = (TextView) accept.getChildAt(0);
            reject.setVisibility(View.GONE);
            textViews.setText("Preview Proposal");
        }
    }
    private void getSummitedProposals(String propertyID) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PROPOSAL_SUMMITED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        proposalSumiitedTotal=jsonObject.getInt("submittedproposal_data");
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
                hashMap.put("pro_id",propertyID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getData() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_REQ_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("req", "onResponse: "+response );
                    JSONObject object =  new JSONObject(response);
                    String sta = object.getString("status");
                    if (sta.equals("1")&&object.has("requirement_data")){
                        data =  new Gson().fromJson(object.getJSONObject("requirement_data").toString(),LeadsRequmentsModal.class);
                        name.setText(data.getName());
                        mobile.setText(data.getMobile());
                        email.setText(data.getEmail());
                        city.setText(data.getCity());
                        project.setText(data.getProject_name());
                        locality.setText(data.getLocality());
                        pincode.setText(data.getSublocality());
                        minarea.setText(data.getArea_min());
                        minmjor.setText(data.getArea_min_mezor());
                        maxarea.setText(data.getArea_max());
                        maxareamjor.setText(data.getArea_max_mezor());
                        minbudget.setText(data.getBudget_min());
                        maxbudget.setText(data.getBudget_max());
                        minseats.setText(data.getOffice_min_no_seat());
                        maxseats.setText(data.getOffice_max_no_seat());
                        carpetarea.setText(data.getCaptur_area_max());
                        carpetareamzor.setText(data.getCaptur_area_max_mezor());
                        builtuparea.setText(data.getBuildup_area_max());
                        builtpareamzor.setText(data.getBuildup_area_max_mezor());
                        superarea.setText(data.getSuper_area_max());
                        superareamzor.setText(data.getSuper_area_max_mezor());
                        moredetails.setText(data.getAdditional_requirement_details());
                        settext(wantbuy,data.getWant_to());
                        settext(residental,data.getProperty_type());
                        settext(comercial,data.getProperty());
                        settext(corneryes,data.getCorner_property_prefference());
                        settext(transactionresale,data.getTransaction_type());
                        settext(boundryyes,data.getBoundary_wall_made());
                        settext(mainroadyes,data.getMain_road_property_prefference());
                        settext(drectionE,data.getDirection_prefference());
                        settext(wanttobuyImidiate,data.getWant_to_buy());
                        settext(jobGOV,data.getNature_of_employment());
                        settext(ressonResidence,data.getReason_for_buying());
                        settext(floorNone,data.getFloor_prefference());
                        settext(receptionYes,data.getReception_area_prefference());
                        settext(buildingClassA,data.getBuilding_class_prefference());
                        settext(furnished,data.getFurnisging_prefference());
                        settext(age1,data.getAge_of_construction());
                        settext(bhk,data.getBhk_type());
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
                hashMap.put("requirement_id",key);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void settext(FrameLayout view, String text) {
        if (text==null|text.equals("")|text.equals(" ")){
            view.setVisibility(View.GONE);
        }else {
        TextView t = (TextView) view.getChildAt(0);
        t.setText(text);
        }
    }
    public void setproposalPRO(){
        if (!acceptStatusPro){
            new WarningDio(PostRequirementFrontView.this, "Requirement Rejected", "Ok", "Cancel", new WarningDio.Response() {
                @Override
                public void getClicks(int click) {

                }
            },false);
        }else {
            new WarningDio(PostRequirementFrontView.this, "Requirement Accepted\nSummit your proposal in next 180 minutes", "Summit Proposal",null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {
                    accepted();
                    if (click==1){
                        Intent intent =  new Intent(PostRequirementFrontView.this,QueryBrokerFirstScreen.class);
                        intent.putExtra("user_id",data.getUser_id());
                        intent.putExtra("id",data.getRequirement_id());
                        startActivity(intent);
                    }
                }
            }, false);
        }
    }

    private void accepted() {
        TextView textView = (TextView) accept.getChildAt(0);
        reject.setVisibility(View.GONE);
        textView.setText("Summit Proposal");
        typeAccept = false;
    }
}
