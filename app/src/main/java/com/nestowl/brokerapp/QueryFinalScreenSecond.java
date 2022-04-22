package com.nestowl.brokerapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class QueryFinalScreenSecond extends AppCompatActivity {
    ImageView back_img;
    Boolean is_Residential_click = false;
    FrameLayout fm_physically,fm_virtually;
    LinearLayout lm_ready_to_move_factory,lm_under_factory;
    RadioButton whats_app,jio_meet,skpye,zoom,google_due,face_time;
    RadioButton rb;
    RadioGroup rg;
    LinearLayout l1_whatsapp,l2_jio_meet,l3_skype,l4_zoom,l5_google_due,l6_face_time;
    HorizontalScrollView horizontalScrollView;
    ScrollView scrollView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_final_screen_second);
        fm_physically=findViewById(R.id.BUILDER_DEAL_PYCALLY);
        fm_virtually=findViewById(R.id.BUILDER_DEAL_VEETRUALLY);
        horizontalScrollView=findViewById(R.id.horizontal_id_two);

        scrollView=findViewById(R.id.scroll);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                horizontalScrollView.setVisibility(View.GONE);
                if (scrollY>55){
                    horizontalScrollView.setVisibility(View.GONE);

                }
            }
        });

        l1_whatsapp=findViewById(R.id.PROPOSAL_REQ_VIDEO_INPUT_ID);
        l2_jio_meet=findViewById(R.id.lnd_jio_meet);
        l3_skype=findViewById(R.id.lnd_skype);
        l4_zoom=findViewById(R.id.lnd_zoom);
        l5_google_due=findViewById(R.id.lnd_google_due);
        l6_face_time=findViewById(R.id.lnd_face_time);
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

                lm_ready_to_move_factory.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lm_under_factory.setVisibility(View.GONE);
                    fm_physically.setBackgroundResource(R.drawable.employe_circle_rounded);

                } else {

                    is_Residential_click = true;

                    lm_under_factory.setVisibility(View.VISIBLE);
                    fm_physically.setBackgroundResource(R.drawable.selected_background_filter);


                }
            }
        });
        fm_virtually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lm_ready_to_move_factory.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    fm_virtually.setBackgroundResource(R.drawable.selected_background_filter);

                    l1_whatsapp.setVisibility(View.GONE);
                    l2_jio_meet.setVisibility(View.GONE);
                    l3_skype.setVisibility(View.GONE);
                    l4_zoom.setVisibility(View.GONE);
                    l5_google_due.setVisibility(View.GONE);
                    l6_face_time.setVisibility(View.GONE);
                    lm_under_factory.setVisibility(View.GONE);

                } else {

                    is_Residential_click = true;
                    fm_virtually.setBackgroundResource(R.drawable.employe_circle_rounded);

                    lm_ready_to_move_factory.setVisibility(View.GONE);

                    l1_whatsapp.setVisibility(View.VISIBLE);
                    l2_jio_meet.setVisibility(View.GONE);
                    l3_skype.setVisibility(View.GONE);
                    l4_zoom.setVisibility(View.GONE);
                    l5_google_due.setVisibility(View.GONE);
                    l6_face_time.setVisibility(View.GONE);

                }
            }
        });


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }


    public void rbclick(View view){
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb =    (RadioButton)findViewById(radiobuttonid);
        if (rb.getText().toString().equalsIgnoreCase("Whats App"))
        {
            l1_whatsapp.setVisibility(View.VISIBLE);
            l2_jio_meet.setVisibility(View.GONE);
            l3_skype.setVisibility(View.GONE);
            l4_zoom.setVisibility(View.GONE);
            l5_google_due.setVisibility(View.GONE);
            l6_face_time.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Jio Meet"))
        {
            l1_whatsapp.setVisibility(View.GONE);
            l2_jio_meet.setVisibility(View.VISIBLE);
            l3_skype.setVisibility(View.GONE);
            l4_zoom.setVisibility(View.GONE);
            l5_google_due.setVisibility(View.GONE);
            l6_face_time.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Skype"))
        {
            l1_whatsapp.setVisibility(View.GONE);
            l2_jio_meet.setVisibility(View.GONE);
            l3_skype.setVisibility(View.VISIBLE);
            l4_zoom.setVisibility(View.GONE);
            l5_google_due.setVisibility(View.GONE);
            l6_face_time.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Zoom"))
        {
            l1_whatsapp.setVisibility(View.GONE);
            l2_jio_meet.setVisibility(View.GONE);
            l3_skype.setVisibility(View.GONE);
            l4_zoom.setVisibility(View.VISIBLE);
            l5_google_due.setVisibility(View.GONE);
            l6_face_time.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Google Due"))
        {
            l1_whatsapp.setVisibility(View.GONE);
            l2_jio_meet.setVisibility(View.GONE);
            l3_skype.setVisibility(View.GONE);
            l4_zoom.setVisibility(View.GONE);
            l5_google_due.setVisibility(View.VISIBLE);
            l6_face_time.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Face Time"))
        {
            l1_whatsapp.setVisibility(View.GONE);
            l2_jio_meet.setVisibility(View.GONE);
            l3_skype.setVisibility(View.GONE);
            l4_zoom.setVisibility(View.GONE);
            l5_google_due.setVisibility(View.GONE);
            l6_face_time.setVisibility(View.VISIBLE);

        }




    }

}
