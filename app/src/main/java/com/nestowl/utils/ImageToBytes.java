package com.nestowl.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ImageToBytes {
    private static String value;
    public static String getImageToEncodedString(Context context,Uri uri){
        try {
            InputStream inputStream = null;
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            value = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);
        }catch (Exception e){
            
        }
        return value;
    }
}
