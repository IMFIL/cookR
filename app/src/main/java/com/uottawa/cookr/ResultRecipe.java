package com.uottawa.cookr;

import java.io.Serializable;

/**
 * Created by filipslatinac on 2016-12-04.
 */

public class ResultRecipe implements Serializable {
    private String [] ingredients;
    private  String name;
    private String serving;
    private String cookingTime;
    private String prepTime;
    private String type;
    private String cuisine;
    private String time;
    private String instructions;
    private int RecipeId;

    public ResultRecipe(String [] ingredients,
            String name, String serving,
            String cookingTime, String prepTime,
            String instructions,int id,String cuisine,
            String time, String type ){
        this.ingredients = ingredients;
        this.name = name;
        this.serving = serving;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
        RecipeId = id;
        this.cuisine = cuisine;
        this.time = time;
        this.type = type;
    }


    public String[] getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getServing() {
        return serving;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getRecipeId(){
        return RecipeId;
    }

    public String getType() {return type;}

    public String getCuisine() {return cuisine;}

    public String getTime() {return time;}
}