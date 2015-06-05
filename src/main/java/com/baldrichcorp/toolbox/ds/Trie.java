package com.baldrichcorp.toolbox.ds;

public class Trie implements ITrie {

	private static class Node{
		/**
		 * The character this node represents
		 */
		char ch;
		/**
		 * The number of words that end on this node
		 */
		int words;
		/**
		 * The number of words that start with the current prefix
		 */
		int prefix;
		private Node edges[] = new Node[26];
		
		public Node at(char ch, boolean create){
			int index = ch - 'a';
			return edges[index] == null && create ? edges[index] = new Node(ch) : edges[index];
		}
		
		public Node at(char ch){
			return at(ch, true);
		}
		
		public Node(char ch){
			this.ch = ch;
			words = 0;
			prefix = 0;
		}
		
		public enum Property{
			PREFIX, WORDS
		}
	
	}
	
	Node root = new Node('*');
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trie add(String word) {
		add(root, word.toCharArray(), 0);
		return this;
	}
	
	private void add(Node node, final char[] word, int index){
		if(index == word.length){
			node.words++;
			return;
		}
		node.prefix++;
		add(node.at(word[index]), word, index+1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countPrefixes(String word) {
		return get(root, word.toCharArray(), 0, Node.Property.PREFIX);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int find(String word) {
		return get(root, word.toCharArray(), 0, Node.Property.WORDS);
	}
	
	private int get(Node node, final char[] word, int index, final Node.Property prop){
		if(index == word.length){
			//This kind of approach (using the same method to obtain different fields)
			//could be improved using the Template pattern with reflection.
			if(prop == Node.Property.WORDS)
				return node.words;
			else if (prop == Node.Property.PREFIX)
				return node.prefix;
		}
		Node next = node.at(word[index], false);
		if(next != null)
			return get(next, word, index+1, prop);
		return 0;
	}

}
