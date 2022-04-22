package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ReadArticles extends AppCompatActivity {
    ImageView back,articleImage;
    TextView title,date,description,nav_title;
    LinearLayout images;
    String TITLE,DATE,IMAGE,DESCRIPTION;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_articles);
        back=findViewById(R.id.ARTICLES_BACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        title=findViewById(R.id.READ_ARTICLES_TITLE);
        date=findViewById(R.id.READ_ARTICLES_DATE);
        articleImage=findViewById(R.id.READ_ARTICLES_IMAGE);
        description=findViewById(R.id.READ_ARTICLES_DESCRIPTION);
//        images=findViewById(R.id.READ_ARTICLES_IMAGES);
        scrollView=findViewById(R.id.READ_ARTICLE_SCROLL);
        nav_title=findViewById(R.id.NAV_TITLE);

        Intent intent = getIntent();
        TITLE =  intent.getStringExtra("title");
        DATE =  intent.getStringExtra("date");
        IMAGE =  intent.getStringExtra("image");
        DESCRIPTION =  intent.getStringExtra("description");

        setData();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY<=50){
                        nav_title.setText("Read Articles");
                    }
                    if (scrollY>=40){
                        nav_title.setText(TITLE);
                    }
                }
            });
        }
    }

    private void setData() {
        title.setText(TITLE);
        date.setText(DATE);
        description.setText(Html.fromHtml(DESCRIPTION));
        Glide.with(this).load(IMAGE).placeholder(R.drawable.house_png_same).into(articleImage);
    }
}