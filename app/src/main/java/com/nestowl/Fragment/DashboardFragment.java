package com.nestowl.Fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.ProfileDashBoardModal;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    TextView todayLeads,todayListing,todayLive,overallRemaing,overallListing,overallProposalAccepted,overallProposalRejected,overallLeads;
    RadioButton rad_today,rad_overall;
    LinearLayout lnd_owner,lnd_agent;
    String userId;
    Boolean is_Residential_click = false;
    LoginPojo loginPojo;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);
        lnd_owner=view.findViewById(R.id.lnd_today);
        lnd_agent=view.findViewById(R.id.lnd_overall);
        rad_today=view.findViewById(R.id.radio_rq);
        rad_overall=view.findViewById(R.id.radiobutton_rq);
        todayLeads=view.findViewById(R.id.PROFILE_DASHBOARD_TODAY_LEADS);
        todayListing=view.findViewById(R.id.PROFILE_DASHBOARD_TODAY_LISTING);
        todayLive=view.findViewById(R.id.PROFILE_DASHBOARD_TODAY_LIVE_LISTING);
        overallRemaing=view.findViewById(R.id.PROFILE_DASHBOARD_OVERALL_REMAIN_LISTING);
        overallListing=view.findViewById(R.id.PROFILE_DASHBOARD_OVERALL_POSTED_LIST);
        overallProposalAccepted=view.findViewById(R.id.PROFILE_DASHBOARD_OVERALL_PROPOSAL_ACCEPTD);
        overallProposalRejected=view.findViewById(R.id.PROFILE_DASHBOARD_OVERALL_PROPOSAL_REJECTED);
        overallLeads=view.findViewById(R.id.PROFILE_DASHBOARD_OVERALL_TOTAL_LEADS);

        loginPojo= PrefMananger.GetLoginData(getContext());
        userId=String.valueOf(loginPojo.getUserId());

        fetchToday();
        fetchOverAll();
        rad_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_owner.setVisibility(View.VISIBLE);
                lnd_agent.setVisibility(View.GONE);
            }
        });
        rad_overall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_agent.setVisibility(View.VISIBLE);
                lnd_owner.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void fetchOverAll() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_DASHBOARD_OVERALL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ProfileDashBoardModal data = new Gson().fromJson(response.toString(),ProfileDashBoardModal.class);
                    if (data.getStatus().equals("1")){
                        overallLeads.setText("Total Leads: "+data.getTotalLeads());
                        overallRemaing.setText("Total: "+data.getRemainingTotalListing());
                        overallListing.setText("Listing Posted: "+data.getTotalListingposted());
                        overallProposalAccepted.setText("Proposal Accepted: "+data.getTillTodayProposalAccepted());
                        overallProposalRejected.setText("Proposal Rejected: "+data.getTillTodayProposalRejected());
                    }
                }catch (Exception e){
                    e.printStackTrace();
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
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void fetchToday() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_DASHBOARD_TODAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ProfileDashBoardModal data = new Gson().fromJson(response.toString(),ProfileDashBoardModal.class);
                    if (data.getStatus().equals("1")){
                        todayLeads.setText("Leads: "+data.getTotalNoofLead());
                        todayListing.setText("Total: "+data.getTotalNoofListing());
                        todayLive.setText("Total: "+data.getTotalNoofLiveListing());
                    }
                }catch (Exception e){
                    e.printStackTrace();
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
                hashMap.put("user_id",userId);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

}
