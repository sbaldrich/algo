package com.baldrichcorp.toolbox.ds;

public interface IMaxPriorityQueue<T extends Comparable<? super T>> {
	boolean isEmpty();
	T removeMax();
	void put(T element);
	int size();
	T peek();
}
