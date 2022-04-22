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
import android.widget.TextView;

import com.nestowl.model.SubscriptionRemainModal;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import org.json.JSONArray;

public class SelectPackageSecondPostProperty extends AppCompatActivity {
    ImageView back_img;
    FrameLayout frm_package,seconde,edit,four,five;
    TextView free,basic,basic_plus,featured,premiaum;
    int freeCount,basicCount,basicPlusCount,featuerdCount,premiumCount;
    String  json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_package_second_post_property);
        freeCount=0;
        basicCount=0;
        basicPlusCount=0;
        featuerdCount=0;
        premiumCount=0;
        freeCount=Integer.parseInt(PrefMananger.getString(this,"remain"));
        try {
            json = PrefMananger.getString(this,"remains");
            if (json!=null){
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    SubscriptionRemainModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionRemainModal.class);
                    if (data.getName().equals("10")){
                        basicCount++;
                    }
                    if (data.getName().equals("11")){
                        basicPlusCount++;
                    }
                    if (data.getName().equals("12")){
                        featuerdCount++;
                    }
                    if (data.getName().equals("13")){
                        premiumCount++;
                    }
                }
                    haddlerRemain();
            }
        }catch (Exception e){

        }
        back_img=findViewById(R.id.ARTICLES_BACK);
        four=findViewById(R.id.four_card_s);
        five=findViewById(R.id.five_card);
        free=findViewById(R.id.PACKAGE_REMAIN_FREE);
        basic=findViewById(R.id.PACKAGE_REMAIN_BASIC);
        basic_plus=findViewById(R.id.PACKAGE_REMAIN_BASIC_PLUS);
        featured=findViewById(R.id.PACKAGE_REMAIN_FEATURED);
        premiaum=findViewById(R.id.PACKAGE_REMAIN_PREMIUM);
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
        PrefMananger.saveString(this,"isfree","no");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (basicPlusCount>0){
                   Intent intent = new Intent(SelectPackageSecondPostProperty.this,PlanBasicActivity.class);
                   intent.putExtra("id","11");
                   startActivity(intent);
               }else {
                   Intent intent = new Intent(SelectPackageSecondPostProperty.this,SubscriptionKnowMore.class);
                   intent.putExtra("type","NO");
                   intent.putExtra("view","2");
                   intent.putExtra("name","Basic Plus");
                   startActivity(intent);
               }
            }
        });
        seconde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (basicCount>0){
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,PlanBasicActivity.class);
                    intent.putExtra("id","10");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,SubscriptionKnowMore.class);
                    intent.putExtra("type","NO");
                    intent.putExtra("view","2");
                    intent.putExtra("name","Basic");
                    startActivity(intent);
                }

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
                if (freeCount>0){
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,PlanBasicActivity.class);
                    intent.putExtra("id","0");
                    PrefMananger.saveString(SelectPackageSecondPostProperty.this,"isfree",null);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,SubscriptionKnowMore.class);
                    intent.putExtra("type","NO");
                    intent.putExtra("view","2");
                    intent.putExtra("name","Free");
                    startActivity(intent);
                }

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (featuerdCount>0){
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,PlanBasicActivity.class);
                    intent.putExtra("id","12");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,SubscriptionKnowMore.class);
                    intent.putExtra("type","NO");
                    intent.putExtra("view","2");
                    intent.putExtra("name","Featured");
                    startActivity(intent);
                }

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (premiumCount>0){
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,PlanBasicActivity.class);
                    intent.putExtra("id","13");
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SelectPackageSecondPostProperty.this,SubscriptionKnowMore.class);
                    intent.putExtra("type","NO");
                    intent.putExtra("view","2");
                    intent.putExtra("name","Premium");
                    startActivity(intent);
                }

            }
        });
        haddlerRemain();
    }

    private void haddlerRemain()  {
        free.setText(freeCount+" Remains");
        basic.setText(basicCount+" Remains");
        basic_plus.setText(basicPlusCount+" Remains");
        featured.setText(featuerdCount+" Remains");
        premiaum.setText(premiumCount+" Remains");
    }
}
