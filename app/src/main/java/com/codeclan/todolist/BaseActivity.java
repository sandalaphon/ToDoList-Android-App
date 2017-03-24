package com.codeclan.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 24/03/2017.
 */

public class BaseActivity extends AppCompatActivity {
    ArrayList<ToDo>fullList;
    ArrayList<PairIndex>pairs;
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
            SharedPrefCleaner clean = new SharedPrefCleaner(this);
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

            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Sorted Pairs Array", pairs);
            intent = intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
        else if (item.getItemId()==R.id.add_delete_category){
            Intent intent = new Intent(this, CategoryAddDeleteActivity.class);
            startActivity(intent);

        }
        else if (item.getItemId()==R.id.sort_date) {
            //get list
            SharedPrefCleaner clean = new SharedPrefCleaner(this);
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



            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Sorted Pairs Array", pairs);
            intent = intent.putExtras(bundle);
            startActivity(intent);

            return true;

        }
        else if (item.getItemId()==R.id.view_category) {
            //get list
            SharedPrefCleaner clean = new SharedPrefCleaner(this);
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

            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Sorted Pairs Array", pairs);
            intent = intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
