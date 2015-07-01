package com.baldrichcorp.toolbox.algorithms;

import org.testng.annotations.Test;
import static org.testng.Assert.*;
@Test
public class RabinKarpTest {
	
	public void simpleTest(){
		String txt[] = {"abracadabrabracadabra", "aaaaaaaa", "hello", "loremimloremipsuloremipsumlorl"};
		String pat[] = {"abracadabra", "aaaa", "ell", "loremipsum"};
		for(int i=0; i < pat.length; i++){
			RabinKarp rk = new RabinKarp(pat[i]);
			assertEquals(rk.search(txt[i]), txt[i].indexOf(pat[i]));
		}
	}
}
