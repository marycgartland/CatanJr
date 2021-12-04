package gameplay;

import java.util.ArrayList;

import board.Board;
import board.Island;
import board.Marketplace;
import board.Stockpile;
import main.Interactor;
import player.Player;
import resources.*;

public class PlayerTurn {
	// ------------------------------------------------------------------------
	// ---------- Variables ---------------------------------------------------
	// ------------------------------------------------------------------------
	private int marketPlaceUse; // Number of times the marketplace has been used this turn
	private boolean turn; // Is 'true' when taking turn, and 'false' when finished turn
	private int diceValue; // Value rolled on the dice
	private Player player; // Player taking the turn
	private String toBuild; // User input answer
	private String toBuy; // User input answer
	protected Marketplace marketplace; // Take in marketplace that has been created
	protected Stockpile stockpile; // Take in stockpile that has been created
	protected CocoTiles cocotiles; // Take in cocotiles that have been created
	protected Board board; // Take in board that has been created

	// ------------------------------------------------------------------------
	// ----------- Constructor ------------------------------------------------
	// ------------------------------------------------------------------------
	public PlayerTurn(Player thisPlayer, Marketplace marketplace, Stockpile stockpile, CocoTiles cocotiles, Board board) {
		this.player = thisPlayer;
		this.marketplace = marketplace;
		this.stockpile = stockpile;
		this.cocotiles = cocotiles;
		this.board = board;
		this.marketPlaceUse = 0; 	// Initialize the marketplace use at 0 for each new turn
		this.turn = true; 			// This person is currently taking their turn
	}

	// ------------------------------------------------------------------------
	// ---------- Create objects ----------------------------------------------
	// ------------------------------------------------------------------------
	Interactor interactor = new Interactor(); 	// Create interactor object
	Dice dice1 = new Dice(); 					// Create dice object

