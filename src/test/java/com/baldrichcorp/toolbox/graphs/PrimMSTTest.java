package com.baldrichcorp.toolbox.graphs;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.graphs.PrimMST;

import static org.testng.Assert.*;

public class PrimMSTTest {
	
	private static int oo = Integer.MAX_VALUE;
	
	private int[][] emptyGraph(int n){
		int [][] graph = new int[n][n];
		for(int i = 0; i < n; i++)
			Arrays.fill(graph[i], oo);
		return graph;
	}
	
	@Test
	public void primShouldWork(){
		long g[][] = new long[][] {{oo,1,2,oo,oo},
								 {1,oo,oo,6,3},
								 {2,oo,oo,4,5},
								 {oo,6,4,oo,7},
								 {oo,3,5,7,oo}};
		assertEquals(PrimMST.prim(g), 10);
	}
	
}
