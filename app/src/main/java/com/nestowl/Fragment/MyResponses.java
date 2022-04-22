package com.nestowl.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.MyPropertyAdapter;
import com.nestowl.AdapterClass.MyPropertyIncompleteAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.BasicDetailsModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.MyPropertyViewModal;
import com.nestowl.brokerapp.EditSignUpForm;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SelectOptionMode;
import com.nestowl.brokerapp.SubscriptionPlan;
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
public class MyResponses extends Fragment {
    FrameLayout filter,lead_status;
    FrameLayout fm2,fm_continue;
    FrameLayout frm_photo_1,frm_photo_2;
    RecyclerView recyclerView,underReview,freeze,incomplete;
    MyPropertyAdapter adapter;
    MyPropertyIncompleteAdapter adapterIn;
    AutoCompleteTextView editText;
    TextView  filterText,postedtext;
    ArrayList<MyPropertyViewModal> list;
    ArrayList<LeadsPublicPro> arrayList,underList,freezeList;
    LoginPojo loginPojo;
    ArrayList<BasicDetailsModal> basic =  new ArrayList<>();
    ArrayList<String> propertyIdis;
    String user_id,filterName,postedNames;
    ArrayAdapter<String>adapterss;
    Autoclicks autoclicks;
    int filters,pstedFilter;
    ConstraintLayout noDataShow;
    CardView postData;
    ArrayList<String>images;
    public MyResponses() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_responses, container, false);
