package com.nestowl.Fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.PostImagesRecyclerAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.PhotoModal;
import com.nestowl.model.PostImagesRecuclerModal;
import com.nestowl.model.PropertyPhotoViewModal;
import com.nestowl.model.aichat;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.utils.GetFiles;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotosSchedule extends Fragment {
    RadioButton rad_exterior_view,rad_living,rad_bedroom,rad_bathrooms,site_view, rad_kitchen, rad_floor_ploan,rad_master_plan,rad_location_map,rad_other,rad_siteView,rad_all;
    PlanBasicActivity planBasicActivity;
    FrameLayout frm_schedul,ADDIMAGES,CAPTUREWITHCAM,Delete,rera,contactYes,contactNo,mainImage;
    CardView card_save;
    LinearLayout l_one,setDefaults;
    ArrayList<Uri> temp,extiriear,livingg,bedroomss,bathrooms,siteView,kicthens,floors,masterplans,locations,others,sites,dmmy;
    public PhotosSchedule() {
        // Required empty public constructor
    }

    Context context;
    Activity activity;
    LiveCommnication liveCommnication;
    RecyclerView recyclerView;
    PostImagesRecuclerModal postImagesRecuclerModal;
    ArrayList<PostImagesRecuclerModal> arrayListModal;
    PostImagesRecyclerAdapter adapter;
    String datais,pid,Property_Id,user_id;
    ArrayList<Uri> uri;
    Boolean isUpdating;
    int diotype,switch1,switch2,bgactive,bgdefaults,RERA,CONTACT;
    MultipartBody.Part[] exteriorPart,livingRoomPart,bathroomsPart,siteViewPart,kicthenPart,floorsPart,masterplansPart,locationsPart,otherPart,sitesPart;
    GetFiles getFiles;
    PhotoModal photoModalPublic =  new PhotoModal();
    ArrayList<PropertyPhotoViewModal> photoViewModals=new ArrayList<>();
    TextView reraYesNpo ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_photos_schedule, container, false);
        isUpdating=false;
        if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE)!=null){
            Property_Id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE);
            user_id = PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER);
            if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)==null){
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE_USER,null);
                PrefMananger.saveString(getContext(),PrefMananger.PROPERTY_TO_UPDATE,null);
            }
            updateRequest();
            boolean s = PrefMananger.FeatureFarmSet(getContext(),Property_Id);
            if (s){
                getPropertyType();
            }
            isUpdating=true;
        }
        planBasicActivity= (PlanBasicActivity) getActivity();
        liveCommnication = ViewModelProviders.of(getActivity()).get(LiveCommnication.class);
        card_save=view.findViewById(R.id.POST_PHOTO_CONTINEUE);
        frm_schedul=view.findViewById(R.id.POST_PHOTO_SLELECT_SCHDULED);
        l_one=view.findViewById(R.id.lnds_linear_one);
        context=activity=getActivity();
        getFiles= new GetFiles(context);
        ADDIMAGES=view.findViewById(R.id.POST_PHOTO_SELCET_IMG);
        CAPTUREWITHCAM=view.findViewById(R.id.POST_PHOTO_TAKE_PICTURE);
        Delete=view.findViewById(R.id.POST_PHOTO_DELETE_IMG);
        rera=view.findViewById(R.id.POST_PHOTO_RERA);
        contactYes=view.findViewById(R.id.POST_PHOTO_HIDE_C_YES);
        contactNo=view.findViewById(R.id.POST_PHOTO_HIDE_C_NO);
        setDefaults=view.findViewById(R.id.POST_PHOTO_SET_DEFAULTS);
        mainImage=view.findViewById(R.id.POST_PHOTO_MAIN_IMAGE);
        recyclerView=view.findViewById(R.id.POST_IMAGES_RECYCLER);
        reraYesNpo=view.findViewById(R.id.POST_PRO_PHOTO_RERA_YES_NO);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        aichat i= new aichat();
        i.setText("getId");
        liveCommnication.setText(i);
        liveCommnication.getText().observe(getActivity(), new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                aichat i = aichat;
                String key =  i.getText();
                String value =  i.getValue();
                if (key.equals("response")){
                    pid = value;
                }
            }
        });

        temp = new ArrayList<>();
        extiriear = new ArrayList<>();
        livingg = new ArrayList<>();
        bedroomss = new ArrayList<>();
        bathrooms = new ArrayList<>();
        siteView = new ArrayList<>();
        kicthens = new ArrayList<>();
        floors = new ArrayList<>();
        masterplans = new ArrayList<>();
        locations = new ArrayList<>();
        others = new ArrayList<>();
        uri=new ArrayList<>();
        arrayListModal =  new ArrayList<>();
        sites = new ArrayList<>();
        dmmy=new ArrayList<>();

        diotype=0;
        switch1=R.drawable.toggle_button;
        switch2=R.drawable.swithc_to_owner;
        bgdefaults=R.drawable.employe_circle_rounded;
        bgactive=R.drawable.selected_background_filter;
        RERA=0;
        CONTACT=0;

        rera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RERA==0){
                    rera.setBackgroundResource(switch1);
                    reraYesNpo.setText("Yes");
                    RERA=1;
                    return;
                }
                if (RERA==1){
                    rera.setBackgroundResource(switch2);
                    reraYesNpo.setText("No");
                    RERA=0;
                }

            }
        });
        ADDIMAGES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
                diotype=1;

            }
        });
        CAPTUREWITHCAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
                diotype=2;
            }
        });
        frm_schedul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopup();
                diotype=3;
            }
        });
        contactYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactYes.setBackgroundResource(bgactive);
                contactNo.setBackgroundResource(bgdefaults);
                CONTACT=1;
            }
        });
        contactNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactYes.setBackgroundResource(bgdefaults);
                contactNo.setBackgroundResource(bgactive);
                CONTACT=2;
            }
        });
        card_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSchedule();


            }
        });
        return view;
    }
    private void getPropertyType() {
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_BASIC, new Response.Listener<String>() {
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
        }, new Response.ErrorListener() {
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
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.GET_PROPERTY_PHOTO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("proResy6", "onResponse: "+response );
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")&&jsonObject.has("data")){
                        JSONArray jsonArray =  jsonObject.getJSONArray("data");
                        JSONArray jsonArray1 = jsonObject.getJSONArray("Photo_data");
                        PhotoModal propertyModal = new Gson().fromJson(jsonArray.getJSONObject(0).toString(),PhotoModal.class);
                        TextView textView = (TextView) card_save.getChildAt(0);
                        textView.setText("Update");
                        if (propertyModal.getHide_contact_details().equals("Yes")){
                            contactYes.performClick();
                        }else {
                            contactNo.performClick();
                        }
                        for (int i=0;i<jsonArray1.length();i++){
                            PropertyPhotoViewModal propertyPhotoViewModal =  new Gson().fromJson(jsonArray1.getJSONObject(i).toString(),PropertyPhotoViewModal.class);
                            photoViewModals.add(propertyPhotoViewModal);
                        }
                        setDataFromServer(photoViewModals);
                    }
                }catch (Exception e){
                    Log.e("errors", "onResponse: "+e );
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
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
    private void setDataFromServer(ArrayList<PropertyPhotoViewModal> data) {
        for (PropertyPhotoViewModal ds :data){
            String type  =ds.getType();
            if (type.equals("PPSiteView")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("SiteView");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPCommonArea")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Common");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPExteriorView")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Exterior");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPLivingRoom")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Living Room");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPBathrooms")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Bathrooms");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPBedroom")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Bedroom");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPKitchen")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Kitchen");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPFloor")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Floor");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPMasterPlan")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Master");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());;
                arrayListModal.add(d);
            }
            if (type.equals("PPLocation")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Location");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPOther")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Other");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
            if (type.equals("PPWashroom")){
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setIsFromServer("true");
                d.setSetAscover("false");
                d.setImageFrom("Washroom");
                d.setId(ds.getId());
                d.setPropertyId(ds.getCategory_id());
                d.setImage(UrlClass.BASE_URL2+ds.getFilename());
                arrayListModal.add(d);
            }
        }
        adapter =  new PostImagesRecyclerAdapter(getContext(),arrayListModal,datais);
        recyclerView.setAdapter(adapter);



    }
    private void openPopup() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.changephotodchedul);
        LinearLayout extriar,living,bedrooms,bathroom,kicthen,floor,masterplan,location,other,contine,siteView,all;
        extriar=dialog.findViewById(R.id.POST_SCDULEDE_DIO_EXTRIER);
        living =dialog.findViewById(R.id.POST_SCDULEDE_DIO_LIVING_ROOM);
        bedrooms =dialog.findViewById(R.id.POST_SCDULEDE_DIO_BEDROOMS);
        bathroom =dialog.findViewById(R.id.POST_SCDULEDE_DIO_BATHROOMS);
        kicthen=dialog.findViewById(R.id.POST_SCDULEDE_DIO_KITCHEN);
        floor=dialog.findViewById(R.id.POST_SCDULEDE_DIO_FLOOR);
        masterplan=dialog.findViewById(R.id.POST_SCDULEDE_DIO_MASTER_PLAN);
        location=dialog.findViewById(R.id.POST_SCDULEDE_DIO_LOCATION);
        other=dialog.findViewById(R.id.POST_SCDULEDE_DIO_OTHER);
        contine=dialog.findViewById(R.id.POST_SCDULEDE_DIO_CONTINIUE);
        siteView=dialog.findViewById(R.id.POST_SCDULEDE_DIO_SITE_VIEW);
        all=dialog.findViewById(R.id.POST_SCDULEDE_DIO_ALL);
        if (diotype==3){
            all.setVisibility(View.VISIBLE);
        }
        rad_exterior_view =dialog.findViewById(R.id.exterior_view_id);
        rad_living =dialog.findViewById(R.id.living_room_id);
        rad_bedroom =dialog.findViewById(R.id.bedroom_id);
        rad_bathrooms =dialog.findViewById(R.id.bathrooms_id);
        rad_kitchen =dialog.findViewById(R.id.kitchen_id);
        rad_floor_ploan =dialog.findViewById(R.id.floor_plan_id);
        rad_master_plan =dialog.findViewById(R.id.master_plan_id);
        rad_location_map =dialog.findViewById(R.id.location_map_id);
        rad_other =dialog.findViewById(R.id.other_id);
        rad_siteView =dialog.findViewById(R.id.POST_SCDULEDE_DIO_SITE_VIEW_RAD);
        rad_all =dialog.findViewById(R.id.POST_SCDULEDE_DIO_ALL_RAD);

        extriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(true);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        living.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(true);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        bedrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(true);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(true);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        kicthen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(true);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_all.setChecked(false);
                rad_siteView.setChecked(false);
            }
        });
        floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(true);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        masterplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(true);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(true);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(true);
                rad_siteView.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        siteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_siteView.setChecked(true);
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_all.setChecked(false);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rad_all.setChecked(true);
                rad_siteView.setChecked(true);
                rad_exterior_view.setChecked(false);
                rad_living.setChecked(false);
                rad_bedroom.setChecked(false);
                rad_bathrooms.setChecked(false);
                rad_kitchen.setChecked(false);
                rad_floor_ploan.setChecked(false);
                rad_master_plan.setChecked(false);
                rad_location_map.setChecked(false);
                rad_other.setChecked(false);
                rad_siteView.setChecked(false);
            }
        });
        contine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rad_exterior_view.isChecked()){
                    datais="Exterior";
                }
                if (rad_living.isChecked()){
                    datais="Living";
                }
                if (rad_bedroom.isChecked()){
                    datais="Bedroom";
                }
                if (rad_bathrooms.isChecked()){
                    datais="Bathrooms";
                }
                if (rad_kitchen.isChecked()){
                    datais="Kitchen";
                }
                if (rad_floor_ploan.isChecked()){
                    datais="Floor";
                }
                if (rad_master_plan.isChecked()){
                    datais="Master";
                }
                if (rad_location_map.isChecked()){
                    datais="Location";
                }
                if (rad_other.isChecked()){
                    datais="Other";
                }
                if (rad_siteView.isChecked()){
                    datais="SiteView";
                }
                if (rad_all.isChecked()){
                    recyclerView.removeAllViews();
                    adapter =  new PostImagesRecyclerAdapter(getContext(), arrayListModal,datais);
                    recyclerView.setAdapter(adapter);
                    diotype=0;
                    dialog.dismiss();
                    return;
                }
                if (diotype==1){
                    Intent intent =  new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 111);
                    diotype=0;
                    dialog.dismiss();
                    return;
                }
                if (diotype==2){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 112);
                    diotype=0;
                    dialog.dismiss();
                    return;
                }
                ArrayList<PostImagesRecuclerModal> h = new ArrayList<>();
                for (PostImagesRecuclerModal tmp : arrayListModal){
                    if (tmp.getImageFrom().equals(datais)){
                        h.add(tmp);
                    }
                }
                recyclerView.removeAllViews();
                adapter =  new PostImagesRecyclerAdapter(getContext(), h,datais);
                recyclerView.setAdapter(adapter);
                TextView textView = (TextView) frm_schedul.getChildAt(0);
                textView.setText(datais);
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
    private void photoSchedule() {
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Saving...");
        pd.setCancelable(false);
        pd.show();
        
        if (arrayListModal.isEmpty()){
            Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_LONG);
            return;
        }
        exteriorPart =  new MultipartBody.Part[extiriear.size()];
        for (int i=0;i<extiriear.size();i++){
            exteriorPart[i]=getFiles.getPartBody("exterior_view[]",extiriear.get(i));
        }

        livingRoomPart =  new MultipartBody.Part[livingg.size()];
        for (int i=0;i<livingg.size();i++){
            livingRoomPart[i]=getFiles.getPartBody("living_room[]",livingg.get(i));
        }

        bathroomsPart=new MultipartBody.Part[bathrooms.size()];
        for (int i=0;i<bathrooms.size();i++){
            bathroomsPart[i]=getFiles.getPartBody("p_bathrooms[]",bathrooms.get(i));
        }

        siteViewPart=new MultipartBody.Part[siteView.size()];
        for (int i=0;i<siteView.size();i++){
            siteViewPart[i]=getFiles.getPartBody("site_view[]",siteView.get(i));
        }

        kicthenPart=new MultipartBody.Part[kicthens.size()];
        for (int i=0;i< kicthens.size();i++){
            kicthenPart[i]=getFiles.getPartBody("kitchen[]",kicthens.get(i));
        }

        floorsPart=new MultipartBody.Part[floors.size()];
        for (int i=0;i<floors.size();i++){
            floorsPart[i]=getFiles.getPartBody("floor_plan[]",floors.get(i));
        }

        masterplansPart=new MultipartBody.Part[masterplans.size()];
        for (int i=0;i<masterplans.size();i++){
            masterplansPart[i]=getFiles.getPartBody("master_plan[]",masterplans.get(i));
        }

        locationsPart=new MultipartBody.Part[locations.size()];
        for (int i=0;i<locations.size();i++){
            locationsPart[i]=getFiles.getPartBody("location_map[]",locations.get(i));
        }

        otherPart=new MultipartBody.Part[others.size()];
        for (int i=0;i<others.size();i++){
            otherPart[i]=getFiles.getPartBody("others[]",others.get(i));
        }
        RequestBody userId = RequestBody.create(MultipartBody.FORM, String.valueOf(PrefMananger.GetLoginData(context).getUserId()));
        if (!isUpdating){
            Property_Id =  PrefMananger.getString(context,PrefMananger.PROPERTY_ID);
        }
        RequestBody status = RequestBody.create(MultipartBody.FORM, Property_Id);
        RequestBody postedTo = null;
        if (isUpdating){
            if (photoModalPublic.getProperypost()!=null){
            postedTo = RequestBody.create(MultipartBody.FORM, photoModalPublic.getProperypost());
            }else {
            postedTo = RequestBody.create(MultipartBody.FORM, "Public");

            }
        }else {
        postedTo = RequestBody.create(MultipartBody.FORM, PrefMananger.getString(context, "posted"));
        }
        Log.e("properytyId", "photoSchedule: "+PrefMananger.getString(context, PrefMananger.PROPERTY_ID) );
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("property_id", status);
        map.put("properypost", postedTo);
        if (CONTACT==1){
            RequestBody contact = RequestBody.create(MultipartBody.FORM, "Yes");
            map.put("hide_contact_details",contact);
        }
        if (CONTACT==2){
            RequestBody contact = RequestBody.create(MultipartBody.FORM, "No");
            map.put("hide_contact_details",contact);
        }
        ApiExecutor.getApiService().uploadPropertyPhotos(
                siteViewPart,
                exteriorPart,
                livingRoomPart,
                bathroomsPart,
                kicthenPart,
                floorsPart,
                masterplansPart,
                locationsPart,
                otherPart,
                map

        ).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, retrofit2.Response<OtpResponse> response) {
                Log.e("response Images", "onResponse: "+response);
                pd.dismiss();
                if (response.isSuccessful()){
                   if (isUpdating){
                       if (PrefMananger.getString(getContext(),PrefMananger.PROPERTY_TO_INCOMPLETEVERIFY)!=null){
                           aichat ai = new aichat();
                           ai.setText("TAB");
                           ai.setValue("5");
                           ai.setValues("tb");
                           liveCommnication.setText(ai);
                           return;
                       }
                       new WarningDio(getContext(), "Updated Successfully", "OK", "CANCEL", new WarningDio.Response() {
                           @Override
                           public void getClicks(int click) {
                               getActivity().finish();
                           }
                       },false);
                   }else {
                       aichat ai = new aichat();
                       ai.setText("TAB");
                       ai.setValue("5");
                       ai.setValues("tb");
                       liveCommnication.setText(ai);
                   }
                }else {
                    new WarningDio(getContext(), response.message(), "OK", "CANCEL", new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                pd.dismiss();
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==111&data.getClipData()!=null){
                int count = data.getClipData().getItemCount();
                int max = uri.size();
                for (int i=0;i<count;i++){
                    uri.add(data.getClipData().getItemAt(i).getUri());
                    PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                    d.setImageLink(uri.get(max+i));
                    d.setImageFrom(datais);
                    d.setSetAscover("false");
                    d.setIsFromServer("false");
                    if (datais.equals("Exterior")){
                        extiriear.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Living")){
                        livingg.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Bedroom")){
                        bedroomss.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Bathrooms")){
                        bathrooms.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Kitchen")){
                        kicthens.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Floor")){
                        floors.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Master")){
                        masterplans.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Location")){
                        locations.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("Other")){
                        others.add(data.getClipData().getItemAt(i).getUri());
                    }
                    if (datais.equals("SiteView")){
                        siteView.add(data.getClipData().getItemAt(i).getUri());
                    }
                    arrayListModal.add(d);
                    Log.e("arrey", "onActivityResult: "+arrayListModal );
                }
                adapter =  new PostImagesRecyclerAdapter(getContext(), arrayListModal,datais);
                recyclerView.setAdapter(adapter);
                TextView textView = (TextView) frm_schedul.getChildAt(0);
                textView.setText("Select");

            }else if(data.getData() != null) {
                Uri imagePath =data.getData();
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setImageLink(imagePath);
                d.setImageFrom(datais);
                d.setSetAscover("false");
                d.setIsFromServer("false");
                if (datais.equals("Exterior")){
                    extiriear.add(imagePath);
                }
                if (datais.equals("Living")){
                    livingg.add(imagePath);
                }
                if (datais.equals("Bedroom")){
                    bedroomss.add(imagePath);
                }
                if (datais.equals("Bathrooms")){
                    bathrooms.add(imagePath);
                }
                if (datais.equals("Kitchen")){
                    kicthens.add(imagePath);
                }
                if (datais.equals("Floor")){
                    floors.add(imagePath);
                }
                if (datais.equals("Master")){
                    masterplans.add(imagePath);
                }
                if (datais.equals("Location")){
                    locations.add(imagePath);
                }
                if (datais.equals("Other")){
                    others.add(imagePath);
                }
                if (datais.equals("SiteView")){
                    siteView.add(imagePath);
                }
                arrayListModal.add(d);
                Log.e("arrey", "onActivityResult: "+arrayListModal );
                adapter =  new PostImagesRecyclerAdapter(getContext(), arrayListModal,datais);
                recyclerView.setAdapter(adapter);
                TextView textView = (TextView) frm_schedul.getChildAt(0);
                textView.setText("Select");

            }
            if (requestCode==112){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), image, "cpture", null);
                Uri imgcap = Uri.parse(path);
                PostImagesRecuclerModal d =  new PostImagesRecuclerModal();
                d.setImageLink(imgcap);
                d.setImageFrom(datais);
                d.setSetAscover("false");
                d.setIsFromServer("false");
                if (datais.equals("Exterior")){
                    extiriear.add(imgcap);
                }
                if (datais.equals("Living")){
                    livingg.add(imgcap);
                }
                if (datais.equals("Bedroom")){
                    bedroomss.add(imgcap);
                }
                if (datais.equals("Bathrooms")){
                    bathrooms.add(imgcap);
                }
                if (datais.equals("Kitchen")){
                    kicthens.add(imgcap);
                }
                if (datais.equals("Floor")){
                    floors.add(imgcap);
                }
                if (datais.equals("Master")){
                    masterplans.add(imgcap);
                }
                if (datais.equals("Location")){
                    locations.add(imgcap);
                }
                if (datais.equals("Other")){
                    others.add(imgcap);
                }
                if (datais.equals("SiteView")){
                    siteView.add(imgcap);
                }
                arrayListModal.add(d);
                adapter =  new PostImagesRecyclerAdapter(getContext(), arrayListModal,datais);
                recyclerView.setAdapter(adapter);
                TextView textView = (TextView) frm_schedul.getChildAt(0);
                textView.setText("Select");
            }
        }
    }
}
