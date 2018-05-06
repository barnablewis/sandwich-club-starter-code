package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<String> convertJsonArrayToStringList(JSONArray jsonArray){
        List<String> outputList = new ArrayList<String>();
        for (int i =0; i<jsonArray.length(); i++){
            try {
                outputList.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return outputList;
    }

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonSandwich = new JSONObject(json);
            JSONObject names = jsonSandwich.getJSONObject("name");
            String mainName = names.getString("mainName");

            JSONArray jsonAKAs =
                     names.getJSONArray("alsoKnownAs");

            List<String> alsoKnownAs = convertJsonArrayToStringList(jsonAKAs);
            String placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
            String description = jsonSandwich.getString("description");
            String image = jsonSandwich.getString("image");
            JSONArray jsonIngredientsArray = jsonSandwich.getJSONArray("ingredients");
            List<String> ingredients = convertJsonArrayToStringList(jsonIngredientsArray);


            Sandwich sandwich =
                    new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
