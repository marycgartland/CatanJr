package gameplay;

// -----------------------------------------------------------------------------------------------------------
// This class deals with the turns of players. It gives them options to take on each turn, and calls the 
// methods required for their chosen action to be taken. It also gives players a chance to view what 
// resources/cocotiles etc. they have. Finally, it can also end the game if a player has won. 
// -----------------------------------------------------------------------------------------------------------

import java.util.ArrayList;

import board.Board;
import board.GhostCaptain;
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
	private int marketPlaceUse;			// Number of times the marketplace has been used this turn
	private boolean turn; 				// Is 'true' when taking turn, and 'false' when finished turn
	private int diceValue; 				// Value rolled on the dice
	private Player player; 				// Player taking the turn
	private String toBuild; 			// User input answer
	private String toBuy; 				// User input answer
	protected Marketplace marketplace; 	// Take in marketplace that has been created
	protected Stockpile stockpile; 		// Take in stockpile that has been created
	protected CocoTiles cocotiles; 		// Take in cocotiles that have been created
	protected Board board; 				// Take in board that has been created
	protected GhostCaptain ghostCaptain = GhostCaptain.getInstance();

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

	// ------------------------------------------------------------------------
	// ---------- Method: takeTurn --------------------------------------------
	// ------------------------------------------------------------------------
	public void takeTurn(Island[] islands, ArrayList<Player> players, Dice dice) {
		// ----- Roll the dice ----------------------------------------------
		diceValue = dice.rollDice();
		interactor.printMessage("Player roll", player.PlayerName(), diceValue);
		// ----- Resource distribution from islands based on dice roll ------
		//board.islandResourceDistribution(diceValue, islands, players);
		if(diceValue!=6) {
			board.islandResourceDistribution(diceValue, islands, players);
		} else {
			board.showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation()); 	// Replace island centers with their #'s
			interactor.printMessage("move ghost captain"); 							// Ask user which island to move GC to
			String number = interactor.takeInAnswer(); 								// Take in user input
			String message = board.moveGhostCaptain(number);
			interactor.printMessage(message);
		}
		
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
			} else if (option.equals("P")) {			// If player wishes to view their pocket
				interactor.printMessage("turn: view pocket");
				viewPocket();				
			}
			else {										// Error check - ensure choice entered is valid
				interactor.printMessage("turn: null");
			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: buy() -----------------------------------------------
	// This method allows players buy cocotiles. If a player wishes to take
	// this option, they get a cocotile and the instructions on the tile are 
	// followed.
	// ------------------------------------------------------------------------
	public void buy() {
		// ----- Display cocotile price to user ---------------------------------
		interactor.printMessage("cocotile price");
		// ----- If the player has enough resources they can buy a cocotile -----
		if (player.checkPocketResources("C") >= 1 && player.checkPocketResources("G") >= 1 && player.checkPocketResources("M") >= 1) {
			// ----- Give player a chance to confirm they wish to buy a cocotile -----
			interactor.printMessage("buy cocotile?");	
			toBuy = interactor.takeInAnswer();
			if (toBuy.equals("Y") || toBuy.equals("y")) {	
				interactor.printMessage("bought cocotile");	
				// ----- Remove cost from users pocket --------------------------------
				player.removeResource(Resources.Cutlasses, 1);
				player.removeResource(Resources.Gold, 1);
				player.removeResource(Resources.Molasses, 1);
				// ----- Return resources to stockpile when cocotile is bought --------
				stockpile.returnResource(Resources.Cutlasses, 1);
				stockpile.returnResource(Resources.Gold, 1);
				stockpile.returnResource(Resources.Molasses, 1);
				// ----- Cocotile bought ----------------------------------------------
				CocoTileTypes cocotile_bought = cocotiles.buyCocoTile(); 			// Indicate type of cocotile
				player.addCocoTile(); 												// Add cocotile to players cocotile count
				if (cocotile_bought == null) {
					interactor.printMessage("no cocotiles");						// Tell user there are no cocotiles to be bought
				} else {
					interactor.printMessage("Cocotile bought", cocotile_bought);	// Confirm to user to cocotile they have received
				}
				cocotileAction(cocotile_bought); 									// Follow instructions on cocotile
			}
		} 
		// ----- Error message if the user does not have resources needed -------
		else { 
			interactor.printMessage("cannot buy cocotile");
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: cocotileAction() ------------------------------------
	// This method completes actions based on cocotile bought by player
	// ------------------------------------------------------------------------
	public void cocotileAction(CocoTileTypes cocotile) {
		if (cocotile.equals(CocoTileTypes.GoatCutlasses)) {			// The goat and cutlass cocotile
			// ----- Add resource to users pocket --------
			player.addResource(Resources.Goats, 2);
			player.addResource(Resources.Cutlasses, 2);
			// ----- Remove resources from stockpile -----
			stockpile.distributeResource(Resources.Goats, 2);
			stockpile.distributeResource(Resources.Cutlasses, 2);
			// ----- Display pocket to player ------------
			viewPocket();
		} else if (cocotile.equals(CocoTileTypes.WoodMolasses)) {	// The wood and molasses cocotile
			// ----- Add resources to users pocket -------
			player.addResource(Resources.Wood, 2);
			player.addResource(Resources.Molasses, 2);
			// ----- Remove resources from stockpile -----
			stockpile.distributeResource(Resources.Wood, 2);
			stockpile.distributeResource(Resources.Molasses, 2);
			// ----- Display pocket to player ------------
			viewPocket();
		} else if (cocotile.equals(CocoTileTypes.GhostCaptain)) {	// The ghost captain cocotile
			board.showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation()); 	// Replace island centers with their #'s
			interactor.printMessage("move ghost captain"); 							// Ask user which island to move GC to
			String number = interactor.takeInAnswer(); 									// Take in user input
			String message = board.moveGhostCaptain(number);
			interactor.printMessage(message);
		} else if (cocotile.equals(CocoTileTypes.ShipCastle)) {		// The ship/lair cocotile
			// ----- Give user ship or lair option -------
			interactor.printMessage("cocotile ship/lair");
			String build_option = interactor.takeInAnswer();
			// ----- Build lair or ship ------------------
			if (build_option.charAt(0) == 'L') {
				board.placeLairShip(player,build_option); 			// Build Lair
			} else if (build_option.charAt(0) == 'S') {
				board.placeLairShip(player,build_option); 			// Build ship
			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Build Method ------------------------------------------------
	// The user can either build a ship or a lair.
	// Cost of a Lair = 1 cutlasses, 1 molasses, 1 goat & 1 wood
	// Cost of a ship = 1 goat & 1 wood
	// ------------------------------------------------------------------------
	public void build() {
		interactor.printMessage("ship/lair price");
		// ----- If the user has the resources to build either, provide both options -----
		if (player.checkPocketResources("C") >= 1 && player.checkPocketResources("W") >= 1 && player.checkPocketResources("GT") >= 1 && player.checkPocketResources("M") >= 1) {
			interactor.printMessage("build ship/lair");				 // Give user option of building a ship or lair
			toBuild = interactor.takeInAnswer(); 					 // Scan in user response
			if (toBuild.equals("S") || toBuild.equals("s")) {		 // Build ship
				buildLairShip("S");								
			} else if (toBuild.equals("L") || toBuild.equals("l")) { // Build Lair
				buildLairShip("L");
			}
		} // ----- If the user only has the resources to build a ship --------------------
		else if (player.checkPocketResources("W") >= 1 && player.checkPocketResources("GT") >= 1) {
			interactor.printMessage("build ship?");					 // Ask user to confirm they wish to buy a ship
			toBuild = interactor.takeInAnswer(); 					 // Scan in user input
			if (toBuild.equals("Y") || toBuild.equals("y")) {		 // Build ship
				buildLairShip("S");					
			}
		} // ----- If they don't have enough resources to build either -------------------
		else { 
			interactor.printMessage("cannot buy ship/lair");		 // Inform user they do not have enough resources
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: buildLairShip() -------------------------------------
	// ------------------------------------------------------------------------
	public void buildLairShip(String toBuild) {
		if (toBuild.equals("S")) {
			interactor.printMessage("build: ship"); // Indicate actions to user
			turn = false; // Pause turn until lair is placed
			turn = board.placeLairShip(player, toBuild);
		} else if (toBuild.equals("L")) {
			interactor.printMessage("build: lair"); // Indicate actions to user
			turn = false; // Pause turn until lair is placed
			turn = board.placeLairShip(player, toBuild);
		}
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
			interactor.printMessage("View Marketplace", marketplace.viewMarketplace()); // Let user view marketplace options			
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
		// ----- Determine what resource user wishes to obtain  - check if their request is in stock -----
		interactor.printMessage("trade out"); 
	String trade_out = interactor.takeInAnswer();
		interactor.printMessage("trade out resource", trade_out);
		if (validResourceCheck(trade_out)) { 															
			boolean in_stock = marketplace.checkForResourceMarketplace(assignResourcesType(trade_out)); 
			if (in_stock) {
				interactor.printMessage("marketplace: in-stock");
				// ----- Determine what resource user wishes to trade in  - check if they have resource in pocket -----
				interactor.printMessage("trade in"); 
				String trade_in = interactor.takeInAnswer();
				interactor.printMessage("trade in resource", trade_in);
				if (validResourceCheck(trade_in)) { 
					if (player.checkPocketResources(trade_in) > 0) { 
						// ----- Make the swap, and increment marketPlaceUse variable to indicate the trade is used (1 per turn) ------
						marketPlaceUse = 1; 
						String message = marketplace.swapMarketplace(assignResourcesType(trade_out), assignResourcesType(trade_in), player);
						interactor.printMessage(message);
						viewPocket();
					} else {	// ----- Cannot trade if pocket doesn't have resource -----
						interactor.printMessage("cannot trade");
					}
				}	
			} else {	// ----- Cannot trade if the marketplace is out of stock -----
				interactor.printMessage("marketplace: out-of-stock");
			}
		}
	}

	// ------------------------------------------------------------------------
	// ---------- Method: tradeStockpile --------------------------------------
	// Method to allow a player trade with the stockpile
	// ------------------------------------------------------------------------
	public void tradeStockpile() {
		// ----- Determine what user wishes to trade -----
		interactor.printMessage("trade out"); 			// Give user option of what to trade out of pocket
		String trade_out = interactor.takeInAnswer();	// Take in user input 
		if (validResourceCheck(trade_out)) { 			// Check if trade_out is valid option
			interactor.printMessage("trade in"); 		// Give user option of what they want to obtain
			String trade_in = interactor.takeInAnswer();// Take in user input 
			if (validResourceCheck(trade_in)) {			// Check that the user entered a valid resource
				// ------ User needs 2+ of the resource to swap with stockpile ------
				if (player.checkPocketResources(trade_in) > 1) { 
					stockpile.swapStockpile(assignResourcesType(trade_out), assignResourcesType(trade_in), player); 
					viewPocket();
				} else { // ----- Otherwise, cannot trade -----
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
	
	// ------------------------------------------------------------------------
	// ---------- Method: viewPocket ------------------------------------------
	// Method to allow player to view their pocket
	// ------------------------------------------------------------------------
	public void viewPocket() {
		interactor.printPocket(player);
	}
	
	// ------------------------------------------------------------------------
	// ---------- Method: endGame ---------------------------------------------
	// ---- This ends game if winner has been found
	// ------------------------------------------------------------------------
	public void endGame() {
		System.exit(0); 
	}
}
