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

import com.nestowl.model.AmentiesListModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.SVG;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class AmentiesAdapter extends RecyclerView.Adapter<AmentiesAdapter.ViewHolder> {
    Context context;
    ArrayList<AmentiesListModal> data;

    public AmentiesAdapter(Context context, ArrayList<AmentiesListModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AmentiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_amenties_view_card,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AmentiesAdapter.ViewHolder holder, int position) {
        AmentiesListModal info =  data.get(position);
        holder.textView.setText(info.getText());
        if (info.getImage()!=null){
        SVG.fetchSvg(context,UrlClass.BASE_LOGO+info.getImage(),holder.imageView);
        }
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        FrameLayout card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.AMENTIES_IMG);
            textView=itemView.findViewById(R.id.AMENTIES_TXT);
            card=itemView.findViewById(R.id.AMENTIES_CARD);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
