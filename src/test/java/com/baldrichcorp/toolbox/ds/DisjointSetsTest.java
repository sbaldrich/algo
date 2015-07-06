package com.baldrichcorp.toolbox.ds;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

@Test
public class DisjointSetsTest {
	
	public void connectivityTest(){
		DisjointSets ds = new DisjointSets(10);
		ds.join(1, 2).join(3, 4).join(5, 6);
		assertFalse(ds.query(1, 6));
		ds.join(2, 3).join(4, 5);
		assertTrue(ds.query(1, 6));
	}
}
