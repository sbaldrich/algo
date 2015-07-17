package com.baldrichcorp.toolbox.sorting;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Implementation of the Merge Sort algorithm, a divide and conquer sorting algorithm supported 
 * on two main operations: sorting a sub-array and merging two sorted sub-arrays. Works in O(nlg(n))
 * 
 * @author sbaldrich
 *
 */

public class MergeSort implements Sorter{

    private static final MergeSort INSTANCE = new MergeSort();

    private MergeSort(){};

    public static MergeSort instance(){
        return INSTANCE;
    }

    public <T extends Comparable<? super T>> void sort(T[] array){
        T[] aux = array.clone();
        mergesort(aux, array, 0, aux.length - 1);
    }

    private <T extends Comparable<? super T>> void mergesort(T[] src, T[] dest, int low, int high) {

        /*
         * With a sufficiently small array, Insertion sort would be a better choice. I don't
         * use it only for educational purposes.
         */

        if(low >= high)
            return;
        int mid = (low + high) >>> 1;

        //Recursively sort each half before copying from src to dest
        mergesort(dest, src, low, mid);
        mergesort(dest, src, mid + 1, high);

        for(int i = low, p = low, q = mid + 1; i <= high; i++){
            if(q > high || p <= mid && src[p].compareTo(src[q]) <= 0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }

}