package com.uottawa.cookr;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by filipslatinac on 2016-12-03.
 */

public class CategoriesResult extends AppCompatActivity{

    String [] els;
    DBhelper dataBase;
    Context context = this;
    deletableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_categories_page);

        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);


        final Bundle categories = getIntent().getExtras();

        els = categories.getStringArray("addedCats");

        List<String> llist = Arrays.asList(els);
        final ArrayList<String> elsList = new ArrayList<>(llist.size());
        elsList.addAll(llist);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Added Categories", this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        adapter = new deletableAdapter(this,elsList,25);

        ListView list = (ListView) findViewById(R.id.listOfCategories);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position >= categories.getInt("CuisineSize")) {
                    dataBase.deleteCategory(els[position], "Type");
                    elsList.remove(position);
                    adapter.notifyDataSetChanged();

                }

                else {
                    dataBase.deleteCategory(els[position], "Cuisine");
                    elsList.remove(position);
                    adapter.notifyDataSetChanged();
                    dataBase.close();
                }

                Toast.makeText(context,"Deleted Recipe", Toast.LENGTH_LONG).show();




            }
        });


    }

    public void onDeleteClick(View view){
//        Toast.makeText(this,"clicked this button", Toast.LENGTH_LONG).show();

    }

}
