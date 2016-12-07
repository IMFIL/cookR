package com.uottawa.cookr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class helpWhatSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_settings);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("What are settings?",this));

        currentActionBar.setDisplayShowCustomEnabled(true);
    }
}
