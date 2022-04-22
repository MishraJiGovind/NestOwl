package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.LeadsViewModal;
import com.nestowl.model.LoginPojo;
import com.nestowl.brokerapp.LeadFirstCardOpen;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class LeadsAdapter extends RecyclerView.Adapter<LeadsAdapter.ViewHolder> {
    Context context;
    ArrayList<LeadsViewModal> data;
    LoginPojo loginPojo;

    public LeadsAdapter(Context context, ArrayList<LeadsViewModal> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
        loginPojo = PrefMananger.GetLoginData(context);
    }

    @NonNull
    @Override
    public LeadsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_leads_broker_view, parent,false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeadsAdapter.ViewHolder holder, int position) {
        LeadsViewModal info = data.get(position);
        holder.AD.setVisibility(View.GONE);
        holder.PROPERTY.setVisibility(View.GONE);
        holder.BROKER.setVisibility(View.GONE);
        Log.e("Accepted", "onBindViewHolder: id:"+info.getPropertyId()+" "+info.getId()+" "+info.getAccepted() );
        if (info.getAd()){
            holder.AD.setVisibility(View.VISIBLE);
            holder.adName.setText("Hello "+loginPojo.getFirstName());
            if (info.getAdd1()!=null){
                holder.adT1.setText(info.getAdd1());
                holder.adT2.setText(info.getAdd2());
                holder.adT3.setText(info.getAdd3());
            }

        }
        if (info.getProperty()){
            holder.PROPERTY.setVisibility(View.VISIBLE);
            holder.propertyName.setText(info.getName());
            holder.propertyDate.setText(info.getDate());
            holder.propertyStatus.setText(info.getStatus());
            holder.propertyDoctor.setText(info.getDoctor());
            holder.propertySecendText.setText(info.getDoctor2());
            holder.propertyBhk.setText(info.getBhk());
            holder.propertyAddress.setText(info.getAddress());
            holder.propertyBudget.setText("Demand ₹"+info.getBudget());
            if (info.getDoctor()==null){
            holder.propertyDoctorFrame.setVisibility(View.GONE);
            }
            if (info.getDoctor2()==null){
            holder.propertySecondFrame.setVisibility(View.GONE);
            }
            Glide.with(context).load(UrlClass.BASE_URL+info.getPropertyImage()).placeholder(R.drawable.default_x_x).into(holder.propertyDP);
//            Glide.with(context).load(info.getImageurl()).placeholder(R.drawable.property_saven).into(holder.propertyDP);
            holder.propertySeeDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LeadFirstCardOpen.class);
                    intent.putExtra("key", info.getPropertyId());
                    intent.putExtra("id", info.getId());
                    intent.putExtra("user", info.getUserId());
                    intent.putExtra("bhk", info.getBhk());
                    intent.putExtra("ad", info.getAddress());
                    intent.putExtra("budget", info.getBudget());
                    intent.putExtra("name", info.getName());
                    intent.putExtra("dp", info.getImageurl());
                    intent.putExtra("unlock", info.getUnlock());
                    intent.putExtra("doctor", info.getDoctor());
                    intent.putExtra("doctor2", info.getDoctor2());
                    intent.putExtra("status", info.getStatus());
                    intent.putExtra("want", info.getWant());
                    intent.putExtra("location", info.getAddress());
                    intent.putExtra("isAccepted",info.getAccepted());
                    intent.putExtra("isSumbited",info.getProposalSummibted());
                    intent.putExtra("isProposalAccepted",info.getProposalAccepted());
                    context.startActivity(intent);
                }
            });
        }
        if (info.getUser()){
            holder.BROKER.setVisibility(View.VISIBLE);
            holder.berokerBHK.setText(info.getBhk());
            holder.brokerAdress.setText(info.getAddress());
            holder.brokerBudget.setText("Budget ₹"+info.getBudget());
            if (info.getUnlock().equals("Unlocking in 0sec")){
                holder.brokerUnlock.setText("");
            }else {
                holder.brokerUnlock.setText(info.getUnlock());
            }
            holder.brokerName.setText(info.getName());
            holder.brokerDate.setText(info.getDate());
            holder.brokerStatus.setText(info.getStatus());
            Glide.with(context).load(UrlClass.BASE_URL+info.getImageurl()).placeholder(R.drawable.user_icon_p).into(holder.brokerDp);
            if (info.getDoctor()==null){
            holder.brokerDoctorFrame.setVisibility(View.GONE);
            }
            holder.brokerDoctor.setText(info.getDoctor());
            holder.brokerSeeDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LeadFirstCardOpen.class);
                    intent.putExtra("key", info.getPropertyId());
                    intent.putExtra("id", info.getId());
                    intent.putExtra("user", info.getUserId());
                    intent.putExtra("bhk", info.getBhk());
                    intent.putExtra("ad", info.getAddress());
                    intent.putExtra("budget", info.getBudget());
                    intent.putExtra("name", info.getName());
                    intent.putExtra("dp", info.getImageurl());
                    intent.putExtra("unlock", info.getUnlock());
                    intent.putExtra("status", info.getStatus());
                    intent.putExtra("doctor2", info.getDoctor2());
                    intent.putExtra("doctor", info.getDoctor());
                    intent.putExtra("want", info.getWant());
                    intent.putExtra("location", info.getAddress());
                    intent.putExtra("isAccepted",info.getAccepted());
                    intent.putExtra("isSumbited",info.getProposalSummibted());
                    intent.putExtra("isProposalAccepted",info.getProposalAccepted());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView berokerBHK,brokerAdress,brokerBudget,brokerUnlock,brokerName,brokerDate,brokerStatus,brokerDoctor,
                propertyName,propertyDate,propertyStatus,propertyDoctor,propertySecendText,propertyBhk,propertyAddress,propertyBudget,
                adName,adT1,adT2,adT3;
        FrameLayout brokerSeeDetails,brokerDoctorFrame,propertyDoctorFrame,propertySecondFrame,propertySeeDetails,AD,PROPERTY,BROKER;
        ImageView brokerDp,propertyDP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            AD= itemView.findViewById(R.id.CUSTOM_LEADS_AD);
            PROPERTY= itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY);
            BROKER= itemView.findViewById(R.id.CUSTOM_LEADS_BROKER);

            berokerBHK=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_BHK);
            brokerAdress=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_ADDRESS);
            brokerBudget=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_BUDGET);
            brokerUnlock=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_UNLCK_IN);
            brokerName=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_NAME);
            brokerDate=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_DATE);
            brokerStatus=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_STATUS);
            brokerDoctor=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_DOCTOR_TEXT);
            brokerDp=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_DP);
            brokerSeeDetails=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_SEE_ALL);
            brokerDoctorFrame=itemView.findViewById(R.id.CUSTOM_LEADS_BROKER_DOCTOR_FRAME);

            propertyName=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_NAME);
            propertyDate=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_DATE);
            propertyStatus=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_STATUS);
            propertyDoctor=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_DOCTOR_TEXT);
            propertySecendText=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_SECEND_TEXT);
            propertyBhk=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_BHK);
            propertyAddress=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_ADDRESSS);
            propertyBudget=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_BUDGET);
            propertyDoctorFrame=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_DOCTOR_FRAME);
            propertySecondFrame=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_SECOND_FRAME);
            propertySeeDetails=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_SEE_DETAILS);
            propertyDP=itemView.findViewById(R.id.CUSTOM_LEDS_PROPERTY_DP);

            adName=itemView.findViewById(R.id.CUSTOM_LEADS_AD_NAME);
            adT1=itemView.findViewById(R.id.CUSTOM_LEADS_AD_T1);
            adT2=itemView.findViewById(R.id.CUSTOM_LEADS_AD_T2);
            adT3=itemView.findViewById(R.id.CUSTOM_LEADS_AD_T3);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
