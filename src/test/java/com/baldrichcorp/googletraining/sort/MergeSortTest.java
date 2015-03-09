package com.baldrichcorp.googletraining.sort;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.annotations.Test;
public class MergeSortTest {

	@Test
	public void testMergeSort() {
		int[] shuffled = new int[]{3,5,4,1,7,2,6,9,8};
		int[] sorted =   new int[]{1,2,3,4,5,6,7,8,9};
		MergeSort sorter = new MergeSort();
		sorter.sort(shuffled);
		System.out.println(Arrays.toString(shuffled));
		System.out.println(Arrays.toString(sorted));
		assertTrue(Arrays.equals(shuffled, sorted));
	}
	
	@Test
	public void testInversions(){
		int[] shuffled = new int[]{1,2,3};
		MergeSort sorter = new MergeSort();
		sorter.sort(shuffled);
		assertEquals(sorter.getInversions(), 0);
	}
	
	@Test
	public void testSortAndInversions() throws Exception{
		BufferedReader reader = new BufferedReader
				(new FileReader( new File ("D:\\santiago.vargas\\learning\\coursera\\week1\\IntegerArray.txt")));
		ArrayList<Integer> list = new ArrayList<>();
		String num;
		while( (num = reader.readLine()) != null ){
			list.add(Integer.parseInt(num));
		}
		int[] array = new int[list.size()];
		for(int i=0; i < array.length; i++)
			array[i] = list.get(i);
		MergeSort sorter = new MergeSort();
		sorter.sort(array);
		System.out.println(sorter.getInversions());
	}
}
