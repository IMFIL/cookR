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

    String [] recipename = {"burger","hotdog","pizza"};

    Integer [] images = {R.drawable.burger,R.drawable.hotdog,R.drawable.pizza};

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

        ImageAndNameAdapter adapter = new ImageAndNameAdapter(this,images,recipename);

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