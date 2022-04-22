package com.nestowl.Fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nestowl.brokerapp.HomeScreen;
import com.nestowl.brokerapp.HomeScreenSeller;
import com.nestowl.brokerapp.MakeOfferAllQueryPages;
import com.nestowl.brokerapp.PlanBasicActivity;
import com.nestowl.brokerapp.R;
import com.nestowl.brokerapp.SaleProperties;
import com.nestowl.brokerapp.SalePropertiesSecondForNestOwners;
import com.nestowl.brokerapp.SaveMoreClickSeller;
import com.nestowl.brokerapp.SearchViewActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeScreenTwo extends Fragment {
    FrameLayout frameLayout;
    TextView textView,tv_post,home_filter,post_second,tv_third;
    HomeScreenSeller homeScreenTwo;
    ScrollView scrollView_two;
    HomeScreen home_firsts;
    LinearLayout lnr_parent_frg,incomplete;
    LinearLayout second_post_property,save_more,lnr_hello_sahil;
    HomeScreen homeScreen;
    FrameLayout frame_nes,frame_second,frame_third,frame_five,frame_last,frame_details,frame_details_second;


    public HomeScreenTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_screen_two, container, false);
        incomplete=view.findViewById(R.id.incomplete_listing_id);
        frame_details=view.findViewById(R.id.nest_owner_first);
        frame_details_second=view.findViewById(R.id.nest_owner_second);

        frame_third=view.findViewById(R.id.manage_property_third);
        frame_second=view.findViewById(R.id.manage_property_second);
        frame_five=view.findViewById(R.id.manage_property_four);
        frame_last=view.findViewById(R.id.manage_property_five);
        frameLayout = view.findViewById(R.id.CUSTOM_PRO_MANNAGE);

        save_more=view.findViewById(R.id.save_more_lnd);

        lnr_hello_sahil =view.findViewById(R.id.id_home_frg_hello_sahil);

        lnr_hello_sahil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));

            }
        });

        second_post_property=view.findViewById(R.id.post_propert_second_design);
        frame_nes=view.findViewById(R.id.nes_pro_arshi);
        second_post_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                startActivity(new Intent(getContext(), IntroScreenPostProperty.class));
*/


            }
        });




        save_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaveMoreClickSeller.class));



            }
        });

        frame_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SaleProperties.class));


            }
        });
        frame_details_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SalePropertiesSecondForNestOwners.class));


            }
        });

        incomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PlanBasicActivity.class));


            }
        });


        frame_nes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MakeOfferAllQueryPages.class));


            }
        });

        tv_post=view.findViewById(R.id.post_property_dialog);

        homeScreenTwo= (HomeScreenSeller) getActivity();
        home_filter=view.findViewById(R.id.edt_home_filterr);
        tv_third=view.findViewById(R.id.manage_property_third_buyer);
        homeScreenTwo= (HomeScreenSeller) getActivity();
        lnr_parent_frg=view.findViewById(R.id.parent_home_frg_lnr);


        tv_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.manage_property_third);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.ivl);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        frame_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.manage_property_fourthd);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.ivl1);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        frame_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.manage_property_five);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imgs=dialog.findViewById(R.id.POPUP_PROPERTY_DISMISSED);
                imgs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        home_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getContext(), SearchViewActivity.class));


            }
        });

        scrollView_two=view.findViewById(R.id.home_screen_seller_id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView_two.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {


                    if (scrollY>100)
                    {
                        homeScreenTwo.bottomNavigationView.setVisibility(View.GONE);

                    }
                    else {
                        homeScreenTwo.bottomNavigationView.setVisibility(View.VISIBLE);
                    }
                    if (scrollY>120)
                    {
                        homeScreenTwo.frm_haider_child.setVisibility(View.GONE);
                        homeScreenTwo.lnr_haider_child.setVisibility(View.VISIBLE);
                        lnr_parent_frg.setVisibility(View.GONE);

                    }
                    else {

                        homeScreenTwo.frm_haider_child.setVisibility(View.VISIBLE);
                        homeScreenTwo.lnr_haider_child.setVisibility(View.GONE);
                        lnr_parent_frg.setVisibility(View.VISIBLE);


                    }


                }
            });
        }
        tv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.boost_post_layour);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();

            }
        });



        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.manage_property_first_design);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));

                dialog.getWindow().setAttributes(lp);


                dialog.show();
                ImageView iv_cancel = dialog.findViewById(R.id.iv_cancel_first);

                iv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


        frame_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.manage_property_second);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imf1=dialog.findViewById(R.id.ivl4);
                imf1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


        return view;
    }

}
