package com.nestowl.brokerapp;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.AddMoreAdapterClass;
import com.nestowl.AdapterClass.DropdownAdapter;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.api_service.UrlContainer;
import com.nestowl.model.DropdownHome;
import com.nestowl.model.DropdownPOJO;
import com.nestowl.model.WhatsAppPojo;
import com.nestowl.model.aichat;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;
import com.nestowl.viewmodal.LiveCommnication;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SignUpOfficeDetails extends AppCompatActivity {
    ImageView back_img,AddNomber;
    FrameLayout frame_state,frame_cit,frm_locality;
    CardView card_office;
    TextView tv_add_more;
    RecyclerView recyclerView;
    Context context;
    ScrollView scrollView;
    AddMoreAdapterClass addMoreAdapterClass;
    ArrayList<WhatsAppPojo> firslistt;
    TextView tv_state;
    TextView tv_city;
    TextView tv_locality;
    EditText et_office_address;
    EditText et_postal_code;
    EditText et_contact_person;
    EditText et_company;
    EditText et_company_website;
    EditText et_additional_phone;
    CheckBox ch_whatsapp;
    TextView office_address_hide;
    TextView tv_comercial,tv_residential;
    TextView tv_permanent,tv_rental;
    String office_address_type="";
    String occupancy_type="";
    LiveCommnication liveCommnication;
    boolean isAdding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context =this;
        setContentView(R.layout.activity_sign_up_office_details);
        firslistt=new ArrayList<>();
        tv_add_more=findViewById(R.id.id_add_more);
        tv_permanent=findViewById(R.id.tv_permanent);
        tv_rental=findViewById(R.id.tv_rental);
        office_address_hide=findViewById(R.id.office_address_hide);
        tv_comercial=findViewById(R.id.commericial_id_office);
        tv_residential=findViewById(R.id.residential_id_office);
        tv_state=findViewById(R.id.tv_state);
        tv_city=findViewById(R.id.tv_city);
        tv_locality=findViewById(R.id.tv_locality);
        frame_state=findViewById(R.id.state_frame);
        recyclerView=findViewById(R.id.recycler_add_more_id);
        et_office_address=findViewById(R.id.et_office_address);
        et_postal_code=findViewById(R.id.et_postal_code);
        et_contact_person=findViewById(R.id.et_contact_person);
        et_company=findViewById(R.id.et_company);
        et_company_website=findViewById(R.id.et_company_website);
        et_additional_phone=findViewById(R.id.et_additional_phone);
        ch_whatsapp=findViewById(R.id.ch_available_whatsapp);
        scrollView=findViewById(R.id.scroll);
        tv_comercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                office_address_type=tv_comercial.getText().toString();
                selected(tv_comercial);
                unselected(tv_residential);
            }
        });
        tv_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                office_address_type=tv_residential.getText().toString();
                unselected(tv_comercial);
                selected(tv_residential);
            }
        });
        tv_permanent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occupancy_type=tv_permanent.getText().toString();
                selected(tv_permanent);
                unselected(tv_rental);
            }
        });
        tv_rental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                occupancy_type=tv_rental.getText().toString();
                unselected(tv_permanent);
                selected(tv_rental);
            }
        });
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                card_office.setVisibility(View.GONE);
                if (scrollY>55){
                    card_office.setVisibility(View.VISIBLE);
                }
            }
        });
        frame_cit=findViewById(R.id.city_frame);
        card_office=findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        AddNomber=findViewById(R.id.OFFICE_ADD_PHONE);
        card_office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_state.getText().toString().isEmpty())
                {
                    Toast.makeText(context, "Please select state", Toast.LENGTH_SHORT).show();
                }else if (tv_city.getText().toString().isEmpty()){
                    Toast.makeText(context, "Please select City", Toast.LENGTH_SHORT).show();
                }else if (tv_locality.getText().toString().isEmpty()){
                    Toast.makeText(context, "Please select locality", Toast.LENGTH_SHORT).show();
                }
                else if (et_office_address.getText().toString().isEmpty())

                {
                    Toast.makeText(context, "Please select Office Address", Toast.LENGTH_SHORT).show();

                } else  if (office_address_type.isEmpty()){
                    Toast.makeText(context, "Please select Office Address Type", Toast.LENGTH_SHORT).show();
                }
                else  if (occupancy_type.isEmpty()){
                    Toast.makeText(context, "Please select Occupancy Type", Toast.LENGTH_SHORT).show();

                } else if (et_postal_code.getText().toString().isEmpty()) {
                    et_postal_code.requestFocus();
                    et_postal_code.setError("Please Enter Postal Code");
                }else if (et_contact_person.getText().toString().isEmpty()) {
                    et_contact_person.requestFocus();
                    et_contact_person.setError("Please Enter Contact Person Name");
                }else if (et_company.getText().toString().isEmpty()) {
                    et_company.requestFocus();
                    et_company.setError("Please Enter Agent Comppany Name");
                }
