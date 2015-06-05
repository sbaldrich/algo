package com.baldrichcorp.toolbox.ds;

public interface ITrie {
	/**
	 * Add a word to the Trie
	 */
	Trie add(String word);
	/**
	 * Find the number of words that begin with the given prefix
	 */
	int countPrefixes(String word);
	/**
	 * Find the number of times the given word is present in the Trie
	 */
	int find(String word);
}
