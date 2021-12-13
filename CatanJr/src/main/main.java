package main;

import gameplay.GameManager;

public class main {
	
	public static void main(String[] args) {
		
		
		GameManager gameManager = GameManager.getInstance(); 
		gameManager.startGame();
		
		// Need to write instruction manual
		// change players pocket back to 0
		
		// IN PLAYER CLASS
			// TODO: Change these back to 0. they are just set to 20 for testing purposes at the moment
		
		// More things I have to do: 
			// TODO: Abstract classes and interfaces?
			// TODO: Limit any code repetition
			// TODO: Error check for players buying cocotiles - entering incorrect options
			// TODO: Delete options for 1 player and 0 player from "BoardSetup" as these options won't make it that far 
					// @EP: Do you agree w this? - EP: Yes
		// place ship/lair still looks very long and loopy - def could be simplified/tidier if we have time. (not necessary but a would-be-nice)
		
		// EP: Notes from meeting:
		// GameManager should be singleton design pattern: one instance of one class only, maybe use for other classes where we only need one instance
		// if more than 2/3 classes have rference to the view(interactor class) then there may be a problem, need unified reference to the view through the controller class
		// implement observer design pattern to checkWinner mehtod, should be seperate method, not in the loop, the subject is the Player beacuse they keep track of the number of lairs,
		// the observer is the GameManager class, so code should only ne notified if the condition is satisfied (lairs=7)
		// Class diagrams: show cardinality
		// Sequence diagrams: include parameters to methods
		// need to update sequence diagram now that I have an observer method to check if theres a winner, it affects GameManager class, PlayerTurn(end game method) and Player (attach and notify methods)
		
		
		
    }
}
