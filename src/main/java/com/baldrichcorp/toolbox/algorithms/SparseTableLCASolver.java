package com.baldrichcorp.toolbox.algorithms;

import java.util.Arrays;

/**
 * Implementation to solve the LCA problem using a Sparse Table. Each M[i][j] on the 
 * table holds the index of the 2^j-th ancestor of node i. Queries are made by using a
 * meta-binary search (look at the comments in the code).
 * 
 * @author sbaldrich
 *
 */
public class SparseTableLCASolver {
	
	private int[][] M; //The dp table.
	private int L[]; //The level (height) of each node.
	private int tree[];
	/**
	 * Constructor. I receive the tree this way to simplify things but
	 * any representation should suffice.
	 * @param T an array that contains the parent of each node in the tree.
	 */
	public SparseTableLCASolver(int T[]) {
		tree = T;
		int n = T.length, log = lg(n) + 1;
		M = new int[n][log];
		L = new int[n];
		for(int[] array : M)
			Arrays.fill(array, -1);
		Arrays.fill(L, -1);
		L[0] = 0;
		//Recursively compute the height of each node
		for(int i=1; i < n; i++){
			level(T, L, i);
		}
		//The parent (2^0th ancestor) of each i is T[i]
		for(int i = 0; i < n; i++)
			M[i][0] = T[i];
		
		//Precompute the DP table.
		for(int j = 1; j < log; j++){
			for(int i = 1; i < n; i++){
				if(M[i][j-1] != -1)
					M[i][j] = M[M[i][j-1]][j-1];
			}
		}
	}
	
	public int query(int p, int q){
		
		//Let's get p pointing to the highest level node
		if(L[p] < L[q]){
			int tmp = p;
			p = q;
			q = tmp;
		}
		
		int log = lg(L[p]); //What is the highest power of 2 we can use to get to L[p]?
		
		//Get p and q at the same level
		for(int i = log; i >= 0; i--)
			if(L[p] - (1 << i) >= L[q])
				p = M[p][i];
		
		if(p == q)
			return p;
		
		//Now that p and q are on the same level of the tree, 
		//let's work our way up the tree until P[p][i] == P[q][i]
		for(int i = log; i >= 0; i-- ){
			if(M[p][i] != -1 && M[p][i] != M[q][i]){
				p = M[p][i];
				q = M[q][i];
			}
		}
		
		return tree[p];
				
	}
	
	/**
	 * Calculates the level of a node on the tree. Values of the 
	 * {@link #L} array are updated.
	 */
	private int level(int[] T, int[] L, int node){
		return L[node] = L[T[node]] != -1 ? L[T[node]] + 1 : level(T,L,T[node]) + 1;  
	}
	
	/**
	 * Compute the [floor of the] base 2 logarithm of the length of the input array
	 * 
	 * @return the floor of the base-2 logarithm of {@code n}
 	 */
	private static int lg( int n ){
		int ans = 0;
		while(n >> ++ans > 0);
		return ans-1;
	}
}
