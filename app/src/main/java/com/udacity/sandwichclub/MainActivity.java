package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.udacity.sandwichclub.model.Sandwich;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView aka ;
    TextView   ing;
    TextView  origin ;
    TextView  des;
    ImageView ingredientsIv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//ButterKnife.bind(this);

        int position = getIntent ().getIntExtra ( EXTRA_POSITION, DEFAULT_POSITION );


        ListView listView = findViewById(R.id.sandwiches_listview);

        final String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);

        // final String[] details = getResources().getStringArray(R.array.sandwich_details);



        //final String json = details[position];
        //  Sandwich sandwich = parseSandwichJson ( json );

   /*    if(sandwich !=null){

           ing = findViewById ( R.id.ingredients_tv );



       }
*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sandwiches);



        // Simplification: Using a ListView instead of a RecyclerView

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
               //     launchDetailActivityT ( position , view);
               // }else{
                    launchDetailActivity(position);
              //  }
               // String[] json = getResources().getStringArray(R.array.sandwich_details);
                //Toast.makeText(getApplication(), json[position], Toast.LENGTH_LONG).show();

            }
        });
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
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
