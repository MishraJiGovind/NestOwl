package com.nestowl.brokerapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.EmptyFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

public class NestProfessionalSecondDesign extends AppCompatActivity {
    FrameLayout frame_make_offer_first,frame_start_offers,frame_last,view_profile,frm_rer,frm_no,
            property_one,property_two,property_third,property_four,amenties_home;
TextView tv_other,tv_first,tv_second,tv_explore,read_disclaimer,tv_profile,tv_read_more,firsts,second,
        tv_third,tv_fourth,tv_five,tv_six,tv_saven,tv_eight,tv_nine,first_one,second_one,third_one,four_one,five_one,six_one,saven_one,
        eight_one,nine_one;
    GoogleMap maps;

    TabLayout tab_filters;
    ViewPager viewPager_filter;
    ScrollView scrollView_first;
    LinearLayout lnr_btm;
    FrameLayout f1,f2,f3,f4,f5,f6,f7;
    NestProfessionalSecondDesign homeScreen;
    ImageView back_img;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_professional_second_designg);


        frame_make_offer_first=findViewById(R.id.CUSTOM_PROPERTY_ONBOARD_MAKE_OFFER);
        frame_start_offers =findViewById(R.id.start_an_offer_nest_owner);
        frame_last=findViewById(R.id.make_eight);
        frm_no=findViewById(R.id.LEADS_PROPERTY_DETA_SUMBIT_NO);
        f1=findViewById(R.id.make_two);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        f2=findViewById(R.id.make_three);
        f3=findViewById(R.id.LEADS_PROPERTY_DETA_CHAT);
        f4=findViewById(R.id.make_five);
        f5=findViewById(R.id.make_six);
        f6=findViewById(R.id.make_saven);

        lnr_btm=findViewById(R.id.lnr_btm_nest_owner);

        property_one=findViewById(R.id.property_one_sixteen_nst);
        property_two=findViewById(R.id.property_two_sixteen_nst);
        tv_first=findViewById(R.id.view_all_sixteen_nst);
        tv_second=findViewById(R.id.view_all_sixteen);
        amenties_home=findViewById(R.id.LEADS_PROPERTY_DETA_AMETIES_MORE);
        tv_explore=findViewById(R.id.explore_more_sixteen);
        read_disclaimer=findViewById(R.id.LEADS_PROPERTY_DETA_DESCLAIMER);
        tv_profile=findViewById(R.id.view_profile_rera);
        tv_read_more=findViewById(R.id.read_more_second);
        first_one=findViewById(R.id.first_one);
        second_one=findViewById(R.id.second_one);
        third_one=findViewById(R.id.third_one);
        four_one=findViewById(R.id.fourth_one);
        five_one=findViewById(R.id.five_one);
        six_one=findViewById(R.id.six_one);
        saven_one=findViewById(R.id.saven_one);
        eight_one=findViewById(R.id.eight_one);
        nine_one=findViewById(R.id.nine_one);


/*
        homeScreen= (HomeScreen) this();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView_first.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY>100)
                    {
                        homeScreen.bottomNavigationView.setVisibility(View.GONE);

                    }
                    else {
                        homeScreen.bottomNavigationView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
*/

        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tab_filters.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    scrollView_first.scrollTo(0,700);
                }else if (tab.getPosition()==1){
                    scrollView_first.scrollTo(0,1050);
                }else if (tab.getPosition()==2){
                    scrollView_first.scrollTo(0,1700);
                }else if (tab.getPosition()==3){
                    scrollView_first.scrollTo(0,2200);
                }else if (tab.getPosition()==4){
                    scrollView_first.scrollTo(0,2500);
                }else if (tab.getPosition()==5){
                    scrollView_first.scrollTo(0,3100);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        scrollView_first=findViewById(R.id.scroll_parent_details);


        firsts=findViewById(R.id.first_first_first);
        second=findViewById(R.id.second_second_second);
        tv_third=findViewById(R.id.third_third_third);
        tv_fourth=findViewById(R.id.fourth_fourth_fourth);
        tv_five=findViewById(R.id.five_five_five);
        tv_saven=findViewById(R.id.saven_saven_saven);
        tv_eight=findViewById(R.id.eight_eight_eight);
        tv_nine=findViewById(R.id.nine_nine_nine);
        tv_six=findViewById(R.id.six_six_six);




        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}




        tv_other=findViewById(R.id.nst_sixteen_tv);
        frm_rer=findViewById(R.id.frm_rer);
        property_third=findViewById(R.id.property_one_sixteen);
        property_third=findViewById(R.id.property_two_sixteen);

        setupViewPager(viewPager_filter);

        tab_filters.setupWithViewPager(viewPager_filter);


        first_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });



        second_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        third_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        four_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        five_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        six_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        saven_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        eight_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        nine_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });






        firsts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               secondSystem();
            }
        });



        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });


        tv_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });


        tv_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        tv_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        tv_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        tv_saven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        tv_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });
        tv_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondSystem();
            }
        });





        tv_read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,ReadMoreScreen.class);
                startActivity(intent);
            }
        });
        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NestProfessionalSecondDesign.this,SahilDahiyaReviews.class);
                startActivity(intent);
            }
        });

