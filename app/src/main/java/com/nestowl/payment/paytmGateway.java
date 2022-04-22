package com.nestowl.payment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.PaymentSubscribeResponseModal;

import com.nestowl.model.SubscriptionRemainModal;
import com.nestowl.model.User;
import com.nestowl.brokerapp.PaymentSuccess;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class paytmGateway {
    Context context;
    PaytmPGService Service;
    LoginPojo loginPojo;
    String checksum,orderId,id,Ammount;
    User user;
    ProgressDialog progressDialog;
    ArrayList<SubscriptionRemainModal>subscriptionRemainModals;
    int call;

    public paytmGateway(Context context,String id,int call) {
        this.context = context;
        this.id=id;
        this.call=call;
        loginPojo= PrefMananger.GetLoginData(context);
        makeOrder();
        subscriptionRemainModals=new ArrayList<>();
        try{
            String json =  PrefMananger.getString(context,"remains");
            if (json!=null){
                JSONArray jsonArray =  new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    SubscriptionRemainModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionRemainModal.class);
                    subscriptionRemainModals.add(modal);
                }
            }

        }catch (Exception e){

        }
        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Making Payment");
        progressDialog.show();
//        Service = PaytmPGService.getProductionService()
    }
    private void genrateChecksum() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.CHECKSUM_GENRATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.e("Error in CheckSum", "onResponse: "+response );
                 JSONObject jsonObject =  new JSONObject(response);
                 String checksums =  jsonObject.getString("GenerateSignatureByParameters");
                 String checksum2 =  jsonObject.getString("GenerateSignatureByParametersInBody");
                 checksum=checksums;
                    Log.e("checksum", "onResponse: "+checksum );
                 setorder();
                }catch (Exception e){
                    Log.e("Error in cheksum", "onResponse: "+e );
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error in checkcsum", "onResponse: "+error );
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("ORDER_ID",orderId);
                hashMap.put("CUST_ID",user.getMobile());
                hashMap.put("MOBILE_NO",user.getMobile());
                hashMap.put("EMAIL",user.getEmail());
                hashMap.put("TXN_AMOUNT",Ammount);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void makeOrder() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.BUY_NOW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.e("Error in Payment Making", "onResponse: "+response );
                    PaymentSubscribeResponseModal data = new Gson().fromJson(response,PaymentSubscribeResponseModal.class);
                    if (data.getStatus().equals("1")){
                        orderId=data.getOrderID();
                        Ammount=data.getPrice();
                        user=data.getUser();
                        genrateChecksum();
                    }
                }catch (Exception e){
                    Log.e("Error in Payment Making", "onResponse: "+e );
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error in Payment Making", "onResponse: "+error );
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",String.valueOf(loginPojo.getUserId()));
                hashMap.put("subscribe_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void setorder(){
        HashMap<String, String> paramMap = new HashMap<String,String>();
        paramMap.put( "MID" , UrlClass.MID);
        paramMap.put( "ORDER_ID" , orderId);
        paramMap.put( "CUST_ID" , user.getMobile());
        paramMap.put( "MOBILE_NO" , user.getMobile());
        paramMap.put( "EMAIL" , loginPojo.getEmail());
        paramMap.put( "CHANNEL_ID" , "WAP");
        paramMap.put( "TXN_AMOUNT" , Ammount);
        paramMap.put( "WEBSITE" , "WEBSTAGING");
        paramMap.put( "INDUSTRY_TYPE_ID" , "Retail");
        paramMap.put( "CALLBACK_URL", UrlClass.PYTM_URL);
        paramMap.put( "CHECKSUMHASH" , checksum);
        PaytmOrder Order = new PaytmOrder(paramMap);
        Service = PaytmPGService.getStagingService("");
        Service.initialize(Order, null);
        orderstart();

    }
    public void orderstart(){
        progressDialog.dismiss();
        Service.startPaymentTransaction(context, true, true, new PaytmPaymentTransactionCallback() {
            public void someUIErrorOccurred(String inErrorMessage) {
                Log.e("response o f payments", "onTransactionResponse: "+inErrorMessage );
                payFailedUpdate();
            }
            public void onTransactionResponse(Bundle inResponse) {

                Log.e("response o f payments", "onTransactionResponse: "+inResponse.getString("STATUS") );
                Log.e("response payments full", "onTransactionResponse: "+inResponse);
                if (inResponse.getString("STATUS").equals("TXN_SUCCESS")){
                    paySuccessUpdate();
                }else {
                    payFailedUpdate();
                }
            }
            public void networkNotAvailable() {
                payFailedUpdate();
            }
            public void clientAuthenticationFailed(String inErrorMessage) {
                Log.e("response o f payments", "onTransactionResponse: "+inErrorMessage );
                payFailedUpdate();
            }
            public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
                Log.e("response o f payments", "onTransactionResponse: "+inErrorMessage );
                payFailedUpdate();
            }
            public void onBackPressedCancelTransaction() {
                payFailedUpdate();
            }
            public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                Log.e("response o f payments", "onTransactionResponse: "+inErrorMessage );
                payFailedUpdate();
            }
        });
    }
    public void paySuccessUpdate(){
        Date date = new Date();
        SubscriptionRemainModal data = new SubscriptionRemainModal();
        data.setPrice(Ammount);
        data.setName(id);
        data.setDate(date);
        if (call==1){
            data.setType("Subscriptions");
        }
        if (call==2){
            data.setType("Property");
        }
        if (call==3){
            data.setType("Lead");
        }
        subscriptionRemainModals.add(data);
        String json = new Gson().toJson(subscriptionRemainModals);
        PrefMananger.saveString(context,"remains",json);
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.PAYMENT_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (call==1){
                  context.startActivity(new Intent(context, PaymentSuccess.class));
                }
                if (call==2){
//                    new WarningDio(context, "Payment Successful.", "OK", null, new WarningDio.Response() {
//                        @Override
//                        public void getClicks(int click) {
//                            context.startActivity(new Intent(context, PlanBasicActivity.class));
//                        }
//                    },false);
                    context.startActivity(new Intent(context, PaymentSuccess.class));
                }
                if (call==3){
                    context.startActivity(new Intent(context, PaymentSuccess.class));
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("orderid",orderId);
                hashMap.put("status","TXN_SUCCESS");
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void payFailedUpdate(){
        new WarningDio(context, "Payment failed.", "OK", null, new WarningDio.Response() {
            @Override
            public void getClicks(int click) {

            }
        },false);
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.PAYMENT_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("orderid",orderId);
                hashMap.put("status","TXN_FAILURE");
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
