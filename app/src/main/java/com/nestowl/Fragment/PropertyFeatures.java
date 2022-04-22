package com.nestowl.Fragment;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.AddBedroomsInfo;
import com.nestowl.CommenDialog.AddDiolog;
import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiService;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.PostPropertyModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.FurnishedStatus;
import com.nestowl.brokerapp.R;
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
public class PropertyFeatures extends Fragment {
    Calendar calendar;
    ApiService apiService;
    FrameLayout bedrooms1,bedroom2,bedroom3,bedrromadd,masterBedroomGraound,masterBedroomFirst,belcnies1,belconies2,belconies3,
    belconies4,belconiesAdd,falts1,flats2,flats3,flats4,flats5,flatsAdd,open1,open2,open3,openAdd,furnished,furnishedsSemi,furnishedUn,
    bathroom0,batroom1,bathroom2,bathroom3,bathroomAdd,possUnder,possRedy,transResale,transNew,

    open4,anyConYes,anyConNo,boundryYes,boundryNo,gatedYes,gatedNo,

    sWash1,sWash2,sWash3,sWashAdd,pWash1,pWash2,pWash3,pWashAdd,pantryDry,pantryWet,pantryNo,confYes,confNo,receptionYes,receptionNo,
    vSpaceYes,vSpaceNo,buildingClass,

    bedroom4,bedroom5,bathroom4,sWash4,

    floorSet,carpetSet,bultupSet,superSet,plotAreaSet,allowedSet,age1,age5,age10,ageAdd;

    EditText floorNoIn,carpetIn,builtUpIn,superIn,plotAreain,plotLenIn,plotBreIn,shopNoIn,allowedContsIn,opensidesIn,minSeatsIn,maxSeatsIn,
    noOfCabinIn,noOfMeetingRoomsIn,venueIn,totalRoomsIn,unDateIn;

    LinearLayout MAINRESI,PLOTRESI,COMERCIAL,OTHER,bedroomsL,masterL,noflorL,faltonfloorL,floorToMeL,furnishedL,washroomsL,swashroomL,pantryL,addareaL,plotareaL,confresncL,receptionL,officeL,doorL,
            opensideL,plotAreaL,possL,possUnderL,possRedyL,shopL,allowedL,bathRoomL,pantrySetL,groundL,venueL,vspaceL,bclassL,transL,roomsL, allfloorToME,
            becloniesL,BEDROOMS,venuePopup;
    CheckBox checkBox;
    TextView  allowedContT,venueType;
    int Ibedrooms,Imasterbedroom,Ibelconies,Iflats,Iage,Iopen,Ifurnished,Ibathrooms,Iposs,Itrance,IanyCon,Iboundry,Igated,Iswash,Ipwash,Ipantry,Iconf,Ireception,IflorSet,IcarpetSet,Ibuiltupset,Isuperset,
    Iallowed,Ivspace,CallBy;
    ArrayList<String> arrayList,Length,Bre,Hall;
    HorizontalScrollView scrollView,possRedyS;
    CardView contineue;
    String error ,cafetareaData, shareCapfetarea,Property_Id,user_id,Svenue;
    LiveCommnication liveCommnication;
    ImageView date;
    String DATE;
    boolean isUpdating;


    public PropertyFeatures() {
        // Required empty public constructor
    }

    Context context;
    Activity activity;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property_features, container, false);
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
        calendar = Calendar.getInstance();
        MAINRESI=view.findViewById(R.id.POST_PRO_RESI_MAIN);
        PLOTRESI=view.findViewById(R.id.POST_PRO_RESI_RESI_PLOT);
        COMERCIAL=view.findViewById(R.id.POST_PRO_COM_MAIN);
        OTHER=view.findViewById(R.id.POST_PRO_OTH_MAIN);
        contineue=view.findViewById(R.id.POST_PHOTO_CONTINEUE);
        Ibedrooms=0;
        Imasterbedroom=0;
        Ibelconies=0;
        Iflats=0;
        Iopen=0;
        Ifurnished=0;
        Ibathrooms=0;
        Iposs=0;
        Itrance=0;
        IanyCon=0;
        Iboundry=0;
        Igated=0;
        Iswash=0;
        Ipwash=0;
        Ipantry=0;
        Iconf=0;
        Ireception=0;
        IflorSet=0;
        IcarpetSet=0;
        Ibuiltupset=0;
        Isuperset=0;
        Iallowed=0;
        Ivspace=0;
        CallBy=0;
        Iage=0;
        error="This Field is required";
        arrayList=new ArrayList<>();
        handlerVisiblesForm();
        handleGraphics();
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        if (MAINRESI.getVisibility()==View.VISIBLE){
            date=view.findViewById(R.id.POST_PRO_RESI_DATE);
//            frames
            bedrooms1=view.findViewById(R.id.POST_PRO_RESI_M_BED_1);
            bedroom2=view.findViewById(R.id.POST_PRO_RESI_M_BED_2);
            bedroom3=view.findViewById(R.id.POST_PRO_RESI_M_BED_3);
            bedrromadd=view.findViewById(R.id.POST_PRO_RESI_M_BED_ADD);
            BEDROOMS=view.findViewById(R.id.POST_PRO_R_BEDROMS);

            masterBedroomGraound=view.findViewById(R.id.POST_PRO_RESI_M_MASTER_BED_GROUND);
            masterBedroomFirst=view.findViewById(R.id.POST_PRO_RESI_M_MASTER_BEDROOM_FIRST);

            belcnies1=view.findViewById(R.id.POST_PRO_RESI_M_BEL_1);
            belconies2=view.findViewById(R.id.POST_PRO_RESI_M_BEL_2);
            belconies3=view.findViewById(R.id.POST_PRO_RESI_M_BEL_3);
            belconies4=view.findViewById(R.id.POST_PRO_RESI_M_BELL_4);
            belconiesAdd=view.findViewById(R.id.POST_PRO_RESI_M_BEL_ADD);

            falts1=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_1);
            flats2=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_2);
            flats3=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_3);
            flats4=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_4);
            flats5=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_5);
            flatsAdd=view.findViewById(R.id.POST_PRO_RESI_M_FLATS_ADD);

            open1=view.findViewById(R.id.POST_PRO_RESI_M_OPEN_1);
            open2=view.findViewById(R.id.POST_PRO_RESI_M_OPEN_2);
            open3=view.findViewById(R.id.POST_PRO_RESI_M_OPEN_3);
            openAdd=view.findViewById(R.id.POST_PRO_RESI_M_OPEN_ADD);

            furnished=view.findViewById(R.id.POST_PRO_RESI_M_FURNISHED);
            furnishedsSemi=view.findViewById(R.id.POST_PRO_RESI_M_FURNISHED_SEMI);
            furnishedUn=view.findViewById(R.id.POST_PRO_RESI_M_FURNISHED_UN);

            batroom1=view.findViewById(R.id.POST_PRO_RESI_M_BATH_1);
            bathroom2=view.findViewById(R.id.POST_PRO_RESI_M_BATH_2);
            bathroom3=view.findViewById(R.id.POST_PRO_RESI_M_BATH_3);
            bathroomAdd=view.findViewById(R.id.POST_PRO_RESI_M_BATH_ADD);

            possUnder=view.findViewById(R.id.POST_PRO_RESI_M_POSSESSION_UN);
            possRedy=view.findViewById(R.id.POST_PRO_RESI_M_POOSESSION_RESY);

            age1=view.findViewById(R.id.POST_PRO_RESI_M_POSS_REDY_1);
            age5=view.findViewById(R.id.POST_PRO_RESI_M_POSS_REDY_5);
            age10=view.findViewById(R.id.POST_PRO_RESI_M_POSS_REDY_10);

            masterL=view.findViewById(R.id.POST_PRO_RESI_MASTER_BEDROOMS);
            opensideL=view.findViewById(R.id.POST_PRO_RESI_OPENSIDES);
            plotareaL=view.findViewById(R.id.POST_PRO_RESI_PLOTAREA);
            faltonfloorL=view.findViewById(R.id.POST_PRO_RESI_M_FLATONFLOR);
            bedroomsL=view.findViewById(R.id.POST_PRO_RESI_M_BEDROOMS);
            noflorL=view.findViewById(R.id.POST_PRO_RESI_M_NOOFFLOOR);
            transResale=view.findViewById(R.id.POST_PRO_RESI_M_TRANS_RESALE);
            transNew=view.findViewById(R.id.POST_PRO_RESI_M_TRANS_NEW);
            possRedyL=view.findViewById(R.id.POST_PRO_RESI_M_POSS_REDY_INN);
            possUnderL=view.findViewById(R.id.POST_PRO_RESI_M_POSS_UNDER_INS);
            scrollView=view.findViewById(R.id.POST_PRO_RESI_P_FRUNISHING);

//            inputs
            floorNoIn=view.findViewById(R.id.POST_PRO_RESI_M_FLOOR_NO_IN);
            carpetIn=view.findViewById(R.id.POST_PRO_RESI_M_CARPET_IN);
            builtUpIn=view.findViewById(R.id.POST_PRO_RESI_M_BULTUP_IN);
            superIn=view.findViewById(R.id.POST_PRO_RESI_M_SUPER_IN);
            plotAreain=view.findViewById(R.id.POST_PRO_RESI_M_PLOT_AREA_IN);
            plotLenIn=view.findViewById(R.id.POST_PRO_RESI_M_PLOT_LENGTH);
            plotBreIn=view.findViewById(R.id.POST_PRO_RESI_M_PLOT_WIGTH);


            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText d = view.findViewById(R.id.POST_PRO_RESI_M_POSS_UNDER_IN);
                    d.setText(date1());
                }
            });
//            cheackboxs
            floorToMeL=view.findViewById(R.id.POST_PRO_RESI_M_FLOR_TOME);
            allfloorToME=view.findViewById(R.id.POST_PRO_RESI_M_FLOR_TOME);
            checkBox=view.findViewById(R.id.POST_PRO_RESI_M_FLOR_CHECK);
//            popupfit
            floorSet=view.findViewById(R.id.POST_PRO_RESI_M_FLOOR_NO_SET);
            carpetSet=view.findViewById(R.id.POST_PRO_RESI_M_CARPET_SET);
            bultupSet=view.findViewById(R.id.POST_PRO_RESI_M_BULTUP_SET);
            superSet=view.findViewById(R.id.POST_PRO_RESI_M_SUPER_SET);
            plotAreaSet=view.findViewById(R.id.POST_PRO_RESI_M_PLOT_AREA_SET);

            setFarmView();
