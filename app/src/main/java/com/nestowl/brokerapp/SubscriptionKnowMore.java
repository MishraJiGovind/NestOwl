package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nestowl.model.SubscriptionApiModal;
import com.nestowl.payment.paytmGateway;
import com.nestowl.utils.PrefMananger;
import com.nestowl.utils.UrlClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SubscriptionKnowMore extends AppCompatActivity {
    TextView time,price,purchase,offer,validity,listingMonths,varification,responseRate,sellerLeads,buyerLeads,earnmore,indianHero,nestPros,chatFeatures,images,freeze,tieUp,view23,title;
    ImageView validityImg,listingMonthsImg,varificationImg,responseRateImg,sellerLeadsImg,buyerLeadsImg,earnmoreImg,indianHeroImg,nestProsImg,chatFeaturesImg,imagesImg,freezeImg,tieUpImg,back;
    LinearLayout view21,view22;
    ArrayList<SubscriptionApiModal> data;
    String type,view,name,id;
    CardView cardView;
    int screen,freeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_know_more);
        freeCount=Integer.parseInt(PrefMananger.getString(this,"remain"));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
        type=getIntent().getStringExtra("type");
        view=getIntent().getStringExtra("view");
        name=getIntent().getStringExtra("name");
        if (PrefMananger.getString(this,"subscription")!=null){
            try {
                String json =  PrefMananger.getString(this,"subscription");
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++){
                    SubscriptionApiModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                    data.add(modal);
                }
                handlerViewData();
            }catch (Exception e){

            }
        }

        view21=findViewById(R.id.SUBSCRIPTION_VIEW_21);
        view22=findViewById(R.id.SUBSCRIPTION_VIEW_22);
        view23=findViewById(R.id.SUBSCRIPTION_VIEW_23);
        title=findViewById(R.id.SUBSCRIPTION_TITLE);
        back=findViewById(R.id.SUBSCRIPTION_VIEW_BACK);
        cardView=findViewById(R.id.SUBSCRIPTION_BUY_PLANS);
        if (view.equals("1")){
        title.setText(name+" Months");
        }else {
        title.setText(name);
            view21.setVisibility(View.GONE);
            view22.setVisibility(View.GONE);
            view23.setVisibility(View.GONE);
        }
        PrefMananger.saveString(this,"isfree","no");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("Free")){
                    if (freeCount>0){
                        startActivity(new Intent(SubscriptionKnowMore.this,PlanBasicActivity.class));
                        PrefMananger.saveString(SubscriptionKnowMore.this,"isfree",null);
                    }
                }else {
                    new paytmGateway(SubscriptionKnowMore.this,id,screen);
                }
            }
        });

        time=findViewById(R.id.SUBSCRIPTION_TIME);
        price=findViewById(R.id.SUBSCRIPTION_PRICE);
        purchase=findViewById(R.id.SUBSCRIPTION_PURCHASE);
        offer=findViewById(R.id.SUBSCRIPTION_OFFER);
        validity=findViewById(R.id.SUBSCRIPTION_VALIDITY);
        listingMonths=findViewById(R.id.SUBSCRIPTION_FREE_LISTINGS);
        varification=findViewById(R.id.SUBSCRIPTION_VARIFICATION);
        responseRate=findViewById(R.id.SUBSCRIPTION_RESPONSE);
        sellerLeads=findViewById(R.id.SUBSCRIPTION_SELLER_LEADS);
        buyerLeads=findViewById(R.id.SUBSCRIPTION_BUYER_LEADS);
        earnmore=findViewById(R.id.SUBSCRIPTION_EARN_MORE);
        indianHero=findViewById(R.id.SUBSCRIPTION_INDIAN_HERO);
        nestPros=findViewById(R.id.SUBSCRIPTION_NEST_PRO);
        chatFeatures=findViewById(R.id.SUBSCRIPTION_CHAT);
        images=findViewById(R.id.SUBSCRIPTION_IMAGES);
        freeze=findViewById(R.id.SUBSCRIPTION_FREEZE);
        tieUp=findViewById(R.id.SUBSCRIPTION_TI_UP);

        validityImg=findViewById(R.id.SUBSCRIPTION_VALIDITY_IMAGE);
        listingMonthsImg=findViewById(R.id.SUBSCRIPTION_LISTING_IMAGE);
        varificationImg=findViewById(R.id.SUBSCRIPTION_IMAGE_VARIFICATION);
        responseRateImg=findViewById(R.id.SUBSCRIPTION_IMAGE_RESPONSE);
        sellerLeadsImg=findViewById(R.id.SUBSCRIPTION_IMAGE_SELLER_LEADS);
        buyerLeadsImg=findViewById(R.id.SUBSCRIPTION_IMAGE_BUYER_LEADS);
        earnmoreImg=findViewById(R.id.SUBSCRIPTION_IMAGE_EARN);
        indianHeroImg=findViewById(R.id.SUBSCRIPTION_IMAGE_HERO);
        nestProsImg=findViewById(R.id.SUBSCRIPTION_IMAGE_NEST);
        chatFeaturesImg=findViewById(R.id.SUBSCRIPTION_IMAGE_CHAT);
        imagesImg=findViewById(R.id.SUBSCRIPTION_IMAGE_IMAGES);
        freezeImg=findViewById(R.id.SUBSCRIPTION_IMAGE_FREEZE);
        tieUpImg=findViewById(R.id.SUBSCRIPTION_IMAGE_TIOP);

        data=new ArrayList<>();

        fetchSubscription();
    }
    private void fetchSubscription() {
        StringRequest request = new StringRequest(Request.Method.POST, UrlClass.GET_SUBSCRIPTIONS_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    String  status = jsonObject.getString("status");
                    if (status.equals("1")){
                        JSONArray jsonArray= jsonObject.getJSONArray("SubscriptionPlans");
                        for (int i=0;i<jsonArray.length();i++){
                            SubscriptionApiModal modal =  new Gson().fromJson(jsonArray.getJSONObject(i).toString(),SubscriptionApiModal.class);
                            data.add(modal);
                        }
                        handlerViewData();
                        String save =  new Gson().toJson(data);
                        PrefMananger.saveString(SubscriptionKnowMore.this,"subscription",save);
                    }
                }catch (Exception e){
                    Log.e("errorFetchingSubs", "onResponse: "+e );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Log.e("errorFetchingSubs", "onResponse: "+error );

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
    private void handlerViewData() {
        String nospace  =name.toLowerCase().replace(" ","");
        if (name.equals("Free")){
            freeHandler();
            return;
        }
        for (SubscriptionApiModal info:data){
            if (info.getPlan().toLowerCase().replace(" ","").equals(nospace)){
                setViewOfPropertySubsCription(info);
                screen=2;
            }
            if (info.getValidity().equals(name)){
                setViewOfSubsCription(info);
                screen=1;
            }
        }
    }
    private void freeHandler() {
        view22.setVisibility(View.GONE);
        view23.setVisibility(View.GONE);
        if (freeCount>0){
            TextView textView = (TextView) cardView.getChildAt(0);
            textView.setText("Post Property");
        }else {
            TextView textView = (TextView) cardView.getChildAt(0);
            textView.setText("No Free Listing Available");
        }

    }
    private void setViewOfSubsCription(SubscriptionApiModal info) {
        id=info.getId();
        time.setText(info.getValidity()+" Months");
        price.setText("₹"+info.getPrice());
        Integer integer = Integer.parseInt(info.getPrice())-184;
        purchase.setText(integer+" Purchase");
        offer.setText(info.getPlan());
        listingMonths.setText(info.getNumber_of_listings()+" Listings Months");
        varification.setText(info.getProperty_self_verification());
        if (info.getProperty_self_verification()==null){
            varificationImg.setImageResource(R.drawable.close_icon_multipul);
            varification.setText("Verification");
        }
        responseRate.setText(info.getResponse_rate());
        if (info.getResponse_rate()==null){
            responseRate.setText("Response Rate");
            responseRateImg.setImageResource(R.drawable.close_icon_multipul);
        }
        String[] sleads = info.getSeller_proposal_leads().split("\\|");
        sellerLeads.setText(sleads[0]+" Seller leads for "+sleads[1]+" Months");

        String[] bleads = info.getBuyer_proposal_leads().split("\\|");
        buyerLeads.setText(bleads[0]+" Buyer leads for "+bleads[1]+" Months");
        earnmore.setText("Join earn more group "+info.getJoin_earn_more_group());
        indianHero.setText("Join indian hero group "+info.getJoin_indias_heros_more_group());
        nestPros.setText(info.getLocal_nest_professinals_features());
        if (info.getChat_feature().equals("Yes")){
            chatFeatures.setText("Chat Feature");
        }else {
            chatFeatures.setText("Chat Feature");
            chatFeaturesImg.setImageResource(R.drawable.close_icon_multipul);
        }
        images.setText(info.getImage_upload_quantity()+" images can upload.");
        if (info.getFreeze_and_resume_project_feature().equals("Yes")){
            freeze.setText("Freeze and Resume");
        }else {
            freeze.setText("Freeze and Resume");
            freezeImg.setImageResource(R.drawable.close_icon_multipul);
        }
        if (info.getContact_privacy_feature().equals("Yes")){
            chatFeatures.setText("Contact Privacy");
        }else {
            chatFeatures.setText("Contact Privacy");
            chatFeaturesImg.setImageResource(R.drawable.close_icon_multipul);
        }
        tieUp.setText("Tie up with builder "+info.getTie_up_with_builders());
    }
    private void setViewOfPropertySubsCription(SubscriptionApiModal info) {
        id=info.getId();
            time.setText(name);
            price.setText("₹ "+info.getPrice());
            Integer integer = Integer.parseInt(info.getPrice())-184;
            purchase.setText(integer+" Purchase");
            validity.setText(info.getNumber_of_listings());
            listingMonths.setText(info.getValidity()+" Days Visibility");
            varification.setText(info.getVisiblity());
            responseRate.setText(info.getResponse_rate()+"Response");
            sellerLeads.setText(info.getOffer_proposal_by_buyer_on_listing()+" Offers");
            buyerLeads.setText(info.getImage_upload_quantity()+" Images can upload");
            if (info.getProperty_description().equals("No")){
            earnmore.setText("Property Description");
            earnmoreImg.setImageResource(R.drawable.close_icon_multipul);
            }else {
            earnmore.setText("Property Description");
            }
            if (info.getChat_feature().equals("Yes")){
                indianHero.setText("Chat Feature");
            }else {
                indianHero.setText("Chat Feature");
                indianHeroImg.setImageResource(R.drawable.close_icon_multipul);
            }
            if (info.getFreeze_and_resume_project_feature().equals("Yes")){
                nestPros.setText("Freeze and Resume");
            }else {
                nestPros.setText("Freeze and Resume");
                nestProsImg.setImageResource(R.drawable.close_icon_multipul);
                }
            if (info.getContact_privacy_feature().equals("Yes")){
                chatFeatures.setText("Contact Privacy");
            }else {
                chatFeatures.setText("Contact Privacy");
                chatFeaturesImg.setImageResource(R.drawable.close_icon_multipul);
            }
            if (info.getTag_on_listing().equals("Yes")){
                images.setText("Tag On Listing");
            }else {
                images.setText("Tag On Listing");
                imagesImg.setImageResource(R.drawable.close_icon_multipul);
            }
    }


}