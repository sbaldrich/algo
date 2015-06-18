package com.baldrichcorp.toolbox.algorithms;

import java.util.Arrays;

/**
 * Solves the RMQ problem by using a Segment Tree. The idea behind it is to use a heap-like structure
 * to store local minimums, i.e., each node represents the {left,right} half of the range represented 
 * by its parent node (excepting the root, which represents the whole array). To query, we cover the 
 * query range by using relevant subranges. 
 * 
 * @author sbaldrich
 *
 */
public class SegmentTreeRMQSolver<T extends Comparable<? super T>> extends RMQSolver<T> {

	int tree[];
	
	private static int left(int x){ return 2*x + 1;}
	private static int right(int x){ return 2*x + 2;}
	
	public SegmentTreeRMQSolver(T[] array) {
		super(array);
		tree = new int[4 * array.length];
		Arrays.fill(tree, -1);
		init(0, 0, array.length - 1);
	}

	private int init(int node, int from, int to){
		if(from == to)
			return tree[node] = from;
		int mid = from + (to - from >> 1);
		int left = init(left(node) , from, mid),
			right = init(right(node), mid + 1, to);
		return tree[node] = elements[left].compareTo(elements[right]) < 0 ? left : right;
	}
	
	private int query(int node, int from, int to, int low, int high){
		if(low >= from && high <= to) // The current range gives useful information
			return tree[node];
		if(low > to || high < from)
			return -1;
		int mid = low + (high - low >> 1);
		int left = query(left(node), from, to, low, mid);
		int right = query(right(node), from, to, mid + 1, high);
		if(left != -1 && right != -1)
			return elements[left].compareTo(elements[right]) < 0 ? left : right;
		if(left != -1)
			return left;
		return right;
	}
	
	@Override
	protected T query(int l, int r) {
		if(l > r){
			int x = r;
			r = l;
			l = x;
		}
		return elements[query(0, l, r, 0, elements.length-1)];
	}

}
