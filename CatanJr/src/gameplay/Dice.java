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
}