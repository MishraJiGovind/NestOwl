package com.nestowl.brokerapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nestowl.CommenDialog.TechnicalError;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.Fragment.ActivityFragmentBuer;
import com.nestowl.Fragment.AlertParentLeadsFragment;
import com.nestowl.Fragment.HomeFragment;
import com.nestowl.Fragment.ProfileBroker;
import com.nestowl.model.DpModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.PaymentOrderModal;
import com.nestowl.model.SubscriptionRemainModal;
import com.nestowl.model.User;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String CHANNEL_ID = "notificationchannel";
    Context context;
    Activity activity;
    LinearLayout lnr_owner_screen,properties_plan,myProperty,headerLayout,leadbost,articals,feedback,rateus,share,about,term,contact,logout,FAQ;
    FrameLayout frame_toolbar_post, GotoChat;
    TextView tv_search;
     public  FrameLayout frm_haider_parent;
     LinearLayout home,post;
    public LinearLayout lnr_haider_second;
    FragmentManager manager = getSupportFragmentManager();

    FragmentTransaction fragmentTransaction = manager.beginTransaction();

   public BottomNavigationView bottomNavigationView;

   TextView tv_name,userName,userMobile;
   ImageView userDp,userEdit;
   String json,userID;
   LoginPojo loginPojo;
   User user;
   NavigationView navigationView;
   ArrayList<SubscriptionRemainModal> subscriptionRemainModals;
   DpModal dpModal;
   LiveCommnication liveCommnication;
   TechnicalError technicalError;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=activity=this;
        setContentView(R.layout.activity_home_screen);
        if (PrefMananger.getString(this,"remain")==null){
        PrefMananger.saveString(this,"remain","5");
        }
        PrefMananger.saveString(this,"id","false");
        PrefMananger.saveString(this,"sub","false");
        properties_plan=findViewById(R.id.properties_plan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        post=findViewById(R.id.post_two);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        frm_haider_parent=findViewById(R.id.haider_layout_frame_parent);
        lnr_haider_second=findViewById(R.id.haider_layout_first_linear);
        liveCommnication= ViewModelProviders.of(this).get(LiveCommnication.class);
        leadbost=findViewById(R.id.HOME_LEAD_BOST);
        articals=findViewById(R.id.HOME_SCREEN_ARTICAL);
        feedback=findViewById(R.id.HOME_SCREEN_FEEDBACK);
        rateus=findViewById(R.id.HOME_SCREEN_RATE_US);
        share=findViewById(R.id.HOME_SCREEN_SHARE);
        about=findViewById(R.id.HOME_SCREEN_ABOUT);
        term=findViewById(R.id.HOME_SCREEN_TERMS);
        contact=findViewById(R.id.HOME_SCREEN_CONTACT);
        FAQ=findViewById(R.id.HOME_SCREEN_FAQ);
        myProperty=findViewById(R.id.HOME_MY_PROPERTYIES);
        navigationView=findViewById(R.id.HOME_NAVIGATION_VIEW);
        userName=navigationView.getHeaderView(0).findViewById(R.id.HOME_NAME);
        userMobile=navigationView.getHeaderView(0).findViewById(R.id.HOME_MOBILE);
        userDp=navigationView.getHeaderView(0).findViewById(R.id.HOME_DP);
        userEdit=navigationView.getHeaderView(0).findViewById(R.id.HOME_EDIT);
        logout=findViewById(R.id.HOME_SCREEN_LOG_OUT);
        FirebaseMessaging.getInstance().subscribeToTopic("broker");
        try {
            json = PrefMananger.getString(this,"remains");
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
                            PrefMananger.saveString(this,"sub","false");
                        }else {
                            PrefMananger.saveString(this,"sub","true");
                        }
                    }
                    if (data.getName().equals("8")){
                        Date date = data.getDate();
                        int futureDate = date.getYear()+1;
                        int month = date.getMonth()+6;
                        int day = date.getDay();

                        Date today = new Date();
                        if (futureDate<=today.getYear()&&month<=today.getMonth()&&day<=today.getDay()){
                            PrefMananger.saveString(this,"sub","false");
                        }else {
                            PrefMananger.saveString(this,"sub","true");
                        }
                    }
                    if (data.getName().equals("9")){
                        Date date = data.getDate();
                        int futureDate = date.getYear()+2;
                        int month = date.getMonth();
                        int day = date.getDay();

                        Date today = new Date();
                        if (futureDate<=today.getYear()&&month<=today.getMonth()&&day<=today.getDay()){
                            PrefMananger.saveString(this,"sub","false");
                        }else {
                            PrefMananger.saveString(this,"sub","true");
                        }
                    }
                }
            }else {
                PrefMananger.saveString(this,"sub","false");
            }
        }catch (Exception e){
            Log.e("date error", "onResponse: "+e );
        }
        loginPojo=PrefMananger.GetLoginData(this);
        if (PrefMananger.getString(this,"user")!=null){
            user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        }
        getUser();
        getNotification();
        userID=String.valueOf(loginPojo.getUserId());
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        subscriptionRemainModals=new ArrayList<>();
        getSubscription();
        userEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,SignUpEditScreen.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDio(HomeScreen.this, "Do you want log out your account?", "LOG OUT", null, new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                            PrefMananger.deletedata(HomeScreen.this);
                            startActivity(new Intent(HomeScreen.this, LoginActivity.class));
                            finish();
                        }
                    }
                },false);
            }
        });

        leadbost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(HomeScreen.this,"sub").equals("true")){
                    startActivity(new Intent(HomeScreen.this,LeadBoastPlan.class));
                }else {
                    if ( PrefMananger.getString(HomeScreen.this,"id").equals("true")){
                        new WarningDio(HomeScreen.this, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(HomeScreen.this,SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("0")){
                            new WarningDio(HomeScreen.this, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(HomeScreen.this,EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("1")){
                            new WarningDio(HomeScreen.this, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("2")){
                            new WarningDio(HomeScreen.this, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }


                    }
                }

            }
        });
        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this, com.nestowl.brokerapp.FAQ.class));
            }
        });
        articals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,Articles.class));
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,FeedBack.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,Contact.class));
            }
        });
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(UrlClass.BASE_URL+"terms-and-conditions"));
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Look what i found , best property broker app. Download Nestowl app now.\nhttps://play.google.com/store/apps/details?id="+getPackageName();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share app");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                }
                catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,AboutUs.class));
            }
        });
        myProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.navigation_activity);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new ActivityFragmentBuer()).commit();
            }
        });






        lnr_owner_screen=findViewById(R.id.owner_plans_home_screen);
        GotoChat = findViewById(R.id.HOME_TO_CHAT);

        home=findViewById(R.id.home_one);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,HomeScreen.class);
                startActivity(intent);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(HomeScreen.this,"sub").equals("true")){
                    HomeScreen.this.startActivity(new Intent(HomeScreen.this, SelectOptionMode.class));
                }else {
                    if ( PrefMananger.getString(HomeScreen.this,"id").equals("true")){
                        new WarningDio(HomeScreen.this, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(HomeScreen.this,SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("0")){
                            new WarningDio(HomeScreen.this, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(HomeScreen.this,EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("1")){
                            new WarningDio(HomeScreen.this, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("2")){
                            new WarningDio(HomeScreen.this, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }
                    }
                }
            }
        });

        tv_search=findViewById(R.id.tv_app_bar_home);
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(HomeScreen.this,SearchViewActivity.class);
                startActivity(intent);*/
            }
        });
        lnr_owner_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScreen.this,SubscriptionPlan.class);
                startActivity(intent);
            }
        });
        properties_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PrefMananger.getString(HomeScreen.this,"sub").equals("true")){
                    Intent intent = new Intent(HomeScreen.this,PropertiesPlan.class);
                    startActivity(intent);
                }else {
                    if ( PrefMananger.getString(HomeScreen.this,"id").equals("true")){
                        new WarningDio(HomeScreen.this, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    startActivity(new Intent(HomeScreen.this,SubscriptionPlan.class));
                                }
                            }
                        },false);
                    }else {
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("0")){
                            new WarningDio(HomeScreen.this, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        startActivity(new Intent(HomeScreen.this,EditSignUpForm.class));
                                    }
                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("1")){
                            new WarningDio(HomeScreen.this, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                        if (PrefMananger.getString(HomeScreen.this,"status").equals("2")){
                            new WarningDio(HomeScreen.this, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                }
                            },false);
                        }


                    }
                }

            }
        });
        setSupportActionBar(toolbar);
        lnr_haider_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreen.this,SearchNestPro.class));
            }
        });
