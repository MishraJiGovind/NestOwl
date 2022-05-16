package com.nestowl.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.brokerapp.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertParentLeadsFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    ViewPager viewPager;
    TabLayout tabLayout;
    Context context;

    public AlertParentLeadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_alert_parent_leads, container, false);
        context= getContext();
        viewPager=view.findViewById(R.id.vp_leads_alert);
        tabLayout=view.findViewById(R.id.tab_leads_alert);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        ProgressDialog pd =  new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Loading...");
//        pd.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
            }
        };
        handler.postDelayed(runnable,6000);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getChildFragmentManager());
        adapter.addFragment(new LeadFragment(), "Lead");
        adapter.addFragment(new AlertsFragmetn(), "Leads Status");
        adapter.addFragment(new PartnerRequirement(), "Partner Requirement");
        adapter.addFragment(new PartnerRequest(), "Partner Request");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
