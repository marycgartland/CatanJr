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
			// TODO: Abstract classes and interfaces?
			// TODO: Limit any code repetition
			// TODO: Error check for players buying cocotiles - entering incorrect options
			// TODO: Delete options for 1 player and 0 player from "BoardSetup" as these options won't make it that far 
					// @EP: Do you agree w this?
		// place ship/lair still looks very long and loopy - def could be simplified/tidier if we have time. (not necessary but a would-be-nice)
    }
}
