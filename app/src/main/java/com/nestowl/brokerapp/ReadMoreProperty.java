package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadMoreProperty extends AppCompatActivity {
    String text,nav;
    TextView mainText, navtxt;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more_property);
        Intent intent = getIntent();
        text=intent.getStringExtra("txt");
        nav=intent.getStringExtra("topic");
        mainText=findViewById(R.id.READ_MORE_PROPERTY_MAIN_TEXT);
        img=findViewById(R.id.iv34);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mainText.setText(text);
        navtxt=findViewById(R.id.READ_MORE_PROPERTY_NAV_TEXT);
        navtxt.setText(nav);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
}