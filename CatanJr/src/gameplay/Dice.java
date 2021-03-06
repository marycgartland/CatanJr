package gameplay;

//-----------------------------------------------------------------------------------------------------------
// This Singleton class represents the dice and is called for rolling the dice on turns.
//-----------------------------------------------------------------------------------------------------------

public class Dice {
	
	// ----------------------------------------------------------------------------------------
	// ---------- Variables -------------------------------------------------------------------
	// ----------------------------------------------------------------------------------------
	private int min = 1;
	private int max = 6;
	private int diceRollValue;
	private static Dice instance = new Dice();
	
	// ----------------------------------------------------------------------------------------
	// ----------- Constructor ----------------------------------------------------------------
	// ----------------------------------------------------------------------------------------
	private Dice() {
	}
	
	// ----------------------------------------------------------------------------------------
	// ---------- Method: rollDice ------------------------------------------------------------
	// This method is to get a random dice value, between 1 and 6 
	// ----------------------------------------------------------------------------------------
	public int rollDice(){
		diceRollValue = (int)Math.floor(Math.random()*(max-min+1)+min);
	    return diceRollValue;
	}

	// Get Method 
	public int getDiceRollValue() {
		return diceRollValue;
	}
	
	// Get the instance of the dice
	   public static Dice getInstance(){
	      return instance;
	   }
}