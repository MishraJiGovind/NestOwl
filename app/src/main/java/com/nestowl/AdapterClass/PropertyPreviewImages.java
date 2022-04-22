package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.PropertyPreviewImagesModal;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class PropertyPreviewImages extends RecyclerView.Adapter<PropertyPreviewImages.ViewHolder> {
    Context context;
    ArrayList<PropertyPreviewImagesModal> data;

    public PropertyPreviewImages(Context context, ArrayList<PropertyPreviewImagesModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PropertyPreviewImages.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_property_images_preview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyPreviewImages.ViewHolder holder, int position) {
        PropertyPreviewImagesModal info =  data.get(position);
        Glide.with(context).load(info.getImageurl()).placeholder(R.drawable.default_x_y).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.IMAGES_VIEW_PROPETY);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
