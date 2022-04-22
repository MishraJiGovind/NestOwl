package com.nestowl.Fragment;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.ImageUploadAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.ImagePOJO;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.VarificationModal;
import com.nestowl.brokerapp.CongratulationEmpty;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.VarifySumbitWarn;
import com.nestowl.utils.FileUtil;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifySubmit extends Fragment {
    TextView tv_gas_bill,tv_electricity,tv_water,tv_tax,tv_property_maintenance,tv_sewarage,tv_property_propert,adhar_card,two_self,varify_sumbit;

    Boolean is_gas_bill_click=false;
    Boolean is_electricity_bill_click=false;
    Boolean is_water_bill_click=false;
    Boolean is_tax_bill_click=false;
    Boolean is_property_maintenance_bill_click=false;
    Boolean is_sewarage_bill_click=false;
    Boolean is_property_paper_click=false;
    Boolean is_aadhar_card_click=false;
    Boolean is_two_self_click=false;
    LinearLayout lnd_adhar_card,lnd_two_self;
    Bitmap verification,verification_or;
    Uri filePath_main,filepathe_or;
    Context context;
    Activity activity;
    ImageUploadAdapter uploadAdapter;
    FrameLayout frm_gas_bill;
    ImageView img_gas_bill,img_electricity_bill,img_water,img_tax,img_property_maintenance,img_sewarage,img_property_paper;
    ImageView img_adhar_front,img_adhar_back,img_two_self_front,img_two_self_back,deleteGas,deleteElectric,deleteWater,deleteTax,deleteProperty,deleteSewage,deletePropertyPaper,deleteAdharFront,deleteAdharBack,deleteSelfBack,deleteSelfFront;
    String verification_name ="";
    String verification_name_or ="",Property_Id,user_id;
    CardView card_continue;
    boolean isUpdating;
    ArrayList<ImagePOJO> bitmapList = new ArrayList<>();
    public VerifySubmit() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=activity=getActivity();
        View view= inflater.inflate(R.layout.fragment_verify_submit, container, false);
        deleteGas=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_GAS);
        deleteElectric=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_ELECTRCITY);
        deleteWater=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_WATER_BILL);
        deleteTax=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_TAX);
        deleteProperty=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_MAINTAENACE);
        deleteSewage=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_SEWARGE);
        deletePropertyPaper=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_PROPERTYBILL);
        deleteAdharFront=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_ADDHAR_FRONT);
        deleteAdharBack=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_ADHAR_BACK);
        deleteSelfFront=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_SELF_FRONT);
        deleteSelfBack=view.findViewById(R.id.VERIFY_SUMBIT_DELETE_SELF_BACK);

        hideDelte();
        if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE)!=null){
            Property_Id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE);
            user_id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER);
            PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER,null);
            PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE,null);
            updateRequest();
            boolean s = PrefMananger.FeatureFarmSet(getContext(),Property_Id);
            if (s){
                getPropertyType();
            }
            isUpdating=true;
            showdeleteoption();
        }

        tv_gas_bill=view.findViewById(R.id.gas_bill);
        tv_electricity=view.findViewById(R.id.electricity_bill);
        adhar_card=view.findViewById(R.id.aadhar_card_id);
        two_self=view.findViewById(R.id.two_self_paper_id);

        lnd_adhar_card=view.findViewById(R.id.lnr_aadhar_card_id);
        lnd_two_self=view.findViewById(R.id.lnr_two_self_id);
        frm_gas_bill=view.findViewById(R.id.frm_gas_bill);

        tv_water=view.findViewById(R.id.water_bill_id);
        tv_tax=view.findViewById(R.id.tax_bill_id);
        tv_property_maintenance=view.findViewById(R.id.property_maintenance_id);
        tv_sewarage=view.findViewById(R.id.sewarage_bill_id);
        tv_property_propert=view.findViewById(R.id.property_paper_id);

        img_adhar_front=view.findViewById(R.id.adhar_front_id);
        img_adhar_back=view.findViewById(R.id.adhar_back_id);
        img_two_self_front=view.findViewById(R.id.two_self_front_id);
        img_two_self_back=view.findViewById(R.id.two_self_back_id);
        varify_sumbit=view.findViewById(R.id.POST_PRO_VARIFY_SUMBIT);


        final LinearLayout l1=view.findViewById(R.id.linear_gas_bill);

        final LinearLayout l2=view.findViewById(R.id.linear_electricity_bill);
        final LinearLayout l3_water=view.findViewById(R.id.linear_water_bill);
        final LinearLayout l4_tax=view.findViewById(R.id.linear_tax_bill);
        final LinearLayout l5_property_maintenance=view.findViewById(R.id.linear_property_maintenance_bill);
        final LinearLayout l6_sewarage=view.findViewById(R.id.linear_sewarage_bill);
        final LinearLayout l7_property_paper=view.findViewById(R.id.linear_property_paper_bill);
        img_gas_bill=view.findViewById(R.id.img_gas_bill);
        img_electricity_bill=view.findViewById(R.id.img_electricity_bill);
        img_water=view.findViewById(R.id.img_water_bill);
        img_tax=view.findViewById(R.id.img_tax_bill);
        img_property_maintenance=view.findViewById(R.id.img_property_maintenance_bill);
        img_sewarage=view.findViewById(R.id.img_sewarage_bill);
        img_property_paper=view.findViewById(R.id.img_property_paper_bill);


        varify_sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), VarifySumbitWarn.class));
            }
        });
        img_adhar_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = SECOND_IMAGE;
                imageView =img_adhar_front;
                SelectImage();

            }
        });
        img_adhar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = THIRDE_IMAGE;
                imageView =img_adhar_back;
                SelectImage();

            }
        });
        img_two_self_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = SECOND_IMAGE;
                imageView =img_two_self_front;
                SelectImage();

            }
        });
        img_two_self_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = THIRDE_IMAGE;
                imageView =img_two_self_back;
                SelectImage();

            }
        });
        img_gas_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView =img_gas_bill;
                SelectImage();

            }
        });
        img_electricity_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_electricity_bill;
                SelectImage();

            }
        });
        img_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_water;
                SelectImage();

            }
        });
        img_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_tax;
                SelectImage();

            }
        });
        img_property_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_property_maintenance;
                SelectImage();

            }
        });
        img_sewarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_sewarage;
                SelectImage();

            }
        });
        img_property_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                imageView=img_property_paper;
                SelectImage();

            }
        });
