package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nestowl.AdapterClass.ChatListAdapter;
import com.nestowl.model.ChatListModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatList extends AppCompatActivity {
    RecyclerView recyclerView;
    ChatListAdapter adapter;
    ArrayList<ChatListModal> data;
    ImageView back_image;
    ConstraintLayout thme;
    LoginPojo loginPojo;
    Context context;
    String useId;
    ArrayList<com.nestowl.model.chatmodals.ChatListModal> chatListModals;
    LinearLayout noChat;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        back_image = findViewById(R.id.ARTICLES_BACK);
        context=this;
        loginPojo= PrefMananger.GetLoginData(this);
        useId=String.valueOf(loginPojo.getUserId());
        noChat=findViewById(R.id.CHATING_LIST_NO_CHAT);
//        useId="2";
        chatListModals=new ArrayList<>();
        getChatList();
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

    }

    private void getChatList() {
        StringRequest requests =  new StringRequest(Request.Method.GET, UrlClass.GET_CHAT_LIST+useId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("chatlist", "onResponse: "+response);
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success){
                        noChat.setVisibility(View.GONE);
                        JSONObject jsonObject1 =  jsonObject.getJSONObject("data");
                        JSONArray jsonArray =  jsonObject1.getJSONArray("conversations");
                        chatListModals=new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject2 =  jsonArray.getJSONObject(i);
                            com.nestowl.model.chatmodals.ChatListModal  data =  new Gson().fromJson(jsonObject2.toString(), com.nestowl.model.chatmodals.ChatListModal.class);
                            chatListModals.add(data);
                        }
                        showInList();
                    }
                }catch (Exception e){
                    Log.d("chatlist", "onResponse: "+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("chatlist", "onResponse: "+error);
            }
        });
        Volley.newRequestQueue(context).add(requests);
//        Volley.newRequestQueue(this).add(requests);
    }
    private void showInList() {
        data = new ArrayList<>();
        for (com.nestowl.model.chatmodals.ChatListModal list :chatListModals){
            ChatListModal tmp = new ChatListModal();
            tmp.setDate("19/Oct/2021");
            tmp.setName(list.getUser().getName());
            tmp.setMassage(list.getMessage());
            tmp.setDpurl(list.getUser().getPhoto_url());
            if (list.getUser().getIs_online().equals("1")){
                tmp.setOnline(true);
            }else {
                tmp.setOnline(false);
            }
            if (!list.getUnread_count().equals("0")){
                tmp.setUnread(true);
                tmp.setUnreadCount(list.getUnread_count());
            }else {
                tmp.setUnread(false);
            }
            tmp.setUid(list.getUser_id());
            data.add(tmp);
        }
        showChatList(data);
    }
    private void showChatList(ArrayList<ChatListModal> data) {
        recyclerView =  findViewById(R.id.CHAT_LIST_VIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatList.this));
        adapter = new ChatListAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }
    private void setonline(int i) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.SET_CHAT_ONLINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("OnlineUpdae", "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("user_id",useId);
                hashMap.put("is_online",String.valueOf(i));
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        setonline(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setonline(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setonline(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setonline(1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setonline(1);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        setonline(0);
    }
}