package com.baldrichcorp.toolbox.algorithms;

import static java.lang.Math.*;


/**
 * This implementation solves the RMQ problem by precomputing the rmq for slices of size sqrt(n)
 * and using them to answer future queries. Its complexity is <O(n), O(sqrt(n))> (<precalc, lookup>).
 * This is a fast (yet not necessarily easy) way to code a solution to the RMQ problem. 
 * 
 * @author sbaldrich
 * 
 */
public class SQRTDecompositionRMQSolver<T extends Comparable<? super T>> implements RMQSolver<T> {

	
	private T elements[];
	private int lookup[];
	private int slice;
	
	
	public SQRTDecompositionRMQSolver(final T[] input){
		elements = input;
		/* Let s = sqrt(n) with n -> the length of the input array.
		 * The lookup array is going to have ceil(s) elements so we 
		 * can query in ~ O(s). To avoid wasting space, each slot in the
		 * lookup array should contain s or s + 1 elements.
		*/
		lookup = new int[(int)(ceil(sqrt(input.length)))];
		slice = elements.length / lookup.length + (elements.length % lookup.length != 0 ? 1 : 0);
		computeLookup();
	}
	
	@Override
	public T query(int l, int r) {
		if(l > r){
			int aux = l;
			l = r;
			r = aux;
		}
		int min = l, i = l;
		for(i = l; i <= r && i % slice != 0; i++){
			if(elements[i].compareTo(elements[min]) < 0)
				min = i;
		}
		for(;i + slice <= r; i += slice)
			if(elements[lookup[i / slice]].compareTo(elements[min]) < 0)
				min = lookup[i / slice];
		for(; i <= r; i++){
			if(elements[i].compareTo(elements[min]) < 0)
				min = i;
		}
		return elements[min];
	}
	
	private void computeLookup(){
		int i, j, min = 0;
		for(i = 0, j = 0; i < elements.length; i++){
			if(i != 0 && i % slice == 0){
				lookup[j++] = min;
			}
			if(i % slice == 0 || elements[i].compareTo(elements[min]) < 0)
				min = i;
		}
		lookup[j] = min;
		
	}

}
