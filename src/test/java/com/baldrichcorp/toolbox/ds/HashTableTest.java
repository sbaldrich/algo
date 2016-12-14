package com.baldrichcorp.toolbox.ds;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {

    @Test
    public void testPut(){
        HashTable<Integer, String> ht = new HashTable<>(7);
        assertTrue(ht.isEmpty());
        ht.put(0, "zero");
        assertFalse(ht.isEmpty());
        assertTrue(ht.size() == 1);
        ht.put(0, "zero");
        assertTrue(ht.size() == 1);
        ht.put(7, "seven");
        assertTrue(ht.size() == 2);
        //Even though 7 % 7 = 0, open addressing scheme
        //puts this element on position 1
        ht.table[1].key.equals(7);
    }


}
