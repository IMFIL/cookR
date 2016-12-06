package com.uottawa.cookr;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class AddNewRecipeV2 extends AppCompatActivity {
    ListView list = null;

    SelectionItems currentList;
    int tmpPos = 0;
    SelectionItems cuisines;
    SelectionItems types;

    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_recipe_edit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView(ActionBarSetter.getActionBarView("Add New Recipe", this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        cuisines = new SelectionItems(getResources().getStringArray(R.array.cuisine_Array), "Cuisines");
        types = new SelectionItems(getResources().getStringArray(R.array.type_Array), "Type");

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void setImage(View view) {
    // Create intent to Open Image applications like Gallery, Google Photos
    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    // Start the Intent
    startActivityForResult(galleryIntent, 1);

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imageView2);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked an image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }


    private void populateListView(String[] els) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item,R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        list.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                currentList.unselect(tmpPos);
                tmpPos = position;
                currentList.select(tmpPos);
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
            case R.id.mealTypeButton:
                selection = types.getArray();
                currentList = types;
                buttonUsed = (Button) findViewById(R.id.mealTypeButton);
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

    private void checkIfNull(String element, String name){
    }

    public void onAddClick(View view){
        String name = ((EditText) findViewById(R.id.recipeNameEntry)).getText().toString();
        String prep = ((EditText) findViewById(R.id.prepEntry)).getText().toString();
        String cook = ((EditText) findViewById(R.id.cookEntry)).getText().toString();
        String serving = ((EditText) findViewById(R.id.servingEntry)).getText().toString();
        String instructions = ((EditText) findViewById(R.id.instructionsEntry)).getText().toString();
        String ingredients = ((EditText) findViewById(R.id.ingredientsEntry)).getText().toString();



    }

}