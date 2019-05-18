package autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import structure5.Assert;

public class Term implements Comparable<Term> {
	// the String and its weight
	protected final String QUERY;
	protected final long WEIGHT;

	/**
	 * Initializes a term with the given query string and weight.
	 * 
	 * @pre the query is non-empty and the weight > 0;  
	 * 
	 * @param query
	 *            word to be stored
	 * @param weight
	 *            associated frequency
	 */
	public Term(String query, long weight) {
				
		// storing constructor parameters
		QUERY = query;
		WEIGHT = weight;
	}

	/**
	 * A method that compares weights of terms in the reverse
	 * order
	 * 
	 * @return comparator ordering elts by descending weight
	 */
	public static Comparator<Term> byReverseWeightOrder() {		
		return new Comparator<Term>(){
			// comparing term weights in reverse order
			public int compare(Term t1, Term t2) {
				if (t1.WEIGHT > t2.WEIGHT) {
					return -1;
				} else if(t1.WEIGHT == t2.WEIGHT) {
					return 0;
				}
				else {
					return 1;
				}
			}
		};
	}

	/**
	 * A method that compares the prefix of the queries of terms
	 * 
	 * @param r
	 *         Number of initial characters to use in comparing words
	 * @return comparator using lexicographic order, but using only the first r
	 *         letters of each word
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		if (r < 0) {
			throw new IllegalArgumentException();
		}
		return new Comparator<Term>() {
			// comparing the prefixes of terms
			public int compare(Term t1, Term t2) {
				String param1 = t1.QUERY.substring(0, Math.min(t1.QUERY.length(), r));
				String param2 = t2.QUERY.substring(0, Math.min(t2.QUERY.length(), r));
				return param1.compareTo(param2);
			}
		};
	}

	/**
	 * A method that compares two terms by comparing their
	 * prefixes
	 * 
	 * 
	 * @pre the other term is not null
	 * @param that
	 *            Term to be compared
	 * @return -1, 0, or 1 depending on whether the word for that is smaller,
	 *         the same or larger than for the receiver
	 */
	public int compareTo(Term that) {
		// checking of pre-condition is fulfilled
		if(that == null) {
			throw new IllegalArgumentException();
		}
		
		return this.QUERY.compareTo(that.QUERY);
	}

	/**
	 * A method that return a string representation of a term
	 * 
	 * @return a string representation of this term in the following format: the
	 *         weight, followed by 2 tabs, followed by the word.
	 **/
	public String toString() {
		return WEIGHT + "\t" + QUERY;    // fix this
	}

	/**
	 * A method that runs when user runs the class
	 * 
	 * @param args
	 * 		the command-line arguments
	 */
	public static void main(String[] args) {
		// a list of terms
		ArrayList<Term>	t = new ArrayList<Term>();
		t.add(new Term("One", 9428938));
		t.add(new Term("Two", 4549));
		t.add(new Term("Three", 454));
		t.add(new Term("Four", 454));
		t.add(new Term("Five", 4509));
		t.add(new Term("Six", 459));
		t.add(new Term("Seven", 4545));
		t.add(new Term("Eight", 4594));
		t.add(new Term("Nine", 459485));
		t.add(new Term("Ten", 548485));
		
		// sorting the string by prefix
		Collections.sort(t, Term.byPrefixOrder(300));
		for (Term tt: t) {
			System.out.println(tt);
		}
		
		// sorting by reverse weight
		System.out.println("\n");
		Collections.sort(t, Term.byReverseWeightOrder());
		for (Term tt: t) {
			System.out.println(tt);
		}
	}
}
