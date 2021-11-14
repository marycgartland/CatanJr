package gameplay;


// Some to do's (noting that this is very much not finished)
// Combine all the valid option check methods into 1
// Connect the build method to the board once that is set up
// Buy method
// Trade method 

import java.util.Scanner;
import gameplay.Dice;
import player.Player;
import resources.Resources;

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
	private Player player;
	private String toBuild;
	
	//-----------------------------------------------------------
	//----------- Constructor -----------------------------------
	//-----------------------------------------------------------
	public PlayerTurn(Player thisPlayer){
		this.player = thisPlayer;
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
		System.out.println("It is your turn, "+ player.getName() + ". You rolled a " + diceValue + ".\n"); // add in player.name once player is set up

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
				build();
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
	//---------- Build Method -----------------------------------
	//-----------------------------------------------------------
	public void build() {
		// TO DO: Check first if they are even able to build ships or layers with the board
		// TO DO: Give them option of location to build on
		// Pirates Lair = 1 cutlass, 1 molasses, 1 goat & 1 wood
		System.out.print("Costs:\n	Ship: 1 Wood, 1 Goat\n	Lair: 1 Wood, 1 Goat, 1 Molasses, 1 Cutlass\n");
		if(player.getCutlassesCount()>=1 && player.getWoodCount()>=1 && player.getGoatsCount()>=1 && player.getMolassesCount()>=1){
			System.out.print("Would you like to build a ship [S] or a lair [L]? ");
			// Scan in user response 
			Scanner scanbuild = new Scanner(System.in);
			toBuild = scanbuild.next();
			// TO DO: Check for valid option
			// Call buildShip or buildLair method based on user input
			if(toBuild.equals("S") || toBuild.equals("s")) {
				buildShip();
			} else{
				buildLair();
			} 
		}
		// Ship = 1 goat & 1 wood
		else if(player.getWoodCount()>=1 && player.getGoatsCount()>=1) {
			System.out.print("Would you like to build a ship? [Y/N] ");
			// Scan in user input
			Scanner scanbuild = new Scanner(System.in);
			toBuild = scanbuild.next();
			// TO DO: Check for valid option
			if(toBuild.equals("Y") || toBuild.equals("y")){
				buildShip();
			}
		} 
		
		// If they don't have enough resources to build either
		else {
			System.out.print("You do not have enough resources to build either a ship or a lair.\n");
		}
		
	}
		// If they choose a pirates lair or ship, place these on the board 
		// Should pirates lair and ships be their own classes and objects probably?
		// Build must be alternating ship and Lair 
	
	//-----------------------------------------------------------
	//---------- Build Ship Method ------------------------------
	//-----------------------------------------------------------
	public void buildShip() {
		// TO DO: placement of the ship
		System.out.print("* Building ship *\n");
		// Take a goat and a wood out of the players pocket
		player.removeResource(Resources.Wood, 1);
		player.removeResource(Resources.Goats, 1);
	}
	
	//-----------------------------------------------------------
		//---------- Build Lair Method ------------------------------
		//-----------------------------------------------------------
		public void buildLair() {
			// TO DO: placement of the ship
			System.out.print("* Building lair *\n");
			// Take a goat, a wood, a cutlass, and a molasses out of the players pocket
			player.removeResource(Resources.Wood, 1);
			player.removeResource(Resources.Goats, 1);
			player.removeResource(Resources.Cutlasses, 1);
			player.removeResource(Resources.Molasses, 1);
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
		
		tradeInResource = assignResources(tradeInItem);	// Assign the word value to the letter entered
		System.out.print("You have chosen to trade in " + tradeInResource + "\n");
		return tradeInResource;	// Return the selected resource to the user
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
		
		tradeOutResource = assignResources(tradeOutItem);	// Assign the word value to the letter entered
		System.out.print("You have chosen to obtain " + tradeOutResource + "\n");
		return tradeOutResource;	// Return the selected resource to the user
	}
	
	//-----------------------------------------------------------
	//---------- Method: assign resources to letters ------------
	//-----------------------------------------------------------
	public String assignResources(String resourceLetter) {
		if(resourceLetter.equals("W")) {
			return "Wood";
		} else if (resourceLetter.equals("M")) {
			return "Molasses";
		} else if(resourceLetter.equals("G")) {
			return "Gold";
		} else {
			return "Cutlass";
		}
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
	//---------- Buy Method ------------
	//----------------------------------
		// This Method lets you buy cocotiles 
		// Cost: Cocotile = 1 cutlass, 1 molasses & 1 gold 
		// If the player chooses a cocotile, give them a cocotile, and follow instructions on cocotile
	