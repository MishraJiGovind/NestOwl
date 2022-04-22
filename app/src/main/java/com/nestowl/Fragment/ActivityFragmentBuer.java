package com.nestowl.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragmentBuer extends Fragment implements TabLayout.OnTabSelectedListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Pager adapter ;
    View pro,response;


    public ActivityFragmentBuer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FrameLayout frame_filter_first;
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_activity, container, false);


        viewPager= view.findViewById(R.id.view_pager_activityy);
        tabLayout = view.findViewById(R.id.tabs_activity);
        adapter = new Pager(getChildFragmentManager());



        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        ProgressDialog pd =  new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Loading...");
        pd.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                pd.dismiss();
            }
        };
        handler.postDelayed(runnable,6000);
        response =((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
        if (PrefMananger.getString(getContext(),"responsekey")!=null){
            viewPager.setCurrentItem(1);
            response.performClick();

        }
        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new MyResponses(), "My Properties");
        adapter.addFragment(new ActivityMyProperties(), "My Responses");

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
