package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-11-24.
 */


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class QuickSelectSearch extends AppCompatActivity {
    ListView list = null;

    selection_items currentList;

    selection_items times;
    selection_items cuisines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_select_search);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Quick Select",this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        times = new selection_items(getResources().getStringArray(R.array.mealTime_Array),"Meal Time");
        cuisines = new selection_items(getResources().getStringArray(R.array.cuisine_Array),"Cuisines");


    }


    private void populateListView(String[] els) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item,R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (int i=0; i < currentList.getArray().length;i++ ){
            if(currentList.isSelected(i)){
                list.setItemChecked(i,true);
            }
        }


        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {


                    if(!currentList.isSelected(position)) {
                        list.setItemChecked(position,true);
                        currentList.select(position);
                    }

                    else{
                        list.setItemChecked(position,false);
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