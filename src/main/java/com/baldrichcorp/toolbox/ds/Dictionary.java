package com.baldrichcorp.toolbox.ds;

/**
 * Interface definition of a Dictionary data structure.
 *
 * @author sbaldrich
 */
public interface Dictionary<K extends Comparable<? super K>, E> {

    /**
     * Add a new element with key {@code key}. If there is already an element
     * in the Dictionary with this key, it is replaced and returned by this method.
     * @param key
     * @param element
     * @return The previous element that was related to the given key. {@code null}
     * if there was none.
     */
	E put(K key, E element);

    /**
     * Returns the element related to the given key. {@code null} if there was none.
     * @param key
     * @return
     */
    E get(K key);

    /**
     * Removes and returns the element related to the given key.
     * @param key
     * @return The removed element or {@code null} if there was no element related to it.
     */
    E remove(K key);

    /**
     * Returns the number of elements currently in the Dictionary.
     * @return
     */
    int size();

    /**
     * Checks whether the Dictionary is empty.
     * @return
     */
    boolean isEmpty();

}
