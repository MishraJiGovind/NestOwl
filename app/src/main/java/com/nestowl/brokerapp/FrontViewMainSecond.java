package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
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
import com.bumptech.glide.Glide;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProposalStringModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrontViewMainSecond extends AppCompatActivity {
    FrameLayout frm_propperty_one;
    ImageView back_img,img1,img2;
    TextView tv_near,hello,mentionedPrice,mentionedPriceENG,bookingAmmount,bookingAmmountENG,idNumber,idDay,idTime,meet,meetday,meettime,property_name,brokerName;
    LinearLayout lnd_few,Limages;
    RadioButton inventoryies,stage,verify,trance,owner,furnish,time,finaltime,paymode,video,videotype,meets,loacl,clint,pick,docs;
    String SELECTED_PRO,ID,USER_ID,Sinventoryies,Sstage,Sverify,Strance,Sowner,Sfurnish,Stime,Sfinaltime,Spaymode,Svideo,Svideotype,Smeets,Sloacl,Sclint,Spick,Sdocs,SmentionedPrice,SbookingAmmount,SidNumber,SidDay,SidTime,Smeet,Smeetday,Smeettime;
    LoginPojo loginPojo;
    int pro,actives,lenth;
    ArrayList<ProposalStringModal> property_one;
    ArrayList<String> images, images1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_view_main_second);
        pro=1;
        actives=1;
        lenth=1;
        SELECTED_PRO="Property1";
        ID =  getIntent().getStringExtra("id");
        USER_ID =  getIntent().getStringExtra("user_id");
        Log.e("Data Recicvie", "onCreate: "+ID+" "+USER_ID );
        frm_propperty_one=findViewById(R.id.ALERT_LEADS_STATUS);
        lnd_few=findViewById(R.id.PROPOSAL_REQ_PRO_MEET_MISSING_TAB);
        property_name=findViewById(R.id.ALERT_LEAD_PROPERTY_SELECTED);
        brokerName=findViewById(R.id.PROPOSAL_VIEW_REQ_BROKER_NAME);
        img1=findViewById(R.id.PROPOSAL_VIEW_REQ_IMG_1);
        img2=findViewById(R.id.PROPOSAL_VIEW_REQ_IMG_2);
        loginPojo = PrefMananger.GetLoginData(this);
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        tv_near=findViewById(R.id.show_all_nearby_places_post_property);
        tv_near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrontViewMainSecond.this,QueryShowAll.class);
                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        frm_propperty_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(FrontViewMainSecond.this);
                dialog.setContentView(R.layout.front_view_property);
                FrameLayout pro1,pro2,pro3,accept,reject;
                ImageView clear;
                int defaults = R.drawable.employe_circle_rounded;
                int active = R.drawable.selected_background_filter;
                pro1=dialog.findViewById(R.id.DIO_PROPOSAL_REQ_PRO_1);
                pro2=dialog.findViewById(R.id.DIO_PROPOSAL_REQ_PRO_2);
                pro3=dialog.findViewById(R.id.DIO_PROPOSAL_REQ_PRO_3);
                accept=dialog.findViewById(R.id.DIO_PROPOSAL_REQ_PRO_APPLY);
                reject=dialog.findViewById(R.id.DIO_PROPOSAL_REQ_PRO_CANCEL);
                clear=dialog.findViewById(R.id.ivf1);
                if (lenth==2){
                    pro2.setVisibility(View.VISIBLE);
                }
                if (lenth==3){
                    pro3.setVisibility(View.VISIBLE);
                }
                if (actives==1){
                    pro1.setBackgroundResource(active);
                }
                if (actives==2){
                    pro2.setBackgroundResource(active);
                }
                if (actives==3){
                    pro3.setBackgroundResource(active);
                }

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(FrontViewMainSecond.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                pro1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pro1.setBackgroundResource(active);
                        pro2.setBackgroundResource(defaults);
                        pro3.setBackgroundResource(defaults);
                        pro=1;
                        actives=1;
                    }
                });
                pro2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pro=2;
                        actives=2;
                        pro1.setBackgroundResource(defaults);
                        pro2.setBackgroundResource(active);
                        pro3.setBackgroundResource(defaults);
                    }
                });
                pro3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pro=3;
                        actives=3;
                        pro1.setBackgroundResource(defaults);
                        pro2.setBackgroundResource(defaults);
                        pro3.setBackgroundResource(active);
                    }
                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (actives==1){
                           property_name.setText("Property 1");
                        }
                        if (actives==2){
                            property_name.setText("Property 2");
                        }
                        if (actives==3){
                            property_name.setText("Property 3");
                        }
                        filterProperty(pro);
                        dialog.dismiss();
                    }
                });
                reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        property_one =  new ArrayList<>();
        images =  new ArrayList<>();
        images1 = new ArrayList<>();

        Limages=findViewById(R.id.PROPOSAL_VIEW_REQ_IMAGES);

        hello=findViewById(R.id.PROPOSAL_VIEW_REQ_HELLO);
        mentionedPrice=findViewById(R.id.PROPOSAL_VIEW_REQ_ASKING_PRICE);
        bookingAmmount=findViewById(R.id.PROPOSAL_VIEW_REQ_BOOKKING);
        mentionedPriceENG=findViewById(R.id.PROPOSAL_VIEW_REQ_ASKING_ENG);
        bookingAmmountENG=findViewById(R.id.PROPOSAL_VIEW_REQ_BOOKING_ENG);
        idNumber=findViewById(R.id.PROPOSAL_VIEW_REQ_VID_IN);
        idDay=findViewById(R.id.PROPOSAL_VIEW_REQ_VID_DAY);
        idTime=findViewById(R.id.PROPOSAL_VIEW_REQ_VID_TIME);
        meet=findViewById(R.id.PROPOSAL_VIEW_REQ_MEET_IN);
        meetday=findViewById(R.id.PROPOSAL_VIEW_REQ_MEET_DAY);
        meettime=findViewById(R.id.PROPOSAL_VIEW_REQ_MEET_TIME);


        inventoryies=findViewById(R.id.PROPOSAL_VIEW_REQ_INVENTORYIES);
        stage=findViewById(R.id.PROPOSAL_VIEW_REQ_STAGE);
        verify=findViewById(R.id.PROPOSAL_VIEW_REQ_VERIFYIED);
        trance=findViewById(R.id.PROPOSAL_VIEW_REQ_TRANSE);
        owner=findViewById(R.id.PROPOSAL_VIEW_REQ_OWNER);
        furnish=findViewById(R.id.PROPOSAL_VIEW_REQ_FURNISH);
        time=findViewById(R.id.PROPOSAL_VIEW_REQ_TIME);
        finaltime=findViewById(R.id.PROPOSAL_VIEW_REQ_FINAL);
        paymode=findViewById(R.id.PROPOSAL_VIEW_REQ_MODE);
        video=findViewById(R.id.PROPOSAL_VIEW_REQ_VID);
        videotype=findViewById(R.id.PROPOSAL_VIEW_REQ_VID_MEDIA);
        meets=findViewById(R.id.PROPOSAL_VIEW_REQ_MEET);
        loacl=findViewById(R.id.PROPOSAL_VIEW_REQ_LOCAL);
        clint=findViewById(R.id.PROPOSAL_VIEW_REQ_VERIFICATION_CLINT);
        pick=findViewById(R.id.PROPOSAL_VIEW_REQ_PICK_DROP);
        docs=findViewById(R.id.PROPOSAL_VIEW_REQ_DOCS);
        FetchData();
    }

    private void filterProperty(int pro) {
        if (pro==1){
            setStrings(property_one.get(0));
        }
        if (pro==2){
            setStrings(property_one.get(1));
        }
        if (pro==3){
            setStrings(property_one.get(2));
        }
    }
    private void FetchData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Wait...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_VIEW_PROPOSAL_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String s =  jsonObject.getString("status");
                    if (s.equals("1")){
                        JSONArray jsonObject1 =  jsonObject.getJSONArray("Brokerpostrequirement");
                        lenth=jsonObject1.length();
                        for (int i =0;i<=jsonObject1.length();i++){
                          ProposalStringModal save =  new Gson().fromJson(jsonObject1.getJSONObject(i).toString(),ProposalStringModal.class);
                          property_one.add(save);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                filterProperty(1);

                            }
                        },1000);

                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",USER_ID);
                hashMap.put("requirement_id",ID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    protected void setStrings(ProposalStringModal data){
        Sinventoryies=data.getInventories();
        Sstage=data.getStage();
        Sverify=data.getVerified_document_property();
        Strance=data.getTransaction_type();
        Sowner=data.getOwnership();
        Sfurnish=data.getProperty_status();
        Stime=data.getBooking_payment();
        Sfinaltime=data.getFinal_payment();
        Spaymode=data.getMode_of_payment();
        Svideo=data.getVideo_tour();
        Svideotype=data.getChat_type();
        Smeets=data.getProperty_meet();
        Sloacl=data.getLocal_facilty();
        Sclint=data.getClient_verification();
        Spick=data.getPick_and_drop();
        Sdocs=data.getProper_document();
        SmentionedPrice=data.getPrice();
        SbookingAmmount=data.getBooking_amount();
        SidNumber=data.getChat_number();
        SidDay=data.getPossilbe_date();
        SidTime=data.getPossilbe_time();
        Smeet=data.getMissing_requirement();
        Smeetday=data.getMeeting_date();
        Smeettime=data.getMeeting_time();
        Glide.with(this).load(UrlClass.BASE_URL+data.getPropertyimage1()).placeholder(R.drawable.default_x_x).into(img1);
        Glide.with(this).load(UrlClass.BASE_URL+data.getPropertyimage2()).placeholder(R.drawable.default_x_x).into(img2);
        settData();
        if (String.valueOf(loginPojo.getUserId()).equals(USER_ID)){
            brokerName.setText("("+loginPojo.getFirstName()+")");
        }else {
            getBrokerName(USER_ID);
        }
    }

    private void getBrokerName(String user_id) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getString("data").toString(),User.class);
                        brokerName.setText("("+user.getFirst_name()+")");
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
                HashMap<String,String>hashMap= new HashMap<>();
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void settData() {
        hello.setText("Hello "+loginPojo.getFirstName());
        mentionedPrice.setText(SmentionedPrice);
        mentionedPriceENG.setText(setTextInEnglish(SmentionedPrice));
        bookingAmmountENG.setText(setTextInEnglish(SbookingAmmount));
        bookingAmmount.setText(SbookingAmmount);
        idNumber.setText(SidNumber);
        idDay.setText(SidDay);
        idTime.setText(SidTime);
        meet.setText(Smeet);
        meetday.setText(Smeetday);
        meettime.setText(Smeettime);
        setchekboxText(inventoryies,Sinventoryies);
        setchekboxText(stage,Sstage);
        setchekboxText(verify,Sverify);
        setchekboxText(trance,Strance);
        setchekboxText(owner,Sowner);
        setchekboxText(furnish,Sfurnish);
        setchekboxText(time,Stime);
        setchekboxText(finaltime,Sfinaltime);
        setchekboxText(paymode,Spaymode);
        setchekboxText(video,Svideo);
        setchekboxText(videotype,Svideotype);
        setchekboxText(meets,Smeets);
        setchekboxText(loacl,Sloacl);
        setchekboxText(clint,Sclint);
        setchekboxText(pick,Spick);
        setchekboxText(docs,Sdocs);

    }
    private void setchekboxText(RadioButton inventoryies, String sinventoryies) {
        inventoryies.setChecked(true);
        inventoryies.setText(sinventoryies);
    }
    private String setTextInEnglish(String s) {
        int no = Integer.parseInt(s.toString());
        String data = null;
        try {
            if (no>=1000000000){
                no=no/1000000000;
                data="₹"+no+" Arab";
            }
            if (no>=10000000){
                no = no/10000000;
                data="₹"+no+" Crore";
            }
            if (no>=100000){
                no = no/100000;
                data = "₹"+no+" Lakh";
            }
            if (no>=1000){
                no = no/1000;
                data = "₹"+no+" Hajar";
            }
        }catch (Exception e){
            Log.e("text", "setTextInEnglish: "+e );
        }
        return data;
    }
}
