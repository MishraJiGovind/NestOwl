package com.nestowl.brokerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class StartAnOffer extends AppCompatActivity {
    TextView tv_offer_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_an_offer);
        tv_offer_now = findViewById(R.id.start_offer_now_id);
        tv_offer_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartAnOffer.this,StartOfferDetails.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }
/*

    public void callCurrencyvalueApi() {
        StringRequest request = new StringRequest(Request.Method.GET, url + currency, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // dialog.show();
                try {
                    Log.d("Response", response);

                    Gson gson = new Gson();
                    currencyValue.clear();
                    //   JSONObject jsonObject=new JSONObject(response);
                    //jsonObject.getJSONObject("rates");
                    CurrencyHomePOJO currencyHomePOJO = gson.fromJson(response, CurrencyHomePOJO.class);
                    tv_updated_date.setText(currencyHomePOJO.getDate());
                    tv_current_currency.setText(currencyHomePOJO.getBase());

                    currencyValue.add(currencyHomePOJO.getRates().getUSD());

                    currencyValue.add(currencyHomePOJO.getRates().getINR());
                    currencyValue.add(currencyHomePOJO.getRates().getPHP());
                    currencyValue.add(currencyHomePOJO.getRates().getTHB());
                    currencyValue.add(currencyHomePOJO.getRates().getTRY());
                    currencyValue.add(currencyHomePOJO.getRates().getSEK());
                    currencyValue.add(currencyHomePOJO.getRates().getCNY());
                    currencyValue.add(currencyHomePOJO.getRates().getPLN());
                    currencyValue.add(currencyHomePOJO.getRates().getAUD());
                    currencyValue.add(currencyHomePOJO.getRates().getRUB());
                    currencyValue.add(currencyHomePOJO.getRates().getSGD());
                    currencyValue.add(currencyHomePOJO.getRates().getDKK());
                    currencyValue.add(currencyHomePOJO.getRates().getCHF());
                    currencyValue.add(currencyHomePOJO.getRates().getMYR());
                    currencyValue.add(currencyHomePOJO.getRates().getHKD());
                    currencyValue.add(currencyHomePOJO.getRates().getEUR());
                    currencyValue.add(currencyHomePOJO.getRates().getNOK());
                    currencyValue.add(currencyHomePOJO.getRates().getMXN());
                    currencyValue.add(currencyHomePOJO.getRates().getNZD());
                    currencyValue.add(currencyHomePOJO.getRates().getZAR());
                    currencyValue.add(currencyHomePOJO.getRates().getHUF());
                    currencyValue.add(currencyHomePOJO.getRates().getHRK());
                    currencyValue.add(currencyHomePOJO.getRates().getBGN());
                    currencyValue.add(currencyHomePOJO.getRates().getKRW());
            */
/*   {"USD","INR","PHP","THB","TRY","SEK","CNY","PLN","AUD","RUB","SGD","DKK",
                            "CHF","MYR","HKD","EUR","NOK","MXN","NZD","ZAR","HUF","HRK","BGN","KRW","CAD","GBP",
                            "ILS","RON","BRL","ISK","CZK","JPY","IDR"};*//*


                    currencyValue.add(currencyHomePOJO.getRates().getCAD());
                    currencyValue.add(currencyHomePOJO.getRates().getGBP());
                    currencyValue.add(currencyHomePOJO.getRates().getILS());
                    currencyValue.add(currencyHomePOJO.getRates().getRON());
                    currencyValue.add(currencyHomePOJO.getRates().getBRL());
                    currencyValue.add(currencyHomePOJO.getRates().getISK());
                    currencyValue.add(currencyHomePOJO.getRates().getCZK());
                    currencyValue.add(currencyHomePOJO.getRates().getJPY());
                    currencyValue.add(currencyHomePOJO.getRates().getIDR());
                    Log.d("ValueListSize",String.valueOf(currencyValue.size()));
                    rv_currency_list.setAdapter(new CurrencyValueAdapter(CurrencyConverter.this, currencyValue, currencyList,flags));

                    dialog.dismiss();

                } catch (Exception e) {
                    Log.d("CurrencyException", e.toString());
                    dialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CurrencyError", error.toString());
                dialog.dismiss();
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        requestQueue.add(request);

    }*/
