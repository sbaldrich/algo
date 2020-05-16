/*
 * Solution for the Range Minimum Query problem using a sparse table approach.
 * Given an array A[0..n], construct a lookup table T[0..n][0..lg(n)] where T[i][j]
 * contains the index of the minimum element of the subarray of size 2^j starting at i.
 * This setup allows to compute the lookup table in O(nlgn).
 *
 * To get the rmq(l,r), we can query the table in O(1) by choosing two slices that completely
 * cover the subarray A[l,r], e.g, let k = lg(l - r), then rmq(l,r) = min(A[T[l][k]], A[T[r - k][k]]).
 */
import java.util.Arrays;

class RangeMinimumQuery{

	int T[][];
	int A[];

	void preprocess(int arr[]){
		A = arr;
		int n = arr.length;
		int k = binlog(n);
		T = new int[n][k + 1];

		for(int i = 0; i < n; i++){
			T[i][0] = i; // A[i] is always the min subarray A[i..i]
		}

		for(int j = 1, l, r; j <= k; j++){
			for(int i = 0; i + (1 << j) <= n; i++){
				l = T[i][j - 1];
				r = T[i + (1 << j - 1)][j - 1];
				T[i][j] = A[l] < A[r] ? l : r;
			}
		}
	}

	int query(int l, int r){
		int k = binlog(r - l); // When querying, we don't care if [l, k] and [r - k, r] overlap.
		return Math.min(A[T[l][k]], A[T[r - k][k]]);
	}


	int binlog(int n){
		return 31 - Integer.numberOfLeadingZeros(n);
	}
	
}
