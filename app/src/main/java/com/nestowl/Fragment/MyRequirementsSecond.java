package com.nestowl.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nestowl.brokerapp.AkshitActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewProfileAkshitViewLeads;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRequirementsSecond extends Fragment {
    FrameLayout card_leads,card_one,card_two;
    FrameLayout frame_view;
    Boolean is_Residential_click = false;
    RadioGroup rg_group;
    RadioButton rad_submit,rad_received,rad_accepted;
    LinearLayout lr_submit,lr_received,lr_accept;
    RadioButton radioButton;
    FrameLayout view_one,view_two,view_three;
    FrameLayout accept1,accept2,accept3;





    public MyRequirementsSecond() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_requirements_second, container, false);
        card_leads=view.findViewById(R.id.card_leads_first);
        card_one=view.findViewById(R.id.card_view_leads_one);
        card_two=view.findViewById(R.id.card_view_leads_two);
        rad_received=view.findViewById(R.id.HOME_PROJECT_RECIVIED);
        rad_accepted=view.findViewById(R.id.HOME_PROJECT_PENDING);
        rad_submit=view.findViewById(R.id.HOME_PROJECT_LIVE);

        rg_group=view.findViewById(R.id.rg_two);
        lr_submit=view.findViewById(R.id.HOME_PROJECT_LNR_LIVE);
        lr_accept=view.findViewById(R.id.HOME_PROJECT_LNR_PENDING);
        lr_received=view.findViewById(R.id.HOME_PROJECT_LNR_RECIVED);
        accept1=view.findViewById(R.id.accept_one);
        accept1=view.findViewById(R.id.accept_one);
        accept1=view.findViewById(R.id.accept_one);

        view_one=view.findViewById(R.id.view_profile_one);
        view_two=view.findViewById(R.id.view_profile_second_s);
        view_three=view.findViewById(R.id.view_profile_third);

        view_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViewProfileAkshitViewLeads.class));

            }
        });

        view_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),ViewProfileAkshitViewLeads.class);
                startActivity(i);
            }
        });  view_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),ViewProfileAkshitViewLeads.class);
                startActivity(i);
            }
        });



        card_leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AkshitActivity.class));

            }
        });

        frame_view=view.findViewById(R.id.requirement_proposal_card);
        frame_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AkshitActivity.class));

            }
        });
        card_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AkshitActivity.class));

            }
        });   card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AkshitActivity.class));

            }
        });

        rad_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lr_received.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lr_submit.setVisibility(View.GONE);

                } else {

                    is_Residential_click = true;
                    lr_submit.setVisibility(View.VISIBLE);


                }
            }
        });
        rad_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lr_submit.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;

                    lr_received.setVisibility(View.VISIBLE);
                } else {

                    is_Residential_click = true;

                    lr_received.setVisibility(View.VISIBLE);

                }
            }
        });
        rad_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lr_received.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;

                    lr_accept.setVisibility(View.VISIBLE);
                } else {

                    is_Residential_click = true;

                    lr_accept.setVisibility(View.VISIBLE);

                }
            }
        });



        return view;
    }
/*
    public void rbclick(View view){
        int radiobuttonid = rg_group.getCheckedRadioButtonId();
        radioButton =    view.findViewById(radiobuttonid);
        if (rad_submit.getText().toString().equalsIgnoreCase("Submit"))
        {
            lr_submit.setVisibility(View.VISIBLE);
            lr_received.setVisibility(View.GONE);
            lr_accept.setVisibility(View.GONE);

        }else if (radioButton.getText().toString().equalsIgnoreCase("Received"))
        {
            lr_received.setVisibility(View.VISIBLE);
            lr_submit.setVisibility(View.GONE);
            lr_accept.setVisibility(View.GONE);

        }else if (radioButton.getText().toString().equalsIgnoreCase("Accepted"))
        {
            lr_accept.setVisibility(View.VISIBLE);
            lr_submit.setVisibility(View.GONE);
            lr_received.setVisibility(View.GONE);

        }

    }*/

}
