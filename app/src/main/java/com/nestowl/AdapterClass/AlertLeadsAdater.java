package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.brokerapp.LeadFirstCardOpen;
import com.nestowl.brokerapp.R;
import com.nestowl.model.AlertLeadsViewModal;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.PostRequirementFrontView;
import com.nestowl.brokerapp.ViewContact;

import java.util.ArrayList;

public class AlertLeadsAdater extends RecyclerView.Adapter<AlertLeadsAdater.ViewHolder> {
    Context context;
    ArrayList<AlertLeadsViewModal> data;
    String seller,buyer,sell;
    int viewHandler = 0;
    int viewHandlers = 0;

    public AlertLeadsAdater(Context context, ArrayList<AlertLeadsViewModal> data) {
        this.context = context;
        this.data = data;
        buyer ="is interested to buy ";
        seller="is interested to sell his ";
    }

    @NonNull
    @Override
    public AlertLeadsAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_leads,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertLeadsAdater.ViewHolder holder, int position) {
        AlertLeadsViewModal info=data.get(position);

        holder.bhk.setSelected(true);
        holder.projectName.setSelected(true);
        holder.budget.setSelected(true);
        holder.SQ.setSelected(true);
        holder.verified.setSelected(true);
        holder.ViewProposal.setSelected(true);

        viewHandler=0;
        Log.e("Property Id", "onBindViewHolder: "+info.getProperty_id() );
        holder.name.setText(info.getName());
        holder.status.setText(info.getStatus());
        holder.bhk.setText(info.getBhk());
        holder.projectName.setText(info.getCity());
        holder.budget.setText(info.getBudget());
        holder.SQ.setText(info.getSq());
        if (info.isType()){
            holder.intrested.setText(seller+info.getBhk());
            holder.textView.setText("View Property");
            holder.viewContact.setVisibility(View.GONE);
            sell="sell";
            viewHandlers=1;
        }
        if (!info.isType()){
            holder.intrested.setText(buyer+info.getBhk());
            holder.textView.setText("View Requirement");
            holder.viewContact.setVisibility(View.GONE);
            sell="buy";
            viewHandler=2;
        }
        if (info.isDubbleLead()){
            holder.viewContact.setVisibility(View.VISIBLE);
            holder.dubbleLead.setVisibility(View.VISIBLE);
            if (info.isType()){
                holder.intrested.setText(seller+info.getBhk());
                holder.textView.setText("View Property");
                sell="sell";
                viewHandlers=1;
            }
            if (!info.isType()){
                holder.intrested.setText(buyer+info.getBhk());
                holder.textView.setText("View Requirement");
                sell="buy";
                viewHandler=2;
            }
        }
        if (info.isAccepted()){
            holder.intrested.setText(buyer+info.getBhk());
            holder.ViewProposal.setVisibility(View.GONE);
            holder.viewContact.setVisibility(View.VISIBLE);
        }
        if (info.getDoctor()!=null){
            holder.doctorFrame.setVisibility(View.VISIBLE);
            holder.doctor.setText(info.getDoctor());
        }
        if (info.getUnlocking()!=null){
            holder.unlocking.setVisibility(View.VISIBLE);
            holder.unlocking.setText(info.getUnlocking());
        }
        holder.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, Chating.class);
                intent.putExtra("id",info.getUser_id());
                intent.putExtra("name",info.getName());
                intent.putExtra("dp","n");
                context.startActivity(intent);
            }
        });
        holder.viewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentss = new Intent(context, ViewContact.class);
                intentss.putExtra("pid",info.getProperty_id());
                intentss.putExtra("user_id",info.getUser_id());
                intentss.putExtra("id",info.getId());
                context.startActivity(intentss);
            }
        });
        holder.ViewProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("viewHolder", "onClick: "+viewHandler );
                if (info.isType()){
                    Intent intent = new Intent(context, InterestedPropertyThirdUser.class);
                    intent.putExtra("id",info.getProperty_id());
                    intent.putExtra("user_id",info.getUser_id());
                    intent.putExtra("idd",info.getProjectname());
                    intent.putExtra("isBroker",false);
                    context.startActivity( intent);
                    return;
                }
                if (!info.isType()){
                    Intent intent = new Intent(context, PostRequirementFrontView.class);
                    intent.putExtra("id",info.getProperty_id());
                    intent.putExtra("user_id",info.getUser_id());
                    intent.putExtra("idd",info.getProjectname());
                    intent.putExtra("isBroker",false);
                    context.startActivity( intent);
                }
            }
        });
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LeadFirstCardOpen.class);
                intent.putExtra("key", info.getProperty_id());
                intent.putExtra("id", info.getId());
                intent.putExtra("user", info.getUser_id());
                intent.putExtra("bhk", info.getBhk());
                intent.putExtra("ad", info.getAddress());
                intent.putExtra("budget", info.getBudget());
                intent.putExtra("name", info.getName());
                intent.putExtra("dp", "");
                intent.putExtra("unlock", info.getUnlocking());
                intent.putExtra("doctor", info.getDoctor());
                intent.putExtra("doctor2", info.getDoctor());
                intent.putExtra("status", info.getStatus());
                intent.putExtra("want", sell);
                intent.putExtra("location", info.getAddress());
                intent.putExtra("isAccepted",info.isAccepted());
                intent.putExtra("isSumbited",info.isProposalSummibted());
                intent.putExtra("isProposalAccepted",info.isProposalAccepted());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,intrested,bhk,projectName,budget,SQ,doctor,status,textView,unlocking,verified;
        FrameLayout doctorFrame,chat,viewContact,ViewProposal;
        ConstraintLayout main;
        LinearLayout dubbleLead;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_ALERT_NAME);
            intrested=itemView.findViewById(R.id.CUSTOM_ALERT_INTRESTED);
            bhk=itemView.findViewById(R.id.CUSTOM_ALERT_BHK);
            projectName=itemView.findViewById(R.id.CUSTOM_ALERT_PROJECT_NAME);
            budget=itemView.findViewById(R.id.CUSTOM_ALERT_BUDGET);
            SQ=itemView.findViewById(R.id.CUSTOM_ALERT_SQ);
            status=itemView.findViewById(R.id.CUSTOM_ALERT_STATUS);
            textView=itemView.findViewById(R.id.CUSTOM_ALERT_EXTRA_TEXT);
            unlocking=itemView.findViewById(R.id.CUSTOM_ALERT_UNLOCKING);
            doctorFrame=itemView.findViewById(R.id.CUSTOM_ALERT_DOCTOR);
            chat=itemView.findViewById(R.id.CUSTOM_ALERT_CHAT);
            viewContact=itemView.findViewById(R.id.CUSTOM_ALERT_VIEW_CONTACT);
            ViewProposal=itemView.findViewById(R.id.CUSTOM_ALERT_VIEW_REQ);
            main=itemView.findViewById(R.id.CUSTOM_ALERT_MAIN);
            dubbleLead=itemView.findViewById(R.id.CUSTOM_ALERT_DUBBLE_LEAD);
            verified=itemView.findViewById(R.id.vrifiedbynest);
            doctor=(TextView) doctorFrame.getChildAt(0);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
