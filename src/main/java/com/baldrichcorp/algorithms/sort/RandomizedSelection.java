package com.baldrichcorp.algorithms.sort;

import java.util.Arrays;
import java.util.Random;

public class RandomizedSelection {
	
	public static void main(String[] args) {
		int array[] = new int[]{9, 79, 38, 54, 36, 24, 16, 67, 
				70, 68, 71, 35, 34, 79, 81, 24, 66, 75, 58, 9};
		int sorted[] = Arrays.copyOf(array, array.length);
		Arrays.sort(sorted);
		int stats[] = new int[]{15,4,5,8,10};
		for(int i : array)
			System.out.printf("%4d", i);
		System.out.println();
		for(int i : sorted)
			System.out.printf("%4d", i);
		System.out.println();
		for(int i=0; i<array.length; i++)
			System.out.printf("%4d", i);
		System.out.println();
		
		for(int order : stats){
			System.out.printf("order: %d [sel : %d ans : %d]\n", order, select(order,array), 
					sorted[order-1]);
		}
	}
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
		int lim = -1;
		for(int i = l+1; i <= r; i++ )
			if(array[i] < array[l] && lim > -1)
				swap(lim++,i);
			else if(lim < 0)
				lim = i; 
		swap(lim-1,l);
		return lim-1;
	}
	
	private static void swap(int x, int y){
		int tmp = array[x]; 
		array[x] = array[y];
		array[y] = tmp;
	}
	
}
