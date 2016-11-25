package com.uottawa.cookr;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by filipslatinac on 2016-11-25.
 */

public class ImageAndNameAdapter extends ArrayAdapter<String> {

    private Integer [] imageIds;
    private String [] recipeNames;
    private final Activity context;

    public ImageAndNameAdapter(Activity context, Integer [] images, String [] texts) {
        super(context, R.layout.recipe_list_item,texts);

        imageIds = images;
        recipeNames = texts;
        this.context = context;

    }


    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.recipe_list_item, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.recipeImg);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.recipeName);

        txtTitle.setText(recipeNames[position]);
        imageView.setImageResource(imageIds[position]);
        return rowView;

    };


}
