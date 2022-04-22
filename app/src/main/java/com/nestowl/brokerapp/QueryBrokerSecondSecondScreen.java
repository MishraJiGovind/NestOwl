package com.nestowl.brokerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.ProposalModal;
import com.nestowl.utils.Converter;
import com.nestowl.utils.GetFiles;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

public class QueryBrokerSecondSecondScreen extends AppCompatActivity {
    ImageView back_img;
    FrameLayout f1,f2;
    LinearLayout l1_whatsapp,l2_jio_meet,l3_skype,l4_zoom,l5_google_due,l6_face_time;
    RadioButton yes,no,not;
    LinearLayout lm_under_factory,lm_ready_to_move_factory;
    Boolean is_Residential_click = false;
    RadioGroup rg,rgb;
    RadioButton rb;
    LinearLayout lnd_few;
    ScrollView scrollView;

    CardView cardView;
    FrameLayout whats_day,whats_time,jio_d,jio_tim,skyp_day,skyp_time,zoom_day,zoom_time,google_day,google_time,face_day,face_time;

    RadioGroup stage,docs,trans,owner,furnishing,req_time,booking_time,demanded_mode,vedio_tour,all_req,video_options;
    EditText demanding_priceIn,booking_priceIn,videoIdIn,meetsIn;
    TextView priceInEng,bookingInEng;
    CheckBox negotable;
    FrameLayout day,time_slot,vc_day,vc_time;
    LinearLayout vid_options,vid_id_inputs,meets_input;
    String filterCLik,Sinvetries,Suser_id,Sreq_id,TOTAL_CLICK,Sstage,Sdocs,Stranc,Sowner,Sfrunishing,SreqTime,SbookingTime,Sdemand_mode,SVideoTour,SallReq,SdemandingPrice,SBookingPrice,SvideoOptions,Sday,Stime,SvcDay,SvcTime,
            providelater,negotiable,missingreq,chatNumber;
    ArrayList<FloorPojo> floorPojos, floorPojos2;
    Context context;
    String clicks;
    boolean sumbition,isFirst,here;
    Converter converter;
    GetFiles getFiles;




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_broker_second_second_screen);
        TOTAL_CLICK =  getIntent().getStringExtra("clicks");
        isFirst=getIntent().getBooleanExtra("f",false);
        lnd_few=findViewById(R.id.PROPOSAL_REQ_PRO_MEET_MISSING_TAB);
        rgb=findViewById(R.id.PROPOSAL_REQ_PRO_MEET);
        context =  this;
        sumbition =true;
        here=false;
        Sstage="null";
        Sdocs="null";
        Stranc="null";
        Sowner="null";
        Sfrunishing="null";
        SreqTime="null";
        SbookingTime="null";
        Sdemand_mode="null";
        SVideoTour="null";
        SallReq="null";
        SdemandingPrice="null";
        SBookingPrice="null";
        SvideoOptions="null";
        Sday="null";
        SvcDay="null";
        SvcTime="null";
        providelater="null";
        negotiable="null";
        missingreq="null";
        chatNumber="null";
        ProposalModal modalBefore =  new Gson().fromJson(PrefMananger.getString(this,PrefMananger.PROPOSAL),ProposalModal.class);
        if (modalBefore!=null){
            if (modalBefore.getStage()!=null){
                Sstage=modalBefore.getStage();
                Sdocs=modalBefore.getVerified_document_property();
                Stranc=modalBefore.getTransaction_type();
                Sowner=modalBefore.getOwnership();
                Sfrunishing=modalBefore.getProperty_status();
                SreqTime=modalBefore.getFinal_payment();
                SbookingTime=modalBefore.getBooking_payment();
                SdemandingPrice=modalBefore.getPrice();
                Sdemand_mode=modalBefore.getMode_of_payment();
                SVideoTour=modalBefore.getVideo_tour();
                SallReq=modalBefore.getProperty_meet();
                SBookingPrice= modalBefore.getBooking_amount();
                SvideoOptions=modalBefore.getChat_type();
                Sday=modalBefore.getMeeting_date();
                Stime=modalBefore.getMeeting_time();
                SvcDay=modalBefore.getPossilbe_date();
                SvcTime=modalBefore.getPossilbe_time();
                providelater=modalBefore.getProvidelater();
                negotiable=modalBefore.getPrice_negotiable();
                chatNumber=modalBefore.getChat_number();
                missingreq=modalBefore.getMissing_requirement();
            }
        }

        stage=findViewById(R.id.PROPOSAL_REQ_SATGE_GROUP);
        docs=findViewById(R.id.PROPOSAL_REQ_DOCUMENT_GROUP);
        trans=findViewById(R.id.PROPOSAL_REQ_MENTION_GRUOP);
        furnishing=findViewById(R.id.PROPOSAL_REQ_PRO_GROUP);
        req_time=findViewById(R.id.PROPOSAL_REQ_TIME);
        booking_time=findViewById(R.id.PROPOSAL_REQ_BOOKING_TIME);
        demanded_mode=findViewById(R.id.PROPOSAL_REQ_DP);
        vedio_tour=findViewById(R.id.PROPOSAL_REQ_VIDEO);
        owner=findViewById(R.id.PROPOSAL_REQ_OWNER_GRUOP);
        all_req=findViewById(R.id.PROPOSAL_REQ_PRO_MEET);
        video_options=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS_GROUP);
        converter =  new Converter(this);
        getFiles=new GetFiles(this);

        demanding_priceIn=findViewById(R.id.PROPOSAL_REQ_DEMAND_IN);
        booking_priceIn=findViewById(R.id.PROPOSAL_REQ_BOOKING_IN);
        videoIdIn=findViewById(R.id.PROPOSAL_REQ_VIDEO_INPUTS_DATA);
        meetsIn=findViewById(R.id.PROPOSAL_REQ_PRO_MEET_MISSING_IN);

        priceInEng=findViewById(R.id.PROPOSAL_REQ_DEMAND_IN_ENGLISH);
        bookingInEng=findViewById(R.id.PROPOSAL_REQ_BOOKING_IN_ENG);

        negotable=findViewById(R.id.PROPOSAL_REQ_DEMAND_NEGOTABLE);

        day=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        time_slot=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        vc_day=findViewById(R.id.PROPOSAL_REQ_VIDEO_DAY);
        vc_time=findViewById(R.id.PROPOSAL_REQ_VIDEO_TIME);
        vid_options=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS);
        vid_id_inputs=findViewById(R.id.PROPOSAL_REQ_VIDEO_INPUT_ID);
        meets_input=findViewById(R.id.PROPOSAL_REQ_PRO_MEET_MISSING_TAB);
        stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ__RADIO_STAGE_FINAL){
                    Sstage="About to Final";
                }else {
                    Sstage ="Inertial discuss";
                }
            }
        });
        docs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_DOCS_RADIO_YES){
                    Sdocs="Yes";
                    return;
                }
                if (checkedId==R.id.PROPOSAL_REQ_DOCS_RADIO_NO){
                    Sdocs="No";
                }else {
                    Sdocs="In Progress";
                }
            }
        });
        trans.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_TRANS_RESALE){
                    Stranc="Resale";
                }else {
                    Stranc="Fee Booking";
                }
            }
        });
        owner.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_OWNER_ATTORNY){
                    Sowner="Attorney";
                }
                if (checkedId==R.id.PROPOSAL_REQ_OWNER_FRESH){
                    Sowner="Fresh";
                }
                if (checkedId==R.id.PROPOSAL_REQ_OWNER_TRANSFER){
                    Sowner="Transfer";
                }
                if (checkedId==R.id.PROPOSAL_REQ_OWNER_LESS){
                    Sowner="Lease hold";
                }
            }
        });
        furnishing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_PRO_FULLY){
                    Sfrunishing="Furnished";
                }
                if (checkedId==R.id.PROPOSAL_REQ_PRO_SEMI){
                    Sfrunishing="SemiFurnished";
                }
                if (checkedId==R.id.PROPOSAL_REQ_PRO_UN){
                    Sfrunishing="UnFurnished";
                }

            }
        });
        req_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_TIME_15){
                    SreqTime="15 Days";
                }
                if (checkedId==R.id.PROPOSAL_REQ_TIME_30){
                    SreqTime="30 Days";
                }
                if (checkedId==R.id.PROPOSAL_REQ_TIME_60){
                    SreqTime="60 Days";
                }
                if (checkedId==R.id.PROPOSAL_REQ_TIME_90){
                    SreqTime="90 Days";
                }
                if (checkedId==R.id.PROPOSAL_REQ_TIME_120){
                    SreqTime="120 Days";
                }
            }
        });
        booking_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_BT_1){
                    SbookingTime="1 Month";
                }
                if (checkedId==R.id.PROPOSAL_REQ_BT_2){
                    SbookingTime="2 Month";
                }
                if (checkedId==R.id.PROPOSAL_REQ_BT_3){
                    SbookingTime="3 Month";
                }
                if (checkedId==R.id.PROPOSAL_REQ_BT_4){
                    SbookingTime="4 Month";
                }
                if (checkedId==R.id.PROPOSAL_REQ_BT_5){
                    SbookingTime="More then 5 Months";
                }
            }
        });
        demanded_mode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_DP_D){
                    Sdemand_mode="Discuss with buyer";
                }
                if (checkedId==R.id.PROPOSAL_REQ_DP_CASH){
                    Sdemand_mode="Cash";
                }
                if (checkedId==R.id.PROPOSAL_REQ_DP__C_C){
                    Sdemand_mode="Cash and Cheque";
                }
                if (checkedId==R.id.PROPOSAL_REQ_DP_CHEQUE){
                    Sdemand_mode="Cheque";
                }
                if (checkedId==R.id.PROPOSAL_REQ__5){
                    Sdemand_mode="More Then 5 Months";
                }

            }
        });
        vedio_tour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_YES){
                    SVideoTour="Yes";
                    vid_options.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_NO){
                    SVideoTour="No";
                    vid_options.setVisibility(View.GONE);
                    vid_id_inputs.setVisibility(View.GONE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_NO_NO){
                    vid_id_inputs.setVisibility(View.GONE);
                    vid_options.setVisibility(View.GONE);
                    SVideoTour="Not Applicable";
                }
            }
        });
        all_req.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_PRO_MEET_YES){
                    SallReq="Yes";
                    meets_input.setVisibility(View.GONE);
                }else {
                    SallReq="No";
                    meets_input.setVisibility(View.VISIBLE);
                }
            }
        });
        video_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_WHATSAPP){
                    SvideoOptions="Whatsapp";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_DUO){
                    SvideoOptions="Duo";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_FACE){
                    SvideoOptions="Face";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_JIO_MEET){
                    SvideoOptions="Jio Meets";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_SKIPE){
                    SvideoOptions="Skype";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.PROPOSAL_REQ_VIDEO_ZOOM){
                    SvideoOptions="Zoom";
                    vid_id_inputs.setVisibility(View.VISIBLE);
                }
            }
        });

        demanding_priceIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null){
                    setTextInEnglishPrice(s);
                }
            }
        });
        booking_priceIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null){
                    setTextInEnglishBooking(s);
                }
            }
        });

        floorPojos =  new ArrayList<>();
        addItems("Sunday",floorPojos);
        addItems("Monday",floorPojos);
        addItems("Tuesday",floorPojos);
        addItems("Wednesday",floorPojos);
        addItems("Thursday",floorPojos);
        addItems("Friday",floorPojos);
        addItems("Saturday",floorPojos);
        addItems("AnyDay",floorPojos);
        floorPojos2 =  new ArrayList<>();
        addItems("9 to 12",floorPojos2);
        addItems("12 to 4",floorPojos2);
        addItems("4 to 7",floorPojos2);
        addItems("Any Time",floorPojos2);

        vc_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) vc_day.getChildAt(0);
                        textView.setText(text);
                        SvcDay=text;
                    }
                },floorPojos);
            }
        });
        vc_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) vc_time.getChildAt(0);
                        textView.setText(text);
                        SvcTime=text;
                    }
                },floorPojos2);
            }
        });
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) day.getChildAt(0);
                        textView.setText(text);
                        Sday=text;
                    }
                },floorPojos);
            }
        });
        time_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) time_slot.getChildAt(0);
                        textView.setText(text);
                        Stime=text;
                    }
                },floorPojos2);
            }
        });
        //arsad code
        scrollView=findViewById(R.id.scroll);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                cardView.setVisibility(View.GONE);
                if (scrollY>55){
                    cardView.setVisibility(View.VISIBLE);

                }
            }
        });
        cardView=findViewById(R.id.SELLER_PROPOSAL_BUYER_COUNTINUE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (sumbition){
                   ProposalModal proposalModal = new ProposalModal();
                   if (demanding_priceIn.getText()== null|demanding_priceIn.getText().length()<=0){
                       demanding_priceIn.setError("Enter price here");
                       return;
                   }
                   if (booking_priceIn.getText()== null|booking_priceIn.getText().length()<=0){
                       booking_priceIn.setError("Enter price here");
                       return;
                   }

                   String data =  "null";
                   String datas =  "null";

                   SdemandingPrice=demanding_priceIn.getText().toString();
                   SBookingPrice=booking_priceIn.getText().toString();
                   if (PrefMananger.getString(context,PrefMananger.PROPOSAL)!=null){
                       Log.e("savedobject Starting", "onClick: "+PrefMananger.getString(context,PrefMananger.PROPOSAL) );
                       proposalModal=new Gson().fromJson(PrefMananger.getString(context,PrefMananger.PROPOSAL).toString(),ProposalModal.class);
                       data=proposalModal.getChat_number();
                       datas=proposalModal.getMissing_requirement();
                   }else {
                       proposalModal=new ProposalModal();
                       proposalModal.setInventories(PrefMananger.getString(QueryBrokerSecondSecondScreen.this,"clisks"));
                       proposalModal.setRequirement_id(PrefMananger.getString(QueryBrokerSecondSecondScreen.this,PrefMananger.PROPOSAL_ID));
                       Log.e("REQURE id", "onClick: "+proposalModal.getRequirement_id() );
                   }
                   proposalModal.setStage(Sstage);
                   providelater="no";
                   proposalModal.setProvidelater(providelater);
                   proposalModal.setVerified_document_property(Sdocs);
                   proposalModal.setTransaction_type(Stranc);
                   proposalModal.setOwnership(Sowner);
                   proposalModal.setProperty_status(Sfrunishing);
                   proposalModal.setPrice(SdemandingPrice);
                   if (negotable.isChecked()){
                       negotiable="Yes";
                   }else {
                       negotiable="No";
                   }
                   proposalModal.setPrice_negotiable(negotiable);
                   proposalModal.setBooking_amount(SBookingPrice);
                   proposalModal.setBooking_payment(SbookingTime);
                   proposalModal.setFinal_payment(SreqTime);
                   proposalModal.setMode_of_payment(Sdemand_mode);
                   proposalModal.setVideo_tour(SVideoTour);
                   proposalModal.setProperty_meet(SallReq);
                   proposalModal.setMissing_requirement(missingreq);
                   proposalModal.setMeeting_date(Sday);
                   proposalModal.setMeeting_time(Stime);
                   proposalModal.setChat_type(SvideoOptions);
                   proposalModal.setChat_number(chatNumber);
                   proposalModal.setPossilbe_date(SvcDay);
                   proposalModal.setPossilbe_time(SvcTime);
                   if (Suser_id !=null){
                       proposalModal.setUser_id(Suser_id);
                   }
                   if (Sreq_id !=null){
                       proposalModal.setRequirement_id(Sreq_id);
                   }
                   if (videoIdIn.getText()==null){
                       proposalModal.setChat_number(data);
                   }else {
                       data=videoIdIn.getText().toString();
                       proposalModal.setChat_number(data);
                   }
                   if (meetsIn.getText()==null){
                       proposalModal.setMissing_requirement(datas);
                   }else {
                       datas=meetsIn.getText().toString();
                       proposalModal.setMissing_requirement(datas);
                   }
                   String json =new Gson().toJson(proposalModal);
                   PrefMananger.saveString(context,PrefMananger.PROPOSAL, json);
                   Log.e("savedobject", "onClick: "+PrefMananger.getString(context,PrefMananger.PROPOSAL) );
                   Intent intent = new Intent(context,QueryBrokerSecondImageSection.class);
                   intent.putExtra("clicks",TOTAL_CLICK);
                   startActivity(intent);
                   finish();
               }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        if (getIntent().getStringExtra("clicks")!=null){
            if (getIntent().getStringExtra("clicks").equals("0")){
                sumbition=false;
                sumbitData();
                here=true;
                return;
            }else {
//                int click = Integer.parseInt(getIntent().getStringExtra("clicks"));
//                click--;
//                clicks = String.valueOf(click);
//                Intent i=new Intent(QueryBrokerSecondSecondScreen.this,QueryBrokerSecondSecondScreen.class);
//                i.putExtra("clicks",clicks);
//                i.putExtra("v","no");
//                startActivity(i);
            }
        }
        if (!isFirst){
            here=false;
            sumbitData();
        }
    }
    private void setTextInEnglishPrice(Editable s) {
        int no = Integer.parseInt(s.toString());
        String data = null;
        try {
            if (no>=1000000000){
                no=no/1000000000;
                data="₹"+no+" Arab";
            }
            if (no>=10000000){
                no = no/10000000;
                data="₹"+no+" Crore";
            }
            if (no>=100000){
                no = no/100000;
                data = "₹"+no+" Lakh";
            }
            if (no>=1000){
                no = no/1000;
                data = "₹"+no+" Hajar";
            }
        }catch (Exception e){
            Log.e("text", "setTextInEnglish: "+e );
        }
        priceInEng.setText(data);
    }
    private void setTextInEnglishBooking(Editable s) {
        int no = Integer.parseInt(s.toString());
        String data = null;
        try {
            if (no>=1000000000){
                no=no/1000000000;
                data="₹"+no+" Arab";
            }
            if (no>=10000000){
                no = no/10000000;
                data="₹"+no+" Crore";
            }
            if (no>=100000){
                no = no/100000;
                data = "₹"+no+" Lakh";
            }
            if (no>=1000){
                no = no/1000;
                data = "₹"+no+" Hajar";
            }
        }catch (Exception e){
            Log.e("text", "setTextInEnglish: "+e );
        }
        bookingInEng.setText(data);
    }
    private void addItems(String text,ArrayList<FloorPojo> floorPojos){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
    private void sumbitData() {
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Sending Proposal Please Wait...");
        pd.setCancelable(false);
        pd.show();

        String pro1=PrefMananger.getString(context,PrefMananger.PROPOSAL);
        ProposalModal proposalModal =  new Gson().fromJson(pro1,ProposalModal.class);
        proposalModal.setUser_id(String.valueOf(PrefMananger.GetLoginData(this).getUserId()));

        int  tmp =Integer.parseInt(TOTAL_CLICK);
        int  total =Integer.parseInt(PrefMananger.getString(this,"clisks"));
        if (total==1){
            if (tmp==1){
                filterCLik="0";
            }
        }
        if (total==2){
            if (tmp==1){
                filterCLik="1";
            }
            if (tmp==2){
                filterCLik="0";
            }
        }
        if (total==3){
            if (tmp==1){
                filterCLik="2";
            }
            if (tmp==2){
                filterCLik="1";
            }
            if (tmp==3){
                filterCLik="0";
            }
        }

        HashMap<String,String>hashMap=new HashMap<>();
        try {
            hashMap.put("user_id",proposalModal.getUser_id());
            hashMap.put("requirement_id",proposalModal.getRequirement_id());
            hashMap.put("inventories",proposalModal.getInventories());
            hashMap.put("stage[]",proposalModal.getStage());
            hashMap.put("providelater[]",proposalModal.getProvidelater());
            hashMap.put("verified_document_property[]",proposalModal.getVerified_document_property());
            hashMap.put("transaction_type[]",proposalModal.getTransaction_type());
            hashMap.put("ownership[]",proposalModal.getOwnership());
            hashMap.put("property_status[]",proposalModal.getProperty_status());
            hashMap.put("price[]",proposalModal.getPrice());
            hashMap.put("price_negotiable[]",proposalModal.getPrice_negotiable());
            hashMap.put("booking_payment[]",proposalModal.getBooking_payment());
            hashMap.put("final_payment[]",proposalModal.getFinal_payment());
            hashMap.put("mode_of_payment[]",proposalModal.getMode_of_payment());
            hashMap.put("video_tour[]",proposalModal.getVideo_tour());
            hashMap.put("property_meet[]",proposalModal.getProperty_meet());
            hashMap.put("missing_requirement[]",proposalModal.getMissing_requirement());
            hashMap.put("meeting_date[]",proposalModal.getMeeting_date());
            hashMap.put("chat_type[]",proposalModal.getChat_type());
            hashMap.put("chat_number[]",proposalModal.getChat_number());
            hashMap.put("possilbe_date[]",proposalModal.getPossilbe_date());
            hashMap.put("possilbe_time[]",proposalModal.getPossilbe_time());
            hashMap.put("market[]",proposalModal.getMarket());
            hashMap.put("bus_stop[]",proposalModal.getBus_stop());
            hashMap.put("metro[]",proposalModal.getMetro());
            hashMap.put("hospital[]",proposalModal.getHospital());
            hashMap.put("police_station[]",proposalModal.getPolice_station());
            hashMap.put("railways_station[]",proposalModal.getRailways_station());
            hashMap.put("local_facilty[]",proposalModal.getLocal_facilty());
            hashMap.put("client_verification[]",proposalModal.getClient_verification());
            hashMap.put("pick_and_drop[]",proposalModal.getPick_and_drop());
            hashMap.put("proper_document[]",proposalModal.getProper_document());
            hashMap.put("booking_amount[]",proposalModal.getBooking_amount());
            hashMap.put("meeting_time[]",proposalModal.getMeeting_time());
            hashMap.put("img_stage",filterCLik);
            hashMap.put("status","1");

            Log.e("conferm ids", "sumbitData: "+hashMap.get("user_id")+" "+hashMap.get("requirement_id")+" "+hashMap.get("inventories"));
        }catch (Exception e){
            Log.e("Error on making ", "sumbitData: "+e );
        }
        ApiExecutor.getApiService().postProposalReq(hashMap).enqueue(new retrofit2.Callback<OtpResponse>() {
            @Override
            public void onResponse(retrofit2.Call<OtpResponse> call, Response<OtpResponse> response) {
                Log.e("REQ_RESPONSE", "onResponse: "+response );
                if (response.isSuccessful()){
                    PrefMananger.saveString(context,PrefMananger.PROPOSAL,null);
                   if (here){
                       startActivity(new Intent(context,MakeOfferCongratulationScreen.class));
                       Log.e("REQ PRO SUMBIT", "onResponse: "+response.body().message );
                       finish();
                   }else {
                       PrefMananger.saveString(context,PrefMananger.PROPOSAL,null);
                       Toast.makeText(QueryBrokerSecondSecondScreen.this,"Continue for next customer",Toast.LENGTH_SHORT);
                   }
                }
                pd.dismiss();
            }
            @Override
            public void onFailure(retrofit2.Call<OtpResponse> call, Throwable t) {
                PrefMananger.saveString(context,PrefMananger.PROPOSAL,null);
                pd.dismiss();
            }
        });
    }

}
