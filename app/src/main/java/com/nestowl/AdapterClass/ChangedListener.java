package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.BudgetPOJO;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class ChangedListener extends RecyclerView.Adapter<ChangedListener.Holder> {

    Integer selectedposition = null;

    public ChangedListener(Context context, ArrayList<BudgetPOJO> firstlist, OnItemSeleted onItemSeleted) {
        this.context = context;
        this.firstlist = firstlist;
        itemSeleted = onItemSeleted;
    }

    Context context;
    ArrayList<BudgetPOJO> firstlist;
    OnItemSeleted itemSeleted;

    public interface OnItemSeleted {
        void onItemSeleted(int position);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layouot_color_chalange, parent, false);

        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {
        // holder.frame_changed.set(firstlist.get(position));
        holder.tv_changed.setText(firstlist.get(position).name);
        if (firstlist.get(position).isSelected) {
            holder.frame_changed.setBackgroundResource(R.drawable.selected_background_filter);
            holder.tv_changed.setTextColor(context.getResources().getColor(R.color.black_color));
        } else {
            holder.frame_changed.setBackgroundResource(R.drawable.ready_to_move_rounded_background);
            holder.tv_changed.setTextColor(context.getResources().getColor(R.color.black_color));


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (!firstlist.get(position).isSelected) {
                    holder.frame_changed.setBackgroundResource(R.drawable.selected_background_filter);
                    holder.tv_changed.setTextColor(context.getResources().getColor(R.color.brown_color));
                    firstlist.get(position).isSelected = true;
                } else {
                    holder.frame_changed.setBackgroundResource(R.drawable.ready_to_move_rounded_background);
                    holder.tv_changed.setTextColor(context.getResources().getColor(R.color.black_color));
                    firstlist.get(position).isSelected = false;
                }
                */
                itemSeleted.onItemSeleted(position);
               // notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return firstlist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        FrameLayout frame_changed;
        TextView tv_changed;

        public Holder(@NonNull View itemView) {
            super(itemView);

            frame_changed = itemView.findViewById(R.id.id_changed_view);
            tv_changed = itemView.findViewById(R.id.tv_changed);


        }
    }
}
