package com.baldrichcorp.googletraining.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;


public class RandomizedContraction {
	public static class Node{
		
		final int index;
		ArrayList<Node> edges = new ArrayList<>();
		
		public Node(int index){
			this.index = index;
		}
		public void connect(Node other){
			edges.add(other);
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder("[" + index +"] -> { ");
			for(Node otherNode : edges)
				sb.append(otherNode.index).append(" ");
			return sb.append("}").toString();
		}
	}
	
	static class Graph{
		HashMap<Integer,Node> nodes = new HashMap<>();
		ArrayList<Integer[]> edges = new ArrayList<>();
		int maxIndex = 0;
		
		public void connect(int x, int y, boolean undirected){
			//Self loops are not allowed
			if(x == y)
				return;
			edges.add(new Integer[]{x,y});
			edges.add(new Integer[]{y,x});
			Node p = getNode(x), q = getNode(y);
			p.connect(q);
			if(undirected)
				q.connect(p);
		}
		
		public void disconnect(int x, int y){
			nodes.get(x).edges.remove(nodes.get(y));
		}
		private Node getNode(int key){
			maxIndex = Math.max(maxIndex, key);
			Node n = nodes.get(key);
			if(n == null)
				nodes.put(key, n = new Node(key));
			return n;
		}
		
		public void merge(int x, int y){
			Node p = nodes.get(x), q = nodes.get(y);
			if(p == null || q == null || x == y)
				return;
			//System.out.printf("Merging %d and %d\n", x, y);
			Node merged = getNode(++maxIndex);
			//The new node is connected to all the nodes
			//that were connected to p ...
			for(Node n : p.edges){
				if(n.index != q.index)
					connect(merged.index, n.index, true);
				disconnect(n.index, p.index);
			}
			//and q
			for(Node n : q.edges){
				if(n.index != p.index)
					connect(merged.index, n.index, true);
				disconnect(n.index,q.index);
			}
			//old nodes are eliminated since they have been merged
			nodes.remove(p.index);
			nodes.remove(q.index);
		}
		
		public int randomizedContraction(int times){
			int bestCut = Integer.MAX_VALUE;
			for(int i = 0; i < times; i++){
				bestCut = Math.min(contract(doppelganger()), bestCut);
				if(bestCut < 34)
					System.out.println(bestCut);
			}
			return bestCut;
		}
		
		private int contract(Graph g){
			
			while(g.nodes.size() > 2){
				Collections.shuffle(g.edges);
				Integer[] edge = g.edges.remove(0);
				g.merge(edge[0], edge[1]);
				//System.out.println(g);
			}
			int cut = 0;
			for(Node node : g.nodes.values())
				cut += node.edges.size();
			return cut/2;
		}
		
		public Graph doppelganger(){
			Graph doppelganger = new Graph();
			for(Node p : nodes.values()){
				for(Node q : p.edges)
					doppelganger.connect(p.index, q.index, false);
			}
			for(Integer[] edge : edges)
				doppelganger.edges.add(new Integer[]{edge[0], edge[1]});
			return doppelganger;
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for(Entry<Integer, Node> entry : nodes.entrySet()){
				sb.append(Objects.toString(entry.getValue())).append("\n");
			}
			return sb.toString();
		}
		
		
	}
	
	
}
