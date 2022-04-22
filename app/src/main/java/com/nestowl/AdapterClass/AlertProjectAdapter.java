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

import com.nestowl.model.ProjectDetailsModal;
import com.nestowl.brokerapp.PartnerRequestThird;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewContact;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class AlertProjectAdapter extends RecyclerView.Adapter<AlertProjectAdapter.ViewHolder> {
    Context context;
    ArrayList<ProjectDetailsModal> data;

    public AlertProjectAdapter(Context context, ArrayList<ProjectDetailsModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AlertProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_projects,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertProjectAdapter.ViewHolder holder, int position) {
        ProjectDetailsModal info =  data.get(position);
        holder.name.setText(info.getProject_name());
        holder.date.setText(getDateInFormat(info.getUpdated_at()));
        holder.status.setText(info.getInappStatus());
        holder.username.setText(info.getCompany_name());
        holder.type.setText(info.getProperty_type());
        if (info.getInappStatus().equals("RECEIVED")){
            holder.intersetd.setVisibility(View.VISIBLE);
        }
        holder.name.setSelected(true);
        holder.viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentss = new Intent(context, ViewContact.class);
                intentss.putExtra("pid",info.getProject_id());
                intentss.putExtra("user_id",info.getUser_id());
                intentss.putExtra("id",info.getId());
                context.startActivity(intentss);
            }
        });
        holder.seedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, PartnerRequestThird.class);
                intent.putExtra("address",info.getProject_address());
                intent.putExtra("developer",info.getContact_person());
                intent.putExtra("type",info.getProperty_type());
                intent.putExtra("link",info.getInappUrl());
                intent.putExtra("conf",info.getProject_area()+"BHK");
                intent.putExtra("budget",info.getPossession_by());
                intent.putExtra("id",info.getProject_id());
                intent.putExtra("user_id",info.getUser_id());
                intent.putExtra("dp", UrlClass.BASE_URL+info.getProject_photo());
                intent.putExtra("name",info.getProject_name());
                intent.putExtra("sq",info.getProject_area());
                intent.putExtra("itemId",info.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,intersetd,date,status,username,type;
        FrameLayout viewContact,seedetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_NAME);
            intersetd=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_INTRERSTED);
            date=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_DATE);
            status=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_STATUS);
            username=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_NAME_USER);
            type=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_TYPE);
            viewContact=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_VIEW_CONTACT);
            seedetails=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_SEE_DETAILS);
        }

        @Override
        public void onClick(View v) {

        }
    } private String getDateInFormat(String updated_at) {
        String data  = null;
        data = updated_at.split("T")[0];
        return data;
    }

}
