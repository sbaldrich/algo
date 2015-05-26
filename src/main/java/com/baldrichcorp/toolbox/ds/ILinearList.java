package com.baldrichcorp.toolbox.ds;

public interface ILinearList<T> extends Iterable<T>{
	void add(int index, T elem);
	T remove(int index);
	T get(int index);
	void pushBack(T elem);
	T popBack();
	int size();
	int indexOf(T element);
	boolean isEmpty();
}
