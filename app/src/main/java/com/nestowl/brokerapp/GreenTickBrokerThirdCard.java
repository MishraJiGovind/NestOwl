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
import android.widget.RadioButton;

public class GreenTickBrokerThirdCard extends AppCompatActivity {
    RadioButton frame_deal;
    FrameLayout fm_review,ask_for;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_tick_broker_third_card);

        frame_deal=findViewById(R.id.frame_deal_closed);
        fm_review=findViewById(R.id.review_first);
        ask_for=findViewById(R.id.ask_for_six);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        fm_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GreenTickBrokerThirdCard.this,ReviewActivity.class);
                startActivity(intent);
            }
        });



        frame_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(GreenTickBrokerThirdCard.this);
                dialog.setContentView(R.layout.yes_no_submit_view_details);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(GreenTickBrokerThirdCard.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.arshi_first);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });



        ask_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(GreenTickBrokerThirdCard.this);
                dialog.setContentView(R.layout.ask_for_review);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(GreenTickBrokerThirdCard.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                FrameLayout no = dialog.findViewById(R.id.no_id);
                FrameLayout yes=dialog.findViewById(R.id.yes_id);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        yesDialog();
                        dialog.dismiss();
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nosDialog();
                        dialog.dismiss();
                    }
                });
            }
        });

    }
    private void yesDialog() {
        Dialog dialog = new Dialog(GreenTickBrokerThirdCard.this);
        dialog.setContentView(R.layout.ask_for_review_yes_layout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(GreenTickBrokerThirdCard.this, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void nosDialog() {
        Dialog dialog = new Dialog(GreenTickBrokerThirdCard.this);
        dialog.setContentView(R.layout.ask_for_review_no_popup);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(GreenTickBrokerThirdCard.this, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }
}
