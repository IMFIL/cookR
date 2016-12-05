package com.uottawa.cookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class SearchMain extends AppCompatActivity {
    ListView list = null;

    SelectionItems currentList;

    DBhelper dataBase;

    SelectionItems times;
    SelectionItems cuisines;
    SelectionItems types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        dataBase = new DBhelper(this.getApplicationContext());

        try{
            dataBase.createDataBase();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();
        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView( ActionBarSetter.getActionBarView("Search",this));
        currentActionBar.setDisplayShowCustomEnabled(true);



        Typeface fontAwesome = Typeface.createFromAsset( getAssets(), "fonts/fontawesome-webfont.ttf" );
        TextView searchButton = (TextView) findViewById(R.id.searchRecipes);

        searchButton.setTypeface(fontAwesome);

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

    public void searchOnClick(View view){
        String [] result;
        EditText recipeName = (EditText) findViewById(R.id.recipeNameSearch);
        EditText ingredients = (EditText) findViewById(R.id.ingredientsSearch);

        if(recipeName.getText().toString().trim().length() == 0 && ingredients.getText().toString().trim().length() == 0){
            System.out.println("CANNOT DO SEARCH add dialog pop up that tells the user to add at least ingredient or recipe");
        }

        else{

            Searchable searchObject = new Searchable(recipeName.getText().toString(), ingredients.getText().toString(),
                    cuisines.getSelected(),types.getSelected(),times.getSelected());

           result = dataBase.getRecipes(searchObject);

           if (result[0].equals("empty")){
                //dialog saying that no results were found.
           }

            else{

           }

        }

    }

}