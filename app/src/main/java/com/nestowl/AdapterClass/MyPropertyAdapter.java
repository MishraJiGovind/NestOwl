package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.CommenDialog.MannagePropertyDialog;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.PopupMannageModal;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.ViewHolder> {
    Context context;
    ArrayList<LeadsPublicPro> data;
    int color =R.color.light_blue;
    SliderAdapter adapter;

    public MyPropertyAdapter(Context context, ArrayList<LeadsPublicPro> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyPropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_my_property,parent,false);
        ViewHolder viewHolder = new ViewHolder(view) {
            @Override
            public void onClick(View v) {

            }
        };
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertyAdapter.ViewHolder holder, int position) {

        LeadsPublicPro info = data.get(position);
        if (info.getIsUnsumbited()!=null){
            holder.feature.setVisibility(View.VISIBLE);
            holder.feature.setText(info.getIsUnsumbited());
        }else {
            holder.feature.setVisibility(View.GONE);
        }
        if (info.getProperypost()==null||info.getProperypost().equals("")){
            holder.posted.setText("N/A");
        }else {
            holder.posted.setText(info.getProperypost());
        }
        holder.locality.setSelected(true);
        holder.address.setSelected(true);

        holder.price.setText(info.getPropertytype()+","+info.getProperty());
        holder.address.setText(info.getLocality()+","+info.getCity());
        holder.locality.setText(info.getLocality());
        holder.ready.setText(info.getPossession());
        holder.resale.setText(info.getTransaction_type());
        holder.FT.setText(info.getPlot_area()+info.getPlot_area_mezor());
        holder.Owner.setText("Owner");
        holder.fev.setVisibility(View.GONE);

//        adapter =  new SliderAdapter(context,info.getImages());
//        holder.sliderView.setSliderAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        holder.sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        holder.sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
//        holder.sliderView.startAutoCycle();

        PopupMannageModal popupMannageModal = new PopupMannageModal();
        popupMannageModal.setId(info.getProperty_id());
        popupMannageModal.setPostedto(info.getProperypost());
        popupMannageModal.setCatogory(info.getPropertytype()+","+info.getProperty());
        if (info.getIsUnsumbited()!=null){
        popupMannageModal.setStatus(info.getIsUnsumbited());
        }else {
            if (info.getP_type()!=null){
                popupMannageModal.setStatus(info.getP_type());
            }else {
                popupMannageModal.setStatus("N/A");
            }
        }
        holder.mannage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MannagePropertyDialog(context,info.getUser_id(),info.getProperty_id(),popupMannageModal);
//                mannagePropertyDialog.showPopup();
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView feature,posted,price,address,locality,ready,resale,FT,Owner;
        CardView card;
        FrameLayout mannage,background;
        ImageView fev;
//        SliderView sliderView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feature=itemView.findViewById(R.id.CUSTOM_MY_PRO_FEATUERD);
            posted=itemView.findViewById(R.id.CUSTOM_MY_PRO_LIVE_TO);
            price=itemView.findViewById(R.id.CUSTOM_MY_PRO_PRICE);
            address=itemView.findViewById(R.id.CUSTOM_MY_PRO_ADDRESS);
            locality=itemView.findViewById(R.id.CUSTOM_MY_PRO_LOCALITY);
            ready=itemView.findViewById(R.id.CUSTOM_MY_PRO_READY);
            resale=itemView.findViewById(R.id.CUSTOM_MY_PRO_RESALE);
            FT=itemView.findViewById(R.id.CUSTOM_MY_PRO_SQ);
            Owner=itemView.findViewById(R.id.CUSTOM_MY_PRO_OWNER);
            card=itemView.findViewById(R.id.CUSTOM_MY_PRO_CARD);
            mannage=itemView.findViewById(R.id.CUSTOM_MY_PRO_MANNAGE);
            background=itemView.findViewById(R.id.CUSTOM_MY_PRO_IMG);
            fev=itemView.findViewById(R.id.CUSTOM_MY_PRO_FEV);
//            sliderView =itemView.findViewById(R.id.CUSTOM_MY_PRO_SLIDER);
        }
    }
}
