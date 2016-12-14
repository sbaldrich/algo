package com.baldrichcorp.toolbox.graphs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.Assert.*;


public class KosarajuTest {

    private static final String TEST_FILE = "SCC.txt";

    //@Test
    public void fileExists() {
        assertNotNull(getClass().getResource("/" + TEST_FILE));
    }


    //@Test(dependsOnMethods="fileExists")
    public void kosaraju() throws Exception {

        Kosaraju k = new Kosaraju();
        BufferedReader reader = new BufferedReader
                (new InputStreamReader(getClass().getResourceAsStream("/" + TEST_FILE)));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String pars[] = line.split("\\s+");
            int tail = Integer.parseInt(pars[0]) - 1;
            for (int i = 1; i < pars.length; i++) {
                k.join(Integer.parseInt(pars[i]) - 1, tail);
                k.joinRev(tail, Integer.parseInt(pars[i]) - 1);
            }
        }
        for (Integer p : k.graph.keySet()) {
            if (!k.visited[p])
                k.buildStack(p);
        }
        Arrays.fill(k.visited, false);
        ArrayList<Integer> sccSizes = new ArrayList<>();
        while (!k.stack.empty()) {
            int p = k.stack.pop();
            if (k.visited[p])
                continue;
            sccSizes.add(k.getComponent(p));
        }
        Collections.sort(sccSizes, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }

        });
        int topSizes[] = new int[]{434821, 968, 459, 313, 211};
        for (int i = 0; i < topSizes.length; i++)
            assertEquals(topSizes[i], sccSizes.get(i).intValue());


    }
}
