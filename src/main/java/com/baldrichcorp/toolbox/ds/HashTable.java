package com.baldrichcorp.toolbox.ds;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of a Dictionary using a HashTable with open addressing.
 *
 * @author sbaldrich
 */
public class HashTable<K extends Comparable<? super K>,E> implements Dictionary<K,E>{

    protected static class TableEntry<K,E>{
        protected K key;
        protected E element;

        private TableEntry(K key, E element){
            this.key = key;
            this.element = element;
        }

        @Override
        public String toString(){
            return "[" + key + ", " + element + "]";
        }
    }

    protected TableEntry<K,E> table[];
    protected boolean clean[];
    protected int mod;
    protected int size;

    @SuppressWarnings("unchecked")
    public HashTable(int mod){
        this.mod = mod;
        size = 0;
        clean = new boolean[mod];
        Arrays.fill(clean, true);
        table = new TableEntry[mod];
    }

    /**
     * Find the position of element with key {@code key} if it exists or the position
     * where it should be inserted otherwise. 
     * @param key
     * @return
     */
    private int locate(K key){
    	int initial = (key.hashCode() + mod) % mod;
    	//I could add a null-check here to use the first available position
    	//gotta check the implications of that
    	if(clean[initial] || (table[initial] != null && key.equals(table[initial].key)))
    		return initial;
    	int possible = (initial + 1) % mod;
    	while(possible != initial && !clean[possible] && table[possible] != null && !key.equals(table[possible].key))
    		possible = (possible + 1) % mod;
    	return possible;
    }

    @Override
    public E put(K key, E element) {
        int pos = locate(key);
        if(table[pos] != null && !table[pos].key.equals(key)){
        	throw new RuntimeException("Sorry, no more memory available");
        }
        E past = table[pos] == null ? null : table[pos].element;
        if(table[pos] == null){
        	table[pos] = new TableEntry<>(key, element);
        	if(clean[pos]){
        		size++;
        		clean[pos] = false;
        	}
        }
        else{
        	table[pos].element = element;
        }
        return past;
    }
    
    @Override
    public E get(K key) {
        int pos = locate(key);
        if(table[pos] == null || !table[pos].key.equals(key))
        	return null;
        return table[pos].element;
    }

    @Override
    public E remove(K key) {
    	int pos = locate(key);
        if(table[pos] == null || !table[pos].key.equals(key))
        	return null;
        E element = table[pos].element;
        table[pos] = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
