package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.NestProSearchAdapter;
import com.nestowl.model.NestProBrokerModal;
import com.nestowl.model.NestProViewModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class NestPros extends AppCompatActivity {
    ImageView back_img,crossCity,crossLocality;
    LinearLayout tabs;
    RecyclerView recyclerView;
    NestProSearchAdapter adapter;
    ArrayList<String> intentData,local;
    FrameLayout city,locality,pincode,zToa,aToz;
    TextView NEST_PROS_COUNTS;
    String localiyes,cityS,pincodeS,type,rera;
    ArrayList<NestProBrokerModal> data =  new ArrayList<>();
    ArrayList<NestProViewModal> dataView =  new ArrayList<>();
    boolean isCityselected,isLocalitySelected;
    ConstraintLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_pros);
        back_img=findViewById(R.id.iv5);
        tabs=findViewById(R.id.NEST_PRO_TABS);
        recyclerView=findViewById(R.id.NEST_PRO_RECYCLER);
        city=findViewById(R.id.NEST_PRO_LOCALTIES);
        locality=findViewById(R.id.NEST_PRO_CITY);
        pincode=findViewById(R.id.NEST_PRO_PIN);
        zToa=findViewById(R.id.NEST_PRO_Z_A);
        aToz=findViewById(R.id.NEST_PRO_A_Z);
        layout   =  findViewById(R.id.ALL_NO_DATA_SHOW);
        crossCity=(ImageView)city.getChildAt(1);
        crossLocality=(ImageView)locality.getChildAt(1);
        NEST_PROS_COUNTS=findViewById(R.id.NEST_PROS_COUNTS);
        intentData =  new ArrayList<>();
        intentData = getIntent().getStringArrayListExtra("data");
        local = getIntent().getStringArrayListExtra("local");
        cityS= intentData.get(0);
        localiyes= local.toString();
        pincodeS= intentData.get(2);
        type= intentData.get(3);
        rera=intentData.get(4);
        searchData();
        isCityselected=true;
        isLocalitySelected=true;
        TextView textView = (TextView) city.getChildAt(0);
        TextView textView1 = (TextView) locality.getChildAt(0);
        TextView textView2 = (TextView) pincode.getChildAt(0);
        textView.setText(intentData.get(0));
        textView1.setText(intentData.get(1));
        textView2.setText(intentData.get(2));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCityselected){
                    crossCity.setVisibility(View.GONE);
                    cityS=null;
                    TextView textView3 = (TextView)city.getChildAt(0);
                    textView3.setText("Add City");
                    searchData();
                    isCityselected=false;
                }else {
                    Intent intent=new Intent(NestPros.this, EnterYourCityNameSearch.class);
                    PrefMananger.saveString(NestPros.this,"nest","nets");
                    startActivityForResult(intent,3);
                    isCityselected=true;
                }
            }
        });
        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocalitySelected){
                    crossLocality.setVisibility(View.GONE);
                    localiyes=null;
                    TextView textView3 = (TextView)locality.getChildAt(0);
                    textView3.setText("Add Locality");
                    searchData();
                    isLocalitySelected=false;
                }else {
                    Intent intent =  new Intent(NestPros.this, LocalitySearchClass.class);
                    PrefMananger.saveString(NestPros.this,"nest","nets");
                    startActivityForResult(intent, 4);
                    isLocalitySelected=true;
                }
            }
        });
        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pincode.setVisibility(View.GONE);
                pincodeS=null;
                searchData();
            }
        });
        aToz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Collections.sort(dataView, new Comparator<NestProViewModal>() {
                   @Override
                   public int compare(NestProViewModal o1, NestProViewModal o2) {
                       return o1.getName().compareToIgnoreCase(o2.getName());
                   }
               });
                adapter.notifyDataSetChanged();
                aToz.setBackgroundResource(R.drawable.selected_background_filter);
                zToa.setBackgroundResource(R.drawable.employe_circle_rounded);
            }
        });
        zToa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zToa.setBackgroundResource(R.drawable.selected_background_filter);
                aToz.setBackgroundResource(R.drawable.employe_circle_rounded);
                Collections.sort(dataView, new Comparator<NestProViewModal>() {
                    @Override
                    public int compare(NestProViewModal o1, NestProViewModal o2) {
                        return o2.getName().compareToIgnoreCase(o1.getName());
                    }
                });
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==1){

            }
            if (requestCode==3){
                cityS=data.getStringExtra("dataCity");
                TextView textView3 = (TextView)city.getChildAt(0);
                textView3.setText(cityS);
                crossCity.setVisibility(View.VISIBLE);
                searchData();
            }
            if (requestCode==4){
//                LocalityTxt.setText(data.getStringExtra("Locality"));
                localiyes= data.getStringExtra("Locality");
                getPin(localiyes);
                TextView textView3 = (TextView)locality.getChildAt(0);
                textView3.setText(localiyes);
                crossLocality.setVisibility(View.VISIBLE);
               
            }
        }
    }
    private void getPin(String localiyes) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        pincodeS= object.getJSONObject(0).getString("value");
                        searchData();
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("locality", localiyes);
                return map;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void searchData() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Searching...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.SEARCH_BOKER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    Log.e("response", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        recyclerView.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.GONE);

                        NEST_PROS_COUNTS.setText(Integer.toString(object.length()));
                        if (object.length()==0){
                            recyclerView.setVisibility(View.GONE);
                            layout.setVisibility(View.VISIBLE);
                        }
                        for (int i = 0;i<object.length();i++){
//                            if (i<=50){
                                JSONObject obData =  object.getJSONObject(i);
                                NestProBrokerModal modal = new Gson().fromJson(obData.toString(), NestProBrokerModal.class);
                                data.add(modal);
                                Log.e("LOOP!", "onResponse: i am runing..." );
//                            }
                        }
                        for (NestProBrokerModal d : data){
                            NestProViewModal newmodal =  new NestProViewModal();
                            newmodal.setDp(d.getProfile_photo());
                            newmodal.setAddres(d.getAddress());
                            newmodal.setExtratext(d.getExpertise_in());
                            newmodal.setName(d.getOwner_name());
                            newmodal.setMoto(d.getTransaction_type());
                            newmodal.setUserid(d.getUser_id());
                            dataView.add(newmodal);
                            Log.e("LOOP!!", "onResponse: Im Runing...!");
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(NestPros.this));
                        adapter =  new NestProSearchAdapter(NestPros.this, dataView);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(NestPros.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        recyclerView.setVisibility(View.GONE);
                        layout.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );
                    recyclerView.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorResponse", "onResponse: "+error );
                pd.dismiss();
                recyclerView.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap =  new HashMap<>();
                hashMap.put("city", cityS);
                hashMap.put("locality", localiyes);
                hashMap.put("pincode", pincodeS);
                hashMap.put("property_type", type);
                hashMap.put("rera", rera);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
