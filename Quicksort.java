/*
 * Given a list of integers, use quicksort to sort them in ascending order
 */
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Main{
	
	static void swap(int x, int y, int[] array){
		int aux = array[y];
		array[y] = array[x];
		array[x] = aux;
	}

	static int partition(int[] array, int left, int right){
		int boundary = left + 1; // boundary after which everything is >= to the pivot
		for (int i = boundary; i <= right; i++){
			if(array[i] < array[left])
				swap(i, boundary++, array);
		}
		swap(left, boundary - 1, array);
		return boundary - 1;
	}
	
	static void quicksort(int[] array, int left, int right){
		if(left >= right) return;
		int pivot = partition(array, left, right);
		quicksort(array, left, pivot - 1);
		quicksort(array, pivot + 1, right);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int[] input = readIntArray(reader);
		quicksort(input, 0, input.length - 1);
		String output = Arrays.stream(input).boxed().map(String::valueOf).collect(Collectors.joining(" "));
		System.out.println(output);
	}

	public static int[] readIntArray(BufferedReader reader) throws IOException{
		return Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
	}
}
