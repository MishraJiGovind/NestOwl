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

public class ImagesShowsAdapter extends RecyclerView.Adapter<ImagesShowsAdapter.ViewHolder> {
    Context context;
    ArrayList<String> data;

    public ImagesShowsAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ImagesShowsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_images_shows,parent,false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesShowsAdapter.ViewHolder holder, int position) {
        String s = data.get(position);
        Glide.with(context).load(UrlClass.BASE_URL+s).placeholder(R.drawable.default_x_x).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.CUSTOM_IMAGES_SHOW_IMG);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
