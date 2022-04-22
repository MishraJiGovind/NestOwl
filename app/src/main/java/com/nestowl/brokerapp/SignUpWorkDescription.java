package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.PincodeAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.LocalitiesModal;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class SignUpWorkDescription extends AppCompatActivity {
    ImageView back_img;
    CardView card_service;
    EditText authorize_agent,trade_licence_no,brief_description;
    TextView date_of_issue,addMoire;
    AutoCompleteTextView localitiesIn;
    Context context;
    Activity activity;
    LinearLayout scrolladds;
    String localitiesFromServer;
    ArrayList<LocalitiesModal> localitiesModals;
    RecyclerView recyclerView;
    PincodeAdapter adapters;
    Calendar calendar;
    ImageView date;
    ArrayAdapter<String> adapter;
    ArrayList<String> locations;
    boolean isEdited=false;
    Autoclicks autoclicks;
    LiveCommnication liveCommnication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_work_description);
        context = activity = this;
        card_service=findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        scrolladds=findViewById(R.id.WORK_DESCRIPTION_SCROLL);
        authorize_agent=findViewById(R.id.authorize_agent);
        trade_licence_no=findViewById(R.id.optional_id);
        calendar = Calendar.getInstance();
        date_of_issue=findViewById(R.id.date_of_issue);
        brief_description=findViewById(R.id.brief_description);
        date=findViewById(R.id.img_idd);
        localitiesModals = new ArrayList<>();
        localitiesIn=findViewById(R.id.WORK_DESCRIPTION_LOCALITIES_IN);
        recyclerView=findViewById(R.id.WORK_DESCRIPTION_LOCALITIES_RECYCLER);
        addMoire=findViewById(R.id.WORK_DESCRIPTION_LOCALITIES_ADD_MORE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key = aichat.getText();
                String value1 = aichat.getValue();
                String value2 = aichat.getValues();
                if (key.equals("p")){
                    addPinCustom(value1,value2);
                }
                if (key.equals("pr")){
                    removeFromList(value1);
                }
                if (key.equals("pt")){
                    addText(value1,value2);
                }
            }
        });
