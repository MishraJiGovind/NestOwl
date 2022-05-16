package com.nestowl.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.AlertStatusAdapter;
import com.nestowl.model.AcceptRejectModal;
import com.nestowl.model.AlertStatusModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.User;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertsFragmetn extends Fragment {
    RadioButton rad_deal,rad_ongoing;
    LinearLayout lnr_ongoing,lnr_deal;
    RecyclerView ongoning,dealcloesed;
    ArrayList<AlertStatusModal> ongoingArrey,dealclosedArrey;
    AlertStatusAdapter adater;
    LoginPojo loginPojo ;
    String userId;
    ConstraintLayout noDataShow;
    Context context;
    public AlertsFragmetn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alerts_fragmetn, container, false);
        context =getContext();
        rad_ongoing=view.findViewById(R.id.radio_ongoing);
        rad_deal=view.findViewById(R.id.radio_deal_closed);
        lnr_ongoing=view.findViewById(R.id.lnd_ongoing);
        lnr_deal=view.findViewById(R.id.lnd_deal_closed);
        ongoning=view.findViewById(R.id.ALERT_StATUS_ONGOING);
        dealcloesed=view.findViewById(R.id.ALERT_STATUS_CLOSED);
        noDataShow=view.findViewById(R.id.ALL_NO_DATA_SHOW);

        ongoingArrey=new ArrayList<>();
        dealclosedArrey=new ArrayList<>();

        ongoning.setLayoutManager(new LinearLayoutManager(context));
        dealcloesed.setLayoutManager(new LinearLayoutManager(context));

        loginPojo= PrefMananger.GetLoginData(context);
        userId=String.valueOf(loginPojo.getUserId());

        fetchStatus();

        rad_ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr_ongoing.setVisibility(View.VISIBLE);
                lnr_deal.setVisibility(View.GONE);
                if (!ongoingArrey.isEmpty()){
                    noDataShow.setVisibility(View.GONE);
                }else {
                    noDataShow.setVisibility(View.VISIBLE);
                }
            }
        });
        rad_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr_deal.setVisibility(View.VISIBLE);
                lnr_ongoing.setVisibility(View.GONE);
                if (!dealclosedArrey.isEmpty()){
                    noDataShow.setVisibility(View.GONE);
                }else {
                    noDataShow.setVisibility(View.VISIBLE);
                }
            }
        });
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setRecyclers();
            }
        };
        handler.postDelayed(runnable,6000);
        return view;
    }

    private void fetchStatus() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_ALL_ACCEPTED_BY_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AlertStatusRsponse", "onResponse: "+response );
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String sttaus = jsonObject.getString("status");
                    if (sttaus.equals("1")){
                        JSONArray jsonArray= jsonObject.getJSONArray("alldata");
                        PrefMananger.saveString(context,"status",jsonArray.toString());
                        for (int i=0;i<jsonArray.length();i++){
                            AcceptRejectModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),AcceptRejectModal.class);
                            if (data.getType().equals("Requirement")){
                                getMoreInfoReq(data);
                            }else {
                                getMoreInfoPro(data);
                            }
                        }
                        setRecyclers();
                    }
                }catch (Exception e){
                    Log.e("errorStatus", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorStatus", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void setRecyclers() {
        adater =  new AlertStatusAdapter(context,ongoingArrey);
        ongoning.setAdapter(adater);
        adater =  new AlertStatusAdapter(context,dealclosedArrey);
        dealcloesed.setAdapter(adater);
    }
    private void getMoreInfoPro(AcceptRejectModal data) {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AlertStatusRsponse", "onResponse: "+response );
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if ( status.equals("1")){
                        noDataShow.setVisibility(View.GONE);
                        JSONArray jsonArray = jsonObject.getJSONArray("Property");
                        LeadsPublicPro datas =  new Gson().fromJson(jsonArray.getJSONObject(0).toString(),LeadsPublicPro.class);
                        if (userId.equals(data.getUser_id())){
                            AlertStatusModal modal =  new AlertStatusModal();
                            modal.setId(data.getId());
                            modal.setProperty_id(datas.getProperty_id());
                            modal.setBhk(datas.getArea());
                            modal.setName(loginPojo.getFirstName());
                            modal.setUser_id(data.getUser_id());
                            if (data.getDeal()!=null){
                                if (data.getDeal().equals("Yes")){
                                    modal.setOngoingStatus("ONGOING");
                                }else {
                                    modal.setOngoingStatus("DEAL CLOSED");
                                }
                            }else {
                                modal.setOngoingStatus("ONGOING");
                            }
                            modal.setPropertyType(data.getPro_id());
                            modal.setOwnerStatus("OWNER");
                            modal.setViewType("Seller");
                            modal.setJob(null);
                            if (data.getDeal()!=null){
                                if (data.getDeal().equals("No")){
                                    ongoingArrey.add(modal);
                                }else {
                                    dealclosedArrey.add(modal);
                                }
                            }else {
                                    ongoingArrey.add(modal);
                            }

                        }else {
                            getName(data,datas);
                        }
                    }
                }catch ( Exception e){
                    Log.e("errorStatus", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorStatus", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id",data.getPro_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void getName(AcceptRejectModal data, LeadsPublicPro datas) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AlertStatusRsponse", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertStatusModal modal =  new AlertStatusModal();
                        modal.setId(data.getId());
                        modal.setProperty_id(datas.getProperty_id());
                        modal.setBhk(datas.getArea());
                        modal.setName(user.getFirst_name());
                        modal.setUser_id(data.getUser_id());
                        if (data.getDeal()!=null){
                            if (data.getDeal().equals("Yes")){
                                modal.setOngoingStatus("ONGOING");
                            }else {
                                modal.setOngoingStatus("DEAL CLOSED");
                            }
                        }else {
                            modal.setOngoingStatus("ONGOING");
                        }
                        modal.setPropertyType(data.getPro_id());
                        modal.setOwnerStatus("OWNER");
                        modal.setViewType("Seller");
                        modal.setJob(null);
                        if (data.getDeal()!=null){
                            if (data.getDeal().equals("No")){
                                ongoingArrey.add(modal);
                            }else {
                                dealclosedArrey.add(modal);
                            }
                        }else {
                            ongoingArrey.add(modal);
                        }
                    }
                }catch (Exception e){
                    Log.e("errorStatus", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorStatus", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);

    }
    private void getMoreInfoReq(AcceptRejectModal data) {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_REQ_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AlertStatusRsponse", "onResponse: "+response );
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if ( status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Requirement");
                        LeadsPublicPro datas =  new Gson().fromJson(jsonArray.getJSONObject(0).toString(),LeadsPublicPro.class);
                        if (userId.equals(data.getUser_id())){
                            AlertStatusModal modal =  new AlertStatusModal();
                            modal.setId(data.getId());
                            modal.setProperty_id(datas.getProperty_id());
                            modal.setBhk(datas.getArea());
                            modal.setName(loginPojo.getFirstName());
                            modal.setUser_id(data.getUser_id());
                           if (data.getDeal()!=null){
                               if (data.getDeal().equals("Yes")){
                                   modal.setOngoingStatus("ONGOING");
                               }else {
                                   modal.setOngoingStatus("DEAL CLOSED");
                               }
                           }else {
                               modal.setOngoingStatus("ONGOING");
                           }
                            modal.setPropertyType(data.getPro_id());
                            modal.setOwnerStatus("OWNER");
                            modal.setViewType("Buyer");
                            modal.setJob(null);
                            if (data.getDeal()!=null){
                                if (data.getDeal().equals("No")){
                                    ongoingArrey.add(modal);
                                }else {
                                    dealclosedArrey.add(modal);
                                }
                            }else {
                                ongoingArrey.add(modal);
                            }
                        }else {
                            getNameReq(data,datas);
                        }
                    }
                }catch ( Exception e){
                    Log.e("errorStatus", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorStatus", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id",data.getPro_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void getNameReq(AcceptRejectModal data, LeadsPublicPro datas) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("AlertStatusRsponse", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertStatusModal modal =  new AlertStatusModal();
                        modal.setId(data.getId());
                        modal.setProperty_id(datas.getProperty_id());
                        modal.setBhk(datas.getArea());
                        modal.setName(user.getFirst_name());
                        modal.setUser_id(data.getUser_id());
                        if (data.getDeal()!=null){
                            if (data.getDeal().equals("Yes")){
                                modal.setOngoingStatus("ONGOING");
                            }else {
                                modal.setOngoingStatus("DEAL CLOSED");
                            }
                        }else {
                            modal.setOngoingStatus("ONGOING");
                        }
                        modal.setPropertyType(data.getPro_id());
                        modal.setOwnerStatus("OWNER");
                        modal.setViewType("Buyer");
                        modal.setJob(null);
                        if (data.getDeal()!=null){
                            if (data.getDeal().equals("No")){
                                ongoingArrey.add(modal);
                            }else {
                                dealclosedArrey.add(modal);
                            }
                        }else {
                            ongoingArrey.add(modal);
                        }
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
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }


}
