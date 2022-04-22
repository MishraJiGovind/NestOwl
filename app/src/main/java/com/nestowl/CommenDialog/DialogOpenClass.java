package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class DialogOpenClass {

    public DialogOpenClass(Context context, OnItemClass onItemClass,ArrayList<FloorPojo> floorPojos) {
        this.context = context;
        this.itemClass = onItemClass;

        this.floorPojosl=floorPojos;
        system();
     //   setuplistData();

    }

    OnItemClass itemClass;

    ArrayList<FloorPojo> floorPojosl;

    private void setuplistData() {
        floorPojosl = new ArrayList<>();
        for (int i = 0; i < 103; i++) {
            FloorPojo floorPojo = new FloorPojo();
            floorPojo.is_selected = false;
            if (i == 0) {
                floorPojo.floor_string = "Lower Basement";
            } else if (i == 1) {
                floorPojo.floor_string = "Basement";
            } else if (i == 2) {
                floorPojo.floor_string = "Ground Floor";
            } else {
                floorPojo.floor_string = "Floor No " + (i - 2);
            }
            floorPojosl.add(floorPojo);
            Log.e("fadf",i+"");
        }
    }

    Context context;

    public interface OnItemClass {

        void onitemClick(String text);
    }

    RecyclerView rv_floor;
    CardView card_submit;

    public void system() {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.property_on_floor);
        card_submit = dialog.findViewById(R.id.card_submit);
        rv_floor = dialog.findViewById(R.id.floor_recycler_view);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));


        dialog.getWindow().setAttributes(lp);
        dialog.show();
        ImageView imageView=dialog.findViewById(R.id.ivr1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (floorPojosl==null){
            setuplistData();
        }
        Log.e("fsdf",floorPojosl.size()+" fs");
        rv_floor.setLayoutManager(new LinearLayoutManager(context));
        rv_floor.setAdapter(new PropertyAdapterClass());
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == null) {
                    Toast.makeText(context, "Please Select A Floor", Toast.LENGTH_SHORT).show();
                } else {
                    itemClass.onitemClick(floorPojosl.get(selectedPosition).floor_string);
              dialog.dismiss();
                }
            }
        });
    }

    public static Integer selectedPosition = null;

    public class PropertyAdapterClass extends RecyclerView.Adapter<PropertyAdapterClass.Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.inflate_floor_list, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, final int position) {
            holder.tv_floor.setText(floorPojosl.get(position).floor_string);
            if (selectedPosition != null && selectedPosition == position) {
                holder.rd_floor.setChecked(true);

            } else {
                holder.rd_floor.setChecked(false);
            }
            holder.main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.rd_floor.setChecked(true);
                }
            });
            holder.rd_floor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked) {
                            selectedPosition = position;
                            notifyDataSetChanged();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }

        @Override
        public int getItemCount() {
            return floorPojosl.size();
        }

        public class Holder extends RecyclerView.ViewHolder {
            TextView tv_floor;
            RadioButton rd_floor;
            LinearLayout main;

            public Holder(@NonNull View itemView) {
                super(itemView);
                setIsRecyclable(false);
                tv_floor = itemView.findViewById(R.id.lower_basement_text);
                rd_floor = itemView.findViewById(R.id.rd_similar);
                main=itemView.findViewById(R.id.INFLATE_FLOOR_MAIN);
            }
        }
    }

}
