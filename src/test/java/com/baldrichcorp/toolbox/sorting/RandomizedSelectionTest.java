package com.baldrichcorp.toolbox.sorting;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.sorting.RandomizedSelection;

import static org.testng.Assert.assertEquals;

public class RandomizedSelectionTest {
	
	@Test
	public void select() {
		int array[] = new int[]{9, 79, 38, 54, 36, 24, 16, 67, 
				70, 68, 71, 35, 34, 79, 81, 24, 66, 75, 58, 9};
		int sorted[] = Arrays.copyOf(array, array.length);
		Arrays.sort(sorted);
		int stats[] = new int[]{15,4,5,8,10,7,1};
		for(int order : stats){
			assertEquals(RandomizedSelection.select(order,array), 
					sorted[order-1]);
		}
	}
}
