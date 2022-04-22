package com.nestowl.Fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.ArticleAdapter;
import com.nestowl.AdapterClass.IncompleteHomeAdpter;
import com.nestowl.AdapterClass.LeadsAdapter;
import com.nestowl.AdapterClass.ProjectListAdapter;
import com.nestowl.AdapterClass.PropertyViewAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.PropertyReciver;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.ArticleModal;
import com.nestowl.model.BasicDetailsModal;
import com.nestowl.model.DpModal;
import com.nestowl.model.DubbleLeadsProsModals;
import com.nestowl.model.DubbleLeadsRequrementsModal;
import com.nestowl.model.LeadsIdianHerosInfo;
import com.nestowl.model.LeadsPublicModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.LeadsViewModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.NotificationModal;
import com.nestowl.model.ProjectDetailsModal;
import com.nestowl.model.ProjectRecivedModal;
import com.nestowl.model.PropertyViewModal;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.SubscriptionRemainModal;
import com.nestowl.model.User;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.Articles;
import com.nestowl.brokerapp.Clints;
import com.nestowl.brokerapp.EditSignUpForm;
import com.nestowl.brokerapp.HomeScreen;
import com.nestowl.brokerapp.HowItWorks;
import com.nestowl.brokerapp.NestOwnersSixteen;
import com.nestowl.brokerapp.NewProjectHomeBuyer;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ReraActivity;
import com.nestowl.brokerapp.SaleProperties;
import com.nestowl.brokerapp.SalePropertiesSecondForNestOwners;
import com.nestowl.brokerapp.SaveMoreActivity;
import com.nestowl.brokerapp.ScreenSixteenDetailsPage;
import com.nestowl.brokerapp.SearchNestPro;
import com.nestowl.brokerapp.SearchViewActivity;
import com.nestowl.brokerapp.SelectOptionMode;
import com.nestowl.brokerapp.SubscriptionPlan;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class HomeFragment extends Fragment {
    FrameLayout WelcomeMassageBody,SubscribeBOdy,new_project,home_card_shadow_first,home_card_shadow_second,nest_search,leadsByPropertyMain,postPropertyBody,postPropertyBtn;
    ImageView imageView,imgSUMBITED,imgRECIVED,imgVARIFIED,imgAPPROVED;
    TextView welcomeText,statusText,tv_se_all,nes_professional,city_centre,nest_project,hot_properties,featured_product,tv_new_project,edt_home,tv_third,propertyCount,leadsByPropertyId,leadsByPropertyType,leadsByPropertyLeads,leadsByPropertyIntrested;
    LinearLayout lnr_save_more,lnr_hello_sahil,lnr_city,ht_properties,lnr_nest_professional,frame_rera;
    ScrollView scrollView_home;
    HomeScreen homeScreen;
    LinearLayout lnr_parent_frg,rohit,subscribe,lnr_listing;
    FrameLayout see_one,see_three;
    Boolean is_Residential_click = false;
    RadioGroup rg_group;
    RadioButton rad_submit,rad_received,rad_accepted;
    LinearLayout lr_submit,lr_received,lr_accept;
    HomeScreen home_first;
    FrameLayout frame_nes,frame_second,frame_third,frame_five,frame_last,frame_details,frame_details_second;
    FrameLayout post_property,frame_incom,frm,show_All;
    LoginPojo data;
    Boolean subscribeClick;
    RecyclerView articlesRecyler,LeadsRecycler,PropertyRecycler,IncompleteList,projectLiveRecyler,projectRecicvedRecyler,projectPendingRecycler;
    ArrayList<ArticleModal> articles =  new ArrayList<>();
    ArrayList<ArticleModal> article =  new ArrayList<>();
    ArrayList<ProjectDetailsModal> liveList,recivedList,pendingList;
    ArrayList<String> projectIdStrings,statusList;
    ArrayList<BasicDetailsModal> incompleteArrey;
    IncompleteHomeAdpter adpterIncomplete;
    Boolean t = true;
    User user = new User();
    DpModal dpModal;
    String UserID,responseIdS;
    Boolean isAd;
    ArrayList<PropertyViewModal> propertyViewModals = new ArrayList<>();
    ArrayList<LeadsViewModal> leadsViewModals =  new ArrayList<>();
    ArrayList<AllAcceptedDataModal> propertyAcceptedData,reqAcceptedData;
    PropertyReciver propertyReciver;
    LinearLayoutManager layoutManager;
    LiveCommnication liveCommnication;
    CardView clints,how2;
    ArrayList<NotificationModal>notificationModals;

    int scrollhandler;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.MANAGE_DOCUMENTS
        };
        if (!hasPermissions(getContext(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }
        if (PrefMananger.getString(getContext(),"user")!=null){
           user =  new Gson().fromJson(PrefMananger.getString(getContext(),"user"),User.class);
           UserID = user.getUser_id();
       }
        tv_new_project =view.findViewById(R.id.new_projects_alt);
        lnr_save_more=view.findViewById(R.id.save_more_linear_layour);
        welcomeText=view.findViewById(R.id.HOME_WELCOME_TEXT);
        WelcomeMassageBody=view.findViewById(R.id.HOME_WELCOME_MASSAGE);
        imgSUMBITED=view.findViewById(R.id.HOME_SUBSCRIBE_SUMBIT_IMG);
        imgRECIVED=view.findViewById(R.id.HOME_SUBSCRIBE_RECIVE_IMG);
        imgVARIFIED=view.findViewById(R.id.HOME_SUBSCRIBE_VARIFIED_IMG);
        imgAPPROVED=view.findViewById(R.id.HOME_SUBSCRIBE_SATUS_IMG);
        statusText=view.findViewById(R.id.HOME_SUBSCRIBE_STATUS_TXT);
        SubscribeBOdy=view.findViewById(R.id.HOME_SUBSCRIBE_BODY);
        show_All=view.findViewById(R.id.HOME_SHOW_ALL);
        propertyCount=view.findViewById(R.id.HOME_PROPERTY_COUNT);
        articlesRecyler=view.findViewById(R.id.HOME_ARTICLE_RECYCLER);
        LeadsRecycler=view.findViewById(R.id.HOME_LEADS_RECYCLER_VIEW);
        IncompleteList=view.findViewById(R.id.HOME_INCOMPLETE_LIST);
        clints=view.findViewById(R.id.HOME_CLINTS);
        how2=view.findViewById(R.id.HOME_CARD_2);

        leadsByPropertyMain=view.findViewById(R.id.HOME_LEADS_BY_PRO_MAIN);
        leadsByPropertyType=view.findViewById(R.id.HOME_LEADS_BY_PRO_TYPE);
        leadsByPropertyLeads=view.findViewById(R.id.HOME_LEADS_BY_PRO_COUNT);
        leadsByPropertyIntrested=view.findViewById(R.id.HOME_LEADS_BY_PRO_INTRESTED);
        leadsByPropertyId=view.findViewById(R.id.HOME_LEADS_BY_PRO_ID);
        postPropertyBody=view.findViewById(R.id.HOME_POST_PROPERTYT_BODY);
        postPropertyBtn=view.findViewById(R.id.HOME_POST_PROPERTYT_BTN);
        liveCommnication= ViewModelProviders.of(getActivity()).get(LiveCommnication.class);

        leadsByPropertyMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aichat ai =new aichat();
                ai.setText("response");
                ai.setValue(responseIdS);
                liveCommnication.setText(ai);
                homeScreen.bottomNavigationView.setVisibility(View.VISIBLE);
                homeScreen.frm_haider_parent.setVisibility(View.GONE);
                homeScreen.lnr_haider_second.setVisibility(View.GONE);
                lnr_parent_frg.setVisibility(View.VISIBLE);
            }
        });
        IncompleteList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        incompleteArrey=new ArrayList<>();
        projectIdStrings= new ArrayList<>();
        statusList=new ArrayList<>();
        propertyAcceptedData=new ArrayList<>();
        reqAcceptedData=new ArrayList<>();
        notificationModals=new ArrayList<>();
        LeadsRecycler.setNestedScrollingEnabled(true);

        PropertyRecycler=view.findViewById(R.id.HOME_PROPERTY_RECYCLER);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        PropertyRecycler.setLayoutManager(layoutManager);
        data = PrefMananger.GetLoginData(getActivity());
        getIncompleteListings();
        welcomeText.setText("Hello "+data.getFirstName());
        subscribeClick=false;
        handleProfileSumbition();
        isAd=true;
        ProgressDialog pd  = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        pd.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
                LeadsViewModal ad =  new LeadsViewModal();
                ad.setAd(true);
                ad.setProperty(false);
                ad.setUser(false);
                leadsViewModals.add(ad);
                setLeadsRecycler();
                try {
                    String json = PrefMananger.getString(getContext(),"remains");
                    if (json!=null){
                        JSONArray jsonArray = new JSONArray(json);
                        for (int i=0;i<jsonArray.length();i++){
                            SubscriptionRemainModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionRemainModal.class);
                            Log.e("date", "onResponse: "+data.getDate() );
                            if (data.getName().equals("7")){
                                Date date = data.getDate();
                                int futureDate = date.getYear()+1;
                                int month = date.getMonth();
                                int day = date.getDay();

                                Date today = new Date();
                                if (futureDate<=today.getYear()&&month<=today.getMonth()&&day<=today.getDay()){
                                    PrefMananger.saveString(getContext(),"sub","false");
                                }else {
                                    PrefMananger.saveString(getContext(),"sub","true");
                                }
                            }
                            if (data.getName().equals("8")){
                                Date date = data.getDate();
                                int futureDate = date.getYear()+1;
                                int month = date.getMonth()+6;
                                int day = date.getDay();

                                Date today = new Date();
                                if (futureDate<=today.getYear()&&month<=today.getMonth()&&day<=today.getDay()){
                                    PrefMananger.saveString(getContext(),"sub","false");
                                }else {
                                    PrefMananger.saveString(getContext(),"sub","true");
                                }
                            }
                            if (data.getName().equals("9")){
                                Date date = data.getDate();
                                int futureDate = date.getYear()+2;
                                int month = date.getMonth();
                                int day = date.getDay();

                                Date today = new Date();
                                if (futureDate<=today.getYear()&&month<=today.getMonth()&&day<=today.getDay()){
                                    PrefMananger.saveString(getContext(),"sub","false");
                                }else {
                                    PrefMananger.saveString(getContext(),"sub","true");
                                }
                            }
                        }
                    }else {
                        PrefMananger.saveString(getContext(),"sub","false");
                    }
                }catch (Exception e){
                    Log.e("date error", "onResponse: "+e );
                }
                if (PrefMananger.getString(getContext(),"sub").equals("true")){
                    SubscribeBOdy.setVisibility(View.GONE);
                    WelcomeMassageBody.setVisibility(View.GONE);
                }else {
                    SubscribeBOdy.setVisibility(View.VISIBLE);
                    WelcomeMassageBody.setVisibility(View.VISIBLE);
                }
            }
        };
        handler.postDelayed(runnable,7000);

        projectLiveRecyler  =view.findViewById(R.id.HOME_PROJECT_LIVE_RECYCLER);
        projectRecicvedRecyler  =view.findViewById(R.id.HOME_PROJECT_RECICVED_RECYCLER);
        projectPendingRecycler  =view.findViewById(R.id.HOME_PROJECT_PENDING_RECYCLER);

        projectLiveRecyler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        projectRecicvedRecyler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        projectPendingRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        lnr_city=view.findViewById(R.id.lnr_city_centric);
        nest_search=view.findViewById(R.id.SEARCH_NEST_PRO_SEARCH);
        rohit=view.findViewById(R.id.hello_rohit);
        subscribe=view.findViewById(R.id.HOME_SUBSCRIBE_BTN);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subscribeClick){
                    startActivity(new Intent(getContext(), SubscriptionPlan.class));
                }else {
                    if (PrefMananger.getString(getContext(),"status").equals("0")){
                        startActivity(new Intent(getContext(), EditSignUpForm.class));
//                        new WarningDio(getContext(), "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
//                            @Override
//                            public void getClicks(int click) {
//                                if (click==1){
//                                }
//                            }
//                        },false);
                    }
                    if (PrefMananger.getString(getContext(),"status").equals("1")){
                        new WarningDio(getContext(), "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                    if (PrefMananger.getString(getContext(),"status").equals("2")){
                        new WarningDio(getContext(), "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                            }
                        },false);
                    }
                }
            }
        });
        rohit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), FrontViewQueryFirst.class));
            }
        });
        how2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HowItWorks.class));
            }
        });
        nest_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchNestPro.class));
            }
        });
        edt_home=view.findViewById(R.id.edt_home_filter);
        frame_incom=view.findViewById(R.id.frame_incomplete);

        frame_second=view.findViewById(R.id.manage_property_second);

        frm = view.findViewById(R.id.CUSTOM_PRO_MANNAGE);
        rg_group=view.findViewById(R.id.rg_two);
        lr_submit=view.findViewById(R.id.HOME_PROJECT_LNR_LIVE);
        lr_accept=view.findViewById(R.id.HOME_PROJECT_LNR_PENDING);
        lr_received=view.findViewById(R.id.HOME_PROJECT_LNR_RECIVED);
        home_first= (HomeScreen) getActivity();
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.HOME_PROJECT_LIVE){
                    lr_submit.setVisibility(View.VISIBLE);
                    lr_accept.setVisibility(View.GONE);
                    lr_received.setVisibility(View.GONE);
                }
                if (checkedId==R.id.HOME_PROJECT_RECIVIED){
                    lr_submit.setVisibility(View.GONE);
                    lr_accept.setVisibility(View.GONE);
                    lr_received.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.HOME_PROJECT_PENDING){
                    lr_submit.setVisibility(View.GONE);
                    lr_accept.setVisibility(View.VISIBLE);
                    lr_received.setVisibility(View.GONE);
                }
            }
        });
        frame_incom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PlanBasicActivity.class));


            }
        });
