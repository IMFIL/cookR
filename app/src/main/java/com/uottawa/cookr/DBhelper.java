package com.uottawa.cookr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Stack;


public class DBhelper extends SQLiteOpenHelper {

    private static String DB_PATH="";
    private static String DB_NAME="Cookr.db";
    private SQLiteDatabase DB;
    private Context myContext = null;

    String [] StringsNeeded;

    public DBhelper(Context context) {
        super(context, DB_NAME, null, 1);

        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir +"/databases/";
        }

        else{
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        myContext = context;
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
        DB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

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
        SQLiteDatabase db = getWritableDatabase();
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

                cursor = db.rawQuery(generateQuery(booleanStack),StringsNeeded);
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String generateQuery(Stack<String> stack){
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

        }
        StringsNeeded = new String [list.size()];
        for(int i=0;i<list.size();i++){
            StringsNeeded[i] = list.get(i);
            System.out.println(StringsNeeded[i]);
        }
        return query;
    }
}
