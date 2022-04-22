package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartOfferSecondQueryScreens extends AppCompatActivity {
    FrameLayout f_yes,f_no,frm_otherr;
    LinearLayout ln_yes,ln_other;
    ImageView back_img;

    CardView cardView;
    Boolean is_bathroom = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_offer_second_query_screens);
        cardView=findViewById(R.id.SELLER_PROPOSAL_BUYER_COUNTINUE);
        ln_yes=findViewById(R.id.lnd_will);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        f_yes=findViewById(R.id.yes_first);
        frm_otherr=findViewById(R.id.frm_engineer);
        ln_other=findViewById(R.id.lnd_engineer);
        f_no=findViewById(R.id.no_first);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StartOfferSecondQueryScreens.this,StartOfferQueryThirdScreen.class);
                startActivity(intent);
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}




        frm_otherr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_bathroom) {
                    is_bathroom = false;
                    frm_otherr.setBackgroundResource(R.drawable.employe_circle_rounded);
                    ln_other.setVisibility(View.GONE);
                } else {

                    is_bathroom = true;
                    frm_otherr.setBackgroundResource(R.drawable.selected_background_filter);

                    ln_other.setVisibility(View.VISIBLE);

                }


            }
        });



        f_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (is_bathroom) {
                    is_bathroom = false;
                    f_yes.setBackgroundResource(R.drawable.employe_circle_rounded);
                    ln_yes.setVisibility(View.GONE);
                } else {

                    is_bathroom = true;
                    f_yes.setBackgroundResource(R.drawable.selected_background_filter);
                    ln_yes.setVisibility(View.VISIBLE);

                }

            }
        });
    }
}
