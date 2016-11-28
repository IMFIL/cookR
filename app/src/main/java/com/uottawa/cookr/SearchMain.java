package com.uottawa.cookr;

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

public class SearchMain extends AppCompatActivity {
    ListView list = null;

    SelectionItems currentList;

    SelectionItems times;
    SelectionItems cuisines;
    SelectionItems types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);



        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Search",this));

        currentActionBar.setDisplayShowCustomEnabled(true);




        times = new SelectionItems(getResources().getStringArray(R.array.mealTime_Array),"Meal Time");
        cuisines = new SelectionItems(getResources().getStringArray(R.array.cuisine_Array),"Cuisines");
        types = new SelectionItems(getResources().getStringArray(R.array.type_Array),"Type");


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
                    currentList.select(position);
                    list.setItemChecked(position,true);
                }

                else{
                    currentList.unselect(position);
                    list.setItemChecked(position,false);
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