package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.DealCLosedModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class BrokerDealClosedAdapter extends RecyclerView.Adapter<BrokerDealClosedAdapter.ViewHolder> {
    Context context;
    ArrayList<DealCLosedModal> data;

    public BrokerDealClosedAdapter(Context context, ArrayList<DealCLosedModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BrokerDealClosedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_broker_closed_deals,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrokerDealClosedAdapter.ViewHolder holder, int position) {
        DealCLosedModal info = data.get(position);
        holder.name.setText(info.getProject_name());
        holder.address.setText(info.getAddress());
        String[] imgs = info.getProject_photo().split(",");
        Glide.with(context).load(UrlClass.BASE_URL+imgs[0]).placeholder(R.drawable.default_x_x).into(holder.img);
        holder.ratingBar.setRating(4);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView name,address;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.CUSTOM_BROKER_DEALS_IMG);
            name=itemView.findViewById(R.id.CUSTOM_BROKER_DEALS_TITLE);
            address=itemView.findViewById(R.id.CUSTOM_BROKER_DEALS_ADRESS);
            ratingBar=itemView.findViewById(R.id.CUSTOM_BROKER_DEALS_RATINGBAR);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
