package com.nestowl.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.AmentiesModal;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.NearbyPlaces;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ShowAllAmenties;
import com.nestowl.brokerapp.ShowallPropertyFeatures;
import com.nestowl.brokerapp.SocietyBuildingUspShowAll;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmentiesFeatures extends Fragment {
    TextView tv_amenties,tv_property_features,society_usp,all_near;
    ImageView img_distance;
    LinearLayout lnr_amenties,amenties_layout;
    TextView tv_swiming,tv_jacuzzi,tv_fire_alarm;
    FrameLayout frt_select;
    LinearLayout lnd_amenties;
    TextView freehold,lasehold,power_of_attorny,co_operative;
    EditText edt_description;
    CardView card_submit;
    String ownershi_status = "";
    String other_features = "";
    PlanBasicActivity planBasicActivity;
    TextView tv_select_approved;
    TextView tv_in_gated,tv_corner,tv_wheelchare;
    TextView tv_distance_from_property;
    TextView fridge,microwave,exhuast_fan;
    EditText metro_railway;
    TextView tv_security_staff,tv_visitor_parking,tv_club_house;
    public static String show_all_property_features="";
    public static String show_all_amenties="";
    public static String show_all_society_building_usp="";

    public static String bus_stop_distance="";
    public static String shopping_mall_distance="";
    public static String office_complex_distance="";
    public static String college_distance="";
    public static String market_distance="";
    public static String playground_distance="";
    public static String gym_distance="";
    public static String police_station_distance="";

    public static String name_of_metro_railway="";
    public static String bus_stop="";
    public static String shopping_mall="";
    public static String office_complex="";
    public static String college="";
    public static String market="";
    public static String playground_park="";
    public static String gym="";
    public static String police_station="",Property_Id,user_id;
    boolean isUpdating;
    String[] AmentiesArrey,PropertyFeaturesArey,UspArrey,OtherFeaturesArrey;
    LiveCommnication liveCommnication;
    ArrayList<String> amentiesArey,showAllArrey,UspArreys,nearbyname,nearbykm;
    public AmentiesFeatures() {
        // Required empty public constructor
    }
    Context context;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_amenties_features, container, false);
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        context=activity=getActivity();
        if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE)!=null){
            Property_Id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE);
            user_id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER);
            if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)==null){
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER,null);
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE,null);
            }
            updateRequest();
            boolean s = PrefMananger.FeatureFarmSet(getContext(),Property_Id);
            if (s){
                getPropertyType();
            }
            isUpdating=true;
        }
        amentiesArey=new ArrayList<>();
        showAllArrey=new ArrayList<>();
        UspArreys=new ArrayList<>();
        nearbyname=new ArrayList<>();
        nearbykm=new ArrayList<>();
        for (int i=0;i<=7;i++){
            nearbyname.add(i,"null");
            nearbykm.add(i,"null");
        }
        show_all_amenties = "";
        show_all_property_features ="";
         show_all_society_building_usp="";


        bus_stop_distance = "";
        shopping_mall_distance = "";
        office_complex_distance = "";
        college_distance = "";
        market_distance = "";
        playground_distance = "";
        gym_distance = "";
        police_station_distance = "";


        bus_stop = "";
        shopping_mall ="";
        office_complex ="";

        college ="";
        market ="";
        playground_park ="";
        gym ="";
        police_station ="";


        tv_security_staff=view.findViewById(R.id.security_staff_id);
        edt_description=view.findViewById(R.id.description_id);



        tv_in_gated=view.findViewById(R.id.in_gated_society);
        tv_corner=view.findViewById(R.id.corner_property_id);
        tv_wheelchare=view.findViewById(R.id.wheelchare_id);

        tv_visitor_parking=view.findViewById(R.id.visitor_parking_id);
        tv_club_house=view.findViewById(R.id.club_house_id);
        tv_amenties=view.findViewById(R.id.show_all_amenties_post_property);
        tv_property_features=view.findViewById(R.id.show_all_property_features_post_property);
        society_usp=view.findViewById(R.id.show_all_society_building_usp_post_property);
        tv_distance_from_property=view.findViewById(R.id.tv_distance_from_property);
        all_near=view.findViewById(R.id.show_all_nearby_places_post_property);
        img_distance=view.findViewById(R.id.distance_from_property);
        amenties_layout=view.findViewById(R.id.id_amenties_main_layout);
        lnr_amenties=view.findViewById(R.id.lnd_amenties);
        card_submit=view.findViewById(R.id.POST_PHOTO_CONTINEUE);
        frt_select=view.findViewById(R.id.select_by_project);
        lnd_amenties=view.findViewById(R.id.luu_amenties);
        amenties_layout.setVisibility(View.VISIBLE);
        metro_railway=view.findViewById(R.id.metro_railway_edt);
        tv_swiming=view.findViewById(R.id.tv_swiming_pool);
        tv_jacuzzi=view.findViewById(R.id.tv_jacuzzi);
        tv_fire_alarm=view.findViewById(R.id.tv_fire_alarm);


        planBasicActivity= (PlanBasicActivity) getActivity();
        freehold=view.findViewById(R.id.freehold_id);
        lasehold=view.findViewById(R.id.lasehold_id);
        power_of_attorny=view.findViewById(R.id.power_of_attorny_id);
        co_operative=view.findViewById(R.id.co_operative_id);
        tv_select_approved=view.findViewById(R.id.tv_select_by_project);
        fridge=view.findViewById(R.id.fridge_id);
        microwave=view.findViewById(R.id.microwave_id);
        exhuast_fan=view.findViewById(R.id.exhuast_fan_id);
        freehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownershi_status = freehold.getText().toString();
                selected(freehold);
                unselected(lasehold);
                unselected(power_of_attorny);
                unselected(co_operative);
            }
        });
        lasehold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownershi_status = lasehold.getText().toString();
                unselected(freehold);
                selected(lasehold);
                unselected(power_of_attorny);
                unselected(co_operative);
            }
        });
        power_of_attorny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownershi_status = power_of_attorny.getText().toString();
                unselected(freehold);
                unselected(lasehold);
                unselected(co_operative);
                selected(power_of_attorny);
            }
        });
        co_operative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownershi_status = co_operative.getText().toString();
                unselected(freehold);
                unselected(lasehold);
                unselected(power_of_attorny);
                selected(co_operative);
            }
        });






        tv_in_gated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_features = tv_in_gated.getText().toString();
                selected(tv_in_gated);
                unselected(tv_corner);
                unselected(tv_wheelchare);
            }
        });
        tv_corner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_features = tv_corner.getText().toString();
                unselected(tv_wheelchare);
                selected(tv_corner);
                unselected(tv_in_gated);
            }
        });
        tv_wheelchare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                other_features = tv_wheelchare.getText().toString();
                unselected(tv_in_gated);
                unselected(tv_corner);
                selected(tv_wheelchare);
            }
        });

        fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amentiesArey.add(fridge.getText().toString());
                selected(fridge);
            }
        });
        microwave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amentiesArey.add(microwave.getText().toString());
                selected(microwave);
            }
        });
        exhuast_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amentiesArey.add(exhuast_fan.getText().toString());
                selected(exhuast_fan);
            }
        });