//            clicks
            bedrooms1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=1;
                    handleGraphics();
                }
            });
            bedroom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=2;
                    handleGraphics();
                }
            });
            bedroom3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=3;
                    handleGraphics();
                }
            });
            bedrromadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=4;
                    handleGraphics();
                }
            });

            masterBedroomGraound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Imasterbedroom=1;
                    handleGraphics();
                }
            });
            masterBedroomFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Imasterbedroom=2;
                    handleGraphics();
                }
            });

            belcnies1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=1;
                    handleGraphics();
                }
            });
            belconies2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=2;
                    handleGraphics();
                }
            });
            belconies3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=3;
                    handleGraphics();
                }
            });
            belconies4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=4;
                    handleGraphics();
                }
            });
            belconiesAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=5;
                    handleGraphics();
                }
            });

            falts1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=1;
                    handleGraphics();
                }
            });
            flats2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=2;
                    handleGraphics();
                }
            });
            flats3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=3;
                    handleGraphics();
                }
            });
            flats4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=4;
                    handleGraphics();
                }
            });
            flats5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=5;
                    handleGraphics();
                }
            });
            flatsAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iflats=6;
                    handleGraphics();
                }
            });

            open1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=1;
                    handleGraphics();
                }
            });
            open2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=2;
                    handleGraphics();
                }
            });
            open3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=3;
                    handleGraphics();
                }
            });
            openAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=4;
                    handleGraphics();
                }
            });

            furnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=1;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedsSemi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=2;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedUn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=3;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                }
            });

            batroom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=1;
                    handleGraphics();
                }
            });
            bathroom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=2;
                    handleGraphics();
                }
            });
            bathroom3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=3;
                    handleGraphics();
                }
            });
            bathroomAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=4;
                    handleGraphics();
                }
            });

            possUnder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=1;
                    handleGraphics();
                    possUnderL.setVisibility(View.VISIBLE);
                    possRedyL.setVisibility(View.GONE);
                }
            });
            possRedy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=2;
                    handleGraphics();
                    possRedyL.setVisibility(View.VISIBLE);
                    possUnderL.setVisibility(View.GONE);
                }
            });
            transResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=1;
                    handleGraphics();
                }
            });
            transNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=2;
                    handleGraphics();
                }
            });

            age1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=1;
                    handleGraphics();
                }
            });
            age5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=2;
                    handleGraphics();
                }
            });
            age10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=2;
                    handleGraphics();
                }
            });


            floorSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFloor();
                    CallBy=1;
                }
            });
            carpetSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMazore();
                    CallBy=1;
                   ;
                }
            });
            bultupSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMazore();
                    CallBy=2;
                }
            });
            superSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMazore();
                    CallBy=3;
                }
            });
            plotAreaSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMazore();
                    CallBy=4;
                }
            });
        }
        if (PLOTRESI.getVisibility()==View.VISIBLE) {
            allowedL=view.findViewById(R.id.POST_PRO_RESI_P_ALLOWED_SET);
            allowedContT=view.findViewById(R.id.POST_PRO_RESI_P_ALLOWED_TXT);
            plotAreaSet=view.findViewById(R.id.POST_PRO_RESI_P_PLOT_SET);
//            frames
            open1=view.findViewById(R.id.POST_PRO_RESI_P_OPEN_1);
            open2=view.findViewById(R.id.POST_PRO_RESI_P_OPEN_2);
            open3=view.findViewById(R.id.POST_PRO_RESI_P_OPEN_3);
            open4=view.findViewById(R.id.POST_PRO_RESI_P_OPEN_4);
            openAdd=view.findViewById(R.id.POST_PRO_RESI_P_OPEN_ADD);


            anyConYes=view.findViewById(R.id.POST_PRO_RESI_P_CONT_DONE_YES);
            anyConNo=view.findViewById(R.id.POST_PRO_RESI_P_CONT_DONE_NO);

            boundryYes=view.findViewById(R.id.POST_PRO_RESI_P_BOUNDRY_YES);
            boundryNo=view.findViewById(R.id.POST_PRO_RESI_P_BOUNDRY_NO);

            gatedYes=view.findViewById(R.id.POST_PRO_RESI_P_GATE_YES);
            gatedNo=view.findViewById(R.id.POST_PRO_RESI_P_GATED_NO);

            plotAreaSet=view.findViewById(R.id.POST_PRO_RESI_P_PLOT_SET);

            transResale=view.findViewById(R.id.POST_PRO_RESI_P_TRANS_RESALE);
            transNew=view.findViewById(R.id.POST_PRO_RESI_P_TRANS_NEW);

//            inputs
            plotAreain=view.findViewById(R.id.POST_PRO_RESI_P_PLOT_IN);
            plotLenIn=view.findViewById(R.id.POST_PRO_RESI_P_PLOT_LEN);
            plotBreIn=view.findViewById(R.id.POST_PRO_RESI_P_PLOT_WEG);

            setFarmView();
            open1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=1;
                    handleGraphics();
                }
            });
            open2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=2;
                    handleGraphics();
                }
            });
            open3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=3;
                    handleGraphics();
                }
            });
            open4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=6;
                    handleGraphics();
                }
            });
            openAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=4;
                    handleGraphics();
                }
            });

            anyConYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IanyCon=1;
                    handleGraphics();
                }
            });
            anyConNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IanyCon=2;
                    handleGraphics();
                }
            });

            boundryYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iboundry=1;
                    handleGraphics();
                }
            });
            boundryNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iboundry=2;
                    handleGraphics();
                }
            });

            gatedYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Igated=1;
                    handleGraphics();
                }
            });
            gatedNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Igated=2;
                    handleGraphics();
                }
            });

            transResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=1;
                    handleGraphics();
                }
            });
            transNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=2;
                    handleGraphics();
                }
            });

            plotAreaSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=4;
                    setMazore();
                }
            });
            allowedL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=1;
                    getAllowedCont();
                }
            });

        }
        if (COMERCIAL.getVisibility()==View.VISIBLE){
            date = view.findViewById(R.id.POST_PRO_COM_DATE);
//            frames
            furnished=view.findViewById(R.id.POST_PRO_COM_FURNISHED);
            furnishedsSemi=view.findViewById(R.id.POST_PRO_COM_FURNISHED_SEMI);
            furnishedUn=view.findViewById(R.id.POST_PRO_COM_FURNISHED_UN);

            bathroom0=view.findViewById(R.id.POST_PRO_COM_WASHROOM_0);
            batroom1=view.findViewById(R.id.POST_PRO_COM_WASHROOM_1);
            bathroom2=view.findViewById(R.id.POST_PRO_COM_WASHROOM_2);
            bathroom3=view.findViewById(R.id.POST_PRO_COM_WASHROOM_3);
            bathroomAdd=view.findViewById(R.id.POST_PRO_COM_WASHROOM_ADD);

            sWash1=view.findViewById(R.id.POST_PRO_COM_SWASH_1);
            sWash2=view.findViewById(R.id.POST_PRO_COM_SWASH_2);
            sWash3=view.findViewById(R.id.POST_PRO_COM_SWASH_3);
            sWashAdd=view.findViewById(R.id.POST_PRO_COM_SWASH_ADD);

            pWash1=view.findViewById(R.id.POST_PRO_COM_PWASH_1);
            pWash2=view.findViewById(R.id.POST_PRO_COM_PWASH_2);
            pWash3=view.findViewById(R.id.POST_PRO_COM_PWASH_3);
            pWashAdd=view.findViewById(R.id.POST_PRO_COM_PWASH_ADD);

            pantryDry=view.findViewById(R.id.POST_PRO_COM_PANTRY_DRY);
            pantryWet=view.findViewById(R.id.POST_PRO_COM_PANTRY_WET);
            pantryNo=view.findViewById(R.id.POST_PRO_COM_PANTRY_NONE);

            confYes=view.findViewById(R.id.POST_PRO_COM_CONF_YES);
            confNo=view.findViewById(R.id.POST_PRO_COM_CONF_NO);

            receptionYes=view.findViewById(R.id.POST_PRO_COM_RECEPTION_YES);
            receptionNo=view.findViewById(R.id.POST_PRO_COM__RECEPTION_NO);

            gatedYes=view.findViewById(R.id.POST_PRO_COM_DOORS_YES);
            gatedNo=view.findViewById(R.id.POST_PRO_COM_DOOR_NO);

            vSpaceYes=view.findViewById(R.id.POST_PRO_COM_VSPACE_YES);
            vSpaceNo=view.findViewById(R.id.POST_PRO_COM_VSPACE_NO);

            possUnder=view.findViewById(R.id.POST_PRO_COM_POSS_UNDER);
            possRedy=view.findViewById(R.id.POST_PRO_COM_POSS_REDY);

            transResale=view.findViewById(R.id.POST_PRO_COM_TRANS_RESALE);
            transNew=view.findViewById(R.id.POST_PRO_COM_TRANS_NEW);

            possRedyL=view.findViewById(R.id.POST_PRO_COM_POSS_REDY_SET);
            possUnderL=view.findViewById(R.id.POST_PRO_COM_POSS_UNDER_SET);
            scrollView=view.findViewById(R.id.POST_PRO_COM_FRUNISHING);

            age1=view.findViewById(R.id.POST_PRO_COM_POSS_AGE_1);
            age5=view.findViewById(R.id.POST_PRO_COM_POSS_AGE_5);
            age10=view.findViewById(R.id.POST_PRO_COM_POSS_AGE_10);

//            clicable frames
            floorSet=view.findViewById(R.id.POST_PRO_COM_SET_FLOOR);
            carpetSet=view.findViewById(R.id.POST_PRO_COM_CARPET_SET);
            bultupSet=view.findViewById(R.id.POST_PRO_COM_BULTUP_SET);
            superSet=view.findViewById(R.id.POST_PRO_COM_SUPER_SET);
            plotAreaSet=view.findViewById(R.id.POST_PRO_COM_PLOT_AREA_SET);
            buildingClass=view.findViewById(R.id.POST_PRO_COM_BUILDING_CLASS_SET);
            venuePopup=view.findViewById(R.id.POST_PRO_COM_VENUE_POPUP_OPEN);
//            farmhide
            shopL=view.findViewById(R.id.POST_PRO_COM_SHOP_NO);
            noflorL=view.findViewById(R.id.POST_PRO_COM_MAIN_NOFLOOR);
            furnishedL=view.findViewById(R.id.POST_PRO_COM_MAIN_FURNISHED);
            bathRoomL=view.findViewById(R.id.POST_PRO_COM_MAIN_WASHROOM);
            allowedL=view.findViewById(R.id.POST_PRO_COM_ALLOWED);
            swashroomL=view.findViewById(R.id.POST_PRO_COM_BATHROOMS);
            pantryL=view.findViewById(R.id.POST_PRO_COM_MAIN_PANTRY);
            addareaL=view.findViewById(R.id.POST_PRO_COM_MAIN_ADDAREA);
            plotAreaL=view.findViewById(R.id.POST_PRO_COM_MAIN_PLOTAREA);
            confresncL=view.findViewById(R.id.POST_PRO_COM_MAIN_CONFERENCE);
            receptionL=view.findViewById(R.id.POST_PRO_COM_MAIN_RECEPTION);
            officeL=view.findViewById(R.id.POST_PRO_COM_MAIN_OFFICE);
            doorL=view.findViewById(R.id.POST_PRO_COM_MAIN_DOORS);
            venueL=view.findViewById(R.id.POST_PRO_COM_MAIN_VENUE);
            vspaceL=view.findViewById(R.id.POST_PRO_COM_MAIN_VSPACE);
            bclassL=view.findViewById(R.id.POST_PRO_COM_MAIN_BCLASS);
            possL=view.findViewById(R.id.POST_PRO_COM_MAIN_POSS);
            transL=view.findViewById(R.id.POST_PRO_COM_MAIN_TRANS);
            allfloorToME=view.findViewById(R.id.POST_PRO_COM_FLOOR_TO_ME);


//            inputs
            floorNoIn=view.findViewById(R.id.POST_PRO_COM_NO_FLOOR_IN);
            shopNoIn=view.findViewById(R.id.POST_PRO_COM_SHOP_NO_IN);
            allowedContsIn=view.findViewById(R.id.POST_PRO_COM_FLOOR_C_IN);
            opensidesIn=view.findViewById(R.id.POST_PRO_COM_FLOOR_OPEN_IN);
            carpetIn=view.findViewById(R.id.POST_PRO_COM_CARPET_IN);
            builtUpIn=view.findViewById(R.id.POST_PRO_COM_BUILTUP_IN);
            superIn=view.findViewById(R.id.POST_PRO_COM_SUPER_IN);
            plotAreain=view.findViewById(R.id.POST_PRO_COM_PLOT_AREA_IN);
            plotLenIn=view.findViewById(R.id.POST_PRO_COM_PLOT_LEN_IN);
            plotBreIn=view.findViewById(R.id.POST_PRO_COM_PLOT_WED_IN);
            minSeatsIn=view.findViewById(R.id.POST_PRO_COM_OFFICE_MIN_SEATS);
            maxSeatsIn=view.findViewById(R.id.POST_PRO_COM_OFFICE_MAX_SEATS);
            noOfCabinIn=view.findViewById(R.id.POST_PRO_COM_OFFICE_CABIN_IN);
            noOfMeetingRoomsIn=view.findViewById(R.id.POST_PRO_COM_OFFICE_MEET_IN);
            venueType=view.findViewById(R.id.POST_PRO_COM_VENUE_TY_IN);
            setFarmView();
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText d = view.findViewById(R.id.POST_PRO_COM_POSS_SET_UNDER);
                    d.setText(date1());
                }
            });
//            click
            furnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=1;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedsSemi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=2;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedUn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=3;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                }
            });

            bathroom0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=1;
                    handleGraphics();
                }
            });
            batroom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=2;
                    handleGraphics();
                }
            });
            bathroom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=3;
                    handleGraphics();
                }
            });
            bathroomAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=4;
                    handleGraphics();
                }
            });

            sWash1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=1;
                    handleGraphics();
                }
            });
            sWash2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=2;
                    handleGraphics();
                }
            });
            sWash3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=3;
                    handleGraphics();
                }
            });
            sWashAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=4;
                    handleGraphics();
                }
            });

            pWash1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipwash=1;
                    handleGraphics();
                }
            });
            pWash2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipwash=2;
                    handleGraphics();
                }
            });
            pWash3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipwash=3;
                    handleGraphics();
                }
            });
            pWashAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipwash=4;
                    handleGraphics();
                }
            });

            pantryDry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipantry=1;
                    handleGraphics();
                }
            });
            pantryWet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipantry=2;
                    handleGraphics();
                }
            });
            pantryNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ipantry=3;
                    handleGraphics();
                }
            });

            confYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iconf=1;
                    handleGraphics();
                }
            });
            confNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iconf=2;
                    handleGraphics();
                }
            });

            receptionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ireception=1;
                    handleGraphics();
                }
            });
            receptionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ireception=2;
                    handleGraphics();
                }
            });

            gatedYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Igated=1;
                    handleGraphics();
                }
            });
            gatedNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Igated=2;
                    handleGraphics();
                }
            });

            vSpaceYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ivspace=1;
                    handleGraphics();
                }
            });
            vSpaceNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ivspace=2;
                    handleGraphics();
                }
            });

            possUnder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=1;
                    handleGraphics();
                }
            });
            possRedy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=2;
                    handleGraphics();
                }
            });

            transResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=1;
                    handleGraphics();
                }
            });
            transNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=2;
                    handleGraphics();
                }
            });

            floorSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=1;
                    getFloor();
                }
            });
            carpetSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=1;
                    setMazore();
                }
            });
            bultupSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=2;
                    setMazore();
                }
            });
            superSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=3;
                    setMazore();
                }
            });
            plotAreaSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=4;
                    setMazore();
                }
            });
            buildingClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    classSet();
                    CallBy=1;
                }
            });

            age1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=1;
                    handleGraphics();
                }
            });
            age5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=2;
                    handleGraphics();
                }
            });
            age10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=3;
                    handleGraphics();
                }
            });
            venuePopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typeData();
                }
            });


        }
        if (OTHER.getVisibility()==View.VISIBLE){
            date = view.findViewById(R.id.POST_PRO_OTH_DATE);
//            frames
            bedrooms1=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_1);
            bedroom2=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_2);
            bedroom3=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_3);
            bedroom4=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_4);
            bedroom5=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_5);
            bedrromadd=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS_ADD);

            belcnies1=view.findViewById(R.id.POST_PRO_OTH_BALCONIES_1);
            belconies2=view.findViewById(R.id.POST_PRO_OTH_BALCONIES_2);
            belconies3=view.findViewById(R.id.POST_PRO_OTH_BALCONIES_3);
            belconies4=view.findViewById(R.id.POST_PRO_OTH_BALCONIES_4);
            belconiesAdd=view.findViewById(R.id.POST_PRO_OTH_BALCONIES_ADD);

            open1=view.findViewById(R.id.POST_PRO_OTH_SIDES_1);
            open2=view.findViewById(R.id.POST_PRO_OTH_SIDES_2);
            open3=view.findViewById(R.id.POST_PRO_OTH_SIDES_3);
            openAdd=view.findViewById(R.id.POST_PRO_OTH_SIDES_ADD);

            furnished=view.findViewById(R.id.POST_PRO_OTH_FURNISHED_F);
            furnishedsSemi=view.findViewById(R.id.POST_PRO_OTH_FURNISHED_SF);
            furnishedUn=view.findViewById(R.id.POST_PRO_OTH_FURNISHED_UF);

            batroom1=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_1);
            bathroom2=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_2);
            bathroom3=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_3);
            bathroom4=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_4);
            bathroomAdd=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_ADD);

            sWash1=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_SW_1);
            sWash2=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_SW_2);
            sWash3=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_SW_3);
            sWash4=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_SW_4);
            sWashAdd=view.findViewById(R.id.POST_PRO_OTH_WASHROOM_SW_ADD);

            possRedyS=view.findViewById(R.id.POST_PRO_OTH_AGE);
            possUnderL=view.findViewById(R.id.POST_PRO_OTH_POSS_UNDER_SET);
            scrollView=view.findViewById(R.id.POST_PRO_OTH_FURNISHED_SELECTED);

