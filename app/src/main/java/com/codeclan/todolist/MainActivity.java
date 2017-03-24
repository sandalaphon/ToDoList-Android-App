package com.codeclan.todolist;


import android.content.Intent;;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements Serializable{
    ArrayList<ToDo>fullList;
    ArrayList<PairIndex>pairs;
    ToDo toDo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_list);
        //is there a bundle?
        Intent intent = getIntent();

        // First we get ArrayList of ToDos

            SharedPrefCleaner clean = new SharedPrefCleaner(MainActivity.this);
            fullList = clean.getFullList();
            {
                ToDoListAdapter toDoListAdapter = new ToDoListAdapter(this, fullList);
                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(toDoListAdapter);
            }

        if (intent.getExtras()!=null) {
            pairs = (ArrayList<PairIndex>) intent.getExtras().getSerializable("Sorted Pairs Array");
            PairsAdapter pairsAdapter = new PairsAdapter(this, pairs);
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(pairsAdapter);
        }
    }


    public void onClickAddSummary(View button){
        //get the summary string
        EditText toDoSummaryET = (EditText)findViewById(R.id.newTaskSummary);
        String toDoSummary = toDoSummaryET.getText().toString();
        //now we go to the add a to do screen..(taking the string with us.)
        Intent intent = new Intent(this, AddToDoActivity.class);
        intent.putExtra("toDoSummary", toDoSummary);//send the string through with the intent
        startActivity(intent);
    }

    public void onClickViewDetails(View button) {

        int currentToDoPosition = (int)button.getTag();
        // the array index of relavent vToDo collected from button tag
        Intent intent = new Intent(this, ViewDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("currentToDo", currentToDoPosition);

        intent = intent.putExtras(bundle); //and sent in serialized bundle
        // to viewDetailsActivity
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
            SharedPrefCleaner clean = new SharedPrefCleaner(MainActivity.this);
            fullList = clean.getFullList();
            Log.d("unsorted list 84 main: " + fullList, "  hello");
        //   now sort the array

            pairs = new ArrayList<>();
            int counter = 0;
            for(ToDo toDo:fullList){
                PairIndex pi = new PairIndex(toDo,counter);
                pairs.add(pi);
                counter++;
            }
            Collections.sort(pairs, new PairIndex.PairPriorityComparator());


            PairsAdapter pairsAdapter = new PairsAdapter(this, pairs);
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(pairsAdapter);

            return true;
        }
        else if (item.getItemId()==R.id.add_delete_category){
            Intent intent = new Intent(this, CategoryAddDeleteActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId()==R.id.sort_date) {
            //get list
            SharedPrefCleaner clean = new SharedPrefCleaner(MainActivity.this);
            fullList = clean.getFullList();
            //Create arraylist of PairIndex
            pairs = new ArrayList<>();
            int counter = 0;
            for(ToDo toDo:fullList){
                PairIndex pi = new PairIndex(toDo,counter);
                pairs.add(pi);
                counter++;
            }
            Collections.sort(pairs, new PairIndex.PairDateComparator());


            PairsAdapter pairsAdapter = new PairsAdapter(this, pairs);
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(pairsAdapter);

            return true;

        }
        else if (item.getItemId()==R.id.view_category) {
            //get list
            SharedPrefCleaner clean = new SharedPrefCleaner(MainActivity.this);
            fullList = clean.getFullList();
            //sort list
            pairs = new ArrayList<>();
            int counter = 0;
            for(ToDo toDo:fullList){
                PairIndex pi = new PairIndex(toDo,counter);
                pairs.add(pi);
                counter++;
            }
            Collections.sort(pairs, new PairIndex.PairCategoryComparator());

            PairsAdapter pairsAdapter = new PairsAdapter(this, pairs);
            ListView listView = (ListView)findViewById(R.id.list);
            listView.setAdapter(pairsAdapter);


            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickDeleteFromList(View button){
        //retreive index of huckleberry
        int currentToDoPosition = (int)button.getTag();
        // get list
        SharedPrefCleaner clean = new SharedPrefCleaner(MainActivity.this);
        fullList = clean.getFullList();

        //remove huckleberry from list
        fullList.remove(currentToDoPosition);
        //save list
        clean.saveFullList(fullList);
        finish();
        startActivity(getIntent());
        //toast the Queen
        Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_LONG).show();
    }


}
