package com.uottawa.cookr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Abdulwahaab on 2016-12-05.
 */

public class DBhelper extends SQLiteOpenHelper {
    String [] StringsNeeded;
    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Cookr.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("Creating new tables");
        createRecipeTable(sqLiteDatabase);
        createAmountTable(sqLiteDatabase);
        createCuisinesTable(sqLiteDatabase);
        createIngredientsTable(sqLiteDatabase);
        createMealTypesTable(sqLiteDatabase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("Upgrade");
        sqLiteDatabase.execSQL("DROP IF EXISTS Recipes;");
        sqLiteDatabase.execSQL("DROP IF EXISTS MealTypes;");
        sqLiteDatabase.execSQL("DROP IF EXISTS Ingredients;");
        sqLiteDatabase.execSQL("DROP IF EXISTS Cuisines;");
        sqLiteDatabase.execSQL("DROP IF EXISTS Amount;");
        onCreate(sqLiteDatabase);
    }

    private void createMealTypesTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE MealTypes (TypeID INTEGER PRIMARY KEY NOT NULL, TypeName STRING NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO MealTypes (\n" +
                "                          TypeName,\n" +
                "                          TypeID\n" +
                "                      )\n" +
                "                      VALUES (\n" +
                "                          'Main Dish',\n" +
                "                          1\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Starter',\n" +
                "                          2\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Dessert',\n" +
                "                          3\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Appetizer/Snack',\n" +
                "                          4\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Drink',\n" +
                "                          5\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Sauce',\n" +
                "                          7\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'Other',\n" +
                "                          8\n" +
                "                      );\n");
    }

