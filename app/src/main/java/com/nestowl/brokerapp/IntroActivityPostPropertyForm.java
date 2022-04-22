
package com.nestowl.brokerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class IntroActivityPostPropertyForm extends AppCompatActivity {
    PreferenceManager preferenceManager;
    LinearLayout Layout_bars;
    TextView[] bottomBars;
    int[] screens;
    ImageView Skip, Next;
    TextView tv_lets_start;
    ViewPager vp;
    SliderAapter myvpAdapter;


    ViewPager.OnPageChangeListener viewPagerPageChangeListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {
            ColoredBars(position);

            if (position == screens.length - 1) {
                // Next.setText("Let's Start");
                //tv_lets_start.setVisibility(View.VISIBLE);
          //      Next.setVisibility(View.GONE);
              //  Skip.setVisibility(View.VISIBLE);
            } else {
            //    Next.setVisibility(View.VISIBLE);
               // Skip.setVisibility(View.VISIBLE);
            }
            if (position==0){
                Skip.setVisibility(View.GONE);
            }else {
                Skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_layout);


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
        vp=findViewById(R.id.intro_viewPager);
        Next=findViewById(R.id.id_third_screen);
        Skip=findViewById(R.id.id_third_back);

        Layout_bars = (LinearLayout) findViewById(R.id.layoutBars);

        screens=new int[]{
                R.layout.post_property_first,
                R.layout.second_post_property,
                R.layout.third_post_property,
                R.layout.five_screen_next_screen

        };
        ColoredBars(0);

        myvpAdapter=new SliderAapter();
        vp.setAdapter(myvpAdapter);
        preferenceManager=new PreferenceManager(this);


        vp.addOnPageChangeListener(viewPagerPageChangeListener);


/* addSlide(AppIntroFragment.newInstance("Welcome to PMR", "Making moving simpler with\ncustomized relocations solutions!",
                R.drawable.first_intro_icon, ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));
        addSlide(AppIntroFragment.newInstance("Second App Into", "Your belongings our responsiblity!",
                R.drawable.second_intro_icon, ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));
        addSlide(AppIntroFragment.newInstance("Third App Into","We beileve that your belongings\nare not just goods and priceless\nmemories.",
                R.drawable.t1hird_intro_icon, ContextCompat.getColor(getApplicationContext(), R.color.colorWhite)));*/



    }

    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            vp.setCurrentItem(i);
        }else {
            launchMain();
        }

    }

    public void skip(View view) {
        int i = getItem(-1);
        if (i >=0) {
            vp.setCurrentItem(i);
        }
        //launchMain();

    }

    private void ColoredBars(int thisScreen) {
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_active_page);
        bottomBars = new TextView[screens.length];

        Layout_bars.removeAllViews();
        for (int i = 0; i < bottomBars.length; i++) {
            bottomBars[i] = new TextView(this);
            bottomBars[i].setTextSize(50);
            bottomBars[i].setText(Html.fromHtml("¯"));
            Layout_bars.addView(bottomBars[i]);
            bottomBars[i].setTextColor(colorsInactive[thisScreen]);
        }
        if (bottomBars.length > 0)
            bottomBars[thisScreen].setTextColor(colorsActive[thisScreen]);

    }

    private int getItem(int i) {
        return vp.getCurrentItem() + i;
    }

    private void launchMain() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(IntroActivityPostPropertyForm.this, PlanBasicActivity.class));
        finish();
    }


    public class SliderAapter extends PagerAdapter
    {
        private LayoutInflater inflater;


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(screens[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return screens.length;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }

}

