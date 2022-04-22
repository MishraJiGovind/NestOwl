package com.nestowl.brokerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.model.ProposalPropertyModal;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import java.util.ArrayList;

public class QueryPagesSecondMakeOffer extends AppCompatActivity {
    RadioGroup early,stage,negotiate,personalyy,timepayments,finalpayment,paymode;
    LinearLayout negotiateL;
    LinearLayout lnr_other;
    FrameLayout f1,f2;
    ImageView back_img;
    ScrollView scrollView;
    CardView cardView;
    String Searly,Sstage,Snegotiate,Spersonalyy,Stimepayments,Sfinalpayment,Spaymode,Stime,Sday,User_Id,P_Id;
    ArrayList<FloorPojo> floorPojos,floorPojos2;
    Context context;
    RadioButton nigoIn,nego;
    EditText nigoINN;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_pages_second_make_offer);
        lnr_other=findViewById(R.id.lnd_otherr);
        context = this;
        negotiateL=findViewById(R.id.SELLER_PROPOSAL_NEGOTIATE_UNLOCK);
        early=findViewById(R.id.SELLER_PROPOSAL_EARLY_GRUOP);
        stage=findViewById(R.id.SELLER_PROPOSAL_STAGE_GRUOP);
        negotiate=findViewById(R.id.SELLER_PROPOSAL_NEGOITIATE_GRUOP);
        personalyy=findViewById(R.id.SELLER_PROPOSAL_PERSONALLY_VARIFICATION_GRUOP);
        timepayments=findViewById(R.id.SELLER_PROPOSAL_TIME_PAYMENT);
        finalpayment=findViewById(R.id.SELLER_PROPOSAL_FINAL_PAY);
        paymode=findViewById(R.id.SELLER_PROPOSAL_PAY_MODE);

        nigoIn=findViewById(R.id.SELLER_PROPOSAL_NIGO_IN_RADIO);
        nego=findViewById(R.id.SELLER_PROPOSAL_NIGO_DISSCUS);
        nigoINN=findViewById(R.id.SELLER_PROPOSAL_NEGO_IN);

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
        early.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_EARLY_WAIT){
                    Searly="WAIT";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_EARLY_SOON){
                    Searly="SOON";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_EARLY_AS){
                    Searly="AS PER WISH";
                }
            }
        });
        stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_STAGE_FINAL){
                    Sstage="FINAL";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_STAGE_INTIAL){
                    Sstage="INITIAL";
                }
            }
        });
        negotiate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.PROPOSAL_VIEW_PRO_NEGOTIATE){
                    Snegotiate="YES";
                    negotiateL.setVisibility(View.VISIBLE);
                }
                if (checkedId==R.id.SELLER_PROPOSAL_NEGOTIATE_NO){
                    Snegotiate="NO";
                    negotiateL.setVisibility(View.GONE);
                }
            }
        });
        nigoIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nego.setChecked(false);
            }
        });
        nego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nigoIn.setChecked(false);
                Snegotiate="discuss with owner";
            }
        });
        nigoINN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s!=null){
                    Snegotiate=s.toString();
                }
            }
        });
        personalyy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_P_VARIFY_YES){
                    Spersonalyy="Yes";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_P_VARIFY_NO){
                    Spersonalyy="No";
                }
            }
        });
        timepayments.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_15){
                    Stimepayments="15 Days";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_30){
                    Stimepayments="30 Days";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_60){
                    Stimepayments="60 Days";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_90){
                    Stimepayments="90 Days";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_120){
                    Stimepayments="120 Days";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_TIME_DISCUSS){
                    Stimepayments="Discuss";
                }
            }
        });
        finalpayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_1) {
                    Sfinalpayment = "1 month";
                }
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_2) {
                    Sfinalpayment = "2 month";
                }
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_3) {
                    Sfinalpayment = "3 month";
                }
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_4) {
                    Sfinalpayment = "4 month";
                }
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_5) {
                    Sfinalpayment = "5 month";
                }
                if (checkedId == R.id.SELLER_PROPOSAL_FINAL_PAY_discuss) {
                    Sfinalpayment = "Discuss";
                }
            }
        });
        paymode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_PAY_MODE_CASH){
                    Spaymode="CASH";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_PAY_MODE_CHEAK){
                    Spaymode="CHECK";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_PAY_MODE_OWNER){
                    Spaymode="OWNER";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_PAY_MODE_RTGS){
                    Spaymode="RTGS";
                }
                if (checkedId==R.id.SELLER_PROPOSAL_PAY_MODE_SOME){
                    Spaymode="SOME";
                }
            }
        });

        f1=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        f2=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) f1.getChildAt(0);
                        textView.setText(text);
                        Sday=text;
                    }
                },floorPojos);
            }
        });
        f2.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView textView =  (TextView) f2.getChildAt(0);
                        textView.setText(text);
                        Stime=text;
                    }
                },floorPojos2);

            }
        });
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        cardView=findViewById(R.id.card_second_query);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProposalPropertyModal proposalPropertyModal =  new ProposalPropertyModal();
                proposalPropertyModal.setUser_id(String.valueOf(PrefMananger.GetLoginData(QueryPagesSecondMakeOffer.this).getUserId()));
                proposalPropertyModal.setProperty_id(PrefMananger.getString(context,PrefMananger.PROPOSAL_ID));
                proposalPropertyModal.setNumber_of_buyers(getIntent().getStringExtra("buyer").toString());
                proposalPropertyModal.setStage(Sstage);
                proposalPropertyModal.setDeal_to_happen(Snegotiate);
                proposalPropertyModal.setVerified_buyer(Spersonalyy);
                proposalPropertyModal.setNegotiate(Snegotiate);
                proposalPropertyModal.setPrice("no field");
                proposalPropertyModal.setDiscuss_with_owner("no field");
                proposalPropertyModal.setBooking_payment(Stimepayments);
                proposalPropertyModal.setFinal_payment(Sfinalpayment);
                proposalPropertyModal.setTransaction(Spaymode);
                proposalPropertyModal.setMeeting_days(Sday);
                proposalPropertyModal.setMeeting_time(Stime);
                String s =  new Gson().toJson(proposalPropertyModal);
                PrefMananger.saveString(context,PrefMananger.PROPERTY,s);
                String g = PrefMananger.getString(context,PrefMananger.PROPERTY);
                Log.e("log", "onClick: "+g );
                if (!g.equals("null")|!g.equals("")){
                    Intent i=new Intent(QueryPagesSecondMakeOffer.this,QueryScreenMakeBrokerThird.class);
                    startActivity(i);
                }
            }
        });
    }
    private void addItems(String text,ArrayList<FloorPojo> floorPojos){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
}
