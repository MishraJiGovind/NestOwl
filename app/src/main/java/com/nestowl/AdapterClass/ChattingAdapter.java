package com.nestowl.AdapterClass;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
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

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {
    Context context;
    ArrayList<ChatListModal> chats;
    String uid;
    DownloadManager manager;


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

        viewHolder.senderTime.setVisibility(View.GONE);
        viewHolder.reciverTime.setVisibility(View.GONE);
        viewHolder.reciverSeen.setVisibility(View.GONE);
        viewHolder.senderSeen.setVisibility(View.GONE);



        if (!chatListModal.getUid().equals(uid)){
            viewHolder.reciver.setVisibility(View.GONE);
            viewHolder.sender.setVisibility(View.VISIBLE);
            viewHolder.senderImage.setVisibility(View.GONE);
            viewHolder.senderText.setVisibility(View.VISIBLE);
            viewHolder.senderDocs.setVisibility(View.GONE);
            viewHolder.senderTime.setVisibility(View.VISIBLE);
            if (chatListModal.getSeen().equals("1")){
                viewHolder.senderSeen.setVisibility(View.VISIBLE);
            }
            viewHolder.senderTimeTxt.setText(chatListModal.getTime());

            if (chatListModal.getReply()){
                viewHolder.senderReplyBox.setVisibility(View.VISIBLE);
                if (chatListModal.getReplayType().equals("0")){
                    viewHolder.senderReplyTxt.setText(chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("1")){
                    viewHolder.senderReplyTxt.setText("Image: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("2")){
                    viewHolder.senderReplyTxt.setText("PDF: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("3")){
                    viewHolder.senderReplyTxt.setText("DOCS: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("4")){
                    viewHolder.senderReplyTxt.setText("AUDIO: "+chatListModal.getFilename());
                }
                if (chatListModal.getReplayType().equals("5")){
                    viewHolder.senderReplyTxt.setText("VIDEO: "+chatListModal.getReplayMassage());
                }
            }

            if (chatListModal.getType().equals("1")){
                viewHolder.senderImage.setVisibility(View.VISIBLE);
                viewHolder.senderDocs.setVisibility(View.GONE);
                viewHolder.senderText.setVisibility(View.GONE);
                Glide.with(context).load(chatListModal.getMassage()).into(viewHolder.imageSender);
                viewHolder.senderImageText.setVisibility(View.GONE);
            }
            if (chatListModal.getType().equals("2")){
                viewHolder.senderImage.setVisibility(View.GONE);
                viewHolder.senderDocs.setVisibility(View.VISIBLE);
                viewHolder.senderText.setVisibility(View.GONE);
                viewHolder.senderDocsType.setImageResource(R.drawable.document);
                viewHolder.senderDocsName.setText(chatListModal.getFilename());
//                viewHolder.senderDocsSize.setText(getSize(chatListModal.getMassage()));
                viewHolder.senderDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(chatListModal.getMassage());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        manager.enqueue(request);
                    }
                });

            }
            if (chatListModal.getType().equals("3")){
                viewHolder.senderImage.setVisibility(View.GONE);
                viewHolder.senderDocs.setVisibility(View.VISIBLE);
                viewHolder.senderText.setVisibility(View.GONE);
                viewHolder.senderDocsType.setImageResource(R.drawable.word);
                viewHolder.senderDocsName.setText(chatListModal.getFilename());
                viewHolder.senderDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(chatListModal.getMassage());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        manager.enqueue(request);
                    }
                });
            }
            if (chatListModal.getType().equals("4")){
                viewHolder.senderImage.setVisibility(View.GONE);
                viewHolder.senderDocs.setVisibility(View.VISIBLE);
                viewHolder.senderText.setVisibility(View.GONE);

                viewHolder.mediaPlayer =  new MediaPlayer();
                viewHolder.mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                    }
                });
                viewHolder.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
                viewHolder.senderDocsBtn.setImageResource(R.drawable.play_button);
                viewHolder.senderDocsType.setImageResource(R.drawable.sound_bars);
                viewHolder.senderDocsName.setText(chatListModal.getFilename());
                try {
                    viewHolder.mediaPlayer.setDataSource(chatListModal.getMassage());
                    viewHolder.mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewHolder.senderDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!viewHolder.mediaPlayer.isPlaying()){
                            viewHolder.mediaPlayer.start();
                            viewHolder.senderDocsBtn.setImageResource(R.drawable.pause);
                        }else {
                            viewHolder.mediaPlayer.pause();
                            viewHolder.senderDocsBtn.setImageResource(R.drawable.play_button);
                        }

                    }
                });

            }
            viewHolder.mainsenderText.setText(chatListModal.getMassage());
