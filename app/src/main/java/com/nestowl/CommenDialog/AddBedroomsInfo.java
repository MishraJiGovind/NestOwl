package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class AddBedroomsInfo {
    Context context;
    int totalRooms,curruntRooms;
    ArrayList<String> lenth,breath,hall;
    getData data;

    public AddBedroomsInfo(Context context, int totalRooms, getData getData) {
        this.context = context;
        this.totalRooms = totalRooms;
        this.data=getData;
        logic();
    }
    void logic(){
        final Dialog dio =  new Dialog(context);
        dio.setContentView(R.layout.custom_bedroms);
        TextView info = dio.findViewById(R.id.CUSTOM_BEDROOM_INFO);
        EditText len = dio.findViewById(R.id.CUSTOM_BEDROOM_L);
        EditText br = dio.findViewById(R.id.CUSTOM_BEDROOM_B);
        Button sumbit = dio.findViewById(R.id.CUSTOOM_BEDROOM_SUMBIT);
        CheckBox checkBox = dio.findViewById(R.id.CUSTOM_BEDROOM_CHEAK);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dio.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dio.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        dio.getWindow().setAttributes(lp);
        dio.show();
        lenth = new ArrayList<>();
        breath = new ArrayList<>();
        hall = new ArrayList<>();
        curruntRooms=1;
        String text = "Enter Details For Room ";
        String error = "Enter Details For Room is required ";
        info.setText(text+curruntRooms+"/"+totalRooms);
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (len.getText()==null|len.getText().length()<=0){
                    len.setError(error);
                    return;
                }
                if (br.getText()==null|br.getText().length()<=0){
                    br.setError(error);
                    return;
                }
                lenth.add(String.valueOf(len.getText()));
                breath.add(String.valueOf(br.getText()));
                if (checkBox.isChecked()){
                    hall.add("Yes");
                }else {
                    hall.add("No");
                }
               if (curruntRooms!=totalRooms){
                   curruntRooms++;
                   info.setText(text+curruntRooms+"/"+totalRooms);
                   len.setText("", null);
                   br.setText("", null);
                   len.setFocusable(true);
                   checkBox.setChecked(false);
               }else {
                   data.getrooms(lenth, breath, hall);
                   dio.dismiss();
               }
            }
        });
    }
    public interface getData{
        void getrooms(ArrayList<String> lenth,ArrayList<String>Br,ArrayList<String>hall);
    }
}
