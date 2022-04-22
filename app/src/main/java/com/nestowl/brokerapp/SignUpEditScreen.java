package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.User;
import com.nestowl.utils.GetFiles;
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

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpEditScreen extends AppCompatActivity {
    ImageView back_img,save,dp;
    EditText firstname,lastname,email,phone,otpa,otpb,otpc,otpd,passwordOne,passwordTwo;
    TextView otpSend,verifyotp,updatepassword;
    ConstraintLayout password;
    LinearLayout otpInputs;
    CardView sumbit;
    User user;
    Context context;
    Activity activity;
    MultipartBody.Part dppart;
    GetFiles getFiles;
    boolean isDpUpdating,isNameUpdating,isEmail,isNumber,isNumberVerified;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_edit_screen);
        context = activity = this;
        progressDialog =  new ProgressDialog(this);
        firstname=findViewById(R.id.EDIT_PROFILE_FIRST_NAME);
        lastname=findViewById(R.id.EDIT_PROFILE_LAST_NAME);
        email=findViewById(R.id.EDIT_PROFILE_EMAIL);
        phone=findViewById(R.id.EDIT_PROFILE_PHONE);
        sumbit=findViewById(R.id.EDIT_PROFILE_SUMBIT);
        save=findViewById(R.id.EDIT_PROFILE_Ok);
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        dp=findViewById(R.id.EDIT_PROFILE_DP);
        otpa=findViewById(R.id.EDIT_PROFILE_OTP_A);
        otpb=findViewById(R.id.EDIT_PROFILE_OTP_B);
        otpc=findViewById(R.id.EDIT_PROFILE_OTP_C);
        otpd=findViewById(R.id.EDIT_PROFILE_OTP_D);
        otpSend=findViewById(R.id.EDIT_PROFILE_PHONE_OTP);
        verifyotp=findViewById(R.id.EDIT_PROFILE_OTP_VARIFY_OTP);
        updatepassword=findViewById(R.id.EDIT_PROFILE_OTP_UPDATE_PASSWORD);
        otpInputs=findViewById(R.id.EDIT_PROFILE_OTP_INPUTS);
        passwordOne=findViewById(R.id.EDIT_PROFILE_PASSWORD_ONE);
        passwordTwo=findViewById(R.id.EDIT_PROFILE_PASSWORD_TWO);
        password=findViewById(R.id.EDIT_PROFILE_UPDATE_PASSWORD_MAIN);

        user=new Gson().fromJson(PrefMananger.getString(this,"user").toString(),User.class);
        showData(user);
        sumbit.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
        isDpUpdating=false;
        isNameUpdating=false;
        isEmail=false;
        isNumber=false;
        isNumberVerified=false;
        dppart =  null;
        getFiles =  new GetFiles(this);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sumbit.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                isNameUpdating=true;
            }
        });
        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sumbit.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                isNameUpdating=true;
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumbit.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                SelectImage();

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if (!s.toString().equals(user.getEmail())){
                        if (s.toString().contains("@")&&s.toString().contains(".com")){
                        varifyemail();
                        }
                    }else {
                        sumbit.setVisibility(View.GONE);
                        save.setVisibility(View.GONE);
                    }
            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.equals(user.getMobile())){
                    sumbit.setVisibility(View.VISIBLE);
                    save.setVisibility(View.VISIBLE);
                    if (s.length()==10){
                        otpSend.setVisibility(View.VISIBLE);
                        isNumber=true;
                    }
                }else {
                    sumbit.setVisibility(View.GONE);
                    save.setVisibility(View.GONE);
                    otpSend.setVisibility(View.GONE);
                }
            }
        });
        sumbit.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
               if (isNameUpdating){
                   if (firstname.getText().length()<=0){
                       firstname.setError("Enter Your First Name");
                       return;
                   }
                   if (lastname.getText().length()<=0){
                       lastname.setError("Enter Your Last Name");
                       return;
                   }
               }
               if (isEmail){
                   if (email.getText().length()<=0){
                       email.setError("Enter Valid Email");
                       return;
                   }
               }
               if (isNumber){
                   if (phone.getText().length()!=10){
                       phone.setError("Enter Valid Number");
                       return;
                   }
               }
                user.setFirst_name(firstname.getText().toString());
                user.setLast_name(lastname.getText().toString());
                saveData();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameUpdating){
                    if (firstname.getText().length()<=0){
                        firstname.setError("Enter Your First Name");
                        return;
                    }
                    if (lastname.getText().length()<=0){
                        lastname.setError("Enter Your Last Name");
                        return;
                    }
                }
                if (isEmail){
                    if (email.getText().length()<=0){
                        email.setError("Enter Valid Email");
                        return;
                    }
                }
                if (isNumber){
                    if (phone.getText().length()!=10){
                        phone.setError("Enter Valid Number");
                        return;
                    }
                }
                user.setFirst_name(firstname.getText().toString());
                user.setLast_name(lastname.getText().toString());
                saveData();
            }
        });
        otpSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp();
            }
        });
        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOtp();
            }
        });

        otpa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    otpb.requestFocus();
                }
            }
        });
        otpb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    otpc.requestFocus();
                }
            }
        });
        otpc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    otpd.requestFocus();
                }
            }
        });
        otpd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    checkOtp();
                }
            }
        });
        otpa.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    otpa.setText(null);
                    UtilityFunction.hideKeybord(context, v);
                }
                return false;
            }
        });
        otpb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    otpb.setText(null);
                    otpa.requestFocus();
                }
                return false;
            }
        });
        otpc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    otpc.setText(null);
                    otpb.requestFocus();
                }
                return false;
            }
        });
        otpd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    otpd.setText(null);
                    otpc.requestFocus();
                }
                return false;
            }
        });

        updatepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getVisibility()==View.GONE){
                    password.setVisibility(View.VISIBLE);
                    otpInputs.setVisibility(View.GONE);
                    verifyotp.setVisibility(View.GONE);
                }else {
                    if (passwordOne.getText().length()<=0){
                        passwordOne.setError("Enter new password");
                        passwordOne.requestFocus();
                        return;
                    }
                    if (passwordTwo.getText().length()<=0){
                        passwordTwo.setError("Confirm your password");
                        passwordTwo.requestFocus();
                        return;
                    }
//                    if (!passwordOne.getText().equals(passwordTwo.getText())){
//                        new WarningDio(SignUpEditScreen.this, "Password not match try again", "OK", null, new WarningDio.Response() {
//                            @Override
//                            public void getClicks(int click) {
//                                if (click==1){
//                                    passwordTwo.setText(null);
//                                    passwordTwo.requestFocus();
//                                }
//                            }
//                        },false);
//                        return;
//                    }
                    updatePassWord();
                }
            }
        });
    }

    private void updatePassWord() {
        progressDialog.setMessage("Updating Password");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.UPDATE_PASSWORD, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("PASSWORD", "onResponse: "+response );
                    String  status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(SignUpEditScreen.this, jsonObject.getString("message"), "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                password.setVisibility(View.GONE);
                            }
                        },false);
                    }else {
                        new WarningDio(SignUpEditScreen.this, jsonObject.getString("message"), "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                passwordOne.setText(null);
                                passwordTwo.setText(null);
                                passwordOne.requestFocus();
                            }
                        },false);
                    }
                }catch (Exception e){
                    Log.e("PASSWORD", "onResponse: "+e );

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("password",passwordOne.getText().toString());
                hashMap.put("password_confirmation",passwordTwo.getText().toString());
                hashMap.put("user_id",user.getUser_id());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void varifyemail() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_PROFILE_BY_Email, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("VerifyEmail", "onResponse: "+response );
                    String status =  jsonObject.getString("status");
                    User data =  new Gson().fromJson(jsonObject.getString("data").toString(),User.class);
                    if (status.equals("1")){
                        sumbit.setVisibility(View.VISIBLE);
                        save.setVisibility(View.VISIBLE);
                        user.setEmail(email.getText().toString());
                        isNameUpdating=true;
                    }else {
                        if (data==null){
                            new WarningDio(SignUpEditScreen.this, "Can not change email right now try again later" , "OK", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        email.setText(user.getEmail());
                                    }
                                }
                            },false);
                            return;
                        }
                        if (!user.getUser_id().equals(data.getUser_id())){
                            new WarningDio(SignUpEditScreen.this, "This email is alerady added in " + data.getFirst_name() + " account.", "Change Email", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        email.setText(null);
                                        email.requestFocus();
                                    }
                                }
                            },false);
                        }else{
                            new WarningDio(SignUpEditScreen.this, "This email is already added", "Change Email", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        email.setText(null);
                                        email.requestFocus();
                                    }
                                }
                            },false);
                        }
                    }
                }catch (Exception e){
                    Log.e("ERROR", "onResponse: "+e );
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
                hashMap.put("email",email.getText().toString());
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    } 
    private CountDownTimer countDownTimer;
    private void startCountDown() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                otpSend.setText("" + String.format("%d:%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
            public void onFinish() {
                otpSend.setText("RESEND OTP");
            }
        }.start();
    }
    public void otp() {
        final ProgressDialog progressDialog = new ProgressDialog(SignUpEditScreen.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.OTP, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        startCountDown();
                        otpInputs.setVisibility(View.VISIBLE);
                        verifyotp.setVisibility(View.VISIBLE);
                        updatepassword.setVisibility(View.GONE);
                        Toast.makeText(SignUpEditScreen.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignUpEditScreen.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(SignUpEditScreen.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", email.getText().toString());
                map.put("mobile", phone.getText().toString());
                return map;
            }
        };
        Volley.newRequestQueue(SignUpEditScreen.this).add(stringRequest);
    }
    public void checkOtp() {
        final ProgressDialog progressDialog = new ProgressDialog(SignUpEditScreen.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.OTP_Check, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                      isNumberVerified=true;
                      otpInputs.setVisibility(View.GONE);
                      otpSend.setVisibility(View.GONE);
                      verifyotp.setVisibility(View.GONE);
                      updatepassword.setVisibility(View.VISIBLE);
                        isNameUpdating=true;
                      new WarningDio(SignUpEditScreen.this, "Your new mobile number verified successfully.", "OK", null, new WarningDio.Response() {
                          @Override
                          public void getClicks(int click) {

                          }
                      },false);
                      user.setMobile(phone.getText().toString());
                    } else {
                        Toast.makeText(SignUpEditScreen.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        otpInputs.setVisibility(View.GONE);
                        otpSend.setVisibility(View.GONE);
                        verifyotp.setVisibility(View.GONE);
                        updatepassword.setVisibility(View.VISIBLE);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(SignUpEditScreen.this, error.toString(), Toast.LENGTH_SHORT).show();
                otpInputs.setVisibility(View.GONE);
                otpSend.setVisibility(View.GONE);
                verifyotp.setVisibility(View.GONE);
                updatepassword.setVisibility(View.VISIBLE);

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", email.getText().toString());
                map.put("mobile", phone.getText().toString());
                map.put("otp", GetOTP());


                return map;
            }
        };

        Volley.newRequestQueue(SignUpEditScreen.this).add(stringRequest);
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
    private static final int SMS_CONSENT_REQUEST = 67;
    String OTP;
    public String GetOTP() {
        String GETOTP = null;
        String Otp1 = otpa.getText().toString();
        String Otp2 = otpb.getText().toString();
        String Otp3 = otpc.getText().toString();
        String Otp4 = otpd.getText().toString();

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
    private void showData(User user) {
        firstname.setText(user.getFirst_name());
        lastname.setText(user.getLast_name());
        email.setText(user.getEmail());
        phone.setText(user.getMobile());
        Log.e("dpUser", "showData: "+user.getAvatar() );
        Glide.with(this).load(user.getAvatar()).placeholder(R.drawable.user_icon_p).into(dp);
    }
    private void SelectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(SignUpEditScreen.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(SignUpEditScreen.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(SignUpEditScreen.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(SignUpEditScreen.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 200);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==1){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                dppart=getFiles.getPartBody("profile_photo",bitmap);
                dp.setImageBitmap(bitmap);
                isDpUpdating=true;
            }
            if (requestCode==2){
                Uri filePath = data.getData();
                dppart=getFiles.getPartBody("profile_photo",filePath);
                dp.setImageURI(filePath);
                isDpUpdating=true;
            }
            if (requestCode == SMS_CONSENT_REQUEST) {
                assert data != null;
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                String oneTimeCode = parseOneTimeCode(message);
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

                    otpa.setText(o1 + "");
                    otpb.setText(o2 + "");
                    otpc.setText(o3 + "");
                    otpd.setText(o4 + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("codeeeee", oneTimeCode.replaceAll("[^0-9]", ""));
            }
        }
    }
    public void saveData(){
        progressDialog.setMessage("Updating Data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.UPDATE_PERSONAL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")){
                        String json = new Gson().toJson(user);
                        PrefMananger.saveString(SignUpEditScreen.this,"user",json);
                        new WarningDio(SignUpEditScreen.this, "Update Successfull", "Go to home screen", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    finish();
                                }
                            }
                        },false);
                    }
                }catch (Exception e){
                    Log.e("Update Error", "onResponse: "+e );
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Update Error", "onResponse: "+error );

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>hashMap=new HashMap<>();
                hashMap.put("user_id",user.getUser_id());
                hashMap.put("first_name",user.getFirst_name());
                hashMap.put("last_name",user.getLast_name());
                hashMap.put("email",user.getEmail());
                hashMap.put("mobile",user.getMobile());
                return hashMap;
            }
        };
          if (isDpUpdating){
             RequestBody userId = RequestBody.create(MultipartBody.FORM, user.getUser_id());
             HashMap<String, RequestBody> map = new HashMap<>();
             map.put("user_id", userId);
             ApiExecutor.getApiService().updateDp(dppart,map).enqueue(new Callback<OtpResponse>() {
                 @Override
                 public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                     if (response.isSuccessful()){
                         if (isNameUpdating){
                         Volley.newRequestQueue(SignUpEditScreen.this).add(request);
//                             savePersonal();
                         }else {
                             progressDialog.dismiss();
                             new WarningDio(SignUpEditScreen.this, "Update Successfull", "Go to home screen", null, new WarningDio.Response() {
                                 @Override
                                 public void getClicks(int click) {
                                     if (click==1){
                                         finish();
                                     }
                                 }
                             },false);
                         }
                     }
                 }

                 @Override
                 public void onFailure(Call<OtpResponse> call, Throwable t) {

                 }
             });
            }else if (isNameUpdating){
//                savePersonal();
              Volley.newRequestQueue(SignUpEditScreen.this).add(request);
          }

    }
//    private void savePersonal() {
//        ApiExecutor.getApiService().updateUserPersonal(user).enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                progressDialog.dismiss();
//                if (response.isSuccessful()){
//                    new WarningDio(SignUpEditScreen.this, "Update Successful", "Go to home screen", null, new WarningDio.Response() {
//                        @Override
//                        public void getClicks(int click) {
//                            if (click==1){
//                                finish();
//                            }
//                        }
//                    },false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                progressDialog.dismiss();
//            }
//        });
//    }
}
