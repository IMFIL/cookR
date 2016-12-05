package com.uottawa.cookr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Settings",this));

        currentActionBar.setDisplayShowCustomEnabled(true);
    }


    public void manageRecipeClick(View view){

        Intent intent = new Intent(this, ManageRecipes.class);
        startActivity(intent);
    }

    public void manageCategoriesClick(View view){

        Intent intent = new Intent(this, ManageCategories.class);
        startActivity(intent);
    }

}
