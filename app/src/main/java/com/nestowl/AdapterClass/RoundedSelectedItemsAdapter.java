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

public class RoundedSelectedItemsAdapter extends RecyclerView.Adapter<RoundedSelectedItemsAdapter.ViewHolder> {
    Context context;
    ArrayList<String> data;

    public RoundedSelectedItemsAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RoundedSelectedItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_work_localities_diolog,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RoundedSelectedItemsAdapter.ViewHolder holder, int position) {
        String info = data.get(position);
        holder.textView.setText(info);
        holder.textView.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.CUSTOM_LOCALITIES_DIOLOG_TEXT);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
