package com.nestowl.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.PostImagesRecuclerModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostImagesRecyclerAdapter extends RecyclerView.Adapter<PostImagesRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<PostImagesRecuclerModal> data;
    String datais;
    boolean ispopup;

    public PostImagesRecyclerAdapter(Context context, ArrayList<PostImagesRecuclerModal> data, String datais) {
        this.context = context;
        this.data = data;
        this.datais = datais;
        ispopup=true;
    }

    @NonNull
    @Override
    public PostImagesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_images_recycler_post, parent,false);
        return new PostImagesRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostImagesRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PostImagesRecuclerModal dat = data.get(position);
        holder.status.setText(dat.getImageFrom());
        if (dat.getIsFromServer().equals("true")){
            Glide.with(context).load(dat.getImage()).placeholder(R.drawable.default_x_x).into(holder.main);
        }else {
            holder.main.setImageURI(dat.getImageLink());
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyItemRemoved(position);
                if (dat.getIsFromServer().equals("true")){
                    deleteFromServer(dat.getId(),dat.getPropertyId(),dat.getImageFrom());
                }
            }
        });
    }

    private void deleteFromServer(String id, String propertyId,String image) {
        if (image.equals("SiteView")){
            siteView(id,propertyId);
        }
        if (image.equals("Common")){
            commanArea(id,propertyId);
        }
        if (image.equals("Exterior")){
            exterierView(id,propertyId);
        }
        if (image.equals("Living Room")){
            livingRoom(id,propertyId);
        }
        if (image.equals("Bathrooms")){
            bathRooms(id,propertyId);
        }
        if (image.equals("Bedroom")){
            bedROoms(id,propertyId);
        }
        if (image.equals("Kitchen")){
            kicthen(id,propertyId);
        }
        if (image.equals("Floor")){
            floor(id,propertyId);
        }
        if (image.equals("Master")){
            master(id,propertyId);
        }
        if (image.equals("Location")){
            location(id,propertyId);
        }
        if (image.equals("Other")){
            other(id,propertyId);
        }
        if (image.equals("Washroom")){
            washrooms(id,propertyId);
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView main, delete;
        TextView status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main=itemView.findViewById(R.id.POST_IMAGES_CUSTOM_MAIN_IMG);
            delete=itemView.findViewById(R.id.POST_IMAGES_CUSTOM_DELETE);
            status=itemView.findViewById(R.id.POST_IMAGES_CUSTOM_TEXT);
        }
        @Override
        public void onClick(View v) {

        }
    }
    private void siteView(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_SITEVIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_SITE_VIEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void commanArea(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_COMAN_AREA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_COMMON_AREA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void exterierView(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_EXTERIR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_EXTERIR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void livingRoom(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_LIVING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_LIVING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void bathRooms(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_BATHROOMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_BATHROOMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void bedROoms(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_BEDROOMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_BEDROOMS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void kicthen(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_KICTHEN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_KICTHEN_MAIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void floor(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_FLOOR_PLANS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_FLOOR, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void master(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_MASTER_PLAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_MASTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void location(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_LOCATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_LOCATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void other(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_OTHER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_OTHER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void washrooms(String id, String propertyId) {
        StringRequest delte =  new StringRequest(Request.Method.POST, UrlClass.DELETE_BY_ID_WASHROOM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("id",id);
                return hashMap;
            }
        };
        StringRequest delteMain =  new StringRequest(Request.Method.POST, UrlClass.DELETE_MAIN_WASHROOM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
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
                hashMap.put("property_id",propertyId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(delte);
        Volley.newRequestQueue(context).add(delteMain);
    }
    private void success(){
        if (ispopup){
            new WarningDio(context, "Delete Successful.", "OK", null, new WarningDio.Response() {
                @Override
                public void getClicks(int click) {

                }
            },false);
        }
        ispopup=false;
        android.os.Handler handler = new Handler();
        Runnable runnable =  new Runnable() {
            @Override
            public void run() {
                ispopup=true;
            }
        };
        handler.postDelayed(runnable,1000);
    }
}
