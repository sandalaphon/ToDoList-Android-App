package com.codeclan.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewDetailsActivity extends BaseActivity implements Serializable{
    private Spinner categorySpinner;
    private int currentToDoPosition;
    private ToDo currentToDo;
    EditText detailsET;
    private ArrayList<ToDo>newToDoListArray;
    private Boolean isDateSet = false;
    private Button dateButton;
    private int year=2017;
    private int month=03;
    private int day=22;
    static final int DIALOG_ID = 0;
    private  ToDo toDo;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);


        Intent intent = getIntent();
        currentToDoPosition = (int)intent.getExtras().getSerializable("currentToDo");

        SharedPrefCleaner clean = new SharedPrefCleaner(ViewDetailsActivity.this);
        newToDoListArray = clean.getFullList();

        currentToDo = newToDoListArray.get(currentToDoPosition);
        TextView title = (TextView)findViewById(R.id.toDoEditSummary);
        title.setText(currentToDo.getTitle());
        RatingBar ratingBar = (RatingBar)findViewById(R.id.edit_priority);
        float rating = (float) (currentToDo.getPriority());
        ratingBar.setRating(rating);
        detailsET = (EditText)findViewById(R.id.set_edit_details);
        detailsET.setText(currentToDo.getDetails());
        addCategoriesToSpinner();

        date = currentToDo.getDate();
        if(date!=null){Log.d("Date", date.toString());}

        if(date!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String dateToString = simpleDateFormat.format(date);
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            String dayString = dayFormat.format(date);
            day = Integer.parseInt(dayString);
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
            String monthString = monthFormat.format(date);
            month = Integer.parseInt(monthString);
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            String yearString = yearFormat.format(date);
            year = Integer.parseInt(yearString);

            TextView dateview = (TextView) findViewById(R.id.date_text_details);
            dateview.setText(dateToString);

        }
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

                    try {
                        date = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + month + "/" + year);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                    String dateToString = simpleDateFormat.format(date);
                    TextView dateview = (TextView) findViewById(R.id.date_text_details);
                    dateview.setText(dateToString);
                    Toast.makeText(ViewDetailsActivity.this, year + "/" + month + "/" + day, Toast.LENGTH_LONG).show();

                }
            };

    public void addCategoriesToSpinner(){

        //get categories string arraylist
        SharedPrefCleaner clean = new SharedPrefCleaner(ViewDetailsActivity.this);
        ArrayList<String>newCategoryArray =clean.getCategoriesStrings();

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
        if(date!=null){toDo = new ToDo(priority, categ, summary, details, date);}
        else if (date==null){toDo = new ToDo(priority, categ, summary, details);}


        newToDoListArray.remove(currentToDoPosition);
        newToDoListArray.add(0, toDo);

        SharedPrefCleaner clean = new SharedPrefCleaner(ViewDetailsActivity.this);
        clean.saveFullList(newToDoListArray);
        Toast.makeText(ViewDetailsActivity.this, "Task Changes Have Been Saved", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void onClickDeleteToDoButton(View button){
        newToDoListArray.remove(currentToDo);
        SharedPrefCleaner clean = new SharedPrefCleaner(ViewDetailsActivity.this);
        clean.saveFullList(newToDoListArray);

        Toast.makeText(ViewDetailsActivity.this, "Completed Task Has been removed\n From To Do List", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    }



