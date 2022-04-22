package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nestowl.brokerapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {
    Context context;
    ArrayList<String> imges;

    public SliderAdapter(Context context, ArrayList<String> imges) {
        this.context = context;
        this.imges = imges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_slider_view,parent);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String s =  imges.get(position);
        Glide.with(context).load(s).placeholder(R.drawable.default_x_y).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return imges.size();
    }
    public class ViewHolder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.SLIDER_IMAGE_VIEW);
        }
    }
}
