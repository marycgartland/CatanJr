package gameplay;

import java.util.Scanner;
import gameplay.Dice;

public class PlayerTurn {
// The example project also has all of the turn steps in this 
	
	//-----------------------------------------------------------
	//---------- Variables --------------------------------------
	//-----------------------------------------------------------
	private int marketPlaceUse;	// Integer variable to keep track of how many times the marketplace has been used this turn 
	private boolean turn;		// turn = true if they are taking their turn, turns to false if they are finished their turn 
	private int diceValue;		// Value rolled on the dice 
	// private Player player;
	
	//-----------------------------------------------------------
	//----------- Constructor -----------------------------------
	//-----------------------------------------------------------
	//public PlayerTurn(Player thisPlayer)
	public PlayerTurn() {
		//this.player = thisPlayer;
		this.marketPlaceUse = 0;	// Initialize the marketplace use at 0 for each new turn 
		this.turn = true;			// This person is currently taking their turn 
		
	}
	
	//-----------------------------------------------------------
	//---------- Method: takeTurn -------------------------------
	//-----------------------------------------------------------

	Dice dice1 = new Dice(); // The dice needs to be created in game setup, not here. Just added here for now 
	
	public void takeTurn() {
		//----- Roll the dice ------
		diceValue = dice1.rollDice();
		System.out.println("It is your turn. You rolled a " + diceValue + ".\n"); // add in player.name once player is set up

		//----- While the players turn isn't over, they will be presented with all options -----
		while (turn == true) {
			//----- Let player choose if they wish to build, buy or trade -----
			System.out.print("Would you like to Buy [B], Build [Bd], Trade [T], or End turn [E]?");
			// Scan in their choice
			Scanner scan = new Scanner(System.in);
			String option = scan.next();
			// Error Check 
			if(option != "B" || option != "Bd" || option != "T" || option != "E") {
				System.out.println("You did not select one of the available options. Please try again.\nWould you like to Buy [B], Build [Bd], Trade [T], or End turn [E]?");
				Scanner scan2 = new Scanner(System.in);
				option = scan2.next();
			}
			System.out.print(option);
			
			
			
			// Buy method: This method lets you buy cocotiles
			// Build method: This method lets you build lairs/ships on the board
			// Trade method
			// They can also choose to end turn 
			// During turn, can be checking to see if they have won? Don't know where this happens 
			
			
		}	
	}
}
	
	
	
	//----------------------------------------------------------
	//---------- "Your-turn" method ----------------------------
	//----------------------------------------------------------
	
	// Notes: This method gives the user a choice of what actions they want to take on their turn
	// We will need to make something in the main class or something that loops through the players turns

		
	//	System.out.print("Would you like to Buy [B], Build [Bd], Trade [T], or End turn [E]?");
		// Take in the option the user wants. Will need to add in error checking later
	//	Scanner scan = new Scanner(System.in);
	//	String option = scan.next();
	//	System.out.print(option);
		
		// Call the 'Buy' Method if the user enters 'B'
	//	if(option == "B") {	
	//	}
	//	
		// Call the 'Build' method if the user enters 'Bd'
	//	else if(option == "Bd") {
	//	}
		
		// Call the 'Trade' method if the user enters 'T'
	//	else if(option == "T") {		
	//	}
		
		// If the user wishes to end turn, leave this method
	//	else {
	//		return;
	//	}
		
		// To do: make sure that this loops, so the player can do multiple things in one turn
	//}
	
	
	//----------------------------------
	//---------- Build Method ----------
	//----------------------------------
	
			// Costs:
				// Pirates Lair = 1 cutlass, 1 molasses, 1 goat & 1 wood
				// Ship = 1 goat & 1 wood
			// If they choose a pirates lair or ship, place these on the board 
				// Should pirates lair and ships be their own classes and objects probably?
			// Build must be alternating ship and Lair 
	
	//----------------------------------
	//---------- Buy Method ------------
	//----------------------------------
			// Cost: Cocotile = 1 cutlass, 1 molasses & 1 gold 
			// If the player chooses a cocotile, give them a cocotile, and follow instructions on cocotile
	
	//----------------------------------
	//---------- Trade Method ----------
	//----------------------------------
			// Option 1: Trade with Marketplace
				// Trade one tile from a players pocket (of choice) with one tile from marketplace (of choice)
				// Can only trade with Marketplace once per turn 
			// Option 2: Trade with stockpile 
				// can trade 2 of the same pocket tiles of choice with 1 tile from the stockpile
				// No limit on times, only limited by resources in pocket/stockpile
			// Option 3: Trade with another player