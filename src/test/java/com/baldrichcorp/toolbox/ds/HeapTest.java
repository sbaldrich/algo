package com.baldrichcorp.toolbox.ds;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.ds.MaxHeap;
import com.baldrichcorp.toolbox.ds.IMaxPriorityQueue;

public class HeapTest {
	
	private static final int M = 50000;
	
	@Test
	public void heapifyTest(){
		Integer[] sorted = new Integer[M], unsorted;
		Random rand = new Random();
		for(int i = 0; i < M; i++)
			sorted[i] = 1 + rand.nextInt(100);
		unsorted = Arrays.copyOf(sorted, M);
		Arrays.sort(sorted);
		IMaxPriorityQueue<Integer> heap = new MaxHeap<>(unsorted);
		for(int i = M - 1; i >= 0; i--)
			unsorted[i] = heap.removeMax();
		assertEquals(sorted,unsorted);
	}
	
	@Test
	public void putTest(){
		Integer[] sorted = new Integer[M], heapSorted = new Integer[M];
		Random rand = new Random();
		IMaxPriorityQueue<Integer> heap = new MaxHeap<>(M);
		for(int i = 0; i < M; i++){
			sorted[i] = 1 + rand.nextInt(100);
			heap.put(sorted[i]);
		}
		Arrays.sort(sorted);
		for(int i = M - 1; i >= 0; i--)
			heapSorted[i] = heap.removeMax();
		assertEquals(sorted,heapSorted);
	}
	
}
