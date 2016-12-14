package com.baldrichcorp.toolbox.ds;

import org.junit.Test;

import static org.junit.Assert.*;

public class DisjointSetsTest {

	@Test
	public void connectivityTest(){
		DisjointSets ds = new DisjointSets(10);
		ds.join(1, 2).join(3, 4).join(5, 6);
		assertFalse(ds.query(1, 6));
		ds.join(2, 3).join(4, 5);
		assertTrue(ds.query(1, 6));
	}


}
