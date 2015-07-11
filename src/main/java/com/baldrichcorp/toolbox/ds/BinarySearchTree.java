package com.baldrichcorp.toolbox.ds;


public class BinarySearchTree<K extends Comparable<? super K>, E> implements Dictionary<K,E> {

    static class TreeNode<K,E>{
        K key;
        E element;

        TreeNode left, right;

        TreeNode(K key, E element){
            this.key = key;
            this.element = element;
            left = null;
            right = null;
        }

        @Override
        public String toString(){
            return "TreeNode[" + key + " => " + element + "]";
        }
    }

    private TreeNode root;
    private int size;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }
    @Override
    public E put(K key, E element) {
        TreeNode<K,E> p = root, pp = null;
        while(p != null){
            pp = p;
            if(key.compareTo(p.key) < 0)
                p = p.left;
            else if(key.compareTo(p.key) > 0)
                p = p.right;
            else{
                E past = p.element;
                p.element = element;
                return past;
            }
        }
        TreeNode<K,E> node = new TreeNode(key, element);
        if(root != null) {
            if(node.key.compareTo(pp.key) < 0)
                pp.left = node;
            else pp.right = node;
        }
        else
            root = node;
        size++;
        return null;
    }

    @Override
    public E get(K key) {
        TreeNode<K,E> p = root;
        while(p != null){
            if(key.compareTo(p.key) < 0){
                p = p.left;
            }
            else if(key.compareTo(p.key) > 0){
                p = p.right;
            }
            else{
                return p.element;
            }
        }
        return null;
    }

    @Override
    public E remove(K key) {
        TreeNode<K,E> p = root, pp = null;
        while(p != null && !key.equals(p.key)){
            pp = p;
            if(key.compareTo(p.key) < 0)
                p = p.left;
            else if(key.compareTo(p.key) > 0)
                p = p.right;
        }
        if(p == null)
            return null;
        E past = p.element;
        if(p.left != null && p.right != null){
            pp = p;
            TreeNode<K,E> tmp = p.left;
            while(tmp.right != null) {
                pp = tmp;
                tmp = tmp.right;
            }
            p.key = tmp.key;
            p.element = tmp.element;
            p = tmp;
        }
        //pp is pointing to the parent of the node we're about to delete
        TreeNode<K,E> child = null;
        if(p.left == null){
            child = p.right;
        }
        else if(p.right == null){
            child = p.left;
        }
        if(p == root){
            root = child;
        }
        else if(pp.left == p){
            pp.left = child;
        }
        else{
            pp.right = child;
        }
        size--;
        return past;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
