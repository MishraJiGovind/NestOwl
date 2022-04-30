package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.LoginPojo;
import com.nestowl.model.User;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SignUpActivity extends AppCompatActivity {
    TextView textView, tv_signup, tv_send_otp;
    ScrollView scrollView;
    EditText edt_full_name, edt_email_adreess,edt_password,edt_mobile, et_one, et_two, et_three, et_four;
    CardView cardView;
    Context context;
    Activity activity;
    String email,name,social,savePassword,vEmail;
    ImageView showHide,back;
    CheckBox checkBox;
    boolean passowrdVisble = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        social=getIntent().getStringExtra("s");

        setContentView(R.layout.activity_sign_up);
        textView = findViewById(R.id.id_continue_singup);
        tv_send_otp = findViewById(R.id.sent_otp);
        cardView = findViewById(R.id.card);
        et_one = findViewById(R.id.LOGIN_SCREEN_OTP_ONE);
        et_two = findViewById(R.id.et_two);
        et_three = findViewById(R.id.et_three);
        et_four = findViewById(R.id.et_four);
        edt_full_name = findViewById(R.id.et_full_name);
        edt_email_adreess = findViewById(R.id.et_email_address);
        edt_password = findViewById(R.id.et_password);
        edt_mobile = findViewById(R.id.et_mobile_number);
        tv_resend_code = findViewById(R.id.tv_resend_code);
        tv_OtpTimer = findViewById(R.id.tv_OtpTimer);
        checkBox=findViewById(R.id.SIGN_UP_CHECKBOX);
        scrollView = findViewById(R.id.scroll);
        back=findViewById(R.id.SIGNUP_BACK);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        showHide=findViewById(R.id.SIGNUP_SHOW_HIDE_PASSWORD);
        savePassword = null;
//        showHide.setOnClickListener(new View.OnClickListener() {
//            boolean isHide  =true;
//            @Override
//            public void onClick(View v) {
//                if (isHide){
//                    showHide.setImageResource(R.drawable.hide);
//                    savePassword=edt_password.getText().toString();
//                    isHide=false;
//                }else {
//                    showHide.setImageResource(R.drawable.show);
//                    isHide =true;
//                }
//            }
//        });
        vEmail="No";
        if (social!=null){
             if (social.equals("true")){
            email=getIntent().getStringExtra("email");
            name=getIntent().getStringExtra("name");
            vEmail="Yes";
            handleSocialLogin();
        }
        }
        edt_password.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=edt_password.getRight()-edt_password.getCompoundDrawables()[right].getBounds().width()){
                        int selection = edt_password.getSelectionEnd();
                        if (passowrdVisble){
                            edt_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_show,0);
                            edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passowrdVisble=false;
                        }else {
                            edt_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_hide,0);
                            edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passowrdVisble=true;
                        }
                        edt_password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        et_one.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_one.getText().toString().length() == 1)     //size as per your requirement
                {
                    et_two.requestFocus();
                }
                OTP = GetOTP();
                if (OTP.length()==4){
                    cardView.setVisibility(View.VISIBLE);


                }else {

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        et_two.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_two.getText().toString().length() == 1)     //size as per your requirement
                {
                    et_three.requestFocus();
                } else if (et_two.getText().toString().length() == 0) {
                    et_one.requestFocus();
                }
                OTP = GetOTP();
                if (OTP.length()==4){
                    cardView.setVisibility(View.VISIBLE);


                }else {

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        et_three.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_three.getText().toString().length() == 1)     //size as per your requirement
                {
                    et_four.requestFocus();
                } else if (et_three.getText().toString().length() == 0) {
                    et_two.requestFocus();
                }
                OTP = GetOTP();
                if (OTP.length()==4){
                    cardView.setVisibility(View.VISIBLE);


                }else {

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });
        et_four.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_four.getText().toString().length() == 1)     //size as per your requirement
                {
                    OTP = GetOTP();
                    if (OTP.length()==4){
                        checkOtp();

                        
                    }else {
                       ;
                    }


                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(et_four.getWindowToken(), 0);
                } else if (et_four.getText().toString().length() == 0) {
                    et_three.requestFocus();

                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void afterTextChanged(Editable s) {
            }

        });
        et_one.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    et_one.setText("");
                    UtilityFunction.hideKeybord(context, v);
                }
                return false;
            }
        });
        et_two.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    et_two.setText("");
                    et_one.requestFocus();
                }
                return false;
            }
        });

        et_three.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace
                    et_three.setText("");
                    et_two.requestFocus();
                }
                return false;
            }
        });

        et_four.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    //this is for backspace\
                    et_four.setText("");
                    et_three.requestFocus();
                }
                return false;
            }
        });

        tv_send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_mobile.getText().toString().isEmpty()) {
                    edt_mobile.requestFocus();
                    edt_mobile.setError("Please enter mobile no");
                } else {
                    et_one.requestFocus();
                    otp();
                }

            }
        });

        tv_resend_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_mobile.getText().toString().isEmpty()) {
                    edt_mobile.requestFocus();
                    edt_mobile.setError("Please enter mobile no");
                } else {
                    et_one.requestFocus();
                    otp();
                }
            }
        });
