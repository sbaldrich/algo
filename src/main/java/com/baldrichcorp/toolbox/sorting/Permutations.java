package com.baldrichcorp.toolbox.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.stream.IntStream;


public class Permutations {
    static int xx = 0;

    /**
     * Rearranges the given array into the next lexicographically greater permutation.
     * The algorithm to find the next permutation of array A of length n is outlined below:
     * <p>
     * let k be the largest index such that A[k] < A[k+1]
     * return false if there is no such index
     * let l be the largest index such that A[k] < A[l]
     * swap the elements in positions k and l
     * reverse the sub-array A[k+1, n]
     * return true
     * </p>
     *
     * @param array the array whose permutation is to be found
     * @return <tt>true</tt> if a next permutation existed, <tt>false</tt> otherwise.
     */
    public static <T extends Comparable> boolean nextPermutation(T[] array) {
        if (array == null) throw new IllegalArgumentException("The given array must not be null");
        if (array.length < 2) return false;
        int k = array.length - 1;
        int l = array.length;
        while (--k >= 0) {
            if (array[k].compareTo(array[k + 1]) < 0)
                break;
        }
        if (k == -1)
            return false;
        while (array[--l].compareTo(array[k]) <= 0) ;
        swap(array, k, l);
        Collections.reverse(Arrays.asList(array).subList(k + 1, array.length));
        return true;
    }

    private static <T> void swap(T[] array, int pos1, int pos2) {
        T tmp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = tmp;
    }

    public static <T extends Comparable> void heaps(T[] array, Consumer<T[]> f){
        heaps(array.length, array, f);
    }

    /**
     * Generates all permutations of an array using Heap's algorithm (https://en.wikipedia.org/wiki/Heap%27s_algorithm)
     * and uses a Consumer to execute a function on each one.
     *
     * @param n     the length of the array.
     * @param array the array to be processed.
     * @param f     the function to be applied to each permutation.
     */
    private static <T extends Comparable> void heaps(int n, T[] array, Consumer<T[]> f) {
        if (n == 1)
            f.accept(array);
        else {
            for (int i = 0; i < n - 1; i++) {
                heaps(n - 1, array, f);
                if (n % 2 == 0)
                    swap(array, i, n - 1);
                else
                    swap(array, 0, n - 1);
            }
            heaps(n - 1, array, f);
        }
    }
}