//        showLeads();
        showProperty();
        showArticles();
        clints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(getContext(),"sub").equals("true")){
                    startActivity(new Intent(getContext(), Clints.class));
                }else {
                    if ( PrefMananger.getString(getContext(),"id").equals("true")){
                        new WarningDio(getContext(), "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(getContext(),SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(getContext(),"status").equals("0")){
                            new WarningDio(getContext(), "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(getContext(),EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("1")){
                            new WarningDio(getContext(), "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("2")){
                            new WarningDio(getContext(), "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }
                    }

                }
            }
        });
        show_All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (t){
//                    ArticleAdapter adapter =  new ArticleAdapter(getContext(), articles);
//                    articlesRecyler.setAdapter(adapter);
//                    TextView textView =  (TextView) show_All.getChildAt(0);
//                    textView.setText("Hide all("+articles.size()+")");
//                    t=false;
//                }else {
//                    ArticleAdapter adapter =  new ArticleAdapter(getContext(), article);
//                    articlesRecyler.setAdapter(adapter);
//                    TextView textView =  (TextView) show_All.getChildAt(0);
//                    textView.setText("Show all("+articles.size()+")");
//                    t=true;
//                }
                getContext().startActivity(new Intent(getContext(), Articles.class));
            }
        });
        post_property=view.findViewById(R.id.post_property_form);

        home_card_shadow_second=view.findViewById(R.id.second_card_shadow);
        lnr_parent_frg=view.findViewById(R.id.parent_home_frg_lnr);

        home_card_shadow_first=view.findViewById(R.id.card_sale_first);
        home_card_shadow_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SalePropertiesSecondForNestOwners.class));

            }
        });
        home_card_shadow_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));


            }
        });
        edt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchViewActivity.class));


            }
        });
        new_project=view.findViewById(R.id.new_projects_home);
        new_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ReraActivity.class));


            }
        });
        scrollView_home=view.findViewById(R.id.scroll_id_home_seller);
        post_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                startActivity(new Intent(getContext(), IntroScreenPostRequirement.class));