//            clicable frames
            carpetSet=view.findViewById(R.id.POST_PRO_OTH_CARPET_SET);
            superSet=view.findViewById(R.id.POST_PRO_OTH_SUPER_SET);
            bultupSet=view.findViewById(R.id.POST_PRO_OTH_BUILTUP_SET);
            plotAreaSet=view.findViewById(R.id.POST_PRO_OTH_PLOT_AREA_SET);


            possUnder=view.findViewById(R.id.POST_PRO_OTH_POSS_UC);
            possRedy=view.findViewById(R.id.POST_PRO_OTH_POSS_RM);

            transResale=view.findViewById(R.id.POST_PRO_OTH_TRANS_RESALE);
            transNew=view.findViewById(R.id.POST_PRO_OTH_TRANS_NEW);
//            hiden
            roomsL=view.findViewById(R.id.POST_PRO_OTH_MAIN_ROOMS);
            bedroomsL=view.findViewById(R.id.POST_PRO_OTH_BEDROOMS);
            becloniesL=view.findViewById(R.id.POST_PRO_OTH_MAIN_BELCO);
            noflorL=view.findViewById(R.id.POST_PRO_OTH_MAIN_FLOOR);
            opensideL=view.findViewById(R.id.POST_PRO_OTH_MAIN_OPENSIDE);
            furnishedL=view.findViewById(R.id.POST_PRO_OTH_MAIN_FURNISED);
            bathRoomL=view.findViewById(R.id.POST_PRO_OTH_MAIN_WASH);
            swashroomL=view.findViewById(R.id.POST_PRO_OTH_MAIN_SWASH);
            washroomsL=view.findViewById(R.id.POST_PRO_OTH_MAIN_SWASH);
            addareaL=view.findViewById(R.id.POST_PRO_OTH_MAIN_CBS);
            plotAreaL=view.findViewById(R.id.POST_PRO_OTH_MAIN_PLOTAREA);
            possL=view.findViewById(R.id.POST_PRO_OTH_MAIN_POS);
            transL=view.findViewById(R.id.POST_PRO_OTH_MAIN_TRANSE);
            age1=view.findViewById(R.id.POST_PRO_OTH_AGE_1);
            age5=view.findViewById(R.id.POST_PRO_OTH_AGE_5);
            age10=view.findViewById(R.id.POST_PRO_OTH_AGE_10);
            ageAdd=view.findViewById(R.id.POST_PRO_OTH_AGE_ADD);
            allfloorToME=view.findViewById(R.id.POST_PRO_OTH_FLOOR_BELONGS);
//            inputs
            totalRoomsIn=view.findViewById(R.id.POST_PRO_OTH_ROOMS_INPUT);
            floorNoIn=view.findViewById(R.id.POST_PRO_OTH_FLOOR_NO_IN);
            allowedContsIn=view.findViewById(R.id.POST_PRO_OTH_FLOOR_ALLOWED_IN);
            carpetIn=view.findViewById(R.id.POST_PRO_OTH_CARPET_IN);
            builtUpIn=view.findViewById(R.id.POST_PRO_OTH_BULTUP_IN);
            superIn=view.findViewById(R.id.POST_PRO_OTH_SUPER_IN);
            plotAreain=view.findViewById(R.id.POST_PRO_OTH_PLOT_AREA_IN);
            plotLenIn=view.findViewById(R.id.POST_PRO_OTH_PLOT_LENGTH);
            plotBreIn=view.findViewById(R.id.POST_PRO_OTH_PLOT_WEGTH);
            unDateIn=view.findViewById(R.id.POST_PRO_OTH_POSS_UNDER_IN_DOB);

            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText d = view.findViewById(R.id.POST_PRO_OTH_POSS_UNDER_IN_DOB);
                    d.setText(date1());
                }
            });
            setFarmView();
