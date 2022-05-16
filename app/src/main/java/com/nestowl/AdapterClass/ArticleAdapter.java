package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.ArticleModal;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.ReadArticles;
import com.nestowl.utils.PrefMananger;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    Context context;
    ArrayList<ArticleModal> data;

    public ArticleAdapter(Context context, ArrayList<ArticleModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_article_view, parent,false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        ArticleModal info =  data.get(position);
        holder.address.setText(info.getSlug());
        holder.title.setText(info.getTitle());
        String d = info.getShorts();
        holder.description.setText(d+"...");
        holder.date.setText(info.getPostdate());
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadArticles.class);
                intent.putExtra("title", info.getTitle());
                intent.putExtra("date", info.getPostdate());
                intent.putExtra("image", info.getImage());
                PrefMananger.saveString(context,"article/body",info.getBody());
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(info.getImage()).placeholder(R.drawable.default_x_y).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView address,title,description,date,readMore;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.CUSTOM_ARTICLE_ADDRES);
            title=itemView.findViewById(R.id.CUSTOM_ARTICLE_TITLE);
            description=itemView.findViewById(R.id.CUSTOM_ARTICLE_DESCRIPTION);
            date=itemView.findViewById(R.id.CUSTOM_ARTICLE_DATE);
            image=itemView.findViewById(R.id.CUSTOM_ARTICLE_IMAGE);
            readMore=itemView.findViewById(R.id.CUSTOM_ARTICLE_READMOre);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
