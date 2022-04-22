package com.nestowl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.nestowl.brokerapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class GetFiles {
    Context context;
    int compressionRatio = 4;

    public GetFiles(Context context) {
        this.context = context;
    }
    public MultipartBody.Part getPartBody(String key,Uri uri){
        File file = null;
        RequestBody requestBody;
        try {
            file = FileUtil.from(context, uri);
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 70, bos);
            Log.e("imageSize", bos.toByteArray().length + " Byte \n" + (bos.toByteArray().length / 1024) + "KB");
            requestBody = RequestBody.create(MediaType.parse(context.getContentResolver().getType(uri)), bos.toByteArray());
            Log.e("Name", file.getName());
            Log.e("Type", file.getAbsolutePath());
        } catch (Exception e) {
            Log.e("GET FILE URI ERROR", e.toString());
            requestBody = RequestBody.create(MediaType.parse(".jpg"), file);
        }
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
        return filePart;
    }
    public MultipartBody.Part getPartBody(String key,Bitmap bitmap){
        File file;
        file = bitmapToFile(bitmap, context.getString(R.string.app_name) + System.currentTimeMillis() + ".jpg");
        try {
            Bitmap bitmaps = BitmapFactory.decodeFile(file.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, new FileOutputStream(file));
        } catch (Throwable t) {
            Log.e("ERROR", "Error compressing file." + t.toString());
            t.printStackTrace();
        }
        RequestBody videoPart = RequestBody.create(MediaType.parse(".jpg"), file);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData(key, file.getName(), videoPart);

        return filePart;
    }
    private File bitmapToFile(Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        File file = null;
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + fileNameToSave);
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();
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
}
