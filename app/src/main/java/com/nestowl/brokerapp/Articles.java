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
import com.nestowl.AdapterClass.ArticleAdapter;
import com.nestowl.model.ArticleModal;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Articles extends AppCompatActivity {
    ArrayList<ArticleModal> articles;
    RecyclerView articlesRecyler;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        articlesRecyler=findViewById(R.id.ARTICLE_RECYCLER);
        back=findViewById(R.id.ARTICLES_BACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        articles = new ArrayList<>();

        showArticles();
    }
    private void showArticles(){
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_ARTICLES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("api", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("Article_data")) {
                        JSONArray object = jsonObject.getJSONArray("Article_data");
                        int length =  object.length();
                        for (int i=0;i<length;i++){
                            JSONObject data = object.getJSONObject(i);
                            ArticleModal t = new Gson().fromJson(data.toString(), ArticleModal.class);
                            articles.add(t);
                        }
                        articlesRecyler.setLayoutManager(new GridLayoutManager(Articles.this,2));
                        ArticleAdapter articleAdapter =  new ArticleAdapter(Articles.this, articles);
                        articlesRecyler.setAdapter(articleAdapter);

                    } else {
                        Log.e("get Profile Error", "onResponse: "+jsonObject.getString("message") );

                    }
                }catch (Exception e){
                    Log.e("Article Catch error", "onResponse: "+e );
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
                HashMap<String,String> hashMap = new HashMap<>();
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}