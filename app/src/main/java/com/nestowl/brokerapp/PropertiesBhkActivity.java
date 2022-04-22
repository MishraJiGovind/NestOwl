package com.nestowl.brokerapp;

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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.PropertyPreviewImages;
import com.nestowl.model.PropertyPreviewImagesModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PropertiesBhkActivity extends AppCompatActivity  {
     TabLayout tabs;
     ImageView back_img;
     ArrayList<PropertyPreviewImagesModal> data,data2;
     User user;
     Bundle bundle,bundle1,bundle2;
     String propertyId,id,name,dp;
     RecyclerView recyclerView;
     PropertyPreviewImages adapter;
     FrameLayout chat,enqury,makeOffer;
     LinearLayout layout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_bhk);
        propertyId=getIntent().getStringExtra("key");
        id = getIntent().getStringExtra("user_id");
        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);

        back_img=findViewById(R.id.ARTICLES_BACK);
        recyclerView = findViewById(R.id.OFFICE_PHOTOS_RECYCLER);
        chat=findViewById(R.id.CHAT);
        enqury=findViewById(R.id.ENQURY_NOW);
        makeOffer=findViewById(R.id.MAKE_OFFER);
        layout=findViewById(R.id.HideLiNie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data=new ArrayList<>();
        data2=new ArrayList<>();
        bundle=new Bundle();
        bundle1=new Bundle();
        bundle2=new Bundle();
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
        tabs = findViewById(R.id.tabs_properties);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertiesBhkActivity.this,Chating.class);
                intent.putExtra("id",id);
                intent.putExtra("name",user.getFirst_name());
                intent.putExtra("dp",user.getAvatar());
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        if (user.getUser_id().equals(id)){
            layout.setVisibility(View.GONE);
            getPhoto(user.getUser_id());
        }else {
            getUser();
        }

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getId()==R.id.office_tab){
                    data2=new ArrayList<>();
                    for (PropertyPreviewImagesModal images:data){
                        if (images.getType().equals("PPOfficePhotos")){
                            data2.add(images);
                        }
                    }
                }
                if (tab.getId()==R.id.site_view_tab){
                    data2=new ArrayList<>();
                    for (PropertyPreviewImagesModal images:data){
                        if (images.getType().equals("PPSiteView")){
                            data2.add(images);
                        }
                    }
                }
                if (tab.getId()==R.id.floor_plan_tab){
                    Log.e("tab test", "onTabSelected: working floor" );
                    data2=new ArrayList<>();
                    for (PropertyPreviewImagesModal images:data){
                        if (images.getType().equals("PPFloorPlan")){
                            data2.add(images);
                        }
                    }
                }
                setRecycler();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void getUser() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        user =  new Gson().fromJson(jsonObject.getString("data"),User.class);
                        JSONObject jsonObject1  = jsonObject.getJSONObject("Photo");
                        user.setAvatar(jsonObject1.getString("profile_photo"));
                        getPhoto(user.getUser_id());
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
                hashMap.put("user_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void setRecycler() {
        adapter=new PropertyPreviewImages(this,data2);
        recyclerView.setAdapter(adapter);
    }

    private void getPhoto(String userId) {
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    Log.e("previewPhotos", "onResponse: "+response );
                    JSONObject jsonObject = new JSONObject(response);
                    String  status  = jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray officePhotos = jsonObject.getJSONArray("Photo_data");
                        String BASE_URL ="https://munafa.digitalfueled.in";
                        for (int i=0;i<officePhotos.length();i++){
                            JSONObject jsonObject1 = officePhotos.getJSONObject(i);
                            PropertyPreviewImagesModal info =  new PropertyPreviewImagesModal();
                            info.setImageurl(BASE_URL+jsonObject1.getString("filename"));
                            info.setType(jsonObject1.getString("type"));
                            data.add(info);
//                            data2.add(info);
                            Log.e("errors", "onResponse: "+jsonObject1+" "+jsonObject1.getString("filename") );
                        }

                        for (PropertyPreviewImagesModal images:data){
                            if (images.getType().equals("PPSiteView")){
                                data2.add(images);
                            }
                        }
                        setRecycler();
                    }
                }catch (Exception e){
                    Log.e("previewPhotos", "onResponse: "+e );

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                    Log.e("previewPhotos", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("user_id",userId);
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }



}
