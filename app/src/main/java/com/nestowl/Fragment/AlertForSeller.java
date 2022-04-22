package com.nestowl.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nestowl.brokerapp.GreekTickSellerSecond;
import com.nestowl.brokerapp.GreenTickDealClosedFirst;
import com.nestowl.brokerapp.GreenTickDealClosedSecond;
import com.nestowl.brokerapp.GreenTickSellerOngoing;
import com.nestowl.brokerapp.GreenTickThirdSeller;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewDetailsScreenGreenThird;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertForSeller extends Fragment {
    TextView textView;
    Boolean is_Residential_click = false;
    RadioButton rad_deal,rad_ongoing;
    CardView view_leads_first,view_leads_second,view_detail_third,card_four,card_five,card_six,card_saven,card_eight,card_nine,card_ten,card_eleven;
    LinearLayout lnr_ongoing,lnr_deal;



    public AlertForSeller() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alert_for_seller, container, false);
        card_eleven=view.findViewById(R.id.view_details_eleven);



        rad_ongoing=view.findViewById(R.id.radio_ongoing);
        rad_deal=view.findViewById(R.id.radio_deal_closed);
        card_four=view.findViewById(R.id.view_details_four);
        card_five=view.findViewById(R.id.view_details_five);
        card_six=view.findViewById(R.id.view_details_six);
        card_saven=view.findViewById(R.id.view_details_saven);
        card_eight=view.findViewById(R.id.view_details_eight);
        card_nine=view.findViewById(R.id.view_details_nine);
        card_ten=view.findViewById(R.id.view_details_ten);


        lnr_ongoing=view.findViewById(R.id.lnd_ongoing);
        lnr_deal=view.findViewById(R.id.lnd_deal_closed);
        view_leads_first=view.findViewById(R.id.CUSTOM_ALERT_STATUS_VIEW_DETAILS);
        view_detail_third=view.findViewById(R.id.view_details_three);
        view_leads_second=view.findViewById(R.id.view_details_second);
        view_leads_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), GreenTickSellerOngoing.class));

            }
        });


        view_leads_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GreekTickSellerSecond.class));

            }
        });

        card_eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GreenTickThirdSeller.class));

            }
        });


        view_detail_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),GreekTickSellerSecond .class));

            }
        });



        card_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), GreenTickDealClosedFirst.class));

            }
        });


        card_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GreenTickDealClosedSecond.class));

            }
        });


        card_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GreenTickThirdSeller.class));

            }
        });

        card_saven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViewDetailsScreenGreenThird.class));

            }
        });


        card_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ViewDetailsScreenGreenThird.class));

            }
        });

        card_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GreenTickDealClosedSecond.class));

            }
        });



        rad_ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnr_deal.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnr_ongoing.setVisibility(View.GONE);

                } else {

                    is_Residential_click = true;
                    lnr_ongoing.setVisibility(View.VISIBLE);


                }
            }
        });
        rad_deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lnr_ongoing.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;

                    lnr_deal.setVisibility(View.VISIBLE);
                } else {

                    is_Residential_click = true;

                    lnr_deal.setVisibility(View.VISIBLE);

                }
            }
        });
        return view;
    }

}
