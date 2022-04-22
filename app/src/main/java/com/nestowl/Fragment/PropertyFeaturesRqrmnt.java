package com.nestowl.Fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.nestowl.CommenDialog.AddDiolog;
import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.model.PostReqFeaturesModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.CongratulationPostReq;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyFeaturesRqrmnt extends Fragment{
    FrameLayout f8, BHK1,BHK2,BHK3,BHKAdd,Furnished, SemiFurnished,UnFurnished,FloorNone,Floor1,Floor2,Flooradd,TransResale,TransNew,DrectionsE,DrectionsW,DrectionsN,DrectionsS,
            DrectionsNE,DrectionsNW,NeedPossesionYes,NeedPossesionNo,JOBgov,JOBpvt,JOBbuss,JOBother,JOBnotsay,RessionResidence,RessionInvestment,
    CornerYes,CornerNo,ReceptionYes,ReceptionNo,ClassA,ClassB,ClassC,ClassNone,Age0,Age1,Age5,AgeAdd,
    BoundryYes,BoundryNo,MainRoadYes,MainRoadNo,WantBuyIM,WantBuyWait,AreaTypeSelectionMin,AreayTypeSelectionMax,
    CarpetChange,BuiltupChange,SuperChange;
    LinearLayout l3,l4,l5,cheakboxparent;
    CardView card_post;
    LinearLayout lnr_by,lnr_housee, com_office_space;
    String TypeText, requierd,DFTXT,ID,UserId,Username;
    TextView CarpetText, BultupText, SuperText,MinAreatext,MaxareaText, AddBhkTxt, AddAgeTxt, AddFlorTxt;
    EditText AreaMin, AreaMax,BudgetMin,BudgetMax,MinSeats,MaxSeats,CarpetAreaInput,BultupInput,SuperInput, ExtraComment;
    CheckBox checkBox;
    ScrollView scrollView;
    Integer callby,getCallby,Bhk,furnished,floor,trans,drection,possions,job,ression,corner,reception,clas,age,boundry,mainroad,want,area;
    LiveCommnication liveCommnication;

    public  PropertyFeaturesRqrmnt() {
        // Required empty public constructor
    }
    Context context;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_property_features_rqrmnt, container, false);
        context=activity=getActivity();
        TypeText=null;
        requierd="This Field is Required";
        DFTXT="+Add";
        callby=0;
        getCallby=0;
        Bhk=0;
        furnished=0;
        floor=0;
        trans=0;
        drection=0;
        possions=0;
        job=0;
        ression=0;
        corner=0;
        reception=0;
        clas=0;
        age=0;
        boundry=0;
        mainroad=0;
        want=0;
        area=0;
        card_post=view.findViewById(R.id.arshi_card_rew);
        lnr_housee=view.findViewById(R.id.linear_house);
        lnr_by=view.findViewById(R.id.linear_by_default);
        com_office_space=view.findViewById(R.id.linear_office_space);
        scrollView=view.findViewById(R.id.POST_REQ_SCROLL);
        scrollView.setSmoothScrollingEnabled(true);
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);

        handelView();
        if (lnr_by.getVisibility() == View.VISIBLE){
//           frames
            CornerYes=view.findViewById(R.id.POST_REQ_CORNER_YES);
            CornerNo=view.findViewById(R.id.LEADS_REQ_SHOW_CORNER_NO);
            TransResale=view.findViewById(R.id.POST_REQ_TRANSCTION_RESALE);
            TransNew=view.findViewById(R.id.POST_REQ_TRANSATION_NEW);
            BoundryYes=view.findViewById(R.id.POST_REQ__BOUNDRY_YES);
            BoundryNo=view.findViewById(R.id.POST_REQ_BOUNDRY_NO);
            MainRoadYes=view.findViewById(R.id.POST_REQ_MAIN_ROAD_YES);
            MainRoadNo=view.findViewById(R.id.POST_REQ_MAIN_ROAD_NO);
            DrectionsE=view.findViewById(R.id.POST_REQ_DRECTION_E);
            DrectionsW=view.findViewById(R.id.POST_REQ_DRECTION_W);
            DrectionsN=view.findViewById(R.id.POST_REQ_DRECTION_N);
            DrectionsS=view.findViewById(R.id.POST_REQ_DRECTION_S);
            WantBuyIM=view.findViewById(R.id.POST_REQ_WANT_FAST);
            WantBuyWait=view.findViewById(R.id.POST_REQ_WANT_SLOW);
            JOBgov=view.findViewById(R.id.POST_REQ_EMPLOYMENT_GOV);
            JOBpvt=view.findViewById(R.id.POST_REQ_EMPLOYMENT_PVT);
            JOBbuss=view.findViewById(R.id.POST_REQ_EMPLOYMENT_BUSS);
            JOBother=view.findViewById(R.id.POST_REQ_EMPLOYMENT_OTHER);
            JOBnotsay=view.findViewById(R.id.POST_REQ_EMPLOYMENT_NOTSAY);
            RessionResidence=view.findViewById(R.id.POST_REQ_RESSION_RASIDENCE);
            RessionInvestment=view.findViewById(R.id.POST_REQ_RESSION_INVESTMENT);
            AreaTypeSelectionMin=view.findViewById(R.id.POST_REQ_MIN_AREA_SELECTED);
            AreayTypeSelectionMax=view.findViewById(R.id.POST_REQ_MAX_AREA_SELECTOR);
//            inputs
            AreaMin=view.findViewById(R.id.POST_REQ_MIN_AREA);
            AreaMax=view.findViewById(R.id.POST_REQ_MAX_AREA_INPUT);
            BudgetMin=view.findViewById(R.id.POST_REQ_BUDGET_MIN);
            BudgetMax=view.findViewById(R.id.POST_REQ_BUDGET_MAX);
            ExtraComment=view.findViewById(R.id.POST_REQ_MORE_COMMENTS);
//            text
            MinAreatext=view.findViewById(R.id.POST_REQ_MIN_AREA_TEXT);
            MaxareaText=view.findViewById(R.id.POST_REQ_MAX_AREA_TEXT);
            handleGraphic();
//            logics
            CornerYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    corner=1;
                    handleGraphic();
                }
            });
            CornerNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    corner=2;
                    handleGraphic();
                }
            });
            TransResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=1;
                    handleGraphic();
                }
            });
            TransNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=2;
                    handleGraphic();
                }
            });
            BoundryYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boundry=1;
                    handleGraphic();
                }
            });
            BoundryNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boundry=2;
                    handleGraphic();
                }
            });
            MainRoadYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainroad=1;
                    handleGraphic();
                }
            });
            MainRoadNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainroad=2;
                    handleGraphic();
                }
            });
            DrectionsE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=1;
                    handleGraphic();
                }
            });
            DrectionsW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=2;
                    handleGraphic();
                }
            });
            DrectionsN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=3;
                    handleGraphic();
                }
            });
            DrectionsS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=4;
                    handleGraphic();
                }
            });
            JOBgov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=1;
                    handleGraphic();
                }
            });
            JOBpvt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=2;
                    handleGraphic();
                }
            });
            JOBbuss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=3;
                    handleGraphic();
                }
            });
            JOBother.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=4;
                    handleGraphic();
                }
            });
            JOBnotsay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=5;
                    handleGraphic();
                }
            });
            RessionResidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=1;
                    handleGraphic();
                }
            });
            RessionInvestment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=2;
                    handleGraphic();
                }
            });
            WantBuyIM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    want=1;
                    handleGraphic();
                }
            });
            WantBuyWait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    want=2;
                    handleGraphic();
                }
            });
            AreaTypeSelectionMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showType();
                    callby=1;
                }
            });
            AreayTypeSelectionMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showType();
                    callby=1;
                }
            });
            //validation
            card_post.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View v) {
                    if (AreaMin.getText() == null | AreaMin.getText().length() <=0){
                        AreaMin.setError(requierd);
                        scrollView.scrollToDescendant(AreaMin);
                        return;
                    }
                    if (AreaMax.getText()==null|AreaMax.getText().length() <= 0){
                        AreaMax.setError(requierd);
                        scrollView.scrollToDescendant(AreaMax);
                        return;
                    }
                    if (BudgetMin.getText() ==null|BudgetMin.getText().length()<=0){
                        BudgetMin.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMin);
                        return;
                    }
                    if (BudgetMax.getText() ==null|BudgetMax.getText().length()<=0){
                        BudgetMax.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMax);
                        return;
                    }
                    postFarm();
                }
            });

        }
        if (lnr_housee.getVisibility() == View.VISIBLE){
//            frames
            BHK1=view.findViewById(R.id.POST_REQ_HOME_1BHK);
            BHK2=view.findViewById(R.id.POST_REQ_HOME_2BHK);
            BHK3=view.findViewById(R.id.POST_REQ_HOME_3BHK);
            BHKAdd=view.findViewById(R.id.POST_REQ_HOME_ADD_BHK);
            Furnished=view.findViewById(R.id.POST_REQ_HOME_FURNISHED);
            SemiFurnished=view.findViewById(R.id.POST_REQ_HOME_F_SEMI);
            UnFurnished=view.findViewById(R.id.POST_REQ_HOME_F_NO);
            FloorNone=view.findViewById(R.id.POST_REQ_HOME_FLOOR_NONE);
            Floor1=view.findViewById(R.id.POST_REQ_HOME_FLOR_1);
            Floor2=view.findViewById(R.id.POST_REQ_HOME_FLOR_2);
            Flooradd=view.findViewById(R.id.POST_REQ_HOME_FLOR_ADD);
            TransResale=view.findViewById(R.id.POST_REQ_HOME_TRANS_RESALE);
            TransNew=view.findViewById(R.id.POST_REQ_HOME_TRANS_NEW);
            DrectionsE=view.findViewById(R.id.POST_REQ_HOME_DRECTION_E);
            DrectionsW=view.findViewById(R.id.POST_REQ_HOME_DRECTION_W);
            DrectionsN=view.findViewById(R.id.POST_REQ_HOME_DRECTION_N);
            DrectionsS=view.findViewById(R.id.POST_REQ_HOME_DRECTION_S);
            DrectionsNE=view.findViewById(R.id.POST_REQ_HOME_DRECTION_NE);
            DrectionsNW=view.findViewById(R.id.POST_REQ_HOME_DRECTION_NW);
            NeedPossesionYes=view.findViewById(R.id.POST_REQ_HOME_POSSESION_YES);
            NeedPossesionNo=view.findViewById(R.id.POST_REQ_HOME_POSSION_NO);
            JOBgov=view.findViewById(R.id.POST_REQ_HOME_JOB_GOV);
            JOBpvt=view.findViewById(R.id.POST_REQ_HOME_JOB_PVT);
            JOBbuss=view.findViewById(R.id.POST_REQ_HOME_JOB_BUSS);
            JOBother=view.findViewById(R.id.POST_REQ_HOME_JOB_OTHER);
            JOBnotsay=view.findViewById(R.id.POST_REQ_HOME_JOB_NOTSAY);
            RessionResidence=view.findViewById(R.id.POST_REQ_HOME_RESSION_RESIDENCE);
            RessionInvestment=view.findViewById(R.id.POST_REQ_HOME_JOB_RESSION_INVESTMENT);
            CarpetChange=view.findViewById(R.id.POST_REQ_HOME_SET_CARPET);
            BuiltupChange=view.findViewById(R.id.POST_REQ_HOME_BULTUP_SET);
            SuperChange=view.findViewById(R.id.POST_REQ_HOME_SET_SUPER_AREA);
//            inputs
            CarpetAreaInput=view.findViewById(R.id.POST_REQ_HOME_ADD_CARPET_IN);
            BultupInput=view.findViewById(R.id.POST_REQ_HOME_ADD_BULTUP_IN);
            SuperInput=view.findViewById(R.id.POST_REQ_HOME_SUPERAREA_IN);
            BudgetMin=view.findViewById(R.id.POST_REQ_HOME_BUDGET_MIN_IN);
            BudgetMax=view.findViewById(R.id.POST_REQ_HOME_BUDGET_MAX_IN);
            ExtraComment=view.findViewById(R.id.POST_REQ_HOME_MORE_COMMENTS);
//            texts
            CarpetText=view.findViewById(R.id.POST_REQ_HOME_SET_CARPET_TEXT);
            BultupText=view.findViewById(R.id.POST_REQ_HOME_ADD_BULTP_TEXT);
            SuperText=view.findViewById(R.id.POST_REQ_HOME_SUPER_TEXT);
            AddBhkTxt=view.findViewById(R.id.POST_REQ_HOME_ADD_BHK_TXT);
            AddFlorTxt=view.findViewById(R.id.POST_REQ_HOME_ADD_FLOOR_TXT);
//            checkBox
            cheakboxparent=view.findViewById(R.id.POST_REQ_HOME_CHEAKBOX);
            checkBox=view.findViewById(R.id.POST_REQ_HOME_BUDGET_ADJUST);
            handleGraphic();
            //logics
            BHK1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bhk=1;
                    handleGraphic();
                }
            });
            BHK2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bhk=2;
                    handleGraphic();
                }
            });
            BHK3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bhk=3;
                    handleGraphic();
                }
            });
            BHKAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bhk=4;
                    handleGraphic();
                }
            });
            Furnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=1;
                    handleGraphic();
                }
            });
            SemiFurnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=2;
                    handleGraphic();
                }
            });
            UnFurnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=3;
                    handleGraphic();
                }
            });
            FloorNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=3;
                    handleGraphic();
                }
            });
            Floor1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=1;
                    handleGraphic();
                }
            });
            Floor2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=2;
                    handleGraphic();
                }
            });
            Flooradd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=4;
                    handleGraphic();
                }
            });
            TransResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=1;
                    handleGraphic();
                }
            });
            TransNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=2;
                    handleGraphic();
                }
            });
            DrectionsE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=1;
                    handleGraphic();
                }
            });
            DrectionsW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=2;
                    handleGraphic();
                }
            });
            DrectionsN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=3;
                    handleGraphic();
                }
            });
            DrectionsS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=4;
                    handleGraphic();
                }
            });
            DrectionsNE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=5;
                    handleGraphic();
                }
            });
            DrectionsNW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=6;
                    handleGraphic();
                }
            });
            NeedPossesionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    possions=1;
                    handleGraphic();
                }
            });
            NeedPossesionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    possions=2;
                    handleGraphic();
                }
            });
            JOBgov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=1;
                    handleGraphic();
                }
            });
            JOBpvt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=2;
                    handleGraphic();
                }
            });
            JOBbuss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=3;
                    handleGraphic();
                }
            });
            JOBother.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=4;
                    handleGraphic();
                }
            });
            JOBnotsay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=5;
                    handleGraphic();
                }
            });
            RessionResidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=1;
                    handleGraphic();
                }
            });
            RessionInvestment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=2;
                    handleGraphic();
                }
            });
            CarpetChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callby=2;
                    showType();
                }
            });
            BuiltupChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callby=3;
                    showType();
                }
            });
            SuperChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callby=4;
                    showType();
                }
            });
            cheakboxparent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()){
                        checkBox.setChecked(false);
                    }else {
                        checkBox.setChecked(true);
                    }
                }
            });
            card_post.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View v) {
                    if (CarpetAreaInput.getText() == null | CarpetAreaInput.getText().length() <=0){
                        CarpetAreaInput.setError(requierd);
                        scrollView.scrollToDescendant(CarpetAreaInput);
                        return;
                    }
                    if (BultupInput.getText()==null|BultupInput.getText().length() <= 0){
                        BultupInput.setError(requierd);
                        scrollView.scrollToDescendant(BultupInput);
                        return;
                    }
                    if (SuperInput.getText()==null|SuperInput.getText().length() <= 0){
                        SuperInput.setError(requierd);
                        scrollView.scrollToDescendant(SuperInput);
                        return;
                    }
                    if (BudgetMin.getText() ==null|BudgetMin.getText().length()<=0){
                        BudgetMin.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMin);
                        return;
                    }
                    if (BudgetMax.getText() ==null|BudgetMax.getText().length()<=0){
                        BudgetMax.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMax);
                        return;
                    }
                   postFarm();
                }
            });

        }
        if (com_office_space.getVisibility() == View.VISIBLE){
//            frames
            FloorNone=view.findViewById(R.id.POST_REQ_OFFICE_FLOR_NONE);
            Floor1=view.findViewById(R.id.POST_REQ_OFFICE_FLOR_1);
            Floor2=view.findViewById(R.id.POST_REQ_OFFICE_FLOR_2);
            Flooradd=view.findViewById(R.id.POST_REQ_OFFICE_FLOR_ADD);
            CornerYes=view.findViewById(R.id.POST_REQ_OFFICE_CORNER_YES);
            CornerNo=view.findViewById(R.id.POST_REQ_OFFICE_CORNER_NO);
            ReceptionYes=view.findViewById(R.id.POST_REQ_OFFICE_RECEPTION_YES);
            ReceptionNo=view.findViewById(R.id.POST_REQ_OFFICE_RESCEPTION_NO);
            ClassA=view.findViewById(R.id.POST_REQ_OFFICE_CLASS_A);
            ClassB=view.findViewById(R.id.POST_REQ_OFFICE_CLASS_B);
            ClassC=view.findViewById(R.id.POST_REQ_OFFICE_CLASS_C);
            ClassNone=view.findViewById(R.id.POST_REQ_OFFICE_CLASS_NONE);
            TransResale=view.findViewById(R.id.POST_REQ_OFFICE_TRANS_RESALE);
            TransNew=view.findViewById(R.id.POST_REQ_OFFICE_TRANS_NEW);
            NeedPossesionYes=view.findViewById(R.id.POST_REQ_OFFICE_POSSAION_YES);
            NeedPossesionNo=view.findViewById(R.id.POST_REQ_OFFICE_POSSATIO_NO);
            Furnished=view.findViewById(R.id.POST_REQ_OFFICE_FURNISHED_YES);
            SemiFurnished=view.findViewById(R.id.POST_REQ_OFFICE_FURNISHED_SEMI);
            UnFurnished=view.findViewById(R.id.POST_REQ_OFFICE_FURNISHED_NO);
            DrectionsE=view.findViewById(R.id.POST_REQ_OFFICE_DRECTION_E);
            DrectionsW=view.findViewById(R.id.POST_REQ_OFFICE_DRECTION_W);
            DrectionsN=view.findViewById(R.id.POST_REQ_OFFICE_DRECTION_N);
            DrectionsS=view.findViewById(R.id.POST_REQ_OFFICE_DRECTION_S);
            Age0=view.findViewById(R.id.POST_REQ_OFFICE_AGE_0);
            Age1=view.findViewById(R.id.POST_REQ_OFFICE_AGE_1);
            Age5=view.findViewById(R.id.POST_REQ_OFFICE_AGE_5);
            AgeAdd=view.findViewById(R.id.POST_REQ_OFFICE_AGE_ADD);
            JOBgov=view.findViewById(R.id.POST_REQ_OFFICE_JOB_GOV);
            JOBpvt=view.findViewById(R.id.POST_REQ_OFFICE_JOB_PVT);
            JOBbuss=view.findViewById(R.id.POST_REQ_OFFICE_JOB_BUSS);
            JOBother=view.findViewById(R.id.POST_REQ_OFFICE_JOB_OTHER);
            JOBnotsay=view.findViewById(R.id.POST_REQ_OFFICE_JOB_NOTSAY);
            RessionResidence=view.findViewById(R.id.POST_REQ_OFFICE_RESSION_RESIDENCE);
            RessionInvestment=view.findViewById(R.id.POST_REQ_OFFICE_RESSION_INVESTMENT);
//            input
            AreaMin=view.findViewById(R.id.POST_REQ_OFFICE_AREA_MIN_IN);
            AreaMax=view.findViewById(R.id.POST_REQ_OFFICE_AREA_MAX_IN);
            BudgetMin=view.findViewById(R.id.POST_REQ_OFFICE_BUDGET_MIN_IN);
            BudgetMax=view.findViewById(R.id.POST_REQ_OFFICE_BUDGET_MAX_IN);
            MinSeats=view.findViewById(R.id.POST_REQ_OFFICE_OFFICESETUP_MIN_SEATS);
            MaxSeats=view.findViewById(R.id.POST_REQ_OFFICE_SETUP_MAC_SEAT);
            ExtraComment=view.findViewById(R.id.POST_REQ_OFFICE_EXTRA_COMMENT);
            //txt
            AddFlorTxt=view.findViewById(R.id.POST_REQ_OFFICE_ADD_FLOOR_TXT);
            AddAgeTxt=view.findViewById(R.id.POST_REQ_OFFICE_ADD_AGE_TXT);

//            cheaks
            cheakboxparent=view.findViewById(R.id.POST_REQ_OFFICE_CAN_ADJUST);
            checkBox=view.findViewById(R.id.POST_REQ_OFFICE_CAN_CHEACK);
            handleGraphic();
//            logics
            CornerYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    corner=1;
                    handleGraphic();
                }
            });
            CornerNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    corner=2;
                    handleGraphic();
                }
            });
            Furnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=1;
                    handleGraphic();
                }
            });
            SemiFurnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=2;
                    handleGraphic();
                }
            });
            UnFurnished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    furnished=3;
                    handleGraphic();
                }
            });
            FloorNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=3;
                    handleGraphic();
                }
            });
            Floor1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=1;
                    handleGraphic();
                }
            });
            Floor2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=2;
                    handleGraphic();
                }
            });
            Flooradd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    floor=4;
                    handleGraphic();
                }
            });
            TransResale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=1;
                    handleGraphic();
                }
            });
            TransNew.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    trans=2;
                    handleGraphic();
                }
            });
            DrectionsE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=1;
                    handleGraphic();
                }
            });
            DrectionsW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=2;
                    handleGraphic();
                }
            });
            DrectionsN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=3;
                    handleGraphic();
                }
            });
            DrectionsS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drection=4;
                    handleGraphic();
                }
            });
            NeedPossesionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    possions=1;
                    handleGraphic();
                }
            });
            NeedPossesionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    possions=2;
                    handleGraphic();
                }
            });
            JOBgov.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=1;
                    handleGraphic();
                }
            });
            JOBpvt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=2;
                    handleGraphic();
                }
            });
            JOBbuss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=3;
                    handleGraphic();
                }
            });
            JOBother.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=4;
                    handleGraphic();
                }
            });
            JOBnotsay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    job=5;
                    handleGraphic();
                }
            });
            RessionResidence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=1;
                    handleGraphic();
                }
            });
            RessionInvestment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ression=2;
                    handleGraphic();
                }
            });
            ReceptionYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reception=1;
                    handleGraphic();
                }
            });
            ReceptionNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reception=2;
                    handleGraphic();
                }
            });
            ClassA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clas=1;
                    handleGraphic();
                }
            });
            ClassB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clas=2;
                    handleGraphic();
                }
            });
            ClassC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clas=3;
                    handleGraphic();
                }
            });
            ClassNone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clas=4;
                    handleGraphic();
                }
            });
            Age0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age=1;
                    handleGraphic();
                }
            });
            Age1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age=2;
                    handleGraphic();
                }
            });
            Age5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age=3;
                    handleGraphic();
                }
            });
            AgeAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    age=4;
                    handleGraphic();
                }
            });
            cheakboxparent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()){
                        checkBox.setChecked(false);
                    }else {
                        checkBox.setChecked(true);
                    }
                }
            });
            card_post.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.Q)
                @Override
                public void onClick(View v) {
                    if (AreaMin.getText() == null | AreaMin.getText().length() <=0){
                        AreaMin.setError(requierd);
                        scrollView.scrollToDescendant(AreaMin);
                        return;
                    }
                    if (AreaMax.getText()==null|AreaMax.getText().length() <= 0){
                        AreaMax.setError(requierd);
                        scrollView.scrollToDescendant(AreaMax);
                        return;
                    }
                    if (MinSeats.getText()==null|MinSeats.getText().length() <= 0){
                        MinSeats.setError(requierd);
                        scrollView.scrollToDescendant(MinSeats);
                        return;
                    }
                    if (MaxSeats.getText()==null|MaxSeats.getText().length() <= 0){
                        MaxSeats.setError(requierd);
                        scrollView.scrollToDescendant(MaxSeats);
                        return;
                    }
                    if (BudgetMin.getText() ==null|BudgetMin.getText().length()<=0){
                        BudgetMin.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMin);
                        return;
                    }
                    if (BudgetMax.getText() ==null|BudgetMax.getText().length()<=0){
                        BudgetMax.setError(requierd);
                        scrollView.scrollToDescendant(BudgetMax);
                        return;
                    }
                    postFarm();
                }
            });

        }
        return view;
    }
    private void postFarm() {
       ProgressDialog pd =  new ProgressDialog(getContext());
       pd.setMessage("Saving...");
       pd.setCancelable(false);
       pd.show();
        PostReqFeaturesModal postData = new PostReqFeaturesModal();
        if (lnr_by.getVisibility() == View.VISIBLE){
//            inputs
            postData.setArea_min(AreaMin.getText().toString());
            postData.setArea_min_mezor(MinAreatext.getText().toString());
            postData.setArea_max(AreaMax.getText().toString());
            postData.setArea_max_mezor(MaxareaText.getText().toString());
            if (corner==1){
                postData.setCorner_property_prefference("yes");
            }
            if (corner==2){
                postData.setCorner_property_prefference("No");
            }
            if (trans==1){
                postData.setTransaction_type("Resale");
            }
            if (trans==2){
                postData.setTransaction_type("New Property");
            }
            if (boundry==1){
                postData.setBoundary_wall_made("Yes");
            }
            if (boundry==2){
                postData.setBoundary_wall_made("No");
            }
            if (mainroad==1){
                postData.setMain_road_property_prefference("Yes");
            }
            if (mainroad==2){
                postData.setMain_road_property_prefference("No");
            }
            if (drection==1){
                postData.setDirection_prefference("East");
            }
            if (drection==2){
                postData.setDirection_prefference("West");
            }
            if (drection==3){
                postData.setDirection_prefference("North");
            }
            if (drection==4){
                postData.setDirection_prefference("South");
            }
            if (want==1){
                postData.setNeed_possession("Soon as Possible");
            }
            if (want==2){
                postData.setNeed_possession("Can Wait For better deal");
            }
        }
        if (lnr_housee.getVisibility() == View.VISIBLE){
            postData.setCaptur_area_max(CarpetAreaInput.getText().toString());
            postData.setBuildup_area_max(BultupInput.getText().toString());
            postData.setSuper_area_max(SuperInput.getText().toString());
            postData.setCaptur_area_max_mezor(CarpetText.getText().toString());
            postData.setBuildup_area_max_mezor(BultupText.getText().toString());
            postData.setSuper_area_max_mezor(SuperText.getText().toString());
            if (furnished==1){
                postData.setFurnisging_prefference("Furnished");
            }
            if (furnished==2){
                postData.setFurnisging_prefference("SemiFurnished");
            }
            if (furnished==3){
                postData.setFurnisging_prefference("UnFurnished");
            }
            if (Bhk==1){
                postData.setBhk_type("1BHK");
            }
            if (Bhk==2){
                postData.setBhk_type("2BHK");
            }
            if (Bhk==3){
                postData.setBhk_type("3BHK");
            }
            if (Bhk==5){
                postData.setBhk_type(AddBhkTxt.getText().toString());
            }
            if (floor==1){
                postData.setFloor_prefference("None");
            }
            if (floor==2){
                postData.setFloor_prefference("1Floor");
            }
            if (floor==3){
                postData.setFloor_prefference("2Floor");
            }
            if (floor==5){
                postData.setFloor_prefference(AddFlorTxt.getText().toString());
            }
            if (drection==1){
                postData.setDirection_prefference("East");
            }
            if (drection==2){
                postData.setDirection_prefference("West");
            }
            if (drection==3){
                postData.setDirection_prefference("North");
            }
            if (drection==4){
                postData.setDirection_prefference("South");
            }
            if (drection==5){
                postData.setDirection_prefference("North-East");
            }
            if (drection==6){
                postData.setDirection_prefference("North-West");
            }
            if (want==1){
                postData.setNeed_possession("Soon as Possible");
            }
            if (want==2){
                postData.setNeed_possession("Can Wait For better deal");
            }
            if (checkBox.isChecked()){
                postData.setAdjest_for_builder_property("Yes");
            }
        }
        if (com_office_space.getVisibility() == View.VISIBLE){
            postData.setArea_min(AreaMin.getText().toString());
            postData.setArea_min_mezor(MinAreatext.getText().toString());
            postData.setArea_max(AreaMax.getText().toString());
            postData.setArea_max_mezor(MaxareaText.getText().toString());
            postData.setOffice_min_no_seat(MinSeats.getText().toString());
            postData.setOffice_max_no_seat(MaxSeats.getText().toString());
            if (furnished==1){
                postData.setFurnisging_prefference("Furnished");
            }
            if (furnished==2){
                postData.setFurnisging_prefference("SemiFurnished");
            }
            if (furnished==3){
                postData.setFurnisging_prefference("UnFurnished");
            }
            if (corner==1){
                postData.setCorner_property_prefference("yes");
            }
            if (corner==2){
                postData.setCorner_property_prefference("No");
            }
            if (drection==1){
                postData.setDirection_prefference("East");
            }
            if (drection==2){
                postData.setDirection_prefference("West");
            }
            if (drection==3){
                postData.setDirection_prefference("North");
            }
            if (drection==4){
                postData.setDirection_prefference("South");
            }
            if (want==1){
                postData.setNeed_possession("Soon as Possible");
            }
            if (want==2){
                postData.setNeed_possession("Can Wait For better deal");
            }
            if (trans==1){
                postData.setTransaction_type("Resale");
            }
            if (trans==2){
                postData.setTransaction_type("New Property");
            }
            if (floor==1){
                postData.setFloor_prefference("None");
            }
            if (floor==2){
                postData.setFloor_prefference("1Floor");
            }
            if (floor==3){
                postData.setFloor_prefference("2Floor");
            }
            if (floor==5){
                postData.setFloor_prefference(AddFlorTxt.getText().toString());
            }
            if (reception==1){
                postData.setReception_area_prefference("Yes");
            }
            if (reception==2){
                postData.setReception_area_prefference("No");
            }
            if (clas==1){
                postData.setBuilding_class_prefference("A+");
            }
            if (clas==2){
                postData.setBuilding_class_prefference("B");
            }
            if (clas==3){
                postData.setBuilding_class_prefference("C");
            }
            if (clas==4){
                postData.setBuilding_class_prefference("None");
            }
            if (age==1){
                postData.setAge_of_construction("0-1yrs");
            }
            if (age==2){
                postData.setAge_of_construction("1-5yrs");
            }
            if (age==3){
                postData.setAge_of_construction("5-10yrs");
            }
            if (age==5){
                postData.setAge_of_construction(AddAgeTxt.getText().toString());
            }
            if (checkBox.isChecked()){
                postData.setAdjest_for_builder_property("Yes");
            }


        }

        if (job==1){
            postData.setNature_of_employment("Gov");
        }
        if (job==2){
            postData.setNature_of_employment("PVT");
        }
        if (job==3){
            postData.setNature_of_employment("Businessman");
        }
        if (job==4){
            postData.setNature_of_employment("Other");
        }
        if (job==5){
            postData.setNature_of_employment("Not want to say");
        }
        if (ression==1){
            postData.setReason_for_buying("Residence");
        }
        if (ression==2){
            postData.setReason_for_buying("Investment");
        }
        postData.setBudget_min(BudgetMin.getText().toString());
        postData.setBudget_max(BudgetMax.getText().toString());
        if (ExtraComment.getText() !=null|ExtraComment.getText().length()>=1){
            postData.setAdditional_requirement_details(ExtraComment.getText().toString());
        }else {
            postData.setAdditional_requirement_details("No Extra Comments");
        }
//        xtra Filds
//        postData.setNumber_of_seats("extra field");
//        postData.setNumber_meeting_rooms("extra field");
//        postData.setOpen_hours("extra field");
//        postData.setLock_in_period("extra field");
//        postData.setCaptur_area_min("extra field");
//        postData.setCaptur_area_min_mezor("extra field");
//        postData.setBuildup_area_min("extra field");
//        postData.setBuildup_area_min_mezor("extra field");
//        postData.setSuper_area_min("extra field");
//        postData.setSuper_area_min_mezor("extra field");
//        postData.setProperypost("extra field");
        ArrayList<String> datas = new ArrayList<>();
        datas.add("Extra");
        datas.add("Field");
        postData.setArea_type(datas);
        postData.setFurnishings(datas);
        aichat ns = new aichat();
        ns.setText("getType");
        ns.setValue(null);
        ns.setValues(null);
        liveCommnication.setText(ns);
        liveCommnication.getText().observe(getActivity(), new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichats) {
                aichat d =  new aichat();
                d =  aichats;
                if (d.getText().equals("getOk")){
                    postData.setProperypost(d.getValue());
                    aichat n = new aichat();
                    n.setText("get");
                    n.setValue(null);
                    n.setValues(null);
                    liveCommnication.setText(n);
                }
                if (d.getText().equals("ok")){
                    postData.setRequirement_id(d.getValue());
                    postData.setUser_id(d.getValues());
                }else {
                    Toast.makeText(getActivity(), "Try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_REQUREMENT_FEATURES, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o =  new JSONObject(response);
                            String s = o.getString("status");
                            if (s.equals("1")){
                                pd.cancel();
                                startActivity(new Intent(getContext(), CongratulationPostReq.class));
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
                      Map<String,String> data =  new HashMap<String, String>();
                        Field[] fields = postData.getClass().getDeclaredFields();
                        for (Field f:fields){
                            f.setAccessible(true);
                            try {
                                String value = (String) f.get(postData);
                                if (value==null){
                                    value="UnSelected";
                                }
                                data.put(f.getName(), value);
                            }catch (Exception e){

                            }

                        }
                        return data;
                    }
                };
                Volley.newRequestQueue(getContext()).add(request);
            }
        });

    }
    private void handleGraphic() {
        int defoult = R.drawable.employe_circle_rounded;
        int active = R.drawable.selected_background_filter;
        if (Bhk==1){
            BHK1.setBackgroundResource(active);
            BHK2.setBackgroundResource(defoult);
            BHK3.setBackgroundResource(defoult);
            BHKAdd.setBackgroundResource(defoult);
            AddBhkTxt.setText(DFTXT);
        }
        if (Bhk==2){
            BHK1.setBackgroundResource(defoult);
            BHK2.setBackgroundResource(active);
            BHK3.setBackgroundResource(defoult);
            BHKAdd.setBackgroundResource(defoult);
            AddBhkTxt.setText(DFTXT);
        }
        if (Bhk==3){
            BHK1.setBackgroundResource(defoult);
            BHK2.setBackgroundResource(defoult);
            BHK3.setBackgroundResource(active);
            BHKAdd.setBackgroundResource(defoult);
            AddBhkTxt.setText(DFTXT);
        }
        if (Bhk==4){
            BHK1.setBackgroundResource(defoult);
            BHK2.setBackgroundResource(defoult);
            BHK3.setBackgroundResource(defoult);
            BHKAdd.setBackgroundResource(active);
            new AddDiolog(context, new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    AddBhkTxt.setText(value+"BHK");
                    Bhk=5;
                }
            });
        }
        if (furnished==1){
            Furnished.setBackgroundResource(active);
            SemiFurnished.setBackgroundResource(defoult);
            UnFurnished.setBackgroundResource(defoult);
        }
        if (furnished==2){
            Furnished.setBackgroundResource(defoult);
            SemiFurnished.setBackgroundResource(active);
            UnFurnished.setBackgroundResource(defoult);
        }
        if (furnished==3){
            Furnished.setBackgroundResource(defoult);
            SemiFurnished.setBackgroundResource(defoult);
            UnFurnished.setBackgroundResource(active);
        }
        if (floor==1){
            Floor1.setBackgroundResource(active);
            Floor2.setBackgroundResource(defoult);
            FloorNone.setBackgroundResource(defoult);
            Flooradd.setBackgroundResource(defoult);
            AddFlorTxt.setText(DFTXT);

        }
        if (floor==2){
            Floor1.setBackgroundResource(defoult);
            Floor2.setBackgroundResource(active);
            FloorNone.setBackgroundResource(defoult);
            Flooradd.setBackgroundResource(defoult);
            AddFlorTxt.setText(DFTXT);

        }
        if (floor==3){
            Floor1.setBackgroundResource(defoult);
            Floor2.setBackgroundResource(defoult);
            FloorNone.setBackgroundResource(active);
            Flooradd.setBackgroundResource(defoult);
            AddFlorTxt.setText(DFTXT);

        }
        if (floor==4){
            Floor1.setBackgroundResource(defoult);
            Floor2.setBackgroundResource(defoult);
            FloorNone.setBackgroundResource(defoult);
            Flooradd.setBackgroundResource(active);
            new AddDiolog(context, new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    AddFlorTxt.setText(value);
                    floor=5;
                }
            });

        }
        if (trans==1){
            TransResale.setBackgroundResource(active);
            TransNew.setBackgroundResource(defoult);
        }
        if (trans==2){
            TransResale.setBackgroundResource(defoult);
            TransNew.setBackgroundResource(active);
        }
        if (drection==1){
            DrectionsE.setBackgroundResource(active);
            DrectionsW.setBackgroundResource(defoult);
            DrectionsN.setBackgroundResource(defoult);
            DrectionsS.setBackgroundResource(defoult);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(defoult);
                DrectionsNW.setBackgroundResource(defoult);
            }

        }
        if (drection==2){
            DrectionsE.setBackgroundResource(defoult);
            DrectionsW.setBackgroundResource(active);
            DrectionsN.setBackgroundResource(defoult);
            DrectionsS.setBackgroundResource(defoult);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(defoult);
                DrectionsNW.setBackgroundResource(defoult);
            }

        }
        if (drection==3){
            DrectionsE.setBackgroundResource(defoult);
            DrectionsW.setBackgroundResource(defoult);
            DrectionsN.setBackgroundResource(active);
            DrectionsS.setBackgroundResource(defoult);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(defoult);
                DrectionsNW.setBackgroundResource(defoult);
            }

        }
        if (drection==4){
            DrectionsE.setBackgroundResource(defoult);
            DrectionsW.setBackgroundResource(defoult);
            DrectionsN.setBackgroundResource(defoult);
            DrectionsS.setBackgroundResource(active);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(defoult);
                DrectionsNW.setBackgroundResource(defoult);
            }

        }
        if (drection==5){
            DrectionsE.setBackgroundResource(defoult);
            DrectionsW.setBackgroundResource(defoult);
            DrectionsN.setBackgroundResource(defoult);
            DrectionsS.setBackgroundResource(defoult);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(active);
                DrectionsNW.setBackgroundResource(defoult);
            }

        }
        if (drection==6){
            DrectionsE.setBackgroundResource(defoult);
            DrectionsW.setBackgroundResource(defoult);
            DrectionsN.setBackgroundResource(defoult);
            DrectionsS.setBackgroundResource(defoult);
            if (DrectionsNW != null|DrectionsNE != null){
                DrectionsNE.setBackgroundResource(defoult);
                DrectionsNW.setBackgroundResource(active);
            }

        }
        if (possions==1){
            NeedPossesionYes.setBackgroundResource(active);
            NeedPossesionNo.setBackgroundResource(defoult);
        }
        if (possions==2){
            NeedPossesionYes.setBackgroundResource(defoult);
            NeedPossesionNo.setBackgroundResource(active);
        }
        if (job==1){
            JOBgov.setBackgroundResource(active);
            JOBpvt.setBackgroundResource(defoult);
            JOBbuss.setBackgroundResource(defoult);
            JOBother.setBackgroundResource(defoult);
            JOBnotsay.setBackgroundResource(defoult);
        }
        if (job==2){
            JOBgov.setBackgroundResource(defoult);
            JOBpvt.setBackgroundResource(active);
            JOBbuss.setBackgroundResource(defoult);
            JOBother.setBackgroundResource(defoult);
            JOBnotsay.setBackgroundResource(defoult);
        }
        if (job==3){
            JOBgov.setBackgroundResource(defoult);
            JOBpvt.setBackgroundResource(defoult);
            JOBbuss.setBackgroundResource(active);
            JOBother.setBackgroundResource(defoult);
            JOBnotsay.setBackgroundResource(defoult);
        }
        if (job==4){
            JOBgov.setBackgroundResource(defoult);
            JOBpvt.setBackgroundResource(defoult);
            JOBbuss.setBackgroundResource(defoult);
            JOBother.setBackgroundResource(active);
            JOBnotsay.setBackgroundResource(defoult);
        }
        if (job==5){
            JOBgov.setBackgroundResource(defoult);
            JOBpvt.setBackgroundResource(defoult);
            JOBbuss.setBackgroundResource(defoult);
            JOBother.setBackgroundResource(defoult);
            JOBnotsay.setBackgroundResource(active);
        }
        if (ression==1){
            RessionResidence.setBackgroundResource(active);
            RessionInvestment.setBackgroundResource(defoult);
        }
        if (ression==2){
            RessionResidence.setBackgroundResource(defoult);
            RessionInvestment.setBackgroundResource(active);
        }
        if (corner==1){
            CornerYes.setBackgroundResource(active);
            CornerNo.setBackgroundResource(defoult);
        }
        if (corner==2){
            CornerYes.setBackgroundResource(defoult);
            CornerNo.setBackgroundResource(active);
        }
        if (reception==1){
            ReceptionYes.setBackgroundResource(active);
            ReceptionNo.setBackgroundResource(defoult);
        }
        if (reception==2){
            ReceptionYes.setBackgroundResource(defoult);
            ReceptionNo.setBackgroundResource(active);
        }
        if (clas==1){
            ClassA.setBackgroundResource(active);
            ClassB.setBackgroundResource(defoult);
            ClassC.setBackgroundResource(defoult);
            ClassNone.setBackgroundResource(defoult);
        }
        if (clas==2){
            ClassA.setBackgroundResource(defoult);
            ClassB.setBackgroundResource(active);
            ClassC.setBackgroundResource(defoult);
            ClassNone.setBackgroundResource(defoult);
        }
        if (clas==3){
            ClassA.setBackgroundResource(defoult);
            ClassB.setBackgroundResource(defoult);
            ClassC.setBackgroundResource(active);
            ClassNone.setBackgroundResource(defoult);
        }
        if (clas==4){
            ClassA.setBackgroundResource(defoult);
            ClassB.setBackgroundResource(defoult);
            ClassC.setBackgroundResource(defoult);
            ClassNone.setBackgroundResource(active);
        }
        if (age==1){
            Age0.setBackgroundResource(active);
            Age1.setBackgroundResource(defoult);
            Age5.setBackgroundResource(defoult);
            AgeAdd.setBackgroundResource(defoult);
            AddAgeTxt.setText(DFTXT);
        }
        if (age==2){
            Age0.setBackgroundResource(defoult);
            Age1.setBackgroundResource(active);
            Age5.setBackgroundResource(defoult);
            AgeAdd.setBackgroundResource(defoult);
            AddAgeTxt.setText(DFTXT);
        }
        if (age==3){
            Age0.setBackgroundResource(defoult);
            Age1.setBackgroundResource(defoult);
            Age5.setBackgroundResource(active);
            AgeAdd.setBackgroundResource(defoult);
            AddAgeTxt.setText(DFTXT);
        }
        if (age==4){
            Age0.setBackgroundResource(defoult);
            Age1.setBackgroundResource(defoult);
            Age5.setBackgroundResource(defoult);
            AgeAdd.setBackgroundResource(active);
            AddDiolog d = new AddDiolog(context, new AddDiolog.onClickDio() {
                @Override
                public void getData(String value) {
                    AddAgeTxt.setText(value+"yrs");
                    age=5;
                }
            });

        }
        if (boundry==1){
            BoundryYes.setBackgroundResource(active);
            BoundryNo.setBackgroundResource(defoult);
        }
        if (boundry==2){
            BoundryYes.setBackgroundResource(defoult);
            BoundryNo.setBackgroundResource(active);
        }
        if (mainroad==1){
            MainRoadYes.setBackgroundResource(active);
            MainRoadNo.setBackgroundResource(defoult);
        }
        if (mainroad==2){
            MainRoadYes.setBackgroundResource(defoult);
            MainRoadNo.setBackgroundResource(active);
        }
        if (want==1){
            WantBuyIM.setBackgroundResource(active);
            WantBuyWait.setBackgroundResource(defoult);
        }
        if (want==2){
            WantBuyIM.setBackgroundResource(defoult);
            WantBuyWait.setBackgroundResource(active);
        }

    }
    private void handelView() {
        lnr_by.setVisibility(View.VISIBLE);
        if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Flat/Apartment"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        } else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("House"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Studio Apartment"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Villa"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Builder Floor Apartment"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Plot Residential"))
        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.VISIBLE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Penthouse"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Duplex"))
        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Office Space"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Shop"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Office IT Park/Shez"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Showroom"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Factory"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Warehouse/Godown"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Plot Commercial"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.VISIBLE);
            com_office_space.setVisibility(View.GONE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Industrial Building"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Industrial Shed"))

        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.VISIBLE);
        }
        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Agriculture Land"))
        {
            lnr_housee.setVisibility(View.GONE);
            lnr_by.setVisibility(View.VISIBLE);
            com_office_space.setVisibility(View.GONE);
        }

        else if (PrefMananger.getString(context,PrefMananger.RESIDENTIAL_KEY).equals("Commercial Land"))

        {
            lnr_housee.setVisibility(View.VISIBLE);
            lnr_by.setVisibility(View.GONE);
            com_office_space.setVisibility(View.GONE);
        }
    }
    void showType() {
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
                TypeText=text;
                if (callby==1){
                    MinAreatext.setText(TypeText);
                    MaxareaText.setText(TypeText);
                }
                if (callby==2){
                    CarpetText.setText(TypeText);
                }
                if (callby==3){
                    BultupText.setText(TypeText);
                }
                if (callby==4){
                    SuperText.setText(TypeText);
                }
            }
        },floorPojos);

    }
    ArrayList<FloorPojo> floorPojos=new ArrayList<>();
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
}
