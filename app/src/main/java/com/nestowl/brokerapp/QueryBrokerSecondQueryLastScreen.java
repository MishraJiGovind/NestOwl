package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProposalModal;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;


public class  QueryBrokerSecondQueryLastScreen extends AppCompatActivity {
    ImageView back_img;
    CardView submit;
    TextView name,sumbit;
    ScrollView scrollView;
    RadioGroup local,clint,pick,docs;
    CheckBox atach,editl;
    String Slocal,Scint,Spick,Sdocs,TOTAL_CLICK;
    Context context;
    int clicks;
    LoginPojo loginPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_broker_second_query_last_screen);
        TOTAL_CLICK =  getIntent().getStringExtra("clicks");
        context = this;
        loginPojo = PrefMananger.GetLoginData(this);
        sumbit=findViewById(R.id.tv_next_make_offer);
        back_img=findViewById(R.id.ARTICLES_BACK);
        name=findViewById(R.id.PROPOSAL_REQ_EXTRA_NAME);
        local=findViewById(R.id.PROPOSAL_REQ_EXTRA_LOCAL);
        clint=findViewById(R.id.PROPOSAL_REQ_EXTRA_CLINT);
        pick=findViewById(R.id.PROPOSAL_REQ_EXTRA_PICK);
        docs=findViewById(R.id.PROPOSAL_REQ_EXTRA_DOCS);
        atach=findViewById(R.id.PROPOSAL_REQ_EXTRA_ATTACH);
        editl=findViewById(R.id.PROPOSAL_REQ_EXTRA_EDIT);
        clicks = Integer.parseInt(TOTAL_CLICK);
        submit=findViewById(R.id.card_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProposalModal proposalModal = new ProposalModal();
                proposalModal = new Gson().fromJson(PrefMananger.getString(context,PrefMananger.PROPOSAL),ProposalModal.class);
                proposalModal.setLocal_facilty(Slocal);
                proposalModal.setClient_verification(Scint);
                proposalModal.setPick_and_drop(Spick);
                proposalModal.setProper_document(Sdocs);
                String json =new Gson().toJson(proposalModal);
                PrefMananger.saveString(context,PrefMananger.PROPOSAL, json);
                Intent intent = new Intent(QueryBrokerSecondQueryLastScreen.this,QueryBrokerSecondSecondScreen.class);
                if (clicks == 1){
                    intent.putExtra("clicks","0");

                }else {
                clicks--;
                sumbit.setText("NEXT");
                intent.putExtra("clicks",String.valueOf(clicks));
                }
                startActivity(intent);
                finish();
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        local.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.PROPOSAL_REQ_EXTRA_LOCAL_YES){
                    Slocal="yes";
                }else {
                    Slocal="No";

                }
            }
        });
        clint.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.PROPOSAL_REQ_EXTRA_CLINT_YES){
                    Scint="yes";
                }else {
                    Scint="No";

                }
            }
        });
        pick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.PROPOSAL_REQ_EXTRA_PICK_YES){
                    Spick="yes";
                }else {
                    Spick="No";

                }
            }
        });
        docs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.PROPOSAL_REQ_EXTRA_DOCS_YES){
                    Sdocs="yes";
                }else {
                    Sdocs="No";
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        name.setText("("+loginPojo.getFirstName()+")");
    }
}
