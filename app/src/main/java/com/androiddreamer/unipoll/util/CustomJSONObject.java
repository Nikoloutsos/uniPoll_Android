package com.androiddreamer.unipoll.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A child of {@link JSONObject} that
 * mimics Alamofire library behaviour for iOS
 */
public class CustomJSONObject extends JSONObject {

    @Override
    public String getString(String name){
        try{
            String value = super.getString(name);
            return value;
        }catch (Exception e){
            return "";
        }
    }

    @Override
    public int getInt(String name){
        try{
            int value = super.getInt(name);
            return value;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public JSONArray getJSONArray(String name){
        try{
            JSONArray value = super.getJSONArray(name);
            return value;
        }catch (Exception e){
            return new JSONArray();
        }
    }

    @Override
    public JSONObject getJSONObject(String name){
        try{
            JSONObject value = super.getJSONObject(name);
            return value;
        }catch (Exception e){
            return new JSONObject();
        }
    }
}
