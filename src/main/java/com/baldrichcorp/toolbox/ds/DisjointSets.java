package com.baldrichcorp.toolbox.ds;

import java.util.stream.IntStream;


/**
 * Simple implementation of the disjoint sets data structure with path-compression. 
 * I didn't consider using Union-by-Rank since I've never needed it in the past. 
 * The number of sets in this implementation is static, however adding more elements
 * should be pretty straight-forward.
 * 
 * @author sbaldrich
 */
public class DisjointSets {
	
	int parent[];
	
	public DisjointSets(int elements){
		parent = IntStream.range(0, elements).toArray();
	}
	
	DisjointSets join(int p, int q){
		parent[root(p)] = root(q);
		return this;
	}
	
	int root(int p){
		if(parent[p] == p)
			return p;
		return parent[p] = root(parent[p]);
	}
	
	boolean query(int p, int q){
		return root(p) == root(q);
	}
	
}
