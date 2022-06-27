package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.ImagePOJO;
import com.nestowl.model.OtpResponse;
import com.nestowl.utils.FileUtil;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;

import org.json.JSONArray;
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

public class SignUpPhotos extends AppCompatActivity implements ImageUploadAdapter.ImageItemClick {
    ImageView back_img,profile,logo,cover,hide;
    CardView cardView;
    ScrollView scrollView;
    FrameLayout add_photos_office_photos, profile_add_photos, logo_add_photos, cover_photo_add_photos;
    ImageView office_photo_img, profile_photo_img, logo_photo_img, cover_photo_img,imagetoHidedp,imagetoHidecover,imagetoHidelogo;
    Context context;
    ImageView one_up, two_down, three_up, four_down, five_up, six_down, saven_up, eight_down, nine_up, ten_down,deleteProfile,deleteLogo,deleteCover;
    Activity activity;
    RecyclerView photo_recycler_view;
    ArrayList<ImagePOJO> bitmapList = new ArrayList<>(),bitmapList2=new ArrayList<>();
    ImageUploadAdapter uploadAdapter;
    boolean isUpdating,isofficePhotoAdded,isOfficePhto;
    String FIRST_IMAGE = "FirstImage";
    String SECOND_IMAGE = "SecondImage";
    String THIRDE_IMAGE = "ThirdImage";
    String FOURTH_IMAGE = "FourthImage";
    String CLICK_ON = "";
    ProgressDialog pd;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = activity = this;
        isUpdating=false;
        isofficePhotoAdded=false;
        isOfficePhto=false;
        setContentView(R.layout.activity_sign_up_photos);
        cardView = findViewById(R.id.card_verification);
        scrollView = findViewById(R.id.scroll);
        back_img = findViewById(R.id.EDIT_PROFILE_BACK);
        profile_add_photos = findViewById(R.id.profile_add_photos);
        profile_photo_img = findViewById(R.id.profile_photo_img);
        logo_add_photos = findViewById(R.id.logo_add_photos);
        logo_photo_img = findViewById(R.id.logo_photo_img);
        office_photo_img = findViewById(R.id.office_photo_img);
        cover_photo_add_photos = findViewById(R.id.cover_photo_add_photos);
        cover_photo_img = findViewById(R.id.cover_photo_img);
        add_photos_office_photos = findViewById(R.id.add_photos_office_photos);
        photo_recycler_view = findViewById(R.id.photos_recycler_view);
        hide = findViewById(R.id.PROFILE_PHOTO_IMAGE_HIDE_OFFICE);
        deleteProfile = findViewById(R.id.PROFILE_PHOTO_PROFILE_DELETE);
        deleteLogo = findViewById(R.id.PROFILE_PHOTO_LOGO_DELETE);
        deleteCover = findViewById(R.id.PROFILE_PHOTO_COVER_DELETE);
        imagetoHidedp=findViewById(R.id.imagetoHidedp);
        imagetoHidelogo=findViewById(R.id.imagetoHidelogo);
        imagetoHidecover=findViewById(R.id.imagetoHidecover);
        bitmapList=new ArrayList<>();
        getUpdatePhotos();
        uploadAdapter = new ImageUploadAdapter(this, bitmapList, this);
        photo_recycler_view.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        photo_recycler_view.setAdapter(uploadAdapter);
        add_photos_office_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmapList.size() < 10) {
//                    CLICK_ON = FIRST_IMAGE;
                    SelectImage();
                    isOfficePhto=true;
                } else {
                    Toast.makeText(context, "You can Upload maximum 10 Photos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        profile_add_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLICK_ON = SECOND_IMAGE;
                SelectImage();
                isOfficePhto=false;
            }

        });
        logo_add_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLICK_ON = THIRDE_IMAGE;
                SelectImage();
                isOfficePhto=false;
            }

        });
        cover_photo_add_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLICK_ON = FOURTH_IMAGE;
                SelectImage();
                isOfficePhto=false;
            }

        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUpdating){
                    if (!isofficePhotoAdded){
                        bitmapList = new ArrayList<>();
                    }
                    if (bitmap2 ==null  && (profile_photo.isEmpty() || profile_photo==null)){
                        new WarningDio(context, "Please upload profile image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    profile_add_photos.performClick();
                                }
                            }
                        },false);
                    }
                    else if (bitmap3==null   && (logo_photo.isEmpty() || logo_photo==null)){
                        new WarningDio(context, "Please upload logo image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    logo_add_photos.performClick();
                                }
                            }
                        },false);
                    }
                    else if (bitmap4==null   && (cover_photo.isEmpty() || cover_photo==null)){
                        new WarningDio(context, "Please upload cover image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    cover_photo_add_photos.performClick();
                                }
                            }
                        },false);
                    }else {
                        uploadPhotos();
                    }
                }else {
                    if (bitmap2 ==null  && (profile_photo.isEmpty() || profile_photo==null)){
                        new WarningDio(context, "Please upload profile image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    profile_add_photos.performClick();
                                }
                            }
                        },false);
                    }
                    else if (bitmap3==null   && (logo_photo.isEmpty() || logo_photo==null)){
                        new WarningDio(context, "Please upload logo image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    logo_add_photos.performClick();
                                }
                            }
                        },false);
                    }
                    else if (bitmap4==null   && (cover_photo.isEmpty() || cover_photo==null)){
                        new WarningDio(context, "Please upload cover image", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                if (click==1){
                                    cover_photo_add_photos.performClick();
                                }
                            }
                        },false);
                    }else {
                        uploadPhotos();
                    }
                }
            }
        });
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                cardView.setVisibility(View.GONE);
                if (scrollY > 55) {
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
                Intent intent = new Intent(SignUpPhotos.this, EditSignUpForm.class);
                startActivity(intent);
            }
        });

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
            }
        });
        deleteCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCover();
            }
        });
        deleteLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLogo();
            }
        });
        pd =  new ProgressDialog(this);
        pd.setMessage("Deleting...");
        pd.setCancelable(false);
