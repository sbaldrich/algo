package com.baldrichcorp.googletraining.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;


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

		/**
		 * {@inheritDoc}
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + index;
			return result;
		}

		/**
		 * {@inheritDoc}
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
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
	
	public static void main(String[] args) throws Exception{
		Dijkstra d = new Dijkstra(201);
		BufferedReader reader = new BufferedReader
				(new FileReader( new File ("dijkstraData.txt")));
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
		int dijkstra[];
		System.out.println(Arrays.toString(dijkstra = d.dijkstra(1)));
		int ans[] = new int[]{7,37,59,82,99,115,133,165,188,197};
		for(int i : ans)
			System.out.println(dijkstra[i]);
		
		
	}	
}