//        if (PrefMananger.getString(this,PrefMananger.LOCALITYIES)!=null){
//            handleLocalities();
//        }
        locations=new ArrayList<>();
        addMoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (localitiesModals.size()<=10){
                    LocalitiesModal localitiesModal = new LocalitiesModal();
                    localitiesModal.setLocalities(null);
                    localitiesModal.setPincode(null);
                    localitiesModals.add(localitiesModal);
                    setAdapterss();
                }else {
                    new WarningDio(SignUpWorkDescription.this, "Locality limit reached", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                }
            }
        });
        locationSuggestin();
        getAllLocaltions();
        localitiesIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()>0){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        final DatePickerDialog.OnDateSetListener fromdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                date1();
            }
        };
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=
                        new DatePickerDialog(SignUpWorkDescription.this, fromdate, calendar.get(calendar.YEAR),
                                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
//        scrolladds.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SignUpWorkDescription.this,SelectWorkingLocalities.class);
//                startActivity(intent);
//            }
//        });
        card_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatesecondWorkerDetails();
            }
        });
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpWorkDescription.this,EditSignUpForm.class);
                startActivity(intent);
            }
        });
        getWorkDescriptionDetailsDetails();
        autoclicks =  new Autoclicks();
        localitiesIn.setOnItemSelectedListener(autoclicks);
        localitiesIn.setOnItemClickListener(autoclicks);
    }

    private void addText(String value1, String value2) {
        LocalitiesModal pincodeModal = localitiesModals.get(Integer.parseInt(value2));
        pincodeModal.setLocalities(value1);
        localitiesModals.add(Integer.parseInt(value2),pincodeModal);
        localitiesModals.remove(Integer.parseInt(value2)+1);
        setAdapterss();
    }
    private void addPinCustom(String value1, String value2) {
        LocalitiesModal pincodeModal = localitiesModals.get(Integer.parseInt(value2));
        pincodeModal.setPincode(value1);
        localitiesModals.add(Integer.parseInt(value2),pincodeModal);
        localitiesModals.remove(Integer.parseInt(value2)+1);
        setAdapterss();
    }
    private void removeFromList(String value1) {
        localitiesModals.remove(Integer.parseInt(value1));
        setAdapterss();
    }
    private void SearchPin(String toString) {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        String value =  jsonObject1.getString("value");
                        LocalitiesModal localitiesModal = new LocalitiesModal();
                        localitiesModal.setLocalities(toString);
                        if (value!=null){
                            localitiesModal.setPincode(value);
                        }
                        localitiesModals.add(localitiesModal);
                        adapters =  new PincodeAdapter(SignUpWorkDescription.this,localitiesModals);
                        recyclerView.setAdapter(adapters);
                        localitiesIn.setText(null);
                    }else {
                        LocalitiesModal localitiesModal = new LocalitiesModal();
                        localitiesModal.setLocalities(toString);
                        localitiesModal.setPincode(null);
                        localitiesModals.add(localitiesModal);
                        adapters =  new PincodeAdapter(SignUpWorkDescription.this,localitiesModals);
                        recyclerView.setAdapter(adapters);
                        localitiesIn.setText(null);
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
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("locality",toString);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
    private void getAllLocaltions() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LOCATION_SUGGESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            locations.add(jsonObject1.getString("value"));
                            adapter = new ArrayAdapter<String>(SignUpWorkDescription.this, android.R.layout.simple_list_item_1,locations);
                            localitiesIn.setAdapter(adapter);
                        }
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
    public void date1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_of_issue.setText(sdf.format(calendar.getTime()));
    }
    public void getWorkDescriptionDetailsDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_WORK_DESCRIPTION, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        isEdited=true;
                        TextView textView = (TextView) card_service.getChildAt(0);
                        textView.setText("Update");
                        localitiesFromServer = object.getString("working_localities");
                        if (localitiesFromServer!=null){
                        handleServerLocalities(localitiesFromServer);
                        }
                        authorize_agent.setText(object.getString("agent_dealers"));
                        trade_licence_no.setText(object.getString("trade_license_number"));
                        date_of_issue.setText(object.getString("date_of_Issue"));
                        brief_description.setText(object.getString("breif_description"));
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
    private void handleServerLocalities(String localitiesFromServer) {
        try {
            JSONArray array = new JSONArray(localitiesFromServer);
            localitiesModals =  new ArrayList<>();
            for (int i=0;i<array.length();i++){
                LocalitiesModal d = new Gson().fromJson(array.getJSONObject(i).toString(),LocalitiesModal.class);
                localitiesModals.add(d);
            }
            setAdapterss();
        } catch (JSONException e) {
            e.printStackTrace();
            PrefMananger.saveString(SignUpWorkDescription.this,PrefMananger.LOCALITYIES,null);
        }
    }
    private void updatesecondWorkerDetails() {
       if (isEdited){
           ProgressDialog pd =  new ProgressDialog(this);
           pd.setCancelable(false);
           pd.setMessage("Updating..");
           pd.show();
           StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.UPDATE_WORK_DESCRIPTION, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   pd.dismiss();
                   try {
                       JSONObject jsonObject = new JSONObject(response);
                       String status = jsonObject.getString("status");
                       if (status.equals("1")){
                           JSONObject object =  jsonObject.getJSONObject("data");
                           Intent intent = new Intent(SignUpWorkDescription.this, SignUpServiceCharges.class);
                           startActivity(intent);
                           Log.e("loge", "onResponse: "+object );
                       }
                   }catch (Exception e){
                       Log.e("loge", "onResponse: "+e );
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   pd.dismiss();
                   Log.e("loge", "onResponse: "+error );
               }
           }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   String data =  new Gson().toJson(localitiesModals);
                   HashMap<String, String> hashMap = new HashMap<>();
//                   PrefMananger.GetLoginData(context).getId() + ""
                   hashMap.put("user_id",PrefMananger.GetLoginData(context).getUserId() + "");
                   hashMap.put("pincode", "110026");
                   hashMap.put("working_localities",data);
                   Log.e("Localities ", "getParams: "+data );
                   hashMap.put("agent_dealers",authorize_agent.getText().toString());
                   hashMap.put("trade_license_number",trade_licence_no.getText().toString());
                   hashMap.put("date_of_Issue",date_of_issue.getText().toString());
                   hashMap.put("breif_description",brief_description.getText().toString());
                   hashMap.put("status", "1");
                   for (String s : hashMap.values()){
                       Log.e("Data to server ", "getParams: "+s );
                   }
                   return hashMap;
               }
           };
           Volley.newRequestQueue(this).add(stringRequest);
       }else {
           if (UtilityFunction.isNetworkConnected(context)) {
            UtilityFunction.showLoading(context, "Please wait...");
            String data =  new Gson().toJson(localitiesModals);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId()+ "");
            hashMap.put("working_localities",data);
            hashMap.put("pincode", "110026");
            hashMap.put("agent_dealers",authorize_agent.getText().toString());
            hashMap.put("trade_license_number",trade_licence_no.getText().toString());
            hashMap.put("date_of_Issue",date_of_issue.getText().toString());
            hashMap.put("breif_description",brief_description.getText().toString());
            hashMap.put("status", "1");
            Log.d("asdf",hashMap.toString());

            ApiExecutor.getApiService().updateworkDetails(hashMap).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, retrofit2.Response<OtpResponse> response) {
                    UtilityFunction.hideLoading();
                    try {
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                        if (response.body().status == 1) {
                            Intent intent = new Intent(SignUpWorkDescription.this, SignUpServiceCharges.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    UtilityFunction.hideLoading();
                }
            });


        } else {
            Toast.makeText(context, "Network is not available", Toast.LENGTH_SHORT).show();
        }
       }

    }
    ArrayList<String> localityList = new ArrayList<>();
    public void locationSuggestin() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCATION_SUGGESTION, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        for (int i = 0; i < object.length(); i++) {

                            localityList.add(object.getJSONObject(i).getString("value"));
                        }

                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                progressDialog.dismiss();
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
    private void setAdapterss(){
        adapters=new PincodeAdapter(this,localitiesModals);
        recyclerView.setAdapter(adapters);
    }
    PopupMenu popupMenu;
    Boolean isSelectedMenu=false;
    private class Autoclicks implements AdapterView.OnItemSelectedListener , AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (localitiesIn.getText().length()>0){
                if (localitiesModals.size()<=10){
                    SearchPin(localitiesIn.getText().toString());
                }else {
                    new WarningDio(SignUpWorkDescription.this, "Locality limit reached", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                }
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (localitiesIn.getText().length()>0){
                if (localitiesModals.size()<=10){
                    SearchPin(localitiesIn.getText().toString());
                }else {
                    new WarningDio(SignUpWorkDescription.this, "Locality limit reached", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
//    public void showLocalitySuggestion(String text) {
//        try {
//            if (popupMenu!=null){
//                popupMenu.dismiss();
//            }
//            if (isSelectedMenu){
//                isSelectedMenu=false;
//                return;
//            }
//            popupMenu = new PopupMenu(context, select_working_localities);
//            for (int i = 0; i < localityList.size(); i++) {
//
//                if (localityList.get(i).toLowerCase().contains(text.toLowerCase())) {
//                    popupMenu.getMenu().add(localityList.get(i));
//                }
//            }
//            popupMenu.show();
//            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    isSelectedMenu=true;
//                    select_working_localities.setText(item.getTitle());
//                    localityApi();
//                    return true;
//                }
//            });
//
//        } catch (Exception e) {
//        }
//    }
//    private void updateWorkerDetails() {
//        if (UtilityFunction.isNetworkConnected(context)) {
//            UtilityFunction.showLoading(context, "Please wait...");
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.put("user_id", PrefMananger.GetLoginData(context).getId() + "");
//            hashMap.put("working_localities", select_working_localities.getText().toString());
//            hashMap.put("date_of_Issue", date_of_issue.getText().toString());
//            hashMap.put("agent_dealers", authorize_agent.getText().toString());
//            hashMap.put("trade_license_number", trade_licence_no.getText().toString());
//            hashMap.put("breif_description", brief_description.getText().toString());
//            hashMap.put("status", "1");
//            ApiExecutor.getApiService().updateworkDetails(hashMap).enqueue(new Callback<OtpResponse>() {
//                @Override
//                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
//                    UtilityFunction.hideLoading();
//                    try {
//                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
//                        if (response.body().status == 1) {
///*
//                            Intent intent = new Intent(SignUpPersonalDetails.this, SignUpOfficeDetails.class);
//                            startActivity(intent);*/
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onFailure(Call<OtpResponse> call, Throwable t) {
//                    UtilityFunction.hideLoading();
//                }
//            });
//        } else {
//            Toast.makeText(context, "Network is not available", Toast.LENGTH_SHORT).show();
//        }
//    }
}