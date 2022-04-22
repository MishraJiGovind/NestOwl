package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.nestowl.AdapterClass.ImageUploadAdapter;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.ImagePOJO;
import com.nestowl.model.OtpResponse;
import com.nestowl.utils.FileUtil;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

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

public class SignUpServiceCharges extends AppCompatActivity {
    ImageView back_img;
    ScrollView scrollView;
    CardView cardView;
    String dealing = "";
    String drop_facility = "";
    String vehicle_typ = "";
    String loan_facility = "";
    String register_facility = "";
    String buyer_seller_verification = "";
    TextView tv_sell,tv_drop_yes,tv_drop_no,if_customer,tv_vehicle_four_wheelers,tv_vehicle_two_wheelers;
    EditText edt_educational,edt_any_extra_achiv;
    TextView tv_yes_loan,tv_no_loan,tv_yes_register,tv_no_register,tv_yes_verification,tv_no_verification;
    Context context;
    CheckBox ch_service_charge;
    Activity activity;
    FrameLayout profile_add_photos,logo_add_photos;
    ImageView profile_photo_img,logo_photo_img;
    ArrayList<ImagePOJO> bitmapList = new ArrayList<>();
    ImageUploadAdapter uploadAdapter;
    boolean isAdding;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        setContentView(R.layout.activity_sign_up_service_charges);
        cardView=findViewById(R.id.PROFILE_CONTACT_US_SUMBIT);
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        ch_service_charge=findViewById(R.id.checkbox_id);
        tv_sell=findViewById(R.id.sell_id);
        profile_add_photos = findViewById(R.id.profile_add_photos);
        profile_photo_img = findViewById(R.id.profile_photo_img);
        logo_add_photos = findViewById(R.id.logo_add_photos);
        logo_photo_img = findViewById(R.id.logo_photo_img);

