package com.codeclan.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;

public class AddToDoActivity extends AppCompatActivity {
    private Button dateButton;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    static final int DIALOG_ID = 0;

    private Category category;
    private Spinner categorySpinner;
    private ArrayList<String>categories;
    public static final String TODOLISTS = "myTasks";
    public static final String CATEGORIES = "categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
//        addcategories();
        addCategoriesToSpinner();
        Intent intent = getIntent();
        String summary = intent.getStringExtra("toDoSummary");
        TextView textView1 = (TextView)findViewById(R.id.toDoSummary);
        textView1.setText(summary);
        showDialogOnSetDateButtonClick();
    }

    public void showDialogOnSetDateButtonClick() {
        dateButton = (Button) findViewById(R.id.set_date_button);

        dateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(
                    this, datePickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener(){
                @Override
                public void onDateSet(DatePicker view, int yearDate, int monthOfYear, int dayOfMonth){
                    year = yearDate;
                    month = monthOfYear;
                    day= dayOfMonth;
                    Toast.makeText(AddToDoActivity.this, year + "/" + month + "/" + day, Toast.LENGTH_LONG).show();

                }
            };



    public void addCategoriesToSpinner(){
//        category = new Category("");
//        categories = new ArrayList<>(category.getCategories());

        Gson gson = new Gson();

        SharedPreferences sharedPreferences2 = getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<String>());
        String newCategoryArrayST = sharedPreferences2.getString("categories", defaultValue);

        TypeToken<ArrayList<String>>typeCategoryArray = new TypeToken<ArrayList<String>>(){};
        ArrayList<String>newCategoryArray= gson.fromJson(newCategoryArrayST,typeCategoryArray.getType());

        categorySpinner = (Spinner)findViewById(R.id.set_category);
            ArrayAdapter<String>categoriesAdapter= new ArrayAdapter<String>(
                    this, R.layout.simple_spinner_dropdown_items, newCategoryArray);
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
        ToDo toDo = new ToDo(priority, categ, summary, details);
        toDo.setDetails(details);

        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences(TODOLISTS, Context.MODE_PRIVATE);
        String defaultValue = gson.toJson(new ArrayList<ToDo>());
        String newToDoListArrayST = sharedPreferences.getString("myTasks", defaultValue);

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

//    public void addcategories(){
//        category = new Category("");
//        categories=category.getCategories();
//        Gson gson= new Gson();
//        SharedPreferences sharedPreferences2= getSharedPreferences(CATEGORIES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor2=  sharedPreferences2.edit();
//        editor2.putString("categories", gson.toJson(categories));
//        editor2.apply();
//    }

    }



