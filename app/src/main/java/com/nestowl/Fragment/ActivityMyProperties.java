package com.nestowl.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.MyResponsesAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.LeadLiveContactModal;
import com.nestowl.model.LeadsLiveOfferModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LiveLeadsModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.MyresponeViewModal;
import com.nestowl.model.PostPropertyModal;
import com.nestowl.model.PropertyPricModal;
import com.nestowl.brokerapp.EditSignUpForm;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SelectModePostRequirement;
import com.nestowl.brokerapp.SubscriptionPlan;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.android.material.tabs.TabLayout;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityMyProperties extends Fragment implements TabLayout.OnTabSelectedListener{
    FrameLayout third_interested,ld_format,ld_to,view_pro,ld_time;
    RecyclerView MakeOfferRecycler,ContactUserRecycler,StartOfferRecycler;
    MyResponsesAdapter adapter;
    ArrayList<MyresponeViewModal> makeOfferList,contactUserList,startOfferList,makeOfferList2,contactUserList2,startOfferList2;
    ArrayList<LiveLeadsModal> MakeOffersArreysData;
    ArrayList<LeadsLiveOfferModal> OfferArreyData;
    ArrayList<LeadLiveContactModal> contacArreyData;
    AutoCompleteTextView ids;
    ArrayList<LeadsPublicPro> leadsPublicPros;
    PostPropertyModal propertyModal;
    PropertyPricModal pricModal;
    ArrayList<String> ides,userIds,acceptedId,rejectedId;
    int formatI,timeL,statusL;
    String formatS,timeS,statusS;
    int def = R.drawable.employe_circle_rounded;
    int act = R.drawable.selected_background_filter;
    ArrayAdapter<String> adapterss;
    LoginPojo loginPojo;
    Autoclicks autoclicks;
    ConstraintLayout noDataShow;
    CardView setData;
    Context context;

    public ActivityMyProperties() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_activity_my_properties, container, false);
        context =getContext();
        ld_format=view.findViewById(R.id.MY_RESPONSE_LEADS_FORMAT);
        MakeOfferRecycler=view.findViewById(R.id.MY_RESPONSE_MAKE_OFFER);
        ContactUserRecycler=view.findViewById(R.id.MY_RESPONSE_CONTACT_USER);
        StartOfferRecycler=view.findViewById(R.id.MY_RESPONSE_START_OFFER);
        ld_time=view.findViewById(R.id.MY_RESPONSE_TIME);
        ids=view.findViewById(R.id.MY_RESPONSE_ID_FITER);
        ld_to=view.findViewById(R.id.MY_RESPONSE_LEADS_STATUS);
        noDataShow=view.findViewById(R.id.ALL_NO_DATA_SHOW);
        setData=view.findViewById(R.id.ALL_NO_DATA_SHOW_BTN);


        makeOfferList=new ArrayList<>();
        contactUserList=new ArrayList<>();
        startOfferList=new ArrayList<>();
        makeOfferList2=new ArrayList<>();
        contactUserList2=new ArrayList<>();
        startOfferList2=new ArrayList<>();
        MakeOffersArreysData=new ArrayList<>();
        OfferArreyData=new ArrayList<>();
        contacArreyData=new ArrayList<>();
        ides=new ArrayList<>();
        userIds=new ArrayList<>();
        acceptedId=new ArrayList<>();
        rejectedId=new ArrayList<>();
        propertyModal =  new PostPropertyModal();
        pricModal =  new PropertyPricModal();
        leadsPublicPros =  new ArrayList<>();

        formatS="Lead format";
        timeS="Time filter";
        statusS=" Lead status";
        adapterss = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,ides);
        ids.setAdapter(adapterss);
        autoclicks =  new Autoclicks();
        ids.setOnItemSelectedListener(autoclicks);
        ids.setOnItemClickListener(autoclicks);
        ids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()==0){
                    searchProperty("");
                }
            }
        });
        setData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(context,"sub").equals("true")){
                    Intent intent = new Intent(context, SelectModePostRequirement.class);
                    startActivity(intent);
                }else {
                    if ( PrefMananger.getString(context,"id").equals("true")){
                        new WarningDio(context, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(context, SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(context,"status").equals("0")){
                            new WarningDio(context, "Your is incomplete", "Complete Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(context, EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(context,"status").equals("1")){
                            new WarningDio(context, "Your is summited", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(context,"status").equals("2")){
                            new WarningDio(context, "Your Profile is pending.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }


                    }
                }
            }
        });
//        third_interested.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(context, NestProfessionalFourthDesign.class));
//
//            }
//        });
        ld_format.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.lead_format_seller);
                FrameLayout drectLead,proposal,clear,apply;
                drectLead=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_DRECT_LEAD);
                proposal=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_PROPOSAL);
                clear=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_CLEAR);
                apply=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_APPLY);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        TextView textView = (TextView) ld_format.getChildAt(0);
                        textView.setText(formatS);
                        setLeadsFormat();
                    }
                });

                if (formatI==1){
                    drectLead.setBackgroundResource(act);
                }
                if (formatI==2){
                    proposal.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView im=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_DISMISSED);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        formatI=0;
                        formatS="Format";
                        setLeadsFormat();
                        TextView textView = (TextView) ld_format.getChildAt(0);
                        textView.setText(formatS);
                        dialog.dismiss();
                    }
                });
                drectLead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        formatI=1;
                        formatS="Direct Lead";
                        drectLead.setBackgroundResource(act);
                        proposal.setBackgroundResource(def);
                    }
                });
                proposal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        formatI=2;
                        formatS="Proposal";
                        drectLead.setBackgroundResource(def);
                        proposal.setBackgroundResource(act);
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setLeadsFormat();
                        TextView textView = (TextView) ld_format.getChildAt(0);
                        textView.setText(formatS);
                        dialog.dismiss();
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        formatI=0;
                        formatS="Format";
                        setLeadsFormat();
                        TextView textView = (TextView) ld_format.getChildAt(0);
                        textView.setText(formatS);
                        dialog.dismiss();
                    }
                });

            }
        });
        ld_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.lead_statuss_broker);
                FrameLayout accepted, rejected,clear,apply;
                accepted=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_ACCEPT);
                rejected=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_REJECT);
                clear=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_CLEAR);
                apply=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_APPLY);

                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        TextView textView = (TextView) ld_to.getChildAt(0);
                        textView.setText(statusS);
                        handleLeadsStatus();
                    }
                });

                if (statusL==1){
                    accepted.setBackgroundResource(act);
                }
                if (statusL==2){
                    rejected.setBackgroundResource(act);
                }
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_DISSMISSED);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusL=0;
                        statusS="Status";
                        TextView textView = (TextView) ld_to.getChildAt(0);
                        textView.setText(statusS);
                        handleLeadsStatus();
                        dialog.dismiss();
                    }
                });
                accepted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusL=1;
                        statusS="Accepted";
                        accepted.setBackgroundResource(act);
                        rejected.setBackgroundResource(def);
                    }
                });
                rejected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusL=2;
                        statusS="Rejected";
                        accepted.setBackgroundResource(def);
                        rejected.setBackgroundResource(act);
                    }
                });
                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        statusL=0;
                        statusS="Status";
                        TextView textView = (TextView) ld_to.getChildAt(0);
                        textView.setText(statusS);
                        handleLeadsStatus();
                        dialog.dismiss();
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = (TextView) ld_to.getChildAt(0);
                        textView.setText(statusS);
                        handleLeadsStatus();
                        dialog.dismiss();
                    }
                });

            }
        });
        ld_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
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
                        TextView textView = (TextView) ld_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
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
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.DIOLOG_MY_RESPONSE_DISSMISSED);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timeL=0;
                        timeS="Time";
                        TextView textView = (TextView) ld_time.getChildAt(0);
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
                        TextView textView = (TextView) ld_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
                        dialog.dismiss();
                    }
                });
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = (TextView) ld_time.getChildAt(0);
                        textView.setText(timeS);
                        handleLeadsTime();
                        dialog.dismiss();
                    }
                });
            }
        });
        loginPojo= PrefMananger.GetLoginData(context);
        getData();
        return view;
    }
    private void getData() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_LIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("LiveLeads", "onResponse: "+response );
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray makeOffers = jsonObject.getJSONArray("Makeanoffers_data");
                        JSONArray contactUser = jsonObject.getJSONArray("ContactUser_data");
                        JSONArray startoffer = jsonObject.getJSONArray("Startanoffer_data");

                        for (int i=0;i<makeOffers.length();i++){
                            LiveLeadsModal data = new Gson().fromJson(makeOffers.getJSONObject(i).toString(),LiveLeadsModal.class);
                            if (!String.valueOf(loginPojo.getUserId()).equals(data.getLead_user_id())){
                                getMoreInfoMakeOffer(data,data.getLead_user_id());
                                ides.add(data.getProperty_id());
                                userIds.add(data.getLead_user_id());
                                noDataShow.setVisibility(View.GONE);
                            }

                        }
                        for (int i=0;i<contactUser.length();i++){
                            LeadLiveContactModal data =  new Gson().fromJson(contactUser.getJSONObject(i).toString(),LeadLiveContactModal.class);
                            if (!String.valueOf(loginPojo.getUserId()).equals(data.getLead_user_id())){
                                getMoreInfoContact(data,data.getLead_user_id());
                                ides.add(data.getProperty_id());
                                userIds.add(data.getLead_user_id());
                                noDataShow.setVisibility(View.GONE);
                            }

                        }
                        for (int i=0;i<startoffer.length();i++){
                            LeadsLiveOfferModal data =  new Gson().fromJson(startoffer.getJSONObject(i).toString(),LeadsLiveOfferModal.class);
                            if (!String.valueOf(loginPojo.getUserId()).equals(data.getLead_user_id())){
                                getMoreInfoStartOffer(data,data.getLead_user_id());
                                ides.add(data.getProperty_id());
                                userIds.add(data.getLead_user_id());
                                noDataShow.setVisibility(View.GONE);
                            }

                        }
                        setRecylcerViews();
                        handlerAcceptReject();
                    }
                }catch (Exception e){
                    Log.e("LiveLeads", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LiveLeads", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",String.valueOf(loginPojo.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void handlerAcceptReject() {
        for (String id:userIds){
            StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_ACCEPTED_BY_USER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String  status = jsonObject.getString("status");
                        if (status.equals("1")){
                            JSONArray jsonArray = jsonObject.getJSONArray("Property_data");
                            for (int i=0;i<jsonArray.length();i++){
                                LeadsPublicPro data = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsPublicPro.class);
                                acceptedId.add(data.getProperty_id());
                            }
                        }
                    }catch (Exception e){
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
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("user_id",id);
                    return hashMap;
                }
            };
            Volley.newRequestQueue(context).add(request);
        }
    }
    private void setRecylcerViews() {
        MakeOfferRecycler.setLayoutManager(new LinearLayoutManager(context));
        ContactUserRecycler.setLayoutManager(new LinearLayoutManager(context));
        StartOfferRecycler.setLayoutManager(new LinearLayoutManager(context));
        adapter  = new MyResponsesAdapter(context,makeOfferList);
        MakeOfferRecycler.setAdapter(adapter);
        adapter  = new MyResponsesAdapter(context,contactUserList);
        ContactUserRecycler.setAdapter(adapter);
        adapter  = new MyResponsesAdapter(context,startOfferList);
        StartOfferRecycler.setAdapter(adapter);

        if (PrefMananger.getString(context,"responsekey")!=null){
            ids.setText(PrefMananger.getString(context,"responsekey"));
            searchProperty(PrefMananger.getString(context,"responsekey"));
            PrefMananger.saveString(context,"responsekey",null);
        }
    }
    private void getMoreInfoStartOffer(LeadsLiveOfferModal data,String leadID) {
        LeadsPublicPro leadsPublicPro = getFullPropertyData(data.getProperty_id(),data.getUser_id());
        MyresponeViewModal modal =  new MyresponeViewModal();
        modal.setProperty_id(data.getProperty_id());
        modal.setUser_id(leadID);
        modal.setBhk(leadsPublicPro.getProperty());
        modal.setId(data.getId());
        modal.setName(data.getName());
        modal.setBudget(pricModal.getExpectedprice());
        modal.setCity(leadsPublicPro.getLocality());
        modal.setSq(propertyModal.getPlot_area()+propertyModal.getPlot_area_mezor());
        modal.setType("proposal");
        modal.setTime(data.getUpdated_at());
        makeOfferList.add(modal);
    }
    private LeadsPublicPro getFullPropertyData(String property_id,String user_id) {
       leadsPublicPros = PrefMananger.getPropertyList(context);
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
    private void getMoreInfoContact(LeadLiveContactModal data,String leadID) {
        LeadsPublicPro leadsPublicPro=getFullPropertyData(data.getProperty_id(),data.getUser_id());
        MyresponeViewModal modal =  new MyresponeViewModal();
        modal.setProperty_id(data.getProperty_id());
        modal.setUser_id(leadID);
        modal.setBhk(leadsPublicPro.getProperty());
        modal.setId(data.getId());
        modal.setName(data.getName());
        modal.setBudget(leadsPublicPro.getExpectedprice());
        modal.setCity(leadsPublicPro.getLocality());
        modal.setSq(leadsPublicPro.getPlot_area()+leadsPublicPro.getPlot_area_mezor());
        modal.setType("contact");
        modal.setTime(data.getUpdated_at());
        makeOfferList.add(modal);
    }
    private void getMoreInfoMakeOffer(LiveLeadsModal data , String leadID) {
        LeadsPublicPro leadsPublicPro= getFullPropertyData(data.getProperty_id(),data.getUser_id());
        MyresponeViewModal modal =  new MyresponeViewModal();
        modal.setProperty_id(data.getProperty_id());
        modal.setUser_id(leadID);
        modal.setBhk(leadsPublicPro.getProperty());
        modal.setId(data.getId());
        modal.setName(data.getName());
        modal.setBudget(leadsPublicPro.getExpectedprice());
        modal.setCity(leadsPublicPro.getLocality());
        modal.setSq(leadsPublicPro.getPlot_area()+leadsPublicPro.getPlot_area_mezor());
        modal.setType("interested");
        modal.setTime(data.getUpdated_at());;
        makeOfferList.add(modal);
    }
    private void handleLeadsTime() {
        if (timeL==0){
            setRecylcerViews();
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date finale = new Date();
        int day = finale.getDay();
        int Month = finale.getMonth();
        int year = finale.getYear();
        makeOfferList2 = new ArrayList<>();
        contactUserList2 = new ArrayList<>();
        startOfferList2 = new ArrayList<>();
        Log.e("date", "handleLeadsTime: "+day+" "+Month+" "+year );
        if (timeL==1){
            for (MyresponeViewModal s : makeOfferList){
                try {
                        Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                       makeOfferList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (MyresponeViewModal s : contactUserList){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        contactUserList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (MyresponeViewModal s : startOfferList){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()==day&&d.getMonth()==Month&&d.getYear()==year){
                        startOfferList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            setRecylcerViews2();
        }else {
            for (MyresponeViewModal s : makeOfferList){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        makeOfferList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (MyresponeViewModal s : contactUserList){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        contactUserList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            for (MyresponeViewModal s : startOfferList){
                try {
                    Date  d=simpleDateFormat.parse(s.getTime());
                    if (d.getDay()!=day|d.getMonth()!=Month|d.getYear()!=year){
                        startOfferList2.add(s);
                    }
                } catch (ParseException e) {
                    Log.e("date", "handleLeadsTime: "+e );
                    e.printStackTrace();
                }
            }
            setRecylcerViews2();
        }

    }
    private void handleLeadsStatus() {
        makeOfferList2=new ArrayList<>();
        contactUserList2=new ArrayList<>();
        startOfferList2=new ArrayList<>();
        if (statusL==1){
            for (MyresponeViewModal data:makeOfferList){
                for (String s:acceptedId){
                    if (data.getProperty_id().equals(s)){
                        makeOfferList2.add(data);
                    }
                }
            }
            for (MyresponeViewModal data:contactUserList){
                for (String s:acceptedId){
                    if (data.getProperty_id().equals(s)){
                        contactUserList2.add(data);
                    }
                }
            }
            for (MyresponeViewModal data:startOfferList){
                for (String s:acceptedId){
                    if (data.getProperty_id().equals(s)){
                        startOfferList2.add(data);
                    }
                }
            }
            setRecylcerViews2();
        }
        if (statusL==0){
            setRecylcerViews();
        }
        if (statusL==2){
            setRecylcerViews();
        }

    }
    private void setLeadsFormat() {
        if (formatI==0){
            MakeOfferRecycler.setVisibility(View.VISIBLE);
            ContactUserRecycler.setVisibility(View.VISIBLE);
            StartOfferRecycler.setVisibility(View.VISIBLE);
        }
        if (formatI==1){
            MakeOfferRecycler.setVisibility(View.GONE);
            ContactUserRecycler.setVisibility(View.VISIBLE);
            StartOfferRecycler.setVisibility(View.VISIBLE);
        }
        if (formatI==2){
            MakeOfferRecycler.setVisibility(View.VISIBLE);
            ContactUserRecycler.setVisibility(View.GONE);
            StartOfferRecycler.setVisibility(View.GONE);
        }
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    private class Autoclicks implements AdapterView.OnItemSelectedListener , AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (ids.getText().length()>0){
                searchProperty(ids.getText().toString());
                Log.e("searchResponse", "searchProperty: "+ids.getText() );
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (ids.getText().length()>0){
                searchProperty(ids.getText().toString());
                Log.e("searchResponse", "searchProperty: "+ids.getText() );
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private void searchProperty(String toString) {
        if (toString.length()==0){
            setRecylcerViews();
        }else {
            for (MyresponeViewModal s : makeOfferList){
                if (s.getProperty_id().contains(toString)){
                    makeOfferList2.add(s);
                }
            }
            for (MyresponeViewModal s : contactUserList){
                if (s.getProperty_id().contains(toString)){
                    contactUserList2.add(s);
                }
            }
            for (MyresponeViewModal s : startOfferList){
                if (s.getProperty_id().contains(toString)){
                    startOfferList2.add(s);
                }
            }
            setRecylcerViews2();
        }
    }
    private void setRecylcerViews2() {
        MakeOfferRecycler.setLayoutManager(new LinearLayoutManager(context));
        ContactUserRecycler.setLayoutManager(new LinearLayoutManager(context));
        StartOfferRecycler.setLayoutManager(new LinearLayoutManager(context));
        adapter  = new MyResponsesAdapter(context,makeOfferList2);
        MakeOfferRecycler.setAdapter(adapter);
        adapter  = new MyResponsesAdapter(context,contactUserList2);
        ContactUserRecycler.setAdapter(adapter);
        adapter  = new MyResponsesAdapter(context,startOfferList2);
        StartOfferRecycler.setAdapter(adapter);
    }
    public static String getDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
