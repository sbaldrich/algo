package com.baldrichcorp.toolbox.sorting;

/**
 * MergeSort algorithm => O(nlogn)
 * 
 * A divide and conquer algorithm supported in two main operations: sorting a sub-array 
 * and merging two sorted sub-arrays.
 * 
 * @author sbaldrich
 *
 */

public class MergeSort{
	private int[] array;
	
	public void sort(int[] array){
		this.array = array;
		int n = array.length;
		msort(0,n-1);
	}
	
	private void msort(int left, int right){
		if(left == right)
			return;
		int n = right - left;
		msort(left,left + n/2);
		msort(left + n/2 + 1, right);
		merge(left, left + n/2 ,left + n/2 + 1, right);
	}

	private void merge(int l1, int r1, int l2, int r2){
		int li = l1, ri = l2, ci = l1;
		int[] aux = new int[array.length];
		while(li <= r1 && ri <= r2){
			if(array[li] <= array[ri])
				aux[ci] = array[li++];
			else
				aux[ci] = array[ri++];
			ci++;
		}
		while(li<=r1)
			aux[ci++] = array[li++];
		while(ri<=r2)
			aux[ci++] = array[ri++];
		System.arraycopy(aux, l1, array, l1, r2-l1 + 1);
	}
}