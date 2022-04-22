package com.nestowl.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.PostReqBasicModal;
import com.nestowl.model.User;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.EnterYourCityNameSearch;
import com.nestowl.brokerapp.LocalitySearchClass;
import com.nestowl.brokerapp.ProjectSocietySearchClass;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicDetailsRqrmnt extends Fragment {
    EditText pin_code;
    FrameLayout frm_residential,frm_commercial,frm_other;
    LinearLayout l1,l2,l3;
    TextView edt_project_sct,enter_city_name,locality;

    Boolean is_Residential_click=false;
    Boolean is_Commercial_click=false;
    Boolean is_other_click=false;
    FrameLayout tv_flat_apartment,tv_house,tv_studio_apartment,tv_villa,tv_builder_flor_apartment,tv_plot_residential,tv_penthouse,tv_duplex;
    FrameLayout one_office_space,two_shop,three_office_it_park,four_showroom,five_factory,six_warehouse,saven_plot_com,eight_industrial_buid,
            nine_industrial_shed;
    FrameLayout frm_agriculture_land,frm_commercial_land,frm_farm_house_land, frm_hotel_resort,frm_banquet_guest_house;
    ScrollView scrollView;

    Context context;
    Activity activity;
    CardView contin;
    LiveCommnication liveCommnication;
    User user;
    String pid;




    public BasicDetailsRqrmnt() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basic_details_rqrmnt, container, false);
        edt_project_sct=view.findViewById(R.id.POST_REQUREMENTS_BASIC_INPUT_PROJECT_SOCITY);
        enter_city_name=view.findViewById(R.id.POST_REQ_OFFICE_FLOR_ADD_IN);
        locality=view.findViewById(R.id.POST_REQUREMENTS_BASIC_INPUT_LOCALITY);
        pin_code=view.findViewById(R.id.POST_REQUREMENTS_BASIC_INPUT_PINCODE);
        context=activity=getActivity();
        scrollView=view.findViewById(R.id.requirement_scroll);
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        getProfile();
        genrateId();
        pid=null;
        contin=view.findViewById(R.id.POST_PHOTO_CONTINEUE);
        user =  new User();
        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostReqBasicModal data =  new PostReqBasicModal();
                if (enter_city_name.getText().equals("Enter City Name")){
                    enter_city_name.setError("This Field is required");
                    return;
                }
                if (edt_project_sct.getText().equals("Enter Project Name")){
                    edt_project_sct.setError("This Field is required");
                    return;
                }
                if (locality.getText().equals("Locality")){
                    locality.setError("This Field is required");
                    return;
                }
                if (pin_code.getText() == null|pin_code.getText().length()<=0){
                    pin_code.setError("This Field is required");
                    return;
                }
                ProgressDialog pd =  new ProgressDialog(getContext());
                pd.setMessage("Saving...");
                pd.setCancelable(false);
                pd.show();
                if (pid!=null){
                    aichat n = new aichat();
                    n.setText("set");
                    n.setValue(pid);
                    n.setValues(String.valueOf(user.getUser_id()));
                    liveCommnication.setText(n);
                    data.setRequirement_id(pid);
                }else {
                    Toast.makeText(getContext(), "Re try", Toast.LENGTH_SHORT).show();
                    return;
                }
                data.setWant_to("Buy");
                data.setSublocality("no data");
                data.setCity(enter_city_name.getText().toString());
                data.setProject_name(edt_project_sct.getText().toString());
                data.setLocality(locality.getText().toString());
                data.setLocality(pin_code.getText().toString());
                data.setAddress(locality.getText().toString());
                if (is_Residential_click){
                    data.setProperty_type("Residential");
                }
                if (is_Commercial_click){
                    data.setProperty_type("Commercial");
                }
                if (is_other_click){
                    data.setProperty_type("Other");
                }
                data.setEmail(user.getEmail());
                data.setName(user.getFirst_name());
                data.setUser_id(String.valueOf(user.getUser_id()));
                data.setMobile(String.valueOf(user.getMobile()));
                data.setProperty(PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY));
                aichat l = new aichat();
                l.setText("cont");
                l.setValue(null);
                StringRequest request = new StringRequest(Request.Method.POST, UrlClass.POST_REQUREMENT_BASIC, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject d = new JSONObject(response);
                            String s =  d.getString("status");
                            if (s.equals("1")){
                                liveCommnication.setText(l);
                                pd.cancel();
                            }else {
                                pd.cancel();
                                Toast.makeText(getContext(), "Some error happen retry", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.cancel();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> datas =  new HashMap<String, String>();
                        Field[] fields = data.getClass().getDeclaredFields();
                        for (Field f:fields){
                            f.setAccessible(true);
                            String value = null;
                            try {
                                value = (String) f.get(data);
                                datas.put(f.getName(), value);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("llop", "getParams: "+datas.toString());
                        return datas;
                    }
                };
                Volley.newRequestQueue(getContext()).add(request);

            }
        });



        one_office_space=view.findViewById(R.id.com_office_space);
        two_shop=view.findViewById(R.id.com_shop);
        three_office_it_park=view.findViewById(R.id.com_office_it_park);
        four_showroom=view.findViewById(R.id.com_showromm);
        five_factory=view.findViewById(R.id.com_factory);
        six_warehouse=view.findViewById(R.id.com_warehouse_godown);
        saven_plot_com=view.findViewById(R.id.com_plot_commercial);
        eight_industrial_buid=view.findViewById(R.id.com_industrial_building);
        nine_industrial_shed=view.findViewById(R.id.com_industrial_shed);

        tv_flat_apartment=view.findViewById(R.id.flat_apartment_property_type);
        tv_house=view.findViewById(R.id.house_property_type);
        tv_studio_apartment=view.findViewById(R.id.studio_apartment_property_type);
        tv_villa=view.findViewById(R.id.villa_property_type);
        tv_builder_flor_apartment=view.findViewById(R.id.builder_floor_apartment);
        tv_plot_residential=view.findViewById(R.id.plot_residential);
        tv_penthouse=view.findViewById(R.id.penthouse_property);
        tv_duplex=view.findViewById(R.id.duplex_property_type);


        frm_agriculture_land=view.findViewById(R.id.agriculture_land);
        frm_commercial_land=view.findViewById(R.id.commercial_land_property);
        frm_farm_house_land=view.findViewById(R.id.farm_house_property);
        frm_hotel_resort=view.findViewById(R.id.hotet_resort_property);
        frm_banquet_guest_house=view.findViewById(R.id.banquet_guest_house);


        final FrameLayout frm_residential=view.findViewById(R.id.POST_REQUREMENTS_BASIC_RESIDENTAL);
        final FrameLayout frm_commercial=view.findViewById(R.id.POST_REQUREMENTS_BASIC_COMERCIAL);
        final FrameLayout frm_other=view.findViewById(R.id.POST_REQUREMENTS_BASIC_OTHER);
        final LinearLayout l1=view.findViewById(R.id.lnr_residential_property_type);

        final LinearLayout l2=view.findViewById(R.id.lnr_commercial_property_type);
        final LinearLayout l3=view.findViewById(R.id.lnr_other_property_type);

        frm_agriculture_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three=frm_agriculture_land;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Agriculture Land");
                other_method();
            }
        });

        frm_commercial_land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_three=frm_commercial_land;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Commercial Land");
                other_method();
            }
        });





        tv_flat_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_flat_apartment;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Flat/Apartment");
                update();
            }
        });


        tv_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_house;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"House");
                update();

            }
        });

        tv_studio_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_studio_apartment;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Studio Apartment");
                update();
            }
        });

        tv_villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_villa;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Villa");
                update();
            }
        });

        tv_builder_flor_apartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_builder_flor_apartment;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Builder Floor Apartment");
                update();
            }
        });
        tv_plot_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_plot_residential;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Plot Residential");
                update();
            }
        });
        tv_penthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_penthouse;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Penthouse");
                update();
            }
        });
        tv_duplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected=tv_duplex;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Duplex");
                update();
            }
        });
        one_office_space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=one_office_space;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Office Space");
                updateSecond();

            }
        });
        two_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=two_shop;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Shop");
                updateSecond();
            }
        });
        three_office_it_park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=three_office_it_park;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Office IT Park/Shez");
                updateSecond();
            }
        });

        four_showroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=four_showroom;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Showroom");
                updateSecond();
            }
        });

        five_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=five_factory;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Factory");
                updateSecond();
            }
        });

        six_warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=six_warehouse;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Warehouse/Godown");
                updateSecond();
            }
        });

        saven_plot_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=saven_plot_com;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Plot Commercial");
                updateSecond();
            }
        });


        eight_industrial_buid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=eight_industrial_buid;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Industrial Building");
                updateSecond();

            }
        });

        nine_industrial_shed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_selected_two=nine_industrial_shed;
                PrefMananger.saveString(context,PrefMananger.RESIDENTIAL_KEY,"Industrial Shed");
                nine_industrial_shed.setBackgroundResource(R.drawable.selected_background_filter);
                updateSecond();

            }
        });
        frm_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                if (l1.getVisibility() == View.VISIBLE){
                    is_Residential_click=false;
                    frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l1.setVisibility(View.GONE);
                } else {

                    is_Residential_click=true;
                    frm_residential.setBackgroundResource(R.drawable.selected_background_filter);
                    frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l1.setVisibility(View.VISIBLE);

                }


            }
        });

        frm_commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);

                if (l2.getVisibility() == View.VISIBLE){
                    is_Commercial_click=false;
                    frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l2.setVisibility(View.GONE);
                } else {

                    is_Commercial_click=true;
                    frm_commercial.setBackgroundResource(R.drawable.selected_background_filter);
                    frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l2.setVisibility(View.VISIBLE);

                }
            }
        });

        frm_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);


                if (l3.getVisibility() == View.VISIBLE){
                    is_other_click=false;
                    frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l3.setVisibility(View.GONE);
                } else {

                    is_other_click=true;
                    frm_other.setBackgroundResource(R.drawable.selected_background_filter);
                    frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                    frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                    l3.setVisibility(View.VISIBLE);

                }


            }
        });

        edt_project_sct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), ProjectSocietySearchClass.class), 1);
            }
        });
        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), LocalitySearchClass.class), 3);
            }
        });
        enter_city_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), EnterYourCityNameSearch.class),2);
            }
        });
        return view;
    }

    private void genrateId() {
        StringRequest request =  new StringRequest(Request.Method.GET, UrlClass.GENRATE_PROPERTY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("property_id")) {
                     pid=jsonObject.getString("property_id");
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getActivity()).add(request);
    }

    private void getProfile() {
        LoginPojo userid = PrefMananger.GetLoginData(getContext());
        Request request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        user = new Gson().fromJson(object.toString(), User.class);

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

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(userid.getUserId()));
                return map;
            }

        };
        Volley.newRequestQueue(context).add(request);
    }

    void getPin(){
        Request request =  new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    int status = jsonObject.getInt("status");
                    if (status==1) {
                        Log.e("api", "onResponse extra: "+response);
                        JSONObject object = jsonObject.getJSONObject("data");
//                        JSONObject data = object.getJSONObject(1);
                        Log.e("api", "onResponse data: "+object);
                        pin_code.setText(object.getString("value"), null);
                    } else {
                        Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "onResponse: "+error);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("locality", locality.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                edt_project_sct.setText(data.getStringExtra("dataSocity"));
            }
        }
        if (requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                enter_city_name.setText(data.getStringExtra("dataCity"));
            }
        }
        if (requestCode == 3){
            if (resultCode == Activity.RESULT_OK){
                locality.setText(data.getStringExtra("Locality"));
                getPin();
            }
        }
    }
    FrameLayout tv_selected;


    private void update(){
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


    private void updateSecond(){

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


    private void other_method(){

        frm_agriculture_land.setBackgroundResource(R.drawable.employe_circle_rounded);
        frm_commercial_land.setBackgroundResource(R.drawable.employe_circle_rounded);

        tv_selected_three.setBackgroundResource(R.drawable.selected_background_filter);






    }
    interface FragmentLishner{
        void actionClicks(Integer i);
    }


}
