package com.nestowl.brokerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nestowl.CommenDialog.DialogOpenClass;
import com.nestowl.CommenDialog.FloorPojo;
import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.api_service.ApiExecutor;
import com.nestowl.model.OtpResponse;
import com.nestowl.model.ProposalModal;
import com.nestowl.utils.GetFiles;
import com.nestowl.utils.PrefMananger;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueryBrokerSecondImageSection extends AppCompatActivity {
    ImageView back_img;
    RadioButton byChat;
    CardView cardView;
    FrameLayout addImages,captureImages,market,busStop,metro,hospital,policeStation,railways;
    LinearLayout imageShow;
    ArrayList<FloorPojo> floorPojos;
    Context context;
    String Smarket,Sbus,Smetro,Shospital,Spolice,Srailways,TOTAL_CLICK,filterCLik;
    ArrayList<Uri> bitmaps;
    ArrayList<String> codedStrings;
    GetFiles files;
    MultipartBody.Part imgeOne,imageTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_broker_second_image_section);
        context = this;
        TOTAL_CLICK =  getIntent().getStringExtra("clicks");
        int  tmp =Integer.parseInt(TOTAL_CLICK);
        int  total =Integer.parseInt(PrefMananger.getString(this,"clisks"));
        if (total==1){
            if (tmp==1){
                filterCLik="0";
            }
        }
        if (total==2){
            if (tmp==1){
                filterCLik="1";
            }
            if (tmp==2){
                filterCLik="0";
            }
        }
        if (total==3){
            if (tmp==1){
                filterCLik="2";
            }
            if (tmp==2){
                filterCLik="1";
            }
            if (tmp==3){
                filterCLik="0";
            }
        }

        cardView=findViewById(R.id.POST_PHOTO_CONTINEUE);
        files=new GetFiles(this);

        cardView.setOnClickListener(new View.OnClickListener() {
            ProposalModal proposalModal = new ProposalModal();
            @Override
            public void onClick(View v) {
                proposalModal = new Gson().fromJson(PrefMananger.getString(context,PrefMananger.PROPOSAL).toString(),ProposalModal.class);
                boolean temp =true;
                int t = 0;
                for (Uri s : bitmaps){
                    if (temp){
                        imgeOne= files.getPartBody("propertyimage1",s);
                        temp=false;
                    }else {
                        imageTwo= (files.getPartBody("propertyimage2",s));
                        temp=true;
                    }
                }
                ProgressDialog pd =  new ProgressDialog(QueryBrokerSecondImageSection.this);
                pd.setMessage("Uploading Images...");
                pd.setCancelable(false);
                pd.show();
                HashMap<String, RequestBody>hashMap=new HashMap<>();
                RequestBody reqId = RequestBody.create(MultipartBody.FORM, PrefMananger.getString(QueryBrokerSecondImageSection.this,PrefMananger.PROPOSAL_ID));
                RequestBody inventorys = RequestBody.create(MultipartBody.FORM,String.valueOf(Integer.parseInt(TOTAL_CLICK)-1)) ;
                hashMap.put("requirement_id",reqId);
                hashMap.put("stage",inventorys);
                ApiExecutor.getApiService().postProposalReqImges(imgeOne,imageTwo,hashMap).enqueue(new Callback<OtpResponse>() {
                    @Override
                    public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                        pd.dismiss();
                        if (response.isSuccessful()){

                            proposalModal.setMarket(Smarket);
                            proposalModal.setBus_stop(Sbus);
                            proposalModal.setMetro(Smetro);
                            proposalModal.setHospital(Shospital);
                            proposalModal.setPolice_station(Spolice);
                            proposalModal.setRailways_station(Srailways);
                            String json =new Gson().toJson(proposalModal);
                            PrefMananger.saveString(context,PrefMananger.PROPOSAL, json);
                            Log.e("savedobject", "onClick: "+PrefMananger.getString(context,PrefMananger.PROPOSAL) );
                            Intent intent = new Intent(context,QueryBrokerSecondQueryLastScreen.class);
                            intent.putExtra("clicks",TOTAL_CLICK);
                            startActivity(intent);
                            finish();
                        }else {
                            new WarningDio(QueryBrokerSecondImageSection.this, "Image not uploaded try again", "TRY AGAIN", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    cardView.performClick();
                                }
                            },false);
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpResponse> call, Throwable t) {
                        pd.dismiss();
                        new WarningDio(QueryBrokerSecondImageSection.this, "Image not uploaded try again", "TRY AGAIN", null, new WarningDio.Response() {
                            @Override
                            public void getClicks(int click) {
                                cardView.performClick();
                            }
                        },false);
                    }
                });

            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        byChat=findViewById(R.id.PROPOSAL_REQ_IMAGES_POST_BY_CHAT);
        addImages=findViewById(R.id.PROPOSAL_REQ_IMAGES_GALLERY);
        captureImages=findViewById(R.id.PROPOSAL_REQ_IMAGES_TAKE_PICTURE);
        market=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_MARKET);
        busStop=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_BUSSTOP);
        metro=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_METRO);
        hospital=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_HOSPITAL);
        policeStation=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_POLICE_STATION);
        railways=findViewById(R.id.PROPOSAL_REQ_IMAGES_POPUP_RAILWAY_STATION);
        imageShow=findViewById(R.id.PROPOSAL_REQ_IMAGES_SHOW);

        floorPojos =  new ArrayList<>();
        bitmaps=new ArrayList<>();
        codedStrings =  new ArrayList<>();
        addItems("0.5KM");
        addItems("1KM");
        addItems("2KM");
        addItems("3KM");
        addItems("More then 3KM");

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) market.getChildAt(0);
                        t.setText(text);
                        Smarket=text;
                    }
                },floorPojos);
            }
        });
        busStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) busStop.getChildAt(0);
                        t.setText(text);
                        Sbus=text;
                    }
                },floorPojos);
            }
        });
        metro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) metro.getChildAt(0);
                        t.setText(text);
                        Smetro=text;
                    }
                },floorPojos);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) hospital.getChildAt(0);
                        t.setText(text);
                        Shospital=text;
                    }
                },floorPojos);
            }
        });
        policeStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) policeStation.getChildAt(0);
                        t.setText(text);
                        Spolice=text;
                    }
                },floorPojos);
            }
        });
        railways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogOpenClass(context, new DialogOpenClass.OnItemClass() {
                    @Override
                    public void onitemClick(String text) {
                        TextView t =  (TextView) railways.getChildAt(0);
                        t.setText(text);
                        Srailways=text;
                    }
                },floorPojos);
            }
        });

        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Build.VERSION.SDK_INT <19){
//                    Intent intent = new Intent();
//                    intent.setType("*/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intent, 5);
//                } else {
//                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                    intent.addCategory(Intent.CATEGORY_OPENABLE);
//                    intent.setType("*/*");
//                    startActivityForResult(intent, 5);
//                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT );
                startActivityForResult(intent, 5);
            }
        });
        captureImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 4);
                }
            }
        });



    }
    private void addItems(String text){
        FloorPojo floorPojo=new FloorPojo();
        floorPojo.is_selected=false;
        floorPojo.floor_string=text;
        floorPojos.add(floorPojo);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            if (requestCode == 5){
                Uri imge = data.getData();
                if (bitmaps.size() >= 2){
                    Toast.makeText(this,"Only 2 Images can select",Toast.LENGTH_LONG).show();
                    return;
                }
                bitmaps.add(imge);
                printImages(bitmaps);
            }
            if (requestCode == 4){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                if (bitmaps.size() >= 2){
                    Toast.makeText(this,"Only 2 Images can select",Toast.LENGTH_LONG).show();
                    return;
                }
                bitmaps.add(bitmapTouri(photo));
                printImages(bitmaps);

            }
        }
    }
    private Uri bitmapTouri(Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "IMGNESTOWL", null);
        return Uri.parse(path);
    }
    private void printImages(ArrayList<Uri> bitmaps) {
        imageShow.removeAllViews();
        for (Uri uri : bitmaps){
             ImageView imageView =  new ImageView(this);
             imageView.setImageURI(uri);
             WindowManager.LayoutParams layoutParams =  new WindowManager.LayoutParams();
             layoutParams.width = 300;
             layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
//             imageView.setMaxWidth(10);
//             imageView.setMaxHeight(10);
             imageView.setLayoutParams(layoutParams);
             imageView.setScaleType(ImageView.ScaleType.FIT_XY);
             imageView.setPadding(2,0,2,0);
             imageShow.addView(imageView);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 4);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}
