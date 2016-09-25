package com.baldrichcorp.toolbox.sorting;

import java.util.Arrays;
import java.util.stream.Stream;

/** QuickSort algorithm => O(nlogn)
 * 
 * Divide and conquer algorithm based on the partition subroutine.
 *
 * It basically chooses a pivot, stores it on the first position of the sub-array and "partitions"
 * the remainder of the array in a way such that all elements less than (or equal) to the pivot
 * appear before those that are greater than it. It is superior to MergeSort because it works
 * in-place and is much cooler so all the girls want to hang out with it.
 * 
 * @author sbaldrich
 *
 */
public class QuickSort implements Sorter{

    private static final QuickSort INSTANCE = new QuickSort();

    private QuickSort(){}

    public static QuickSort instance(){
        return INSTANCE;
    }


    @Override
    public <T extends Comparable<? super T>> void sort(T[] array) {
        quickSort(0, array.length - 1, array);
    }

	private <T extends Comparable<? super T>> void quickSort(int l, int r, T[] array){
		if(r <= l)
			return;
		//Encapsulate the choice of the pivot to make changing it a piece of cake
        pivot(l, r, array);
		int p = partition(l, r, array);
		quickSort(l, p - 1, array);
		quickSort(p + 1, r, array);
	}

	/**
     * Select the pivot element and put it on the first position of the current sub-array.
	 * The pivot can be chosen in a variety of different ways. This particular implementation
	 * takes the median of the left-side, mid and right-side elements. A random selection would 
	 * suffice as well.
     *
     * @param l The left limit of the sub-array.
     * @param r The right limit of the sub-array.
     * @param array The input array.
	 */
	private <T extends Comparable<? super T>> void pivot(int l, int r, T[] array){
		int pivot = l, mid = (r-l) / 2;
        if(array[pivot].compareTo(array[mid]) > 0)
            swap(pivot, mid);
        if(array[r].compareTo(array[pivot]) > 0)
            if(array[r].compareTo(array[mid]) < 0)
                swap(pivot, r);
            else
                swap(pivot, mid);
        swap(pivot, l, array);
	}

	private <T extends Comparable<? super T>>int partition(int l, int r, T[] array){
		int j = l+1;
		for(int i=l+1; i <= r; i++){
			if(array[i].compareTo(array[l]) < 0){
				swap(i, j, array);
				j++;
			}
		}
		swap(l, j-1, array);
		return j-1;
	}

	private <T extends Comparable<? super T>> void swap(int x, int y, T[] array){
		T z = array[x];
		array[x] = array[y];
		array[y] = z;
	}

    private void swap(int x, int y){
        int z = x;
        x = y;
        y = z;
    }

}
