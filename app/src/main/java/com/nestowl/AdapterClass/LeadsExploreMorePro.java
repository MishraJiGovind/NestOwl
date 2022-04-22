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

public class LeadsExploreMorePro extends RecyclerView.Adapter<LeadsExploreMorePro.ViewHolder> {
    Context context;
    ArrayList<com.nestowl.model.LeadsExploreMorePro> data;

    public LeadsExploreMorePro(Context context, ArrayList<com.nestowl.model.LeadsExploreMorePro> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public LeadsExploreMorePro.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_leads_explore_property,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeadsExploreMorePro.ViewHolder holder, int position) {
        com.nestowl.model.LeadsExploreMorePro info = data.get(position);
        holder.para.setText(info.getPara());
        holder.head.setText(info.getHead());
        holder.para.setSelected(true);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView head,para;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.CUSTOM_LEADS_PRO_ECPLORE_HEAD);
            para=itemView.findViewById(R.id.CUSTOM_LEADS_PRO_ECPLORE_PARA);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
