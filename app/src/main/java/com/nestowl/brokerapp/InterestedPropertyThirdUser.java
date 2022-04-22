package com.nestowl.brokerapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.AmentiesAdapter;
import com.nestowl.AdapterClass.Pager;
import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.Fragment.EmptyFragment;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.LeadsClicks;
import com.nestowl.model.AllAcceptedDataModal;
import com.nestowl.model.AmentiesListModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ResponseAllacceptRejectModal;
import com.nestowl.model.User;
import com.nestowl.model.aichat;
import com.nestowl.utils.AmentiesImages;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class InterestedPropertyThirdUser extends AppCompatActivity {
    FrameLayout viewContact,makeoffer,buywithNestPros,exploreMore,powerBackup,maditaion,swimingpool,amentiesMore,providedInfoYes,providedInfoNo,accept,reject,chat,ON_SAMEUSER_CONTACT,ON_SAME_USER_BOTTOM_INFO_1,ON_SAME_USER_BOTTOM_INFO_2;
    LinearLayout leadsDataCorrect,leadsDataCorrectOptions,otherCharges,frm_third;
    TextView title,price,date,address,ownername,ownerMob,carpetArea,address2,location,configration,status,frunishing,proDescription,proDescriptionReadMore,overLokking,openSides,funishing2,area,desclaimer,desclaimerReadMore;
    TabLayout tab_filters;
    ViewPager viewPager_filter;
    ScrollView scroll;
    ImageView back_img,back,backImage,shareProperty;
    HorizontalScrollView horizontalScrollView;
    Intent intent;
    String id,user_id,description,otherChargesTXT,idd,desclaimerS,URL;
    User user;
    LoginPojo loginPojo ;
    ArrayList<String> keyvalues,valuesData;
    ArrayList<FloorPojo> floorPojos;
    ArrayList<AmentiesListModal> amenties,homeListAMenties;
    LiveCommnication liveCommnication;
    boolean acceptStatusPro,typeAccept,isUser,isAccepted,isSummited,isProposalAccepted,isBroker;
    LeadsClicks handleLeads;
    LeadsPublicPro data;
    RecyclerView recyclerView;
    AppBarLayout appBarLayout;
    int proposalSumiitedTotal;
    ArrayList<AllAcceptedDataModal> propertyAcceptedData;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_property_third_user);
        intent = getIntent();
        proposalSumiitedTotal=0;
        Uri uri = getIntent().getData();
        id=intent.getStringExtra("id");
        user_id=intent.getStringExtra("user_id");
        idd=intent.getStringExtra("idd");
        URL = UrlClass.BASE_URL+"property/";

        if (uri!=null){
            String data = uri.toString();
            String[] splits = data.split("/");
            for (String s : splits){
                if (s.contains("NESTOWL")){
                    Log.e("URL GET", "onCreate: "+s );
                    id=s;
                }
            }
        }
        isBroker=intent.getBooleanExtra("isBroker",false);
        isAccepted=intent.getBooleanExtra("isAccepted",false);
        isSummited=intent.getBooleanExtra("isSummited",false);
        isProposalAccepted=intent.getBooleanExtra("isProposalAccepted",false);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back=findViewById(R.id.PROPERTY_PREVIEW_BACK);
        backImage=findViewById(R.id.LEADS_PROPERTY_DETA_BG_IMAGE);
        appBarLayout=findViewById(R.id.PROPERTY_PREVIEW_APPBAR);
        shareProperty=findViewById(R.id.LEADS_PROPERTY_DETA_SHARE);
        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        propertyAcceptedData=new ArrayList<>();
        shareProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Check out this property I found using Nestowl mobile app";
                String link = URL;
