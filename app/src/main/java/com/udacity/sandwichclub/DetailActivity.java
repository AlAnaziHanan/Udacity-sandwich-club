package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    Toolbar toolbar;
    TextView aka ;
    TextView   ing;
    TextView  origin ;
    TextView  des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv=findViewById ( R.id.image_iv );
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        //System.out.println (sandwich);

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName ());

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
        //  setSupportActionBar ( toolbar );
        //ActionBar aBar = getSupportActionBar ();
        /* if(aBar !=null){
            aBar.setDisplayShowHomeEnabled(true);
            aBar.setDisplayHomeAsUpEnabled ( true );
        }
        */



       /* Picasso.with(this)
                .load(sandwich.get( position ).getAlsoKnownAs().toString ())
                .into( (Target) aka );
        Picasso.with(this)
                .load(sandwich.get( position ).getPlaceOfOrigin ())
                .into( (Target) origin );
        Picasso.with(this)
                .load(sandwich.get( position ).getIngredients ().toString ())
                .into( (Target) ing );
        Picasso.with(this)
                .load(sandwich.get( position ).getDescription ())
                .into( (Target) des );
                */

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView  aka = findViewById ( R.id.also_known_tv );
        TextView  ing = findViewById ( R.id.ingredients_tv );
        TextView  origin = findViewById ( R.id.origin_tv );
        TextView    des = findViewById ( R.id.description_tv );


        List<String>ingList;
        if (sandwich!=null){

            String desText = sandwich.getDescription ();
            if(desText.isEmpty ()){
                des.setText ( R.string.detail_error_message );
            } else{
                des.setText ( desText );
            }

            String oText = sandwich.getPlaceOfOrigin ();
            if(oText.isEmpty ()){
                origin.setText ( R.string.detail_error_message );
            } else{
                origin.setText ( oText );
            }

            List<String> akaList=sandwich.getAlsoKnownAs ();

            if(akaList.size ()==0){
                aka.setText ( R.string.detail_error_message );
            } else{
                StringBuilder akab = new StringBuilder (  );
                for (String alsoknownas:akaList){
                    akab.append ( alsoknownas ).append ( ", " );
                }
                aka.setText ( akab );
            }


            ingList=sandwich.getIngredients ();
            if(ingList.size ()==0){
                ing.setText ( R.string.detail_error_message );
            } else{
                StringBuilder ingb = new StringBuilder (  );
                for (String ingredients:ingList){
                    ingb.append ( ingredients ).append ( ", " );
                }
                aka.setText ( ingb );
            }

        }
    }
    public static String getStringfromSandwich ( Sandwich sandwich){
        return sandwich.getImage()+"\n"
                +sandwich.getMainName() +"\n"
                +sandwich.getAlsoKnownAs()+"\n"
                +sandwich.getPlaceOfOrigin()+"\n"
                +sandwich.getDescription()+"\n"
                +sandwich.getIngredients();

    }
}
