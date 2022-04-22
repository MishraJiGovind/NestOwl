package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.LeadsClicks;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.User;
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

public class LeadFirstCardOpen extends AppCompatActivity {
    ImageView imageView,dp;
    FrameLayout frm_view_requirement,doctorFrame,photo,accept,reject,chat;
    String UserId,PropertyID,IDD,NAME,BHK,AD,BUDGET,DP,UNLOCK,STATUS,DOCTOR,DOCTOR2,ID,WANT,LOCATION,userId;
    TextView name,bhk,ad,budget,doctor,status,unlocking,reqText,updateReq,navText;
    Boolean isSeller,isBuyer,isBroker,typeAccept,acceptStatusPro,acceptStatusReq;
    LiveCommnication liveCommnication;
    LeadsClicks handleLeads;
    boolean isAccepted,isSumbited,isProposalAccepted;
    int proposalSumiitedTotal;
    User user;
    ArrayList<AllAcceptedDataModal> propertyAcceptedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_first_card_open);
        frm_view_requirement=findViewById(R.id.LEADS_ACTIVITY_VIEW_REQ);

        Intent intent = getIntent();
        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        userId=user.getUser_id();
        proposalSumiitedTotal=0;
        UserId = intent.getStringExtra("user");
        ID=intent.getStringExtra("id");
        PropertyID = intent.getStringExtra("key");
        NAME = intent.getStringExtra("name");
        BHK = intent.getStringExtra("bhk");
        AD = intent.getStringExtra("ad");
        BUDGET = intent.getStringExtra("budget");
        DP = intent.getStringExtra("dp");
        UNLOCK = intent.getStringExtra("unlock");
        STATUS = intent.getStringExtra("status");
        DOCTOR = intent.getStringExtra("doctor");
        DOCTOR2 = intent.getStringExtra("doctor2");
        IDD = intent.getStringExtra("idd");
        WANT = intent.getStringExtra("want");
        LOCATION = intent.getStringExtra("location");
        isAccepted=intent.getBooleanExtra("isAccepted",false);
        isSumbited=intent.getBooleanExtra("isSumbited",false);
        isProposalAccepted=intent.getBooleanExtra("isProposalAccepted",false);
        imageView=findViewById(R.id.iv34);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSummitedProposals(PropertyID);
        handleLeads =  new LeadsClicks(this,ID,userId,PropertyID);
        Log.e("id error", "onCreate: "+ID+" "+userId+" "+PropertyID);
        Log.e("Accepted", "onCreate: "+isAccepted);
        isSeller=false;
        isBuyer=false;
        isBroker=false;
        typeAccept=true;
        propertyAcceptedData=new ArrayList<>();
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                aichat d =  aichat;
                String key =  d.getText();
                String value = d.getValue();
                if (key.equals("AP")){
                    if (value.equals("true")){
                        acceptStatusPro = true;
                        setproposalPRO();
                    }else{
                        acceptStatusPro = false;
                        setproposalPRO();
                    }
                }
                if (key.equals("AR")){
                    if (value.equals("true")){
                        acceptStatusReq=true;
                        setproposalREQ();
                    }else {
                        acceptStatusReq=false;
                        setproposalREQ();
                    }
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        dp=findViewById(R.id.LEADS_ACTIVITY_DP);
        name=findViewById(R.id.LEADS_ACTIVITY_NAME);
        doctor=findViewById(R.id.LEADS_ACTIVITY_DOCTOR_TEXT);
        unlocking=findViewById(R.id.LEADS_ACTIVITY_UNLOCKS);
        bhk=findViewById(R.id.LEADS_ACTIVITY_BHK);
        ad=findViewById(R.id.LEADS_ACTIVITY_AD);
        budget=findViewById(R.id.LEADS_ACTIVITY_BUDGET);
        doctorFrame=findViewById(R.id.LEADS_ACTIVITY_DOCTOR_FRAME);
        status=findViewById(R.id.LEADS_ACTIVITY_STATUS);
        photo=findViewById(R.id.LEADS_ACTIVITY_VIEWPOHOTO);
        accept=findViewById(R.id.LEADS_ACTIVITY_ACCEPT);
        reject=findViewById(R.id.LEADS_ACTIVITY_REJECT);
        chat=findViewById(R.id.LEADS_ACTIVITY_CHAT);
        reqText=findViewById(R.id.LEADS_ACTIVITY_VIEW_REQ_TEXT);
        updateReq=findViewById(R.id.LEADS_REQ_TEXT_UPDATE);
        navText=findViewById(R.id.LEADS_NAV_TEXT);
        photo.setVisibility(View.GONE);
        setdata();
        if (DOCTOR2!=null){
            reject.setVisibility(View.GONE);
            photo.setVisibility(View.VISIBLE);
            TextView ts = (TextView) photo.getChildAt(0);
            ts.setText("View Contact");
        }
        if (STATUS.equals("SELLER")){
           reqText.setText("See Property");
           reject.setVisibility(View.VISIBLE);
           navText.setText("Seller Details");
           updateReq.setText("Property");
           isSeller=true;
        }
        if (STATUS.equals("NEST PROS")){
            isBroker=true;
            reject.setVisibility(View.GONE);
            photo.setVisibility(View.VISIBLE);
            if (WANT!=null){
                if (WANT.equals("Sell")){
                    navText.setText("Seller Details");
                    updateReq.setText("Property");
                    reqText.setText("See Property");
                }
            }else {
                navText.setText("Seller Details");
                updateReq.setText("Property");
                reqText.setText("See Property");
            }

            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
            TextView ts = (TextView) photo.getChildAt(0);
            ts.setText("View Profile");


        }
        if (STATUS.equals("BUYER")){
            isBuyer=true;
            reject.setVisibility(View.VISIBLE);
        }
        if (isAccepted){
            setAccepted();
        }
        if (isSumbited){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Proposal");
        }
        if (isProposalAccepted){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
        }
        frm_view_requirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (STATUS.equals("SELLER")){
                        Intent intent1 =  new Intent(LeadFirstCardOpen.this,InterestedPropertyThirdUser.class);
                        intent1.putExtra("id",PropertyID);
                        intent1.putExtra("user_id",UserId);
                        intent1.putExtra("idd",IDD);
                        intent1.putExtra("isBroker",false);
                        intent1.putExtra("isAccepted",isAccepted);
                        intent1.putExtra("isSummited",isSumbited);
                        intent1.putExtra("isProposalAccepted",isProposalAccepted);
                        startActivity(intent1);
                    }
                    if (STATUS.equals("BUYER")){
                        Intent intent = new Intent(LeadFirstCardOpen.this,PostRequirementFrontView.class);
                        intent.putExtra("id",PropertyID);
                        intent.putExtra("user_id",UserId);
                        intent.putExtra("idd",IDD);
                        intent.putExtra("isBroker",false);
                        intent.putExtra("isAccepted",isAccepted);
                        intent.putExtra("isSummited",isSumbited);
                        intent.putExtra("isProposalAccepted",isProposalAccepted);
                        startActivity( intent);
                    }
                    if (STATUS.equals("NEST PROS")){
                        if (WANT!=null){
                            if (WANT.equals("Sell")){
                                Intent intent1 =  new Intent(LeadFirstCardOpen.this,InterestedPropertyThirdUser.class);
                                intent1.putExtra("id",PropertyID);
                                intent1.putExtra("user_id",UserId);
                                intent1.putExtra("idd",IDD);
                                intent1.putExtra("isBroker",true);
                                intent1.putExtra("isAccepted",isAccepted);
                                intent1.putExtra("isSummited",isSumbited);
                                intent1.putExtra("isProposalAccepted",isProposalAccepted);

                                startActivity(intent1);
                            }else {
                                Intent intent = new Intent(LeadFirstCardOpen.this,PostRequirementFrontView.class);
                                intent.putExtra("id",PropertyID);
                                intent.putExtra("user_id",UserId);
                                intent.putExtra("idd",IDD);
                                intent.putExtra("isBroker",true);
                                intent.putExtra("isAccepted",isAccepted);
                                intent.putExtra("isSummited",isSumbited);
                                intent.putExtra("isProposalAccepted",isProposalAccepted);
                                startActivity( intent);
                            }
                        }else {
                            Intent intent1 =  new Intent(LeadFirstCardOpen.this,InterestedPropertyThirdUser.class);
                            intent1.putExtra("id",PropertyID);
                            intent1.putExtra("user_id",UserId);
                            intent1.putExtra("idd",IDD);
                            intent1.putExtra("isBroker",true);
                            intent1.putExtra("isAccepted",isAccepted);
                            intent1.putExtra("isSummited",isSumbited);
                            intent1.putExtra("isProposalAccepted",isProposalAccepted);
                            startActivity(intent1);
                        }
                    }
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBroker){
                    Intent intent =  new Intent(LeadFirstCardOpen.this,ViewContact.class);
                    intent.putExtra("pid",PropertyID);
                    intent.putExtra("user_id",UserId);
                    intent.putExtra("id",IDD);
                    startActivity(intent);
                    return;
                }
                if (isSumbited){
                    if (STATUS.equals("SELLER")){
                        Intent intent1 =  new Intent(LeadFirstCardOpen.this,FrontViewQuerySecond.class);
                        intent1.putExtra("id",PropertyID);
                        intent1.putExtra("user_id",UserId);
                        startActivity(intent1);
                    }else {
                        Intent intent1 =  new Intent(LeadFirstCardOpen.this,FrontViewMainSecond.class);
                        intent1.putExtra("id",PropertyID);
                        intent1.putExtra("user_id",UserId);
                        startActivity(intent1);
                    }
                }
                if (isProposalAccepted){
                    Intent intent =  new Intent(LeadFirstCardOpen.this,ViewContact.class);
                    intent.putExtra("pid",PropertyID);
                    intent.putExtra("user_id",UserId);
                    intent.putExtra("id",IDD);
                    startActivity(intent);
                    return;
                }
                if (typeAccept){
                    new WarningDio(LeadFirstCardOpen.this, "Do you want to accept", "Yes", "No", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                if (isBroker){

                                }
                                if (isBuyer){
                                    boolean d = handleLeads.acceptREQ();
                                }
                                if (isSeller){
                                    boolean ds = handleLeads.acceptProperty();
                                }
                            }
                        }
                    },false);

                }else {

                    PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_ID,PropertyID);
                    PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_USER_ID,UserId);
                    if (isBuyer){
                        if (proposalSumiitedTotal<=9){
                            Intent intent =  new Intent(LeadFirstCardOpen.this,QueryBrokerFirstScreen.class);
                            intent.putExtra("user_id",UserId);
                            intent.putExtra("id",PropertyID);
                            intent.putExtra("idd",IDD);
                            intent.putExtra("name",NAME);
                            intent.putExtra("bhk",BHK);
                            intent.putExtra("budget",BUDGET);
                            intent.putExtra("location",LOCATION);
                            PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_ID,PropertyID);

                            startActivity(intent);
                        }else {
                            new WarningDio(LeadFirstCardOpen.this, "This requirement has reached on his proposal summiting limit", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        return;
                    }
                    if (isSeller){
                        if (proposalSumiitedTotal<=9){
                            Intent intent =  new Intent(LeadFirstCardOpen.this,QueryMakeOfferFirstPages.class);
                            intent.putExtra("user_id",UserId);
                            intent.putExtra("id",PropertyID);
                            intent.putExtra("idd",IDD);
                            intent.putExtra("name",NAME);
                            intent.putExtra("bhk",BHK);
                            intent.putExtra("budget",BUDGET);
                            intent.putExtra("location",LOCATION);
                            PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL,PropertyID);
                            startActivity(intent);
                        }else {
                            new WarningDio(LeadFirstCardOpen.this, "This property has reached on his proposal summiting limit", "OK", null, new WarningDio.Response() {
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
                new WarningDio(LeadFirstCardOpen.this, "Do you want to reject", "Yes", "No", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                            if (isBuyer){
                                boolean d =  handleLeads.rejectREQ();
                            }
                            if (isSeller){
                                boolean d =  handleLeads.rejectProperty();
                            }
                        }
                    }
                },false);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =  new Intent(LeadFirstCardOpen.this,Chating.class);
                intent1.putExtra("key", UserId);
                intent1.putExtra("name", NAME);
                intent1.putExtra("dp", DP);
                startActivity(intent1);

            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBroker){
                    Intent intent =  new Intent(LeadFirstCardOpen.this, BrokerSubmitProfileRequirement.class);
                    intent.putExtra("key", UserId);
                    startActivity(intent);
                    return;
                }
                if (DOCTOR2!=null||!DOCTOR2.equals("")){
                    Intent intent =  new Intent(LeadFirstCardOpen.this,ViewContact.class);
                    intent.putExtra("pid",PropertyID);
                    intent.putExtra("user_id",UserId);
                    intent.putExtra("id",IDD);
                    startActivity(intent);
                    return;
                }
            }
        });
        cheackStatus();
        getAllAcceptedRejected(userId);
    }
    private void getAllAcceptedRejected(String userID) {
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("user_id",userID);
        ApiExecutor.getApiService().getAllAccepted(hashMap).enqueue(new retrofit2.Callback<ResponseAllacceptRejectModal>() {
            @Override
            public void onResponse(Call<ResponseAllacceptRejectModal> call, retrofit2.Response<ResponseAllacceptRejectModal> response) {
                Log.e("responseByRetroFits", "onResponse: "+response+" "+response.body().getProperty_data() );
                if (STATUS.equals("SELLER")){
                    propertyAcceptedData=response.body().getProperty_data();
                    for (AllAcceptedDataModal d : propertyAcceptedData){
                        if (d.getPro_id().equals(ID)){
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
                                if (d.getSubmittedproposal().equals("Yes")){
                                    isSumbited=true;
                                }
                            }else {
                                isSumbited=false;
                            }
                        }
                    }
                }else{
                    propertyAcceptedData=response.body().getRequirement_data();
                    for (AllAcceptedDataModal d : propertyAcceptedData){
                        if (d.getPro_id().equals(ID)){
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
                                if (d.getSubmittedproposal().equals("Yes")){
                                    isSumbited=true;
                                }
                            }else {
                                isSumbited=false;
                            }
                        }
                    }
                }

                handlerAceptsRejects();
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);
            }
        });
    }
    private void handlerAceptsRejects() {
        if (isAccepted){
            setAccepted();
        }
        if (isSumbited){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Proposal");
        }
        if (isProposalAccepted){
            TextView t = (TextView) accept.getChildAt(0);
            t.setText("View Contact");
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
    private void cheackStatus() {

    }
    private void setdata() {
        Glide.with(this).load(UrlClass.BASE_URL+DP).placeholder(R.drawable.user_icon_p).into(dp);
        name.setText(NAME);
        if (UNLOCK==null){
            unlocking.setVisibility(View.GONE);
        }else if (UNLOCK.equals("Unlocking in 0sec")||UNLOCK.equals("0sec")){
            unlocking.setVisibility(View.GONE);
        }else {
            unlocking.setText(UNLOCK);
        }
        bhk.setText(BHK);
        ad.setText(AD);
        budget.setText(BUDGET);
        status.setText(STATUS);
        if (DOCTOR==null||DOCTOR.equals("")){
            doctorFrame.setVisibility(View.GONE);
        }else {
            doctor.setText(DOCTOR);
        }
    }
    public void setproposalREQ(){
        if (!acceptStatusReq){
            new WarningDio(LeadFirstCardOpen.this, "Requirement Rejected", "Ok", "Cancel", new WarningDio.Response() {
                @Override
                public void getClicks(int click) {

                }
            },false);
        }else {
            new WarningDio(LeadFirstCardOpen.this, "Requirement Accepted\nSummit your proposal in next 180 minutes", "Summit Proposal", null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {
                   setAccepted();
                   if (click==1){
                       PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_ID,PropertyID);
                       PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_USER_ID,UserId);
                       if (isSeller){
                           Intent intent =  new Intent(LeadFirstCardOpen.this,QueryMakeOfferFirstPages.class);
                           intent.putExtra("user_id",UserId);
                           intent.putExtra("id",PropertyID);
                           intent.putExtra("idd",IDD);
                           intent.putExtra("name",NAME);
                           intent.putExtra("bhk",BHK);
                           intent.putExtra("budget",BUDGET);
                           intent.putExtra("location",LOCATION);
                           startActivity(intent);
                       }
                       if (isBuyer){
                           Intent intent =  new Intent(LeadFirstCardOpen.this,QueryBrokerFirstScreen.class);
                           intent.putExtra("user_id",UserId);
                           intent.putExtra("id",PropertyID);
                           intent.putExtra("idd",IDD);
                           intent.putExtra("name",NAME);
                           intent.putExtra("bhk",BHK);
                           intent.putExtra("budget",BUDGET);
                           intent.putExtra("location",LOCATION);
                           startActivity(intent);
                       }
                   }
                }
            }, false);
        }
    }
    public void setproposalPRO(){
        if (!acceptStatusPro){
            new WarningDio(LeadFirstCardOpen.this, "Property Rejected", "Ok", "Cancel", new WarningDio.Response() {
                @Override
                public void getClicks(int click) {

                }
            },false);
        }else {
            new WarningDio(LeadFirstCardOpen.this, "Property Accepted\nSummit your proposal in next 180 minutes", "Summit Proposal", null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {
                    setAccepted();
                    if (click==1){
                        PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_ID,PropertyID);
                        PrefMananger.saveString(LeadFirstCardOpen.this,PrefMananger.PROPOSAL_USER_ID,UserId);
                        if (isSeller){
                            Intent intent =  new Intent(LeadFirstCardOpen.this,QueryMakeOfferFirstPages.class);
                            intent.putExtra("user_id",UserId);
                            intent.putExtra("id",PropertyID);
                            intent.putExtra("idd",IDD);
                            intent.putExtra("name",NAME);
                            intent.putExtra("bhk",BHK);
                            intent.putExtra("budget",BUDGET);
                            intent.putExtra("location",LOCATION);
                            startActivity(intent);
                        }
                        if (isBuyer){
                            Intent intent =  new Intent(LeadFirstCardOpen.this,QueryBrokerFirstScreen.class);
                            intent.putExtra("user_id",UserId);
                            intent.putExtra("id",PropertyID);
                            intent.putExtra("idd",IDD);
                            intent.putExtra("name",NAME);
                            intent.putExtra("bhk",BHK);
                            intent.putExtra("budget",BUDGET);
                            intent.putExtra("location",LOCATION);
                            startActivity(intent);
                        }
                    }
                }
            }, false);
        }
    }
    public void setAccepted(){
        TextView textView = (TextView) accept.getChildAt(0);
        reject.setVisibility(View.GONE);
        textView.setText("Summit Proposal");
        typeAccept = false;
    }
}
