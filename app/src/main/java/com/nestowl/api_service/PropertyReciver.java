package com.nestowl.api_service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.PropertyPhotosModal;
import com.nestowl.model.PropertyPricModal;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PropertyReciver {
    Context context;
    String user_id,property_id;
    PropertyPricModal propertyPricModal;
    PropertyPhotosModal propertyPhotosModal;

    public PropertyReciver(Context context, String user_id, String property_id) {
        this.context = context;
        this.user_id = user_id;
        this.property_id = property_id;
        propertyPricModal =  new PropertyPricModal();
        propertyPhotosModal = new PropertyPhotosModal();
    }
    public synchronized PropertyPricModal getPrice(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PRICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String stataus = jsonObject.getString("status");
                    Log.e("PropertyRecive", "onResponse: "+jsonObject.getString("message") );
                    if (stataus.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        propertyPricModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PropertyPricModal.class);
                    }
                }catch (Exception e){
                    Log.e("PropertyReciveEX", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PropertyReciveER", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",property_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return propertyPricModal;
    }
    public synchronized PropertyPhotosModal getPhotos(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String statas =  jsonObject.getString("status");
                    Log.e("PropertyRecive", "onResponse: "+jsonObject.getString("message") );
                    if (statas.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        propertyPhotosModal =  new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PropertyPhotosModal.class);
                    }
                }catch (Exception e){
                    Log.e("PropertyReciveE", "onResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PropertyReciveERR", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",property_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return propertyPhotosModal;
    }
}
