package com.nestowl.brokerapp;

import android.content.Context;
import android.content.SharedPreferences;


import static android.content.Context.MODE_PRIVATE;


public final class PreferenceManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;
    Context context;
    private static final String FIRST_LAUNCH = "firstenquiryappLaunch";
    int MODE = 0;
    public static final String CHECKED_REMEMBER = "RememberMe";

    public static final String PrefDB = "loginPref";

    private static final String PREFERENCE = "EnquiryPreferences";

    public PreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE, MODE);
        spEditor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        spEditor.putBoolean(FIRST_LAUNCH, isFirstTime);
        spEditor.commit();
    }

    public boolean FirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }
    public static final String PREFERENCE_NAME = "Avelo";
    Context ctx;

    private PreferenceManager() {
    }
/*
    public static void SaveLoginData(Context context, LoginResponseData responseData)
    {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        String json=new Gson().toJson(responseData);
        sp.edit().putString("LoginData",json).apply();
        Log.d("LoginData",json);
    }
    public static LoginResponseData GetLoginData(Context context)
    {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        String json=sp.getString("LoginData","");
        Log.d("LoginData",json);
        if (json.equalsIgnoreCase(""))
        {
            return null;
        }else {
            LoginResponseData responseData = new Gson().fromJson(json, LoginResponseData.class);
            return responseData;
        }
        // sp.edit().putString("LoginData",json).apply();

    }
*/

    private static SharedPreferences getPreferences(Context context) {
        if (context != null) {
            return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        }
        return null;
    }

    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defaultValue) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null && preferences.contains(key)) {
            return preferences.getString(key, defaultValue);
        }
        return defaultValue;
    }

    public static void saveString(Context context, String key, String value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            preferences.edit().putString(key, value).apply();
        }
    }

    public static boolean getBoolean(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null && preferences.contains(key)) {
            return preferences.getBoolean(key, false);
        }
        return false;
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            preferences.edit().putBoolean(key, value).apply();
        }
    }

    public static long getLong(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        try {
            return preferences.getLong(key, 0);
        } catch (Exception e) {
        }
        return 0;
    }


    public static double getdouble(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        try {
            return Double.parseDouble(preferences.getString(key, ""));
        } catch (Exception e) {
        }
        return 0;
    }


    public static void savedouble(Context context, String key, double value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            try {
                preferences.edit().putString(key, String.valueOf(value)).apply();
            } catch (Exception e) {

            }
        }
    }

    public static void saveLong(Context context, String key, long value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            try {
                preferences.edit().putLong(key, value).apply();
            } catch (Exception e) {

            }
        }
    }

    public static float getFloat(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null && preferences.contains(key)) {
            return preferences.getFloat(key, 0);
        }
        return 0;
    }

    public static void saveFloat(Context context, String key, float value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            preferences.edit().putFloat(key, value).apply();
        }
    }

    public static void deleteAllData(Context context) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            preferences.edit().clear().apply();
        }
    }

    public static void delete(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            preferences.edit().remove(key).apply();
        }
    }

    public static int getInt(Context context, String key) {
        final SharedPreferences preferences = getPreferences(context);
        try {
            return preferences.getInt(key, 0);
        } catch (Exception e) {

        }
        return 0;
    }

    public static void saveInt(Context context, String key, int value) {
        final SharedPreferences preferences = getPreferences(context);
        if (preferences != null) {
            try {
                preferences.edit().putInt(key, value).apply();
            } catch (Exception e) {

            }
        }
    }




    /*public static void saveBooleanInSP(Context _context, boolean value){
        SharedPreferences preferences = _context.getSharedPreferences("Avelo", android.content.Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ISLOGGEDIN", value);
        editor.commit();
    }


    public static boolean getBooleanFromSP(Context _context) {
// TODO Auto-generated method stub
        SharedPreferences preferences = _context.getSharedPreferences("Avelo", android.content.Context.MODE_PRIVATE);
        return preferences.getBoolean("ISLOGGEDIN", false);
    }*/


    public static boolean GetBooleanPref(Context context, String KEY) {
        SharedPreferences sp = context.getSharedPreferences(PrefDB, MODE_PRIVATE);
        return sp.getBoolean(KEY, false);
    }
}
