package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProposalModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QueryBrokerFirstScreen extends AppCompatActivity {
    ImageView back_img;
    CardView card_submit,image;
    LeadsRequmentsModal leadsRequmentsModal;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    String clicks,id, userid;
    TextView name,bhks,budget,loaction,username;
    ArrayList<ProposalModal> proposals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_broker_first_screen);
        back_img=findViewById(R.id.ivr3);
        card_submit=findViewById(R.id.BUYER_REQ_PROPSAL_SUMBIT);
        id=getIntent().getStringExtra("id");
        userid=getIntent().getStringExtra("user_id");
        proposals =  new ArrayList<>();
        image=findViewById(R.id.card_image);
        radioButton1=findViewById(R.id.BUYER_REQ_PROPSAL_INVENTORY_1);
        radioButton2=findViewById(R.id.BUYER_REQ_PROPSAL_INVENTORY_2);
        radioButton3=findViewById(R.id.BUYER_REQ_PROPSAL_INVENTORY_3);
        name=findViewById(R.id.BUYER_REQ_PROPSAL_NAME);
        bhks=findViewById(R.id.BUYER_REQ_PROPSAL_REQ);
        budget=findViewById(R.id.BUYER_REQ_PROPSAL_PRICE);
        loaction=findViewById(R.id.BUYER_REQ_PROPSAL_LOCATION);
        username=findViewById(R.id.BUYER_REQ_PROPSAL_NAME_HELLO);
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefMananger.saveString(QueryBrokerFirstScreen.this,PrefMananger.PROPOSAL,null);
                if (radioButton1.isChecked()){
                    clicks = "1";
                    Intent i=new Intent(QueryBrokerFirstScreen.this,QueryBrokerSecondSecondScreen.class);
                    i.putExtra("clicks",clicks);
                    i.putExtra("user_id",userid);
                    i.putExtra("id",id);
                    i.putExtra("v","yes");
                    i.putExtra("f",true);
                    PrefMananger.saveString(QueryBrokerFirstScreen.this,"clisks",clicks);
                    startActivity(i);
                }
                if (radioButton2.isChecked()){
                    clicks = "2";
                    Intent i=new Intent(QueryBrokerFirstScreen.this,QueryBrokerSecondSecondScreen.class);
                    i.putExtra("clicks",clicks);
                    i.putExtra("user_id",userid);
                    i.putExtra("id",id);
                    i.putExtra("v","yes");
                    i.putExtra("f",true);
                    PrefMananger.saveString(QueryBrokerFirstScreen.this,"clisks",clicks);
                    startActivity(i);
                }
                if (radioButton3.isChecked()){
                    clicks = "3";
                    Intent i=new Intent(QueryBrokerFirstScreen.this,QueryBrokerSecondSecondScreen.class);
                    i.putExtra("clicks",clicks);
                    i.putExtra("user_id",userid);
                    i.putExtra("id",id);
                    i.putExtra("v","yes");
                    i.putExtra("f",true);
                    PrefMananger.saveString(QueryBrokerFirstScreen.this,"clisks",clicks);
                    startActivity(i);
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(QueryBrokerFirstScreen.this,QueryBrokerSecondImageSection.class);
                startActivity(i);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        getReqData();
    }

    private void getReqData() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_REQ_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        leadsRequmentsModal =  new Gson().fromJson(jsonObject.getJSONObject("requirement_data").toString(),LeadsRequmentsModal.class);
                        setIntData();
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
                hashMap.put("requirement_id",id);
                return hashMap;
            };
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void setIntData() {
        name.setText(leadsRequmentsModal.getName());
        bhks.setText(leadsRequmentsModal.getArea_max()+"BHK");
        budget.setText(getBudgetInLakhs(leadsRequmentsModal.getBudget_max()));
        loaction.setText(leadsRequmentsModal.getLocality()+","+leadsRequmentsModal.getCity());
        LoginPojo loginPojo = new LoginPojo();
        loginPojo = PrefMananger.GetLoginData(this);
        username.setText("Hello "+loginPojo.getFirstName());
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
