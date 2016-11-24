package com.uottawa.cookr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        String [] els = {"1 teapsoon Salt","2 teapsoon Pepper","3 Eggs","Cooking spray"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, els);

        ListView list = (ListView) findViewById(R.id.LS1);

        list.setAdapter(adapter);

        String [] els2 = {"Step 1","Step 2","Step 3","Step 4"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, els2);

        ListView list2 = (ListView) findViewById(R.id.LS2);

        list2.setAdapter(adapter2);
    }


}
