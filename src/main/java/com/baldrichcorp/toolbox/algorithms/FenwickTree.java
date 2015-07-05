package com.baldrichcorp.toolbox.algorithms;

import java.util.Arrays;

/**
 * Implementation of the Fenwick Tree (A.K.A Binary Indexed Tree). It is a heap-like structure
 * on which each node contains the sum of its left subtree. Queries and updates are done by
 * making use of some simple (read as fu**ing amazing) bit-based functions.
 * 
 * @author sbaldrich
 **/
/*
 Given an array of integers, we can obtain the sum (this can be any accumulating binary function) 
 of any sub-array by precomputing an array of successive sums and using it to perform queries. 
 For example, let A be an integer array, S is the sum array and let S_ij represent the sum of numbers
 from position i to position j:
 
 A = [ 1  2  3  1  4  2  1 ]
 S = [ 1  3  6  7 11 13 14 ]
       1  2  3  4  5  6  7
 Using this approach, we can perform queries in O(1) (S_ij = S[j] - S[i]). However, if we need to update
 some values, we'd have to update the whole sum array (if position j is updated, all k > j must be updated
 as well), getting an O(n) complexity.
 
 Now, let's change the way we store the sums a little. Let's use a tree where each node contains the sum
 of the element it represents and all the values represented by the nodes on its left subtree (notice the way
 the nodes are numbered, this is not a coincidence):
                         
                 4
               [ 7 ]
              /     \
           2           6
         [ 3 ]       [ 6 ]
         /   \       /   \
        1     3     5     7
      [ 1 ] [ 3 ] [ 4 ] [ 1 ]

If we wanted to calculate the sum up to position 5, we can traverse the tree starting from node 5 and
moving up to the root. We should add the values from the visited nodes every time we go up a via a right
child. So S_15 would be tree[5] + (don't add tree[6]) + tree[4] = 11. In order to update the value at some 
position, we should let all nodes that have the node to update on their left subtree (all nodes on the path
from the node to update to the root that are accessed via a left child) that its value has changed. For example, 
to update A[1], we should also update tree[2] and tree[4].

Given that we only need the values of nodes to which we get by traversing a {right,left) child, it is possible 
to waste many steps if the tree is large enough. Enter binary numbers.
      
Representing indexes as binary numbers, we get 

                100
               [ 7 ]
              /     \
          011         110
         [ 3 ]       [ 6 ]
         /   \       /   \
       001   110    101   111
      [ 1 ] [ 3 ] [ 4 ]  [ 1 ]

Now, a very, very cool observation made by Fenwick is that if we unset the last 1 from the binary representation
of the index of a node, we get the index of the next node in the path to the root that is accessed via a right child
edge, i.e. the next node we need. How to unset the last 1 bit?

Let bi be the binary representation of the index i. So bi = a1b where a is some combination of 0's and 1's and b is all
zeroes. ~bi = (~a)0(~b) = (~a)0(11..1). => ~bi + 1 = (~a)1(00..0). Remember now that the binary representation of -i 
is exactly ~bi + 1 so bi v (~bi + 1) => i & -i would give us exactly that last 1 we're looking for.

To get the index of the next node in the path to the root that is accessed via a left child edge, we simply add that 
last bit we obtained before. Say we're updating node 1 (001), adding the last set bit, we get 010, so we update node 3.
We add the last set bit once again (001) and get 100, so we update node 4.

I hope this comment is specific enough to not let me forget (or help me remember if needed) the utterly magnificent data
structure that is the Fenwick Tree.
*/
public class FenwickTree {
	int[] tree;
	
	public FenwickTree(int[] array){
		tree = new int[1 + array.length];
		for(int i=1; i < tree.length; i++){
			update(i, array[i-1]);
		}
	}
	
	public void update(int pos, int val){
		while(pos < tree.length){
			tree[pos] += val;
			pos += lb(pos);
		}
	}
	
	public int query(int pos){
		int ans = 0;
		pos += 1;
		while(pos > 0){
			ans += tree[pos];
			pos -= lb(pos);
		}
		return ans;
	}
	
	/**
	 * Lowbit function. Obtains the lowest bit that is set in an integer. 
	 */
	public static int lb(int i){
		return i & -i;
	}
	
}
