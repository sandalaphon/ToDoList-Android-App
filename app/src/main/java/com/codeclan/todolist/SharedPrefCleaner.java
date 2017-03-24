package com.codeclan.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SharedPrefCleaner extends AppCompatActivity {
    Gson gson;
    SharedPreferences sharedPreferences;
    public static final String TODOLISTS = "myTasks";
    public static final String TODOSUBLIST = "sublist";
    public static final String CATEGORIES = "categories";
    ToDo toDo;
    String category;
    ArrayList<ToDo>fullList;
    ArrayList<ToDo>subList;
    ArrayList<String>categoriesSP;
    Context context;

    public SharedPrefCleaner(Context context) {
        this.context = context;
    }

    public ArrayList<ToDo> getFullList(){
        gson = new Gson();
        String defaultString = gson.toJson(new ArrayList<ToDo>());
        sharedPreferences = context.getSharedPreferences("myTasks", Context.MODE_PRIVATE);
        String toDoListString = sharedPreferences.getString("myTasks", defaultString);
        TypeToken<ArrayList<ToDo>> typeToDoList = new TypeToken<ArrayList<ToDo>>() {
        };
        fullList = gson.fromJson(toDoListString, typeToDoList.getType());
        return fullList;
    }

    public void saveFullList(ArrayList<ToDo>fullList1){
        gson = new Gson();
        sharedPreferences= context.getSharedPreferences("myTasks", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myTasks", gson.toJson(fullList1));
        editor.apply();
    }

    public ArrayList<String> getCategoriesStrings(){
        gson = new Gson();
        String defaultString = gson.toJson(new ArrayList<String>());
        sharedPreferences = context.getSharedPreferences("categories", Context.MODE_PRIVATE);
        String toDoListString = sharedPreferences.getString("categories", defaultString);
        TypeToken<ArrayList<String>> typeToDoList = new TypeToken<ArrayList<String>>() {
        };
        categoriesSP = gson.fromJson(toDoListString, typeToDoList.getType());
        return categoriesSP;
    }

    public void saveCategory(ArrayList<String>categoriesSP){
        gson = new Gson();
        sharedPreferences = context.getSharedPreferences("categories", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("categories", gson.toJson(categoriesSP));
        editor.apply();
    }

}