//            viewHolder.senderImageText.setText(chatListModal.getMassage());
        }else {
            viewHolder.reciver.setVisibility(View.VISIBLE);
            viewHolder.sender.setVisibility(View.GONE);
            viewHolder.reciverImage.setVisibility(View.GONE);
            viewHolder.reciverText.setVisibility(View.VISIBLE);
            viewHolder.reciverDocs.setVisibility(View.GONE);
            viewHolder.reciverTime.setVisibility(View.VISIBLE);
            viewHolder.reciverTimeTxt.setText(chatListModal.getTime());

            if (chatListModal.getReply()){
                viewHolder.reciverReplybox.setVisibility(View.VISIBLE);
                if (chatListModal.getReplayType().equals("0")){
                    viewHolder.reciverReplyTxt.setText(chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("1")){
                    viewHolder.reciverReplyTxt.setText("Image: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("2")){
                    viewHolder.reciverReplyTxt.setText("PDF: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("3")){
                    viewHolder.reciverReplyTxt.setText("DOCS: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("4")){
                    viewHolder.reciverReplyTxt.setText("AUDIO: "+chatListModal.getReplayMassage());
                }
                if (chatListModal.getReplayType().equals("5")){
                    viewHolder.reciverReplyTxt.setText("VIDEO: "+chatListModal.getReplayMassage());
                }
            }

            if (chatListModal.getType().equals("1")){
                viewHolder.reciverImage.setVisibility(View.VISIBLE);
                viewHolder.reciverText.setVisibility(View.GONE);
                viewHolder.reciverDocs.setVisibility(View.GONE);
                Glide.with(context).load(chatListModal.getMassage()).into(viewHolder.imageReciver);
                viewHolder.reciverImageText.setVisibility(View.GONE);
            }
            if (chatListModal.getType().equals("2")){
                viewHolder.reciverDocsName.setText(chatListModal.getFilename());
                viewHolder.reciverImage.setVisibility(View.GONE);
                viewHolder.reciverDocs.setVisibility(View.VISIBLE);
                viewHolder.reciverText.setVisibility(View.GONE);
                viewHolder.reciverDocsType.setImageResource(R.drawable.document);
                viewHolder.reciverDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(chatListModal.getMassage());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        manager.enqueue(request);
                    }
                });
            }
            if (chatListModal.getType().equals("3")){
                viewHolder.reciverDocsName.setText(chatListModal.getFilename());
                viewHolder.reciverImage.setVisibility(View.GONE);
                viewHolder.reciverDocs.setVisibility(View.VISIBLE);
                viewHolder.reciverText.setVisibility(View.GONE);
                viewHolder.reciverDocsType.setImageResource(R.drawable.word);
                viewHolder.reciverDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(chatListModal.getMassage());
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        manager.enqueue(request);
                    }
                });
            }
            if (chatListModal.getType().equals("4")){
                viewHolder.reciverImage.setVisibility(View.GONE);
                viewHolder.reciverDocs.setVisibility(View.VISIBLE);
                viewHolder.reciverText.setVisibility(View.GONE);
                viewHolder.reciverDocsName.setText(chatListModal.getFilename());

                viewHolder.mediaPlayer =  new MediaPlayer();
                viewHolder.mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                    }
                });
                viewHolder.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                    }
                });
                viewHolder.reciverDocsBtn.setImageResource(R.drawable.play_button);
                viewHolder.reciverDocsType.setImageResource(R.drawable.sound_bars);
                try {
                    viewHolder.mediaPlayer.setDataSource(chatListModal.getMassage());
                    viewHolder.mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                viewHolder.reciverDocsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!viewHolder.mediaPlayer.isPlaying()){
                            viewHolder.mediaPlayer.start();
                            viewHolder.reciverDocsBtn.setImageResource(R.drawable.pause);
                        }else {
                            viewHolder.mediaPlayer.pause();
                            viewHolder.reciverDocsBtn.setImageResource(R.drawable.play_button);
                        }

                    }
                });
            }
            viewHolder.mainreciverText.setText(chatListModal.getMassage());
