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
import com.nestowl.model.ProjectDetailsModal;
import com.nestowl.brokerapp.PartnerRequestThird;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewModal> {
    Context context;
    ArrayList<ProjectDetailsModal> data;

    public ProjectListAdapter(Context context, ArrayList<ProjectDetailsModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ProjectListAdapter.ViewModal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_project_onbord,parent,false);
        ViewModal viewModal =  new ViewModal(view);
        return viewModal;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListAdapter.ViewModal holder, int position) {
        ProjectDetailsModal info = data.get(position);
        holder.name.setText(info.getProject_name());
        holder.devlopername.setText(info.getContact_person());
        holder.type.setText(info.getProperty_type());
        if (info.getInappStatus()!=null){
        holder.status.setText(info.getInappStatus());
        }else {
            holder.statusFrame.setVisibility(View.GONE);
        }
        Glide.with(context).load(UrlClass.BASE_URL+info.getProject_photo()).placeholder(R.drawable.default_x_x).into(holder.propertyDp);
        holder.details.setOnClickListener(new View.OnClickListener() {
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
                intent.putExtra("dp",UrlClass.BASE_URL+info.getProject_photo());
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

    public class ViewModal extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,devlopername,type,status,budget;
        FrameLayout statusFrame,details;
        ImageView propertyDp;
        public ViewModal(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_NAME);
            devlopername=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_DEVLOPER_NAME);
            type=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_TYPE);
            status=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_STATUS_TEXT);
            budget=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_BUDGET);
            statusFrame=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_STATUS);
            details=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_SEE_DETAILS);
            propertyDp=itemView.findViewById(R.id.CUSTOM_PROJECT_ON_BOARD_IMG);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
