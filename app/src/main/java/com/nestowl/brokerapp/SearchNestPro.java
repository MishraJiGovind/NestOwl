
package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchNestPro extends AppCompatActivity {
    CardView card_search;
    ImageView back_img;
    TextView pincode;
    FrameLayout city,locality,residental,comrecial,other,rerayes,reraNo,pin;
    TextView CityTExt,LocalityTxt;
    int defaults = R.drawable.employe_circle_rounded;
    int active = R.drawable.selected_background_filter;
    String Category, Rera;
    ArrayList<String> localities,catogorys;
    boolean isResiDent,isCome,isOth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nest_pro);
        back_img=findViewById(R.id.iv5);
        card_search=findViewById(R.id.SEARCH_NEST_PRO_SEARCH);
        pincode=findViewById(R.id.SEARCH_NEST_PRO_PIN_IN);
        city=findViewById(R.id.SEARCH_NEST_PRO_CITY);
        locality=findViewById(R.id.SEARCH_NEST_PRO_LOCALITY);
        residental=findViewById(R.id.SEARCH_NEST_PRO_RESIDENTAL);
        comrecial=findViewById(R.id.SEARCH_NEST_PRO_COMERCIAL);
        other=findViewById(R.id.SEARCH_NEST_PRO_OTHERS);
        rerayes=findViewById(R.id.SEARCH_NEST_PRO_RERA_YES);
        reraNo=findViewById(R.id.SEARCH_NEST_PRO_RERA_NO);
        pin=findViewById(R.id.SEARCH_NEST_PRO_PIN);
        CityTExt=findViewById(R.id.SEARCH_NEST_PRO_CITY_TEXT);
        LocalityTxt=findViewById(R.id.SEARCH_NEST_PRO_LOCALITY_TEXT);
        localities =  new ArrayList<>();
        catogorys =  new ArrayList<>();
        isResiDent=true;
        isCome=true;
        isOth=true;

        pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SearchNestPro.this,PincodeSearch.class);
//                startActivity(intent);
            }
        });
        locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefMananger.saveString(SearchNestPro.this,"nest","nets");
                startActivityForResult(new Intent(SearchNestPro.this, LocalitySearchClass.class), 4);
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchNestPro.this, EnterYourCityNameSearch.class);
                PrefMananger.saveString(SearchNestPro.this,"nest","nets");
                startActivityForResult(intent,3);

            }
        });
        card_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchNestPro.this,NestPros.class);
                ArrayList<String> dat = new ArrayList<>();
                if (CityTExt.getText().equals("City")){
                    CityTExt.setError("Input City");
                    return;
                }
                if (LocalityTxt.getText().equals("Locality")){
                    LocalityTxt.setError("Input Locality");
                    return;
                }
//                if (pincode.getText()==null|pincode.getText().length()<=0){
//                    pincode.setError("Input Pincode");
//                    pincode.requestFocus();
//                    return;
//                }
                dat.add(CityTExt.getText().toString());
                dat.add(LocalityTxt.getText().toString());
                dat.add(pincode.getText().toString());
                dat.add(catogorys.toString());
                dat.add(Rera);
                intent.putExtra("data",dat);
                intent.putExtra("local",localities);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        locationSuggestin();
        residental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResiDent){
                    residental.setBackgroundResource(active);
                    catogorys.add("Residential");
                    isResiDent=false;
                }else {
                    isResiDent=true;
                    residental.setBackgroundResource(defaults);
                    for (int i=0;i<=catogorys.size();i++){
                        if (catogorys.get(i).equals("Residential")){
                            catogorys.remove(i);
                        }
                    }

                }

            }
        });
        comrecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCome){
                    comrecial.setBackgroundResource(active);
                    catogorys.add("Commercial");
                    isCome=false;
                }else {
                    isCome=true;
                    comrecial.setBackgroundResource(defaults);
                    for (int i=0;i<=catogorys.size();i++){
                        if (catogorys.get(i).equals("Commercial")){
                            catogorys.remove(i);
                        }
                    }

                }
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOth){
                    other.setBackgroundResource(active);
                    catogorys.add("Other");
                    isOth=false;
                }else {
                    isOth=true;
                    other.setBackgroundResource(defaults);
                    for (int i=0;i<=catogorys.size();i++){
                        if (catogorys.get(i).equals("Other")){
                            catogorys.remove(i);
                        }
                    }

                }
            }
        });
        reraNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reraNo.setBackgroundResource(active);
                rerayes.setBackgroundResource(defaults);
                Rera  = "No";
            }
        });
        rerayes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reraNo.setBackgroundResource(defaults);
                rerayes.setBackgroundResource(active);
                Rera  = "Yes";
            }
        });


    }
    public void localityApi() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCALITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        pincode.setText(object.getJSONObject(0).getString("value"));
                    } else {
                        Toast.makeText(SearchNestPro.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(SearchNestPro.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("locality", LocalityTxt.getText().toString());
                return map;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
    ArrayList<String> localityList = new ArrayList<>();
    public void locationSuggestin() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOCATION_SUGGESTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONArray object = jsonObject.getJSONArray("data");
                        for (int i = 0; i < object.length(); i++) {

                            localityList.add(object.getJSONObject(i).getString("value"));

                        }

                    } else {
                        Toast.makeText(SearchNestPro.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(SearchNestPro.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
    PopupMenu popupMenu;
    Boolean isSelectedMenu=false;
    public void showLocalitySuggestion(String text) {
        try {
            if (popupMenu!=null){
                popupMenu.dismiss();
            }
            if (isSelectedMenu){
                isSelectedMenu=false;
                return;
            }
            popupMenu = new PopupMenu(SearchNestPro.this, LocalityTxt);
            for (int i = 0; i < localityList.size(); i++) {

                if (localityList.get(i).toLowerCase().contains(text.toLowerCase())) {
                    popupMenu.getMenu().add(localityList.get(i));
                }
            }
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    isSelectedMenu=true;
                    pincode.setText(item.getTitle());
                    localityApi();
                    return true;
                }
            });

        } catch (Exception e) {

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode==1){

            }
            if (requestCode==3){
                CityTExt.setText(data.getStringExtra("dataCity"));
                CityTExt.setError(null);
            }
            if (requestCode==4){
                LocalityTxt.setText(data.getStringExtra("Locality"));
                LocalityTxt.setError(null);
//                localities.add(data.getStringExtra("Locality"));
                setLoaclities();
                showLocalitySuggestion(LocalityTxt.getText().toString());
            }
        }
    }

    private void setLoaclities() {
        String d = LocalityTxt.getText().toString();
        for (String s : localities){
            if (d==null|d.equals("Locality")){
                LocalityTxt.setText(s);
            }else{
                LocalityTxt.setText(d+","+s);
            }
        }
    }
}
