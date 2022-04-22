package com.nestowl.AdapterClass;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.BasicDetailsModal;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyPropertyIncompleteAdapter extends RecyclerView.Adapter<MyPropertyIncompleteAdapter.ViewHolder> {
    Context context;
    ArrayList<BasicDetailsModal> data;

    public MyPropertyIncompleteAdapter(Context context, ArrayList<BasicDetailsModal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyPropertyIncompleteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_incomplete,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyPropertyIncompleteAdapter.ViewHolder holder, int position) {
        BasicDetailsModal info =  data.get(position);
        holder.name.setText(info.getLooking());
        holder.city.setText("City :"+info.getCity());
        holder.continie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleIncomplete(info.getProperty_id(),info.getUser_id());
//                Intent  intent =  new Intent(context, PlanBasicActivity.class);
//                intent.putExtra(PrefMananger.MY_DATABASE,0);
//                PrefMananger.saveString(context,PrefMananger.RESUMEPROPERTY_ID,info.getProperty_id());
//                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, city,bedrooms,floor,carpet;
        FrameLayout continie,adphoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_NAME);
            city=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_CITY);
            bedrooms=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_BEDROOMS);
            floor=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_FLOOR);
            carpet=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_CARPETE);
            continie=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_POST);
            adphoto=itemView.findViewById(R.id.CUSTOM_MY_PROPERTY_PENDING_ADD_PHOTO);
        }
        @Override
        public void onClick(View v) {

        }
    }
    private void handleIncomplete(String id,String user_id) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Getting listing...");
        pd.setCancelable(false);
        pd.show();
        Log.e("IncompleteId", "handleIncomplete: "+id );
        StringRequest verification =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_VERIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        pd.dismiss();
                        new WarningDio(context, "Property Already completed.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,5);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        StringRequest photos =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        Volley.newRequestQueue(context).add(verification);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,4);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        StringRequest amenties =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_AMENTIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        Volley.newRequestQueue(context).add(photos);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,3);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        StringRequest price =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PRICE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        Volley.newRequestQueue(context).add(amenties);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,2);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        StringRequest features =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_FEATURES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        Volley.newRequestQueue(context).add(price);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,1);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        StringRequest basic =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BASIC, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    JSONArray jsonArray =  jsonObject.getJSONArray("data");
                    if (status.equals("1")&&jsonArray.length()>=1){
                        Volley.newRequestQueue(context).add(features);
                    }else {
                        pd.dismiss();
                        Intent intent = new Intent(context,PlanBasicActivity.class);
                        intent.putExtra(PrefMananger.MY_DATABASE,0);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE,id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_UPDATE_USER,user_id);
                        PrefMananger.saveString(context,PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,"yes");
                        context.startActivity(intent);
                    }
                }catch (Exception e){
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user_id);
                hashMap.put("property_id",id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(basic);
    }
}
