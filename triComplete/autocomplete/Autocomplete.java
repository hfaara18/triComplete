package autocomplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Autocomplete implements AutocompleteInterface 
{
	// a list of terms
	protected List<Term> list;
	
	public Autocomplete(List<Term> lt) {
		// storing constructor parameter
		list = lt;
		
		// sorting list items by prefix
		Collections.sort(list, Term.byPrefixOrder(100));
	}
	
	/**
	 * 
	 */
	public List<Term> allMatches(String prefix) {
		// the first index of prefix
		int firstOccurence = BinarySearchForAll.firstIndexOf(list, new Term(prefix, 0) , Term.byPrefixOrder(prefix.length()));
		
		// the last index of prefix in list
		int lastOccurence = BinarySearchForAll.lastIndexOf(list, new Term(prefix, 0) , Term.byPrefixOrder(prefix.length()));
		
		// a list of terms with the given prefix
		List<Term> matches = new ArrayList<Term>();
		
		// adding items to list if there were any matches
		if (firstOccurence != -1) {
			for (int i = firstOccurence; i <= lastOccurence; i++) {
				matches.add(list.get(i));
			}
		}
		
		// sorting the matches by weight in descending order
		Collections.sort(matches, Term.byReverseWeightOrder());
		return matches;
	}

	/**
	 * A method that is called when user runs class
	 * 
	 * @param args
	 * 		command-line arguments
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// a list of terms
		List<Term> l = new ArrayList<Term>();
		
		// a buffered reader of cities test file
		BufferedReader br = new BufferedReader(new FileReader("cities.txt"));
		
		// reading lines from cities file
		String line = br.readLine();
		line= br.readLine();
		
		// creating terms from cities file and adding term to list
		while (line != null) {
			String[] ls = line.split("\\W+");

			if (ls.length > 2) {
				l.add(new Term(ls[2], Long.parseLong(ls[1])));
			}
			line= br.readLine();
		}
		System.out.println("Done!");
		
		// an instance of the autocomplete class
		Autocomplete testAuto = new Autocomplete(l);
		
		// list of terms containing "Accra"
		List<Term> test = testAuto.allMatches("Accra");
		
		// printing items in matches
		for (Term hel: test) {
			System.out.println(hel);
		}
		
		// list of terms containing "Lome"
		List<Term> test2 = testAuto.allMatches("Lome");
		
		// printing items in matches
		for (Term pom: test2) {
			System.out.println(pom);
		}
	}
}
