package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DealSumbit extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView back, colender;
    EditText name,mob,email,date,extra;
    TextView projectName;
    CardView sumbit;
    String dateSelected,projectSelected;
    LinearLayout selectProject;
    User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_sumbit);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        };

        back=findViewById(R.id.DEALS_BACK);
        colender=findViewById(R.id.DEAL_CALENDER);
        name=findViewById(R.id.DEAL_NAME);
        mob=findViewById(R.id.DEAL_MOB_NO);
        email=findViewById(R.id.DEAL_EMAIL);
        date=findViewById(R.id.DEAL_CLOSED_DATE);
        extra=findViewById(R.id.DEAL_EXTRA);
        sumbit=findViewById(R.id.DEAL_SUMIT);
        projectName=findViewById(R.id.DEAL_SHOW_PROJECT);
        selectProject=findViewById(R.id.DEAL_PROJECT);

        user= new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        colender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        selectProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DealSumbit.this,ProjectSocietySearchClass.class),1);
            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText()==null){
                    name.setError("Enter clint name");
                    name.requestFocus();
                    return;
                }
                if (mob.getText()==null){
                    mob.setError("Enter clint mobile no.");
                    mob.requestFocus();
                    return;
                }
                if (email.getText()==null){
                    email.setError("Enter clint email");
                    email.requestFocus();
                    return;
                }
                if (selectProject==null){
                    new WarningDio(DealSumbit.this, "Project not selected please select a project", "SELECT PROJECT", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            startActivityForResult(new Intent(DealSumbit.this,ProjectSocietySearchClass.class),1);
                        }
                    },false);
                    return;
                }
                if (date.getText()==null){
                    date.setError("Enter deal closed date");
                    date.requestFocus();
                    return;
                }
                sumbitFarm();
            }
        });

    }

    private void sumbitFarm() {
        ProgressDialog pd  = new ProgressDialog(this);
        pd.setMessage("Sumbiting...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.CLINT_DETAILS_SUMBIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        startActivity(new Intent(DealSumbit.this,ClintSumbitCongo.class));
                        finish();
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("mobile",mob.getText().toString());
                hashMap.put("project",projectSelected);
                hashMap.put("dealclosedate",dateSelected);
                hashMap.put("comments",extra.getText().toString());

                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void showDatePicker() {
        DatePickerDialog datePickerDialog =  new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateSelected =  String.valueOf(dayOfMonth)+"/"+String.valueOf(month+1)+"/"+year;
        date.setText(dateSelected);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==1){
                projectSelected= data.getStringExtra("dataSocity");
                projectName.setText(projectSelected);
                projectName.setTextColor(Color.parseColor("#000000"));
            }
        }

    }
}