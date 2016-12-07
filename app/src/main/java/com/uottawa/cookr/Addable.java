package com.uottawa.cookr;

/**
 * Created by filipslatinac on 2016-12-06.
 */

public class Addable {
    private String [] ingredients;
    private  String name;
    private String serving;
    private String cookingTime;
    private String prepTime;
    private String instructions;
    private String cuisine;
    private String type;
    private String time;

    public Addable(String [] ing, String name, String serving) {

    }

    public Addable(String[] ingredients, String name, String serving, String cookingTime,
                   String prepTime, String instructions, String cuisine, String type, String time) {
        this.ingredients = ingredients;
        this.name = name;
        this.serving = serving;
        this.cookingTime = cookingTime;
        this.prepTime = prepTime;
        this.instructions = instructions;
        this.cuisine = cuisine;
        this.type = type;
        this.time = time;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public int getServing() {
        return Integer.parseInt(serving);
    }

    public int getCookingTime() {
        return Integer.parseInt(cookingTime);

    }

    public int getPrepTime() {
        return Integer.parseInt(prepTime);
    }

    public String getInstructions() {
        return instructions;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }
}
