package PetriNetProject;

import java.util.Arrays;

public class Transition {
	private int[] input;
	private int[] output;
	
	public Transition(int n){
		input = new int[n];
		output = new int[n];
	}
	
	public boolean isFireable(int[] marking){
		boolean status = false;
		for (int i = 0; i < input.length; i++){
			if (marking[i] >= input[i]){
				status = true;
			}
			else return false;
		}
		return status;
	}

	public int[] subtractInput(int[] marking){
		System.out.println("Sub marking" + Arrays.toString(marking));
		for (int i = 0; i < input.length; i++){
			marking[i] -= input[i];
			System.out.println("Sub place " + i + ":" + marking[i]);
		}
		return marking;
	}
	public int[] addOutput(int[] marking){
		System.out.println("Add marking" + Arrays.toString(marking));
		for (int i = 0; i < output.length; i++){
			marking[i] += output[i];
			System.out.println("Output Marking " + i + ":" + Arrays.toString(output));
			System.out.println("Add place " + i + ":" + marking[i]);
		}
		return marking;
	}
	/**
	 * @return the input
	 */
	public int[] getInput() {
		return input;
	}

	/**
	 * @return the output
	 */
	public int[] getOutput() {
		return output;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(int[] input) {
		this.input = input;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(int[] output) {
		this.output = output;
	}
}
