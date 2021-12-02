package gameplay;

import java.util.Random;

public class Dice {
	
	//----------------------------------------------------------------------------------------
	//---------- Variables -------------------------------------------------------------------
	//----------------------------------------------------------------------------------------
	private int min = 1;
	private int max = 6;
	private int diceRollValue;
	
	
	//----------------------------------------------------------------------------------------
	//----------- Constructor ----------------------------------------------------------------
	//----------------------------------------------------------------------------------------
	public Dice() {
	}
	
	
	//----------------------------------------------------------------------------------------
	//---------- Method: rollDice ------------------------------------------------------------
	// This method is to get a random dice value, between 1 and 6 
	//----------------------------------------------------------------------------------------
	public int rollDice(){
		diceRollValue = (int)Math.floor(Math.random()*(max-min+1)+min);
		//System.out.println(diceRollValue);
	    return diceRollValue;
	}
	
	
	//----------------------------------------------------------------------------------------
	//---------- Method: checkRoll -----------------------------------------------------------
	// This method is to check what the next things to be done are depending on the dice value 
	//----------------------------------------------------------------------------------------
	// MG: All methods should be public, correct? EP: Yas
	public void checkRoll(int diceRollValue){
		// If the user rolls 1-5...
		
		// roll 1:  cutlasses, wood, goat
		// roll 2:  wood, molasses, goat
		// roll 3:  wood, gold, 
		// roll 4:  cutlasses, molasses
		// roll 5:  goat, gold
		if(diceRollValue <=5){
			// check which islands are associated with that number
			// Assign people touching the island with that given resource, UNLESS the ghost captain is on that island
		}
		
		// If the user rolls a 6 ...
		else{
			// Check the location of the ghost captain
			// provide places for the user to be able to move the ghost captian
			// relocate the ghost captain
			// assign the player 2 resources of the player that just rolled the 6
		}
	}
	
	// Get Method 
	public int getDiceRollValue() {
		return diceRollValue;
	}
	
	
}