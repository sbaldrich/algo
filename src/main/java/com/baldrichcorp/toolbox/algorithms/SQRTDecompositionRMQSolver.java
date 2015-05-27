package com.baldrichcorp.toolbox.algorithms;

import static java.lang.Math.*;

public class SQRTDecompositionRMQSolver<T extends Comparable<? super T>> implements RMQSolver<T> {

	
	private T elements[];
	private int sqrt[];
	
	public SQRTDecompositionRMQSolver(final T[] input){
		elements = input;
		//The lookup array is going to have sqrt(n) elements | n => the number of elements in input.
		sqrt = new int[(int)(ceil(sqrt(input.length)))];
		computeLookup();
	}
	
	@Override
	public T query(int l, int r) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void computeLookup(){
		int n = (int)sqrt(elements.length);
		System.out.println("  n =>" + n);
		int i,j,k;
		for(i=0, j=0, k=0; i < elements.length; i++, j++){
			if(j == n){
				System.out.println("   =>[" + k++ +"] element.");
				j=0;
			}
			System.out.printf("%3d", i);
		}
		if(i != 0)
			System.out.println("     => [" + k + "] element.");
	}
	
	public static void main(String[] args) {
		new SQRTDecompositionRMQSolver<Integer>(new Integer[11]);
		new SQRTDecompositionRMQSolver<Integer>(new Integer[9]);
		new SQRTDecompositionRMQSolver<Integer>(new Integer[2]);
		new SQRTDecompositionRMQSolver<Integer>(new Integer[1]);
	}

}