/*
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        ll_back = findViewById(R.id.ll_back);
        //  tv_edit = findViewById(R.id.tv_edit);
        tv_current_currency = findViewById(R.id.tv_current_currency);
        tv_updated_date = findViewById(R.id.tv_currency_updated_date);
        et_input_currency = findViewById(R.id.et_input_currency);
        et_currency = findViewById(R.id.et_currency);
        sp_currency_type = findViewById(R.id.sp_select_currencyy);
        sp_currency = findViewById(R.id.sp_currency);

        rv_currency_list = findViewById(R.id.rv_currency_rate_list);
        rv_currency_list.setHasFixedSize(true);
        rv_currency_list.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley.newRequestQueue(this);
        // getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        dialog = new ProgressDialog(this);

        dialog.setMessage("Please wait...");
        dialog.show();

        sp_currency_type.setAdapter(new CurrencySpinnerAdapter(this, currencyList,flags));
        sp_currency_type.setSelection(1);
        //  ((TextView)sp_currency_type.getSelectedView().findViewById(R.id.tv_currency)).setTextSize(15f);


        callCurrencyvalueApi();

        sp_currency_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currency = currencyList[position];
                dialog.setMessage("Please wait...");
                TextView tv_inputCurrencyType=sp_currency_type.getSelectedView().findViewById(R.id.tv_currency);
                tv_inputCurrencyType.setTextSize(16f);
                tv_inputCurrencyType.setTextColor(Color.WHITE);
                dialog.show();
                // et_input_currency.setText("");
                et_currency.setText("");
                sp_currency.setSelection(0);
                currencyValue.clear();
                callCurrencyvalueApi();
                //  currencyRate=Double.parseDouble(currencyValue.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        et_input_currency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()>0)
                {
                    sp_currency.setEnabled(true);
                    //  inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());

                    Log.d("SpinnerSize",String.valueOf(currencyList.length));
                    sp_currency.setAdapter(new SecondCurrencySpinnerAdapter(CurrencyConverter.this,currencyConvert,flags));
                    sp_currency.setSelection(selected_position);

                }else {
                    //   inputCurrencyValue=0;
                    et_currency.setText("");
                    sp_currency.setEnabled(false);
//                 sp_currency.removeAllViews();
                }
            }
        });

        sp_currency.setEnabled(false);

        sp_currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (sp_currency.getSelectedItemPosition()==0)
                    {
                        ((TextView)sp_currency.getSelectedView().findViewById(R.id.tv_currency)).setTextSize(10f);
                        selected_position=0;
                        et_currency.setText("");
                    }else if (sp_currency.getSelectedItemPosition()==1)
                    {
                        selected_position=1;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(0));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else  if (sp_currency.getSelectedItemPosition()==2)
                    {
                        selected_position=2;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(1));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else  if (sp_currency.getSelectedItemPosition()==3)
                    {
                        selected_position=3;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(2));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else  if (sp_currency.getSelectedItemPosition()==4)
                    {
                        selected_position=4;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(3));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else  if (sp_currency.getSelectedItemPosition()==5)
                    {
                        selected_position=5;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(4));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==6)
                    {
                        selected_position=6;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(5));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==7)
                    {
                        selected_position=7;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(6));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==8)
                    {
                        selected_position=8;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(7));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==9)
                    {
                        selected_position=9;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(8));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==10)
                    {
                        selected_position=10;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(9));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==11)
                    {
                        selected_position=11;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(10));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==12)
                    {
                        selected_position=12;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(11));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==13)
                    {
                        selected_position=13;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(12));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==14)
                    {
                        selected_position=14;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(13));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==15)
                    {
                        selected_position=15;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(14));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==16)
                    {
                        selected_position=16;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(15));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==17)
                    {
                        selected_position=17;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(16));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==18)
                    {
                        selected_position=18;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(17));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==19)
                    {
                        selected_position=19;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(18));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==20)
                    {
                        selected_position=20;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(19));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==21)
                    {
                        selected_position=21;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(20));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==22)
                    {
                        selected_position=22;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(21));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==23)
                    {
                        selected_position=23;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(22));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==24)
                    {
                        selected_position=24;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(23));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==25)
                    {
                        selected_position=25;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(24));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==26)
                    {
                        selected_position=26;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(25));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==27)
                    {
                        selected_position=27;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(26));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==28)
                    {
                        selected_position=28;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(27));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==29)
                    {
                        selected_position=29;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(28));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==30)
                    {
                        selected_position=30;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(29));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==31)
                    {
                        selected_position=31;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(30));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==32)
                    {
                        selected_position=32;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(31));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }else if (sp_currency.getSelectedItemPosition()==33)
                    {
                        selected_position=33;
                        inputCurrencyValue= Double.parseDouble(et_input_currency.getText().toString());
                        currencyRate=Double.parseDouble(currencyValue.get(32));
                        calculatedCurrencyValue=((int)Math.round(inputCurrencyValue *currencyRate * 1000)/(double)1000);
                        et_currency.setText(String.valueOf(calculatedCurrencyValue));
                    }

                    TextView tv_selected= sp_currency.getSelectedView().findViewById(R.id.tv_currency);
                    tv_selected.setTextColor(Color.WHITE);

                }catch (Exception e)
                {
                    Log.d("InputCurrency",e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }*//*

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_dom_inter_domestic:

                if (isChecked) {
                    rb_international.setChecked(false);
                    rb_domestic.setChecked(true);
                }
                break;
            case R.id.rb_dom_inter_international:

                if (isChecked) {
                    rb_domestic.setChecked(false);
                    rb_international.setChecked(true);
                }
                break;
        }
    }

*/


}
