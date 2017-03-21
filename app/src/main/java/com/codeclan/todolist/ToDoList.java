package com.codeclan.todolist;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class ToDoList extends AppCompatActivity  {
    public static final String TODOLIST = "thisToDoList";
    private ArrayList<ToDo> toDoList;
    private List<ToDo> toDoListSortedPriority;
    private int length;

    public ToDoList() {
        this.toDoList = toDoList;
        this.length = length;
        toDoList = new ArrayList<>();
        this.toDoListSortedPriority = new ArrayList<>();
    }

    public void addAToDo(ToDo toDo){
        toDoList.add(toDo);
    }

    public ArrayList<ToDo> getToDoList() {
        return toDoList;
    }
    public void setToDoList(ArrayList<ToDo> toDoList) {
        this.toDoList = toDoList;
    }

    public ArrayList<ToDo> getByCategory(String category){
        ArrayList<ToDo>categoryToDo = new ArrayList<>();
        for(ToDo toDo: toDoList){
            if(toDo.getCategory().getCategory().equals(category)){
                categoryToDo.add(toDo);
            }
        }
        return categoryToDo;
    }
}
