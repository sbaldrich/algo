/*
 * Given a list of integers, sort them using Mergesort
 */
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {
	
	static void sort(int[] input){
		int[] aux = Arrays.copyOf(input, input.length);
		mergesort(aux, input, 0, input.length - 1);
	}

	static void mergesort(int[] source, int[] dest, int low, int high){
		if(low >= high) return; // already sorted
		int mid = (low + high) >>> 1; // avoid issues with overflowing.
		mergesort(dest, source, low, mid);
		mergesort(dest, source, mid + 1, high);

		// sub arrays are sorted, so now we need to merge.
		for(int i = low, li = low, ri = mid+1; i <= high; i++){
			if(ri > high || li <= mid && source[li] < source[ri])
				dest[i] = source[li++];
			else
				dest[i] = source[ri++];
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int[] array = readIntArray(reader);
		sort(array);
		String out = Arrays.stream(array).boxed().map(String::valueOf).collect(Collectors.joining(" "));
		System.out.println(out);
	}

	public static int[] readIntArray(BufferedReader reader) throws IOException{
		return Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
	}
}
