package com.uottawa.cookr;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SingleRecipeResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_recipe_result);

        String [] els = {"1 teapsoon Salt","2 teapsoon Pepper","3 Eggs","Cooking spray","1/4 cup Milk"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, els);

        ListView list = (ListView) findViewById(R.id.LS1);

        list.setAdapter(adapter);

        String [] els2 = {"Step 1","Step 2","Step 3","Step 4","Step 5","Step 6"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, els2);

        ListView list2 = (ListView) findViewById(R.id.LS2);

        list2.setAdapter(adapter2);

        //Allows you to scroll the listview while you are in a scrollview layout
        list.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //Allows you to scroll the listview while you are in a scrollview layout
        list2.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        Typeface fontAwesome = Typeface.createFromAsset( getAssets(), "fonts/fontawesome-webfont.ttf" );
        TextView faveIcon = (TextView)findViewById( R.id.faveIcon );
        faveIcon.setTypeface(fontAwesome);

    }

}
