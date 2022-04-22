package com.nestowl.brokerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.User;
import com.nestowl.utils.GetFiles;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    TextView name;
    RatingBar ratingBar;
    ImageView back_img,dp,photoPreview;
    EditText editText;
    FrameLayout addPhoto;
    CheckBox checkBox;
    CardView cardView;
    User user;
    GetFiles getFiles;
    MultipartBody.Part image;
    boolean isImageSelected;
    String PropertyId,ProjectName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        isImageSelected=false;
        PropertyId=getIntent().getStringExtra("pid");
        ProjectName=getIntent().getStringExtra("reviewType");
        back_img=findViewById(R.id.ARTICLES_BACK);
        name=findViewById(R.id.BUYER_REVIEWS_NAME);
        ratingBar=findViewById(R.id.REVIEW_SUMBIT_SELLER_RATING);
        dp=findViewById(R.id.REVIEW_SUMBIT_SELLER_DP);
        editText=findViewById(R.id.REVIEW_SUMBIT_SELLER_INPUT);
        addPhoto=findViewById(R.id.REVIEW_SUMBIT_SELLER_ADDD_PHOTO);
        checkBox=findViewById(R.id.REVIEW_SUMBIT_SELLER_CHECKBOX);
        cardView=findViewById(R.id.REVIEW_SUMBIT_SELLER_SUMBIT);
        photoPreview=findViewById(R.id.REVIEW_SUMBIT_SELLER_ADDD_PHOTO_PREVIEW);

        user=new Gson().fromJson(PrefMananger.getString(this,"user"),User.class);
        getFiles =  new GetFiles(this);

        name.setText(user.getFirst_name()+" "+user.getLast_name());
        Glide.with(this).load(user.getAvatar()).placeholder(R.drawable.default_x_x).into(dp);

        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ratingBar.getRating()<=0){
                    new WarningDio(ReviewActivity.this, "Please select a rating.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {

                        }
                    },false);
                    return;
                }
                if (editText.getText()==null){
                    editText.requestFocus();
                    editText.setError("Write a review.");
                    return;
                }
                if (!isImageSelected){
                    new WarningDio(ReviewActivity.this, "Please select a image.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                addPhoto.performClick();
                            }
                        }
                    },false);
                    return;
                }
                if (!checkBox.isChecked()){
                    new WarningDio(ReviewActivity.this, "Please check the box.", "OK", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                             checkBox.setChecked(true);
                            }
                        }
                    },false);
                    return;
                }
                sumbitReview();
            }
        });

    }

    private void sumbitReview() {
        ProgressDialog pd =  new ProgressDialog(this);
        pd.setMessage("Sumbiting Review...");
        pd.setCancelable(false);
        pd.show();
        HashMap<String, RequestBody>hashMap=new HashMap<>();

        RequestBody userId = RequestBody.create(MultipartBody.FORM, user.getUser_id());
        RequestBody p_id = RequestBody.create(MultipartBody.FORM, PropertyId);
        RequestBody type = RequestBody.create(MultipartBody.FORM, ProjectName);
        RequestBody star_review = RequestBody.create(MultipartBody.FORM, String.valueOf(ratingBar.getRating()));
        RequestBody about_review = RequestBody.create(MultipartBody.FORM, editText.getText().toString());
        RequestBody terms = RequestBody.create(MultipartBody.FORM, "Yes");

        hashMap.put("user_id",userId);
        hashMap.put("p_id",p_id);
        hashMap.put("type",type);
        hashMap.put("star_review",star_review);
        hashMap.put("about_review",about_review);
        hashMap.put("terms",terms);

        ApiExecutor.getApiService().sumbitReview(image,hashMap).enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                pd.dismiss();
                if (response.isSuccessful()){
                    new WarningDio(ReviewActivity.this, "Sumbit successfully.", "Go to home screen", null, new WarningDio.Response() {
                        @Override
                        public void getClicks(int click) {
                            if (click==1){
                                startActivity(new Intent(ReviewActivity.this,HomeScreen.class));
                                finish();
                            }
                        }
                    },false);
                }
            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {
                pd.dismiss();
                new WarningDio(ReviewActivity.this, "Sumbit failed.", "Ok", null, new WarningDio.Response() {
                    @Override
                    public void getClicks(int click) {

                    }
                },false);
            }
        });
    }

    private void SelectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(ReviewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(ReviewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(ReviewActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(ReviewActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 200);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            isImageSelected=true;
            if (requestCode == 1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                photoPreview.setVisibility(View.VISIBLE);
                photoPreview.setImageBitmap(bitmap);
                image = getFiles.getPartBody("project_photo",bitmap);
            }
            if (requestCode == 2) {
                Uri filePath = data.getData();
                photoPreview.setVisibility(View.VISIBLE);
                photoPreview.setImageURI(filePath);
                image =  getFiles.getPartBody("project_photo",filePath);
            }
        }
    }

}
