package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nestowl.AdapterClass.PropertyPreviewImages;
import com.nestowl.model.PropertyPreviewImagesModal;
import com.nestowl.brokerapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectPhotos extends Fragment {
    RecyclerView recyclerView;
    PropertyPreviewImages adapter;
    Bundle bundle;
    ArrayList<PropertyPreviewImagesModal> data;

    public ProjectPhotos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_project_photos, container, false);

        data=new ArrayList<>();
        bundle=getArguments();
        try {
            JSONArray jsonArray = new JSONArray(bundle.getString("data"));
            for (int i=0;i<jsonArray.length();i++){
                PropertyPreviewImagesModal info =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),PropertyPreviewImagesModal.class);
                if (info.getType().equals(bundle.getString("key"))){
                    data.add(info);
                }else {
                    Log.e("geting photos", "onCreateView: not macthed" );
                }
            }
            adapter=new PropertyPreviewImages(getContext(),data);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){
                    Log.e("geting photos", "onCreateView: "+e );
        }

        return view;
    }

}
