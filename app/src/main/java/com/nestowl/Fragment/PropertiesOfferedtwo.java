package com.nestowl.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nestowl.brokerapp.NestOwnersSixteen;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ScreenSixteenDetailsPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PropertiesOfferedtwo extends Fragment {
    RadioButton rad_owner,rad_agent;
    LinearLayout lnd_owner,lnd_agent;
    Boolean is_Residential_click = false;
    FrameLayout deal_one,deal_two,deal_three,listine_one,listing_two,listing_three,listing_four;
    FrameLayout deal_four,deal_five,deal_six,deal_saven,deal_last;
    CardView cardView;
    FrameLayout card_one,card_two,card_three,card_four;



    public PropertiesOfferedtwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_properties_offeredtwo, container, false);
        cardView=view.findViewById(R.id.card_four);


        rad_owner=view.findViewById(R.id.radio_rq);
        rad_agent=view.findViewById(R.id.radiobutton_rq);
        lnd_owner=view.findViewById(R.id.lnr_owner);
        lnd_agent=view.findViewById(R.id.lnr_agent);
        deal_last=view.findViewById(R.id.deal_closed_four_s);
        deal_one=view.findViewById(R.id.deal_closed_one);
        deal_two=view.findViewById(R.id.deal_closed_two);
        deal_three=view.findViewById(R.id.deal_closed_three);
        listine_one=view.findViewById(R.id.frame_one);
        listing_two=view.findViewById(R.id.frame_two);
        listing_three=view.findViewById(R.id.frame_three);
        card_one=view.findViewById(R.id.arshi_one);
        card_two=view.findViewById(R.id.arshi_two);
        card_three=view.findViewById(R.id.arshi_three);
        card_four=view.findViewById(R.id.arshi_four);
        deal_four=view.findViewById(R.id.deal_closed_four);
        deal_five=view.findViewById(R.id.deal_closed_five);
        deal_six=view.findViewById(R.id.deal_closed_six);
        deal_saven=view.findViewById(R.id.deal_closed_saven);



        listine_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScreenSixteenDetailsPage.class));


            }
        });
        listing_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScreenSixteenDetailsPage.class));


            }
        });

        listing_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScreenSixteenDetailsPage.class));


            }

        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScreenSixteenDetailsPage.class));


            }
        });





        card_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));


            }
        });




        card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));


            }
        });


        card_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));


            }
        });


        card_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NestOwnersSixteen.class));


            }
        });






        deal_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        deal_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        deal_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        deal_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        deal_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        deal_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });




        deal_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        deal_saven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FrameLayout yes,no;
                LinearLayout l_all;
                TextView textView;

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.deal_closed_popup);
                yes=dialog.findViewById(R.id.yes_frame);
                no=dialog.findViewById(R.id.no_frame);
                l_all=dialog.findViewById(R.id.lnd_yes);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivf3);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });




        rad_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnd_agent.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnd_owner.setVisibility(View.GONE);

                } else {

                    is_Residential_click = true;
                    lnd_owner.setVisibility(View.VISIBLE);


                }
            }
        });
        rad_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnd_owner.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;

                    lnd_agent.setVisibility(View.VISIBLE);
                } else {

                    is_Residential_click = true;

                    lnd_agent.setVisibility(View.VISIBLE);

                }
            }
        });

        return view;
    }

}
