package com.codeclan.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddToDoActivity extends AppCompatActivity {

    private Date date;
    private Boolean isDateSet = false;
    private Button dateButton;
    private int year=2017;
    private int month=03;
    private int day=22;
    static final int DIALOG_ID = 0;
    private Spinner categorySpinner;
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
                    isDateSet=true;
                    year = yearDate;
                    month = monthOfYear;
                    day= dayOfMonth;
                    Toast.makeText(AddToDoActivity.this, year + "/" + month + "/" + day, Toast.LENGTH_LONG).show();

                }
            };

    public void addCategoriesToSpinner(){
        //get list of categories
        SharedPrefCleaner clean = new SharedPrefCleaner(AddToDoActivity.this);
        ArrayList<String> newCategoryArray=clean.getCategoriesStrings();
        //cast a spinner object from id
        categorySpinner = (Spinner)findViewById(R.id.set_category);
        //adapt array to populate spinner
            ArrayAdapter<String>categoriesAdapter= new ArrayAdapter<String>(
                    this, R.layout.simple_spinner_dropdown_items, newCategoryArray);
        //use adapter to populate spinner
        categoriesAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_items);
        categorySpinner.setAdapter(categoriesAdapter);
        }

    public void onClickAddToDo(View button) {
        final EditText detailsET = (EditText) findViewById(R.id.set_details);
        String details = detailsET.getText().toString();
        final RatingBar priorityRT = (RatingBar) findViewById(R.id.set_priority);
        float priorityFloat = priorityRT.getRating();
        double priority = (double) priorityFloat;
        final TextView textView = (TextView) findViewById(R.id.toDoSummary);
        String summary = textView.getText().toString();
        final Spinner categorySp = (Spinner) findViewById(R.id.set_category);
        String category = categorySp.getSelectedItem().toString();
        Category categ = new Category(category);
        ToDo toDo = new ToDo(priority, categ, summary, details);
        toDo.setDetails(details);

        if (isDateSet == true) {
            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + month + "/" + year);

                toDo = new ToDo(priority, categ, summary, details, date);
                Log.d("" + date, "hello");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            //get list
            SharedPrefCleaner clean = new SharedPrefCleaner(AddToDoActivity.this);
            ArrayList<ToDo>newToDoListArray=clean.getFullList();
            //add object to list
            newToDoListArray.add(toDo);
            //save list
            clean.saveFullList(newToDoListArray);
            //toast the Queen
            Toast.makeText(AddToDoActivity.this, "New Task Added", Toast.LENGTH_LONG).show();
            //return to main activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }




