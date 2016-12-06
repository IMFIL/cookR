package com.uottawa.cookr;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    DBhelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);


        TextView searchTxv = (TextView) findViewById(R.id.Search);
        TextView randomTxv = (TextView) findViewById(R.id.RandomRecipe);
        TextView addTxv = (TextView) findViewById(R.id.AddRecipe);
        TextView settingsTxv = (TextView) findViewById(R.id.Settings);
        TextView faveTxv = (TextView) findViewById(R.id.Favorites);
        TextView helpTxv = (TextView) findViewById(R.id.Help);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/SanFranciscoDisplay-Light.otf");

        searchTxv.setTypeface(face);
        randomTxv.setTypeface(face);
        addTxv.setTypeface(face);
        settingsTxv.setTypeface(face);
        faveTxv.setTypeface(face);
        helpTxv.setTypeface(face);

        Typeface fontAwesome = Typeface.createFromAsset( getAssets(), "fonts/fontawesome-webfont.ttf" );

        TextView searchIcon = (TextView)findViewById( R.id.searchIcon );
        TextView randomIcon = (TextView)findViewById( R.id.randomIcon );
        TextView addIcon = (TextView)findViewById( R.id.addIcon );
        TextView faveIcon = (TextView)findViewById( R.id.faveIcon );
        TextView settingsIcon = (TextView)findViewById( R.id.settingsIcon );
        TextView helpIcon = (TextView)findViewById( R.id.helpIcon );

        searchIcon.setTypeface(fontAwesome);
        randomIcon.setTypeface(fontAwesome);
        addIcon.setTypeface(fontAwesome);
        faveIcon.setTypeface(fontAwesome);
        settingsIcon.setTypeface(fontAwesome);
        helpIcon.setTypeface(fontAwesome);



    }

    public void helpClick(View view){

        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void supriseMeClick(View view){

        Intent intent = new Intent (this,SingleRecipeResult.class);
        ResultRecipe RR = dataBase.generateRandomRecipe();
        intent.putExtra("RR",RR);
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

        Intent intent = new Intent(this, AddNewRecipeV2.class);
        startActivity(intent);
    }

    public void settingsClick(View view){

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}

