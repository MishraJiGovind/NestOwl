package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class PropertyOneFirstInventory extends AppCompatActivity {

    ImageView back_img;
    FrameLayout f1,f2;
    LinearLayout l1_whatsapp,l2_jio_meet,l3_skype,l4_zoom,l5_google_due,l6_face_time;
    RadioButton yes,no,not;
    LinearLayout lm_under_factory,lm_ready_to_move_factory;
    Boolean is_Residential_click = false;
    RadioGroup rg,rgb;
    RadioButton rb;
    CardView cardView;
    LinearLayout lnd_few;
    ScrollView scrollView;
    FrameLayout whats_day,whats_time,jio_d,jio_tim,skyp_day,skyp_time,zoom_day,zoom_time,google_day,google_time,face_day,face_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_one_first_inventory);
        back_img=findViewById(R.id.ARTICLES_BACK);
        yes=findViewById(R.id.PROPOSAL_REQ_VIDEO_YES);
        no=findViewById(R.id.PROPOSAL_REQ_VIDEO_NO);
        not=findViewById(R.id.PROPOSAL_REQ_VIDEO_NO_NO);
        lnd_few=findViewById(R.id.PROPOSAL_REQ_PRO_MEET_MISSING_TAB);
        rgb=findViewById(R.id.PROPOSAL_REQ_PRO_MEET);

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
        zoom_day=findViewById(R.id.zoom_day);
        zoom_time=findViewById(R.id.zoom_time);
        face_day=findViewById(R.id.face_day);
        face_time=findViewById(R.id.facer_time);

        google_day=findViewById(R.id.google_date);
        google_time=findViewById(R.id.google_time);
        skyp_day=findViewById(R.id.skype_day);
        skyp_time=findViewById(R.id.skype_time);
        jio_d=findViewById(R.id.jio_day);
        jio_tim=findViewById(R.id.jio_time);
        whats_day=findViewById(R.id.PROPOSAL_REQ_VIDEO_DAY);
        whats_time=findViewById(R.id.PROPOSAL_REQ_VIDEO_TIME);
        face_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));
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

        face_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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


        google_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        google_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        zoom_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        zoom_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        skyp_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        skyp_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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
        jio_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        jio_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        whats_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

        whats_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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



        cardView=findViewById(R.id.SELLER_PROPOSAL_BUYER_COUNTINUE);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PropertyOneFirstInventory.this,PropertyOneSecondFirstInventory.class);
                startActivity(intent);
            }
        });

        lm_under_factory=findViewById(R.id.BUILDER_DEAL_PYCALLY_LNR);
        lm_ready_to_move_factory=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS);
        rg=findViewById(R.id.PROPOSAL_REQ_VIDEO_OPTIONS_GROUP);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lm_under_factory.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lm_ready_to_move_factory.setVisibility(View.VISIBLE);

                } else {

                    is_Residential_click = true;

                    lm_ready_to_move_factory.setVisibility(View.VISIBLE);


                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lm_ready_to_move_factory.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;

                    lm_under_factory.setVisibility(View.GONE);
                } else {

                    is_Residential_click = true;

                    lm_ready_to_move_factory.setVisibility(View.GONE);

                }
            }
        });



        l1_whatsapp=findViewById(R.id.PROPOSAL_REQ_VIDEO_INPUT_ID);
        l2_jio_meet=findViewById(R.id.lnd_jio_meet);
        l3_skype=findViewById(R.id.lnd_skype);
        l4_zoom=findViewById(R.id.lnd_zoom);
        l5_google_due=findViewById(R.id.lnd_google_due);
        l6_face_time=findViewById(R.id.lnd_face_time);


        f1=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        f2=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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

                final Dialog dialog = new Dialog(PropertyOneFirstInventory.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(PropertyOneFirstInventory.this, android.R.color.transparent)));


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






        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
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

    public void rbset(View view){
        int radiobuttonid = rgb.getCheckedRadioButtonId();
        rb =    (RadioButton)findViewById(radiobuttonid);
        if (rb.getText().toString().equalsIgnoreCase("Yes"))
        {
            lnd_few.setVisibility(View.GONE);

        }else if (rb.getText().toString().equalsIgnoreCase("Few Missing"))
        {
            lnd_few.setVisibility(View.VISIBLE);

        }


    }
}
