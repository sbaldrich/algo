package com.baldrichcorp.toolbox.ds;

public interface ITrie {

    /**
	 * Add a new word to the Trie.
	 * @param word The word to add.
 	 * @return this Trie with the new word added.
     */
	Trie add(String word);

    /**
     * Find the number of words that begin with the given prefix
     * @param prefix the prefix used for searching.
     * @return an <em>int</em> representing the number of words that start with the given prefix.
     */
	int countByPrefix(String prefix);

    /**
	 * Count the number of times the given word is present in the Trie
     * @param word the word to search for.
     * @return an <em>int</em> representing the number of times the given word has been added to the trie.
	 */
	int count(String word);
}
