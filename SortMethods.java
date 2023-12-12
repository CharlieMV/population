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
	 * 	@return ArrayList<City>		sorted array
	 */
	public ArrayList<City> bubbleSort(ArrayList<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer --)
			for (int inner = 0; inner < outer; inner ++) 
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0)
					swap(arr, inner, inner + 1);
		return arr;
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(ArrayList<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 	@return ArrayList<City>		sorted array
	 */
	public ArrayList<City> selectionSort(ArrayList<City> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer --) {
			int highest = 0;
			for (int inner = 0; inner <= outer; inner ++) {
				if (arr.get(inner).compareTo(arr.get(highest)) > 0) 
					highest = inner;
			}
			swap(arr, highest, outer);
			highest = 0;
		}
		return arr;
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 * 	@return ArrayList<City>		sorted array
	 */
	public ArrayList<City> insertionSort(ArrayList<City> arr) {
		for (int outer = 1; outer < arr.size(); outer ++) {
			int inner = outer - 1;
			while (inner >= 0 && arr.get(inner + 1).compareTo(
												arr.get(inner)) < 0) {
				swap(arr, inner, inner + 1);
				inner --;
			}
		}
		return arr;
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order, only comparing names
	 *	@param arr		array of Integer objects to sort
	 * 	@return ArrayList<City>		sorted array
	 */
	public ArrayList<City> insertionSortName(ArrayList<City> arr) {
		for (int outer = 1; outer < arr.size(); outer ++) {
			int inner = outer - 1;
			while (inner >= 0 && arr.get(inner + 1).getName().compareTo(
										arr.get(inner).getName()) < 0) {
				swap(arr, inner, inner + 1);
				inner --;
			}
		}
		return arr;
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 *  @return ArrayList<City>		sorted array
	 */
	public ArrayList<City> mergeSort(ArrayList<City> arr) {
		return (mergeSplit(0, arr.size() - 1, arr));
	}
	
	/**
	 *  Split helper method for merge. If end - start indices = 1 or 2,
	 * 	break the loop.
	 * 	@param	int		start index
	 * 	@param	int		end index
	 * 	@param	ArrayList[]	array to split
	 * 	@return ArrayList[]	merged array
	 */
	private ArrayList<City> mergeSplit(int start, int end, ArrayList<City> arr) {
		// Find middle to split
		int splitIndex = (start + end) / 2;
		// Array for each half
		ArrayList<City> firstHalf = new ArrayList<City>();
		ArrayList<City> secondHalf = new ArrayList<City>();
		// First half
		if (splitIndex - start > 1) {
			firstHalf = mergeSplit(start, splitIndex, arr);
		} else if (splitIndex == start) {
			firstHalf.add(arr.get(start));
		} else {
			// Swap if needed
			if (arr.get(start).compareTo(arr.get(start + 1)) > 0)
				swap(arr, start, start + 1);
			firstHalf.add(arr.get(start));
			firstHalf.add(arr.get(start + 1));
		}
		// Second half
		if (end - (splitIndex + 1) > 1) {
			secondHalf = mergeSplit(splitIndex + 1, end, arr);
		} else if (splitIndex + 1 == end) {
			secondHalf.add(arr.get(end));
		} else {
			if (arr.get(splitIndex + 1).compareTo(arr.get(splitIndex + 2)) > 0)
				swap(arr, splitIndex + 1, splitIndex + 2);
			secondHalf.add(arr.get(end - 1));
			secondHalf.add(arr.get(end));
		}
		// Output Array
		return merge(firstHalf, secondHalf);
	}
	
	/**
	 * 	Sort two sorted arrays into one
	 * 	@param	Integer[]	array to sort and merge
	 * 	@param	Integer[]	array to sort and merge
	 * 	@return Integer[]	sorted array
	 */
	private ArrayList<City> merge(ArrayList<City> arr1, ArrayList<City> arr2) {
		// Array to output
		ArrayList<City> output = new ArrayList<City>();
		// Index of arrays
		int index1 = 0;
		int index2 = 0;
		boolean finished = false;
		while(!finished) {
			// Add lowest to output
			if (arr1.get(index1).compareTo(arr2.get(index2)) > 0) {
				output.add(arr2.get(index2));
				index2 ++;
			} else {
				output.add(arr1.get(index1));	
				index1++;
			}
			/* If index reaches end of an array, copy the rest of the
			 * other array to output */
			if (index1 > arr1.size() - 1) {
				while (index2 < arr2.size()) {
					output.add(arr2.get(index2));
					index2++;
				}
				finished = true;
			}
			if (index2 > arr2.size() - 1) {
				while (index1 < arr1.size()) {
					output.add(arr1.get(index1));
					index1++;
				}
				finished = true;
			}
		}
		return output;
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order by only name
	 *	@param arr		array of Integer objects to sort
	 *  @return ArrayList<City>		sorted array
	 */
	public ArrayList<City> mergeSortName(ArrayList<City> arr) {
		return (mergeSplitName(0, arr.size() - 1, arr));
	}
	
	/**
	 *  Split helper method for merge. If end - start indices = 1 or 2,
	 * 	break the loop.
	 * 	@param	int		start index
	 * 	@param	int		end index
	 * 	@param	ArrayList[]	array to split
	 * 	@return ArrayList[]	merged array
	 */
	private ArrayList<City> mergeSplitName(int start, int end, ArrayList<City> arr) {
		// Find middle to split
		int splitIndex = (start + end) / 2;
		// Array for each half
		ArrayList<City> firstHalf = new ArrayList<City>();
		ArrayList<City> secondHalf = new ArrayList<City>();
		// First half
		if (splitIndex - start > 1) {
			firstHalf = mergeSplitName(start, splitIndex, arr);
		} else if (splitIndex == start) {
			firstHalf.add(arr.get(start));
		} else {
			// Swap if needed
			if (arr.get(start).getName().compareTo(arr.get(start + 1)
														.getName()) > 0)
				swap(arr, start, start + 1);
			firstHalf.add(arr.get(start));
			firstHalf.add(arr.get(start + 1));
		}
		// Second half
		if (end - (splitIndex + 1) > 1) {
			secondHalf = mergeSplitName(splitIndex + 1, end, arr);
		} else if (splitIndex + 1 == end) {
			secondHalf.add(arr.get(end));
		} else {
			if (arr.get(splitIndex + 1).getName().compareTo(arr.get
										(splitIndex + 2).getName()) > 0)
				swap(arr, splitIndex + 1, splitIndex + 2);
			secondHalf.add(arr.get(end - 1));
			secondHalf.add(arr.get(end));
		}
		// Output Array
		return mergeName(firstHalf, secondHalf);
	}
	private ArrayList<City> mergeName(ArrayList<City> arr1, ArrayList<City> arr2) {
		// Array to output
		ArrayList<City> output = new ArrayList<City>();
		// Index of arrays
		int index1 = 0;
		int index2 = 0;
		boolean finished = false;
		while(!finished) {
			// Add lowest to output
			if (arr1.get(index1).getName().compareTo(arr2.get
											(index2).getName()) > 0) {
				output.add(arr2.get(index2));
				index2 ++;
			} else {
				output.add(arr1.get(index1));	
				index1++;
			}
			/* If index reaches end of an array, copy the rest of the
			 * other array to output */
			if (index1 > arr1.size() - 1) {
				while (index2 < arr2.size()) {
					output.add(arr2.get(index2));
					index2++;
				}
				finished = true;
			}
			if (index2 > arr2.size() - 1) {
				while (index1 < arr1.size()) {
					output.add(arr1.get(index1));
					index1++;
				}
				finished = true;
			}
		}
		return output;
	}
}
