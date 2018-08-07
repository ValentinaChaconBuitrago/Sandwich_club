package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;


    //-----------------------------Views-----------------------------

    private ImageView sandwichPictureIv;
    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private TextView akaTv;


    //---------------------------Attributes--------------------------

    private static Sandwich sandwich;


    //----------------------------Methods----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        sandwichPictureIv = (ImageView) findViewById(R.id.image_iv);
        originTv = (TextView)  findViewById(R.id.origin_tv);
        descriptionTv = (TextView) findViewById(R.id.description_tv);
        ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        akaTv = (TextView) findViewById(R.id.also_known_tv);


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

        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(sandwichPictureIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        originTv.setText(sandwich.getPlaceOfOrigin());
        descriptionTv.setText(sandwich.getDescription());
        for(String ingredient : sandwich.getIngredients()){

            //Is there another way to create a bullet point in the list?
            ingredientsTv.append("- "+ingredient + "\n");
        }
        for(String name : sandwich.getAlsoKnownAs()){
            akaTv.append("- "+name + "\n");
        }
    }
}
