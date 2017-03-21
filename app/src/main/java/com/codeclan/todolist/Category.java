package com.codeclan.todolist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by user on 17/03/2017.
 */

public class Category implements Serializable{
    private String category;
    private ArrayList<String>categories;

    public Category(String category) {
        this.categories = new ArrayList<>();
        this.category = category;
        categories.add("Family");
        categories.add("Social");
        categories.add("Work");
        categories.add("Shopping");
        categories.add("Admin");
        categories.add("General");

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
}
