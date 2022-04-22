package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProposalPropertyModal;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class QueryScreenMakeBrokerThird extends AppCompatActivity {
    ImageView back_img;
    CardView cardView;
    ScrollView scrollView;
    RadioGroup loan,verification,docs,pick;
    String Sloan,Sverification,Sdocs,Spick;
    TextView name;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_screen_make_broker_third);
        back_img=findViewById(R.id.ARTICLES_BACK);
        name=findViewById(R.id.SELLER_PROPOSAL_NAME);
        LoginPojo loginPojo =  PrefMananger.GetLoginData(this);
        name.setText("("+loginPojo.getFirstName()+")");
        cardView=findViewById(R.id.card_submit);
        context = this;

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        loan=findViewById(R.id.SELLER_PROPOSAL_LOAN);
        verification=findViewById(R.id.SELLER_PROPOSAL_CLINT);
        docs=findViewById(R.id.SELLER_PROPOSAL_DOCS);
        pick=findViewById(R.id.SELLER_PROPOSAL_PICK);

        loan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_LOAN_YES){
                    Sloan="yes";
                }else {
                    Sloan="no";
                }
            }
        });
        verification.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_CLINT_YES){
                    Sverification="yes";
                }else {
                    Sverification="no";
                }
            }
        });
        docs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_DOCS_YES){
                    Sdocs="yes";
                }else {
                    Sdocs="no";
                }
            }
        });
        pick.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.SELLER_PROPOSAL_PICK_YES){
                    Spick="yes";
                }else {
                    Spick="no";
                }
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProposalPropertyModal proposalPropertyModal =  new ProposalPropertyModal();
                String s = PrefMananger.getString(context, PrefMananger.PROPERTY);
                proposalPropertyModal = new Gson().fromJson(s.toString(),ProposalPropertyModal.class);
                Log.e("error", "onClick: "+ s + " " + Sloan );
                proposalPropertyModal.setLoan_facilty(Sloan);
                proposalPropertyModal.setClient_verification(Sverification);
                proposalPropertyModal.setFaciltyproper_document(Sdocs);
                postData(proposalPropertyModal);
            }
        });

    }

    private void postData(ProposalPropertyModal proposalPropertyModal) {
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Sending Proposal wait...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.POST_PROPOSAL_PRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PrefMananger.saveString(QueryScreenMakeBrokerThird.this,PrefMananger.PROPERTY,"null");
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status=  jsonObject.getString("status");
                    String massage = jsonObject.getString("message");
                    if (status.equals("1")){
                        Toast.makeText(QueryScreenMakeBrokerThird.this,massage,Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(QueryScreenMakeBrokerThird.this,MakeOfferCongratulationScreen.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(QueryScreenMakeBrokerThird.this,massage,Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e+"   "+response );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                PrefMananger.saveString(QueryScreenMakeBrokerThird.this,PrefMananger.PROPERTY,"null");
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                Field[] fields = proposalPropertyModal.getClass().getDeclaredFields();
                for (Field f:fields){
                    f.setAccessible(true);
                    try {
                        String value = (String) f.get(proposalPropertyModal);
                        if (value==null){
                            value="no Data";
                        }
                        hashMap.put(f.getName(), value);
                        Log.e("proposal sumbit ", "getParams: "+f.getName()+" "+ value );
                    }catch (Exception e){
                        Log.e("Error", "getParams: "+e);
                    }
                }
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
