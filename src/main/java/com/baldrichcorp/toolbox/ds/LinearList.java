package com.baldrichcorp.toolbox.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinearList<T> implements ILinearList<T>, Iterable<T>{

	protected T[] array;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public LinearList(int capacity){
		this.array = (T[]) new Object[capacity];
		size = 0;
	}
	
	public LinearList(){
		this(10);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void add(int index, T elem) {
		if(index > size || index < 0)
			throw new NoSuchElementException("Invalid index");
		if(size == array.length){
			T[] old = array;
			array = (T[]) new Object[2*size];
			System.arraycopy(old, 0, array, 0, size);
		}
		array[size++] = elem; 
	}

	@Override
	public T remove(int index) {
		if(index >= size || index < 0)
			throw new NoSuchElementException("Invalid index: " + index);
		T removed = array[index];
		for(int i = index; i < size - 1; i++){
			array[i] = array[i + 1];
		}
		array[--size] = null;
		return removed;
	}

	public T get(int index){
		if(index < 0 || index >= size)
			throw new NoSuchElementException();
		return array[index];
	}
	
	@Override
	public void pushBack(T elem) {
		add(size, elem);
	}

	@Override
	public T popBack() {
		return remove(size-1);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int indexOf(T element) {
		for(int i = 0; i<size; i++ )
			if(array[i].equals(element))
				return i;
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	protected class LinearListIterator implements Iterator<T>{

		LinearList<T> list;
		int currentIndex;
		
		protected LinearListIterator(LinearList<T> list) {
			this.list = list;
		}
		@Override
		public boolean hasNext() {
			return currentIndex < list.size;
		}

		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return list.get(currentIndex++);
		}

		@Override
		public void remove() {
			if(!hasNext())
				throw new NoSuchElementException();
			list.remove(currentIndex);
		}
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinearListIterator(this);
	}
}