//                else
//                if (et_company_website.getText().toString().isEmpty()) {
//                    et_company_website.requestFocus();
//                    et_company_website.setError("Please Enter Company Website Name");
                /*else if (et_additional_phone.getText().toString().isEmpty()) {
                    et_additional_phone.requestFocus();
                    et_additional_phone.setError("Please Enter Addition Phone No");
                }*/

                else {
                    if (isAdding){
                    postOfficeDetails();
                    }else {
                        updateOfficeDetails();
                    }
                }
            }
        });
        liveCommnication = ViewModelProviders.of(this).get(LiveCommnication.class);
        liveCommnication.getText().observe(this, new Observer<aichat>() {
            @Override
            public void onChanged(aichat aichat) {
                String key  =aichat.getText();
                String value = aichat.getValue();
                if (key.equals("r")){
                    removeFromNombers(value);
                }
            }
        });
        et_additional_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (s.toString().length()==10){
                        AddNomber.setVisibility(View.VISIBLE);
                    }else {
                        AddNomber.setVisibility(View.GONE);
                    }
            }
        });
        AddNomber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhatsAppPojo whatsAppPojo =  new WhatsAppPojo();
                whatsAppPojo.setPhone(et_additional_phone.getText().toString());
                if (ch_whatsapp.isChecked()){
                    whatsAppPojo.setWhatsapp("yes");
                }else {
                    whatsAppPojo.setWhatsapp("no");
                }
                firslistt.add(whatsAppPojo);
                et_additional_phone.setText(null);
                refreshConatcs();
            }
        });
