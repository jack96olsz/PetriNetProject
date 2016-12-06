package PetriNetProject;

import java.util.Arrays;

public class PetriNetLauncher {
	public static void main(String[] args){
		UserInput ui = new UserInput();
		System.out.println(ui.getInput());
		for(Transition T : ui.getTrans()){
			System.out.println(Arrays.toString(T.getOutput()));
		}
		ui.findReachableMarkings(ui.getInitialMarking());
		ui.printReachableMarkings();
	}
}
