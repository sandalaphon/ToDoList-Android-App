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

import java.util.ArrayList;

public class AddToDoActivity extends AppCompatActivity {
    private Category category;
    private Spinner categorySpinner;
    private ArrayList<String>categories;
    public static final String TODOLISTS = "myTasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        addCategoriesToSpinner();
        Intent intent = getIntent();
        String summary = intent.getStringExtra("toDoSummary");
        TextView textView1 = (TextView)findViewById(R.id.toDoSummary);
        textView1.setText(summary);


    }

    public void addCategoriesToSpinner(){
        category = new Category("");
        categories = new ArrayList<>(category.getCategories());
        categorySpinner = (Spinner)findViewById(R.id.set_category);
            ArrayAdapter<String>categoriesAdapter= new ArrayAdapter<String>(
                    this, R.layout.simple_spinner_dropdown_items, categories);
        categoriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_items);
        categorySpinner.setAdapter(categoriesAdapter);
        }

    public void onClickAddToDo(View button){
        final EditText detailsET = (EditText)findViewById(R.id.set_details);
        String details = detailsET.getText().toString();
        final RatingBar priorityRT = (RatingBar)findViewById(R.id.set_priority);
        float priorityfloat = priorityRT.getRating();
        double priority = (double)priorityfloat;
        final TextView textView = (TextView)findViewById(R.id.toDoSummary);
        String summary = textView.getText().toString();
        final Spinner categorySp=(Spinner)findViewById(R.id.set_category);
        String category = categorySp.getSelectedItem().toString();
        Category categ = new Category(category);
        ToDo toDo = new ToDo(priority, categ, summary);


        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(TODOLISTS, Context.MODE_PRIVATE);
        String newToDoListArrayST = sharedPreferences.getString("myTasks", "whatever");
        TypeToken<ArrayList<ToDo>>typeNewToDoListArray = new TypeToken<ArrayList<ToDo>>(){};
        ArrayList<ToDo>newToDoListArray= gson.fromJson(newToDoListArrayST,typeNewToDoListArray.getType());
        newToDoListArray.add(toDo);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myTasks", gson.toJson(newToDoListArray));
        editor.apply();


        Toast.makeText(AddToDoActivity.this, "New Task Added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }

    }



