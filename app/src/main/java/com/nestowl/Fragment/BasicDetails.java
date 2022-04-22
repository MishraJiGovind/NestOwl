package com.nestowl.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.EnterYourCityNameSearch;
import com.nestowl.brokerapp.LocalitySearchClass;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.ProjectSocietySearchClass;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;
import com.nestowl.viewmodal.LiveCommnication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicDetails extends Fragment {
    EditText pin_code,address_optional;
    FrameLayout frm_residential, frm_commercial, frm_other;
    LinearLayout l1, l2, l3;
    TextView tv_sell, edt_project_sct, enter_city_name, edt_locality;

    Boolean isUpdating,isEdit;
    TextView one, two, three, four, five, six, saven, eight;
    FrameLayout tv_flat_apartment, tv_house, tv_studio_apartment, tv_villa, tv_builder_flor_apartment, tv_plot_residential, tv_penthouse, tv_duplex;
    FrameLayout one_office_space, two_shop, three_office_it_park, four_showroom, five_factory, six_warehouse, saven_plot_com, eight_industrial_buid,
            nine_industrial_shed;
    PlanBasicActivity planBasicActivity;
    CardView card_basic;
    FrameLayout frm_agriculture_land, frm_commercial_land, frm_farm_house_land, frm_hotel_resort, frm_banquet_guest_house;
    String i_am_looking = "";
    String property ,propert_type,user_id,Property_Id;
    public BasicDetails() {
        // Required empty public constructor
    }
    Context context;
    Activity activity;
    LiveCommnication liveCommnication;
    LoginPojo loginPojo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_details, container, false);
        frm_agriculture_land = view.findViewById(R.id.agriculture_land);
        frm_commercial_land = view.findViewById(R.id.commercial_land_property);
        address_optional = view.findViewById(R.id.address_optional);
        tv_sell = view.findViewById(R.id.sell_id);
        isUpdating=false;
        if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE)!=null){
            Property_Id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE);
            user_id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER);
            if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)==null){
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER,null);
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE,null);
            }
            isUpdating=true;
            updateRequest();
        }
        Log.e("isupdating", "onCreateView: "+isUpdating );
        loginPojo =  PrefMananger.GetLoginData(getContext());
        isEdit=false;
        if (PrefMananger.getString(getContext(),PrefMananger.RESUMEPROPERTY_ID)!=null){
            isEdit=true;
            Property_Id = PrefMananger.getString(getContext(),PrefMananger.RESUMEPROPERTY_ID);
            PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);
            user_id = String.valueOf(loginPojo.getUserId());
            updateRequest();
            PrefMananger.saveString(getContext(),PrefMananger.RESUMEPROPERTY_ID,null);
        }

        frm_farm_house_land = view.findViewById(R.id.farm_house_property);
        frm_hotel_resort = view.findViewById(R.id.hotet_resort_property);
        frm_banquet_guest_house = view.findViewById(R.id.banquet_guest_house);
        edt_locality = view.findViewById(R.id.edt_locality_post);


        card_basic = view.findViewById(R.id.basic_details_card);
        planBasicActivity = (PlanBasicActivity) getActivity();
        context = getContext();
        one_office_space = view.findViewById(R.id.com_office_space);
        two_shop = view.findViewById(R.id.com_shop);
        three_office_it_park = view.findViewById(R.id.com_office_it_park);
        four_showroom = view.findViewById(R.id.com_showromm);
        five_factory = view.findViewById(R.id.com_factory);
        six_warehouse = view.findViewById(R.id.com_warehouse_godown);
        saven_plot_com = view.findViewById(R.id.com_plot_commercial);
        eight_industrial_buid = view.findViewById(R.id.com_industrial_building);
        nine_industrial_shed = view.findViewById(R.id.com_industrial_shed);
        one = view.findViewById(R.id.tv_one);
        two = view.findViewById(R.id.text_house);
        three = view.findViewById(R.id.text_studio);
        four = view.findViewById(R.id.text_villa);
        five = view.findViewById(R.id.text_builder);
        six = view.findViewById(R.id.text_plot);
        saven = view.findViewById(R.id.text_penthouse);
        eight = view.findViewById(R.id.text_duplex);
        edt_project_sct = view.findViewById(R.id.edt_enter_project_society);
        enter_city_name = view.findViewById(R.id.enter_your_city_name);

        /*locality=view.findViewById(R.id.edt_locality);*/
        pin_code = view.findViewById(R.id.edt_pin_code);
        tv_flat_apartment = view.findViewById(R.id.flat_apartment_property_type);
        tv_house = view.findViewById(R.id.house_property_type);
        tv_studio_apartment = view.findViewById(R.id.studio_apartment_property_type);
        tv_villa = view.findViewById(R.id.villa_property_type);
        tv_builder_flor_apartment = view.findViewById(R.id.builder_floor_apartment);
        tv_plot_residential = view.findViewById(R.id.plot_residential);
        tv_penthouse = view.findViewById(R.id.penthouse_property);
        tv_duplex = view.findViewById(R.id.duplex_property_type);
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        liveCommnication.getText().observe(getActivity(), new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key =  aichat.getText();
                String value = aichat.getValue();

            }
        });

        card_basic.setOnClickListener(new View.OnClickListener() {
            String error = "This Field is required";
            @Override
            public void onClick(View v) {
                if (enter_city_name.getText()==null|enter_city_name.getText().length()<=0|enter_city_name.getText().equals("Enter city name")){
                    enter_city_name.setError(error);
                    return;
                }
                if (edt_project_sct.getText()==null|edt_project_sct.getText().length()<=0|edt_project_sct.getText().equals("Name of Project/Socity")){
                    edt_project_sct.setError(error);
                    return;
                }
                if (edt_locality.getText()==null|edt_locality.getText().length()<=0|edt_locality.getText().equals("Locality")){
                    edt_locality.setError(error);
                    return;
                }
                if (pin_code.getText()==null|pin_code.getText().length()<=0){
                    pin_code.setError(error);
                    return;
                }
               if (isUpdating){
                   basicDetails();
               }else {
                   aichat aichat = new aichat();
                   aichat.setText("remainSet");
                   aichat.setValue("");
                   aichat.setValues("");
                   liveCommnication.setText(aichat);

                   aichat n = new aichat();
                   n.setText("TAB");
                   n.setValue("0001");
                   n.setValues("one");
                   liveCommnication.setText(n);
                   basicDetails();
               }
            }
        });

        frm_residential = view.findViewById(R.id.frm_residential_basic);
        frm_commercial = view.findViewById(R.id.frm_commercial_basic);
        frm_other = view.findViewById(R.id.frm_other_basic);
         l1 = view.findViewById(R.id.lnr_residential_property_type);

         l2 = view.findViewById(R.id.lnr_commercial_property_type);
         l3 = view.findViewById(R.id.lnr_other_property_type);
        i_am_looking = "Sell";
        generatePropertyId();

        edt_locality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    showLocalitySuggestion(s.toString());
                } else {
                    pin_code.setText("");
                }

            }
        });
        locationSuggestin();
        frm_agriculture_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three = frm_agriculture_land;

                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Agriculture Land");
                property = "Agriculture Land";
                other_method();
            }
        });
        frm_commercial_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three = frm_commercial_land;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Commercial Land");
                property = "Commercial Land";

                other_method();
            }
        });
        frm_farm_house_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three = frm_farm_house_land;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Farm House");
                property = "Farm House";
                other_method();
            }
        });
        frm_hotel_resort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three = frm_hotel_resort;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Hotel/Resort");
                property = "Hotel/Resort";
                other_method();
            }
        });
        frm_banquet_guest_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three = frm_banquet_guest_house;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Banquet/Guest House");
                property = "Banquet/Guest House";
                other_method();
            }
        });
        tv_flat_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_flat_apartment;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Flat/Apartment");
                property = "Flat/Apartment";

                update();
            }
        });
        tv_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_house;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "House");
                property = "House";
                update();
            }
        });
        tv_studio_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_studio_apartment;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Studio Apartment");
                property = "Studio Apartment";
                update();
            }
        });
        tv_villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_villa;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Villa");
                property = "Villa";
                update();
            }
        });
        tv_builder_flor_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_builder_flor_apartment;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Builder Floor Apartment");
                property = "Builder Floor Apartment";
                update();
            }
        });
        tv_plot_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_plot_residential;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Plot Residential");
                property = "Plot Residential";
                update();
            }
        });
        tv_penthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_penthouse;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Penthouse");
                property = "Penthouse";
                update();
            }
        });
        tv_duplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected = tv_duplex;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Duplex");
                property = "Duplex";
                update();
            }
        });
        one_office_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = one_office_space;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Office Space");
                property = "Office Space";
                updateSecond();

            }
        });
        two_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = two_shop;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Shop");
                property = "Shop";
                updateSecond();
            }
        });
        three_office_it_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = three_office_it_park;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Office IT Park/Shez");
                property = "Office IT Park/Shez";
                updateSecond();
            }
        });

        four_showroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = four_showroom;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Showroom");
                property = "Showroom";
                updateSecond();
            }
        });

        five_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = five_factory;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Factory");
                property = "Factory";
                updateSecond();
            }
        });

        six_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = six_warehouse;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Warehouse/Godown");
                property = "Warehouse/Godown";

                updateSecond();
            }
        });
        saven_plot_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = saven_plot_com;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Plot Commercial");
                property = "Plot Commercial";
                updateSecond();
            }
        });
        eight_industrial_buid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = eight_industrial_buid;
                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Industrial Building");
                property = "Industrial Building";

                updateSecond();
            }
        });
        nine_industrial_shed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two = nine_industrial_shed;

                PrefMananger.saveString(context, PrefMananger.RESIDENTIAL_KEY, "Industrial Shed");
                property = "Industrial Shed";

                nine_industrial_shed.setBackgroundResource(R.drawable.selected_background_filter);
                updateSecond();
            }
        });
        frm_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                frm_residential.setBackgroundResource(R.drawable.selected_background_filter);
                frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                propert_type = "Residential";
                l1.setVisibility(View.VISIBLE);
            }
        });
        frm_commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                frm_commercial.setBackgroundResource(R.drawable.selected_background_filter);
                frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                propert_type = "Commercial";
            }
        });
        frm_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
                propert_type = "Other";
                frm_other.setBackgroundResource(R.drawable.selected_background_filter);
                frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
            }
        });


