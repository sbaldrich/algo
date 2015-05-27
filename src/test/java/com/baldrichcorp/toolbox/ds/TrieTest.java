package com.baldrichcorp.toolbox.ds;

import java.util.Random;

import org.testng.annotations.*;

import static org.testng.Assert.*;


public class TrieTest {
	
	private ITrie trie;
	private String[] words = new String[]{"algorithm", "algorithms",
											"algo", "banana", "arya", "algae"};
	@BeforeMethod
	public void setupTrie(){
		trie = new Trie();
		for(String word : words){
			trie.addWord(word);
		}
	}
	
	@Test
	public void countWords() {
		Random random = new Random();
		for(String word : words){
			trie.addWord(word).addWord(word);
		}
		assertTrue(trie.find(words[random.nextInt(words.length)]) == 3);
	}
	
	public void countPrefixes(){
		assertTrue(trie.countPrefixes("alg") == 4);
	}
}
