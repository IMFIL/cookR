package com.uottawa.cookr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class DBhelper extends SQLiteOpenHelper {


    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Cookr.db", factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createRecipeTable(sqLiteDatabase);
        createCuisinesTable(sqLiteDatabase);
        createIngredientsTable(sqLiteDatabase);
        createMealTypesTable(sqLiteDatabase);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("Upgrade");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Recipes;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MealTypes;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Ingredients;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cuisines;");
        onCreate(sqLiteDatabase);
    }

    private void createMealTypesTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE MealTypes (TypeName STRING NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO MealTypes (\n" +
                "                          TypeName\n" +
                "                      )\n" +
                "                      VALUES (\n" +
                "                          'main dish'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'starter'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'dessert'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'appetizer/snack'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'drink'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'sauce'\n" +
                "                      ),\n" +
                "                      (\n" +
                "                          'other'\n" +
                "                      );\n");
    }


    private void createIngredientsTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Ingredients (RecipeID INTEGER NOT NULL, IngredientName STRING NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO Ingredients (\n" +
                "                            IngredientName,\n" +
                "                            RecipeID\n" +
                "                        )\n" +
                "                        VALUES (\n" +
                "                            'egg',\n" +
                "                            1\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'milk',\n" +
                "                            1\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'salt',\n" +
                "                            1\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'pepper',\n" +
                "                            1\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'cooking spray',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'vegetable oil',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'parmesan cheese',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'garlic powder',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'paprika',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'ground black pepper',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'red potatoe',\n" +
                "                            2\n" +
                "                        ),\n" +
                "                        (\n" +
                "                            'Sour cream',\n" +
                "                            2\n" +
                "                        );\n");
    }

    private void createCuisinesTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Cuisines (CuisineName STRING NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO Cuisines (\n" +
                "                         CuisineName\n" +
                "                     )\n" +
                "                     VALUES (\n" +
                "                         'universal'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'american'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'italian'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'mexican'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'african'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'middle Eastern'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'indian'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'other'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'asian'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         'greek'\n" +
                "                     );\n");
    }


    private void createRecipeTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE Recipes (PrimRecipeID INTEGER UNIQUE NOT NULL PRIMARY KEY ASC AUTOINCREMENT, RecipeName STRING NOT NULL, Instructions STRING NOT NULL, TimeOfDay STRING NOT NULL, Cuisine STRING NOT NULL, Servings INTEGER, Favourite INT NOT NULL, PreparationTime INTEGER, CookingTime INTEGER, Type STRING, userCreated INT);");
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
                "                        PrimRecipeID\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        0,\n" +
                "                        'main dish',\n" +
                "                        2,\n" +
                "                        2,\n" +
                "                        1,\n" +
                "                        1,\n" +
                "                        'american',\n" +
                "                        'breakfast',\n" +
                "                        'Whisk eggs, milk, salt and pepper in small bowl. Spray skillet with cooking spray. Heat skillet over medium-high heat until hot enough to sizzle a drop of water.\n" +
                "Pour in egg mixture and immediately reduce heat to medium-low. As eggs begin to set, gently move spatula across bottom and side of skillet to form large, soft curds.\n" +
                "Cook until eggs are thickened and no visible liquid egg remains, but they eggs are not dry.',\n" +
                "                        'Scrambled Eggs',\n" +
                "                        1\n" +
                "                    ),\n" +
                "                    (\n" +
                "                        0,\n" +
                "                        'main dish',\n" +
                "                        30,\n" +
                "                        15,\n" +
                "                        1,\n" +
                "                        6,\n" +
                "                        'universal',\n" +
                "                        'appetizer/snack',\n" +
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
        Cursor cursor = null;
        String [] returnNames = null;

        try {
            if (search.getRecipeName().trim().equals("")) {

                String [] allWords = search.getIngredients().toLowerCase().split(" ");
                ArrayList <String> ingredients = new ArrayList<String>();
                ArrayList <String> operators = new ArrayList<String>();
                StringBuilder tmpS = new StringBuilder();

                for(int i=0; i<allWords.length;i++){

                    switch (allWords[i].toLowerCase()){
                        case("and"):
                            ingredients.add(tmpS.toString());
                            operators.add(allWords[i]);
                            tmpS = new StringBuilder();
                            break;
                        case("or"):
                            ingredients.add(tmpS.toString());
                            operators.add(allWords[i]);
                            tmpS = new StringBuilder();
                            break;
                        case("not"):
                            ingredients.add(tmpS.toString());
                            operators.add(allWords[i]);
                            tmpS = new StringBuilder();
                            break;
                        default:
                            tmpS.append(allWords[i]);
                            tmpS.append(" ");
                    }
                }

                ingredients.add(tmpS.toString());


                cursor = db.rawQuery(generateQuery(ingredients,operators,search),null);
            }

            else {
                cursor = db.rawQuery("SELECT RecipeName FROM Recipes WHERE RecipeName LIKE ?", new String[]{"%" +search.getRecipeName()+"%"});
            }

            if (cursor == null) return new String [] {"empty"};

            ArrayList<String> names = new ArrayList<String>();
            int count = 0;

            cursor.moveToFirst();

            do {
                names.add (cursor.getString(cursor.getColumnIndex("RecipeName")));
            }

            while(cursor.moveToNext());
            returnNames = new String [names.size()];
            for (int i = 0; i < names.size(); i++){
                returnNames[i] = names.get(i);
            }
            cursor.close();
        }

        catch (Exception e){
            e.printStackTrace();
            return new String [] {"empty"};
        }

        finally {
            cursor.close();
        }

        return returnNames;
    }

    private String generateQuery(ArrayList ings,ArrayList ops, Searchable search){
        StringBuilder query = new StringBuilder();

        query.append("SELECT PrimRecipeID,RecipeName,Instructions,TimeOFDay,Cuisine,Servings,Favourite,PreparationTime,CookingTime,Type,userCreated\n" +
                "FROM(    \n" +
                "    select RecipeID, group_concat(IngredientName, ' ') as IngName\n" +
                "    from Ingredients \n" +
                "    group by RecipeID)\n" +
                "    \n" +
                "join Recipes \n" +
                "ON Recipes.PrimRecipeID = RecipeID WHERE");


        int counter = 0;
        if (ings.size() == 1){
            query.append(" IngName LIKE '%"+ings.get(0).toString().trim()+"%'");
        }

        else{
            for (int i = 0; i < ings.size(); i++) {
                query.append(" IngName LIKE '%" + ings.get(i).toString().trim() + "%' " + getOperator(ings.size(),i,ops));
            }
        }

        if (search.getCuisines().length == 0){

        }

        else{
            query.append("AND Recipes.Cuisine IN ('");
            for(int i=0;i<search.getCuisines().length;i++){
                if (i == search.getCuisines().length-1){
                    query.append(search.getCuisines()[i] + "')");
                }
                else{
                    query.append(search.getCuisines()[i].toLowerCase() + "', '");
                }
            }
        }

        if (search.getTypes().length == 0){

        }

        else{
            query.append("AND Recipes.Type IN ('");
            for(int i=0;i<search.getTypes().length;i++){
                if (i == search.getTypes().length-1){
                    query.append(search.getTypes()[i] + "')");
                }
                else{
                    query.append(search.getTypes()[i].toLowerCase() + "', '");
                }
            }
        }

        if (search.getTimes().length == 0){

        }

        else{
            query.append("AND Recipes.TimeOfDay IN ('");
            for(int i=0;i<search.getTimes().length;i++){
                if (i == search.getTimes().length-1){
                    query.append(search.getTimes()[i] + "')");
                }
                else{
                    query.append(search.getTimes()[i].toLowerCase() + "', '");
                }
            }
        }
        return query.toString();
    }

    private String getOperator(int size,int index,ArrayList operators){

        if(index == size-1){
            return "";
        }
        else{
            if (operators.get(index).toString().trim().equals("not")){
                return ("AND NOT");
            }

            return operators.get(index).toString().trim().toUpperCase();
        }
    }

    public ResultRecipe getSingleResult(String recipeName){
        Cursor cursorING = null;
        Cursor cursorREC = null;
        int recipeID = 0;
        ResultRecipe tmp;


        String recipeQuery = "SELECT * FROM Recipes WHERE RecipeName=?";
        String ingredientsQuery ="SELECT IngredientName FROM Ingredients WHERE RecipeID=?";

        ArrayList <String> ingredients = new ArrayList <String>();

        try {
            cursorREC = this.getReadableDatabase().rawQuery(recipeQuery, new String[]{recipeName});
            cursorREC.moveToFirst();

            recipeID = Integer.parseInt(cursorREC.getString(cursorREC.getColumnIndex("PrimRecipeID")));
            Log.d("ERROR",String.valueOf(recipeID));

            cursorING = this.getReadableDatabase().rawQuery(ingredientsQuery,  new String[] {String.valueOf(recipeID)});
            cursorING.moveToFirst();

            do {
                ingredients.add(cursorING.getString(cursorING.getColumnIndex("IngredientName")));
            }

            while (cursorING.moveToNext());

            cursorING.close();

            String[] Ingredients = new String[ingredients.size()];

            for (int i = 0; i < ingredients.size(); i++) {
                Ingredients[i] = ingredients.get(i);
            }

            do {
                tmp = new ResultRecipe(Ingredients, cursorREC.getString(cursorREC.getColumnIndex("RecipeName")),
                        cursorREC.getString(cursorREC.getColumnIndex("Servings")), cursorREC.getString(cursorREC.getColumnIndex("CookingTime")),
                        cursorREC.getString(cursorREC.getColumnIndex("PreparationTime")), cursorREC.getString(cursorREC.getColumnIndex("Instructions")),
                        Integer.parseInt(cursorREC.getString(cursorREC.getColumnIndex("PrimRecipeID"))));
            }

            while (cursorREC.moveToNext());

            cursorREC.close();
            return tmp;
        }

        catch (Exception e){
            e.printStackTrace();
            return new ResultRecipe(new String [] {}, "", "", "", "", "", 0);
        }

        finally {
            cursorING.close();
            cursorREC.close();
        }

    }

    public void setUnsetFavorite(int sou, int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE Recipes SET Favourite = " + sou + " WHERE PrimRecipeID = " +id;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        c.close();
        db.close();
    }

    public String [] getFavorite(){
        String query = "SELECT RecipeName FROM Recipes WHERE Favourite = 1";
        Cursor c = this.getWritableDatabase().rawQuery(query, null);
        ArrayList<String> faves = new ArrayList<String>();
        if (c.getCount() == 0) return new String[]{};

        c.moveToFirst();
        do {
            faves.add(c.getString(c.getColumnIndex("RecipeName")));
        }

        while (c.moveToNext());

        String [] favorties = new String [faves.size()];

        for (int i = 0; i < faves.size(); i++) {
            favorties[i] = faves.get(i);
        }
        c.close();
        return favorties;
    }

    public ResultRecipe generateRandomRecipe() {
        String query = "SELECT RecipeName FROM Recipes";
        Cursor c = this.getReadableDatabase().rawQuery(query,null);
        ArrayList <String> names = new ArrayList<String>();
        c.moveToFirst();
        do{
            names.add(c.getString(c.getColumnIndex("RecipeName")));
        }

        while(c.moveToNext());

        Random rand  = new Random();
        int random = rand.nextInt(names.size()) + 1;
        c.close();
        return getSingleResult(names.get(random-1));
    }

    public String addRecipe(Addable toAdd) {
        ContentValues values = new ContentValues();

        values.put("RecipeName", toAdd.getName());
        values.put("Instructions", toAdd.getInstructions());
        values.put("TimeOfDay", toAdd.getTime());
        values.put("Cuisine", toAdd.getCuisine());
        values.put("Servings", toAdd.getServing());
        values.put("Favourite", 1);
        values.put("PreparationTime", toAdd.getPrepTime());
        values.put("CookingTime", toAdd.getCookingTime());
        values.put("Type", toAdd.getType());
        values.put("userCreated", 1);

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert("Recipes", null, values);
            db.close();
        }

        catch(Exception e){
            e.printStackTrace();
            return "Error";
        }



        String newRecipeID = "SELECT PRIMRecipeID WHERE RecipeName= " +toAdd.getName()+";";
        Cursor cursor = this.getReadableDatabase().rawQuery(newRecipeID,null);
        cursor.moveToFirst();
        int idOfRecipe = 0;
        try {

            do{
                idOfRecipe = Integer.parseInt(cursor.getString(cursor.getColumnIndex("PRIMRecipeID")));
            }
            while(cursor.moveToNext());
        }

        catch(Exception e){
            e.printStackTrace();
            return "Error";
        }

        finally {
            cursor.close();
        }

        SQLiteDatabase WDB = this.getWritableDatabase();

        for(int i=0;i<toAdd.getIngredients().length;i++){
            values = new ContentValues();
            values.put("RecipeID",idOfRecipe);
            values.put("IngredientName",toAdd.getIngredients()[i]);
            try {
                WDB.insert("Ingredients", null, values);
            }

            catch(Exception e){
                e.printStackTrace();
                return "Error";
            }
        }
        WDB.close();
        return "Success";
    }

    public String [] getAdded() {
        String query = "SELECT RecipeName FROM Recipes WHERE userCreated = 1";
        Cursor c = this.getWritableDatabase().rawQuery(query, null);
        ArrayList<String> faves = new ArrayList<String>();
        if (c.getCount() == 0) return new String[]{};

        c.moveToFirst();
        do {
            faves.add(c.getString(c.getColumnIndex("RecipeName")));
        }

        while (c.moveToNext());

        String [] favorties = new String [faves.size()];

        for (int i = 0; i < faves.size(); i++) {
            favorties[i] = faves.get(i);
        }
        c.close();
        return favorties;
    }

    public void deleteAddedRecipe(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Recipes", "PrimRecipeID" + "=" +id , null);
        db.execSQL("DELETE FROM Ingredients WHERE RecipeID = " + id);
        db.close();
    }

    public String [] getAllCuisines() {
        String query = "SELECT CuisineName FROM Cuisines";
        Cursor cursor = this.getReadableDatabase().rawQuery(query,null);
        ArrayList<String> cuisines = new ArrayList<String>();
        if(cursor.getCount() == 0) return new String [] {};

        cursor.moveToFirst();

        do {
            cuisines.add((cursor.getString(cursor.getColumnIndex("CuisineName"))));
        }

        while(cursor.moveToNext());

        cursor.close();

        String [] cuisinesReturned = new String [cuisines.size()];

        for (int i = 0; i < cuisines.size(); i++) {
            cuisinesReturned[i] = cuisines.get(i);
        }

        return cuisinesReturned;
    }

    public String [] getAllTypes() {
        String query = "SELECT TypeName FROM MealTypes";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        ArrayList<String> types = new ArrayList<String>();

        if(cursor.getCount() == 0) return new String [] {};
        cursor.moveToFirst();

        do {
            types.add((cursor.getString(cursor.getColumnIndex("TypeName"))));
        }

        while(cursor.moveToNext());

        String [] typesReturned = new String [types.size()];

        for (int i = 0; i < types.size(); i++) {
            typesReturned[i] = types.get(i);
        }
        cursor.close();
        return typesReturned;
    }

    public void deleteCategory(String name, String place){
        String whereToDelete = "";
        if (place.equals("Cuisine")) {
            whereToDelete = "Cuisines";
        }
        else {
            whereToDelete = "MealTypes";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(whereToDelete, place+"Name" + " = " + "'" +name+ "'" , null);
        db.close();
    }

    public void addToCuisine(String name) {
        ContentValues values = new ContentValues();
        values.put("CuisineName", name);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Cuisines", null, values);
        db.close();
    }

    public void addToType(String name) {
        ContentValues values = new ContentValues();
        values.put("TypeName", name);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("MealTypes", null, values);
        db.close();

    }

    public void editRecipe(Addable edit, String previousName) {
        ContentValues values = new ContentValues();

        values.put("RecipeName", edit.getName());
        values.put("Instructions", edit.getInstructions());
        values.put("TimeOfDay", edit.getTime());
        values.put("Cuisine", edit.getCuisine());
        values.put("Servings", edit.getServing());
        values.put("PreparationTime", edit.getPrepTime());
        values.put("CookingTime", edit.getCookingTime());
        values.put("Type", edit.getType());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update("Recipes", values, "RecipeName= "+ "'" + edit.getName() + "'", null);
        db.close();
    }

    public String [] getAllRecipes() {
        ArrayList<String> recipes = new ArrayList<String>();
        String query = "SELECT RecipeName FROM Recipes";
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();

        do {
            recipes.add((cursor.getString(cursor.getColumnIndex("RecipeName"))));
        }

        while (cursor.moveToNext());

        String [] rec = new String [recipes.size()];

        for (int i = 0; i < recipes.size(); i++) {
            rec[i] = recipes.get(i);
        }

        cursor.close();
        return rec;
    }

}