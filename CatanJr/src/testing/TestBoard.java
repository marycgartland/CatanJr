package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import board.*;
import gameplay.*;
import main.*;
import player.*;
import resources.*;
import setup.*;


class TestBoard {
	



	// ---------- 
	//@Before
	//public void setUp() throws Exception{
	//	// SetUp players for testing
	//	ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
	//	//Player player1 = new Player("testPlayer1", "Blue");
	//	testPlayerList.add(new Player("testPlayer1", "Blue"));
	//	testPlayerList.add(new Player("testPlayer2", "Red"));
	//	testPlayerList.add(new Player("testPlayer3", "White"));
	//	BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
	//	Board board = new Board();
	//	board = testboardSetup.getBoard();
	//}
	
	
	
	// --------------- Testing mostCocotiles method ---------------
	
	// Test that if one player has the most cocotiles, they will get a lair in the center (if the GC isnt there)
	@Test
	void testMostCocotilesPlayer() {
		// SetUp players for testing
		ArrayList<Player> testPlayerList=new ArrayList<Player>();  // Set up test players 
		//Player player1 = new Player("testPlayer1", "Blue");
		testPlayerList.add(new Player("testPlayer1", "Blue"));
		testPlayerList.add(new Player("testPlayer2", "Red"));
		testPlayerList.add(new Player("testPlayer3", "White"));
		BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
		Board testBoard = new Board();
		testBoard = testboardSetup.getBoard();
		testBoard.moveGC(); 										// Remove GC from centre
		testPlayerList.get(0).addCocoTile();						// Add cocotiles to players
		testPlayerList.get(0).addCocoTile();
		testPlayerList.get(1).addCocoTile();
		testBoard.mostCocotiles(testPlayerList);
		// compare the letter expected at the center and the actual letter at the centre
		char boardCentre = testBoard.getBoardDesign()[8][18];
		assertEquals('B', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'B'
	}
	
	// Test that if one player has the most cocotiles, their lair won't be placed in the center if the GC is there
		@Test
		void testMostCocotilesPlayerGC() {
			// Setup
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  	// Set up test players 
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			char[][] design = testBoard.getBoardDesign();
			testPlayerList.get(0).addCocoTile();						// Add cocotiles to players 
			testPlayerList.get(0).addCocoTile();
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// Compare the letter expected at the center and the actual letter at the centre
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('G', boardCentre, "Most cocotiles, but GC is in centre");
		}
		
	// Test if the player with the most cocotiles changes, the lair in the center changes 
		@Test
		void testMostCocotilesSwitchPlayer() {
			// SetUp players for testing
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  		// Set up test players 
			//Player player1 = new Player("testPlayer1", "Blue");
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);		// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			testBoard.moveGC(); 											// Remove GC from centre
			testPlayerList.get(0).addCocoTile();							// Add cocotiles to players
			testBoard.mostCocotiles(testPlayerList);
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('B', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'B'
			testPlayerList.get(1).addCocoTile();
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// compare the letter expected at the center and the actual letter at the centre
			boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals('R', boardCentre, "Most cocotiles, lair in centre"); // Expecting 'R'
		}
		
	// If 2 players have the same number of cocotiles, no cocotiles in the middle
		@Test
		void testMostCocotilesEqualPlayers() {
			// Setup
			ArrayList<Player> testPlayerList=new ArrayList<Player>();  	// Set up test players 
			testPlayerList.add(new Player("testPlayer1", "Blue"));
			testPlayerList.add(new Player("testPlayer2", "Red"));
			testPlayerList.add(new Player("testPlayer3", "White"));
			BoardSetup testboardSetup = new BoardSetup(testPlayerList);	// Set up a test board
			Board testBoard = new Board();
			testBoard = testboardSetup.getBoard();
			testBoard.moveGC(); 
			testPlayerList.get(0).addCocoTile();						// Add cocotiles to players 
			testPlayerList.get(1).addCocoTile();
			testBoard.mostCocotiles(testPlayerList);
			// Compare the letter expected at the center and the actual letter at the centre
			char boardCentre = testBoard.getBoardDesign()[8][18];
			assertEquals(' ', boardCentre, "Equal most cocotiles, neither lair in center");
		}

}


// Method: reduceLairCount()
// Method: placeLairMostCocotile
// Method: moveGhostCaptain()
// Method: Method: placeLairShip
// Method: Method: islandResourceDistribution()

