package com.uottawa.cookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class SearchMain extends AppCompatActivity {
    ListView list = null;
    SelectionItems currentList;
    DBhelper dataBase;
    SelectionItems times;
    SelectionItems cuisines;
    SelectionItems types;
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_search);

        dataBase = new DBhelper(this.getApplicationContext(), "", null, 2);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();
        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);
        currentActionBar.setCustomView(ActionBarSetter.getActionBarView("Search", this));
        currentActionBar.setDisplayShowCustomEnabled(true);

        Typeface fontAwesome = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        TextView searchButton = (TextView) findViewById(R.id.searchRecipes);

        searchButton.setTypeface(fontAwesome);

        times = new SelectionItems(getResources().getStringArray(R.array.mealTime_Array), "Meal Time");
        cuisines = new SelectionItems(dataBase.getAllCuisines(), "Cuisines");
        types = new SelectionItems(dataBase.getAllTypes(), "Type");

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void populateListView(String[] els) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item, R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        for (int i = 0; i < currentList.getArray().length; i++) {
            if (currentList.isSelected(i)) {
                list.setItemChecked(i, true);
            }
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (!currentList.isSelected(position)) {
                    currentList.select(position);
                    list.setItemChecked(position, true);
                } else {
                    currentList.unselect(position);
                    list.setItemChecked(position, false);
                }
            }

        });
    }


    public void showDialogListView(View view) {
        String[] selection = {};
        Button buttonUsed = null;

        switch (view.getId()) {
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final Button finalButtonUsed = buttonUsed;
        builder.setPositiveButton("Select",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finalButtonUsed.setText(currentList.getSelectionText());
                    }
                });
        builder.setNegativeButton("Cancel", null);
        builder.setView(list);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void searchOnClick(View view) {
        String[] result;
        EditText recipeName = (EditText) findViewById(R.id.recipeNameSearch);
        EditText ingredients = (EditText) findViewById(R.id.ingredientsSearch);

        if (recipeName.getText().toString().trim().length() == 0 &&
                ingredients.getText().toString().trim().length() == 0 &&
                cuisines.isEmpty() && types.isEmpty() && times.isEmpty()) {

            result = dataBase.getAllRecipes();
        } else {
            Searchable searchObject = new Searchable(recipeName.getText().toString(), ingredients.getText().toString(),
                    cuisines.getSelected(), types.getSelected(), times.getSelected());

            result = dataBase.getRecipes(searchObject);

            if (result[0].equals("empty")) {
                Toast.makeText(this, "Nothing found", Toast.LENGTH_LONG).show();
                flag = false;
            }


        }

        if (flag) {
            Intent intent = new Intent(this, Recipe_results.class);
            intent.putExtra("recipeNames", result);
            startActivity(intent);
        }
    }
}