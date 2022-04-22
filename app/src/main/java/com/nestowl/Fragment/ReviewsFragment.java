package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.ReviewsAdapter;
import com.nestowl.model.ReviewsModal;
import com.nestowl.model.User;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ReviewsModal> reviewsModals;
    ReviewsAdapter adapter;
    User user;

    public ReviewsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_reviews, container, false);
        recyclerView=view.findViewById(R.id.REVIEWS_RECYCLER);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        lnr_firs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), ReviewActivity.class));
//            }
//        });
//        lnr_second.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), ReviewBuyerActivity.class));
//            }
//        });

        user=new Gson().fromJson(PrefMananger.getString(getContext(),"user"),User.class);
        reviewsModals=new ArrayList<>();
        adapter=new ReviewsAdapter(getContext(),reviewsModals);
        fetchReviews();


        return view;
    }
    private void fetchReviews() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.REVIEW_LIST_BY_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String staus =  jsonObject.getString("status");
                    if (staus.equals("1")){
                        JSONArray jsonArray  = jsonObject.getJSONArray("Review");
                        for (int u=0;u<jsonArray.length();u++){
                            ReviewsModal reviewsModal  = new Gson().fromJson(jsonArray.getJSONObject(u).toString(),ReviewsModal.class);
                            reviewsModals.add(reviewsModal);
                        }
                        fechProfile(reviewsModals);
                    }
                }catch (Exception e){
                    Log.e("REVIEWS ERROR", "onResponse: "+e );
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
        Volley.newRequestQueue(getContext()).add(request);
    }

    private void fechProfile(ArrayList<ReviewsModal> reviews) {
        reviewsModals = new ArrayList<>();

        for (int i=0;i<reviews.size();i++){
            ReviewsModal temps =  reviews.get(i);
            StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_ID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject =  new JSONObject(response);
                        String status =  jsonObject.getString("status");
                        if (status.equals("1")){
                            User name =  new Gson().fromJson(jsonObject.getString("data"),User.class);
                            temps.setName(name.getFirst_name()+" "+name.getLast_name());
                            JSONObject jsonObject1  = jsonObject.getJSONObject("Photo");
                            temps.setDp(UrlClass.BASE_URL+jsonObject1.getString("profile_photo"));
                            temps.setTypes("NEST PROS");
                            reviewsModals.add(temps);
                            adapter.notifyDataSetChanged();
                        }
                    }catch (Exception e){
                        Log.e("geting names in reviews", "onResponse: "+e );
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
                    hashMap.put("user_id",temps.getUser_id());
                    return hashMap;
                }
            };
            Volley.newRequestQueue(getContext()).add(request);

        }
        adapter=new ReviewsAdapter(getContext(),reviewsModals);
        recyclerView.setAdapter(adapter);
    }

}
