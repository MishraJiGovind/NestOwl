package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class AdapterClassProjectSociety extends RecyclerView.Adapter<AdapterClassProjectSociety.Holder> {
    ArrayList<String>firtlist;
    ItemClick itemClick;
    public AdapterClassProjectSociety(ArrayList<String> firtlist, Context context,ItemClick itemClick) {
        this.firtlist = firtlist;
        this.context = context;
        this.itemClick=itemClick;
    }
    public interface ItemClick{
        void onItemClick(int position);
    }
    Context context;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.project_society_recycler_view, parent, false);
        Holder holder= new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.tv_sector.setText(firtlist.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return firtlist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_sector;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_sector=itemView.findViewById(R.id.tvv_sector_56);
        }
    }
}
