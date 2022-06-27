package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditSignUpForm extends AppCompatActivity {
    ImageView img_edit,personal,office,work,service,earn,verification,photos;
    CardView cardProgressParrent,cardProgressMain;
    FrameLayout fm_personal,second,third,four,five,six,saven, eight;
    TextView progressText;
    ProgressBar progressBar;
    int complete1,complete2,complete3,complete4,complete5,complete6,complete7,no;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sign_up_form);
        img_edit=findViewById(R.id.EDIT_PROFILE_BACK);
        six=findViewById(R.id.basic_details_six);
        saven=findViewById(R.id.basic_details_saven);
        four=findViewById(R.id.basic_details_fourth);
        eight=findViewById(R.id.basic_primary);
        no=R.drawable.close_icon_multipul;
        complete1=0;
        complete2=0;
        complete3=0;
        complete4=0;
        complete5=0;
        complete6=0;
        complete7=0;
        user = new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        cardProgressMain=findViewById(R.id.PROFILE_SUMIT_PROGRESS_CARD);
        cardProgressParrent=findViewById(R.id.PROFILE_SUMIT_PROGRESS_CARD_PARENT);
        progressText=findViewById(R.id.PROFILE_SUMIT_PROGRESS);
        progressBar=findViewById(R.id.PROFILE_SUMIT_PROGRESSBAR);
        personal=findViewById(R.id.PROFILE_SUMIT_IMG_PERSONAL);
        office=findViewById(R.id.PROFILE_SUMIT_IMG_OFFICE);
        work=findViewById(R.id.PROFILE_SUMIT_IMG_WORK);
        service=findViewById(R.id.PROFILE_SUMIT_IMG_SERVICE_CHARGE);
        earn=findViewById(R.id.PROFILE_SUMIT_IMG_EARN);
        verification=findViewById(R.id.PROFILE_SUMIT_IMG_VERFICATION);
        photos=findViewById(R.id.PROFILE_SUMIT_IMG_PHOTOS);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpEditScreen.class);
                startActivity(intent);
            }
        });
        five=findViewById(R.id.basic_details_five);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpServiceCharges.class);
                startActivity(intent);

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpVerification.class);
                startActivity(intent);

            }
        });
        saven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpPhotos.class);
                startActivity(intent);

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpEarnMoney.class);
                startActivity(intent);

            }
        });
        fm_personal=findViewById(R.id.basic_details_first);
        second=findViewById(R.id.basic_details_second);
        third=findViewById(R.id.basic_details_third);
        fm_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpPersonalDetails.class);
                startActivity(intent);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpOfficeDetails.class);
                startActivity(intent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSignUpForm.this,SignUpWorkDescription.class);
                startActivity(intent);
            }
        });
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getProfileInfo(user.getUser_id());
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
             handlerViews();
             progressDialog.dismiss();
            }
        };
        handler.postDelayed(runnable,3500);

    }

    private void getProfileInfo(String user_id) {
        StringRequest personalDetails = new StringRequest(Request.Method.POST, UrlClass.GET_PERSONAL_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete1=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(personalDetails);
        StringRequest offceDetails = new StringRequest(Request.Method.POST, UrlClass.GET_OFFICE_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete2=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(offceDetails);
        StringRequest work = new StringRequest(Request.Method.POST, UrlClass.GET_WORK_DESCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete3=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(work);
        StringRequest sarvice = new StringRequest(Request.Method.POST, UrlClass.GET_SERVICE_CHARGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete4=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(sarvice);
        StringRequest earnMore = new StringRequest(Request.Method.POST, UrlClass.GET_EARN_MONEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete5=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(earnMore);
        StringRequest varification = new StringRequest(Request.Method.POST, UrlClass.GET_VERIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete6=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(varification);
        StringRequest photos = new StringRequest(Request.Method.POST, UrlClass.GET_PHOTO_UPLOADS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        complete7=1;
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(photos);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handlerViews() {
        progressBar.setProgress(getCompleted());
        progressText.setText(String.valueOf(getCompleted())+"%");
        if (getCompleted()==100){
            progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.Greeen)));
            progressText.setTextColor(ContextCompat.getColor(this, R.color.Greeen));
        }
        if (complete1==0){
            personal.setImageResource(no);
        }
        if (complete2==0){
            office.setImageResource(no);
        }
        if (complete3==0){
            work.setImageResource(no);
        }
        if (complete4==0){
            service.setImageResource(no);
        }
        if (complete5==0){
            earn.setImageResource(no);
        }
        if (complete6==0){
            verification.setImageResource(no);
        }
        if (complete7==0){
            photos.setImageResource(no);
        }
    }
    private int getCompleted(){
        int finalresult = complete1+complete2+complete3+complete4+complete5+complete6+complete7;
        int total = finalresult*100/7;
        return total;
    }

    @Override
    public void onBackPressed() {
        if (getCompleted()==100){
            Intent i = new Intent(EditSignUpForm.this,HomeScreen.class);
            startActivity(i);
            finish();
        }else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Confirming progress...");
            progressDialog.show();
            getProfileInfo(user.getUser_id());
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    handlerViews();
                    progressDialog.dismiss();
                    if (getCompleted()==100){
                        Intent i = new Intent(EditSignUpForm.this,HomeScreen.class);
                        startActivity(i);
                        finish();
                    }
                    new WarningDio(EditSignUpForm.this, "Do you want to exit your profile not completed yet.", "Complete Now", "Exit", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){

                            }else {
                                Intent i = new Intent(EditSignUpForm.this,HomeScreen.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    },false);
                }
            };
            handler.postDelayed(runnable,3500);

        }





    }
}
