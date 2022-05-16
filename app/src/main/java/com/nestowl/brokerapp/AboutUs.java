package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.nestowl.model.AboutUsModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutUs extends AppCompatActivity {
    TextView mainText,nestText;
    BottomNavigationView bottomNavigationView;
    LinearLayout ourMision,nestPros;
    ImageView back;
    WebView ourMission,nestPross;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";
    final String header = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" ;
    final String bodyOpen =   "\n</head>\n" + "<body>\n";
    final String close =    "\n</body>\n" + "</html>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mainText=findViewById(R.id.ABOUT_US_MAIN_TEXT);
        nestText=findViewById(R.id.ABOUT_US_NEST_TEXT);
        back=findViewById(R.id.ABOUT_US_BACK);
        bottomNavigationView=findViewById(R.id.ABOUT_US_BOTTOM_NAV);
        ourMision=findViewById(R.id.ABOUT_US_OUR_MISSION);
        nestPros=findViewById(R.id.ABOUT_US_NEST_PROFETIONALS);
        ourMission=findViewById(R.id.ABOUT_US_MAIN_WEB_VIEW);
        nestPross=findViewById(R.id.ABOUT_US_NEST_WEBVIEW);

        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        if (PrefMananger.getString(this,"aboutus/our")!=null){
            String jsons = PrefMananger.getString(this,"aboutus/our");
            try {
                AboutUsModal data =  new Gson().fromJson(jsons,AboutUsModal.class);
                String html = header+data.getIntrotext()+bodyOpen+data.getArticle_description()+close;
//                mainText.setText(Html.fromHtml(data.getArticle_description()));
                ourMission.loadDataWithBaseURL("", html, mimeType, encoding, "");
            }catch (Exception e){
                Log.e("preLoadError", "onCreate: "+e );
            }
        }
        if (PrefMananger.getString(this,"aboutus/nest")!=null){
            String jsons = PrefMananger.getString(this,"aboutus/nest");
            try {
                AboutUsModal data =  new Gson().fromJson(jsons,AboutUsModal.class);
                String html = header+data.getIntrotext()+bodyOpen+data.getArticle_description()+close;
//                nestText.setText(Html.fromHtml(data.getArticle_description()));
                nestPross.loadDataWithBaseURL("", html, mimeType, encoding, "");
            }catch (Exception e){
                Log.e("preLoadError", "onCreate: "+e );
            }
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getAbout();
        getNestPros();
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
    private void getAbout() {
        StringRequest request=new StringRequest(Request.Method.POST, UrlClass.GET_CONTENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        AboutUsModal data =  new Gson().fromJson(jsonObject.getJSONObject("Content_data").toString(),AboutUsModal.class);
                        data.setIntrotext(jsonObject.getString("css_data"));
                        String html = header+data.getIntrotext()+bodyOpen+data.getArticle_description()+close;
//                        Log.e("HTML", "onResponse: "+data.toString()+html );
                        mainText.setText(Html.fromHtml(data.getArticle_description()));
                        ourMission.loadDataWithBaseURL("", data.getArticle_description(), mimeType, encoding, "");
                        String json = new Gson().toJson(data);
                        PrefMananger.saveString(AboutUs.this,"aboutus/our",json);
                        Log.e("About", "onResponse: saveed" );
                    }
                }catch (Exception e){
                    Log.e("errorABout", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorABout", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id","73");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void getNestPros(){
        StringRequest request=new StringRequest(Request.Method.POST, UrlClass.GET_CONTENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        AboutUsModal data =  new Gson().fromJson(jsonObject.getJSONObject("Content_data").toString(),AboutUsModal.class);
                        data.setIntrotext(jsonObject.getString("css_data").toString());
                        String html = header+data.getIntrotext()+bodyOpen+data.getArticle_description()+close;
                        nestText.setText(Html.fromHtml(data.getArticle_description()));
                        nestPross.loadDataWithBaseURL("", data.getArticle_description(), mimeType, encoding, "");
                        String json = new Gson().toJson(data);
                        PrefMananger.saveString(AboutUs.this,"aboutus/nest",json);
                        Log.e("About", "onResponse: saveed" );
                    }
                }catch (Exception e){
                    Log.e("errorABout", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errorABout", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id","74");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.our_mission:
                    ourMision.setVisibility(View.VISIBLE);
                    nestPros.setVisibility(View.GONE);
                    break;
                case R.id.nest_pros:
                    ourMision.setVisibility(View.GONE);
                    nestPros.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    };
}