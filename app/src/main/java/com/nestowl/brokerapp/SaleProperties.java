package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.SeekBar;
import android.widget.TextView;

import com.nestowl.AdapterClass.ChangedListener;
import com.nestowl.AdapterClass.MyPager;
import com.nestowl.AdapterClass.Pager;
import com.nestowl.Fragment.FiltersFragemetnLimit;
import com.nestowl.Fragment.FiltersFragmentSecond;
import com.nestowl.Fragment.FiltersOtherThird;
import com.nestowl.model.BudgetPOJO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SaleProperties extends AppCompatActivity implements ChangedListener.OnItemSeleted, TabLayout.BaseOnTabSelectedListener {
    FrameLayout frame_contact_builder, make_offer, make_offer_second, frame_one, frame_two, frame_three,amenties_id;
    CardView card_sale,card_two;
    ScrollView scroll_sale;
    FrameLayout frame_sale, frame_posted, frame_budget, frame_bhk, frame_propert,carpet_area;
    Context context;
    LinearLayout lnr_sort;
    TextView tv_filter,tv_dial;
    MyPager myPager;
    NestedScrollView nested;
    ImageView three_first,dots_second,back_img;
    SeekBar sekbar;
    private ViewPager viewPager;


    Boolean is_Residential_click=false;
    Boolean is_Commercial_click=false;
    Boolean is_other_click=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_properties);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           onBackPressed();

            }
        });
        dots_second=findViewById(R.id.three_dots_second);

        carpet_area=findViewById(R.id.NEST_PRO_CITY);
        nested=findViewById(R.id.nested_scroll_view);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }


        make_offer = findViewById(R.id.make_offer_sale_act);
        three_first=findViewById(R.id.three_dots_firs);
        init();

        make_offer_second = findViewById(R.id.maek_offer_two);


        frame_one = findViewById(R.id.frame_one_image);



        frame_sale = findViewById(R.id.sort_filter_second_main);
        amenties_id= findViewById(R.id.amenties_id_sale);
/*
        scroll_sale = findViewById(R.id.scroll_view_sale);
*/
        frame_three = findViewById(R.id.CUSTOM_MY_PRO_IMG);

        frame_budget = findViewById(R.id.NEST_PRO_PIN);


        frame_bhk = findViewById(R.id.NEST_PRO_Z_A);
        frame_propert = findViewById(R.id.NEST_PRO_A_Z);
        frame_posted = findViewById(R.id.NEST_PRO_LOCALTIES);
        lnr_sort=findViewById(R.id.lnr_sort_sale);

        three_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this,PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });


        dots_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this,PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });




        tv_filter=findViewById(R.id.first_filter_sale);

        carpet_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*
                sekbar=dialog.findViewById(R.id.seek_bar_dialog);
                tv_dial=dialog.findViewById(R.id.tv_dialog);*/
                final Dialog dialog = new Dialog(SaleProperties.this);
                dialog.setContentView(R.layout.carpet_layout);

                SeekBar seekbar_second;
                 final TextView tv_second;
                seekbar_second=dialog.findViewById(R.id.seek_bar_capet_area);
                tv_second=dialog.findViewById(R.id.tv_carpet_area);
                dialog.setCanceledOnTouchOutside(true);



                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView imageView=dialog.findViewById(R.id.iv_first);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                seekbar_second.setMax(2450);
                seekbar_second.setProgress(0);


                seekbar_second.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tv_second.setText(""+progress);
                        //tv1.setText(""+0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });




            }
        });
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nested.setVisibility(View.VISIBLE);
/*
                tab_filters.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));
*/


                /*Intent intent=new Intent(SaleProperties.this, FiltersScreen.class);
                startActivity(intent);*/
            }
        });

        amenties_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                final Dialog dialog= new Dialog(SaleProperties.this);
                dialog.setContentView(R.layout.amenties_layout_second);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));

                dialog.getWindow().setAttributes(lp);

                FrameLayout iv_c = dialog.findViewById(R.id.iv_cancel_9);

                dialog.show();
                iv_c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        lnr_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(SaleProperties.this);
                dialog.setContentView(R.layout.sort_by_filter);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img_four=dialog.findViewById(R.id.iv_fourth);
                img_four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


        frame_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(SaleProperties.this);

                dialog.setContentView(R.layout.bhk_layout);



     final TextView tv_dialog;
      SeekBar seekbar_dialog;

