package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.nestowl.utils.FileUtil;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpVerification extends AppCompatActivity {
    ImageView back_img;
    TextView rera_frm,_aadhar_frm;
    LinearLayout rera_lnr,adhar_card_lnr;
    Boolean is_Residential_click = false;
    Boolean is_available = false;
    Boolean in_process = false;
    Boolean is_not_available = false;
    boolean isAdhar = false;
    boolean isRera= false;
    boolean isImagesAdded=false;
    TextView availble,process,not_available;
    LinearLayout lnr_availble,lnr_process;
    ImageView img_calendar,img_frondt_adhar,img_back_adhar,img_choose_file;
    TextView tv_calendar_text;
    CardView verification;
    Calendar calendar;
    EditText edt_verification_link;
    Context context;
    Activity activity;
    String  verification_typee ="";
    String  join_now_serve ="";
    EditText edt_adhar_card;
    FrameLayout frm_front_adhar,frm_back_adhar,frm_choose_file;
    LinearLayout lnr_date,lnr_verification_link;
    boolean isAdding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        setContentView(R.layout.activity_sign_up_verification);
        not_available=findViewById(R.id.not_available_frame);
        rera_frm=findViewById(R.id.frm_rera);
        back_img=findViewById(R.id.EDIT_PROFILE_BACK);
        img_frondt_adhar=findViewById(R.id.img_front_photo);
        img_back_adhar=findViewById(R.id.img_back_photo);
        frm_choose_file=findViewById(R.id.choose_file_frm);
        img_choose_file=findViewById(R.id.choose_file_img);
        calendar = Calendar.getInstance();
        edt_verification_link=findViewById(R.id.verification_link_edt);
        _aadhar_frm=findViewById(R.id.frm_aadhar);
        rera_lnr=findViewById(R.id.rera_ir);
        frm_front_adhar=findViewById(R.id.frm_add_front_photo);
        frm_back_adhar=findViewById(R.id.frm_add_back_photo);
        edt_adhar_card=findViewById(R.id.adhar_card_verification_id);
        availble=findViewById(R.id.available_frame);
        process=findViewById(R.id.in_process_frame);
        lnr_availble=findViewById(R.id.lnd_available);
        lnr_process=findViewById(R.id.lnd_process);
        img_calendar=findViewById(R.id.img_calendar_verification);
        tv_calendar_text=findViewById(R.id.tv_calendar_verification);
        adhar_card_lnr=findViewById(R.id.aadhar_card);
        verification=findViewById(R.id.card_verification);
        lnr_verification_link=findViewById(R.id.lnr_verification_link);
        lnr_date=findViewById(R.id.lnr_verification_date);
        frm_front_adhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = SECOND_IMAGE;
                SelectImage();
            }
        });
        frm_back_adhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = THIRDE_IMAGE;
                SelectImage();
            }
        });
        frm_choose_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLICK_ON = FIRST_IMAGE;
                SelectImage();
            }
        });
        final DatePickerDialog.OnDateSetListener fromdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, dayOfMonth);
                date1();

            }
        };
        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=
                new DatePickerDialog(SignUpVerification.this, fromdate, calendar.get(calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        verification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdding){
                    if (isImagesAdded){
                    setVerification();
                    }else {
                        new WarningDio(SignUpVerification.this, "Image not selected please select.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }
                }else {
                updateVerificationDetails();
                }
            }
        });
        rera_frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRera = true;
                isAdhar=false;
                adhar_card_lnr.setVisibility(View.GONE);
                _aadhar_frm.setBackgroundResource(R.drawable.employe_circle_rounded);
                lnr_verification_link.setVisibility(View.GONE);
                lnr_date.setVisibility(View.GONE);
                lnr_process.setVisibility(View.GONE);
                is_not_available=false;
                is_available=false;
                in_process=false;
                unselected(process);
                unselected(availble);
                unselected(not_available);
                if (rera_lnr.getVisibility()==View.VISIBLE){
                    rera_lnr.setVisibility(View.GONE);
                    rera_frm.setBackgroundResource(R.drawable.employe_circle_rounded);
                }else {
                    rera_lnr.setVisibility(View.VISIBLE);
                    verification_typee="Rera Id";
                    rera_frm.setBackgroundResource(R.drawable.selected_background_filter);
                }
            }
        });
        _aadhar_frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAdhar=true;
                isRera=false;
                rera_lnr.setVisibility(View.GONE);
                rera_frm.setBackgroundResource(R.drawable.employe_circle_rounded);
                if (adhar_card_lnr.getVisibility()==View.VISIBLE) {
                    is_Residential_click = false;
                    _aadhar_frm.setBackgroundResource(R.drawable.selected_background_filter);
                    adhar_card_lnr.setVisibility(View.GONE);
                } else {
                    is_Residential_click = true;
                    _aadhar_frm.setBackgroundResource(R.drawable.selected_background_filter);
                    verification_typee="Aadhar Card";
                    adhar_card_lnr.setVisibility(View.VISIBLE);
                }
            }
        });
        availble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                process.setBackgroundResource(R.drawable.employe_circle_rounded);
                not_available.setBackgroundResource(R.drawable.employe_circle_rounded);
                in_process=false;
                is_not_available=false;
                if (is_available) {
                    is_Residential_click = false;
                    lnr_availble.setVisibility(View.GONE);
                    availble.setBackgroundResource(R.drawable.selected_background_filter);
                } else {
                    is_available = true;
                    lnr_availble.setVisibility(View.VISIBLE);

                    lnr_process.setVisibility(View.VISIBLE);
                    lnr_date.setVisibility(View.VISIBLE);
                    lnr_verification_link.setVisibility(View.VISIBLE);
                    join_now_serve="Available";
                    availble.setBackgroundResource(R.drawable.selected_background_filter); }
            }
        });
        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // lnr_availble.setVisibility(View.GONE);
                availble.setBackgroundResource(R.drawable.employe_circle_rounded);
                not_available.setBackgroundResource(R.drawable.employe_circle_rounded);
                is_available=false;
                is_not_available=false;
                if (in_process) {
                    in_process = false;
                    process.setBackgroundResource(R.drawable.employe_circle_rounded);
                    lnr_process.setVisibility(View.GONE);
                } else {
                    in_process = true;
                    process.setBackgroundResource(R.drawable.selected_background_filter);
                    join_now_serve="In Process";
                    lnr_availble.setVisibility(View.VISIBLE);
                    lnr_process.setVisibility(View.VISIBLE);
                    lnr_date.setVisibility(View.GONE);
                    lnr_verification_link.setVisibility(View.GONE);
                }
            }
        });
        not_available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRera=false;
                lnr_availble.setVisibility(View.GONE);
                lnr_process.setVisibility(View.GONE);
                process.setBackgroundResource(R.drawable.employe_circle_rounded);
                availble.setBackgroundResource(R.drawable.employe_circle_rounded);


                is_available=false;
                in_process=false;
                if (is_not_available) {
                    is_not_available = false;
                    not_available.setBackgroundResource(R.drawable.employe_circle_rounded);
                    lnr_process.setVisibility(View.GONE);
                } else {
                    is_available = true;
                    not_available.setBackgroundResource(R.drawable.selected_background_filter);
                    join_now_serve="Not Available";
                    lnr_process.setVisibility(View.GONE);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpVerification.this,EditSignUpForm.class);
                startActivity(intent);
            }
        });
        getVerificationDetails();
    }

    public void date1() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv_calendar_text.setText(sdf.format(calendar.getTime()));
    }
    String CLICK_ON = "";
    String FIRST_IMAGE = "OneImage";
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

    Bitmap bitmap1;
    Bitmap bitmap2;
    Bitmap bitmap3;

    Uri filePath1;
    Uri filePath2;
    Uri filePath3;
    int imges = 0;
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
                    img_frondt_adhar.setImageBitmap(bitmap);
                    imges=1;
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    img_back_adhar.setImageBitmap(bitmap);
                    imges=2;
                }
                else if (CLICK_ON.equals(FIRST_IMAGE)) {
                    bitmap1 = bitmap;
                    img_choose_file.setImageBitmap(bitmap);
                    imges=3;
                }
                if (imges==1){
                    isImagesAdded=false;
                }
                if (imges==2){
                    isImagesAdded=true;
                }
                if (imges==3){
                    isImagesAdded=true;
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
                    imges=1;
                    bitmap2 = bitmap;
                    filePath2 = filePath;
                    img_frondt_adhar.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    imges=2;
                    bitmap3 = bitmap;
                    filePath3 = filePath;
                    img_back_adhar.setImageBitmap(bitmap);
                } else if (CLICK_ON.equals(FIRST_IMAGE)) {
                    imges=3;
                    bitmap1 = bitmap;
                    filePath1 = filePath;
                    img_choose_file.setImageBitmap(bitmap);
                }
                if (imges==1){
                    isImagesAdded=false;
                }
                if (imges==2){
                    isImagesAdded=true;
                }
                if (imges==3){
                    isImagesAdded=true;
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



    public void getVerificationDetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_VERIFICATION, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1")) {
                        JSONObject object = jsonObject.getJSONObject("data");
                        edt_verification_link.setText(object.getString("verification_link"));
                        tv_calendar_text.setText(object.getString("validities"));
                        edt_adhar_card.setText(object.getString("aadhar_number"));
                        String aadhar_front=object.getString("aadhar_front");
                        String aadhar_back=object.getString("aadhar_back");
                        String supporting_document=object.getString("supporting_document");
                        if (aadhar_front!=null && !aadhar_front.trim().isEmpty()) {
                            Glide.with(context).load(UrlClass.BASE_URL + object.getString("aadhar_front")).into(img_frondt_adhar);
                        }
                        if (aadhar_back!=null && !aadhar_back.trim().isEmpty()) {
                            Glide.with(context).load(UrlClass.BASE_URL+object.getString("aadhar_back")).into(img_back_adhar);
                        }
                        if (supporting_document!=null && !supporting_document.trim().isEmpty()) {
                            Glide.with(context).load(UrlClass.BASE_URL+object.getString("supporting_document")).into(img_choose_file);
                        }


                        join_now_serve=object.getString("verification");
                        verification_typee=object.getString("verification_type");
                        if (verification_typee !=null) {
                            if (verification_typee.contains("Rera Id")) {
                                selected(rera_frm);
                                unselected(_aadhar_frm);
                                rera_lnr.setVisibility(View.VISIBLE);
                                adhar_card_lnr.setVisibility(View.GONE);
                                if (join_now_serve !=null){
                                    if (join_now_serve.equals("Available")){
                                        selected(availble);
                                        lnr_availble.setVisibility(View.VISIBLE);
                                        lnr_process.setVisibility(View.VISIBLE);
                                    }
                                    else if (join_now_serve.equals("In Process")){
                                        selected(process);
                                        lnr_verification_link.setVisibility(View.GONE);
                                        lnr_date.setVisibility(View.GONE);
                                        lnr_process.setVisibility(View.VISIBLE);
                                        lnr_availble.setVisibility(View.VISIBLE);

                                    }
                                    else if (join_now_serve.equals("Not Available")){
                                        selected(not_available);
                                        lnr_availble.setVisibility(View.GONE);
                                        lnr_process.setVisibility(View.GONE);
                                    }
                                }
                            } else if (verification_typee.contains("Aadhar Card")) {
                                selected(_aadhar_frm);
                                unselected(rera_frm);
                                adhar_card_lnr.setVisibility(View.VISIBLE);
                                rera_lnr.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        TextView textView = (TextView)verification.getChildAt(0);
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
    private void updateVerificationDetails() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        try {
            //   RequestBody subCategory = RequestBody.create(MultipartBody.FORM, UtilityFunction.getCalculatedDate("yyyy-MM-dd",0));

            int compressionRatio = 25; //1 == originalImage, 2 = 50% compression, 4=25% compress
            File file;
            MultipartBody.Part logoPart=null;
            MultipartBody.Part profilePart=null;
            MultipartBody.Part supportingPart=null;
            if (filePath1 != null) {
                file = FileUtil.from(this, filePath1);
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

                    Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath1)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file.getName());
                    Log.e("Type", file.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file);
                }
                supportingPart = MultipartBody.Part.createFormData("supporting_document", file.getName(), videoPart);
            } else if (bitmap1!=null) {
                file = bitmapToFile(bitmap1, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file);
                if (file.getName() == null) {

                } else {
                    supportingPart = MultipartBody.Part.createFormData("supporting_document", file.getName(), videoPart);
                }
            }

            File file1;

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
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file1.getName());
                    Log.e("Type", file1.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                }
                profilePart = MultipartBody.Part.createFormData("aadhar_front", file1.getName(), videoPart);
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
                    profilePart = MultipartBody.Part.createFormData("aadhar_front", file1.getName(), videoPart);
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
                logoPart = MultipartBody.Part.createFormData("aadhar_back", file2.getName(), videoPart);
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
                    logoPart = MultipartBody.Part.createFormData("aadhar_back", file2.getName(), videoPart);
                }
            }
            RequestBody userId = RequestBody.create(MultipartBody.FORM, PrefMananger.GetLoginData(context).getUserId() + "");
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody verification_type = RequestBody.create(MultipartBody.FORM, verification_typee);
            RequestBody verification = RequestBody.create(MultipartBody.FORM, join_now_serve);
            RequestBody verification_link = RequestBody.create(MultipartBody.FORM, edt_verification_link.getText().toString());
            RequestBody aadhar_number = RequestBody.create(MultipartBody.FORM, edt_adhar_card.getText().toString());
            RequestBody validities = RequestBody.create(MultipartBody.FORM, tv_calendar_text.getText().toString());
            //  RequestBody subCategory = RequestBody.create(MultipartBody.FORM, subCategoryId + "");
            HashMap<String, RequestBody> hashMap = new HashMap<>();
            hashMap.put("user_id", userId);
            hashMap.put("verification_type", verification_type);
            hashMap.put("verification", verification);
            hashMap.put("validities", validities);
            hashMap.put("verification_link", verification_link);
            hashMap.put("aadhar_number", aadhar_number);
            hashMap.put("status", status);
            ApiExecutor.getApiService().updateVerificationData(
                    logoPart,profilePart,supportingPart,hashMap
            ).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    try {
                        dialog.dismiss();
                        Intent intent = new Intent(SignUpVerification.this, SignUpPhotos.class);
                        startActivity(intent);
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
    private void setVerification(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        try {
            //   RequestBody subCategory = RequestBody.create(MultipartBody.FORM, UtilityFunction.getCalculatedDate("yyyy-MM-dd",0));

            int compressionRatio = 25; //1 == originalImage, 2 = 50% compression, 4=25% compress
            File file;
            MultipartBody.Part logoPart=null;
            MultipartBody.Part profilePart=null;
            MultipartBody.Part supportingPart=null;
            if (filePath1 != null) {
                file = FileUtil.from(this, filePath1);
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

                    Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath1)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file.getName());
                    Log.e("Type", file.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file);
                }
                supportingPart = MultipartBody.Part.createFormData("supporting_document", file.getName(), videoPart);
            } else if (bitmap1!=null) {
                file = bitmapToFile(bitmap1, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                //String realpath = getRealPathFromURI_API19(AddQoute.this, imageRequests.get(i).uri);
                // file1=new File(realpath);
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file);
                if (file.getName() == null) {

                } else {
                    supportingPart = MultipartBody.Part.createFormData("supporting_document", file.getName(), videoPart);
                }
            }

            File file1;

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
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", file1.getName());
                    Log.e("Type", file1.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), file1);
                }
                profilePart = MultipartBody.Part.createFormData("aadhar_front", file1.getName(), videoPart);
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
                    profilePart = MultipartBody.Part.createFormData("aadhar_front", file1.getName(), videoPart);
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
                logoPart = MultipartBody.Part.createFormData("aadhar_back", file2.getName(), videoPart);
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
                    logoPart = MultipartBody.Part.createFormData("aadhar_back", file2.getName(), videoPart);
                }
            }
            RequestBody userId = RequestBody.create(MultipartBody.FORM, PrefMananger.GetLoginData(context).getUserId() + "");
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody verification_type = RequestBody.create(MultipartBody.FORM, verification_typee);
            RequestBody verification = RequestBody.create(MultipartBody.FORM, join_now_serve);
            RequestBody verification_link = RequestBody.create(MultipartBody.FORM, edt_verification_link.getText().toString());
            RequestBody aadhar_number = RequestBody.create(MultipartBody.FORM, edt_adhar_card.getText().toString());
            RequestBody validities = RequestBody.create(MultipartBody.FORM, tv_calendar_text.getText().toString());
            //  RequestBody subCategory = RequestBody.create(MultipartBody.FORM, subCategoryId + "");
            HashMap<String, RequestBody> hashMap = new HashMap<>();
            hashMap.put("user_id", userId);
            hashMap.put("verification_type", verification_type);
            hashMap.put("verification", verification);
            hashMap.put("validities", validities);
            hashMap.put("verification_link", verification_link);
            hashMap.put("aadhar_number", aadhar_number);
            hashMap.put("status", status);
            ApiExecutor.getApiService().setVerificationData(
                    logoPart,profilePart,supportingPart,hashMap
            ).enqueue(new Callback<OtpResponse>() {
                @Override
                public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                    try {
                        dialog.dismiss();
                        Intent intent = new Intent(SignUpVerification.this, SignUpPhotos.class);
                        startActivity(intent);
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
    private void selected(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background_filter);
    }
    private void unselected(TextView textView) {
        textView.setBackgroundResource(R.drawable.employe_circle_rounded);
    }
}