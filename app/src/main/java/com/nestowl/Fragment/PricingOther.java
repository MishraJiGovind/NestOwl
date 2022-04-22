package com.nestowl.Fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.AddDiolog;
import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.brokerapp.FloorDetails;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.PostPricingModal;
import com.nestowl.model.PropertyPricModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.Converter;
import com.nestowl.utils.NumberToWords;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PricingOther extends Fragment {
    Calendar calendar;
    LiveCommnication liveCommnication;
    ArrayList<FloorPojo> LandZone,SpecialLand,LEED;

    FrameLayout poojaRoom,studyRoom,storeRoom,sarvantRoom,directionNorth,directionSouth,directionEast,directionWest,directionNE,directionNW,directionSE,
    overLookingGarden,overLookingPol,overLookingMainRood,overLookingNotAvailable,parkingCovered,parkingOpen,liftYes,liftNo,water24,waterAdd,
    waterSMunicipal,waterSBourwell,waterSADD,electric24,electicAdd,powerNone,powerFull,powerPartial,floorMarble,floorConcrete,floorSeeAll,
    popupMaintance,popupFacing,poupland,popupSpecial,popupLed;

    EditText edt_business_since,ideal_for_business_id,expectedIn,pricePerSqIn,maintenceIn,bookingAmmIn,expectedRentalIn,
    membershipIn,faciingIn;
    LinearLayout RESI,COMMRCIAL,PLOTCOMERCIAL,OTHER,
            COM_OFFICE_PRIVIOUS,COM_IDEAL,COM_ADDTIONAL,COM_LEED,
            OTH_IDEAL,OTH_AD,OTH_PARK_LIFT,OTH_FLOOR;

    LinearLayout assuredReturns,leesed,plc,carparking,clubmembership,stumpduty,air,oxygen,usp,Negotable,setLandZoneL,setSpecialZoneL;
    FrameLayout fireExi,fireSensor,sprinkels,fireHouse,stair1,stair2,stair3,stair4,certifiedyes,certifiedno,nocYes,nocNo,
    backendOffice,caOffice,frontedOffice,traderOffice,addOffice,setLandZone,setSpecialZone;
    RadioGroup parkingGruopRadio;
    RadioButton PPB,PPO,PP;
    CheckBox NegotableRadio,PLC_R,CARP_R,CLUB_R,STUMP_R;
    String Idrection,Ioverlooking,Iparking,Ileft,Iwater,ISwater,Ielectric,Ipower,Ifloor,IAssured,Ileased,Iair,IUsp,Ioxygen,DATE,
    Ifire,Istairs,Icertified,Inoc,Ioffice,IpopupMaintainace,IpopupFace,Ipopupland,IpopupSpecial,Ipopupled,IcoverdParking2,Icoveredparking4,IopenParking2,IopenParking4,Iideal;
    int defaults = R.drawable.employe_circle_rounded;
    int active = R.drawable.selected_background_filter;
    ArrayList<String> OverLockingData,Rooms,flors;
    TextView priceInEnglish;
    NumberToWords numberToWords;

    public PricingOther() {

        // Required empty public constructor
    }
    Context context;
    Activity activity;
    CardView card_submit;
    PlanBasicActivity planBasicActivity;
    String user_id,Property_Id;
    boolean isUpdating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pricing_other, container, false);
        context = activity = getActivity();
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        calendar = Calendar.getInstance();
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
        Ileft="a";
        RESI=view.findViewById(R.id.POST_PRCE_RESI);
        COMMRCIAL=view.findViewById(R.id.POST_PRICE_COM);
        PLOTCOMERCIAL=view.findViewById(R.id.POST_PRICE_COM_PLOT);
        OTHER=view.findViewById(R.id.POST_PRICE_OTH);

        COM_IDEAL=view.findViewById(R.id.POST_PRICE_COM_IDEAL_L);
        COM_ADDTIONAL=view.findViewById(R.id.POST_PRICE_COM_ADDTINAL_ROOMS_L);
        COM_OFFICE_PRIVIOUS=view.findViewById(R.id.POST_PRICE_COM_OFFICE_PREIVIOUS_L);
        COM_LEED=view.findViewById(R.id.POST_PRICE_COM_LEED_L);

        OTH_IDEAL=view.findViewById(R.id.POST_PRICE_OTH_IDEAL_L);
        OTH_AD=view.findViewById(R.id.POST_PRICE_OTH_ADDTINAL_L);
        OTH_PARK_LIFT=view.findViewById(R.id.POST_PRICE_OTH_LIFTS_L);
        OTH_FLOOR=view.findViewById(R.id.POST_PRICE_OTH_FLOOR_L);

        OverLockingData =  new ArrayList<>();
        Rooms =  new ArrayList<>();
        handleViews();
        LandZone =  new ArrayList<>();
        SpecialLand =  new ArrayList<>();
        flors =  new ArrayList<>();
        LEED = new ArrayList<>();

        if (RESI.getVisibility()==View.VISIBLE){
            Negotable=view.findViewById(R.id.POST_PRICE_RESI_CHECKBOX);
            NegotableRadio = (CheckBox) Negotable.getChildAt(0);
            Negotable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NegotableRadio.isChecked()){
                        NegotableRadio.setChecked(false);
                    }else {
                        NegotableRadio.setChecked(true);
                    }
                }
            });
            plc=view.findViewById(R.id.POST_PRICE_RESI_PLC_CHEACKBOX);
            carparking=view.findViewById(R.id.POST_PRICE_RESI_CARPARKING_CHEACKBOX);
            clubmembership=view.findViewById(R.id.POST_PRICE_RESI_CLUB_CHEACKBOX);
            stumpduty=view.findViewById(R.id.POST_PRICE_RESI_STUMP_DUTY_CEHACKBOX);
            priceInEnglish=view.findViewById(R.id.POST_PRICE_PRICE_INENGLISH);

            PLC_R=(CheckBox) plc.getChildAt(0);
            CARP_R=(CheckBox) carparking.getChildAt(0);
            CLUB_R=(CheckBox) clubmembership.getChildAt(0);
            STUMP_R=(CheckBox) stumpduty.getChildAt(0);

            plc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PLC_R.isChecked()){
                        PLC_R.setChecked(false);
                    }else {
                        PLC_R.setChecked(true);
                    }
                }
            });
            carparking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CARP_R.isChecked()){
                        CARP_R.setChecked(false);
                    }else {
                        CARP_R.setChecked(true);
                    }
                }
            });
            clubmembership.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CLUB_R.isChecked()){
                        CLUB_R.setChecked(false);
                    }else {
                        CLUB_R.setChecked(true);
                    }
                }
            });
            stumpduty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (STUMP_R.isChecked()){
                        STUMP_R.setChecked(false);
                    }else {
                        STUMP_R.setChecked(true);
                    }
                }
            });


            poojaRoom=view.findViewById(R.id.POST_PRICE_RESI_ADDTINAL_ROOM_POOJA);
            studyRoom=view.findViewById(R.id.POST_PRICE_RESI_ADDTINAL_ROOM_STUDY);
            storeRoom=view.findViewById(R.id.POST_PRICE_RESI_ADDTINAL_ROOM_STORE);
            sarvantRoom=view.findViewById(R.id.POST_PRICE_RESI_ADDTINAL_ROOM_SARVANT);

            directionNorth=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_N);
            directionSouth=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_S);
            directionEast=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_E);
            directionWest=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_WEST);
            directionNE=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_NE);
            directionNW=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_NW);
            directionSE=view.findViewById(R.id.POST_PRICE_RESI_DRECTION_SE);

            overLookingGarden=view.findViewById(R.id.POST_PRICE_RESI_OVERLOOKING_GARDEN);
            overLookingPol=view.findViewById(R.id.POST_PRICE_RESI_OVERLOOKING_POOL);
            overLookingMainRood=view.findViewById(R.id.POST_PRICE_RESI_OVERLOOKING_MAINROAD);
            overLookingNotAvailable=view.findViewById(R.id.POST_PRICE_RESI_OVERLOOKING_NO);

            parkingCovered=view.findViewById(R.id.POST_PRICE_RESI_COVERED_PARKING);
            parkingOpen=view.findViewById(R.id.POST_PRICE_RESI_PARKING_OPeN);

            liftYes=view.findViewById(R.id.POST_PRICE_RESI_LIFT_YES);
            liftNo=view.findViewById(R.id.POST_PRICE_RESI_LIFT_NO);

            water24=view.findViewById(R.id.POST_PRICE_RESI_WATER_24);
            waterAdd=view.findViewById(R.id.POST_PRICE_RESI_WATER_ADD);

            waterSMunicipal=view.findViewById(R.id.POST_PRICE_RESI_WATER_S_MUNCIPAL);
            waterSBourwell=view.findViewById(R.id.POST_PRICE_RESI_WATER_S_BOURWELL);
            waterSADD=view.findViewById(R.id.POST_PRICE_RESI_WATER_S_ADD);

            electric24=view.findViewById(R.id.POST_PRICE_RESI_ELECTRIC_24);
            electicAdd=view.findViewById(R.id.POST_PRICE_RESI_ELECTRIC_Add);

            powerNone=view.findViewById(R.id.POST_PRICE_RESI_BACKUP_NONE);
            powerFull=view.findViewById(R.id.POST_PRICE_RESI_POWER_BACKUP_FULL);
            powerPartial=view.findViewById(R.id.POST_PRICE_RESI_POWER_PARTIAL);

            floorMarble=view.findViewById(R.id.POST_PRICE_RESI_FLOR_MARBLE);
            floorConcrete=view.findViewById(R.id.POST_PRICE_RESI_FLOOR_CONCRATE);
            floorSeeAll=view.findViewById(R.id.POST_PRICE_RESI_FLOOR_SEEALL);

            popupMaintance=view.findViewById(R.id.POST_PRICE_RESI_);
            popupFacing=view.findViewById(R.id.POST_PRICE_RESI_FACING_POPUP);

            popupMaintance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Monthly");
                    addItems("Yearly");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                        TextView textView = (TextView) popupMaintance.getChildAt(0);
                        textView.setText(text);
                        IpopupMaintainace=text;
                        }
                    }, floorPojos);
                }
            });
            popupFacing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Feet");
                    addItems("Meter");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupFacing.getChildAt(0);
                            textView.setText(text);
                            IpopupFace=text;
                        }
                    }, floorPojos);
                }
            });

