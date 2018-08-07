package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //---------------------------Constants---------------------------------

    private static String INFORMATIVE_MESSAGE = "There is no information available";



    //----------------------------Methods----------------------------------

    public static Sandwich parseSandwichJson(String json) {

        Sandwich ans = new Sandwich(null, null, null, null, null,null);

        /*
          Example of a Sandwich Json object:
          {
           "name": {
                    "mainName" : "Gua bao",
                    "alsoKnownAs" : ["Steamed bao" , "Pork belly bun"]
                    },
           "placeOfOrigin" : "Taiwan",
           "description" : "Gua bao is a Taiwanese snack food consisting of a slice of stewed meat
                            and other condiments sandwiched between flat steamed bread. The steamed
                            bread is typically 6-8 centimetres (2.4-3.1 in) in size, semi-circular
                            and flat in form, with a horizontal fold that, when opened, gives the
                            appearance that it has been sliced. The traditional filling for gua bao
                            is a slice of red-cooked pork belly , typically dressed with stir-fried
                            suan cai(pickled mustard greens), cilantro, and ground peanuts.",
            "image" : "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg/600px-Steamed_Sandwich%2Ctaken_by_LeoAlmighty.jpg" ,
            "ingredients" : ["Steamed bread", "Stewed meat", "Condiments"]
          }
         */

        try {

            //Main Name
            JSONObject sandwich =  new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");
            if(mainName != null && !mainName.equals(""))
                ans.setMainName(mainName);
            else
                ans.setMainName(INFORMATIVE_MESSAGE);


            //Also Known as
            List<String> alsoKnow =  new ArrayList<>();
            JSONArray conocidoComo = name.getJSONArray("alsoKnownAs");
            if(conocidoComo.length() >= 1) {
                for (int i = 0; i < conocidoComo.length(); i++) {
                    String also = conocidoComo.getString(i);
                    alsoKnow.add(also);
                }
                ans.setAlsoKnownAs(alsoKnow);
            }
            else{
                alsoKnow.add(INFORMATIVE_MESSAGE);
                ans.setAlsoKnownAs(alsoKnow);
            }


            //Place of Origin
            String placeOfOrigin  = sandwich.getString("placeOfOrigin");
            if(placeOfOrigin != null && !placeOfOrigin.equals(""))
                ans.setPlaceOfOrigin(placeOfOrigin);
            else
                ans.setPlaceOfOrigin(INFORMATIVE_MESSAGE);


            //Description
            String description = sandwich.getString("description");
            if(description != null && !description.equals(""))
                ans.setDescription(description);
            else
                ans.setDescription(INFORMATIVE_MESSAGE);


            //Image
            String image =  sandwich.getString("image");
            if(image != null && !image.equals(""))
                ans.setImage(image);
            else
                ans.setImage(INFORMATIVE_MESSAGE);


            //Ingredients
            List<String> ingredients =  new ArrayList<>();
            JSONArray ingred = sandwich.getJSONArray("ingredients");
            if(ingred.length() >= 1){
                for(int i = 0; i < ingred.length();i++){
                    String temp = ingred.getString(i);
                    ingredients.add(temp);
                }
                ans.setIngredients(ingredients);
            }
            else{
                ingredients.add(INFORMATIVE_MESSAGE);
                ans.setIngredients(ingredients);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;  
    }
}
