package com.baldrichcorp.toolbox.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;


public abstract class SortingAlgorithmTest {
    public static final int ARRAY_SIZE = 1000;

    private Sorter sorter;

    SortingAlgorithmTest(Sorter sorter){
        this.sorter = sorter;
    }

    @Test
    public void sortARandomArray() {
        Integer[] random = new Random().ints(Integer.MIN_VALUE,Integer.MAX_VALUE).limit(ARRAY_SIZE).
                boxed().toArray(Integer[]::new), copy = random.clone();
        Arrays.sort(copy);
        sorter.sort(random);
        assertTrue(Arrays.equals(copy, random));
    }

    @Test
    public void sortAnAscendingArray() {
        Integer[] ascending = IntStream.range(0, ARRAY_SIZE).boxed().toArray(Integer[]::new), copy = ascending.clone();
        Arrays.sort(copy);
        sorter.sort(ascending);
        assertTrue(Arrays.equals(copy, ascending));
    }

    @Test
    public void sortADescendingArray() {
        Integer[] descending = IntStream.range(0, ARRAY_SIZE).map(n -> ARRAY_SIZE - n).boxed().
                toArray(Integer[]::new), copy = descending.clone();
        Arrays.sort(copy);
        sorter.sort(descending);
        assertTrue(Arrays.equals(copy, descending));
    }

    @Test
    public void sortARandomDoublesArray() {
        Double[] randomDoubles = new Random().doubles(Double.MIN_VALUE,Double.MAX_VALUE).limit(ARRAY_SIZE).
                boxed().toArray(Double[]::new), copy = randomDoubles.clone();
        Arrays.sort(copy);
        sorter.sort(randomDoubles);
        assertTrue(Arrays.equals(copy, randomDoubles));
    }
}
