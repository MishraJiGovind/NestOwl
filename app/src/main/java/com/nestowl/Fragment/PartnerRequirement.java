package com.nestowl.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nestowl.AdapterClass.AlertPartnerRecivedAdapter;
import com.nestowl.AdapterClass.AlertPartnerReqSumbitedAdapter;
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
public class PartnerRequirement extends Fragment {
    Boolean is_Residential_click = false;
    RadioButton rad_deal,rad_ongoing;
    LinearLayout lnr_ongoing,lnr_deal;
    RecyclerView recived,sumbited;
    AlertPartnerRecivedAdapter adapterRecived;
    AlertPartnerReqSumbitedAdapter adapterSumbit;
    ArrayList<LeadsPublicPro> recivedArrey,sumbitArrey;
    LoginPojo loginPojo;
    ConstraintLayout noDataShow;
    Context context;

    public PartnerRequirement() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_partner_requirement, container, false);
        context = getContext();

        rad_ongoing=view.findViewById(R.id.radio_ongoing);
        rad_deal=view.findViewById(R.id.radio_deal_closed);
        lnr_ongoing=view.findViewById(R.id.lnd_ongoing);
        lnr_deal=view.findViewById(R.id.lnd_deal_closed);
        recived=view.findViewById(R.id.ALERT_PARTNER_REQ_RECIVED_RECYCLER);
        sumbited=view.findViewById(R.id.ALERT_PARTNER_REQ_SUMBITED);
        noDataShow=view.findViewById(R.id.ALL_NO_DATA_SHOW);

        recivedArrey=new ArrayList<>();
        sumbitArrey=new ArrayList<>();

        recived.setLayoutManager(new LinearLayoutManager(context));
        sumbited.setLayoutManager(new LinearLayoutManager(context));

        loginPojo= PrefMananger.GetLoginData(context);

        fetchBrokerLeads();

        rad_ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr_deal.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnr_ongoing.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lnr_ongoing.setVisibility(View.VISIBLE);
                }
                if (!recivedArrey.isEmpty()){
                    noDataShow.setVisibility(View.GONE);
                }else {
                    noDataShow.setVisibility(View.VISIBLE);
                }
            }
        });
        rad_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnr_ongoing.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnr_deal.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lnr_deal.setVisibility(View.VISIBLE);
                }
                if (!sumbitArrey.isEmpty()){
                    noDataShow.setVisibility(View.GONE);
                }else {
                    noDataShow.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    private void fetchBrokerLeads() {
        StringRequest request  = new StringRequest(Request.Method.POST, UrlClass.LEADS_BROKER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        noDataShow.setVisibility(View.GONE);
                        JSONArray jsonArray = jsonObject.getJSONArray("brokar_data");
                        for (int i=0;i<jsonArray.length();i++){
                            LeadsPublicPro data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsPublicPro.class);
                            if (data.getUser_id().equals(String.valueOf(loginPojo.getUserId()))){
                                data.setName(loginPojo.getFirstName());
                                sumbitArrey.add(data);
                            }else {
                                getname(data);
                            }
                        }
                        setRecyclerSumbitRecyler();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(request);
    }

    private void setRecyclerSumbitRecyler() {
        adapterSumbit=new AlertPartnerReqSumbitedAdapter(context,sumbitArrey);
        sumbited.setAdapter(adapterSumbit);
    }
    private void getname(LeadsPublicPro data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")){
                        JSONObject da = jsonObject.getJSONObject("data");
                        User user = new Gson().fromJson(da.toString(), User.class);
                        Log.e("Name Func", "getName: "+user.getFirst_name() );
                        data.setName(user.getFirst_name());
                        recivedArrey.add(data);
                        setLeadsRecycler();
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
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", data.getUser_id());
                return hashMap;
            }

        };
        Volley.newRequestQueue(context).add(request);
    }

    private void setLeadsRecycler() {
        adapterRecived=new AlertPartnerRecivedAdapter(context,recivedArrey);
        recived.setAdapter(adapterRecived);
    }
}