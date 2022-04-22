package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.brokerapp.R;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.LeadFirstCardOpen;
import com.nestowl.brokerapp.PostRequirementFrontView;
import com.nestowl.brokerapp.ViewContact;
import com.nestowl.utils.NumberToWords;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AlertPartnerRecivedAdapter extends RecyclerView.Adapter<AlertPartnerRecivedAdapter.ViewModal> {
    Context context;
    ArrayList<LeadsPublicPro> data;
    NumberToWords numberToWords;

    public AlertPartnerRecivedAdapter(Context context, ArrayList<LeadsPublicPro> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public ViewModal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_alert_partner_recived,parent,false);
        ViewModal viewModal = new ViewModal(view);
        return viewModal;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModal holder, int position) {
        LeadsPublicPro info = data.get(position);
        holder.name.setText(info.getName());
        holder.intersted.setText("is interested to buy "+info.getProperty()+" in "+info.getCity());
        holder.bhk.setText(info.getPlot_area()+"Bhk");
        holder.flat.setText(info.getPropertytype());
        holder.budget.setText(getBudgetInLakhs(info.getExpectedprice()));
        holder.sq.setText(info.getArea()+info.getArea_mezor());
       if (info.getLooking()!=null){
           if (info.getLooking().equals("Sell")){
               holder.proposal.setText("View Property");
           }
       }else {
           holder.viewProposal.setVisibility(View.GONE);
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
        holder.viewProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getLooking()!=null){
                    if (info.getLooking().equals("Sell")){
                        Intent intent1 =  new Intent(context, InterestedPropertyThirdUser.class);
                        intent1.putExtra("id",info.getProperty_id());
                        intent1.putExtra("user_id",info.getUser_id());
                        intent1.putExtra("idd",info.getId());
                        intent1.putExtra("isBroker",true);
                        context.startActivity(intent1);
                    }else {
                        Intent intent = new Intent(context, PostRequirementFrontView.class);
                        intent.putExtra("id",info.getProperty_id());
                        intent.putExtra("user_id",info.getUser_id());
                        intent.putExtra("idd",info.getId());
                        intent.putExtra("isBroker",true);
                        context.startActivity( intent);
                    }
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
                intent.putExtra("bhk", info.getPlot_area());
                intent.putExtra("ad", info.getAddress());
                intent.putExtra("budget", getBudgetInLakhs(info.getExpectedprice()));
                intent.putExtra("name", info.getName());
                intent.putExtra("dp", "");
                intent.putExtra("unlock", geUnclockTime(info.getUpdated_at()));
                intent.putExtra("doctor", "");
                intent.putExtra("doctor2", "");
                intent.putExtra("status", "NEST PROS");
                intent.putExtra("want", info.getLooking());
                intent.putExtra("location", info.getAddress());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewModal extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,intersted,bhk,flat,budget,sq,proposal;
        FrameLayout chat,viewContact,viewProposal;
        CardView main;
        public ViewModal(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_NAME);
            intersted=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_INTRESTED);
            bhk=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_BHK);
            flat=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_FLAT);
            budget=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_BUDGET);
            sq=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_SQ);
            chat=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_CHAT);
            viewContact=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_VIEW_CONTACT);
            viewProposal=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_VIEW_PROPSAL);
            main=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_R_MAIN);
            proposal=(TextView) viewProposal.getChildAt(0);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private String getBudgetInLakhs(String budget_max) {
        String data =  null;
        if (budget_max==null){
            return data;
        }
        int no = Integer.parseInt(budget_max);
        if (no>=1000000000){
            no=no/1000000000;
            data=no+"Arab";
            return data;
        }
        if (no>=10000000){
            no = no/10000000;
            data=no+"Crore";
            return data;
        }
        if (no>=100000){
            no = no/100000;
            data = no+"Lakh";
            return data;
        }
        if (no>=1000){
            no = no/1000;
            data = no+"K";
            return data;
        }
        if (no>999){
            data = String.valueOf(no);

        }
        return data;
    }

    private String geUnclockTime(String updated_at) {
        String data =  null;
        Calendar calendar =  Calendar.getInstance();
        String time =  updated_at.split("T")[1];
        String hour = time.split(":")[0];
        String min = time.split(":")[1];
        String secTemp = time.split(":")[2];
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date past = format.parse(updated_at);
            calendar.setTime(past);
            Date now = new Date();
            long letestHour = now.getHours();
            long letestSec = now.getMinutes();

            if (past.compareTo(now)>0){
                Log.e("TIME", "geUnclockTime: now is greter" );
                if (letestHour<=Long.getLong(hour)){
                    long t = letestHour-Integer.parseInt(hour);
                    data = String.valueOf(t);
                }else{
                    long t = Integer.parseInt(hour)-letestHour;
                    data = String.valueOf(t);
                }

            }
            if (past.compareTo(now)<0){
                Log.e("TIME", "geUnclockTime: now is less");
                data="0sec";
            }
            if (past.compareTo(now)==0){
                Log.e("TIME", "geUnclockTime: now is equl" );
                data="0sec";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
