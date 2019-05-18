package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class BinarySearchForAll {
	// flag indicating whether a key occurs at all in the list
	public static final int NOT_FOUND = -1;

	/**
	 * Returns the index of the first element in aList that equals key
	 *
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Index of first item in aList matching key or -1 if not in aList
	 **/
	public static <Key> int firstIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		// starting left and right indices to begin search
		int left = 0;
		int right = aList.size() - 1;
		 
		// 
		while (left <= right) {
			// the middle index to search
			int mid = left + (right - left) /2;
			
			// shifting search area to the left if mid value 
			// is bigger than search value
			if (comparator.compare(key, aList.get(mid)) < 0) {
				right = mid - 1;
			}
			// shifting search area to the right if mid value 
			// is smaller than search value
			else if (comparator.compare( key, aList.get(mid)) > 0) {
				left = mid + 1;
			}
			else if (comparator.compare(aList.get(mid), key) == 0) {
				// returning index if value found at start of list
				if (mid == 0) {
					return mid;
				}
				// shifting search to the left if previous value is the same 
				// as the search value
				else if (comparator.compare(key, aList.get(mid - 1)) == 0) {
					right = mid - 1;
				}
				// returning the index of the search value otherwise
				else {
					return mid;
				}
			}
		}
		// returns a default value if value not found in list
		return NOT_FOUND;
	}



	/**
	 * Returns the index of the last element in aList that equals key
	 * 
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Location of last item of aList matching key or -1 if no such key.
	 **/
	public static <Key> int lastIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		// starting left and right indices to begin search	}
		int left = 0;
		int right = aList.size() - 1;
		 
		while (left <= right) {
			// the middle index to search
			int mid = left + (right - left) /2;
			
			// shifting search area to the left if mid value 
			// is bigger than search value
			if (comparator.compare(key, aList.get(mid)) < 0) {
				right = mid - 1;
			}
			// shifting search area to the right if mid value 
			// is smaller than search value
			else if (comparator.compare( key, aList.get(mid)) > 0) {
				left = mid + 1;
			}
			else if (comparator.compare(aList.get(mid), key) == 0) {
				// returning index if value found at end of list
				if (mid == aList.size() - 1) {
					return mid;
				}
				// shifting search to the right if next value is the same 
				// as the search value
				else if (comparator.compare(key, aList.get(mid + 1)) == 0) {
					left = mid + 1;
				}
				// returning the index of the search value otherwise
				else {
					return mid;
				}
			} 
		 }
		// returns a default value if value not found in list
		return NOT_FOUND;
	}
	
	/**
	 * A method that runs when user runs the class
	 * 
	 * @param args
	 * 		command-line arguments
	 */
	public static void main(String[] args) {
		// a comparator
		Comparator<Integer> comp = (Integer int1, Integer int2) -> int1.compareTo(int2);
		
		// a random object generator
		Random r = new Random();
		
		// a list of random
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i=0; i< 20; i++) {
			l.add(r.nextInt(20));
		}
		
		// sorting and printing out the list
		Collections.sort(l, comp);
		System.out.println(l);
		
		// finding the first and last indices of random integers in the list
		for (Integer i: l) {
			int j = r.nextInt(20);
			System.out.println("First index of " + j + ": " + BinarySearchForAll.firstIndexOf(l, j, comp));
			System.out.println("Last index of " + j + ": " + BinarySearchForAll.lastIndexOf(l, j, comp));
		}
	}	
}
