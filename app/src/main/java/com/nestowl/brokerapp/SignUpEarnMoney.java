package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.OtpResponse;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpEarnMoney extends AppCompatActivity {
    CardView card_know_more,double_leads;
    ImageView back_img;
    CardView card_earn_more;
    Context context;
    Activity activity;
    String join_now_serve="";
    String opputunity_to_duble="";
    TextView tv_serve_as_hero,tv_maybe_later,tv_yes_want_to_earn,tv_second_maybe_later;
    boolean isAdding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_earn_money);
        context = activity = this;
        card_know_more=findViewById(R.id.know_more);
        card_earn_more=findViewById(R.id.earn_more);
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        tv_serve_as_hero=findViewById(R.id.serve_as_hero_id);
        tv_maybe_later=findViewById(R.id.may_be_later_id);
        tv_yes_want_to_earn=findViewById(R.id.yes_i_want_to_earn);
        tv_second_maybe_later=findViewById(R.id.maybe_later_second);
        double_leads=findViewById(R.id.know_more_double_leads);
        getEarnMoney();
        tv_serve_as_hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join_now_serve=tv_serve_as_hero.getText().toString();
                selected(tv_serve_as_hero);
                unselected(tv_maybe_later);
            }
        });
        tv_maybe_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join_now_serve=tv_maybe_later.getText().toString();
                unselected(tv_serve_as_hero);
                selected(tv_maybe_later);
            }
        });
        tv_yes_want_to_earn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opputunity_to_duble=tv_yes_want_to_earn.getText().toString();
                selected(tv_yes_want_to_earn);
                unselected(tv_second_maybe_later);
            }
        });
        tv_second_maybe_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opputunity_to_duble=tv_second_maybe_later.getText().toString();
                unselected(tv_yes_want_to_earn);
                selected(tv_second_maybe_later);
            }
        });
        card_earn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdding){
                    setEarnMore();
                }else {
                    updateEarnmoreDetails();
                }
            }
        });
        card_know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpEarnMoney.this,SignUpKnowMore.class);
                startActivity(intent);
            }
        });

        double_leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpEarnMoney.this,SignUpKnowMoreDoubleLeads.class);
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
                Intent intent = new Intent(SignUpEarnMoney.this,EditSignUpForm.class);
                startActivity(intent);

            }
        });
        getEarnMoney();
    }

    public void getEarnMoney() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_EARN_MONEY, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {

                        JSONObject object = jsonObject.getJSONObject("data");
                        // stateValues=
                        join_now_serve=object.getString("join_now");
                        opputunity_to_duble=object.getString("benifit_to_you");

                        if (join_now_serve.equals("Serve as hero")){
                            selected(tv_serve_as_hero);
                            unselected(tv_maybe_later);
                        }else  if (join_now_serve.equals("May be later")){
                            selected(tv_maybe_later);
                            unselected(tv_serve_as_hero);
                        }
                        if (opputunity_to_duble.contains("Yes i want to earn more")){
                            selected(tv_yes_want_to_earn);
                            unselected(tv_second_maybe_later);
                        }else if (opputunity_to_duble.contains("May be later")){
                            selected(tv_second_maybe_later);
                            unselected(tv_yes_want_to_earn);
                        }
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        TextView textView = (TextView)card_earn_more.getChildAt(0);
                        textView.setText("Save and Continue");
                        isAdding=true;
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



    private void updateEarnmoreDetails() {
        if (UtilityFunction.isNetworkConnected(context)) {
            UtilityFunction.showLoading(context, "Please wait...");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", String.valueOf(PrefMananger.GetLoginData(context).getUserId()));
            hashMap.put("join_now", join_now_serve);
            hashMap.put("benifit_to_you", opputunity_to_duble);
            hashMap.put("status", "1");
            ApiExecutor.getApiService().updateEarnMoreDetails(hashMap).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    UtilityFunction.hideLoading();
                    Log.e("response", "onResponse: "+response );
                    try {
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                        if (response.body().status == 1) {

                            Intent intent = new Intent(SignUpEarnMoney.this, SignUpVerification.class);
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
    private void setEarnMore(){
        if (UtilityFunction.isNetworkConnected(context)) {
            UtilityFunction.showLoading(context, "Please wait...");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", String.valueOf(PrefMananger.GetLoginData(context).getUserId()));
            hashMap.put("join_now", join_now_serve);
            hashMap.put("benifit_to_you", opputunity_to_duble);
            hashMap.put("status", "1");
            ApiExecutor.getApiService().setEarnMoreDetails(hashMap).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    UtilityFunction.hideLoading();
                    Log.e("response", "onResponse: "+response );
                    try {
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                        if (response.body().status == 1) {

                            Intent intent = new Intent(SignUpEarnMoney.this, SignUpVerification.class);
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


    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }

    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
}