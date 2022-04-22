package com.nestowl.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nestowl.brokerapp.IntroArmyQuery;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SahilDahiyaCircle;
import com.nestowl.brokerapp.SavedListing;
import com.nestowl.brokerapp.SavedSearchProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragementSeller extends Fragment {

    FrameLayout frm;
    TextView tv,tv_saved_search,saved_pro;




    public ProfileFragementSeller() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_fragement_seller, container, false);
        tv=view.findViewById(R.id.id_view_nest_profile);
        frm=view.findViewById(R.id.frem_complete);
        saved_pro=view.findViewById(R.id.saved_properties);
        tv_saved_search=view.findViewById(R.id.saved_search);


        tv_saved_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SavedSearchProfile.class));
            }
        });


        saved_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SavedListing.class));
            }
        });

        frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), IntroArmyQuery.class));
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SahilDahiyaCircle.class));
            }
        });

        return  view;
    }



}
