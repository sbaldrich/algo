package com.baldrichcorp.toolbox.ds;

public interface ITrie {
	Trie addWord(String word);
	int countPrefixes(String word);
	int find(String word);
}
