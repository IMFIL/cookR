package com.uottawa.cookr;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by filipslatinac on 2016-11-25.
 */

public class CategoryAdapter extends ArrayAdapter<String> {

    private String [] recipeNames;
    private final Activity context;

    public CategoryAdapter(Activity context, String [] texts) {
        super(context, R.layout.single_category_item, texts);

        recipeNames = texts;
        this.context = context;

    }


    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.single_category_item, null, true);

        Typeface fontAwesome = Typeface.createFromAsset( context.getAssets(), "fonts/fontawesome-webfont.ttf" );

        TextView txtTitle = (TextView) rowView.findViewById(R.id.CategoryName);
        TextView txtX = (TextView) rowView.findViewById(R.id.DeleteCategoryBtn);

        txtTitle.setText(recipeNames[position]);
        txtX.setTypeface(fontAwesome);

        return rowView;

    };


}
