package com.baldrichcorp.algorithms.ds.heaps;

public interface MaxPriorityQueue<T extends Comparable<? super T>> {
	boolean isEmpty();
	T removeMax();
	void put(T element);
	int size();
	T peek();
}
