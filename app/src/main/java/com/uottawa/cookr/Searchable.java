package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-12-04.
 */

public class Searchable {
    String recipeName;
    String ingredients;

    String [] cuisines;
    String [] types;
    String [] times;

    public Searchable (String name, String ing, String [] cuis, String [] ty, String ti []){
        recipeName = name;
        ingredients = ing;

        cuisines = cuis;
        types = ty;
        times = ti;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String[] getCuisines() {
        return cuisines;
    }

    public String[] getTypes() {
        return types;
    }

    public String[] getTimes() {
        return times;
    }
}
