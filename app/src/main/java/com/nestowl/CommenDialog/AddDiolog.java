package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.nestowl.brokerapp.R;

public class AddDiolog  {
    Context contexts;
    onClickDio click;

    public AddDiolog(Context context, onClickDio click) {
        this.contexts = context;
        this.click = click;
        dio();
    }

    private void dio() {
        final Dialog dialog = new Dialog(contexts);
        dialog.setContentView(R.layout.custom_add_diolog);
        EditText getingText = dialog.findViewById(R.id.CUSTOM_ADD_DIOIN);
        Button succes = dialog.findViewById(R.id.CUSTOM_ADD_DIO_SUCCES);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(contexts, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        succes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getingText.getText()==null|getingText.getText().length()<=0){
                    getingText.setError("Add Value Here");
                    return;
                }
                click.getData(getingText.getText().toString());
                dialog.dismiss();
            }
        });

    }
    public interface onClickDio{
        void getData(String value);
    }

}
