package com.baldrichcorp.algorithms.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;


public class Kosaraju {
	private static final int M = 875714;
	private static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(M);
	private static HashMap<Integer, ArrayList<Integer>> reversed = new HashMap<>(M);
	private static void join(int p, int q){
		if(graph.get(p) == null)
			graph.put(p,new ArrayList<Integer>());
		if(graph.get(q) == null)
			graph.put(q,new ArrayList<Integer>());
		graph.get(p).add(q);
	}
	private static void joinRev(int p, int q){
		if(reversed.get(p) == null)
			reversed.put(p,new ArrayList<Integer>());
		if(reversed.get(q) == null)
			reversed.put(q,new ArrayList<Integer>());
		reversed.get(p).add(q);
	}
	private static boolean visited[] = new boolean[M];
	
	private static Stack<Integer> stack = new Stack<>();
	private static Stack<Integer> dfsStack = new Stack<>();
	private static void buildStack(int start){
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
	
	private static int getComponent(int start){
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
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader
				(new FileReader( new File ("SCC.txt")));
		String line = null;
		while((line = reader.readLine()) != null ){
			String pars[] = line.split("\\s+");
			int tail = Integer.parseInt(pars[0]) - 1;
			for(int i=1; i < pars.length; i++ ){
				join(Integer.parseInt(pars[i]) - 1,tail);
				joinRev(tail,Integer.parseInt(pars[i]) - 1);
			}
		}
		for(Integer p : graph.keySet()){
			if(!visited[p])
				buildStack(p);
		}
		Arrays.fill(visited, false);
		ArrayList<Integer> ans = new ArrayList<>();
		while(!stack.empty()){
			int p = stack.pop();
			if(visited[p])
				continue;
			ans.add(getComponent(p));
		}
		Collections.sort(ans, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
			
		});
		for(int i=0; i<5; i++)
			System.out.println(ans.get(i));
			
	}
	
}