	// ------------------------------------------------------------------------
	// ---------- Method: takeTurn --------------------------------------------
	// ------------------------------------------------------------------------
	public void takeTurn(Island[] islands, ArrayList<Player> players) {
		// ----- Roll the dice ----------------------------------------------
		diceValue = dice1.rollDice();
		interactor.printMessage("Player roll", player.PlayerName(), diceValue);

		// ----- Resource distribution from islands based on dice roll ------
		board.islandResourceDistribution(diceValue, islands, players);

		// ----- Present users with options while it is their turn ------------
		while (turn == true) {
			interactor.printMessage("your turn");		// Provide user options and take in their choice
			String option = interactor.takeInAnswer();	
			if (option.equals("B")) {					// If player wishes to buy	
				interactor.printMessage("turn: buy");
				buy();
			} else if (option.equals("Bd")) {			// If player wishes to build
				interactor.printMessage("turn: build");
				build();
			} else if (option.equals("T")) {			// If player wishes to trade
				interactor.printMessage("turn: trade");
				Trade();
			} else if (option.equals("E")) {			// If player wishes to end turn
				interactor.printMessage("turn: end");
				turn = false;
			} else {									// Error check - ensure choice entered is valid
				interactor.printMessage("turn: null");
			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Buy Method --------------------------------------------------
	// This method allows players buy cocotiles. If a player wishes to take
	// this option, they get a cocotile and the instructions on the tile are 
	// followed.
	// ------------------------------------------------------------------------
	public void buy() {
		// Display cocotile price to user
		interactor.printMessage("cocotile price");
		
		// If the player has enough resources they can buy a cocotile
		if (player.getCutlassesCount() >= 1 && player.getGoldCount() >= 1 && player.getMolassesCount() >= 1) {
			
			// Give player a chance to confirm they wish to buy a cocotile
			interactor.printMessage("buy cocotile?");	
			toBuy = interactor.takeInAnswer();			
			if (toBuy.equals("Y") || toBuy.equals("y")) {	
				interactor.printMessage("bought cocotile");	
				
				// Remove cost from users pocket
				player.removeResource(Resources.Cutlasses, 1);
				player.removeResource(Resources.Gold, 1);
				player.removeResource(Resources.Molasses, 1);
				
				// Return resources to stockpile when cocotile is bought
				stockpile.ReturnResource(Resources.Cutlasses, 1);
				stockpile.ReturnResource(Resources.Gold, 1);
				stockpile.ReturnResource(Resources.Molasses, 1);
				
				// Cocotile bought
				CocoTileTypes cocotile_bought = cocotiles.buyCocoTile(); 	// Indicates type of cocotile
				player.addCocoTile(); 										// Add cocotile to players cocotile count
				interactor.printMessage("Cocotile bought", cocotile_bought);// Confirm to user
				cocotileAction(cocotile_bought); 							// Follow instructions on cocotile
			}
		} 
		
		// Error message if the user does not have resources needed
		else { 
			interactor.printMessage("cannot buy: cocotile");
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Buy cocotileAction ------------------------------------------
	// This completes actions based on cocotile bought by player
	// ------------------------------------------------------------------------
	public void cocotileAction(CocoTileTypes cocotile) {
		if (cocotile.equals(CocoTileTypes.GoatCutlasses)) {			// The goat and cutlass cocotile
			
			// Add resource to users pocket
			player.addResource(Resources.Goats, 2);
			player.addResource(Resources.Cutlasses, 2);
			
			// Remove resources from stockpile
			stockpile.DistributeResource(Resources.Goats, 2);
			stockpile.DistributeResource(Resources.Cutlasses, 2);
			
			// Display pocket to player
			interactor.printPocket(player);
			
		} else if (cocotile.equals(CocoTileTypes.WoodMolasses)) {	// The wood and molasses cocotile
			// Add resources to users pocket
			player.addResource(Resources.Wood, 2);
			player.addResource(Resources.Molasses, 2);
			
			// Remove resources from stockpile
			stockpile.DistributeResource(Resources.Wood, 2);
			stockpile.DistributeResource(Resources.Molasses, 2);
			
			// Display pocket to player
			interactor.printPocket(player);
			
		} else if (cocotile.equals(CocoTileTypes.GhostCaptain)) {	// The ghost captain cocotile
			board.moveGhostCaptain();	// move the GC
			
		} else if (cocotile.equals(CocoTileTypes.ShipCastle)) {		// the ship/lair cocotile
			
			// Give user ship or lair option
			interactor.printMessage("cocotile ship/lair");
			String build_option = interactor.takeInAnswer();
			
			// Build lair or ship
			if (build_option.charAt(0) == 'L') {
				board.placeLair(player);	// Build Lair
			} else if (build_option.charAt(0) == 'S') {
				board.placeShip(player);	// Build ship

			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Build Method ------------------------------------------------
	// The user can either build a ship or a lair.
	// Cost of a Lair = 1 cutlass, 1 molasses, 1 goat & 1 wood
	// Cost of a ship = 1 goat & 1 wood
	// ------------------------------------------------------------------------
	public void build() {
		interactor.printMessage("ship/lair price");
		// If the user has the resources to build either, provide both options
		if (player.getCutlassesCount() >= 1 && player.getWoodCount() >= 1 && player.getGoatsCount() >= 1 && player.getMolassesCount() >= 1) {
			interactor.printMessage("build ship/lair");				 // Give user option of building a ship or lair
			toBuild = interactor.takeInAnswer(); 					 // Scan in user response
			if (toBuild.equals("S") || toBuild.equals("s")) {		 // Build ship
				buildShip();								
			} else if (toBuild.equals("L") || toBuild.equals("l")) { // Build Lair
				buildLair();
			}
		} 
		// If the user only has the resources to build a ship
		else if (player.getWoodCount() >= 1 && player.getGoatsCount() >= 1) {
			interactor.printMessage("build ship?");					 // Ask user to confirm they wish to buy a ship
			toBuild = interactor.takeInAnswer(); 					 // Scan in user input
			if (toBuild.equals("Y") || toBuild.equals("y")) {		 // Build ship
				buildShip();					
			}
		} 
		// If they don't have enough resources to build either
		else { 
			interactor.printMessage("cannot buy ship/lair");		 // Inform user they do not have enough resources
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Build Ship Method -------------------------------------------
	// ------------------------------------------------------------------------
	public void buildShip() {
		interactor.printMessage("build: ship");	// Indicate actions to user
		turn = false; 							// Pause turn until lair is placed
		turn = board.placeShip(player);			// place a ship for the user on the board
	}

	// ------------------------------------------------------------------------
	// ---------- Build Lair Method -------------------------------------------
	// ------------------------------------------------------------------------
	public void buildLair() {
		interactor.printMessage("build: lair");	// Indicate actions to user
		player.addLair();						// Increment the players lair count
		turn = false; 							// Pause turn until lair is placed
		turn = board.placeLair(player);			// place a lair for the user on the board
	}

	// ------------------------------------------------------------------------
	// ---------- Trade Method ------------------------------------------------
	// ------------------------------------------------------------------------
	public void Trade() {
		// ----- Give the user options on what trade they wish to make --------------
		if (marketPlaceUse == 0) { 					// Can only trade with marketplace 1x/turn
			interactor.printMessage("trade M/S?");
		} else {
			interactor.printMessage("trade S?");
		}
		String option = interactor.takeInAnswer();	// Take in user response

		// ----- Option 1: Trade with Marketplace -----------------------------------
		// Trade one tile from a players pocket (of choice) with one tile from marketplace (of choice)
		if ((option.equals("M") || option.equals("m")) && marketPlaceUse == 0) {
			interactor.printMessage("trade: M");
			marketplace.viewMarketplace();	// Let user view marketplace options
			tradeMarketplace(); 			// Call method to trade with marketplace
		}
		
		// ----- Option 2: Trade with Stockpile -------------------------------------
		// Trade 2 equivalent pocket tiles (of choice) with 1 stockpile tile (of choice)
		// No limit on times, only limited by resources in pocket/stockpile. 
		else if (option.equals("S") || option.equals("s")) {
			interactor.printMessage("trade: S");
			tradeStockpile(); 				// Call method to trade with stockpile
		}

		// ----- Alt. option 2: Trade with Stockpile when it's the only choice ------
		else if (marketPlaceUse != 0 && (option.equals("Y") || option.equals("y"))) {
			interactor.printMessage("trade: S");
			tradeStockpile(); 				// Call method to trade with stockpile
		}

		// ----- Option 3: Choose not to trade --------------------------------------
		else if (option.equals("N") || option.equals("n")) {
			interactor.printMessage("trade: N");
		}

		// ----- Error check if the user selects an invalid option ------------------
		else {
			interactor.printMessage("turn: null");
		}
	}

	
	// ------------------------------------------------------------------------
	// ---------- Method tradeMarketplace -------------------------------------
	// This method allows a user to trade with the marketplace
	// ------------------------------------------------------------------------
	public void tradeMarketplace() {
		interactor.printMessage("trade out"); // what item does user want to obtain
		String trade_out = interactor.takeInAnswer();
		interactor.printMessage("trade out resource", trade_out);
		if (validResourceCheck(trade_out)) { // check if trade_out is vaild option
			boolean in_stock = marketplace.CheckForResourceMarketplace(assignResourcesType(trade_out)); // check if has marketplace has this in stock
			if (in_stock) {
				interactor.printMessage("marketplace: in-stock");
				interactor.printMessage("trade in"); // what item does user want to trade in
				String trade_in = interactor.takeInAnswer();
				interactor.printMessage("trade in resource", trade_in);
				if (validResourceCheck(trade_in)) { // check if this trade_in answer is valid
					if (player.checkPocketResourcesLetter(trade_in) > 0) { // check users pocket to see if they have the
																			// resources to swap
						// Can only trade with Marketplace once per turn
						marketPlaceUse = 1; // successful trade with marketplace
						marketplace.SwapMarketplace(assignResourcesType(trade_out), assignResourcesType(trade_in),
								player);
						interactor.printPocket(player);
					} else {
						interactor.printMessage("cannot trade");
					}
				}
			} else {
				interactor.printMessage("marketplace: out-of-stock");
			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: tradeStockpile --------------------------------------
	// Method to allow a player trade with the stockpile
	// ------------------------------------------------------------------------
	public void tradeStockpile() {
		interactor.printMessage("trade out"); 			// Give user option of what to trade out of pocket
		String trade_out = interactor.takeInAnswer();	// Take in user input 
		if (validResourceCheck(trade_out)) { 			// Check if trade_out is valid option
			interactor.printMessage("trade in"); 		// Give user option of what they want to obtain
			String trade_in = interactor.takeInAnswer();// Take in user input 
			if (validResourceCheck(trade_in)) {			// Check that the user 
				if (player.checkPocketResourcesLetter(trade_in) > 1) { // user needs to have more than 2 of the resource
																		// for a swap with stockpile
					stockpile.SwapStockpile(assignResourcesType(trade_out), assignResourcesType(trade_in), player); // call
																													// stockpile
																													// swap
																													// method
					interactor.printPocket(player);
				} else {
					interactor.printMessage("cannot trade");
				}

			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: assignResourcesType ---------------------------------
	// Assign letters to represent resource types
	// ------------------------------------------------------------------------
	public Resources assignResourcesType(String resource_letter) {
		if (resource_letter.equals("W") || resource_letter.equals("w")) {
			return Resources.Wood;
		} else if (resource_letter.equals("M") || resource_letter.equals("m")) {
			return Resources.Molasses;
		} else if (resource_letter.equals("GT") || resource_letter.equals("gt")) {
			return Resources.Goats;
		} else if (resource_letter.equals("G") || resource_letter.equals("g")) {
			return Resources.Gold;
		} else {
			return Resources.Cutlasses;
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: validResourceCheck ----------------------------------
	// Error check to confirm that a valid resource option has been entered
	// Returns true if entered option is valid, false if not 
	// ------------------------------------------------------------------------
	public boolean validResourceCheck(String enteredResource) {
		if (enteredResource.equals("M") || enteredResource.equals("m") || enteredResource.equals("W")
				|| enteredResource.equals("w") || enteredResource.equals("C") || enteredResource.equals("c")
				|| enteredResource.equals("G") || enteredResource.equals("g") || enteredResource.equals("gt")
				|| enteredResource.equals("GT")) {
			return true;
		} else {
			interactor.printMessage("invalid resource");
			return false;
		}
	}
}
