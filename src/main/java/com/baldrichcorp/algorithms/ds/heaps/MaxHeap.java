package com.baldrichcorp.algorithms.ds.heaps;


/**
 * Implementation of a (max) priority queue using a heap. 
 * 
 * @author sbaldrich
 */
public class MaxHeap<T extends Comparable<? super T>> implements MaxPriorityQueue<T> {

	private T[] heap;
	private int size;
	
	@SuppressWarnings("unchecked")
	public MaxHeap(int initialCapacity){
		if(initialCapacity <= 0)
			throw new IllegalArgumentException("Size must be non-negative");
		heap = (T[]) new Comparable[initialCapacity + 1];
		size = 0;
	}
	
	public MaxHeap(){
		this(10);
	}
	
	/**
	 * Creates a heap from an array by heapifying it.
	 */
	@SuppressWarnings("unchecked")
	public MaxHeap(T[] array){
		heap = (T[]) new Comparable[array.length + 1]; 
		System.arraycopy(array, 0, heap, 1, array.length);
		size = array.length;
		for(int root = size / 2; root > 0; root--){
			T rootElement = heap[root];
			int child = 2 * root;
			while(child <= size){
				if(child < size && heap[child].compareTo(heap[child+1]) < 0)
					child++;
				if(rootElement.compareTo(heap[child]) >= 0)
					break;
				heap[child / 2] = heap[child];
				child *= 2;
			}
			heap[child / 2] = rootElement;
		}
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	/**
	 * Remove and return the element with the highest priority in the queue.
	 * Returns <code>null</code> if the heap is empty.
	 */
	public T removeMax() {
		if(isEmpty())
			return null;
		T maxElement = heap[1];
		T lastElement = heap[size--];
		int curNode = 1, child = 2;
		while(child <= size){
			if(child < size && heap[child].compareTo(heap[child+1]) < 0)
				child++;
			if(heap[child].compareTo(lastElement) <= 0)
				break;
			heap[curNode] = heap[child];
			curNode = child;
			child *= 2;
		}
		heap[curNode] = lastElement;
		return maxElement;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void put(T element) {
		if(size == heap.length - 1){
			T[] old = heap;
			heap = (T[]) new Comparable[2*size];
			System.arraycopy(old, 0, heap, 0, size);
		}
		int curNode = ++size;
		while(curNode != 1 && heap[curNode / 2].compareTo(element) < 0){
			heap[curNode] = heap[curNode / 2];
			curNode /= 2;
		}
		heap[curNode] = element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	/**
	 * Get the element with the highest priority in the heap without removing it.
	 * Returns <code>null</code> if the heap is empty.
	 */
	public T peek() {
		return size > 0 ? heap[1] : null;
	}

}
