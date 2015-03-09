package com.baldrichcorp.algorithms.sort;

import java.util.Random;

/**
 * Randomized Selection algorithm => O(logn)
 * 
 * A very cool recursive algorithm for finding order statistics in an unsorted array. It is based 
 * on the partition subroutine (the same as QuickSort). 
 * 
 * @author sbaldrich
 *
 */
public class RandomizedSelection {
	
	private static Random random = new Random();
	private static int[] array;
	
	public static int select(int order, int[] in){
		array = in;
		return subselect(0,array.length-1,order - 1);
	}
	
	private static int subselect(int l, int r, int order){
		if(l == r)
			return array[l];
		pivot(l,r);
		int p = partition(l,r);
		if(p - l == order)
			return array[p];
		if(p - l > order)
			return subselect(l, p-1, order);
		else return subselect(p+1,r, order - p + l - 1);
	}
	
	private static void pivot(int l, int r){
		int p = random.nextInt(r-l) + l;
		swap(p,l);
	}
	
	private static int partition(int l, int r){ 
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
	
	private static void swap(int x, int y){
		int tmp = array[x]; 
		array[x] = array[y];
		array[y] = tmp;
	}
	
}
