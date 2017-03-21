package com.codeclan.todolist;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;


public class ToDoListTest {
    Category shopping;
    Category codeClan;
    Category social;
    Category family;
    ToDo milk;
    ToDo butter;
    ToDo calljamie;
    ToDo presentation;
    ArrayList<ToDo> toDoArrayList;
    ToDoList toDoList;
    ArrayList<ToDo> sortedArrayPriority;
//    ToDoList sortedTDL;


    @Before
    public void before(){
        shopping = new Category("Shopping");
        codeClan = new Category("CodeClan");
        social = new Category("Social");
        family = new Category("Family");

        milk = new ToDo(2.5, shopping, "milk");
        butter = new ToDo(5, shopping, "butter");
        calljamie = new ToDo(4, family, "Call Jamie");
        presentation = new ToDo(3, codeClan, "project presentation");
        toDoArrayList = new ArrayList<ToDo>();
        toDoArrayList.add(milk);
        toDoArrayList.add(butter);
        toDoArrayList.add(calljamie);
        toDoArrayList.add(presentation);
        toDoList = new ToDoList();
        sortedArrayPriority = new ArrayList<ToDo>();
        sortedArrayPriority.add(butter);
        sortedArrayPriority.add(calljamie);
        sortedArrayPriority.add(presentation);
        sortedArrayPriority.add(milk);




//        sortedTDL= new ToDoList(sortedArrayPriority);
    }

    @Test
    public void testgetsetToDo(){
        assertEquals("milk", milk.getTitle());
    }

    @Test
    public void testsetDetails(){
        milk.setDetails("Buy Milk");
        assertEquals("Buy Milk", milk.getDetails());
    }

   @Test
    public void testgetByCategory(){
       ArrayList<ToDo> testarray = new ArrayList<>();
       testarray.add(milk);
       testarray.add(butter);
       assertEquals(testarray, toDoList.getByCategory("Shopping"));
   }

   @Test
    public void testGetByPriority() throws IOException {
       SortPriority sorter = new SortPriority();
       ArrayList<ToDo>toDoList=new ArrayList<>();
       toDoList.add(presentation);
       toDoList.add(milk);
       toDoList.add(calljamie);
       toDoList.add(butter);
       assertEquals(sortedArrayPriority, sorter.sortPriority(toDoList));
   }
}