        scrollView=findViewById(R.id.scroll);
        tv_yes_loan=findViewById(R.id.yes_loan);
        tv_yes_verification=findViewById(R.id.yes_verification);
        tv_no_verification=findViewById(R.id.no_verification);
        tv_yes_loan=findViewById(R.id.yes_loan);
        tv_no_loan=findViewById(R.id.no_loan);
        tv_yes_register=findViewById(R.id.yes_register);
        tv_no_register=findViewById(R.id.no_register);
        tv_drop_yes=findViewById(R.id.drop_facility_yes);
        tv_drop_no=findViewById(R.id.drop_facility_no);
        if_customer=findViewById(R.id.if_customer_id);
        tv_vehicle_four_wheelers=findViewById(R.id.four_wheelers_id);
        tv_vehicle_two_wheelers=findViewById(R.id.two_wheelers_id);
        edt_educational=findViewById(R.id.educational_id);
        edt_any_extra_achiv=findViewById(R.id.any_extra_achievement_id);
        getServiceChargeDetails();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdding){
                    setServices();
                }else {
                updateSeriviceChargeDetails();
                }
            }
        });
        profile_add_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLICK_ON = SECOND_IMAGE;
                SelectImage();
            }

        });
        logo_add_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLICK_ON = THIRDE_IMAGE;
                SelectImage();
            }

        });
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                cardView.setVisibility(View.GONE);
                if (scrollY>55){
                    cardView.setVisibility(View.VISIBLE);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpServiceCharges.this,EditSignUpForm.class);
                startActivity(intent);
            }
        });
        tv_drop_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop_facility=tv_drop_yes.getText().toString();
                selected(tv_drop_yes);
                unselected(tv_drop_no);
                unselected(if_customer);
            }
        });
        tv_drop_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop_facility=tv_drop_no.getText().toString();
                unselected(if_customer);
                unselected(tv_drop_yes);
                selected(tv_drop_no);
            }
        });
        if_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drop_facility=if_customer.getText().toString();
                unselected(tv_drop_yes);
                unselected(tv_drop_no);
                selected(if_customer);
            }
        });
        tv_vehicle_four_wheelers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle_typ=tv_vehicle_four_wheelers.getText().toString();
                selected(tv_vehicle_four_wheelers);
                unselected(tv_vehicle_two_wheelers);
            }
        });
        tv_vehicle_two_wheelers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle_typ=tv_vehicle_two_wheelers.getText().toString();
                selected(tv_vehicle_two_wheelers);
                unselected(tv_vehicle_four_wheelers);
            }
        });
        tv_yes_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loan_facility=tv_yes_loan.getText().toString();
                selected(tv_yes_loan);
                unselected(tv_no_loan);
            }
        });
        tv_no_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loan_facility=tv_no_loan.getText().toString();
                selected(tv_no_loan);
                unselected(tv_yes_loan);
            }
        });
        tv_yes_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_facility=tv_yes_register.getText().toString();
                selected(tv_yes_register);
                unselected(tv_no_register);
            }
        });
        tv_no_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_facility=tv_no_register.getText().toString();
                selected(tv_no_register);
                unselected(tv_yes_register);
            }
        });
        tv_yes_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyer_seller_verification=tv_yes_verification.getText().toString();
                selected(tv_yes_verification);
                unselected(tv_no_verification);
            }
        });
        tv_no_verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyer_seller_verification=tv_no_verification.getText().toString();
                selected(tv_no_verification);
                unselected(tv_yes_verification);
            }
        });
        tv_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dealing.isEmpty()) {
                    dealing = tv_sell.getText().toString();
                    selected(tv_sell);
                } else {
                    if (dealing.contains(tv_sell.getText().toString())) {
                        /*if (dealing.contains(tv_sell.getText().toString() + ",")) {
                            dealing = dealing.replace(tv_sell.getText().toString() + ",", "");
                        } else if (dealing.contains("," + tv_sell.getText().toString())) {
                            dealing = dealing.replace("," + tv_sell.getText().toString(), "");
                        } else {
                            dealing = dealing.replace(tv_sell.getText().toString(), "");
                        }
                        */
                        dealing="";
                        unselected(tv_sell);
                    } else {
                        dealing = dealing + "," + tv_sell.getText().toString();
                        selected(tv_sell);
                    }
                }
            }
        });
    }
    String CLICK_ON = "";
    String SECOND_IMAGE = "SecondImage";
    String THIRDE_IMAGE = "ThirdImage";
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


    Bitmap bitmap;
    Uri filePath;

    Bitmap bitmap2;
    Bitmap bitmap3;

    Uri filePath2;
    Uri filePath3;


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                bitmap = (Bitmap) data.getExtras().get("data");


                Log.e("1258963", bitmap + "");
                File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(file1);

                 if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    profile_photo_img.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    logo_photo_img.setImageBitmap(bitmap);
                }
            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
                    //iv_UploadImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                  if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    filePath2 = filePath;
                    profile_photo_img.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    filePath3 = filePath;
                    logo_photo_img.setImageBitmap(bitmap);
                }
            }
        }
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

    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
    public void getServiceChargeDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_SERVICE_CHARGE, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        dealing=object.getString("property_type");
                        drop_facility=object.getString("site_visit");/*
                        String customer_education_doc=object.getString("customer_education_doc");
                        String any_extra_achivement_doc=object.getString("any_extra_achivement_doc");*/
 /*                       Glide.with(context).load(UrlClass.BASE_URL+object.getString("customer_education_doc")).into(profile_photo_img);
                        Glide.with(context).load(UrlClass.BASE_URL+object.getString("any_extra_achivement_doc")).into(logo_photo_img);

*/
                        String aadhar_front=object.getString("customer_education_doc");
                        String aadhar_back=object.getString("any_extra_achivement_doc");
                        if (aadhar_front!=null && !aadhar_front.trim().isEmpty()) {
                            Glide.with(context).load(UrlClass.BASE_URL + object.getString("customer_education_doc")).into(profile_photo_img);
                        }
                        if (aadhar_back!=null && !aadhar_back.trim().isEmpty()) {
                            Glide.with(context).load(UrlClass.BASE_URL+object.getString("any_extra_achivement_doc")).into(logo_photo_img);
                        }
                        vehicle_typ=object.getString("vehicle_type_you_own");
                        loan_facility=object.getString("loan_facility");
                        register_facility=object.getString("registery_facility");
                        buyer_seller_verification=object.getString("customer_verification");
                        edt_educational.setText(object.getString("customer_education"));
                        edt_any_extra_achiv.setText(object.getString("any_extra_achivement"));
                        ch_service_charge.setText(object.getString("service_charge"));



                        if (object.getString("service_charge").equals("Fix 1 %")){
                            ch_service_charge.setChecked(true);
                        }
                        if (dealing.equals("Sale")){
                            selected(tv_sell);
                        }
                        if (drop_facility.contains("yes")){
                            selected(tv_drop_yes);
                            unselected(tv_drop_no);
                            unselected(if_customer);
                        }else if (drop_facility.contains("No")){
                            selected(tv_drop_no);
                            unselected(tv_drop_yes);
                            unselected(if_customer);
                        }
                        else if (drop_facility.contains("If Customer Demands")){
                            selected(if_customer);
                            unselected(tv_drop_no);
                            unselected(tv_drop_yes);
                        }
                        if (vehicle_typ.contains("4 Wheelers")){
                            selected(tv_vehicle_four_wheelers);
                            unselected(tv_vehicle_two_wheelers);

                        }else if (vehicle_typ.contains("2 Wheelers")){
                            selected(tv_vehicle_two_wheelers);
                            unselected(tv_vehicle_four_wheelers);
                        }
                        if (loan_facility.contains("Yes")){
                            selected(tv_yes_loan);
                            unselected(tv_no_loan);

                        }else if (loan_facility.contains("No")){
                            selected(tv_no_loan);
                            unselected(tv_yes_loan);
                        }


                        if (register_facility.contains("Yes")){
                            selected(tv_yes_register);
                            unselected(tv_no_register);

                        }else if (register_facility.contains("No")){
                            selected(tv_no_register);
                            unselected(tv_yes_register);
                        }

                        if (buyer_seller_verification.contains("Yes")){
                            selected(tv_yes_verification);
                            unselected(tv_no_verification);

                        }else if (buyer_seller_verification.contains("No")){
                            selected(tv_no_verification);
                            unselected(tv_yes_verification);
                        }

                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        TextView textView = (TextView)cardView.getChildAt(0);
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
    private void updateSeriviceChargeDetails() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        try {
            //   RequestBody subCategory = RequestBody.create(MultipartBody.FORM, UtilityFunction.getCalculatedDate("yyyy-MM-dd",0));


            int compressionRatio = 25; //1 == originalImage, 2 = 50% compression, 4=25% compress
            File file1;

            MultipartBody.Part logoPart=null;
            MultipartBody.Part profilePart=null;
            if (filePath2 != null) {
                file1 = FileUtil.from(this, filePath2);
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

                    Bitmap bmp = BitmapFactory.decodeFile(file1.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file1.getName());
                    Log.e("Type", file1.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                }
                profilePart = MultipartBody.Part.createFormData("customer_education_doc", file1.getName(), videoPart);
            } else if (bitmap2!=null) {
                file1 = bitmapToFile(bitmap2, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file1.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file1));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                if (file1.getName() == null) {

                } else {
                    profilePart = MultipartBody.Part.createFormData("customer_education_doc", file1.getName(), videoPart);
                }
            }
                 File file2;

            if (filePath3 != null) {
                file2 = FileUtil.from(this, filePath3);
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
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file2.getName());
                    Log.e("Type", file2.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file2);
                }
                logoPart = MultipartBody.Part.createFormData("any_extra_achivement_doc", file2.getName(), videoPart);
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
                    logoPart = MultipartBody.Part.createFormData("any_extra_achivement_doc", file2.getName(), videoPart);
                }

            }

            RequestBody userId = RequestBody.create(MultipartBody.FORM, String.valueOf(PrefMananger.GetLoginData(context).getUserId() ));
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody property_type = RequestBody.create(MultipartBody.FORM, dealing);
            RequestBody customer_education = RequestBody.create(MultipartBody.FORM, edt_educational.getText().toString().split(" ").toString());
            RequestBody any_extra_achivement = RequestBody.create(MultipartBody.FORM, edt_any_extra_achiv.getText().toString().split(" ").toString());
            RequestBody site_visit = RequestBody.create(MultipartBody.FORM, drop_facility);
            RequestBody vehicle_type_you_own = RequestBody.create(MultipartBody.FORM, vehicle_typ);
            RequestBody loanFacility = RequestBody.create(MultipartBody.FORM, loan_facility);
            RequestBody registery_facility = RequestBody.create(MultipartBody.FORM, register_facility);
            RequestBody customer_verification = RequestBody.create(MultipartBody.FORM, buyer_seller_verification);

            //  RequestBody subCategory = RequestBody.create(MultipartBody.FORM, subCategoryId + "");
            HashMap<String, RequestBody> hashMap = new HashMap<>();
            hashMap.put("user_id", userId);
            hashMap.put("property_type", property_type);
            hashMap.put("customer_education[]", customer_education);
            hashMap.put("any_extra_achivement[]", any_extra_achivement);
            hashMap.put("site_visit", site_visit);
            hashMap.put("vehicle_type_you_own", vehicle_type_you_own);
            hashMap.put("loan_facility", loanFacility);
            hashMap.put("registery_facility", registery_facility);
            hashMap.put("customer_verification",customer_verification );
            hashMap.put("status", status);

            ApiExecutor.getApiService().updateServiceCharge(
                    logoPart,profilePart,hashMap
            ).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    try {
                        dialog.dismiss();
                        Log.e("response", "onResponse: "+response );
                        Intent intent = new Intent(SignUpServiceCharges.this, SignUpEarnMoney.class);
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("fdsadf",t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }
    private void setServices(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        try {
            //   RequestBody subCategory = RequestBody.create(MultipartBody.FORM, UtilityFunction.getCalculatedDate("yyyy-MM-dd",0));


            int compressionRatio = 25; //1 == originalImage, 2 = 50% compression, 4=25% compress
            File file1;

            MultipartBody.Part logoPart=null;
            MultipartBody.Part profilePart=null;
            if (filePath2 != null) {
                file1 = FileUtil.from(this, filePath2);
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

                    Bitmap bmp = BitmapFactory.decodeFile(file1.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file1.getName());
                    Log.e("Type", file1.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                }
                profilePart = MultipartBody.Part.createFormData("customer_education_doc", file1.getName(), videoPart);
            } else if (bitmap2!=null) {
                file1 = bitmapToFile(bitmap2, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file1.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file1));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                if (file1.getName() == null) {

                } else {
                    profilePart = MultipartBody.Part.createFormData("customer_education_doc", file1.getName(), videoPart);
                }
            }
            File file2;

            if (filePath3 != null) {
                file2 = FileUtil.from(this, filePath3);
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
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file2.getName());
                    Log.e("Type", file2.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file2);
                }
                logoPart = MultipartBody.Part.createFormData("any_extra_achivement_doc", file2.getName(), videoPart);
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
                    logoPart = MultipartBody.Part.createFormData("any_extra_achivement_doc", file2.getName(), videoPart);
                }

            }

            RequestBody userId = RequestBody.create(MultipartBody.FORM, String.valueOf(PrefMananger.GetLoginData(context).getUserId() ));
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody property_type = RequestBody.create(MultipartBody.FORM, dealing);
            RequestBody customer_education = RequestBody.create(MultipartBody.FORM, edt_educational.getText().toString().split(" ").toString());
            RequestBody any_extra_achivement = RequestBody.create(MultipartBody.FORM, edt_any_extra_achiv.getText().toString().split(" ").toString());
            RequestBody site_visit = RequestBody.create(MultipartBody.FORM, drop_facility);
            RequestBody vehicle_type_you_own = RequestBody.create(MultipartBody.FORM, vehicle_typ);
            RequestBody loanFacility = RequestBody.create(MultipartBody.FORM, loan_facility);
            RequestBody registery_facility = RequestBody.create(MultipartBody.FORM, register_facility);
            RequestBody customer_verification = RequestBody.create(MultipartBody.FORM, buyer_seller_verification);

            //  RequestBody subCategory = RequestBody.create(MultipartBody.FORM, subCategoryId + "");
            HashMap<String, RequestBody> hashMap = new HashMap<>();
            hashMap.put("user_id", userId);
            hashMap.put("property_type", property_type);
            hashMap.put("customer_education[]", customer_education);
            hashMap.put("any_extra_achivement[]", any_extra_achivement);
            hashMap.put("site_visit", site_visit);
            hashMap.put("vehicle_type_you_own", vehicle_type_you_own);
            hashMap.put("loan_facility", loanFacility);
            hashMap.put("registery_facility", registery_facility);
            hashMap.put("customer_verification",customer_verification );
            hashMap.put("status", status);

            ApiExecutor.getApiService().setServiceCharge(
                    logoPart,profilePart,hashMap
            ).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    try {
                        dialog.dismiss();
                        Log.e("response", "onResponse: "+response );
                        Intent intent = new Intent(SignUpServiceCharges.this, SignUpEarnMoney.class);
                        startActivity(intent);
                        finish();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                @Override
                public void onFailure(Call<OtpResponse> call, Throwable t) {
                    dialog.dismiss();
                    Log.e("fdsadf",t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }



}