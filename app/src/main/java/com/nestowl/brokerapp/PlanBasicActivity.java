package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.Fragment.AmentiesFeatures;
import com.nestowl.Fragment.BasicDetails;
import com.nestowl.Fragment.PhotosSchedule;
import com.nestowl.Fragment.PricingOther;
import com.nestowl.Fragment.PropertyFeatures;
import com.nestowl.Fragment.VerifySubmit;
import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.model.SubscriptionRemainModal;
import com.nestowl.model.SubsricptionModal;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.apache.http.nio.client.HttpAsyncClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlanBasicActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    ViewPager viewPager_filter;
    public TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location,navtext;
    View basic,feature,pricing,amenties,photo,verify;
    CardView cardView;
    ImageView back_icon;
    int allowedTab,freeCount,stop;
    LiveCommnication liveCommnication;
    String value;
    String key,pid,planId,json;
    ArrayList<SubscriptionRemainModal> remainModals;
    ArrayList<SubsricptionModal>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_basic);
        planId=getIntent().getStringExtra("id");
        stop=0;
        tab_filters = findViewById(R.id.tab_filter_screen);
        freeCount=Integer.parseInt(PrefMananger.getString(this,"remain"));
        remainModals=new ArrayList<>();

        basic =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(0);
        feature =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(1);
        pricing =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(2);
        amenties =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(3);
        photo =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(4);
        verify =((ViewGroup) tab_filters.getChildAt(0)).getChildAt(5);

        basic.setClickable(false);
        feature.setClickable(false);
        pricing.setClickable(false);
        amenties.setClickable(false);
        photo.setClickable(false);
        verify.setClickable(false);
        data= new ArrayList<>();
        getSubscriptions();


        try {
            json = PrefMananger.getString(this,"remains");
            if (json!=null){
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    SubscriptionRemainModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionRemainModal.class);
                   if (stop==0){
                       if (data.getName().equals(planId)){
                            stop=1;
                       }else {
                           remainModals.add(data);
                       }
                   }else {
                       remainModals.add(data);
                   }

                }
            }
        }catch (Exception e){
        }
        back_icon=findViewById(R.id.DEALS_BACK);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        navtext=findViewById(R.id.NAV_TEXT_PLAN_BASIC);
        navtext.setText("Post Property");
        viewPager_filter = findViewById(R.id.filters_view_pager);
        tv_add_location = findViewById(R.id.add_localities);

        allowedTab=0;
        value=null;
        key=null;
        pid=null;

        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichats) {
                aichat a = new aichat();
                a=aichats;
                key = a.getText();
                value = a.getValue();
                if (key==null|value==null){
                    return;
                }
                if (key.equals("TAB")){
                    if( value.equals("0001")){
                        allowedTab=1;
                        tab_filters.selectTab(tab_filters.getTabAt(1));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PropertyFeatures()).commit();
                    }
                    if (value.equals("2")){
                        allowedTab=2;
                        tab_filters.selectTab(tab_filters.getTabAt(2));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PricingOther()).commit();
                    }
                    if (value.equals("3")){
                        allowedTab=3;
                        tab_filters.selectTab(tab_filters.getTabAt(3));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new AmentiesFeatures()).commit();
                    }
                    if (value.equals("4")){
                        allowedTab=4;
                        tab_filters.selectTab(tab_filters.getTabAt(4));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PhotosSchedule()).commit();
                    }
                    if (value.equals("5")){
                        allowedTab=5;
                        tab_filters.selectTab(tab_filters.getTabAt(5));
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new VerifySubmit()).commit();
                    }
                }
                if (key.equals("getId")){
                    if (pid!=null){
                        aichat i =  new aichat();
                        i.setText("response");
                        i.setValue(pid);
                    }
                }
                if (key.equals("setId")){
                    pid = value;
                }
                if (key.equals("remainSet")){
                    if (planId==null){

                    }else {
                        if (planId.equals("0")){
                            PrefMananger.saveString(PlanBasicActivity.this,"remain",String.valueOf(freeCount--));
                            Log.e("listing", "onChanged: free "+String.valueOf(freeCount--) );
                        }else {
                            String js =  new Gson().toJson(remainModals);
                            PrefMananger.saveString(PlanBasicActivity.this,"remains",js);
                        }
                        Log.e("listing", "Response 1 ");
                    }

                }
            }
        });
