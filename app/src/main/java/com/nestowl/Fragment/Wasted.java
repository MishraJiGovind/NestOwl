package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestowl.brokerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Wasted extends Fragment {


    public Wasted() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wasted, container, false);
    }

}
