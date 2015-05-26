package com.baldrichcorp.toolbox.sorting;

/** QuickSort algorithm => O(nlogn)
 * 
 * Divide and conquer algorithm based on the Partition subroutine.
 *
 * It basically chooses a pivot, stores it on the first position of the sub-array and "partitions"
 * the remainder of the array in a way such that all elements less than (or equal) to the pivot
 * appear before those that are greater than it. It is superior to MergeSort because it works
 * in-place and is much cooler and all the girls want to hang out with it.
 * 
 * @author sbaldrich
 *
 */
public class QuickSort{ private int[] array;

	public void sort(int[] array){
		this.array = array;
		qs(0,array.length-1);
	}

	private void qs(int l, int r){
		if(r<=l)
			return;
		pivot(l,r);
		int p = partition(l,r);
		qs(l,p-1);
		qs(p+1,r);
	}

	/*
	 * The pivot can be chosen in a variety of different ways. This particular implementation
	 * takes the median of the left-side, mid and right-side elements. A random selection would 
	 * suffice as well. 
	 */
	private void pivot(int l, int r){
		int x = array[l], y = array[(r-l)/2] , z = array[r], p = l;
		if( (x <= y && y <= z) || (z <= y && y <= x))
			p = (r-l)/2;
		else if( (y <= x && x <= z) || (z <= x && x <= y))
			p = l;
		else p = r;
		swap(p,l);
	}

	private int partition(int l, int r){
		int j = l+1;
		for(int i=l+1; i <= r; i++){
			if(array[i] < array[l]){
				swap(i, j);
				j++;
			}
		}
		swap(l,j-1);
		return j-1;
	}

	private void swap(int x, int y){
		int z = array[x];
		array[x] = array[y];
		array[y] = z;	
	}
	
}