//        if (PrefMananger.getString(getContext(),"responsekey")!=null){
//            searchProperty(PrefMananger.getString(getContext(),"responsekey"));
//            PrefMananger.saveString(getContext(),"responsekey",null);
//        }
        fm2=view.findViewById(R.id.CUSTOM_MY_PRO_MANNAGE);
        frm_photo_1=view.findViewById(R.id.frame_one_image);
        frm_photo_2=view.findViewById(R.id.CUSTOM_MY_PRO_IMG);
        fm_continue=view.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_POST);
        lead_status=view.findViewById(R.id.MY_PROPERTY_POSTED);
        editText=view.findViewById(R.id.MY_PROPERTY_ID_SEARCH);
        recyclerView=view.findViewById(R.id.MY_PROPERTY_RECYCLER);
        underReview=view.findViewById(R.id.MY_PROPERTY_RECYCLER_UNDER_REVIEW);
        freeze=view.findViewById(R.id.MY_PROPERTY_RECYCLER_FREEZE);
        incomplete=view.findViewById(R.id.MY_PROPERTY_RECYCLER_INCOMPLETE);
        noDataShow=view.findViewById(R.id.ALL_NO_DATA_SHOW);
        postData=view.findViewById(R.id.ALL_NO_DATA_SHOW_BTN);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        underReview.setLayoutManager(new LinearLayoutManager(getContext()));
        freeze.setLayoutManager(new LinearLayoutManager(getContext()));
        incomplete.setLayoutManager(new LinearLayoutManager(getContext()));
        loginPojo = PrefMananger.GetLoginData(getContext());
        user_id=String.valueOf(loginPojo.getUserId());
        propertyIdis= new ArrayList<>();
        arrayList=new ArrayList<>();
        underList=new ArrayList<>();
        freezeList=new ArrayList<>();
        basic=new ArrayList<>();
        images=new ArrayList<>();
        filters=0;
        pstedFilter=0;
        fetchPropertyiesList();
        fetcthUnderReiview();
        fetchFreezes();
        fetchIncomplete();
        filter=view.findViewById(R.id.MY_PROPERTY_STATUS);
        filterText = (TextView)filter.getChildAt(0);
        filterName="Property Status";
        postedNames="Posted to";
        filterText.setText(filterName);
        postedtext=(TextView)lead_status.getChildAt(0);
        postData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(getContext(),"sub").equals("true")){
                    Intent intent = new Intent(getContext(), SelectOptionMode.class);
                    startActivity(intent);
                }else {
                    if ( PrefMananger.getString(getContext(),"id").equals("true")){
                        new WarningDio(getContext(), "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(getContext(), SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(getContext(),"status").equals("0")){
                            new WarningDio(getContext(), "Your is incomplete", "Complete Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(getContext(), EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("1")){
                            new WarningDio(getContext(), "Your is summited", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("2")){
                            new WarningDio(getContext(), "Your Profile is pending.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }


                    }
                }
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int def = R.drawable.employe_circle_rounded;
                int act = R.drawable.selected_background_filter;
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.property_status_layout);
                FrameLayout live,freeze,under,incomplete,hidtory,clear,apply;
                live=dialog.findViewById(R.id.POPUP_STATUS_LIVE);
                freeze=dialog.findViewById(R.id.POPUP_STATUS_FREEZE);
                under=dialog.findViewById(R.id.POPUP_STATUS_REVIEW);
                incomplete=dialog.findViewById(R.id.POPUP_STATUS_INCOMPLETE);
                hidtory=dialog.findViewById(R.id.POPUP_STATUS_HISTORY);
                clear=dialog.findViewById(R.id.POPUP_STATUS_CLEAR);
                apply=dialog.findViewById(R.id.POPUP_STATUS_APPLY);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        filterText.setText(filterName);
                    }
                });


                if (filters==1){
                    live.setBackgroundResource(act);

                }
                if (filters==2){

                    freeze.setBackgroundResource(act);
                }
                if (filters==3){

                    under.setBackgroundResource(act);
                }
                if (filters==4){

                    incomplete.setBackgroundResource(act);
                }
                if (filters==5){

                    hidtory.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf1);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filters=0;
                        dialog.dismiss();
                        handlerStatusFiler();
                        filterName="Property Status";
                        filterText.setText(filterName);
                    }
                });
                live.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        live.setBackgroundResource(act);
                        freeze.setBackgroundResource(def);
                        under.setBackgroundResource(def);
                        incomplete.setBackgroundResource(def);
                        hidtory.setBackgroundResource(def);
                        filters=1;
                        filterName="Live";
                    }
                });
                freeze.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        live.setBackgroundResource(def);
                        freeze.setBackgroundResource(act);
                        under.setBackgroundResource(def);
                        incomplete.setBackgroundResource(def);
                        hidtory.setBackgroundResource(def);
                        filters=2;
                        filterName="Freeze";
                    }
                });
                under.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterName="Under Review";
                        live.setBackgroundResource(def);
                        freeze.setBackgroundResource(def);
                        under.setBackgroundResource(act);
                        incomplete.setBackgroundResource(def);
                        hidtory.setBackgroundResource(def);
                        filters=3;
                    }
                });
                incomplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        live.setBackgroundResource(def);
                        freeze.setBackgroundResource(def);
                        under.setBackgroundResource(def);
                        incomplete.setBackgroundResource(act);
                        hidtory.setBackgroundResource(def);
                        filters=4;
                        filterName="Incomplete";
                    }
                });
                hidtory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        live.setBackgroundResource(def);
                        freeze.setBackgroundResource(def);
                        under.setBackgroundResource(def);
                        incomplete.setBackgroundResource(def);
                        hidtory.setBackgroundResource(act);
                        filters=5;
                        filterName="History";
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filters=0;
                        dialog.dismiss();
                        handlerStatusFiler();
                        filterName="Property Status";
                        filterText.setText(filterName);
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handlerStatusFiler();
                        filterText.setText(filterName);
                        dialog.dismiss();
                    }
                });
            }
        });
        adapterss = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,propertyIdis);
        editText.setAdapter(adapterss);
        autoclicks =  new Autoclicks();
        editText.setOnItemSelectedListener(autoclicks);
        editText.setOnItemClickListener(autoclicks);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0){
                    searchProperty(null);
                    Log.e("searchResponse", "searchProperty: "+s.toString() );
                }

            }
        });
        lead_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int def = R.drawable.employe_circle_rounded;
                int act = R.drawable.selected_background_filter;
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.lead_status_seller);
                FrameLayout publics,localityies,city,clear,apply;
                publics=dialog.findViewById(R.id.DIOLOG_POSTED_TO_PUBLIC);
                localityies=dialog.findViewById(R.id.DIOLOG_POSTED_TO_LOCLITY);
                city=dialog.findViewById(R.id.DIOLOG_POSTED_TO_CITY);
                apply=dialog.findViewById(R.id.DIOLOG_POSTED_TO_APPLY);
                clear=dialog.findViewById(R.id.DIOLOG_POSTED_TO_CLEAR);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        postedtext.setText(postedNames);
                    }
                });

                if (pstedFilter==1){
                    publics.setBackgroundResource(act);
                }
                if (pstedFilter==2){
                    localityies.setBackgroundResource(act);
                }
                if (pstedFilter==3){
                    city.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.DIOLOG_POSTED_TO_DELETE);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pstedFilter=0;
                        dialog.dismiss();
                        postedNames="Posted to";
                        handlerStatusPostd(postedNames);
                        postedtext.setText(postedNames);
                    }
                });
                publics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(act);
                        localityies.setBackgroundResource(def);
                        city.setBackgroundResource(def);
                        pstedFilter=1;
                        postedNames="Public";
                    }
                });
                localityies.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(def);
                        localityies.setBackgroundResource(act);
                        city.setBackgroundResource(def);
                        pstedFilter=2;
                        postedNames="Locality";
                    }
                });
                city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postedNames="City";
                        publics.setBackgroundResource(def);
                        localityies.setBackgroundResource(def);
                        city.setBackgroundResource(act);
                        pstedFilter=3;
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pstedFilter=0;
                        dialog.dismiss();
                        postedNames="Posted to";
                        handlerStatusPostd(postedNames);
                        postedtext.setText(postedNames);
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handlerStatusPostd(postedNames);
                        postedtext.setText(postedNames);
                        dialog.dismiss();
                    }
                });
            }
        });
        adapter=new MyPropertyAdapter(getContext(),arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void getPropertyPhotos(LeadsPublicPro data) {
        images=new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("Photo_data");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 =  jsonArray.getJSONObject(i);
                            images.add(UrlClass.BASE_URL2+jsonObject1.getString("filename"));
                            Log.e("images", "onResponse: "+jsonObject1.getString("filename") );
                        }
                        data.setImages(images);
                        arrayList.add(data);
                        showRecyclerView(recyclerView,arrayList);
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
                hashMap.put("property_id",data.getProperty_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void handlerStatusPostd(String data){
        ArrayList<LeadsPublicPro> arrayList2 = new ArrayList<>();
        if (pstedFilter==0){
            recyclerView.setVisibility(View.VISIBLE);
            freeze.setVisibility(View.VISIBLE);
            incomplete.setVisibility(View.VISIBLE);
            underReview.setVisibility(View.VISIBLE);
            showRecyclerView(recyclerView,arrayList);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            freeze.setVisibility(View.GONE);
            incomplete.setVisibility(View.GONE);
            underReview.setVisibility(View.GONE);
            if (pstedFilter==1){
                for (LeadsPublicPro s:arrayList){
                    if (s.getProperypost()!=null){
                        if (s.getProperypost().equals("Public")){
                            arrayList2.add(s);
                        }
                    }
                }
                showRecyclerView(recyclerView,arrayList2);
            }
            if (pstedFilter==2){
                for (LeadsPublicPro s:arrayList){
                    if (s.getProperypost()!=null){
                        if (s.getProperypost().equals("Locality Nest Pro's and Public")){
                            arrayList2.add(s);
                        }
                    }
                }
                showRecyclerView(recyclerView,arrayList2);
            }
            if (pstedFilter==3){
                for (LeadsPublicPro s:arrayList){
                    if (s.getProperypost()!=null){
                        if (s.getProperypost().equals("Whole city and Public")){
                            arrayList2.add(s);
                        }
                    }
                }
                showRecyclerView(recyclerView,arrayList2);
            }
        }

    }
    private void handlerStatusFiler() {
        if (filters==0){
            recyclerView.setVisibility(View.VISIBLE);
            freeze.setVisibility(View.VISIBLE);
            incomplete.setVisibility(View.VISIBLE);
            underReview.setVisibility(View.VISIBLE);
        }
        if (filters==1){
            recyclerView.setVisibility(View.VISIBLE);
            freeze.setVisibility(View.GONE);
            incomplete.setVisibility(View.GONE);
            underReview.setVisibility(View.GONE);
        }
        if (filters==2){
            recyclerView.setVisibility(View.GONE);
            freeze.setVisibility(View.VISIBLE);
            incomplete.setVisibility(View.GONE);
            underReview.setVisibility(View.GONE);
        }
        if (filters==3){
            recyclerView.setVisibility(View.GONE);
            freeze.setVisibility(View.GONE);
            incomplete.setVisibility(View.GONE);
            underReview.setVisibility(View.VISIBLE);
        }
        if (filters==4){
            recyclerView.setVisibility(View.GONE);
            freeze.setVisibility(View.GONE);
            incomplete.setVisibility(View.VISIBLE);
            underReview.setVisibility(View.GONE);
        }
        if (filters==5){
            recyclerView.setVisibility(View.GONE);
            freeze.setVisibility(View.GONE);
            incomplete.setVisibility(View.GONE);
            underReview.setVisibility(View.GONE);
        }

    }
    private void fetchIncomplete() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST_INCOMPLETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String stauts = jsonObject.getString("status");

                    if (stauts.equals("1")){
                        noDataShow.setVisibility(View.GONE);
                        JSONArray jsonArray =  jsonObject.getJSONArray("InComplete");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            BasicDetailsModal data  = new Gson().fromJson(jsonObject1.toString(),BasicDetailsModal.class);
                            propertyIdis.add(data.getProperty_id());
                            basic.add(data);
                        }
                       showIncomplete(basic);

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
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void fetchFreezes() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST_FREEZE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  staus = jsonObject.getString("status");
                    arrayList =  new ArrayList<>();
                    if (staus.equals("1")){
                        noDataShow.setVisibility(View.GONE);
                        JSONArray jsonArray =  jsonObject.getJSONArray("Freeze");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 =  jsonArray.getJSONObject(i);
                            LeadsPublicPro data =  new Gson().fromJson(jsonObject1.toString(),LeadsPublicPro.class);
                            data.setIsUnsumbited("Freeze");
                            propertyIdis.add(data.getProperty_id());
                            freezeList.add(data);
                        }
                        showRecyclerView(freeze,arrayList);
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void fetcthUnderReiview() {
            StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST_UNDER_REVIEW, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String  staus = jsonObject.getString("status");
                        arrayList =  new ArrayList<>();
                        if (staus.equals("1")){
                            noDataShow.setVisibility(View.GONE);
                            JSONArray jsonArray =  jsonObject.getJSONArray("UnderReview");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1 =  jsonArray.getJSONObject(i);
                                LeadsPublicPro data =  new Gson().fromJson(jsonObject1.toString(),LeadsPublicPro.class);
                                data.setIsUnsumbited("Under Review");
                                propertyIdis.add(data.getProperty_id());
                                underList.add(data);
                            }
                            showRecyclerView(underReview,arrayList);
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
                    hashMap.put("user_id",user_id);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(getContext()).add(request);
    }
    private void fetchPropertyiesList() {
        ProgressDialog pd =  new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String  stauts = jsonObject.getString("status");

                    if (stauts.equals("1")){
                        noDataShow.setVisibility(View.GONE);
                        JSONArray js =  jsonObject.getJSONArray("Properties");
                        arrayList =  new ArrayList<>();
                        adapter=new MyPropertyAdapter(getContext(),arrayList);
                        recyclerView.setAdapter(adapter);
                        for (int i=0;i<js.length();i++){
                            JSONObject jsonObject1 =  js.getJSONObject(i);
                            LeadsPublicPro data = new Gson().fromJson(jsonObject1.toString(),LeadsPublicPro.class);
                            data.setIsUnsumbited(null);
                            propertyIdis.add(data.getProperty_id());
                            getPropertyPhotos(data);
                        }





                    }
                }catch (Exception e){
                    Log.e("my property", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("my property", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id", String.valueOf(loginPojo.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void showRecyclerView(RecyclerView list,ArrayList<LeadsPublicPro> data) {
        adapter =  new MyPropertyAdapter(getContext(),data);
        list.setAdapter(adapter);
    }
    private class Autoclicks implements AdapterView.OnItemSelectedListener , AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (editText.getText().length()>0){
                searchProperty(editText.getText().toString());
                Log.e("searchResponse", "searchProperty: "+editText.getText() );
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (editText.getText().length()>0){
                searchProperty(editText.getText().toString());
                Log.e("searchResponse", "searchProperty: "+editText.getText() );
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private void searchProperty(String toString) {
        Log.e("searchResponse", "searchProperty: "+toString );
        if (toString==null){
            showRecyclerView(recyclerView,arrayList);
            showRecyclerView(underReview,underList);
            showRecyclerView(freeze,freezeList);
        }else {
            ArrayList<LeadsPublicPro> arrayList2,underList2,freezeList2;
            arrayList2=new ArrayList<>();
            underList2=new ArrayList<>();
            freezeList2=new ArrayList<>();
            ArrayList<BasicDetailsModal> incompleet  =  new ArrayList<>();
            for (LeadsPublicPro s : arrayList){
                if (s.getProperty_id().contains(toString)){
                    arrayList2.add(s);
                }
            }
            showRecyclerView(recyclerView,arrayList2);
            for (LeadsPublicPro s:underList){
                if (s.getProperty_id().contains(toString)){
                    underList2.add(s);
                }
            }
            showRecyclerView(underReview,underList2);
            for (LeadsPublicPro s:freezeList){
                if (s.getProperty_id().contains(toString)){
                    freezeList2.add(s);
                }
            }
            showRecyclerView(freeze,freezeList2);
            for (BasicDetailsModal s:basic){
                if (s.getProperty_id().contains(toString)){
                    incompleet.add(s);
                }
            }
            showIncomplete(incompleet);
        }
    }
    private void showIncomplete(ArrayList<BasicDetailsModal> incompleet) {
        adapterIn = new MyPropertyIncompleteAdapter(getContext(),incompleet);
        incomplete.setAdapter(adapterIn);
    }
}