/*
        exhuast_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_all_amenties.isEmpty()) {
                    show_all_amenties = exhuast_fan.getText().toString();
                    selected(exhuast_fan);
                } else {
                    if (show_all_amenties.contains(exhuast_fan.getText().toString())) {
                        if (show_all_amenties.contains(exhuast_fan.getText().toString() + ",")) {
                            show_all_amenties = show_all_amenties.replace(exhuast_fan.getText().toString() + ",", "");
                        } else if (show_all_amenties.contains("," + exhuast_fan.getText().toString())) {
                            show_all_amenties = show_all_amenties.replace("," + exhuast_fan.getText().toString(), "");
                        } else {
                            show_all_amenties = show_all_amenties.replace(exhuast_fan.getText().toString(), "");
                        }
                        unselected(exhuast_fan);
                    } else {
                        show_all_amenties = show_all_amenties + "," + exhuast_fan.getText().toString();
                        selected(exhuast_fan);
                    }
                }
            }
        });*/

        tv_swiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllArrey.add(tv_swiming.getText().toString());
                selected(tv_swiming);
            }
        });


        tv_jacuzzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllArrey.add(tv_jacuzzi.getText().toString());
                selected(tv_jacuzzi);
            }
        });
        tv_fire_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllArrey.add(tv_fire_alarm.getText().toString());
                selected(tv_fire_alarm);
            }
        });



        tv_security_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UspArreys.add(tv_security_staff.getText().toString());
                selected(tv_security_staff);
            }
        });

        tv_visitor_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UspArreys.add(tv_visitor_parking.getText().toString());
                selected(tv_visitor_parking);
            }
        });

        tv_club_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UspArreys.add(tv_club_house.getText().toString());
                selected(tv_club_house);
            }
        });


        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amentiesFeatures();

            }
        });
        frt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*      floorPojos=new ArrayList<>();*//*
                addItems("Delhi Development Authority");
                addItems("New Delhi Municipal Council");
                addItems("Municipal Corporation of Delhi");
                addItems("Developer");
                addItems("RWA/Co-operative Housing Society");
                addItems("Development Authority");
                addItems("City Municipal Corporation");*//*
                addItems("Hrera");
                addItems("Huda");
                addItems("Hsvp");

                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                },floorPojos);*/
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.approved_by_amenties);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                final RadioButton hrera=dialog.findViewById(R.id.id_hrera);
                final RadioButton huda=dialog.findViewById(R.id.id_huda);
                final RadioButton hsvp=dialog.findViewById(R.id.id_hsvp);
                CardView card_operating = dialog.findViewById(R.id.card_operating_since);
                card_operating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (hrera.isChecked()) {
                            tv_select_approved.setText(hrera.getText().toString());
                        }
                        if (huda.isChecked()) {
                            tv_select_approved.setText(huda.getText().toString());
                        }
                        if (hsvp.isChecked()) {
                            tv_select_approved.setText(hsvp.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
        if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Plot Commercial"))
        {
            amenties_layout.setVisibility(View.VISIBLE);
            lnr_amenties.setVisibility(View.GONE);
            lnd_amenties.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Plot Residential"))
        {
            amenties_layout.setVisibility(View.VISIBLE);
            lnr_amenties.setVisibility(View.GONE);
            lnd_amenties.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Agriculture Land"))
        { amenties_layout.setVisibility(View.VISIBLE);
            lnr_amenties.setVisibility(View.GONE);
            lnd_amenties.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Commercial Land"))
        {
            amenties_layout.setVisibility(View.VISIBLE);
            lnr_amenties.setVisibility(View.GONE);
            lnd_amenties.setVisibility(View.GONE);
        }
        img_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.metro_popup);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            tv_distance_from_property.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            tv_distance_from_property.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            tv_distance_from_property.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            tv_distance_from_property.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            tv_distance_from_property.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        all_near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivityForResult(new Intent(getContext(), NearbyPlaces.class),4);
            }
        });
        society_usp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), SocietyBuildingUspShowAll.class),3);
            }
        });
        tv_property_features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), ShowallPropertyFeatures.class),2);
            }
        });
        tv_amenties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), ShowAllAmenties.class),1);
            }
        });
        return view;
    }
    ArrayList<FloorPojo> floorPojos=new ArrayList<>();
    private void getPropertyType() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BASIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String stats =  jsonObject.getString("status");
                    if (stats.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        JSONObject jsonObject1 =  jsonArray.getJSONObject(0);
                        EditPropertyModal edit=new EditPropertyModal();
                        edit.setId(Property_Id);
                        edit.setPropertyCategory(jsonObject1.getString("property"));
                        String s = new Gson().toJson(edit);
                        PrefMananger.saveString(getContext(),PrefMananger.HANDLE_FEATURES_FARM,s);
                        PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);
                        PrefMananger.FeatureFarmSet(getContext(),Property_Id);
