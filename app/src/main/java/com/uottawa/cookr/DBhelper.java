package com.uottawa.cookr;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class DBhelper extends SQLiteOpenHelper {

    private static String DB_PATH="";
    private static String DB_NAME="Cookr.db";
    private SQLiteDatabase DB;
    private Context myContext = null;

    String [] StringsNeeded;

    public DBhelper(Context context) {
        super(context, DB_NAME, null, 2);

        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir +"/databases/";
        }

        else{
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        myContext = context;
        openDataBase();
        //createTables();
    }


    @Override
    public synchronized void close() {
        if (DB != null) {
            DB.close();
        }
        super.close();
    }


    private boolean checkDataBase(){
        SQLiteDatabase tmpDB = null;

        try{
            String path = DB_PATH+ DB_NAME;
            tmpDB = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        }

        catch(SQLException e){

        }

        if(tmpDB != null){
            tmpDB.close();
        }

        return tmpDB != null;
    }


    public void copyDataBase() throws IOException{
        try{
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH+DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte []  buffer = new byte [1024];
            int length;

            while ((length=myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {

        String myPath = DB_PATH + DB_NAME;
        DB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);

    }

    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();
        if(dbExist){

        }

        else{
            this.getReadableDatabase();

            try {
                copyDataBase();

            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

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



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private String generateQuery(Stack<String> stack,Searchable search){
        String query = "SELECT * FROM Recipes " +
                "JOIN Amounts ON Amounts.RecipeID=Recipes._id " +
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
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;

        String query = "SELECT * FROM Amounts JOIN Recipes ON Recipes._id = " +
                "Amounts.RecipeID JOIN Ingredients ON Ingredients.IngredientID = " +
                "Amounts.IngredientID WHERE RecipeName=?";

        ArrayList <String> ingredients = new ArrayList <String>();

        try {

            cursor = db.rawQuery(query, new String[]{recipeName});

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


            cursor = db.rawQuery("SELECT * FROM RECIPES WHERE RecipeName=?", new String[]{recipeName});

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
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Recipes SET Favourite = " + sou + " WHERE RecipeID = " +id;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        c.close();
    }

    public String [] getFavorite(){
        SQLiteDatabase db = getReadableDatabase();
        System.out.println(DB_PATH);
        File folder = new File(DB_PATH);
        File[] listOfFiles = folder.listFiles();
        System.out.println("========START HERE========");
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                System.out.println(c.getString(0));
                c.moveToNext();
            }
        }
        System.out.println("========END HERE========");
        String query = "SELECT RecipeName FROM Recipes WHERE Favourite = 1";
        c = db.rawQuery(query,null);
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
        }
        c.close();
        System.out.println(favorties);
        return  favorties;
    }

    public ResultRecipe generateRandomRecipe(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM Recipes";
        Cursor c = db.rawQuery(query,null);
        ArrayList<String> faves = new ArrayList<String>();

        if (c.getCount() == 0) return null;

        Random rand = new Random();
        int randomNum = rand.nextInt((c.getCount() - 1) + 1) + 1;
        c.close();

        c = db.rawQuery("SELECT RecipeName FROM Recipes WHERE _id = " + randomNum,null);
        c.moveToFirst();
        return getSingleResult(c.getString(c.getColumnIndex("RecipeName")));



    }

    private boolean createTables(){
        String query = "CREATE TABLE Recipes (_id INTEGER UNIQUE NOT NULL PRIMARY KEY ASC AUTOINCREMENT, RecipeName STRING NOT NULL, Instructions STRING NOT NULL, TimeOfDay STRING NOT NULL, Cuisine STRING NOT NULL, Servings INTEGER, Favourite INT NOT NULL, PreparationTime INTEGER, CookingTime INTEGER, Type STRING, userCreated INT)";
        SQLiteDatabase db = getReadableDatabase();
        //Cursor c;
        db.execSQL(query);
        return true;
    }
}