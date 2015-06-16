package com.baldrichcorp.toolbox.algorithms;

public abstract class RMQSolver<T extends Comparable<? super T>> {
	
	//The input array. Is it necessary? IDK :P
	T elements[];
	
	public RMQSolver(final T[] array){
		this.elements = array;
	} 
	
	/**
	 * Return the minimum element in the range [l,r]
	 */
	protected abstract T query(int l, int r);
}
