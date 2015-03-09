package com.baldrichcorp.googletraining.sort;

public class QuickSort{
	private int[] array;
	private int comparisons = 0;

	public void sort(int[] array){
		comparisons = 0;
		this.array = array;
		qs(0,array.length-1);
	}

	private void qs(int l, int r){
		if(r<=l)
			return;
		comparisons += r-l;
		pivot(l,r);
		int p = partition(l,r);
		qs(l,p-1);
		qs(p+1,r);
	}

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
	
	public int getComparisons(){
		return comparisons;
	}
}