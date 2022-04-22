package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nestowl.model.ChatListModal;
import com.nestowl.brokerapp.R;

import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {
    Context context;
    ArrayList<ChatListModal> chats;
    String uid;

    public ChattingAdapter(Context context, ArrayList<ChatListModal> chats, String uid) {
        this.context = context;
        this.chats = chats;
        this.uid = uid;
    }

    @NonNull
    @Override
    public ChattingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_chating, viewGroup, false);
        return new ChattingAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ChattingAdapter.ViewHolder viewHolder, int i) {
        ChatListModal chatListModal = chats.get(i);
        if (chatListModal.getUid().equals(uid)){
            viewHolder.reciver.setVisibility(View.GONE);
            viewHolder.sender.setVisibility(View.VISIBLE);
            viewHolder.senderImage.setVisibility(View.GONE);
            viewHolder.senderText.setVisibility(View.VISIBLE);
            viewHolder.senderDocs.setVisibility(View.GONE);
            if (chatListModal.getImage()){
                viewHolder.senderImage.setVisibility(View.VISIBLE);
                viewHolder.senderDocs.setVisibility(View.GONE);
                viewHolder.senderText.setVisibility(View.GONE);
                Glide.with(context).load(chatListModal.getImageUrl()).into(viewHolder.imageSender);
            }
            viewHolder.mainsenderText.setText(chatListModal.getMassage());
            viewHolder.senderImageText.setText(chatListModal.getMassage());
        }else {
            viewHolder.reciver.setVisibility(View.VISIBLE);
            viewHolder.sender.setVisibility(View.GONE);
            viewHolder.reciverImage.setVisibility(View.GONE);
            viewHolder.reciverText.setVisibility(View.VISIBLE);
            viewHolder.reciverDocs.setVisibility(View.GONE);
            if (chatListModal.getImage()){
                viewHolder.reciverImage.setVisibility(View.VISIBLE);
                viewHolder.reciverText.setVisibility(View.GONE);
                viewHolder.reciverDocs.setVisibility(View.GONE);
                Glide.with(context).load(chatListModal.getImageUrl()).into(viewHolder.imageReciver);
            }

            viewHolder.mainreciverText.setText(chatListModal.getMassage());
            viewHolder.reciverImageText.setText(chatListModal.getMassage());


        }


    }
    @Override
    public int getItemCount() {
        return chats.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout reciverText ,reciverDocs, reciverImage, reciver, sender, senderText, senderDocs, senderImage;
        ImageView imageSender, imageReciver, reciverDocsBtn, senderDocsBtn;
        TextView mainsenderText, mainreciverText, senderImageText, reciverImageText, reciverDocsName, reciverDocsSize, senderDocsName, senderDocsSize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reciver = itemView.findViewById(R.id.CHATING_C_RECIVER);
            reciverText = itemView.findViewById(R.id.CHATING_RICIVER_TEXT);
            reciverDocs = itemView.findViewById(R.id.CHATING_RECIVER_DOCUMENT);
            reciverImage = itemView.findViewById(R.id.CHATING_RECIVER_IMAGE);

            mainreciverText = itemView.findViewById(R.id.CHATING_RECIVER_TEXT_MASG);
            reciverImageText = itemView.findViewById(R.id.CHATING_RECIVER_IMAGE_TEXT);
            imageReciver = itemView.findViewById(R.id.CHATIING_RECIVER_IMAGE);
            reciverDocsBtn = itemView.findViewById(R.id.CHATING_RECIVER_DOCS_BTN);
            reciverDocsSize = itemView.findViewById(R.id.CHATTING_RECIVER_DOCS_SIZE);
            reciverDocsName = itemView.findViewById(R.id.CHATING_DOCS_NAME);

            sender = itemView.findViewById(R.id.CHATTING_C_SENDER);
            senderText = itemView.findViewById(R.id.CHATING_SENDER_MSG);
            senderDocs = itemView.findViewById(R.id.CHATING_SENDER_DOCUMENTS);
            senderImage = itemView.findViewById(R.id.CHATING_SENDER_IMAGES);

            mainsenderText = itemView.findViewById(R.id.CHATING_SENDER_TEXT);
            senderImageText = itemView.findViewById(R.id.CHATING_SENDER_IMAGE_TEXT);
            imageSender = itemView.findViewById(R.id.CHATING_SENDER_IMAGE);
            senderDocsBtn =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_BTN);
            senderDocsName =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_NAME);
            senderDocsSize =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_SIZE);


        }
        @Override
        public void onClick(View view) {

        }
    }
}
