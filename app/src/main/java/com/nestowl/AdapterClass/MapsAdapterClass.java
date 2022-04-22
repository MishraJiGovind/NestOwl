package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class MapsAdapterClass extends RecyclerView.Adapter<MapsAdapterClass.Holder> {
    ArrayList<String>firstlist;

    public MapsAdapterClass(ArrayList<String> firstlist, ArrayList<Integer> secondlist, Context context) {
        this.firstlist = firstlist;
        this.secondlist = secondlist;
        this.context = context;
    }

    ArrayList<Integer>secondlist;
    Context context;


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.maps_bottom_item_layout, parent, false);
Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.img_map.setImageResource(secondlist.get(position));
        holder.tv_map.setText(firstlist.get(position));




    }

    @Override
    public int getItemCount() {
        return firstlist.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_map;
        ImageView img_map;
        FrameLayout frm_map;
        public Holder(@NonNull View itemView) {
            super(itemView);

            tv_map=itemView.findViewById(R.id.tv_maps);
            img_map=itemView.findViewById(R.id.image_maps);
            frm_map=itemView.findViewById(R.id.frame_map_id);
        }
    }
}
