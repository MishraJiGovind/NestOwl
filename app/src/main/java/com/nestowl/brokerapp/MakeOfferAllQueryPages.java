package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nestowl.CommenDialog.FloorPojo;

import java.util.ArrayList;

public class MakeOfferAllQueryPages extends AppCompatActivity {
    RadioButton radio_other,rad_yes;
    LinearLayout lnr_yes;
    LinearLayout lnr_other;
    Context context;
    CardView cardView;
    Activity activity;
    Boolean is_bathroom = false;

    FrameLayout f1,f2;
    LinearLayout ln_add_more;
    TextView tv_add_more;
    ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_offer_all_query_pages);
        imageView=findViewById(R.id.iv31);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        radio_other=findViewById(R.id.radio_other);
        lnr_other=findViewById(R.id.lnd_otherr);
        lnr_yes=findViewById(R.id.SELLER_PROPOSAL_NEGOTIATE_UNLOCK);
        rad_yes=findViewById(R.id.PROPOSAL_VIEW_PRO_NEGOTIATE);
        tv_add_more=findViewById(R.id.id_add_more);
        ln_add_more=findViewById(R.id.lnd_add);
/*
        cardView=findViewById(R.id.card_submit);
*/

        f1=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        f2=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MakeOfferAllQueryPages.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MakeOfferAllQueryPages.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img13=dialog.findViewById(R.id.iv13);
                img13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MakeOfferAllQueryPages.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MakeOfferAllQueryPages.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv12);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        tv_add_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (is_bathroom) {
                    is_bathroom = false;
                    ln_add_more.setVisibility(View.GONE);
                } else {

                    is_bathroom = true;
                    ln_add_more.setVisibility(View.VISIBLE);

                }

            }
        });


        radio_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_bathroom) {
                    is_bathroom = false;
                    lnr_other.setVisibility(View.GONE);
                } else {

                    is_bathroom = true;

                    lnr_other.setVisibility(View.VISIBLE);

                }


            }
        });

        rad_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_bathroom) {
                    is_bathroom = false;
                    lnr_yes.setVisibility(View.GONE);
                } else {

                    is_bathroom = true;

                    lnr_yes.setVisibility(View.VISIBLE);

                }


            }
        });



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }

    ArrayList<FloorPojo> floorPojos=new ArrayList<>();
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
}
