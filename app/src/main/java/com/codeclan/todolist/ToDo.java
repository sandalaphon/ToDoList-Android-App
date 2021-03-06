package com.codeclan.todolist;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class ToDo implements Serializable {
    private double priority;
    private Category category;
    private String title;
    private Date date;
    private String details;

    public ToDo(double priority, Category category, String title, String details) {
        this.priority = priority;
        this.category = category;
        this.title = title;
        this.details = details;

    }

    public ToDo (double priority, Category category, String title, String details, Date date){
        this.priority = priority;
        this.category = category;
        this.title = title;
        this.details = details;
        this.date = date;
    }





    public double getPriority() {
        return priority;
    }
    public void setPriority(float priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {return date;}
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }


    static class ToDoDateComparator implements Comparator<ToDo> {

        @Override
        public int compare(ToDo toDo1, ToDo toDo2){

            if(toDo1.getDate()==null || toDo2.getDate()==null) {
                return ( toDo1.getDate()==null&&toDo2.getDate()==null) ? 0 :
                        (toDo1.getDate()==null) ? 1 : -1;
            }
            return toDo1.getDate().compareTo(toDo2.getDate());

        }
    }

    static class ToDoCategoryComparator implements Comparator<ToDo> {

        @Override
        public int compare(ToDo toDo1, ToDo toDo2){
            return toDo1.getCategory().getCategory().compareTo(toDo2.getCategory().getCategory());
        }
    }
}


