package com.uottawa.cookr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class addNewRecipe extends AppCompatActivity {
    ListView list = null;

    SelectionItems currentList;
    int tmpPos = 0;
    SelectionItems cuisines;
    SelectionItems types;

    String imgDecodableString;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_recipe);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.customToolBar);
        setSupportActionBar(myToolbar);

        ActionBar currentActionBar = getSupportActionBar();

        currentActionBar.setDisplayShowHomeEnabled(false);
        currentActionBar.setDisplayShowTitleEnabled(false);

        currentActionBar.setCustomView(ActionBarSetter.getActionBarView("Add New Recipe", this));

        currentActionBar.setDisplayShowCustomEnabled(true);

        cuisines = new SelectionItems(getResources().getStringArray(R.array.cuisine_Array), "Cuisines");
        types = new SelectionItems(getResources().getStringArray(R.array.type_Array), "Type");

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void setImage(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

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
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_listview_item, R.id.txtitem, els);
        list = new ListView(this);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                currentList.unselect(tmpPos);
                tmpPos = position;
                currentList.select(tmpPos);
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
            case R.id.mealTypeButton:
                selection = types.getArray();
                currentList = types;
                buttonUsed = (Button) findViewById(R.id.mealTypeButton);
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

    public void addNewIngredients(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.add_new_recipe, null);

        // Find the ScrollView
        //ScrollView sv = (ScrollView) v.findViewById(R.id.scrollView);
        LinearLayout sv = (LinearLayout) v.findViewById(R.id.ingredientsContainer);

        // Create a LinearLayout element
        LinearLayout ll = new LinearLayout(this);
        //ll = (LinearLayout) v.findViewById(R.id.ingredientsContainer);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        // Add text

//        app:hintTextAppearance="@style/AppTheme"
//        android:layout_width="match_parent"
//        android:layout_weight="1"
//        android:textColorHint="@color/text"
//        android:layout_height="wrap_content"
//        android:paddingBottom="20dp"
//        android:layout_marginLeft="10dp"
//        android:layout_marginRight="10dp"
//        android:paddingTop="10dp"

        EditText ingredientsTxt = new EditText(this);
        android.support.design.widget.TextInputLayout ingredientsView = new android.support.design.widget.TextInputLayout(this);
        ingredientsView.addView(ingredientsTxt);
        ingredientsView.setHint("Ingredients");
        ingredientsView.setHintTextAppearance(R.color.text);
        //ingredientsView.setPadding(0,10,0,20);
        ingredientsView.setWeightSum(1);

        EditText amountsTxt = new EditText(this);
        amountsTxt.setHint("Amounts");
        //amountsTxt.setHintTextColor(R.color.text);

        android.support.design.widget.TextInputLayout amountsView = new android.support.design.widget.TextInputLayout(this);
        amountsView.addView(amountsTxt);
        amountsView.setHint("Amounts");
        amountsView.setHintTextAppearance(R.color.text);
        amountsView.setPadding(10,10,10,20);
        amountsView.setWeightSum(1);

        Button btnAdd = new Button(this);

        btnAdd.setText("+");
        //btnAdd.setBackground(R.drawable.customized_btn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewIngredients(v);
            }
        });


        ll.addView(ingredientsView);
        ll.addView(amountsView);
        ll.addView(btnAdd);

        // Add the LinearLayout element to the ScrollView
        sv.addView(ll);

        // Display the view
        setContentView(v);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("addNewRecipe Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}