package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class SahilReviewSubmitProperty extends AppCompatActivity {

    TextView tv_make_offer,tv_view_all,tv_view_all_office,tv_view_all_deal_closed,tv_read_more_abt_sahil,tv_review_me;
    FrameLayout f1,f2,f3,f4,submit_property;
    ImageView back_img;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sahil_review_submit_property);
        tv_make_offer=findViewById(R.id.make_offer_rera);
        tv_view_all_office=findViewById(R.id.BROKER_PROFILE_OFFICE_VIEW_ALL);
        tv_view_all_deal_closed=findViewById(R.id.BROKER_PROFILE_CLOSED_DEALS);
        tv_read_more_abt_sahil=findViewById(R.id.BROKER_PROFILE_READMORE_ABOUT);
        submit_property=findViewById(R.id.submt_property_id);
        back_img=findViewById(R.id.arshi11);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submit_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                final Dialog dialog = new Dialog(SahilReviewSubmitProperty.this);
                dialog.setContentView(R.layout.submit_property_owner_mode_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SahilReviewSubmitProperty.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.iv6);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });




            }
        });
        f1=findViewById(R.id.CUSTOM_PROPERTY_ONBOARD_MAKE_OFFER);
        f2=findViewById(R.id.make_two);
        f3=findViewById(R.id.make_three);
        f4=findViewById(R.id.LEADS_PROPERTY_DETA_CHAT);
        tv_review_me=findViewById(R.id.tv_reviews_me);
        tv_review_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,ReviewMainActivity.class);
                startActivity(intent);

            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);

            }
        });


        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);

            }
        });   f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);

            }
        });   f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);

            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        tv_read_more_abt_sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,ReadDisclaimer.class);
                startActivity(intent);

            }
        });
        tv_view_all_deal_closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    firtsSysteme();
                }
            }
        });
        tv_view_all_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firtsSysteme();
            }
        });


        tv_make_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SahilReviewSubmitProperty.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });
    }


    public void firtsSysteme(){

        Intent intent=new Intent( SahilReviewSubmitProperty.this,PropertiesBhkActivity.class);
        startActivity(intent);


    }
}
