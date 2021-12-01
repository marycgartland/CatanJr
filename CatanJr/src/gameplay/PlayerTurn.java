package gameplay;


// Some to do's (noting that this is very much not finished)
// Combine all the valid option check methods into 1
// Connect the build method to the board once that is set up
// Buy method
// Trade method 

import java.util.Scanner;

import board.Board;
import board.Marketplace;
import board.Stockpile;
import gameplay.Dice;
import main.Interactor;
import player.Player;
import resources.*;

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
	private String toBuy;
	Interactor interactor = new Interactor();
	protected Marketplace marketplace; // need to take in marketplace that has been created
	protected Stockpile stockpile;
	protected CocoTiles cocotiles;
	protected Board board;

	
	//-----------------------------------------------------------
	//----------- Constructor -----------------------------------
	//-----------------------------------------------------------
	public PlayerTurn(Player thisPlayer, Marketplace marketplace, Stockpile stockpile, CocoTiles cocotiles, Board board){
		this.player = thisPlayer;
		this.marketPlaceUse = 0;	// Initialize the marketplace use at 0 for each new turn 
		this.turn = true;			// This person is currently taking their turn 
		this.marketplace = marketplace;
		this.stockpile = stockpile;
		this.cocotiles = cocotiles;
		
	}
	
	//-----------------------------------------------------------
	//---------- Method: takeTurn -------------------------------
	//-----------------------------------------------------------

	Dice dice1 = new Dice(); // The dice needs to be created in game setup, not here. Just added here for now 
	
	public void takeTurn() {
		//----- Roll the dice ------
		diceValue = dice1.rollDice();
		System.out.println("It is your turn, "+ player.getName() + ". You rolled a " + diceValue + ".\n"); // add in player.name once player is set up
		// should assign resources depending on the value rolled

		//----- While the players turn isn't over, they will be presented with all options -----
		while (turn == true) {
			//----- Let player choose if they wish to build, buy or trade -----
			interactor.printMessage("your turn");			
			String option = interactor.takeInAnswer(); 			// Scan in their choice

			//----- If the player wants to buy ----------------------------
			if(option.equals("B")) {
				interactor.printMessage("turn: buy");
				buy(); // Call the buy method
			} 	
			//----- If the player wants to build --------------------------
			else if(option.equals("Bd")) {
				interactor.printMessage("turn: build");
				build(); // Call the build method 
			}
			//----- If the player wants to trade --------------------------
			else if(option.equals("T")) {
				interactor.printMessage("turn: trade");
				marketplace.viewMarketplace();
				Trade();	// Call the trade method 
			}
			//----- If the player wants to end their turn -----------------
			else if(option.equals("E")) {
				interactor.printMessage("turn: end");
				turn = false;
			}
			//----- Error Check: If the player entered something else -----
			else{
				interactor.printMessage("turn: null");
			}
		}
	}
	
	
	//-----------------------------------------------------------
	//---------- Buy Method -------------------------------------
	//-----------------------------------------------------------
	// This Method lets you buy cocotiles 
	// If the player chooses a cocotile, give them a cocotile, and follow instructions on cocotile
	public void buy() {
		interactor.printMessage("cocotile price");
		if (player.getCutlassesCount() >= 1 && player.getGoldCount() >= 1 && player.getMolassesCount() >= 1) {
			interactor.printMessage("buy cocotile?");
			// Scan in user input
			toBuy = interactor.takeInAnswer();
			// TO DO: Check for valid option
			if (toBuy.equals("Y") || toBuy.equals("y")) {
				interactor.printMessage("bought cocotile");
				// remove cost from users pocket
				player.removeResource(Resources.Cutlasses, 1);
				player.removeResource(Resources.Gold, 1);
				player.removeResource(Resources.Molasses, 1);

				// return resources to stockpile when cocotile is bought
				stockpile.ReturnResource(Resources.Cutlasses, 1);
				stockpile.ReturnResource(Resources.Gold, 1);
				stockpile.ReturnResource(Resources.Molasses, 1);

				CocoTileTypes cocotile_bought = cocotiles.buyCocoTile(); // this tells which type of cocotile it is
				player.addCocoTile(); // add cocotile to players count of cocotiles
				cocotileAction(cocotile_bought); // follow instructions on cocotile
			}
		} else {
			interactor.printMessage("cannot buy: cocotile");
		}
	}
	
	// Method to complete action based on cocotile bought by player
	public void cocotileAction(CocoTileTypes cocotile) {
		if (cocotile.equals(CocoTileTypes.GoatCutlasses)) {
			// add resource to users pocket
			player.addResource(Resources.Goats, 2);
			player.addResource(Resources.Cutlasses, 2);

			// remove resources from stockpile
			stockpile.DistributeResource(Resources.Goats, 2);
			stockpile.DistributeResource(Resources.Cutlasses, 2);

		} else if (cocotile.equals(CocoTileTypes.WoodMolasses)) {
			// add resources to users pocket
			player.addResource(Resources.Wood, 2);
			player.addResource(Resources.Molasses, 2);

			// remove resources from stockpile
			stockpile.DistributeResource(Resources.Wood, 2);
			stockpile.DistributeResource(Resources.Molasses, 2);

		} else if (cocotile.equals(CocoTileTypes.GhostCaptain)) {
			// need to access board in order to give user options to place the ghost captain
			board.placeGhostCaptain();

		} else if (cocotile.equals(CocoTileTypes.ShipCastle)) {
			// need to let user place ships and lairs on board
			board.placeShip();
			board.placeLair();
		}
	}
	
	//-----------------------------------------------------------
	//---------- Build Method -----------------------------------
	//-----------------------------------------------------------
	public void build() {
		// TO DO: Check first if they are even able to build ships or layers with the board: cant build two of the same in a row
		// TO DO: Give them option of location to build on
		// Pirates Lair = 1 cutlass, 1 molasses, 1 goat & 1 wood
		interactor.printMessage("ship/lair price");
		if(player.getCutlassesCount()>=1 && player.getWoodCount()>=1 && player.getGoatsCount()>=1 && player.getMolassesCount()>=1){
			interactor.printMessage("build ship/lair");			
			toBuild = interactor.takeInAnswer(); 			// Scan in user response 
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
			interactor.printMessage("build ship?");
			// Scan in user input
			toBuild = interactor.takeInAnswer();
			// TO DO: Check for valid option
			if(toBuild.equals("Y") || toBuild.equals("y")){
				buildShip();
			}
		} 
		
		// If they don't have enough resources to build either
		else {
			interactor.printMessage("cannot buy ship/lair");
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
		interactor.printMessage("build: ship");
		// Take a goat and a wood out of the players pocket
		player.removeResource(Resources.Wood, 1);
		player.removeResource(Resources.Goats, 1);
		board.placeShip();
	}
	
	//-----------------------------------------------------------
		//---------- Build Lair Method ------------------------------
		//-----------------------------------------------------------
		public void buildLair() {
			// TO DO: placement of the ship
			interactor.printMessage("build: lair");
			// Take a goat, a wood, a cutlass, and a molasses out of the players pocket
			player.removeResource(Resources.Wood, 1);
			player.removeResource(Resources.Goats, 1);
			player.removeResource(Resources.Cutlasses, 1);
			player.removeResource(Resources.Molasses, 1);
			player.addLair();
			board.placeLair();
		}
	
	
	//-----------------------------------------------------------
	//---------- Trade Method -----------------------------------
	//-----------------------------------------------------------
	public void Trade() {
		//----- Give the user options on what trade they wish to make -----
		if(marketPlaceUse == 0) {	// You can only trade with the marketplace once per turn
			interactor.printMessage("trade M/S?");
		} else {
			interactor.printMessage("trade S?");
		}
		String option = interactor.takeInAnswer();
		
		//----- Option 1: Trade with Marketplace --------------------------
			// Trade one tile from a players pocket (of choice) with one tile from marketplace (of choice)
			// Can only trade with Marketplace once per turn 
		if(option.equals("M") && marketPlaceUse == 0) {
			interactor.printMessage("trade: M");
			marketplace.viewMarketplace();
			tradeMarketplace(); 			// call method to trade with marketplace
		}
		
		//----- Option 2: Trade with Stockpile ----------------------------
			// can trade 2 of the same pocket tiles of choice with 1 tile from the stockpile
			// No limit on times, only limited by resources in pocket/stockpile
		else if(option.equals("S")) {
			interactor.printMessage("trade: S");
			tradeStockpile(); // call method to trade with stockpile
			// EP: I dont think these methods below are needed now because the tradeStockpile does what they are both doing 01/12/2021
			// Show them which items they have 2 of that they can trade
			//tradeOut();	// Let user select what Item they wish to obtain from the stockpile
			//tradeIn();	// Let user select what item they wish to push into the stockpile in return 
			
		}

		//----- Error check if the user selects an invalid option ---------
		else{
			interactor.printMessage("turn: null");
		}

	}

	// Method to trade with marketplace
	// do i need to remove the resource from the users pocket
	public void tradeMarketplace() {
		interactor.printMessage("trade out"); // System.out.print("What item would you like to obtain (Wood [W],
												// Molasses [M], Gold [G], or Cutlass [C])? ");
		String trade_out = interactor.takeInAnswer();
		if (validResourceCheck(trade_out)) { // check if trade_out is vaild option
			boolean in_stock = marketplace.CheckForResourceMarketplace(assignResourcesType(trade_out)); // check if has marketplace has this in stock
																										
			if (in_stock) {
				interactor.printMessage("marketplace: in-stock");
				interactor.printMessage("trade in"); // System.out.print("What item would you like to trade in (Wood [W],
														// Molasses [M], Gold [G], Goat[GT] or Cutlass [C])? ");
				String trade_in = interactor.takeInAnswer();
				if (validResourceCheck(trade_in)) { // check if this trad_in answer is valid
					if (player.checkPocketResourcesLetter(trade_in) > 0) { // check users pocket to see if they have the resources to swap
						marketPlaceUse = 1; // successful trade with marketplace
						marketplace.SwapMarketplace(assignResourcesType(trade_out), assignResourcesType(trade_in), player);
						System.out.println(player.viewPocket()); // show user their pocket
					} else {
						interactor.printMessage("cannot trade");
					}
				}
			} else {
				interactor.printMessage("marketplace: out-of-stock");
			}
		}

	}
	
	
	// Method to trade with stockpile
	public void tradeStockpile() {
		interactor.printMessage("trade out"); // System.out.print("What item would you like to obtain (Wood [W],
		// Molasses [M], Gold [G], or Cutlass [C])? ");
		String trade_out = interactor.takeInAnswer();
		if (validResourceCheck(trade_out)) { // check if trade_out is valid option
			interactor.printMessage("trade in"); // System.out.print("What item would you like to trade in (Wood [W], Molasses [M], Gold [G], or Cutlass [C])? ");
			String trade_in = interactor.takeInAnswer();
			if(validResourceCheck(trade_in)) {
				if (player.checkPocketResourcesLetter(trade_in) > 1) { // user needs to have more than 2 of the resource for a swap with stockpile
					stockpile.SwapStockpile(assignResourcesType(trade_out), assignResourcesType(trade_in), player); // call stockpile swap method
					System.out.println(player.viewPocket()); // show user their pocket
				} else {
					interactor.printMessage("cannot trade");
				}
				
			} 
		}
	}
	
	
	//-----------------------------------------------------------
	//---------- Method: assign resources to Resource types------
	//-----------------------------------------------------------
	public Resources assignResourcesType(String resource_letter) {
		if (resource_letter.equals("W")) {
			return Resources.Wood;
		} else if (resource_letter.equals("M")) {
			return Resources.Molasses;
		} else if (resource_letter.equals("GT")) {
			return Resources.Goats;
		} else if (resource_letter.equals("G")) {
			return Resources.Gold;
		} else {
			return Resources.Cutlasses;
		}
	}
	
	
	//-----------------------------------------------------------
	//---------- Method: validResourceCheck ---------------------
	//-----------------------------------------------------------
	public boolean validResourceCheck(String enteredResource){
		// If entered resource is  a valid resource return true, otherwise return false
		if (enteredResource.equals("M") || enteredResource.equals("W") || enteredResource.equals("C") || enteredResource.equals("G") || enteredResource.equals("GT")) {
			return true;
		} else {
			interactor.printMessage("invalid resource");
			return false;
		}
		
	}
	
	// Methods not needed anymore:
	//-----------------------------------------------------------
	//---------- Method: TradeIn --------------------------------
	//-----------------------------------------------------------
//	public String tradeIn() {
//		tradeInValid = false;
//		while(tradeInValid == false) {
//			interactor.printMessage("trade in");
//			tradeInItem = interactor.takeInAnswer();
//			tradeInValid = validResourceCheck(tradeInItem);
//		}
//		
//		tradeInResource = assignResources(tradeInItem);	// Assign the word value to the letter entered
//		interactor.printMessage("trade in resource", tradeInResource );
//		return tradeInResource;	// Return the selected resource to the user
//	}
	
	
	//-----------------------------------------------------------
	//---------- Method: TradeOut --------------------------------
	//-----------------------------------------------------------
//	public String tradeOut() {
//		tradeOutValid = false;
//		while(tradeOutValid == false) {
//			interactor.printMessage("trade out");
//			tradeOutItem = interactor.takeInAnswer();
//			tradeOutValid = validResourceCheck(tradeOutItem);
//		}
//		
//		tradeOutResource = assignResources(tradeOutItem);	// Assign the word value to the letter entered
//		interactor.printMessage("trade out resource", tradeOutResource );
//		return tradeOutResource;	// Return the selected resource to the user
//	}
	
	//-----------------------------------------------------------
	//---------- Method: assign resources to letters ------------
	//-----------------------------------------------------------
//	public String assignResources(String resourceLetter) {
//		if(resourceLetter.equals("W")) {
//			return "Wood";
//		} else if (resourceLetter.equals("M")) {
//			return "Molasses";
//		} else if(resourceLetter.equals("G")) {
//			return "Gold";
//		} else if(resourceLetter.equals("GT")) {
//			return "Goat";
//		} else {
//			return "Cutlass";
//		}
//	}
	

	
}
	
	
	



	// During turn, can be checking to see if they have won? Don't know where this happens 
	
	//----------------------------------------------------------
	//---------- "Your-turn" method ----------------------------
	//----------------------------------------------------------
	
	// Notes: This method gives the user a choice of what actions they want to take on their turn
	// We will need to make something in the main class or something that loops through the players turns

	