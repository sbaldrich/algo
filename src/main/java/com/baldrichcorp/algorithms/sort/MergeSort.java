package com.baldrichcorp.algorithms.sort;


public class MergeSort{
	private int[] array;
	private long inv = -1;
	
	public void sort(int[] array){
		this.array = array;
		int n = array.length;
		inv = 0;
		msort(0,n-1);
	}
	
	/**
	 * Returns the number of inversions from the last sort.
	 * @return 
	 */
	public long getInversions(){
		return inv;
	}

	private void msort(int l, int r){
		if(l == r)
			return;
		int n = r - l;
		msort(l,l + n/2);
		msort(l + n/2 + 1, r);
		merge(l, l + n/2 ,l + n/2 + 1, r);
	}

	private void merge(int l1, int r1, int l2, int r2){
		int li = l1, ri = l2, ci = l1;
		int[] aux = new int[array.length];
		while(li <= r1 && ri <= r2){
			if(array[li] <= array[ri])
				aux[ci] = array[li++];
			else{
				aux[ci] = array[ri++];
				inv += r1 - li + 1;
			}
			ci++;
		}
		while(li<=r1)
			aux[ci++] = array[li++];
		while(ri<=r2)
			aux[ci++] = array[ri++];
		System.arraycopy(aux, l1, array, l1, r2-l1 + 1);
	}
}