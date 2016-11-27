package PetriNetProject;

import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
	private Scanner scan;
	private int places;
	private int transitions;
	private String input;
	private String output;
	private int[] initialMarking;
	
	public UserInput(){
		scan = new Scanner(System.in);
	}
	
	public String getInput(){
		String outputString = "";
		
		System.out.println("Input Petri Net information");
		System.out.println("Enter number of places:");
		places = scan.nextInt();
		System.out.println("Enter number of transitions:");
		transitions = scan.nextInt();
		initialMarking = new int[places];
		processInput();
		outputString = "\nPlaces: " + places + "\nTransitions: " + transitions + "\nInput: " + input + "\nOutput: " + output + "\nInitial Marking: " + Arrays.toString(initialMarking);
		return outputString;
	}
	
	public void processInput(){
		Transition[] trans = new Transition[transitions];
		String temp = "";
		// Loop through IO for transitions based on # of places and transitions entered by the user
		for(int i = 1; i <= transitions; i++){
			System.out.println("Enter Input for t" + i);
			trans[i-1] = new Transition(places);
			temp = "t" + i + ": (";
			for(int j = 1; j <= places; j++){
				System.out.print("p" + j + ": ");
				trans[i-1].getInput()[j-1] = scan.nextInt();
				if(j == places)	{temp += trans[i-1].getInput()[j-1] + ")";}
				else			{temp += trans[i-1].getInput()[j-1] + ", ";}
			}
			System.out.println(temp);
			System.out.println("Enter Output for t" + i);
			trans[i-1] = new Transition(places);
			temp = "t" + i + ": (";
			for(int j = 1; j <= places; j++){
				System.out.print("p" + j + ": ");
				trans[i-1].getOutput()[j-1] = scan.nextInt();
				if(j == places)	{temp += trans[i-1].getOutput()[j-1] + ")";}
				else			{temp += trans[i-1].getOutput()[j-1] + ", ";}
			}
			System.out.println(temp);
		}
		
		// get user's initial marking
		System.out.println("Enter initial marking");
		temp = "Initial Marking: (";
		for(int k = 1; k <= places; k++){
			System.out.print("p" + k + ": ");
			initialMarking[k-1] = scan.nextInt();
			if(k == places)	{temp += initialMarking[k-1] + ")";}
			else			{temp += initialMarking[k-1] + ", ";}
		}
		System.out.println(temp);
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
