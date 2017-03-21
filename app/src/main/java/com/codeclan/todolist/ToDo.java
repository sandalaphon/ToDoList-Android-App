package com.codeclan.todolist;

import java.io.Serializable;
import java.util.Date;

public class ToDo implements Comparable<ToDo>, Serializable {
    private double priority;
    private Category category;
    private String title;
    private String date;
    private String details;

    public ToDo(double priority, Category category, String title, String details) {
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

    public String getDate() {return date;}
    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int compareTo(ToDo toDo1){
        if(this.getPriority()<toDo1.getPriority()){
            return 1;}
        else if (this.getPriority()>toDo1.getPriority()){
            return -1;}
        return 0;
    }
}
