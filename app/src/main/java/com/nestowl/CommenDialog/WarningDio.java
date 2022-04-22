package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nestowl.brokerapp.R;

public class WarningDio {
    Context context;
    String WARNING,ACCEPTTEXT,CANCELTEXT;
    Response response;
    boolean wait,call;

    public WarningDio(Context context, String WARNING, String ACCEPTTEXT, String CANCELTEXT, Response response, boolean wait) {
        this.context = context;
        this.WARNING = WARNING;
        this.ACCEPTTEXT = ACCEPTTEXT;
        this.CANCELTEXT = CANCELTEXT;
        this.response = response;
        this.wait = wait;
        dio();
    }

    private void dio() {

        final Dialog dio =  new Dialog(context);
        dio.setContentView(R.layout.cutom_dio_warning);
        TextView warning,cancel,accept;
        ImageView close;
        warning= dio.findViewById(R.id.WARNIGS_TEXT);
        accept= dio.findViewById(R.id.WARNIGS_ACCEPT_TEXT);
        cancel= dio.findViewById(R.id.WARNIGS_CANCEL_TEXT);
        FrameLayout acceptf,rejectf;
        acceptf=dio.findViewById(R.id.WARNIGS_ACCEPT);
        rejectf=dio.findViewById(R.id.WARNIGS_CANCEL);
        close=dio.findViewById(R.id.WARNIGS_CANCEL_IMG);
        warning.setText(WARNING);
        if (CANCELTEXT!=null){
        cancel.setText(CANCELTEXT);
        }else {
            rejectf.setVisibility(View.GONE);
        }
        if (ACCEPTTEXT!=null){
        accept.setText(ACCEPTTEXT);
        }
        if (call){
            dio.dismiss();
            return;
        }
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dio.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dio.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        dio.getWindow().setAttributes(lp);
        dio.show();

        acceptf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getClicks(1);
                if (!wait){
                 dio.dismiss();
                }
            }
        });
        rejectf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getClicks(0);
                if (!wait){
                dio.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response.getClicks(0);
                if (!wait){
                    dio.dismiss();
                }
            }
        });

    }
    public interface Response{
        void getClicks(int click);
    }
    public void dissmised(){
        call = true;
        dio();
    }
}
