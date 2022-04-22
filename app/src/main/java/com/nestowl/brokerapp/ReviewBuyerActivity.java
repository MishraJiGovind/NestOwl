package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReviewBuyerActivity extends AppCompatActivity {
    TextView name,propertyName,size,price,city;
    ImageView back_img, dp;
    EditText review;
    RatingBar ratingBar;
    User user;
    CheckBox checkBox;
    CardView cardView;
    String PropertyId,ReviewType,pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_buyer);
        back_img=findViewById(R.id.ARTICLES_BACK);
        PropertyId=getIntent().getStringExtra("pid");
        ReviewType=getIntent().getStringExtra("reviewType");

        name=findViewById(R.id.REVIEW_SUMBIT_BUYER_NAME);
        propertyName=findViewById(R.id.REVIEW_SUMBIT_BUYER_P_NAME);
        size=findViewById(R.id.REVIEW_SUMBIT_BUYER_P_SIZE);
        price=findViewById(R.id.REVIEW_SUMBIT_BUYER_P_BUDGET);
        city=findViewById(R.id.REVIEW_SUMBIT_BUYER_P_CITY);
        dp=findViewById(R.id.REVIEW_SUMBIT_BUYER_DP);
        review=findViewById(R.id.REVIEW_SUMBIT_BUYER_INPUT);
        ratingBar=findViewById(R.id.REVIEW_SUMBIT_BUYER_RATING);
        checkBox=findViewById(R.id.REVIEW_SUMBIT_BUYER_CHECK_BOX);
        cardView=findViewById(R.id.REVIEW_SUMBIT_BUYER_SUMBIT);

        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);

        name.setText(user.getFirst_name()+" "+user.getLast_name());
        Glide.with(this).load(user.getAvatar()).placeholder(R.drawable.default_x_x).into(dp);

        getPropertyInfo(PropertyId);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating()<=0){
                    new WarningDio(ReviewBuyerActivity.this, "Please select a rating.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                    return;
                }
                if (review.getText()==null){
                    review.setError("Write some lines.");
                    review.requestFocus();
                    return;
                }
                if (!checkBox.isChecked()){
                    new WarningDio(ReviewBuyerActivity.this, "Please check the box.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                    return;
                }
                sumbitReview();
            }
        });



        back_img.setOnClickListener(new View.OnClickListener() {
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
    }

    private void sumbitReview() {
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Sumbiting Review...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.SUMBIT_REVIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(ReviewBuyerActivity.this, "Review sumbited succesfully.", "Go to home screen", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                startActivity(new Intent(ReviewBuyerActivity.this,HomeScreen.class));
                                finish();
                            }
                        },false);
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                hashMap.put("p_id",pid);
                hashMap.put("type",ReviewType);
                hashMap.put("star_review",String.valueOf(ratingBar.getRating()));
                hashMap.put("about_review",review.getText().toString());
//                hashMap.put("project_photo","null");
                hashMap.put("terms","Yes");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void getPropertyInfo(String propertyId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        LeadsPublicPro data =  new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(),LeadsPublicPro.class);
                        propertyName.setText(data.getProperty());
                        if (data.getArea().equals("")||data.getArea()==null){
                            size.setText("N/A");
                        }else {
                        size.setText(data.getArea()+"BHK");
                        }
                        price.setText(getBudgetInLakhs(data.getExpectedprice()));
                        city.setText(data.getCity());
                        pid=data.getPid();

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
                hashMap.put("property_id",propertyId);
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
}
