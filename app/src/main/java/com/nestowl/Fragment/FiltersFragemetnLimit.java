package com.nestowl.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewAllActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersFragemetnLimit extends Fragment {
    TextView tv_view_all_amenties;
    SeekBar seekbar_first,seekbar_second,seekbar_thir;
    TextView tv_first,tv_second,tv_third,tv_bedroom;
    FrameLayout frm_flate;


    public FiltersFragemetnLimit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_filters_fragemetn_limit, container, false);
         tv_view_all_amenties=view.findViewById(R.id.view_all_amentie);
         seekbar_first=view.findViewById(R.id.seek_bar_budger);
         tv_second=view.findViewById(R.id.tv_carpet_are);
         seekbar_thir=view.findViewById(R.id.seekbar_third);
         tv_third=view.findViewById(R.id.tv_third_basement);
         frm_flate=view.findViewById(R.id.flat_apartment_id);
         seekbar_second=view.findViewById(R.id.seek_bar_carpet_area);
         frm_flate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 frm_flate.setBackgroundResource(R.drawable.selected_background_filter);

             }
         });
        seekbar_second.setMax(2450);
        seekbar_second.setProgress(0);

        seekbar_thir.setMax(120);
        seekbar_thir.setProgress(0);
tv_first=view.findViewById(R.id.tv_budget);
        seekbar_first.setMax(70000000);
        seekbar_first.setProgress(0);
        seekbar_first.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<10000000) {
                    progress=(progress/500000);
                    tv_first.setText("" + (progress*5) + " L");
                }else{
                    long value=progress/10000000;
                    long lac=(progress%10000000);
                    lac=lac/1000000;
                    if (progress<seekBar.getMax()) {
                        //if (value>1) {
                            tv_first.setText("" + Math.round((value)) + "." + lac + " Cr");
                      //  }
                    }else {
                        tv_first.setText("7.0 Cr");
                    }
                }
                //tv1.setText(""+0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_second.setText(""+progress);
                //tv1.setText(""+0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tv_view_all_amenties.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 startActivity(new Intent(getActivity(), ViewAllActivity.class));


             }
         });
        seekbar_thir.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_third.setText(""+progress);
                //tv1.setText(""+0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





        return view;
    }



}
