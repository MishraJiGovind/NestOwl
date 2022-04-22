package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nestowl.brokerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersOtherThird extends Fragment {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    SeekBar other_fragment,seekbar_second,seekbar_flor;


    public FiltersOtherThird() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_filters_other_third, container, false);
        tv1=view.findViewById(R.id.id_budget_minimum);
        seekbar_second =view.findViewById(R.id.seekbar_other_second);
        tv3=view.findViewById(R.id.tv_other_second);
        tv5=view.findViewById(R.id.tv_flor_first);
        seekbar_flor=view.findViewById(R.id.seek_bar_flor);

        other_fragment=view.findViewById(R.id.seekbar_other);
        other_fragment.setMax(70000000);
        other_fragment.setProgress(0);
        seekbar_second.setMax(2450);
        seekbar_second.setProgress(0);

        seekbar_flor.setMax(120);
        seekbar_flor.setProgress(0);

        seekbar_flor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv5.setText(""+progress);
                //tv1.setText(""+0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        other_fragment.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress<10000000) {
                    progress=(progress/500000);
                    tv1.setText("" + (progress*5) + " L");
                }else{
                    long value=progress/10000000;
                    long lac=(progress%10000000);
                    lac=lac/1000000;
                    if (progress<seekBar.getMax()) {
                        //if (value>1) {
                        tv1.setText("" + Math.round((value)) + "." + lac + " Cr");
                        //  }
                    }else {
                        tv1.setText("7.0 Cr");
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

        seekbar_second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv3.setText(""+progress);
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
