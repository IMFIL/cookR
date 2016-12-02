package com.uottawa.cookr;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView searchTxv = (TextView) findViewById(R.id.Search);
        TextView smTxv = (TextView) findViewById(R.id.SM);
        TextView addTxv = (TextView) findViewById(R.id.AddRecipe);
        TextView manageTxv = (TextView) findViewById(R.id.Manage);
        TextView faveTxv = (TextView) findViewById(R.id.Favorites);
        TextView helpTxv = (TextView) findViewById(R.id.Help);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/SanFranciscoDisplay-Light.otf");

        searchTxv.setTypeface(face);
        smTxv.setTypeface(face);
        addTxv.setTypeface(face);
        manageTxv.setTypeface(face);
        faveTxv.setTypeface(face);
        helpTxv.setTypeface(face);



    }

    public void helpClick(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void supriseMeClick(View view){

        Intent intent = new Intent(this, SupriseMe.class);
        startActivity(intent);
    }

    public void searchRecipeClick(View view){

        Intent intent = new Intent(this, SearchMain.class);
        startActivity(intent);
    }

    public void favouritesClick(View view){

        Intent intent = new Intent(this, Favorites_results.class);
        startActivity(intent);
    }



    public void addNewRecipeClick(View view){

        Intent intent = new Intent(this, addNewRecipe.class);
        startActivity(intent);
    }

}