//            viewHolder.reciverImageText.setText(chatListModal.getMassage());


        }
    }



    @Override
    public int getItemCount() {
        return chats.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MediaPlayer mediaPlayer;
        LinearLayout reciverText ,reciverDocs, reciverImage, reciver, sender, senderText, senderDocs, senderImage,senderTime,reciverTime,senderReplyBox,reciverReplybox;
        ImageView imageSender, imageReciver, reciverDocsBtn,reciverDocsType, senderDocsBtn,senderDocsType;
        TextView mainsenderText, mainreciverText, senderImageText, reciverImageText, reciverDocsName, reciverDocsSize, senderDocsName, senderDocsSize,senderTimeTxt,
                senderSeen,reciverTimeTxt,reciverSeen,senderReplyTxt,reciverReplyTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reciver = itemView.findViewById(R.id.CHATING_C_RECIVER);
            reciverText = itemView.findViewById(R.id.CHATING_RICIVER_TEXT);
            reciverDocs = itemView.findViewById(R.id.CHATING_RECIVER_DOCUMENT);
            reciverImage = itemView.findViewById(R.id.CHATING_RECIVER_IMAGE);
            reciverTime = itemView.findViewById(R.id.CHATTING_TIME_RECIVER);
            reciverSeen = itemView.findViewById(R.id.CHATTING_TIME_RECIVER_SEEN);
            reciverTimeTxt = itemView.findViewById(R.id.CHATTING_TIME_RECIVER_TIME);

            mainreciverText = itemView.findViewById(R.id.CHATING_RECIVER_TEXT_MASG);
            reciverImageText = itemView.findViewById(R.id.CHATING_RECIVER_IMAGE_TEXT);
            imageReciver = itemView.findViewById(R.id.CHATIING_RECIVER_IMAGE);
            reciverDocsBtn = itemView.findViewById(R.id.CHATING_RECIVER_DOCS_BTN);
            reciverDocsSize = itemView.findViewById(R.id.CHATTING_RECIVER_DOCS_SIZE);
            reciverDocsName = itemView.findViewById(R.id.CHATING_DOCS_NAME);
            reciverDocsType = itemView.findViewById(R.id.CHATTING_RECIVER_DOCS_TYPE_IMAGE);
            reciverReplybox = itemView.findViewById(R.id.CHATING_REPLAY_RECIVER_VIEW);
            reciverReplyTxt = itemView.findViewById(R.id.CHATING_REPLAY_RECIVER_TXT);

            sender = itemView.findViewById(R.id.CHATTING_C_SENDER);
            senderText = itemView.findViewById(R.id.CHATING_SENDER_MSG);
            senderDocs = itemView.findViewById(R.id.CHATING_SENDER_DOCUMENTS);
            senderImage = itemView.findViewById(R.id.CHATING_SENDER_IMAGES);
            senderTime = itemView.findViewById(R.id.CHATTING_TIME_SENDER);
            senderSeen = itemView.findViewById(R.id.CHATTING_TIME_SENDER_SEEN);
            senderTimeTxt = itemView.findViewById(R.id.CHATTING_TIME_SENDER_TIME);

            mainsenderText = itemView.findViewById(R.id.CHATING_SENDER_TEXT);
            senderImageText = itemView.findViewById(R.id.CHATING_SENDER_IMAGE_TEXT);
            imageSender = itemView.findViewById(R.id.CHATING_SENDER_IMAGE);
            senderDocsBtn =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_BTN);
            senderDocsName =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_NAME);
            senderDocsSize =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_SIZE);
            senderDocsType =  itemView.findViewById(R.id.CHATING_SENDER_DOCS_TYPE_IMAGE);
            senderReplyBox =  itemView.findViewById(R.id.CHATING_REPLAY_SENDER_VIEW);
            senderReplyTxt =  itemView.findViewById(R.id.CHATING_REPLAY_SENDER_TXT);



        }
        @Override
        public void onClick(View view) {

        }
    }
}
