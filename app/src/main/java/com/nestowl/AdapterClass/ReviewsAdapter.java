package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.ReviewsModal;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    Context context;
    ArrayList<ReviewsModal> data;

    public ReviewsAdapter(Context context, ArrayList<ReviewsModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profile_reviews_view,parent,false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.ViewHolder holder, int position) {
        ReviewsModal info =  data.get(position);
        Glide.with(context).load(info.getDp()).placeholder(R.drawable.default_x_x).into(holder.dp);
        holder.name.setText(info.getName());
        holder.msg.setText(info.getAbout_review());
        holder.type.setText(info.getTypes());
        holder.ratingBar.setRating(Float.parseFloat(info.getStar_review()));
        if (info.getTypes()==null){
            holder.mainType.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView dp;
        TextView name,msg,type;
        RatingBar ratingBar;
        FrameLayout mainType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dp=itemView.findViewById(R.id.REVIEWS_DP);
            name=itemView.findViewById(R.id.REVIEWS_NAME);
            msg=itemView.findViewById(R.id.REVIEWS_MSG);
            type=itemView.findViewById(R.id.REVIEWS_TYPE);
            mainType=itemView.findViewById(R.id.REVIEWS_TYPE_MAIN);
            ratingBar=itemView.findViewById(R.id.REVIEWS_STARS);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