//            input
            expectedIn=view.findViewById(R.id.POST_PRICE_RESI_EXPECTED);
            pricePerSqIn=view.findViewById(R.id.POST_PRICE_RESI_PRICE_PERSQ);
            maintenceIn=view.findViewById(R.id.POST_PRICE_RESI_MAINTENACE_CHARGE_IN);
            bookingAmmIn=view.findViewById(R.id.POST_PRICE_RESI_BOOKING_AMMOUNT);
            expectedRentalIn=view.findViewById(R.id.POST_PRICE_RESI_EXPECTED_RENTAL);
            membershipIn=view.findViewById(R.id.POST_PRICE_RESI_MEMBERSHIP_CHARGE);
            faciingIn=view.findViewById(R.id.POST_PRICE_RESI_FACING_IN);

            expectedIn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s==null|s.length()<=0){
                        return;
                    }
                    setTextInEnglish(s);
                }
            });

            poojaRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    poojaRoom.setBackgroundResource(active);;
                    Rooms.add("Pooja");
                    Toast.makeText(getContext(), "Hello", Toast.LENGTH_LONG).show();
                }
            });
            studyRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studyRoom.setBackgroundResource(active);;
                    Rooms.add("Study");
                }
            });
            storeRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeRoom.setBackgroundResource(active);
                    Rooms.add("Store");
                }
            });
            sarvantRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sarvantRoom.setBackgroundResource(active);;
                    Rooms.add("Sarvant");
                }
            });
            directionSouth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Idrection="South";
                    directionSouth.setBackgroundResource(active);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                }
            });
            directionNorth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(active);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="North";
                }
            });
            directionEast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(active);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="East";
                }
            });
            directionWest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(active);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="West";
                }
            });
            directionNE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(active);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NE";
                }
            });
            directionNW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(active);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NW";
                }
            });
            directionSE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(active);
                    Idrection="SE";
                }
            });

            overLookingGarden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(active);
                    OverLockingData.add("Garden");
                }
            });
            overLookingPol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingPol.setBackgroundResource(active);
                    OverLockingData.add("Pool");
                }
            });
            overLookingMainRood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingMainRood.setBackgroundResource(active);
                    OverLockingData.add("Main Road");
                }
            });
            overLookingNotAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(defaults);
                    overLookingPol.setBackgroundResource(defaults);
                    overLookingMainRood.setBackgroundResource(defaults);
                    overLookingNotAvailable.setBackgroundResource(active);
                    OverLockingData.clear();
                    OverLockingData.add("Not Available");
                }
            });

            parkingCovered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingCovered.setBackgroundResource(active);
                    parkingOpen.setBackgroundResource(defaults);
                    Iparking="Covered";

                }
            });
            parkingOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingOpen.setBackgroundResource(active);
                    parkingCovered.setBackgroundResource(defaults);
                    Iparking="Open";
                }
            });
            liftYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftYes.setBackgroundResource(active);
                    liftNo.setBackgroundResource(defaults);
                    Ileft="1";
                }
            });
            liftNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftNo.setBackgroundResource(active);
                    liftYes.setBackgroundResource(defaults);
                    Ileft="2";
                }
            });
            water24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    water24.setBackgroundResource(active);
                    waterAdd.setBackgroundResource(defaults);
                    Iwater="24";
                    TextView textView = (TextView) waterAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterAdd.setBackgroundResource(active);
                    water24.setBackgroundResource(defaults);
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Iwater=value;
                            TextView textView = (TextView) waterAdd.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
            waterSMunicipal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(active);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Municipal";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSBourwell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(active);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Burwell";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(active);
                    ISwater="ADD";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) waterSADD.getChildAt(0);
                            textView.setText(value);
                            ISwater=value;
                        }
                    });
                }
            });
            electric24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electric24.setBackgroundResource(active);
                    electicAdd.setBackgroundResource(defaults);
                    Ielectric="24";
                    TextView textView = (TextView) electicAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            electicAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electicAdd.setBackgroundResource(active);
                    electric24.setBackgroundResource(defaults);
                    Ielectric="add";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) electicAdd.getChildAt(0);
                            textView.setText(value);
                            Iwater=value;
                        }
                    });
                }
            });
            powerFull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(active);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="Full";
                }
            });
            powerNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(active);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="None";
                }
            });
            powerPartial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(active);
                    Ipower="Particial";
                }
            });
            floorMarble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(active);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Marble";
                }
            });
            floorConcrete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(active);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Concreate";
                }
            });
            floorSeeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(active);
                    Intent intent =  new Intent(getContext(),FloorDetails.class);
                    intent.putExtra("data",flors);
                    startActivityForResult(intent,1);
                }
            });
        }
        if (COMMRCIAL.getVisibility()==View.VISIBLE){
            Negotable=view.findViewById(R.id.POST_PRICE_OTH_NEGOTABLE_CHEXK);
            NegotableRadio = (CheckBox) Negotable.getChildAt(0);
            Negotable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NegotableRadio.isChecked()){
                        NegotableRadio.setChecked(false);
                    }else {
                        NegotableRadio.setChecked(true);
                    }
                }
            });
            plc=view.findViewById(R.id.POST_PRICE_OTH_PLC_CHEACK);
            carparking=view.findViewById(R.id.POST_PRICE_OTH_CARPARKING_CHEAKBOX);
            clubmembership=view.findViewById(R.id.POST_PRICE_OTH_CLUB_CHEAKBOX);
            stumpduty=view.findViewById(R.id.POST_PRICE_OTH_STUMP_DUTY_CHECK);
            priceInEnglish=view.findViewById(R.id.POST_PRICE_PRICE_INENGLISH_COM);

            PLC_R=(CheckBox) plc.getChildAt(0);
            CARP_R=(CheckBox) carparking.getChildAt(0);
            CLUB_R=(CheckBox) clubmembership.getChildAt(0);
            STUMP_R=(CheckBox) stumpduty.getChildAt(0);

            plc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PLC_R.isChecked()){
                        PLC_R.setChecked(false);
                    }else {
                        PLC_R.setChecked(true);
                    }
                }
            });
            carparking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CARP_R.isChecked()){
                        CARP_R.setChecked(false);
                    }else {
                        CARP_R.setChecked(true);
                    }
                }
            });
            clubmembership.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CLUB_R.isChecked()){
                        CLUB_R.setChecked(false);
                    }else {
                        CLUB_R.setChecked(true);
                    }
                }
            });
            stumpduty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (STUMP_R.isChecked()){
                        STUMP_R.setChecked(false);
                    }else {
                        STUMP_R.setChecked(true);
                    }
                }
            });



            assuredReturns=view.findViewById(R.id.POST_PRICE_OTH_ASSURED_L);
            leesed=view.findViewById(R.id.POST_PRICE_OTH_LESED_L);
            air=view.findViewById(R.id.POST_PRICE_OTH_CENTRAL_AIR_L);
            oxygen=view.findViewById(R.id.POST_PRICE_OTH_OXYGEN_L);
            usp=view.findViewById(R.id.POST_PRICE_OTH_USP_L);

            fireExi=view.findViewById(R.id.POST_PRICE_OTH_FIREEXHUSTER);
            fireSensor=view.findViewById(R.id.POST_PRICE_OTH_FIRE_SENSOR);
            sprinkels=view.findViewById(R.id.POST_PRICE_OTH_SPRINKLE);
            fireHouse=view.findViewById(R.id.POST_PRICE_OTH_FIRE_HOUSE);

            stair1=view.findViewById(R.id.POST_PRICE_OTH_STAIRCASE_1);
            stair2=view.findViewById(R.id.POST_PRICE_OTH_STAIRCASE_2);
            stair3=view.findViewById(R.id.POST_PRICE_OTH_SATIRCASE_3);
            stair4=view.findViewById(R.id.POST_PRICE_OTH_STAIRCASE_4);

            directionNorth=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_N);
            directionSouth=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_S);
            directionEast=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_E);
            directionWest=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_WEST);
            directionNE=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_NE);
            directionNW=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_NW);
            directionSE=view.findViewById(R.id.POST_PRICE_OTH_DIRECTION_SE);

            overLookingGarden=view.findViewById(R.id.POST_PRICE_OTH_OVERLOOKING_GARDEN);
            overLookingPol=view.findViewById(R.id.POST_PRICE_OTH_OVERLOOKING_POOL);
            overLookingMainRood=view.findViewById(R.id.POST_PRICE_OTH_OVERLOOKING_MAIN_ROAD);
            overLookingNotAvailable=view.findViewById(R.id.POST_PRICE_OTH_OVERLOOKING_NO);

            parkingOpen=view.findViewById(R.id.POST_PRICE_OTH_PARKING_YES);
            parkingCovered=view.findViewById(R.id.POST_PRICE_OTH_PARKING_NO);

            parkingGruopRadio=view.findViewById(R.id.POST_PRICE_OTH_CHEACKGROUP);
            PPB=view.findViewById(R.id.POST_PRICE_OTH_CHEAK_1);
            PPO=view.findViewById(R.id.POST_PRICE_OTH_CHEAK_2);
            PP=view.findViewById(R.id.POST_PRICE_OTH_CHEAK_3);

            liftYes=view.findViewById(R.id.POST_PRICE_OTH_LIFT_YES);
            liftNo=view.findViewById(R.id.POST_PRICE_OTH_LIFT_NO);

            certifiedyes=view.findViewById(R.id.POST_PRICE_OTH_CERTIFIED_Yes);
            certifiedno=view.findViewById(R.id.POST_PRICE_OTH_CERTIFIED_No);

            nocYes=view.findViewById(R.id.POST_PRICE_OTH_NOC_YES);
            nocNo=view.findViewById(R.id.POST_PRICE_OTH_NOC_NO);

            backendOffice=view.findViewById(R.id.POST_PRICE_OTH_OFFICE_BACKEND);
            caOffice=view.findViewById(R.id.POST_PRICE_OTH_OFFICE_CA);
            frontedOffice=view.findViewById(R.id.POST_PRICE_OTH_OFFICE_FRONT);
            traderOffice=view.findViewById(R.id.POST_PRICE_OTH_OFFICE_TRADER);
            addOffice=view.findViewById(R.id.POST_PRICE_OTH_OFFICE_ADD);

            water24=view.findViewById(R.id.POST_PRICE_OTH_WATER_24);
            waterAdd=view.findViewById(R.id.POST_PRICE_OTH_WATER_ADD);

            waterSMunicipal=view.findViewById(R.id.POST_PRICE_OTH_WATER_S_MUN);
            waterSBourwell=view.findViewById(R.id.POST_PRICE_OTH_WATER_S_BOREWELL);
            waterSADD=view.findViewById(R.id.POST_PRICE_OTH_WATER_S_ADD);

            electric24=view.findViewById(R.id.POST_PRICE_OTH_ELECTRIC_24);
            electicAdd=view.findViewById(R.id.POST_PRICE_OTH_ELECTRIC_ADD);

            powerNone=view.findViewById(R.id.POST_PRICE_OTH_POWER_No);
            powerFull=view.findViewById(R.id.POST_PRICE_OTH_POWER_Full);
            powerPartial=view.findViewById(R.id.POST_PRICE_OTH_POWER_PARTIAL);

            floorMarble=view.findViewById(R.id.POST_PRICE_OTH_FLOOR_MARBLE);
            floorConcrete=view.findViewById(R.id.POST_PRICE_OTH_FLOOR_CONC);
            floorSeeAll=view.findViewById(R.id.POST_PRICE_OTH_FLOOR_SEE_ALL);

