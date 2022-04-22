package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.BrokerDealClosedAdapter;
import com.nestowl.model.DealCLosedModal;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DealClosed extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView back;
    ArrayList<DealCLosedModal> cLosedModals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_closed);
        back=findViewById(R.id.ARTICLES_BACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        recyclerView=findViewById(R.id.DEAL_CLOSED_RECYCLER);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        cLosedModals=new ArrayList<>();
        fetchClosed(getIntent().getStringExtra("user_id"));
    }
    private void fetchClosed(String userId) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_PROJECT_CLOSED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("DealClosed Response", "onResponse: "+response );
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("ProjectDealClosedLead_data");
                        for (int i=0;i<jsonArray.length();i++){
                            DealCLosedModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),DealCLosedModal.class);
                            cLosedModals.add(data);
                        }
                        BrokerDealClosedAdapter adapter = new BrokerDealClosedAdapter(DealClosed.this,cLosedModals);
                        recyclerView.setAdapter(adapter);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}