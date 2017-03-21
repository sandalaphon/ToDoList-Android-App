package com.codeclan.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class ViewDetailsActivity extends AppCompatActivity implements Serializable{
    private Category category;
    private Spinner categorySpinner;
    private ArrayList<String> categories;
    public static final String TODOLISTS = "myTasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        addCategoriesToSpinner();

        Intent intent = getIntent();
        ToDo currentToDo = (ToDo)intent.getExtras().getSerializable("currentToDo");


        TextView title = (TextView)findViewById(R.id.toDoEditSummary);
        title.setText(currentToDo.getTitle());
    }

    public void addCategoriesToSpinner(){
        category = new Category("");
        categories = new ArrayList<>(category.getCategories());
        categorySpinner = (Spinner)findViewById(R.id.edit_category);
        ArrayAdapter<String> categoriesAdapter= new ArrayAdapter<String>(
                this, R.layout.simple_spinner_dropdown_items, categories);
        categoriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_items);
        categorySpinner.setAdapter(categoriesAdapter);
    }


}
