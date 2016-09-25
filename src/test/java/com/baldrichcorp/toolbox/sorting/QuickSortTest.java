package com.baldrichcorp.toolbox.sorting;

import org.testng.annotations.Test;

@Test
public class QuickSortTest extends SortingAlgorithmTest{
	public QuickSortTest(){
		super(QuickSort.instance());
	}
}
