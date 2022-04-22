package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.DropdownPOJO;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class DropdownAdapter extends RecyclerView.Adapter<DropdownAdapter.DropdownHolder> {
     Context context;
     ArrayList<DropdownPOJO> dropdownPOJOS;
     OnItemSelected itemSelected;

    public DropdownAdapter(Context context, ArrayList<DropdownPOJO> dropdownPOJOS, OnItemSelected itemSelected) {
        this.context = context;
        this.dropdownPOJOS = dropdownPOJOS;
        this.itemSelected = itemSelected;
        isFirstTime=true;
    }
    public interface  OnItemSelected{
         void onSelected(int position,DropdownPOJO dropdownPOJO);
     }
    @NonNull
    @Override
    public DropdownHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.inflate_dropdown_list,parent,false);
        return new DropdownHolder(view);
    }
    int selectedPosition=0;
    Boolean isFirstTime=true;


    @Override
    public void onBindViewHolder(@NonNull DropdownHolder holder, final int position) {
        if (dropdownPOJOS.get(position).state_code!=null) {
            holder.tv_name.setText(dropdownPOJOS.get(position).value + "(" + dropdownPOJOS.get(position).state_code + ")");
        }else {
            holder.tv_name.setText(dropdownPOJOS.get(position).value);
        }
        if (!isFirstTime && selectedPosition==position){
            holder.iv_selected.setVisibility(View.VISIBLE);
            holder.iv_unselected.setVisibility(View.GONE);
        }else {
            holder.iv_selected.setVisibility(View.GONE);
            holder.iv_unselected.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFirstTime=false;
                selectedPosition=position;
                itemSelected.onSelected(position,dropdownPOJOS.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dropdownPOJOS.size();
    }

    public class DropdownHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_selected;
        ImageView iv_unselected;

        public DropdownHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.HOME_NAME);
            iv_selected=itemView.findViewById(R.id.iv_selected);
            iv_unselected=itemView.findViewById(R.id.iv_unselected);

        }
    }
}
