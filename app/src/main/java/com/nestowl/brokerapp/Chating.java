package com.nestowl.brokerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.ChattingAdapter;
import com.nestowl.CommenDialog.BottomChatingDio;
import com.nestowl.model.ChatListModal;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class Chating extends AppCompatActivity implements BottomChatingDio.clicks {
    TextView name;
    ImageButton share, send;
    ImageView dp, back;
    RecyclerView recyclerView;
    EditText massage;
    ArrayList<ChatListModal> data;
    ChattingAdapter adapter;
    String names, key, dplink;
    ConstraintLayout thme;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        try {
            thme = findViewById(R.id.theme_nav);
            thme.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.whit_button));

        }catch (Exception e){

        }

        name = findViewById(R.id.CHATING_NAME);
        share = findViewById(R.id.CHATING_SHARE);
        send = findViewById(R.id.CHATING_SEND);
        dp = findViewById(R.id.CHATING_DP);
        back = findViewById(R.id.CHATING_BACK);
        recyclerView = findViewById(R.id.CHATING_VIEW);
        massage = findViewById(R.id.CHATING_INPUT);

        // geting intent data
        Intent intent =  getIntent();
        names = intent.getStringExtra("name");
        key = intent.getStringExtra("key");
        dplink = intent.getStringExtra("dp");

//        nav init
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        name.setText(names);
        Glide.with(this).load(UrlClass.BASE_URL +dplink).placeholder(R.drawable.user_icon_p).into(dp);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (massage.getText() == null){
                    return;
                }
                if (massage.getText().length() == 0){
                    return;
                }
                sendMasageClick();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomChatingDio dio = new BottomChatingDio();
                dio.show(getSupportFragmentManager(), "bottomAction");
            }
        });
        data =  new ArrayList<>();
        showMassages();
    }

    private void showMassages() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new ChattingAdapter(this, data, key);
        recyclerView.setAdapter(adapter);
    }

    private void sendMasageClick() {
        ChatListModal chat = new ChatListModal();
        chat.setMassage(massage.getText().toString());
        chat.setUnread(false);
        chat.setOnline(true);
        chat.setImage(false);
        chat.setUid("xyz");


        data.add(chat);
        showMassages();
        massage.setText(null, null);
    }

    @Override
    public void onBottonClick(Integer id) {
        if (id == 1){
            //gallery click
            ChatListModal chat = new ChatListModal();
            chat.setMassage(massage.getText().toString());
            chat.setUnread(false);
            chat.setOnline(true);
            chat.setImage(true);
            chat.setImageUrl("https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__480.jpg");
            chat.setUid("xyz");


            data.add(chat);
            showMassages();
            massage.setText(null, null);

        }
        if (id == 2){
            //docs click
            ChatListModal chat = new ChatListModal();
            chat.setMassage(massage.getText().toString());
            chat.setUnread(false);
            chat.setOnline(true);
            chat.setImage(false);
            chat.setUid("xyz");


            data.add(chat);
            showMassages();

        }

    }
}