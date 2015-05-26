package com.baldrichcorp.toolbox.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.baldrichcorp.toolbox.sorting.WorkScheduling.DifferenceComparator;
import com.baldrichcorp.toolbox.sorting.WorkScheduling.Job;
import com.baldrichcorp.toolbox.sorting.WorkScheduling.RatioComparator;

public class WorkSchedulingTest {
	
	private static int j[][] = new int[][]{{5,2},{3,1},{6,5}};
	
	@Test
	public void differenceComparator(){
		List<Job> jobs = new ArrayList<>();
		for(int[] job : j)
			jobs.add(new Job(job[0], job[1]));
		Collections.sort(jobs, new DifferenceComparator());
		System.out.println(jobs);
	}
	
	@Test
	public void ratioComparator(){
		List<Job> jobs = new ArrayList<>();
		for(int[] job : j)
			jobs.add(new Job(job[0], job[1]));
		Collections.sort(jobs, new RatioComparator());
		System.out.println(jobs);
	}
	
}