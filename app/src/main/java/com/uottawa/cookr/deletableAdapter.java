package com.uottawa.cookr;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by filipslatinac on 2016-11-25.
 */

public class deletableAdapter extends ArrayAdapter<String> {

    private ArrayList<String> recipeNames;
    private final Activity context;
    int textSize = 0;

    public deletableAdapter(Activity context, ArrayList<String> texts, int TextSize) {
        super(context, R.layout.deletable_item, texts);

        textSize = TextSize;
        recipeNames = texts;
        this.context = context;

    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.deletable_item, null, true);

        Typeface fontAwesome = Typeface.createFromAsset( context.getAssets(), "fonts/fontawesome-webfont.ttf" );

        TextView txtTitle = (TextView) rowView.findViewById(R.id.CategoryName);
        txtTitle.setTextSize(textSize);
        TextView txtX = (TextView) rowView.findViewById(R.id.DeleteCategoryBtn);
        txtX.setTextSize(textSize);

        txtTitle.setText(recipeNames.get(position));
        txtX.setTypeface(fontAwesome);

        return rowView;

    };


}
