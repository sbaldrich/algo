package com.baldrichcorp.toolbox.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the Knuth-Morris-Pratt algorithm. It defines a "Failure Function" that provides a
 * way to scan through a text T searching for pattern P in O(|T|)
 *
 * @author sbaldrich
 *
 */

public class KMP {

	private int F[]; //Failure function
	private char[] pat;
	private int m; //The length of the pattern (not necessary but mñeh)

	public KMP(final String pattern){

		pat = pattern.toCharArray();
		m = pattern.length();
		F = new int[m + 1];

		for(int i=2, j; i <= m; i++){
			j = F[i-1];
			//Try to expand the match
			if(pat[j] == pat[i-1]){
				F[i] = j + 1;
				continue;
			}
			//We couldn't, so we try to expand the previous one.
			while(j > 0 && pat[j] != pat[i-1]){
				j = F[j];
			}
			//In the end, either we expand a proper prefix or we
			//expand the empty string.
			F[i] = pat[j] != pat[i-1] ? 0 : j + 1;
		}
	}

	/**
	 * Return a {@code List} containing the indexes where the pattern represented
	 * by this {@code KMP} object appear.
	 * @param text The text to scan through
	 * @return A List of Integers
	 */
	public List<Integer> match(String text){
		int n = text.length(), pi = 0, ti = 0;
		char[] txt = text.toCharArray();
		ArrayList<Integer> matches = new ArrayList<>();
		while(ti < n){
			if(pi == m){
				matches.add(ti - m);
				pi = F[pi];
			}
			if(txt[ti] == pat[pi]){
				pi++;
				ti++;
			}
			else if(pi > 0){
				pi = F[pi];
			}
			else{
				ti++;
			}
		}
		if(pi == m){
			matches.add(ti - m);
			pi = F[pi];
		}
		return matches;
	}

}
