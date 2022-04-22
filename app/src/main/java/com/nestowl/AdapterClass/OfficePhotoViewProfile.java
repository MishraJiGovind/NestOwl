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

import java.util.ArrayList;

public class OfficePhotoViewProfile extends RecyclerView.Adapter<OfficePhotoViewProfile.ViewHolder> {
    Context context;
    ArrayList<String> data;

    public OfficePhotoViewProfile(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public OfficePhotoViewProfile.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.inflate_image_upload,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OfficePhotoViewProfile.ViewHolder holder, int position) {
        String info =  data.get(position);
        holder.remove.setVisibility(View.GONE);
        Glide.with(context).load(info).placeholder(R.drawable.default_x_x).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView,remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remove=itemView.findViewById(R.id.iv_remove_image);
            imageView=itemView.findViewById(R.id.iv_uploaded_image);
        }

        @Override
        public void onClick(View v) {


        }
    }
}
