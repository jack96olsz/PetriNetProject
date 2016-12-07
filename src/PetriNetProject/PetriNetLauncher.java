package PetriNetProject;

import java.util.Arrays;

public class PetriNetLauncher {
	public static void main(String[] args){
		UserInput ui = new UserInput();
		System.out.println(ui.getInput());
		ui.findReachableMarkings(ui.getInitialMarking());
		ui.printReachableMarkings();
	}
}
