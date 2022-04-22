package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

public class FrontViewQuerySecond extends AppCompatActivity {
    ImageView back_img;
    HorizontalScrollView card_query;
    TextView price,address,bed,bath,hello,day,time,broker;
    RadioButton buyers,deals,stage,verified,negtiate,times,finalpay,mode,loan,clint,docs,pick;
    String ID,USER_ID;
    LoginPojo loginPojo;
    CardView cardView;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_view_query_second);
        card_query=findViewById(R.id.horizontal);
        ID=getIntent().getStringExtra("id");
        USER_ID=getIntent().getStringExtra("user_id");
        back_img=findViewById(R.id.ARTICLES_BACK);
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
        loginPojo = PrefMananger.GetLoginData(this);
        price=findViewById(R.id.PROPOSAL_VIEW_PRO_PRICE);
        address=findViewById(R.id.PROPOSAL_VIEW_PRO_PRICE_ADDRESS);
        bed=findViewById(R.id.PROPOSAL_VIEW_PRO_BED);
        bath=findViewById(R.id.PROPOSAL_VIEW_PRO_BATH);
        hello=findViewById(R.id.PROPOSAL_VIEW_PRO_HELO);
        day=findViewById(R.id.PROPOSAL_VIEW_PRO_DAY);
        time=findViewById(R.id.PROPOSAL_VIEW_PRO_TIME_TXT);
        broker=findViewById(R.id.PROPOSAL_VIEW_PRO_NAME);

        buyers=findViewById(R.id.PROPOSAL_VIEW_PRO_BUYERS);
        deals=findViewById(R.id.PROPOSAL_VIEW_PRO_WANT);
        stage=findViewById(R.id.PROPOSAL_VIEW_PRO_STAGE);
        verified=findViewById(R.id.PROPOSAL_VIEW_PRO_VERIFYD);
        negtiate=findViewById(R.id.PROPOSAL_VIEW_PRO_NEGOTIATE);
        times=findViewById(R.id.PROPOSAL_VIEW_PRO_TIME);
        finalpay=findViewById(R.id.PROPOSAL_VIEW_PRO_FINAL);
        mode=findViewById(R.id.PROPOSAL_VIEW_PRO_MODE);
        loan=findViewById(R.id.PROPOSAL_VIEW_PRO_LOADn);
        clint=findViewById(R.id.PROPOSAL_VIEW_PRO_CLINT);
        docs=findViewById(R.id.PROPOSAL_VIEW_PRO_DOCS);
        pick=findViewById(R.id.PROPOSAL_VIEW_PRO_PICK);

        broker.setText("Hello "+loginPojo.getFirstName());
        FecthData();

    }

    private void FecthData() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Wait...");
        pd.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.LEADS_VIEW_PROPOSAL_PRO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("Brokeracceptproposal")){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("Brokeracceptproposal");
                        ProposalPropertyModal proposalPropertyModal =  new Gson().fromJson(jsonObject1.toString(),ProposalPropertyModal.class);
                        setdata(proposalPropertyModal);
                    }
                }catch (Exception e){
                    Log.e("errorEX", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.e("errorEX", "onResponse: "+error);
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap =  new HashMap<>();
                hashMap.put("user_id",USER_ID);
                hashMap.put("property_id",ID);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    protected void setdata(ProposalPropertyModal data){
        price.setText(data.getPrice());
        time.setText(data.getMeeting_time());
        day.setText(data.getMeeting_days());
        setradio(buyers,data.getNumber_of_buyers());
        setradio(deals,data.getDeal_to_happen());
        setradio(stage,data.getStage());
        setradio(verified,data.getVerified_buyer());
        setradio(negtiate,data.getNegotiate());
        setradio(times,data.getBooking_payment());
        setradio(finalpay,data.getFinal_payment());
        setradio(mode,data.getTransaction());
        setradio(loan,data.getLoan_facilty());
        setradio(clint,data.getClient_verification());
        setradio(docs,data.getFaciltyproper_document());
        setradio(pick,"NO");
    }
    protected void setradio(RadioButton button,String data){
        button.setChecked(true);
        button.setText(data);
    }
}
