package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.AcceptRejectModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VeiwDetailsOpenScreen extends AppCompatActivity {
    LinearLayout postedByLnr,requestAcceptLnr,proposalSumbitedLnr,customerAcceptedLnr,dealClosedLnr,askReviewLnr;
    TextView propertyId,postedByTxt,requestAcceptTxt,proposalSumbitedTxt,customerAcceptedTxt,dealClosedTxt;
    ImageView postedByImg,requestAcceptImg,proposalSumbitedImg,customerAcceptedImg,dealClosedImg,askReviewImg;
    FrameLayout askForReviewButton,line;
    RadioButton dealClosedButton;
    String id,user_id,property_id,p_id,UserId,jsssonStrings,Type;
    int key;
    LoginPojo loginPojo;
    int greenTick = R.drawable.green_circle;
    User user;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_details_open_screen);
        id=getIntent().getStringExtra("id");
        user_id=getIntent().getStringExtra("user_id");
        property_id=getIntent().getStringExtra("property_id");
        p_id=getIntent().getStringExtra("P_ID");
        key=getIntent().getIntExtra("view",0);
        Type="Property";
        pd=new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Sumbiting...");

        jsssonStrings =  PrefMananger.getString(this,"status");
        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        if (jsssonStrings!=null){
            try {
                JSONArray jsonArray =  new JSONArray(jsssonStrings);
                for (int i=0;i<jsonArray.length();i++){
                    AcceptRejectModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),AcceptRejectModal.class);
                    if (p_id.equals(data.getPro_id())){
                        setView(data);
                    }
                }
            }catch (Exception e){

            }
        }

        postedByLnr=findViewById(R.id.ALERT_STATUS_TRACKER_POSTED_BY_LNR);
        requestAcceptLnr=findViewById(R.id.ALERT_STATUS_TRACKER_REQ_ACCEPTED_LNR);
        proposalSumbitedLnr=findViewById(R.id.ALERT_STATUS_TRACKER_SUMBITED_LNR);
        customerAcceptedLnr=findViewById(R.id.ALERT_STATUS_TRACKER_CUSTOMER_ACCEPT_LNR);
        dealClosedLnr=findViewById(R.id.ALERT_STATUS_TRACKER_DEAL_CLOSED_LNR);
        askReviewLnr=findViewById(R.id.ALERT_STATUS_TRACKER_ASK_REVIEW_LNR);

        propertyId=findViewById(R.id.ALERT_STATUS_TRACKER_PROPERTY_ID);
        postedByTxt=findViewById(R.id.ALERT_STATUS_TRACKER_POSTED_BY_TXT);
        requestAcceptTxt=findViewById(R.id.ALERT_STATUS_TRACKER_ACCEPT_REQ_TXT);
        proposalSumbitedTxt=findViewById(R.id.ALERT_STATUS_TRACKER_SUMBITED_TXT);
        customerAcceptedTxt=findViewById(R.id.ALERT_STATUS_TRACKER_CUSTOMER_ACCEPTED_TXT);
        dealClosedTxt=findViewById(R.id.ALERT_STATUS_TRACKER_DEALCLOSED_TXT);

        postedByImg=findViewById(R.id.ALERT_STATUS_TRACKER_POSTED_BY_IMG);
        requestAcceptImg=findViewById(R.id.ALERT_STATUS_TRACKER_REQ_ACCEPT_IMG);
        proposalSumbitedImg=findViewById(R.id.ALERT_STATUS_TRACKER_SUMBITED_IMG);
        customerAcceptedImg=findViewById(R.id.ALERT_STATUS_TRACKER_CUSTOMER_ACCEPT_IMG);
        dealClosedImg=findViewById(R.id.ALERT_STATUS_TRACKER_DEAL_CLOSED_IMG);
        askReviewImg=findViewById(R.id.ALERT_STATUS_TRACKER_ASK_REVIEW_IMG);

        askForReviewButton=findViewById(R.id.ALERT_STATUS_TRACKER_ASK_FOR_REVIEW_BTN);
        dealClosedButton=findViewById(R.id.ALERT_STATUS_TRACKER_DEAL_CLOSED_BUTTON);

        line=findViewById(R.id.DEAL_STATUS_LINE);

        loginPojo = PrefMananger.GetLoginData(this);
        UserId=String.valueOf(loginPojo.getUserId());
        propertyId.setText("Property id: "+property_id);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        fetchDataFromServer();
        getmoreInfo();
