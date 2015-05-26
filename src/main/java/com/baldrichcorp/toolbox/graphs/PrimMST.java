package com.baldrichcorp.toolbox.graphs;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class PrimMST {

	private static int oo = Integer.MAX_VALUE;
	
	public static long prim(long[][] g) {
		boolean intree[] = new boolean[g.length];
		Arrays.fill(intree, false);
		// q : next node, w : weight of edge that connects us to next node
		int n = g.length, q;
		long w = 0, cost = 0;
		intree[0] = true;
		for (int it = 1; it < n; it++) {
			q = -1;
			for (int i = 0; i < n; i++) {
				if (intree[i]) {
					for (int j = 0; j < n; j++) {
						if (!intree[j] && (g[i][j] < w | q == -1)) {
							q = j;
							w = g[i][j];
						}

					}
				}
			}
			intree[q] = true;
			cost += w;
		}
		return cost;
	}
	
	public static void main(String args[]) throws Exception{
		List<String> lines = Files.readAllLines(Paths.get("D:\\santiago.vargas\\learning\\coursera\\week7\\prim.txt"), Charset.defaultCharset());
		String[] pars = lines.get(0).split("\\s+");
		int n = Integer.valueOf(pars[0]), m = Integer.parseInt(pars[1]);
		long g[][] = new long[n][n]; 
		for(int i = 0; i < n; i++)
			Arrays.fill(g[i], oo);
		for(int i = 1, u, v, c; i <= m; i++){
			pars = lines.get(i).split("\\s+");
			u = Integer.parseInt(pars[0]) - 1;
			v = Integer.parseInt(pars[1]) - 1 ;
			c = Integer.parseInt(pars[2]);
			g[u][v] = g[v][u] = c;
			
		}
		System.out.println(prim(g));
	}
}
