package com.udacity.sandwichclub;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson ( String json ) {

        //create array of Sandwiches (return values)
        List<Sandwich> sandwiches;
        sandwiches = new ArrayList<> ();
        Sandwich sandwich=null;
        //get response data from API
        try {
            //  URL url = new URL(SANDWICH_URL);
            //  String response = getSandwichUrl(url);
            //make json object
            JSONObject jsonObject = new JSONObject ( json );
            /* String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients */

            String mainName = jsonObject.getJSONObject ("name").getString ( "mainName" );

            JSONArray akaArray = jsonObject.getJSONArray ( "alsoKnownAs" );
            List<String>  akalist = new ArrayList<> (  );

            for (int i=0;i<akaArray.length();i++){
                akalist.add ( akaArray.getString ( i) );
            }

            String placeOfOrigin = jsonObject.getString ( "placeOfOrigin" );
            String description = jsonObject.getString ( "description" );
            String image = jsonObject.getString ( "image" );

            JSONArray ingArray = jsonObject.getJSONArray ( "ingredients" );
            ArrayList<String>  ingList = new ArrayList<> (  );
            for (int i=0;i<ingArray.length();i++){
                ingList.add ( ingArray.getString ( i) );
            }


            /* String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients */
            sandwich = new Sandwich ( mainName , ingList , placeOfOrigin , description , image , akalist );
            //fill Json array with sandwiches array with all info
            return sandwich;

          /*  System.out.printf (  "name: "+mainName+"\n"+
                    "Also Known As:"+akalist.toString ()+"\n"+
                    "Place of Origin: "+placeOfOrigin+"\n"+
                    "Description: "+description+"\n"+
                    "Image "+image+"\n"+
                    "Ingredients: "+ing);
                    */

        } catch (JSONException e) {
            e.printStackTrace ();
        }

        return sandwich;

    }

}