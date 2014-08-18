import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * This program is written by Yifan Ge and Yushan Zhang for CSCI 311 Project.
 * 
 * Wordgame currently functions as a game that takes in a file containing
 * 5-letter words and maps them to a graph. Every word is a Vertex.
 * Each word is connected to other words that are only have 1 or 2 characters 
 * different from itself. Each connection is an edge, and it has a cost of either
 * 1 or 5 (for 1 or 2 character difference).
 * 
 * Then the game asks for user input 5-letter word, and print out all Vertices
 * connects to the input word with their edge cost. The user will then be asked
 * if he/she wants to continue or quit.
 */


public class Wordgame {

	/**
	 * Main method takes in a file with 5-letter words, puts them into a graph,
	 * and find edges for user input word.
	 */
	public static void main(String[] args) {
		
		Hashtable graph = new Hashtable(); // To store graph after all words from the documents are added
		ArrayList<Vertex> graphTmp = new ArrayList<Vertex>(); // To store graph temporarily
		String input; 
		Scanner in;
		boolean done = false;
		
		if (args.length != 1){
			System.err.println("Usgae: java Wordgame filename");
			System.exit(1);
		}
		
		try {
			in = new Scanner(new BufferedReader(new FileReader(args[0])));
			while (in.hasNext()){
				input = in.next();
				Vertex v = new Vertex(input);
				
				// Compare with other vertices and add edges
				for(Vertex tV: graphTmp){
					int cost = compareString(v.getRecord(), tV.getRecord());
					if(cost > 0){
						tV.insertEdge(v, cost);
						v.insertEdge(tV, cost);
					}
				}
				graphTmp.add(v);
			}
			
			// Move the graph from Arraylist to HashTable, for later faster access.
			for(Vertex ttV: graphTmp){
				graph.put(ttV.getRecord(), ttV);
			}
			
			Scanner usrInput = new Scanner(System.in);
			String strIn;
			Vertex match;
			
			while(!done){
				System.out.print("Enter a five-letter word: ");
				System.out.flush();
				
				strIn = usrInput.next().toUpperCase();
				// Find the vertex in graph
				match = (Vertex)graph.get(strIn); 
				if(null != match){
					System.out.printf("\nThe neighbors of %s are\n", strIn);
					match.printEdge();
				}else
					System.out.printf("Can't find %s in the graph!!\n", strIn);
				
				while(true){
					System.out.println("Would you like to do another trial?");
					strIn = usrInput.next().toLowerCase();
					if(strIn.equals("y") || strIn.equals("yes")){
						done = false;
						break;
					}else if(strIn.equals("n") || strIn.equals("no")){
						done = true;
						break;
					}else
						System.out.println("Sorry I didn't get what you said. Please enter again.");
				}
			}
				
		} catch (FileNotFoundException e) {
			System.err.println("Error opening file");
			System.exit(1);
		}
		
		System.out.println("Thank you for playing wordgame 101!");
		

	}
	
	/**
	 * This method compares two 5-letter Strings. It returns the cost 
	 * from s1 to s2. Cost is 5 if 2 characters are different, and 1 if
	 * 1 character is different. It returns -1 if the difference is 0 or
	 * larger than 2.
	 */
	public static int compareString(String s1, String s2){
		
		int retval = 0;
		
		char[] charArray1 = s1.toCharArray();
		char[] charArray2 = s2.toCharArray();
		
		for(int i=0; i<s1.length(); i++){
			if(charArray1[i] != charArray2[i])
				retval++;
		}
		if(retval == 2)
			retval = 5;
		else if(retval > 2)
			retval = -1;
		
		return retval;
	}

}
