package com.baldrichcorp.toolbox.ds;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TrieTest {
	
	private ITrie trie;
	private String[] words = new String[]{"algorithm", "algorithms",
											"algo", "banana", "arya", "algae"};
	@Before
	public void setupTrie(){
		trie = new Trie();
		for(String word : words){
			trie.add(word);
		}
	}
	
	@Test
	public void countWords() {
		Random random = new Random();
		for(String word : words){
			trie.add(word).add(word);
		}
		for(int i = 0; i < 5; i++)
			assertTrue(trie.count(words[random.nextInt(words.length)]) == 3);
	}
	
	@Test
	public void notExistingWordShouldNotBeFound(){
		assertTrue(trie.count("notexisting") == 0);
	}
	
	@Test
	public void countPrefixes(){
		assertTrue(trie.countByPrefix("alg") == 4);
	}
}
