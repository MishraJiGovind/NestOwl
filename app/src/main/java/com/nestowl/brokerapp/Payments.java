package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.SubscriptionHistoryAdapter;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.PaymentOrderModal;
import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Payments extends AppCompatActivity {
    SubscriptionHistoryAdapter adapter;
    RecyclerView recyclerView;
    LoginPojo loginPojo;
    String userId;
    ArrayList<PaymentOrderModal> orderModals;
    ArrayList<SubscriptionApiModal>dataMain;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        recyclerView=findViewById(R.id.SUBSCRIPTION_HISTORY_RECYCLER);
        imageView=findViewById(R.id.PAYMENTS_BACK);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        loginPojo= PrefMananger.GetLoginData(this);
        userId=String.valueOf(loginPojo.getUserId());
        orderModals=new ArrayList<>();
        dataMain=new ArrayList<>();
        fetchdata();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void fetchdata() {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_ORDERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("subs", "onResponse: "+response );
                    JSONObject jsonObject =  new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("OrderDetails");
                        for (int i=0;i<jsonArray.length();i++){
                            PaymentOrderModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),PaymentOrderModal.class);
                            if (data.getPayment_status().contains("Paid")){
                                orderModals.add(data);
                            }
                        }
                        handleOrdeders();
                    }
                }catch (Exception e){
                    Log.e("subs", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("subs", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void handleOrdeders() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_SUBSCRIPTIONS_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response", "onResponse: "+response );
                    JSONObject jsonObject = new JSONObject(response);
                    String staus=  jsonObject.getString("status");
                    if (staus.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("SubscriptionPlans");
                        for (PaymentOrderModal d:orderModals){
                            for (int i=0;i<jsonArray.length();i++){
                                SubscriptionApiModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                                if (data.getId().equals(d.getProduct_id())){
                                    Log.e("macthed", "onResponse: matched "+data.getId() );
                                    if (data.getPlan_type().equals("Registration")){
                                        data.setValidity(data.getValidity()+" Months");
                                        dataMain.add(data);
                                    }
                                    if (data.getPlan_type().equals("ListingPackage")){
                                        data.setPrice(data.getPlan());
                                        data.setValidity(data.getValidity()+" Days");
                                        dataMain.add(data);
                                    }
                                    if (data.getPlan_type().equals("LeadBoost")){
                                        data.setPrice(data.getPlan());
                                        data.setValidity(data.getValidity());
                                        dataMain.add(data);
                                    }
                                }
                            }
                        }

                        adapter= new SubscriptionHistoryAdapter(Payments.this,dataMain);
                        recyclerView.setAdapter(adapter);

                    }
                }catch (Exception e){
                    Log.e("response", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("response", "onResponse: "+error );
            }
        });
        Volley.newRequestQueue(Payments.this).add(request);

    }
}