//            inputs
            expectedIn=view.findViewById(R.id.POST_PRICE_OTH_EXPECTED_PRICE_IN);
            pricePerSqIn=view.findViewById(R.id.POST_PRICE_OTH_PRICE_SQ_IM);
            bookingAmmIn=view.findViewById(R.id.POST_PRICE_OTH_BOOKING_IN);
            expectedRentalIn=view.findViewById(R.id.POST_PRICE_OTH_RENTAL_IN);
            membershipIn=view.findViewById(R.id.POST_PRICE_OTH_MEMBERSHIP_IN);
            maintenceIn=view.findViewById(R.id.POST_PRICE_OTH_MAINTANAN_IN);
            ideal_for_business_id=view.findViewById(R.id.POST_PRICE_OTH_IDEAL_IN);
            faciingIn=view.findViewById(R.id.POST_PRICE_OTH_ROAD_FACING_IN);

            expectedIn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s==null|s.length()<=0){
                        return;
                    }
                    setTextInEnglish(s);
                }
            });
            popupMaintance=view.findViewById(R.id.POST_PRICE_OTH_MAINTANACE_SELECT);
            popupFacing=view.findViewById(R.id.POST_PRICE_OTH_ROAD_FACING_SELECT);


            popupMaintance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Monthly");
                    addItems("Yearly");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupMaintance.getChildAt(0);
                            textView.setText(text);
                            IpopupMaintainace=text;
                        }
                    }, floorPojos);
                }
            });
            popupFacing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Feet");
                    addItems("Meeter");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupFacing.getChildAt(0);
                            textView.setText(text);
                            IpopupFace=text;
                        }
                    }, floorPojos);
                }
            });

            setLandZone=view.findViewById(R.id.POST_PRICE_OTH_LAND_XONE);
            setSpecialZone=view.findViewById(R.id.POST_PRICE_OTH_SPECIAL_ZONE);
            setLandZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LandZone = new ArrayList<>();
                    addItems("Industrial");
                    addItems("Commercial");
                    addItems("Residential");
                    addItems("Transport and Commercial");
                    addItems("Public Utilities");
                    addItems("Public Utilities");
                    addItems("Public and Semi Public use");
                    addItems("Open Spaces");
                    addItems("Agriculture Zone");
                    addItems("Speacial Economice Zone");
                    addItems("Natural Conversation Zone");
                    addItems("Goverment");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setLandZone.getChildAt(1);
                            textView.setText(text);
                            Ipopupland=text;
                        }
                    }, LandZone);
                }
            });
            setSpecialZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpecialLand = new ArrayList<>();
                    addItems("Special Economice Zone");
                    addItems("Free Trade Zone");
                    addItems("Export Processing Zone");
                    addItems("Free Zone");
                    addItems("Industrial Estate");
                    addItems("Free Ports");
                    addItems("Urben Enterprise Zones");
                    addItems("Software Technology Park");
                    addItems("Electronic Hardware Technology Park");
                    addItems("Export Oriented Unit");
                    addItems("Bio Technology Park");

                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setSpecialZone.getChildAt(1);
                            textView.setText(text);
                            IpopupSpecial=text;
                        }
                    }, SpecialLand);
                }
            });

            popupLed=view.findViewById(R.id.POST_PRICE_OTH_LEED_SELECT);
            popupLed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LEED = new ArrayList<>();
                    addItems("Not Applicable");
                    addItems("Certified");
                    addItems("Silver Certified");
                    addItems("Gold Certified");
                    addItems("Platinum Certified");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupLed.getChildAt(0);
                            textView.setText(text);
                            Ipopupled=text;
                        }
                    }, LEED);
                }
            });

            RadioButton radioButton1 = (RadioButton) assuredReturns.getChildAt(1);
            RadioButton radioButton2 = (RadioButton) assuredReturns.getChildAt(2);
            radioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton2.setChecked(false);
                    IAssured="Yes";
                }
            });
            radioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton1.setChecked(false);
                    IAssured="No";
                }
            });
            RadioButton radioButton3 = (RadioButton) leesed.getChildAt(1);
            RadioButton radioButton4 = (RadioButton) leesed.getChildAt(2);
            radioButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton4.setChecked(false);
                    Ileased="Yes";
                }
            });
            radioButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton3.setChecked(false);
                    Ileased="No";
                }
            });

            RadioButton radioButton5 = (RadioButton) air.getChildAt(1);
            RadioButton radioButton6 = (RadioButton) air.getChildAt(2);
            radioButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton6.setChecked(false);
                    Iair="Yes";
                }
            });
            radioButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton5.setChecked(false);
                    Iair="NO";
                }
            });
            RadioButton radioButton7 = (RadioButton) oxygen.getChildAt(1);
            RadioButton radioButton8 = (RadioButton) oxygen.getChildAt(2);
            radioButton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton8.setChecked(false);
                    Ioxygen="Yes";
                }
            });
            radioButton8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton7.setChecked(false);
                    Ioxygen="NO";
                }
            });


            RadioButton radioButton9 = (RadioButton) usp.getChildAt(1);
            RadioButton radioButton0 = (RadioButton) usp.getChildAt(2);
            radioButton9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton0.setChecked(false);
                    IUsp="Yes";
                }
            });
            radioButton0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton9.setChecked(false);
                    IUsp="No";
                }
            });

            fireExi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireExi.setBackgroundResource(active);
                    fireSensor.setBackgroundResource(defaults);
                    sprinkels.setBackgroundResource(defaults);
                    fireHouse.setBackgroundResource(defaults);
                    Ifire="Exi";
                }
            });
            fireSensor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireExi.setBackgroundResource(defaults);
                    fireSensor.setBackgroundResource(active);
                    sprinkels.setBackgroundResource(defaults);
                    fireHouse.setBackgroundResource(defaults);
                    Ifire="Sensor";
                }
            });
            sprinkels.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireExi.setBackgroundResource(defaults);
                    fireSensor.setBackgroundResource(defaults);
                    sprinkels.setBackgroundResource(active);
                    fireHouse.setBackgroundResource(defaults);
                    Ifire="Sprinkles";
                }
            });
            fireHouse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fireExi.setBackgroundResource(defaults);
                    fireSensor.setBackgroundResource(defaults);
                    sprinkels.setBackgroundResource(defaults);
                    fireHouse.setBackgroundResource(active);
                    Ifire="House";
                }
            });

            stair1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stair1.setBackgroundResource(active);
                    stair2.setBackgroundResource(defaults);
                    stair3.setBackgroundResource(defaults);
                    stair4.setBackgroundResource(defaults);
                    Istairs="1";
                }
            });
            stair2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stair1.setBackgroundResource(defaults);
                    stair2.setBackgroundResource(active);
                    stair3.setBackgroundResource(defaults);
                    stair4.setBackgroundResource(defaults);
                    Istairs="2";
                }
            });
            stair3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stair1.setBackgroundResource(defaults);
                    stair2.setBackgroundResource(defaults);
                    stair3.setBackgroundResource(active);
                    stair4.setBackgroundResource(defaults);
                    Istairs="3";
                }
            });
            stair4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stair1.setBackgroundResource(defaults);
                    stair2.setBackgroundResource(defaults);
                    stair3.setBackgroundResource(defaults);
                    stair4.setBackgroundResource(active);
                    Istairs="4";
                }
            });

            directionSouth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Idrection="South";
                    directionSouth.setBackgroundResource(active);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                }
            });
            directionNorth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(active);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="North";
                }
            });
            directionEast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(active);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="East";
                }
            });
            directionWest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(active);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="West";
                }
            });
            directionNE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(active);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NE";
                }
            });
            directionNW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(active);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NW";
                }
            });
            directionSE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(active);
                    Idrection="SE";
                }
            });

            overLookingGarden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(active);
                    OverLockingData.add("Garden");
                }
            });
            overLookingPol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingPol.setBackgroundResource(active);
                    OverLockingData.add("Pool");
                }
            });
            overLookingMainRood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingMainRood.setBackgroundResource(active);
                    OverLockingData.add("Main Road");
                }
            });
            overLookingNotAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(defaults);
                    overLookingPol.setBackgroundResource(defaults);
                    overLookingMainRood.setBackgroundResource(defaults);
                    overLookingNotAvailable.setBackgroundResource(active);
                    OverLockingData.clear();
                    OverLockingData.add("Not Available");
                }
            });

            parkingCovered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingCovered.setBackgroundResource(active);
                    parkingOpen.setBackgroundResource(defaults);

                }
            });
            parkingOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingOpen.setBackgroundResource(active);
                    parkingCovered.setBackgroundResource(defaults);
                }
            });
            liftYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftYes.setBackgroundResource(active);
                    liftNo.setBackgroundResource(defaults);
                    Ileft="1";
                }
            });
            liftNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftNo.setBackgroundResource(active);
                    liftYes.setBackgroundResource(defaults);
                    Ileft="2";
                }
            });
            water24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    water24.setBackgroundResource(active);
                    waterAdd.setBackgroundResource(defaults);
                    Iwater="24";
                    TextView textView = (TextView) waterAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterAdd.setBackgroundResource(active);
                    water24.setBackgroundResource(defaults);
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Iwater = value;
                            TextView textView = (TextView) waterAdd.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
            waterSMunicipal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(active);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Muncipal";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSBourwell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(active);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Bourwell";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(active);
                    ISwater="ADD";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) waterSADD.getChildAt(0);
                            textView.setText(value);
                            ISwater=value;
                        }
                    });
                }
            });
            electric24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electric24.setBackgroundResource(active);
                    electicAdd.setBackgroundResource(defaults);
                    Ielectric="24";
                    TextView textView = (TextView) electicAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            electicAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electicAdd.setBackgroundResource(active);
                    electric24.setBackgroundResource(defaults);
                    Ielectric="add";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Ielectric=value;
                            TextView textView = (TextView) electicAdd.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
            powerFull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(active);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="Full";
                }
            });
            powerNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(active);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="None";
                }
            });
            powerPartial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(active);
                    Ipower="Particial";
                }
            });
            floorMarble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(active);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Marble";
                }
            });
            floorConcrete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(active);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Concreate";
                }
            });
            floorSeeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(active);
                    Intent intent =  new Intent(getContext(),FloorDetails.class);
                    intent.putExtra("data",flors);
                    startActivityForResult(intent,1);
                }
            });

            parkingGruopRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId==R.id.POST_PRICE_OTH_CHEAK_1){
                        Iparking="PPB";
                    }
                    if (checkedId==R.id.POST_PRICE_OTH_CHEAK_2){
                        Iparking="PPO";
                    }
                    if (checkedId==R.id.POST_PRICE_OTH_CHEAK_3){
                        Iparking="PP";
                    }
                }
            });

            certifiedyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    certifiedyes.setBackgroundResource(active);
                    certifiedno.setBackgroundResource(defaults);
                    Icertified="Yes";
                }
            });
            certifiedno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    certifiedyes.setBackgroundResource(defaults);
                    certifiedno.setBackgroundResource(active);
                    Icertified="No";
                }
            });
            nocYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nocYes.setBackgroundResource(active);
                    nocNo.setBackgroundResource(defaults);
                    Inoc="Yes";
                }
            });
            nocNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nocNo.setBackgroundResource(active);
                    nocYes.setBackgroundResource(defaults);
                    Inoc="Noc";
                }
            });
            backendOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backendOffice.setBackgroundResource(active);
                    caOffice.setBackgroundResource(defaults);
                    frontedOffice.setBackgroundResource(defaults);
                    traderOffice.setBackgroundResource(defaults);
                    addOffice.setBackgroundResource(defaults);
                    Ioffice="Backend";
                    TextView textView = (TextView) addOffice.getChildAt(0);
                    textView.setText("+add");
                }
            });
            caOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backendOffice.setBackgroundResource(defaults);
                    caOffice.setBackgroundResource(active);
                    frontedOffice.setBackgroundResource(defaults);
                    traderOffice.setBackgroundResource(defaults);
                    addOffice.setBackgroundResource(defaults);
                    Ioffice="CA";
                    TextView textView = (TextView) addOffice.getChildAt(0);
                    textView.setText("+add");
                }
            });
            frontedOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backendOffice.setBackgroundResource(defaults);
                    caOffice.setBackgroundResource(defaults);
                    frontedOffice.setBackgroundResource(active);
                    traderOffice.setBackgroundResource(defaults);
                    addOffice.setBackgroundResource(defaults);
                    Ioffice="Fronted";
                    TextView textView = (TextView) addOffice.getChildAt(0);
                    textView.setText("+add");
                }
            });
            traderOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backendOffice.setBackgroundResource(defaults);
                    caOffice.setBackgroundResource(defaults);
                    frontedOffice.setBackgroundResource(defaults);
                    traderOffice.setBackgroundResource(active);
                    addOffice.setBackgroundResource(defaults);
                    Ioffice="Trader";
                    TextView textView = (TextView) addOffice.getChildAt(0);
                    textView.setText("+add");
                }
            });
            addOffice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backendOffice.setBackgroundResource(defaults);
                    caOffice.setBackgroundResource(defaults);
                    frontedOffice.setBackgroundResource(defaults);
                    traderOffice.setBackgroundResource(defaults);
                    addOffice.setBackgroundResource(active);
                    Ioffice="Trader";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Ioffice =  value;
                            TextView textView = (TextView) addOffice.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
        }
        if (PLOTCOMERCIAL.getVisibility()==View.VISIBLE){
            directionNorth=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_N);
            directionEast=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_E);
            directionSouth=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_S);
            directionWest=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_W);
            directionNE=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_NE);
            directionNW=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_NW);
            directionSE=view.findViewById(R.id.POST_PRICE_COM_PLOT_DRECTION_SE);

            overLookingGarden=view.findViewById(R.id.POST_PRICE_COM_PLOT_OVERLOOKING_G);
            overLookingPol=view.findViewById(R.id.POST_PRICE_COM_PLOT_OVERLOOKING_P);
            overLookingMainRood=view.findViewById(R.id.POST_PRICE_COM_PLOT_OVERLOOKING_MR);
            overLookingNotAvailable=view.findViewById(R.id.POST_PRICE_COM_PLOT_OVERLOOKING_NO);

            parkingOpen=view.findViewById(R.id.POST_PRICE_OTH_PARKING_YES);
            parkingCovered=view.findViewById(R.id.POST_PRICE_COM_PLOT_PARKING_NO);

            parkingGruopRadio=view.findViewById(R.id.POST_PRICE_COM_PLOT_RADIOOS);

            water24=view.findViewById(R.id.POST_PRICE_COM_PLOT_Water_24);
            waterAdd=view.findViewById(R.id.POST_PRICE_COM_PLOT_WATER_ADD);

            waterSMunicipal=view.findViewById(R.id.POST_PRICE_COM_PLOT_WATER_S_MUNICIPAL);
            waterSBourwell=view.findViewById(R.id.POST_PRICE_COM_PLOT_WATER_S_BOREWELL);
            waterSADD=view.findViewById(R.id.POST_PRICE_COM_PLOT_WATER_S_ADD);

            electric24=view.findViewById(R.id.POST_PRICE_COM_PLOT_ELECTRIC_24);
            electicAdd=view.findViewById(R.id.POST_PRICE_COM_PLOT_ELECTRIC_ADD);

            powerNone=view.findViewById(R.id.POST_PRICE_COM_PLOT_POWER_BACKUP_NO);
            powerFull=view.findViewById(R.id.POST_PRICE_COM_PLOT_POWER_FULL);
            powerPartial=view.findViewById(R.id.POST_PRICE_COM_PLOT_POWER_PARTIAL);

            floorMarble=view.findViewById(R.id.POST_PRICE_COM_PLOT_MARBLE);
            floorConcrete=view.findViewById(R.id.POST_PRICE_COM_PLOT_FLOOR_CONC);
            floorSeeAll=view.findViewById(R.id.POST_PRICE_COM_PLOT_FLOOR_SHOW);
            priceInEnglish=view.findViewById(R.id.POST_PRICE_PRICE_INENGLISH_PLOT);


