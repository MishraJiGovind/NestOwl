package com.nestowl.brokerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nestowl.AdapterClass.ChatListAdapter;
import com.nestowl.model.ChatListModal;

import java.util.ArrayList;

public class ChatList extends AppCompatActivity {
    RecyclerView recyclerView;
    ChatListAdapter adapter;
    ArrayList<ChatListModal> data;
    ImageView back_image;
    ConstraintLayout thme;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        back_image = findViewById(R.id.ARTICLES_BACK);
       try {
           thme = findViewById(R.id.theme_chat_list);
           thme.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
           Window window = this.getWindow();
           window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
           window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
           window.setStatusBarColor(getResources().getColor(R.color.whit_button));

       }catch (Exception e){

       }
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ChatListModal tmp = new ChatListModal();
        tmp.setDate("19/Oct/2021");
        tmp.setName("Rajesh Kumar");
        tmp.setMassage("Mujhe flat ke baare me discuss krna hai");
        tmp.setDpurl("https://i.pinimg.com/originals/1c/70/8e/1c708e7979f6d25d138bb253f46dfb69.jpg");
        tmp.setOnline(true);
        tmp.setUnread(true);
        tmp.setUid("xyz");

        ChatListModal dummy = new ChatListModal();
        dummy.setDate("18/Oct/2021");
        dummy.setName("Rakesh Pandey");
        dummy.setMassage("ok Deal final");
        dummy.setDpurl("https://i2.wp.com/sguru.org/wp-content/uploads/2017/06/profile_pictures_dp_for_whatsapp_facebook_602.jpg?resize=398%2C486&ssl=1");
        dummy.setOnline(false);
        dummy.setUnread(false);
        dummy.setUid("notdummyuser");

        data = new ArrayList<>();
        data.add(tmp);
        data.add(dummy);

        recyclerView =  findViewById(R.id.CHAT_LIST_VIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatList.this));
        adapter = new ChatListAdapter(this, data);
        recyclerView.setAdapter(adapter);

//        showChatList(data);
    }

    private void showChatList(ArrayList<ChatListModal> data) {
        recyclerView =  findViewById(R.id.CHAT_LIST_VIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatList.this));
        adapter = new ChatListAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
}