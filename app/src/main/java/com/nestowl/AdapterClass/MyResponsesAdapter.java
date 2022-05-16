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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.brokerapp.BuyerDetails;
import com.nestowl.model.MyresponeViewModal;
import com.nestowl.brokerapp.Chating;
import com.nestowl.brokerapp.FrontViewQuerySecond;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ViewContact;
import com.nestowl.utils.PrefMananger;

import java.util.ArrayList;

public class MyResponsesAdapter extends RecyclerView.Adapter<MyResponsesAdapter.ViewHolder> {
    Context context;
    ArrayList<MyresponeViewModal> data;
    String buy = "is interested to buy your ";
    String offer = "has started a offer for your property ";
    String test;
   


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
        boolean isWithProposal = false,isContact = false,isIntrested = false,isReq = false;
        holder.name.setText(info.getName());
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

        if (info.getType().contains("interested")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            holder.interseted.setText(offer+info.getBhk());
            textView.setText("Interested Property");
            holder.chat.setVisibility(View.GONE);
            isWithProposal=false;
            isContact=false;
            isIntrested=true;
            PrefMananger.saveString(context,"response/key","i");
            Log.e("debug error", "onClick: in" );
        }
        if (info.getType().contains("proposal")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            holder.interseted.setText(buy+info.getBhk());
            textView.setText("View Proposal");
            isWithProposal=true;
            isContact=false;
            isIntrested=false;
            PrefMananger.saveString(context,"response/key","p");
            Log.e("debug error", "onClick: pro" );
        }
        if (info.getType().contains("contact")){
            TextView textView = (TextView) holder.viewProposal.getChildAt(0);
            holder.interseted.setText(buy+info.getBhk());
            textView.setText("View Contact");
            isWithProposal=false;
            isContact=true;
            isIntrested=false;
            PrefMananger.saveString(context,"response/key","c");
            Log.e("debug error", "onClick: con" );
        }


        holder.viewProposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getType().equals("proposal")){
                    Intent intents = new Intent(context, FrontViewQuerySecond.class);
                    intents.putExtra("user_id",info.getUser_id());
                    intents.putExtra("id",info.getProperty_id());
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
                    intentd.putExtra("isBroker",false);
                    context.startActivity(intentd);
                }
            }
        });
        boolean finalIsContact = isContact;
        boolean finalIsProposal = isWithProposal;
        boolean finalIsIntrested = isIntrested;
        boolean finalIsReq = isReq;
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("debug error", "onClick: "+finalIsProposal+ finalIsContact +finalIsReq+finalIsIntrested );
                Intent intent = new Intent(context, BuyerDetails.class);
                intent.putExtra("property_id",info.getProperty_id());
                intent.putExtra("user_id",info.getUser_id());
                intent.putExtra("isWith",finalIsProposal);
                intent.putExtra("isContact", finalIsContact);
                intent.putExtra("isInterested",finalIsIntrested);
                intent.putExtra("isReq",finalIsReq);
                context.startActivity(intent);
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
