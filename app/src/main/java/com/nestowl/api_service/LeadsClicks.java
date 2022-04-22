package com.nestowl.api_service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.aichat;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LeadsClicks {
    Context context;
    String id, user_id, extraId;
    boolean statuss;
    LiveCommnication liveCommnication;


    public LeadsClicks(Context context, String id, String user_id, String extraId) {
        this.context = context;
        this.id = id;
        this.user_id = user_id;
        this.extraId = extraId;
        statuss=false;
        Log.e("Accepet Data", "LeadsClicks: "+id+" "+user_id );
        live();
    }

    private void live() {
        liveCommnication = ViewModelProviders.of((FragmentActivity) context).get(LiveCommnication.class);
    }

    public boolean acceptProperty(){
        ProgressDialog pd  =  new ProgressDialog(context);
        pd.setMessage("Accepting...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_ACCEPT_PRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status=  jsonObject.getString("status");
                    if (status.equals("1")){
                        statuss=true;
                        pd.dismiss();
                        aichat  ai =  new aichat();
                        ai.setText("AP");
                        ai.setValue("true");
                        liveCommnication.setText(ai);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
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
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return statuss;
    }
    public boolean rejectProperty(){
        ProgressDialog pd= new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Rejecting....");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_REJECT_PRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status=  jsonObject.getString("status");
                    if (status.equals("1")){
                        statuss=true;
                        pd.dismiss();
                        aichat  ai =  new aichat();
                        ai.setText("AP");
                        ai.setValue("false");
                        liveCommnication.setText(ai);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
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
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return statuss;
    }
    public boolean acceptREQ(){
        ProgressDialog pd= new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Accepting...");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_ACCEPT_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status=  jsonObject.getString("status");
                    if (status.equals("1")){
                        statuss=true;
                        pd.dismiss();
                        aichat  ai =  new aichat();
                        ai.setText("AR");
                        ai.setValue("true");
                        liveCommnication.setText(ai);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
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
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return statuss;
    }
    public boolean rejectREQ(){
        ProgressDialog pd= new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Rejecting....");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_REJECT_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status=  jsonObject.getString("status");
                    if (status.equals("1")){
                        statuss=true;
                        pd.dismiss();
                        aichat  ai =  new aichat();
                        ai.setText("AR");
                        ai.setValue("false");
                        liveCommnication.setText(ai);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
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
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
        return statuss;
    }
}
