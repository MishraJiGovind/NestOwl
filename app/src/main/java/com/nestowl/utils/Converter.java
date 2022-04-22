package com.nestowl.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Converter {
    Context context;

    public Converter(Context context) {
        this.context = context;
    }
    public String getSrtingArrey(ArrayList<String> data){
        Log.e("CONVERTER BEFORE", "getSrtingArrey: "+data.toString() );
        String make = null;
        for (String s:data){
           if (make==null){
               make=s;
           }else {
               make = make+","+s;
           }
        }
//        String ss =new Gson().toJson(strings);
//        String s = ss.replace("[","");
//        return s.replace("]","");
        Log.e("CONVERTER AFTER", "getSrtingArrey: "+make );
        return make;
    }
}
