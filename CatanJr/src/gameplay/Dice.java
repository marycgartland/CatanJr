package gameplay;

import java.util.Random;

public class Dice {
	
	// Variables
	private int min = 1;
	private int max = 6;
	private int diceRollValue;
	
	// Constructor
	public Dice() {
	}
	
	// Get random dice value  
	public int rollDice(){
		diceRollValue = (int)Math.floor(Math.random()*(max-min+1)+min);
		System.out.println(diceRollValue);
	    return diceRollValue;
	}
	
	// If the user rolls 1-5...
			// check which islands are associated with that number
			// Assign people touching the island with that given resource, UNLESS the ghost captain is on that island
	
	// If the user rolls a 6...
			// Check the location of the ghost captain
			// provide places for the user to be able to move the ghost captian
			// relocate the ghost captain
			// assign the player 2 resources of the player that just rolled the 6
}