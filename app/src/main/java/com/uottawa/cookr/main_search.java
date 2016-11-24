package com.uottawa.cookr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class main_search extends AppCompatActivity {
    ListView list = null;

    selection_items currentList;

    selection_items times;
    selection_items cuisines;
    selection_items types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        times = new selection_items(getResources().getStringArray(R.array.mealTime_Array),"Meal Time");
        cuisines = new selection_items(getResources().getStringArray(R.array.cuisine_Array),"Cuisines");
        types = new selection_items(getResources().getStringArray(R.array.type_Array),"Type");


    }


    private void populateListView(String[] els) {
        list = null;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item,R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);


        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {

                ViewGroup vg=(ViewGroup)view;
                TextView txt=(TextView)vg.findViewById(R.id.txtitem);

                if(!currentList.isSelected(position)) {
                    txt.setBackgroundResource(R.color.redPastel);
                    currentList.select(position);
                }

                else{
                    txt.setBackgroundResource(R.color.white);
                    currentList.unselect(position);
                }

            }

        });

    }

    public void showDialogListView(View view){
        String [] selection = {};
        Button buttonUsed = null;

        switch (view.getId()){
            case R.id.cuisineButton:
                selection = cuisines.getArray();
                currentList = cuisines;
                buttonUsed = (Button) findViewById(R.id.cuisineButton);
                break;
            case R.id.mealTimeButton:
                selection = times.getArray();
                currentList = times;
                buttonUsed = (Button) findViewById(R.id.mealTimeButton);
                break;
            case R.id.typeButton:
                selection = types.getArray();
                currentList = types;
                buttonUsed = (Button) findViewById(R.id.typeButton);
                break;
        }

        populateListView(selection);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final Button finalButtonUsed = buttonUsed;
        builder.setPositiveButton("Select",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finalButtonUsed.setText(currentList.getSelectionText());
                    }
            });
        builder.setNegativeButton("Cancel",null);


        builder.setView(list);
        AlertDialog dialog=builder.create();
        dialog.show();

    }

}