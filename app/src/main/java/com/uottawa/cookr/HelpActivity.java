package com.uottawa.cookr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Need Help?", this));

        currentActionBar.setDisplayShowCustomEnabled(true);
    }

    public void helpAddRecipe(View view) {
        Intent intent = new Intent(this, helpAddNewRecipe.class);
        startActivity(intent);
    }

    public void helpWhatFavourites(View view) {
        Intent intent = new Intent(this, helpWhatFavourites.class);
        startActivity(intent);
    }

    public void helpSearchRecipe(View view) {
        Intent intent = new Intent(this, helpSearchRecipe.class);
        startActivity(intent);
    }

    public void helpWhatSelect(View view) {
        Intent intent = new Intent(this, helpWhatSelect.class);
        startActivity(intent);
    }
}