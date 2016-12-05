package com.uottawa.cookr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by filipslatinac on 2016-12-03.
 */

public class CategoriesResult extends AppCompatActivity{

    String [] els = {"African","pre-meal","vegan","vegetarian"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_categories_page);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Added Categories",this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        CategoryAdapter adapter = new CategoryAdapter(this,els);


        ListView list = (ListView) findViewById(R.id.listOfCategories);
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
