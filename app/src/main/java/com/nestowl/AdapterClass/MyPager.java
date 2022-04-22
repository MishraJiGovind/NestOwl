package com.nestowl.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.nestowl.brokerapp.R;


public class MyPager extends PagerAdapter {
    private Context context;
    FrameLayout imageView;


    public MyPager(Context context)
    {
        this.context=context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(getImageAt(position),null);

        /*imageView = view.findViewById(R.id.image_view_idd);*/
/*
        imageView.layout(context.getResources().getDrawable(getImageAt(position)));
*/
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object ==view;
    }

    private int getImageAt(int position) {
        switch (position) {
            case 0:
                return R.layout.subscriptionplan_first;
            case 1:
                return R.layout.subscription_second_layouot;
            case 2:
                return R.layout.subscripition_third_screen;
            default:
                return R.layout.subscriptionplan_first;
        }
    }

}
