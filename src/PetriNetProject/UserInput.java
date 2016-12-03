package PetriNetProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
	private Scanner scan; 			// For scanning user input
	private int places;				// # of places entered by user
	private int transitions;		// # of transitions entered by user
	Transition[] trans;
	private String input;			// Input for all transitions
	private String output;			// Output for all transitions
	private int[] initialMarking;	// Marking entered by user
	private ArrayList<int[]> reachableMarkings; // List of reachable markings
	
	
	//Constructor
	public UserInput(){
		scan = new Scanner(System.in);
		places = 1;
		transitions = 0;
		input = "";
		output = "";
	}
	
	// Get User input
	public String getInput(){
		String outputString = "";	// String for summary of input
		
		// Get input from user
		System.out.println("Input Petri Net information");
		
		// Ask for user input until a valid number is entered
		// loop for number of times equal to the value of places
		while (places <= 1){
			System.out.println("Enter a number of places greater than 1:");
			places = askForInt();
		}
		// loop for number of times equal to the value of transitions
		while (transitions <= 0){
			System.out.println("Enter a number of transitions greater than 0:");
			transitions = askForInt();
		}
		initialMarking = new int[places]; 
		getTransFromUser(); // Get IO for transitions and display result
		getMarkingFromUser(); // Get Initial Marking from user and display result
		outputString = "\nPlaces: " + places + "\nTransitions: " + transitions + "\nInput: " + input + "\nOutput: " + output + "\nInitial Marking: " + Arrays.toString(initialMarking);
		return outputString;
	}
	
	public void getTransFromUser(){
		trans = new Transition[transitions];
		String temp = "";
		// Loop through IO for transitions based on # of places and transitions entered by the user
		for(int i = 1; i <= transitions; i++){
			trans[i-1] = new Transition(places);
			//Input
			System.out.println("Enter Input for t" + i);
			temp = "t" + i + ": (";
			for(int j = 1; j <= places; j++){
				trans[i-1].getInput()[j-1] = -1;
				while(trans[i-1].getInput()[j-1] < 0){
					System.out.print("p" + j + ": ");
					trans[i-1].getInput()[j-1] = askForInt();
				}
				if(j == places)	{temp += trans[i-1].getInput()[j-1] + ")";}
				else			{temp += trans[i-1].getInput()[j-1] + ", ";}
			}
			System.out.println(temp);
			input += temp;
			
			//Output
			System.out.println("Enter Output for t" + i);
			trans[i-1] = new Transition(places);
			temp = "t" + i + ": (";
			for(int j = 1; j <= places; j++){
				trans[i-1].getOutput()[j-1] = -1;
				while(trans[i-1].getOutput()[j-1] < 0){
					System.out.print("p" + j + ": ");
					trans[i-1].getOutput()[j-1] = askForInt();
				}
				if(j == places)	{temp += trans[i-1].getOutput()[j-1] + ")";}
				else			{temp += trans[i-1].getOutput()[j-1] + ", ";}
			}
			System.out.println(temp);
			output += temp;
		}
	}
	
	public void getMarkingFromUser(){
		String temp = "";
		// get user's initial marking
				System.out.println("Enter initial marking");
				temp = "Initial Marking: (";
				for(int k = 1; k <= places; k++){
					initialMarking[k-1] = -1;
					while(initialMarking[k-1] < 0){
						System.out.print("p" + k + ": ");
						initialMarking[k-1] = askForInt();
					}
					if(k == places)	{temp += initialMarking[k-1] + ")";}
					else			{temp += initialMarking[k-1] + ", ";}
				}
				System.out.println(temp);
	}

	// checks for valid input
	public int askForInt(){
		int answer = -1;
		if(scan.hasNextInt()){
			answer = scan.nextInt();
		}
		else {
			scan.next();
		}
		return answer;
	}
	
	public void findReachableMarkings(){
		//To-Do//
			// Start with initial marking
			// Loop through Transitions (1, 2, 3,...)
				// Check if Transition is fireable
					// if fireable
						// Subtract input
						// Add output
						// Create Marking
						// Check if marking already exists in reachableMarkings
						// if marking exists 
							// do nothing
						// else
							// add marking to reachableMarkings
					// else
						// next transition
		
		// need to somehow loop through entire set of possible transition combinations
			// for example, 1 > 2 > 4 > 1 > 4 > 1 compared to 1 > 4 > 1
			// use a tree to represent markings like in class
			// recursion? every new marking becomes an "initialMarking" and calls the method again
				// possibly finReachableMarkings(int[] marking)
	}
	/**
	 * @return the places
	 */
	public int getPlaces() {
		return places;
	}

	/**
	 * @return the transitions
	 */
	public int getTransitions() {
		return transitions;
	}

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @return the initialMarking
	 */
	public int[] getInitialMarking() {
		return initialMarking;
	}

	/**
	 * @param places the places to set
	 */
	public void setPlaces(int places) {
		this.places = places;
	}

	/**
	 * @param transitions the transitions to set
	 */
	public void setTransitions(int transitions) {
		this.transitions = transitions;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @param initialMarking the initialMarking to set
	 */
	public void setInitialMarking(int[] initialMarking) {
		this.initialMarking = initialMarking;
	}
	
	
}
