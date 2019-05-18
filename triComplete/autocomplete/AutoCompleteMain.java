package autocomplete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import structure5.Assert;

/**
 * A program that simulates autocomplete using word prefix
 * from user
 * 
 * @author Hussein
 *
 */
public class AutoCompleteMain {
	
	/**
	 * A method that is called when user runs class
	 * 
	 * @param args
	 * 		command-line arguments	
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException();
		}
		else if (Integer.parseInt(args[0]) < 0) {
			throw new IllegalArgumentException();
		}
		
		// number of items to show
		int numItems = Integer.parseInt(args[0]);
		
		// a ist of terms
		List<Term> l = new ArrayList<Term>();
		
		// a buffered reader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(args[1]));
		} catch (FileNotFoundException e) {
			// alerting user if filename's incorrect
			System.out.println(e);
		}
		
		// a line from the file
		String line = "";
		try {
			// reading lines from the file
			line = br.readLine();
			line= br.readLine();
			
			// creating and storing terms from file data
			while (line != null) {
				String[] ls = line.split("\\s+");
				if (ls.length > 2) {
					l.add(new Term(ls[2], Long.parseLong(ls[1])));
				}
				
				line= br.readLine();
			}	
		} catch (IOException e) {
			// alerting user if there's a problem with reading file
			System.out.println(e);
		}
		
		// a test autocomplete class
		Autocomplete testAuto = new Autocomplete(l);
		
		// testing autocomplete class with user inputs
		while (true) {
			System.out.print("Enter a new prefix: ");
			
			// user input
			Scanner input = new Scanner(System.in);
			
			// list of matches
			List<Term> matches = testAuto.allMatches(input.next());
			
			// handling case when there are no  matches
			if (matches.size() == 0) {
				System.out.println("There are no matches.");
				continue;
			}
			
			// handling case when there are matches
			System.out.println("There are " + matches.size() + " matches.");
			int printCount = Math.min(matches.size(), numItems);
			if (printCount != numItems) {
				System.out.println("The matching items are: ");
			}
			else {
				System.out.println("The " + numItems + " largest are: ");
			}
			for (int i = 0; i< printCount; i++) {
				System.out.println(matches.get(i));
			}
		}		
	}
}
