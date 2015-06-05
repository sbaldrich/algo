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
			assertTrue(trie.find(words[random.nextInt(words.length)]) == 3);
	}
	
	@Test
	public void notExistingWordShouldNotBeFound(){
		assertTrue(trie.find("notexisting") == 0);
	}
	
	@Test
	public void countPrefixes(){
		assertTrue(trie.countPrefixes("alg") == 4);
	}
}
