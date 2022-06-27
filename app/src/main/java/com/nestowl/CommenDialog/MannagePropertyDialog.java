package com.nestowl.CommenDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.PopupMannageModal;
import com.nestowl.brokerapp.EditingScreenAll;
import com.nestowl.brokerapp.InterestedPropertyThirdUser;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MannagePropertyDialog {
    Context context;
    String user_id,property_id;
    PopupMannageModal popupMannageModal;
    boolean isContact,isFreeze;
    Dialog dialog ;
    public MannagePropertyDialog(Context context, String user_id, String property_id, PopupMannageModal popupMannageModal) {
        this.context = context;
        this.user_id = user_id;
        this.popupMannageModal=popupMannageModal;
        this.property_id = property_id;
        isContact=false;
        isFreeze=false;
        checkStatus();
        dialog = new Dialog(context);
    }
    private void checkStatus() {
        ProgressDialog pd =  new ProgressDialog(context);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_FREEZE_CONTATC_STATUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String sttaus= jsonObject.getString("status");
                    if (sttaus.equals("1")){
                        JSONObject Freeze= jsonObject.getJSONObject("Freeze");
                        JSONObject jsonObject1 = jsonObject.getJSONObject("Contact");
                        String contact = jsonObject1.getString("hide_contact_details");
                        String freezestatus = Freeze.getString("freeze");
                        if (contact!=null){
                            if (contact.equals("Yes")){
                                isContact=true;
                            }else {
                                isContact=false;
                            }
                        }

                        if (freezestatus!=null){
                        if (freezestatus.equals("Yes")){
                            isFreeze=true;
                        }else {
                            isFreeze=false;
                        }
                        }else {
                            isFreeze=false;
                        }
                        showPopup();
                    }
                }catch (Exception e){

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
                hashMap.put("property_id",property_id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    public void showPopup(){

        dialog.setContentView(R.layout.manage_property_five);
        TextView id,status,posted,catogory;

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);

        ImageView imgs=dialog.findViewById(R.id.POPUP_PROPERTY_DISMISSED);
        FrameLayout frm_preview=dialog.findViewById(R.id.POPUP_PROPERTY_PRIVIEW);
        FrameLayout frm_edit5=dialog.findViewById(R.id.POPUP_PROPERTY_EDIT);
        FrameLayout frm_show_contact=dialog.findViewById(R.id.POPUP_PROPERTY_HIDE_CONTACT);
        FrameLayout FREEZE=dialog.findViewById(R.id.POPUP_PROPERTY_FREEZE);
        id=dialog.findViewById(R.id.POPUP_PROPERTY_ID);
        status=dialog.findViewById(R.id.POPUP_PROPERTY_STATUS);
        posted=dialog.findViewById(R.id.POPUP_PROPERTY_POSTED_TO);
        catogory=dialog.findViewById(R.id.POPUP_PROPERTY_CATEGORY_STATUS);
        id.setText(popupMannageModal.getId());
        posted.setText(popupMannageModal.getPostedto());
        status.setText("Status :"+popupMannageModal.getStatus());
        status.setSelected(true);
        posted.setSelected(true);
        id.setSelected(true);
        catogory.setSelected(true);
        catogory.setText(popupMannageModal.getCatogory());
        if (!isFreeze){
            TextView textView =  (TextView) FREEZE.getChildAt(0);
            textView.setText("Freeze Property");
        }else {
            TextView textView =  (TextView) FREEZE.getChildAt(0);
            textView.setText("UnFreeze Property");
        }
        if (isContact){
            TextView textView =  (TextView) frm_show_contact.getChildAt(0);
            textView.setText("Hide Contact");
        }else {
            TextView textView =  (TextView) frm_show_contact.getChildAt(0);
            textView.setText("Show Contact");
        }
        dialog.show();
        frm_show_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showcontactDialog();
               if (isContact){
                   new WarningDio(context, "Do You want to Show Your Contact.", "Yes", "No", new WarningDio.Response() {
                       @Override
                       public void getClicks(int click) {
                           if (click==1){
                                showHideContact("Yes");
                           }
                       }
                   },false);
               }else {
                   new WarningDio(context, "Do You want to Hide Your Contact.", "Yes", "No", new WarningDio.Response() {
                       @Override
                       public void getClicks(int click) {
                           if (click==1){
                                showHideContact("No");
                           }
                       }
                   },false);
               }
                dialog.dismiss();
            }
        });
        FrameLayout frm_dlt=dialog.findViewById(R.id.POPUP_PROPERTY_DELETE);
        frm_dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deletetDialog();
                new WarningDio(context, "Do you want to delete your property", "YES", "NO", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){

                        }
                    }
                },false);
                dialog.dismiss();
            }
        });
        frm_edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                openShowContactDialog();
                new WarningDio(context, "Do you Want edit your property", "YES", "NO", new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {
                        if (click==1){
                            Intent intent=new Intent(context, EditingScreenAll.class);
                            intent.putExtra("user_id", user_id);
                            intent.putExtra("idd", property_id);
                            intent.putExtra("id", property_id);
                            context.startActivity(intent);
                        }
                    }
                },false);
                dialog.dismiss();
            }
        });
        frm_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, InterestedPropertyThirdUser.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("idd", property_id);
                intent.putExtra("id", property_id);
                intent.putExtra("isBroker", true);
                context.startActivity(intent);
            }
        });
        imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        FREEZE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFreeze){
                    new WarningDio(context, "Do you want to freeze your property", "YES", "NO", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                freeezePro();
                            }
                        }
                    },false);
                }else {
                    new WarningDio(context, "Do you want to unfreeze your property", "YES", "NO", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                unfreezePro();
                            }
                        }
                    },false);
                }
            }
        });
    }
    private void unfreezePro() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_FREEZE_NO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")){
                        new WarningDio(context, "Property Unfreeze Successfully.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                dialog.dismiss();
                                checkStatus();
                            }
                        },false);
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
                hashMap.put("property_id",property_id);
                hashMap.put("active","No");
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void freeezePro() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_FREEZE_YES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("1")){
                        new WarningDio(context, "Property Freeze Successfully.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                dialog.dismiss();
                                checkStatus();
                            }
                        },false);
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
                hashMap.put("property_id",property_id);
                hashMap.put("active","Yes");
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
    private void showHideContact(String set){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.SET_PROPRTY_CONTATC_VISBILITY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String sttaus= jsonObject.getString("status");
                    if (sttaus.equals("1")){
                        new WarningDio(context, "Contact status updated", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                dialog.dismiss();
                                checkStatus();
                            }
                        },false);

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
                hashMap.put("hide_contact_details",set);
                hashMap.put("property_id",property_id);
                hashMap.put("user_id",user_id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