//            inputs
            expectedIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_EXPECTED_IN);
            pricePerSqIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_SQ_IN);
            expectedRentalIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_EXPECTED_RENTAL_IN);
            bookingAmmIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_BOOKING_AMMOUNT_IN);
            membershipIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_MEMBERSHIP_CHARGE);
            ideal_for_business_id=view.findViewById(R.id.POST_PRICE_COM_PLOT_IDEAL_IN);
            faciingIn=view.findViewById(R.id.POST_PRICE_COM_PLOT_FACING_IN);

            expectedIn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s==null|s.length()<=0){
                        return;
                    }
                    setTextInEnglish(s);
                }
            });

//            cheacks
            Negotable=view.findViewById(R.id.POST_PRICE_COM_PLOT_NEGOTABLE_CHEACKBOX);
            NegotableRadio = (CheckBox) Negotable.getChildAt(0);
            Negotable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NegotableRadio.isChecked()){
                        NegotableRadio.setChecked(false);
                    }else {
                        NegotableRadio.setChecked(true);
                    }
                }
            });
            plc=view.findViewById(R.id.POST_PRICE_COM_PLOT_PLC_CHEACKBOX);
            carparking=view.findViewById(R.id.POST_PRICE_COM_PLOT_CAR_PARKING_CHEACBOX);
            clubmembership=view.findViewById(R.id.POST_PRICE_COM_PLOT_CLUB_CHEACKBOX);
            stumpduty=view.findViewById(R.id.POST_PRICE_COM_PLOT_STUMP_DUTY_CHEACKBOX);

            PLC_R=(CheckBox) plc.getChildAt(0);
            CARP_R=(CheckBox) carparking.getChildAt(0);
            CLUB_R=(CheckBox) clubmembership.getChildAt(0);
            STUMP_R=(CheckBox) stumpduty.getChildAt(0);

            plc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PLC_R.isChecked()){
                        PLC_R.setChecked(false);
                    }else {
                        PLC_R.setChecked(true);
                    }
                }
            });
            carparking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CARP_R.isChecked()){
                        CARP_R.setChecked(false);
                    }else {
                        CARP_R.setChecked(true);
                    }
                }
            });
            clubmembership.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CLUB_R.isChecked()){
                        CLUB_R.setChecked(false);
                    }else {
                        CLUB_R.setChecked(true);
                    }
                }
            });
            stumpduty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (STUMP_R.isChecked()){
                        STUMP_R.setChecked(false);
                    }else {
                        STUMP_R.setChecked(true);
                    }
                }
            });

