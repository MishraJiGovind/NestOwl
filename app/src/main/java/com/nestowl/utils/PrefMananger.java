package com.nestowl.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nestowl.model.EditPropertyModal;
import com.nestowl.model.LeadsPublicPro;
import com.nestowl.model.LoginPojo;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class PrefMananger {
    public static final String RESIDENTIAL_KEY = "ResidentialKEy";
    public static final String COMMERICIAL_KEY = "CommercialKey";
    public static final String OTHER_KEY = "OtherkEY";
    public static final String LOGIN_DATA = "LoginData";
    public static final String PROPERTY_ID = "PropertyId";

    public static final String MY_DATABASE = "MyDatabase";
    public static final String PROPOSAL = "PROPOSAL";
    public static final String PROPOSAL_TWO = "PROPOSAL_TWO";
    public static final String PROPOSAL_THREE = "PROPOSAL_THREE";
    public static final String PROPOSAL_ID = "INVENTOYIES";
    public static final String PROPOSAL_USER_ID = "INVENTOYIESss";
    public static final String PROPERTY = "propertysave";
    public static final String LOCALITYIES = "locals";
    public static final String PROPERTY_TO_UPDATE = "updateProperty";
    public static final String PROPERTY_TO_UPDATE_USER = "dhgdfhigihdfgdf";
    public static final String PROPERTY_TO_INCOMPLETEVERIFY = "fdfdhgdfhigihdfgdf";
    public static final String HANDLE_FEATURES_FARM = "dhgdfsdhigihdfgdf";
    public static final String RESUMEPROPERTY_ID = "fsfjsdhgdfsdhigihdfgdf";
    public static final String PROPERTIESLIST = "dffsdgfsfjsdhgdfsdhigihdfgdf";
    public static final String ARREY_LOCALTIES = "localityies";


    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_DATABASE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).apply();
    }
    public static String getString(Context context, String key) {
        return context.getSharedPreferences(MY_DATABASE, Context.MODE_PRIVATE).getString(key, null);
    }
    public static void SaveLoginData(Context context, LoginPojo loginPojo){
       if (loginPojo==null){
        saveString(context,LOGIN_DATA,"");
       }else {
           String json = new Gson().toJson(loginPojo);
           saveString(context,LOGIN_DATA,json);

       }
    }
    public static LoginPojo GetLoginData(Context context){
        String json=getString(context,LOGIN_DATA);
        if (json==null){
            return null;
        }else {
            LoginPojo loginPojo=new Gson().fromJson(json,LoginPojo.class);
            return loginPojo;
        }
    }
    public static boolean FeatureFarmSet(Context context,String id){
        boolean s = true;
        EditPropertyModal editPropertyModal  = new Gson().fromJson(PrefMananger.getString(context,HANDLE_FEATURES_FARM),EditPropertyModal.class);
        if (editPropertyModal!=null){
            if (id.equals(editPropertyModal.getId())){
            PrefMananger.saveString(context,RESIDENTIAL_KEY,editPropertyModal.getPropertyCategory());
                s=false;
            }else {
                s=true;
            }
        }
        return s;
    }
    public static void savePropertyiList(Context context,ArrayList<LeadsPublicPro> data){
        String json = new Gson().toJson(data);
        saveString(context,PROPERTIESLIST,json);
    }
    public static ArrayList<LeadsPublicPro> getPropertyList(Context context){
        String data =  getString(context,PROPERTIESLIST);
        ArrayList<LeadsPublicPro> d = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i=0;i<jsonArray.length();i++){
                LeadsPublicPro newdata = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),LeadsPublicPro.class);
                d.add(newdata);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return d;
    }
    public static void deletedata(Context context){
        context.getSharedPreferences(MY_DATABASE, 0).edit().clear().commit();
    }
}