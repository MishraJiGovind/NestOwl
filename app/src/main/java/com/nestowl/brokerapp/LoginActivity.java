package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.brodcast.OtpResponseReciver;
import com.nestowl.model.LoginPojo;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.nestowl.utils.UtilityFunction;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    TextView tv, tv_otp, tv2, tv3, tv4, tv5,forget_password,title;
    CardView cardView;
    RadioGroup radioGroup;
    RadioButton otp, password;
    FrameLayout lnd_otp, lnd_password,FB_LOGIN,GOOGLE_LOGIN;
    Boolean is_Residential_click = false,otpsendenable;
    EditText one, two, three, four;
    Context context;
    Activity activity;
    EditText email, pass;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 562;
    CallbackManager callbackManager;
    LoginButton loginButton;
    boolean isForgetPassword,passowrdVisble=false;
    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        isForgetPassword = false;
        otpsendenable=true;
        setContentView(R.layout.activity_login);
        tv_otp = findViewById(R.id.sent_otp);
        lnd_otp = findViewById(R.id.lnr_otp);
        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.password);
        lnd_password = findViewById(R.id.lnr_password);
        one = findViewById(R.id.LOGIN_SCREEN_OTP_ONE);
        two = findViewById(R.id.LOGIN_SCREEN_OTP_TWO);
        three = findViewById(R.id.LOGIN_SCREEN_OTP_THREE);
        four = findViewById(R.id.LOGIN_SCREEN_OTP_FOUR);
        otp = findViewById(R.id.otp_login);
        radioGroup = findViewById(R.id.group_login);
        password = findViewById(R.id.password_loging);
        tv2 = findViewById(R.id.login_id);
        cardView = findViewById(R.id.card_login);
        FB_LOGIN=findViewById(R.id.FB_LOGIN);
        GOOGLE_LOGIN=findViewById(R.id.GOOGLE_LOGIN);
        loginButton=findViewById(R.id.login_button_fb);
        title=findViewById(R.id.LOGIN_TITLE);
        GOOGLE_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithGoogle();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=pass.getRight()-pass.getCompoundDrawables()[right].getBounds().width()){
                        int selection = pass.getSelectionEnd();
                        if (passowrdVisble){
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_show,0);
                            pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passowrdVisble=false;
                        }else {
                            pass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_hide,0);
                            pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passowrdVisble=true;
                        }
                        pass.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isForgetPassword){
                    ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setCancelable(false);
                    pd.setMessage("Wait...");
                    pd.show();
                    if (email.getText().equals("")|email.getText().length()<=0){
                        email.setError("Enter your number/email to reset password");
                        pd.cancel();
                        return;
                    }
                    StringRequest request = new StringRequest(Request.Method.POST, UrlClass.FORGET_PASS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status=jsonObject.getString("status");
                                String massage=jsonObject.getString("message");
                                if (status.equals("1")){
                                    new WarningDio(LoginActivity.this, massage, "OK", null, new WarningDio.Response() {
                                        @Override
                                        public void getClicks(int click) {
                                        }
                                    },false);
                                }else {
                                    new WarningDio(LoginActivity.this, massage, "Retry", null, new WarningDio.Response() {
                                        @Override
                                        public void getClicks(int click) {
                                            if(click==1){
                                                cardView.performClick();
                                            }
                                        }
                                    },false);
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
                            HashMap<String,String>hashMap = new HashMap<>();
                            hashMap.put("user",email.getText().toString());
                            return hashMap;
                        }
                    };
                    Volley.newRequestQueue(LoginActivity.this).add(request);
                }else {
                    if (password.isChecked()) {
                        if (email.getText().toString().isEmpty()) {
                            email.requestFocus();
                            email.setError("Please enter email/mobile no");
                            return;
                        }
                        if (pass.getText().toString().isEmpty()) {
                            pass.requestFocus();
                            pass.setError("Please enter password");
                            return;
                        }
                        loginApi();
                    } else {
                    }
                    if (otp.isChecked()) {
                        ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                        pd.setMessage("Verifying Otp...");
                        pd.setCancelable(false);
                        if (email.getText().toString().isEmpty()) {
                            email.requestFocus();
                            email.setError("Please enter email/mobile no");
                            pd.cancel();
                            return;
                        }
                        if (one.getText().toString().isEmpty()) {
                            one.requestFocus();
                            one.setError("Enter Otp here to continue");
                            tv_otp.performClick();
                            pd.cancel();
                            return;
                        }
                        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.OTP_Check_MOBILE, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.e("dfadfsa", response);
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    if (status.equals("1") && jsonObject.has("data")) {
                                        loginWithOtp();
                                        pd.cancel();
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
                                pd.cancel();
                                Toast.makeText(context, "Otp Validation Failed", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("mobile", email.getText().toString());
                                hashMap.put("otp", one.getText().toString());
                                return hashMap;
                            }
                        };
                        Volley.newRequestQueue(LoginActivity.this).add(request);

                    }
                }
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_password.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnd_otp.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lnd_otp.setVisibility(View.VISIBLE);
                }
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lnd_otp.setVisibility(View.GONE);
                if (is_Residential_click) {
                    is_Residential_click = false;
                    lnd_password.setVisibility(View.VISIBLE);
                } else {
                    is_Residential_click = true;
                    lnd_password.setVisibility(View.VISIBLE);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        forget_password = findViewById(R.id.forgot_password);
        tv_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!otpsendenable){
                    new WarningDio(LoginActivity.this, "Wait time to finish.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                    return;
                }
                ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Sending otp");
                pd.setCancelable(false);
                pd.show();
                if (otp.isChecked()){
                    String per = Manifest.permission.RECEIVE_SMS;
                    int grant = ContextCompat.checkSelfPermission(LoginActivity.this, per);
                    if (grant == PackageManager.PERMISSION_GRANTED){

                    }
                    if (email.getText().length()!=10){
                        email.requestFocus();
                        email.setError("Enter Mobile Number");
                        if (email.getText().toString().contains("@")|email.getText().toString().contains(".com")){
                            new WarningDio(LoginActivity.this, "You are using email, for otp use mobile number, otherwise use password", "OK", "CANCEL", new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        password.performClick();
                                    }else {
                                        email.setText(null);
                                    }
                                }
                            },false);
                        }
                        pd.cancel();
                        return;
                    }
                    StringRequest request =  new StringRequest(Request.Method.POST, UrlClass.OTP_MOBILE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.e("OtpR",response);
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                if (status.equals("1") && jsonObject.has("data")) {
                                    new OtpResponseReciver().setEditText(one);
                                    startCountDown();
//                                    cardView.performClick();
                                    pd.cancel();
                                } else {
                                    Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                pd.cancel();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Otp Send Failed", Toast.LENGTH_SHORT).show();
                            email.setText(null, null);
                            one.setText(null);
                            pd.cancel();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap =  new HashMap<>();
                            hashMap.put("mobile", email.getText().toString());
                            return hashMap;
                        }
                    };
                    Volley.newRequestQueue(LoginActivity.this).add(request);
                }

            }
        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isForgetPassword){
                    isForgetPassword=false;
                    title.setText("Login");
                    forget_password.setText("Forget Password");
                    radioGroup.setVisibility(View.VISIBLE);
                    if (otp.isChecked()){
                        lnd_otp.setVisibility(View.VISIBLE);
                    }else {
                        lnd_password.setVisibility(View.VISIBLE);
                    }
                   TextView textView = (TextView)cardView.getChildAt(0);
                   textView.setText("Sumbit");

               }else {
                   title.setText("Forgot Password");
                   isForgetPassword=true;
                   TextView textView = (TextView)cardView.getChildAt(0);
                   textView.setText("Forgot Password");
                   forget_password.setText("Back to Login");
                   lnd_otp.setVisibility(View.GONE);
                   lnd_password.setVisibility(View.GONE);
                   radioGroup.setVisibility(View.GONE);
               }
            }
        });
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginResult result = loginResult;
                Log.e("FB", "onSuccess: "+result.getAccessToken().getUserId() );
            }

            @Override
            public void onCancel() {
                Log.e("FB", "onSuccess: CAnCELED" );
            }

            @Override
            public void onError(@NonNull FacebookException e) {
                Log.e("FB", "onError: "+e );
            }
        });
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        LoginResult result = loginResult;
                        Log.e("FB", "onSuccess: "+result.getAccessToken().getUserId() );
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("FB", "onErrorBtn: "+exception );
                    }
                });
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        FB_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile"));
            }
        });

        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    two.requestFocus();
                }
            }
        });
        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    three.requestFocus();
                }
            }
        });
        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                    four.requestFocus();
                }
            }
        });
        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==1){
                   loginWithOtp();
                }
            }
        });
        one.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    one.setText(null);
                    UtilityFunction.hideKeybord(context, v);
                }
                return false;
            }
        });
        two.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    two.setText(null);
                    one.requestFocus();
                }
                return false;
            }
        });
        three.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    three.setText(null);
                    two.requestFocus();
                }
                return false;
            }
        });
        four.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_DEL){
                    four.setText(null);
                    three.requestFocus();
                }
                return false;
            }
        });


