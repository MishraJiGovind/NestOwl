package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.PincodeAdapter;
import com.nestowl.model.LocalitiesModal;
import com.nestowl.model.PincodeModal;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectPinCodeSubmit extends AppCompatActivity {
    CardView cardView;
    RecyclerView recyclerView;
    PincodeAdapter adapter;
    LocalitiesModal localitiesModal;
    ArrayList<LocalitiesModal> localitiesModals, withPins;
    LiveCommnication liveCommnication;
    String savedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pin_code_submit);
        cardView=findViewById(R.id.card_cont);
        recyclerView=findViewById(R.id.PINCODE_ADD_RECYCLER);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        localitiesModals=new ArrayList<>();
        withPins=new ArrayList<>();
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key = aichat.getText();
                String val = aichat.getValue();
                if (key.equals("p")){
                    addNewPincode(val,Integer.parseInt(aichat.getValues()));
                }
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s =  new Gson().toJson(withPins);
                PrefMananger.saveString(SelectPinCodeSubmit.this,PrefMananger.LOCALITYIES,s);
                finish();
//                Intent intent = new Intent(SelectPinCodeSubmit.this,SignUpWorkDescription.class);
//                startActivity(intent);
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        savedData = PrefMananger.getString(this,PrefMananger.LOCALITYIES);
        fetchData();
//        LocalitiesModal d =  new LocalitiesModal();
//        d.setLocalities("SECTOR 63-A, VILLAGE ULLAHWAS, GURUGRAM");
//        d.setPincode(null);
//        LocalitiesModal ds =  new LocalitiesModal();
//        ds.setLocalities("SECTOR 107, VILLAGE ULLAHWAS, GURUGRAM");
//        ds.setPincode(null);
//        localitiesModals.add(d);
//        localitiesModals.add(ds);
//        searchPincodeFilter(localitiesModals);
    }

    private void fetchData() {
        try {
            JSONArray jsonArray = new JSONArray(savedData);
            localitiesModals =  new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                LocalitiesModal data = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LocalitiesModal.class);
                localitiesModals.add(data);
                Log.e("fecthing", "onCreate: "+data.getLocalities() );
            }
            searchPincodeFilter(localitiesModals);
            Log.e("called", "onCreate: "+localitiesModals);
        }catch (Exception e){
            Log.e("error", "onCreate: "+e );
        }
    }
    private void searchPincodeFilter(ArrayList<LocalitiesModal> data){
        for (LocalitiesModal values:data){
            String[] extracted = values.getLocalities().split(",");
            String sectors = extracted[0];
            if (sectors.contains("-")){
                sectors.replace("-","");
                String[] news = sectors.split(" ");
                String finals = news[0]+" "+news[1];
                getPin(finals);
                Log.e("Local", "onCreate: "+finals );
            }else {
                String[] news = sectors.split(" ");
                String finals = news[0]+" "+news[1];
                getPin(finals);
                Log.e("Local", "onCreate: "+finals );
            }
        }
    }
    private void getPin(String locality){
        ProgressDialog progressDialog =  new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting Pincode...");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        PincodeModal pincodeModal=new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PincodeModal.class);
                        LocalitiesModal localitiesModal =  new LocalitiesModal();
                        localitiesModal.setLocalities(locality);
                        if (pincodeModal.getValue()!=null){
                            localitiesModal.setPincode(pincodeModal.getValue());
                        }else {
                            localitiesModal.setPincode(null);
                        }
                        withPins.add(localitiesModal);
                        refreshRecycler();
                    }else {
                        LocalitiesModal localitiesModal =  new LocalitiesModal();
                        localitiesModal.setLocalities(locality);
                        localitiesModal.setPincode(null);
                        withPins.add(localitiesModal);
                        refreshRecycler();
                    }


                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("locality",locality);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void addNewPincode(String val, int parseInt) {
        LocalitiesModal l =withPins.get(parseInt);
        l.setPincode(val);
        withPins.add(parseInt,l);
        withPins.remove(parseInt++);
        refreshRecycler();
    }
    private void refreshRecycler() {
        adapter =  new PincodeAdapter(this,withPins);
        recyclerView.setAdapter(adapter);
    }
}
