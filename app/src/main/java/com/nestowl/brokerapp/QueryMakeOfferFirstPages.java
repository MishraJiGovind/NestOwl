package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LoginPojo;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QueryMakeOfferFirstPages extends AppCompatActivity {
    CardView card_query;
    ImageView back_img,bgImg;
    String price,bed,bath,nameBroker,image,addres,user_id,pro_id,buyers;
    Intent intent;
    LoginPojo loginPojo;
    TextView budget,address,bedTxt,bathTxt,name;
    Context thiss;
    RadioGroup radioGroup;
    LeadsPublicPro data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_make_offer_first_pages);
        intent = getIntent();
        thiss = this;
        price = intent.getStringExtra("budget");
        addres = intent.getStringExtra("location");
        user_id = intent.getStringExtra("user_id");
        pro_id = intent.getStringExtra("id");
        loginPojo = PrefMananger.GetLoginData(this);
        nameBroker= loginPojo.getFirstName();

        budget=findViewById(R.id.SELLER_PROPOSAL_PRICE);
        address=findViewById(R.id.SELLER_PROPOSAL_ADDRES);
        bedTxt=findViewById(R.id.SELLER_PROPOSAL_BED);
        bathTxt=findViewById(R.id.SELLER_PROPOSAL_BATH);
        name=findViewById(R.id.SELLER_PROPOSAL_NAME);
        radioGroup=findViewById(R.id.SELLER_PROPOSAL_BUYER_GROUP);
        bgImg=findViewById(R.id.SELLER_PROPOSAL_IMG);

        FecthPropertyDataById(pro_id);
        card_query=findViewById(R.id.SELLER_PROPOSAL_BUYER_COUNTINUE);
        back_img=findViewById(R.id.ARTICLES_BACK);
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

        card_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryMakeOfferFirstPages.this,QueryPagesSecondMakeOffer.class);
                intent.putExtra("buyer",buyers);
                startActivity(intent);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_BUYER_1){
                    buyers="1";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_BUYER_2){
                    buyers="2";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_BUYER_3){
                    buyers="3";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_BUYER_MORE){
                    buyers="More then 3";
                }
            }
        });
    }

    private void setdata() {
        name.setText("Hello "+nameBroker);
        budget.setText(getBudgetInLakhs(data.getExpectedprice()));
        address.setText(data.getLocality()+","+data.getCity());
        bathTxt.setText(data.getBedrooms());
        bathTxt.setText(data.getBathrooms());
        Glide.with(this).load(UrlClass.BASE_URL+data.getSite_view()).placeholder(R.drawable.default_x_y).into(bgImg);
    }
    private void FecthPropertyDataById(String id) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PROPERTY_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("apiFetch", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String statuss = jsonObject.getString("status");
                    if (statuss.equals("1") && jsonObject.has("publicpros_data")) {
                        data = new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(), LeadsPublicPro.class);
                        setdata();
                    } else {
                        Log.e("get PropertyDeta Error", "onResponse: "+jsonObject.getString("message") );
                    }
                }catch (Exception e){
                    Log.e("Property Catch error", "onResponse: "+e );
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
                hashMap.put("property_id",id);
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
