package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class SubscriptionHistoryAdapter extends RecyclerView.Adapter<SubscriptionHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<SubscriptionApiModal> data;

    public SubscriptionHistoryAdapter(Context context, ArrayList<SubscriptionApiModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SubscriptionHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_subscription,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionHistoryAdapter.ViewHolder holder, int position) {
        SubscriptionApiModal info = data.get(position);
        holder.plan.setText(info.getPrice());
        holder.validty.setText(info.getValidity());
        holder.mathod.setText("PayTm");
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.plan.setSelected(true);
        holder.validty.setSelected(true);
        holder.mathod.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView plan,validty,mathod;
        ConstraintLayout main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plan=itemView.findViewById(R.id.CUSTOM_SUBSCRIPTION_PLAN);
            validty=itemView.findViewById(R.id.CUSTOM_SUBSCRIPTION_VALIDITY);
            mathod=itemView.findViewById(R.id.CUSTOM_SUBSCRIPTION_MODE);
            main=itemView.findViewById(R.id.CUSTOM_SUBSCRIPTION_MAIN);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