/*
        uploadAdapter = new ImageUploadAdapter(context, bitma, this);
*/



        card_continue=view.findViewById(R.id.id_save_and_continue);

        card_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadPhotos();
            }
        });


        tv_gas_bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.GONE);

                l1.setVisibility(View.VISIBLE);
                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_gas_bill_click){
                    is_gas_bill_click=false;
                } else {
                    is_gas_bill_click=true;
                    l1.setVisibility(View.VISIBLE);
                    verification_name= tv_gas_bill.getText().toString();

                }
            }
        });
        tv_electricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);

                l2.setVisibility(View.VISIBLE);

                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_electricity_bill_click){
                    is_electricity_bill_click=false;
                } else {
                    is_electricity_bill_click=true;
                    l2.setVisibility(View.VISIBLE);
                    verification_name= tv_electricity.getText().toString();

                }
            }
        });
        tv_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3_water.setVisibility(View.VISIBLE);

                l2.setVisibility(View.GONE);

                l1.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_water_bill_click){
                    is_water_bill_click=false;
                } else {

                    is_water_bill_click=true;
                    l3_water.setVisibility(View.VISIBLE);
                    verification_name= tv_water.getText().toString();


                }

            }
        });
        tv_tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);

                l2.setVisibility(View.GONE);

                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.VISIBLE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_tax_bill_click){
                    is_tax_bill_click=false;
                } else {
                    is_tax_bill_click=true;
                    l4_tax.setVisibility(View.VISIBLE);
                    verification_name= tv_tax.getText().toString();

                }
            }
        });
        tv_property_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);

                l2.setVisibility(View.GONE);

                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.VISIBLE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_property_maintenance_bill_click){
                    is_property_maintenance_bill_click=false;
                } else {
                    is_property_maintenance_bill_click=true;
                    l5_property_maintenance.setVisibility(View.VISIBLE);
                    verification_name= tv_property_maintenance.getText().toString();

                }
            }
        });
        tv_sewarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.VISIBLE);
                l7_property_paper.setVisibility(View.GONE);
                if (is_sewarage_bill_click){
                    is_sewarage_bill_click=false;
                } else {
                    is_sewarage_bill_click=true;
                    l6_sewarage.setVisibility(View.VISIBLE);
                    verification_name= tv_sewarage.getText().toString();

                }
            }
        });
        tv_property_propert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);

                l2.setVisibility(View.GONE);

                l3_water.setVisibility(View.GONE);
                l4_tax.setVisibility(View.GONE);
                l5_property_maintenance.setVisibility(View.GONE);
                l6_sewarage.setVisibility(View.GONE);
                l7_property_paper.setVisibility(View.VISIBLE);
                if (is_property_paper_click){
                    is_property_paper_click=false;
                } else {
                    is_property_paper_click=true;
                    l7_property_paper.setVisibility(View.VISIBLE);
                    verification_name= tv_property_propert.getText().toString();

                }
            }
        });


        adhar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_two_self.setVisibility(View.GONE);

                lnd_adhar_card.setVisibility(View.VISIBLE);
                if (is_aadhar_card_click){
                    is_aadhar_card_click=false;
                } else {
                    is_aadhar_card_click=true;
                    lnd_adhar_card.setVisibility(View.VISIBLE);
                    verification_name_or= adhar_card.getText().toString();

                }
            }
        });
        two_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_two_self.setVisibility(View.VISIBLE);
                lnd_adhar_card.setVisibility(View.GONE);
                if (is_two_self_click){
                    is_two_self_click=false;
                } else {
                    is_two_self_click=true;
                    lnd_two_self.setVisibility(View.VISIBLE);
                    verification_name_or= two_self.getText().toString();

                }
            }
        });
        deleteGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteElectric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteSewage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deletePropertyPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePhoto();
            }
        });
        deleteAdharFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFront();
            }
        });
        deleteAdharBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBack();
            }
        });
        deleteSelfFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFront();
            }
        });
        deleteSelfBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBack();
            }
        });
        return view;
    }

    private void showdeleteoption() {
        deleteGas.setVisibility(View.VISIBLE);
        deleteWater.setVisibility(View.VISIBLE);
        deleteElectric.setVisibility(View.VISIBLE);
        deleteProperty.setVisibility(View.VISIBLE);
        deleteTax.setVisibility(View.VISIBLE);
        deleteSewage.setVisibility(View.VISIBLE);
        deletePropertyPaper.setVisibility(View.VISIBLE);
        deleteAdharFront.setVisibility(View.VISIBLE);
        deleteAdharBack.setVisibility(View.VISIBLE);
        deleteSelfFront.setVisibility(View.VISIBLE);
        deleteSelfBack.setVisibility(View.VISIBLE);
    }
    private void hideDelte(){
        deleteGas.setVisibility(View.GONE);
        deleteWater.setVisibility(View.GONE);
        deleteElectric.setVisibility(View.GONE);
        deleteProperty.setVisibility(View.GONE);
        deleteTax.setVisibility(View.GONE);
        deleteSewage.setVisibility(View.GONE);
        deletePropertyPaper.setVisibility(View.GONE);
        deleteAdharFront.setVisibility(View.GONE);
        deleteAdharBack.setVisibility(View.GONE);
        deleteSelfFront.setVisibility(View.GONE);
        deleteSelfBack.setVisibility(View.GONE);
    }

    private void deletePhoto(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_VERIFICATIION, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                       success();
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void deleteFront(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_VERIFICATION_PHOTO_FRONT, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void deleteBack(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_VERIFICATION_PHOTO_BACK, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        success();
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void success(){
        new WarningDio(getContext(), "Delete Successful.", "OK", null, new WarningDio.Response() {
            @Override
            public void getClicks(int click) {
                updateRequest();
            }
        },false);
    }
    private void getPropertyType() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BASIC, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String stats =  jsonObject.getString("status");
                    if (stats.equals("1")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        JSONObject jsonObject1 =  jsonArray.getJSONObject(0);
                        EditPropertyModal edit=new EditPropertyModal();
                        edit.setId(Property_Id);
                        edit.setPropertyCategory(jsonObject1.getString("property"));
                        String s = new Gson().toJson(edit);
                        PrefMananger.saveString(getContext(),PrefMananger.HANDLE_FEATURES_FARM,s);
                        PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_ID,Property_Id);
                        PrefMananger.FeatureFarmSet(getContext(),Property_Id);
//                        handlerVisiblesForm();
                    }
                }catch (Exception e){
                    Log.e("error", "onResponse: "+e );
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_is",user_id);
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
    }
    private void updateRequest() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_VERIFICATION, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("proResy6", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        VarificationModal propertyModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),VarificationModal.class);
                        setDataFromServer(propertyModal);
                        TextView textView = (TextView) card_continue.getChildAt(0);
                        textView.setText("Update");
                    }
                }catch (Exception e){
                    Log.e("errors", "onResponse: "+e );
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errors", "onResponse: "+error );
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_is",user_id);
                hashMap.put("property_id",Property_Id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);

    }
    private void setDataFromServer(VarificationModal data) {
     if (data.getVerification_name()!=null){
         if (data.getVerification_name().equals("Gas Bill")){
             tv_gas_bill.performClick();
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_gas_bill);
         }
         if (data.getVerification_name().equals("Electricity Bill")){
             tv_electricity.performClick();
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_electricity_bill);
         }
         if (data.getVerification_name().equals("Water Bill")){
             tv_water.performClick();
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_water);
         }
         if (data.getVerification_name().equals("Tax Bill")){
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_tax);
             tv_tax.performClick();
         }
         if (data.getVerification_name().equals("Property Maintenance Bill")){
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_property_maintenance);
             tv_property_maintenance.performClick();
         }
         if (data.getVerification_name().equals("Sewerage Bill")){
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_sewarage);
             tv_sewarage.performClick();
         }
         if (data.getVerification_name().equals("Property Paper")){
             tv_property_propert.performClick();
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo()).placeholder(R.drawable.default_x_x).into(img_property_paper);
         }
     }
     if (data.getVerification_name_or()!=null){
        if (data.getVerification_name_or().equals("Aadhaar Card")){
            adhar_card.performClick();
            Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo_or_back()).placeholder(R.drawable.default_x_x).into(img_adhar_back);
            Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo_or_front()).placeholder(R.drawable.default_x_x).into(img_adhar_front);
        }
         if (data.getVerification_name_or().equals("Self")){
             adhar_card.performClick();
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo_or_back()).placeholder(R.drawable.default_x_x).into(img_two_self_back);
             Glide.with(getActivity()).load(UrlClass.BASE_URL+data.getVerification_photo_or_front()).placeholder(R.drawable.default_x_x).into(img_two_self_front);
         }
     }
    }
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    String FIRST_IMAGE = "FirstImage";
    String SECOND_IMAGE = "SecondImage";
    String THIRDE_IMAGE = "ThirdImage";
    String FOURTH_IMAGE = "FourthImage";
    String CLICK_ON = "";
    ImageView iv_click_view;
    private void SelectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 200);
                    return;
                }
                if (options[which].equals("Take Photo")) {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                } else if (options[which].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                } else if (options[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public File bitmapToFile(Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {
          /*  String path = Environment.getExternalStorageDirectory() + File.separator + getString(R.string.app_name);
            File directory = new File(path);*/

            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + fileNameToSave);

            file.createNewFile();

//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }
    Bitmap bitmap;
    Uri filePath;


    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;
    Uri filePath1;
    Uri filePath2;
    Uri filePath3;

    ImageView imageView;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                bitmap = (Bitmap) data.getExtras().get("data");
//                imageView.setImageBitmap(bitmap);
                Glide.with(VerifySubmit.this).load(bitmap).placeholder(R.drawable.default_x_x).into(imageView);

                Log.e("1258963", bitmap + "");
                File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(file1);
                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;

/*
                    office_photo_img.setImageBitmap(bitmap);
*/
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                }
            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
//                    imageView.setImageBitmap(bitmap);
                    Glide.with(VerifySubmit.this).load(bitmap).placeholder(R.drawable.default_x_x).into(imageView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;
                    filePath1 = filePath;

                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    filePath2 = filePath;

                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    filePath3 = filePath;

                }
            }
        }
    }
    private void uploadPhotos() {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();

        try {


            //   RequestBody subCategory = RequestBody.create(MultipartBody.FORM, UtilityFunction.getCalculatedDate("yyyy-MM-dd",0));

            File file1 = null;
            int compressionRatio = 25; //1 == originalImage, 2 = 50% compression, 4=25% compress

            File file2;
            MultipartBody.Part logoPart=null;
            if (filePath3 != null) {

                file2 = FileUtil.from(context, filePath3);

                try {
                    //Bitmap bitmap = BitmapFactory.decodeFile (file1.getPath ());
                    //bitmap.compress (Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file1));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                   /* if (videoUri!=null && !videoSavingPath.isEmpty()){
                        file1=new File(videoUri.getPath());
                    }*/
                RequestBody videoPart = null;
                try {

                    Bitmap bmp = BitmapFactory.decodeFile(file2.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(context.getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file2.getName());
                    Log.e("Type", file2.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file2);
                }

                logoPart = MultipartBody.Part.createFormData("verification_photo_or_back", file2.getName(), videoPart);


            } else if (bitmap3!=null){
                file2 = bitmapToFile(bitmap3, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file2.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file2));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file2);
                if (file2.getName() == null) {

                } else {
                    logoPart = MultipartBody.Part.createFormData("verification_photo_or_back", file2.getName(), videoPart);

                }
            }
            File file3;
            MultipartBody.Part coverPart=null;
            if (filePath1 != null) {

                file3 = FileUtil.from(context, filePath1);

                try {
                    //Bitmap bitmap = BitmapFactory.decodeFile (file1.getPath ());
                    //bitmap.compress (Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file1));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                   /* if (videoUri!=null && !videoSavingPath.isEmpty()){
                        file1=new File(videoUri.getPath());
                    }*/
                RequestBody videoPart = null;
                try {

                    Bitmap bmp = BitmapFactory.decodeFile(file3.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(context.getContentResolver().getType(filePath1)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file3.getName());
                    Log.e("Type", file3.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file3);
                }

                coverPart = MultipartBody.Part.createFormData("verification_photo", file3.getName(), videoPart);


            } else if (bitmap1!=null){
                file3 = bitmapToFile(bitmap1, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file3.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file3));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file3);
                if (file3.getName() == null) {

                } else {
                    coverPart = MultipartBody.Part.createFormData("verification_photo", file3.getName(), videoPart);

                }
            }
            File file4;
            MultipartBody.Part profile_photoPart=null;
            if (filePath2 != null) {

                file4 = FileUtil.from(context, filePath2);

                try {
                    //Bitmap bitmap = BitmapFactory.decodeFile (file1.getPath ());
                    //bitmap.compress (Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file1));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                   /* if (videoUri!=null && !videoSavingPath.isEmpty()){
                        file1=new File(videoUri.getPath());
                    }*/
                RequestBody videoPart = null;
                try {

                    Bitmap bmp = BitmapFactory.decodeFile(file4.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(context.getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file4.getName());
                    Log.e("Type", file4.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file4);
                }

                profile_photoPart = MultipartBody.Part.createFormData("verification_photo_or_front", file4.getName(), videoPart);


            } else if (bitmap2!=null){
                file4 = bitmapToFile(bitmap2, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file4.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file4));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file4);
                if (file4.getName() == null) {

                } else {
                    profile_photoPart = MultipartBody.Part.createFormData("verification_photo_or_front", file4.getName(), videoPart);

                }
            }


            RequestBody userId = RequestBody.create(MultipartBody.FORM, PrefMananger.GetLoginData(context).getUserId() + "");
            RequestBody verification = RequestBody.create(MultipartBody.FORM, verification_name + "");
            RequestBody property_id = RequestBody.create(MultipartBody.FORM, PrefMananger.getString(context,PrefMananger.PROPERTY_ID) + "");
            RequestBody verification_nam = RequestBody.create(MultipartBody.FORM,   verification_name_or);
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            //  RequestBody subCategory = RequestBody.create(MultipartBody.FORM, subCategoryId + "");
            HashMap<String, RequestBody> map = new HashMap<>();
            map.put("user_id", userId);
            map.put("verification_name", verification);
            map.put("verification_name_or", verification_nam);
            map.put("property_id", property_id);
            map.put("status", status);

            ApiExecutor.getApiService().photosVerification(
                    profile_photoPart,coverPart,logoPart,map
            ).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()){
                        if (isUpdating){
                            if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)!=null){
                                startActivity(new Intent(getContext(), CongratulationEmpty.class));
                                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY,null);
                                return;
                            }
                            new WarningDio(getContext(), "Updated Successfully", "OK", "CANCEL", new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    getActivity().finish();
                                }
                            },false);
                        }else {
                            startActivity(new Intent(getContext(), CongratulationEmpty.class));

                        }
                    }else {
                        Log.e("verify", "onResponse: "+response );
                    }
                }

                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    dialog.dismiss();
                }
            });
        } catch (Exception e) {
            Log.e("verify",e.toString());
            e.printStackTrace();
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }


}
