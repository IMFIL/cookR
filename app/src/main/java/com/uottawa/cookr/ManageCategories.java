package com.uottawa.cookr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ManageCategories extends AppCompatActivity {
    DBhelper dataBase;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_categories);

        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Manage Categories", this));
        currentActionBar.setDisplayShowCustomEnabled(true);
    }


    public void addTypeClick(View view){

        TextView newType = (TextView) findViewById(R.id.newTypeEntry);
        dataBase.addToType(newType.getText().toString());
        Toast.makeText(context,"Added a type", Toast.LENGTH_LONG).show();
    }

    public void addCuisineClick(View view){
        TextView newCuisine = (TextView) findViewById(R.id.newCuisineEntry);
        dataBase.addToCuisine(newCuisine.getText().toString());
        Toast.makeText(context,"Added a cuisine", Toast.LENGTH_LONG).show();
    }

    public void seeAllAddedClick(View view) {
        Intent intent = new Intent(this, CategoriesResult.class);
        String [] cuisines = dataBase.getAllCuisines();
        String [] types = dataBase.getAllTypes();
        String [] allCat = new String [cuisines.length + types.length];

        for (int i = 0; i < cuisines.length; i++) {
            allCat[i] = cuisines[i];
        }

        for (int i = 0; i < types.length; i++){
            allCat[cuisines.length + i] = types[i];
        }

        intent.putExtra("addedCats", allCat);
        intent.putExtra("TypeSize", types.length);
        intent.putExtra("CuisineSize", cuisines.length);
        startActivity(intent);
    }
}