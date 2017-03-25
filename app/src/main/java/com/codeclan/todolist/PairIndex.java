package com.codeclan.todolist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by user on 24/03/2017.
 */

public class PairIndex implements Serializable{
    ToDo toDo;
    int index;

    public PairIndex(ToDo toDo, int index) {
        this.toDo = toDo;
        this.index = index;
    }

    public ToDo getToDo() {
        return toDo;
    }//get
    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }//set

    public int getIndex() {
        return index;
    }//get
    public void setIndex(int index) {
        this.index = index;
    }//set


    static class PairDateComparator implements Comparator<PairIndex> {

        @Override
        public int compare(PairIndex pair1, PairIndex pair2) {
            //if either date is null we cant use compareTo
            if (pair1.getToDo().getDate() == null || pair2.getToDo().getDate() == null) {
                return (pair1.getToDo().getDate() == null && pair2.getToDo().getDate() == null) ? 0 : (pair1.getToDo().getDate() == null) ? 1 : -1;
            }
            //if neither date is null compareTo is okay
            return pair1.getToDo().getDate().compareTo(pair2.getToDo().getDate());

        }
    }

    static class PairPriorityComparator implements Comparator<PairIndex> {

        @Override
        public int compare(PairIndex pair1, PairIndex pair2) {

            return (pair2.getToDo().getPriority()>pair1.getToDo().getPriority())? 1 :
                    (pair1.getToDo().getPriority()==pair2.getToDo().getPriority()) ? 0 : -1;
        }
    }

    static class PairCategoryComparator implements Comparator<PairIndex> {

        @Override
        public int compare(PairIndex pair1, PairIndex pair2) {

            return pair1.getToDo().getCategory().getCategory().
                    compareTo(pair2.getToDo().getCategory().getCategory());
        }
    }
}
