package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-12-04.
 */

public class ResultRecipe {
    String [] ingredients;
    String name;
    String serving;
    String cookingTime;
    String prepTime;
    String instructions;

    public ResultRecipe(String [] ingredients,
            String name, String serving,
            String cookingTime, String prepTime,
            String instructions, String cuisine){

        this.ingredients = ingredients;
        this.name = name;
        this.serving = serving;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
    }

}
