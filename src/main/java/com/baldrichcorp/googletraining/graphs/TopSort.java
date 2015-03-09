package com.baldrichcorp.googletraining.graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class TopSort {
	
	ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	private int order[];
	private boolean visit[];
	private int index;
	public TopSort(int n){
		order = new int[n];
		visit = new boolean[n];
		while(n-- > 0)
			graph.add(new ArrayList<Integer>());
	}
	
	public void connect(int p, int q){
		graph.get(--p).add(--q);
	}
	 
	public int[] topSort(){
		final int n = graph.size();
		Arrays.fill(visit, false);
		index = n;
		for(int i = 0; i < n; i++)
			if(!visit[i])
				dfs(i);
		return order;
	}
	
	private void dfs(int node){
		visit[node] = true;
		for(int v : graph.get(node))
			if(!visit[v])
				dfs(v);
		order[node] = index--;
	}
	
	public static void main(String[] args) {
		TopSort ts = new TopSort(7);
		int[][] edges = {{1,2},{2,3},{2,4},
						{3,4},{4,5},{4,6},
						{7,5},};
		for(int[] edge : edges)
			ts.connect(edge[0], edge[1]);
		System.out.println(Arrays.toString(ts.topSort()));
		ts = new TopSort(9);
		edges = new int[][]{{9,8},{5,1},{8,7},
							{3,4},{7,6},{4,2},
							{2,5},{6,5},{6,4},
							{9,7}, {6,3}};
		for(int[] edge : edges)
			ts.connect(edge[0], edge[1]);
		System.out.println(Arrays.toString(ts.topSort()));
		
	}
	
	
}
