package com.nestowl.AdapterClass;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.ImagePOJO;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class ImageUploadAdapter extends RecyclerView.Adapter<ImageUploadAdapter.ImageUploadHolder> {
    Activity activity;
    ArrayList<ImagePOJO> imageList;
    ImageItemClick itemClick;


    public ImageUploadAdapter(Activity activity, ArrayList<ImagePOJO> bitmapList, ImageItemClick imageItemClick) {
        this.activity = activity;
        this.imageList = bitmapList;
        this.itemClick=imageItemClick;
    }
    public interface ImageItemClick
    {
        void removeImage(int position,String id);
    }
    @NonNull
    @Override
    public ImageUploadHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(activity).inflate(R.layout.inflate_image_upload,viewGroup,false);
        return new ImageUploadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageUploadHolder holder, final int i) {
        ImagePOJO info =  imageList.get(i);
        try {
//            holder.iv_image.setImageBitmap(imageList.get(i).imageUri);
            if (imageList.get(i).imageUri==null){
                Glide.with(activity).load(info.getBitmap()).placeholder(R.drawable.default_x_x).into(holder.iv_image);
            }else {
                Glide.with(activity).load(info.getImageUri()).placeholder(R.drawable.default_x_x).into(holder.iv_image);
            }
            Log.e("URI", "onBindViewHolder: "+info.getImageUri() );
            holder.iv_remove_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.removeImage(i,info.getId());
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("URI ERROR", "onBindViewHolder: "+e );
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageUploadHolder extends RecyclerView.ViewHolder {
     ImageView iv_remove_image;
     ImageView iv_image;
        public ImageUploadHolder(@NonNull View itemView) {
            super(itemView);
            iv_remove_image=itemView.findViewById(R.id.iv_remove_image);
            iv_image=itemView.findViewById(R.id.iv_uploaded_image);

        }
    }
}