//        getName();
        askForReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               askForReview();
            }
        });
        dealClosedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealclosed();
            }
        });
    }

    private void getmoreInfo() {
        if (key==4){
            StringRequest req = new StringRequest(Request.Method.POST, UrlClass.LEADS_REQ_DETAILS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("1")){
                            LeadsRequmentsModal requmentsModal = new Gson().fromJson(jsonObject.getJSONObject("requirement_data").toString(),LeadsRequmentsModal.class);
                            getName(requmentsModal.getProperty());
                        }
                    }catch (Exception e){
                        Log.e("REQ ERROR", "onResponse: "+e );
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
                    hashMap.put("requirement_id",property_id);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(req);
            return;
        }
        if (key==5){
            StringRequest pro = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_DETAILS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("1")){
                            LeadsPublicPro requmentsModal = new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(),LeadsPublicPro.class);
                            getName(requmentsModal.getProperty());
                        }
                    }catch (Exception e){
                        Log.e("PRO ERROR", "onResponse: "+e );
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
                    hashMap.put("property_id",property_id);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(pro);
        }
    }
    private void dealclosed() {
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DEAL_CLOSED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(VeiwDetailsOpenScreen.this, "Deal Closed Successfully.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }else {
                        new WarningDio(VeiwDetailsOpenScreen.this, "Deal already closed.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                }catch ( Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                hashMap.put("id",p_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void askForReview() {
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.ASK_FOR_REVIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status  = jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(VeiwDetailsOpenScreen.this, "Review request sumbited successfully.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }else {
                        new WarningDio(VeiwDetailsOpenScreen.this, "Review request already sumbited.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                }catch (Exception e){
                    Log.e("error in ask", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                hashMap.put("p_id",p_id);
                hashMap.put("type",Type);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void sumbitResponse() {
        new WarningDio(this, "Your Response has been Summited.", "OK", "CANCEL", new WarningDio.Response() {
            @Override
            public void getClicks(int click) {

            }
        },false);
    }
    private void getName(String property) {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        if (key==5){
                            postedByTxt.setText(user.getFirst_name()+" has sent a lead for "+property);
                            postedByImg.setImageResource(greenTick);
                        }
                        if (key==4){
                            postedByImg.setImageResource(greenTick);
                            postedByTxt.setText(user.getFirst_name()+" has sent a requirement for "+property);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void fetchDataFromServer() {
        Log.e("id", "fetchDataFromServer: "+UserId+" "+user_id );
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_ALL_ACCEPTED_BY_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("errorTracker", "onResponse: "+response );
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("alldata");
                        for (int i=0;i<jsonArray.length();i++){
                            AcceptRejectModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),AcceptRejectModal.class);
                            if (p_id.equals(data.getPro_id())){
                                setView(data);
                            }
                        }
                    }
                }catch (Exception e){
                    Log.e("errorTracker", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorTracker", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",UserId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void setView(AcceptRejectModal data) {
        if (data.getSubmittedproposal()==null){
            proposalSumbitedLnr.setVisibility(View.GONE);
        }
        if (data.getAcceptedproposal()==null){
            requestAcceptLnr.setVisibility(View.GONE);
        }
        if (data.getClient_acccept()==null){
            customerAcceptedLnr.setVisibility(View.GONE);
        }
        try {
            if (data.getSubmittedproposal()!=null){
                if (data.getSubmittedproposal().equals("Yes")){
                    proposalSumbitedImg.setImageResource(greenTick);
                }
            }

            if (data.getAcceptedproposal()!=null){
                if (data.getAcceptedproposal().equals("Yes")){
                    requestAcceptImg.setImageResource(greenTick);
                }
            }

            if (data.getClient_acccept()!=null){
                if (data.getClient_acccept().equals("Yes")){
                    customerAcceptedImg.setImageResource(greenTick);
                }
            }

            if (data.getDeal()!=null){
                if (data.getDeal().contains("Yes")){
                    dealClosedImg.setImageResource(greenTick);
                    dealClosedButton.setChecked(true);
                    dealClosedButton.setClickable(false);
                    askReviewLnr.setVisibility(View.VISIBLE);
                    line.setVisibility(View.VISIBLE);
                }
            }

        }catch (Exception e){
            Log.e("error in status", "setView: "+e );
        }
    }
    private void yesDialog() {
        new WarningDio(this, "Your Request has been Summited for Review.", "OK", "CANCEL", new WarningDio.Response() {
            @Override
            public void getClicks(int click) {

            }
        },false);
    }

}
