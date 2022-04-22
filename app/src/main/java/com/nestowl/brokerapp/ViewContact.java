package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public class ViewContact extends AppCompatActivity {
    ConstraintLayout farm,contact;
    FrameLayout sumbit;
    TextView name,mobile,email,warning;
    EditText nameEdit,mobileEdit,emailEdit;
    ImageView quit;
    Intent intent;
    String UID,PID,ID;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        intent =  getIntent();
        UID = intent.getStringExtra("user_id");
        PID = intent.getStringExtra("pid");
        ID = intent.getStringExtra("id");
        user = new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        farm=findViewById(R.id.VIEW_CONTACT_MAIN_FARM);
        contact=findViewById(R.id.VIEW_CONTACT_CONTACT_SHOW);
        sumbit=findViewById(R.id.VIEW_CONTACT_SUMBIT);
        name=findViewById(R.id.VIEW_CONTACT_SHOW_NAME);
        mobile=findViewById(R.id.VIEW_CONTACT_SHOW_MOBILE);
        email=findViewById(R.id.VIEW_CONTACT_SHOW_EMAIL);
        warning=findViewById(R.id.VIEW_CONTACT_TEXT);
        nameEdit=findViewById(R.id.VIEW_CONTACT_FARM_NAME);
        mobileEdit=findViewById(R.id.VIEW_CONTACT_FARM_MOB);
        emailEdit=findViewById(R.id.VIEW_CONTACT_FARM_EMAIL);
        quit=findViewById(R.id.VIEW_CONTACT_QUIT);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        nameEdit.setText(user.getFirst_name());
        emailEdit.setText(user.getEmail());
        mobileEdit.setText(user.getMobile());
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEdit.getText()==null|nameEdit.getText().length()<=0){
                    nameEdit.setError("Enter your name here");
                    return;
                }
                if (mobileEdit.getText()==null|mobileEdit.getText().length()<=0){
                    mobileEdit.setError("Enter your mobile number here");
                    return;
                }
                if (emailEdit.getText()==null|emailEdit.getText().length()<=0){
                    emailEdit.setError("Enter your email here");
                    return;
                }
                sumbitContactData();
                getContactInfo();
            }

        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDio(ViewContact.this, "Do you Want Call "+mobile.getText().toString(), "Call Now", "Copy Number", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){

                            Intent intenta = new Intent();
                            intenta.setAction(Intent.ACTION_CALL);
                            String nu = mobile.getText().toString();
                            intenta.setData(Uri.parse("tel:"+nu));
                            startActivity(intenta);
                        }else {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Mobile", mobile.getText().toString());
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                },false);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDio(ViewContact.this, "Copy Email", "YES", "NO", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("Email", email.getText().toString());
                            clipboard.setPrimaryClip(clip);
                        }
                    }
                },false);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Log.e("Uid", "onCreate: user id "+UID );
    }
    private void sumbitContactData(){
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Summiting...");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_CONTACT_SAVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String stus = jsonObject.getString("status");
                    if (stus.equals("1")){
                        getContactInfo();
                    }
                }catch (Exception e){

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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("property_id",PID);
                hashMap.put("user_id",UID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    };
    private void getContactInfo(){
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Getting Contact...");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                  JSONObject jsonObject =  new JSONObject(response);
                  String status =  jsonObject.getString("status");
                  if (status.equals("1")&&jsonObject.has("data")){
                      User data =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                      farm.setVisibility(View.GONE);
                      contact.setVisibility(View.VISIBLE);
                      sumbit.setVisibility(View.GONE);
                      warning.setText("Your contact summited successfully");
                      name.setText(data.getFirst_name());
                      mobile.setText(data.getMobile());
                      email.setText(data.getEmail());
                  }
                }catch (Exception e){
                    Log.e("error", "onErrorResponse: "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onErrorResponse: "+error);
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id",UID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}