package com.baldrichcorp.toolbox.algorithms;

public interface RMQSolver<T extends Comparable<? super T>> {
	/**
	 * Return the minimum element in the range [l,r]
	 */
	T query(int l, int r);
}
