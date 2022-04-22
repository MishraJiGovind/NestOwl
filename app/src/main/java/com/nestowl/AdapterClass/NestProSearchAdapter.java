package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.nestowl.model.NestProViewModal;
import com.nestowl.brokerapp.BrokerSubmitProfileRequirement;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class NestProSearchAdapter extends RecyclerView.Adapter<NestProSearchAdapter.ViewHolder> {
    Context context;
    ArrayList<NestProViewModal> data;

    public NestProSearchAdapter(Context context, ArrayList<NestProViewModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NestProSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_nest_pro_search, parent,false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NestProSearchAdapter.ViewHolder holder, int position) {
        NestProViewModal modal =  data.get(position);
        holder.name.setText(modal.getName());
        holder.addres.setText(modal.getAddres());
        holder.moto.setText(modal.getMoto());
        holder.extratext.setText(modal.getExtratext());
        Glide.with(context).load(UrlClass.BASE_URL+modal.getDp()).into(holder.dp);
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, Chating.class);
                intent.putExtra("key", modal.getUserid());
                intent.putExtra("name", modal.getName());
                intent.putExtra("dp", UrlClass.BASE_URL+modal.getDp());
                context.startActivity(intent);
            }
        });
        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, BrokerSubmitProfileRequirement.class);
                intent.putExtra("key", modal.getUserid());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,addres,moto,extratext;
        FrameLayout chat,contact,profile;
        ImageView dp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_NAME);
            addres=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_ADDRESS);
            moto=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_MOTO);
            extratext=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_EXTRA_TEXT);
            chat=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_CHAT_CLICK);
            contact=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_VIEW_CONTACT);
            profile=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_VIEW_PROFILE);
            dp=itemView.findViewById(R.id.CUSTOM_NESTPRO_SEARCH_DP);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
