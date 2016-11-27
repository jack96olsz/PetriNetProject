package PetriNetProject;

public class Transition {
	private int[] input;
	private int[] output;
	
	public Transition(int n){
		input = new int[n];
		output = new int[n];
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
