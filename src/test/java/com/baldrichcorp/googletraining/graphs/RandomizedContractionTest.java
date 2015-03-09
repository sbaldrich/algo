package com.baldrichcorp.googletraining.graphs;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import org.testng.annotations.Test;

import com.baldrichcorp.googletraining.graphs.RandomizedContraction.Graph;


public class RandomizedContractionTest {
	
	Random rand = new Random();
	Graph g;
	@Test
	public void testGraph(){
		g = new Graph();
		for(int i = 0; i < 5; i++){
			g.connect(rand.nextInt(50), rand.nextInt(50), true);
		}
		System.out.println(g);
	}
	
	@Test(dependsOnMethods="testGraph")
	public void testDoppelganger(){
		Graph doppel = g.doppelganger();
		g.connect(-1, 11, true);
		System.out.println("doppel:");
		System.out.println(doppel);
		System.out.println("g:");
		System.out.println(g);
		
	}
	
	@Test
	public void testMerge(){
		Graph graph = new Graph();
		graph.connect(0, 1, true);
		graph.connect(1, 2, true);
		graph.connect(2, 5, true);
		System.out.println(graph);
		graph.merge(0, 1);
		System.out.println(graph);
		graph.merge(5, 6);
		System.out.println(graph);
	}
	
	@Test
	public void randomizedContractionTest(){
		Graph graph = new Graph();
		graph.connect(1,2, true);
		graph.connect(2,3, true);
		graph.connect(2,4, true);
		graph.connect(3,4, true);
		graph.connect(3,5, true);
		graph.connect(4,5, true);
		System.out.println(graph);
		System.out.println(graph.randomizedContraction(50));
		assertTrue(graph.randomizedContraction(1) == 1);
	}
	
	@Test
	public void randomizedContractionBigTest() throws Exception{
		BufferedReader reader = new BufferedReader
				(new FileReader( new File ("D:\\santiago.vargas\\learning\\coursera\\week3\\Graph.txt")));
		Graph graph = new Graph();
		String line;
		while( (line = reader.readLine()) != null ){
			String[] pars = line.split("\\s+");
			for(int i = 1; i < pars.length; i++)
				graph.connect(Integer.parseInt(pars[0]), Integer.parseInt(pars[i]), true);
		}
		int n = graph.nodes.size();
		System.out.println(graph.randomizedContraction( 10 ));
		//System.out.println(graph.edges.size());
		//System.out.println(graph);
	}
}
