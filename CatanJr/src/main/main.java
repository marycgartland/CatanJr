package main;

//----------------------------------------------------------------------------------------
// This main class is used to call the GameManager to begin and run the game.
//----------------------------------------------------------------------------------------
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

		// TODO: Make a summary at the top of each class? 
		// place ship/lair still looks very long and loopy - def could be simplified/tidier if we have time. (not necessary but a would-be-nice)
		
		// EP: Notes from meeting:
		// if more than 2/3 classes have rference to the view(interactor class) then there may be a problem, need unified reference to the view through the controller class
		// Class diagrams: show cardinality -  I updated this but run through it with mary and make sure it makes sense
		// need to update sequence diagram now that I have an observer method to check if theres a winner, it affects GameManager class, PlayerTurn(end game method) and Player (attach and notify methods)		
    }
}