//            clicks
            bedrooms1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=1;
                    handleGraphics();
                }
            });
            bedroom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=2;
                    handleGraphics();
                }
            });
            bedroom3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=3;
                    handleGraphics();
                }
            });
            bedroom4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=5;
                    handleGraphics();
                }
            });
            bedroom5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=6;
                    handleGraphics();
                }
            });
            bedrromadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibedrooms=4;
                    handleGraphics();
                }
            });

            belcnies1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=1;
                    handleGraphics();
                }
            });
            belconies2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=2;
                    handleGraphics();
                }
            });
            belconies3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=3;
                    handleGraphics();
                }
            });
            belconies4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=4;
                    handleGraphics();
                }
            });
            belconiesAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibelconies=5;
                }
            });

            open1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=1;
                    handleGraphics();
                }
            });
            open2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=2;
                    handleGraphics();
                }
            });
            open3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=3;
                    handleGraphics();
                }
            });
            openAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iopen=4;
                    handleGraphics();
                }
            });

            furnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=1;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedsSemi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=2;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                    startActivityForResult(new Intent(getActivity(), FurnishedStatus.class), 1);
                }
            });
            furnishedUn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ifurnished=3;
                    handleGraphics();
                    arrayList.clear();
                    setFurnishing();
                }
            });

            batroom1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=1;
                    handleGraphics();
                }
            });
            bathroom2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=2;
                    handleGraphics();
                }
            });
            bathroom3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=3;
                    handleGraphics();
                }
            });
            bathroom4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=4;
                    handleGraphics();
                }
            });
            bathroomAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ibathrooms=5;
                    handleGraphics();
                }
            });

            sWash1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=1;
                    handleGraphics();
                }
            });
            sWash2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=2;
                    handleGraphics();
                }
            });
            sWash3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=3;
                    handleGraphics();
                }
            });
            sWash4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=4;
                    handleGraphics();
                }
            });
            sWashAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iswash=5;
                    handleGraphics();
                }
            });

            transResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=1;
                    handleGraphics();
                }
            });
            transNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Itrance=2;
                    handleGraphics();
                }
            });

            possUnder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=1;
                    handleGraphics();
                }
            });
            possRedy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iposs=2;
                    handleGraphics();
                }
            });

            carpetSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=1;
                    setMazore();
                }
            });
            bultupSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=2;
                    setMazore();
                }
            });
            superSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=3;
                    setMazore();
                }
            });
            plotAreaSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBy=4;
                    setMazore();
                }
            });

            age1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=1;
                    handleGraphics();
                }
            });
            age5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=2;
                    handleGraphics();
                }
            });
            age10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=3;
                    handleGraphics();
                }
            });
            ageAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Iage=4;
                    handleGraphics();
                    new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                        @Override
                        public void getData(String value) {
                            TextView t = (TextView) ageAdd.getChildAt(0);
                            t.setText(value);
                            Iage=5;
                        }
                    });
                }
            });

        }
        contineue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MAINRESI.getVisibility()==View.VISIBLE){
                    if (faltonfloorL.getVisibility()==View.VISIBLE){
                        if (floorNoIn.getText()==null|floorNoIn.getText().length()<=0){
                            floorNoIn.setError(error);
                            return;
                        }
                    }
                    if (carpetIn.getText().length()<=0&&builtUpIn.getText().length()<=0&&superIn.getText().length()<=0){
                        carpetIn.setError(error);
                        builtUpIn.setError(error);
                        superIn.setError(error);
                        return;
                    }
                   if (plotareaL.getVisibility()==View.VISIBLE){
                       if (plotAreain.getText()==null|plotAreain.getText().length()<=0){
                           plotAreain.setError(error);
                           return;
                       }
                       if (plotLenIn.getText()==null|plotBreIn.getText().length()<=0){
                           plotLenIn.setError(error);
                           return;
                       }
                       if (plotBreIn.getText()==null|plotBreIn.getText().length()<=0){
                           plotBreIn.setError(error);
                           return;
                       }
                   }
                    postFeatures();
                }
                if (PLOTRESI.getVisibility()==View.VISIBLE){
                    if (plotAreain.getText()==null|plotAreain.getText().length()<=0){
                        plotAreain.setError(error);
                        return;
                    }
                    if (plotLenIn.getText()==null|plotLenIn.getText().length()<=0){
                        plotLenIn.setError(error);
                        return;
                    }
                    if (plotBreIn.getText()==null|plotBreIn.getText().length()<=0){
                        plotBreIn.setError(error);
                        return;
                    }
                    postFeatures();
                }
                if (COMERCIAL.getVisibility()==View.VISIBLE){
                    if (noflorL.getVisibility()==View.VISIBLE){
                        if (floorNoIn.getText()==null|floorNoIn.getText().length()<=0){
                            floorNoIn.setError(error);
                            return;
                        }
                    }
                    if (addareaL.getVisibility()==View.VISIBLE){
                        if (carpetIn.getText().length()<=0&&builtUpIn.getText().length()<=0&&superIn.getText().length()<=0){
                            carpetIn.setError(error);
                            builtUpIn.setError(error);
                            superIn.setError(error);
                            return;
                        }
                    }
                    if (plotAreaL.getVisibility()==View.VISIBLE){
                        if (plotAreain.getText()==null|plotAreain.getText().length()<=0){
                            plotAreain.setError(error);
                            return;
                        }
                        if (plotLenIn.getText()==null|plotBreIn.getText().length()<=0){
                            plotLenIn.setError(error);
                            return;
                        }
                        if (plotBreIn.getText()==null|plotBreIn.getText().length()<=0){
                            plotBreIn.setError(error);
                            return;
                        }
                    }
                    if (shopL!=null&&shopL.getVisibility()==View.VISIBLE){
                        if (shopNoIn.getText()==null|shopNoIn.getText().length()<=0){
                            shopNoIn.setError(error);
                            return;
                        }
                    }
                    if (allowedL!=null&&allowedL.getVisibility()==View.VISIBLE){
                        if (allowedContsIn.getText()==null|allowedContsIn.getText().length()<=0){
                            allowedContsIn.setError(error);
                            return;
                        }
                    }
                    if (opensideL!=null&&opensideL.getVisibility()==View.VISIBLE){
                        if (opensidesIn.getText()==null|opensidesIn.getText().length()<=0){
                            opensidesIn.setError(error);
                            return;
                        }
                    }
                    if (officeL!=null&officeL.getVisibility()==View.VISIBLE){
                        if (minSeatsIn.getText()==null|minSeatsIn.getText().length()<=0){
                            minSeatsIn.setError(error);
                            return;
                        }
                        if (maxSeatsIn.getText()==null|maxSeatsIn.getText().length()<=0){
                            maxSeatsIn.setError(error);
                            return;
                        }
                        if (noOfMeetingRoomsIn.getText() == null | noOfMeetingRoomsIn.getText().length() <= 0) {
                            noOfMeetingRoomsIn.setError(error);
                            return;
                        }
                        if (noOfCabinIn.getText()==null|noOfCabinIn.getText().length()<=0){
                            noOfCabinIn.setError(error);
                            return;
                        }
                    }
                    postFeatures();
                }
                if (OTHER.getVisibility()==View.VISIBLE){
                    if (noflorL!=null&&noflorL.getVisibility()==View.VISIBLE){
                        if (floorNoIn.getText()==null|floorNoIn.getText().length()<=0){
                            floorNoIn.setError(error);
                            return;
                        }
                    }
                    if (addareaL!=null&&addareaL.getVisibility()==View.VISIBLE){
                        if (carpetIn.getText().length()<=0&&builtUpIn.getText().length()<=0&&superIn.getText().length()<=0){
                            carpetIn.setError(error);
                            builtUpIn.setError(error);
                            superIn.setError(error);
                            return;
                        }
                    }
                    if (plotareaL!=null&&plotareaL.getVisibility()==View.VISIBLE){
                        if (plotAreain.getText()==null|plotAreain.getText().length()<=0){
                            plotAreain.setError(error);
                            return;
                        }
                        if (plotLenIn.getText()==null|plotBreIn.getText().length()<=0){
                            plotLenIn.setError(error);
                            return;
                        }
                        if (plotBreIn.getText()==null|plotBreIn.getText().length()<=0){
                            plotBreIn.setError(error);
                            return;
                        }
                    }
                    if (roomsL!=null&&roomsL.getVisibility()==View.VISIBLE){
                        if (totalRoomsIn.getText()==null|totalRoomsIn.getText().length()<=0){
                            totalRoomsIn.setError(error);
                            return;
                        }
                    }
                    if (allowedL!=null&&allowedL.getVisibility()==View.VISIBLE){
                        if (allowedContsIn.getText()==null|allowedContsIn.getText().length()<=0){
                            allowedContsIn.setError(error);
                            return;
                        }
                    }
                    if (unDateIn.getVisibility()!=View.GONE){
                        if (unDateIn.getText()==null|unDateIn.getText().length()<=0){
                            unDateIn.setError(error);
                            return;
                        }
                    }
                    postFeatures();
                }
            }
        });
        return view;
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
                        PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);
                        PrefMananger.FeatureFarmSet(getContext(),Property_Id);
                        handlerVisiblesForm();
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
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_FEATURES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("proResy6", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        PostPropertyModal propertyModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PostPropertyModal.class);
                        setDataFromServer(propertyModal);
                        TextView textView = (TextView) contineue.getChildAt(0);
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
    private void setDataFromServer(PostPropertyModal propertyModal) {
        if (MAINRESI.getVisibility()==View.VISIBLE){
            if (propertyModal.getBedrooms()!=null){
                if (Integer.parseInt(propertyModal.getBedrooms())>13){
                    TextView textView = (TextView) bedrromadd.getChildAt(0);
                    textView.setText(propertyModal.getBedrooms());
                    Ibedrooms = Integer.parseInt(propertyModal.getBedrooms())+10;
                }else {
                    Ibedrooms = Integer.parseInt(propertyModal.getBedrooms())+10;
                }
            }
            if (propertyModal.getMasterroom()!=null){
                if (propertyModal.getMasterroom().equals("Ground Floor")){
                    Imasterbedroom = 1;
                }else {
                    Imasterbedroom = 2;
                }
                Log.e("test", "onResponse: test1" );
            }
            if (propertyModal.getBalconies()!=null){
                if (Integer.parseInt(propertyModal.getBalconies())<4){
                    TextView textView = (TextView) belconiesAdd.getChildAt(0);
                    textView.setText(propertyModal.getBedrooms());
                }else {
                    Ibelconies = Integer.parseInt(propertyModal.getBalconies());
                }
            }
            if (propertyModal.getTotal_no_of_floor()!=null){
                if (Integer.parseInt(propertyModal.getTotal_no_of_floor())<5){
                    TextView textView = (TextView) flatsAdd.getChildAt(0);
                    textView.setText(propertyModal.getTotal_no_of_floor());
                }else {
                    Iflats = Integer.parseInt(propertyModal.getTotal_no_of_floor());
                }
            }
            if (propertyModal.getNo_of_open_sides()!=null){
                if (Integer.parseInt(propertyModal.getNo_of_open_sides())<3){
                    TextView textView = (TextView) openAdd.getChildAt(0);
                    textView.setText(propertyModal.getNo_of_open_sides());
                }else {
                    Iopen = Integer.parseInt(propertyModal.getNo_of_open_sides());
                }
            }
            if (propertyModal.getFurnished_status().equals("Furnished")){
                Ifurnished=1;
            }
            if (propertyModal.getFurnished_status().equals("SemiFurnished")){
                Ifurnished=2;
            }
            if (propertyModal.getFurnished_status().equals("UnFurnished")){
                Ifurnished=3;
            }
            if (propertyModal.getBathrooms()!=null){
                if (Integer.parseInt(propertyModal.getBathrooms())<3){
                    TextView textView =(TextView) bathroomAdd.getChildAt(0);
                    textView.setText(propertyModal.getBathrooms());
                    Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                }else {
                    Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                }
            }
            if (propertyModal.getPossession_status().equals("Under Construction")){
                Iposs=1;
            }else {
                Iposs=2;
            }
            if (propertyModal.getAge_property().equals("1 - 5 Yrs")){
                Iage=1;
            }
            if (propertyModal.getAge_property().equals("5 - 10 Yrs")){
                Iage=2;
            }else {
                Iage=3;
            }
            floorNoIn.setText(propertyModal.getTotal_no_of_floor());
            carpetIn.setText(propertyModal.getCaptur_area_max());
            builtUpIn.setText(propertyModal.getBuildup_area_max());
            superIn.setText(propertyModal.getSuper_area_max());
            plotAreain.setText(propertyModal.getPlot_area());
            plotBreIn.setText(propertyModal.getPlotbreadth_ft());
            plotLenIn.setText(propertyModal.getPlotlength_ft());
            SetTextByParrent(carpetSet,propertyModal.getCaptur_area_max_mezor());
            SetTextByParrent(bultupSet,propertyModal.getBuildup_area_max_mezor());
            SetTextByParrent(superSet,propertyModal.getSuper_area_max_mezor());
            SetTextByParrent(plotAreaSet,propertyModal.getPlot_area_mezor());
            SetTextByParrent(floorSet,propertyModal.getPropertyonfloor());
            if (propertyModal.getAll_floors_belongs_to_me().equals("true")){
                checkBox.setChecked(true);
            }

        }
        if (PLOTRESI.getVisibility()==View.VISIBLE){
                            if (Integer.parseInt(propertyModal.getNo_of_open_sides())==4){
                                TextView textView = (TextView) openAdd.getChildAt(0);
                                textView.setText(propertyModal.getNo_of_open_sides());
                            }else {
                                Iopen = Integer.parseInt(propertyModal.getNo_of_open_sides());
                            }
                            if (propertyModal.getAny_construction().equals("Yes")){
                                IanyCon=1;
                            }else {
                                IanyCon=2;
                            }
                            if (propertyModal.getBoundary_wall_made().equals("Yes")){
                                Iboundry=1;
                            }else {
                                Iboundry=2;
                            }
                            if (propertyModal.getGated_colony().equals("Yes")){
                                Igated=1;
                            }else {
                                Igated=2;
                            }
                            SetTextByParrent(plotAreaSet,propertyModal.getPlot_area_mezor());
                            if (propertyModal.getTransaction_type().equals("Resale")){
                                Itrance=1;
                            }else {
                                Itrance=2;
                            }
                            plotAreain.setText(propertyModal.getPlot_area());
                            plotLenIn.setText(propertyModal.getPlotlength_ft());
                            plotBreIn.setText(propertyModal.getPlotbreadth_ft());

                        }
        if (COMERCIAL.getVisibility()==View.VISIBLE){
                            if (propertyModal.getFurnished_status().equals("Furnished")){
                                Ifurnished=1;
                            }
                            if (propertyModal.getFurnished_status().equals("SemiFurnished")){
                                Ifurnished=2;
                            }
                            if (propertyModal.getFurnished_status().equals("UnFurnished")){
                                Ifurnished=3;
                            }
                            if (Integer.parseInt(propertyModal.getBathrooms())<3){
                                TextView textView =(TextView) bathroomAdd.getChildAt(0);
                                textView.setText(propertyModal.getBathrooms());
                                Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                            }else {
                                Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                            }
                            if (propertyModal.getGated_colony().equals("Yes")){
                                Igated=1;
                            }else {
                                Igated=2;
                            }
                            if (propertyModal.getTransaction_type().equals("Resale")){
                                Itrance=1;
                            }else {
                                Itrance=2;
                            }
                            if (propertyModal.getPossession_status().equals("Under Construction")){
                                Iposs=1;
                            }else {
                                Iposs=2;
                            }
                            if (Integer.parseInt(propertyModal.getPersonalbathrooms())<3){
                                TextView textView =(TextView) pWashAdd.getChildAt(0);
                                textView.setText(propertyModal.getPersonalbathrooms());
                                Ipwash=4;
                            }else {
                                Ipwash=Integer.parseInt(propertyModal.getPersonalbathrooms());
                            }
                            if (Integer.parseInt(propertyModal.getSharedwashrooms())<3){
                                TextView textView =(TextView) sWashAdd.getChildAt(0);
                                textView.setText(propertyModal.getSharedwashrooms());
                                Iswash=4;
                            }else {
                                Iswash=Integer.parseInt(propertyModal.getSharedwashrooms());
                            }
                            if (propertyModal.getShared_pantry().equals("Dry")){
                                Ipantry=1;
                            }
                            if (propertyModal.getShared_pantry().equals("Wet")){
                                Ipantry=2;
                            }else {
                                Ipantry=3;
                            }
                            if (propertyModal.getConference_room().equals("Yes")){
                                Iconf=1;
                            }else {
                                Iconf=2;
                            }
                            if (propertyModal.getReception_area().equals("Yes")){
                                Ireception=1;
                            }else {
                                Ireception=2;
                            }
                            if (propertyModal.getAge_property().equals("1")){
                                Iage=1;
                            }
                            if (propertyModal.getAge_property().equals("5")){
                                Iage=2;
                            }else {
                                Iage=3;
                            }
                            floorNoIn.setText(propertyModal.getTotal_no_of_floor());
                            shopNoIn.setText(propertyModal.getCafeteria());
                            allowedContsIn.setText(propertyModal.getFloor_allowed_for_construction());
                            opensidesIn.setText(propertyModal.getNo_of_open_sides());
                            carpetIn.setText(propertyModal.getCaptur_area_max());
                            builtUpIn.setText(propertyModal.getBuildup_area_max());
                            superIn.setText(propertyModal.getSuper_area_max());
                            plotAreain.setText(propertyModal.getPlot_area());
                            plotBreIn.setText(propertyModal.getPlotbreadth_ft());
                            plotLenIn.setText(propertyModal.getPlotlength_ft());
                            maxSeatsIn.setText(propertyModal.getMax_no_of_seats());
                            minSeatsIn.setText(propertyModal.getMin_no_of_seats());
                            noOfCabinIn.setText(propertyModal.getNo_of_cabins());
                            noOfMeetingRoomsIn.setText(propertyModal.getNo_of_meeting_rooms());
                            venueIn.setText(propertyModal.getVenue_type());

                            SetTextByParrent(floorSet,propertyModal.getFloors_belongs());
                            SetTextByParrent(carpetSet,propertyModal.getCaptur_area_max_mezor());
                            SetTextByParrent(bultupSet,propertyModal.getBuildup_area_max_mezor());
                            SetTextByParrent(superSet,propertyModal.getSuper_area_max_mezor());
                            SetTextByParrent(plotAreaSet,propertyModal.getPlot_area_mezor());

                        }
        if (OTHER.getVisibility()==View.VISIBLE){
                            if (Integer.parseInt(propertyModal.getBedrooms())<13){
                                TextView textView = (TextView) bedrromadd.getChildAt(0);
                                textView.setText(propertyModal.getBedrooms());
                            }else {
                                Ibedrooms = Integer.parseInt(propertyModal.getBedrooms())+10;
                            }
                            if (Integer.parseInt(propertyModal.getBalconies())<4){
                                TextView textView = (TextView) belconiesAdd.getChildAt(0);
                                textView.setText(propertyModal.getBedrooms());
                            }else {
                                Ibelconies = Integer.parseInt(propertyModal.getBalconies());
                            }
                            if (Integer.parseInt(propertyModal.getNo_of_open_sides())<3){
                                TextView textView = (TextView) openAdd.getChildAt(0);
                                textView.setText(propertyModal.getNo_of_open_sides());
                            }else {
                                Iopen = Integer.parseInt(propertyModal.getNo_of_open_sides());
                            }
                            if (propertyModal.getFurnished_status().equals("Furnished")){
                                Ifurnished=1;
                            }
                            if (propertyModal.getFurnished_status().equals("SemiFurnished")){
                                Ifurnished=2;
                            }
                            if (propertyModal.getFurnished_status().equals("UnFurnished")){
                                Ifurnished=3;
                            }
                            if (Integer.parseInt(propertyModal.getBathrooms())<3){
                                TextView textView =(TextView) bathroomAdd.getChildAt(0);
                                textView.setText(propertyModal.getBathrooms());
                                Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                            }else {
                                Ibathrooms=Integer.parseInt(propertyModal.getBathrooms());
                            }
                            if (Integer.parseInt(propertyModal.getSharedwashrooms())<3){
                                TextView textView =(TextView) sWashAdd.getChildAt(0);
                                textView.setText(propertyModal.getSharedwashrooms());
                                Iswash=4;
                            }else {
                                Iswash=Integer.parseInt(propertyModal.getSharedwashrooms());
                            }
                            if (propertyModal.getPossession_status().equals("Under Construction")){
                                Iposs=1;
                            }else {
                                Iposs=2;
                            }
                            if (propertyModal.getTransaction_type().equals("Resale")){
                                Itrance=1;
                            }else {
                                Itrance=2;
                            }
                            totalRoomsIn.setText(propertyModal.getRoom_detail());
                            floorNoIn.setText(propertyModal.getTotal_no_of_floor());
                            allowedContsIn.setText(propertyModal.getFloor_allowed_for_construction());
                            carpetIn.setText(propertyModal.getCaptur_area_max());
                            builtUpIn.setText(propertyModal.getBuildup_area_max());
                            superIn.setText(propertyModal.getSuper_area_max());
                            plotAreain.setText(propertyModal.getPlot_area());
                            plotLenIn.setText(propertyModal.getPlotlength_ft());
                            plotBreIn.setText(propertyModal.getPlotbreadth_ft());
                            unDateIn.setText(propertyModal.getPossession());

                            SetTextByParrent(floorSet,propertyModal.getFloors_belongs());
                            SetTextByParrent(carpetSet,propertyModal.getCaptur_area_max_mezor());
                            SetTextByParrent(bultupSet,propertyModal.getBuildup_area_max_mezor());
                            SetTextByParrent(superSet,propertyModal.getSuper_area_max_mezor());
                            SetTextByParrent(plotAreaSet,propertyModal.getPlot_area_mezor());
                        }
        handleGraphics();

    }
    private void SetTextByParrent(FrameLayout fame, String data) {
        TextView textView = (TextView) fame.getChildAt(0);
        textView.setText(data);
    }
    private void postFeatures() {
        PostPropertyModal post =  new PostPropertyModal();
        post.setProperty_id(PrefMananger.getString(context,PrefMananger.PROPERTY_ID));
        post.setUser_id(PrefMananger.GetLoginData(context).getUserId()+"");
        if (MAINRESI.getVisibility()==View.VISIBLE){
            if (Length!=null|Bre!=null|Hall!=null){
                String[] len = new String[Length.size()],bre=new String[Bre.size()],hall=new String[Hall.size()];
                for (int i=0;i<Length.size();i++){
                    len[i]=Length.get(i);
                }
                for (int i=0;i<Bre.size();i++){
                    bre[i]=Bre.get(i);
                }
                for (int i=0;i<Hall.size();i++){
                    hall[i]=Hall.get(i);
                }
                post.setRoomlength(len);
                post.setRoombreadth(bre);
                post.setRoomhall(hall);
            }
            if (Ibedrooms>=14){
                TextView textView = (TextView) bedrromadd.getChildAt(0);
                post.setRoom_detail(textView.getText().toString());
            }else {
                if (Ibedrooms==11){
                    post.setRoom_detail(String.valueOf(1));
                }
                if (Ibedrooms==12){
                    post.setRoom_detail(String.valueOf(2));
                }
                if (Ibedrooms==13){
                    post.setRoom_detail(String.valueOf(3));
                }
                if (Ibedrooms==15){
                    post.setRoom_detail(String.valueOf(5));
                }
                if (Ibedrooms==16){
                    post.setRoom_detail(String.valueOf(6));
                }
            }
            if (Imasterbedroom>=0){
                if (Imasterbedroom==1){
                    post.setMasterroom("Ground Floor");
                }else {
                    post.setMasterroom("First Floor");
                }
            }
            if (Ibelconies==1){
                post.setBalconies("1");
            }
            if (Ibelconies==2){
                post.setBalconies("2");
            }
            if (Ibelconies==3){
                post.setBalconies("3");
            }
            if (Ibelconies==4){
                TextView textView5 = (TextView) belconiesAdd.getChildAt(0);
                post.setBalconies(textView5.getText().toString());
            }
            if (Ibelconies==6){
                post.setBalconies("4");
            }
            if (Ibathrooms>=0){
                if (Ibathrooms>=3){
                    post.setWashrooms(String.valueOf(Ibathrooms));
                }
                if (Ibathrooms==4){
                    TextView textView = (TextView) bathroomAdd.getChildAt(0);
                    post.setWashrooms(textView.getText().toString());
                }
            }
            post.setTotal_no_of_floor(floorNoIn.getText().toString());
            if (checkBox.isChecked()){
                post.setAll_floors_belongs_to_me("Yes");
            }
            if (Ifurnished>=0){
                if (Ifurnished==1){
                    post.setFurnished_status("Furnished");
                    String[] furnishing=new String[arrayList.size()];
                    for (int i=0;i<arrayList.size();i++){
                        furnishing[i]=arrayList.get(i);
                    }
                    post.setFurnishings(furnishing);
                }
                if (Ifurnished==2){
                    post.setFurnished_status("SemiFurnished");
                    String[] furnishing=new String[arrayList.size()];
                    for (int i=0;i<arrayList.size();i++){
                        furnishing[i]=arrayList.get(i);
                    }
                    post.setFurnishings(furnishing);
                }
                if (Ifurnished==3){
                    post.setFurnished_status("UnFurnished");
                }
            }
            String carpet = carpetIn.getText().toString();
            String builtup = builtUpIn.getText().toString();
            String supere = superIn.getText().toString();
            TextView textView = (TextView) carpetSet.getChildAt(0);
            TextView textView1 = (TextView) bultupSet.getChildAt(0);
            TextView textView2= (TextView) superSet.getChildAt(0);
            String majorCar =  textView.getText().toString();
            String majorBuilt= textView1.getText().toString();
            String majorSuper =textView2.getText().toString();
            post.setCaptur_area_max(carpet);
            post.setBuildup_area_max(builtup);
            post.setSuper_area_max(supere);
            post.setCaptur_area_min(carpet);
            post.setBuildup_area_min(builtup);
            post.setSuper_area_min(supere);
            post.setCaptur_area_max_mezor(majorCar);
            post.setCaptur_area_min_mezor(majorCar);
            post.setBuildup_area_max_mezor(majorBuilt);
            post.setBuildup_area_min(majorBuilt);
            post.setSuper_area_max_mezor(majorSuper);
            post.setSuper_area_min_mezor(majorSuper);
            post.setArea(plotAreain.getText().toString());
            TextView textView3 =(TextView) plotAreaSet.getChildAt(0);
            post.setArea_mezor(textView3.getText().toString());
            post.setPlot_area(post.getArea());
            post.setPlot_area_mezor(post.getArea_mezor());
            post.setPlotlength_ft(plotLenIn.getText().toString());
            post.setPlotbreadth_ft(plotBreIn.getText().toString());
            if (Iposs>=0){
                if (Iposs==1){
                    post.setPossession("Under Construction");
                    post.setPossession_status(post.getPossession());
                }
                if (Iposs==2){
                    post.setPossession_status("Ready to Move");
                    post.setPossession(post.getPossession_status());
                    if (Iage==1){
                        post.setAge_property("0-1yrs");
                    }
                    if (Iage==2){
                        post.setAge_property("1-5yrs");
                    }
                    if (Iage==3){
                        post.setAge_property("5-10yrs");
                    }
                    if (Iage==4){
                        TextView textView4 = (TextView) ageAdd.getChildAt(0);
                        post.setAge_property(textView4.getText().toString());
                    }
                }
            };
            if (Itrance==1){
                post.setTransaction_type("Resale");
            }
            if (Itrance==2){
                post.setTransaction_type("New");
            }
            post.setBedrooms(post.getRoom_detail());
            if (Iopen>=0){
                if (Iopen==1){
                    post.setNo_of_open_sides("1");
                }
                if (Iopen==2){
                    post.setNo_of_open_sides("2");
                }
                if (Iopen==3){
                    post.setNo_of_open_sides("3");
                }
                if (Iopen==4){
                    TextView textView4 = (TextView) openAdd.getChildAt(0);
                    post.setNo_of_open_sides(textView4.getText().toString());
                }
                if (Iopen==5){
                    post.setNo_of_open_sides("4");
                }
            }
        }
        if (PLOTRESI.getVisibility()==View.VISIBLE){
            if (allowedContsIn!=null){
                post.setFloor_allowed_for_construction(allowedContsIn.getText().toString());
            }
            if (checkBox!=null){
                post.setAll_floors_belongs_to_me(String.valueOf(checkBox.isChecked()));
            }
            if (Iopen>=0){
                if (Iopen==1){
                    post.setNo_of_open_sides("1");
                }
                if (Iopen==2){
                    post.setNo_of_open_sides("2");
                }
                if (Iopen==3){
                    post.setNo_of_open_sides("3");
                }
                if (Iopen==4){
                    TextView textView4 = (TextView) openAdd.getChildAt(0);
                    post.setNo_of_open_sides(textView4.getText().toString());
                }
                if (Iopen==5){
                    post.setNo_of_open_sides("4");
                }
            }
            if (IanyCon==1){
                post.setAny_construction("Yes");
            }
            if (IanyCon==2){
                post.setAny_construction("No");
            }
            if (Iboundry==1){
                post.setBoundary_wall_made("Yes");
            }
            if (Iboundry==2){
                post.setBoundary_wall_made("No");
            }
            if (Igated==1){
                post.setGated_colony("Yes");
            }
            if (Igated==2){
                post.setGated_colony("No");
            }
            post.setArea(plotAreain.getText().toString());
            TextView textView8 =(TextView) plotAreaSet.getChildAt(0);
            post.setArea_mezor(textView8.getText().toString());
            post.setPlot_area(post.getArea());
            post.setPlot_area_mezor(post.getArea_mezor());
            post.setPlotlength_ft(plotLenIn.getText().toString());
            post.setPlotbreadth_ft(plotBreIn.getText().toString());
            if (Itrance==1){
                post.setTransaction_type("Resale");
            }
            if (Itrance==2){
                post.setTransaction_type("New");
            }
        }
        if (COMERCIAL.getVisibility()==View.VISIBLE){
             if (Ifurnished>=0){
                if (Ifurnished==1){
                    post.setFurnished_status("Furnished");
                    String[] furnishing=new String[arrayList.size()];
                    for (int i=0;i<arrayList.size();i++){
                        furnishing[i]=arrayList.get(i);
                    }
                    post.setFurnishings(furnishing);
                }
                if (Ifurnished==2){
                    post.setFurnished_status("SemiFurnished");
                    String[] furnishing=new String[arrayList.size()];
                    for (int i=0;i<arrayList.size();i++){
                        furnishing[i]=arrayList.get(i);
                    }
                    post.setFurnishings(furnishing);
                }
                if (Ifurnished==3){
                    post.setFurnished_status("UnFurnished");
                }
            }
            if (Ibathrooms==1){
                post.setBathrooms("1");
            }
            if (Ibathrooms==2){
                post.setBathrooms("2");
            }
            if (Ibathrooms==3){
                post.setBathrooms("3");
            }
            if (Ibathrooms==4){
                TextView textView5 = (TextView) bathroomAdd.getChildAt(0);
                post.setBathrooms(textView5.getText().toString());
            }
            if (Ibathrooms==6){
                post.setBathrooms("4");
            }
            if (Iswash==1){
                post.setSharedbathrooms("1");
            }
            if (Iswash==2){
                post.setSharedbathrooms("2");
            }
            if (Iswash==3){
                post.setSharedbathrooms("3");
            }
            if (Iswash==4){
                TextView textView5 = (TextView) sWashAdd.getChildAt(0);
                post.setSharedbathrooms(textView5.getText().toString());
            }
            if (Iswash==6){
                post.setSharedbathrooms("4");
            }
            post.setSharedwashrooms(post.getSharedbathrooms());
            if (Ipwash==1){
                post.setPersonalbathrooms("1");
            }
            if (Ipwash==2){
                post.setPersonalbathrooms("2");
            }
            if (Ipwash==3){
                post.setPersonalbathrooms("3");
            }
            if (Ipwash==4){
                TextView textView5 = (TextView) pWashAdd.getChildAt(0);
                post.setPersonalbathrooms(textView5.getText().toString());
            }
            if (Ipantry>=0){
                if (Ipantry==1){
                    post.setCafeteria("Dry Pantry");
                    post.setNo_of_cafeteria(cafetareaData);
                    post.setNo_of_share_cafeteria(shareCapfetarea);
                }
                if (Ipantry==2){
                    post.setCafeteria("Wet Pantry");
                    post.setNo_of_cafeteria(cafetareaData);
                    post.setNo_of_share_cafeteria(shareCapfetarea);
                }
                if (Ipantry==3){
                    post.setCafeteria("Not Available");
                    post.setNo_of_cafeteria(cafetareaData);
                    post.setNo_of_share_cafeteria(shareCapfetarea);
                }

            }
            if (Iconf==1){
                post.setConference_room("Yes");
            }
            if (Iconf==2){
                post.setConference_room("No");
            }
            if (Ireception==1){
                post.setReception_area("Yes");
            }
            if (Ireception==2){
                post.setReception_area("No");
            }
            if (minSeatsIn!=null){
                post.setMin_no_of_seats(minSeatsIn.getText().toString());
                post.setMax_no_of_seats(maxSeatsIn.getText().toString());
                post.setNo_of_cabins(noOfCabinIn.getText().toString());
                post.setNo_of_meeting_rooms(noOfMeetingRoomsIn.getText().toString());
            }
            post.setDoors_constructed(post.getGated_colony());
            if (Ivspace==1){
                post.setVirtual_space("Yes");
            }
            if (Ivspace==2){
                post.setVirtual_space("No");
            }
            TextView textView5 = (TextView) buildingClass.getChildAt(0);
            post.setBuilding_class(textView5.getText().toString());
            String[] furnishing=new String[arrayList.size()];
            for (int i=0;i<arrayList.size();i++){
                furnishing[i]=arrayList.get(i);
            }
            post.setArea_type(furnishing);
            String carpet = carpetIn.getText().toString();
            String builtup = builtUpIn.getText().toString();
            String supere = superIn.getText().toString();
            TextView textView = (TextView) carpetSet.getChildAt(0);
            TextView textView1 = (TextView) bultupSet.getChildAt(0);
            TextView textView2= (TextView) superSet.getChildAt(0);
            String majorCar =  textView.getText().toString();
            String majorBuilt= textView1.getText().toString();
            String majorSuper =textView2.getText().toString();
            post.setCaptur_area_max(carpet);
            post.setBuildup_area_max(builtup);
            post.setSuper_area_max(supere);
            post.setCaptur_area_min(carpet);
            post.setBuildup_area_min(builtup);
            post.setSuper_area_min(supere);
            post.setCaptur_area_max_mezor(majorCar);
            post.setCaptur_area_min_mezor(majorCar);
            post.setBuildup_area_max_mezor(majorBuilt);
            post.setBuildup_area_min(majorBuilt);
            post.setSuper_area_max_mezor(majorSuper);
            post.setSuper_area_min_mezor(majorSuper);
            post.setArea(plotAreain.getText().toString());
            TextView textView3 =(TextView) plotAreaSet.getChildAt(0);
            post.setArea_mezor(textView3.getText().toString());
            post.setPlot_area(post.getArea());
            post.setPlot_area_mezor(post.getArea_mezor());
            post.setPlotlength_ft(plotLenIn.getText().toString());
            post.setPlotbreadth_ft(plotBreIn.getText().toString());
            post.setVenue_type(Svenue);
            if (Iposs>=0){
                if (Iposs==1){
                    post.setPossession("Under Construction");
                    post.setPossession_status(post.getPossession());
                }
                if (Iposs==2){
                    post.setPossession_status("Ready to Move");
                    post.setPossession(post.getPossession_status());
                    if (Iage==1){
                        post.setAge_property("0-1yrs");
                    }
                    if (Iage==2){
                        post.setAge_property("1-5yrs");
                    }
                    if (Iage==3){
                        post.setAge_property("5-10yrs");
                    }
                    if (Iage==4){
                        TextView textView4 = (TextView) ageAdd.getChildAt(0);
                        post.setAge_property(textView4.getText().toString());
                    }
                }
            }
            if (Igated==1){
                post.setGated_colony("Yes");
            }
            if (Igated==2){
                post.setGated_colony("No");
            }
            TextView textView4 = (TextView) floorSet.getChildAt(0);
            post.setPropertyonfloor(textView4.getText().toString());
        }
        if (OTHER.getVisibility()==View.VISIBLE){
            if (Ibedrooms>=14){
                TextView textView = (TextView) bedrromadd.getChildAt(0);
                post.setRoom_detail(textView.getText().toString());
            }else {
                if (Ibedrooms==11){
                    post.setRoom_detail(String.valueOf(1));
                }
                if (Ibedrooms==12){
                    post.setRoom_detail(String.valueOf(2));
                }
                if (Ibedrooms==13){
                    post.setRoom_detail(String.valueOf(3));
                }
                if (Ibedrooms==15){
                    post.setRoom_detail(String.valueOf(5));
                }
                if (Ibedrooms==16){
                    post.setRoom_detail(String.valueOf(6));
                }
            }
            if (Ibelconies==1){
                post.setBalconies("1");
            }
            if (Ibelconies==2){
                post.setBalconies("2");
            }
            if (Ibelconies==3){
                post.setBalconies("3");
            }
            if (Ibelconies==4){
                TextView textView5 = (TextView) belconiesAdd.getChildAt(0);
                post.setBalconies(textView5.getText().toString());
            }
            if (Ibelconies==6){
                post.setBalconies("4");
            }
            if (Iopen>=0){
                if (Iopen==1){
                    post.setNo_of_open_sides("1");
                }
                if (Iopen==2){
                    post.setNo_of_open_sides("2");
                }
                if (Iopen==3){
                    post.setNo_of_open_sides("3");
                }
                if (Iopen==4){
                    TextView textView4 = (TextView) openAdd.getChildAt(0);
                    post.setNo_of_open_sides(textView4.getText().toString());
                }
                if (Iopen==5){
                    post.setNo_of_open_sides("4");
                }
            }
            if (Ibathrooms==1){
                post.setBathrooms("1");
            }
            if (Ibathrooms==2){
                post.setBathrooms("2");
            }
            if (Ibathrooms==3){
                post.setBathrooms("3");
            }
            if (Ibathrooms==4){
                TextView textView5 = (TextView) bathroomAdd.getChildAt(0);
                post.setBathrooms(textView5.getText().toString());
            }
            if (Ibathrooms==6){
                post.setBathrooms("4");
            }
            if (Iswash==1){
                post.setSharedbathrooms("1");
            }
            if (Iswash==2){
                post.setSharedbathrooms("2");
            }
            if (Iswash==3){
                post.setSharedbathrooms("3");
            }
            if (Iswash==4){
                TextView textView5 = (TextView) sWashAdd.getChildAt(0);
                post.setSharedbathrooms(textView5.getText().toString());
            }
            if (Iswash==6){
                post.setSharedbathrooms("4");
            }
            post.setSharedwashrooms(post.getSharedbathrooms());
            if (Iposs>=0){
                if (Iposs==1){
                    post.setPossession("Under Construction");
                    post.setPossession_status(post.getPossession());
                }
                if (Iposs==2){
                    post.setPossession_status("Ready to Move");
                    post.setPossession(post.getPossession_status());
                    if (Iage==1){
                        post.setAge_property("0-1yrs");
                    }
                    if (Iage==2){
                        post.setAge_property("1-5yrs");
                    }
                    if (Iage==3){
                        post.setAge_property("5-10yrs");
                    }
                    if (Iage==4){
                        TextView textView4 = (TextView) ageAdd.getChildAt(0);
                        post.setAge_property(textView4.getText().toString());
                    }
                }
            }
            String carpet = carpetIn.getText().toString();
            String builtup = builtUpIn.getText().toString();
            String supere = superIn.getText().toString();
            TextView textView = (TextView) carpetSet.getChildAt(0);
            TextView textView1 = (TextView) bultupSet.getChildAt(0);
            TextView textView2= (TextView) superSet.getChildAt(0);
            String majorCar =  textView.getText().toString();
            String majorBuilt= textView1.getText().toString();
            String majorSuper =textView2.getText().toString();
            post.setCaptur_area_max(carpet);
            post.setBuildup_area_max(builtup);
            post.setSuper_area_max(supere);
            post.setCaptur_area_min(carpet);
            post.setBuildup_area_min(builtup);
            post.setSuper_area_min(supere);
            post.setCaptur_area_max_mezor(majorCar);
            post.setCaptur_area_min_mezor(majorCar);
            post.setBuildup_area_max_mezor(majorBuilt);
            post.setBuildup_area_min(majorBuilt);
            post.setSuper_area_max_mezor(majorSuper);
            post.setSuper_area_min_mezor(majorSuper);
            post.setArea(plotAreain.getText().toString());
            TextView textView3 =(TextView) plotAreaSet.getChildAt(0);
            post.setArea_mezor(textView3.getText().toString());
            post.setPlot_area(post.getArea());
            post.setPlot_area_mezor(post.getArea_mezor());
            post.setPlotlength_ft(plotLenIn.getText().toString());
            post.setPlotbreadth_ft(plotBreIn.getText().toString());
            if (Itrance==1){
                post.setTransaction_type("Resale");
            }
            if (Itrance==2){
                post.setTransaction_type("New");
            }
            post.setBedrooms(post.getRoom_detail());
        }
        String[] furnishing=new String[arrayList.size()];
        for (int i=0;i<arrayList.size();i++){
            furnishing[i]=arrayList.get(i);
        }
        post.setArea_type(furnishing);
        ProgressDialog pd =  new ProgressDialog(getContext());
        pd.setMessage("Saving...");
        pd.show();
//        ApiExecutor.getApiService().postFeature(post).enqueue(new Callback<PostPropertyModal>() {
//            @Override
//            public void onResponse(Call<PostPropertyModal> call, retrofit2.Response<PostPropertyModal> response) {
//                if (response.isSuccessful()){
//                    aichat ai = new aichat();
//                    ai.setText("TAB");
//                    ai.setValue("2");
//                    ai.setValues("tb");
//                    liveCommnication.setText(ai);
//                }
//                pd.cancel();
//            }
//
//            @Override
//            public void onFailure(Call<PostPropertyModal> call, Throwable t) {
//
//            }
//        });

        HashMap<String,String>hashMap=new HashMap<>();
        Field[] fields = post.getClass().getDeclaredFields();
        for (Field f:fields){
            f.setAccessible(true);
            try {
                if (f.get(post).getClass().isArray()){

                }else {
                    String value = (String) f.get(post);
                    if (value==null){
                        value="null";
                        hashMap.put(f.getName(),value);
                    }else {
                        hashMap.put(f.getName(),value);
                    }
                }
            }catch (Exception e){

            }

        }
//        hashMap.put("user_id",post.getUser_id());
//        hashMap.put("property_id",post.getProperty_id());
//        hashMap.put("room_detail",post.getRoom_detail());
//        hashMap.put("roomlength[]",post.getRoomlength());
//        hashMap.put("roombreadth[]",post.getRoombreadth());
//        hashMap.put("roomhall[]",post.getRoomhall());
//        hashMap.put("masterroom",post.getMasterroom());
//        hashMap.put("total_no_of_floor",post.getTotal_no_of_floor());
//        hashMap.put("floors_belongs",post.getFloors_belongs());
//        hashMap.put("furnished_status",post.getFurnished_status());
//        hashMap.put("furnishings[]",post.getFurnishings());
//        hashMap.put("cafeteria",post.getCafeteria());
//        hashMap.put("no_of_cafeteria",post.getNo_of_cafeteria());
//        hashMap.put("no_of_share_cafeteria",post.getNo_of_share_cafeteria());
//        hashMap.put("area_type[]",post.getArea_type());
//        hashMap.put("captur_area_min",post.getCaptur_area_min());
//        hashMap.put("captur_area_min_mezor",post.getCaptur_area_min_mezor());
//        hashMap.put("captur_area_max",post.getCaptur_area_max());
//        hashMap.put("captur_area_max_mezor",post.getCaptur_area_max_mezor());
//        hashMap.put("buildup_area_min",post.getBuildup_area_min());
//        hashMap.put("buildup_area_min_mezor",post.getBuildup_area_min_mezor());
//        hashMap.put("buildup_area_max",post.getBuildup_area_max());
//        hashMap.put("buildup_area_max_mezor",post.getBuildup_area_max_mezor());
//        hashMap.put("super_area_min",post.getSuper_area_min());
//        hashMap.put("super_area_min_mezor",post.getSuper_area_min_mezor());
//        hashMap.put("super_area_max",post.getSuper_area_max());
//        hashMap.put("super_area_max_mezor",post.getSuper_area_max_mezor());
//        hashMap.put("area",post.getArea());
//        hashMap.put("area_mezor",post.getArea_mezor());
//        hashMap.put("plot_area",post.getPlot_area());
//        hashMap.put("plot_area_mezor",post.getPlot_area_mezor());
//        hashMap.put("plotlength_ft",post.getPlotlength_ft());
//        hashMap.put("plotbreadth_ft",post.getPlotbreadth_ft());
//        hashMap.put("possession",post.getPossession());
//        hashMap.put("possession_status",post.getPossession_status());
//        hashMap.put("age_property",post.getAge_property());
//        hashMap.put("transaction_type",post.getTransaction_type());
//        hashMap.put("floor_allowed_for_construction",post.getFloor_allowed_for_construction());
//        hashMap.put("all_floors_belongs_to_me",post.getAll_floors_belongs_to_me());
//        hashMap.put("no_of_open_sides",post.getNo_of_open_sides());
//        hashMap.put("bedrooms",post.getBedrooms());
//        hashMap.put("balconies",post.getBalconies());
//        hashMap.put("propertyonfloor",post.getPropertyonfloor());
//        hashMap.put("bathrooms",post.getBathrooms());
//        hashMap.put("sharedbathrooms",post.getSharedbathrooms());
//        hashMap.put("personalbathrooms",post.getPersonalbathrooms());
//        hashMap.put("sharedwashrooms",post.getSharedwashrooms());
//        hashMap.put("any_construction",post.getAny_construction());
//        hashMap.put("boundary_wall_made",post.getBoundary_wall_made());
//        hashMap.put("gated_colony",post.getGated_colony());
//        hashMap.put("width_of_entrance",post.getWidth_of_entrance());
//        hashMap.put("width_of_entrance_mezor",post.getWidth_of_entrance_mezor());
//        hashMap.put("construction_status_of_walls",post.getConstruction_status_of_walls());
//        hashMap.put("conference_room",post.getConference_room());
//        hashMap.put("totle_no_conference_room",post.getTotle_no_conference_room());
//        hashMap.put("reception_area",post.getReception_area());
//        hashMap.put("min_no_of_seats",post.getMin_no_of_seats());
//        hashMap.put("max_no_of_seats",post.getMax_no_of_seats());
//        hashMap.put("no_of_cabins",post.getNo_of_cabins());
//        hashMap.put("no_of_meeting_rooms",post.getNo_of_meeting_rooms());
//        hashMap.put("doors_constructed",post.getDoors_constructed());
//        hashMap.put("venue_type",post.getVenue_type());
//        hashMap.put("virtual_space",post.getVirtual_space());
//        hashMap.put("building_class",post.getBuilding_class());
//        hashMap.put("rate_of_return",post.getRate_of_return());
//        hashMap.put("shared_pantry",post.getShared_pantry());
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_PROPERY_FEATURES, new Response.Listener<String>() {
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
                                ai.setValue("2");
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
                            ai.setValue("2");
                            ai.setValues("tb");
                            liveCommnication.setText(ai);
                        }

                    }else {
                        pd.cancel();
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
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void classSet(){
        floorPojos=new ArrayList<>();
                addItems("Not Applicable");
                addItems("Grade A+");
                addItems("Grade A");
                addItems("Grade B");
                addItems("Grade C");

                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        if (CallBy==1){
                            TextView t = (TextView) buildingClass.getChildAt(0);
                            t.setText(text);
                        }
                    }
                },floorPojos);
    };
    private void setMazore() {
        floorPojos=new ArrayList<>();
        addItems("Sq-Ft");
        addItems("Sq-m");
        addItems("Acre");
        addItems("Bigha");
        addItems("Hectare");
        addItems("Sq-yrd");
        addItems("Sq-m");
        addItems("Acre");
        addItems("Bigha");
        addItems("Hectare");
        new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
            @Override
            public void onitemClick(String text) {
               if (CallBy==1){
                   TextView t = (TextView) carpetSet.getChildAt(0);
                   t.setText(text);
               }
               if (CallBy==2){
                   TextView t = (TextView) bultupSet.getChildAt(0);
                   t.setText(text);
               }
                if (CallBy==3){
                    TextView t = (TextView) superSet.getChildAt(0);
                    t.setText(text);
                }
                if (CallBy==4){
                    TextView t = (TextView) plotAreaSet.getChildAt(0);
                    t.setText(text);
                }
            }
        },floorPojos);
    }
    private void typeData(){
        String[] data = {null};
        floorPojos=new ArrayList<>();
                addItems("Shopping Mall");
                addItems("Shopping Complex");
                addItems("Bus Terminal");
                addItems("Metro Station");
                addItems("Sez/IT Park");
                addItems("Railway Station");
                addItems("Residential area");
                addItems("Corporate area");
                addItems("Airport");
                addItems("Hotel");
                addItems("Business Centre");
                addItems("Hospital");
                addItems("Bus Staion");

                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        Svenue=text;
                        venueType.setText(text);
                    }
                },floorPojos);

    }
    private void getFloor(){
        floorPojos=new ArrayList<>();
        addItems("Lower Basement");
        addItems("Basement");
        addItems("Ground Floor");
        for(int i=1;i<=100;i++){
            addItems(String.valueOf(i));
        }
        new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
            @Override
            public void onitemClick(String text) {
                if (CallBy==1){
                    TextView t = (TextView) floorSet.getChildAt(0);
                    t.setText(text);
                }
            }
        },floorPojos);
    }
    private void getAllowedCont(){
        floorPojos=new ArrayList<>();
        for(int i=1;i<=100;i++){
            addItems(String.valueOf(i));
        }
        new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
            @Override
            public void onitemClick(String text) {
                if (CallBy==1){
                    allowedContT.setText(text);
                }
            }
        },floorPojos);
    }
    private void handleGraphics()  {
        int defaults = R.drawable.employe_circle_rounded;
        int active = R.drawable.selected_background_filter;
        String ad = "+add";
        TextView addbed = null;
        if (Ibedrooms==1){
            bedrooms1.setBackgroundResource(active);
            bedroom2.setBackgroundResource(defaults);
            bedroom3.setBackgroundResource(defaults);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(defaults);
                bedroom5.setBackgroundResource(defaults);
            }
            bedrromadd.setBackgroundResource(defaults);
            addbed= (TextView) bedrromadd.getChildAt(0);
            addbed.setText(ad);
            new AddBedroomsInfo(getContext(), 1, new AddBedroomsInfo.getData() {
                @Override
                public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                    Length =  new ArrayList<>();
                    Bre =  new ArrayList<>();
                    Hall = new ArrayList<>();
                    Length = lenth;
                    Bre = Br;
                    Hall=hall;
                    Ibedrooms=11;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                    LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                    layout.removeAllViews();
                    showRooms();
                }
            });

        }
        if (Ibedrooms==2){
            bedrooms1.setBackgroundResource(defaults);
            bedroom2.setBackgroundResource(active);
            bedroom3.setBackgroundResource(defaults);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(defaults);
                bedroom5.setBackgroundResource(defaults);
            }
            bedrromadd.setBackgroundResource(defaults);
            addbed= (TextView) bedrromadd.getChildAt(0);
            addbed.setText(ad);
            new AddBedroomsInfo(getContext(), 2, new AddBedroomsInfo.getData() {
                @Override
                public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                    Length =  new ArrayList<>();
                    Bre =  new ArrayList<>();
                    Hall = new ArrayList<>();
                    Length = lenth;
                    Bre = Br;
                    Hall=hall;
                    Ibedrooms=12;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                    LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                    layout.removeAllViews();
                    showRooms();
                }
            });

        }
        if (Ibedrooms==3){
            bedrooms1.setBackgroundResource(defaults);
            bedroom2.setBackgroundResource(defaults);
            bedroom3.setBackgroundResource(active);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(defaults);
                bedroom5.setBackgroundResource(defaults);
            }
            bedrromadd.setBackgroundResource(defaults);
            addbed= (TextView) bedrromadd.getChildAt(0);
            addbed.setText(ad);
            new AddBedroomsInfo(getContext(), 3, new AddBedroomsInfo.getData() {
                @Override
                public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                    Length =  new ArrayList<>();
                    Bre =  new ArrayList<>();
                    Hall = new ArrayList<>();
                    Length = lenth;
                    Bre = Br;
                    Hall=hall;
                    Ibedrooms=13;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                    LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                    layout.removeAllViews();
                    showRooms();
                }
            });

        }
        if (Ibedrooms==4){
            bedrooms1.setBackgroundResource(defaults);
            bedroom2.setBackgroundResource(defaults);
            bedroom3.setBackgroundResource(defaults);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(defaults);
                bedroom5.setBackgroundResource(defaults);
            }
            bedrromadd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                   TextView ddbed= (TextView) bedrromadd.getChildAt(0);
                    ddbed.setText(value);
                    Ibedrooms=55;
                    new AddBedroomsInfo(getContext(), Integer.parseInt(value), new AddBedroomsInfo.getData() {
                        @Override
                        public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                            Length =  new ArrayList<>();
                            Bre =  new ArrayList<>();
                            Hall = new ArrayList<>();
                            Length = lenth;
                            Bre = Br;
                            Hall=hall;
                            Ibedrooms=14;
                            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                            LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                            layout.removeAllViews();
                            showRooms();
                        }
                    });
                }
            });


        }
        if (Ibedrooms==5){
            bedrooms1.setBackgroundResource(defaults);
            bedroom2.setBackgroundResource(defaults);
            bedroom3.setBackgroundResource(defaults);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(active);
                bedroom5.setBackgroundResource(defaults);
            }
            bedrromadd.setBackgroundResource(defaults);
            addbed= (TextView) bedrromadd.getChildAt(0);
            addbed.setText(ad);
            new AddBedroomsInfo(getContext(), 4, new AddBedroomsInfo.getData() {
                @Override
                public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                    Length =  new ArrayList<>();
                    Bre =  new ArrayList<>();
                    Hall = new ArrayList<>();
                    Length = lenth;
                    Bre = Br;
                    Hall=hall;
                    Ibedrooms=15;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                    LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                    layout.removeAllViews();
                    showRooms();
                }
            });


        }
        if (Ibedrooms==6){
            bedrooms1.setBackgroundResource(defaults);
            bedroom2.setBackgroundResource(defaults);
            bedroom3.setBackgroundResource(defaults);
            if (bedroom4!=null){
                bedroom4.setBackgroundResource(defaults);
                bedroom5.setBackgroundResource(active);
            }
            bedrromadd.setBackgroundResource(defaults);
            addbed= (TextView) bedrromadd.getChildAt(0);
            addbed.setText(ad);
            new AddBedroomsInfo(getContext(), 5, new AddBedroomsInfo.getData() {
                @Override
                public void getrooms(ArrayList<String> lenth, ArrayList<String> Br, ArrayList<String> hall) {
                    Length =  new ArrayList<>();
                    Bre =  new ArrayList<>();
                    Hall = new ArrayList<>();
                    Length = lenth;
                    Bre = Br;
                    Hall=hall;
                    Ibedrooms=16;
                    HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                    LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);
                    layout.removeAllViews();
                    showRooms();
                }
            });

        }

        if (Imasterbedroom==1){
            masterBedroomGraound.setBackgroundResource(active);
            masterBedroomFirst.setBackgroundResource(defaults);
        }
        if (Imasterbedroom==2){
            masterBedroomGraound.setBackgroundResource(defaults);
            masterBedroomFirst.setBackgroundResource(active);
        }

        if (Ibelconies==1){
            belcnies1.setBackgroundResource(active);
            belconies2.setBackgroundResource(defaults);
            belconies3.setBackgroundResource(defaults);
            belconies4.setBackgroundResource(defaults);
            belconiesAdd.setBackgroundResource(defaults);
            TextView belconiesAddChildAt = (TextView) belconiesAdd.getChildAt(0);
            belconiesAddChildAt.setText(ad);
        }
        if (Ibelconies==2){
            belcnies1.setBackgroundResource(defaults);
            belconies2.setBackgroundResource(active);
            belconies3.setBackgroundResource(defaults);
            belconies4.setBackgroundResource(defaults);
            belconiesAdd.setBackgroundResource(defaults);
            TextView belconiesAddChildAt = (TextView) belconiesAdd.getChildAt(0);
            belconiesAddChildAt.setText(ad);
        }
        if (Ibelconies==3){
            belcnies1.setBackgroundResource(defaults);
            belconies2.setBackgroundResource(defaults);
            belconies3.setBackgroundResource(active);
            belconies4.setBackgroundResource(defaults);
            belconiesAdd.setBackgroundResource(defaults);
            TextView belconiesAddChildAt = (TextView) belconiesAdd.getChildAt(0);
            belconiesAddChildAt.setText(ad);
        }
        if (Ibelconies==4){
            belcnies1.setBackgroundResource(defaults);
            belconies2.setBackgroundResource(defaults);
            belconies3.setBackgroundResource(defaults);
            belconies4.setBackgroundResource(active);
            belconiesAdd.setBackgroundResource(defaults);
            TextView belconiesAddChildAt = (TextView) belconiesAdd.getChildAt(0);
            belconiesAddChildAt.setText(ad);
        }
        if (Ibelconies==5){
            belcnies1.setBackgroundResource(defaults);
            belconies2.setBackgroundResource(defaults);
            belconies3.setBackgroundResource(defaults);
            belconies4.setBackgroundResource(defaults);
            belconiesAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView belconiesAddChildAt = (TextView) belconiesAdd.getChildAt(0);
                    belconiesAddChildAt.setText(value);
                    Ibelconies=55;
                }
            });
        }

        if (Iflats==1){
            falts1.setBackgroundResource(active);
            flats2.setBackgroundResource(defaults);
            flats3.setBackgroundResource(defaults);
            flats4.setBackgroundResource(defaults);
            flats5.setBackgroundResource(defaults);
            flatsAdd.setBackgroundResource(defaults);
            TextView flatstext = (TextView) flatsAdd.getChildAt(0);
            flatstext.setText(ad);
        }
        if (Iflats==2){
            falts1.setBackgroundResource(defaults);
            flats2.setBackgroundResource(active);
            flats3.setBackgroundResource(defaults);
            flats4.setBackgroundResource(defaults);
            flats5.setBackgroundResource(defaults);
            flatsAdd.setBackgroundResource(defaults);
            TextView flatstext = (TextView) flatsAdd.getChildAt(0);
            flatstext.setText(ad);
        }
        if (Iflats==3){
            falts1.setBackgroundResource(defaults);
            flats2.setBackgroundResource(defaults);
            flats3.setBackgroundResource(active);
            flats4.setBackgroundResource(defaults);
            flats5.setBackgroundResource(defaults);
            flatsAdd.setBackgroundResource(defaults);
            TextView flatstext = (TextView) flatsAdd.getChildAt(0);
            flatstext.setText(ad);
        }
        if (Iflats==4){
            falts1.setBackgroundResource(defaults);
            flats2.setBackgroundResource(defaults);
            flats3.setBackgroundResource(defaults);
            flats4.setBackgroundResource(active);
            flats5.setBackgroundResource(defaults);
            flatsAdd.setBackgroundResource(defaults);
            TextView flatstext = (TextView) flatsAdd.getChildAt(0);
            flatstext.setText(ad);
        }
        if (Iflats==5){
            falts1.setBackgroundResource(defaults);
            flats2.setBackgroundResource(defaults);
            flats3.setBackgroundResource(defaults);
            flats4.setBackgroundResource(defaults);
            flats5.setBackgroundResource(active);
            flatsAdd.setBackgroundResource(defaults);
            TextView flatstext = (TextView) flatsAdd.getChildAt(0);
            flatstext.setText(ad);
        }
        if (Iflats==6){
            falts1.setBackgroundResource(defaults);
            flats2.setBackgroundResource(defaults);
            flats3.setBackgroundResource(defaults);
            flats4.setBackgroundResource(defaults);
            flats5.setBackgroundResource(defaults);
            flatsAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView flatstext = (TextView) flatsAdd.getChildAt(0);
                    flatstext.setText(value);
                    Iflats=55;
                }
            });
        }

        if (Iopen==1){
            open1.setBackgroundResource(active);
            open2.setBackgroundResource(defaults);
            open3.setBackgroundResource(defaults);
            openAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) openAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iopen==2){
            open1.setBackgroundResource(defaults);
            open2.setBackgroundResource(active);
            open3.setBackgroundResource(defaults);
            openAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) openAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iopen==3){
            open1.setBackgroundResource(defaults);
            open2.setBackgroundResource(defaults);
            open3.setBackgroundResource(active);
            openAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) openAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iopen==4){
            open1.setBackgroundResource(defaults);
            open2.setBackgroundResource(defaults);
            open3.setBackgroundResource(defaults);
            openAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView openText = (TextView) openAdd.getChildAt(0);
                    openText.setText(value);
                }
            });
        }

        if (Ifurnished==1){
            furnished.setBackgroundResource(active);
            furnishedsSemi.setBackgroundResource(defaults);
            furnishedUn.setBackgroundResource(defaults);
        }
        if (Ifurnished==2){
            furnished.setBackgroundResource(defaults);
            furnishedsSemi.setBackgroundResource(active);
            furnishedUn.setBackgroundResource(defaults);
        }
        if (Ifurnished==3){
            furnished.setBackgroundResource(defaults);
            furnishedsSemi.setBackgroundResource(defaults);
            furnishedUn.setBackgroundResource(active);
        }

        if (Ibathrooms==1){
            batroom1.setBackgroundResource(active);
            bathroom2.setBackgroundResource(defaults);
            bathroom3.setBackgroundResource(defaults);
            if (bathroom4!=null){
                bathroom4.setBackgroundResource(defaults);
            }
            bathroomAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) bathroomAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ibathrooms==2){
            batroom1.setBackgroundResource(defaults);
            bathroom2.setBackgroundResource(active);
            bathroom3.setBackgroundResource(defaults);
            if (bathroom4!=null){
                bathroom4.setBackgroundResource(defaults);
            }
            bathroomAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) bathroomAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ibathrooms==3){
            batroom1.setBackgroundResource(defaults);
            bathroom2.setBackgroundResource(defaults);
            bathroom3.setBackgroundResource(active);
            if (bathroom4!=null){
                bathroom4.setBackgroundResource(defaults);
            }
            bathroomAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) bathroomAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ibathrooms==4){
            batroom1.setBackgroundResource(defaults);
            bathroom2.setBackgroundResource(defaults);
            bathroom3.setBackgroundResource(defaults);
            if (bathroom4!=null){
                bathroom4.setBackgroundResource(defaults);
            }
            bathroomAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView openText = (TextView) bathroomAdd.getChildAt(0);
                    openText.setText(value);
                }
            });
        }
        if (Ibathrooms==5){
            batroom1.setBackgroundResource(defaults);
            bathroom2.setBackgroundResource(defaults);
            bathroom3.setBackgroundResource(defaults);
            if (bathroom4!=null){
                bathroom4.setBackgroundResource(active);
            }
            bathroomAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) bathroomAdd.getChildAt(0);
            openText.setText(ad);
        }

        if (Iposs==1){
            possUnder.setBackgroundResource(active);
            possRedy.setBackgroundResource(defaults);
        }
        if (Iposs==2){
            possUnder.setBackgroundResource(defaults);
            possRedy.setBackgroundResource(active);
        }

        if (Itrance==1){
            transResale.setBackgroundResource(active);
            transNew.setBackgroundResource(defaults);
        }
        if (Itrance==2){
            transResale.setBackgroundResource(defaults);
            transNew.setBackgroundResource(active);
        }

        if (IanyCon==1){
            anyConYes.setBackgroundResource(active);
            anyConNo.setBackgroundResource(defaults);
        }
        if (IanyCon==2){
            anyConYes.setBackgroundResource(defaults);
            anyConNo.setBackgroundResource(active);
        }

        if (Iboundry==1){
            boundryYes.setBackgroundResource(active);
            boundryNo.setBackgroundResource(defaults);
        }
        if (Iboundry==2){
            boundryYes.setBackgroundResource(defaults);
            boundryNo.setBackgroundResource(active);
        }

        if (Igated==1){
            gatedYes.setBackgroundResource(active);
            gatedNo.setBackgroundResource(defaults);
        }
        if (Igated==2){
            gatedYes.setBackgroundResource(defaults);
            gatedNo.setBackgroundResource(active);
        }

        if (Iswash==1){
            sWash1.setBackgroundResource(active);
            sWash2.setBackgroundResource(defaults);
            sWash3.setBackgroundResource(defaults);
            if (sWash4!=null){
                sWash4.setBackgroundResource(defaults);
            }
            sWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) sWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iswash==2){
            sWash1.setBackgroundResource(defaults);
            sWash2.setBackgroundResource(active);
            sWash3.setBackgroundResource(defaults);
            if (sWash4!=null){
                sWash4.setBackgroundResource(defaults);
            }
            sWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) sWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iswash==3){
            sWash1.setBackgroundResource(defaults);
            sWash2.setBackgroundResource(defaults);
            sWash3.setBackgroundResource(active);
            if (sWash4!=null){
                sWash4.setBackgroundResource(defaults);
            }
            sWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) sWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Iswash==4){
            sWash1.setBackgroundResource(defaults);
            sWash2.setBackgroundResource(defaults);
            sWash3.setBackgroundResource(defaults);
            if (sWash4!=null){
                sWash4.setBackgroundResource(defaults);
            }
            sWashAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView openText = (TextView) sWashAdd.getChildAt(0);
                    openText.setText(value);
                }
            });
        }
        if (Iswash==5){
            sWash1.setBackgroundResource(defaults);
            sWash2.setBackgroundResource(defaults);
            sWash3.setBackgroundResource(defaults);
            if (sWash4!=null){
                sWash4.setBackgroundResource(active);
            }
            sWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) sWashAdd.getChildAt(0);
            openText.setText(ad);
        }

        if (Ipwash==1){
            pWash1.setBackgroundResource(active);
            pWash2.setBackgroundResource(defaults);
            pWash3.setBackgroundResource(defaults);
            pWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) pWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ipwash==2){
            pWash1.setBackgroundResource(defaults);
            pWash2.setBackgroundResource(active);
            pWash3.setBackgroundResource(defaults);
            pWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) pWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ipwash==3){
            pWash1.setBackgroundResource(defaults);
            pWash2.setBackgroundResource(defaults);
            pWash3.setBackgroundResource(active);
            pWashAdd.setBackgroundResource(defaults);
            TextView openText = (TextView) pWashAdd.getChildAt(0);
            openText.setText(ad);
        }
        if (Ipwash==4){
            pWash1.setBackgroundResource(defaults);
            pWash2.setBackgroundResource(defaults);
            pWash3.setBackgroundResource(defaults);
            pWashAdd.setBackgroundResource(active);
            new AddDiolog(getContext(), new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    TextView openText = (TextView) pWashAdd.getChildAt(0);
                    openText.setText(value);
                }
            });
        }

        if (Ipantry==1){
            pantryDry.setBackgroundResource(active);
            pantryWet.setBackgroundResource(defaults);
            pantryNo.setBackgroundResource(defaults);
        }
        if (Ipantry==2){
            pantryDry.setBackgroundResource(defaults);
            pantryWet.setBackgroundResource(active);
            pantryNo.setBackgroundResource(defaults);
        }
        if (Ipantry==3){
            pantryDry.setBackgroundResource(defaults);
            pantryWet.setBackgroundResource(defaults);
            pantryNo.setBackgroundResource(active);
        }

        if (Iconf==1){
            confYes.setBackgroundResource(active);
            confNo.setBackgroundResource(defaults);
        }
        if (Iconf==2){
            confYes.setBackgroundResource(defaults);
            confNo.setBackgroundResource(active);
        }

        if (Ireception==1){
            receptionYes.setBackgroundResource(active);
            receptionNo.setBackgroundResource(defaults);
        }
        if (Ireception==2){
            receptionYes.setBackgroundResource(defaults);
            receptionNo.setBackgroundResource(active);
        }

        if (Ivspace==1){
            vSpaceYes.setBackgroundResource(active);
            vSpaceNo.setBackgroundResource(defaults);
        }
        if (Ivspace==2){
            vSpaceYes.setBackgroundResource(defaults);
            vSpaceNo.setBackgroundResource(active);
        }

        if (Iage==1){
            age1.setBackgroundResource(active);
            age5.setBackgroundResource(defaults);
            age10.setBackgroundResource(defaults);
            if (ageAdd!=null){
                ageAdd.setBackgroundResource(defaults);
            }

        }
        if (Iage==2){
            age1.setBackgroundResource(defaults);
            age5.setBackgroundResource(active);
            age10.setBackgroundResource(defaults);
            if (ageAdd!=null){
                ageAdd.setBackgroundResource(defaults);
            }

        }
        if (Iage==3){
            age1.setBackgroundResource(defaults);
            age5.setBackgroundResource(defaults);
            age10.setBackgroundResource(active);
            if (ageAdd!=null){
                ageAdd.setBackgroundResource(defaults);
            }

        }
        if (Iage==4){
            age1.setBackgroundResource(defaults);
            age5.setBackgroundResource(defaults);
            age10.setBackgroundResource(defaults);
            if (ageAdd!=null){
                ageAdd.setBackgroundResource(active);
            }

        }

    }
    private void showRooms() {
        int defaults = R.drawable.employe_circle_rounded;
        int count = 1;
        int length = Length.size();
        for (String s : Length){
            for (String bre : Bre){
                for (String hal : Hall){
                   if (length>=count){
                       HorizontalScrollView horizontalScrollView = (HorizontalScrollView) BEDROOMS.getChildAt(0);
                       LinearLayout layout = (LinearLayout) horizontalScrollView.getChildAt(0);

                       FrameLayout frameLayout = new FrameLayout(getContext());
                       frameLayout.setPadding(70, 35, 70, 35);
                       frameLayout.setBackgroundResource(defaults);
                       frameLayout.removeAllViews();

                       TextView textView =  new TextView(getContext());
                       textView.setText("Room:"+count+" Len:"+s+"ft Bre:"+bre+"ft Hall:"+hal);
                       frameLayout.addView(textView);
                       layout.addView(frameLayout);
                       count++;
                   }
                }

            }
        }


    }
    private void setFarmView() {
        if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Flat/Apartment")) {
            masterL.setVisibility(View.GONE);
            opensideL.setVisibility(View.GONE);
            plotareaL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("House")){
            masterL.setVisibility(View.GONE);
            faltonfloorL.setVisibility(View.GONE);
            plotareaL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Studio Apartment")){
            masterL.setVisibility(View.GONE);
            faltonfloorL.setVisibility(View.GONE);
            bedroomsL.setVisibility(View.GONE);
            opensideL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Villa")){
            masterL.setVisibility(View.GONE);
            faltonfloorL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Builder Floor Apartment")){
            masterL.setVisibility(View.GONE);
            opensideL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Penthouse")){
            masterL.setVisibility(View.GONE);
            faltonfloorL.setVisibility(View.GONE);
            plotareaL.setVisibility(View.GONE);
            opensideL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Duplex")){
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            faltonfloorL.setVisibility(View.GONE);
            opensideL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office Space")){
            shopL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            noflorL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Shop")){
            shopL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office IT Park/Shez")){
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            shopL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Showroom")){
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            receptionL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            doorL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Factory")){
            shopL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            receptionL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Warehouse/Godown")){
            shopL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            pantryL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
            possL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Plot Commercial")){
            shopL.setVisibility(View.GONE);
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            furnishedL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            addareaL.setVisibility(View.GONE);
            pantryL.setVisibility(View.GONE);
            receptionL.setVisibility(View.GONE);
            doorL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
            bclassL.setVisibility(View.GONE);
            possL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Building"))        {
            shopL.setVisibility(View.GONE);
            furnishedL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            receptionL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            allowedL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            pantryL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
            possL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Shed")){
            shopL.setVisibility(View.GONE);
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            furnishedL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
            receptionL.setVisibility(View.GONE);
            confresncL.setVisibility(View.GONE);
            officeL.setVisibility(View.GONE);
            venueL.setVisibility(View.GONE);
            doorL.setVisibility(View.GONE);
            vspaceL.setVisibility(View.GONE);
            pantryL.setVisibility(View.GONE);
            bclassL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Agriculture Land")) {
            roomsL.setVisibility(View.GONE);
            bedroomsL.setVisibility(View.GONE);
            becloniesL.setVisibility(View.GONE);
            noflorL.setVisibility(View.GONE);
            allfloorToME.setVisibility(View.GONE);
            furnishedL.setVisibility(View.GONE);
            washroomsL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            addareaL.setVisibility(View.GONE);
            possL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Commercial Land")){
            roomsL.setVisibility(View.GONE);
            bedroomsL.setVisibility(View.GONE);
            becloniesL.setVisibility(View.GONE);
            addareaL.setVisibility(View.GONE);
            bathRoomL.setVisibility(View.GONE);
            furnishedL.setVisibility(View.GONE);
            possL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Farm House")){
            roomsL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Hotel/Resort")){
            bedroomsL.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Banquet/Guest House")){
            bedroomsL.setVisibility(View.GONE);
            becloniesL.setVisibility(View.GONE);
            swashroomL.setVisibility(View.GONE);
        }
    }
    private void handlerVisiblesForm() {
        if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Flat/Apartment")|PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("House")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Studio Apartment")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Villa")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Builder Floor Apartment")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Penthouse")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Duplex")) {
            MAINRESI.setVisibility(View.VISIBLE);
            PLOTRESI.setVisibility(View.GONE);
            COMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Plot Residential")) {
            MAINRESI.setVisibility(View.GONE);
            PLOTRESI.setVisibility(View.VISIBLE);
            COMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.GONE);
        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office Space")|PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Shop")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Office IT Park/Shez")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Showroom")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Factory")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Warehouse/Godown")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Plot Commercial")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Building")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Industrial Shed")) {
            MAINRESI.setVisibility(View.GONE);
            PLOTRESI.setVisibility(View.GONE);
            COMERCIAL.setVisibility(View.VISIBLE);
            OTHER.setVisibility(View.GONE);

        }else if (PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Agriculture Land")|PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Commercial Land")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Farm House")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Hotel/Resort")
        |PrefMananger.getString(context, PrefMananger.RESIDENTIAL_KEY).equals("Banquet/Guest House")){
            MAINRESI.setVisibility(View.GONE);
            PLOTRESI.setVisibility(View.GONE);
            COMERCIAL.setVisibility(View.GONE);
            OTHER.setVisibility(View.VISIBLE);
        }
    }
    ArrayList<FloorPojo> floorPojos=new ArrayList<>();
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
    public void system(){

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.property_on_floor);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        ImageView imageView=dialog.findViewById(R.id.ivr1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==1){
                arrayList = data.getStringArrayListExtra("data");
                setFurnishing();
            }
        }
    }
    @SuppressLint("ResourceAsColor")
    private void setFurnishing() {
        if (!arrayList.isEmpty()){
            int defaults = R.drawable.employe_circle_rounded;
            int colar = R.color.black_font;
            for (String data :arrayList){
                FrameLayout a = new FrameLayout(getContext());
                TextView t = new TextView(getContext());
                t.setTextColor(colar);
                t.setText(data);
                a.addView(t);
                a.setBackgroundResource(defaults);
                a.setPadding(70, 30, 70, 30);

                LinearLayout l = (LinearLayout) scrollView.getChildAt(0);
                l.setPadding(20, 40, 20, 40);
                l.addView(a);
            }
        }else {
            LinearLayout l = (LinearLayout) scrollView.getChildAt(0);
            l.removeAllViews();
        }
    }
    public String date1() {
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
        return DATE;
    }
}
