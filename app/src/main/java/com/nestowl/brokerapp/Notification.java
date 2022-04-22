package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nestowl.AdapterClass.NotificationAdapter;
import com.nestowl.model.NotificationExtraModal;
import com.nestowl.model.NotificationModal;
import com.nestowl.model.NotificationServerModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Notification extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    ImageView back;
    NotificationAdapter adapter;
    ArrayList<NotificationModal> notificationModals;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        recyclerView=findViewById(R.id.NOTIFICATIONS_RECYCLER);
        linearLayout=findViewById(R.id.NOTIFICATION_NO);
        back=findViewById(R.id.NOTIFICATIONS_BACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        notificationModals=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        if (PrefMananger.getString(this,"notification")!=null){
//            try {
//                NotificationExtraModal notificationExtraModal =  new Gson().fromJson(PrefMananger.getString(this,"notification").toString(),NotificationExtraModal.class);
//                notificationModals=notificationExtraModal.getData();
//                adapter.notifyDataSetChanged();
//                recyclerView.setVisibility(View.VISIBLE);
//                linearLayout.setVisibility(View.GONE);
//            }catch (Exception e){
//
//            }
//        }else {
//        }

        user=new Gson().fromJson(PrefMananger.getString(this,"user").toString(),User.class);
        fetchNotifications();
    }

    private void fetchNotifications() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("Notifications");
                        recyclerView.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        notificationModals=new ArrayList<>();
                        for (int i=0;i<jsonArray.length();i++){
                            NotificationServerModal notificationServerModal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),NotificationServerModal.class);
                            NotificationModal notificationModal =  new NotificationModal();
                            if (notificationServerModal.getReadmsg()!=null){
                                if (notificationServerModal.getReadmsg().equals("Yes")){
                                    notificationModal.setUnread(false);
                                }else {
                                    notificationModal.setUnread(true);
                                }
                            }
                            notificationModal.setTitle(notificationServerModal.getTitle());
                            notificationModal.setBody(notificationServerModal.getNotification());
                            notificationModal.setWhere(notificationServerModal.getType());
                            notificationModal.setId(notificationServerModal.getId());
                            notificationModal.setPropertyId(notificationServerModal.getPid());
                            notificationModal.setUserId(notificationServerModal.getUser_id());
                            notificationModal.setTime(notificationServerModal.getTodate());
                            notificationModals.add(notificationModal);
                            Log.e("Notification", "onResponse: "+notificationModal.getBody() );
                        }
                        adapter=new NotificationAdapter(Notification.this, notificationModals);
                        recyclerView.setAdapter(adapter);
//                        save();
//                        offilneLoad();
                    }
                }catch (Exception e){
                        offilneLoad();
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    Log.e("Notifications", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                        offilneLoad();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void offilneLoad() {
        if (PrefMananger.getString(this,"notification")!=null){
            try {
                NotificationExtraModal notificationExtraModal =  new Gson().fromJson(PrefMananger.getString(this,"notification").toString(),NotificationExtraModal.class);
                notificationModals=notificationExtraModal.getData();
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }catch (Exception e){

            }
        }else {
        }
    }

    private void save() {
        NotificationExtraModal modal =  new NotificationExtraModal();
        modal.setData(notificationModals);
        modal.setLastupdated(String.valueOf(new Date()));
        String data = new Gson().toJson(modal);
        PrefMananger.saveString(this,"notification",data);
        notificationModals=new ArrayList<>();
        if (PrefMananger.getString(this,"notification")!=null){
            NotificationExtraModal modals =  new Gson().fromJson(PrefMananger.getString(this,"notification").toString(),NotificationExtraModal.class);
            notificationModals=modals.getData();
            adapter.notifyDataSetChanged();
        }
    }

}