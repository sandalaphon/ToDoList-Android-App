package com.codeclan.todolist;

import java.util.ArrayList;

public class QuickSort {
    ArrayList<Integer> toSort;
     int length;

    public QuickSort() {
        this.toSort = toSort;
        this.length = length;
    }

    public ArrayList<Integer> getToSort() {
        return toSort;
    }

    public void sortArray(ArrayList<Integer> values) {
        if (values ==null || values.size()==0) {return;}
        this.toSort = values;
        length = values.size();
        sortMe(0, length - 1);
    }


    private void sortMe(int first, int last) {
        int i = first;
        int j = last;
        int index = (first + (last - first) / 2);
        int pivot = toSort.get(index);
        while (i <= j) {
            while (this.toSort.get(i) < pivot) {
                i++;
            }
            while (this.toSort.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if(first<j)
        {sortMe(0, j);}
        if(i<last)
        {sortMe(i, last);}
    }

    private void swap(int i, int j){
        Integer holder;
        holder = this.toSort.get(i);
        this.toSort.set(i, this.toSort.get(j));
        this.toSort.set(j, holder);
    }
}
