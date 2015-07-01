package com.baldrichcorp.toolbox.algorithms;

import java.math.BigInteger;
import java.util.Random;

/**
 * Implementation of the Rabin-Karp algorithm for string matching. Its rolling hash
 * idea can be applied to many string-related problems, e.g LCP, LCS, etc. This particular
 * implementation 'gambles' that there is a match if the hash values obtained for a given 
 * string using two different (R,M) pairs are equal. 
 * 
 * @author sbaldrich
 *
 */
public class RabinKarp {
	
	private long radix; //Radix
	private long checkRadix; 
	private long checkModulo;
	private long modulo; //Modulo
	private long RM; //R^(m-1) % M for removing the leading character.
					 //Clearly, this could be computed using powermod.
	private long patHash;
	private long checkHash;
	private int m, n;
	
	public RabinKarp(String pattern){
		modulo = generatePrime();
		checkModulo = generatePrime();
		radix = 256;
		checkRadix = 512;
		m = pattern.length();
		RM = 1;
		for(int i=1; i <= m-1; i++){
			RM = (radix * RM) % modulo;
		}
		patHash = hash(pattern, radix, modulo);
		checkHash = hash(pattern, checkRadix, checkModulo);
	}
	
	public static long generatePrime(){
		return BigInteger.probablePrime(31, new Random()).longValue();
	}
	
	public long hash(String string, long radix, long mod){
		long hash = 0;
		for(int i = 0; i < m; i++){
			hash = (hash * radix + string.charAt(i)) % mod;
		}
		return hash;
	}
	
	/**
	 * Search the text passed as parameter for the pattern represented
	 * by this class.
	 * @param text The text to search.
	 * @return the index of the first match. The length of {@code txt} if there is no
	 * match.
	 */
	public int search(String text){
		int n = text.length();
		if(n < m){
			return n;
		}
		
		long txtHash = hash(text, radix, modulo);
		
		if(txtHash == patHash){
			return 0;
		}
		for(int i = m; i < n; i++){
			txtHash = (txtHash + modulo - RM * text.charAt(i-m) % modulo) % modulo;
			txtHash = (txtHash * radix + text.charAt(i)) % modulo;
			int offset = i - m + 1;
			if(patHash == txtHash && 
					checkHash == hash(text.substring(offset,offset + m), checkRadix, checkModulo))
				return offset;
		}
		return n;
	}
}