//        getUpdatePhotos();
    }

    private void officePhotoDelete(String id) {
        Log.e("Delte office ", "officePhotoDelete: "+id );
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_OFFICE_PHOTO_BY_ID, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(SignUpPhotos.this, "Delete Successful.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                getUpdatePhotos();
                            }
                        },false);
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("id", id);
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void deleteLogo() {
        pd.show();
        Glide.with(SignUpPhotos.this).load("nthing").placeholder(R.drawable.default_x_x).into(logo_photo_img);
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_LOGO_PHOTO, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(SignUpPhotos.this, "Delete Successful.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                getUpdatePhotos();
                            }
                        },false);
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void deleteCover() {
        pd.show();
        Glide.with(SignUpPhotos.this).load("nthing").placeholder(R.drawable.default_x_x).into(cover_photo_img);
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_COVER_PHOTO, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(SignUpPhotos.this, "Delete Successful.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                getUpdatePhotos();
                            }
                        },false);
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void deleteProfile() {
        pd.show();
        Glide.with(SignUpPhotos.this).load("nthing").placeholder(R.drawable.default_x_x).into(profile_photo_img);
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.DELETE_PROFILE_PHOTO, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status =  jsonObject.getString("status");
                    if (status.equals("1")){
                        new WarningDio(SignUpPhotos.this, "Delete Successful.", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                getUpdatePhotos();
                            }
                        },false);
                    }
                }catch (Exception e){

                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("user_id", PrefMananger.GetLoginData(context).getUserId() + "");
                return hashMap;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
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
                    if (isOfficePhto){
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3);
                    }else {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);
                    }


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
    Bitmap bitmap4;
    Uri filePath1;
    Uri filePath2;
    Uri filePath3;
    Uri filePath4;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                hide.setVisibility(View.GONE);
                if(data.getClipData() != null) {
                    try {
                        int count = data.getClipData().getItemCount();
                        for(int i = 0; i < count; i++) {
                            Uri imageUri = data.getClipData().getItemAt(i).getUri();
                            ImagePOJO imagePOJO = new ImagePOJO();
                            imagePOJO.setBitmap(null);
                            imagePOJO.setImageUri(imageUri);
                            if (bitmapList.size()>=10){
                                new WarningDio(SignUpPhotos.this, "Max photo selected", "OK", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {

                                    }
                                },false);
                                return;
                            }else {
                                bitmapList.add(imagePOJO);
                                bitmapList2.add(imagePOJO);
                                uploadAdapter.notifyDataSetChanged();
                                isofficePhotoAdded=true;
                            }
                        }
                        Log.e(" photo selected", "onActivityResult: "+count );
                    }catch (Exception e){
                        Log.e("error office photo", "onActivityResult: "+e );
                    }

                }else if(data.getData() != null) {
                    Uri imagePath =data.getData();
                    ImagePOJO imagePOJO = new ImagePOJO();
                    imagePOJO.setBitmap(null);
                    imagePOJO.setImageUri(imagePath);
                    if (bitmapList.size()>=10){
                        new WarningDio(SignUpPhotos.this, "Max photo selected", "OK", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {

                            }
                        },false);
                    }else {
                        bitmapList.add(imagePOJO);
                        bitmapList2.add(imagePOJO);
                        uploadAdapter.notifyDataSetChanged();
                        isofficePhotoAdded=true;
                    }

                    //do something with the image (save it to some directory or whatever you need to do with it here)
                }
            }

            if (requestCode == 1) {
                bitmap = (Bitmap) data.getExtras().get("data");
                Log.e("1258963", bitmap + "");
                File file1 = bitmapToFile(bitmap, getString(R.string.app_name) + "_" + System.currentTimeMillis() + ".jpg");
                filePath = Uri.fromFile(file1);
                if (isOfficePhto) {
                    bitmap1 = bitmap;
                    isofficePhotoAdded=true;
/*
                    office_photo_img.setImageBitmap(bitmap);
*/
                    ImagePOJO imagePOJO = new ImagePOJO();
                    imagePOJO.setBitmap(bitmap);
                    imagePOJO.setImageUri(filePath);
                    bitmapList.add(imagePOJO);
                    bitmapList2.add(imagePOJO);
                    uploadAdapter.notifyDataSetChanged();
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    Glide.with(SignUpPhotos.this).load(bitmap).placeholder(R.drawable.default_x_x).into(profile_photo_img);
//                    profile_photo_img.setImageBitmap(bitmap);
                    Log.e("DP", "onActivityResult: setting dp" );
                    imagetoHidedp.setVisibility(View.GONE);
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
//                    logo_photo_img.setImageBitmap(bitmap);
                    Glide.with(SignUpPhotos.this).load(bitmap).placeholder(R.drawable.default_x_x).into(logo_photo_img);
                    imagetoHidelogo.setVisibility(View.GONE);
                } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                    bitmap4 = bitmap;
//                    cover_photo_img.setImageBitmap(bitmap);
                    Glide.with(SignUpPhotos.this).load(bitmap).placeholder(R.drawable.default_x_x).into(cover_photo_img);
                    imagetoHidecover.setVisibility(View.GONE);
                }
            } else if (requestCode == 2) {
                filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (CLICK_ON.equals(FIRST_IMAGE)) {
                    isofficePhotoAdded=true;
                    ImagePOJO sdata = new ImagePOJO();
                    sdata.setBitmap(bitmap);
                    sdata.setImageUri(filePath);
                    bitmapList.add(sdata);
                    bitmapList2.add(sdata);
                    photo_recycler_view.setAdapter(uploadAdapter);
                    uploadAdapter.notifyDataSetChanged();
                    Log.e("OFFICE ADDED", "onActivityResult: "+filePath );
                } else if (CLICK_ON.equals(SECOND_IMAGE)) {
                    bitmap2 = bitmap;
                    filePath2 = filePath;
//                    profile_photo_img.setImageBitmap(bitmap);
                    Glide.with(SignUpPhotos.this).load(filePath).placeholder(R.drawable.default_x_x).into(profile_photo_img);
                    imagetoHidedp.setVisibility(View.GONE);
                    Log.e("DP", "onActivityResult: setting dp" );
                } else if (CLICK_ON.equals(THIRDE_IMAGE)) {
                    bitmap3 = bitmap;
                    filePath3 = filePath;
//                    logo_photo_img.setImageBitmap(bitmap);
                    Glide.with(SignUpPhotos.this).load(filePath).placeholder(R.drawable.default_x_x).into(logo_photo_img);
                    imagetoHidelogo.setVisibility(View.GONE);
                } else if (CLICK_ON.equals(FOURTH_IMAGE)) {
                    bitmap4 = bitmap;
                    filePath4 = filePath;
//                    cover_photo_img.setImageBitmap(bitmap);
                    Glide.with(SignUpPhotos.this).load(filePath).placeholder(R.drawable.default_x_x).into(cover_photo_img);
                    imagetoHidecover.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void removeImage(int position,String id) {
        bitmapList.remove(position);
        bitmapList2=new ArrayList<>();
        officePhotoDelete(id);
        uploadAdapter.notifyDataSetChanged();

    }
    String  profile_photo ="";
    String  logo_photo ="";
    String  cover_photo ="";
    public void getUpdatePhotos() {
        bitmapList=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UrlClass.GET_PHOTO_UPLOADS, new com.android.volley.Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("dfadfsa", response);
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("1") && jsonObject.has("data")) {
                        isUpdating=true;
                        TextView textView =  (TextView) cardView.getChildAt(0);
                        textView.setText("Update");
                        JSONObject object = jsonObject.getJSONObject("data");
                        profile_photo=object.getString("profile_photo");
                        logo_photo=object.getString("logo");
                        cover_photo=object.getString("cover");
                        JSONArray officePhoto = jsonObject.getJSONArray("Office_Photo");
                        ArrayList<String> office  = new ArrayList<>();
//                        bitmapList=new ArrayList<>();

                        for (int i=0;i<officePhoto.length();i++){
                            JSONObject jsonObject1 = officePhoto.getJSONObject(i);
                            office.add(jsonObject1.getString("filename"));
                            ImagePOJO imagePOJO =  new ImagePOJO();
                            imagePOJO.imageUri = Uri.parse(UrlClass.BASE_URL2+jsonObject1.getString("filename"));
                            imagePOJO.setId(jsonObject1.getString("id"));
                            Log.e("IDIMAGES", "onResponse: "+imagePOJO.getId() );
                            bitmapList.add(imagePOJO);
                        }
                        uploadAdapter.notifyDataSetChanged();

//                        filePath2=Uri.parse(BASE_URL+profile_photo);
//                        filePath4=Uri.parse(BASE_URL+cover_photo);
//                        filePath3=Uri.parse(BASE_URL+logo_photo);

                        bitmap2 =null;
                        bitmap3 =null;
                        bitmap4 =null;

//                        OfficePhotoViewProfile adpter = new OfficePhotoViewProfile(SignUpPhotos.this,office);
//                        photo_recycler_view.setAdapter(adpter);

                        Glide.with(context).load(UrlClass.BASE_URL+object.getString("logo")).into(logo_photo_img);
                        Glide.with(context).load(UrlClass.BASE_URL+object.getString("cover")).into(cover_photo_img);
                        Glide.with(context).load(UrlClass.BASE_URL+object.getString("profile_photo")).into(profile_photo_img);

                        imagetoHidedp.setVisibility(View.GONE);
                        imagetoHidelogo.setVisibility(View.GONE);
                        imagetoHidecover.setVisibility(View.GONE);
//                        Glide.with(context).load(UrlClass.BASE_URL+object.getString("office_photo")).into(office_photo_img);
                    } else {
                        isUpdating=false;
                        Toast.makeText(context, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error", "onResponse: "+e );
                    isUpdating=false;
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("fasdfafsd", error.toString());
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                isUpdating=false;
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
    private void uploadPhotos() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();
        MultipartBody.Part[] parts = new MultipartBody.Part[bitmapList2.size()];
        try {
            if (isofficePhotoAdded){
//                MultipartBody.Part[] parts = new MultipartBody.Part[bitmapList.size()];
                for (int i = 0; i < bitmapList2.size(); i++) {
                    File officePhoto = null;
                    int compressionRatio = 4;
                    if (bitmapList2.get(i).imageUri != null) {
                        officePhoto = FileUtil.from(this, bitmapList2.get(i).imageUri);
                        RequestBody videoPart = null;
                        try {
                            Bitmap bmp = BitmapFactory.decodeFile(officePhoto.getAbsolutePath());
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                            Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                            videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(bitmapList2.get(i).imageUri)), bos.toByteArray());
                            Log.e("Name", officePhoto.getName());
                            Log.e("Type", officePhoto.getAbsolutePath());
                        } catch (Exception e) {
                            e.printStackTrace();
                            videoPart = RequestBody.create(MediaType.parse(".jpeg"), officePhoto);
                        }
                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("office_photo[]", officePhoto.getName(), videoPart);
                        parts[i] = filePart;

                    }
                    if (bitmapList2.get(i).bitmap!=null){
                        officePhoto = bitmapToFile(bitmapList2.get(i).bitmap, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                        try {
                            Bitmap bitmap = BitmapFactory.decodeFile(officePhoto.getPath());
                            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(officePhoto));
                        } catch (Throwable t) {
                            Log.e("ERROR", "Error compressing file." + t.toString());
                            t.printStackTrace();
                        }
                        RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), officePhoto);
                        if (officePhoto.getName() == null) {

                        } else {
                            MultipartBody.Part filePart = MultipartBody.Part.createFormData("office_photo[]", officePhoto.getName(), videoPart);
                            parts[i] = filePart;
                        }
                    }

                }
            }
            int compressionRatio = 4; //1 == originalImage, 2 = 50% compression, 4=25% compress
            File logo;
            MultipartBody.Part logoPart=null;
            if (filePath3 != null) {
                logo = FileUtil.from(this, filePath3);
                RequestBody videoPart = null;
                try {
                    Bitmap bmp = BitmapFactory.decodeFile(logo.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath2)), bos.toByteArray());
                    Log.e("Name", logo.getName());
                    Log.e("Type", logo.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), logo);
                }
                logoPart = MultipartBody.Part.createFormData("logo", logo.getName(), videoPart);


            }
            if (bitmap3!=null){
                logo = bitmapToFile(bitmap3, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(logo.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(logo));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), logo);
                if (logo.getName() == null) {

                } else {
                    logoPart = MultipartBody.Part.createFormData("logo", logo.getName(), videoPart);

                }
            }

            File Cover;
            MultipartBody.Part coverPart=null;
            if (filePath4 != null) {
                Cover = FileUtil.from(this, filePath4);
                RequestBody videoPart = null;
                try {

                    Bitmap bmp = BitmapFactory.decodeFile(Cover.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath4)), bos.toByteArray());
                    //  RequestBody.create(MEDIA_TYPE_PNG, bos.toByteArray());

                    Log.e("Name", Cover.getName());
                    Log.e("Type", Cover.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), Cover);
                }

                coverPart = MultipartBody.Part.createFormData("cover", Cover.getName(), videoPart);


            }
            if (bitmap4!=null){
                Cover = bitmapToFile(bitmap4, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(Cover.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(Cover));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), Cover);
                if (Cover.getName() == null) {

                } else {
                    coverPart = MultipartBody.Part.createFormData("cover", Cover.getName(), videoPart);

                }
            }

            File profilePhoto;
            MultipartBody.Part profile_photoPart=null;
            if (filePath2 != null) {
                profilePhoto = FileUtil.from(this, filePath2);
                RequestBody videoPart = null;
                try {

                    Bitmap bmp = BitmapFactory.decodeFile(profilePhoto.getAbsolutePath());
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
                    Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
                    videoPart = RequestBody.create(MediaType.parse(getContentResolver().getType(filePath4)), bos.toByteArray());
                    Log.e("Name", profilePhoto.getName());
                    Log.e("Type", profilePhoto.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    videoPart = RequestBody.create(MediaType.parse(".jpg"), profilePhoto);
                }
                profile_photoPart = MultipartBody.Part.createFormData("profile_photo", profilePhoto.getName(), videoPart);


            }
            if (bitmap2!=null){
                profilePhoto = bitmapToFile(bitmap2, getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(profilePhoto.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(profilePhoto));
                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }

                RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), profilePhoto);
                if (profilePhoto.getName() == null) {

                } else {
                    profile_photoPart = MultipartBody.Part.createFormData("profile_photo", profilePhoto.getName(), videoPart);

                }
            }


            RequestBody userId = RequestBody.create(MultipartBody.FORM, String.valueOf(PrefMananger.GetLoginData(context).getUserId()));
            RequestBody status = RequestBody.create(MultipartBody.FORM, "1");
            HashMap<String, RequestBody> map = new HashMap<>();
            map.put("user_id", userId);
            map.put("status", status);
            Log.e("error", "uploadPhotos: "+parts );
            if (isUpdating){
                ApiExecutor.getApiService().updatePhotos(
                        profile_photoPart,coverPart,logoPart,parts,map
                ).enqueue(new Callback<OtpResponse>() {
                    @Override
                    public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                        try {
                            dialog.dismiss();
                            Log.e("error", "onResponse: "+response );
                            Intent intent = new Intent(SignUpPhotos.this, SignUpLastScreenCongratuilation.class);
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
            }else {
                ApiExecutor.getApiService().uploadPhotos(
                        profile_photoPart,coverPart,logoPart,parts,map
                ).enqueue(new Callback<OtpResponse>() {
                    @Override
                    public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                        try {
                            dialog.dismiss();
                            Log.e("error", "onResponse: "+response );
                            Intent intent = new Intent(SignUpPhotos.this, SignUpLastScreenCongratuilation.class);
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("errorWhileuploading", "uploadPhotos: "+e );
            if (dialog != null) {
                dialog.dismiss();
            }
        }

//        dialog.setCanceledOnTouchOutside(true);
    }

}