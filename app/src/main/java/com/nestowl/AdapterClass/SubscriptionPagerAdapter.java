package com.nestowl.AdapterClass;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.nestowl.CommenDialog.WarningDio;
import com.nestowl.model.SubsricptionModal;
import com.nestowl.brokerapp.EditSignUpForm;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SubscriptionKnowMore;
import com.nestowl.brokerapp.SubscriptionPlan;
import com.nestowl.payment.paytmGateway;
import com.nestowl.utils.PrefMananger;

import java.util.ArrayList;

public class SubscriptionPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<SubsricptionModal> data;
    TextView type,remaining,extra,price,toremove,lineOne,lineTwo,cut;
    FrameLayout knowMore,subscribe;

    public SubscriptionPagerAdapter(Context context, ArrayList<SubsricptionModal> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.properties_plan_five,container,false);
        type=view.findViewById(R.id.PROPERTY_PLAN_TYPE);
        remaining=view.findViewById(R.id.PROPERTY_PLAN_REMAINING);
        extra=view.findViewById(R.id.PROPERTY_PLAN_EXTRA_TEXT);
        price=view.findViewById(R.id.PROPERTY_PLAN_PRICE);
        toremove=view.findViewById(R.id.PROPERTY_PLAN_ONLYINPROPERTY);
        lineOne=view.findViewById(R.id.PROPERTY_PLAN_LINE_ONE);
        lineTwo=view.findViewById(R.id.PROPERTY_PLAN_LINE_TWO);
        knowMore=view.findViewById(R.id.PROPERTY_PLAN_KNOW_MORE);
        subscribe=view.findViewById(R.id.PROPERTY_PLAN_BUYPROPERTY);
        cut=view.findViewById(R.id.PROPERTY_PLAN_CUT);

        cut.setPaintFlags(cut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        SubsricptionModal info = data.get(position);
        type.setText(info.getTime());
        remaining.setText(info.getPurchases());
        extra.setText(info.getExtraLine());
        price.setText("₹"+info.getPrice());
        int tota=Integer.parseInt(info.getPrice())+599;
        cut.setText("₹"+tota);
        lineOne.setText(info.getLine());
        lineTwo.setText(info.getLine2());
        if (info.getTime().equals("FREE")){
            TextView textView =  (TextView) subscribe.getChildAt(0);
            textView.setText("Post Property");
        }else {
            TextView textView =  (TextView) subscribe.getChildAt(0);
            textView.setText("Buy Plan");
        }
        if (info.getPurchases()==null){
            subscribe.setVisibility(View.GONE);
            remaining.setText("0 Free post left");
        }

        if (info.getType().equals("Property")){
            toremove.setVisibility(View.VISIBLE);
        }
        if (info.getType().equals("lead")){
            knowMore.setVisibility(View.GONE);
//            lineTwo.setVisibility(View.GONE);
            extra.setVisibility(View.GONE);
        }
        knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getType().equals("Property")){
                    if (info.getTime().equals("FREE")){
                        Intent intent = new Intent(context, SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","2");
                        intent.putExtra("name","Free");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("Featured")){
                        Intent intent = new Intent(context, SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","2");
                        intent.putExtra("name","Featured");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("Premium")){
                        Intent intent = new Intent(context, SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","2");
                        intent.putExtra("name","Premium");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("Basic")){
                        Intent intent = new Intent(context, SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","2");
                        intent.putExtra("name","Basic");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("Basic Plus")){
                        Intent intent = new Intent(context, SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","2");
                        intent.putExtra("name","Basic Plus");
                        context.startActivity(intent);
                    }

                }else {
                    if (info.getTime().equals("12 Months")){
                        Intent intent = new Intent(context,SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","1");
                        intent.putExtra("name","12");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("18 Months")){
                        Intent intent = new Intent(context,SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","1");
                        intent.putExtra("name","18");
                        context.startActivity(intent);
                    }
                    if (info.getTime().equals("24 Months")){
                        Intent intent = new Intent(context,SubscriptionKnowMore.class);
                        intent.putExtra("type","NO");
                        intent.putExtra("view","1");
                        intent.putExtra("name","24");
                        context.startActivity(intent);
                    }
                }
            }
        });
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getType().equals("lead")){
                   new paytmGateway(context,info.getId(),3);
                }
                if (info.getTime().equals("FREE")){
                    if (PrefMananger.getString(context,"sub").equals("true")){
                        context.startActivity(new Intent(context, PlanBasicActivity.class));
                    }else {
                        if ( PrefMananger.getString(context,"id").equals("true")){
                            new WarningDio(context, "You are not subscribe any plan please subscribe to continue.", "Subscribe Now", null, new WarningDio.Response() {
                                @Override
                                public void getClicks(int click) {
                                    if (click==1){
                                        context.startActivity(new Intent(context,SubscriptionPlan.class));
                                    }
                                }
                            },false);
                        }else {
                            if (PrefMananger.getString(context,"status").equals("0")){
                                new WarningDio(context, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {
                                        if (click==1){
                                            context.startActivity(new Intent(context,EditSignUpForm.class));
                                        }
                                    }
                                },false);
                            }
                            if (PrefMananger.getString(context,"status").equals("1")){
                                new WarningDio(context, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {

                                    }
                                },false);
                            }
                            if (PrefMananger.getString(context,"status").equals("2")){
                                new WarningDio(context, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {
                                    }
                                },false);
                            }


                        }
                    }

                }else {
                    if (info.getType().equals("Property")){
                        new paytmGateway(context,info.getId(),2);
                    }else {
                        if ( PrefMananger.getString(context,"id").equals("true")){
                            new paytmGateway(context,info.getId(),1);
                        }else {
                            if (PrefMananger.getString(context,"status").equals("0")){
                                new WarningDio(context, "Your Profile is not Summited yet.", "Summit Profile", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {
                                        if (click==1){
                                            context.startActivity(new Intent(context, EditSignUpForm.class));
                                        }
                                    }
                                },false);
                            }
                            if (PrefMananger.getString(context,"status").equals("1")){
                                new WarningDio(context, "Your Profile is Summited but not verified yet.", "Ok", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {

                                    }
                                },false);
                            }
                            if (PrefMananger.getString(context,"status").equals("2")){
                                new WarningDio(context, "Your Profile is in Review yet.", "OK", null, new WarningDio.Response() {
                                    @Override
                                    public void getClicks(int click) {
                                    }
                                },false);
                            }
                        }
                    }
                }
            }
        });
        try {
            container.addView(view,position);
        }catch (Exception e){
            Log.e("subscriptionError", "instantiateItem: "+e );
        }

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
