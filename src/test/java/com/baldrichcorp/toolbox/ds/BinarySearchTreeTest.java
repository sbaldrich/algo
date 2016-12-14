package com.baldrichcorp.toolbox.ds;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void removeTheRoot(){
        BinarySearchTree<Integer,String> bst = new BinarySearchTree<>();
        Random random = new Random();
        Set<Integer> keys = new HashSet<>();
        //Get 10 unique keys and add them to the BST
        while(keys.size() < 10){
            keys.add(random.nextInt(100) - 50);
        }
        for(int key : keys){
            bst.put(key, "" + key);
        }
        //Make sure all elements are there
        for(int k : keys){
            assertEquals(""+k , bst.get(k));
        }
        Iterator<Integer> it = keys.iterator();
        //Remove the root
        int rem = it.next();
        bst.remove(rem);
        assertNull(bst.get(rem));
        while(it.hasNext()){
            int key = it.next();
            assertEquals("" + key, bst.get(key));
        }
    }
}