//            popups
            popupMaintance=view.findViewById(R.id.POST_PRICE_COM_PLOT_MAINTANACE_SELECT);
            popupFacing=view.findViewById(R.id.POST_PRICE_COM_PLOT_FACING_SELCET);


            popupMaintance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Monthly");
                    addItems("Yearly");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupMaintance.getChildAt(0);
                            textView.setText(text);
                            IpopupMaintainace=text;
                        }
                    }, floorPojos);
                }
            });
            popupFacing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Feet");
                    addItems("Meeter");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupFacing.getChildAt(0);
                            textView.setText(text);
                            IpopupFace=text;
                        }
                    }, floorPojos);
                }
            });

            setLandZone=view.findViewById(R.id.POST_PRICE_COM_PLOT_LAND_ZONE);
            setSpecialZone=view.findViewById(R.id.POST_PRICE_COM_PLOT_SPECIAL_ZONE);
            setLandZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LandZone = new ArrayList<>();
                    addItems("Industrial");
                    addItems("Commercial");
                    addItems("Residential");
                    addItems("Transport and Commercial");
                    addItems("Public Utilities");
                    addItems("Public Utilities");
                    addItems("Public and Semi Public use");
                    addItems("Open Spaces");
                    addItems("Agriculture Zone");
                    addItems("Speacial Economice Zone");
                    addItems("Natural Conversation Zone");
                    addItems("Goverment");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setLandZone.getChildAt(1);
                            textView.setText(text);
                            Ipopupland=text;
                        }
                    }, LandZone);
                }
            });
            setSpecialZone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpecialLand = new ArrayList<>();
                    addItems("Special Economice Zone");
                    addItems("Free Trade Zone");
                    addItems("Export Processing Zone");
                    addItems("Free Zone");
                    addItems("Industrial Estate");
                    addItems("Free Ports");
                    addItems("Urben Enterprise Zones");
                    addItems("Software Technology Park");
                    addItems("Electronic Hardware Technology Park");
                    addItems("Export Oriented Unit");
                    addItems("Bio Technology Park");

                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setSpecialZone.getChildAt(1);
                            textView.setText(text);
                            IpopupSpecial=text;
                        }
                    }, SpecialLand);
                }
            });

            directionSouth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Idrection="South";
                    directionSouth.setBackgroundResource(active);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                }
            });
            directionNorth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(active);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="North";
                }
            });
            directionEast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(active);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="East";
                }
            });
            directionWest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(active);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="West";
                }
            });
            directionNE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(active);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NE";
                }
            });
            directionNW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(active);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NW";
                }
            });
            directionSE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(active);
                    Idrection="SE";
                }
            });

            overLookingGarden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(active);
                    OverLockingData.add("Garden");
                }
            });
            overLookingPol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingPol.setBackgroundResource(active);
                    OverLockingData.add("Pool");
                }
            });
            overLookingMainRood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingMainRood.setBackgroundResource(active);
                    OverLockingData.add("Main Road");
                }
            });
            overLookingNotAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(defaults);
                    overLookingPol.setBackgroundResource(defaults);
                    overLookingMainRood.setBackgroundResource(defaults);
                    overLookingNotAvailable.setBackgroundResource(active);
                    OverLockingData.clear();
                    OverLockingData.add("Not Available");
                }
            });

            parkingCovered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingCovered.setBackgroundResource(active);
                    parkingOpen.setBackgroundResource(defaults);

                }
            });
            parkingOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingOpen.setBackgroundResource(active);
                    parkingCovered.setBackgroundResource(defaults);
                }
            });

            water24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    water24.setBackgroundResource(active);
                    waterAdd.setBackgroundResource(defaults);
                    Iwater="24";
                    TextView textView = (TextView) waterAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterAdd.setBackgroundResource(active);
                    water24.setBackgroundResource(defaults);
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Iwater = value;
                            TextView textView = (TextView) waterAdd.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
            waterSMunicipal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(active);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Muncipal";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSBourwell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(active);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Bourwell";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(active);
                    ISwater="ADD";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) waterSADD.getChildAt(0);
                            textView.setText(value);
                            ISwater=value;
                        }
                    });
                }
            });
            electric24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electric24.setBackgroundResource(active);
                    electicAdd.setBackgroundResource(defaults);
                    Ielectric="24";
                    TextView textView = (TextView) electicAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            electicAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electicAdd.setBackgroundResource(active);
                    electric24.setBackgroundResource(defaults);
                    Ielectric="add";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            Ielectric=value;
                            TextView textView = (TextView) electicAdd.getChildAt(0);
                            textView.setText(value);
                        }
                    });
                }
            });
            powerFull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(active);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="Full";
                }
            });
            powerNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(active);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="None";
                }
            });
            powerPartial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(active);
                    Ipower="Particial";
                }
            });
            floorMarble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(active);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Marble";
                }
            });
            floorConcrete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(active);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Concreate";
                }
            });
            floorSeeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(active);
                    Intent intent =  new Intent(getContext(),FloorDetails.class);
                    intent.putExtra("data",flors);
                    startActivityForResult(intent,1);
                }
            });

        }
        if (OTHER.getVisibility()==View.VISIBLE){
            assuredReturns=view.findViewById(R.id.POST_PRICE_COM_ASSURED_L);
            leesed=view.findViewById(R.id.POST_PRICE_COM_LEESED_L);

            setLandZoneL=view.findViewById(R.id.POST_PRICE_COM_LAND_ZONE);
            setSpecialZoneL=view.findViewById(R.id.POST_PRICE_COM_SPECIAL_ZONE);

            poojaRoom=view.findViewById(R.id.POST_PRICE_COM_ROOM_POOJA);
            studyRoom=view.findViewById(R.id.POST_PRICE_COM_ROOM_STUDY_ROOM);
            storeRoom=view.findViewById(R.id.POST_PRICE_COM_ROOM_STORE_ROOM);
            sarvantRoom=view.findViewById(R.id.POST_PRICE_COM_ROOM_SARVANT_ROOM);

            directionNorth=view.findViewById(R.id.POST_PRICE_COM_Drection_N);
            directionSouth=view.findViewById(R.id.POST_PRICE_COM_Drection_S);
            directionEast=view.findViewById(R.id.POST_PRICE_COM_Drection_E);
            directionWest=view.findViewById(R.id.POST_PRICE_COM_Drection_W);
            directionNE=view.findViewById(R.id.POST_PRICE_COM_Drection_NE);
            directionNW=view.findViewById(R.id.POST_PRICE_COM_Drection_NW);
            directionSE=view.findViewById(R.id.POST_PRICE_COM_Drection_SE);

            overLookingGarden=view.findViewById(R.id.POST_PRICE_COM_OVERLOOKING_Garden);
            overLookingPol=view.findViewById(R.id.POST_PRICE_COM_OVERLOOKING_Pool);
            overLookingMainRood=view.findViewById(R.id.POST_PRICE_COM_OVERLOOKING_MainRoad);
            overLookingNotAvailable=view.findViewById(R.id.POST_PRICE_COM_OVERLOOKING_NOI);

            parkingOpen=view.findViewById(R.id.POST_PRICE_OTH_PARKING_YES);
            parkingCovered=view.findViewById(R.id.POST_PRICE_COM_PARKING_NO);

            parkingGruopRadio=view.findViewById(R.id.POST_PRICE_COM_RADIO);

            liftYes=view.findViewById(R.id.POST_PRICE_COM_LIFT_YES);
            liftNo=view.findViewById(R.id.POST_PRICE_COM_LIFT_NO);

            water24=view.findViewById(R.id.POST_PRICE_COM_WATER_24);
            waterAdd=view.findViewById(R.id.POST_PRICE_COM_WATER_ADD);

            powerNone=view.findViewById(R.id.POST_PRICE_COM_POWER_NONE);
            powerFull=view.findViewById(R.id.POST_PRICE_COM_POWER_FULL);
            powerPartial=view.findViewById(R.id.POST_PRICE_COM_POWER_PARTIAL);

            floorMarble=view.findViewById(R.id.POST_PRICE_COM_FLOOR_MARBLE);
            floorConcrete=view.findViewById(R.id.POST_PRICE_COM_FLOOR_CONCREATE);
            floorSeeAll=view.findViewById(R.id.POST_PRICE_COM_FLOOR_SEE_ALL);

            waterSMunicipal=view.findViewById(R.id.POST_PRICE_COM_WATER_S_MUNCIPAL);
            waterSBourwell=view.findViewById(R.id.POST_PRICE_COM_WATER_S_BOREWELL);
            waterSADD=view.findViewById(R.id.POST_PRICE_COM_WATER_S_ADD);

            electric24=view.findViewById(R.id.POST_PRICE_COM_ELECTIC_24);
            electicAdd=view.findViewById(R.id.POST_PRICE_COM_ELECTIC_ADD);
            priceInEnglish=view.findViewById(R.id.POST_PRICE_PRICE_INENGLISH_OTH);



//            inputs

            expectedIn=view.findViewById(R.id.POST_PRICE_COM_EXPECTED_IN);
            pricePerSqIn=view.findViewById(R.id.POST_PRICE_COM_SQ_PRICE_IN);
            maintenceIn=view.findViewById(R.id.POST_PRICE_COM_MAINTANCE_IN);
            bookingAmmIn=view.findViewById(R.id.POST_PRICE_COM_BOOKING_IN);
            expectedRentalIn=view.findViewById(R.id.POST_PRICE_COM_EXPECTED_RENTAL_IN);
            membershipIn=view.findViewById(R.id.POST_PRICE_COM_MEMBERSHIP_CHARGE_IN);
            ideal_for_business_id=view.findViewById(R.id.POST_PRICE_COM_IDEAL_BUSS_IN);
            faciingIn=view.findViewById(R.id.POST_PRICE_COM_FACING_IN);
            Negotable=view.findViewById(R.id.POST_PRICE_COM_PRICE_NEGOTABLE);
            NegotableRadio = (CheckBox) Negotable.getChildAt(0);
            Negotable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NegotableRadio.isChecked()){
                        NegotableRadio.setChecked(false);
                    }else {
                        NegotableRadio.setChecked(true);
                    }
                }
            });
            expectedIn.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s==null|s.length()<=0){
                        return;
                    }
                    setTextInEnglish(s);
                }
            });
            plc=view.findViewById(R.id.POST_PRICE_COM_CHEACKBOX_PLC);
            carparking=view.findViewById(R.id.POST_PRICE_COM_CHEACKBOX_PARKING);
            clubmembership=view.findViewById(R.id.POST_PRICE_COM_CHEACKBOX_CLUB);
            stumpduty=view.findViewById(R.id.POST_PRICE_COM_CHEACKBOX_STUMP);

            PLC_R=(CheckBox) plc.getChildAt(0);
            CARP_R=(CheckBox) carparking.getChildAt(0);
            CLUB_R=(CheckBox) clubmembership.getChildAt(0);
            STUMP_R=(CheckBox) stumpduty.getChildAt(0);

            plc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (PLC_R.isChecked()){
                        PLC_R.setChecked(false);
                    }else {
                        PLC_R.setChecked(true);
                    }
                }
            });
            carparking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CARP_R.isChecked()){
                        CARP_R.setChecked(false);
                    }else {
                        CARP_R.setChecked(true);
                    }
                }
            });
            clubmembership.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CLUB_R.isChecked()){
                        CLUB_R.setChecked(false);
                    }else {
                        CLUB_R.setChecked(true);
                    }
                }
            });
            stumpduty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (STUMP_R.isChecked()){
                        STUMP_R.setChecked(false);
                    }else {
                        STUMP_R.setChecked(true);
                    }
                }
            });
            RadioButton radioButton1 = (RadioButton) assuredReturns.getChildAt(1);
            RadioButton radioButton2 = (RadioButton) assuredReturns.getChildAt(2);
            radioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton2.setChecked(false);
                    IAssured="Yes";
                }
            });
            radioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton1.setChecked(false);
                    IAssured="No";
                }
            });
            RadioButton radioButton3 = (RadioButton) leesed.getChildAt(1);
            RadioButton radioButton4 = (RadioButton) leesed.getChildAt(2);
            radioButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton4.setChecked(false);
                    Ileased="Yes";
                }
            });
            radioButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioButton3.setChecked(false);
                    Ileased="No";
                }
            });

            poojaRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    poojaRoom.setBackgroundResource(active);;
                    Rooms.add("Pooja");
                    Toast.makeText(getContext(), "Hello", Toast.LENGTH_LONG).show();
                }
            });
            studyRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studyRoom.setBackgroundResource(active);;
                    Rooms.add("Study");
                }
            });
            storeRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storeRoom.setBackgroundResource(active);
                    Rooms.add("Store");
                }
            });
            sarvantRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sarvantRoom.setBackgroundResource(active);;
                    Rooms.add("Sarvant");
                }
            });

            directionSouth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Idrection="South";
                    directionSouth.setBackgroundResource(active);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                }
            });
            directionNorth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(active);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="North";
                }
            });
            directionEast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(active);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="East";
                }
            });
            directionWest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(active);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="West";
                }
            });
            directionNE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(active);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NE";
                }
            });
            directionNW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(active);
                    directionSE.setBackgroundResource(defaults);
                    Idrection="NW";
                }
            });
            directionSE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directionSouth.setBackgroundResource(defaults);
                    directionNorth.setBackgroundResource(defaults);
                    directionEast.setBackgroundResource(defaults);
                    directionWest.setBackgroundResource(defaults);
                    directionNE.setBackgroundResource(defaults);
                    directionNW.setBackgroundResource(defaults);
                    directionSE.setBackgroundResource(active);
                    Idrection="SE";
                }
            });

            overLookingGarden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(active);
                    OverLockingData.add("Garden");
                }
            });
            overLookingPol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingPol.setBackgroundResource(active);
                    OverLockingData.add("Pool");
                }
            });
            overLookingMainRood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingMainRood.setBackgroundResource(active);
                    OverLockingData.add("Main Road");
                }
            });
            overLookingNotAvailable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overLookingGarden.setBackgroundResource(defaults);
                    overLookingPol.setBackgroundResource(defaults);
                    overLookingMainRood.setBackgroundResource(defaults);
                    overLookingNotAvailable.setBackgroundResource(active);
                    OverLockingData.clear();
                    OverLockingData.add("Not Available");
                }
            });

            parkingCovered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingCovered.setBackgroundResource(active);
                    parkingOpen.setBackgroundResource(defaults);

                }
            });
            parkingOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parkingOpen.setBackgroundResource(active);
                    parkingCovered.setBackgroundResource(defaults);
                }
            });
            liftYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftYes.setBackgroundResource(active);
                    liftNo.setBackgroundResource(defaults);
                    Ileft="1";
                }
            });
            liftNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liftNo.setBackgroundResource(active);
                    liftYes.setBackgroundResource(defaults);
                    Ileft="2";
                }
            });
            water24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    water24.setBackgroundResource(active);
                    waterAdd.setBackgroundResource(defaults);
                    Iwater="24";
                    TextView textView = (TextView) waterAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterAdd.setBackgroundResource(active);
                    water24.setBackgroundResource(defaults);
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) waterAdd.getChildAt(0);
                            textView.setText(value);
                            Iwater=value;
                        }
                    });
                }
            });
            waterSMunicipal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(active);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Muncipal";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSBourwell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(active);
                    waterSADD.setBackgroundResource(defaults);
                    ISwater="Bourwell";
                    TextView textView = (TextView) waterSADD.getChildAt(0);
                    textView.setText("+add");
                }
            });
            waterSADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    waterSMunicipal.setBackgroundResource(defaults);
                    waterSBourwell.setBackgroundResource(defaults);
                    waterSADD.setBackgroundResource(active);
                    ISwater="ADD";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) waterSADD.getChildAt(0);
                            textView.setText(value);
                            ISwater=value;
                        }
                    });
                }
            });
            electric24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electric24.setBackgroundResource(active);
                    electicAdd.setBackgroundResource(defaults);
                    Ielectric="24";
                    TextView textView = (TextView) electicAdd.getChildAt(0);
                    textView.setText("+add");
                }
            });
            electicAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    electicAdd.setBackgroundResource(active);
                    electric24.setBackgroundResource(defaults);
                    Ielectric="add";
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView textView = (TextView) electicAdd.getChildAt(0);
                            textView.setText(value);
                            Ielectric=value;
                        }
                    });
                }
            });
            powerFull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(active);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="Full";
                }
            });
            powerNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(active);
                    powerPartial.setBackgroundResource(defaults);
                    Ipower="None";
                }
            });
            powerPartial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    powerFull.setBackgroundResource(defaults);
                    powerNone.setBackgroundResource(defaults);
                    powerPartial.setBackgroundResource(active);
                    Ipower="Particial";
                }
            });
            floorMarble.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(active);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Marble";
                }
            });
            floorConcrete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(active);
                    floorSeeAll.setBackgroundResource(defaults);
                    Ifloor="Concreate";
                }
            });
            floorSeeAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorMarble.setBackgroundResource(defaults);
                    floorConcrete.setBackgroundResource(defaults);
                    floorSeeAll.setBackgroundResource(active);
                    Intent intent =  new Intent(getContext(),FloorDetails.class);
                    intent.putExtra("data",flors);
                    startActivityForResult(intent,1);
                }
            });

            parkingGruopRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId==R.id.POST_PRICE_COM_RADIO_1){
                        Iparking="PPB";
                    }
                    if (checkedId==R.id.POST_PRICE_COM_RADIO_2){
                        Iparking="PPO";
                    }
                    if (checkedId==R.id.POST_PRICE_COM_RADIO_3){
                        Iparking="PP";
                    }
                }
            });

            setLandZoneL=view.findViewById(R.id.POST_PRICE_COM_LAND_ZONE);
            setSpecialZoneL=view.findViewById(R.id.POST_PRICE_COM_SPECIAL_ZONE);
            setLandZoneL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LandZone = new ArrayList<>();
                    addItems("Industrial");
                    addItems("Commercial");
                    addItems("Residential");
                    addItems("Transport and Commercial");
                    addItems("Public Utilities");
                    addItems("Public Utilities");
                    addItems("Public and Semi Public use");
                    addItems("Open Spaces");
                    addItems("Agriculture Zone");
                    addItems("Speacial Economice Zone");
                    addItems("Natural Conversation Zone");
                    addItems("Goverment");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setLandZoneL.getChildAt(0);
                            textView.setText(text);
                            Ipopupland=text;
                        }
                    }, LandZone);
                }
            });
            setSpecialZoneL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SpecialLand = new ArrayList<>();
                    addItems("Special Economice Zone");
                    addItems("Free Trade Zone");
                    addItems("Export Processing Zone");
                    addItems("Free Zone");
                    addItems("Industrial Estate");
                    addItems("Free Ports");
                    addItems("Urben Enterprise Zones");
                    addItems("Software Technology Park");
                    addItems("Electronic Hardware Technology Park");
                    addItems("Export Oriented Unit");
                    addItems("Bio Technology Park");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) setSpecialZoneL.getChildAt(0);
                            textView.setText(text);
                            IpopupSpecial=text;
                        }
                    }, SpecialLand);
                }
            });

            popupMaintance=view.findViewById(R.id.POST_PRICE_COM_MAINTAN_SELECT);
            popupFacing=view.findViewById(R.id.POST_PRICE_COM_OVERLOOKING_FACING_SELECT);


            popupMaintance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Monthly");
                    addItems("Yearly");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupMaintance.getChildAt(0);
                            textView.setText(text);
                            IpopupMaintainace=text;
                        }
                    }, floorPojos);
                }
            });
            popupFacing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floorPojos = new ArrayList<>();
                    addItems("Select");
                    addItems("Feet");
                    addItems("Meeter");
                    new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                        @Override
                        public void onitemClick(String text) {
                            TextView textView = (TextView) popupFacing.getChildAt(0);
                            textView.setText(text);
                            IpopupFace=text;
                        }
                    }, floorPojos);
                }
            });








        }

        planBasicActivity = (PlanBasicActivity) getActivity();
        card_submit=view.findViewById(R.id.POST_PHOTO_CONTINEUE);
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                aichat ai = new aichat();
//                ai.setText("TAB");
//                ai.setValue("3");
//                ai.setValues("tb");
//                liveCommnication.setText(ai);
                pricingFeatures();
            }
        });
        return view;
    }
    private void updateRequest() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PRICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        PropertyPricModal propertyModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PropertyPricModal.class);
                        setDataFromServer(propertyModal);
                        TextView textView = (TextView) card_submit.getChildAt(0);
                        textView.setText("Update");
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onResponse: "+error );
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
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

    }
    private void setDataFromServer(PropertyPricModal data) {
        if (RESI.getVisibility()==View.VISIBLE){
            if (data.getPrice_negotiable().equals("Yes")){
                NegotableRadio.setChecked(true);
            }
            if (data.getAdditional_price().equals("PLC")){
                PLC_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Car Parking")){
                CARP_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Club Membership")){
                CLUB_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Stump Duty Registration")){
                STUMP_R.setChecked(true);
            }
            String[] poja = data.getAdditional_rooms().split(",");
            for (String s:poja){
                if (s.equals("Pooja")){
                    poojaRoom.performClick();
                }
                if (s.equals("Study")){
                    studyRoom.performClick();
                }
                if (s.equals("Store")){
                    storeRoom.performClick();
                }
                if (s.equals("Sarvant")){
                    sarvantRoom.performClick();
                }
            }
            if (data.getDirection_facing().equals("South")){
                directionSouth.performClick();
            }
            if (data.getDirection_facing().equals("North")){
                directionNorth.performClick();
            }
            if (data.getDirection_facing().equals("East")){
                directionEast.performClick();
            }
            if (data.getDirection_facing().equals("West")){
                directionWest.performClick();
            }
            if (data.getDirection_facing().equals("NE")){
                directionNE.performClick();
            }
            if (data.getDirection_facing().equals("NW")){
                directionNW.performClick();
            }
            if (data.getDirection_facing().equals("SE")){
                directionSE.performClick();
            }
            String[] oeverloacking = data.getOverlooking().split(",");
            for (String s:oeverloacking){
                if (s.equals("Garden")){
                    overLookingGarden.performClick();
                }
                if (s.equals("Pool")){
                    overLookingPol.performClick();
                }
                if (s.equals("Main Road")){
                    overLookingMainRood.performClick();
                }
                if (s.equals("Not Available")){
                    overLookingNotAvailable.performClick();
                }
            }
            if (data.getParking_facility().equals("Covered")){
                parkingCovered.performClick();
            }
            if (data.getParking_facility().equals("Open")){
                parkingOpen.performClick();
            }
            if (data.getResidentiallift().equals("Yes")){
                liftYes.performClick();
            }else {
                liftNo.performClick();
            }
            if (data.getWater_availability().equals("24")){
                water24.performClick();
            }else {
                waterAdd.setBackgroundResource(active);
                TextView textView = (TextView) waterAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Iwater=data.getWater_availability();
            }
            if (data.getWater_source().equals("Municipal")){
                waterSMunicipal.performClick();
            }
            if (data.getWater_source().equals("Burwell")){
                waterSBourwell.performClick();
            }else {
                waterSADD.setBackgroundResource(active);
                TextView textView = (TextView) waterSADD.getChildAt(0);
                textView.setText(data.getWater_source());
                ISwater=data.getWater_source();
            }
            if (data.getElectricity_availability().equals("24")){
                electric24.performClick();
            }else {
                electicAdd.setBackgroundResource(active);
                TextView textView = (TextView) electicAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Ielectric=data.getWater_availability();
            }
            if (data.getPower_backup().equals("Full")){
                powerFull.performClick();
            }
            if (data.getPower_backup().equals("None")){
                powerNone.performClick();
            }else {
               powerPartial.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorMarble.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorConcrete.performClick();
            }else {
                floorConcrete.setBackgroundResource(active);
                TextView textView = (TextView) floorConcrete.getChildAt(0);
                textView.setText(data.getFloor_details());
                Ifloor=data.getFloor_details();
            }
            expectedIn.setText(data.getExpectedprice());
            pricePerSqIn.setText(data.getPrice_per_sq_ft());
            maintenceIn.setText(data.getMaintenance_charge());
            bookingAmmIn.setText(data.getBooking_amount());
            expectedRentalIn.setText(data.getExpected_rental());
            faciingIn.setText(data.getFacing_road());

        }
        if (COMMRCIAL.getVisibility()==View.VISIBLE){
            if (data.getPrice_negotiable().equals("Yes")){
                NegotableRadio.setChecked(true);
            }
            if (data.getAdditional_price().equals("PLC")){
                PLC_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Car Parking")){
                CARP_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Club Membership")){
                CLUB_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Stump Duty Registration")){
                STUMP_R.setChecked(true);
            }
            if (data.getDirection_facing().equals("South")){
                directionSouth.performClick();
            }
            if (data.getDirection_facing().equals("North")){
                directionNorth.performClick();
            }
            if (data.getDirection_facing().equals("East")){
                directionEast.performClick();
            }
            if (data.getDirection_facing().equals("West")){
                directionWest.performClick();
            }
            if (data.getDirection_facing().equals("NE")){
                directionNE.performClick();
            }
            if (data.getDirection_facing().equals("NW")){
                directionNW.performClick();
            }
            if (data.getDirection_facing().equals("SE")){
                directionSE.performClick();
            }
            String[] oeverloacking = data.getOverlooking().split(",");
            for (String s:oeverloacking){
                if (s.equals("Garden")){
                    overLookingGarden.performClick();
                }
                if (s.equals("Pool")){
                    overLookingPol.performClick();
                }
                if (s.equals("Main Road")){
                    overLookingMainRood.performClick();
                }
                if (s.equals("Not Available")){
                    overLookingNotAvailable.performClick();
                }
            }
            if (data.getParking_facility().equals("Covered")){
                parkingCovered.performClick();
            }
            if (data.getParking_facility().equals("Open")){
                parkingOpen.performClick();
            }
            if (data.getWater_availability().equals("24")){
                water24.performClick();
            }else {
                waterAdd.setBackgroundResource(active);
                TextView textView = (TextView) waterAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Iwater=data.getWater_availability();
            }
            if (data.getWater_source().equals("Municipal")){
                waterSMunicipal.performClick();
            }
            if (data.getWater_source().equals("Burwell")){
                waterSBourwell.performClick();
            }else {
                waterSADD.setBackgroundResource(active);
                TextView textView = (TextView) waterSADD.getChildAt(0);
                textView.setText(data.getWater_source());
                ISwater=data.getWater_source();
            }
            if (data.getElectricity_availability().equals("24")){
                electric24.performClick();
            }else {
                electicAdd.setBackgroundResource(active);
                TextView textView = (TextView) electicAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Ielectric=data.getWater_availability();
            }
            if (data.getPower_backup().equals("Full")){
                powerFull.performClick();
            }
            if (data.getPower_backup().equals("None")){
                powerNone.performClick();
            }else {
                powerPartial.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorMarble.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorConcrete.performClick();
            }else {
                floorConcrete.setBackgroundResource(active);
                TextView textView = (TextView) floorConcrete.getChildAt(0);
                textView.setText(data.getFloor_details());
                Ifloor=data.getFloor_details();
            }
            if (data.getAssured_returns().equals("Yes")){
                RadioButton radioButton = (RadioButton) assuredReturns.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) assuredReturns.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getCurrently_leased_out().equals("Yes")){
                RadioButton radioButton = (RadioButton) leesed.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) leesed.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getCenteral_air_conditioning().equals("Yes")){
                RadioButton radioButton = (RadioButton) air.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) air.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getOxygen_duct().equals("Yes")){
                RadioButton radioButton = (RadioButton) oxygen.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) oxygen.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getUsp().equals("Yes")){
                RadioButton radioButton = (RadioButton) usp.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) usp.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getFire_safety().equals("Exi")){
                fireExi.performClick();
            }
            if (data.getFire_safety().equals("Sensor")){
                fireSensor.performClick();
            }
            if (data.getFire_safety().equals("Sprinkles")){
                sprinkels.performClick();
            }
            if (data.getFire_safety().equals("House")){
                fireHouse.performClick();
            }
            if (data.getNumber_of_staircase().equals("1")){
                stair1.performClick();
            }
            if (data.getNumber_of_staircase().equals("2")){
                stair2.performClick();
            }
            if (data.getNumber_of_staircase().equals("3")){
                stair3.performClick();
            }
            if (data.getNumber_of_staircase().equals("4")){
                stair4.performClick();
            }
            if (data.getPrivete_parking_in_basement_four_wheeler().equals("Yes")|data.getPrivete_parking_in_basement_two_wheeler().equals("Yes")){
                RadioButton radioButton = (RadioButton) parkingGruopRadio.getChildAt(0);
                radioButton.setChecked(true);
            }
            if (data.getPrivete_parking_in_outside_four_wheeler().equals("Yes")|data.getPrivete_parking_in_outside_two_wheeler().equals("Yes")){
                RadioButton radioButton = (RadioButton) parkingGruopRadio.getChildAt(1);
                radioButton.setChecked(true);
            }
            if (data.getPublic_parking_four_wheeler().equals("Yes")|data.getPublic_parking_two_wheeler().equals("Yes")){
                RadioButton radioButton = (RadioButton) parkingGruopRadio.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getLeed_certification().equals("Yes")){
                certifiedyes.performClick();
            }else {
                certifiedno.performClick();
            }
            if (data.getOffice_noc_certified().equals("Yes")|
                    data.getShop_noc_certified().equals("Yes")|
                    data.getShowroom_noc_certified().equals("Yes")|
                    data.getWarehouse_noc_certified().equals("Yes")){
                nocYes.performClick();
            }else {
                nocNo.performClick();
            }
            if (data.getOffice_previous_used_for().equals("CA")){
                caOffice.performClick();
            }
            if (data.getOffice_previous_used_for().equals("Backend")){
                backendOffice.performClick();
            }
            if (data.getOffice_previous_used_for().equals("Fronted")){
                frontedOffice.performClick();
            }
            if (data.getOffice_previous_used_for().equals("Trader")){
                traderOffice.performClick();
            }else {
                addOffice.setBackgroundResource(active);
                TextView textView = (TextView) addOffice.getChildAt(0);
                textView.setText(data.getOffice_previous_used_for());
                Ioffice=data.getOffice_previous_used_for();
            }
            expectedIn.setText(data.getExpectedprice());
            pricePerSqIn.setText(data.getPrice_per_sq_ft());
            bookingAmmIn.setText(data.getBooking_amount());
            expectedRentalIn.setText(data.getExpected_rental());
            membershipIn.setText(data.getRental_membership());
            ideal_for_business_id.setText(data.getIdea_for_businesses());
            faciingIn.setText(data.getFacing_road());
        }
        if (PLOTCOMERCIAL.getVisibility()==View.VISIBLE){
            if (data.getPrice_negotiable().equals("Yes")){
                NegotableRadio.setChecked(true);
            }
            expectedIn.setText(data.getExpectedprice());
            pricePerSqIn.setText(data.getPrice_per_sq_ft());
            bookingAmmIn.setText(data.getBooking_amount());
            expectedRentalIn.setText(data.getExpected_rental());
            membershipIn.setText(data.getRental_membership());
            ideal_for_business_id.setText(data.getIdea_for_businesses());
            faciingIn.setText(data.getFacing_road());
            if (data.getDirection_facing().equals("South")){
                directionSouth.performClick();
            }
            if (data.getDirection_facing().equals("North")){
                directionNorth.performClick();
            }
            if (data.getDirection_facing().equals("East")){
                directionEast.performClick();
            }
            if (data.getDirection_facing().equals("West")){
                directionWest.performClick();
            }
            if (data.getDirection_facing().equals("NE")){
                directionNE.performClick();
            }
            if (data.getDirection_facing().equals("NW")){
                directionNW.performClick();
            }
            if (data.getDirection_facing().equals("SE")){
                directionSE.performClick();
            }
            String[] oeverloacking = data.getOverlooking().split(",");
            for (String s:oeverloacking){
                if (s.equals("Garden")){
                    overLookingGarden.performClick();
                }
                if (s.equals("Pool")){
                    overLookingPol.performClick();
                }
                if (s.equals("Main Road")){
                    overLookingMainRood.performClick();
                }
                if (s.equals("Not Available")){
                    overLookingNotAvailable.performClick();
                }
            }
            if (data.getParking_facility().equals("Covered")){
                parkingCovered.performClick();
            }
            if (data.getParking_facility().equals("Open")){
                parkingOpen.performClick();
            }
            if (data.getWater_availability().equals("24")){
                water24.performClick();
            }else {
                waterAdd.setBackgroundResource(active);
                TextView textView = (TextView) waterAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Iwater=data.getWater_availability();
            }
            if (data.getWater_source().equals("Municipal")){
                waterSMunicipal.performClick();
            }
            if (data.getWater_source().equals("Burwell")){
                waterSBourwell.performClick();
            }else {
                waterSADD.setBackgroundResource(active);
                TextView textView = (TextView) waterSADD.getChildAt(0);
                textView.setText(data.getWater_source());
                ISwater=data.getWater_source();
            }
            if (data.getElectricity_availability().equals("24")){
                electric24.performClick();
            }else {
                electicAdd.setBackgroundResource(active);
                TextView textView = (TextView) electicAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Ielectric=data.getWater_availability();
            }
            if (data.getPower_backup().equals("Full")){
                powerFull.performClick();
            }
            if (data.getPower_backup().equals("None")){
                powerNone.performClick();
            }else {
                powerPartial.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorMarble.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorConcrete.performClick();
            }else {
                floorConcrete.setBackgroundResource(active);
                TextView textView = (TextView) floorConcrete.getChildAt(0);
                textView.setText(data.getFloor_details());
                Ifloor=data.getFloor_details();
            }
        }
        if (OTHER.getVisibility()==View.VISIBLE){
            if (data.getAssured_returns().equals("Yes")){
                RadioButton radioButton = (RadioButton) assuredReturns.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) assuredReturns.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getCurrently_leased_out().equals("Yes")){
                RadioButton radioButton = (RadioButton) leesed.getChildAt(1);
                radioButton.setChecked(true);
            }else {
                RadioButton radioButton = (RadioButton) leesed.getChildAt(2);
                radioButton.setChecked(true);
            }
            if (data.getAdditional_price().equals("PLC")){
                PLC_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Car Parking")){
                CARP_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Club Membership")){
                CLUB_R.setChecked(true);
            }
            if (data.getAdditional_price().equals("Stump Duty Registration")){
                STUMP_R.setChecked(true);
            }
            if (data.getPrice_negotiable().equals("Yes")){
                NegotableRadio.setChecked(true);
            }
            expectedIn.setText(data.getExpectedprice());
            pricePerSqIn.setText(data.getPrice_per_sq_ft());
            bookingAmmIn.setText(data.getBooking_amount());
            expectedRentalIn.setText(data.getExpected_rental());
            membershipIn.setText(data.getRental_membership());
            ideal_for_business_id.setText(data.getIdea_for_businesses());
            faciingIn.setText(data.getFacing_road());
            String[] poja = data.getAdditional_rooms().split(",");
            for (String s:poja){
                if (s.equals("Pooja")){
                    poojaRoom.performClick();
                }
                if (s.equals("Study")){
                    studyRoom.performClick();
                }
                if (s.equals("Store")){
                    storeRoom.performClick();
                }
                if (s.equals("Sarvant")){
                    sarvantRoom.performClick();
                }
            }
            if (data.getDirection_facing().equals("South")){
                directionSouth.performClick();
            }
            if (data.getDirection_facing().equals("North")){
                directionNorth.performClick();
            }
            if (data.getDirection_facing().equals("East")){
                directionEast.performClick();
            }
            if (data.getDirection_facing().equals("West")){
                directionWest.performClick();
            }
            if (data.getDirection_facing().equals("NE")){
                directionNE.performClick();
            }
            if (data.getDirection_facing().equals("NW")){
                directionNW.performClick();
            }
            if (data.getDirection_facing().equals("SE")){
                directionSE.performClick();
            }
            String[] oeverloacking = data.getOverlooking().split(",");
            for (String s:oeverloacking){
                if (s.equals("Garden")){
                    overLookingGarden.performClick();
                }
                if (s.equals("Pool")){
                    overLookingPol.performClick();
                }
                if (s.equals("Main Road")){
                    overLookingMainRood.performClick();
                }
                if (s.equals("Not Available")){
                    overLookingNotAvailable.performClick();
                }
            }
            if (data.getParking_facility().equals("Covered")){
                parkingCovered.performClick();
            }
            if (data.getParking_facility().equals("Open")){
                parkingOpen.performClick();
            }
            if (data.getResidentiallift().equals("Yes")){
                liftYes.performClick();
            }else {
                liftNo.performClick();
            }
            if (data.getWater_availability().equals("24")){
                water24.performClick();
            }else {
                waterAdd.setBackgroundResource(active);
                TextView textView = (TextView) waterAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Iwater=data.getWater_availability();
            }
            if (data.getWater_source().equals("Municipal")){
                waterSMunicipal.performClick();
            }
            if (data.getWater_source().equals("Burwell")){
                waterSBourwell.performClick();
            }else {
                waterSADD.setBackgroundResource(active);
                TextView textView = (TextView) waterSADD.getChildAt(0);
                textView.setText(data.getWater_source());
                ISwater=data.getWater_source();
            }
            if (data.getElectricity_availability().equals("24")){
                electric24.performClick();
            }else {
                electicAdd.setBackgroundResource(active);
                TextView textView = (TextView) electicAdd.getChildAt(0);
                textView.setText(data.getWater_availability());
                Ielectric=data.getWater_availability();
            }
            if (data.getPower_backup().equals("Full")){
                powerFull.performClick();
            }
            if (data.getPower_backup().equals("None")){
                powerNone.performClick();
            }else {
                powerPartial.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorMarble.performClick();
            }
            if (data.getFloor_details().equals("Marble")){
                floorConcrete.performClick();
            }else {
                floorConcrete.setBackgroundResource(active);
                TextView textView = (TextView) floorConcrete.getChildAt(0);
                textView.setText(data.getFloor_details());
                Ifloor=data.getFloor_details();
            }
        }
    }
    private void setTextInEnglish(Editable s) {
        String ss =  s.toString();
        priceInEnglish.setText(numberToWords.main(new String[]{ss}));
    }
    private void handleViews() {
        if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Flat/Apartment")|PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("House")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Studio Apartment")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Villa")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Builder Floor Apartment")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Plot Residential")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Penthouse")
                |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Duplex")) {

            RESI.setVisibility(View.VISIBLE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);


        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office Space")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Shop")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_OFFICE_PRIVIOUS.setVisibility(View.GONE);
            COM_LEED.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
            COM_IDEAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office IT Park/Shez")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);

        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Showroom")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
            COM_IDEAL.setVisibility(View.GONE);

        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Factory")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_OFFICE_PRIVIOUS.setVisibility(View.GONE);
            COM_LEED.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
            COM_IDEAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Warehouse/Godown")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_LEED.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Plot Commercial")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.VISIBLE);
            OTHER.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Building")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_OFFICE_PRIVIOUS.setVisibility(View.GONE);
            COM_LEED.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
            COM_IDEAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Shed")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.VISIBLE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
            COM_OFFICE_PRIVIOUS.setVisibility(View.VISIBLE);
            COM_LEED.setVisibility(View.GONE);
            COM_ADDTIONAL.setVisibility(View.GONE);
            COM_IDEAL.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Agriculture Land")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
            OTH_FLOOR.setVisibility(View.GONE);
            OTH_IDEAL.setVisibility(View.GONE);
            OTH_PARK_LIFT.setVisibility(View.GONE);
            OTH_AD.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Commercial Land")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
            OTH_FLOOR.setVisibility(View.GONE);
            OTH_IDEAL.setVisibility(View.VISIBLE);
            OTH_PARK_LIFT.setVisibility(View.GONE);
            OTH_AD.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Farm House")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
            OTH_IDEAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Hotel/Resort")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
            OTH_IDEAL.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Banquet/Guest House")) {
            RESI.setVisibility(View.GONE);
            COMMRCIAL.setVisibility(View.GONE);
            PLOTCOMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
            OTH_IDEAL.setVisibility(View.GONE);
        }
    }
    ArrayList<FloorPojo> floorPojos = new ArrayList<>();
    private void addItems(String text) {
        FloorPojo floorPojo = new FloorPojo();
        floorPojo.is_selected = false;
        floorPojo.floor_string = text;
        floorPojos.add(floorPojo);
        LandZone.add(floorPojo);
        SpecialLand.add(floorPojo);
        LEED.add(floorPojo);
    }
    private void pricingFeatures() {
        if (ideal_for_business_id != null){
            if (ideal_for_business_id.getText()!=null){
            Iideal = ideal_for_business_id.getText().toString();
            }
        }
        String error = "This is required";
        ArrayList<String> dummy = new ArrayList<>();
        dummy.add("No Data");
        dummy.add("No data");
        PostPricingModal postPricingModal =  new PostPricingModal();
        postPricingModal.setUser_id(String.valueOf(PrefMananger.GetLoginData(context).getUserId()));
        postPricingModal.setProperty_id(PrefMananger.getString(context,PrefMananger.PROPERTY_ID));

        postPricingModal.setExpectedprice(expectedIn.getText().toString());
        postPricingModal.setPrice_per_sq_ft(pricePerSqIn.getText().toString());
        if (NegotableRadio.isChecked()){
        postPricingModal.setPrice_negotiable("True");
        }else {
        postPricingModal.setPrice_negotiable("False");
        }
        postPricingModal.setOther_charge("No data");
        postPricingModal.setMaintenance_charge(maintenceIn.getText().toString());
        postPricingModal.setCharge_type(IpopupMaintainace);
        postPricingModal.setBooking_amount(bookingAmmIn.getText().toString());
        postPricingModal.setExpected_rental(expectedRentalIn.getText().toString());
        postPricingModal.setRental_membership(membershipIn.getText().toString());
        postPricingModal.setAnnual_dues_payble("no data");
        postPricingModal.setIdea_for_businesses(Iideal);
        postPricingModal.setIdeal_for_use(Iideal);
        postPricingModal.setOffice_previous_used_for(Ioffice);
        postPricingModal.setParking_capacity(Iparking);
        postPricingModal.setWater_availability(Iwater);
        postPricingModal.setWater_source(ISwater);
        postPricingModal.setFacing_road_ft(IpopupFace);
        postPricingModal.setBest_suitable_for_crop("No data");
        postPricingModal.setCovered_parking_capacity_two_wheeler(IcoverdParking2);
        postPricingModal.setCovered_parking_capacity_four_wheeler(Icoveredparking4);
        postPricingModal.setOpen_parking_capacity_two_wheeler(IopenParking2);
        postPricingModal.setOpen_parking_capacity_four_wheeler(IopenParking4);
        if (COMMRCIAL.getVisibility()==View.VISIBLE){
            if (PPB.isChecked()){
                postPricingModal.setPrivete_parking_in_basement_four_wheeler("True");
                postPricingModal.setPrivete_parking_in_basement_two_wheeler("True");
            }else {
                postPricingModal.setPrivete_parking_in_basement_two_wheeler("False");
                postPricingModal.setPrivete_parking_in_basement_four_wheeler("False");
            }
            if (PPO.isChecked()){
                postPricingModal.setPrivete_parking_in_outside_four_wheeler("true");
                postPricingModal.setPrivete_parking_in_outside_two_wheeler("true");
            }else {
                postPricingModal.setPrivete_parking_in_outside_four_wheeler("false");
                postPricingModal.setPrivete_parking_in_outside_two_wheeler("false");
            }
            if (PP.isChecked()){
                postPricingModal.setPublic_parking_four_wheeler("true");
                postPricingModal.setPublic_parking_two_wheeler("true");
            }else {
                postPricingModal.setPublic_parking_two_wheeler("False");
                postPricingModal.setPublic_parking_four_wheeler("False");
            }
        }
        if (Ileft.equals("R")){
            postPricingModal.setResidentiallift("Yes");
            postPricingModal.setCommerciallift("NO");
            postPricingModal.setPassengerlift("NO");
            postPricingModal.setServicelift("NO");
        }
        if (Ileft.equals("C")){
            postPricingModal.setResidentiallift("No");
            postPricingModal.setCommerciallift("Yes");
            postPricingModal.setPassengerlift("NO");
            postPricingModal.setServicelift("NO");
        }
        if (Ileft.equals("P")){
            postPricingModal.setResidentiallift("No");
            postPricingModal.setCommerciallift("NO");
            postPricingModal.setPassengerlift("Yes");
            postPricingModal.setServicelift("NO");
        }
        if (Ileft.equals("S")){
            postPricingModal.setResidentiallift("NO");
            postPricingModal.setCommerciallift("NO");
            postPricingModal.setPassengerlift("NO");
            postPricingModal.setServicelift("Yes");
        }
        if (Ileft!=null){
            postPricingModal.setResidentiallift("NO");
            postPricingModal.setCommerciallift("NO");
            postPricingModal.setPassengerlift("NO");
            postPricingModal.setServicelift("no");
        }
        postPricingModal.setOccupancy_certificate(Icertified);
        postPricingModal.setOffice_noc_certified(Inoc);
        postPricingModal.setShowroom_noc_certified(Inoc);
        postPricingModal.setWarehouse_noc_certified(Inoc);
        postPricingModal.setShop_noc_certified(Inoc);
        postPricingModal.setLeed_certification(Ipopupled);
        postPricingModal.setElectricity_availability(Ielectric);
        postPricingModal.setPower_backup(Ipower);
        postPricingModal.setFloor_details(Ifloor);
        postPricingModal.setLand_zone(Ipopupland);
        postPricingModal.setSpecial_zone(IpopupSpecial);
        postPricingModal.setAssured_returns(IAssured);
        postPricingModal.setCurrently_leased_out(Ileased);
        postPricingModal.setProperty_leased(Ileased);
        postPricingModal.setLeased_on(Ileased);
        postPricingModal.setCurrent_business_sector("No data");
        postPricingModal.setBusiness_since("No data");
        postPricingModal.setApproval_date("No data");
        postPricingModal.setCenteral_air_conditioning(Iair);
        postPricingModal.setOxygen_duct(Ioxygen);
        postPricingModal.setUsp(IUsp);
        postPricingModal.setFire_safety(Ifire);
        postPricingModal.setIdeal_businesses(Iideal);
        postPricingModal.setNumber_of_staircase(Istairs);
        postPricingModal.setDirection_facing(Idrection);
        postPricingModal.setFacing_road(Ioverlooking);
        ArrayList<String> dummys =  new ArrayList<>();
        dummys.add("No data");
        postPricingModal.setAdditional_rooms(Rooms);
        postPricingModal.setAdditional_price(dummys);
        postPricingModal.setOverlooking(dummys);
        postPricingModal.setParking_facility(dummys);


        ProgressDialog pd =  new ProgressDialog(getContext());
        pd.setMessage("Saving..");
        pd.show();

//        ApiExecutor.getApiService().postPricing(postPricingModal).enqueue(new Callback<PostPricingModal>() {
//            @Override
//            public void onResponse(Call<PostPricingModal> call, retrofit2.Response<PostPricingModal> response) {
//                pd.cancel();
//                aichat ai = new aichat();
//                ai.setText("TAB");
//                ai.setValue("3");
//                ai.setValues("tb");
//                liveCommnication.setText(ai);
//                Toast.makeText(getContext(), "happen ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<PostPricingModal> call, Throwable t) {
//                pd.cancel();
//                Toast.makeText(getContext(), "Some error happen retry2", Toast.LENGTH_SHORT).show();
//            }
//        });
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_PROPERY_PRICING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject o =  new JSONObject(response);
                    String s = o.getString("status");
                    if (s.equals("1")){
                        if (isUpdating){
                            if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)!=null){
                                aichat ai = new aichat();
                                ai.setText("TAB");
                                ai.setValue("3");
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
                            ai.setValue("3");
                            ai.setValues("tb");
                            liveCommnication.setText(ai);
                        }
                        pd.cancel();

                        Toast.makeText(getContext(), "happen ", Toast.LENGTH_SHORT).show();
                    }else {
                        pd.cancel();
                        Toast.makeText(getContext(), "Some error happen retry1", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.cancel();
                    Toast.makeText(getContext(), "Some error happen retry2", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
//                aichat ai = new aichat();
//                ai.setText("TAB");
//                ai.setValue("3");
//                ai.setValues("tb");
//                liveCommnication.setText(ai);
                Toast.makeText(getContext(), "Some error happen retry3", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                Field[] fields = postPricingModal.getClass().getDeclaredFields();
                for (Field f:fields){
                    f.setAccessible(true);
                    try {
                        if (f.get(postPricingModal).getClass().isArray()){
                            ArrayList<String> arrayList = (ArrayList<String>) f.get(postPricingModal);
                            if (!arrayList.isEmpty()){
                                hashMap.put(f.getName()+"[]", arrayList.toString());
                            }else {
                                hashMap.put(f.getName()+"[]", dummys.toString());
                            }
                        }else {
                            String value = (String) f.get(postPricingModal);
                            if (value==null|value==""|value==" "){
                                value="UnSelected";
                            }
                            hashMap.put(f.getName(), value);
                        }
//                        if (f.get(postPricingModal).getClass().getSimpleName()==String){
//
//                        }


                    }catch (Exception e){

                    }

                }
                return hashMap;
            }
        };
        Volley.newRequestQueue(getActivity()).add(request);
    }
    public void date1() {
        new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        DATE = sdf.format(calendar.getTime());
    }
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
                        PrefMananger.FeatureFarmSet(getContext(),Property_Id);
                        PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);
                        handleViews();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==1){
                flors = data.getStringArrayListExtra("data");
                Converter converter = new Converter(getContext());
                Ifloor=converter.getSrtingArrey(flors);
            }else {
                floorSeeAll.setBackgroundResource(defaults);
            }
        }
    }
}

