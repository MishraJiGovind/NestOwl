package com.nestowl.brokerapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;


import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.io.File;


public class BuyPlan extends AppCompatActivity {
    Uri video;
    ConstraintLayout layout;
    VideoView view;
    RangeSeekBar rangeSeekBar;


    int duration;
    File file;
    String originalPath;

    boolean ispicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_plan);
        layout=findViewById(R.id.onpickup);
        view=findViewById(R.id.videoView);
        rangeSeekBar=findViewById(R.id.Seekbar);

        findViewById(R.id.pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ispicked){
                    trimVideo(rangeSeekBar.getSelectedMinValue().intValue()*1000,rangeSeekBar.getSelectedMaxValue().intValue()*1000,"triming");
                    ispicked=false;
                }else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("video/*");
                    startActivityForResult(intent,1);
                    ispicked=true;
                }

            }
        });
        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration=view.getDuration()/1000;
                rangeSeekBar.setRangeValues(0,duration);
                rangeSeekBar.setSelectedMaxValue(duration);
                rangeSeekBar.setSelectedMinValue(0);
                rangeSeekBar.setEnabled(true);

                rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                    @Override
                    public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                        view.seekTo((int) minValue*1000);
                    }
                });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (view.getCurrentPosition()>=rangeSeekBar.getSelectedMaxValue().intValue()*1000){
                            view.seekTo(rangeSeekBar.getSelectedMaxValue().intValue()*1000);
                        }
                    }
                },1000);
            }
        });


    }

    private void trimVideo(int min, int max, String name) {
        File folder = new File(Environment.getExternalStorageDirectory()+"/DCIM");
        if (!folder.exists()){
            folder.mkdir();
        }
        String prefix = ".mp4";
        file = new File(folder,name+prefix);
        originalPath= getOrigianalPath(getApplicationContext(),video);





    }

    private String getOrigianalPath(Context context, Uri video) {
        String data =  null;
        Cursor cursor = null;

        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor=context.getContentResolver().query(video,proj,null,null,null);
            int columindex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            data = cursor.getString(columindex);
            return data;
        }catch (Exception e){
            return "";
        }finally {
            cursor.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            if (requestCode==1){
                video=data.getData();
                view.setVideoURI(video);
                view.start();
                layout.setVisibility(View.VISIBLE);
                Log.e("picup", "onActivityResult: "+video );
            }
        }
    }
}