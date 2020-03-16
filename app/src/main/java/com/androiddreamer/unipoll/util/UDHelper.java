package com.androiddreamer.unipoll.util;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Responsible for saving and retrieving user data from phone
 */
public class UDHelper {

    // key unique identifiers
    public static final String KEY_EMAIL = "key_email";
    public static final String KEY_ECLASS_USERNAME = "key_eclass_username";
    public static final String KEY_ECLASS_PASS = "key_eclass_pass";
    public static final String KEY_STUDENT_ID = "key_student_id";

    private Context context;
    private SharedPreferences pref;


    /**
     *
     * @param context used for accessing sharedPreferences
     */
    public UDHelper(Context context) {
        this.context = context;
        final String APP_KEY = context.getPackageName().replaceAll("\\.", "_").toLowerCase();
        pref = context.getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
    }


    /**
     *
     * @param keyIdentifier
     * @param value the new value to be stored
     */
    public void setString(String keyIdentifier, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(keyIdentifier, value);
        editor.apply();
    }

    /**
     *
     * @param keyIdentifier
     * @param value the new value to be stored
     */
    public void setInt(String keyIdentifier, int value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(keyIdentifier, value);
        editor.apply();
    }


    public String getString(String keyIdentifier){
        return pref.getString(keyIdentifier, "");
    }


    public int getInt(String keyIdentifier){
        return pref.getInt(keyIdentifier, Integer.MAX_VALUE);
    }




}