	//--------------------------------------------------------------------------------------------------
	// ---------- Method: mostCocotiles() --------------------------------------------------------------------
	// Determines which player has the most cocotiles. If 'G' is no longer at the centre (design[8][18]), the 
	// user with the most cocotiles can add a lair there.
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public void mostCocotiles(ArrayList<Player> players) { int max = 0; char
	 * maxchar = ' '; if (design[8][18] != 'G') { // Check that the GC isn't at
	 * centre of board for (int i = 0; i <= players.size() - 1; i++) { // ---- If
	 * player has >= cocotiles to another - remove previous players lair and reduce
	 * lair count ---- if (players.get(i).getCocoTileCount() > max) { // If player
	 * has most cocotiles - lair to center reduceLairCount(maxchar, players); max =
	 * players.get(i).getCocoTileCount(); maxchar =
	 * players.get(i).getColour().charAt(0); } else if
	 * (players.get(i).getCocoTileCount() == max) { // If no player has max, no lair
	 * at center reduceLairCount(maxchar, players); maxchar = ' '; } }
	 * placeLairMostCocotile(maxchar, players); // Place lair for player with most
	 * cocotiles } showBoardLayout(); // Display new board layout for users }
	 */

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: reduceLairCount --------------------------------------------------------------------
	// Finds player and reduces their lair count by 1. If a new player has the most cocotiles, remove the 
	// previous players lair and reduce the #
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public void reduceLairCount(char colour, ArrayList<Player> players) { for
	 * (int i = 0; i <= players.size() - 1; i++) { if
	 * (players.get(i).getColour().charAt(0) == colour) {
	 * players.get(i).removeLair(); } } }
	 */

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: placeLairMostCocotile --------------------------------------------------------------
	// Places lair on spooky island based on who has the most cocotiles
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public void placeLairMostCocotile(char player_colour, ArrayList<Player>
	 * players) { design[8][18] = Character.toUpperCase(player_colour); for (int i =
	 * 0; i <= players.size() - 1; i++) { if (players.get(i).getColour().charAt(0)
	 * == player_colour) { players.get(i).addLair(); } } }
	 */

	// -------------------------------------------------------------------------------------------------------
	// ---------- moveGhostCaptain() -------------------------------------------------------------------------
	// Moves the ghost captain to another island
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public void moveGhostCaptain() { boolean integer_given = false; while
	 * (!integer_given) { interactor.printMessage("move ghost captain"); // Ask user
	 * which island to move GC to
	 * showIslandNumberLayout(ghostCaptain.getGhostCaptainLocation()); // Replace
	 * island centers with their #'s String number = interactor.takeInAnswer(); //
	 * Take in user input List<String> comparison_list =
	 * Arrays.asList(island_numbers); if (comparison_list.contains(number)) { // If
	 * input is a valid island # ...
	 * ghostCaptain.updateLocationGC(Integer.parseInt(number)); // Update GC
	 * location for (int i = 0; i <= 17 - 1; i++) { for (int j = 0; j <= 38 - 1;
	 * j++) { if (design[i][j] == 'G') { design[i][j] = ' ';
	 * possibleLocationGhostCaptain(Integer.parseInt(number)); // Place 'G' at
	 * specificed location } } } interactor.printMessage("GC moved"); // Confirm GC
	 * has been moved showBoardLayout(); // Display new board layout integer_given =
	 * true; // End loop } else { // If island is invalid ...
	 * interactor.printMessage("invalid island"); integer_given = false; } } }
	 */

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: placeLairShip ----------------------------------------------------------------------
	// ---- Place a users lair or ship on the board ----------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public boolean placeLairShip(Player player, String shiplair_choice) { boolean
	 * continue_turn = false; boolean integer_given = false; int count = 0; char
	 * ship_lair_option = ' '; for (int i = 0; i <= 17 - 1; i++) { for (int j = 0; j
	 * <= 38 - 1; j++) { // ----- To build a ship, must search for available lair
	 * positions ---- if (shiplair_choice.equals("S")) { ship_lair_option =
	 * Character.toUpperCase(player.getColour().charAt(0)); } // ----- To build a
	 * lair, must search for available ship positions ---- else if
	 * (shiplair_choice.equals("L")) { ship_lair_option =
	 * player.getColour().charAt(0); } if (design[i][j] == ship_lair_option) { for
	 * (int p = j - 3; p <= j + 3; p++) { // ------ Place a ship
	 * ----------------------------------- if (shiplair_choice.equals("S")) { //
	 * ----- Check row above for available ship place --------------- if (design[i -
	 * 1][p] == '\\' || design[i - 1][p] == '/' || design[i - 1][p] == '|') {
	 * symbolHolder[count] = design[i - 1][p]; design[i - 1][p] =
	 * options_string[count].charAt(0); count = count + 1; } // ----- Check row
	 * below for available ship place --------------- else if (design[i + 1][p] ==
	 * '\\' || design[i + 1][p] == '/' || design[i + 1][p] == '|') {
	 * symbolHolder[count] = design[i + 1][p]; design[i + 1][p] =
	 * options_string[count].charAt(0); count = count + 1; } } // ------ Place a
	 * lair ----------------------------------- else if
	 * (shiplair_choice.equals("L")) { // ----- Check row above for available lair
	 * place --------------- if (design[i - 1][p] == 'X') { design[i - 1][p] =
	 * options_string[count].charAt(0); count = count + 1; } // ----- Check row
	 * below for available lair place --------------- else if (design[i + 1][p] ==
	 * 'X') { design[i + 1][p] = options_string[count].charAt(0); count = count + 1;
	 * } } } } } } // ----- If options available, let user decide where to build
	 * their lair/ship // -------- if (count > 0) { while (!integer_given) { //
	 * ----- Interact with user to determine lair/ship placement -----------------
	 * showBoardLayout(); interactor.printMessage("build ship/lair option",
	 * shiplair_choice); String location_number = interactor.takeInAnswer(); //
	 * ----- If valid input, place ship, take out cost ---------------------- if
	 * (Character.isDigit(location_number.charAt(0))) { if
	 * (Integer.parseInt(location_number) > 0 && Integer.parseInt(location_number)
	 * <= count) { for (int i = 0; i <= 17 - 1; i++) { for (int j = 0; j <= 38 - 1;
	 * j++) { if (shiplair_choice.equals("S")) { if (design[i][j] ==
	 * location_number.charAt(0)) { // ----- Place ship ------- design[i][j] =
	 * player.getColour().charAt(0); interactor.printMessage("ship built"); // -----
	 * Take out cost ----- player.removeResource(Resources.Wood, 1);
	 * player.removeResource(Resources.Goats, 1); } else if (design[i][j] == '1' ||
	 * design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4' ||
	 * design[i][j] == '5') { // replace numbers with // slashs design[i][j] =
	 * symbolHolder[Character.getNumericValue(design[i][j]) - 1]; } } else if
	 * (shiplair_choice.equals("L")) { if (design[i][j] ==
	 * location_number.charAt(0)) { // ----- Place lair
	 * ----------------------------------------- design[i][j] =
	 * Character.toUpperCase(player.getColour().charAt(0)); // ----- Take out cost
	 * (wood, custlass, molasses,goat) ------- player.removeResource(Resources.Wood,
	 * 1); player.removeResource(Resources.Goats, 1);
	 * player.removeResource(Resources.Cutlasses, 1);
	 * player.removeResource(Resources.Molasses, 1);
	 * interactor.printMessage("lair built"); } else if (design[i][j] == '1' ||
	 * design[i][j] == '2' || design[i][j] == '3' || design[i][j] == '4' ||
	 * design[i][j] == '5') { // replace numbers with X's // again design[i][j] =
	 * 'X'; } } } } showBoardLayout(); integer_given = true; } else { // If user
	 * input is invalid, cannot place lair/ship
	 * interactor.printMessage("invalid option"); integer_given = false; } } else {
	 * // If user input is invalid, cannot place lair/ship
	 * interactor.printMessage("invalid option"); integer_given = false; } } } else
	 * { // If no space for a lair/ship, cannot place it
	 * interactor.printMessage("no ships/lairs", shiplair_choice); } return
	 * continue_turn = true; }
	 */

	// -------------------------------------------------------------------------------------------------------
	// ---------- Method: islandResourceDistribution() -------------------------------------------------------
	// Checks islands according to the value of the dice roll, and calls the checkArray method
	// -------------------------------------------------------------------------------------------------------
	/*
	 * public void islandResourceDistribution(int diceValue, Island[] islands,
	 * ArrayList<Player> players) { if (diceValue == 1) { // ---------- Roll a 1 -
	 * Islands 1, 3 and 10 ----------- islands[0].checkArray(players,
	 * Resources.Cutlasses, design[4][9], design); // Island 1, Assign: Cutlasses
	 * islands[2].checkArray(players, Resources.Goats, design[4][21], design); //
	 * Island 3, Assign: goat islands[9].checkArray(players, Resources.Wood,
	 * design[12][15], design); // Island 10, Assign: wood } else if (diceValue ==
	 * 2) { // ---------- Roll a 2 - Islands 2, 4, and 11 ----------
	 * islands[1].checkArray(players, Resources.Wood, design[4][15], design); //
	 * Island 2, Assign: wood islands[3].checkArray(players, Resources.Molasses,
	 * design[4][27], design); // Island 4, Assign: molasses
	 * islands[10].checkArray(players, Resources.Goats, design[12][21], design); //
	 * Island 11, Assign: goat } else if (diceValue == 3) { // ---------- Roll a 3 -
	 * islands 5 and 7 --------------- islands[4].checkArray(players,
	 * Resources.Wood, design[8][6], design); // Island 5, Assign: wood
	 * islands[6].checkArray(players, Resources.Gold, design[8][24], design); //
	 * Island 7, Assign: gold } else if (diceValue == 4) { // ---------- Roll a 4 -
	 * islands 9 and 12 -------------- islands[8].checkArray(players,
	 * Resources.Cutlasses, design[12][9], design); // Island 9, Assign: Cutlass
	 * islands[11].checkArray(players, Resources.Molasses, design[12][27],
	 * design);// Island 12, Assign: molasses } else if (diceValue == 5) { //
	 * ---------- Roll a 5 - islands 6 and 8 ---------------
	 * islands[5].checkArray(players, Resources.Gold, design[8][12], design); //
	 * Island 6, Assign: gold islands[7].checkArray(players, Resources.Goats,
	 * design[8][30], design); // Island 8, Assign: goat } else { // ---------- Roll
	 * a 6 - ghost captain ----------------- moveGhostCaptain(); } } }
	 */
