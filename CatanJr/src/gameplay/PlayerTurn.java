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
	private boolean tradeOutValid;
	private boolean tradeInValid;
	private String tradeInResource;
	private String tradeOutResource;
	private String tradeOutItem;
	private String tradeInItem;

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
			System.out.print("Would you like to Buy [B], Build [Bd], Trade [T], or End turn [E]? ");
			// Scan in their choice
			Scanner scan = new Scanner(System.in);
			String option = scan.next();

			//----- If the player wants to buy ----------------------------
			if(option.equals("B")) {
				System.out.print("You have chosen to buy.\n");
				// Call the buy method
			} 	
			//----- If the player wants to build --------------------------
			else if(option.equals("Bd")) {
				System.out.print("You have chosen to build.\n");
				// Call the build method 
			}
			//----- If the player wants to trade --------------------------
			else if(option.equals("T")) {
				System.out.print("You have chosen to Trade.\n");
				Trade();	// Call the trade method 
			}
			//----- If the player wants to end their turn -----------------
			else if(option.equals("E")) {
				System.out.print("You have chosen to End your turn.\n");
				turn = false;
			}
			//----- Error Check: If the player entered something else -----
			else{
				System.out.println("You did not select one of the available options. Please try again. ");
			}
		}
	}
	
	//-----------------------------------------------------------
	//---------- Trade Method -----------------------------------
	//-----------------------------------------------------------
	public void Trade() {
		//----- Give the user options on what trade they wish to make -----
		if(marketPlaceUse == 0) {	// You can only trade with the marketplace once per turn
			System.out.print("Would you like to trade with the marketplace [M], stockpile [S], or another player[O]? ");
		} else {
			System.out.print("Would you like to trade with the stockpile [S] or another player [O]? ");
		}
		Scanner scan = new Scanner(System.in);
		String option = scan.next();
		
		//----- Option 1: Trade with Marketplace --------------------------
			// Trade one tile from a players pocket (of choice) with one tile from marketplace (of choice)
			// Can only trade with Marketplace once per turn 
		if(option.equals("M") && marketPlaceUse == 0) {
			System.out.print("You have chosen to trade with the marketplace.\n");
			marketPlaceUse = 1;
		}
		
		//----- Option 2: Trade with Stockpile ----------------------------
			// can trade 2 of the same pocket tiles of choice with 1 tile from the stockpile
			// No limit on times, only limited by resources in pocket/stockpile
		else if(option.equals("S")) {
			System.out.print("You have chosen to trade with the stockpile.\n");
			// Show them which items they have 2 of that they can trade
			tradeOut();	// Let user select what Item they wish to obtain from the stockpile
			tradeIn();	// Let user select what item they wish to push into the stockpile in return 
		}
		
		//----- Option 3: Trade with another player -----------------------
		else if (option.equals("O")) {
			System.out.print("You have chosen to trade with the another player.\n");
		}

		//----- Error check if the user selects an invalid option ---------
		else{
			System.out.print("You did not select one of the available options. Please try again. ");
		}

	}
	
	
	//-----------------------------------------------------------
	//---------- Method: TradeIn --------------------------------
	//-----------------------------------------------------------
	
	public String tradeIn() {
		tradeInValid = false;
		while(tradeInValid == false) {
			System.out.print("What item would to trade in (Wood [W], Molasses [M], Gold [G], or Cutlass [C])? ");
			Scanner scanTI = new Scanner(System.in);
			tradeInItem = scanTI.next();
			tradeInValid = validResourceCheck(tradeInItem);
		}
		
		// Assign the word value to the letter entered
		if(tradeInItem.equals("W")) {
			tradeInResource = "Wood";
		} else if (tradeInItem.equals("M")) {
			tradeInResource = "Molasses";
		} else if(tradeInItem.equals("G")) {
			tradeInResource = "Gold";
		} else {
			tradeInResource = "Cutlass";
		}
		
		System.out.print("You have chosen to trade in " + tradeInResource + "\n");
		
		// Return the selected resource to the user
		return tradeInResource;
	}
	
	//-----------------------------------------------------------
	//---------- Method: TradeOut --------------------------------
	//-----------------------------------------------------------
	public String tradeOut() {
		tradeOutValid = false;
		while(tradeOutValid == false) {
			System.out.print("What item would you like to obtain (Wood [W], Molasses [M], Gold [G], or Cutlass [C])? ");
			Scanner scanTO = new Scanner(System.in);
			tradeOutItem = scanTO.next();
			tradeOutValid = validResourceCheck(tradeOutItem);
		}
		
		// Assign the word value to the letter entered
		if(tradeOutItem.equals("W")) {
			tradeOutResource = "Wood";
		} else if (tradeOutItem.equals("M")) {
			tradeOutResource = "Molasses";
		} else if(tradeOutItem.equals("G")) {
			tradeOutResource = "Gold";
		} else {
			tradeOutResource = "Cutlass";
		}
		
		System.out.print("You have chosen to obtain " + tradeOutResource + "\n");
		
		// Return the selected resource to the user
		return tradeOutResource;
	}
	
	
	//-----------------------------------------------------------
	//---------- Method: validResourceCheck ---------------------
	//-----------------------------------------------------------
	public boolean validResourceCheck(String enteredResource){
		// If entered resource is  avalide resource return true, otherwise return false
		if (enteredResource.equals("M") || enteredResource.equals("W") || enteredResource.equals("C") || enteredResource.equals("G")) {
			return true;
		} else {
			System.out.print("Choice is invalid. Please enter a valid choice.\n");
			return false;
		}
		
	}
	
}
	
	
	
	// During turn, can be checking to see if they have won? Don't know where this happens 
	
	//----------------------------------------------------------
	//---------- "Your-turn" method ----------------------------
	//----------------------------------------------------------
	
	// Notes: This method gives the user a choice of what actions they want to take on their turn
	// We will need to make something in the main class or something that loops through the players turns

	//----------------------------------
	//---------- Build Method ----------
	//----------------------------------
		// This method lets you build lairs/ships on the board
		// Costs:
			// Pirates Lair = 1 cutlass, 1 molasses, 1 goat & 1 wood
			// Ship = 1 goat & 1 wood
		// If they choose a pirates lair or ship, place these on the board 
			// Should pirates lair and ships be their own classes and objects probably?
		// Build must be alternating ship and Lair 
	
	//----------------------------------
	//---------- Buy Method ------------
	//----------------------------------
		// This Method lets you buy cocotiles 
		// Cost: Cocotile = 1 cutlass, 1 molasses & 1 gold 
		// If the player chooses a cocotile, give them a cocotile, and follow instructions on cocotile
	