/*
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                cardView.setVisibility(View.GONE);
                if (scrollY > 55) {
                    cardView.setVisibility(View.GONE);

                }
            }
        });*/
        tv_signup = findViewById(R.id.sign_up_login);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent= new Intent(SignUpActivity.this,EditSignUpForm.class);
                startActivity(intent);*/
                otp = et_one.getText().toString() + et_two.getText().toString() + et_three.getText().toString() + et_four.getText().toString();
                if (edt_full_name.getText().toString().isEmpty()) {
                    edt_full_name.requestFocus();
                    edt_full_name.setError("Please enter full name");
                } else if (edt_email_adreess.getText().toString().isEmpty()) {
                    edt_email_adreess.requestFocus();
                    edt_email_adreess.setError("Please enter email address");
                } else if (edt_password.getText().toString().isEmpty()) {
                    edt_password.requestFocus();
                    edt_password.setError("Please enter password");
                } else if (edt_mobile.getText().toString().isEmpty()) {
                    edt_mobile.requestFocus();
                    edt_mobile.setError("Please enter mobile no");
                } else if (edt_mobile.getText().toString().isEmpty()) {
                    edt_mobile.requestFocus();
                    edt_mobile.setError("Please enter a otp");
                } else if (otp.isEmpty()) {
                    Toast.makeText(context, "Please Enter Otp", Toast.LENGTH_SHORT).show();
                } else if (otp.length() < 4) {
                    Toast.makeText(context, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                } else {
                    if (!checkBox.isChecked()){
                        new WarningDio(SignUpActivity.this, "Please Agree to Nestowl Terms and Condtions", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                        return;
                    }
                    signup();
                }


            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_email_adreess.getText()==null){
                    return;
                }
                if (edt_mobile.getText()==null){
                    return;
                }
                if (edt_password.getText()==null){
                    return;
                }
                if (edt_full_name.getText()==null){
                    return;
                }
                if (!checkBox.isChecked()){
                    new WarningDio(SignUpActivity.this, "Please Agree to Nestowl Terms and Condtions", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                    return;
                }
                signup();
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                //tv_resend_code.setVisibility(View.VISIBLE);

                //tv_Timer.setText("Resend OTP in: " + millisUntilFinished / 1000);
                tv_send_otp.setText("" + String.format("%d:%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                tv_send_otp.setText("Resend Otp");
//                tv_resend_code.setVisibility(View.VISIBLE);
//                tv_OtpTimer.setVisibility(View.GONE);
            }

        };
    }

    private void handleSocialLogin() {
        Log.e("socia;", "handleSocialLogin: "+name + email );
        if (name!=null){
        edt_full_name.setText(name,null);
        }
        if (email!=null&&!email.equals("null")){
        edt_email_adreess.setText(email,null);
        }
    }
    private TextView tv_resend_code, tv_OtpTimer;
    private CountDownTimer countDownTimer;

    private void startCountDown() {

//        tv_OtpTimer.setVisibility(View.VISIBLE);
//        tv_resend_code.setVisibility(View.GONE);
//        tv_send_otp.setVisibility(View.GONE);
       countDownTimer.start();


    }
        String otp = "";
    public void signup() {
        String[] name = edt_full_name.getText().toString().split(" ");
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.SIGN_UP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        User user = new Gson().fromJson(jsonObject.getJSONObject("data").toString(), User.class);

                        LoginPojo loginPojo = new LoginPojo();
                        loginPojo.setFirstName(user.getFirst_name());
                        loginPojo.setLastName(user.getLast_name());
                        loginPojo.setId(Integer.parseInt(user.getId()));
                        loginPojo.setUserId(Integer.parseInt(user.getUser_id()));
                        loginPojo.setEmail(user.getEmail());
                        loginPojo.setCreatedAt(user.getCreated_at());
                        loginPojo.setUpdatedAt(user.getUpdated_at());
                        loginPojo.setStatus("0");
                        loginPojo.setTerm("0");
                        loginPojo.setLastLogin("no data");
                        loginPojo.setPermissions("no data");

                        String userinfo =  new Gson().toJson(user);

                        PrefMananger.saveString(SignUpActivity.this,"user",userinfo);
                        PrefMananger.SaveLoginData(context, loginPojo);

                        Intent intent = new Intent(SignUpActivity.this, EditSignUpForm.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", edt_email_adreess.getText().toString());
                map.put("first_name",name[0]);
                map.put("last_name",name[1]);
                map.put("v_email", vEmail);
                map.put("password", edt_password.getText().toString());
                map.put("mobile", edt_mobile.getText().toString());
                map.put("OTP", otp);
                return map;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }
    public void otp() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.OTP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        startCountDown();
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", edt_email_adreess.getText().toString());
                map.put("mobile", edt_mobile.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
    public void checkOtp() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.OTP_Check, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
//                        startCountDown();
                        cardView.setVisibility(View.VISIBLE);
                        if (edt_email_adreess.getText()==null){
                            return;
                        }
                        if (edt_mobile.getText()==null){
                            return;
                        }
                        if (edt_password.getText()==null){
                            return;
                        }
                        if (edt_full_name.getText()==null){
                            return;
                        }
//                        signup();

                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                        cardView.setVisibility(View.GONE);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", edt_email_adreess.getText().toString());
                map.put("mobile", edt_mobile.getText().toString());
                map.put("otp", GetOTP());


                return map;
            }
        };

        Volley.newRequestQueue(context).add(stringRequest);
    }
    @Override
    protected void onResume() {

        Task<Void> task = SmsRetriever.getClient(getApplicationContext()).startSmsUserConsent(null);
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("succcessss", "done");

            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("failerrr", e.toString());
            }
        });

        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsVerificationReceiver, intentFilter);

        super.onResume();

    }
    private BroadcastReceiver smsVerificationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                Status smsRetrieverStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

                switch (smsRetrieverStatus.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        // Get consent intent
                        Intent consentIntent = extras.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
                        try {
                            // Start activity to show consent dialog to user, activity must be started in
                            // 5 minutes, otherwise you'll receive another TIMEOUT intent
                            startActivityForResult(consentIntent, SMS_CONSENT_REQUEST);
                        } catch (ActivityNotFoundException e) {
                            // Handle the exception ...
                        }
                        break;
                    case CommonStatusCodes.TIMEOUT:
                        // Time out occurred, handle the error.
                        break;
                }
            }
        }
    };
    @Override
    protected void onPause() {
        super.onPause();
        try {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(smsVerificationReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final int SMS_CONSENT_REQUEST = 2;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SMS_CONSENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get SMS message content
                countDownTimer.cancel();
                tv_send_otp.setText("Resend Otp");
                assert data != null;
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                // Extract et_one-time code from the message and complete verification
                // `sms` contains the entire text of the SMS message, so you will need
                // to parse the string.
                String oneTimeCode = parseOneTimeCode(message); // define this function

                assert oneTimeCode != null;
                String fullMsg = oneTimeCode.replaceAll("[^0-9]", "");

                try {
                    // et_otp.setText(fullMsg);
                    char o1 = fullMsg.charAt(0);
                    char o2 = fullMsg.charAt(1);
                    char o3 = fullMsg.charAt(2);
                    char o4 = fullMsg.charAt(3);
                   /* char o5 = fullMsg.charAt(4);
                    char o6 = fullMsg.charAt(5);
*/

                    et_one.setText(o1 + "");
                    et_two.setText(o2 + "");
                    et_three.setText(o3 + "");
                    et_four.setText(o4 + "");
                    /*et_five.setText(o5 + "");
                    et_six.setText(o6 + "");
                    */
                    // et_otp.setText(o1 + o2 + o3 + o4 + o5 + o6);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //verifyUser();
                //saveUserVerified();

                Log.e("codeeeee", oneTimeCode.replaceAll("[^0-9]", ""));

                //   ToastMessage.makeText(getActivity(),oneTimeCode,ToastMessage.LENGTH_SHORT).show();
                // send et_one time code to the server
            } else {
                // Consent canceled, handle the error ...
            }
        }
    }
    String OTP;
    public String GetOTP() {
        String GETOTP = null;
        String Otp1 = et_one.getText().toString();
        String Otp2 = et_two.getText().toString();
        String Otp3 = et_three.getText().toString();
        String Otp4 = et_four.getText().toString();

        if (Otp1.length()<0) {
            myToast(this,"Enter otp");
            return null;

        }
        if (Otp2.length()<0) {
            myToast(this,"Enter otp");
            return null;

        }
        if (Otp3.length()<0) {
            myToast(this,"Enter otp");
            return null;

        }
        if (Otp4.length()<0) {
            myToast(this,"Enter otp");
            return null;
        }

        GETOTP = Otp1 + Otp2 + Otp3 + Otp4 ;//+ Otp5 + Otp6;
        return GETOTP;
    }
    private void myToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    private String parseOneTimeCode(String message) {
        return message;
    }
    }
