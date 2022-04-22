package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.LocalitiesAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.LocalitiesModal;
import com.nestowl.model.LocalitiesViewModal;
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

public class SelectWorkingLocalities extends AppCompatActivity {
    ImageView back_img;
    CardView cardView;
    TextView textView;
    String localityName;
    ArrayList<LocalitiesModal> localitiesModals;
    ArrayList<LocalitiesViewModal> localitiesViewModals,searchs;
    RecyclerView recyclerView;
    LocalitiesAdapter adapter;
    LiveCommnication liveCommnication;
    EditText input;
    boolean isserching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_working_localities);
        back_img=findViewById(R.id.ARTICLES_BACK);
        cardView=findViewById(R.id.card_cont);
        textView=findViewById(R.id.locality_tv);
        localityName=null;
        localitiesModals=new ArrayList<>();
        localitiesViewModals=new ArrayList<>();
        searchs=new ArrayList<>();
        input =findViewById(R.id.LOCALITIES_INPUT_SEARCH);
        recyclerView=findViewById(R.id.LOCALITIES_RECYLER);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String arreySting = String.valueOf(localitiesModals);
                String s = new Gson().toJson(localitiesModals);
                PrefMananger.saveString(SelectWorkingLocalities.this,PrefMananger.LOCALITYIES,s);
                Intent intent =  new Intent(SelectWorkingLocalities.this,SelectPinCodeSubmit.class);
                startActivity(intent);
                finish();
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(SelectWorkingLocalities.this);
                dialog.setContentView(R.layout.locality_popup);
                EditText in = dialog.findViewById(R.id.DIO_LOCALITYIES_ADD_IN);
                CardView sumbit = dialog.findViewById(R.id.DIO_LOALITIES_SUMBIT);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SelectWorkingLocalities.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (in.getText().length()<=0){
                            in.setError("Enter Localities");
                            return;
                        }
                        localityisAdd(in.getText().toString(),00);
                        dialog.dismiss();
                    }
                });

            }
        });
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key =  aichat.getText();
                String value = aichat.getValue();
                if (key.equals("a")){
                    addToList(value);
                }
                if (key.equals("r")){
//                    removeFromList(value);
                }
            }
        });
        getLocalities();
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0){
                    searchs = new ArrayList<>();
                    for (LocalitiesViewModal v : localitiesViewModals){
                        if (v.getName().toLowerCase().contains(s.toString().toLowerCase())){
                            searchs.add(v);
                        }
                    }
                    recyclerView.removeAllViews();
                    adapter = new LocalitiesAdapter(SelectWorkingLocalities.this,searchs);
                    recyclerView.setAdapter(adapter);
                    Log.e("tmp", "afterTextChanged: "+s.toString() );
                    isserching=true;
                }else {
                    addedToListView();
                    isserching=false;
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

//    private void removeFromList(String value) {
//        int v =Integer.parseInt(value);
//        int count = 0;
//        LocalitiesViewModal localitiesViewModal =  localitiesViewModals.get(v);
//        localitiesViewModal.setAdded(false);
//        localitiesViewModals.add(Integer.parseInt(value),localitiesViewModal);
//        for (LocalitiesModal d : localitiesModals){
//            if (d.getLocalities().equals(localitiesViewModal.getName())){
//                localitiesModals.remove(count);
//            }
//            count++;
//        }
//        showSelected();
//        addedToListView();
//    }
    private void showSelected() {
        Log.e("Selected Localties", "showSelected: "+localitiesModals.toString() );
    }
    private void addToList(String value) {
        int i = Integer.parseInt(value);
        if (isserching){
            LocalitiesViewModal d =  searchs.get(i);
            localityisAdd(d.getName(),i);
        }else {
            LocalitiesViewModal d =  localitiesViewModals.get(i);
            localityisAdd(d.getName(),i);
        }

        Log.e("idex", "addToList: "+value );

    }
    private void getLocalities() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LOCALITIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status  = jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        for (int o = 0;o<jsonArray.length();o++){
                            JSONObject data = jsonArray.getJSONObject(o);
                            String val = data.getString("value");
                            LocalitiesViewModal localitiesViewModal =  new LocalitiesViewModal();
                            localitiesViewModal.setName(val);
                            localitiesViewModal.setPincode("0000");
                            localitiesViewModal.setAdded(false);
                            localitiesViewModals.add(localitiesViewModal);
                            addedToListView();
                        }
                    }
                }catch (Exception e){
                Log.e("error e", "onErrorResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onErrorResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap = new HashMap<>();
                hashMap.put("location","GURUGRAM");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void localityisAdd(String toString,int i) {
        localityName=toString;
        if (i!=999999999){
           if (isserching){
               LocalitiesViewModal d =  new LocalitiesViewModal();
               d.setAdded(true);
               d.setName(localityName);
               d.setPincode("no data");
               searchs.add(i,d);
               searchs.remove(i+1);
               LocalitiesModal l =  new LocalitiesModal();
               l.setPincode("no data");
               l.setLocalities(localityName);
               localitiesModals.add(l);
               recyclerView.removeAllViews();
               adapter = new LocalitiesAdapter(SelectWorkingLocalities.this,searchs);
               recyclerView.setAdapter(adapter);
               showSelected();
           }else {
               LocalitiesViewModal d =  new LocalitiesViewModal();
               d.setAdded(true);
               d.setName(localityName);
               d.setPincode("no data");
               localitiesViewModals.add(i,d);
               localitiesViewModals.remove(i+1);
               LocalitiesModal l =  new LocalitiesModal();
               l.setPincode("no data");
               l.setLocalities(localityName);
               localitiesModals.add(l);
               recyclerView.removeAllViews();
               addedToListView();
               showSelected();
           }
        }else {
            LocalitiesModal localitiesModal =  new LocalitiesModal();
            localitiesModal.setLocalities(localityName);
            localitiesModal.setPincode("no data");
            localitiesModals.add(localitiesModal);
            nowDoTHis();
            showSelected();
        }
//        searchPin(localityName,00);
    }
//    private void searchPin(String localityName, int i) {
//        ProgressDialog dio = new ProgressDialog(this);
//        dio.setCancelable(false);
//        dio.setMessage("Adding...");
//        dio.show();
//        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                dio.dismiss();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String status =  jsonObject.getString("status");
//                    if (status.equals("1")&&jsonObject.has("data")){
//                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
//                        PincodeModal pincodeModal =  new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PincodeModal.class);
//                        LocalitiesModal localitiesModal =  new LocalitiesModal();
//                        localitiesModal.setLocalities(localityName);
//                        localitiesModal.setPincode(pincodeModal.getValue());
//                        localitiesModals.add(localitiesModal);
//                        Log.e("pincode", "The Pin: "+pincodeModal.getValue() );
//                        nowDoTHis();
//                        if (i!=00){
//                            LocalitiesViewModal d =  new LocalitiesViewModal();
//                            d.setAdded(true);
//                            d.setName(localityName);
//                            d.setPincode(pincodeModal.getValue());
//                            localitiesViewModals.add(i,d);
//                            addedToListView();
//                        }
//                    }
//                }catch (Exception e){
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                dio.dismiss();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String>hashMap =  new HashMap<>();
//                hashMap.put("locality",localityName);
//                return hashMap;
//            }
//        };
//        Volley.newRequestQueue(this).add(request);
//    }
    private void nowDoTHis() {
        new WarningDio(this, "Localities added now you can select more", "SELECT MORE", "SUBMIT", new WarningDio.Response() {
            @Override
            public void getClicks(int click) {
                if (click!=1){
//                    String arreySting = String.valueOf(localitiesModals);
                    String s = new Gson().toJson(localitiesModals);
                    PrefMananger.saveString(SelectWorkingLocalities.this,PrefMananger.LOCALITYIES,s);
                    finish();
                }
            }
        },false);
    }
    private void addedToListView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LocalitiesAdapter(this,localitiesViewModals);
        recyclerView.setAdapter(adapter);
    }
}
