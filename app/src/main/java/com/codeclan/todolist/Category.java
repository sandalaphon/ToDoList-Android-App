package com.codeclan.todolist;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
    private String category;
    private ArrayList<String>categories;

    public Category(String category) {
        categories = new ArrayList<>();
        this.category = category;
        categories.add("Family");
        categories.add("Social");
        categories.add("Work");
        categories.add("Shopping");
        categories.add("Admin");
        categories.add("General");
        this.categories = categories;

    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public int getIndexByCategory(Category category3){
        int index;
        int counter=0;
        for(String category: categories){
            if(category.equals(category3)){
                return counter;
            }
            counter ++;
        }
        return -1;
    }


}


