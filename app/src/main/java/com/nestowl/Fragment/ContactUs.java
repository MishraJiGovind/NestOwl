package com.nestowl.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {
    EditText name,phone,email,qury;
    CardView sumbit;

    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        name=view.findViewById(R.id.PROFILE_CONTACT_US_NAME);
        phone=view.findViewById(R.id.PROFILE_CONTACT_US_PHONE);
        email=view.findViewById(R.id.PROFILE_CONTACT_US_EMAIL_ADDRESS);
        qury=view.findViewById(R.id.PROFILE_CONTACT_US_QURY);
        sumbit=view.findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().length()<0){
                    name.setError("Enter Name Here.");
                    name.requestFocus();
                    return;
                }
                if (phone.getText().length()!=10){
                    phone.setError("Enter correct Number Here.");
                    phone.requestFocus();
                    return;
                }
                if (email.getText().length()<0){
                    email.setError("Enter Email Here.");
                    email.requestFocus();
                    return;
                }
                postToserver();
            }
        });

        return view;
    }

    private void postToserver() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Summiting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.POST_CONTACTS_US, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(getContext(), "Contact request summited", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("mobile",phone.getText().toString());
                hashMap.put("msg",qury.getText().toString());
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }

}
