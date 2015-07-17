package com.baldrichcorp.toolbox.sorting;

import java.util.List;

/**
 * Interface that defines a sorting algorithm.
 * 
 * @author sbaldrich
 *
 * @param <T>
 */
public interface Sorter{
	<T extends Comparable<? super T>> void sort(T[] array);
}
