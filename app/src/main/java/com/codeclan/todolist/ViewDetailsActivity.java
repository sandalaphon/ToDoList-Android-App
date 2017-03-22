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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class ViewDetailsActivity extends AppCompatActivity implements Serializable{
    private Category category;
    private Spinner categorySpinner;
    private ArrayList<String> categories;
    private int currentToDoPosition;
    private ToDo currentToDo;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    EditText detailsET;
    private ArrayList<ToDo>newToDoListArray;
    public static final String TODOLISTS = "myTasks";
    public static final String CATEGORIES = "categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);


        Intent intent = getIntent();
        currentToDoPosition = (int)intent.getExtras().getSerializable("currentToDo");

        gson = new Gson();

        sharedPreferences = getSharedPreferences(TODOLISTS, Context.MODE_PRIVATE);
        String newToDoListArrayST = sharedPreferences.getString("myTasks", "whatever");
        TypeToken<ArrayList<ToDo>> typeNewToDoListArray = new TypeToken<ArrayList<ToDo>>(){};
        editor = sharedPreferences.edit();
        newToDoListArray= gson.fromJson(newToDoListArrayST,typeNewToDoListArray.getType());
        currentToDo = newToDoListArray.get(currentToDoPosition);
        TextView title = (TextView)findViewById(R.id.toDoEditSummary);
        title.setText(currentToDo.getTitle());
        RatingBar ratingBar = (RatingBar)findViewById(R.id.edit_priority);
        float rating = (float) (currentToDo.getPriority());
        ratingBar.setRating(rating);
        detailsET = (EditText)findViewById(R.id.set_edit_details);
        detailsET.setText(currentToDo.getDetails());
        addCategoriesToSpinner();
    }

    public void addCategoriesToSpinner(){
//        category = new Category("");
//        categories = new ArrayList<>(category.getCategories());

        Gson gson = new Gson();

        SharedPreferences sharedPreferences2 = getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<String>());
        String newCategoryArrayST = sharedPreferences2.getString("categories", defaultValue);

        TypeToken<ArrayList<String>>typeCategoryArray = new TypeToken<ArrayList<String>>(){};
        ArrayList<String>newCategoryArray= gson.fromJson(newCategoryArrayST,typeCategoryArray.getType());

        categorySpinner = (Spinner)findViewById(R.id.edit_category);
        ArrayAdapter<String> categoriesAdapter= new ArrayAdapter<String>(
                this, R.layout.simple_spinner_dropdown_items, newCategoryArray);
        categoriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_items);
        categorySpinner.setAdapter(categoriesAdapter);
        if (!currentToDo.equals(null)) {
            int spinnerPosition = categoriesAdapter.getPosition(currentToDo.getCategory().getCategory());
            categorySpinner.setSelection(spinnerPosition);
        }


    }

    public void onClickEditToDoButton(View button){
        ////////////////////////////////////////
        final EditText detailsET = (EditText)findViewById(R.id.set_edit_details);
        String details = detailsET.getText().toString();
        final RatingBar priorityRT = (RatingBar)findViewById(R.id.edit_priority);
        float priorityFloat = priorityRT.getRating();
        double priority = (double)priorityFloat;
        final EditText titleEditText = (EditText) findViewById(R.id.toDoEditSummary);
        String summary = titleEditText.getText().toString();
        final Spinner categorySp=(Spinner)findViewById(R.id.edit_category);
        String category = categorySp.getSelectedItem().toString();
        Category categ = new Category(category);
        ToDo toDo = new ToDo(priority, categ, summary, details);

        newToDoListArray.set(currentToDoPosition, toDo);

        editor.putString("myTasks", gson.toJson(newToDoListArray));
        editor.apply();

        Toast.makeText(ViewDetailsActivity.this, "Task Changes Have Been Saved", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickDeleteToDoButton(View button){
        newToDoListArray.remove(currentToDoPosition);
        editor.putString("myTasks", gson.toJson(newToDoListArray));
        editor.apply();


        Toast.makeText(ViewDetailsActivity.this, "Completed Task Has been removed\n From To Do List", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    }



