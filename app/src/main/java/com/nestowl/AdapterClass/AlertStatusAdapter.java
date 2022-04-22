package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.AlertStatusModal;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.VeiwDetailsOpenScreen;

import java.util.ArrayList;

public class AlertStatusAdapter extends RecyclerView.Adapter<AlertStatusAdapter.ViewHolder> {
    Context context;
    ArrayList<AlertStatusModal> data;
    int key ;
    String drect,start,make,seller,buyer,project;
    public AlertStatusAdapter(Context context, ArrayList<AlertStatusModal> data) {
        this.context = context;
        this.data = data;
        key=0;
        drect="has contacted you for ";
        start="has started a offer for ";
        make="has made an  offer for ";
        seller="has sent you a lead to sell his ";
        buyer="has sent a requirement for ";
        project="has enquired for ";
    }

    @NonNull
    @Override
    public AlertStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_status,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertStatusAdapter.ViewHolder holder, int position) {
        AlertStatusModal info =  data.get(position);
        String check = info.getViewType();
        holder.name.setText(info.getName());
        holder.ongonig.setText(info.getOngoingStatus());
        holder.owner.setText(info.getOwnerStatus());
        if (check.equals("Direct")){
            holder.line.setText(drect+info.getBhk()+"Bhk");
            key=1;
        }
        if (check.equals("Start")){
            key=2;
            holder.line.setText(start+info.getBhk()+"Bhk ");
        }
        if (check.equals("Make")){
            key=3;
            holder.line.setText(make+info.getBhk()+"Bhk ");
        }
        if (check.equals("Seller")){
            key=4;
            holder.line.setText(seller+info.getBhk()+"Bhk ");
        }
        if (check.equals("Buyer")){
            key=5;
            holder.line.setText(buyer+info.getBhk()+"Bhk ");
        }
        if (check.equals("Project")){
            key=6;
            holder.line.setText(project+info.getBhk()+"Bhk ");
        }
        if (info.getJob()!=null){
            holder.service.setVisibility(View.VISIBLE);
            TextView textView = (TextView) holder.service.getChildAt(0);
            textView.setText(info.getJob());
        }
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VeiwDetailsOpenScreen.class);
                intent.putExtra("name",info.getName());
                intent.putExtra("id",info.getId());
                intent.putExtra("user_id",info.getUser_id());
                intent.putExtra("property_id",info.getProperty_id());
                intent.putExtra("P_ID",info.getPropertyType());
                intent.putExtra("view",key);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,line,ongonig,owner;
        FrameLayout service,viewDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_NAME);
            line=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_LINE);
            ongonig=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_STATUS_TEXT);
            owner=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_OWNER_TEXT);
            service=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_JOB);
            viewDetails=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS_VIEW_DETAILS);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
