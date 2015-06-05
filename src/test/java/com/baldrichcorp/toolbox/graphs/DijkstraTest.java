package com.baldrichcorp.toolbox.graphs;

import static org.testng.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.graphs.Dijkstra;

public class DijkstraTest {
	
	private static final String TEST_FILE = "dijkstraData.txt";
	//@Test
	public void fileExists(){
		assertNotNull(getClass().getResource("/"+TEST_FILE), "Test file was not found.");
	}
	
	//@Test(dependsOnMethods="fileExists")
	public void dijkstra() throws Exception{
		Dijkstra d = new Dijkstra(201);
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(getClass().getResourceAsStream("/"+TEST_FILE)));
		String line = null;
		while((line = reader.readLine()) != null){
			String[] pars = line.split("\\s+"), edge;
			int p = Integer.parseInt(pars[0]), q, c;
			for(int i=1; i < pars.length; i++){
				edge = pars[i].split(",");
				q = Integer.parseInt(edge[0]);
				c = Integer.parseInt(edge[1]);
				d.connect(p, q, c);
			}
		}
		int dijkstra[] = d.dijkstra(1);
		int vertex[] = new int[]{7,37,59,82,99,115,133,165,188,197};
		int ans[] = new int[]{2599,2610,2947,2052,2367,2399,2029,2442,2505,3068};
		for(int i=0; i<vertex.length; i++)
			assertEquals(dijkstra[vertex[i]], ans[i]);
		
	}
}
