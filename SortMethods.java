import java.util.*;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Charles Chang
 *	@since	November 30, 2023
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(ArrayList<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer --)
			for (int inner = 0; inner < outer; inner ++) 
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0)
					swap(arr, inner, inner + 1);
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> arr, int x, int y) {
		Integer temp = arr.get(x);
		arr.set(x, arr.get(y)); 
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(ArrayList<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer --) {
			int highest = 0;
			for (int inner = 0; inner <= outer; inner ++) {
				if (arr.get(inner).compareTo(arr.get(highest)) > 0) 
					highest = inner;
			}
			swap(arr, highest, outer);
			highest = 0;
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(ArrayList<City> arr) {
		for (int outer = 1; outer < arr.size(); outer ++) {
			int inner = outer - 1;
			while (inner >= 0 && arr[inner + 1].compareTo(arr[inner]) < 0) {
				swap(arr, inner, inner + 1);
				inner --;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		Integer[] temp = (mergeSplit(0, arr.length - 1, arr));
		for (int i = 0; i < arr.length; i++) {
			arr[i] = temp[i];
		}
	}
	
	/**
	 *  Split helper method for merge. If end - start indices = 1 or 2,
	 * 	break the loop.
	 * 	@param	int		start index
	 * 	@param	int		end index
	 * 	@param	Integer[]	array to split
	 * 	@return Integer[]	merged array
	 */
	private Integer[] mergeSplit(int start, int end, Integer[] arr) {
		// Find middle to split
		int splitIndex = (start + end) / 2;
		// Array for each half
		Integer[] firstHalf;
		Integer[] secondHalf;
		// First half
		if (splitIndex - start > 1) {
			firstHalf = mergeSplit(start, splitIndex, arr);
		} else if (splitIndex == start) {
			firstHalf = new Integer[] {arr[start]};
		} else {
			// Swap if needed
			if (arr[start].compareTo(arr[start + 1]) > 0)
				swap(arr, start, start + 1);
			firstHalf = new Integer[] {arr[start], arr[start + 1]};
		}
		// Second half
		if (end - (splitIndex + 1) > 1) {
			secondHalf = mergeSplit(splitIndex + 1, end, arr);
		} else if (splitIndex + 1 == end) {
			secondHalf = new Integer[] {arr[end]};
		} else {
			if (arr[splitIndex + 1].compareTo(arr[splitIndex + 2]) > 0)
				swap(arr, splitIndex + 1, splitIndex + 2);
			secondHalf = new Integer[] {arr[end  - 1], arr[end]};
		}
		// Output Array
		// Integer[] output = new Integer[firstHalf.length + secondHalf.length];
		return merge(firstHalf, secondHalf);
	}
	
	/**
	 * 	Sort two sorted arrays into one
	 * 	@param	Integer[]	array to sort and merge
	 * 	@param	Integer[]	array to sort and merge
	 * 	@return Integer[]	sorted array
	 */
	private Integer[] merge(Integer[] arr1, Integer[] arr2) {
		// Array to output
		Integer[] output = new Integer[arr1.length + arr2.length];
		// Index of arrays
		int index1 = 0;
		int index2 = 0;
		boolean finished = false;
		while(!finished) {
			// Add lowest to output
			if (arr1[index1] > arr2[index2]) {
				output[index1 + index2] = arr2[index2];
				index2 ++;
			} else {
				output[index1 + index2] = arr1[index1];	
				index1++;
			}
			/* If index reaches end of an array, copy the rest of the
			 * other array to output */
			if (index1 > arr1.length - 1) {
				while (index2 < arr2.length) {
					output[index1 + index2] = arr2[index2];
					index2++;
				}
				finished = true;
			}
			if (index2 > arr2.length - 1) {
				while (index1 < arr1.length) {
					output[index1 + index2] = arr1[index1];
					index1++;
				}
				finished = true;
			}
		}
		return output;
	}

	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}
}
