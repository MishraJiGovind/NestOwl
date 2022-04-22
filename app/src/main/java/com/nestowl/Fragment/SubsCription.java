package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class SubsCription extends Fragment {
    SubscriptionHistoryAdapter adapter;
    RecyclerView recyclerView;
    LoginPojo loginPojo;
    String userId;
    ArrayList<PaymentOrderModal> orderModals;
    ArrayList<SubscriptionApiModal>dataMain;
    public SubsCription() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_subs_cription, container, false);
        recyclerView=view.findViewById(R.id.SUBSCRIPTION_HISTORY_RECYCLER);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true));
        loginPojo= PrefMananger.GetLoginData(getContext());
        userId=String.valueOf(loginPojo.getUserId());
        orderModals=new ArrayList<>();
        dataMain=new ArrayList<>();
        fetchdata();



        return view;
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
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

                        adapter= new SubscriptionHistoryAdapter(getContext(),dataMain);
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
        Volley.newRequestQueue(getContext()).add(request);

    }

}
