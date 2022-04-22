package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class OfficePhotosAdapter extends RecyclerView.Adapter<OfficePhotosAdapter.ViewHolder> {
    Context context;
    ArrayList<String>data ;
    boolean from;

    public OfficePhotosAdapter(Context context, ArrayList<String> data,boolean from) {
        this.context = context;
        this.data = data;
        this.from=from;
    }

    @NonNull
    @Override
    public OfficePhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_office_photo,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfficePhotosAdapter.ViewHolder holder, int position) {
        String info =  data.get(position);
            holder.imageView.setVisibility(View.GONE);
            holder.imageView2.setVisibility(View.GONE);
        if (from){
            holder.imageView2.setVisibility(View.VISIBLE);
            Glide.with(context).load(UrlClass.BASE_URL+info).placeholder(R.drawable.default_x_y).into(holder.imageView2);
        }else {
            holder.imageView.setVisibility(View.VISIBLE);
            Glide.with(context).load(UrlClass.BASE_URL+info).placeholder(R.drawable.default_x_x).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView,imageView2 ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.OFFICE_PHOTO_IMG);
            imageView2 = itemView.findViewById(R.id.OFFICE_PHOTO_IMG_2);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