//        SmsRetrieverClient client = SmsRetriever.getClient(this);
//        Task<Void> task = client.startSmsRetriever();
//        task.addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                // Successfully started retriever, expect broadcast intent
//                // ...
//            }
//        });
//
//        task.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                // Failed to start retriever, inspect Exception for more details
//                // ...
//            }
//        });

        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                tv_otp.setText("" + String.format("%d:%d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                otpsendenable=true;
                tv_otp.setText("Resend Otp");
            }

        };



    }

    private void myToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    private void loginWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    private void updateUI(GoogleSignInAccount account) {
      Intent intent =  new Intent(this,SignUpActivity.class);
      intent.putExtra("name",account.getDisplayName());
      intent.putExtra("email",account.getEmail());
      intent.putExtra("s","true");
      startActivity(intent);
      finish();


    }
    private void loginWithFacebook() {
        Intent intent =  new Intent(this,SignUpActivity.class);
//        intent.putExtra("name",account.getDisplayName());
//        intent.putExtra("email",account.getEmail());
        intent.putExtra("s","true");
        startActivity(intent);
    }
    private static final int SMS_CONSENT_REQUEST = 2;
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
    @Override
    protected void onPause() {
        super.onPause();
        try {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(smsVerificationReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SMS_CONSENT_REQUEST) {
            if (resultCode == RESULT_OK) {
                countDownTimer.cancel();
                tv_otp.setText("Resend Otp");
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

                    one.setText(o1 + "");
                    two.setText(o2 + "");
                    three.setText(o3 + "");
                    four.setText(o4 + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Consent canceled, handle the error ...
            }
        }
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Log.e("G Login", "handleSignInResult: "+account.getDisplayName() );
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("G Login", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }
    private String parseOneTimeCode(String message) {
        return message;
    }
    public void loginApi() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("dfadfsa",response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        LoginPojo loginPojo = new Gson().fromJson(object.toString(), LoginPojo.class);
                        PrefMananger.SaveLoginData(context, loginPojo);
                        Intent intent = new Intent(LoginActivity.this,HomeScreen.class);
                        startActivity(intent);
                        finishAffinity();
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
                Log.e("fasdfafsd",error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user", email.getText().toString());
                map.put("password", pass.getText().toString());
                map.put("type", "password");
                return map;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
    public void loginWithOtp() {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.e("otpL",response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        LoginPojo loginPojo = new Gson().fromJson(object.toString(), LoginPojo.class);
                        PrefMananger.SaveLoginData(context, loginPojo);
                        Log.e("otp response", "onResponse: "+status );
                        Intent intent = new Intent(LoginActivity.this,HomeScreen.class);
                        startActivity(intent);
                        finishAffinity();


                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("otpE",error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user", email.getText().toString());
                map.put("password", GetOTP());
                map.put("type", "otp");
                return map;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
    }
    public String GetOTP() {
        String GETOTP = null;
        String Otp1 = one.getText().toString();
        String Otp2 = two.getText().toString();
        String Otp3 = three.getText().toString();
        String Otp4 = four.getText().toString();

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
    private CountDownTimer countDownTimer;
    private void startCountDown() {
        otpsendenable=false;
       countDownTimer.start();

    }

}