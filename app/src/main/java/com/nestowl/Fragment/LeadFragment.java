package com.nestowl.Fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.nestowl.AdapterClass.AlertLeadsAdater;
import com.nestowl.AdapterClass.LocalitiesdialogAdapter;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.AlertLeadsViewModal;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.LeadsIdianHerosInfo;
import com.nestowl.model.LeadsProjectModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LocalitiesDioModal;
import com.nestowl.model.LocalitiesModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProjectInfoModal;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.User;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadFragment extends Fragment {
    FrameLayout f1_wokk_location,f2_proposal_accepted,f3_lead_type,f5_time;
    RecyclerView publicLead,indianHerosLeads,dubbleLeads,projectLead;
    ArrayList<AlertLeadsViewModal> publicLeadArrey,indianHerosLeadsArrey,dubbleLeadsArrey,projectLeadArrey,publicLeadArrey2,indianHerosLeadsArrey2,dubbleLeadsArrey2,projectLeadArrey2;
    ArrayList<LeadsPublicPro> leadsPublicPros, leadsPublicProsMain;
    ArrayList<LeadsRequmentsModal> leadsRequmentsModals;
    AlertLeadsAdater adater;
    LoginPojo loginPojo;
    HashMap<String,String> hashMap;
    ArrayList<LocalitiesDioModal> localitiesModals;
    String userId,timeS,proposalS,typeS;
    ConstraintLayout noData;
    ArrayList<String>allLeadsId;
    int leadsType,proposalInt,timeL;
    int def = R.drawable.employe_circle_rounded;
    int act = R.drawable.selected_background_filter;
    ArrayList<AllAcceptedDataModal> propertyAcceptedData,reqAcceptedData;
    public LeadFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_lead, container, false);
        projectLead=view.findViewById(R.id.ALERT_LEADS_PROJECT_RECYCLER);
        publicLead=view.findViewById(R.id.ALERT_LEADS_PUBLIC_RECYLER);
        indianHerosLeads=view.findViewById(R.id.ALERT_LEADS_INDIAN_HEROS_RECYCLER);
        dubbleLeads=view.findViewById(R.id.ALERT_LEADS_DUBBLE_RECYCLER);
        noData=view.findViewById(R.id.ALL_NO_DATA_SHOW);

        publicLeadArrey=new ArrayList<>();
        indianHerosLeadsArrey=new ArrayList<>();
        dubbleLeadsArrey=new ArrayList<>();
        projectLeadArrey=new ArrayList<>();
        leadsPublicPros=new ArrayList<>();
        leadsPublicProsMain=new ArrayList<>();
        leadsRequmentsModals=new ArrayList<>();
        localitiesModals  =new ArrayList<>();
        allLeadsId  =new ArrayList<>();
        propertyAcceptedData  =new ArrayList<>();
        reqAcceptedData  =new ArrayList<>();
        leadsType=0;
        timeS="Time";
        proposalS="Proposal Status";
        typeS="Lead Type";

        projectLead.setLayoutManager(new LinearLayoutManager(getContext()));
        publicLead.setLayoutManager(new LinearLayoutManager(getContext()));
        indianHerosLeads.setLayoutManager(new LinearLayoutManager(getContext()));
        dubbleLeads.setLayoutManager(new LinearLayoutManager(getContext()));

        loginPojo = PrefMananger.GetLoginData(getContext());
        userId=String.valueOf(loginPojo.getUserId());


        getWorkLocaliies();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setRecycler();
            }
        };
        handler.postDelayed(runnable,4000);

        f1_wokk_location=view.findViewById(R.id.ALERT_LEADS_WORK_LOCATION);
        f2_proposal_accepted=view.findViewById(R.id.ALERT_LEADS_STATUS);
        f3_lead_type=view.findViewById(R.id.ALERT_LEADS_TYPE);
        f5_time=view.findViewById(R.id.ALERT_LEADS_TIME);
        f1_wokk_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog= new Dialog(getContext());
                dialog.setContentView(R.layout.work_location);
                String data = PrefMananger.getString(getContext(),PrefMananger.ARREY_LOCALTIES);
                if (data!=null){
                    try {
                        localitiesModals= new ArrayList<>();
                        JSONArray jsonArray  = new JSONArray(data);
                        for (int i=0;i<jsonArray.length();i++){
                            LocalitiesDioModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LocalitiesDioModal.class);
                            localitiesModals.add(d);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                FrameLayout apply,clear;
                RecyclerView recyclerView;
                apply=dialog.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_APPLY);
                clear=dialog.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_CLEAR_ALL);
                recyclerView=dialog.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_RECYLCER);
                LocalitiesdialogAdapter adapter =  new LocalitiesdialogAdapter(getContext(),localitiesModals);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                recyclerView.setAdapter(adapter);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                FrameLayout iv_c = dialog.findViewById(R.id.iv_cancel_9);
                dialog.show();
                iv_c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handlelocalitiesFilter(localitiesModals);
                        dialog.dismiss();
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<LocalitiesDioModal> da =  new ArrayList<>();
                        for (LocalitiesDioModal s : localitiesModals){
                            s.setSelected("false");
                            da.add(s);
                        }
                        String ss = new Gson().toJson(da);
                        PrefMananger.saveString(getContext(),PrefMananger.ARREY_LOCALTIES,ss);
                        handlelocalitiesFilter(da);
                        dialog.dismiss();
                    }
                });
            }
        });
        f3_lead_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(getContext());
                dialog.setContentView(R.layout.lead_type_broker);
                FrameLayout publics,indian,dubble,project,apply,clear;
                publics=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_PUBLIC);
                indian=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_INDIAN_HEROS);
                dubble=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_DUBBLE_LEADS);
                project=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_PROJECT);
                apply=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_APPLY);
                clear=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_TYPE_CLEAR);
                TextView textView =  (TextView) f3_lead_type.getChildAt(0);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        textView.setText(typeS);
                    }
                });

                if (leadsType==1){
                    publics.setBackgroundResource(act);
                }
                if (leadsType==2){
                    indian.setBackgroundResource(act);
                }
                if (leadsType==3){
                    dubble.setBackgroundResource(act);
                }
                if (leadsType==4){
                    project.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                ImageView iv_c = dialog.findViewById(R.id.iv_six);
                dialog.show();
                iv_c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leadsType=0;
                        handlerLeadsTypes();
                        typeS="Lead Type";
                        textView.setText(typeS);
                        dialog.dismiss();
                    }
                });
                publics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(act);
                        indian.setBackgroundResource(def);
                        dubble.setBackgroundResource(def);
                        project.setBackgroundResource(def);
                        leadsType=1;
                        typeS="Public";

                    }
                });
                indian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(def);
                        indian.setBackgroundResource(act);
                        dubble.setBackgroundResource(def);
                        project.setBackgroundResource(def);
                        leadsType=2;
                        typeS="Indian Hero";

                    }
                });
                dubble.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(def);
                        indian.setBackgroundResource(def);
                        dubble.setBackgroundResource(act);
                        project.setBackgroundResource(def);
                        leadsType=3;
                        typeS="Dubble";

                    }
                });
                project.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publics.setBackgroundResource(def);
                        indian.setBackgroundResource(def);
                        dubble.setBackgroundResource(def);
                        project.setBackgroundResource(act);
                        leadsType=4;
                        typeS="Project";

                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handlerLeadsTypes();
                        textView.setText(typeS);
                        dialog.dismiss();
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leadsType=0;
                        handlerLeadsTypes();
                        typeS="Lead Type";
                        textView.setText(typeS);
                        dialog.dismiss();
                    }
                });
            }
        });
        f2_proposal_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.proposal_status);
                FrameLayout accpetMe,rejectMe,accpetUser,rejectUser,apply,clear;
                accpetMe=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_ACCETP_ME);
                accpetUser=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_ACCEPT_UESR);
                rejectMe=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_REJECT_ME);
                rejectUser=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_REJECTED_USER);
                apply=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_APPLY);
                clear=dialog.findViewById(R.id.ALERT_CUSTOM_LEAD_PROPOSAL_CLEAR);
                TextView textView = (TextView) f2_proposal_accepted.getChildAt(0);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        textView.setText(proposalS);
                    }
                });


                if (proposalInt==1){
                    accpetMe.setBackgroundResource(act);
                }
                if (proposalInt==2){
                    rejectMe.setBackgroundResource(act);
                }
                if (proposalInt==3){
                    accpetUser.setBackgroundResource(act);
                }
                if (proposalInt==4){
                    rejectUser.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.DIOLOG_POSTED_TO_DELETE);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        proposalInt=0;
                        proposalS="Proposal Status";
                        textView.setText(proposalS);
                        handlerAcceptes();
                        dialog.dismiss();
                    }
                });
                accpetMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accpetMe.setBackgroundResource(act);
                        rejectMe.setBackgroundResource(def);
                        accpetUser.setBackgroundResource(def);
                        rejectUser.setBackgroundResource(def);
                        proposalInt=1;
                        proposalS="Accept by me";
                    }
                });
                rejectMe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accpetMe.setBackgroundResource(def);
                        rejectMe.setBackgroundResource(act);
                        accpetUser.setBackgroundResource(def);
                        rejectUser.setBackgroundResource(def);
                        proposalInt=2;
                        proposalS="Reject by me";
                    }
                });
                accpetUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accpetMe.setBackgroundResource(def);
                        rejectMe.setBackgroundResource(def);
                        accpetUser.setBackgroundResource(act);
                        rejectUser.setBackgroundResource(def);
                        proposalInt=3;
                        proposalS="Accept by user";
                    }
                });
                rejectUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        accpetMe.setBackgroundResource(def);
                        rejectMe.setBackgroundResource(def);
                        accpetUser.setBackgroundResource(def);
                        rejectUser.setBackgroundResource(act);
                        proposalInt=4;
                        proposalS="Reject by user";
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handlerAcceptes();
                        textView.setText(proposalS);
                        dialog.dismiss();
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        proposalInt=0;
                        proposalS="Proposal Status";
                        textView.setText(proposalS);
                        handlerAcceptes();
                        dialog.dismiss();
                    }
                });

            }
        });
        f5_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.lead_statuss_broker);
                FrameLayout accepted, rejected,clear,apply;
                TextView status  =dialog.findViewById(R.id.STATUS_OF_MYRESPONSE);
                status.setText("Leads Time");
                accepted=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_ACCEPT);
                rejected=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_REJECT);
                clear=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_CLEAR);
                apply=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_APPLY);
                TextView textView = (TextView) accepted.getChildAt(0);
                textView.setText("Today's Leads");
                TextView textViews= (TextView) rejected.getChildAt(0);
                textViews.setText("Previous Leads");

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        TextView textView = (TextView) f5_time.getChildAt(0);
                        textView.setText(timeS);
                    }
                });

                if (timeL==1){
                    accepted.setBackgroundResource(act);
                }
                if (timeL==2){
                    rejected.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_DISSMISSED);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeL=0;
                        timeS="Time";
                        TextView textView = (TextView) f5_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
                        dialog.dismiss();
                    }
                });
                accepted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeL=1;
                        timeS="Today's";
                        accepted.setBackgroundResource(act);
                        rejected.setBackgroundResource(def);
                    }
                });
                rejected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeL=2;
                        timeS="Previous";
                        rejected.setBackgroundResource(act);
                        accepted.setBackgroundResource(def);
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeL=0;
                        timeS="Time";
                        TextView textView = (TextView) f5_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
                        dialog.dismiss();
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = (TextView) f5_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
                        dialog.dismiss();
                    }
                });
            }
        });

        HashMap<String,String>hashMap =  new HashMap<>();
        hashMap.put("user_id",userId);
        getAllAcceptedRejected(userId);
        return view;
    }
    private void getAllAcceptedRejected(String userID) {
        HashMap<String,String>hashMap=new HashMap<>();
        hashMap.put("user_id",userID);
        ApiExecutor.getApiService().getAllAccepted(hashMap).enqueue(new Callback<ResponseAllacceptRejectModal>() {
            @Override
            public void onResponse(Call<ResponseAllacceptRejectModal> call, retrofit2.Response<ResponseAllacceptRejectModal> response) {
                if (response.isSuccessful()){
                    Log.e("responseByRetroFits", "onResponse: "+response+" "+response.body().getProperty_data() );
                    propertyAcceptedData=response.body().getProperty_data();
                    reqAcceptedData=response.body().getRequirement_data();
                    fetchPublicLeads();
                    fecthIndianHeros();
                    fecthDubbleleads();
                    fetcProjetcLeads();
                }else {
                    fetchPublicLeads();
                    fecthIndianHeros();
                    fecthDubbleleads();
                    fetcProjetcLeads();
                }
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);
                fetchPublicLeads();
                fecthIndianHeros();
                fecthDubbleleads();
                fetcProjetcLeads();
            }
        });
    }
    private void handlerAcceptes() {

    }
    private void handlerLeadsTypes() {
        if (leadsType==0){
            projectLead.setVisibility(View.VISIBLE);
            publicLead.setVisibility(View.VISIBLE);
            indianHerosLeads.setVisibility(View.VISIBLE);
            dubbleLeads.setVisibility(View.VISIBLE);
        }
        if (leadsType==1){
            projectLead.setVisibility(View.GONE);
            publicLead.setVisibility(View.VISIBLE);
            indianHerosLeads.setVisibility(View.GONE);
            dubbleLeads.setVisibility(View.GONE);
        }
        if (leadsType==2){
            projectLead.setVisibility(View.GONE);
            publicLead.setVisibility(View.GONE);
            indianHerosLeads.setVisibility(View.VISIBLE);
            dubbleLeads.setVisibility(View.GONE);
        }
        if (leadsType==3){
            projectLead.setVisibility(View.GONE);
            publicLead.setVisibility(View.GONE);
            indianHerosLeads.setVisibility(View.GONE);
            dubbleLeads.setVisibility(View.VISIBLE);
        }
        if (leadsType==4){
            projectLead.setVisibility(View.VISIBLE);
            publicLead.setVisibility(View.GONE);
            indianHerosLeads.setVisibility(View.GONE);
            dubbleLeads.setVisibility(View.GONE);
        }
    }
    private void handlelocalitiesFilter(ArrayList<LocalitiesDioModal> localitiesModals) {
        publicLeadArrey2 =  new ArrayList<>();
        indianHerosLeadsArrey2=new ArrayList<>();
        dubbleLeadsArrey2=new ArrayList<>();
        projectLeadArrey2=new ArrayList<>();
        if (publicLeadArrey!=null) {
            for (AlertLeadsViewModal s : publicLeadArrey) {
                for (LocalitiesDioModal d : localitiesModals) {
                    if (d.getSelected().equals("true")) {
                        if (s.getProjectname().contains(d.getName())) {
                            publicLeadArrey2.add(s);
                        }
                    }
                }
            }
        }
        if (indianHerosLeadsArrey!=null) {
            for (AlertLeadsViewModal s : indianHerosLeadsArrey) {
                for (LocalitiesDioModal d : localitiesModals) {
                    if (d.getSelected().equals("true")) {
                        if (s.getProjectname().contains(d.getName())) {
                            indianHerosLeadsArrey2.add(s);
                        }
                    }
                }
            }
        }
        if (dubbleLeadsArrey!=null) {
            for (AlertLeadsViewModal s : dubbleLeadsArrey) {
                for (LocalitiesDioModal d : localitiesModals) {
                    if (d.getSelected().equals("true")) {
                        if (s.getProjectname().contains(d.getName())) {
                            dubbleLeadsArrey2.add(s);
                        }
                    }
                }
            }
        }
        if (projectLeadArrey!=null) {
            for (AlertLeadsViewModal s : projectLeadArrey) {
                for (LocalitiesDioModal d : localitiesModals) {
                    if (d.getSelected().equals("true")) {
                        if (s.getProjectname().contains(d.getName())) {
                            projectLeadArrey2.add(s);
                        }
                    }
                }
            }
        }
        setRecycler2();
    }
    private void setRecycler2() {
        adater =  new AlertLeadsAdater(getContext(),publicLeadArrey2);
        publicLead.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),indianHerosLeadsArrey2);
        indianHerosLeads.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),dubbleLeadsArrey2);
        dubbleLeads.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),projectLeadArrey2);
        projectLead.setAdapter(adater);
    }

    private void fecthIndianHeros() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        noData.setVisibility(View.GONE);
                        JSONArray publicPros =  jsonObject.getJSONArray("publicpros_data");
                        JSONArray requirement_data =  jsonObject.getJSONArray("requirement_data");
                        if (publicPros!=null) {
                            for (int i = 0; i < publicPros.length(); i++) {
                                LeadsPublicPro data = new Gson().fromJson(publicPros.getJSONObject(i).toString(), LeadsPublicPro.class);
                                getMoreDetailsIndianPublic(data);
                                allLeadsId.add(data.getProperty_id());
                            }
                        }
                        adater =  new AlertLeadsAdater(getContext(),indianHerosLeadsArrey);
                        indianHerosLeads.setAdapter(adater);
                        if (requirement_data!=null) {
                            for (int i = 0; i < requirement_data.length(); i++) {
                                LeadsRequmentsModal data = new Gson().fromJson(requirement_data.getJSONObject(i).toString(), LeadsRequmentsModal.class);
                                getMoreInfoIndianReq(data);
                                allLeadsId.add(data.getRequirement_id());
                            }
                        }
                        adater =  new AlertLeadsAdater(getContext(),indianHerosLeadsArrey);
                        indianHerosLeads.setAdapter(adater);
                    }
                }
                catch (Exception e){
                    Log.e("errorIndian", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorIndian", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",userId);
                return  hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getMoreInfoIndianReq(LeadsRequmentsModal data) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    String d =  jsonObject.getString("data");
                    if (status.equals("1")&&!d.equals(null)){

                        LeadsIdianHerosInfo datas = new Gson().fromJson(jsonObject.getJSONObject("data").toString(),LeadsIdianHerosInfo.class);
                        finalIndianHeroReq(data,datas.getService());
                    }
                }catch (Exception e){
                    Log.e("error India", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error India", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void finalIndianHeroReq(LeadsRequmentsModal data, String service) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getBudget_max()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getRequirement_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getId());
                        viewModal.setSq(data.getBuildup_area_max()+data.getBuildup_area_max_mezor());
                        viewModal.setStatus("BUYER");
                        viewModal.setType(false);
                        viewModal.setDoctor(service);
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        viewModal.setTime(data.getUpdated_at());
                        viewModal.setBhk(data.getProperty());
                        publicLeadArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getMoreDetailsIndianPublic(LeadsPublicPro data) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    String d =  jsonObject.getString("data");
                    if (status.equals("1")&&d!=null){
                        LeadsIdianHerosInfo datas = new Gson().fromJson(jsonObject.getJSONObject("data").toString(),LeadsIdianHerosInfo.class);
                        finalIndianHeroPublic(data,datas.getService());
                    }
                }catch (Exception e){
                    Log.e("error India", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error India", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void finalIndianHeroPublic(LeadsPublicPro data, String service) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getProperty_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getPid());
                        viewModal.setSq(data.getPlot_area()+data.getPlot_area_mezor());
                        viewModal.setStatus("SELLER");
                        viewModal.setType(true);
                        viewModal.setAccepted(true);
                        viewModal.setDoctor(service);
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        viewModal.setTime(data.getUpdated_at());
                        viewModal.setBhk(data.getProperty());
                        dubbleLeadsArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void fecthDubbleleads() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_DUBBLE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        noData.setVisibility(View.GONE);
                        JSONArray publicPros =  jsonObject.getJSONArray("publicpros_data");
                        JSONArray requirement_data =  jsonObject.getJSONArray("requirement_data");
                        if (publicPros!=null) {
                            for (int i = 0; i < publicPros.length(); i++) {
                                LeadsPublicPro data = new Gson().fromJson(publicPros.getJSONObject(i).toString(), LeadsPublicPro.class);
                                getMoreDetailsDubblePublic(data);
                                allLeadsId.add(data.getProperty_id());
                            }
                        }
                        adater =  new AlertLeadsAdater(getContext(),dubbleLeadsArrey);
                        dubbleLeads.setAdapter(adater);
                        if (requirement_data!=null) {
                            for (int i = 0; i < requirement_data.length(); i++) {
                                LeadsRequmentsModal data = new Gson().fromJson(requirement_data.getJSONObject(i).toString(), LeadsRequmentsModal.class);
                                getMoreInfoDubbleReq(data);
                                allLeadsId.add(data.getRequirement_id());
                            }
                        }
                        adater =  new AlertLeadsAdater(getContext(),dubbleLeadsArrey);
                        dubbleLeads.setAdapter(adater);
                    }
                }catch (Exception e){
                    Log.e("error Dubble", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error Dubble", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getMoreInfoDubbleReq(LeadsRequmentsModal data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getBudget_max()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getRequirement_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getId());
                        viewModal.setSq(data.getBuildup_area_max()+data.getBuildup_area_max_mezor());
                        viewModal.setStatus("BUYER");
                        viewModal.setType(false);
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        viewModal.setTime(data.getUpdated_at());
                        viewModal.setBhk(data.getProperty());
                        dubbleLeadsArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getMoreDetailsDubblePublic(LeadsPublicPro data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getProperty_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getPid());
                        viewModal.setSq(data.getPlot_area()+data.getPlot_area_mezor());
                        viewModal.setStatus("SELLER");
                        viewModal.setType(true);
                        viewModal.setTime(data.getUpdated_at());
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        viewModal.setBhk(data.getProperty());
                        dubbleLeadsArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void fetcProjetcLeads() {
            StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_PROJECT_PREVIOUS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if (status.equals("1")){
                            noData.setVisibility(View.GONE);
                            JSONArray jsonArray = jsonObject.getJSONArray("ProjectPreviousLead_data");
                            for (int i=0;i<jsonArray.length();i++){
                                LeadsProjectModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsProjectModal.class);
                                getProejctInfo(data);
                                allLeadsId.add(data.getProperty_id());
                            }
                        }
                    }catch (Exception e){
                        Log.e("projectErroe", "onResponse: "+e );
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("projectErroe", "onResponse: "+error );
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("user_id",userId);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(getContext()).add(request);
        StringRequest requests = new StringRequest(Request.Method.POST, UrlClass.LEADS_PROJECT_TODAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        noData.setVisibility(View.GONE);
                        JSONArray jsonArray = jsonObject.getJSONArray("ProjectTodayLead_data");
                        for (int i=0;i<jsonArray.length();i++){

                            LeadsProjectModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsProjectModal.class);
                            getProejctInfo(data);
                        }
                    }
                }catch (Exception e){
                    Log.e("projectErroe", "onResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("projectErroe", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(requests);
    }
    private void getProejctInfo(LeadsProjectModal data) {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_PROJECT_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String status =jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Project");
                        ProjectInfoModal modal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),ProjectInfoModal.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(modal.getTotal_price()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getProperty_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(modal.getName());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getId());
                        viewModal.setSq(modal.getCarpet_area()+"Sq");
                        if (modal.getDealing().equals("Sell")){
                        viewModal.setStatus("SELLER");
                            viewModal.setType(true);
                        }else {
                        viewModal.setStatus("BUYER");
                            viewModal.setType(false);
                        }
                        viewModal.setTime(modal.getUpdated_at());
                        viewModal.setBhk(modal.getNumber_bhk());
                        viewModal.setUnlocking(geUnclockTime(modal.getUpdated_at()));
                        viewModal.setBhk(data.getProperty() + " in "+data.getProject_name());
                        projectLeadArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("errorProjectInfo", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorProjectInfo", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id",data.getId());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void fetchPublicLeads() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_PUBLIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        noData.setVisibility(View.GONE);
                        JSONArray requirement_data =  jsonObject.getJSONArray("requirement_data");
                        JSONArray publicprodata  = jsonObject.getJSONArray("publicpros_data");
                         if (requirement_data!=null){
                           for (int i=0;i<requirement_data.length();i++){
                               LeadsRequmentsModal data =  new Gson().fromJson(requirement_data.getJSONObject(i).toString(),LeadsRequmentsModal.class);
                               leadsRequmentsModals.add(data);
                               allLeadsId.add(data.getRequirement_id());
                           }
                         }
                        if (publicprodata!=null){
                            for (int i=0;i<publicprodata.length();i++){
                                LeadsPublicPro data = new Gson().fromJson(publicprodata.getJSONObject(i).toString(),LeadsPublicPro.class);
                                leadsPublicProsMain.add(data);
                                allLeadsId.add(data.getProperty_id());
                            }
                        }
                        handlerPublicLeads();
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void handlerPublicLeads() {
        publicLeadArrey =  new ArrayList<>();
        for (LeadsRequmentsModal data:leadsRequmentsModals){
            setViewModal(data);
        }
        adater =  new AlertLeadsAdater(getContext(),publicLeadArrey);
        publicLead.setAdapter(adater);
        for (LeadsPublicPro data:leadsPublicProsMain){
            setViewModalPublicPRos(data);
        }
        adater =  new AlertLeadsAdater(getContext(),publicLeadArrey);
        publicLead.setAdapter(adater);
    }
    private void setViewModalPublicPRos(LeadsPublicPro data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getProperty_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getPid());
                        viewModal.setSq(data.getPlot_area()+data.getPlot_area_mezor());
                        viewModal.setStatus("SELLER");
                        viewModal.setTime(data.getUpdated_at());
                        viewModal.setType(true);
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        viewModal.setBhk(data.getProperty());
                        publicLeadArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void setViewModal(LeadsRequmentsModal data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        User user =  new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                        AlertLeadsViewModal viewModal =  new AlertLeadsViewModal();
                        viewModal.setBudget(getBudgetInLakhs(data.getBudget_max()));
                        viewModal.setId(data.getId());
                        viewModal.setProperty_id(data.getRequirement_id());
                        viewModal.setUser_id(data.getUser_id());
                        viewModal.setName(user.getFirst_name());
                        viewModal.setCity(data.getCity());
                        viewModal.setProjectname(data.getId());
                        viewModal.setSq(data.getBuildup_area_max()+data.getBuildup_area_max_mezor());
                        viewModal.setStatus("BUYER");
                        viewModal.setType(false);
                        viewModal.setTime(data.getUpdated_at());
//                        if (data.getProperty_type().equals("Residential")){
//                        viewModal.setBhk(data.get);
//                        }else {
//                        }
                        viewModal.setBhk(data.getProperty());
                        viewModal.setUnlocking(geUnclockTime(data.getUpdated_at()));
                        publicLeadArrey.add(viewModal);
                    }
                }catch (Exception e){
                    Log.e("alertErrors", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("alertErrors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void setRecycler(){
        adater =  new AlertLeadsAdater(getContext(),publicLeadArrey);
        publicLead.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),indianHerosLeadsArrey);
        indianHerosLeads.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),dubbleLeadsArrey);
        dubbleLeads.setAdapter(adater);
        adater =  new AlertLeadsAdater(getContext(),projectLeadArrey);
        projectLead.setAdapter(adater);
    }
    private LeadsPublicPro getFullPropertyData(String property_id, String user_id) {
        leadsPublicPros = PrefMananger.getPropertyList(getContext());
        LeadsPublicPro data =  new LeadsPublicPro();
        if (leadsPublicPros!=null){
            for (LeadsPublicPro d : leadsPublicPros){
                if (d.getProperty_id().equals(property_id)){
                    data =d;
                }
            }
        }
        return data;
    }
    private String getBudgetInLakhs(String budget_max) {
        String data =  null;
        if (budget_max==null){
            return data;
        }
        int no = Integer.parseInt(budget_max);
        if (no>=1000000000){
            no=no/1000000000;
            data=no+"Arab";
            return data;
        }
        if (no>=10000000){
            no = no/10000000;
            data=no+"Crore";
            return data;
        }
        if (no>=100000){
            no = no/100000;
            data = no+"Lakh";
            return data;
        }
        if (no>=1000){
            no = no/1000;
            data = no+"K";
            return data;
        }
        if (no>999){
            data = String.valueOf(no);

        }
        return data;
    }
    private String getDateInFormat(String updated_at) {
        String data  = null;
        data = updated_at.split("T")[0];
        return data;
    }
    private String geUnclockTime(String updated_at) {
        String data =  null;
        String time =  updated_at.split("T")[1];
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        String secTemp = time.split(":")[2];
        try {
            // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date past = format.parse(updated_at);
            Date now = new Date();
            long letestHour = now.getHours();
            long letestSec = now.getMinutes();

            if (past.compareTo(now)>0){
                Log.e("TIME", "geUnclockTime: now is greter" );
                if (letestHour<=Long.getLong(hour)){
                    long t = letestHour-Integer.parseInt(hour);
                    data = "Unlocking in "+String.valueOf(t);
                }else{
                    long t = Integer.parseInt(hour)-letestHour;
                    data = "Unlocking in "+String.valueOf(t);
                }

            }
            if (past.compareTo(now)<0){
                Log.e("TIME", "geUnclockTime: now is less");
                data=null;
            }
            if (past.compareTo(now)==0){
                Log.e("TIME", "geUnclockTime: now is equl" );
                data=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    private void getWorkLocaliies(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_WORK_DESCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("loge", "onResponse: "+response );
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONObject jsonObject1 =  jsonObject.getJSONObject("data");
                        String data = jsonObject1.getString("working_localities");
                        JSONArray jsonArray =  new JSONArray(data);
                        for (int i=0;i<jsonArray.length();i++){
                            LocalitiesModal  localitiesModal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LocalitiesModal.class);
                            LocalitiesDioModal dioModal = new LocalitiesDioModal();
                            dioModal.setSelected("false");
                            dioModal.setName(localitiesModal.getLocalities());
                            localitiesModals.add(dioModal);
                            Log.e("loge", "onResponse: "+localitiesModal.getPincode() );

                        }
                        String s = new Gson().toJson(localitiesModals);
                        PrefMananger.saveString(getContext(),PrefMananger.ARREY_LOCALTIES,s);
                        Log.e("loge", "onResponse: "+data );
                    }
                }catch (Exception e){
                    Log.e("loge", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("loge", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void handleLeadsTime() {
        if (timeL==0){
            setRecycler();
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date finale = new Date();
        int day = finale.getDay();
        int Month = finale.getMonth();
        int year = finale.getYear();
        publicLeadArrey2 =  new ArrayList<>();
        indianHerosLeadsArrey2=new ArrayList<>();
        dubbleLeadsArrey2=new ArrayList<>();
        projectLeadArrey2=new ArrayList<>();
        Log.e("date", "handleLeadsTime: "+day+" "+Month+" "+year );
        if (timeL==1){
            for (AlertLeadsViewModal s : publicLeadArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        publicLeadArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : indianHerosLeadsArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        indianHerosLeadsArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : dubbleLeadsArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        dubbleLeadsArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : projectLeadArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        projectLeadArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            setRecycler2();
        }else {
            for (AlertLeadsViewModal s : publicLeadArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        publicLeadArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : indianHerosLeadsArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        indianHerosLeadsArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : dubbleLeadsArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        dubbleLeadsArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (AlertLeadsViewModal s : projectLeadArrey){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        projectLeadArrey2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            setRecycler2();
        }

    }

}
