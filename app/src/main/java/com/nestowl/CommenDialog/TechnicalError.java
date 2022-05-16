package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nestowl.brokerapp.R;

public class TechnicalError {
    Context context;

    public TechnicalError(Context context) {
        this.context = context;
        dio();
    }

    private void dio(){
        final Dialog dio =  new Dialog(context);
        dio.setContentView(R.layout.technical_error);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dio.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dio.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        dio.getWindow().setAttributes(lp);
        dio.setCancelable(false);
        dio.show();
    }
}
