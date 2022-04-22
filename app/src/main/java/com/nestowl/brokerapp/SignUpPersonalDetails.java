package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.AdapterClass.RoundedSelectedItemsAdapter;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.utils.Converter;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignUpPersonalDetails extends AppCompatActivity {
    ImageView back_img;
    FrameLayout frm_operating, frame_expertise;
    CardView card_personal_detials;
    ScrollView scrollView;
    ImageView img_date;
    EditText tv_owner;
    TextView tv_date_dialog;
    EditText et_company;
    EditText et_expertise;
    TextView tv_male, tv_female, tv_other;
    TextView tv_residential, tv_commercial, tv_type_other;
    TextView tv_resale, tv_original, tv_dtl_other, tv_sell;
    TextView ready_to_move, under_construction, pre_launch;
    TextView tv_date;
    Calendar calendar;
    String gender = "";
    String dealing = "";
    String propery_type = "";
    String transaction = "";
    String possesion_status = "";
    Context context;
    Activity activity;
    String expertise_in = "";
    ArrayList<String> experties,possasion,transactions,propertyType;
    RecyclerView recyclerView ;
    boolean isResidental,isComercial,isOther,isResale,isOriginal,isOthers,isReady,isUnder,isPre,isAdding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        experties=new ArrayList<>();
        possasion=new ArrayList<>();
        transactions=new ArrayList<>();
        propertyType=new ArrayList<>();
        setContentView(R.layout.activity_sign_up_personal_details);
        frm_operating = findViewById(R.id.operating_frame);
        tv_owner = findViewById(R.id.id_owner_name);
//        et_expertise = findViewById(R.id.et_expertise);
        calendar = Calendar.getInstance();
        tv_date_dialog = findViewById(R.id.id_date);
        et_company = findViewById(R.id.et_company);
        tv_date = findViewById(R.id.date_id_first);
        frame_expertise = findViewById(R.id.experties_frame);
        img_date = findViewById(R.id.img_idd);
        ready_to_move = findViewById(R.id.tv_ready_to_move);
        under_construction = findViewById(R.id.tv_under_construction);
        pre_launch = findViewById(R.id.tv_pre_launch);
        tv_male = findViewById(R.id.male);
        tv_resale = findViewById(R.id.resale);
        tv_sell = findViewById(R.id.sell_first);
        tv_original = findViewById(R.id.original_booking);
        tv_dtl_other = findViewById(R.id.details_other);
        tv_female = findViewById(R.id.female);
        tv_other = findViewById(R.id.other);
        tv_residential = findViewById(R.id.residential);
        tv_commercial = findViewById(R.id.commercial);
        tv_type_other = findViewById(R.id.type_other);
        recyclerView=findViewById(R.id.PERSONAL_DETAILS_RECYCLER);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        final DatePickerDialog.OnDateSetListener fromdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                date1();
            }
        };
        img_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=
                        new DatePickerDialog(SignUpPersonalDetails.this, fromdate, calendar.get(calendar.YEAR),
                                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        tv_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealing = tv_sell.getText().toString();
                selected(tv_sell);
            }
        });
        tv_resale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResale){
                    for (int i=0;i<transactions.size();i++){
                        if (transactions.get(i).equals(tv_resale.getText().toString())){
                            transactions.remove(i);
                        }
                    }
                    unselected(tv_resale);
                    isResale=false;
                }else {
                    transactions.add(tv_resale.getText().toString());
                    selected(tv_resale);
                    isResale=true;
                }
            }
        });
        tv_original.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOriginal){
                    for (int i=0;i<transactions.size();i++){
                        if (transactions.get(i).equals(tv_original.getText().toString())){
                            transactions.remove(i);
                        }
                    }
                    unselected(tv_original);
                    isOriginal=false;
                }else {
                    transactions.add(tv_original.getText().toString());
                    selected(tv_original);
                    isOriginal=true;
                }
            }
        });
        tv_dtl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOthers){
                    for (int i=0;i<transactions.size();i++){
                        if (transactions.get(i).equals(tv_dtl_other.getText().toString())){
                            transactions.remove(i);
                        }
                    }
                    unselected(tv_dtl_other);
                    isOthers=false;
                }else {
                    transactions.add(tv_dtl_other.getText().toString());
                    selected(tv_dtl_other);
                    isOthers=true;
                }
            }
        });
        ready_to_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isReady){
                    for (int i=0;i<possasion.size();i++){
                        if (possasion.get(i).equals(ready_to_move.getText().toString())){
                            possasion.remove(i);
                        }
                    }
                    unselected(ready_to_move);
                    isReady=false;
                }else {
                    possasion.add(ready_to_move.getText().toString());
                    selected(ready_to_move);
                    isReady=true;
                }

            }
        });
        under_construction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUnder){
                    for (int i=0;i<possasion.size();i++){
                        if (possasion.get(i).equals(under_construction.getText().toString())){
                            possasion.remove(i);
                        }
                    }
                    unselected(under_construction);
                    isUnder=false;
                }else {
                    possasion.add(under_construction.getText().toString());
                    selected(under_construction);
                    isUnder=true;
                }
            }
        });
        pre_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPre){
                    for (int i=0;i<possasion.size();i++){
                        if (possasion.get(i).equals(pre_launch.getText().toString())){
                            possasion.remove(i);
                        }
                    }
                    unselected(pre_launch);
                    isPre=false;
                }else {
                    possasion.add(pre_launch.getText().toString());
                    selected(pre_launch);
                    isPre=true;
                }
            }
        });
        getPersonalDetails();
        tv_residential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResidental){
                    for (int i=0;i<propertyType.size();i++){
                        if (propertyType.get(i).equals(tv_residential.getText().toString())){
                            propertyType.remove(i);
                        }
                    }
                    unselected(tv_residential);
                    isResidental=false;
                }else {
                    propertyType.add(tv_residential.getText().toString());
                    selected(tv_residential);
                    isResidental=true;
                }
            }
        });
        tv_commercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isComercial){
                    for (int i=0;i<propertyType.size();i++){
                        if (propertyType.get(i).equals(tv_commercial.getText().toString())){
                            propertyType.remove(i);
                        }
                    }
                    unselected(tv_commercial);
                    isComercial=false;
                }else {
                    propertyType.add(tv_commercial.getText().toString());
                    selected(tv_commercial);
                    isComercial=true;
                }
            }
        });
        tv_type_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOther){
                    for (int i=0;i<propertyType.size();i++){
                        if (propertyType.get(i).equals(tv_type_other.getText().toString())){
                            propertyType.remove(i);
                        }
                    }
                    unselected(tv_type_other);
                    isOther=false;
                }else {
                    propertyType.add(tv_type_other.getText().toString());
                    selected(tv_type_other);
                    isOther=true;
                }
            }
        });
        tv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = tv_male.getText().toString();
                selected(tv_male);
                unselected(tv_female);
                unselected(tv_other);
            }
        });
        tv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = tv_female.getText().toString();
                unselected(tv_male);
                selected(tv_female);
                unselected(tv_other);
            }
        });
        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = tv_other.getText().toString();
                unselected(tv_male);
                unselected(tv_female);
                selected(tv_other);

            }
        });
        card_personal_detials = findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        scrollView = findViewById(R.id.scroll);
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                card_personal_detials.setVisibility(View.GONE);
                if (scrollY > 55) {
                    card_personal_detials.setVisibility(View.VISIBLE);
                }
            }
        });
        card_personal_detials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tv_owner.getText().toString().isEmpty()) {
                    tv_owner.requestFocus();
                    tv_owner.setError("Please Enter Owner Name");

                } else if (et_company.getText().toString().isEmpty()) {
                    et_company.requestFocus();
                    et_company.setError("Please Enter Company Name");
                } else if (gender.isEmpty()) {
                    new WarningDio(SignUpPersonalDetails.this, "Please select gender", "Select Now", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                } else if (dealing.isEmpty()) {
                    new WarningDio(SignUpPersonalDetails.this, "Please select I am dealing", "Select Now", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                } else if (propertyType.isEmpty()) {
                    new WarningDio(SignUpPersonalDetails.this, "Property Type is Now selected", "Select Now", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                } else {
                    if (isAdding){
                        addPersonalDetails();
                    }else {
                        updatePersonalDetails();
                    }
                }
            }
        });
        frame_expertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SignUpPersonalDetails.this);
                dialog.setContentView(R.layout.expertise_in);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpPersonalDetails.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView = dialog.findViewById(R.id.iv_cancel);
                final CheckBox tv_resale = dialog.findViewById(R.id.resale_original);
                final CheckBox tv_original = dialog.findViewById(R.id.tv_original_booking);
                final CheckBox tv_resale_premium = dialog.findViewById(R.id.resale_premium_property);
                final CheckBox tv_best_negotiation = dialog.findViewById(R.id.best_negotiation_for_buyers);
                final CheckBox tv_best_negotiation_seller = dialog.findViewById(R.id.best_negotiation_for_sellers);
                final CheckBox tv_pre_launch = dialog.findViewById(R.id.pre_launch_project);
                final CheckBox tv_lease = dialog.findViewById(R.id.lease_commercial_properties);
                final CheckBox tv_new_launch = dialog.findViewById(R.id.new_launch_project);
                final CheckBox tv_rent_residential = dialog.findViewById(R.id.rent_residential_properties);
                if (!experties.isEmpty()) {
                    for (String expertise_in :experties){
                        if (expertise_in.contains(tv_resale.getText().toString())) {
                            tv_resale.setChecked(true);
                        }  if (expertise_in.contains(tv_original.getText().toString())) {
                            tv_original.setChecked(true);
                        }  if (expertise_in.contains(tv_resale_premium.getText().toString())) {
                            tv_resale.setChecked(true);
                        } if (expertise_in.contains(tv_best_negotiation.getText().toString())) {
                            tv_best_negotiation.setChecked(true);
                        }  if (expertise_in.contains(tv_best_negotiation_seller.getText().toString())) {
                            tv_best_negotiation_seller.setChecked(true);
                        }  if (expertise_in.contains(tv_pre_launch.getText().toString())) {
                            tv_pre_launch.setChecked(true);
                        } if (expertise_in.contains(tv_lease.getText().toString())) {
                            tv_lease.setChecked(true);
                        }
                        if (expertise_in.contains(tv_new_launch.getText().toString())) {
                            tv_new_launch.setChecked(true);
                        }

                        if (expertise_in.contains(tv_rent_residential.getText().toString())) {
                            tv_rent_residential.setChecked(true);
                        }
                    }


                }
                CardView card_dialog = dialog.findViewById(R.id.dialog_submit);
                card_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tv_resale.isChecked()) {
                            experties.add(tv_resale.getText().toString());
                        }
                        if (tv_original.isChecked()) {
                            experties.add(tv_original.getText().toString());
                        }
                        if (tv_resale_premium.isChecked()) {
                            experties.add(tv_resale_premium.getText().toString());
                        }
                        if (tv_best_negotiation.isChecked()) {
                            experties.add(tv_best_negotiation.getText().toString());
                        }
                        if (tv_best_negotiation_seller.isChecked()) {
                            experties.add(tv_best_negotiation_seller.getText().toString());
                        }
                        if (tv_pre_launch.isChecked()) {
                            experties.add(tv_pre_launch.getText().toString());
                        }
                        if (tv_lease.isChecked()) {
                            experties.add(tv_lease.getText().toString());
                        }
                        if (tv_new_launch.isChecked()) {
                            experties.add(tv_new_launch.getText().toString());
                        }
                        if (tv_rent_residential.isChecked()) {
                            experties.add(tv_rent_residential.getText().toString());
                        }
                        setRecyclerView();
                        dialog.dismiss();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        back_img = findViewById(R.id.EDIT_PROFILE_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPersonalDetails.this, EditSignUpForm.class);
                startActivity(intent);
            }
        });
        frm_operating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SignUpPersonalDetails.this);
                dialog.setContentView(R.layout.operating_since);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SignUpPersonalDetails.this, android.R.color.transparent)));
                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView = dialog.findViewById(R.id.iv_cancel);
                final RadioButton tv_2021 = dialog.findViewById(R.id.id_2021);
                final RadioButton tv_2020 = dialog.findViewById(R.id.id_2020);
                final RadioButton tv_2019 = dialog.findViewById(R.id.id_2019);
                final RadioButton tv_2018 = dialog.findViewById(R.id.id_2018);
                final RadioButton tv_2017 = dialog.findViewById(R.id.id_2017);
                final RadioButton tv_2016 = dialog.findViewById(R.id.id_2016);
                final RadioButton tv_2015 = dialog.findViewById(R.id.id_2015);
                final RadioButton tv_2014 = dialog.findViewById(R.id.id_2014);
                final RadioButton tv_2013 = dialog.findViewById(R.id.id_2013);
                final RadioButton tv_2012 = dialog.findViewById(R.id.id_2012);
                final RadioButton tv_2011 = dialog.findViewById(R.id.id_2011);
                final RadioButton tv_2010 = dialog.findViewById(R.id.id_2010);
                final RadioButton tv_2009 = dialog.findViewById(R.id.id_2009);
                final RadioButton tv_2008 = dialog.findViewById(R.id.id_2008);
                final RadioButton tv_2007 = dialog.findViewById(R.id.id_2007);
                final RadioButton tv_2006 = dialog.findViewById(R.id.id_2006);
                final RadioButton tv_2005 = dialog.findViewById(R.id.id_2005);
                final RadioButton tv_2004 = dialog.findViewById(R.id.id_2004);
                final RadioButton tv_2003 = dialog.findViewById(R.id.id_2003);
                final RadioButton tv_2002 = dialog.findViewById(R.id.id_2002);
                final RadioButton tv_2001 = dialog.findViewById(R.id.id_2001);
                final RadioButton tv_2000 = dialog.findViewById(R.id.id_2000);
                CardView card_operating = dialog.findViewById(R.id.card_operating_since);
                if (tv_date_dialog.getText().toString().equals(tv_2021.getText().toString())){
                    tv_2021.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2020.getText().toString())){
                    tv_2020.setChecked(true);
                }

                if (tv_date_dialog.getText().toString().equals(tv_2019.getText().toString())){
                    tv_2019.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2018.getText().toString())){
                    tv_2018.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2017.getText().toString())){
                    tv_2017.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2016.getText().toString())){
                    tv_2016.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2015.getText().toString())){
                    tv_2015.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2014.getText().toString())){
                    tv_2014.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2013.getText().toString())){
                    tv_2013.setChecked(true);
                }

                if (tv_date_dialog.getText().toString().equals(tv_2012.getText().toString())){
                    tv_2012.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2011.getText().toString())){
                    tv_2011.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2010.getText().toString())){
                    tv_2010.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2009.getText().toString())){
                    tv_2009.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2008.getText().toString())){
                    tv_2008.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2007.getText().toString())){
                    tv_2007.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2006.getText().toString())){
                    tv_2006.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2005.getText().toString())){
                    tv_2005.setChecked(true);
                }

                if (tv_date_dialog.getText().toString().equals(tv_2004.getText().toString())){
                    tv_2004.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2003.getText().toString())){
                    tv_2003.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2002.getText().toString())){
                    tv_2002.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2001.getText().toString())){
                    tv_2001.setChecked(true);
                }
                if (tv_date_dialog.getText().toString().equals(tv_2000.getText().toString())){
                    tv_2000.setChecked(true);
                }








                card_operating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tv_2021.isChecked()) {
                            tv_date_dialog.setText(tv_2021.getText().toString());
                        }
                        if (tv_2020.isChecked()) {
                            tv_date_dialog.setText(tv_2020.getText().toString());
                        }
                        if (tv_2019.isChecked()) {
                            tv_date_dialog.setText(tv_2019.getText().toString());
                        }
                        if (tv_2018.isChecked()) {
                            tv_date_dialog.setText(tv_2018.getText().toString());
                        }
                        if (tv_2017.isChecked()) {
                            tv_date_dialog.setText(tv_2017.getText().toString());
                        }
                        if (tv_2016.isChecked()) {
                            tv_date_dialog.setText(tv_2016.getText().toString());
                        }
                        if (tv_2015.isChecked()) {
                            tv_date_dialog.setText(tv_2015.getText().toString());
                        }
                        if (tv_2014.isChecked()) {
                            tv_date_dialog.setText(tv_2014.getText().toString());
                        }
                        if (tv_2013.isChecked()) {
                            tv_date_dialog.setText(tv_2013.getText().toString());
                        }
                        if (tv_2012.isChecked()) {
                            tv_date_dialog.setText(tv_2012.getText().toString());
                        }
                        if (tv_2011.isChecked()) {
                            tv_date_dialog.setText(tv_2011.getText().toString());
                        }
                        if (tv_2010.isChecked()) {
                            tv_date_dialog.setText(tv_2010.getText().toString());
                        }
                        if (tv_2009.isChecked()) {
                            tv_date_dialog.setText(tv_2009.getText().toString());
                        }
                        if (tv_2008.isChecked()) {
                            tv_date_dialog.setText(tv_2008.getText().toString());
                        }
                        if (tv_2007.isChecked()) {
                            tv_date_dialog.setText(tv_2007.getText().toString());
                        }
                        if (tv_2006.isChecked()) {
                            tv_date_dialog.setText(tv_2006.getText().toString());
                        }
                        if (tv_2005.isChecked()) {
                            tv_date_dialog.setText(tv_2005.getText().toString());
                        }
                        if (tv_2004.isChecked()) {
                            tv_date_dialog.setText(tv_2004.getText().toString());
                        }
                        if (tv_2003.isChecked()) {
                            tv_date_dialog.setText(tv_2003.getText().toString());
                        }
                        if (tv_2002.isChecked()) {
                            tv_date_dialog.setText(tv_2002.getText().toString());
                        }
                        if (tv_2001.isChecked()) {
                            tv_date_dialog.setText(tv_2001.getText().toString());
                        }
                        if (tv_2000.isChecked()) {
                            tv_date_dialog.setText(tv_2000.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void setRecyclerView() {
        RoundedSelectedItemsAdapter adapter = new RoundedSelectedItemsAdapter(this,experties);
        recyclerView.setAdapter(adapter);
    }

    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    public void date1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv_date.setText(sdf.format(calendar.getTime()));
    }
    public void getPersonalDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_PERSONAL_DETAILS, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if (status.equals("1")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        Log.e("Personal Details", "onResponse: "+object );

                        tv_owner.setText(object.getString("owner_name"));
                        gender=object.getString("gender");
                        dealing=object.getString("dealing");
                        transaction=object.getString("transaction_type");
                        Log.d("transaction_type",transaction);
                        possesion_status=object.getString("possession_status");
                        et_company.setText(object.getString("company_name"));

                        expertise_in=object.getString("expertise_in");
                        propery_type=object.getString("property_type");
                        tv_date.setText(object.getString("age"));
                        String[] moreData =object.getString("expertise_in").split(",");
                        experties=new ArrayList<>();
                        for (String s: moreData){
                            experties.add(s);
                        }
                        setRecyclerView();

                        tv_date_dialog.setText(object.getString("operating_since"));

                        if (dealing.equals("Sell")){
                            tv_sell.performClick();
                        }

                        if (gender.equals("Male")){
                            tv_male.performClick();
                        }else if (gender.equals("Female")){
                           tv_female.performClick();
                        } else if (gender.equals("Other")){
                            tv_other.performClick();
                        }

                        if (propery_type.contains("Residential")){
                            tv_residential.performClick();
                        }  if (propery_type.contains("Commercial")){
                            tv_commercial.performClick();
                        } if (propery_type.contains("Other")){
                            tv_type_other.performClick();
                        }
                        if (transaction.contains("Resale")){
                            tv_resale.performClick();
                        } if (transaction.contains("Original Booking")){
                            tv_original.performClick();
                        } if (transaction.contains("Other")){
                            tv_dtl_other.performClick();
                        }
                        if (possesion_status.contains("Ready to Move")){
                            ready_to_move.performClick();
                        } if (possesion_status.contains("Under Construction")){
                            under_construction.performClick();
                        } if (possesion_status.contains("Pre-Launch")){
                            pre_launch.performClick();
                        }

                    } else {
                        TextView textView = (TextView)card_personal_detials.getChildAt(0);
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
    private void updatePersonalDetails()  {
        Converter converter =  new Converter(this);
        if (UtilityFunction.isNetworkConnected(context)) {
            UtilityFunction.showLoading(context, "Please wait...");
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
            Log.e("IdPersonalUpdate", "updatePersonalDetails: "+PrefMananger.GetLoginData(context).getUserId() );
            hashMap.put("owner_name", tv_owner.getText().toString());
            hashMap.put("gender", gender);
            hashMap.put("age", tv_date.getText().toString());
            hashMap.put("company_name", et_company.getText().toString());
            hashMap.put("dealing", tv_sell.getText().toString());
            hashMap.put("property_type", converter.getSrtingArrey(propertyType));
            hashMap.put("property", "Property");
            hashMap.put("transaction_type", converter.getSrtingArrey(transactions));
            hashMap.put("possession_status", converter.getSrtingArrey(possasion));
            hashMap.put("operating_since", tv_date_dialog.getText().toString());
            hashMap.put("expertise_in", converter.getSrtingArrey(experties));
            hashMap.put("status", "1");
            for (String ss: hashMap.values()){
                Log.e("hashmap", "updatePersonalDetails: "+ss);
            }
            StringRequest request = new StringRequest(Request.Method.POST, UrlClass.UPDATE_BROKER_PERSONAL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    UtilityFunction.hideLoading();
                    try {
                        JSONObject jsonObject =  new JSONObject(response);
                        Log.e("respomne", "onResponse: "+response );
                        String data = jsonObject.getString("status");
                        if (data.equals("1")){
                            Intent intent = new Intent(SignUpPersonalDetails.this, SignUpOfficeDetails.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignUpPersonalDetails.this,jsonObject.getString("massage"),Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e){

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    UtilityFunction.hideLoading();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                    hashMap.put("owner_name", tv_owner.getText().toString());
                    hashMap.put("gender", gender);
                    hashMap.put("age", tv_date.getText().toString());
                    hashMap.put("company_name", et_company.getText().toString());
                    hashMap.put("dealing", tv_sell.getText().toString());
                    hashMap.put("property_type", converter.getSrtingArrey(propertyType));
                    hashMap.put("property", "Property");
                    hashMap.put("transaction_type", converter.getSrtingArrey(transactions));
                    hashMap.put("possession_status", converter.getSrtingArrey(possasion));
                    hashMap.put("operating_since", tv_date_dialog.getText().toString());
                    hashMap.put("expertise_in", converter.getSrtingArrey(experties));
                    hashMap.put("status", "1");
                    return hashMap;
                }
            };
            Volley.newRequestQueue(this).add(request);
//            ApiExecutor.getApiService().updateBrokerPersonalDetails(hashMap).enqueue(new Callback<OtpResponse>() {
//                @Override
//                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
//                    UtilityFunction.hideLoading();
//                    try {
//                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
//                        if (response.body().status == 1) {
//
//                            Intent intent = new Intent(SignUpPersonalDetails.this, SignUpOfficeDetails.class);
//                            startActivity(intent);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<OtpResponse> call, Throwable t) {
//                    UtilityFunction.hideLoading();
//                    Log.e("error", "onFailure: "+t );
//                }
//            });
        } else {
            Toast.makeText(context, "Network is not available", Toast.LENGTH_SHORT).show();
        }
    }
    private  void addPersonalDetails(){
        Converter converter =  new Converter(this);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.SET_PERSONALDETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                UtilityFunction.hideLoading();
                dialog.dismiss();
                try {
                    JSONObject jsonObject =  new JSONObject(response);
                    Log.e("respomne", "onResponse: "+response );
                    String data = jsonObject.getString("status");
                    if (data.equals("1")){
                        Intent intent = new Intent(SignUpPersonalDetails.this, SignUpOfficeDetails.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignUpPersonalDetails.this,jsonObject.getString("massage"),Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                UtilityFunction.hideLoading();
                dialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                hashMap.put("owner_name", tv_owner.getText().toString());
                hashMap.put("gender", gender);
                hashMap.put("age", tv_date.getText().toString());
                hashMap.put("company_name", et_company.getText().toString());
                hashMap.put("dealing", tv_sell.getText().toString());
                hashMap.put("property_type", converter.getSrtingArrey(propertyType));
                hashMap.put("property", "Property");
                hashMap.put("transaction_type", converter.getSrtingArrey(transactions));
                hashMap.put("possession_status", converter.getSrtingArrey(possasion));
                hashMap.put("operating_since", tv_date_dialog.getText().toString());
                hashMap.put("expertise_in", converter.getSrtingArrey(experties));
                hashMap.put("status", "1");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }



}