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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.AlertProjectAdapter;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProjectDetailsModal;
import com.nestowl.model.ProjectRecivedModal;
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
public class PartnerRequest extends Fragment {
    RadioGroup rg_group;
    RadioButton rad_submit,rad_received,rad_accepted;
    LinearLayout lr_submit,lr_received,lr_accept;
    FrameLayout thirdl;
    Boolean is_Residential_click = false;
    RecyclerView live, recived,pendings;
    AlertProjectAdapter adapter;
    ArrayList<ProjectDetailsModal> liveArrey,recievdArrey,pendingsArrey;
    LoginPojo data;
    ArrayList<String> projectIdStrings,statusList;
    ConstraintLayout noDatashow;
    Context context;

    public PartnerRequest() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_partner_request, container, false);
        context = getContext();
        rg_group=view.findViewById(R.id.rg_two);
        lr_submit=view.findViewById(R.id.HOME_PROJECT_LNR_LIVE);
        lr_accept=view.findViewById(R.id.HOME_PROJECT_LNR_PENDING);
        live=view.findViewById(R.id.ALERT_PROJECT_LIVE_RECYCLER);
        recived=view.findViewById(R.id.ALERT_PROJECT_RECIVED_RECYCLER);
        pendings=view.findViewById(R.id.ALERT_PROJECT_PENDING_RECYCLER);
        noDatashow=view.findViewById(R.id.ALL_NO_DATA_SHOW);

        live.setLayoutManager(new LinearLayoutManager(context));
        recived.setLayoutManager(new LinearLayoutManager(context));
        pendings.setLayoutManager(new LinearLayoutManager(context));
        
        liveArrey=new ArrayList<>();
        recievdArrey=new ArrayList<>();
        pendingsArrey=new ArrayList<>();
        projectIdStrings=new ArrayList<>();
        statusList=new ArrayList<>();

        data = PrefMananger.GetLoginData(context);
        
        getprojectOnBoard();

        lr_received=view.findViewById(R.id.HOME_PROJECT_LNR_RECIVED);
        rad_received=view.findViewById(R.id.HOME_PROJECT_RECIVIED);
        rad_accepted=view.findViewById(R.id.HOME_PROJECT_PENDING);
        rad_submit=view.findViewById(R.id.HOME_PROJECT_LIVE);
        rad_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lr_received.setVisibility(View.GONE);
                lr_submit.setVisibility(View.VISIBLE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lr_submit.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lr_submit.setVisibility(View.VISIBLE);
                }
                if (!liveArrey.isEmpty()){
                    noDatashow.setVisibility(View.GONE);
                }else {
                    noDatashow.setVisibility(View.VISIBLE);
                }
            }
        });
        rad_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lr_submit.setVisibility(View.GONE);
                lr_received.setVisibility(View.VISIBLE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lr_received.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lr_received.setVisibility(View.VISIBLE);
                }
                if (!recievdArrey.isEmpty()){
                    noDatashow.setVisibility(View.GONE);
                }else {
                    noDatashow.setVisibility(View.VISIBLE);
                }
            }
        });
        rad_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lr_received.setVisibility(View.GONE);
                lr_submit.setVisibility(View.GONE);
                lr_accept.setVisibility(View.VISIBLE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lr_accept.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lr_accept.setVisibility(View.VISIBLE);
                }
                if (!pendingsArrey.isEmpty()){
                    noDatashow.setVisibility(View.GONE);
                }else {
                    noDatashow.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    private void getprojectOnBoard() {
        StringRequest requestss =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_LIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        noDatashow.setVisibility(View.GONE);
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectLive");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("LIVE");
                        }
                        handlerProjectArrey();
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",String.valueOf(data.getUserId()));
                return hashMap;
            }
        };

        StringRequest requests =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_RECIVED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectReceived");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("RECEIVED");
                        }
                        Volley.newRequestQueue(context).add(requestss);
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
                HashMap<String,String>hashMap =  new HashMap<>();
                return hashMap;
            }
        };

        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_PENDING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectPending");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("PENDING");
                        }
                        Volley.newRequestQueue(context).add(requests);
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",String.valueOf(data.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);

    }
    private void handlerProjectArrey() {
        liveArrey=new ArrayList<>();
        recievdArrey=new ArrayList<>();
        pendingsArrey=new ArrayList<>();
        for (int i =0;i<statusList.size();i++){
            String id = projectIdStrings.get(i);
            String status = statusList.get(i);
            getProjectInfo(id,status);
            Log.e("Proejects", "handlerProjectArrey: "+id+" "+status);
        }

    }
    private void getProjectInfo(String id,String stats) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject =  new JSONObject(response);
                    String s =  jsonObject.getString("status");
                    if (s.equals("1")){
                        ProjectDetailsModal data =  new Gson().fromJson(jsonObject.getJSONObject("ProjectDetails").toString(),ProjectDetailsModal.class);
                        data.setInappUrl(jsonObject.getString("Url"));
                        data.setInappStatus(stats);
                        if (stats.equals("LIVE")){
                            liveArrey.add(data);
                        }
                        if (stats.equals("RECEIVED")){
                            recievdArrey.add(data);
                        }
                        if (stats.equals("PENDING")){
                            pendingsArrey.add(data);
                        }
                    }
                    setProjectRecyclerViews();
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
                hashMap.put("project_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void setProjectRecyclerViews() {
        adapter =  new AlertProjectAdapter(context,liveArrey);
        live.setAdapter(adapter);
        adapter =  new AlertProjectAdapter(context,recievdArrey);
        recived.setAdapter(adapter);
        adapter =  new AlertProjectAdapter(context,pendingsArrey);
        pendings.setAdapter(adapter);
    }
}