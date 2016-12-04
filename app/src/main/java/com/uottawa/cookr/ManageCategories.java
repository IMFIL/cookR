package com.uottawa.cookr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ManageCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_categories);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Manage Categories",this));

        currentActionBar.setDisplayShowCustomEnabled(true);
    }


    public void addTypeClick(View view){

    }

    public void addCuisineClick(View view){

    }

    public void seeAllAddedClick(View view){

        Intent intent = new Intent(this, CategoriesResult.class);
        startActivity(intent);
    }

}
