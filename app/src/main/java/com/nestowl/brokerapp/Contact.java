package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Contact extends AppCompatActivity {
    EditText name,phone,email,qury;
    CardView sumbit;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        name=findViewById(R.id.PROFILE_CONTACT_US_NAME);
        phone=findViewById(R.id.PROFILE_CONTACT_US_PHONE);
        email=findViewById(R.id.PROFILE_CONTACT_US_EMAIL_ADDRESS);
        qury=findViewById(R.id.PROFILE_CONTACT_US_QURY);
        sumbit=findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        back=findViewById(R.id.CONTACT_US_BACK);

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length()<0){
                    name.setError("Enter Name Here.");
                    name.requestFocus();
                    return;
                }
                if (phone.getText().length()!=10){
                    phone.setError("Enter correct Number Here.");
                    phone.requestFocus();
                    return;
                }
                if (email.getText().length()<0){
                    email.setError("Enter Email Here.");
                    email.requestFocus();
                    return;
                }
                postToserver();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
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
    }

    private void postToserver() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Summiting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_CONTACTS_US, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(Contact.this, "Contact request summited", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
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
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("mobile",phone.getText().toString());
                hashMap.put("msg",qury.getText().toString());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}