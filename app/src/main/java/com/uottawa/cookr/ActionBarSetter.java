package com.uottawa.cookr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by filipslatinac on 2016-11-25.
 */

public class ActionBarSetter {
    private static String text;
    private static Context context = null;


    public static View getActionBarView(String t,Context c){
        text = t;
        context = c;

        LayoutInflater inflater = LayoutInflater.from(context);

        View actionView = inflater.inflate(R.layout.activity_bar, null);
        TextView textOfActionBar = (TextView) actionView.findViewById(R.id.titleOfActionBar);
        textOfActionBar.setText(text);

        return actionView;

    }



}