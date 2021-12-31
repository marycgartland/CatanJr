package main;

// -----------------------------------------------------------------------------------------------------------
// This main class is used to call the GameManager to begin and run the game.
// -----------------------------------------------------------------------------------------------------------
import gameplay.GameManager;

public class main {
	
	public static void main(String[] args) {
		GameManager gameManager = GameManager.getInstance(); 
		gameManager.startGame();
    }
}