//        TextView textView = bottomNavigationView.findViewById(R.id.nav_post_property).findViewById(R.id.largeLabel);
        //ImageView iv_add = bottomNavigationView.findViewById(R.id.iv_add);
//        textView.setTextSize(10);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        frame_toolbar_post = findViewById(R.id.toolbar_post_requirement);
        frame_toolbar_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Intent i = new Intent(HomeScreen.this,IntroScreenPostRequirement.class);
                startActivity(i);*/
            }
        });
        Fragment activity = new ActivityFragmentBuer();
        Fragment alert = new AlertParentLeadsFragment();
        Fragment profile = new ProfileBroker();
        Fragment home = new HomeFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.navigation_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new HomeFragment()).commit();


                } else if (menuItem.getItemId()==R.id.navigation_activity){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new ActivityFragmentBuer()).commit();


                }else if (menuItem.getItemId()==R.id.nav_post_property){
                    Dialog dialog = new Dialog(HomeScreen.this);
                    dialog.setContentView(R.layout.switch_to_owner_seller_post_requirement);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.BOTTOM;
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(HomeScreen.this, android.R.color.transparent)));
                    dialog.getWindow().setAttributes(lp);
                    LinearLayout post_property = dialog.findViewById(R.id.post_property_first);
                    LinearLayout post_requirement=dialog.findViewById(R.id.post_requirement);
                    ImageView back=dialog.findViewById(R.id.iv9);
                    dialog.show();
                    post_requirement.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (PrefMananger.getString(HomeScreen.this,"sub").equals("true")){
                                Intent intent = new Intent(HomeScreen.this,SelectModePostRequirement.class);
                                startActivity(intent);
                            }else {
                                if ( PrefMananger.getString(HomeScreen.this,"id").equals("true")){
                                    new WarningDio(HomeScreen.this, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                                        @Override
                                        public void getClicks(int click) {
                                            if (click==1){
                                                startActivity(new Intent(HomeScreen.this,SubscriptionPlan.class));
                                            }
                                        }
                                    },false);
                                }else {
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("0")){
                                        new WarningDio(HomeScreen.this, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {
                                                if (click==1){
                                                    startActivity(new Intent(HomeScreen.this,EditSignUpForm.class));
                                                }
                                            }
                                        },false);
                                    }
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("1")){
                                        new WarningDio(HomeScreen.this, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {

                                            }
                                        },false);
                                    }
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("2")){
                                        new WarningDio(HomeScreen.this, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {
                                            }
                                        },false);
                                    }
                                }

                            }

                        }
                    });
                    post_property.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (PrefMananger.getString(HomeScreen.this,"sub").equals("true")){
                                Intent intent = new Intent(HomeScreen.this,SelectOptionMode.class);
                                startActivity(intent);
                            }else {
                                if ( PrefMananger.getString(HomeScreen.this,"id").equals("true")){
                                    new WarningDio(HomeScreen.this, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                                        @Override
                                        public void getClicks(int click) {
                                            if (click==1){
                                                startActivity(new Intent(HomeScreen.this,SubscriptionPlan.class));
                                            }
                                        }
                                    },false);
                                }else {
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("0")){
                                        new WarningDio(HomeScreen.this, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {
                                                if (click==1){
                                                    startActivity(new Intent(HomeScreen.this,EditSignUpForm.class));
                                                }
                                            }
                                        },false);
                                    }
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("1")){
                                        new WarningDio(HomeScreen.this, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {

                                            }
                                        },false);
                                    }
                                    if (PrefMananger.getString(HomeScreen.this,"status").equals("2")){
                                        new WarningDio(HomeScreen.this, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                            @Override
                                            public void getClicks(int click) {
                                            }
                                        },false);
                                    }


                                }
                            }

                        }
                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else if (menuItem.getItemId()==R.id.navigation_alert){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new AlertParentLeadsFragment()).commit();



                }else if (menuItem.getItemId()==R.id.navigation_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new ProfileBroker()).commit();


                }

                return true;
            }
        });
        GotoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,ChatList.class));
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout_home_one,new HomeFragment()).commit();
        }
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key = aichat.getText();
                String value = aichat.getValue();
                String values = aichat.getValues();
                if (key.equals("response")){
                    bottomNavigationView.setSelectedItemId(R.id.navigation_activity);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_home_one,new ActivityFragmentBuer()).commit();
                    PrefMananger.saveString(HomeScreen.this,"responsekey",value);
                    Log.e("VALUE ON CLICK", "onChanged: "+value );
                }
            }
        });
    }

    private void getNotification() {
        createNotificationChannel();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.TOTAL_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        Integer total  = jsonObject.getInt("Notifications");
                        if (PrefMananger.getString(HomeScreen.this,"notificationCounts")!=null){
                            if (Integer.parseInt(PrefMananger.getString(HomeScreen.this,"notificationCounts"))!=total){
                                showNotification("Hello "+user.getFirst_name(),"You have "+total+" notifications.");
                                PrefMananger.saveString(HomeScreen.this,"notificationCounts",String.valueOf(total));
                            }
                        }else {
                            if (total!=0){
                                showNotification("Hello "+user.getFirst_name(),"You have "+total+" notifications.");
                                PrefMananger.saveString(HomeScreen.this,"notificationCounts",String.valueOf(total));
                            }
                        }
                    }
                }catch ( Exception e){
                    Log.e("Notification Error", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Notification Error", "onResponse: "+error );
//                technicalError=new TechnicalError(context);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void showNotification(String title,String context){

        Intent intent = new Intent(this, Notification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(title)
                .setContentText(context)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
    private void getUser() {
        StringRequest request= new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        user = new Gson().fromJson(jsonObject.getString("data").toString(),User.class);
                        LoginPojo loginPojo = new Gson().fromJson(jsonObject.getString("data").toString(),LoginPojo.class);
                        PrefMananger.SaveLoginData(HomeScreen.this,loginPojo);
                        Log.e("userId", "onResponse: "+loginPojo.getUserId());

                        dpModal = new Gson().fromJson(jsonObject.getString("Photo").toString(),DpModal.class);
                        user.setAvatar(UrlClass.BASE_URL+dpModal.getProfile_photo());
                        PrefMananger.saveString(HomeScreen.this,"user",new Gson().toJson(user));
                        PrefMananger.saveString(HomeScreen.this,"photo",jsonObject.getString("Photo").toString());
                        setNameOnSideBar();
                    }
                }catch (Exception e){
                        PrefMananger.saveString(HomeScreen.this,"user",new Gson().toJson(user));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                technicalError=new TechnicalError(context);
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
        Volley.newRequestQueue(this).add(request);
    }

    private void setNameOnSideBar() {
        userName.setText(user.getFirst_name());
        userMobile.setText(user.getMobile());
        Glide.with(this).load(UrlClass.BASE_URL+dpModal.getProfile_photo()).placeholder(R.drawable.user_icon_p).into(userDp);
    }
    private void getSubscription() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_ORDERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("OrderDetails");
                        if (jsonArray.length()==0|jsonArray==null){
                            PrefMananger.saveString(HomeScreen.this,"remains",null);
                            return;
                        }
                        for (int i=0;i<jsonArray.length();i++){
                            PaymentOrderModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),PaymentOrderModal.class);
                            if (data.getPayment_status().contains("Paid")){
                                SubscriptionRemainModal modal =  new SubscriptionRemainModal();
                                modal.setType(data.getProduct_name());
                                modal.setName(data.getProduct_id());
                                modal.setPrice(data.getPrice());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
                                Date date =  format.parse(data.getUpdated_at());
                                modal.setDate(date);
                                Log.e("date from Server", "onResponse: "+date );
                                subscriptionRemainModals.add(modal);
                            }
                            String js = new Gson().toJson(subscriptionRemainModals);
                            PrefMananger.saveString(HomeScreen.this,"remains",js);
                        }
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );
                    PrefMananger.saveString(HomeScreen.this,"remains",null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onResponse: "+error );
                PrefMananger.saveString(HomeScreen.this,"remains",null);
                technicalError=new TechnicalError(context);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",userID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


        } else if (id == R.id.nav_post) {

            Intent intent = new Intent(context,PlanBasicActivity.class);

            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.owner) {
            Intent intent = new Intent(context,SubscriptionPlan.class);
            startActivity(intent);
        } else if (id ==R.id.view_leads){
            Intent intent = new Intent(context,PropertiesPlan.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("test","helo");
    }
}
