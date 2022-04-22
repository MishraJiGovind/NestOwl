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

public class SavedSearchListing extends AppCompatActivity {
    CardView card_sale,card_two,card_save;
    CardView card1,card2,card3,card4;
    ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_search_listing);
        card_save=findViewById(R.id.card_save_listing);
        card1=findViewById(R.id.card_firsts);
        back_img=findViewById(R.id.arshi9);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        card2=findViewById(R.id.cards_second);
        card3=findViewById(R.id.cards_three);
        card4=findViewById(R.id.cards_fours);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SavedSearchListing.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SavedSearchListing.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SavedSearchListing.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SavedSearchListing.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedSearchListing.this, ScreenSixteenDetailsPage.class);
                startActivity(intent);
            }
        });

        card_sale = findViewById(R.id.CUSTOM_MY_PRO_CARD);
        card_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedSearchListing.this, ScreenSixteenDetailsPage.class);
                startActivity(intent);
            }
        });

        card_two=findViewById(R.id.card_sale_two);
        card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedSearchListing.this, ScreenSixteenDetailsPage.class);
                startActivity(intent);
            }
        });
    }
}