    private void createIngredientsTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Ingredients (IngredientID INTEGER NOT NULL UNIQUE PRIMARY KEY ASC AUTOINCREMENT, IngredientName STRING NOT NULL UNIQUE)");
        sqLiteDatabase.execSQL("INSERT INTO Ingredients (\n" +
                "                            IngredientName,\n" +
                "                            IngredientID\n" +
                "                        )\n" +
                "                        VALUES (\n" +
                "                            'Egg',\n" +
                "                            1\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Milk',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Salt',\n" +
                "                            3\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Pepper',\n" +
                "                            4\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Cooking Spray',\n" +
                "                            5\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Vegetable Oil',\n" +
                "                            6\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Parmesan Cheese',\n" +
                "                            7\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Garlic Powder',\n" +
                "                            8\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Paprika',\n" +
                "                            9\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Ground Black Pepper',\n" +
                "                            10\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Red Potatoe',\n" +
                "                            11\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Sour Cream',\n" +
                "                            12\n" +
                "                        );\n");
    }

    private void createCuisinesTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Cuisines (CuisineID INTEGER PRIMARY KEY NOT NULL, CuisineName STRING NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO Cuisines (\n" +
                "                         CuisineName,\n" +
                "                         CuisineID\n" +
                "                     )\n" +
                "                     VALUES (\n" +
                "                         'Universal',\n" +
                "                         1\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'American',\n" +
                "                         2\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Italian',\n" +
                "                         3\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Mexican',\n" +
                "                         4\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'African',\n" +
                "                         5\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Middle Eastern',\n" +
                "                         6\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Indian',\n" +
                "                         7\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Other',\n" +
                "                         8\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Asian',\n" +
                "                         9\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'Greek',\n" +
                "                         10\n" +
                "                     );\n");
    }

    private void createAmountTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Amounts (RecipeID INTEGER REFERENCES Recipes (RecipeID) ON DELETE SET NULL NOT NULL, IngredientID INTEGER NOT NULL REFERENCES Ingredients (IngredientID) ON DELETE SET NULL, Amount STRING)");
        sqLiteDatabase.execSQL("INSERT INTO Amounts (\n" +
                "                        Amount,\n" +
                "                        IngredientID,\n" +
                "                        RecipeID\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        2,\n" +
                "                        1,\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '2 tbsp',\n" +
                "                        2,\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        'Pinch',\n" +
                "                        3,\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        'Pinch',\n" +
                "                        4,\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        NULL,\n" +
                "                        5,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1 tbsp',\n" +
                "                        6,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '2 tbsp',\n" +
                "                        7,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1/2 tsp',\n" +
                "                        8,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1/2 tsp',\n" +
                "                        3,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1/2 tsp',\n" +
                "                        9,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1/4 tsp',\n" +
                "                        10,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '2 lbs',\n" +
                "                        11,\n" +
                "                        2\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        '1/4 cup',\n" +
                "                        12,\n" +
                "                        2\n" +
                "                    );\n");
    }

    private void createRecipeTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Recipes (RecipeID INTEGER UNIQUE NOT NULL PRIMARY KEY ASC AUTOINCREMENT, RecipeName STRING NOT NULL, Instructions STRING NOT NULL, TimeOfDay STRING NOT NULL, Cuisine STRING NOT NULL, Servings INTEGER, Favourite INT NOT NULL, PreparationTime INTEGER, CookingTime INTEGER, Type STRING, userCreated INT);");
        sqLiteDatabase.execSQL("INSERT INTO Recipes (\n" +
                "                        userCreated,\n" +
                "                        Type,\n" +
                "                        CookingTime,\n" +
                "                        PreparationTime,\n" +
                "                        Favourite,\n" +
                "                        Servings,\n" +
                "                        Cuisine,\n" +
                "                        TimeOfDay,\n" +
                "                        Instructions,\n" +
                "                        RecipeName,\n" +
                "                        RecipeID\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        0,\n" +
                "                        'Food',\n" +
                "                        2,\n" +
                "                        2,\n" +
                "                        1,\n" +
                "                        1,\n" +
                "                        'American',\n" +
                "                        'Breakfast',\n" +
                "                        'Whisk eggs, milk, salt and pepper in small bowl. Spray skillet with cooking spray. Heat skillet over medium-high heat until hot enough to sizzle a drop of water.\n" +
                "Pour in egg mixture and immediately reduce heat to medium-low. As eggs begin to set, gently move spatula across bottom and side of skillet to form large, soft curds.\n" +
                "Cook until eggs are thickened and no visible liquid egg remains, but they eggs are not dry.',\n" +
                "                        'Scrambled Eggs',\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        0,\n" +
                "                        'Food',\n" +
                "                        30,\n" +
                "                        15,\n" +
                "                        1,\n" +
                "                        6,\n" +
                "                        'Universal',\n" +
                "                        'Appetizer/Snack',\n" +
                "                        'Preheat oven to 400 degrees F (200 degrees C). Spray a 9x13-inch baking pan or cast iron skillet with cooking spray; pour in about 1 teaspoon vegetable oil to coat the bottom.\n" +
                "Mix Parmesan cheese, salt, garlic powder, paprika, and black pepper together in a bowl.\n" +
                "Blot the cut-side of potatoes with a paper towel to remove any moisture. Place potatoes in a bowl and drizzle with 1 tablespoon vegetable oil; toss until potatoes are lightly coated. Sprinkle potatoes with Parmesan cheese mixture; toss to coat. Arrange potatoes, cut-side down, onto the prepared baking pan.\n" +
                "Bake in the preheated oven for 15 to 20 minutes. Turn potatoes to cut-side up; continue baking until golden and crispy, about 15 to 20 more minutes. Serve with sour cream.',\n" +
                "                        'Oven Roasted Parmesan Potatoes',\n" +
                "                        2\n" +
                "                    );\n");

    }

    //make it return a resultRecipe Object

    public String [] getRecipes(Searchable search){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        String [] returnNames = null;

        try{
            if(search.getRecipeName().trim().equals("")){

                Stack<String> booleanStack = new Stack<String>();   //from here to the last push, its string parsing so we can get the proper boolean operators
                String tmpIngredient = "";
                String tmpBool = "";
                boolean ingredient = true;
                boolean operator = false;

                for(int i=0;i<search.getIngredients().length();i++){

                    if(search.getIngredients().charAt(i) == ' '){
                        if(ingredient){
                            ingredient=false;
                            operator=true;
                            booleanStack.push(tmpIngredient);
                            tmpIngredient="";
                        }
                        else{
                            ingredient=true;
                            operator=false;
                            booleanStack.push(tmpBool);
                            tmpBool="";
                        }

                        while(search.getIngredients().charAt(i) == ' '){
                            i++;
                        }
                        i--;
                    }

                    else if (ingredient){
                        tmpIngredient += search.getIngredients().charAt(i);
                    }

                    else if (operator){
                        tmpBool += search.getIngredients().charAt(i);
                    }
                }

                booleanStack.push(tmpIngredient);  //last push


                if(booleanStack.size() % 2 == 0){
                    //this is not a correct query.
                }

                cursor = db.rawQuery(generateQuery(booleanStack,search),StringsNeeded);
            }

            else{
                cursor = db.rawQuery("SELECT RecipeName FROM Recipes WHERE RecipeName=?", new String [] {search.getRecipeName()});
            }

            if(cursor==null) return new String [] {"empty"};

            ArrayList<String> names = new ArrayList<String>();
            int count = 0;

            cursor.moveToFirst();

            do{
                names.add (cursor.getString(cursor.getColumnIndex("RecipeName")));
            }

            while(cursor.moveToNext());
            returnNames = new String [names.size()];
            for(int i=0; i< names.size();i++){
                returnNames[i] = names.get(i);
            }
            cursor.close();
        }

        catch (Exception e){
            e.printStackTrace();
            return new String [] {"empty"};
        }


        db.close();
        return returnNames;
    }



    private String generateQuery(Stack<String> stack,Searchable search){
        String query = "SELECT * FROM Recipes " +
                "JOIN Amounts ON Amounts.RecipeID=Recipes.RecipeID " +
                "JOIN Ingredients ON Amounts.IngredientID = Ingredients.IngredientID " +
                "WHERE Ingredients.IngredientName LIKE ? ";

        ArrayList<String> list = new ArrayList<String>();
        int count = 0;
        while(!stack.empty()){
            String item = stack.pop().toLowerCase();

            if (count == 0){
                list.add("%"+item+"%");
            }

            if(item.equals("and")){
                query+= "AND Ingredients.IngredientName LIKE ? ";
            }

            else if(item.equals("or")){
                query+= "OR Ingredients.IngredientName LIKE ? ";
            }

            else if(item.equals("not")){
                query+= "AND NOT Ingredients.IngredientName LIKE ? ";
            }

            else{
                if(count!=0) {
                    list.add("%" + item + "%");
                }
            }
            count++;

            if(search.getCuisines().length>1){
                query+="AND Recipes.Ethnicity LIKE ? ";
                list.add("%" + search.getCuisines()[0] + "%");

                for(int i=1;i<search.getCuisines().length;i++){
                    query+="OR Recipes.Ethnicity LIKE ? ";
                    list.add("%" + search.getCuisines()[i] + "%");
                }
            }

            else if(search.getCuisines().length==1){
                query+="AND Recipes.Ethnicity LIKE ? ";
                list.add("%" +search.getCuisines()[0]+ "%");
            }

            if(search.getTypes().length>1){
                query+="AND Recipes.Type LIKE ? ";
                list.add("%"+search.getTypes()[0]+ "%");

                for(int i=1;i<search.getTypes().length;i++){
                    query+="OR Recipes.Type LIKE ?";
                    list.add("%"+search.getTypes()[i]+ "%");
                }
            }

            else if(search.getTypes().length==1){
                query+="AND Recipes.Type LIKE ? ";
                list.add("%"+search.getTypes()[0] + "%");
            }

            if(search.getTimes().length>1){
                query+="AND Recipes.TimeOfDay=? ";
                list.add(search.getTimes()[0]);

                for(int i=1;i<search.getTimes().length;i++){
                    query+="OR Recipes.TimeOfDay=?";
                    list.add(search.getTimes()[i]);
                }
            }

            else if(search.getTimes().length==1){
                query+="AND Recipes.TimeOfDay=? ";
                list.add(search.getTimes()[0]);
            }

        }
        StringsNeeded = new String [list.size()];
        for(int i=0;i<list.size();i++){
            StringsNeeded[i] = list.get(i);
        }
        return query;
    }

    public ResultRecipe getSingleResult(String recipeName){
        Cursor cursor;

        String query = "SELECT * FROM Amounts JOIN Recipes ON Recipes.RecipeID = " +
                "Amounts.RecipeID JOIN Ingredients ON Ingredients.IngredientID = " +
                "Amounts.IngredientID WHERE RecipeName=?";

        ArrayList <String> ingredients = new ArrayList <String>();

        try {

            cursor = this.getReadableDatabase().rawQuery(query, new String[]{recipeName});

            cursor.moveToFirst();

            do {
                ingredients.add(cursor.getString(cursor.getColumnIndex("IngredientName")));
            }

            while (cursor.moveToNext());

            cursor.close();

            String[] Ingredients = new String[ingredients.size()];

            for (int i = 0; i < ingredients.size(); i++) {
                Ingredients[i] = ingredients.get(i);
            }


            cursor = this.getReadableDatabase().rawQuery("SELECT * FROM RECIPES WHERE RecipeName=?", new String[]{recipeName});

            cursor.moveToFirst();

            ResultRecipe tmp;

            do {
                tmp = new ResultRecipe(Ingredients, cursor.getString(cursor.getColumnIndex("RecipeName")),
                        cursor.getString(cursor.getColumnIndex("Servings")), cursor.getString(cursor.getColumnIndex("CookingTime")),
                        cursor.getString(cursor.getColumnIndex("PreparationTime")), cursor.getString(cursor.getColumnIndex("Instructions")),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex("RecipeID"))));
            }

            while (cursor.moveToNext());

            cursor.close();
            return tmp;
        }

        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public void setUnsetFavorite(int sou,int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE Recipes SET Favourite = " + sou + " WHERE RecipeID = " +id;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        c.close();
    }

    public String [] getFavorite(){
        String query = "SELECT RecipeName FROM Recipes WHERE Favourite = 1";
        Cursor c = this.getWritableDatabase().rawQuery(query,null);
        System.out.println(c.getCount());
        ArrayList<String> faves = new ArrayList<String>();
        if (c.getCount() == 0) return new String[]{};

        c.moveToFirst();
        do{
            faves.add(c.getString(c.getColumnIndex("RecipeName")));
        }

        while(c.moveToNext());

        String [] favorties = new String [faves.size()];

        for(int i=0;i<faves.size();i++){
            favorties[i] = faves.get(i);
            System.out.println(faves.get(i));
        }
        c.close();
        System.out.println(favorties);
        return  favorties;
    }

    public ResultRecipe generateRandomRecipe(){
        String query = "SELECT * FROM Recipes";
        Cursor c = this.getReadableDatabase().rawQuery(query,null);

        if (c.getCount() == 0) return null;

        Random rand = new Random();
        int randomNum = rand.nextInt((c.getCount() - 1) + 1) + 1;
        c.close();

        c = this.getReadableDatabase().rawQuery("SELECT RecipeName FROM Recipes WHERE RecipeID = " + randomNum,null);
        c.moveToFirst();
        System.out.println(c.getString(c.getColumnIndex("RecipeName")));
        return getSingleResult(c.getString(c.getColumnIndex("RecipeName")));



    }
}