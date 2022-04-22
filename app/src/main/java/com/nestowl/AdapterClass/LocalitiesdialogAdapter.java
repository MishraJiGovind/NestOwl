package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.LocalitiesDioModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LocalitiesdialogAdapter extends RecyclerView.Adapter<LocalitiesdialogAdapter.ViewHolder> {
    Context context;
    ArrayList<LocalitiesDioModal> data,extra;
    int act = R.drawable.selected_background_filter;

    public LocalitiesdialogAdapter(Context context, ArrayList<LocalitiesDioModal> data) {
        this.context = context;
        this.data = data;
        extra=new ArrayList<>();
    }

    @NonNull
    @Override
    public LocalitiesdialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_work_localities_diolog,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalitiesdialogAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LocalitiesDioModal s = data.get(position);
        holder.textView.setText(s.getName());
        if (s.getSelected().equals("true")){
            holder.frameLayout.setBackgroundResource(act);
        }
        holder.textView.setSelected(true);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.frameLayout.setBackgroundResource(act);
                s.setSelected("true");
                data.add(position,s);
                data.remove(position+1);
                String datas =  new Gson().toJson(data);
                PrefMananger.saveString(context,PrefMananger.ARREY_LOCALTIES,datas);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        FrameLayout frameLayout;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frameLayout=itemView.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_MAIN);
            textView=itemView.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_TEXT);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