//        tv_add_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (firslistt==null){
//                    firslistt= new ArrayList<>();
//                }
//                WhatsAppPojo whatsAppPojo =new WhatsAppPojo();
//                whatsAppPojo.setWhatsapp("no");
//                whatsAppPojo.setPhone("");
//                firslistt.add(whatsAppPojo);
//                if (addMoreAdapterClass==null){
//                    addMoreAdapterClass=new AddMoreAdapterClass(context,firslistt);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                    recyclerView.setAdapter(addMoreAdapterClass);
//                }
//                addMoreAdapterClass.notifyDataSetChanged();
//            }
//        });
        frm_locality=findViewById(R.id.locality_frame);
        frame_cit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCitySelectDialog();
            }
        });
        frm_locality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (cityValues==null){
//                    Toast.makeText(context, "Please select city", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                openLocalitySelectDialog();
            }
        });
        frame_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openStateSelectDialog();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpOfficeDetails.this,EditSignUpForm.class);
                startActivity(intent);
            }
        });
        getOfficeDetailsDetails();
    }

    private void removeFromNombers(String value) {
        firslistt.remove(Integer.parseInt(value));
        refreshConatcs();
    }
    private void refreshConatcs() {
        addMoreAdapterClass=new AddMoreAdapterClass(context,firslistt);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(addMoreAdapterClass);
    }
    private void handlerContacts(String phoneData) {
        try {
        JSONObject jsonObject = new JSONObject(phoneData);
            firslistt =  new ArrayList<>();
        for (int i = 1;i<jsonObject.length();i++){
            JSONObject jsonObject1 = jsonObject.getJSONObject(String.valueOf(i));
            WhatsAppPojo whatsAppPojo =  new Gson().fromJson(jsonObject1.toString(),WhatsAppPojo.class);
            firslistt.add(whatsAppPojo);
            refreshConatcs();
        }
        }catch (Exception e){
            Log.e("error in contacts", "handlerContacts: "+e );
        }
    }
    EditText et_search;
    RecyclerView rv_states;
    private void openStateSelectDialog() {
        final Dialog dialog = new Dialog(SignUpOfficeDetails.this);
        dialog.setContentView(R.layout.select_state);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        LinearLayout add = dialog.findViewById(R.id.STATE_DIO_LIN);
        rv_states=dialog.findViewById(R.id.rv_states);
        et_search=dialog.findViewById(R.id.et_search);
        et_search.setHint("Search State");
        CardView card_submit=dialog.findViewById(R.id.card_submit);
        add.setVisibility(View.VISIBLE);
        dialog.show();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogs = new Dialog(SignUpOfficeDetails.this);
                dialogs.setContentView(R.layout.cityname_pop_up);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialogs.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                TextView textView = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_NAME);
                EditText getFields = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESSES_IN);
                CardView sumbit = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_SUMBIT);
                sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getFields.getText() == null|getFields.getText().length() <=0){
                            getFields.setError("Data is Empty");
                            return;
                        }
                        tv_state.setText(getFields.getText().toString());
                        dialog.dismiss();
                        dialogs.dismiss();
                    }
                });
                textView.setText("Please Enter The Locality Name");
                dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
                dialogs.getWindow().setAttributes(lp);
                dialogs.show();
            }
        });
        ImageView imageView=dialog.findViewById(R.id.iv_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                selectedState=null;
            }
        });
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedState==null){
                    Toast.makeText(context, "Please select state", Toast.LENGTH_SHORT).show();
                    return;
                }
                stateValues=selectedState;
                tv_state.setText(stateValues.value);
                selectedState=null;
                dialog.dismiss();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString().length()>0){
                        ArrayList<DropdownPOJO> arrayList=new ArrayList<>();
                        for (DropdownPOJO pojo : stateList){
                            if (pojo.value.toLowerCase().contains(s.toString().toLowerCase())
                                ||    pojo.state_code.toLowerCase().contains(s.toString().toLowerCase())
                            ){
                                arrayList.add(pojo);
                            }
                        }
                        setStateData(arrayList);
                    }else {
                        setStateData(stateList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        if (stateList.size()==0){
            getStateList();
        }else {
            setStateData(stateList);
        }
    }
    DropdownPOJO selectedState;
    DropdownPOJO stateValues;
    ArrayList<DropdownPOJO> stateList=new ArrayList<>();
    private void setStateData(ArrayList<DropdownPOJO> dropdownPOJOS){
        DropdownAdapter adapter=new DropdownAdapter(context, dropdownPOJOS, new DropdownAdapter.OnItemSelected() {
            @Override
            public void onSelected(int position, DropdownPOJO dropdownPOJO) {
                selectedState=dropdownPOJO;
            }
        });
        rv_states.setLayoutManager(new LinearLayoutManager(context));
        rv_states.setAdapter(adapter);
    }
    private void getStateList(){
        UtilityFunction.showLoading(context,"Please wait...");
        ApiExecutor.getApiService().getDropdownData(UrlContainer.GET_STATES).enqueue(new Callback<DropdownHome>() {
            @Override
            public void onResponse(Call<DropdownHome> call, Response<DropdownHome> response) {
                UtilityFunction.hideLoading();
                if (response.isSuccessful() && response.body().status==1){
                    stateList=response.body().dropdownPOJOS;
                    setStateData(stateList);
                }
            }
            @Override
            public void onFailure(Call<DropdownHome> call, Throwable t) {
                UtilityFunction.hideLoading();
            }
        });
    }
    private void openCitySelectDialog() {
        final Dialog dialog = new Dialog(SignUpOfficeDetails.this);
        dialog.setContentView(R.layout.select_state);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        rv_states=dialog.findViewById(R.id.rv_states);
        et_search=dialog.findViewById(R.id.et_search);
        LinearLayout add = dialog.findViewById(R.id.STATE_DIO_LIN);
        TextView textView = dialog.findViewById(R.id.OFFICE_DIO_MAIN_TEXT);
        textView.setText("Select City");
        et_search.setHint("Search City");
        CardView card_submit=dialog.findViewById(R.id.card_submit);
        add.setVisibility(View.VISIBLE);
        dialog.show();
        ImageView imageView=dialog.findViewById(R.id.iv_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                selectedCity=null;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogs = new Dialog(SignUpOfficeDetails.this);
                dialogs.setContentView(R.layout.cityname_pop_up);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialogs.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                TextView textView = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_NAME);
                EditText getFields = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESSES_IN);
                CardView sumbit = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_SUMBIT);
                sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getFields.getText() == null|getFields.getText().length() <=0){
                            getFields.setError("Data is Empty");
                            return;
                        }
                        tv_city.setText(getFields.getText().toString());
                        dialog.dismiss();
                        dialogs.dismiss();
                    }
                });
                textView.setText("Please Enter The Locality Name");
                dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
                dialogs.getWindow().setAttributes(lp);
                dialogs.show();
            }
        });
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCity==null){
                    Toast.makeText(context, "Please select City", Toast.LENGTH_SHORT).show();
                    return;
                }
                cityValues=selectedCity;
               // tv_state.setText(cityValues.value);
                tv_city.setText(cityValues.value);
                selectedCity=null;
                dialog.dismiss();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString().length()>0){
                        ArrayList<DropdownPOJO> arrayList=new ArrayList<>();
                        for (DropdownPOJO pojo : cityList){
                            if (pojo.value.toLowerCase().contains(s.toString().toLowerCase())
                            ){
                                arrayList.add(pojo);
                            }
                        }
                        setCityData(arrayList);
                    }else {
                        setCityData(cityList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
            getCityList();

    }
    DropdownPOJO selectedCity;
    DropdownPOJO cityValues;
    ArrayList<DropdownPOJO> cityList=new ArrayList<>();
    private void setCityData(ArrayList<DropdownPOJO> dropdownPOJOS){
        DropdownAdapter adapter=new DropdownAdapter(context, dropdownPOJOS, new DropdownAdapter.OnItemSelected() {
            @Override
            public void onSelected(int position, DropdownPOJO dropdownPOJO) {
                selectedCity=dropdownPOJO;
            }
        });
        rv_states.setLayoutManager(new LinearLayoutManager(context));
        rv_states.setAdapter(adapter);
    }
    private void getCityList(){
        UtilityFunction.showLoading(context,"Please wait...");
        String url="";//UrlContainer.GET_STATES;
        if (stateValues!=null){
            url=UrlContainer.GET_CITY_BY_STATES+"?state="+stateValues.state_code;
        }else {
            url=UrlContainer.GET_ALL_CITY;
        }
        ApiExecutor.getApiService().getDropdownData(url).enqueue(new Callback<DropdownHome>() {
            @Override
            public void onResponse(Call<DropdownHome> call, Response<DropdownHome> response) {
                UtilityFunction.hideLoading();
                if (response.isSuccessful() && response.body().status==1){
                    cityList=response.body().dropdownPOJOS;
                    setCityData(cityList);
                }
            }
            @Override
            public void onFailure(Call<DropdownHome> call, Throwable t) {
                UtilityFunction.hideLoading();
            }
        });
    }
    private void openLocalitySelectDialog() {
        final Dialog dialog = new Dialog(SignUpOfficeDetails.this);
        dialog.setContentView(R.layout.select_state);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
        dialog.getWindow().setAttributes(lp);
        rv_states=dialog.findViewById(R.id.rv_states);
        et_search=dialog.findViewById(R.id.et_search);
        LinearLayout linearLayout = dialog.findViewById(R.id.STATE_DIO_LIN);
        LinearLayout add = dialog.findViewById(R.id.STATE_DIO_LIN);
        CardView card_submit=dialog.findViewById(R.id.card_submit);
        TextView textView = dialog.findViewById(R.id.OFFICE_DIO_MAIN_TEXT);
        textView.setText("Select Localities");
        et_search.setHint("Search Localities");
        linearLayout.setVisibility(View.VISIBLE);

        dialog.show();
        ImageView imageView=dialog.findViewById(R.id.iv_cancel);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogs = new Dialog(SignUpOfficeDetails.this);
                dialogs.setContentView(R.layout.cityname_pop_up);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialogs.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;
                TextView textView = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_NAME);
                EditText getFields = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESSES_IN);
                CardView sumbit = dialogs.findViewById(R.id.POST_REQ_DIO_ADRESS_SUMBIT);
                sumbit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getFields.getText() == null|getFields.getText().length() <=0){
                            getFields.setError("Data is Empty");
                            return;
                        }
                        tv_locality.setText(getFields.getText().toString());
                        dialog.dismiss();
                        dialogs.dismiss();
                    }
                });
                textView.setText("Please Enter The Locality Name");
                dialogs.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpOfficeDetails.this, android.R.color.transparent)));
                dialogs.getWindow().setAttributes(lp);
                dialogs.show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                selectedCity=null;
            }
        });
        card_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedLocality==null){
                    Toast.makeText(context, "Please select locality", Toast.LENGTH_SHORT).show();
                    return;
                }
                localityValues=selectedLocality;
               // tv_state.setText(cityValues.value);
                tv_locality.setText(localityValues.value);
                selectedLocality=null;
                dialog.dismiss();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString().length()>0){
                        ArrayList<DropdownPOJO> arrayList=new ArrayList<>();
                        for (DropdownPOJO pojo : localityList){
                            if (pojo.value.toLowerCase().contains(s.toString().toLowerCase())
                            ){
                                arrayList.add(pojo);
                            }
                        }
                        setLocalityData(arrayList);
                    }else {
                        setLocalityData(localityList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
            getLocaility();

    }
    DropdownPOJO selectedLocality;
    DropdownPOJO localityValues;
    ArrayList<DropdownPOJO> localityList=new ArrayList<>();
    private void setLocalityData(ArrayList<DropdownPOJO> dropdownPOJOS){
        DropdownAdapter adapter=new DropdownAdapter(context, dropdownPOJOS, new DropdownAdapter.OnItemSelected() {
            @Override
            public void onSelected(int position, DropdownPOJO dropdownPOJO) {
                selectedLocality=dropdownPOJO;
            }
        });
        rv_states.setLayoutManager(new LinearLayoutManager(context));
        rv_states.setAdapter(adapter);
    }
    private void getLocaility(){
        UtilityFunction.showLoading(context,"Please wait...");
        String url=UrlContainer.GET_LOCALITY;
        if (cityValues!=null){
            url=UrlContainer.GET_LOCALITY+"?location="+cityValues.value;
        }
        ApiExecutor.getApiService().getDropdownData(url).enqueue(new Callback<DropdownHome>() {
            @Override
            public void onResponse(Call<DropdownHome> call, Response<DropdownHome> response) {
                UtilityFunction.hideLoading();
                if (response.isSuccessful() && response.body().status==1){
                    localityList=response.body().dropdownPOJOS;
                    setLocalityData(localityList);
                }
            }
            @Override
            public void onFailure(Call<DropdownHome> call, Throwable t) {
                UtilityFunction.hideLoading();
            }
        });
    }
    public void getOfficeDetailsDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_OFFICE_DETAILS, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {

                        JSONObject object = jsonObject.getJSONObject("data");
                       // stateValues=
                        tv_state.setText(object.getString("state"));
                        tv_city.setText(object.getString("city"));
                        tv_locality.setText(object.getString("locality"));
                        et_office_address.setText(object.getString("address"));
                        office_address_type=object.getString("address_type");
                        occupancy_type=object.getString("occupancy_type");
                        et_postal_code.setText(object.getString("pincode"));
                        et_contact_person.setText(object.getString("contact_person_name"));
                        et_company.setText(object.getString("company_name"));
                        et_company_website.setText(object.getString("company_website"));
//                        et_additional_phone.setText(object.getString("phone"));
                        String phoneData= object.getString("phone");
                        handlerContacts(phoneData);
                        if (office_address_type.equals("Commericial")){
                            selected(tv_comercial);
                            unselected(tv_residential);
                        }else  if (office_address_type.equals("Residential")){
                            selected(tv_residential);
                            unselected(tv_comercial);
                        }
                        if (occupancy_type.contains("Permanent")){
                            selected(tv_permanent);
                            unselected(tv_rental);
                        }else if (occupancy_type.contains("Rental")){
                            selected(tv_rental);
                            unselected(tv_permanent);
                        }
                    } else {
                        TextView textView = (TextView)card_office.getChildAt(0);
                        textView.setText("Save and Continue");
                        isAdding=true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                return hashMap;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }

    private void postOfficeDetails(){
        ProgressDialog pd  = new ProgressDialog(this);
        pd.setMessage("Saving...");
        pd.setCancelable(false);
        pd.show();
        HashMap<String,WhatsAppPojo> hashMap =  new HashMap<>();
        int i =1;
        for (WhatsAppPojo s : firslistt){
            hashMap.put(String.valueOf(i),s);
            i++;
        }
        String json = new Gson().toJson(hashMap);
        StringRequest  request = new StringRequest(Request.Method.POST, UrlClass.SET_OFFICE_DETAILS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String statas= jsonObject.getString("status");
                    String data= jsonObject.getString("data");
                    if (statas.equals("1")){
                        Intent intent = new Intent(SignUpOfficeDetails.this,SignUpWorkDescription.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();

                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId()+"");
                hashMap.put("state",tv_state.getText().toString());
                hashMap.put("city",tv_city.getText().toString());
                hashMap.put("locality",tv_locality.getText().toString());
                hashMap.put("maps","");
                hashMap.put("company_name",et_company.getText().toString());
                hashMap.put("addresss",et_office_address.getText().toString());
                hashMap.put("address_type",office_address_type);
                hashMap.put("occupancy_type",occupancy_type);
                hashMap.put("pincode",et_postal_code.getText().toString());
                hashMap.put("contact_person_name",et_contact_person.getText().toString());
                hashMap.put("company_website",et_company_website.getText().toString());
                hashMap.put("phone",json);
                if (ch_whatsapp.isChecked()) {
                    hashMap.put("whatsapp", "Yes");
                }else {
                    hashMap.put("whatsapp","No");
                }
                hashMap.put("status","1");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void selected(TextView textView){
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView){
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    private void updateOfficeDetails(){
        ProgressDialog pd  = new ProgressDialog(this);
        pd.setMessage("Saving...");
        pd.setCancelable(false);
        pd.show();
        HashMap<String,WhatsAppPojo> hashMap =  new HashMap<>();
        int i =1;
        for (WhatsAppPojo s : firslistt){
            hashMap.put(String.valueOf(i),s);
            i++;
        }
        String json = new Gson().toJson(hashMap);
        StringRequest  request = new StringRequest(Request.Method.POST, UrlClass.UPDATE_BROKER_OFFICE, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.cancel();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    String statas= jsonObject.getString("status");
                    if (statas.equals("1")){
                        Intent intent = new Intent(SignUpOfficeDetails.this,SignUpWorkDescription.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();

                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId()+"");
                hashMap.put("state",tv_state.getText().toString());
                hashMap.put("city",tv_city.getText().toString());
                hashMap.put("locality",tv_locality.getText().toString());
                hashMap.put("maps","");
                hashMap.put("company_name",et_company.getText().toString());
                hashMap.put("addresss",et_office_address.getText().toString());
                hashMap.put("address_type",office_address_type);
                hashMap.put("occupancy_type",occupancy_type);
                hashMap.put("pincode",et_postal_code.getText().toString());
                hashMap.put("contact_person_name",et_contact_person.getText().toString());
                hashMap.put("company_website",et_company_website.getText().toString());
                hashMap.put("phone",json);
                if (ch_whatsapp.isChecked()) {
                    hashMap.put("whatsapp", "Yes");
                }else {
                    hashMap.put("whatsapp","No");
                }
                hashMap.put("status","1");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}