//        tab_filters.addTab(tab_filters.setText("Basic Details"));
//        tab_filters.addTab(tab_filters.newTab().setText("Property Features"));
//        tab_filters.addTab(tab_filters.newTab().setText("Pricing Other"));
//        tab_filters.addTab(tab_filters.newTab().setText("Amenities Features"));
//        tab_filters.addTab(tab_filters.newTab().setText("Photos Schedule"));
//        tab_filters.addTab(tab_filters.newTab().setText("Verify Submit"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    ;
        tab_filters.setOnTabSelectedListener(this);
        int tabPosition = getIntent().getIntExtra(PrefMananger.MY_DATABASE, 0);
        if (getIntent().hasExtra(PrefMananger.MY_DATABASE)) {

            if (tabPosition==0){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new BasicDetails()).commit();
            }
            if (tabPosition==1){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PropertyFeatures()).commit();
            }
            if (tabPosition==2){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PricingOther()).commit();
            }
            if (tabPosition==3){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new AmentiesFeatures()).commit();
            }
            if (tabPosition==4){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PhotosSchedule()).commit();
            }
            if (tabPosition==5){
                tab_filters.getTabAt(tabPosition).select();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new VerifySubmit()).commit();
            }


        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new BasicDetails()).commit();
        }
        /*if (tabPosition==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new BasicDetails()).commit();
        }else if (tabPosition==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new PropertyFeatures()).commit();
        }
        else if (tabPosition==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PricingOther()).commit();
        }
        else if (tabPosition==3){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new AmentiesFeatures()).commit();
        }
        else if (tabPosition==4){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PhotosSchedule()).commit();
        } else if (tabPosition==5){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new VerifySubmit()).commit();
        }*/
    }

    private void getSubscriptions() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_SUBSCRIPTIONS_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("SubscriptionPlans");
                        for (int i=0;i<jsonArray.length();i++){
                            SubscriptionApiModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                            if (modal.getPlan_type().equals("ListingPackage")){
                                SubsricptionModal months = new SubsricptionModal();
                                months.setTime(modal.getPlan());
                                Integer integer = Integer.parseInt(modal.getPrice())-184;
                                months.setPurchases(integer+"Purchase");
                                months.setExtraLine(i+" out of "+jsonArray.length()+" buy this");
                                months.setLine(modal.getVisiblity());
                                months.setLine2(modal.getValidity());
                                months.setPrice(modal.getPrice());
                                months.setType("Property");
                                months.setId(modal.getId());
                                data.add(months);
                                Log.e("data", "onCreate: plans"+months.getLine2() );
                            }
                        }
                        for (SubsricptionModal modal : data){
                            if (!planId.equals("0")){
                                if (planId.equals(modal.getId())){
                                    getTimeToExpire(modal.getLine2());
                                    Log.e("data", "onCreate: subs"+modal.getLine2() );
                                }
                            }else {
                                Log.e("data", "onCreate: subs free");
                                getTimeToExpire("60");
                            }

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
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void getTimeToExpire(String time) {
        Date date =  new Date();
        Calendar calendar =  Calendar.getInstance();
        int month = Integer.parseInt(time)/30;
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,month);

        String dat=new SimpleDateFormat("MMM dd yy").format(calendar.getTime());
        Log.e("date Error", "getTimeToExpire: "+dat );

        PrefMananger.saveString(this,"expire",dat);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition() == 0) {
            if (allowedTab==tab.getPosition()){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new BasicDetails()).commit();
            }
        }
        if (tab.getPosition() == 1) {
            if (allowedTab==tab.getPosition()){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PropertyFeatures()).commit();
            }
        }
        if (tab.getPosition() == 2) {
            if (allowedTab==tab.getPosition()){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new PricingOther()).commit();
            }
        }
        if (tab.getPosition() == 3) {
            if (allowedTab==tab.getPosition()){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new AmentiesFeatures()).commit();
            }
        }if (tab.getPosition() == 4) {
            if (allowedTab==tab.getPosition()){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new PhotosSchedule()).commit();
            }
        }if (tab.getPosition() == 5) {
            if (allowedTab==tab.getPosition()){
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new VerifySubmit()).commit();
            }
        }



    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void reset(View view) {


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
