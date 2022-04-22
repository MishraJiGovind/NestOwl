package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.MyresponeViewModal;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.FrontViewQuerySecond;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewContact;

import java.util.ArrayList;

public class MyResponsesAdapter extends RecyclerView.Adapter<MyResponsesAdapter.ViewHolder> {
    Context context;
    ArrayList<MyresponeViewModal> data;


    public MyResponsesAdapter(Context context, ArrayList<MyresponeViewModal> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public MyResponsesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.cutom_myresponse,parent,false);
       ViewHolder viewHolder =  new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyResponsesAdapter.ViewHolder holder, int position) {
        MyresponeViewModal info =  data.get(position);
        holder.name.setText(info.getName());
        holder.interseted.setText("is interested to buy your "+info.getBhk());
        holder.bhk.setText(info.getBhk());
        holder.city.setText(info.getCity());
        holder.budget.setText(info.getBudget());
        holder.sq.setText(info.getSq()+" SQ.Ft.");
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
        if (info.getType().equals("proposal")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            textView.setText("View Proposal");
        }
        if (info.getType().equals("contact")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            textView.setText("View Contact");
        }
        if (info.getType().equals("interested")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            textView.setText("Interested Property");
            holder.chat.setVisibility(View.GONE);
        }
        holder.viewProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getType().equals("proposal")){
                    Intent intents = new Intent(context, FrontViewQuerySecond.class);
                    intents.putExtra("user_id","13");
                    intents.putExtra("id","NESTOWL202106030719");
                    context.startActivity(intents);
                }
                if (info.getType().equals("contact")){
                    Intent intentss = new Intent(context, ViewContact.class);
                    intentss.putExtra("pid",info.getProperty_id());
                    intentss.putExtra("user_id",info.getUser_id());
                    intentss.putExtra("id",info.getId());
                    context.startActivity(intentss);
                }
                if (info.getType().equals("interested")){
                    Intent intentd = new Intent(context, InterestedPropertyThirdUser.class);
                    intentd.putExtra("id",info.getProperty_id());
                    intentd.putExtra("user_id",info.getUser_id());
                    intentd.putExtra("idd",info.getId());
                    intentd.putExtra("isBroker","false");
                    context.startActivity(intentd);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,interseted,bhk,city,budget,sq;
        FrameLayout chat,viewProposal;
        ConstraintLayout main;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_NAME);
            interseted=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_INTRESTED);
            bhk=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_BHK);
            city=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_CITY);
            budget=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_BUDGET);
            sq=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_SQ);
            chat=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_CHAT);
            viewProposal=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_VIEW_PROPOSAL);
            main=itemView.findViewById(R.id.CUSTOM_MY_RESPONSE_MAIN);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
