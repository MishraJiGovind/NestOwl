package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.model.LocalitiesViewModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.R;
import com.nestowl.viewmodal.LiveCommnication;

import java.util.ArrayList;

public class LocalitiesAdapter extends RecyclerView.Adapter<LocalitiesAdapter.ViewModal> {
    Context context;
    ArrayList<LocalitiesViewModal> data;
    LiveCommnication liveCommnication;

    public LocalitiesAdapter(Context context, ArrayList<LocalitiesViewModal> data) {
        this.context = context;
        this.data = data;
        liveCommnication = ViewModelProviders.of((FragmentActivity) context).get(LiveCommnication.class);
    }

    @NonNull
    @Override
    public LocalitiesAdapter.ViewModal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_localities_serach,parent,false);
        ViewModal viewModal = new ViewModal(view);
        return viewModal;
    }
    @Override
    public void onBindViewHolder(@NonNull LocalitiesAdapter.ViewModal holder, @SuppressLint("RecyclerView") int position) {
        LocalitiesViewModal info = data.get(position);
        holder.added.setVisibility(View.GONE);
        holder.delete.setVisibility(View.GONE);
        holder.name.setText(info.getName());
        if (info.isAdded()){
            holder.added.setVisibility(View.VISIBLE);
//            holder.delete.setVisibility(View.VISIBLE);
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                removeAtPoss(position);
            }
        });
       holder.main.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (info.isAdded()){

               }else {
                   addToArrey(position);
               }
           }
       });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewModal extends RecyclerView.ViewHolder implements View.OnClickListener{
        ConstraintLayout main;
        TextView name,added;
        ImageView delete;
        public ViewModal(@NonNull View itemView) {
            super(itemView);
            main=itemView.findViewById(R.id.CUSTOM_LOCALITIES_MAIN);
            name=itemView.findViewById(R.id.CUSTOM_LOCALITIES_NAME);
            added=itemView.findViewById(R.id.CUSTOM_LOCALITIES_ADDED);
            delete=itemView.findViewById(R.id.CUSTOM_LOCALITIES_REMOVE);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private void addToArrey(int position) {
        aichat aichat =  new aichat();
        aichat.setText("a");
        aichat.setValue(String.valueOf(position));
        liveCommnication.setText(aichat);
    }
//    private void removeAtPoss(int position) {
//        aichat aichat =  new aichat();
//        aichat.setText("r");
//        aichat.setValue(String.valueOf(position));
//        liveCommnication.setText(aichat);
//    }
}
