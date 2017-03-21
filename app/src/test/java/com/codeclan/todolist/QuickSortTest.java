package com.codeclan.todolist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;

/**
 * Created by user on 18/03/2017.
 */

public class QuickSortTest {
    ArrayList<Integer>toSort;
    QuickSort quickSort;
    ArrayList<Integer> hopedFor;
    @Before
    public void before(){
        toSort = new ArrayList<>();
        hopedFor = new ArrayList<>();
        toSort.add(72);
        toSort.add(94);
        toSort.add(25);
        toSort.add(2);
        toSort.add(9);
        toSort.add(7);
        toSort.add(29);
//        toSort.add(6743);
//        toSort.add(5748);
//        toSort.add(987);
//        toSort.add(3452);
        hopedFor.add(2);
        hopedFor.add(7);
        hopedFor.add(9);
        hopedFor.add(25);
        hopedFor.add(29);
        hopedFor.add(72);
        hopedFor.add(94);
//        hopedFor.add(987);
//        hopedFor.add(3452);
//        hopedFor.add(5748);
//        hopedFor.add(6743);
        quickSort = new QuickSort();

    }

    @Test
    public void testQuickSort(){
        quickSort.sortArray(toSort);
        assertEquals(hopedFor,quickSort.getToSort());

    }
}
