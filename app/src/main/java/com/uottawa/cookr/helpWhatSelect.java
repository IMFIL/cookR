package com.uottawa.cookr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class helpWhatSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_what_select);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();
        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("What is random recipe?", this));
        currentActionBar.setDisplayShowCustomEnabled(true);
    }
}