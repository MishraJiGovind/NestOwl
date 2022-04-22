package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.utils.UrlClass;

import java.util.HashMap;
import java.util.Map;

public class BuyPlan extends AppCompatActivity {
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_plan);
        i= 0;
        for (int o=0;o<405;o++){
        runing();
        }

    }

    private void runing() {
        StringRequest stringRequest =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                i++;
                Log.e("Limites Test", "onResponse: runing "+i );
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
                hashMap.put("user_id","50");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}