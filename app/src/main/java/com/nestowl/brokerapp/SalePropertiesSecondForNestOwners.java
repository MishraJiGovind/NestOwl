package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

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

public class SalePropertiesSecondForNestOwners extends AppCompatActivity implements ChangedListener.OnItemSeleted, TabLayout.BaseOnTabSelectedListener {
FrameLayout property_id,bhk_two,budget_two,posted_two,carpet_area,tv_amenties,frame_one,frame_two,frm_sale,frm_ra;
TextView tv_sort,tv_filters;
LinearLayout lnr_rera,lnr_listing;
ImageView third,four;
FrameLayout f1,f2,firs,second;
CardView card1,card2;
ImageView back_img;
    MyPager myPager;

    NestedScrollView nested;
    private ViewPager viewPager;

    Boolean is_Residential_click=false;
    Boolean is_Commercial_click=false;
    Boolean is_other_click=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_properties_second_for_nest_owners);

        frm_ra=findViewById(R.id.frm_reras);
        firs=findViewById(R.id.replica_first_s);
        second=findViewById(R.id.replica_second);
third=findViewById(R.id.three_dots_third);
four=findViewById(R.id.three_dots_fourt);
card1=findViewById(R.id.card_arshi_onee);
card2=findViewById(R.id.card_arshi_twoo);
lnr_listing=findViewById(R.id.lnr_home_listing);
        back_img=findViewById(R.id.ARTICLES_BACK);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        nested=findViewById(R.id.nested_scroll_view);
        init();

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

lnr_listing.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(SalePropertiesSecondForNestOwners.this,NestOwnersSixteen.class);
        startActivity(intent);
    }
});

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalePropertiesSecondForNestOwners.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SalePropertiesSecondForNestOwners.this,NestOwnersSixteen.class);
                startActivity(intent);
            }
        });




/*
        llPagerDots = findViewById(R.id.id_circle_indicator);
*/

/*
        viewPager = findViewById(R.id.view_pager_login);
*/
frame_two=findViewById(R.id.sale_second_one);

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalePropertiesSecondForNestOwners.this,PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });


        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalePropertiesSecondForNestOwners.this,PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });











        frame_two.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(SalePropertiesSecondForNestOwners.this,PropertiesBhkActivity.class);
        startActivity(intent);
    }
});

        property_id=findViewById(R.id.property_id_sale_two);
        bhk_two=findViewById(R.id.bhk_id_sale_two);

        budget_two=findViewById(R.id.budget_id_sale_two);

        posted_two=findViewById(R.id.posted_by_sale_two);
        tv_sort=findViewById(R.id.sort_second);
        carpet_area=findViewById(R.id.carpet_area_two);
        tv_amenties=findViewById(R.id.amenties_two);
/*
        frame_one=findViewById(R.id.frame_one_image);
*/
        frame_two=findViewById(R.id.frame_twos_image);
        lnr_rera =findViewById(R.id.lnr_reras);



        lnr_rera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalePropertiesSecondForNestOwners.this, NestOwnersSixteen.class);
                startActivity(intent);
            }
        });




        frame_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SalePropertiesSecondForNestOwners.this, PropertiesBhkActivity.class);
                startActivity(intent);
            }
        });
        tv_amenties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Dialog dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
                dialog.setContentView(R.layout.amenties_layout_second);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView i_six=dialog.findViewById(R.id.iv_six);
                i_six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


            }
        });
        carpet_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
                dialog.setContentView(R.layout.carpet_layout);

                SeekBar seekbar_second;
                final TextView tv_second;
                seekbar_second=dialog.findViewById(R.id.seek_bar_capet_area);
                tv_second=dialog.findViewById(R.id.tv_carpet_area);


                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img_first=dialog.findViewById(R.id.iv_first);
                img_first.setOnClickListener(new View.OnClickListener() {
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
        tv_filters=findViewById(R.id.filters_second);
        tv_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nested.setVisibility(View.VISIBLE);

            }
        });

        tv_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
                dialog.setContentView(R.layout.sort_by_filter);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


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

        posted_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BudgetPOJO> budgetPOJOS=new ArrayList<>();
                budgetPOJOS.add(new BudgetPOJO("Nest Pro",false));
                budgetPOJOS.add(new BudgetPOJO("Owner",false));
                openDialog(budgetPOJOS);
            }
        });

        budget_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                final Dialog dialog = new Dialog(SalePropertiesSecondForNestOwners.this);

                dialog.setContentView(R.layout.budget_second_layout);



                final TextView tv_first;
                SeekBar seekbar_first;

                tv_first=dialog.findViewById(R.id.minimum_budget_dialog_tv_one);
                seekbar_first=dialog.findViewById(R.id.seekbar_dialog_one);






                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView i_saven=dialog.findViewById(R.id.iv_cancel);
                i_saven.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                seekbar_first.setMax(70000000);
                seekbar_first.setProgress(0);
                seekbar_first.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (progress<10000000) {
                            progress=(progress/500000);
                            tv_first.setText("" + (progress*5) + " L");
                        }else{
                            long value=progress/10000000;
                            long lac=(progress%10000000);
                            lac=lac/1000000;
                            if (progress<seekBar.getMax()) {
                                //if (value>1) {
                                tv_first.setText("" + Math.round((value)) + "." + lac + " Cr");
                                //  }
                            }else {
                                tv_first.setText("7.0 Cr");
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

        bhk_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ArrayList<BudgetPOJO> budgetPOJOS=new ArrayList<>();
                budgetPOJOS.add(new BudgetPOJO("Studio",false));
                budgetPOJOS.add(new BudgetPOJO("1Bhk",false));
                budgetPOJOS.add(new BudgetPOJO("2BHK",false));
                budgetPOJOS.add(new BudgetPOJO("3BHK",false));
                budgetPOJOS.add(new BudgetPOJO("4BHK",false));
                budgetPOJOS.add(new BudgetPOJO("7>BHK",false));
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

        property_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Dialog dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
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
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();



            }
        });
    }

    Dialog dialog;
    ArrayList<BudgetPOJO> budgetPOJOS;
    ChangedListener changedListener;
    RecyclerView rv_list;

    public void openDialog(ArrayList<BudgetPOJO> budgetPOJOArrayList) {
        budgetPOJOS = budgetPOJOArrayList;

        dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
        dialog.setContentView(R.layout.property_type_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



    public void dialogthird(ArrayList<BudgetPOJO> budgetPOJOArrayList) {
        budgetPOJOS = budgetPOJOArrayList;

        dialog = new Dialog(SalePropertiesSecondForNestOwners.this);
        dialog.setContentView(R.layout.bhk_main_layout);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SalePropertiesSecondForNestOwners.this, android.R.color.transparent)));


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
                Intent intent=new Intent(SalePropertiesSecondForNestOwners.this,SearchViewActivity.class);
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


