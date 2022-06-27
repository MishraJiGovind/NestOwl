package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nestowl.AdapterClass.ChattingAdapter;
import com.nestowl.CommenDialog.BottomChatingDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.ChatListModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.User;
import com.nestowl.model.chatmodals.ChatConversationModal;
import com.nestowl.model.chatmodals.ChatFileResponseModal;
import com.nestowl.model.chatmodals.ChatFileUploadResponseModal;
import com.nestowl.model.chatmodals.ChatUserModal;
import com.nestowl.utils.GetFileSizeByUrl;
import com.nestowl.utils.GetFiles;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;

public class Chating extends AppCompatActivity implements BottomChatingDio.clicks {
    TextView name,replyText;
    ImageButton share, send;
    ImageView dp, back,replayCancel;
    RecyclerView recyclerView;
    EditText massage;
    ArrayList<ChatListModal> data;
    ChattingAdapter adapter;
    String names, key, dplink,FROM,TO,URL,SIZE;
    ConstraintLayout thme;
    ArrayList<ChatConversationModal> conversationModal;
    ChatUserModal userModal;
    Context context;
    LoginPojo loginPojo;
    User user;
    GetFileSizeByUrl getDocsSize;
    LinearLayout replayView,noChat;
    boolean isReply,isImage;
    MultipartBody.Part[] imagesArrey;
    ArrayList<Uri> images;
    GetFiles files;
    ChatConversationModal replyModal;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);
        loginPojo= PrefMananger.GetLoginData(this);
        context=this;
        getDocsSize =  new GetFileSizeByUrl(context);
        images=new ArrayList<>();
        files=new GetFiles(context);
        isImage=false;
        user=new Gson().fromJson(PrefMananger.getString(context,"user").toString(),User.class);
        if (user!=null){
            if (user.getAvatar()==null){
                user.setAvatar("nodata");
            }
        }
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
        replayCancel=findViewById(R.id.CHATING_REPLY_CANCEL);
        replayView=findViewById(R.id.CHATING_REPLY_VIEW);
        replyText=findViewById(R.id.CHATING_REPLY_TXT);
        noChat=findViewById(R.id.CHATING_NO_CHAT);

        // geting intent data
        Intent intent =  getIntent();
        names = intent.getStringExtra("name");
        key = intent.getStringExtra("key");
        dplink = intent.getStringExtra("dp");
        TO=key;
        FROM= String.valueOf(loginPojo.getUserId());
        userModal=new ChatUserModal();
        userModal.setName(names);
        SIZE="NO";

