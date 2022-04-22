package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nestowl.AdapterClass.LeadsExploreMorePro;

import java.util.ArrayList;

public class LeadsPropertyShowMore extends AppCompatActivity {
    Intent intent;
    ArrayList<String> keys,values;
    RecyclerView recyclerView;
    LeadsExploreMorePro adpter;
    ImageView back,backImage;
    ArrayList<com.nestowl.model.LeadsExploreMorePro> data;
    com.nestowl.model.LeadsExploreMorePro exploreMorePro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_property_show_more);
        intent = getIntent();
        keys = new ArrayList<>();
        values =  new ArrayList<>();
        keys=intent.getStringArrayListExtra("values");
        values=intent.getStringArrayListExtra("data");
        recyclerView=findViewById(R.id.LEADS_PROPERTY_EXPLORE_RECYCLER);

        back=findViewById(R.id.iv34);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data =  new ArrayList<>();
        for (int i=0;i<keys.size();i++){
            exploreMorePro =  new com.nestowl.model.LeadsExploreMorePro();
            if (values.get(i)!=null) {
                if (!values.get(i).equals("")||!values.get(i).equals("nullnull")||!values.get(i).equals("null")){
                    exploreMorePro.setHead(keys.get(i));
                    exploreMorePro.setPara(values.get(i));
                    data.add(exploreMorePro);
                }
            }
        }
        adpter =  new LeadsExploreMorePro(this,data);
        recyclerView.setAdapter(adpter);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


    }
}