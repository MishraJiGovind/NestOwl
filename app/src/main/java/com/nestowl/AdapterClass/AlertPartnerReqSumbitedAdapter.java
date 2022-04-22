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

import com.nestowl.brokerapp.R;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.PostRequirementFrontView;

import java.util.ArrayList;

public class AlertPartnerReqSumbitedAdapter extends RecyclerView.Adapter<AlertPartnerReqSumbitedAdapter.ViewHolder> {
    Context context;
    ArrayList<LeadsPublicPro> data;
    int key;

    public AlertPartnerReqSumbitedAdapter(Context context, ArrayList<LeadsPublicPro> data) {
        this.context = context;
        this.data = data;
        key=0;
    }

    @NonNull
    @Override
    public AlertPartnerReqSumbitedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_partner_sumbited,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlertPartnerReqSumbitedAdapter.ViewHolder holder, int position) {
        LeadsPublicPro info = data.get(position);
        if (info.getLooking()!=null){
            if (info.getLooking().equals("Sell")){
                holder.proposal.setText("View Property");
                holder.name.setText("Property Selling Details");
                key=1;
            }
        }
        holder.date.setText(getDateInFormat(info.getUpdated_at()));
        holder.bhk.setText(info.getArea()+"Bhk");
        holder.city.setText(info.getCity());
        holder.budget.setText(getBudgetInLakhs(info.getExpectedprice()));
        holder.sq.setText(info.getPlot_area()+info.getPlot_area_mezor());
        holder.views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (key==1){
                    Intent intent1 =  new Intent(context, InterestedPropertyThirdUser.class);
                    intent1.putExtra("id",info.getProperty_id());
                    intent1.putExtra("user_id",info.getUser_id());
                    intent1.putExtra("idd",info.getId());
                    intent1.putExtra("isBroker","true");
                    context.startActivity(intent1);
                }else {
                    Intent intent = new Intent(context, PostRequirementFrontView.class);
                    intent.putExtra("id",info.getProperty_id());
                    intent.putExtra("user_id",info.getUser_id());
                    intent.putExtra("idd",info.getId());
                    intent.putExtra("isBroker","true");
                    context.startActivity( intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,date,bhk,city,budget,sq,proposal;
        FrameLayout views;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_NAME);
            date=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_DATE);
            bhk=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_BHK);
            city=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_CITY);
            budget=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_BUDGET);
            sq=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_SQ);
            views=itemView.findViewById(R.id.CUSTOM_ALERT_PARTNER_S_VIEW_REQ);
            proposal=(TextView) views.getChildAt(0);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private String getDateInFormat(String updated_at) {
        String data  = null;
        data = updated_at.split("T")[0];
        return data;
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
}