//        nav init
        replayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReply=false;
                replayView.setVisibility(View.GONE);
            }
        });
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
        getAllChatsHistory();
        realtimehandler();

    }
    private void showMassages() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,true));
        adapter =  new ChattingAdapter(this, data, FROM);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper =  new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void realtimehandler(){
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               getAllChatsRealTime();
           }
       },1000);
    }
    private void sendMasageClick() {
        final int random = new Random().nextInt(999999999);
        if (isReply){
            StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.CHAT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("msg sent", "onResponse: "+response);
                    try {
                        JSONObject jsonObject= new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success){
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            massage.setText(null, null);
                            replayView.setVisibility(View.GONE);
                        }
                    }catch (Exception e){

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("from_id",FROM);
                    hashMap.put("to_id",TO);
                    hashMap.put("is_archive_chat","0");
                    hashMap.put("message",massage.getText().toString());
                    hashMap.put("is_my_contact","1");
                    hashMap.put("is_group","0");
                    hashMap.put("time",getTimeCrrunt());
                    hashMap.put("senderName",loginPojo.getFirstName());
                    hashMap.put("senderRole","Nestowl");
                    hashMap.put("senderImg",user.getAvatar());
                    hashMap.put("randomMsgId",String.valueOf(random));
//                    hashMap.put("replyMessage",replyModal.toString());
                    hashMap.put("receiverName",userModal.getName());
                    hashMap.put("reply_to",replyModal.getId());
                    return hashMap;
                }
            };
            Volley.newRequestQueue(context).add(request);
            return;
        }
        if (images.isEmpty()){
            StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.CHAT, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("msg sent", "onResponse: "+response);
                    try {
                        JSONObject jsonObject= new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success){
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            massage.setText(null, null);
                        }
                    }catch (Exception e){

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String>hashMap=new HashMap<>();
                    hashMap.put("from_id",FROM);
                    hashMap.put("to_id",TO);
                    hashMap.put("is_archive_chat","0");
                    hashMap.put("message",massage.getText().toString());
                    hashMap.put("is_my_contact","1");
                    hashMap.put("is_group","0");
                    hashMap.put("time",getTimeCrrunt());
                    hashMap.put("senderName",loginPojo.getFirstName());
                    hashMap.put("senderRole","Nestowl");
                    hashMap.put("senderImg",user.getAvatar());
                    hashMap.put("randomMsgId",String.valueOf(random));
                    hashMap.put("replyMessage","");
                    hashMap.put("receiverName",userModal.getName());
                    return hashMap;
                }
            };
            Volley.newRequestQueue(context).add(request);
        }
        else {
            ProgressDialog pd =  new ProgressDialog(context);
            pd.setCancelable(false);
            pd.setMessage("Uploading Files");
            pd.show();
            imagesArrey =  new MultipartBody.Part[images.size()];
            for (int i = 0; i < images.size(); i++) {
                if (isImage){
                    imagesArrey[i] = files.getPartBody("file[]",images.get(i));
                }else {
                    imagesArrey[i] = files.getPartBodyFile("file[]",images.get(i));
                }
            }
            ApiExecutor.getChatFileService().uploadChatsFile(imagesArrey).enqueue(new Callback<ChatFileUploadResponseModal>() {
                @Override
                public void onResponse(Call<ChatFileUploadResponseModal> call, retrofit2.Response<ChatFileUploadResponseModal> response) {
                    pd.dismiss();
                    if (response.isSuccessful()){
                        if (response.body().getSuccess()){
                            ArrayList<ChatFileResponseModal> urls = response.body().getData();
                            for (ChatFileResponseModal data : urls){
                                sendFileMassages(data.getAttachment(),data.getFile_name(),data.getMessage_type(),data.getUnique_code());
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<ChatFileUploadResponseModal> call, Throwable t) {
                    pd.dismiss();
                }
            });
        }

    }
    private void sendFileMassages(String attachment, String file_name, String message_type, String unique_code) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.CHAT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("msg sent", "onResponse: "+response);
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        massage.setText(null, null);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("from_id",FROM);
                hashMap.put("to_id",TO);
                hashMap.put("is_archive_chat","0");
                hashMap.put("message",attachment);
                hashMap.put("is_my_contact","1");
                hashMap.put("is_group","0");
                hashMap.put("time",getTimeCrrunt());
                hashMap.put("senderName",loginPojo.getFirstName());
                hashMap.put("senderRole","Nestowl");
                hashMap.put("senderImg",user.getAvatar());
                hashMap.put("randomMsgId",unique_code);
                hashMap.put("replyMessage","");
                hashMap.put("receiverName",userModal.getName());
                hashMap.put("file_name",file_name);
                hashMap.put("message_type",message_type);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    @Override
    public void onBottonClick(Integer id) {
        if (id == 2){
            //gallery click
            Intent intent =  new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,1);
        }
        if (id == 1){
            //docs click
            Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
            startActivityForResult(intent,2);
        }

    }
    private void  getAllChatsHistory(){
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Loading chats...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.CHAT_HISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CHATING ERROR", "onResponse: "+response);
                pd.dismiss();
               try {
                   JSONObject jsonObject= new JSONObject(response);
                   boolean succes = jsonObject.getBoolean("success");
                   if (succes){
                       noChat.setVisibility(View.GONE);
                       JSONObject main = jsonObject.getJSONObject("data");
                       userModal = new Gson().fromJson(main.getJSONObject("user").toString(),ChatUserModal.class);
                       name.setText(userModal.getName());
                       Glide.with(context).load(userModal.getPhoto_url()).placeholder(R.drawable.user_icon_p).into(dp);
                       JSONArray jsonArray =  main.getJSONArray("conversations");
                       Log.d("JSON ARREY", "onResponse: "+jsonArray);
                       conversationModal = new ArrayList<>();
                       for (int i = 0; i < jsonArray.length(); i++) {
                           ChatConversationModal data = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ChatConversationModal.class);
                           conversationModal.add(data);
                       }
                       convertTochatingList();
                   }
               }catch (Exception e){
                   Log.d("CHATING ERROR", "onResponse: "+e);
               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CHATING ERROR", "onResponse: "+error);
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("from_id",FROM);
                hashMap.put("to_id",TO);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void convertTochatingList() {
        data = new ArrayList<>();
        for (ChatConversationModal chats : conversationModal){
            ChatListModal chat = new ChatListModal();
            if (chats.getReply_message()!=null){
                chat.setMassage(chats.getMessage());
                chat.setUnread(false);
                chat.setOnline(true);
                chat.setImage(false);
                chat.setReply(true);
                chat.setReplayType(chats.getReply_message().getMessage_type());
                if (chats.getReply_message().getMessage_type().equals("0")){
                    chat.setReplayMassage(chats.getReply_message().getMessage());
                }else{
                    chat.setReplayMassage(chats.getReply_message().getFile_name());
                }
                chat.setUid(chats.getTo_id());
                chat.setType(chats.getMessage_type());
                chat.setSeen(chats.getStatus());
                chat.setFileSize(SIZE);
                chat.setFilename(chats.getFile_name());
                chat.setTime(getTimeInReadableFarm(chats.getCreated_at()));
            }else {
                chat.setMassage(chats.getMessage());
                chat.setUnread(false);
                chat.setOnline(true);
                chat.setImage(false);
                chat.setReply(false);
                chat.setUid(chats.getTo_id());
                chat.setType(chats.getMessage_type());
                chat.setSeen(chats.getStatus());
                chat.setFileSize(SIZE);
                chat.setFilename(chats.getFile_name());
                chat.setTime(getTimeInReadableFarm(chats.getCreated_at()));
            }

            data.add(chat);
        };
        showMassages();
    }
    private String getTimeInReadableFarm(String created_at) {
        String data = null;
        Calendar calendar =  Calendar.getInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date1 = format.parse(created_at);
            calendar.setTime(date1);
            String amPm = null;
            if (calendar.get(Calendar.AM_PM)==1){
                amPm="PM";
            }else {
                amPm="AM";
            }
            data = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" "+amPm;
        }catch (Exception e){

        }
        return data;
    }
    private String getTimeCrrunt() {
        String data = null;
        Calendar calendar =  Calendar.getInstance();
        try {
            Date date1 = new Date();
            calendar.setTime(date1);
            String amPm = null;
            if (calendar.get(Calendar.AM_PM)==1){
                amPm="PM";
            }else {
                amPm="AM";
            }
            data = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" "+amPm;
        }catch (Exception e){

        }
        return data;
    }
    private void  getAllChatsRealTime(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.CHAT_HISTORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("CHATING ERROR", "onResponse: "+response);

                try {
                    JSONObject jsonObject= new JSONObject(response);
                    boolean succes = jsonObject.getBoolean("success");
                    if (succes){
                        JSONObject main = jsonObject.getJSONObject("data");
                        JSONArray jsonArray =  main.getJSONArray("conversations");
                        Log.d("JSON ARREY", "onResponse: "+jsonArray);
                        conversationModal = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            ChatConversationModal data = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),ChatConversationModal.class);
                            conversationModal.add(data);
                        }
                        convertTochatingList2();
                        realtimehandler();
                    }
                }catch (Exception e){
                    Log.d("CHATING ERROR", "onResponse: "+e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CHATING ERROR", "onResponse: "+error);

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("from_id",FROM);
                hashMap.put("to_id",TO);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void convertTochatingList2() {
        data = new ArrayList<>();
        for (ChatConversationModal chats : conversationModal){
            ChatListModal chat = new ChatListModal();
            if (chats.getReply_message()!=null){
                chat.setMassage(chats.getMessage());
                chat.setUnread(false);
                chat.setOnline(true);
                chat.setImage(false);
                chat.setReply(true);
                chat.setReplayType(chats.getReply_message().getMessage_type());
                if (chats.getReply_message().getMessage_type().equals("0")){
                    chat.setReplayMassage(chats.getReply_message().getMessage());
                }else{
                    chat.setReplayMassage(chats.getReply_message().getFile_name());
                }
                chat.setUid(chats.getTo_id());
                chat.setType(chats.getMessage_type());
                chat.setSeen(chats.getStatus());
                chat.setFileSize(SIZE);
                chat.setFilename(chats.getFile_name());
                chat.setTime(getTimeInReadableFarm(chats.getCreated_at()));
            }else {
                chat.setMassage(chats.getMessage());
                chat.setUnread(false);
                chat.setOnline(true);
                chat.setImage(false);
                chat.setReply(false);
                chat.setUid(chats.getTo_id());
                chat.setType(chats.getMessage_type());
                chat.setSeen(chats.getStatus());
                chat.setFileSize(SIZE);
                chat.setFilename(chats.getFile_name());
                chat.setTime(getTimeInReadableFarm(chats.getCreated_at()));
            }

            data.add(chat);
        };
        Log.d("REALTIME", "convertTochatingList2: runing");
        showMassages();
//        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK && data!=null){
            if (requestCode==2){
                images=new ArrayList<>();
                isImage=true;
                if (data.getClipData()!=null){
                    int count =  data.getClipData().getItemCount();
                    for (int i = 0; i < count; i++) {
                        Uri selectedImage = data.getClipData().getItemAt(i).getUri();
                        images.add(selectedImage);
                    }
                    sendMasageClick();
                } else if (data.getData()!=null) {
                    images.add(data.getData());
                    sendMasageClick();
                }

            }
            if (requestCode==1){
                isImage=false;
                images=new ArrayList<>();
                images.add(data.getData());
                sendMasageClick();
            }
        }
    }
    ItemTouchHelper.SimpleCallback simpleCallback =  new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction==ItemTouchHelper.LEFT){
                    isReply=true;
                    replayView.setVisibility(View.VISIBLE);
                    ChatListModal chatListModal =  data.get(viewHolder.getAdapterPosition());
                    replyModal = conversationModal.get(viewHolder.getAdapterPosition());
                    if (chatListModal.getType().equals("0")){
                        replyText.setText(chatListModal.getMassage());
                    }
                    if (chatListModal.getType().equals("1")){
                        replyText.setText("IMAGE- "+chatListModal.getFilename());
                    }
                    if (chatListModal.getType().equals("2")){
                        replyText.setText("PDF- "+chatListModal.getFilename());
                    }
                    if (chatListModal.getType().equals("3")){
                        replyText.setText("DOC- "+chatListModal.getFilename());
                    }
                    if (chatListModal.getType().equals("4")){
                        replyText.setText("AUDIO- "+chatListModal.getFilename());
                    }
                    if (chatListModal.getType().equals("5")){
                        replyText.setText("VIDEO- "+chatListModal.getFilename());
                    }
                    adapter.notifyDataSetChanged();
                }
        }
    };
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
                hashMap.put("user_id",FROM);
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