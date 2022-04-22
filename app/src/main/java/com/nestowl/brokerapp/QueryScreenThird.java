package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nestowl.AdapterClass.AddMoreAdapterClass;
import com.nestowl.CommenDialog.FloorPojo;

import java.util.ArrayList;

public class QueryScreenThird extends AppCompatActivity {

    Context context;
    CardView cardView;
    Activity activity;
    Boolean is_bathroom = false;
    ImageView back_img;

    FrameLayout f1,f2;
    LinearLayout ln_add_more;
    TextView tv_add_more;
    RecyclerView recyclerView;
    AddMoreAdapterClass addMoreAdapterClass;
    ArrayList<String> firslistt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_screen_third);
        context=this;
        tv_add_more=findViewById(R.id.id_add_more);
        ln_add_more=findViewById(R.id.lnd_add);

        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        f1=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        f2=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        recyclerView=findViewById(R.id.recycler_add_more_id);


        cardView=findViewById(R.id.card_submit);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(QueryScreenThird.this,MakeOfferCongratulationScreen.class);
                startActivity(intent);
            }
        });


//        tv_add_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (firslistt==null){
//                    firslistt= new ArrayList<>();
//
//                }
//                firslistt.add("");
//                if (addMoreAdapterClass==null){
//                    addMoreAdapterClass=new AddMoreAdapterClass(context,firslistt);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                    recyclerView.setAdapter(addMoreAdapterClass);
//
//                }
//                addMoreAdapterClass.notifyDataSetChanged();
//
//
//
//            }
//        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        f1=findViewById(R.id.PROPOSAL_REQ_MEET_DAY);
        f2=findViewById(R.id.PROPOSAL_REQ_MEET_TIME);
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(QueryScreenThird.this);
                dialog.setContentView(R.layout.query_date);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(QueryScreenThird.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img13=dialog.findViewById(R.id.iv13);
                img13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(QueryScreenThird.this);
                dialog.setContentView(R.layout.query_time);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(QueryScreenThird.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv12);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

    }

    ArrayList<FloorPojo> floorPojos=new ArrayList<>();
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }

}
