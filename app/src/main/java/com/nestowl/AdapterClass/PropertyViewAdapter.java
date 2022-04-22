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

import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.MannagePropertyDialog;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.PopupMannageModal;
import com.nestowl.model.PropertyViewModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import java.util.ArrayList;

public class PropertyViewAdapter extends RecyclerView.Adapter<PropertyViewAdapter.ViewHolder> {
    Context context;
    ArrayList<PropertyViewModal> data;
    String ID;
    LoginPojo loginPojo;

    public PropertyViewAdapter(Context context, ArrayList<PropertyViewModal> data) {
        this.context = context;
        this.data = data;
        loginPojo = PrefMananger.GetLoginData(context);
    }

    @NonNull
    @Override
    public PropertyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_property_view, parent,false);
        ViewHolder holder =  new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewAdapter.ViewHolder holder, int position) {
        PropertyViewModal info =  data.get(position);
        holder.staus.setText(info.getStatus());
        holder.id.setText("ID:"+info.getProId());
        holder.price.setText("PRICE:"+info.getPrice());
        holder.rents.setText(info.getRentstatus());
        holder.address.setText(info.getAddress());
        Glide.with(context).load(UrlClass.BASE_URL +info.getImageUrl()).placeholder(R.drawable.default_x_x).into(holder.image);
        PopupMannageModal popupMannageModal = new PopupMannageModal();
        popupMannageModal.setId(info.getId());
        popupMannageModal.setPostedto(info.getStatus());
        popupMannageModal.setCatogory(info.getRentstatus());
        popupMannageModal.setStatus("N/A");
        holder.mannage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MannagePropertyDialog(context,String.valueOf(loginPojo.getUserId()),info.getId(),popupMannageModal).showPopup();
            }
        });
        holder.staus.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView staus,id,price,rents,address;
        FrameLayout mannage;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            staus=itemView.findViewById(R.id.CUSTOM_PRO_STATUS);
            id=itemView.findViewById(R.id.CUSTOM_PRO_ID);
            price=itemView.findViewById(R.id.CUSTOM_PRO_PRICE);
            rents=itemView.findViewById(R.id.CUSTOM_PRO_RENT_STATUS);
            address=itemView.findViewById(R.id.CUSTOM_PRO_ADDRESS);
            image=itemView.findViewById(R.id.CUSTOM_PRO_PROPERTY_IMAGE);
            mannage=itemView.findViewById(R.id.CUSTOM_PRO_MANNAGE);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
