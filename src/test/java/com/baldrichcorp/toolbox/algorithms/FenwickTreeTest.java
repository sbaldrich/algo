package com.baldrichcorp.toolbox.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FenwickTreeTest {

    @Test
    public void testAccumulatedSums() {
        int A[] = new int[]{1, 2, 3, 1, 4, 2, 1};
        int S[] = new int[]{1, 3, 6, 7, 11, 13, 14};
        FenwickTree ft = new FenwickTree(A);
        for (int i = 0; i < A.length; i++)
            assertEquals(S[i], ft.query(i));
    }
}