//        pin_code.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivityForResult(new Intent(getContext(), PinCodeClass.class), 1);
//
//            }
//        });
        edt_project_sct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ProjectSocietySearchClass.class);

                startActivityForResult(intent,2);

            }
        });
        edt_locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), LocalitySearchClass.class), 4);

            }
        });
        enter_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EnterYourCityNameSearch.class);
                startActivityForResult(intent,3);


            }
        });


        return view;
    }

    private void updateRequest() {
        StringRequest request=new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BASIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int active  =  R.drawable.selected_background_filter;
                try {
                    Log.e("updating", "onResponse: "+response );
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status  =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        Log.e("propoertyInfo", "onResponse: "+jsonObject1.getString("propertytype") );
                        Log.e("propoertyInfo", "onResponse: "+jsonObject1.getString("property") );
                        if (jsonObject1.getString("propertytype").equals("Residential")){
                            frm_residential.performClick();
                            if (jsonObject1.getString("property").equals("Studio Apartment")){
                                tv_studio_apartment.performClick();
                            }
                            if (jsonObject1.getString("property").equals("House")){
                                tv_house.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Flat/Apartment")){
                                tv_flat_apartment.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Villa")){
                                tv_villa.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Builder Floor Apartment")){
                                tv_builder_flor_apartment.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Plot Residential")){
                                tv_plot_residential.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Penthouse")){
                                tv_penthouse.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Duplex")){
                                tv_duplex.performClick();
                            }
                        }
                        if (jsonObject1.getString("propertytype").equals("Commercial")){
                            frm_commercial.performClick();
                            if (jsonObject1.getString("property").equals("Office Space")){
                                one_office_space.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Shop")){
                                two_shop.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Office IT Park/Shez")){
                                three_office_it_park.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Showroom")){
                                four_showroom.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Factory")){
                                five_factory.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Warehouse/Godown")){
                                six_warehouse.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Plot Commercial")){
                                saven_plot_com.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Industrial Building")){
                                eight_industrial_buid.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Industrial Shed")){
                                nine_industrial_shed.performClick();
                            }
                        }
                        if (jsonObject1.getString("propertytype").equals("Other")){
                            frm_other.performClick();
                            if (jsonObject1.getString("property").equals("Agriculture Land")){
                                frm_agriculture_land.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Commercial Land")){
                                frm_commercial_land.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Farm House")){
                                frm_farm_house_land.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Hotel/Resort")){
                                frm_hotel_resort.performClick();
                            }
                            if (jsonObject1.getString("property").equals("Banquet/Guest House")){
                                frm_banquet_guest_house.performClick();
                            }
                        }
                        enter_city_name.setText(jsonObject1.getString("city"));
                        edt_project_sct.setText(jsonObject1.getString("project_name"));
                        edt_locality.setText(jsonObject1.getString("locality"));
                        pin_code.setText(jsonObject1.getString("sublocality"));
                        address_optional.setText(jsonObject1.getString("address"));
                        TextView textView = (TextView) card_basic.getChildAt(0);
                        textView.setText("Update");
                        EditPropertyModal edit=new EditPropertyModal();
                        edit.setId(Property_Id);
                        edit.setPropertyCategory(jsonObject1.getString("property"));
                        String s = new Gson().toJson(edit);
                        PrefMananger.saveString(getContext(),PrefMananger.HANDLE_FEATURES_FARM,s);
                        PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);


                    }
                }catch (Exception e){
                    Log.e("updating", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("updating", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

    public static final int SEARCH_FOR_SOCIETY=100;
    public static final int SEARCH_FOR_CITY=101;
    String socityName="";
    String CITYName="";
    String pid;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==2){
                socityName= data.getStringExtra("dataSocity");
                edt_project_sct.setText(socityName);
            }
            if (requestCode==1){

            }
            if (requestCode==3){
                CITYName= data.getStringExtra("dataCity");
                enter_city_name.setText(CITYName);
            }
            if (requestCode==4){
                edt_locality.setText(data.getStringExtra("Locality"));
            }
        }
    }
    FrameLayout tv_selected;
    private void update() {
        tv_flat_apartment.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_house.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_studio_apartment.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_villa.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_builder_flor_apartment.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_plot_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_penthouse.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_duplex.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_selected.setBackgroundResource(R.drawable.selected_background_filter);

        one_office_space.setBackgroundResource(R.drawable.employe_circle_rounded);
        two_shop.setBackgroundResource(R.drawable.employe_circle_rounded);
        three_office_it_park.setBackgroundResource(R.drawable.employe_circle_rounded);
        four_showroom.setBackgroundResource(R.drawable.employe_circle_rounded);
        five_factory.setBackgroundResource(R.drawable.employe_circle_rounded);
        six_warehouse.setBackgroundResource(R.drawable.employe_circle_rounded);
        saven_plot_com.setBackgroundResource(R.drawable.employe_circle_rounded);
        eight_industrial_buid.setBackgroundResource(R.drawable.employe_circle_rounded);
        nine_industrial_shed.setBackgroundResource(R.drawable.employe_circle_rounded);

    }
    FrameLayout tv_selected_two;
    public void localityApi() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        pin_code.setText(object.getJSONObject(0).getString("value"));
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("locality", edt_locality.getText().toString());
                return map;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }
    ArrayList<String> localityList = new ArrayList<>();
    public void locationSuggestin() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCATION_SUGGESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        for (int i = 0; i < object.length(); i++) {

                            localityList.add(object.getJSONObject(i).getString("value"));
                        }

                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());

                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(context).add(stringRequest);
    }
    PopupMenu popupMenu;
    Boolean isSelectedMenu=false;
    public void showLocalitySuggestion(String text) {
        try {
            if (popupMenu!=null){
                popupMenu.dismiss();
            }
            if (isSelectedMenu){
                isSelectedMenu=false;
                return;
            }
             popupMenu = new PopupMenu(getActivity(), edt_locality);
            for (int i = 0; i < localityList.size(); i++) {

                if (localityList.get(i).toLowerCase().contains(text.toLowerCase())) {
                    popupMenu.getMenu().add(localityList.get(i));
                }
            }
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    isSelectedMenu=true;
                    edt_locality.setText(item.getTitle());
                    localityApi();
                    return true;
                }
            });

        } catch (Exception e) {

        }
    }
    private void updateSecond() {
        one_office_space.setBackgroundResource(R.drawable.employe_circle_rounded);
        two_shop.setBackgroundResource(R.drawable.employe_circle_rounded);
        three_office_it_park.setBackgroundResource(R.drawable.employe_circle_rounded);
        four_showroom.setBackgroundResource(R.drawable.employe_circle_rounded);
        five_factory.setBackgroundResource(R.drawable.employe_circle_rounded);
        six_warehouse.setBackgroundResource(R.drawable.employe_circle_rounded);
        saven_plot_com.setBackgroundResource(R.drawable.employe_circle_rounded);
        eight_industrial_buid.setBackgroundResource(R.drawable.employe_circle_rounded);
        nine_industrial_shed.setBackgroundResource(R.drawable.employe_circle_rounded);
        tv_selected_two.setBackgroundResource(R.drawable.selected_background_filter);
    }
    FrameLayout tv_selected_three;
    private void other_method() {
        frm_agriculture_land.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_commercial_land.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_farm_house_land.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_hotel_resort.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_hotel_resort.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_banquet_guest_house.setBackgroundResource(R.drawable.employe_circle_rounded);


        tv_selected_three.setBackgroundResource(R.drawable.selected_background_filter);


    }
    private void error() {
        one.setTextColor(getResources().getColor(R.color.brown_color));
        two.setTextColor(getResources().getColor(R.color.brown_color));
        three.setTextColor(getResources().getColor(R.color.brown_color));
        four.setTextColor(getResources().getColor(R.color.brown_color));
        five.setTextColor(getResources().getColor(R.color.brown_color));
        six.setTextColor(getResources().getColor(R.color.brown_color));
        saven.setTextColor(getResources().getColor(R.color.brown_color));
        eight.setTextColor(getResources().getColor(R.color.brown_color));


    }
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    public void generatePropertyId(){
        if (isUpdating){
            return;
        }
        if (isEdit){
            return;
        }
        StringRequest request =  new StringRequest(Request.Method.GET, UrlClass.GENRATE_PROPERTY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("property_id")) {
                        pid=jsonObject.getString("property_id");
                        PrefMananger.saveString(getContext(), PrefMananger.PROPERTY_ID, pid);
                        aichat i =  new aichat();
                        i.setText("setId");
                        i.setValue(pid);
                        liveCommnication.setText(i);

                        Log.e("pid", "onResponse: "+pid );
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error", "onErrorResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("error", "onErrorResponse: "+error );
            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
    }
    private void basicDetails() {
        if (UtilityFunction.isNetworkConnected(context)) {
            UtilityFunction.showLoading(context, "Please wait...");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
            hashMap.put("property_id", PrefMananger.getString(context,PrefMananger.PROPERTY_ID));
            Log.e("propertyIdUpdate", "basicDetails: "+PrefMananger.getString(context,PrefMananger.PROPERTY_ID) );

            hashMap.put("looking", i_am_looking);
            hashMap.put("propertytype", propert_type);
            hashMap.put("property", property);
            hashMap.put("city", enter_city_name.getText().toString());
            hashMap.put("project_name", edt_project_sct.getText().toString());

            hashMap.put("locality", edt_locality.getText().toString());
            hashMap.put("sublocality", pin_code.getText().toString());
            hashMap.put("address", address_optional.getText().toString());
            hashMap.put("status", "1");
            ApiExecutor.getApiService().basicDetailsSubmit(hashMap).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, retrofit2.Response<OtpResponse> response) {
                    UtilityFunction.hideLoading();
                    if (isUpdating){
                        if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)!=null){
                            aichat ai = new aichat();
                            ai.setText("TAB");
                            ai.setValue("0001");
                            ai.setValues("tb");
                            liveCommnication.setText(ai);
                            return;
                        }
                        new WarningDio(getContext(), "Updated Successfully", "OK", "CANCEL", new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    getActivity().finish();
                                }
                            }
                        },false);
                    }else {
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                    }
                                  }

                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    UtilityFunction.hideLoading();
                }
            });
        } else {
            Toast.makeText(context, "Network is not available", Toast.LENGTH_SHORT).show();
        }
    }

}
