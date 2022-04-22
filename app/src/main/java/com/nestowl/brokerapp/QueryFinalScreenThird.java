package com.nestowl.brokerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.ProjectRecivedModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QueryFinalScreenThird extends AppCompatActivity {
    ImageView back_img;
    Boolean is_Residential_click = false,isAccepted;
    FrameLayout fm_physically,fm_virtually,accept,reject,chat;
    LinearLayout lm_ready_to_move_factory,lm_under_factory,phycallyLnr,vetuallyLnr;
    RadioButton whats_app,jio_meet,skpye,zoom,google_due,face_time;
    RadioButton projectName,possassion,type,valid,vetualyRd;
    RadioGroup rg;
    EditText address,unit,bhk,minPrice,maxPrice,commission,addtionInfo,contactDetails,officeAddres,didgitalId;
    LinearLayout l1_whatsapp,l2_jio_meet,l3_skype,l4_zoom,l5_google_due,l6_face_time;
    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;
    String id,user_id,INTREST;
    RecyclerView imagesView;
    TextView dateDate,dateMonth,timeTime;
    User frontUser,user;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_final_screen_third);
        id=getIntent().getStringExtra("id");
        user_id=getIntent().getStringExtra("user_id");

        INTREST="NO";
        isAccepted=false;
        getUSer();

        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        projectName=findViewById(R.id.BUILDER_DEAL_PROJECT_NAME);
        possassion=findViewById(R.id.BUILDER_DEAL_POSSASTION);
        type=findViewById(R.id.BUILDER_DEAL_TYPE);
        valid=findViewById(R.id.BUILDER_DEAL_VALID);
        address=findViewById(R.id.BUILDER_DEAL_ADDRESS);
        unit=findViewById(R.id.BUILDER_DEAL_UNITS);
        bhk=findViewById(R.id.BUILDER_DEAL_BHK);
        minPrice=findViewById(R.id.BUILDER_DEAL_BUDGET_MIN);
        maxPrice=findViewById(R.id.BUILDER_DEAL_BUDGET_MAX);
        commission=findViewById(R.id.BUILDER_DEAL_COMMISTION);
        addtionInfo=findViewById(R.id.BUILDER_DEAL_EXTRA_DETAILS);
        contactDetails=findViewById(R.id.BUILDER_DEAL_CONTACT_DETAILS);
        officeAddres=findViewById(R.id.BUILDER_DEAL_ADDRESS_OFFICE);
        didgitalId=findViewById(R.id.BUILDER_DEAL_DIGITAL_ID);
        dateDate=findViewById(R.id.BUILDER_DEAL_DATE_DATE);
        dateMonth=findViewById(R.id.BUILDER_DEAL_DATE_MONTH);
        timeTime=findViewById(R.id.BUILDER_DEAL_TIME_TIME);
        imagesView=findViewById(R.id.BUILDER_DEAL_RECYCLER);
        imagesView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        vetuallyLnr=findViewById(R.id.BUILDER_DEAL_VITUALLY_LNR);
        phycallyLnr=findViewById(R.id.BUILDER_DEAL_PYCALLY_LNR);
        vetualyRd=findViewById(R.id.BUILDER_DEAL_VERTUAL_RD);

        accept=findViewById(R.id.BUILDER_DEAL_ACCEPT);
        reject=findViewById(R.id.BUILDER_DEAL_REJECT);
        chat=findViewById(R.id.BUILDER_DEAL_CHAT);

        fm_physically=findViewById(R.id.BUILDER_DEAL_PYCALLY);
        fm_virtually=findViewById(R.id.BUILDER_DEAL_VEETRUALLY);
        horizontalScrollView=findViewById(R.id.horizontal_id_two);
        scrollView=findViewById(R.id.scroll);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                horizontalScrollView.setVisibility(View.GONE);
                if (scrollY>55){
                    horizontalScrollView.setVisibility(View.VISIBLE);

                }
            }
        });

        l1_whatsapp=findViewById(R.id.PROPOSAL_REQ_VIDEO_INPUT_ID);
        rg=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS_GROUP);
        whats_app=findViewById(R.id.PROPOSAL_REQ_VIDEO_WHATSAPP);
        jio_meet=findViewById(R.id.PROPOSAL_REQ_VIDEO_JIO_MEET);
        skpye=findViewById(R.id.PROPOSAL_REQ_VIDEO_SKIPE);
        zoom=findViewById(R.id.PROPOSAL_REQ_VIDEO_ZOOM);
        google_due=findViewById(R.id.PROPOSAL_REQ_VIDEO_DUO);
        face_time=findViewById(R.id.PROPOSAL_REQ_VIDEO_FACE);
        lm_under_factory=findViewById(R.id.BUILDER_DEAL_PYCALLY_LNR);
        lm_ready_to_move_factory=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        fm_physically.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phycallyLnr.setVisibility(View.VISIBLE);
                vetuallyLnr.setVisibility(View.GONE);
                fm_physically.setBackgroundResource(R.drawable.selected_background_filter);
                fm_virtually.setBackgroundResource(R.drawable.employe_circle_rounded);
            }
        });
        fm_virtually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm_virtually.setBackgroundResource(R.drawable.selected_background_filter);
                fm_physically.setBackgroundResource(R.drawable.employe_circle_rounded);
                vetuallyLnr.setVisibility(View.VISIBLE);
                phycallyLnr.setVisibility(View.GONE);
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isAccepted){
                   Intent intentss = new Intent(QueryFinalScreenThird.this, ViewContact.class);
                   intentss.putExtra("pid",id);
                   intentss.putExtra("user_id",user_id);
                   intentss.putExtra("id","no");
                   startActivity(intentss);
               }else {
                   INTREST="Yes";
                   handleAcceptreject();
               }
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                INTREST="No";
                handleAcceptreject();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryFinalScreenThird.this,Chating.class);
                intent.putExtra("name",frontUser.getFirst_name());
                intent.putExtra("key",user_id);
                intent.putExtra("dp","no");
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        fetchProposal();
    }

    private void handleAcceptreject() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Sending request...");
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.PROJECT_ACCEPT_REJECT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        if (INTREST.equals("Yes")){
                            new WarningDio(QueryFinalScreenThird.this, "Project accepted", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                            reject.setVisibility(View.GONE);
                            TextView textView =  (TextView) accept.getChildAt(0);
                            textView.setText("View Contact");
                            isAccepted = true;
                        }else {
                            new WarningDio(QueryFinalScreenThird.this, "Project Rejected", "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {

                                }
                            },false);
                        }
                    }else {
                        new WarningDio(QueryFinalScreenThird.this, "Already done", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                }catch (Exception e){
                    Log.e("PROJECT ACCEPT", "onResponse: "+e );
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id",id);
                hashMap.put("interest",INTREST);
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void fetchProposal() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPOSAL_INFO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try {
                        JSONObject jsonObject =  new JSONObject(response);
                        Log.e("viewProPro", "onResponse: "+response );
                        String status=jsonObject.getString("status");
                        if (status.equals("1")){
                            JSONObject jsonObject1 = jsonObject.getJSONObject("NestDetails");
                            ProjectRecivedModal data = new Gson().fromJson(jsonObject1.toString(),ProjectRecivedModal.class);
                            handleData(data);
                        }
                    }catch (Exception e){
                        Log.e("viewProPro", "onResponse: "+e );
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("viewProPro", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap= new HashMap<>();
                hashMap.put("id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void handleData(ProjectRecivedModal data) {
        projectName.setChecked(true);
        projectName.setText(data.getProject_name());
        possassion.setText(data.getPossession_types());
        possassion.setChecked(true);
        type.setText(data.getProject_type());
        type.setChecked(true);
        valid.setText(data.getDeal_valid());
        valid.setChecked(true);
        vetualyRd.setText(data.getMode_of_meet());
        vetualyRd.setChecked(true);

        address.setText(data.getAddress());
        unit.setText(data.getTotal_unit_unsold());
        commission.setText(data.getService_charge());
        addtionInfo.setText(data.getAdditional_details());
        officeAddres.setText(data.getOffice_address());
        didgitalId.setText(data.getNest_professionals());
        String[] d = data.getMeet_date().split("-");
        dateDate.setText(d[2]);
        dateMonth.setText(getmonth(Integer.parseInt(d[1])));
        String[] time = data.getMeet_time().split(":");
        timeTime.setText(time[0]+":"+time[1]);
    }
    private String getmonth(int parseInt) {
        if (parseInt==1){
            return "January";
        }
        if (parseInt==2){
            return "February";
        }
        if (parseInt==3){
            return "March";
        }
        if (parseInt==4){
            return "April";
        }
        if (parseInt==5){
            return "May";
        }
        if (parseInt==6){
            return "Jun";
        }
        if (parseInt==7){
            return "July";
        }
        if (parseInt==8){
            return "August";
        }
        if (parseInt==9){
            return "September";
        }
        if (parseInt==10){
            return "October";
        }
        if (parseInt==11){
            return "November";
        }
        if (parseInt==12){
            return "December";
        }
        return null;
    }
    private void getUSer(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        frontUser=new Gson().fromJson(jsonObject.getJSONObject("data").toString(),User.class);
                    }
                }catch ( Exception e){

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

}