read_disclaimer.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent =new Intent(NestProfessionalSecondDesign.this,ReadDisclaimer.class);
        startActivity(intent);
    }
});
        tv_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(NestProfessionalSecondDesign.this);
                dialog.setContentView(R.layout.property_details_second);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NestProfessionalSecondDesign.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img2=dialog.findViewById(R.id.iv2);
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        amenties_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NestProfessionalSecondDesign.this);
                dialog.setContentView(R.layout.amenties_layout);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NestProfessionalSecondDesign.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView im3=dialog.findViewById(R.id.iv3);
                im3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        property_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NestProfessionalSecondDesign.this, NestProfessionalSecondDesign.class);
                startActivity(intent);
            }
        });
/*
        property_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NestOwnersSixteen.this, NestOwnersSixteen.class);
                startActivity(intent);
            }
        });*/
        property_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this, NestProfessionalSecondDesign.class);
                startActivity(intent);
            }
        });

        property_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this, NestProfessionalSecondDesign.class);
                startActivity(intent);
            }
        });



        tv_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,SalePropertiesSecondForNestOwners.class);
                startActivity(intent);
            }
        });
        tv_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,SalePropertiesSecondForNestOwners.class);
                startActivity(intent);
            }
        });


        tv_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,SalePropertiesSecondForNestOwners.class);
                startActivity(intent);
            }
        });
        frm_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NestProfessionalSecondDesign.this);
                dialog.setContentView(R.layout.explore_more);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NestProfessionalSecondDesign.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.iv_cancel);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(NestProfessionalSecondDesign.this);
                dialog.setContentView(R.layout.other_charges_layout);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NestProfessionalSecondDesign.this,
                        android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView im4=dialog.findViewById(R.id.DIO_OTHER_CHARGES_DISSMISED);
                im4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        view_profile=findViewById(R.id.view_profile_nineteen);

        frm_rer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NestProfessionalSecondDesign.this,PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,SahilDahiyaReviews.class);
                startActivity(intent);
            }
        });
        frame_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });

        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });
        f4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });
        f5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });
        f6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }
        });
        frame_start_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,StartOfferQueryIntro.class);
                startActivity(intent);
            }
        });
        frame_make_offer_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NestProfessionalSecondDesign.this,QueryMakeOfferFirstPages.class);
                startActivity(intent);
            }

        });

        scrollView_first.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                tab_filters.setVisibility(View.VISIBLE);

                if (scrollY>=3100)
                {
                    viewPager_filter.setCurrentItem(5);

                }else

                if (scrollY>=2500)
                {
                    viewPager_filter.setCurrentItem(4);

                }else

                if (scrollY>=2200)
                {
                    viewPager_filter.setCurrentItem(3);

                }
                else
                if (scrollY>=1700)
                {
                    viewPager_filter.setCurrentItem(2);

                }else
                if (scrollY>=1050)
                {
                    viewPager_filter.setCurrentItem(1);

                }else

                if (scrollY>=700)
                {
                    viewPager_filter.setCurrentItem(0);

                }else {
                    viewPager_filter.setCurrentItem(0);
                    tab_filters.setVisibility(View.GONE);
                }
            }
        });
    }



    public void onMapReady(GoogleMap googleMap) {
        maps = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        maps.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        maps.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        maps.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent=new Intent(NestProfessionalSecondDesign.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }


    public  void secondSystem(){

        Intent intent=new Intent(NestProfessionalSecondDesign.this, NestProfessionalSecondDesign.class);
        startActivity(intent);
    }

    private void setupViewPager(ViewPager viewPager) {
        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new EmptyFragment(), "Overview");
        adapter.addFragment(new EmptyFragment(), "Description");
        adapter.addFragment(new EmptyFragment(), "Property Details");
        adapter.addFragment(new EmptyFragment(), "Amenties");
        adapter.addFragment(new EmptyFragment(), "Locality");
        adapter.addFragment(new EmptyFragment(), "Properties");

        viewPager.setAdapter(adapter);
    }


}
