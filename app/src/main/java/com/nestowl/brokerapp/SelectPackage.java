package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nestowl.utils.PrefMananger;

public class SelectPackage extends AppCompatActivity {
    ImageView back_img;
    FrameLayout frm_package,seconde,edit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_package);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        frm_package=findViewById(R.id.frame_select_package);
        seconde=findViewById(R.id.frame_premiumm);
        edit=findViewById(R.id.editing_open);
        PrefMananger.saveString(this,PrefMananger.RESIDENTIAL_KEY,"");
        PrefMananger.saveString(this,PrefMananger.COMMERICIAL_KEY,"");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPackage.this,PlanBasicActivity.class);
                startActivity(intent);
            }
        });
        seconde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPackage.this,PlanBasicActivity.class);
                startActivity(intent);
/*
                Dialog dialog = new Dialog(SelectPackage.this);
                dialog.setContentView(R.layout.switch_to_owner_mode);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SelectPackage.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();*/

            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        frm_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectPackage.this,PlanBasicActivity.class);
                startActivity(intent);
            }
        });
    }
}
