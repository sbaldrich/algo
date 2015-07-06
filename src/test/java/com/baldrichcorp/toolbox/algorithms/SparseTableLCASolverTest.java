package com.baldrichcorp.toolbox.algorithms;

import org.testng.annotations.Test;
import static org.testng.Assert.*;


@Test
public class SparseTableLCASolverTest {
	public void simpleTreeTest(){
		int tree[] = new int[]{-1,0,0,0,1,2,3,4,5,5,6,7,10,10,11};
		SparseTableLCASolver solver = new SparseTableLCASolver(tree);
		assertEquals(solver.query(14,7), 7);
		assertEquals(solver.query(7,14), 7);
		assertEquals(solver.query(1,2), 0);
		assertEquals(solver.query(8,9), 5);
		assertEquals(solver.query(13,4), 0);
	}
	
	public void singleBranchTest(){
		int tree[] = new int[]{-1,0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		SparseTableLCASolver solver = new SparseTableLCASolver(tree);
		assertEquals(solver.query(9,4), 4);
		assertEquals(solver.query(10,2), 2);
		assertEquals(solver.query(0,4), 0);
	}
}