*/
            }
        });
        homeScreen= (HomeScreen) getActivity();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView_home.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY>100)
                    {
                        homeScreen.bottomNavigationView.setVisibility(View.GONE);

                    }
                    else {
                        homeScreen.bottomNavigationView.setVisibility(View.VISIBLE);
                    }
                    if (scrollY>120)
                    {
                        homeScreen.frm_haider_parent.setVisibility(View.GONE);
                        homeScreen.lnr_haider_second.setVisibility(View.VISIBLE);
                        lnr_parent_frg.setVisibility(View.GONE);

                    }
                    else {

                        homeScreen.frm_haider_parent.setVisibility(View.VISIBLE);
                        homeScreen.lnr_haider_second.setVisibility(View.GONE);
                        lnr_parent_frg.setVisibility(View.VISIBLE);                    }


                }
            });
        }
        lnr_nest_professional =view.findViewById(R.id.lnr_nest_professional);
        lnr_nest_professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));

            }
        });
        ht_properties=view.findViewById(R.id.hot_properties_nr);
        ht_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ReraActivity.class));

            }
        });
        lnr_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ReraActivity.class));

            }
        });/*
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));
*/

        frame_rera=view.findViewById(R.id.home_frame_nest);
        frame_rera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), ScreenSixteenDetailsPage.class));


            }
        });
        lnr_hello_sahil =view.findViewById(R.id.id_home_frg_hello_sahil);

        lnr_hello_sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        lnr_save_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaveMoreActivity.class));


            }
        });

        nes_professional=view.findViewById(R.id.see_all_nest_professional);
        city_centre= view.findViewById(R.id.see_all_city_centre);
        nest_project = view.findViewById(R.id.see_all_new_project);
        hot_properties=view.findViewById(R.id.see_all_hot_properties);
        featured_product=view.findViewById(R.id.see_all_featured_product);
        /*imageView = view.findViewById(R.id.search_view);*/
        tv_se_all = view.findViewById(R.id.see_all_nestowener);
        tv_new_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewProjectHomeBuyer.class));

            }
        });

        featured_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        hot_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        nest_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        city_centre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        nes_professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        tv_se_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });
        scrollhandler=0;
        getprojectOnBoard();
        PropertyRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("testRecycler", "onScrolled: "+dx+" "+dy );
               if (dx<=1){
                   if (scrollhandler==1){
                       if (layoutManager.findLastCompletelyVisibleItemPosition()>0){
                           PropertyViewModal s = propertyViewModals.get(layoutManager.findLastCompletelyVisibleItemPosition());
                           leadsByPropertyId.setText("RESPONSE ON ID:"+s.getProId());
                           getLeadsById(s.getId());
                           responseIdS=s.getId();
                           Log.e("RESPONSE ID", "onScrolled: "+responseIdS );
                           scrollhandler=0;
                           return ;
                       }else {
//                           PropertyViewModal s = propertyViewModals.get(0);
//                           leadsByPropertyId.setText("RESPONSE ON ID:"+s.getProId());
//                           getLeadsById(s.getId());
//                           scrollhandler=0;
//                           responseIdS=s.getId();
//                           Log.e("RESPONSE ID", "onScrolled: "+responseIdS );
//                           return ;
                       }

                   }
               }
               if (dx>=1){
                   if (scrollhandler==1){
                       if (layoutManager.findLastCompletelyVisibleItemPosition()>0){
                           PropertyViewModal s = propertyViewModals.get(layoutManager.findLastCompletelyVisibleItemPosition());
                           leadsByPropertyId.setText("RESPONSE ON ID:"+s.getProId());
                           getLeadsById(s.getId());
                           responseIdS=s.getId();
                           Log.e("RESPONSE ID", "onScrolled: "+responseIdS );
                           scrollhandler=0;
                           return ;
                       }else {
//                           PropertyViewModal s = propertyViewModals.get(0);
//                           leadsByPropertyId.setText("RESPONSE ON ID:"+s.getProId());
//                           getLeadsById(s.getId());
//                           scrollhandler=0;
//                           responseIdS=s.getId();
//                           Log.e("RESPONSE ID", "onScrolled: "+responseIdS );
//                           return ;
                       }

                   }
               }
            }
        });
        PropertyRecycler.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollhandler=1;
                return false;
            }
        });

        postPropertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(getContext(),"sub").equals("true")){
                getContext().startActivity(new Intent(getContext(), SelectOptionMode.class));
                }else {
                    if ( PrefMananger.getString(getContext(),"id").equals("true")){
                        new WarningDio(getContext(), "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(getContext(),SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(getContext(),"status").equals("0")){
                            new WarningDio(getContext(), "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(getContext(),EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("1")){
                            new WarningDio(getContext(), "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(getContext(),"status").equals("2")){
                            new WarningDio(getContext(), "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }
                    }
                }
            }
        });
        getAllAcceptedRejected(UserID);
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
                    showLeads();
                }else {
                    showLeads();
                }
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);
                showLeads();
            }
        });
    }
    private void getLeadsById(String id) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_LEADS_COUNT_BY_PROID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        int one = jsonObject.getInt("Makeanoffers_Lead");
                        int two = jsonObject.getInt("ContactUser_Lead");
                        int three = jsonObject.getInt("Startanoffer_Lead");
                        leadsByPropertyLeads.setText(String.valueOf(one+two+three));

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
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private void getprojectOnBoard() {
        StringRequest requestss =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_LIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectLive");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("LIVE");
                        }
                        handlerProjectArrey();
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",String.valueOf(data.getUserId()));
                return hashMap;
            }
        };

        StringRequest requests =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_RECIVED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectReceived");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("RECEIVED");
                        }
                        Volley.newRequestQueue(getContext()).add(requestss);
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
                HashMap<String,String>hashMap =  new HashMap<>();
                return hashMap;
            }
        };

        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_PENDING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status  =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ProjectPending");
                        for (int i=0;i<jsonArray.length();i++){
                            ProjectRecivedModal d = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ProjectRecivedModal.class);
                            projectIdStrings.add(d.getProject_name());
                            statusList.add("PENDING");
                        }
                        Volley.newRequestQueue(getContext()).add(requests);
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",String.valueOf(data.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }
    private void handlerProjectArrey() {
        liveList =  new ArrayList<>();
        recivedList=new ArrayList<>();
        pendingList=new ArrayList<>();
        for (int i =0;i<statusList.size();i++){
            String id = projectIdStrings.get(i);
            String status = statusList.get(i);
            getProjectInfo(id,status);
            Log.e("Proejects", "handlerProjectArrey: "+id+" "+status);
        }

    }
    private void setProjectRecyclerViews() {
        ProjectListAdapter adapter =  new ProjectListAdapter(getContext(),liveList);
        ProjectListAdapter adapterrecived =  new ProjectListAdapter(getContext(),recivedList);
        ProjectListAdapter adapterPending =  new ProjectListAdapter(getContext(),pendingList);
        projectLiveRecyler.setAdapter(adapter);
        projectRecicvedRecyler.setAdapter(adapterrecived);
        projectPendingRecycler.setAdapter(adapterPending);
    }
    private void getProjectInfo(String id,String stats) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROJRCT_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject =  new JSONObject(response);
                    String s =  jsonObject.getString("status");
                    if (s.equals("1")){
                        ProjectDetailsModal data =  new Gson().fromJson(jsonObject.getJSONObject("ProjectDetails").toString(),ProjectDetailsModal.class);
                        data.setInappUrl(jsonObject.getString("Url"));
                        data.setInappStatus(stats);
                        if (stats.equals("LIVE")){
                            liveList.add(data);
                        }
                        if (stats.equals("RECEIVED")){
                            recivedList.add(data);
                        }
                        if (stats.equals("PENDING")){
                            pendingList.add(data);
                        }
                    }
                    setProjectRecyclerViews();
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
                hashMap.put("project_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getIncompleteListings() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST_INCOMPLETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("incompleteHome", "onResponse: "+response );
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    incompleteArrey = new ArrayList<>();
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("InComplete");
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            BasicDetailsModal d = new Gson().fromJson(jsonObject1.toString(),BasicDetailsModal.class);
                            incompleteArrey.add(d);
                        }
                        adpterIncomplete =  new IncompleteHomeAdpter(getContext(),incompleteArrey);
                        IncompleteList.setAdapter(adpterIncomplete);
                    }
                }catch (Exception e){
                    Log.e("incompleteHome", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("incompleteHome", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",String.valueOf(data.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void showLeads() {
        StringRequest PublicHeros =  new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("requirement_data")&&jsonObject.has("publicpros_data")) {
                        JSONArray arreyRequrements = jsonObject.getJSONArray("requirement_data");
                        JSONArray arrayPublicPros = jsonObject.getJSONArray("publicpros_data");
                        for (int i=0;i<arreyRequrements.length();i++){
                            LeadsRequmentsModal data = new Gson().fromJson(arreyRequrements.getJSONObject(i).toString(), LeadsRequmentsModal.class);
                            if (!data.getUser_id().equals(UserID)){
                                if (getdate(data.getCreated_at(),1,0,0,0)){
                                    varifyIndianHeros(data); }
                            }
                        }
                        for (int i=0;i<arrayPublicPros.length();i++){
                            LeadsPublicPro data = new Gson().fromJson(arrayPublicPros.getJSONObject(i).toString(),LeadsPublicPro.class);
                            if (!data.getUser_id().equals(UserID)){
                                if (getdate(data.getCreated_at(),1,0,0,0)){
                                    varifyIndianheroPro(data);
                                }
                            }
                        }
                    } else {
                        Log.e("get Public Leads Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String ,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",UserID);
                return hashMap;
            }
        };

        StringRequest dubbleLeads = new StringRequest(Request.Method.POST, UrlClass.LEADS_DUBBLE, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("requirement_data")&&jsonObject.has("publicpros_data")) {
                        JSONArray arreyRequrements = jsonObject.getJSONArray("requirement_data");
                        JSONArray arrayPublicPros = jsonObject.getJSONArray("publicpros_data");
                        for (int i=0;i<arreyRequrements.length();i++){
                            DubbleLeadsRequrementsModal data = new Gson().fromJson(arreyRequrements.getJSONObject(i).toString(), DubbleLeadsRequrementsModal.class);
                            if (!data.getUser_id().equals(UserID)){
                                if (getdate(data.getCreated_at(),1,0,0,0)){
                                    getRequrementsDataByReqId(data.getRequirement_id());
                                }
                            }
                        }
                        for (int i=0;i<arrayPublicPros.length();i++){
                            DubbleLeadsProsModals data = new Gson().fromJson(arrayPublicPros.getJSONObject(i).toString(),DubbleLeadsProsModals.class);
                            if (!data.getUser_id().equals(UserID)){
                                if (getdate(data.getCreated_at(),1,0,0,0)){
                                    getPropertyInfoById(data.getProperty_id());
                                }
                            }
                        }
                    } else {
                        Log.e("get Public Leads Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
                }
                Volley.newRequestQueue(getContext()).add(PublicHeros);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Dubble error res", "onResponse: "+error );
                Volley.newRequestQueue(getContext()).add(PublicHeros);
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id", UserID);
                return hashMap;
            }
        };

        StringRequest brokerLeads = new StringRequest(Request.Method.POST, UrlClass.LEADS_BROKER, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("brokar_data")) {
                        JSONArray arreyRequrements = jsonObject.getJSONArray("brokar_data");
                        for (int i=0;i<arreyRequrements.length();i++){
                            LeadsPublicPro data = new Gson().fromJson(arreyRequrements.getJSONObject(i).toString(), LeadsPublicPro.class);
                            LeadsViewModal view =  new LeadsViewModal();
                            view.setProperty(false);
                            view.setAd(false);
                            view.setUser(true);
                            view.setName(null);
                            view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                            view.setBhk(data.getRoom_detail()+" BHK");
                            view.setStatus("NEST PROS");
                            view.setAddress(data.getCity());
                            view.setDate(getDateInFormat(data.getUpdated_at()));
                            view.setUserId(data.getUser_id());
                            view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                            view.setPropertyId(data.getProperty_id());
                            view.setWant(data.getLooking());
                            view.setId(data.getId());
//                            view.setImageurl(data.getSite_view());
                            getName(data.getUser_id(),view,leadsViewModals.size());
                            if (!data.getUser_id().equals(UserID)&&!reqAcceptedData.isEmpty()){
                                for (AllAcceptedDataModal accepted : reqAcceptedData) {
                                    Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                    Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                    if (data.getId().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                        if (accepted.getAccepts().equals("Yes")) {
                                            view.setAccepted(true);
                                        }
                                        if (accepted.getSubmittedproposal()!=null){
                                            if (accepted.getSubmittedproposal().equals("Yes")) {
                                                view.setProposalSummibted(true);
                                            }
                                        }
                                        if (accepted.getAcceptedproposal()!=null){
                                            if (accepted.getAcceptedproposal().equals("Yes")) {
                                                view.setProposalAccepted(true);
                                            }
                                        }
                                    } else {
                                        view.setAccepted(false);
                                        view.setProposalSummibted(false);
                                        view.setProposalAccepted(false);
                                    }
                                }
                            }else {
                                view.setAccepted(false);
                                view.setProposalSummibted(false);
                                view.setProposalAccepted(false);

                            }
                            if (getdate(data.getCreated_at(),1,0,0,0)){
                                if (!view.getProposalSummibted()){
                                    leadsViewModals.add(view);
                                }
                            }

                        }

                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
                }
                Volley.newRequestQueue(getContext()).add(dubbleLeads);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                        Volley.newRequestQueue(getContext()).add(dubbleLeads);
                Log.e("Dubble error res", "onResponse: "+error );
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("user_id", UserID);
                return hashMap;
            }
        };
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PUBLIC, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("requirement_data")&&jsonObject.has("publicpros_data")) {
                        JSONArray arreyRequrements = jsonObject.getJSONArray("requirement_data");
                        JSONArray arrayPublicPros = jsonObject.getJSONArray("publicpros_data");
                        for (int i=0;i<arreyRequrements.length();i++){
                            LeadsPublicModal data = new Gson().fromJson(arreyRequrements.getJSONObject(i).toString(), LeadsPublicModal.class);
                            LeadsViewModal view =  new LeadsViewModal();
                            view.setProperty(false);
                            view.setAd(false);
                            view.setUser(true);
                            view.setName(data.getName());
                            view.setBudget(getBudgetInLakhs(data.getBudget_max()));
                            view.setBhk(data.getBhk_type()+" BHK");
                            view.setStatus("BUYER");
                            view.setAddress(data.getCity());
                            view.setDate(getDateInFormat(data.getUpdated_at()));
                            view.setUserId(data.getUser_id());
                            view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                            view.setPropertyId(data.getRequirement_id());
                            view.setId(data.getId());

                            if (!data.getUser_id().equals(UserID)&&!reqAcceptedData.isEmpty()){
                                for (AllAcceptedDataModal accepted : reqAcceptedData) {
                                    Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                    Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                    if (data.getId().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                        if (accepted.getAccepts().equals("Yes")) {
                                            view.setAccepted(true);
                                        }
                                        if (accepted.getSubmittedproposal()!=null){
                                            if (accepted.getSubmittedproposal().equals("Yes")) {
                                                view.setProposalSummibted(true);
                                            }
                                        }
                                        if (accepted.getAcceptedproposal()!=null){
                                            if (accepted.getAcceptedproposal().equals("Yes")) {
                                                view.setProposalAccepted(true);
                                            }
                                        }
                                    } else {
                                        view.setAccepted(false);
                                        view.setProposalSummibted(false);
                                        view.setProposalAccepted(false);
                                    }
                                }
                            }else {
                                view.setAccepted(false);
                                view.setProposalSummibted(false);
                                view.setProposalAccepted(false);

                            }
                            if (getdate(data.getCreated_at(),1,0,0,0)){
                                if (!view.getProposalSummibted()){
                                    leadsViewModals.add(view);
                                }
                            }

                        }
                        for (int i=0;i<arrayPublicPros.length();i++){
                            LeadsPublicPro data = new Gson().fromJson(arrayPublicPros.getJSONObject(i).toString(),LeadsPublicPro.class);
                            LeadsViewModal view =  new LeadsViewModal();
                            view.setProperty(true);
                            view.setAd(false);
                            view.setUser(false);
                            view.setName(null);
                            view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                            view.setBhk(data.getRoom_detail()+" BHK");
                            view.setStatus("SELLER");
                            view.setAddress(data.getCity());
                            view.setDate(getDateInFormat(data.getUpdated_at()));
                            view.setUserId(data.getUser_id());
                            view.setPropertyId(data.getProperty_id());
                            view.setId(data.getPid());
                            view.setImageurl(data.getSite_view());
                            getName(data.getUser_id(),view,leadsViewModals.size());
                            if (!data.getUser_id().equals(UserID)&&!propertyAcceptedData.isEmpty()){
                                for (AllAcceptedDataModal accepted : propertyAcceptedData) {
                                    Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                    Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                    if (data.getPid().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                        if (accepted.getAccepts().equals("Yes")) {
                                            view.setAccepted(true);
                                        }
                                        if (accepted.getSubmittedproposal()!=null){
                                            if (accepted.getSubmittedproposal().equals("Yes")) {
                                                view.setProposalSummibted(true);
                                            }
                                        }
                                        if (accepted.getAcceptedproposal()!=null){
                                            if (accepted.getAcceptedproposal().equals("Yes")) {
                                                view.setProposalAccepted(true);
                                            }
                                        }
                                    } else {
                                        view.setAccepted(false);
                                        view.setProposalSummibted(false);
                                        view.setProposalAccepted(false);
                                    }
                                }
                            }else {
                                view.setAccepted(false);
                                view.setProposalSummibted(false);
                                view.setProposalAccepted(false);

                            }
                            if (getdate(data.getCreated_at(),1,0,0,0)){
                                if (!view.getProposalSummibted()){
                                    leadsViewModals.add(view);
                                }
                            }
                        }


                    } else {
                        Log.e("get Public Leads Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Accepted", "onResponse: "+e );
                }
                Volley.newRequestQueue(getContext()).add(brokerLeads);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Volley.newRequestQueue(getContext()).add(brokerLeads);
                Log.e("Public Response error", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap =  new HashMap<>();
                hashMap.put("user_id", UserID);
//                hashMap.put("user_id", String.valueOf(data.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);




    }
    private void varifyIndianheroPro(LeadsPublicPro data) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_FIND_INDIANHEROS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String datareso = jsonObject.getString("data");
                    if (status.equals("1") && datareso.equals("1")) {
                        getIndianHerosServicepro(data);
                        JSONObject arreyRequrements = jsonObject.getJSONObject("publicpros_data");
                        LeadsPublicPro data = new Gson().fromJson(arreyRequrements.toString(), LeadsPublicPro.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(true);
                        view.setAd(false);
                        view.setUser(false);
                        view.setName(null);
                        view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        view.setBhk(data.getRoom_detail()+" BHK");
                        view.setStatus("SELLER");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getProperty_id());
                        view.setId(data.getId());
                        view.setImageurl(data.getSite_view());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!propertyAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : propertyAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getPid().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }

                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String,String> hashMap =  new HashMap<>();
                hashMap.put("property_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getIndianHerosServicepro(LeadsPublicPro data) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS_DETAILS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject arreyRequrements = jsonObject.getJSONObject("data");
                        LeadsIdianHerosInfo datas = new Gson().fromJson(arreyRequrements.toString(), LeadsIdianHerosInfo.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(true);
                        view.setAd(false);
                        view.setUser(false);
                        view.setName(datas.getName());
                        view.setDoctor(datas.getService());
                        view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        view.setBhk(data.getRoom_detail()+" BHK");
                        view.setStatus("SELLER");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getProperty_id());
                        view.setId(data.getId());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!propertyAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : propertyAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getPid().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }
                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }
    private void varifyIndianHeros(LeadsRequmentsModal datas) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_FIND_INDIANHEROS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String datareso = jsonObject.getString("data");
                    if (status.equals("1") && datareso.equals("1")) {
                        getIndianHerosService(datas);
                        JSONObject arreyRequrements = jsonObject.getJSONObject("requirement_data");
                        LeadsPublicPro data = new Gson().fromJson(arreyRequrements.toString(), LeadsPublicPro.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(true);
                        view.setAd(false);
                        view.setUser(false);
                        view.setName(null);
                        view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        view.setBhk(data.getRoom_detail()+" BHK");
                        view.setStatus("SELLER");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getProperty_id());
                        view.setId(data.getId());
                        view.setImageurl(data.getSite_view());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!reqAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : reqAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getId().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }
                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("user_id", datas.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getIndianHerosService(LeadsRequmentsModal data) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_INDIANHEROS_DETAILS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject d = jsonObject.getJSONObject("data");
                        LeadsIdianHerosInfo datas = new Gson().fromJson(d.toString(),LeadsIdianHerosInfo.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(true);
                        view.setAd(false);
                        view.setUser(false);
                        view.setName(datas.getName());
                        view.setDoctor(datas.getService());
                        view.setBudget(getBudgetInLakhs(data.getBudget_max()));
                        view.setBhk(data.getBhk_type()+" BHK");
                        view.setStatus("SELLER");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getRequirement_id());
                        view.setId(data.getId());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!reqAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : reqAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getId().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }

                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",data.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }
    private void getPropertyInfoById(String property_id) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PROPERTY_DETAILS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("publicpros_data")) {
                        JSONObject arreyRequrements = jsonObject.getJSONObject("publicpros_data");
                        LeadsPublicPro data = new Gson().fromJson(arreyRequrements.toString(), LeadsPublicPro.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(true);
                        view.setAd(false);
                        view.setUser(false);
                        view.setName(null);
                        view.setBudget(getBudgetInLakhs(data.getExpectedprice()));
                        view.setBhk(data.getRoom_detail()+" BHK");
                        view.setStatus("SELLER");
                        view.setDoctor2("Second Deal");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getProperty_id());
                        view.setId(data.getId());
                        view.setImageurl(data.getSite_view());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!propertyAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : propertyAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getPid());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getPid().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }

                    } else {
                        Log.e("get Dubble pro", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch pro", "onResponse: "+e );
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
                HashMap<String,String> hashMap =  new HashMap<>();
                hashMap.put("property_id",property_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void getRequrementsDataByReqId(String requirement_id) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_REQ_DETAILS, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("requirement_data")) {
                        JSONObject arreyRequrements = jsonObject.getJSONObject("requirement_data");
                        LeadsRequmentsModal data = new Gson().fromJson(arreyRequrements.toString(), LeadsRequmentsModal.class);
                        LeadsViewModal view =  new LeadsViewModal();
                        view.setProperty(false);
                        view.setAd(false);
                        view.setUser(true);
                        view.setName(null);
                        view.setBudget(getBudgetInLakhs(data.getBudget_max()));
                        view.setBhk(data.getBhk_type()+" BHK");
                        view.setStatus("BUYER");
                        view.setDoctor2("Second Deal");
                        view.setAddress(data.getCity());
                        view.setDate(getDateInFormat(data.getUpdated_at()));
                        view.setUserId(data.getUser_id());
                        view.setUnlock("Unlocking in "+geUnclockTime(data.getUpdated_at()));
                        view.setPropertyId(data.getRequirement_id());
                        view.setWant(data.getWant_to());
                        view.setId(data.getId());
                        getName(data.getUser_id(),view,leadsViewModals.size());
                        if (!data.getUser_id().equals(UserID)&&!reqAcceptedData.isEmpty()){
                            for (AllAcceptedDataModal accepted : reqAcceptedData) {
                                Log.e("Accepted", "onResponse: " + accepted.getPro_id() + " " + data.getId());
                                Log.e("Accepted", "onResponse: " + accepted.getAccepts() + " " + accepted.getReject() + " " + accepted.getSubmittedproposal());
                                if (data.getId().equals(accepted.getPro_id())&&accepted.getReject() != null&&accepted.getReject().equals("No")) {
                                    if (accepted.getAccepts().equals("Yes")) {
                                        view.setAccepted(true);
                                    }
                                    if (accepted.getSubmittedproposal()!=null){
                                        if (accepted.getSubmittedproposal().equals("Yes")) {
                                            view.setProposalSummibted(true);
                                        }
                                    }
                                    if (accepted.getAcceptedproposal()!=null){
                                        if (accepted.getAcceptedproposal().equals("Yes")) {
                                            view.setProposalAccepted(true);
                                        }
                                    }
                                } else {
                                    view.setAccepted(false);
                                    view.setProposalSummibted(false);
                                    view.setProposalAccepted(false);
                                }
                            }
                        }else {
                            view.setAccepted(false);
                            view.setProposalSummibted(false);
                            view.setProposalAccepted(false);

                        }
                        if (getdate(data.getCreated_at(),1,0,0,0)){
                            if (!view.getProposalSummibted()){
                                leadsViewModals.add(view);
                            }
                        }
                    } else {
                        Log.e("get Dubble Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Dubble Catch error", "onResponse: "+e );
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
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("requirement_id", requirement_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void setLeadsRecycler(){
        LeadsRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        LeadsAdapter adapter =  new LeadsAdapter(getContext(), leadsViewModals);
        LeadsRecycler.setAdapter(adapter);
    }
    private void getName(String user_id,LeadsViewModal leadsViewModal,int i) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")){
                        JSONObject da = jsonObject.getJSONObject("data");
                        user = new Gson().fromJson(da.toString(), User.class);
                        dpModal=new Gson().fromJson(jsonObject.getString("Photo").toString(),DpModal.class);
                        Log.e("Name Func", "getName: "+user.getFirst_name() );
                        leadsViewModal.setName(user.getFirst_name());
                        leadsViewModal.setImageurl(dpModal.getProfile_photo());
                        leadsViewModals.set(i,leadsViewModal);
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
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", user_id);
                return hashMap;
            }

        };
        Volley.newRequestQueue(getContext()).add(request);
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
        Calendar calendar =  Calendar.getInstance();
        String time =  updated_at.split("T")[1];
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        String secTemp = time.split(":")[2];
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date past = format.parse(updated_at);
            calendar.setTime(past);
            Date now = new Date();
            long letestHour = now.getHours();
            long letestSec = now.getMinutes();

            if (past.compareTo(now)>0){
                Log.e("TIME", "geUnclockTime: now is greter" );
                if (letestHour<=Long.getLong(hour)){
                    long t = letestHour-Integer.parseInt(hour);
                    data = String.valueOf(t);
                }else{
                    long t = Integer.parseInt(hour)-letestHour;
                    data = String.valueOf(t);
                }

            }
            if (past.compareTo(now)<0){
                Log.e("TIME", "geUnclockTime: now is less");
                data="0sec";
            }
            if (past.compareTo(now)==0){
                Log.e("TIME", "geUnclockTime: now is equl" );
                data="0sec";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    private void showProperty(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String stats = jsonObject.getString("status");
                    Log.e("PropertyShow", "onResponse: "+response );
                    if (stats.equals("1")&&jsonObject.has("Properties")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("Properties");
                        ArrayList<LeadsPublicPro> tosave =  new ArrayList<>();
                        propertyCount.setText(String.valueOf(jsonArray.length()));
                        propertyViewModals = new ArrayList<>();
                        if (jsonArray.length()==0){
                            postPropertyBody.setVisibility(View.VISIBLE);
                        }
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            LeadsPublicPro d =  new Gson().fromJson(object.toString(),LeadsPublicPro.class);
                            propertyReciver =  new PropertyReciver(getContext(),d.getUser_id(),d.getProperty_id());
                            PropertyViewModal views =  new PropertyViewModal();
                            views.setStatus(null);
                            views.setId(d.getProperty_id());
                            views.setPrice(getBudgetInLakhs(d.getExpectedprice()));
                            views.setRentstatus(d.getProperty());
                            views.setAddress(d.getLocality()+","+d.getCity());
                            views.setImageUrl(d.getSite_view());
                            views.setStatus(d.getProperypost());
                            views.setProId(d.getId());
                            propertyViewModals.add(views);
                            tosave.add(d);

                        }
                        PrefMananger.savePropertyiList(getContext(),tosave);
                       if (PrefMananger.getString(getContext(),"sub").equals("true")){
                           PropertyViewAdapter adapter =  new PropertyViewAdapter(getContext(), propertyViewModals);
                           PropertyRecycler.setAdapter(adapter);
                       }
                        PropertyViewModal s = propertyViewModals.get(0);
                        leadsByPropertyId.setText("RESPONSE ON ID:"+s.getProId());
                        getLeadsById(s.getId());
                    }
                }catch (Exception e){
                    Log.e("PropertyShowEX", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("PropertyShowER", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id", String.valueOf(data.getUserId()));
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void showArticles(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_ARTICLES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("Article_data")) {
                        JSONArray object = jsonObject.getJSONArray("Article_data");
                        int length =  object.length();
                        for (int i=0;i<length;i++){
                            JSONObject data = object.getJSONObject(i);
                            ArticleModal t = new Gson().fromJson(data.toString(), ArticleModal.class);
                            articles.add(t);
                            if (i<=1){
                                article.add(t);
                            }
                        }
                        articlesRecyler.setLayoutManager(new GridLayoutManager(getContext(),2));
                        ArticleAdapter articleAdapter =  new ArticleAdapter(getContext(), article);
                        articlesRecyler.setAdapter(articleAdapter);
                        TextView textView = (TextView) show_All.getChildAt(0);
                        textView.setText("Show all("+object.length()+")");
                    } else {
                        Log.e("get Profile Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Article Catch error", "onResponse: "+e );
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
                HashMap<String,String> hashMap = new HashMap<>();
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void handleProfileSumbition() {
        int res = getResources().getIdentifier(String.valueOf(R.drawable.green_circle), "drawable", getContext().getPackageName());
        int no = getResources().getIdentifier(String.valueOf(R.drawable.close_icon_multipul), "drawable", getContext().getPackageName());
        String id = String.valueOf(data.getUserId());
        StringRequest getProfile = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        LoginPojo loginPojo = new Gson().fromJson(object.toString(), LoginPojo.class);
                        if (loginPojo.getStatus().equals("0")){
                            imgSUMBITED.setImageResource(res);
                            imgAPPROVED.setImageResource(no);
                            imgVARIFIED.setImageResource(no);
                            imgRECIVED.setImageResource(no);
                            statusText.setText("Sumbited");
                            SubscribeBOdy.setVisibility(View.VISIBLE);
                            TextView textView =  (TextView)subscribe.getChildAt(0);
                            textView.setText("Sumbit Profile");
                            PrefMananger.saveString(getContext(),"status","1");
                        }
                        if (loginPojo.getStatus().equals("1")){
                            imgSUMBITED.setImageResource(res);
                            imgRECIVED.setImageResource(res);
                            imgVARIFIED.setImageResource(no);
                            imgAPPROVED.setImageResource(no);
                            statusText.setText("Pending");
                            SubscribeBOdy.setVisibility(View.VISIBLE);
                            PrefMananger.saveString(getContext(),"status","2");
                        }
                        if (loginPojo.getStatus().equals("2")){
                            imgRECIVED.setImageResource(res);
                            imgVARIFIED.setImageResource(res);
                            imgAPPROVED.setImageResource(res);
                            imgSUMBITED.setImageResource(res);
                            statusText.setText("Approved");
                            subscribeClick=true;
                            PrefMananger.saveString(getContext(),"id","true");
                        }
                        if (PrefMananger.getString(getContext(),"sub").equals("true")){
                            SubscribeBOdy.setVisibility(View.GONE);
                            WelcomeMassageBody.setVisibility(View.GONE);
                        }else {
                            SubscribeBOdy.setVisibility(View.VISIBLE);
                            WelcomeMassageBody.setVisibility(View.VISIBLE);
                            postPropertyBody.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Log.e("get Profile Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("get Profile Error", "onResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("get Profile Error", "onResponse: "+error );
                imgSUMBITED.setImageResource(no);
                imgAPPROVED.setImageResource(no);
                imgVARIFIED.setImageResource(no);
                imgRECIVED.setImageResource(no);
                statusText.setText("Profile Incomplete");
                PrefMananger.saveString(getContext(),"status","0");
                subscribeClick=false;
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap =  new HashMap<>();
                hashMap.put("user_id", id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(getProfile);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean getdate(String dates,int day,int month,int year,int hour){
        boolean isdate = true;
        Calendar calendar =  Calendar.getInstance();
        Date toCampareWith =  calendar.getTime();

       try {
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
           format.setTimeZone(TimeZone.getTimeZone("UTC"));
           Date date1 = format.parse(dates);
           calendar.setTime(date1);
           calendar.add(Calendar.DATE,day);
           calendar.add(Calendar.MONTH,month);
           calendar.add(Calendar.YEAR, year);
           calendar.add(Calendar.HOUR, hour);
           Date date = calendar.getTime();

           if (toCampareWith.compareTo(date)==1||toCampareWith.compareTo(date)==0){
                isdate=false;
           }


       }catch (Exception e){

       }
           return isdate;
    }




}
