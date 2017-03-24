package com.codeclan.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CategoryAddDeleteActivity extends BaseActivity {
    public static final String CATEGORIES = "categories";
    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add_delete);
        populateSpinner();
    }

    public void onClickAddCategory(View button){
        EditText editText = (EditText)findViewById(R.id.category_to_add);
        String categoryToAdd = editText.getText().toString();
        Gson gson= new Gson();

        SharedPreferences sharedPreferences2 = getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<String>());
        String newCategoryArrayST = sharedPreferences2.getString("categories", defaultValue);

        TypeToken<ArrayList<String>> typeCategoryArray = new TypeToken<ArrayList<String>>(){};
        ArrayList<String>newCategoryArray= gson.fromJson(newCategoryArrayST,typeCategoryArray.getType());

        newCategoryArray.add(categoryToAdd);

        SharedPreferences.Editor editor2=  sharedPreferences2.edit();
        editor2.putString("categories", gson.toJson(newCategoryArray));
        editor2.apply();

        Toast.makeText(CategoryAddDeleteActivity.this,"Category has been added", Toast.LENGTH_LONG ).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void populateSpinner(){
        Gson gson= new Gson();

        SharedPreferences sharedPreferences2 = getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<String>());
        String newCategoryArrayST = sharedPreferences2.getString("categories", defaultValue);

        TypeToken<ArrayList<String>> typeCategoryArray = new TypeToken<ArrayList<String>>(){};
        ArrayList<String>newCategoryArray= gson.fromJson(newCategoryArrayST,typeCategoryArray.getType());

        categorySpinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> categoriesAdapter= new ArrayAdapter<String>(
                this, R.layout.simple_spinner_dropdown_items, newCategoryArray);
        categoriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_items);
        categorySpinner.setAdapter(categoriesAdapter);
    }

    public void onClickDeleteCategoryButton(View button){
         Spinner categorySpinnerValue = (Spinner)findViewById(R.id.spinner);
        String categoryToDelete = categorySpinnerValue.getSelectedItem().toString();

        Gson gson= new Gson();

        SharedPreferences sharedPreferences2 = getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<String>());
        String newCategoryArrayST = sharedPreferences2.getString("categories", defaultValue);

        TypeToken<ArrayList<String>> typeCategoryArray = new TypeToken<ArrayList<String>>(){};
        ArrayList<String>newCategoryArray= gson.fromJson(newCategoryArrayST,typeCategoryArray.getType());
        Log.d("newCategoryArray: " + newCategoryArray, "categoryToDelete: " + categoryToDelete);
        newCategoryArray.remove(categoryToDelete);
        Log.d("newCatArray after  " + newCategoryArray, "hello");
        SharedPreferences.Editor editor2=  sharedPreferences2.edit();
        editor2.putString("categories", gson.toJson(newCategoryArray));
        editor2.apply();

        Toast.makeText(CategoryAddDeleteActivity.this,"Category has been deleted", Toast.LENGTH_LONG ).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}