//                        handlerVisiblesForm();
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );
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
                hashMap.put("user_is",user_id);
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void updateRequest() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_AMENTIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("proResy6", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        AmentiesModal amentiesModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),AmentiesModal.class);
                        dataFromServer(amentiesModal);
                        TextView textView = (TextView) card_submit.getChildAt(0);
                        textView.setText("Update");
                    }
                }catch (Exception e){
                    Log.e("errors", "onResponse: "+e );
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_is",user_id);
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }
    private void dataFromServer(AmentiesModal data) {
        if (data.getOwnership_status().equals("Freehold")){
            freehold.performClick();
        }
        if (data.getOwnership_status().equals("Leasehold")){
            lasehold.performClick();
        }
        if (data.getOwnership_status().equals("Power of Attorney")){
            power_of_attorny.performClick();
        }
        if (data.getOwnership_status().equals("Co-Operative Society")){
            co_operative.performClick();
        }
        tv_select_approved.setText(data.getApproved_by());
        AmentiesArrey = data.getAmenities().split(",");
        for (String s:AmentiesArrey){
            if (s.equals("Fridge")){
                fridge.performClick();
            }
            if (s.equals("Microwaves")){
                microwave.performClick();
            }
            if (s.equals("Exhaust Fan")){
                exhuast_fan.performClick();
            }
        }
        PropertyFeaturesArey =  data.getProperty_features().split(",");
        for (String s:PropertyFeaturesArey){
            if (s.equals("Swimming Pool")){
                tv_swiming.performClick();
            }
            if (s.equals("Jacuzzi")){
                tv_jacuzzi.performClick();
            }
            if (s.equals("Fire Alarm")){
                tv_fire_alarm.performClick();
            }
        }
        UspArrey =data.getSb_usp().split(",");
        for (String s:UspArrey){
            if (s.equals("Security Staff")){
                tv_security_staff.performClick();
            }
            if (s.equals("Visitor Parking")){
                tv_visitor_parking.performClick();
            }
            if (s.equals("Club House")){
                tv_club_house.performClick();
            }
        }
        if (data.getOther_features().equals("In a Gated Socity")){
            tv_in_gated.performClick();
        }
        if (data.getOther_features().equals("Corner Property")){
            tv_corner.performClick();
        }
        if (data.getOther_features().equals("Wheelchair Friendly")){
            tv_wheelchare.performClick();
        }
        edt_description.setText(data.getKey_selling_points());

    }
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    private void amentiesFeatures() {
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Saving...");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_PROPERY_AMENTHIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject o =  new JSONObject(response);
                    String s = o.getString("status");
                    if (s.equals("1")){
                        pd.cancel();
                       if (isUpdating){
                           if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)!=null){
                               aichat ai = new aichat();
                               ai.setText("TAB");
                               ai.setValue("4");
                               ai.setValues("tb");
                               liveCommnication.setText(ai);
                               return;
                           }
                           new WarningDio(getContext(), "Updated Successfully", "OK", "CANCEL", new WarningDio.Response() {
                               @Override
                               public void getClicks(int click) {
                                   getActivity().finish();
                               }
                           },false);
                       }else {
                           aichat ai = new aichat();
                           ai.setText("TAB");
                           ai.setValue("4");
                           ai.setValues("tb");
                           liveCommnication.setText(ai);
                       }
                        Toast.makeText(getContext(), "happen ", Toast.LENGTH_SHORT).show();
                    }else {
                        pd.cancel();
                        Toast.makeText(getContext(), "Some error happen retry", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Some error happen retry3", Toast.LENGTH_SHORT).show();
                    pd.cancel();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Some error happen retry2", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                hashMap.put("property_id", PrefMananger.getString(context,PrefMananger.PROPERTY_ID));

                hashMap.put("ownership_status", ownershi_status);
                hashMap.put("approved_by", tv_select_approved.getText().toString());
                hashMap.put("amenities[]", amentiesArey.toString());
                hashMap.put("property_features[]", showAllArrey.toString());
                hashMap.put("sb_usp[]", UspArreys.toString());
                hashMap.put("other_features", other_features);
                hashMap.put("metro_railway_name", metro_railway.getText().toString());
                hashMap.put("metro_railway_dist", tv_distance_from_property.getText().toString());
                hashMap.put("bus_stops_name", nearbyname.get(0));
                hashMap.put("bus_stops_dist", nearbykm.get(0));

                hashMap.put("shopping_malls_name", nearbyname.get(1));
                hashMap.put("shopping_malls_dist", nearbykm.get(1));

                hashMap.put("office_complex_name", nearbyname.get(2));
                hashMap.put("office_complex_dist", nearbykm.get(2));

                hashMap.put("college_univercity_name", nearbyname.get(3));
                hashMap.put("college_univercity_dist", nearbykm.get(3));

                hashMap.put("market_name", nearbyname.get(4));
                hashMap.put("market_dist", nearbykm.get(4));

                hashMap.put("playground_park_name", nearbyname.get(5));
                hashMap.put("playground_park_dist", nearbykm.get(5));

                hashMap.put("gym_name", nearbyname.get(6));
                hashMap.put("gym_dist", nearbykm.get(6));

                hashMap.put("police_station_name", nearbyname.get(7));
                hashMap.put("police_station_dist", nearbykm.get(7));

                hashMap.put("key_selling_points", edt_description.getText().toString());



                hashMap.put("status", "1");
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if(requestCode == 1){
                amentiesArey=new ArrayList<>();
                amentiesArey=data.getStringArrayListExtra("result");
            }
            if (requestCode == 2) {
                showAllArrey=new ArrayList<>();
                showAllArrey=data.getStringArrayListExtra("result");
            }
            if (requestCode == 3) {
                UspArreys=new ArrayList<>();
                UspArreys=data.getStringArrayListExtra("result");
            }
            if (requestCode == 4) {
                nearbyname=new ArrayList<>();
                nearbykm=new ArrayList<>();
                nearbyname=data.getStringArrayListExtra("result");
                nearbykm=data.getStringArrayListExtra("result0");
            }
        }
    }
}
