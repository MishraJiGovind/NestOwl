package com.nestowl.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nestowl.model.AcceptRejectModal;
import com.nestowl.model.LeadsContactUserModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LeadsRequmentsModal;
import com.nestowl.model.NotificationExtraModal;
import com.nestowl.model.NotificationModal;
import com.nestowl.model.PaymentOrderModal;
import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class NestowlNotificationHandler {
    Context context;
    User user;
    String updatedDate;
    ArrayList<NotificationModal>notificationModals;

    public NestowlNotificationHandler(Context context) {
        this.context = context;
        user=new Gson().fromJson(PrefMananger.getString(context,"user").toString(),User.class);
        if (PrefMananger.getString(context,"notification")!=null){
            NotificationExtraModal modal =  new Gson().fromJson(PrefMananger.getString(context,"notification").toString(),NotificationExtraModal.class);
            updatedDate=modal.getLastupdated();
            notificationModals=modal.getData();
        }else {
            notificationModals=new ArrayList<>();
            Calendar calendar =  Calendar.getInstance();
            Date toCampareWith =  calendar.getTime();
            updatedDate = toCampareWith.toString();
        }
        getNotifications();
    }

    private void getNotifications() {
        Packages();
        PublicLeads();
        ReqSumbit();
        Contacts();
        Brokerleads();
        DealClosed();
    }

    public void Packages(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_ORDERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("OrderDetails");
                        for (int i=0;i<jsonArray.length();i++){
                            PaymentOrderModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),PaymentOrderModal.class);
                            if (compareNotificationTime(data.getUpdated_at(),updatedDate)){
                                if (data.getPayment_status().contains("Paid")){
                                  getPlanById(data.getProduct_id());
                                }
                            }
                        }
                        save();
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "onResponse: "+error );

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void save() {
        NotificationExtraModal modal =  new NotificationExtraModal();
        modal.setData(notificationModals);
        modal.setLastupdated(String.valueOf(new Date()));
        String data = new Gson().toJson(modal);
        PrefMananger.saveString(context,"notification",data);
        notificationModals=new ArrayList<>();
        if (PrefMananger.getString(context,"notification")!=null){
            NotificationExtraModal modals =  new Gson().fromJson(PrefMananger.getString(context,"notification").toString(),NotificationExtraModal.class);
            updatedDate=modals.getLastupdated();
            notificationModals=modals.getData();
        }
    }
    private void getPlanById(String product_id) {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_SUBSCRIPTIONS_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        SubscriptionApiModal data = new Gson().fromJson(jsonObject.getJSONArray("SubscriptionPlans").getJSONObject(0).toString(),SubscriptionApiModal.class);
                        if (data.getPlan_type().equals("Registration")){
                            NotificationModal notificationModal =  new NotificationModal();
                            notificationModal.setId(data.getId());
                            notificationModal.setTitle("Registration Package");
                            notificationModal.setBody("HI, your subscription of "+data.getPrice()+"/- is now live , valid upto "+getexpiredate(data.getUpdated_at(),0,24,0,0));
                            notificationModal.setUnread(true);
                            notificationModal.setWhere("no");
                            notificationModal.setTime(String.valueOf(new Date()));
                            notificationModals.add(notificationModal);
                        }
                        if (data.getPlan_type().equals("ListingPackage")){
                            NotificationModal notificationModal =  new NotificationModal();
                            notificationModal.setId(data.getId());
                            notificationModal.setTitle("Listing Package");
                            notificationModal.setBody("Your listing package of  "+data.getPrice()+"/- is now live , valid upto "+getexpiredate(data.getUpdated_at(),0,24,0,0));
                            notificationModal.setUnread(true);
                            notificationModal.setWhere("no");
                            notificationModal.setTime(String.valueOf(new Date()));
                            notificationModals.add(notificationModal);
                        }
                        if (data.getPlan_type().equals("LeadBoost")){
                            NotificationModal notificationModal =  new NotificationModal();
                            notificationModal.setId(data.getId());
                            notificationModal.setTitle("Lead Boost Package");
                            notificationModal.setBody("Your lead boost package is now live, valid upto "+getexpiredate(data.getUpdated_at(),0,24,0,0));
                            notificationModal.setUnread(true);
                            notificationModal.setWhere("no");
                            notificationModal.setTime(String.valueOf(new Date()));
                            notificationModals.add(notificationModal);
                        }
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("id",product_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void PublicLeads(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_PUBLIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray req =  jsonObject.getJSONArray("requirement_data");
                        JSONArray pro =  jsonObject.getJSONArray("publicpros_data");
                        for (int i = 0;i<req.length();i++){
                            LeadsRequmentsModal data =  new Gson().fromJson(req.getJSONObject(i).toString(),LeadsRequmentsModal.class);
                            NotificationModal notificationModal =  new NotificationModal();
                            notificationModal.setId(data.getId());
                            notificationModal.setTitle("Lead from buyer");
                            notificationModal.setBody(data.getName()+" has shared a requirement of "+data.getProperty_type()+" property in "+data.getLocality());
                            notificationModal.setUnread(true);
                            notificationModal.setWhere("req");
                            notificationModal.setPropertyId(data.getRequirement_id());
                            notificationModal.setUserId(data.getUser_id());
                            notificationModal.setTime(String.valueOf(new Date()));
                            notificationModals.add(notificationModal);
                        }
                        save();
                        for (int i = 0;i<pro.length();i++){
                            LeadsPublicPro data =  new Gson().fromJson(pro.getJSONObject(i).toString(),LeadsPublicPro.class);
                            if (compareNotificationTime(data.getUpdated_at(),updatedDate)){
                                StringRequest request1 = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(response);
                                            String status = jsonObject.getString("status");
                                            if (status.equals("1")){
                                                JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                                                NotificationModal notificationModal =  new NotificationModal();
                                                notificationModal.setId(data.getId());
                                                notificationModal.setTitle("Lead from seller");
                                                notificationModal.setBody(data.getName()+" has shared a lead of "+data.getPropertytype()+" property in "+data.getLocality());
                                                notificationModal.setUnread(true);
                                                notificationModal.setWhere("pro");
                                                notificationModal.setPropertyId(data.getProperty_id());
                                                notificationModal.setUserId(data.getUser_id());
                                                notificationModal.setTime(String.valueOf(new Date()));
                                                notificationModals.add(notificationModal);
                                            }
                                        }catch ( Exception e){

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
                                        hashMap.put("user_id",data.getUser_id());
                                        return hashMap;
                                    }
                                };
                            }
                        }
                        save();
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void ReqSumbit(){

    }
    public void Contacts() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_LIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("ContactUser_data");
                        for (int i = 0;i<jsonArray.length();i++){
                            LeadsContactUserModal data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsContactUserModal.class);
                            if (compareNotificationTime(data.getUpdated_at(),updatedDate)){
                                StringRequest property =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_DETAILS, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject1 = new JSONObject(response);
                                            String  status = jsonObject1.getString("status");
                                            if (status.equals("1")){
                                                LeadsPublicPro leadsPublicPro =  new Gson().fromJson(jsonObject1.getJSONObject("publicpros_data").toString(),LeadsPublicPro.class);
                                                NotificationModal notificationModal =  new NotificationModal();
                                                notificationModal.setId(data.getId());
                                                notificationModal.setTitle("Contact");
                                                notificationModal.setBody(data.getName()+" has contacted you for your "+leadsPublicPro.getPropertytype()+" property in "+leadsPublicPro.getLocality());
                                                notificationModal.setUnread(true);
                                                notificationModal.setWhere("contact");
                                                notificationModal.setPropertyId(data.getProperty_id());
                                                notificationModal.setUserId(data.getUser_id());
                                                notificationModal.setTime(String.valueOf(new Date()));
                                                notificationModals.add(notificationModal);
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
                                        HashMap<String,String>hashMap=new HashMap<>();
                                        hashMap.put("property_id",data.getProperty_id());
                                        return hashMap;
                                    }
                                };
                                Volley.newRequestQueue(context).add(property);

                            }
                        }
                        save();
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void Brokerleads(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.LEADS_BROKER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("brokar_data");
                        for (int i = 0;i<jsonArray.length();i++){
                            LeadsPublicPro data =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsPublicPro.class);
                            if (compareNotificationTime(data.getUpdated_at(),updatedDate)){
                                if (!data.getUser_id().equals(user.getUser_id())){
                                    StringRequest name =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject jsonObject1 = new JSONObject(response);
                                                String  status = jsonObject1.getString("status");
                                                if (status.equals("1")){
                                                    JSONObject jsonObject2 =  jsonObject1.getJSONObject("data");
                                                    if (data.getLooking()!=null){
                                                        if (data.getLooking().equals("Sell")){
                                                            NotificationModal notificationModal =  new NotificationModal();
                                                            notificationModal.setId(data.getId());
                                                            notificationModal.setTitle("Nest Pro");
                                                            notificationModal.setBody(jsonObject2.getString("first_name")+" has shared a  "+data.getPropertytype()+" property in "+data.getLocality());
                                                            notificationModal.setUnread(true);
                                                            notificationModal.setWhere("proBroker");
                                                            notificationModal.setPropertyId(data.getProperty_id());
                                                            notificationModal.setUserId(data.getUser_id());
                                                            notificationModal.setTime(String.valueOf(new Date()));
                                                            notificationModals.add(notificationModal);
                                                        }else {
                                                            NotificationModal notificationModal =  new NotificationModal();
                                                            notificationModal.setId(data.getId());
                                                            notificationModal.setTitle("Nest Pro");
                                                            notificationModal.setBody(jsonObject2.getString("first_name")+" has shared a requirement of "+data.getPropertytype()+" property in "+data.getLocality());
                                                            notificationModal.setUnread(true);
                                                            notificationModal.setWhere("reqBroker");
                                                            notificationModal.setPropertyId(data.getProperty_id());
                                                            notificationModal.setUserId(data.getUser_id());
                                                            notificationModal.setTime(String.valueOf(new Date()));
                                                            notificationModals.add(notificationModal);
                                                        }
                                                    }


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
                                            HashMap<String,String>hashMap=new HashMap<>();
                                            hashMap.put("user_id",data.getUser_id());
                                            return hashMap;
                                        }
                                    };
                                    Volley.newRequestQueue(context).add(name);
                                }

                            }
                        }
                        save();
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void DealClosed(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_ALL_ACCEPTED_BY_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray = jsonObject.getJSONArray("alldata");
                        for (int i=0;i<jsonArray.length();i++){
                            AcceptRejectModal acceptRejectModal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),AcceptRejectModal.class);
                            if (compareNotificationTime(acceptRejectModal.getCreated_at(),updatedDate)){
                                if (acceptRejectModal.getDeal()!=null){
                                    if (acceptRejectModal.getDeal().contains("Yes")){
                                        NotificationModal notificationModal =  new NotificationModal();
                                        notificationModal.setId(acceptRejectModal.getId());
                                        notificationModal.setTitle("Deal Closed");
                                        notificationModal.setUnread(true);
                                        notificationModal.setWhere("no");
                                        notificationModal.setPropertyId(acceptRejectModal.getPro_id());
                                        notificationModal.setUserId(acceptRejectModal.getUser_id());
                                        notificationModal.setTime(String.valueOf(new Date()));

                                        StringRequest name =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonObject1 = new JSONObject(response);
                                                    String  status = jsonObject1.getString("status");
                                                    if (status.equals("1")){
                                                        JSONObject name =  jsonObject.getJSONObject("data");
                                                        String nameOfUSer = name.getString("first_name");

                                                        StringRequest Property =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BY_ID, new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                try {
                                                                    JSONObject jsonObject2 = new JSONObject(response);
                                                                    String  status =  jsonObject2.getString("status");
                                                                    if (status.equals("1")){
                                                                        LeadsPublicPro leadsPublicPro =  new Gson().fromJson(jsonObject2.getJSONArray("Property").getJSONObject(0).toString(),LeadsPublicPro.class);
                                                                        notificationModal.setBody(nameOfUSer+") has closed a deal of your "+leadsPublicPro.getPropertytype()+" property, "+leadsPublicPro.getProperty()+" with you.");
                                                                        notificationModals.add(notificationModal);
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
                                                                HashMap<String,String>hashMap=new HashMap<>();
                                                                hashMap.put("id",acceptRejectModal.getPro_id());
                                                                return hashMap;
                                                            }
                                                        };
                                                        Volley.newRequestQueue(context).add(Property);

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
                                                HashMap<String, String> hashMap = new HashMap<>();
                                                hashMap.put("user_id", acceptRejectModal.getUser_id());
                                                return hashMap;
                                            }
                                        };
                                        Volley.newRequestQueue(context).add(name);



                                    }
                                }
                            }
                        }
                        save();
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
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private String getexpiredate(String dates,int day,int month,int year,int hour){
        String expiredate = null;
        Calendar calendar =  Calendar.getInstance();

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date1 = format.parse(dates);
            calendar.setTime(date1);
            calendar.add(Calendar.DATE,day);
            calendar.add(Calendar.MONTH,month);
            calendar.add(Calendar.YEAR, year);
            calendar.add(Calendar.HOUR, hour);
            expiredate = calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH+1)+"/"+calendar.get(Calendar.YEAR);


        }catch (Exception e){

        }
        return expiredate;
    }
    private boolean compareNotificationTime(String dates,String updatedDate){
        boolean isdate = true;
        Calendar calendar =  Calendar.getInstance();

        try {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date notificationDate = format.parse(dates);

            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            formats.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date updatedDates = format.parse(updatedDate);

            if (updatedDates.compareTo(notificationDate)==1||updatedDates.compareTo(notificationDate)==0){
                isdate=false;
            }


        }catch (Exception e){

        }
        return isdate;
    }
}
