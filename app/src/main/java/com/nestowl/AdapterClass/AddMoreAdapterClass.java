package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.nestowl.brokerapp.R;
import com.nestowl.model.WhatsAppPojo;
import com.nestowl.model.aichat;
import com.nestowl.viewmodal.LiveCommnication;

import java.util.ArrayList;

public class AddMoreAdapterClass extends RecyclerView.Adapter<AddMoreAdapterClass.Holder> {
    Context context;
    ArrayList<WhatsAppPojo> data;
    LiveCommnication liveCommnication;

    public AddMoreAdapterClass(Context context, ArrayList<WhatsAppPojo> data) {
        this.context = context;
        this.data = data;
        liveCommnication = ViewModelProviders.of((FragmentActivity) context).get(LiveCommnication.class);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.add_more_recycler_view,parent,false);
        Holder holder= new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {
        WhatsAppPojo info = data.get(position);
        String whats = info.getWhatsapp().toLowerCase();
        if (whats.equals("yes")){
            holder.whatsapp.setChecked(true);
        }else {
            holder.whatsapp.setChecked(false);
        }
        holder.number.setText(info.getPhone());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aichat aichat =  new aichat();
                aichat.setText("r");
                aichat.setValue(String.valueOf(position));
                liveCommnication.setText(aichat);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        TextView number,delete;
        CheckBox whatsapp;
        public Holder(@NonNull View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.CUSTOM_OFFICE_CONTACT_NO);
            delete=itemView.findViewById(R.id.CUSTOM_OFFICE_CONTACT_DELET);
            whatsapp=itemView.findViewById(R.id.CUSTOM_OFFICE_CONTACT_WHATSAPP);

        }
    }
}