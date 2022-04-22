package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.SubscriptionPagerAdapter;
import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.model.SubsricptionModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PropertiesPlan extends AppCompatActivity {
    CardView card_select_package;
    ImageView im_back;
    private ViewPager viewPager;
    ArrayList<SubsricptionModal>data= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_plan);



        llPagerDots = findViewById(R.id.id_circle_indicator);
        viewPager = findViewById(R.id.view_pager_login);
        card_select_package=findViewById(R.id.select_package_card);
        im_back=findViewById(R.id.iv33);
        SubsricptionModal free = new SubsricptionModal();
        free.setTime("FREE");
        free.setPurchases(PrefMananger.getString(this,"remain"));
        free.setExtraLine("Use it wisely, its limited");
        free.setLine("Normal Visibility");
        free.setLine2("60 Days Visibility");
        free.setPrice("0");
        free.setType("Property");
        data.add(free);

        if (PrefMananger.getString(this,"subscription")!=null){
            try {
                String json =  PrefMananger.getString(this,"subscription");
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    SubscriptionApiModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                    if (modal.getPlan_type().equals("ListingPackage")){
                        SubsricptionModal months = new SubsricptionModal();
                        months.setTime(modal.getPlan());
                        Integer integer = Integer.parseInt(modal.getPrice())-184;
                        months.setPurchases(integer+"Purchase");
                        months.setExtraLine(i+" out of "+jsonArray.length()+" buy this");
                        months.setLine(modal.getVisiblity()+" Visibility");
                        months.setLine2(modal.getValidity()+" days visibility");
                        months.setPrice(modal.getPrice());
                        months.setType("Property");
                        months.setId(modal.getId());
                        data.add(months);
                    }
                }
                setUpViewPager();
            }catch (Exception e){

            }
        }
        getdataFromServer();
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }

    private LinearLayout llPagerDots;
    private ImageView[] ivArrayDotsPager;
    private void getdataFromServer() {


        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_SUBSCRIPTIONS_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String st =  jsonObject.getString("status");
                    if (st.equals("1")){
                        JSONArray jsonArray= jsonObject.getJSONArray("SubscriptionPlans");
                        for (int i=0;i<jsonArray.length();i++){
                            SubscriptionApiModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                            if (modal.getPlan_type().equals("ListingPackage")){
                                SubsricptionModal months = new SubsricptionModal();
                                months.setTime(modal.getPlan());
                                Integer integer = Integer.parseInt(modal.getPrice())-184;
                                months.setPurchases(integer+"Purchase");
                                months.setExtraLine(i+" out of "+jsonArray.length()+" buy this");
                                months.setLine(modal.getVisiblity()+" Visibility");
                                months.setLine2(modal.getValidity()+" days visibility");
                                months.setPrice(modal.getPrice());
                                months.setType("Property");
                                months.setId(modal.getId());
                                data.add(months);
                            }

                        }
                        setUpViewPager();
                        String save =  new Gson().toJson(data);
                        PrefMananger.saveString(PropertiesPlan.this,"subscription",save);
                    }
                }catch (Exception e){
                    Log.e("dataGetEror", "onResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("dataGetEror", "onResponse: "+error );

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
    public void setUpViewPager() {
        SubscriptionPagerAdapter adapter =  new SubscriptionPagerAdapter(this,data);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);


        setupPagerIndidcatorDots();

        ivArrayDotsPager[0].setImageResource(R.drawable.page_indicator_selected);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.page_unselected_bg);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.page_indicator_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[data.size()];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.page_unselected_bg);
//ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            llPagerDots.addView(ivArrayDotsPager[i]);
            llPagerDots.bringToFront();
        }
    }
}
