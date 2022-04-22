package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nestowl.utils.UrlClass;

public class PartnerRequestThird extends AppCompatActivity {
    ImageView imageView;
    FrameLayout view_proposal,contact,chat;
    Intent intent;
    String name,address,type,conf,link,dp,budget,id,user_id,projectname,sqs,itemId;
    TextView nameText,addressText,typeText,bhkText,projectnameText,bhk2,sq,budgettext,address2;
    ImageView dpImg;
    CardView property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_request_third);
        intent = getIntent();
        name=intent.getStringExtra("developer");
        address=intent.getStringExtra("address");
        type=intent.getStringExtra("type");
        conf=intent.getStringExtra("conf");
        link=intent.getStringExtra("link");
        dp=intent.getStringExtra("dp");
        budget=intent.getStringExtra("budget");
        id=intent.getStringExtra("id");
        user_id=intent.getStringExtra("user_id");
        projectname=intent.getStringExtra("name");
        sqs=intent.getStringExtra("sq");
        itemId=intent.getStringExtra("itemId");
        view_proposal=findViewById(R.id.view_proposal);
        view_proposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PartnerRequestThird.this,QueryFinalScreenThird.class);
                intent.putExtra("id",itemId);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        imageView=findViewById(R.id.iv34);
        imageView.setOnClickListener(new View.OnClickListener() {
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
        contact=findViewById(R.id.PROJECTS_INFO_VIEW_CONTACT);
        chat=findViewById(R.id.PROJECTS_INFO_CHAT);
        property=findViewById(R.id.card_interested);
        nameText=findViewById(R.id.PROJECTS_INFO_NAME);
        addressText=findViewById(R.id.PROJECTS_INFO_ADDDRESS);
        typeText=findViewById(R.id.PROJECTS_INFO_TYPE);
        bhkText=findViewById(R.id.PROJECTS_INFO_CONF);
        projectnameText=findViewById(R.id.PROJECTS_INFO_PROJECT_NAME);
        bhk2=findViewById(R.id.PROJECTS_INFO_EXTRA_CONF);
        budgettext=findViewById(R.id.PROJECTS_INFO_EXTRA_BUDGET);
        address2=findViewById(R.id.PROJECTS_INFO_EXTRA_ADDRESS);
        sq=findViewById(R.id.PROJECTS_INFO_EXTRA_SQ);
        dpImg=findViewById(R.id.PROJECTS_INFO_EXTRA_IMG);
        setdata();
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =  new Intent(PartnerRequestThird.this,Chating.class);
                intent.putExtra("id",user_id);
                intent.putExtra("name",name);
                intent.putExtra("dp",dp);
                startActivity(intent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent =  new Intent(PartnerRequestThird.this,ViewContact.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("pid",id);
                intent.putExtra("id","null");
                startActivity(intent);
            }
        });
        property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(UrlClass.PROJECT_URL+id));
                startActivity(intent);
            }
        });

    }
    private void setdata() {
        nameText.setText(name);
        addressText.setText(address);
        typeText.setText(type);
        bhkText.setText(conf);
        bhk2.setText(conf);
        projectnameText.setText("Project Name :"+projectname);
        budgettext.setText(budget);
        address2.setText(address);
        sq.setText(sqs);
        Glide.with(this).load(dp).placeholder(R.drawable.default_x_x).into(dpImg);
    }
}