//                intent.putExtra(Intent.EXTRA_TEXT,body);
                intent.putExtra(Intent.EXTRA_TEXT,body+"\n"+link);
                startActivity(Intent.createChooser(intent,"Share using")) ;
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        isUser=true;
        loginPojo = PrefMananger.GetLoginData(this);

        amenties =  new ArrayList<>();
        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        scroll=findViewById(R.id.scroll_tab_layout);
        horizontalScrollView=findViewById(R.id.btm_horizontally);
        frm_third=findViewById(R.id.LEADS_PRO_IMAGES_OPEN);
        frm_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InterestedPropertyThirdUser.this, PropertiesBhkActivity.class);
                intent.putExtra("key",id);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });

        tab_filters = findViewById(R.id.tab_filter_screen);
        setupViewPager(viewPager_filter);
        valuesData =  new ArrayList<>();
        keyvalues =  new ArrayList<>();
        tab_filters.setupWithViewPager(viewPager_filter);
        tab_filters.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition()==0){
                    scroll.scrollTo(0,400);
                }else if (tab.getPosition()==1){
                    scroll.scrollTo(0,1050);
                }else if (tab.getPosition()==2){
                    scroll.scrollTo(0,1700);
                }else if (tab.getPosition()==3){
                    scroll.scrollTo(0,2200);
                }else if (tab.getPosition()==4){
                    scroll.scrollTo(0,2500);
                }else if (tab.getPosition()==5){
                    scroll.scrollTo(0,2850);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        // scroll.setOnTouchListener(handleTouch);
        scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                tab_filters.setVisibility(View.VISIBLE);
                horizontalScrollView.setVisibility(View.GONE);

                if (scrollY>=300){

                }else {

                }
                if (scrollY>=400){
                    if (isUser){
                        horizontalScrollView.setVisibility(View.VISIBLE);

                    }
                }
                if (scrollY>=2850){
                    viewPager_filter.setCurrentItem(5);
                }else if (scrollY>=2500){
                    viewPager_filter.setCurrentItem(4);
                }else if (scrollY>=2200){
                    viewPager_filter.setCurrentItem(3);
                }else if (scrollY>=1700){
                    viewPager_filter.setCurrentItem(2);
                }else if (scrollY>=1050) {
                    viewPager_filter.setCurrentItem(1);
                }else if (scrollY>=400){
                    viewPager_filter.setCurrentItem(0);
                }else {
                    viewPager_filter.setCurrentItem(0);
                    tab_filters.setVisibility(View.GONE);
                    frm_third.setVisibility(View.VISIBLE);
                    appBarLayout.setVisibility(View.GONE);

                }
                if (scrollY>=50){
                    appBarLayout.setVisibility(View.VISIBLE);
                    frm_third.setVisibility(View.GONE);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        data =  new LeadsPublicPro();
        typeAccept = true;
        getSummitedProposals(id);
        title=findViewById(R.id.LEADS_PROPERTY_DETA_TITLE);
        price=findViewById(R.id.LEADS_PROPERTY_DETA_PRICE);
        date=findViewById(R.id.LEADS_PROPERTY_DETA_DATE);
        address=findViewById(R.id.LEADS_PROPERTY_DETA_ADDRESS);
        ownername=findViewById(R.id.LEADS_PROPERTY_DETA_OWNER_NAME);
        ownerMob=findViewById(R.id.LEADS_PROPERTY_DETA_OWNER_MOB);
        carpetArea=findViewById(R.id.LEADS_PROPERTY_DETA_CARPET_AREA);
        address2=findViewById(R.id.LEADS_PROPERTY_DETA_ADDRES);
        location=findViewById(R.id.LEADS_PROPERTY_DETA_LOCATION);
        configration=findViewById(R.id.LEADS_PROPERTY_DETA_CONFIGRATION);
        status=findViewById(R.id.LEADS_PROPERTY_DETA_STATUS);
        frunishing=findViewById(R.id.LEADS_PROPERTY_DETA_FRUNISHING);
        proDescription=findViewById(R.id.LEADS_PROPERTY_DETA_DESCRIPTION);
        proDescriptionReadMore=findViewById(R.id.LEADS_PROPERTY_DETA_DESCRIPTION_READMORE);
        overLokking=findViewById(R.id.LEADS_PROPERTY_DETA_OVERLOOKING);
        openSides=findViewById(R.id.LEADS_PROPERTY_DETA_OPEN_SIDES);
        funishing2=findViewById(R.id.LEADS_PROPERTY_DETA_FRUNISHING_2);
        area=findViewById(R.id.LEADS_PROPERTY_DETA_AREEA);
        desclaimer=findViewById(R.id.LEADS_PROPERTY_DETA_DESCLAIMER_TXT);
        desclaimerReadMore=findViewById(R.id.LEADS_PROPERTY_DETA_DESCLAIMER);
        recyclerView=findViewById(R.id.LEADS_PROPERTY_RECYCLER);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true));
        homeListAMenties=new ArrayList<>();
