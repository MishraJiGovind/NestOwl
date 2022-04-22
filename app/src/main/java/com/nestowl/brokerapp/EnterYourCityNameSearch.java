package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.AdapterClassProjectSociety;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnterYourCityNameSearch extends AppCompatActivity {
ImageView imageView;
    Context context;
    ArrayList<String > societyDataList=new ArrayList<>();
    RecyclerView recyclerView;
    EditText edt_select_locality;
    TextView tv_locality;
    LinearLayout layout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_your_city_name_search);
        context=this;
        layout=findViewById(R.id.SEARCH_CITY_ADD);
        String chesk = PrefMananger.getString(this,"nest");
        if (chesk!=null){
            layout.setVisibility(View.GONE);
            PrefMananger.saveString(this,"nest",null);
        }
        tv_locality=findViewById(R.id.locality_tv);
        edt_select_locality=findViewById(R.id.locality_or_project);
        recyclerView=findViewById(R.id.recycler_project_society);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        edt_select_locality.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0 && societyDataList.size()>0){
                    ArrayList<String> arrayList=new ArrayList<>();
                    for (String value : societyDataList){
                        if (value.toLowerCase().contains(s.toString().toLowerCase())){
                            arrayList.add(value);
                        }
                    }
                    setUpDataList(arrayList);
                }else {
                    setUpDataList(societyDataList);
                }
            }
        });
        tv_locality.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(EnterYourCityNameSearch.this);
                dialog.setContentView(R.layout.cityname_pop_up);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                TextView textView = dialog.findViewById(R.id.POST_REQ_DIO_ADRESS_NAME);
                EditText getFields = dialog.findViewById(R.id.POST_REQ_DIO_ADRESSES_IN);
                CardView sumbit = dialog.findViewById(R.id.POST_REQ_DIO_ADRESS_SUMBIT);
                sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getFields.getText() == null|getFields.getText().length() <=0){
                            getFields.setError("Data is Empty");
                            return;
                        }
                        Intent intent=new Intent();
                        intent.putExtra("dataCity",getFields.getText().toString());
                        setResult(Activity.RESULT_OK,intent);
                        finish();
                    }
                });
                textView.setText("Please Enter The City Name");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(EnterYourCityNameSearch.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
            }
        });
        AllCityApi();
        imageView=findViewById(R.id.iv42);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        recyclerView=findViewById(R.id.recycler_project_society);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
    public void AllCityApi() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.All_CITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsjjjjjjjjjja", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        for (int i=0;i<object.length();i++) {
                            societyDataList.add(object.getJSONObject(i).getString("value"));
                        }
                        ArrayList<String> arrayList=new ArrayList<>();
                        for (String value : societyDataList){
                            if (value.toLowerCase().contains("gurugram")){
                                arrayList.add(value);
                            }
                        }
                        setUpDataList(arrayList);
//                        setUpDataList(societyDataList);
                    } else {
                        Toast.makeText(EnterYourCityNameSearch.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(EnterYourCityNameSearch.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("term", "");
                return map;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }

    private void setUpDataList(final ArrayList<String> dataList){
        AdapterClassProjectSociety projectSociety=new AdapterClassProjectSociety(dataList, context, new AdapterClassProjectSociety.ItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent();
                intent.putExtra("dataCity",dataList.get(position));
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
        recyclerView.setAdapter(projectSociety);
    }

}
