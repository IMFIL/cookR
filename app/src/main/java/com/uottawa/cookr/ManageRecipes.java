package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-11-24.
 */


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ManageRecipes extends AppCompatActivity {

    ListView list = null;

    String [] recipename = {"All Dressed Burger","Sizzling Hot-Dog","Classic Cheeseburger"};

    Integer [] images = {R.drawable.alldressedburger,R.drawable.hotdog, R.drawable.cheeseburger};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_recipes);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Added Recipes",this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        RecipeDisplayAdapter adapter = new RecipeDisplayAdapter(this,images,recipename);

        list = (ListView)findViewById(R.id.listOfRecipesAdded);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("hehe");
            }
        });


    }

}