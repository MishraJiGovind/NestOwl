package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nestowl.Fragment.AmentiesFeatures;

import java.util.ArrayList;

public class NearbyPlaces extends AppCompatActivity {
    ImageView one,two,three,four,five,six,saven,eight;
    TextView bus_stop_distance;
    TextView office_comp_dstn,shopping_mall_distance,college_dstnc,market_dstnc,playground_dstnc,gym_dstnc,police_station;
    CardView cardView;
    EditText edt_bus_stop,edt_shopping_mall,edt_office_complex,edt_college,edt_market,edt_playground,edt_gym,edt_police_station;
    ArrayList<String> name,km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places);
        name=new ArrayList<>();
        km=new ArrayList<>();
        edt_bus_stop=findViewById(R.id.edt_bus_stop);
        edt_shopping_mall=findViewById(R.id.edt_shopping_mall);
        edt_office_complex=findViewById(R.id.edt_bus_stop);
        edt_college=findViewById(R.id.edt_bus_stop);
        edt_market=findViewById(R.id.edt_bus_stop);
        edt_playground=findViewById(R.id.edt_bus_stop);
        edt_gym=findViewById(R.id.edt_bus_stop);
        edt_police_station=findViewById(R.id.edt_bus_stop);

        one=findViewById(R.id.property_on_floor_image);
        two=findViewById(R.id.property_shopping_mall);
        three=findViewById(R.id.property_office_complex);
        four=findViewById(R.id.property_name_of_college);
        five=findViewById(R.id.property_of_market);
        six=findViewById(R.id.property_playground);
        college_dstnc=findViewById(R.id.college_id);
        office_comp_dstn=findViewById(R.id.office_complex);
        bus_stop_distance=findViewById(R.id.distance);
        saven=findViewById(R.id.property_name_of_gym);
        shopping_mall_distance=findViewById(R.id.shopping_mall);
        playground_dstnc=findViewById(R.id.playground_id);
        eight=findViewById(R.id.property_of_police_station);
        market_dstnc=findViewById(R.id.market_id);
        gym_dstnc=findViewById(R.id.gym_id);
        cardView =findViewById(R.id.card_submit);
        police_station=findViewById(R.id.police_station);

        edt_bus_stop.setText(AmentiesFeatures.bus_stop);
        bus_stop_distance.setText(AmentiesFeatures.bus_stop_distance);

        edt_shopping_mall.setText(AmentiesFeatures.shopping_mall);
        shopping_mall_distance.setText(AmentiesFeatures.shopping_mall_distance);

        edt_office_complex.setText(AmentiesFeatures.office_complex);
        office_comp_dstn.setText(AmentiesFeatures.office_complex_distance);

        edt_college.setText(AmentiesFeatures.college);
        college_dstnc.setText(AmentiesFeatures.college_distance);

        edt_market.setText(AmentiesFeatures.market);
        market_dstnc.setText(AmentiesFeatures.market_distance);

        edt_playground.setText(AmentiesFeatures.playground_park);
        playground_dstnc.setText(AmentiesFeatures.playground_distance);


        edt_gym.setText(AmentiesFeatures.gym);
        gym_dstnc.setText(AmentiesFeatures.gym_distance);

        edt_police_station.setText(AmentiesFeatures.police_station);
        police_station.setText(AmentiesFeatures.police_station_distance);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.add(0,edt_bus_stop.getText().toString());
                km.add(0,bus_stop_distance.getText().toString());

                name.add(1,edt_shopping_mall.getText().toString());
                km.add(1,shopping_mall_distance.getText().toString());

                name.add(2,edt_office_complex.getText().toString());
                km.add(2,office_comp_dstn.getText().toString());

                name.add(3,edt_college.getText().toString());
                km.add(3,college_dstnc.getText().toString());

                name.add(4,edt_market.getText().toString());
                km.add(4,market_dstnc.getText().toString());

                name.add(5,edt_playground.getText().toString());
                km.add(5,playground_dstnc.getText().toString());

                name.add(6,edt_gym.getText().toString());
                km.add(6,gym_dstnc.getText().toString());

                name.add(7,edt_police_station.getText().toString());
                km.add(7,police_station.getText().toString());

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",name);
                returnIntent.putExtra("result0",km);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));

                dialog.getWindow().setAttributes(lp);
                dialog.show();
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            office_comp_dstn.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            office_comp_dstn.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            office_comp_dstn.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            office_comp_dstn.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            office_comp_dstn.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            college_dstnc.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            college_dstnc.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            college_dstnc.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            college_dstnc.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            college_dstnc.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });


                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            market_dstnc.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            market_dstnc.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            market_dstnc.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            market_dstnc.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            market_dstnc.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));

                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            playground_dstnc.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            playground_dstnc.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            playground_dstnc.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            playground_dstnc.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            playground_dstnc.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        saven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            gym_dstnc.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            gym_dstnc.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            gym_dstnc.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            gym_dstnc.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            gym_dstnc.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));

                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            police_station.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            police_station.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            police_station.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            police_station.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            police_station.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });



                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);
                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            shopping_mall_distance.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            shopping_mall_distance.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            shopping_mall_distance.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            shopping_mall_distance.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            shopping_mall_distance.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(NearbyPlaces.this);
                dialog.setContentView(R.layout.metro_popup);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(NearbyPlaces.this, android.R.color.transparent)));


                dialog.getWindow().setAttributes(lp);
                dialog.show();
                ImageView img=dialog.findViewById(R.id.iv_29);
                final RadioButton half=dialog.findViewById(R.id.one_half_id_km);

                final RadioButton one=dialog.findViewById(R.id.one_id_km);
                final RadioButton two=dialog.findViewById(R.id.two_id_km);
                final RadioButton three=dialog.findViewById(R.id.three_id_km);
                final RadioButton more_than=dialog.findViewById(R.id.more_than_id_km);
                CardView cardView=dialog.findViewById(R.id.card_submit_id);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (half.isChecked()) {
                            bus_stop_distance.setText(half.getText().toString());
                        }
                        if (one.isChecked()) {
                            bus_stop_distance.setText(one.getText().toString());
                        }
                        if (two.isChecked()) {
                            bus_stop_distance.setText(two.getText().toString());
                        }
                        if (three.isChecked()) {
                            bus_stop_distance.setText(three.getText().toString());
                        }
                        if (more_than.isChecked()) {
                            bus_stop_distance.setText(more_than.getText().toString());
                        }
                        dialog.dismiss();
                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));}
    }
}
