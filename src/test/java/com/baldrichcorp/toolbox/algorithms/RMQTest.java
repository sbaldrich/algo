package com.baldrichcorp.toolbox.algorithms;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

import org.testng.annotations.Test;


public class RMQTest {
	
	private Integer[] ascIntegers = IntStream.range(1, 10).boxed().toArray(Integer[]::new);
	private Integer[] descIntegers = IntStream.range(1, 10).map(x -> 10 - x).boxed().toArray(Integer[]::new);
	private Integer[] randomIntegers = new Random().ints(900,-100,100).boxed().toArray(Integer[]::new);
	
	@Test
	public void orderedAscending(){
		RMQSolver<Integer> solver = new SQRTDecompositionRMQSolver<Integer>(ascIntegers);
		assertEquals(ascIntegers[0], solver.query(0, 7));
		assertEquals(ascIntegers[1], solver.query(1, 6));
		assertEquals(ascIntegers[5], solver.query(5, 5));
	}
	
	@Test
	public void orderedDescending(){
		RMQSolver<Integer> solver = new SQRTDecompositionRMQSolver<Integer>(descIntegers);
		assertEquals(descIntegers[7], solver.query(0, 7));
		assertEquals(descIntegers[6], solver.query(1, 6));
		assertEquals(descIntegers[5], solver.query(5, 5));
	}
	
	@Test
	public void randomArray(){
		RMQSolver<Integer> solver = new SQRTDecompositionRMQSolver<Integer>(randomIntegers);
		Random random = new Random();
		Iterator<Integer> rand = random.ints(1000,0,randomIntegers.length).iterator();
		for(int i = 0, l = rand.next(), r = rand.next(); i < 100; i++, l = rand.next(), r = rand.next()){
			assertEquals(stupidQuery(randomIntegers, l, r), solver.query(l, r));
		}
		
	}
	
	private Integer stupidQuery(Integer array[], int l, int r){
		if(l > r){
			int aux = l;
			l = r;
			r = aux;
		}
		Integer min = l;
		for(int i=l; i <= r; i++)
			if(array[i].compareTo(array[min]) < 0)
				min = i;
		return array[min];
	}
}
