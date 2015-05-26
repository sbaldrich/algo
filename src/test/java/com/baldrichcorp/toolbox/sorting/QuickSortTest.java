package com.baldrichcorp.toolbox.sorting;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.sorting.QuickSort;

public class QuickSortTest {

	@Test
	public void testQuickSort() {
		int[] shuffled = new int[] { 3, 5, 4, 1, 7, 2, 6, 9, 8 };
		int[] sorted = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		QuickSort sorter = new QuickSort();
		sorter.sort(shuffled);
		System.out.println(Arrays.toString(shuffled));
		System.out.println(Arrays.toString(sorted));
		assertTrue(Arrays.equals(shuffled, sorted));
	}

}
