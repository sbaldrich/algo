package com.baldrichcorp.algorithms.sort;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorkScheduling {
	
	static DifferenceComparator diffComparator = new DifferenceComparator();
	static RatioComparator ratioComparator = new RatioComparator();
	
	static class Job{
		int w;
		int l;
		public Job(int weight, int length){
			this.w = weight;
			this.l = length;
		}
		public String toString(){
			return String.format("[ %d, %d ]", w, l);
		}
	}
	
	static class DifferenceComparator implements Comparator<Job>{
		@Override
		public int compare(Job o1, Job o2) {
			int d1 = o1.w - o1.l;
			int d2 = o2.w - o2.l;
			if(d1 == d2)
				return o1.w > o2.w ? -1 : 1;
			return d1 > d2 ? -1 : 1;
		}
	}
	
	static class RatioComparator implements Comparator<Job>{
		@Override
		public int compare(Job o1, Job o2) {
			double r1 = o1.w / (double)o1.l;
			double r2 = o2.w / (double)o2.l;
			return r1 >= r2 ? -1 : 1;
		}
	}
	
	public static void main(String args[]) throws Exception{
		List<String> lines = Files.readAllLines(Paths.get("D:\\santiago.vargas\\learning\\coursera\\week7\\jobs.txt"), Charset.defaultCharset());
		List<Job> jobs = new ArrayList<>();
		int n = Integer.valueOf(lines.get(0));
		for(String line : lines.subList(1, lines.size())){
			String[] pars = line.split("\\s+");
			jobs.add(new Job(Integer.parseInt(pars[0]), Integer.parseInt(pars[1])));
		}
		Collections.sort(jobs, diffComparator);
		long c = 0, ans = 0;
		for(Job j : jobs){
			c += j.l;
			ans += c * j.w;
		}
		System.out.println(ans);
		
		
	}
	
}