tv_dialog=dialog.findViewById(R.id.minimum_budget_dialog_tv);
seekbar_dialog=dialog.findViewById(R.id.seekbar_dialog);






                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img_second=dialog.findViewById(R.id.iv_second);
                img_second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                seekbar_dialog.setMax(70000000);
                seekbar_dialog.setProgress(0);
                seekbar_dialog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (progress<10000000) {
                            progress=(progress/500000);
                            tv_dialog.setText("" + (progress*5) + " L");
                        }else{
                            long value=progress/10000000;
                            long lac=(progress%10000000);
                            lac=lac/1000000;
                            if (progress<seekBar.getMax()) {
                                //if (value>1) {
                                tv_dialog.setText("" + Math.round((value)) + "." + lac + " Cr");
                                //  }
                            }else {
                                tv_dialog.setText("7.0 Cr");
                            }
                        }
                        //tv1.setText(""+0);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });





            }
        });
        frame_bhk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ArrayList<BudgetPOJO> budgetPOJOS=new ArrayList<>();
                budgetPOJOS.add(new BudgetPOJO("Studio",false));
                budgetPOJOS.add(new BudgetPOJO("1Bhk",false));
                budgetPOJOS.add(new BudgetPOJO("2BHK",false));
                budgetPOJOS.add(new BudgetPOJO("3BHK",false));
                budgetPOJOS.add(new BudgetPOJO("4BHK",false));
                budgetPOJOS.add(new BudgetPOJO(">4BHK",false));
                dialogthird(budgetPOJOS);

             /*   Dialog dialog = new Dialog(SaleProperties.this);
                dialog.setContentView(R.layout.bhk_layout);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();*/
            }
        });

        frame_propert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SaleProperties.this);
                dialog.setContentView(R.layout.property_type_layout_second);
                final FrameLayout frm_residential=dialog.findViewById(R.id.frm_residential);
                final FrameLayout frm_commercial=dialog.findViewById(R.id.frm_commercial);
                final FrameLayout frm_other=dialog.findViewById(R.id.frm_other);
                final LinearLayout l1=dialog.findViewById(R.id.lnr_residential_property_type);

                final LinearLayout l2=dialog.findViewById(R.id.lnr_commercial_property_type);
                final LinearLayout l3=dialog.findViewById(R.id.lnr_other_property_type);

                frm_residential.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        l2.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);
                        if (is_Residential_click){
                            is_Residential_click=false;
                            frm_residential.setBackgroundResource(R.drawable.employe_circle_rounded);
                            l1.setVisibility(View.GONE);
                        } else {

                            is_Residential_click=true;
                            frm_residential.setBackgroundResource(R.drawable.selected_background_filter);
                            l1.setVisibility(View.VISIBLE);

                        }


                    }
                });



                frm_commercial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        l1.setVisibility(View.GONE);
                        l3.setVisibility(View.GONE);


                        if (is_Commercial_click){
                            is_Commercial_click=false;
                            frm_commercial.setBackgroundResource(R.drawable.employe_circle_rounded);
                            l2.setVisibility(View.GONE);
                        } else {

                            is_Commercial_click=true;
                            frm_commercial.setBackgroundResource(R.drawable.selected_background_filter);
                            l2.setVisibility(View.VISIBLE);

                        }


                    }
                });

                frm_other.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        l1.setVisibility(View.GONE);
                        l2.setVisibility(View.GONE);


                        if (is_other_click){
                            is_other_click=false;
                            frm_other.setBackgroundResource(R.drawable.employe_circle_rounded);
                            l3.setVisibility(View.GONE);
                        } else {

                            is_other_click=true;
                            frm_other.setBackgroundResource(R.drawable.selected_background_filter);
                            l3.setVisibility(View.VISIBLE);

                        }


                    }
                });



                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img_third=dialog.findViewById(R.id.iv_third);
                img_third.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        frame_posted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BudgetPOJO> budgetPOJOS=new ArrayList<>();
                budgetPOJOS.add(new BudgetPOJO("Nest Pro",false));
                budgetPOJOS.add(new BudgetPOJO("Owner",false));
                dialogtwo(budgetPOJOS);
            }
        });


        frame_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });


        card_two=findViewById(R.id.card_sale_two);
        card_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, ScreenSixteenDetailsPage.class);
                startActivity(intent);
            }
        });
        frame_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, PropertiesBhkActivity.class);
                startActivity(intent);

            }
        });
        card_sale = findViewById(R.id.CUSTOM_MY_PRO_CARD);
        card_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, ScreenSixteenDetailsPage.class);
                startActivity(intent);
            }
        });
        make_offer_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, MakeOfferActivity.class);
                startActivity(intent);

            }
        });
        make_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaleProperties.this, MakeOfferActivity.class);
                startActivity(intent);
            }
        });

         }

    Dialog dialog;
    ArrayList<BudgetPOJO> budgetPOJOS;
    ChangedListener changedListener;
    RecyclerView rv_list;

    public void openDialog(ArrayList<BudgetPOJO> budgetPOJOArrayList) {
        budgetPOJOS = budgetPOJOArrayList;

        dialog = new Dialog(SaleProperties.this);
        dialog.setContentView(R.layout.property_type_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


        dialog.getWindow().setAttributes(lp);
         rv_list = dialog.findViewById(R.id.recycler_view_property_type);
        ImageView iv_cancel = dialog.findViewById(R.id.iv_cancel);

        dialog.show();
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(3,1));
        changedListener=new ChangedListener(this,budgetPOJOS,this);
        rv_list.setAdapter(changedListener);

    }

    @Override
    public void onItemSeleted(int position) {
        if (budgetPOJOS.get(position).isSelected){
            budgetPOJOS.get(position).isSelected=false;
        }else {
            budgetPOJOS.get(position).isSelected=true;
        }
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(3,1));
        changedListener=new ChangedListener(this,budgetPOJOS,this);
        rv_list.setAdapter(changedListener);

    }


    public void dialogtwo(ArrayList<BudgetPOJO> budgetPOJOArrayList) {
        budgetPOJOS = budgetPOJOArrayList;

        dialog = new Dialog(SaleProperties.this);
        dialog.setContentView(R.layout.posted_by);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


        dialog.getWindow().setAttributes(lp);
        rv_list = dialog.findViewById(R.id.recycler_view_property_type);
        ImageView iv_cancel = dialog.findViewById(R.id.iv_cancel);

        dialog.show();
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        changedListener=new ChangedListener(this,budgetPOJOS,this);
        rv_list.setAdapter(changedListener);

    }


    public void dialogthird(ArrayList<BudgetPOJO> budgetPOJOArrayList) {
        budgetPOJOS = budgetPOJOArrayList;

        dialog = new Dialog(SaleProperties.this);
        dialog.setContentView(R.layout.bhk_main_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleProperties.this, android.R.color.transparent)));


        dialog.getWindow().setAttributes(lp);
        rv_list = dialog.findViewById(R.id.recycler_view_property_type);
        ImageView iv_cancel = dialog.findViewById(R.id.iv_cancel);

        dialog.show();
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(3,1));
        changedListener=new ChangedListener(this,budgetPOJOS,this);
        rv_list.setAdapter(changedListener);

    }

    ViewPager viewPager_filter;
    TabLayout tab_filters;
    FrameLayout frm_rst, frm_appl;
    TextView tv_add_location;

     void init() {

        viewPager_filter =findViewById(R.id.filters_view_pager);
        tab_filters = findViewById(R.id.tab_filter_screen);
        tv_add_location=findViewById(R.id.add_localities);
        tv_add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SaleProperties.this,SearchViewActivity.class);
                startActivity(intent);
            }
        });

        tab_filters.addTab(tab_filters.newTab().setText("Residential"));
        tab_filters.addTab(tab_filters.newTab().setText("Commercial"));
        tab_filters.addTab(tab_filters.newTab().setText("Other"));

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));

        }
        tab_filters.setOnTabSelectedListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frame_filter_id,new FiltersFragemetnLimit()).commit();

    }

    private void setupViewPager(ViewPager viewPager) {

        Pager adapter = new Pager(getSupportFragmentManager());
        adapter.addFragment(new FiltersFragemetnLimit(), "Residential");
        adapter.addFragment(new FiltersFragemetnLimit(), "Commercial");
        adapter.addFragment(new FiltersFragemetnLimit(), "Other");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getPosition()==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragemetnLimit()).commit();

        }else if (tab.getPosition()==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id,new FiltersFragmentSecond()).commit();

        } else if (tab.getPosition()==2){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_filter_id, new FiltersOtherThird()).commit();
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void reset(View view) {


    }

}
