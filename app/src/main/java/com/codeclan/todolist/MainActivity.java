package com.codeclan.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.id.edit;
import static android.os.Build.VERSION_CODES.M;


public class MainActivity extends AppCompatActivity implements Serializable{
    public static final String TODOLIST = "myTasks";
    ArrayList<ToDo>fullList;
    Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);

        String defaultString = gson.toJson(new ArrayList<ToDo>());
        sharedPreferences = getSharedPreferences("myTasks", Context.MODE_PRIVATE);
        String toDoListString = sharedPreferences.getString("myTasks", defaultString );

        TypeToken<ArrayList<ToDo>>typeToDoList= new TypeToken<ArrayList<ToDo>>(){};
        fullList = gson.fromJson(toDoListString, typeToDoList.getType());

        ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, fullList);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(toDoListAdapter);
    }


    public void onClickAddSummary(View button){
        EditText toDoSummaryET = (EditText)findViewById(R.id.newTaskSummary);
        String toDoSummary = toDoSummaryET.getText().toString();

        Intent intent = new Intent(this, AddToDoActivity.class);
        intent.putExtra("toDoSummary", toDoSummary);
        startActivity(intent);
    }

    public void onClickViewDetails(View button) {


        int currentToDoPosition = (int)button.getTag();

        Intent intent = new Intent(this, ViewDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentToDo", currentToDoPosition);

        intent = intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.sort_priority){
            //First get the full toDoList
            sharedPreferences = getSharedPreferences("myTasks", Context.MODE_PRIVATE);
            String toDoListString = sharedPreferences.getString("myTasks", "whatever");

            gson = new Gson();

            TypeToken<ArrayList<ToDo>>typeToDoList= new TypeToken<ArrayList<ToDo>>(){};



            ArrayList<ToDo>fullList = gson.fromJson(toDoListString, typeToDoList.getType());

            SortPriority sorter = new SortPriority();
            ArrayList<ToDo>sorted=sorter.sortPriority(fullList);

            ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, sorted);

            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(toDoListAdapter);

            return true;
        }
        else if (item.getItemId()==R.id.add_delete_category){
            Intent intent = new Intent(this, CategoryAddDeleteActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickDeleteFromList(View button){
        int currentToDoPosition = (int)button.getTag();
        fullList.remove(currentToDoPosition);
        editor =sharedPreferences.edit();
        editor.putString("myTasks", gson.toJson(fullList));
        editor.apply();
        finish();
        startActivity(getIntent());


        Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_LONG).show();


    }


}
