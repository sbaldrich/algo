package com.baldrichcorp.toolbox.algorithms;

/**
 * This implementation solves the RMQ problem by creating a lookup table M[i][j] where
 * M[i][j] = RMQ(i, i + 2 ^ (j-1)). It has a complexity of <O(nlg(n), O(1)>, which makes
 * it a pretty good choice for programming contests. The sparse table approach can be applied
 * to various problems, including a direct solution (i.e. not going through RMQ) to the LCA 
 * problem.
 * 
 * @author sbaldrich
 * 
 */
public class SparseTableRMQSolver<T extends Comparable<? super T>> extends RMQSolver<T> {

	private int[][] lookup;
	
	public SparseTableRMQSolver(final T input[]){
		super(input);
		elements = input;
		int n = input.length, lg = lg(n);
		lookup = new int[n][lg + 1];
		for(int i=0; i<n; i++){
			lookup[i][0] = i;
		}
		/*
		 * So, for each slice i,2^(j-1) we should compute the rmq. 
		 * Obviously lookup[i][0] = i f.a i. Now, for 1 <= j < n, 
		 * we get our answer by looking at the lower and higher halves
		 * of the current slice (M[i][j-1] and M[i+2^(j-1)][j-1].
		 */
		for(int j = 1; j <= lg; j++){
			for(int i=0; i + (1 << j) <= n; i++){
				if(elements[lookup[i][j-1]]
						.compareTo(elements[lookup[i + (1 << j - 1)][j-1]]) < 0){
					lookup[i][j] = lookup[i][j-1];
				}
				else lookup[i][j] = lookup[i + (1 << j - 1)][j-1];
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T query(int l, int r) {
		if(r < l){
			int x = r;
			r = l;
			l = x;
		}
		if(r == l)
			return elements[r];
		int lg = lg(r-l);
		/*
		 * Querying is done in a similar fashion to the construction of the lookup table,
		 * we just find a couple of slices that fully cover the range we're interested on.
		 */
		T left = elements[lookup[l][lg]],
				right = elements[lookup[r - ((1 << lg) - 1)][lg]];
		if(left.compareTo(right) <= 0)
			return left;
		return right;
	}

	/**
	 * Compute the [floor of the] base 2 logarithm of the length of the input array
	 * 
	 * @param n
	 * @return the floor of the base-2 logarithm of {@code n}
 	 */
	private static int lg( int n ){
		int ans = 0;
		while(n >> ++ans > 0);
		return ans-1;
	}
}