//        frames
        viewContact=findViewById(R.id.LEADS_PROPERTY_DETA_OWNER_CONTACT);
        makeoffer=findViewById(R.id.LEADS_PROPERTY_DETA_MAKE_OFFER);
        buywithNestPros=findViewById(R.id.LEADS_PROPERTY_DETA_BUYTHIS);
        exploreMore=findViewById(R.id.LEADS_PROPERTY_DETA_EXPLORE_MORE);
        powerBackup=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_POWER_BACKUP);
//        swimingpool=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_SWIMING_POOL);
//        maditaion=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_MEDIATION);
        amentiesMore=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_MORE);
        providedInfoYes=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBIT_YES);
        providedInfoNo=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBIT_NO);
        accept=findViewById(R.id.LEADS_PROPERTY_DETA_ACCEPT);
        reject=findViewById(R.id.LEADS_PROPERTY_DETA_REJECT);
        chat=findViewById(R.id.LEADS_PROPERTY_DETA_CHAT);
//        lenearlayouts
        leadsDataCorrect=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBITION_VIEW);
        leadsDataCorrectOptions=findViewById(R.id.lnr_visible_show);
        otherCharges=findViewById(R.id.LEADS_PROPERTY_DETA_OTHER_CHARGES);
//        cliks
        if (isAccepted){
            typeAccept=false;
            aceepted();
        }
        if (isBroker){
            TextView textView = (TextView) accept.getChildAt(0);
            reject.setVisibility(View.GONE);
            textView.setText("View Contact");
            typeAccept = false;
        }

        viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeAccept){
                    new WarningDio(InterestedPropertyThirdUser.this, "You need to Accept Order First", "Accept now", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                accept.callOnClick();
                            }
                        }
                    },false);
                }else {
                   if (isProposalAccepted){
                       Intent intent =  new Intent(InterestedPropertyThirdUser.this,ViewContact.class);
                       intent.putExtra("pid",id);
                       intent.putExtra("user_id",user_id);
                       intent.putExtra("id",idd);
                       startActivity(intent);
                   }else {
                       if (isBroker){
                           Intent intent =  new Intent(InterestedPropertyThirdUser.this,ViewContact.class);
                           intent.putExtra("pid",id);
                           intent.putExtra("user_id",user_id);
                           intent.putExtra("id",idd);
                           startActivity(intent);
                       }else {
                           new WarningDio(InterestedPropertyThirdUser.this, "Your proposal not accepted by owner", "OK", null, new WarningDio.Response() {
                               @Override
                               public void getClicks(int click) {
                                   if (click==1){

                                   }
                               }
                           },false);
                       }

                   }
                }
            }
        });
        makeoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        amentiesMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Dialog dio =  new Dialog(InterestedPropertyThirdUser.this);
                    dio.setContentView(R.layout.amenties_layout);
                    ImageView cancle = dio.findViewById(R.id.iv3);
                    RecyclerView recyclerView = dio.findViewById(R.id.AMENTIES_CARD_RECYCLER);
                    recyclerView.setLayoutManager(new GridLayoutManager(InterestedPropertyThirdUser.this,3));
                    AmentiesAdapter adapter = new AmentiesAdapter(InterestedPropertyThirdUser.this,amenties);
                    recyclerView.setAdapter(adapter);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dio.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    dio.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(InterestedPropertyThirdUser.this, android.R.color.transparent)));
                    dio.getWindow().setAttributes(lp);
                    dio.show();
                    cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dio.dismiss();

                        }
                    });
                }catch (Exception e){
                    Log.e("Amentiies Error", "onClick: "+e );
                }

            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSummited){
                    Intent intent =  new Intent(InterestedPropertyThirdUser.this,FrontViewQuerySecond.class);
                    intent.putExtra("id",id);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                    return;
                }
                if (typeAccept){
                    new WarningDio(InterestedPropertyThirdUser.this, "Do you want to accept", "Yes", "No", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                boolean ds = handleLeads.acceptProperty();
                            }
                        }
                    },false);
                }else {
                    if (isBroker){
                        Intent intent =  new Intent(InterestedPropertyThirdUser.this,ViewContact.class);
                        intent.putExtra("pid",id);
                        intent.putExtra("user_id",user_id);
                        intent.putExtra("id",idd);
                        startActivity(intent);
                        return;
                    }
                   if (proposalSumiitedTotal<=9){
                       Intent intent =  new Intent(InterestedPropertyThirdUser.this,QueryMakeOfferFirstPages.class);
                       intent.putExtra("user_id",user.getUser_id());
                       intent.putExtra("id",id);
                       PrefMananger.saveString(InterestedPropertyThirdUser.this,PrefMananger.PROPOSAL_ID,id);
                       startActivity(intent);
                   }else {
                       new WarningDio(InterestedPropertyThirdUser.this, "This property has reached on his proposal summiting limit", "OK", null, new WarningDio.Response() {
                           @Override
                           public void getClicks(int click) {

                           }
                       },false);
                   }
                }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDio(InterestedPropertyThirdUser.this, "Do you want to reject", "Yes", "No", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                                boolean d =  handleLeads.rejectProperty();
                        }
                    }
                },false);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InterestedPropertyThirdUser.this,Chating.class);
                intent.putExtra("key",user.getUser_id());
                intent.putExtra("name",user.getFirst_name());
                intent.putExtra("dp",user.getAvatar());
                startActivity(intent);
            }
        });
        proDescriptionReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InterestedPropertyThirdUser.this,ReadMoreProperty.class);
                intent.putExtra("topic","Property Description");
                intent.putExtra("txt",description);
                startActivity(intent);
            }
        });
        desclaimerReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InterestedPropertyThirdUser.this,ReadMoreProperty.class);
                intent.putExtra("topic","Disclaimer");
                intent.putExtra("txt",desclaimerS);
                startActivity(intent);
            }
        });
        exploreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(InterestedPropertyThirdUser.this,LeadsPropertyShowMore.class);
                intent.putExtra("values",keyvalues);
                intent.putExtra("data",valuesData);
                startActivity(intent);
            }
        });
        providedInfoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               floorPojos=new ArrayList<>();
                addItems("Property Sold Out");
                addItems("Incorrect Price");
                addItems("Incorrect Location");
                addItems("Wrong Contact Info");
                addItems("Other");
                new DialogOpenClass(InterestedPropertyThirdUser.this, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        leadsDataCorrect.setVisibility(View.VISIBLE);
                        leadsDataCorrectOptions.setVisibility(View.GONE);
                    }
                },floorPojos);
            }
        });
        providedInfoYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leadsDataCorrect.setVisibility(View.VISIBLE);
                leadsDataCorrectOptions.setVisibility(View.GONE);
            }
        });
        otherCharges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupOtherCharg();
            }
        });
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                aichat d =  aichat;
                String key =  d.getText();
                String value = d.getValue();
                if (key.equals("AP")){
                    if (value.equals("true")){
                        acceptStatusPro = true;
                        setproposalPRO();
                    }else{
                        acceptStatusPro = false;
                        setproposalPRO();
                    }
                }
            }
        });
        FecthPropertyDataById(id);
        getAllAcceptedRejected(user.getUser_id());
        handleLeads =  new LeadsClicks(InterestedPropertyThirdUser.this,idd,user.getUser_id(),idd);
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
                    for (AllAcceptedDataModal d : propertyAcceptedData){
                        if (d.getPro_id().equals(idd)){
                            if (d.getAccepts()!=null){
                                if (d.getAccepts().equals("Yes")){
                                    isAccepted=true;
                                }
                            }else {
                                isAccepted=false;
                            }
                            if (d.getAcceptedproposal()!=null){
                                if (d.getAcceptedproposal().equals("Yes")){
                                    isProposalAccepted=true;
                                }
                            }else {
                                isProposalAccepted=false;
                            }
                            if (d.getSubmittedproposal()!=null){
                                if (d.getSubmittedproposal().equals("Yes")){
                                    isSummited=true;
                                }
                            }else {
                                isSummited=false;
                            }
                        }
                    }
                    handlerAceptsRejects();
                }else {
                }
            }

            @Override
            public void onFailure(Call<ResponseAllacceptRejectModal> call, Throwable t) {
                Log.e("responseByRetroFit", "onResponse: "+t);
            }
        });
    }

    private void handlerAceptsRejects() {
        if (isAccepted){
            typeAccept=false;
            aceepted();
        }
        if (isSummited){
            TextView textView = (TextView) accept.getChildAt(0);
            reject.setVisibility(View.GONE);
            textView.setText("View Proposal");
        }
    }

    private void getSummitedProposals(String propertyID) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PROPOSAL_SUMMITED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        proposalSumiitedTotal=jsonObject.getInt("submittedproposal_data");
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
                hashMap.put("pro_id",propertyID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void userIsViewingProperty() {
        isUser=false;
        ON_SAMEUSER_CONTACT=findViewById(R.id.LEADS_PROPERTY_SAME_CONTATC);
        ON_SAME_USER_BOTTOM_INFO_1=findViewById(R.id.LEADS_PROPERTY_HIDE_INFO_1);
        ON_SAME_USER_BOTTOM_INFO_2=findViewById(R.id.LEADS_PROPERTY_HIDE_INFO_2);

        ON_SAMEUSER_CONTACT.setVisibility(View.GONE);
        ON_SAME_USER_BOTTOM_INFO_1.setVisibility(View.GONE);
        ON_SAME_USER_BOTTOM_INFO_2.setVisibility(View.GONE);


    }
    private void popupOtherCharg() {
        Dialog dio =  new Dialog(this);
        dio.setContentView(R.layout.other_charges_layout);
        TextView resale = dio.findViewById(R.id.DIO_OTHER_CHARGES_RESALE);
        TextView REG = dio.findViewById(R.id.DIO_OTHER_CHARGES_REAGISTRATION);
        ImageView cancel = dio.findViewById(R.id.DIO_OTHER_CHARGES_DISSMISED);
        resale.setText("₹ "+otherChargesTXT);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dio.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dio.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, android.R.color.transparent)));
        dio.getWindow().setAttributes(lp);
        dio.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dio.dismiss();
            }
        });

    }
    private void FecthPropertyDataById(String id) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_PROPERTY_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("apiFetch", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String statuss = jsonObject.getString("status");
                    if (statuss.equals("1") && jsonObject.has("publicpros_data")) {
                        data = new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(),LeadsPublicPro.class);
                        getUserInfo(data.getUser_id());
                        URL=URL+data.getPropertytype().toLowerCase()+"/"+data.getProperty_id()+"/"+data.getProperty().replace(" ","-").toLowerCase();
                        user_id =  data.getUser_id();
                        idd= data.getPid();
                        if (user_id.equals(String.valueOf(loginPojo.getUserId()))){
                            userIsViewingProperty();
                        }
                        title.setText(data.getPropertytype()+", "+data.getProperty());
                        price.setText("₹ "+getBudgetInLakhs(data.getExpectedprice()));
                        date.setText("Posted on:"+getDateInFormat(data.getCreated_at()));
                        address.setText(data.getLocality()+", "+data.getCity());
                        address.setSelected(true);
                        carpetArea.setText(data.getCaptur_area_max()+data.getCaptur_area_max_mezor());
                        address2.setText(data.getLocality()+", "+data.getCity());
                        location.setText(data.getLocality());
                        configration.setText(data.getBedrooms()+"Beds,"+data.getWashrooms()+"Wash");
                        status.setText(data.getPossession_status());
                        frunishing.setText(data.getFurnished_status());
                        proDescription.setText(data.getKey_selling_points());
                        overLokking.setText(data.getOverlooking());
                        openSides.setText(data.getNo_of_open_sides());
                        funishing2.setText(data.getFurnished_status());
                        area.setText(data.getBedrooms()+"Beds,"+data.getWashrooms()+"Wash");
                        description=data.getKey_selling_points();
                        desclaimerS=description;
                        Glide.with(InterestedPropertyThirdUser.this).load(UrlClass.BASE_URL+data.getSite_view()).placeholder(R.drawable.default_x_y).into(backImage);
                        String[] amnties = data.getAmenities().split(",");
                        HashMap<String,String>hashMap = AmentiesImages.getLogos();
                        int i =0;
                        for (String s : amnties){
                            AmentiesListModal l =  new AmentiesListModal();
                            String moreFilter = s.replace("[","");
                            String moreFilter1 = moreFilter.replace("]","");
                            l.setText(moreFilter1);
                            String nospace = moreFilter1.replace(" ","");
                            l.setImage(hashMap.get(nospace.toLowerCase()));
                            amenties.add(l);
                            Log.e("images", "onResponse: "+l.getText()+" to  "+l.getImage()+" "+data.getProperty_id());
                            if (i!=3){
                                homeListAMenties.add(l);
                                i++;
                            }
                        }
//                        if (data.getAmenities().contains("[")){
//                            StringArreyModal arreyModal =  new Gson().fromJson(jsonObject.getJSONObject("publicpros_data").toString(),StringArreyModal.class);
//                            for (String s:arreyModal.getAmenities()){
//                                AmentiesListModal l =  new AmentiesListModal();
//                                l.setText(s);
//                                String nospace = s.replace(" ","");
//                                l.setImage(hashMap.get(nospace.toLowerCase()));
//                                amenties.add(l);
//                                Log.e("images", "onResponse: "+l.getText()+" to  "+l.getImage()+" "+data.getProperty_id());
//                            }
//                        }
                        arreyHandler(data);
                        otherChargesTXT=data.getOther_charge();
                        AmentiesAdapter adapter =  new AmentiesAdapter(InterestedPropertyThirdUser.this,homeListAMenties);
                        recyclerView.setAdapter(adapter);


                    } else {
                        Log.e("get PropertyDeta Error", "onResponse: "+jsonObject.getString("message") );
                    }
                }catch (Exception e){
                    Log.e("Property Catch error", "onResponse: "+e );
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
                HashMap<String,String>hashMap= new HashMap<>();
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
    private void arreyHandler(LeadsPublicPro data) {
        keyvalues.add("Property Features");
        valuesData.add(data.getProperty_features());

        keyvalues.add("Sub usp");
        valuesData.add(data.getSb_usp());

        keyvalues.add("Office on floor");
        valuesData.add(data.getOffice_on_the_floor());

        keyvalues.add("Other Features");
        valuesData.add(data.getOther_features());

        keyvalues.add("Metro Railway Name");
        valuesData.add(data.getMetro_railway_name());

        keyvalues.add("Metro Railway Distance");
        valuesData.add(data.getMetro_railway_dist());

        keyvalues.add("Bus Stop Name");
        valuesData.add(data.getBus_stops_name());

        keyvalues.add("Bus Stop Distance");
        valuesData.add(data.getBus_stops_dist());

        keyvalues.add("Airport Name");
        valuesData.add(data.getAirports_name());

        keyvalues.add("Airport Dist");
        valuesData.add(data.getAirports_dist());

        keyvalues.add("Shopping Mall Name");
        valuesData.add(data.getShopping_malls_name());

        keyvalues.add("Shopping Mall Distance");
        valuesData.add(data.getShopping_malls_dist());

        keyvalues.add("Office Complex Name");
        valuesData.add(data.getOffice_complex_name());

        keyvalues.add("Office Complex Distance");
        valuesData.add(data.getOffice_complex_dist());

        keyvalues.add("College University Name");
        valuesData.add(data.getCollege_univercity_name());

        keyvalues.add("Collage University Distance");
        valuesData.add(data.getCollege_univercity_dist());

        keyvalues.add("Market name");
        valuesData.add(data.getMarket_name());

        keyvalues.add("Market Distance");
        valuesData.add(data.getMarket_dist());

        keyvalues.add("Playground Park Name");
        valuesData.add(data.getPlayground_park_name());

        keyvalues.add("Playground Park Distance");
        valuesData.add(data.getPlayground_park_dist());

        keyvalues.add("Auto Taxi Stand Name");
        valuesData.add(data.getAuto_taxi_stand_name());

        keyvalues.add("Auto Taxi Stand Distance");
        valuesData.add(data.getAuto_taxi_stand_dist());

        keyvalues.add("Gym Name");
        valuesData.add(data.getGym_name());

        keyvalues.add("Gym Distance");
        valuesData.add(data.getGym_dist());

        keyvalues.add("Police Station Name");
        valuesData.add(data.getPolice_station_name());

        keyvalues.add("Police Station Dist");
        valuesData.add(data.getPolice_station_dist());

        keyvalues.add("Room Details");
        valuesData.add(data.getRoom_detail());

        keyvalues.add("Room Length");
        valuesData.add(data.getRoomlength());

        keyvalues.add("Room Breadth");
        valuesData.add(data.getRoombreadth());

        keyvalues.add("Room Hall");
        valuesData.add(data.getRoomhall());

        keyvalues.add("Master Room");
        valuesData.add(data.getMasterroom());

        keyvalues.add("Total Floors");
        valuesData.add(data.getTotal_no_of_floor());

        keyvalues.add("Floor Belongs");
        valuesData.add(data.getFloors_belongs());

        keyvalues.add("Furnished Status");
        valuesData.add(data.getFurnished_status());

        keyvalues.add("Washroom");
        valuesData.add(data.getWashrooms());

        keyvalues.add("Cafeteria");
        valuesData.add(data.getCafeteria());

        keyvalues.add("Total Cafeteria");
        valuesData.add(data.getNo_of_cafeteria());

        keyvalues.add("Shared Cafeteria");
        valuesData.add(data.getNo_of_share_cafeteria());

        keyvalues.add("Area Type");
        valuesData.add(data.getArea_type());

        keyvalues.add("Min Capture Area");
        valuesData.add(data.getCaptur_area_min()+data.getCaptur_area_min_mezor());

        keyvalues.add("Max Capture Area");
        valuesData.add(data.getCaptur_area_max()+data.getCaptur_area_max_mezor());

        keyvalues.add("Min Buildup Area");
        valuesData.add(data.getBuildup_area_min()+data.getBuildup_area_min_mezor());

        keyvalues.add("Max Builddup");
        valuesData.add(data.getBuildup_area_max()+data.getBuildup_area_max_mezor());

        keyvalues.add("Min Super Area");
        valuesData.add(data.getSuper_area_min()+data.getSuper_area_min_mezor());

        keyvalues.add("Max Super Area");
        valuesData.add(data.getSuper_area_max()+data.getSuper_area_max_mezor());

        keyvalues.add("Area");
        valuesData.add(data.getArea()+data.getArea_mezor());

        keyvalues.add("Plot Area");
        valuesData.add(data.getPlot_area()+data.getPlot_area_mezor());

        keyvalues.add("Plot Length");
        valuesData.add(data.getPlotlength_ft());

        keyvalues.add("Plot Breadth");
        valuesData.add(data.getPlotbreadth_ft());

        keyvalues.add("Possession");
        valuesData.add(data.getPossession());

        keyvalues.add("Possession Status");
        valuesData.add(data.getPossession_status());

        keyvalues.add("AGE");
        valuesData.add(data.getAge_property());

        keyvalues.add("Transaction");
        valuesData.add(data.getTransaction_type());

        keyvalues.add("Allowed Floor");
        valuesData.add(data.getFloor_allowed_for_construction());

        keyvalues.add("All Floor Belong");
        valuesData.add(data.getFloors_belongs());

        keyvalues.add("Open Sides");
        valuesData.add(data.getNo_of_open_sides());

        keyvalues.add("Bedrooms");
        valuesData.add(data.getBedrooms());

        keyvalues.add("Balconies");
        valuesData.add(data.getBalconies());

        keyvalues.add("Property on Floor");
        valuesData.add(data.getPropertyonfloor());

        keyvalues.add("Bathrooms");
        valuesData.add(data.getBathrooms());

        keyvalues.add("Shared Washrooms");
        valuesData.add(data.getSharedbathrooms());

        keyvalues.add("Personal Bathroom");
        valuesData.add(data.getPersonalbathrooms());

        keyvalues.add("Any Construction");
        valuesData.add(data.getAny_construction());

        keyvalues.add("Boundary Wall");
        valuesData.add(data.getBoundary_wall_made());

        keyvalues.add("Gated Colony");
        valuesData.add(data.getGated_colony());

        keyvalues.add("Width of entrance");
        valuesData.add(data.getWidth_of_entrance()+data.getWidth_of_entrance_mezor());

        keyvalues.add("Construction Status of walls");
        valuesData.add(data.getConstruction_status_of_walls());

        keyvalues.add("Conference Room");
        valuesData.add(data.getConference_room());

        keyvalues.add("Total Conference Room");
        valuesData.add(data.getTotle_no_conference_room());

        keyvalues.add("Reception Area");
        valuesData.add(data.getReception_area());

        keyvalues.add("Min Seats");
        valuesData.add(data.getMin_no_of_seats());

        keyvalues.add("Max Seats");
        valuesData.add(data.getMax_no_of_seats());

        keyvalues.add("Cabins");
        valuesData.add(data.getNo_of_cabins());

        keyvalues.add("Meeting Rooms");
        valuesData.add(data.getNo_of_meeting_rooms());

        keyvalues.add("Doors Constructed");
        valuesData.add(data.getDoors_constructed());

        keyvalues.add("Venue");
        valuesData.add(data.getVenue_type());

        keyvalues.add("Virtual Space");
        valuesData.add(data.getVirtual_space());

        keyvalues.add("Building Class");
        valuesData.add(data.getBuilding_class());

        keyvalues.add("Rate of Return");
        valuesData.add(data.getRate_of_return());

        keyvalues.add("Shared Pantry");
        valuesData.add(data.getShared_pantry());

    }
    private String getCodedMobileNo(String mobile) {
        String no = null;
        String first2 = mobile.substring(0,2);
        String last2 = mobile.substring(8,10);
        String finaldata = first2+"******"+last2;
        no=finaldata;
        return no;
    }
    private void getUserInfo(String user_id) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object =  new JSONObject(response);
                    String status = object.getString("status");
                    if (status.equals("1")&&object.has("data")){
                        user = new Gson().fromJson(object.getJSONObject("data").toString(),User.class);
                        ownername.setText(user.getFirst_name());
                        ownerMob.setText("+91-"+getCodedMobileNo(user.getMobile()));
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
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    public  void secondSystem(){
        Intent intent=new Intent(InterestedPropertyThirdUser.this, ScreenSixteenDetailsPage.class);
        startActivity(intent);
    }
    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment(), "Overview");
        adapter.addFragment(new EmptyFragment(), "Description");
        adapter.addFragment(new EmptyFragment(), "Property Details");
        adapter.addFragment(new EmptyFragment(), "Amenties");
        adapter.addFragment(new EmptyFragment(), "Locality");
        adapter.addFragment(new EmptyFragment(), "Properties");

        viewPager.setAdapter(adapter);
    }
    private String getBudgetInLakhs(String budget_max) {
        String data =  null;
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
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
    public void setproposalPRO(){
        if (!acceptStatusPro){
            new WarningDio(InterestedPropertyThirdUser.this, "Property Rejected", "Ok", null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {

                }
            },false);
        }else {
            new WarningDio(InterestedPropertyThirdUser.this, "Property Accepted\nSummit your proposal in next 180 minutes", "Summit Proposal", null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {
                    aceepted();
                    Intent intent =  new Intent(InterestedPropertyThirdUser.this,QueryBrokerFirstScreen.class);
                    intent.putExtra("user_id",user.getUser_id());
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }, false);
        }
    }
    private String getDateInFormat(String updated_at) {
        String data  = null;
        data = updated_at.split("T")[0];
        return data;
    }

    private void aceepted() {
        TextView textView = (TextView) accept.getChildAt(0);
        reject.setVisibility(View.GONE);
        textView.setText("Summit Proposal");
        typeAccept = false;
    }
}
