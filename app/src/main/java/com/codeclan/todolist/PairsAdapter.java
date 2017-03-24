package com.codeclan.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class PairsAdapter  extends ArrayAdapter<PairIndex> {
    ArrayList<ToDo>fullList;
    ArrayList<PairIndex> pairIndices;
    SharedPrefCleaner clean;
    public PairsAdapter(Context context, ArrayList<PairIndex> pairIndices){
        super(context, 0, pairIndices);
        this.pairIndices = pairIndices;
        clean = new SharedPrefCleaner(context);
        this.fullList = clean.getFullList();


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if(listItemView==null) {
            listItemView = LayoutInflater.from(
                    getContext()).inflate(R.layout.todo_list_item, parent, false);
        }
        int indexInFullList = pairIndices.get(position).getIndex();
        ToDo currentToDo = fullList.get(indexInFullList);

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
        button.setTag(indexInFullList);
        Button deletebutton =(Button)listItemView.findViewById(R.id.list_delete_button);
        deletebutton.setTag(indexInFullList);


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
