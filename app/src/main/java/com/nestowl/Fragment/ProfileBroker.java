package com.nestowl.Fragment;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.brokerapp.Notification;
import com.nestowl.model.DpModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.User;
import com.nestowl.brokerapp.EditSignUpForm;
import com.nestowl.brokerapp.FeedBack;
import com.nestowl.brokerapp.LoginActivity;
import com.nestowl.brokerapp.Payments;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SahilDahiyaCircle;
import com.nestowl.brokerapp.SignUpEarnMoney;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileBroker extends Fragment {
    LinearLayout edt_profile;
    TextView tv_feedback,rate_us,logout,name,email,extra;
    ImageView dp;
    FrameLayout hideView,complete;
    LoginPojo loginPojo;
    CardView FAQ;
    Context context;


    LinearLayout view_nest,lnr_payment,notification;

    public ProfileBroker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_broker, container, false);
        context=getContext();
        edt_profile=view.findViewById(R.id.lnr_post_property);
        rate_us=view.findViewById(R.id.rate_us_on_the);
        tv_feedback=view.findViewById(R.id.send_feedback);
        hideView=view.findViewById(R.id.PROFILE_SERVE_INDIAN_HERO_LNR);
        complete=view.findViewById(R.id.PROFILE_SERVE_INDIAN_HERO_COMPLETE);
        extra=view.findViewById(R.id.EXTRA_TEXT_VIEW_TO_HIDE);
        name=view.findViewById(R.id.HOME_PROFILE_NAME);
        email=view.findViewById(R.id.HOME_PROFILE_EMAIL);
        dp=view.findViewById(R.id.HOME_PROFILE_DP);
        FAQ=view.findViewById(R.id.PROFILE_FAQ);
        logout=view.findViewById(R.id.logout);
        notification=view.findViewById(R.id.lnr_activity);
        loginPojo= PrefMananger.GetLoginData(context);
        getUserInfo();
        getEarnmoreInfo();
        name.setText(loginPojo.getFirstName());
        email.setText(loginPojo.getEmail());
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Notification.class));

            }
        });
        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, com.nestowl.brokerapp.FAQ.class));
            }
        });
        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getActivity().getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getActivity().getPackageName())));
                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new WarningDio(context, "Do you want log out your account?", "LOG OUT", null, new WarningDio.Response() {
                   @Override
                   public void getClicks(int click) {
                       if (click==1){
                           PrefMananger.SaveLoginData(context,null);
                           startActivity(new Intent(context, LoginActivity.class));
                           getActivity().finish();
                       }
                   }
               },false);
/*
                startActivity(new Intent(context, FrontViewQuerySecond.class));

*/

            }
        });
        view_nest=view.findViewById(R.id.lnd_view_nest_profile);
        lnr_payment=view.findViewById(R.id.lnr_payment);
        lnr_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Payments.class));
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SignUpEarnMoney.class));

            }
        });
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FeedBack.class));

            }
        });
        view_nest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SahilDahiyaCircle.class));


            }
        });
        edt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, EditSignUpForm.class));


            }
        });
        return view;
    }

    private void getEarnmoreInfo() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_EARN_MONEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  staus =  jsonObject.getString("status");
                    if (staus.equals("1")){
                       JSONObject jsonObject1 =  jsonObject.getJSONObject("data");
                       String joinNow  =jsonObject1.getString("join_now").toLowerCase().replace(" ","");
                       String benifits  =jsonObject1.getString("benifit_to_you").toLowerCase().replace(" ","");
                       String equls  ="maybelater";
                       if (joinNow.equals(equls)|benifits.equals(equls)){
                           hideView.setVisibility(View.VISIBLE);
                           extra.setVisibility(View.VISIBLE);
                        }
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
                hashMap.put("user_id",String.valueOf(loginPojo.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void getUserInfo() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  staus =  jsonObject.getString("status");
                    if (staus.equals("1")){
                        User data  = new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        DpModal dps  = new Gson().fromJson(jsonObject.getJSONObject("Photo").toString(), DpModal.class);
                        Glide.with(context).load(UrlClass.BASE_URL+dps.getProfile_photo()).placeholder(R.drawable.profile_img_sahil).into(dp);
                        data.setAvatar(UrlClass.BASE_URL+dps.getProfile_photo());
                        String json =  new Gson().toJson(data);
                        PrefMananger.saveString(context,"user",json);
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
                hashMap.put("user_id",String.valueOf(loginPojo.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }


}
