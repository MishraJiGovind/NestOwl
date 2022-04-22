package com.nestowl.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewAllActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersFragmentSecond extends Fragment {
    TextView tv_filters;
    TextView tv_first,tv_second,tv_third;
            SeekBar seekbar_firsts,second,thirds;


    public FiltersFragmentSecond() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_filters_fragment_second, container, false);
tv_first=view.findViewById(R.id.budget_first_id);
tv_third=view.findViewById(R.id.flor_basement);
thirds=view.findViewById(R.id.flor_seekbar);
seekbar_firsts=view.findViewById(R.id.seekbar_budgets);
tv_second=view.findViewById(R.id.id_carpets);
second=view.findViewById(R.id.seekbar_carpets);

        thirds.setMax(120);
        thirds.setProgress(0);
        second.setMax(2450);
        second.setProgress(0);
        seekbar_firsts.setMax(70000000);
        seekbar_firsts.setProgress(0);
        tv_filters =view.findViewById(R.id.view_all_amentie_two);
        tv_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ViewAllActivity.class));

            }
        });
        seekbar_firsts.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        thirds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
