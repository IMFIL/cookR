package com.uottawa.cookr;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleRecipeResultManage extends AppCompatActivity {

    DBhelper dataBase;
    int id;
    boolean isFave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recipe_result_manage);

        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);

        ResultRecipe RR = (ResultRecipe) getIntent().getSerializableExtra("RR");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();
        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView( ActionBarSetter.getActionBarView(RR.getName(),this));
        currentActionBar.setDisplayShowCustomEnabled(true);

        String [] faves = dataBase.getFavorite();


        for(int i=0;i<faves.length;i++){
            if(faves[i].equals(RR.getName())){
                TextView favoriteSelector = (TextView) findViewById(R.id.AddToFavorite);
                favoriteSelector.setText("Remove From Favorites");
                isFave = true;
            }
        }

        id = RR.getRecipeId();

        String [] ingredients = RR.getIngredients();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ingredients);

        ListView list = (ListView) findViewById(R.id.LS1);

        list.setAdapter(adapter);

        String instructions = RR.getInstructions();

        String [] steps = instructions.split("\\.");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, steps);

        ListView list2 = (ListView) findViewById(R.id.LS2);

        list2.setAdapter(adapter2);

        //Allows you to scroll the listview while you are in a scrollview layout
        list.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //Allows you to scroll the listview while you are in a scrollview layout
        list2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        TextView prepTime = (TextView) findViewById(R.id.prepTime);
        prepTime.setText(RR.getPrepTime() + "\n Prep Time");

        TextView cookTime = (TextView) findViewById(R.id.cookTime);
        cookTime.setText(RR.getPrepTime() + "\n Cook Time");

        TextView serving = (TextView) findViewById(R.id.serving);
        serving.setText(RR.getServing() + "\n Servings");

    }

    public void addToFavoriteClick(View view){
        if(isFave){
            dataBase.setUnsetFavorite(0,id);
            TextView favoriteSelector = (TextView) findViewById(R.id.AddToFavorite);
            favoriteSelector.setText("Add To Favorites");
            isFave = false;
        }
        else{
            dataBase.setUnsetFavorite(1,id);
            TextView favoriteSelector = (TextView) findViewById(R.id.AddToFavorite);
            favoriteSelector.setText("Remove From Favorites");
            isFave=true;
        }

    }

    public void deleteOnClick(View view){
        dataBase.deleteAddedRecipe(id);
        Toast.makeText(this,"You deleted this recipe", Toast.LENGTH_LONG).show();

    }

}
