package com.codeclan.todolist;

import android.content.Context;
import java.text.SimpleDateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.priority;
import static android.R.id.list;


public class ToDoListAdapter extends ArrayAdapter<ToDo>{
    ArrayList<ToDo>toDoArray;
    public ToDoListAdapter(Context context, ArrayList<ToDo>toDoArray){
        super(context, 0, toDoArray);
        this.toDoArray = toDoArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if(listItemView==null) {
            listItemView = LayoutInflater.from(
                    getContext()).inflate(R.layout.todo_list_item, parent, false);
            }
                ToDo currentToDo = toDoArray.get(position);

            TextView title = (TextView) listItemView.findViewById(R.id.title);
            title.setText(currentToDo.getTitle());


        TextView dateview = (TextView) listItemView.findViewById(R.id.date_text_view);
        Date date = currentToDo.getDate();
        if(date!=null){Log.d("Date", date.toString());}

        if(date!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            String dateToString = simpleDateFormat.format(date);
            dateview.setText(dateToString);
        }
        else{ dateview.setText("no date set");}


        Button button =(Button)listItemView.findViewById(R.id.detailsButton);
        button.setTag(position);
        Button deletebutton =(Button)listItemView.findViewById(R.id.list_delete_button);
        deletebutton.setTag(position);


            TextView category = (TextView) listItemView.findViewById(R.id.category);
            String str = currentToDo.getCategory().getCategory();
            Log.d(currentToDo.getTitle(), str);
            category.setText(str);
            Log.d(str, str);

            float f = (float) currentToDo.getPriority();
            Log.d("Priority" + f, "Prior" + currentToDo.getPriority());
            RatingBar priority = (RatingBar) listItemView.findViewById(R.id.priority);
            priority.setRating(f);

            return listItemView;
    }
}
