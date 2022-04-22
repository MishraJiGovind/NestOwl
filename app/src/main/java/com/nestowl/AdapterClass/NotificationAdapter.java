package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.PostRequirementFrontView;
import com.nestowl.brokerapp.R;
import com.nestowl.model.NotificationModal;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewModal> {
    Context context;
    ArrayList<NotificationModal> data;
    String where;
    User user;

    public NotificationAdapter(Context context, ArrayList<NotificationModal> data) {
        this.context = context;
        this.data = data;
        user=new Gson().fromJson(PrefMananger.getString(context,"user").toString(),User.class);
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewModal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_ui,parent,false);
        ViewModal viewModal = new ViewModal(view);
        return viewModal;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewModal holder, @SuppressLint("RecyclerView") int position) {
        NotificationModal info =  data.get(position);
        where=null;
        if (info.getUri()!=null){
            holder.imageViewMain.setVisibility(View.VISIBLE);
            Glide.with(context).load(info.getUri()).placeholder(R.drawable.default_x_x).into(holder.imageView);
        }
//        holder.time.setText(info.getTime());
        holder.title.setText(info.getTitle());
        holder.body.setText(info.getBody());
        where=info.getWhere();
        if (!info.isUnread()){
            holder.title.setTextColor(context.getResources().getColor(R.color.lightGrey));
            holder.body.setTextColor(context.getResources().getColor(R.color.lightGrey));
        }

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marksAsRead(info);

            }
        });

    }

    private void marksAsRead(NotificationModal info) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.READ_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("Notification", "onClick: "+response );
                    if (info.getWhere().equals("requirement")){
                        Intent intent = new Intent(context, PostRequirementFrontView.class);
                        intent.putExtra("id",info.getPropertyId());
                        intent.putExtra("user_id",info.getUserId());
                        intent.putExtra("idd",info.getId());
                        intent.putExtra("isBroker",false);
                        context.startActivity(intent);
                        Log.e("Notification", "onClick: clicl req" );

                    }
                    if (info.getWhere().equals("property")){
                        Intent intent = new Intent(context, InterestedPropertyThirdUser.class);
                        intent.putExtra("id",info.getPropertyId());
                        intent.putExtra("user_id",info.getUserId());
                        intent.putExtra("idd",info.getId());
                        intent.putExtra("isBroker",false);
                        context.startActivity( intent);
                        Log.e("Notification", "onClick: clicl pro" );

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
                hashMap.put("id",info.getId());
                hashMap.put("readmsg","Yes");
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewModal extends RecyclerView.ViewHolder implements View.OnClickListener{

        ConstraintLayout main;
        TextView title,body,time;
        ImageView imageView;
        CardView imageViewMain;

        public ViewModal(@NonNull View itemView) {
            super(itemView);
            main=itemView.findViewById(R.id.NOTIFICATIONS_MAIN);
            title=itemView.findViewById(R.id.NOTIFICATIONS_TITLE);
            body=itemView.findViewById(R.id.NOTIFICATIONS_BODY);
            time=itemView.findViewById(R.id.NOTIFICATIONS_TIME);
            imageView=itemView.findViewById(R.id.NOTIFICATIONS_IMAGE);
            imageViewMain=itemView.findViewById(R.id.NOTIFICATIONS_IMAGE_MAIN);
        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface MarksAsRead{
        void marksAsRead(int postion);
    }
}
