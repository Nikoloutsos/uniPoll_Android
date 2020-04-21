package com.androiddreamer.unipoll.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JavaUtil {


    public static final String getJsonValue(JSONObject json, String key){
        try {
            return json.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }

    public static List<JSONObject> getMockPollList(){
        String mock = "{\n" +
                "    \"status\" : 1,\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"id\" : 1,\n" +
                "            \"title\" : \"What platform do you prefer?\",\n" +
                "            \"author\": \"Kotidis\",\n" +
                "            \"tags\":[\"aueb\", \"computer science\"],\n" +
                "            \"time_created\": \"2020-03-01 23:33:11\",\n" +
                "            \"time_ended\": \"2020-03-08 23:33:11\"\n" +
                "        },\n" +
                "\n" +
                "        {\n" +
                "            \"id\" : 2,\n" +
                "            \"title\" : \"Do you prefer dark or light theme\",\n" +
                "            \"author\": \"Unipoll\",\n" +
                "            \"tags\":[\"unipoll\",\"aueb\"],\n" +
                "            \"time_created\": \"2020-03-05 23:33:11\",\n" +
                "            \"time_ended\": \"2020-03-08 23:33:11\"\n" +
                "        },\n" +
                "\n" +
                "        {\n" +
                "            \"id\" : 3,\n" +
                "            \"title\" : \"Do you want a deadline extension (Distributed systems) ?\",\n" +
                "            \"author\": \"Miss Jen\",\n" +
                "            \"tags\":[\"aueb\", \"Distrubuted systems\", \"\"],\n" +
                "            \"time_created\": \"2020-04-01 23:33:11\",\n" +
                "            \"time_ended\": \"2020-04-08 23:33:11\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        try {
            JSONObject j = new JSONObject(mock);
            JSONArray data = j.getJSONArray("data");

            ArrayList<JSONObject> result = new ArrayList<>();
            for (int i = 0; i < data.length() ; i++) {
                result.add(data.getJSONObject(i));
            }
            return result;
        }catch (Exception e){
            return null;
        }


    }
}
