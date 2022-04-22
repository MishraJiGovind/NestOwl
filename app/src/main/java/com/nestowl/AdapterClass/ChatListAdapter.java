package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.ChatListModal;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.R;
import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHoler> {
    Context context;
    ArrayList<ChatListModal> chats;
    Integer unread;

    public ChatListAdapter(Context context, ArrayList<ChatListModal> chats) {
        this.context = context;
        this.chats = chats;
    }


    @NonNull
    @Override
    public ChatListAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_chat_view, viewGroup, false);
        return new ChatListAdapter.ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatListAdapter.ViewHoler viewHoler, int i) {
        ChatListModal data = chats.get(i);
        if (data.getOnline()){
            viewHoler.online.setVisibility(View.VISIBLE);
        }
        if (data.getUnread()){
            viewHoler.unread.setVisibility(View.VISIBLE);
//            viewHoler.unread.setText(unread.toString());
            viewHoler.massage.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHoler.time.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHoler.username.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }else {
            viewHoler.unread.setVisibility(View.GONE);
        }
        viewHoler.username.setText(data.getName());
        viewHoler.massage.setText(data.getMassage());
        Glide.with(context).load(data.getDpurl()).placeholder(R.drawable.app_logo).into(viewHoler.dp);
        viewHoler.body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chating.class);
                intent.putExtra("key", data.getUid());
                intent.putExtra("name", data.getName());
                intent.putExtra("dp", data.getDpurl());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return chats.size();
    }
    public class ViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, massage , time , unread;
        ImageView dp , online;
        ConstraintLayout body;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.CHAT_LIST_USERNAME);
            massage = itemView.findViewById(R.id.CHAT_LIST_MASSAGE);
            time = itemView.findViewById(R.id.CHAT_LIST_DATE);
            unread = itemView.findViewById(R.id.CHAT_LIST_UNREAD);
            dp = itemView.findViewById(R.id.CHAT_LIST_DP);
            online = itemView.findViewById(R.id.CHAT_LIST_ONLINE);
            body = itemView.findViewById(R.id.CHAT_LIST_BODY);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
