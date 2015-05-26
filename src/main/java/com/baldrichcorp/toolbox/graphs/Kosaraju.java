package com.baldrichcorp.toolbox.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Kosaraju's algorithm for finding SCCs. 
 * A dfs on G' (the graph with the direction of the edges inverted)
 * gives the order in which a dfs on G would obtain the SCCs. This
 * is one of my favorite algoriths. 
 * 
 * @author sbaldrich
 *
 */


public class Kosaraju {
	public final int M = 875714;
	public HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(M);
	public HashMap<Integer, ArrayList<Integer>> reversed = new HashMap<>(M);
	public void join(int p, int q){
		if(graph.get(p) == null)
			graph.put(p,new ArrayList<Integer>());
		if(graph.get(q) == null)
			graph.put(q,new ArrayList<Integer>());
		graph.get(p).add(q);
	}
	public void joinRev(int p, int q){
		if(reversed.get(p) == null)
			reversed.put(p,new ArrayList<Integer>());
		if(reversed.get(q) == null)
			reversed.put(q,new ArrayList<Integer>());
		reversed.get(p).add(q);
	}
	public boolean visited[] = new boolean[M];
	
	public Stack<Integer> stack = new Stack<>();
	public Stack<Integer> dfsStack = new Stack<>();
	public void buildStack(int start){
		dfsStack.push(start);
		visited[start] = true;
		boolean done = true;
		while(!dfsStack.empty()){
			int p = dfsStack.peek();
			done = true;
			for(Integer q : graph.get(p)){
				if(!visited[q]){
					done = false;
					dfsStack.push(q);
					visited[q] = true;
				}
			}
			if(done)
				stack.push(dfsStack.pop());
		}
	}
	
	public int getComponent(int start){
		int comp = 0;
		dfsStack.push(start);
		visited[start] = true;
		boolean done = true;
		while(!dfsStack.empty()){
			int p = dfsStack.peek();
			done = true;
			for (Integer q : reversed.get(p)){
				if(!visited[q]){
					done = false;
					dfsStack.push(q);
					visited[q] = true;
				}
			}
			if(done){
				dfsStack.pop();
				comp++;
			}
		}
		return comp;
	}
	
}
