package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.LoginPojo;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FeedBack extends AppCompatActivity {
    TextView name,cardviewtext;
    RadioGroup radioGroup;
    RadioButton awossomer,goodr,improver;
    CardView cardView;
    LoginPojo loginPojo;
    EditText email,comments;
    ConstraintLayout screen1,screen2,screen3;
    String radioOutput;
    int screens;
    LinearLayout awosome,good,improve;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        if (Build.VERSION.SDK_INT >= 21) {
            loginPojo = PrefMananger.GetLoginData(this);
            name=findViewById(R.id.FEEDBACK_USERNAME);
            email=findViewById(R.id.FEEDBACK_EMAIL);
            comments=findViewById(R.id.FEEDBACK_COMMENTS);
            radioGroup=findViewById(R.id.FEEDBACK_RADIO_GRUOP);
            cardView=findViewById(R.id.FEEDBACK_CONTINIUE);
            awosome=findViewById(R.id.FEEDBACK_RADIO_LNR_AWOSOME);
            good=findViewById(R.id.FEEDBACK_RADIO_LNR_GOOD);
            improve=findViewById(R.id.FEEDBACK_RADIO_LNR_IMPROVE);
            awossomer=findViewById(R.id.FEEDBACK_RADIO_AWOSOME);
            goodr=findViewById(R.id.FEEDBACK_RADIO_GOOD);
            improver=findViewById(R.id.FEEDBACK_RADIO_IMPROVE);
            back=findViewById(R.id.FEEDBACK_BACK);

            screen1=findViewById(R.id.FEEDBACK_SCREEN_1);
            screen2=findViewById(R.id.FEEDBACK_SCREEN_2);
            screen3=findViewById(R.id.FEEDBACK_SCREEN_3);

            name.setText("Hello "+loginPojo.getFirstName());
            email.setText(loginPojo.getEmail());

            cardviewtext=(TextView)cardView.getChildAt(0);

            radioOutput="Awesome";
            screens=1;



            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (screens==1){
                        if (awossomer.isChecked()){
                            radioOutput="Awesome";
                        }
                        if (goodr.isChecked()){
                            radioOutput="Good";
                        }
                        if (improver.isChecked()){
                            radioOutput="Improve";
                        }
                        screen1.setVisibility(View.GONE);
                        screen2.setVisibility(View.VISIBLE);
                        screens=2;
                        cardviewtext.setText("Summit");
                        return;
                    }
                    if (screens==2){
                        if (email.getText().length()<0){
                            email.setError("Enter email here");
                            email.requestFocus();
                            return;
                        }
                        handleSumbition();
                        return;
                    }
                    if (screens==3){
                        finish();
                    }
                }
            });
            improve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(true);
                    goodr.setChecked(false);
                    awossomer.setChecked(false);
                }
            });
            good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(false);
                    goodr.setChecked(true);
                    awossomer.setChecked(false);
                }
            });
            awosome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(false);
                    goodr.setChecked(false);
                    awossomer.setChecked(true);
                }
            });
            improver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(true);
                    goodr.setChecked(false);
                    awossomer.setChecked(false);
                }
            });
            goodr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(false);
                    goodr.setChecked(true);
                    awossomer.setChecked(false);
                }
            });
            awossomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    improver.setChecked(false);
                    goodr.setChecked(false);
                    awossomer.setChecked(true);
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });


            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }

    private void handleSumbition() {
        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setMessage("Summiting");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.POST_FEED_BACK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        screen2.setVisibility(View.GONE);
                        screen3.setVisibility(View.VISIBLE);
                        screens=3;
                        cardviewtext.setText("Go to Home Screen");
                    }
                }catch (Exception e){
                    e.printStackTrace();
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
                hashMap.put("experience",radioOutput);
                hashMap.put("email",email.getText().toString());
                hashMap.put("msg",comments.getText().toString());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
}