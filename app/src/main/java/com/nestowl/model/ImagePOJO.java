package com.nestowl.model;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class ImagePOJO {
    public Bitmap bitmap;
    public Uri imageUri;
    public String id;

    public ImagePOJO() {
    }

    public ImagePOJO(Bitmap bitmap, Uri imageUri, String id) {
        this.bitmap = bitmap;
        this.imageUri = imageUri;
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

