package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.LocalitiesModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.R;
import com.nestowl.viewmodal.LiveCommnication;

import java.util.ArrayList;

public class PincodeAdapter extends RecyclerView.Adapter<PincodeAdapter.ViewHolder> {
    Context context;
    ArrayList<LocalitiesModal> data;
    LiveCommnication liveCommnication;
    String pincode;


    public PincodeAdapter(Context context, ArrayList<LocalitiesModal> data) {
        this.context = context;
        this.data = data;
        pincode=null;
        liveCommnication = ViewModelProviders.of((FragmentActivity) context).get(LiveCommnication.class);
    }

    @NonNull
    @Override
    public PincodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_pincode,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PincodeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setVisibility(View.GONE);
        holder.pincode.setVisibility(View.GONE);
        holder.input.setVisibility(View.GONE);
        holder.area.setVisibility(View.GONE);
        holder.saveArea.setVisibility(View.GONE);
        LocalitiesModal info=data.get(position);
        if (info.getLocalities()==null){
            holder.area.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.GONE);
        }else {
        holder.name.setText(info.getLocalities());
        }
        holder.name.setSelected(true);
        if (info.getPincode()==null){
            holder.input.setVisibility(View.VISIBLE);
            holder.input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().length()==6){
                        holder.imageView.setVisibility(View.VISIBLE);
                        pincode=s.toString();
                    }
                }
            });
        }else {
            holder.pincode.setVisibility(View.VISIBLE);
            holder.pincode.setText(info.getPincode());
            pincode=info.getPincode();
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aichat aichat =  new aichat();
                if (pincode!=null){
                    aichat.setText("p");
                    aichat.setValue(pincode);
                    aichat.setValues(String.valueOf(position));
                    liveCommnication.setText(aichat);
                }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aichat aichat = new aichat();
                aichat.setText("pr");
                aichat.setValue(String.valueOf(position));
                liveCommnication.setText(aichat);
            }
        });
        holder.area.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0){
                    holder.saveArea.setVisibility(View.VISIBLE);
                }else {
                    holder.saveArea.setVisibility(View.GONE);

                }
            }
        });
        holder.saveArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aichat aichat = new aichat();
                aichat.setText("pt");
                aichat.setValue(holder.area.getText().toString());
                aichat.setValues(String.valueOf(position));
                liveCommnication.setText(aichat);
            }
        });

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,pincode,remove,saveArea,imageView;
        EditText input,area;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_PINCODE_ADDRESS);
            pincode=itemView.findViewById(R.id.CUSTOM_PINCODE_SHOW);
            input=itemView.findViewById(R.id.CUSTOM_PINCODE_IN);
            imageView=itemView.findViewById(R.id.CUSTOM_PINCODE_ADD);
            saveArea=itemView.findViewById(R.id.CUSTOM_PINCODE_ADD2);
            remove=itemView.findViewById(R.id.CUSTOM_PINCODE_REMOVE);
            area=itemView.findViewById(R.id.CUSTOM_PINCODE_INPUT_ADD);
        }
        @Override
        public void onClick(View v) {

        }
    }
}
