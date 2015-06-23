package com.baldrichcorp.toolbox.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
	
	private List<List<Integer>> nodes;
	
	private HashMap<Integer[],Integer> edges; //Why not a 3d array? too much memory.
	
	public Graph(int size){
		this(size, false);
	}
	
	public Graph(int size, boolean directed){
		nodes = new ArrayList<>(size);
	}
	
	public void connect(int p, int q, boolean directed, int cost){
		List<Integer> node = nodes.get(p);
		if(node == null){
			node = new ArrayList<Integer>();
		}
		node.add(q);
		if(!directed){
			connect(q, p, true, cost);
		}
		edges.put(new Integer[]{p, q}, cost);
	}
	
	public void connect(int p, int q, boolean directed){
		connect(p, q, directed, 1);
	}
	
	public void connect(int p, int q){
		connect(p, q, false, 1);
	}
	
	public List<Integer> at(int node){
		List<Integer> cur = nodes.get(node);
		return cur == null ? cur = new ArrayList<>() : cur;
	}
	
}
