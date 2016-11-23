package com.uottawa.cookr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class main_search extends AppCompatActivity {
    final Context context = this;
    ListView list = null;
    String [] times;
    String [] cuisines;
    String [] types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        times = getResources().getStringArray(R.array.mealTime_Array);
        cuisines = getResources().getStringArray(R.array.cuisine_Array);
        types = getResources().getStringArray(R.array.type_Array);


    }


    private void populateListView(String[] els) {
        list = null;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item,R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);
    }

    public void showDialogListView(View view){
        String [] selection = {};

        switch (view.getId()){
            case R.id.cuisineButton:
                selection = cuisines;
                break;
            case R.id.mealTimeButton:
                selection = times;
                break;
            case R.id.typeButton:
                selection = types;
                break;
        }

        populateListView(selection);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setPositiveButton("Add",null);
        builder.setNegativeButton("Cancel",null);


        builder.setView(list);
        AlertDialog dialog=builder.create();
        dialog.show();

    }

}