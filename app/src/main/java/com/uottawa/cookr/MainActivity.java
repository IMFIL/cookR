package com.uottawa.cookr;

import android.content.Intent;
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
    }

    public void helpClick(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void quickSelectClick(View view){

        Intent intent = new Intent(this, QuickSelectSearch.class);
        startActivity(intent);
    }

    public void searchRecipeClick(View view){

        Intent intent = new Intent(this, MainActivity.class);
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

