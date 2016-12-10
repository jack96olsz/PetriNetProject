package PetriNetProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
	private Scanner scan; 			// For scanning user input
	private int places;				// # of places entered by user
	private int transitions;		// # of transitions entered by user
	private Transition[] trans;		// List of transitions
	private String input;			// Input for all transitions
	private String output;			// Output for all transitions
	private int[] initialMarking;	// Marking entered by user
	private int[] currentMarking; 	// Keeps track of marking currently in use
	private ArrayList<int[]> reachableMarkings; // List of reachable markings

	//Constructor
	public UserInput(){
		scan = new Scanner(System.in);
		places = 1;
		transitions = 0;
		input = "";
		output = "";
		reachableMarkings = new ArrayList<int[]>();
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
		reachableMarkings.add(initialMarking.clone());
		System.out.println("Add marking to reachableMarkings: " + Arrays.toString(initialMarking));
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
			temp = "t" + i + ": (";
			for(int k = 1; k <= places; k++){
				trans[i-1].getOutput()[k-1] = -1;
				while(trans[i-1].getOutput()[k-1] < 0){
					System.out.print("p" + k + ": ");
					trans[i-1].getOutput()[k-1] = askForInt();
				}
				if(k == places)	{temp += trans[i-1].getOutput()[k-1] + ")";}
				else			{temp += trans[i-1].getOutput()[k-1] + ", ";}
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
	
	public void findReachableMarkings(int[] marking){
		ArrayList<int[]> foundMarkings = new ArrayList<int[]>();
		for (int i = 0; i < trans.length; i++){
			currentMarking = marking.clone();
			if (trans[i].isFireable(currentMarking)){
				currentMarking = trans[i].subtractInput(currentMarking.clone());
				currentMarking = trans[i].addOutput(currentMarking.clone());
				
				for(int j = 0; j < reachableMarkings.size(); j++){
					if(compareMarkings(reachableMarkings.get(j), currentMarking)){
						System.out.println("reach mark: " + Arrays.toString(reachableMarkings.get(j)));
						System.out.println("skip marking: " + Arrays.toString(currentMarking));
						System.out.println("skip marking: " + Arrays.toString(currentMarking));
						j = reachableMarkings.size() - 1;
					}
					else if(j == reachableMarkings.size()-1){
						System.out.println("add?");
						reachableMarkings.add(currentMarking.clone());
						System.out.println("+ to found");
						foundMarkings.add(currentMarking.clone());
						System.out.println("Add marking to reachableMarkings: " + Arrays.toString(currentMarking));
					}
				}
			}
		}
		int[] array;
		for(int i = 0; i < reachableMarkings.size(); i++){
			for(int j = i + 1; j < reachableMarkings.size(); j++){
				if(isLessThanEqual(reachableMarkings.get(i), reachableMarkings.get(j))){
					array = reachableMarkings.get(i).clone();
					reachableMarkings.set(i, setW(array.clone(), reachableMarkings.get(j).clone()));
				}
			}
		}
		for(int i = 0; i < foundMarkings.size(); i++){
			for(int j = i + 1; j < foundMarkings.size(); j++){
				if(isLessThanEqual(foundMarkings.get(i), foundMarkings.get(j))){
					array = foundMarkings.get(i).clone();
					foundMarkings.set(i, setW(array.clone(), foundMarkings.get(j).clone()));
				}
			}
		} 
		
		
		// detect w markings to stop the program from searching for more of the same marking
			// after each level, search for w
				// if (compare every marking) == islessthan or equal
					// then test for w conditions
						// if true
							// set w marking (maybe make the w marking a negative number)
								// set w for reachable markings and found markings to stop infinite loop
						// else
							// continue to next marking

		
		// Testing for W 
		// If there is a Marking M that is less than another marking M' then through some sequence of transitions there is an w marking
		// Incidence Matrix and State equation show Petri Net Behavior
		// With Marking M and M', ^M = M' - M and the ^M represents the change in tokens that must be a result of a transition firing sequence to get from M to M'
			// We first need to find the the firing sequence and then figure out how many times that sequence must be fired to get from M to M' (even if its only once)
				// Possibly done with an Incidence Matrix Solution
					// find two markings that fit criteria of w
					// find solution
					// apply w to solution
		
		
			// if Output - Input = a marking with a positive place and no negative places then positive place = w
				// EX:	O:(0 0 1) - I:(1 0 0) = (-1 0 1) <Normal>
				//		O:(1 0 1) - I:(1 0 0) = (0 0 1) <w>
		
		
				//		O:(0 0 1) - I:(1 0 0) = (-1 0 1)
				// 		O:(1 1 0) - I:(0 0 1) = (1 1 -1) <w>
		
		boolean stop = false;
		for (int i = 0; i < foundMarkings.size(); i++){
			for (int j = 0; j < foundMarkings.get(i).length; j++){
				if (foundMarkings.get(i)[j] > 100){
					System.out.print("going over 100");
					stop = true;
				}
			}
			if(!stop){
				findReachableMarkings(foundMarkings.get(i).clone());
			}
		}
		//To-Do//
			// Start with initial marking
			// Loop through Transitions (1, 2, 3,...)
				// Check if Transition is fireable
					// if (transition.isFireable())
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
	
	public void printReachableMarkings(){
		int[] array;
		for (int i = 0; i < reachableMarkings.size(); i++){
			array = reachableMarkings.get(i).clone();
			System.out.println("M" + i + ": " + Arrays.toString(array));
		}
	}
	
	public boolean compareMarkings(int[] first, int[] second){
		for (int i = 0; i < first.length; i++){
			if(first[i] != second[i]){
				return false; // markings are different
			}
		}
		return true; // markings are the same
	}
	
	public boolean isLessThanEqual(int[] first, int[] second){
		for (int i = 0; i < first.length; i++){
			if ((first[i] != -1) && (second[i] != -1)){
				if(!(first[i] <= second[i])){
					return false; // first is not less than or equal to second
				}
			}
			else {
				if(first[i] <= second[i]){
					return false;
				}
			}
		}
		return true; // first is less than or equal to second
	}
	
	public int[] setW(int[] first, int[] second){
		int[] array = new int[first.length];
		for (int i = 0; i < first.length; i++){
			array[i] = first[i] - second[i];
			if(array[i] > 0){
				first[i] = -1;
			}
		}
		return first;
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
	/**
	 * @return the reachableMarkings
	 */
	public ArrayList<int[]> getReachableMarkings() {
		return reachableMarkings;
	}

	/**
	 * @param reachableMarkings the reachableMarkings to set
	 */
	public void setReachableMarkings(ArrayList<int[]> reachableMarkings) {
		this.reachableMarkings = reachableMarkings;
	}

	/**
	 * @return the trans
	 */
	public Transition[] getTrans() {
		return trans;
	}

	/**
	 * @param trans the trans to set
	 */
	public void setTrans(Transition[] trans) {
		this.trans = trans;
	}
	
}
