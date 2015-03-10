package com.baldrichcorp.algorithms.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Implementation of Dijkstra's shortest path algorithm. => O(|E|log(|V|))
 * A TreeSet is used as the Priority Queue so it is cool but not as cool as
 * a raccoon smoking a cigarette while riding a motorcycle.
 *   
 * @author sbaldrich
 *
 */
public class Dijkstra {
	
	private static final int oo = (int)1e6;
	ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	
	public void connect(int source, int destination, int cost){
		if(cost < 0)
			throw new IllegalArgumentException("Funny. No negative weights allowed");
		graph.get(source).add(new Node(destination, cost));
	}
	
	public Dijkstra(int nodes){
		while(nodes-- > 0){ 
			graph.add(new ArrayList<Node>());
		}
	}
	
	public Dijkstra(){
		this(10);
	}
	class Node implements Comparable<Node>{
		int index;
		int cost;
		
		public Node(int index, int cost){
			this.index = index;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return cost - o.cost;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + index;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (index != other.index)
				return false;
			return true;
		}

		private Dijkstra getOuterType() {
			return Dijkstra.this;
		}
		
		@Override
		public String toString(){
			return String.format("[%d,%d]", index, cost);
		}
		
	}
	public int[] dijkstra(int source){
		final int d[] = new int[graph.size()];
		final boolean processed[] = new boolean[graph.size()];
		Arrays.fill(d, oo);
		d[source] = 0;
		TreeSet<Node> priorityQueue = new TreeSet<>();
		priorityQueue.add(new Node(source,0));
		int proc = 0;
		while(!priorityQueue.isEmpty() && proc < 200){
			Node p = priorityQueue.first();
			processed[p.index] = true;
			proc++;
			priorityQueue.remove(priorityQueue.first());
			ArrayList<Node> out = graph.get(p.index);
			for(Node q : out){
				if(!processed[q.index]){
					d[q.index] = Math.min(d[q.index], d[p.index] + q.cost);
					q.cost = d[q.index];
					priorityQueue.remove(q);
					priorityQueue.add(q);
				}
			}
		}
		return d;
	}
	
}
