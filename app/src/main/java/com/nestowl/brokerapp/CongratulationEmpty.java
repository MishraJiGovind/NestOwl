package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

public class CongratulationEmpty extends AppCompatActivity {
    TextView tv_edit,id,expire;
    LinearLayout priview,edit;
    ImageView imageView;
    String userId;
    User user;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation_empty);
        imageView=findViewById(R.id.iv46);
        id=findViewById(R.id.POST_PROPERTY_CONGO_ID);
        expire=findViewById(R.id.POST_PROPERTY_CONGO_EXPIRE);
        priview=findViewById(R.id.POST_PROPERTY_CONGO_PRIVIEW);
        edit=findViewById(R.id.POST_PROPERTY_CONGO_EDIT);
        cardView=findViewById(R.id.POST_PRO_CONGO_HOME);
        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        userId=user.getUser_id();

        if (PrefMananger.getString(this,"expire")!=null){
            expire.setText(PrefMananger.getString(this,"expire"));
            PrefMananger.saveString(this,"expire",null);
        }

        id.setText(PrefMananger.getString(this,PrefMananger.PROPERTY_ID) );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CongratulationEmpty.this,HomeScreen.class));
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongratulationEmpty.this,EditingScreenAll.class);
                intent.putExtra("id",PrefMananger.getString(CongratulationEmpty.this,PrefMananger.PROPERTY_ID));
                intent.putExtra("user_id",userId);
                startActivity(intent);
            }
        });
        priview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CongratulationEmpty.this,InterestedPropertyThirdUser.class);
                intent.putExtra("id",PrefMananger.getString(CongratulationEmpty.this,PrefMananger.PROPERTY_ID));
                intent.putExtra("user_id",userId);
                startActivity(intent);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CongratulationEmpty.this,HomeScreen.class));
                finish();
            }
        });



        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}


    }
}
