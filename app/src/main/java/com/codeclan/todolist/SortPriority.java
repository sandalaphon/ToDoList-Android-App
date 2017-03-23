package com.codeclan.todolist;

import java.util.ArrayList;
import java.util.Collections;

public class SortPriority  {
    ArrayList<ToDo>atoDoList;
    public SortPriority() {
        this.atoDoList=new ArrayList<ToDo>();
    }

        public ArrayList<ToDo> sortPriority(ArrayList<ToDo>toDoList) {
            Collections.sort(toDoList);
            return toDoList;
        }


}






