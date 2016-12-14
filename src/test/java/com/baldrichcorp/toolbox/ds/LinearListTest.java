package com.baldrichcorp.toolbox.ds;


import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

public class LinearListTest {
	@Test
	public void testIterator() {
		LinearList<Integer> list = new LinearList<>();
		for(int i = 0; i < 10; i++ )
			list.pushBack(i);
		assertTrue(list.size == 10);
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext())
			iterator.remove();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void addAndRemoveShouldProperlyModifySize(){
		int n = 100;
		ILinearList<String> list = new LinearList<>(); 
		Random random = new Random();
		for(int i = 0; i < n; i++){
			list.pushBack(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		for(int i = 0; i < n / 2; i++){
			list.remove(random.nextInt(list.size()));
		}
		assertTrue(list.size() == n/2);
	}
	
	@Test
	public void removeActuallyRemovesTheCorrectElements(){
		int n = 15;
		String strings[] = new String[n];
		ILinearList<String> list = new LinearList<>();
		for(int i=0; i<n; i++){
			strings[i] = UUID.randomUUID().toString().replaceAll("-", "");
			list.pushBack(strings[i]);
		}
		for(int i=0; i < n / 2 + (n % 2); i++){
			list.remove(i);
		}
		for(int i=0,j=1; i<list.size();i++,j+=2){
			if(!list.get(i).equals(strings[j]))
				fail("The strings do not match");
		}
	}
	
	@Test
	public void theListResizesProperly(){
		ILinearList<Integer> list = new LinearList<>(1);
		for(int i=0; i<100; i++)
			list.pushBack(i);
	}
}
