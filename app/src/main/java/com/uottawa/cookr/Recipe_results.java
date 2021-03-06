package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-11-24.
 */



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Recipe_results extends AppCompatActivity {

    DBhelper dataBase;
    ListView list = null;
    String [] recipename;
    Integer [] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        final Context context = this;

        Bundle names = getIntent().getExtras();
        recipename = names.getStringArray("recipeNames");
        images = new Integer[recipename.length];

        for (int i = 0;i < recipename.length; i++) {
            images[i] = R.drawable.chef;
        }

        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Recipes", this));
        currentActionBar.setDisplayShowCustomEnabled(true);

        RecipeDisplayAdapter adapter = new RecipeDisplayAdapter(this,images,recipename);

        list = (ListView)findViewById(R.id.listOfRecipes);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent (context, SingleRecipeResult.class);
                ResultRecipe RR = dataBase.getSingleResult(recipename[position]);
                dataBase.close();
                intent.putExtra("RR", RR);
                startActivity(intent);
            }
        });
    }
}