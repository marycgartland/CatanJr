package main;

import gameplay.GameManager;

public class main {
	
	public static void main(String[] args) {
		
		GameManager gameManager = new GameManager();
		gameManager.startGame();
		
			
		// Need to write instruction manual
		
		// IN PLAYER CLASS
			// TODO: Change these back to 0. they are just set to 20 for testing purposes at the moment
		
		// More things I have to do: 
			// TODO: Finish changing all user interactions to interactor class
			// TODO: Abstract classes and interfaces?
			// TODO: Limit any code repetition
			// TODO: Error check for players buying cocotiles - entering incorrect options

		// for placing ship/lair choosing a valid integer is not a good enough check, if its between 1 and 7 it will be accepted even if there arent 7 options to choose some
    }
}
